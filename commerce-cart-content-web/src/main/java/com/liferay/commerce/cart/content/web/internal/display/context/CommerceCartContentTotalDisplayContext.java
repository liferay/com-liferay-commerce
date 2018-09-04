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

package com.liferay.commerce.cart.content.web.internal.display.context;

import com.liferay.commerce.cart.content.web.internal.portlet.configuration.CommerceCartContentTotalPortletInstanceConfiguration;
import com.liferay.commerce.constants.CommercePortletKeys;
import com.liferay.commerce.model.CommerceOrder;
import com.liferay.commerce.order.CommerceOrderValidatorRegistry;
import com.liferay.commerce.price.CommerceOrderPriceCalculation;
import com.liferay.commerce.price.CommerceProductPriceCalculation;
import com.liferay.commerce.product.util.CPDefinitionHelper;
import com.liferay.commerce.product.util.CPInstanceHelper;
import com.liferay.commerce.service.CommerceOrderItemService;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.portlet.PortletURLFactoryUtil;
import com.liferay.portal.kernel.security.permission.resource.ModelResourcePermission;
import com.liferay.portal.kernel.theme.PortletDisplay;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.Portal;
import com.liferay.portal.kernel.util.WebKeys;

import javax.portlet.PortletRequest;
import javax.portlet.PortletURL;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Alessio Antonio Rendina
 */
public class CommerceCartContentTotalDisplayContext
	extends CommerceCartContentDisplayContext {

	public CommerceCartContentTotalDisplayContext(
			HttpServletRequest httpServletRequest,
			CommerceOrderItemService commerceOrderItemService,
			CommerceOrderPriceCalculation commerceOrderPriceCalculation,
			CommerceOrderValidatorRegistry commerceOrderValidatorRegistry,
			CommerceProductPriceCalculation commerceProductPriceCalculation,
			CPDefinitionHelper cpDefinitionHelper,
			CPInstanceHelper cpInstanceHelper,
			ModelResourcePermission<CommerceOrder>
				commerceOrderModelResourcePermission,
			Portal portal)
		throws PortalException {

		super(
			httpServletRequest, commerceOrderItemService,
			commerceOrderPriceCalculation, commerceOrderValidatorRegistry,
			commerceProductPriceCalculation, cpDefinitionHelper,
			cpInstanceHelper, commerceOrderModelResourcePermission);

		_portal = portal;

		ThemeDisplay themeDisplay =
			(ThemeDisplay)httpServletRequest.getAttribute(
				WebKeys.THEME_DISPLAY);

		PortletDisplay portletDisplay = themeDisplay.getPortletDisplay();

		_commerceCartContentTotalPortletInstanceConfiguration =
			portletDisplay.getPortletInstanceConfiguration(
				CommerceCartContentTotalPortletInstanceConfiguration.class);
	}

	public PortletURL getCheckoutPortletURL() throws PortalException {
		long plid = _portal.getPlidFromPortletId(
			commerceCartContentRequestHelper.getScopeGroupId(),
			CommercePortletKeys.COMMERCE_CHECKOUT);

		return PortletURLFactoryUtil.create(
			commerceCartContentRequestHelper.getRequest(),
			CommercePortletKeys.COMMERCE_CHECKOUT, plid,
			PortletRequest.RENDER_PHASE);
	}

	@Override
	public String getDisplayStyle() {
		return _commerceCartContentTotalPortletInstanceConfiguration.
			displayStyle();
	}

	@Override
	public long getDisplayStyleGroupId() {
		if (_displayStyleGroupId > 0) {
			return _displayStyleGroupId;
		}

		_displayStyleGroupId =
			_commerceCartContentTotalPortletInstanceConfiguration.
				displayStyleGroupId();

		if (_displayStyleGroupId <= 0) {
			_displayStyleGroupId =
				commerceCartContentRequestHelper.getScopeGroupId();
		}

		return _displayStyleGroupId;
	}

	private final CommerceCartContentTotalPortletInstanceConfiguration
		_commerceCartContentTotalPortletInstanceConfiguration;
	private long _displayStyleGroupId;
	private final Portal _portal;

}