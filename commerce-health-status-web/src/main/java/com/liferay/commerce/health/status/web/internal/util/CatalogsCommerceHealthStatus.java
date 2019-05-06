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

import com.liferay.commerce.health.status.CommerceHealthStatus;
import com.liferay.commerce.health.status.web.internal.constants.CommerceHealthStatusConstants;
import com.liferay.commerce.product.constants.CommerceCatalogConstants;
import com.liferay.commerce.product.util.CommerceCatalogRoleHelper;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.language.LanguageUtil;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.Group;
import com.liferay.portal.kernel.model.Role;
import com.liferay.portal.kernel.service.GroupLocalService;
import com.liferay.portal.kernel.service.RoleLocalService;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.service.ServiceContextFactory;
import com.liferay.portal.kernel.transaction.Propagation;
import com.liferay.portal.kernel.transaction.TransactionConfig;
import com.liferay.portal.kernel.transaction.TransactionInvokerUtil;
import com.liferay.portal.kernel.util.ResourceBundleUtil;

import java.util.Locale;
import java.util.ResourceBundle;
import java.util.concurrent.Callable;

import javax.servlet.http.HttpServletRequest;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Alec Sloan
 */
@Component(
	immediate = true,
	property = {
		"commerce.health.status.display.order:Integer=120",
		"commerce.health.status.key=" + CommerceHealthStatusConstants.CATALOGS_COMMERCE_HEALTH_STATUS_KEY
	},
	service = CommerceHealthStatus.class
)
public class CatalogsCommerceHealthStatus implements CommerceHealthStatus {

	@Override
	public void fixIssue(HttpServletRequest httpServletRequest)
		throws PortalException {

		try {
			ServiceContext serviceContext = ServiceContextFactory.getInstance(
				httpServletRequest);

			Callable<Object> catalogRoleCallable = new CatalogRoleCallable(
				serviceContext);

			TransactionInvokerUtil.invoke(
				_transactionConfig, catalogRoleCallable);
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
				CATALOGS_COMMERCE_HEALTH_STATUS_DESCRIPTION);
	}

	@Override
	public String getKey() {
		return CommerceHealthStatusConstants.
			CATALOGS_COMMERCE_HEALTH_STATUS_KEY;
	}

	@Override
	public String getName(Locale locale) {
		ResourceBundle resourceBundle = ResourceBundleUtil.getBundle(
			"content.Language", locale, getClass());

		return LanguageUtil.get(
			resourceBundle,
			CommerceHealthStatusConstants.CATALOGS_COMMERCE_HEALTH_STATUS_KEY);
	}

	@Override
	public boolean isFixed(long groupId) throws PortalException {
		Group group = _groupLocalService.getGroup(groupId);

		for (String catalogRoleName : _CATALOG_ROLE_NAMES) {
			Role role = _roleLocalService.fetchRole(
				group.getCompanyId(), catalogRoleName);

			if ((role == null) ||
				(role.getSubtype() !=
					CommerceCatalogConstants.ROLE_SUBTYPE_CATALOG)) {

				return false;
			}
		}

		return true;
	}

	private static final String[] _CATALOG_ROLE_NAMES = {
		CommerceCatalogConstants.CATALOG_MANAGER_ROLE_NAME,
		CommerceCatalogConstants.INVENTORY_MANAGER_ROLE_NAME,
		CommerceCatalogConstants.CATALOG_ORDER_MANAGER_ROLE_NAME,
		CommerceCatalogConstants.PRICING_MANAGER_ROLE_NAME
	};

	private static final Log _log = LogFactoryUtil.getLog(
		CatalogsCommerceHealthStatus.class);

	private static final TransactionConfig _transactionConfig =
		TransactionConfig.Factory.create(
			Propagation.REQUIRED, new Class<?>[] {Exception.class});

	@Reference
	private CommerceCatalogRoleHelper _commerceCatalogRoleHelper;

	@Reference
	private GroupLocalService _groupLocalService;

	@Reference
	private RoleLocalService _roleLocalService;

	private class CatalogRoleCallable implements Callable<Object> {

		@Override
		public Object call() throws Exception {
			_commerceCatalogRoleHelper.checkCommerceCatalogRoles(
				_serviceContext.getCompanyId(), _serviceContext.getUserId(),
				_serviceContext.getLanguageId());

			return null;
		}

		private CatalogRoleCallable(ServiceContext serviceContext) {
			_serviceContext = serviceContext;
		}

		private final ServiceContext _serviceContext;

	}

}