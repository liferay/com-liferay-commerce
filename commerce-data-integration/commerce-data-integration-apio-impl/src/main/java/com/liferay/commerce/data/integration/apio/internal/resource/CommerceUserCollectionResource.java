/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 */

package com.liferay.commerce.data.integration.apio.internal.resource;

import com.liferay.apio.architect.credentials.Credentials;
import com.liferay.apio.architect.functional.Try;
import com.liferay.apio.architect.pagination.PageItems;
import com.liferay.apio.architect.pagination.Pagination;
import com.liferay.apio.architect.representor.Representor;
import com.liferay.apio.architect.resource.CollectionResource;
import com.liferay.apio.architect.routes.CollectionRoutes;
import com.liferay.apio.architect.routes.ItemRoutes;
import com.liferay.commerce.data.integration.apio.identifier.ClassPKExternalReferenceCode;
import com.liferay.commerce.data.integration.apio.identifier.CommerceUserIdentifier;
import com.liferay.commerce.data.integration.apio.internal.form.CommerceUserUpserterForm;
import com.liferay.commerce.data.integration.apio.internal.model.UserWrapper;
import com.liferay.commerce.data.integration.apio.internal.util.CommerceUserHelper;
import com.liferay.portal.apio.permission.HasPermission;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.language.LanguageUtil;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.Company;
import com.liferay.portal.kernel.model.Contact;
import com.liferay.portal.kernel.model.ListType;
import com.liferay.portal.kernel.model.Role;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.security.auth.PrincipalException;
import com.liferay.portal.kernel.security.permission.PermissionChecker;
import com.liferay.portal.kernel.service.ListTypeService;
import com.liferay.portal.kernel.service.RoleService;
import com.liferay.portal.kernel.service.UserLocalService;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.workflow.WorkflowConstants;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.ws.rs.NotFoundException;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Alejandro Hernández
 * @author Rodrigo Guedes de Souza
 * @author Eduardo V. Bruno
 * @author Zoltán Takács
 */
