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

package com.liferay.commerce.subscription.web.internal.display.context;

import com.liferay.commerce.payment.method.CommercePaymentMethodRegistry;
import com.liferay.commerce.payment.service.CommercePaymentMethodGroupRelLocalService;
import com.liferay.commerce.product.definitions.web.portlet.action.ActionHelper;
import com.liferay.commerce.product.definitions.web.servlet.taglib.ui.CPInstanceScreenNavigationConstants;
import com.liferay.commerce.product.model.CPInstance;
import com.liferay.commerce.product.service.CommerceChannelRelLocalService;
import com.liferay.commerce.product.util.CPSubscriptionTypeJSPContributorRegistry;
import com.liferay.commerce.product.util.CPSubscriptionTypeRegistry;
import com.liferay.portal.kernel.exception.PortalException;

import javax.portlet.PortletURL;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Alessio Antonio Rendina
 * @author Alec Sloan
 */
public class CPInstanceSubscriptionInfoDisplayContext
	extends BaseCPDefinitionSubscriptionInfoDisplayContext {

	public CPInstanceSubscriptionInfoDisplayContext(
		ActionHelper actionHelper, HttpServletRequest httpServletRequest,
		CommerceChannelRelLocalService commerceChannelRelLocalService,
		CommercePaymentMethodGroupRelLocalService
			commercePaymentMethodGroupRelLocalService,
		CommercePaymentMethodRegistry commercePaymentMethodRegistry,
		CPSubscriptionTypeJSPContributorRegistry
			cpSubscriptionTypeJSPContributorRegistry,
		CPSubscriptionTypeRegistry cpSubscriptionTypeRegistry) {

		super(
			actionHelper, httpServletRequest,
			commercePaymentMethodGroupRelLocalService,
			commercePaymentMethodRegistry,
			cpSubscriptionTypeJSPContributorRegistry,
			cpSubscriptionTypeRegistry);

		_commerceChannelRelLocalService = commerceChannelRelLocalService;
		_commercePaymentMethodGroupRelLocalService =
			commercePaymentMethodGroupRelLocalService;
		_commercePaymentMethodRegistry = commercePaymentMethodRegistry;
		_cpSubscriptionTypeJSPContributorRegistry =
			cpSubscriptionTypeJSPContributorRegistry;
		_cpSubscriptionTypeRegistry = cpSubscriptionTypeRegistry;
	}

	public CPInstance getCPInstance() throws PortalException {
		return actionHelper.getCPInstance(cpRequestHelper.getRenderRequest());
	}

	public long getCPInstanceId() throws PortalException {
		CPInstance cpInstance = getCPInstance();

		if (cpInstance == null) {
			return 0;
		}

		return cpInstance.getCPInstanceId();
	}

	@Override
	public PortletURL getPortletURL() throws PortalException {
		PortletURL portletURL = liferayPortletResponse.createRenderURL();

		portletURL.setParameter("mvcRenderCommandName", "editProductInstance");
		portletURL.setParameter(
			"cpDefinitionId", String.valueOf(getCPDefinitionId()));
		portletURL.setParameter(
			"cpInstanceId", String.valueOf(getCPInstanceId()));
		portletURL.setParameter(
			"screenNavigationCategoryKey",
			CPInstanceScreenNavigationConstants.CATEGORY_KEY_DETAILS);
		portletURL.setParameter(
			"screenNavigationEntryKey",
			CPInstanceScreenNavigationConstants.
				ENTRY_KEY_SUBSCRIPTION_OVERRIDE);

		return portletURL;
	}

	private final CommerceChannelRelLocalService
		_commerceChannelRelLocalService;
	private final CommercePaymentMethodGroupRelLocalService
		_commercePaymentMethodGroupRelLocalService;
	private final CommercePaymentMethodRegistry _commercePaymentMethodRegistry;
	private final CPSubscriptionTypeJSPContributorRegistry
		_cpSubscriptionTypeJSPContributorRegistry;
	private final CPSubscriptionTypeRegistry _cpSubscriptionTypeRegistry;

}