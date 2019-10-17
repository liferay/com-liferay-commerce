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

import com.liferay.commerce.currency.util.CommercePriceFormatter;
import com.liferay.commerce.payment.method.CommercePaymentMethod;
import com.liferay.commerce.payment.method.CommercePaymentMethodRegistry;
import com.liferay.commerce.payment.model.CommercePaymentMethodGroupRel;
import com.liferay.commerce.payment.service.CommercePaymentMethodGroupRelLocalService;
import com.liferay.commerce.product.definitions.web.internal.display.context.CPInstanceDisplayContext;
import com.liferay.commerce.product.definitions.web.portlet.action.ActionHelper;
import com.liferay.commerce.product.definitions.web.servlet.taglib.ui.CPInstanceScreenNavigationConstants;
import com.liferay.commerce.product.model.CPDefinition;
import com.liferay.commerce.product.model.CommerceChannel;
import com.liferay.commerce.product.model.CommerceChannelRel;
import com.liferay.commerce.product.service.CPDefinitionOptionRelService;
import com.liferay.commerce.product.service.CPInstanceService;
import com.liferay.commerce.product.service.CommerceChannelRelLocalService;
import com.liferay.commerce.product.service.CommerceChannelRelLocalServiceUtil;
import com.liferay.commerce.product.util.CPInstanceHelper;
import com.liferay.commerce.product.util.CPSubscriptionType;
import com.liferay.commerce.product.util.CPSubscriptionTypeJSPContributor;
import com.liferay.commerce.product.util.CPSubscriptionTypeJSPContributorRegistry;
import com.liferay.commerce.product.util.CPSubscriptionTypeRegistry;
import com.liferay.portal.kernel.exception.PortalException;

import java.util.List;

import javax.portlet.PortletURL;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Alessio Antonio Rendina
 */
public class CPInstanceSubscriptionInfoDisplayContext
	extends CPInstanceDisplayContext {

	public CPInstanceSubscriptionInfoDisplayContext(
			ActionHelper actionHelper, HttpServletRequest httpServletRequest,
			CommerceChannelRelLocalService commerceChannelRelLocalService,
			CommercePaymentMethodGroupRelLocalService
				commercePaymentMethodGroupRelLocalService,
			CommercePaymentMethodRegistry commercePaymentMethodRegistry,
			CommercePriceFormatter commercePriceFormatter,
			CPDefinitionOptionRelService cpDefinitionOptionRelService,
			CPInstanceService cpInstanceService,
			CPInstanceHelper cpInstanceHelper,
			CPSubscriptionTypeJSPContributorRegistry
				cpSubscriptionTypeJSPContributorRegistry,
			CPSubscriptionTypeRegistry cpSubscriptionTypeRegistry)
		throws PortalException {

		super(
			actionHelper, httpServletRequest, commercePriceFormatter,
			cpDefinitionOptionRelService, cpInstanceService, cpInstanceHelper);

		_commerceChannelRelLocalService = commerceChannelRelLocalService;
		_commercePaymentMethodGroupRelLocalService =
			commercePaymentMethodGroupRelLocalService;
		_commercePaymentMethodRegistry = commercePaymentMethodRegistry;
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

	public boolean hasRecurringPaymentMethod() throws PortalException {
		List<CommerceChannelRel> commerceChannelRels =
			_commerceChannelRelLocalService.getCommerceChannelRels(
				CPDefinition.class.getName(), super.getCPDefinitionId(), -1, -1,
				null);

		for (CommerceChannelRel commerceChannelRel : commerceChannelRels) {
			boolean channelHasRecurringPaymentMethod = false;

			CommerceChannel commerceChannel =
				commerceChannelRel.getCommerceChannel();

			List<CommercePaymentMethodGroupRel> commercePaymentMethodGroupRels =
				_commercePaymentMethodGroupRelLocalService.
					getCommercePaymentMethodGroupRels(
						commerceChannel.getSiteGroupId(), true);

			for (CommercePaymentMethodGroupRel commercePaymentMethodGroupRel :
				commercePaymentMethodGroupRels) {

				if (commercePaymentMethodGroupRel.getActive()) {
					CommercePaymentMethod commercePaymentMethod =
						_commercePaymentMethodRegistry.getCommercePaymentMethod(
							commercePaymentMethodGroupRel.getEngineKey());

					if (commercePaymentMethod.isProcessRecurringEnabled()) {
						channelHasRecurringPaymentMethod = true;
					}
				}
			}

			if (!channelHasRecurringPaymentMethod) {
				return false;
			}
		}

		return true;
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