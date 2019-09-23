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

import com.liferay.commerce.checkout.web.internal.display.context.OrderSummaryCheckoutStepDisplayContext;
import com.liferay.commerce.constants.CommerceCheckoutWebKeys;
import com.liferay.commerce.constants.CommerceWebKeys;
import com.liferay.commerce.context.CommerceContext;
import com.liferay.commerce.exception.CommerceOrderBillingAddressException;
import com.liferay.commerce.exception.CommerceOrderPaymentMethodException;
import com.liferay.commerce.exception.CommerceOrderShippingAddressException;
import com.liferay.commerce.exception.CommerceOrderShippingMethodException;
import com.liferay.commerce.inventory.engine.CommerceInventoryEngine;
import com.liferay.commerce.model.CommerceOrder;
import com.liferay.commerce.order.CommerceOrderHttpHelper;
import com.liferay.commerce.order.CommerceOrderValidatorRegistry;
import com.liferay.commerce.payment.engine.CommercePaymentEngine;
import com.liferay.commerce.price.CommerceOrderPriceCalculation;
import com.liferay.commerce.price.CommerceProductPriceCalculation;
import com.liferay.commerce.product.util.CPInstanceHelper;
import com.liferay.commerce.service.CommerceOrderItemService;
import com.liferay.commerce.service.CommerceOrderService;
import com.liferay.commerce.util.BaseCommerceCheckoutStep;
import com.liferay.commerce.util.CommerceCheckoutStep;
import com.liferay.frontend.taglib.servlet.taglib.util.JSPRenderer;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.module.configuration.ConfigurationProvider;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.service.ServiceContextFactory;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.Portal;
import com.liferay.portal.kernel.util.WebKeys;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Marco Leo
 * @author Andrea Di Giorgi
 * @author Alessio Antonio Rendina
 */
@Component(
	immediate = true,
	property = {
		"commerce.checkout.step.name=" + OrderSummaryCommerceCheckoutStep.NAME,
		"commerce.checkout.step.order:Integer=" + (Integer.MAX_VALUE - 150)
	},
	service = CommerceCheckoutStep.class
)
public class OrderSummaryCommerceCheckoutStep extends BaseCommerceCheckoutStep {

	public static final String NAME = "order-summary";

	@Override
	public String getName() {
		return NAME;
	}

	@Override
	public boolean isSennaDisabled() {
		return true;
	}

	@Override
	public void processAction(
			ActionRequest actionRequest, ActionResponse actionResponse)
		throws Exception {

		long commerceOrderId = ParamUtil.getLong(
			actionRequest, "commerceOrderId");

		_validateCommerceOrder(actionRequest, commerceOrderId);

		_checkoutCommerceOrder(_portal.getHttpServletRequest(actionRequest));
	}

	@Override
	public void render(
			HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse)
		throws Exception {

		OrderSummaryCheckoutStepDisplayContext
			orderSummaryCheckoutStepDisplayContext =
				new OrderSummaryCheckoutStepDisplayContext(
					_commerceOrderHttpHelper, _commerceOrderPriceCalculation,
					_commerceOrderValidatorRegistry, _commercePaymentEngine,
					_commerceProductPriceCalculation, _cpInstanceHelper,
					httpServletRequest);

		CommerceOrder commerceOrder =
			orderSummaryCheckoutStepDisplayContext.getCommerceOrder();

		if (!commerceOrder.isOpen()) {
			httpServletRequest.setAttribute(
				CommerceCheckoutWebKeys.COMMERCE_CHECKOUT_STEP_ORDER_DETAIL_URL,
				_commerceCheckoutStepHelper.getOrderDetailURL(
					httpServletRequest, commerceOrder));

			_jspRenderer.renderJSP(
				httpServletRequest, httpServletResponse, "/error.jsp");
		}
		else {
			httpServletRequest.setAttribute(
				CommerceCheckoutWebKeys.COMMERCE_CHECKOUT_STEP_DISPLAY_CONTEXT,
				orderSummaryCheckoutStepDisplayContext);

			_jspRenderer.renderJSP(
				httpServletRequest, httpServletResponse,
				"/checkout_step/order_summary.jsp");
		}
	}

