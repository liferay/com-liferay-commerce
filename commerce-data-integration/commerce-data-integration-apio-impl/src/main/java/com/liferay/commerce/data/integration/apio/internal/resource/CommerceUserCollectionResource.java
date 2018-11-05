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
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.Company;
import com.liferay.portal.kernel.model.ListTypeModel;
import com.liferay.portal.kernel.model.Role;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.security.auth.PrincipalException;
import com.liferay.portal.kernel.security.permission.PermissionChecker;
import com.liferay.portal.kernel.service.ListTypeService;
import com.liferay.portal.kernel.service.RoleService;
import com.liferay.portal.kernel.service.UserLocalService;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.workflow.WorkflowConstants;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
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
		).addString(
			"email", User::getEmailAddress
		).addString(
			"accountExternalReferenceCode",
			UserWrapper::getExternalReferenceCode
		).addString(
			"familyName", User::getLastName
		).addString(
			"givenName", User::getFirstName
		).addString(
			"jobTitle", User::getJobTitle
		).addString(
			"name", User::getFullName
		).addNumberList(
			"commerceAccountIds", this::_getCommerceAccountIds
		).addStringList(
			"roleNames", this::_getRoleNames
		).addString(
			"jobTitle", UserWrapper::getJobTitle
		).build();
	}

	private String _getAlternateName(
		CommerceUserUpserterForm commerceUserUpserterForm, User user) {

		if (commerceUserUpserterForm.getAlternateName() == null) {
			return user.getScreenName();
		}

		return commerceUserUpserterForm.getAlternateName();
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

	private Integer _getDefaultValue(
		Optional<Integer> optional, int defaultValue) {

		return optional.orElse(defaultValue);
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

	private long _getPrefixId(
		String honorificTitle, String className, long defaultTitle) {

		return Try.fromFallible(
			() -> _listTypeService.getListType(honorificTitle, className)
		).map(
			ListTypeModel::getListTypeId
		).orElse(
			defaultTitle
		);
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

	private Boolean _isMale(
			CommerceUserUpserterForm commerceUserUpserterForm, User user)
		throws PortalException {

		Optional<Boolean> optional = commerceUserUpserterForm.isMaleOptional();

		return optional.orElse(user.isMale());
	}

	private byte[] _readInputStream(InputStream inputStream)
		throws IOException {

		ByteArrayOutputStream byteArrayOutputStream =
			new ByteArrayOutputStream();

		byte[] bytes = new byte[1024];
		int value = -1;

		while ((value = inputStream.read(bytes)) != -1) {
			byteArrayOutputStream.write(bytes, 0, value);
		}

		byteArrayOutputStream.flush();

		return byteArrayOutputStream.toByteArray();
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

	private UserWrapper _upsertUser(
			CommerceUserUpserterForm commerceUserUpserterForm,
			ThemeDisplay themeDisplay)
		throws PortalException {

		return _commerceUserHelper.upsert(
			themeDisplay, commerceUserUpserterForm);
	}

	private static final Log _log = LogFactoryUtil.getLog(
		CommerceUserCollectionResource.class);

	@Reference
	private CommerceUserHelper _commerceUserHelper;

	@Reference(
		target = "(model.class.name=com.liferay.portal.kernel.model.User)"
	)
	private HasPermission<ClassPKExternalReferenceCode> _hasPermission;

	@Reference
	private ListTypeService _listTypeService;

	@Reference
	private RoleService _roleService;

	@Reference
	private UserLocalService _userLocalService;

}