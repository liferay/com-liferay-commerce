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

import com.liferay.commerce.currency.util.CommercePriceFormatter;
import com.liferay.commerce.product.constants.CPConstants;
import com.liferay.commerce.product.definitions.web.portlet.action.ActionHelper;
import com.liferay.commerce.product.model.CPDefinition;
import com.liferay.commerce.product.service.CPDefinitionOptionRelService;
import com.liferay.commerce.product.service.CPInstanceService;
import com.liferay.commerce.product.util.CPInstanceHelper;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.security.permission.resource.ModelResourcePermission;
import com.liferay.portal.kernel.security.permission.resource.PortletResourcePermission;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Pellizzon Luca
 */
public class CPInstanceSubscriptionInfoDisplayContext
	extends CPInstanceDisplayContext {

	public CPInstanceSubscriptionInfoDisplayContext(
			ActionHelper actionHelper, HttpServletRequest httpServletRequest,
			CommercePriceFormatter commercePriceFormatter,
			ModelResourcePermission<CPDefinition>
				cpDefinitionModelResourcePermission,
			CPDefinitionOptionRelService cpDefinitionOptionRelService,
			CPInstanceService cpInstanceService,
			CPInstanceHelper cpInstanceHelper,
			PortletResourcePermission portletResourcePermission)
		throws PortalException {

		super(
			actionHelper, httpServletRequest, commercePriceFormatter,
			cpDefinitionModelResourcePermission, cpDefinitionOptionRelService,
			cpInstanceService, cpInstanceHelper, portletResourcePermission);
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