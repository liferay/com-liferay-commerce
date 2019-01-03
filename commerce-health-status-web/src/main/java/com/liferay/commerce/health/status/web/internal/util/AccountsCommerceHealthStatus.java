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

package com.liferay.commerce.health.status.web.internal.util;

import com.liferay.commerce.account.constants.CommerceAccountActionKeys;
import com.liferay.commerce.account.constants.CommerceAccountConstants;
import com.liferay.commerce.account.model.CommerceAccount;
import com.liferay.commerce.constants.CommerceOrderActionKeys;
import com.liferay.commerce.constants.CommerceOrderConstants;
import com.liferay.commerce.health.status.CommerceHealthStatus;
import com.liferay.commerce.health.status.web.internal.constants.CommerceHealthStatusConstants;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.language.LanguageUtil;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.Group;
import com.liferay.portal.kernel.model.GroupConstants;
import com.liferay.portal.kernel.model.ResourceConstants;
import com.liferay.portal.kernel.model.Role;
import com.liferay.portal.kernel.security.permission.ActionKeys;
import com.liferay.portal.kernel.service.GroupLocalService;
import com.liferay.portal.kernel.service.ResourcePermissionLocalService;
import com.liferay.portal.kernel.service.RoleService;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.service.ServiceContextFactory;
import com.liferay.portal.kernel.transaction.Propagation;
import com.liferay.portal.kernel.transaction.TransactionConfig;
import com.liferay.portal.kernel.transaction.TransactionInvokerUtil;
import com.liferay.portal.kernel.util.ResourceBundleUtil;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.concurrent.Callable;

import javax.servlet.http.HttpServletRequest;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Alessio Antonio Rendina
 */
@Component(
	immediate = true,
	property = {
		"commerce.health.status.display.order:Integer=110",
		"commerce.health.status.key=" + CommerceHealthStatusConstants.ACCOUNTS_COMMERCE_HEALTH_STATUS_KEY
	},
	service = CommerceHealthStatus.class
)
public class AccountsCommerceHealthStatus implements CommerceHealthStatus {

	@Override
	public void fixIssue(HttpServletRequest httpServletRequest)
		throws PortalException {

		try {
			ServiceContext serviceContext = ServiceContextFactory.getInstance(
				httpServletRequest);

			Callable<Object> accountRoleCallable = new AccountRoleCallable(
				serviceContext);

			TransactionInvokerUtil.invoke(
				_transactionConfig, accountRoleCallable);
		}
		catch (Throwable t) {
			_log.error(t, t);
		}
	}

	@Override
	public String getDescription(Locale locale) {
		ResourceBundle resourceBundle = ResourceBundleUtil.getBundle(
			"content.Language", locale, getClass());

		return LanguageUtil.get(
			resourceBundle,
			CommerceHealthStatusConstants.
				ACCOUNTS_COMMERCE_HEALTH_STATUS_DESCRIPTION);
	}

	@Override
	public String getKey() {
		return
			CommerceHealthStatusConstants.ACCOUNTS_COMMERCE_HEALTH_STATUS_KEY;
	}

	@Override
	public String getName(Locale locale) {
		ResourceBundle resourceBundle = ResourceBundleUtil.getBundle(
			"content.Language", locale, getClass());

		return LanguageUtil.get(
			resourceBundle,
			CommerceHealthStatusConstants.ACCOUNTS_COMMERCE_HEALTH_STATUS_KEY);
	}

	@Override
	public boolean isFixed(long groupId) throws PortalException {
		Group group = _groupLocalService.getGroup(groupId);

		List<Role> roles = _roleService.getRoles(
			group.getCompanyId(), new int[] {_ROLE_TYPE});

		return !roles.isEmpty();
	}

	// TODO creare costante

	private void _addRole(String name, ServiceContext serviceContext)
		throws PortalException {

		Role role = _roleService.addRole(
			null, 0, name,
			Collections.singletonMap(serviceContext.getLocale(), name),
			Collections.emptyMap(), _ROLE_TYPE, StringPool.BLANK,
			serviceContext);

		_setRolePermissions(role, serviceContext);
	}

