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

package com.liferay.commerce.user.web.internal.display.context;

import com.liferay.commerce.account.model.CommerceAccount;
import com.liferay.commerce.account.util.CommerceAccountHelper;
import com.liferay.commerce.constants.CommerceWebKeys;
import com.liferay.commerce.context.CommerceContext;
import com.liferay.commerce.user.service.CommerceUserService;
import com.liferay.commerce.user.util.CommerceRoleRegistry;
import com.liferay.portal.kernel.model.Role;
import com.liferay.portal.kernel.service.UserGroupRoleLocalService;
import com.liferay.portal.kernel.util.Portal;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Alessio Antonio Rendina
 */
public class CommerceUserPermissionsDisplayContext
	extends BaseCommerceUserDisplayContext {

	public CommerceUserPermissionsDisplayContext(
		CommerceRoleRegistry commerceRoleRegistry,
		CommerceUserService commerceUserService,
		HttpServletRequest httpServletRequest, Portal portal,
		UserGroupRoleLocalService userGroupRoleLocalService) {

		super(commerceUserService, httpServletRequest, portal);

		_commerceRoleRegistry = commerceRoleRegistry;
		_userGroupRoleLocalService = userGroupRoleLocalService;

		_commerceContext = (CommerceContext)httpServletRequest.getAttribute(
			CommerceWebKeys.COMMERCE_CONTEXT);
	}

	public List<Role> getRoles() {
		List<Role> roles = _commerceRoleRegistry.getRoles(
			commerceUserRequestHelper.getCompanyId());

		return roles;
	}

	public boolean hasUserGroupRole(long userId, long roleId) throws Exception {
		CommerceAccount commerceAccount =
			_commerceContext.getCommerceAccount();

		if (commerceAccount == null) {
			return false;
		}

		return _userGroupRoleLocalService.hasUserGroupRole(
			userId, commerceAccount.getCommerceAccountGroupId(), roleId);
	}

	private final CommerceContext _commerceContext;
	private final CommerceRoleRegistry _commerceRoleRegistry;
	private final UserGroupRoleLocalService _userGroupRoleLocalService;

}