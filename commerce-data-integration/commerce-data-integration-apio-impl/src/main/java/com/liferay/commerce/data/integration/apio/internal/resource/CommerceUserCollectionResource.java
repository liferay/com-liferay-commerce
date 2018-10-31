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

import com.liferay.apio.architect.pagination.PageItems;
import com.liferay.apio.architect.pagination.Pagination;
import com.liferay.apio.architect.representor.Representor;
import com.liferay.apio.architect.resource.CollectionResource;
import com.liferay.apio.architect.routes.CollectionRoutes;
import com.liferay.apio.architect.routes.ItemRoutes;
import com.liferay.commerce.data.integration.apio.identifier.ClassPKExternalReferenceCode;
import com.liferay.commerce.data.integration.apio.identifier.CommerceUserIdentifier;
import com.liferay.commerce.data.integration.apio.internal.form.CommerceUserUpserterForm;
import com.liferay.commerce.data.integration.apio.internal.util.CommerceUserHelper;
import com.liferay.portal.apio.permission.HasPermission;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.Company;
import com.liferay.portal.kernel.model.Role;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.model.UserWrapper;
import com.liferay.portal.kernel.service.RoleService;
import com.liferay.portal.kernel.service.UserService;
import com.liferay.portal.kernel.theme.ThemeDisplay;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.NotFoundException;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
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
			this::_getPageItems, Company.class
		).addCreator(
			this::_addUser, ThemeDisplay.class, _hasPermission::forAdding,
			CommerceUserUpserterForm::buildForm
		).build();
	}

	@Override
	public String getName() {
		return "commerce-person";
	}

	@Override
	public ItemRoutes<UserWrapper, ClassPKExternalReferenceCode> itemRoutes(
		ItemRoutes.Builder<UserWrapper, ClassPKExternalReferenceCode> builder) {

		return builder.addGetter(
			this::_getUserWrapper, Company.class
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

	private UserWrapper _addUser(
			CommerceUserUpserterForm commerceUserUpserterForm,
			ThemeDisplay themeDisplay)
		throws PortalException {

		return _commerceUserHelper.upsert(
			themeDisplay.getCompanyId(), themeDisplay.getUserId(),
			commerceUserUpserterForm);
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

	private PageItems<UserWrapper> _getPageItems(
			Pagination pagination, Company company)
		throws PortalException {

		List<User> users = _userService.getCompanyUsers(
			company.getCompanyId(), pagination.getStartPosition(),
			pagination.getEndPosition());

		List<UserWrapper> userWrappers = new ArrayList<>(users.size());

		for (User user : users) {
			userWrappers.add(new UserWrapper(user));
		}

		int total = _userService.getCompanyUsersCount(company.getCompanyId());

		return new PageItems<>(userWrappers, total);
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
			ClassPKExternalReferenceCode userId, Company company)
		throws PortalException {

		User user = _commerceUserHelper.getUser(userId, company);

		if (user == null) {
			throw new NotFoundException();
		}

		return new UserWrapper(user);
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
	private RoleService _roleService;

	@Reference
	private UserService _userService;

}