	private void _setRolePermissions(
			Role role, Map<String, String[]> resourceActionIds,
			ServiceContext serviceContext)
		throws PortalException {

		for (Map.Entry<String, String[]> entry : resourceActionIds.entrySet()) {
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

		if (name.equals("Account Administrator")) {
			resourceActionIds.put(
				CommerceAccountConstants.RESOURCE_NAME,
				new String[] {CommerceAccountActionKeys.MANAGE_ACCOUNTS});
			resourceActionIds.put(
				CommerceOrderConstants.RESOURCE_NAME,
				new String[] {
					CommerceOrderActionKeys.ADD_COMMERCE_ORDER,
					CommerceOrderActionKeys.APPROVE_OPEN_COMMERCE_ORDERS,
					CommerceOrderActionKeys.CHECKOUT_OPEN_COMMERCE_ORDERS,
					CommerceOrderActionKeys.DELETE_COMMERCE_ORDERS,
					CommerceOrderActionKeys.MANAGE_COMMERCE_ORDERS,
					CommerceOrderActionKeys.VIEW_COMMERCE_ORDERS,
					CommerceOrderActionKeys.VIEW_OPEN_COMMERCE_ORDERS
				});
			resourceActionIds.put(
				CommerceAccount.class.getName(),
				new String[] {
					ActionKeys.DELETE, ActionKeys.UPDATE, ActionKeys.VIEW
				});
		}
		else if (name.equals("Account Manager")) {
			resourceActionIds.put(
				CommerceAccountConstants.RESOURCE_NAME,
				new String[] {CommerceAccountActionKeys.MANAGE_ACCOUNTS});
			resourceActionIds.put(
				CommerceAccount.class.getName(),
				new String[] {
					ActionKeys.DELETE, ActionKeys.UPDATE, ActionKeys.VIEW
				});
		}
		else if (name.equals("Buyer")) {
			resourceActionIds.put(
				CommerceOrderConstants.RESOURCE_NAME,
				new String[] {
					CommerceOrderActionKeys.ADD_COMMERCE_ORDER,
					CommerceOrderActionKeys.CHECKOUT_OPEN_COMMERCE_ORDERS,
					CommerceOrderActionKeys.VIEW_COMMERCE_ORDERS,
					CommerceOrderActionKeys.VIEW_OPEN_COMMERCE_ORDERS
				});
		}
		else if (name.equals("Order Manager")) {
			resourceActionIds.put(
				CommerceOrderConstants.RESOURCE_NAME,
				new String[] {
					CommerceOrderActionKeys.ADD_COMMERCE_ORDER,
					CommerceOrderActionKeys.APPROVE_OPEN_COMMERCE_ORDERS,
					CommerceOrderActionKeys.CHECKOUT_OPEN_COMMERCE_ORDERS,
					CommerceOrderActionKeys.DELETE_COMMERCE_ORDERS,
					CommerceOrderActionKeys.MANAGE_COMMERCE_ORDERS,
					CommerceOrderActionKeys.VIEW_COMMERCE_ORDERS,
					CommerceOrderActionKeys.VIEW_OPEN_COMMERCE_ORDERS
				});
		}

		_setRolePermissions(role, resourceActionIds, serviceContext);
	}

	private static final int _ROLE_TYPE = 4;

	private static final Log _log = LogFactoryUtil.getLog(
		AccountsCommerceHealthStatus.class);

	private static final TransactionConfig _transactionConfig =
		TransactionConfig.Factory.create(
			Propagation.REQUIRED, new Class<?>[] {Exception.class});

	@Reference
	private GroupLocalService _groupLocalService;

	@Reference
	private ResourcePermissionLocalService _resourcePermissionLocalService;

	@Reference
	private RoleService _roleService;

	private class AccountRoleCallable implements Callable<Object> {

		@Override
		public Object call() throws Exception {
			_addRole("Account Administrator", _serviceContext);
			_addRole("Account Manager", _serviceContext);
			_addRole("Buyer", _serviceContext);
			_addRole("Order Manager", _serviceContext);

			return null;
		}

		private AccountRoleCallable(ServiceContext serviceContext) {
			_serviceContext = serviceContext;
		}

		private final ServiceContext _serviceContext;

	}

}