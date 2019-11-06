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

package com.liferay.commerce.account.internal.util;

import com.liferay.commerce.account.constants.CommerceAccountActionKeys;
import com.liferay.commerce.account.constants.CommerceAccountConstants;
import com.liferay.commerce.account.util.CommerceAccountRoleHelper;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.model.GroupConstants;
import com.liferay.portal.kernel.model.ResourceConstants;
import com.liferay.portal.kernel.model.Role;
import com.liferay.portal.kernel.model.RoleConstants;
import com.liferay.portal.kernel.security.permission.ActionKeys;
import com.liferay.portal.kernel.service.ResourceActionLocalService;
import com.liferay.portal.kernel.service.ResourcePermissionLocalService;
import com.liferay.portal.kernel.service.RoleLocalService;
import com.liferay.portal.kernel.service.ServiceContext;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Marco Leo
 * @author Alessio Antonio Rendina
 */
@Component(immediate = true, service = CommerceAccountRoleHelper.class)
public class CommerceAccountRoleHelperImpl
	implements CommerceAccountRoleHelper {

	@Override
	public void checkCommerceAccountRoles(ServiceContext serviceContext)
		throws PortalException {

		_checkRole(
			CommerceAccountConstants.ACCOUNT_ADMINISTRATOR_ROLE_NAME,
			serviceContext);
		_checkRole(
			CommerceAccountConstants.ACCOUNT_BUYER_ROLE_NAME, serviceContext);
		_checkRole(
			CommerceAccountConstants.ACCOUNT_ORDER_MANAGER_ROLE_NAME,
			serviceContext);
	}

	private void _checkRole(String name, ServiceContext serviceContext)
		throws PortalException {

		Role role = _roleLocalService.fetchRole(
			serviceContext.getCompanyId(), name);

		if (role == null) {
			role = _roleLocalService.addRole(
				serviceContext.getUserId(), null, 0, name,
				Collections.singletonMap(serviceContext.getLocale(), name),
				Collections.emptyMap(), RoleConstants.TYPE_SITE,
				StringPool.BLANK, serviceContext);
		}

		_setRolePermissions(role, serviceContext);
	}

	private void _setRolePermissions(
			Role role, Map<String, String[]> resourceActionIds,
			ServiceContext serviceContext)
		throws PortalException {

		for (Map.Entry<String, String[]> entry : resourceActionIds.entrySet()) {
			_resourceActionLocalService.checkResourceActions(
				entry.getKey(), Arrays.asList(entry.getValue()));

			for (String actionId : entry.getValue()) {
				_resourcePermissionLocalService.addResourcePermission(
					serviceContext.getCompanyId(), entry.getKey(),
					ResourceConstants.SCOPE_GROUP_TEMPLATE,
					String.valueOf(GroupConstants.DEFAULT_PARENT_GROUP_ID),
					role.getRoleId(), actionId);
			}
		}
	}

	private void _setRolePermissions(Role role, ServiceContext serviceContext)
		throws PortalException {

		Map<String, String[]> resourceActionIds = new HashMap<>();

		String name = role.getName();

		if (name.equals(
				CommerceAccountConstants.ACCOUNT_ADMINISTRATOR_ROLE_NAME)) {

			resourceActionIds.put(
				"com.liferay.commerce.account.model.CommerceAccount",
				new String[] {
					CommerceAccountActionKeys.MANAGE_ADDRESS,
					CommerceAccountActionKeys.MANAGE_MEMBERS, ActionKeys.UPDATE,
					ActionKeys.VIEW, CommerceAccountActionKeys.VIEW_ADDRESSES,
					CommerceAccountActionKeys.VIEW_MEMBERS
				});

			resourceActionIds.put(
				"com.liferay.commerce.order",
				new String[] {
					"ADD_COMMERCE_ORDER", "APPROVE_OPEN_COMMERCE_ORDERS",
					"CHECKOUT_OPEN_COMMERCE_ORDERS", "DELETE_COMMERCE_ORDERS",
					"MANAGE_COMMERCE_ORDERS", "VIEW_COMMERCE_ORDERS",
					"VIEW_OPEN_COMMERCE_ORDERS"
				});
		}
		else if (name.equals(
					CommerceAccountConstants.ACCOUNT_BUYER_ROLE_NAME)) {

			resourceActionIds.put(
				"com.liferay.commerce.order",
				new String[] {
					"ADD_COMMERCE_ORDER", "CHECKOUT_OPEN_COMMERCE_ORDERS",
					"VIEW_COMMERCE_ORDERS", "VIEW_OPEN_COMMERCE_ORDERS"
				});
		}
		else if (name.equals(
					CommerceAccountConstants.ACCOUNT_ORDER_MANAGER_ROLE_NAME)) {

			resourceActionIds.put(
				"com.liferay.commerce.order",
				new String[] {
					"ADD_COMMERCE_ORDER", "APPROVE_OPEN_COMMERCE_ORDERS",
					"CHECKOUT_OPEN_COMMERCE_ORDERS", "DELETE_COMMERCE_ORDERS",
					"MANAGE_COMMERCE_ORDERS", "VIEW_COMMERCE_ORDERS",
					"VIEW_OPEN_COMMERCE_ORDERS"
				});
		}

		_setRolePermissions(role, resourceActionIds, serviceContext);
	}

	@Reference
	private ResourceActionLocalService _resourceActionLocalService;

	@Reference
	private ResourcePermissionLocalService _resourcePermissionLocalService;

	@Reference
	private RoleLocalService _roleLocalService;

}