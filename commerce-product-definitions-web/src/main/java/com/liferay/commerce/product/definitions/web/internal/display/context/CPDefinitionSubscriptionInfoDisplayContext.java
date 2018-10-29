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

package com.liferay.commerce.product.definitions.web.internal.display.context;

import com.liferay.commerce.product.definitions.web.portlet.action.ActionHelper;
import com.liferay.commerce.product.model.CPDefinition;
import com.liferay.commerce.product.service.CPDefinitionService;
import com.liferay.commerce.product.util.CPDefinitionHelper;
import com.liferay.commerce.product.util.CPSubscriptionType;
import com.liferay.commerce.product.util.CPSubscriptionTypeJSPContributor;
import com.liferay.commerce.product.util.CPSubscriptionTypeJSPContributorRegistry;
import com.liferay.commerce.product.util.CPSubscriptionTypeRegistry;
import com.liferay.item.selector.ItemSelector;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.security.permission.resource.ModelResourcePermission;
import com.liferay.portal.kernel.security.permission.resource.PortletResourcePermission;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Alessio Antonio Rendina
 */
public class CPDefinitionSubscriptionInfoDisplayContext
	extends CPDefinitionsDisplayContext {

	public CPDefinitionSubscriptionInfoDisplayContext(
			ActionHelper actionHelper, HttpServletRequest httpServletRequest,
			CPDefinitionHelper cpDefinitionHelper,
			ModelResourcePermission<CPDefinition>
				cpDefinitionModelResourcePermission,
			CPDefinitionService cpDefinitionService, ItemSelector itemSelector,
			PortletResourcePermission portletResourcePermission,
			CPSubscriptionTypeJSPContributorRegistry
				cpSubscriptionTypeJSPContributorRegistry,
			CPSubscriptionTypeRegistry cpSubscriptionTypeRegistry)
		throws PortalException {

		super(
			actionHelper, httpServletRequest, cpDefinitionHelper,
			cpDefinitionModelResourcePermission, cpDefinitionService,
			itemSelector, portletResourcePermission);

		_cpSubscriptionTypeJSPContributorRegistry =
			cpSubscriptionTypeJSPContributorRegistry;
		_cpSubscriptionTypeRegistry = cpSubscriptionTypeRegistry;
	}

	public CPSubscriptionType getCPSubscriptionType(String subscriptionType) {
		return _cpSubscriptionTypeRegistry.getCPSubscriptionType(
			subscriptionType);
	}

	public CPSubscriptionTypeJSPContributor getCPSubscriptionTypeJSPContributor(
		String subscriptionType) {

		return _cpSubscriptionTypeJSPContributorRegistry.
			getCPSubscriptionTypeJSPContributor(subscriptionType);
	}

	public List<CPSubscriptionType> getCPSubscriptionTypes() {
		return _cpSubscriptionTypeRegistry.getCPSubscriptionTypes();
	}

	private final CPSubscriptionTypeJSPContributorRegistry
		_cpSubscriptionTypeJSPContributorRegistry;
	private final CPSubscriptionTypeRegistry _cpSubscriptionTypeRegistry;

}