@Component(immediate = true, service = CollectionResource.class)
public class CommerceUserCollectionResource
	implements CollectionResource
		<UserWrapper, ClassPKExternalReferenceCode, CommerceUserIdentifier> {

	@Override
	public CollectionRoutes<UserWrapper, ClassPKExternalReferenceCode>
		collectionRoutes(
			CollectionRoutes.Builder<UserWrapper, ClassPKExternalReferenceCode>
				builder) {

		return builder.addGetter(
			this::_getPageItems, Credentials.class, ThemeDisplay.class
		).addCreator(
			this::_upsertUser, ThemeDisplay.class, _hasPermission::forAdding,
			CommerceUserUpserterForm::buildForm
		).build();
	}

	@Override
	public String getName() {
		return "commerce-user-account";
	}

	@Override
	public ItemRoutes<UserWrapper, ClassPKExternalReferenceCode> itemRoutes(
		ItemRoutes.Builder<UserWrapper, ClassPKExternalReferenceCode> builder) {

		return builder.addGetter(
			this::_getUserWrapper, ThemeDisplay.class
		).addRemover(
			_commerceUserHelper::deleteUser, Company.class,
			_hasPermission::forDeleting
		).addUpdater(
			this::_updateUser, ThemeDisplay.class, _hasPermission::forUpdating,
			CommerceUserUpserterForm::buildForm
		).build();
	}

	@Override
	public Representor<UserWrapper> representor(
		Representor.Builder<UserWrapper, ClassPKExternalReferenceCode>
			builder) {

		return builder.types(
			"Person"
		).identifier(
			_commerceUserHelper::userToClassPKExternalReferenceCode
		).addDate(
			"birthDate", CommerceUserCollectionResource::_getBirthday
		).addLocalizedStringByLocale(
			"honorificPrefix", _getContactField(Contact::getPrefixId)
		).addLocalizedStringByLocale(
			"honorificSuffix", _getContactField(Contact::getSuffixId)
		).addNumberList(
			"commerceAccountIds", this::_getCommerceAccountIds
		).addRelativeURL(
			"image", UserWrapper::getPortraitURL
		).addString(
			"additionalName", User::getMiddleName
		).addString(
			"alternateName", User::getScreenName
		).addString(
			"dashboardURL", UserWrapper::getDashboardURL
		).addString(
			"email", User::getEmailAddress
		).addString(
			"externalReferenceCode", UserWrapper::getExternalReferenceCode
		).addString(
			"familyName", User::getLastName
		).addString(
			"gender", CommerceUserCollectionResource::_getGender
		).addString(
			"givenName", User::getFirstName
		).addString(
			"jobTitle", User::getJobTitle
		).addString(
			"name", User::getFullName
		).addString(
			"profileURL", UserWrapper::getProfileURL
		).addStringList(
			"roleNames", this::_getRoleNames
		).build();
	}

	private static Date _getBirthday(User user) {
		return Try.fromFallible(
			user::getBirthday
		).orElse(
			null
		);
	}

	private static String _getGender(User user) {
		return Try.fromFallible(
			user::isMale
		).map(
			male -> male ? "male" : "female"
		).orElse(
			null
		);
	}

	private List<Number> _getCommerceAccountIds(UserWrapper userWrapper) {
		List<Number> commerceAccountIds = new ArrayList<>();

		try {
			for (long organizationId : userWrapper.getOrganizationIds()) {
				commerceAccountIds.add(organizationId);
			}
		}
		catch (PortalException pe) {
			_log.error("Unable to retrieve organizations", pe);
		}

		return commerceAccountIds;
	}

	private BiFunction<UserWrapper, Locale, String> _getContactField(
		Function<Contact, Long> function) {

		return (user, locale) -> Try.fromFallible(
			user::getContact
		).map(
			function::apply
		).map(
			_listTypeService::getListType
		).map(
			ListType::getName
		).map(
			name -> LanguageUtil.get(locale, name)
		).orElse(
			null
		);
	}

	private PageItems<UserWrapper> _getPageItems(
			Pagination pagination, Credentials credentials,
			ThemeDisplay themeDisplay)
		throws PortalException {

		PermissionChecker permissionChecker =
			(PermissionChecker)credentials.get();

		if (!permissionChecker.isCompanyAdmin(themeDisplay.getCompanyId())) {
			throw new PrincipalException.MustBeCompanyAdmin(permissionChecker);
		}

		List<User> users = _userLocalService.getUsers(
			themeDisplay.getCompanyId(), false,
			WorkflowConstants.STATUS_APPROVED, pagination.getStartPosition(),
			pagination.getEndPosition(), null);

		List<UserWrapper> userWrappers = _toUserWrappers(users, themeDisplay);

		int count = _userLocalService.getUsersCount(
			themeDisplay.getCompanyId(), false,
			WorkflowConstants.STATUS_APPROVED);

		return new PageItems<>(userWrappers, count);
	}

	private List<String> _getRoleNames(UserWrapper userWrapper) {
		List<String> roleNames = new ArrayList<>();

		for (long id : userWrapper.getRoleIds()) {
			try {
				Role role = _roleService.fetchRole(id);

				roleNames.add(role.getName());
			}
			catch (PortalException pe) {
				pe.printStackTrace();
			}
		}

		return roleNames;
	}

	private UserWrapper _getUserWrapper(
			ClassPKExternalReferenceCode commerceUserCPKERC,
			ThemeDisplay themeDisplay)
		throws PortalException {

		User user = _commerceUserHelper.getUser(
			commerceUserCPKERC, themeDisplay.getCompanyId());

		if ((user == null) ||
			(themeDisplay.getDefaultUserId() == user.getUserId())) {

			throw new NotFoundException();
		}

		return new UserWrapper(user, themeDisplay);
	}

	private List<UserWrapper> _toUserWrappers(
		List<User> users, ThemeDisplay themeDisplay) {

		return Stream.of(
			users
		).flatMap(
			List::stream
		).map(
			user -> new UserWrapper(user, themeDisplay)
		).collect(
			Collectors.toList()
		);
	}

	private UserWrapper _updateUser(
			ClassPKExternalReferenceCode commerceUserCPKERC,
			CommerceUserUpserterForm commerceUserUpserterForm,
			ThemeDisplay themeDisplay)
		throws PortalException {

		return _commerceUserHelper.updateUser(
			commerceUserCPKERC, commerceUserUpserterForm, themeDisplay);
	}

	private UserWrapper _upsertUser(
			CommerceUserUpserterForm commerceUserUpserterForm,
			ThemeDisplay themeDisplay)
		throws PortalException {

		return _commerceUserHelper.upsert(
			commerceUserUpserterForm, themeDisplay);
	}

	private static final Log _log = LogFactoryUtil.getLog(
		CommerceUserCollectionResource.class);

	@Reference
	private CommerceUserHelper _commerceUserHelper;

	@Reference(target = "(name=com.liferay.commerce.user)")
	private HasPermission<ClassPKExternalReferenceCode> _hasPermission;

	@Reference
	private ListTypeService _listTypeService;

	@Reference
	private RoleService _roleService;

	@Reference
	private UserLocalService _userLocalService;

}