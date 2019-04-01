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

package com.liferay.commerce.checkout.web.internal.util;

import com.liferay.commerce.checkout.web.constants.CommerceCheckoutWebKeys;
import com.liferay.commerce.checkout.web.internal.display.context.ShippingAddressCheckoutStepDisplayContext;
import com.liferay.commerce.checkout.web.util.BaseCommerceCheckoutStep;
import com.liferay.commerce.checkout.web.util.CommerceCheckoutStep;
import com.liferay.commerce.exception.CommerceAddressCityException;
import com.liferay.commerce.exception.CommerceAddressCountryException;
import com.liferay.commerce.exception.CommerceAddressNameException;
import com.liferay.commerce.exception.CommerceAddressStreetException;
import com.liferay.commerce.exception.CommerceAddressZipException;
import com.liferay.commerce.exception.CommerceOrderBillingAddressException;
import com.liferay.commerce.exception.CommerceOrderShippingAddressException;
import com.liferay.commerce.model.CommerceOrder;
import com.liferay.commerce.service.CommerceAddressService;
import com.liferay.commerce.service.CommerceOrderService;
import com.liferay.frontend.taglib.servlet.taglib.util.JSPRenderer;
import com.liferay.portal.kernel.security.permission.resource.ModelResourcePermission;
import com.liferay.portal.kernel.servlet.SessionErrors;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Andrea Di Giorgi
 * @author Luca Pellizzon
 */
@Component(
	immediate = true,
	property = {
		"commerce.checkout.step.name=" + ShippingAddressCommerceCheckoutStep.NAME,
		"commerce.checkout.step.order:Integer=10"
	},
	service = CommerceCheckoutStep.class
)
public class ShippingAddressCommerceCheckoutStep
	extends BaseCommerceCheckoutStep {

	public static final String NAME = "shipping-address";

	@Override
	public String getName() {
		return NAME;
	}

	@Override
	public void processAction(
			ActionRequest actionRequest, ActionResponse actionResponse)
		throws Exception {

		try {
			AddressCommerceCheckoutStepUtil addressCommerceCheckoutStepUtil =
				new AddressCommerceCheckoutStepUtil(
					commerceOrderService, commerceAddressService,
					commerceOrderModelResourcePermission);

			CommerceOrder commerceOrder =
				addressCommerceCheckoutStepUtil.updateCommerceOrderAddress(
					actionRequest,
					CommerceCheckoutWebKeys.SHIPPING_ADDRESS_PARAM_NAME);

			actionRequest.setAttribute(
				CommerceCheckoutWebKeys.COMMERCE_ORDER, commerceOrder);
		}
		catch (Exception e) {
			if (e instanceof CommerceAddressCityException ||
				e instanceof CommerceAddressCountryException ||
				e instanceof CommerceAddressNameException ||
				e instanceof CommerceAddressStreetException ||
				e instanceof CommerceAddressZipException ||
				e instanceof CommerceOrderBillingAddressException ||
				e instanceof CommerceOrderShippingAddressException) {

				SessionErrors.add(actionRequest, e.getClass());

				return;
			}

			throw e;
		}
	}

	@Override
	public void render(
			HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse)
		throws Exception {

		httpServletRequest.setAttribute(
			CommerceCheckoutWebKeys.COMMERCE_CHECKOUT_STEP_DISPLAY_CONTEXT,
			new ShippingAddressCheckoutStepDisplayContext(
				commerceAddressService, httpServletRequest));

		jspRenderer.renderJSP(
			httpServletRequest, httpServletResponse,
			"/checkout_step/address.jsp");
	}

	@Reference
	protected CommerceAddressService commerceAddressService;

	@Reference(
		target = "(model.class.name=com.liferay.commerce.model.CommerceOrder)"
	)
	protected ModelResourcePermission<CommerceOrder>
		commerceOrderModelResourcePermission;

	@Reference
	protected CommerceOrderService commerceOrderService;

	@Reference
	protected JSPRenderer jspRenderer;

}