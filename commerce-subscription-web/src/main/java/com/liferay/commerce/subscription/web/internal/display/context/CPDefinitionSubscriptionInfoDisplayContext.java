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

import com.liferay.commerce.payment.method.CommercePaymentMethod;
import com.liferay.commerce.payment.method.CommercePaymentMethodRegistry;
import com.liferay.commerce.payment.model.CommercePaymentMethodGroupRel;
import com.liferay.commerce.payment.service.CommercePaymentMethodGroupRelLocalService;
import com.liferay.commerce.product.definitions.web.display.context.BaseCPDefinitionsDisplayContext;
import com.liferay.commerce.product.definitions.web.portlet.action.ActionHelper;
import com.liferay.commerce.product.definitions.web.servlet.taglib.ui.CPDefinitionScreenNavigationConstants;
import com.liferay.commerce.product.model.CPDefinition;
import com.liferay.commerce.product.model.CommerceChannel;
import com.liferay.commerce.product.model.CommerceChannelRel;
import com.liferay.commerce.product.service.CPDefinitionService;
import com.liferay.commerce.product.service.CommerceCatalogService;
import com.liferay.commerce.product.service.CommerceChannelRelLocalServiceUtil;
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
 * @author Alec Sloan
 */
public class CPDefinitionSubscriptionInfoDisplayContext
	extends BaseCPDefinitionsDisplayContext {

	public CPDefinitionSubscriptionInfoDisplayContext(
		ActionHelper actionHelper, HttpServletRequest httpServletRequest,
		CommercePaymentMethodGroupRelLocalService
			commercePaymentMethodGroupRelLocalService,
		CommercePaymentMethodRegistry commercePaymentMethodRegistry,
		CommerceCatalogService commerceCatalogService,
		CPDefinitionService cpDefinitionService,
		CPSubscriptionTypeJSPContributorRegistry
			cpSubscriptionTypeJSPContributorRegistry,
		CPSubscriptionTypeRegistry cpSubscriptionTypeRegistry) {

		super(actionHelper, httpServletRequest);

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
		PortletURL portletURL = super.getPortletURL();

		portletURL.setParameter(
			"mvcRenderCommandName", "editProductDefinition");
		portletURL.setParameter(
			"screenNavigationCategoryKey",
			getSelectedScreenNavigationCategoryKey());
		portletURL.setParameter(
			"screenNavigationEntryKey",
			CPDefinitionScreenNavigationConstants.ENTRY_KEY_SUBSCRIPTION);

		return portletURL;
	}

	public boolean hasRecurringPaymentMethod() throws PortalException {
		List<CommerceChannelRel> commerceChannelRels =
			CommerceChannelRelLocalServiceUtil.getCommerceChannelRels(
				CPDefinition.class.getName(), getCPDefinitionId(), -1, -1,
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

	private final CommercePaymentMethodGroupRelLocalService
		_commercePaymentMethodGroupRelLocalService;
	private final CommercePaymentMethodRegistry _commercePaymentMethodRegistry;
	private final CPSubscriptionTypeJSPContributorRegistry
		_cpSubscriptionTypeJSPContributorRegistry;
	private final CPSubscriptionTypeRegistry _cpSubscriptionTypeRegistry;

}