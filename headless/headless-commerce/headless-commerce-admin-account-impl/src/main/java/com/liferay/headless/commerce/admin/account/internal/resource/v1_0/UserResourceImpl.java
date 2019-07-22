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

package com.liferay.headless.commerce.admin.account.internal.resource.v1_0;

import com.liferay.commerce.account.exception.NoSuchAccountException;
import com.liferay.commerce.account.model.CommerceAccount;
import com.liferay.commerce.account.service.CommerceAccountService;
import com.liferay.commerce.account.service.CommerceAccountUserRelService;
import com.liferay.headless.commerce.admin.account.dto.v1_0.User;
import com.liferay.headless.commerce.admin.account.resource.v1_0.UserResource;
import com.liferay.headless.commerce.core.dto.v1_0.converter.DTOConverter;
import com.liferay.headless.commerce.core.dto.v1_0.converter.DTOConverterRegistry;
import com.liferay.headless.commerce.core.dto.v1_0.converter.DefaultDTOConverterContext;
import com.liferay.headless.commerce.core.util.ServiceContextHelper;
import com.liferay.portal.kernel.model.Role;
import com.liferay.portal.kernel.service.RoleLocalService;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.service.UserService;
import com.liferay.portal.kernel.util.ArrayUtil;
import com.liferay.portal.kernel.util.LocaleUtil;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.osgi.service.component.annotations.ServiceScope;

/**
 * @author Alessio Antonio Rendina
 */
@Component(
	properties = "OSGI-INF/liferay/rest/v1_0/user.properties",
	scope = ServiceScope.PROTOTYPE, service = UserResource.class
)
public class UserResourceImpl extends BaseUserResourceImpl {

	@Override
	public User postAccountByExternalReferenceCodeAccountMemberCreateUser(
			String externalReferenceCode, User user)
		throws Exception {

		CommerceAccount commerceAccount =
			_commerceAccountService.fetchByExternalReferenceCode(
				contextCompany.getCompanyId(), externalReferenceCode);

		if (commerceAccount == null) {
			throw new NoSuchAccountException(
				"Unable to find Account with externalReferenceCode: " +
					externalReferenceCode);
		}

		ServiceContext serviceContext = _serviceContextHelper.getServiceContext(
			commerceAccount.getCommerceAccountGroupId());

		com.liferay.portal.kernel.model.User invitedUser = _userService.addUser(
			contextCompany.getCompanyId(), true, null, null, false,
			user.getScreenName(), user.getEmail(), 0L, null,
			LocaleUtil.getDefault(), user.getFirstName(), user.getMiddleName(),
			user.getLastName(), 0L, 0L, user.getMale(), 1, 1, 1970,
			user.getJobTitle(),
			new long[] {commerceAccount.getCommerceAccountGroupId()}, null,
			null, null, false, serviceContext);

		// Account rel

		long[] roleIds = new long[0];

		String[] roles = user.getRoles();

		if (roles != null) {
			for (String role : roles) {
				Role curRole = _roleLocalService.getRole(
					contextCompany.getCompanyId(), role);

				roleIds = ArrayUtil.append(roleIds, curRole.getRoleId());
			}
		}

		_commerceAccountUserRelService.addCommerceAccountUserRel(
			commerceAccount.getCommerceAccountId(), invitedUser.getUserId(),
			roleIds, serviceContext);

		DTOConverter userDTOConverter = _dtoConverterRegistry.getDTOConverter(
			invitedUser.getModelClassName());

		return (User)userDTOConverter.toDTO(
			new DefaultDTOConverterContext(
				contextAcceptLanguage.getPreferredLocale(),
				invitedUser.getUserId()));
	}

	@Reference
	private CommerceAccountService _commerceAccountService;

	@Reference
	private CommerceAccountUserRelService _commerceAccountUserRelService;

	@Reference
	private DTOConverterRegistry _dtoConverterRegistry;

	@Reference
	private RoleLocalService _roleLocalService;

	@Reference
	private ServiceContextHelper _serviceContextHelper;

	@Reference
	private UserService _userService;

}