	@Override
	public boolean showControls(
		HttpServletRequest httpServletRequest,
		HttpServletResponse httpServletResponse) {

		CommerceOrder commerceOrder =
			(CommerceOrder)httpServletRequest.getAttribute(
				CommerceCheckoutWebKeys.COMMERCE_ORDER);

		if (!commerceOrder.isOpen()) {
			return false;
		}

		try {
			ThemeDisplay themeDisplay =
				(ThemeDisplay)httpServletRequest.getAttribute(
					WebKeys.THEME_DISPLAY);

			return _commerceOrderValidatorRegistry.isValid(
				themeDisplay.getLocale(), commerceOrder);
		}
		catch (PortalException pe) {
			_log.error(pe, pe);

			return false;
		}
	}

	private void _checkoutCommerceOrder(HttpServletRequest httpServletRequest)
		throws PortalException {

		CommerceOrder commerceOrder =
			(CommerceOrder)httpServletRequest.getAttribute(
				CommerceCheckoutWebKeys.COMMERCE_ORDER);

		if (commerceOrder.isOpen()) {
			ServiceContext serviceContext = ServiceContextFactory.getInstance(
				CommerceOrder.class.getName(), httpServletRequest);

			CommerceContext commerceContext =
				(CommerceContext)httpServletRequest.getAttribute(
					CommerceWebKeys.COMMERCE_CONTEXT);

			_commerceOrderService.checkoutCommerceOrder(
				commerceOrder.getCommerceOrderId(), commerceContext,
				serviceContext);
		}
	}

	private void _validateCommerceOrder(
			ActionRequest actionRequest, long commerceOrderId)
		throws PortalException {

		CommerceOrder commerceOrder = _commerceOrderService.getCommerceOrder(
			commerceOrderId);

		if (commerceOrder.getShippingAddressId() <= 0) {
			throw new CommerceOrderShippingAddressException();
		}

		HttpServletRequest httpServletRequest = _portal.getHttpServletRequest(
			actionRequest);

		if ((commerceOrder.getBillingAddressId() <= 0) &&
			_commerceCheckoutStepHelper.
				isActiveBillingAddressCommerceCheckoutStep(
					httpServletRequest)) {

			throw new CommerceOrderBillingAddressException();
		}

		if ((commerceOrder.getCommerceShippingMethodId() <= 0) &&
			_commerceCheckoutStepHelper.
				isActiveShippingMethodCommerceCheckoutStep(
					httpServletRequest)) {

			throw new CommerceOrderShippingMethodException();
		}

		String commercePaymentMethodKey =
			commerceOrder.getCommercePaymentMethodKey();

		if (commercePaymentMethodKey.isEmpty() &&
			_commerceCheckoutStepHelper.
				isActivePaymentMethodCommerceCheckoutStep(httpServletRequest)) {

			throw new CommerceOrderPaymentMethodException();
		}
	}

	private static final Log _log = LogFactoryUtil.getLog(
		OrderSummaryCommerceCheckoutStep.class);

	@Reference
	private CommerceCheckoutStepHelper _commerceCheckoutStepHelper;

	@Reference
	private CommerceInventoryEngine _commerceInventoryEngine;

	@Reference
	private CommerceOrderHttpHelper _commerceOrderHttpHelper;

	@Reference
	private CommerceOrderItemService _commerceOrderItemService;

	@Reference
	private CommerceOrderPriceCalculation _commerceOrderPriceCalculation;

	@Reference
	private CommerceOrderService _commerceOrderService;

	@Reference
	private CommerceOrderValidatorRegistry _commerceOrderValidatorRegistry;

	@Reference
	private CommercePaymentEngine _commercePaymentEngine;

	@Reference
	private CommerceProductPriceCalculation _commerceProductPriceCalculation;

	@Reference
	private ConfigurationProvider _configurationProvider;

	@Reference
	private CPInstanceHelper _cpInstanceHelper;

	@Reference
	private JSPRenderer _jspRenderer;

	@Reference
	private Portal _portal;

}