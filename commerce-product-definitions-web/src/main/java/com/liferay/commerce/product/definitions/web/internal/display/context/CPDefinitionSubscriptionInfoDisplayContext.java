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

import com.liferay.commerce.product.constants.CPConstants;
import com.liferay.commerce.product.definitions.web.portlet.action.ActionHelper;
import com.liferay.commerce.product.model.CPDefinition;
import com.liferay.commerce.product.service.CPDefinitionService;
import com.liferay.commerce.product.util.CPDefinitionHelper;
import com.liferay.item.selector.ItemSelector;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.security.permission.resource.ModelResourcePermission;
import com.liferay.portal.kernel.security.permission.resource.PortletResourcePermission;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Pellizzon Luca
 */
public class CPDefinitionSubscriptionInfoDisplayContext
	extends CPDefinitionsDisplayContext {

	public CPDefinitionSubscriptionInfoDisplayContext(
			ActionHelper actionHelper, HttpServletRequest httpServletRequest,
			CPDefinitionHelper cpDefinitionHelper,
			ModelResourcePermission<CPDefinition>
				cpDefinitionModelResourcePermission,
			CPDefinitionService cpDefinitionService, ItemSelector itemSelector,
			PortletResourcePermission portletResourcePermission)
		throws PortalException {

		super(
			actionHelper, httpServletRequest, cpDefinitionHelper,
			cpDefinitionModelResourcePermission, cpDefinitionService,
			itemSelector, portletResourcePermission);
	}

	public List<String> getSubscriptionCyclePeriods() {
		List<String> subscriptionCyclePeriods = new ArrayList<>();

		subscriptionCyclePeriods.add(CPConstants.SUBSCRIPTION_DAYS);
		subscriptionCyclePeriods.add(CPConstants.SUBSCRIPTION_WEEK);
		subscriptionCyclePeriods.add(CPConstants.SUBSCRIPTION_MONTH);
		subscriptionCyclePeriods.add(CPConstants.SUBSCRIPTION_YEAR);

		return subscriptionCyclePeriods;
	}

}