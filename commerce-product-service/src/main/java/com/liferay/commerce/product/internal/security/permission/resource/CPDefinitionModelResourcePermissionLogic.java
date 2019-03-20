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

package com.liferay.commerce.product.internal.security.permission.resource;

import com.liferay.commerce.product.catalog.rule.CPRuleType;
import com.liferay.commerce.product.catalog.rule.CPRuleTypeRegistry;
import com.liferay.commerce.product.configuration.CPRuleGroupServiceConfiguration;
import com.liferay.commerce.product.constants.CPActionKeys;
import com.liferay.commerce.product.constants.CPRuleConstants;
import com.liferay.commerce.product.model.CPDefinition;
import com.liferay.commerce.product.model.CPRule;
import com.liferay.commerce.product.util.CPRulesThreadLocal;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.module.configuration.ConfigurationProvider;
import com.liferay.portal.kernel.security.permission.ActionKeys;
import com.liferay.portal.kernel.security.permission.PermissionChecker;
import com.liferay.portal.kernel.security.permission.resource.ModelResourcePermissionLogic;
import com.liferay.portal.kernel.security.permission.resource.PortletResourcePermission;
import com.liferay.portal.kernel.settings.GroupServiceSettingsLocator;
import com.liferay.portal.kernel.util.ListUtil;

import java.util.List;

/**
 * @author Alessio Antonio Rendina
 * @author Andrea Di Giorgi
 * @author Ethan Bustad
 */
public class CPDefinitionModelResourcePermissionLogic
	implements ModelResourcePermissionLogic<CPDefinition> {

	public CPDefinitionModelResourcePermissionLogic(
		CPRuleTypeRegistry cpRuleTypeRegistry,
		PortletResourcePermission portletResourcePermission,
		ConfigurationProvider configurationProvider) {

		_cpRuleTypeRegistry = cpRuleTypeRegistry;
		_portletResourcePermission = portletResourcePermission;
		_configurationProvider = configurationProvider;
	}

	@Override
	public Boolean contains(
			PermissionChecker permissionChecker, String name,
			CPDefinition cpDefinition, String actionId)
		throws PortalException {

		if (permissionChecker.isCompanyAdmin(cpDefinition.getCompanyId()) ||
			permissionChecker.isGroupAdmin(cpDefinition.getGroupId()) ||
			_portletResourcePermission.contains(
				permissionChecker, cpDefinition.getGroupId(),
				CPActionKeys.MANAGE_CATALOG)) {

			return true;
		}

		if (!actionId.equals(ActionKeys.VIEW) || !cpDefinition.isApproved() ||
			!cpDefinition.isPublished()) {

			return false;
		}

		List<CPRule> cpRules = CPRulesThreadLocal.getCPRules();

		if (ListUtil.isEmpty(cpRules)) {
			return false;
		}

		boolean matchAll = true;

		CPRuleGroupServiceConfiguration cpRuleGroupServiceConfiguration =
			_configurationProvider.getConfiguration(
				CPRuleGroupServiceConfiguration.class,
				new GroupServiceSettingsLocator(
					cpDefinition.getGroupId(), CPRuleConstants.SERVICE_NAME));

		if ((cpRuleGroupServiceConfiguration != null) &&
			(cpRuleGroupServiceConfiguration.catalogRuleApplicationType() ==
				CPRuleConstants.APPLICATION_TYPE_ANY)) {

			matchAll = false;
		}

		for (CPRule cpRule : cpRules) {
			CPRuleType cpRuleType = _cpRuleTypeRegistry.getCPRuleType(
				cpRule.getType());

			boolean satisfied = cpRuleType.isSatisfied(cpDefinition, cpRule);

			if (!satisfied && matchAll) {
				return false;
			}
			else if (satisfied && !matchAll) {
				return true;
			}
		}

		return matchAll;
	}

	private final ConfigurationProvider _configurationProvider;
	private final CPRuleTypeRegistry _cpRuleTypeRegistry;
	private final PortletResourcePermission _portletResourcePermission;

}