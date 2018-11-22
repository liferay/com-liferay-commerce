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

package com.liferay.commerce.checkout.web.internal.display.context;

import com.liferay.commerce.checkout.web.internal.display.context.util.CommerceCheckoutRequestHelper;
import com.liferay.commerce.checkout.web.internal.util.PaymentProcessCommerceCheckoutStep;
import com.liferay.commerce.checkout.web.util.CommerceCheckoutStep;
import com.liferay.commerce.checkout.web.util.CommerceCheckoutStepServicesTracker;
import com.liferay.commerce.constants.CommercePaymentConstants;
import com.liferay.commerce.constants.CommerceWebKeys;
import com.liferay.commerce.context.CommerceContext;
import com.liferay.commerce.exception.CommerceOrderBillingAddressException;
import com.liferay.commerce.exception.CommerceOrderPaymentMethodException;
import com.liferay.commerce.exception.CommerceOrderShippingAddressException;
import com.liferay.commerce.exception.CommerceOrderShippingMethodException;
import com.liferay.commerce.model.CommerceOrder;
import com.liferay.commerce.payment.engine.CommercePaymentEngine;
import com.liferay.commerce.payment.method.CommercePaymentResult;
import com.liferay.commerce.service.CommerceOrderService;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.portlet.LiferayPortletResponse;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.service.ServiceContextFactory;
import com.liferay.portal.kernel.servlet.SessionErrors;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.Portal;
import com.liferay.portal.kernel.util.Validator;

import javax.portlet.PortletURL;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Luca Pellizzon
 */
public class PaymentProcessCheckoutStepDisplayContext {

	public PaymentProcessCheckoutStepDisplayContext(
			HttpServletRequest httpServletRequest,
			CommerceOrderService commerceOrderService,
			CommercePaymentEngine commercePaymentEngine,
			CommerceCheckoutStepServicesTracker
				commerceCheckoutStepServicesTracker,
			Portal portal)
		throws Exception {

		_commerceOrderService = commerceOrderService;
		_commercePaymentEngine = commercePaymentEngine;
		_commerceCheckoutStepServicesTracker =
			commerceCheckoutStepServicesTracker;
		_portal = portal;
		_commerceOrderId = ParamUtil.getLong(
			httpServletRequest, "commerceOrderId");

		_commerceCheckoutRequestHelper = new CommerceCheckoutRequestHelper(
			httpServletRequest);
	}

	public String getNextStepUrl() throws Exception {
		LiferayPortletResponse liferayPortletResponse =
			_commerceCheckoutRequestHelper.getLiferayPortletResponse();

		CommerceCheckoutStep commerceCheckoutStep =
			_commerceCheckoutStepServicesTracker.getNextCommerceCheckoutStep(
				PaymentProcessCommerceCheckoutStep.NAME,
				_commerceCheckoutRequestHelper.getRequest(),
				_portal.getHttpServletResponse(liferayPortletResponse));

		PortletURL portletURL = liferayPortletResponse.createRenderURL();

		portletURL.setParameter(
			"commerceOrderId", String.valueOf(_commerceOrderId));

		portletURL.setParameter(
			"checkoutStepName", commerceCheckoutStep.getName());

		return portletURL.toString();
	}

	public String getUrl() {
		return _url;
	}

	public boolean redirect() throws Exception {
		int commercePaymentMethodType =
			_commercePaymentEngine.getCommercePaymentMethodType(
				_commerceOrderId);

		if (CommercePaymentConstants.
				COMMERCE_PAYMENT_METHOD_TYPE_ONLINE_REDIRECT ==
					commercePaymentMethodType) {

			return true;
		}

		return false;
	}

	public void startPayment() throws Exception {
		try {
			startPayment(_commerceCheckoutRequestHelper.getRequest());
		}
		catch (Exception e) {
			if (e instanceof CommerceOrderBillingAddressException ||
				e instanceof CommerceOrderPaymentMethodException ||
				e instanceof CommerceOrderShippingAddressException ||
				e instanceof CommerceOrderShippingMethodException) {

				SessionErrors.add(
					_commerceCheckoutRequestHelper.getRequest(), e.getClass());

				return;
			}

			throw e;
		}
	}

	protected void startPayment(HttpServletRequest httpServletRequest)
		throws Exception {

		CommerceOrder commerceOrder = _checkoutCommerceOrder(
			httpServletRequest);

		String nextStepUrl = getNextStepUrl();

		CommercePaymentResult commercePaymentResult =
			_commercePaymentEngine.processPayment(
				commerceOrder.getCommerceOrderId(),
				nextStepUrl, httpServletRequest);

		if (Validator.isNotNull(commercePaymentResult) &&
			Validator.isNotNull(commercePaymentResult.isOnlineRedirect()) &&
			commercePaymentResult.isOnlineRedirect()) {

			_url = commercePaymentResult.getRedirectUrl();
		}
	}

	private CommerceOrder _checkoutCommerceOrder(
			HttpServletRequest httpServletRequest)
		throws PortalException {

		CommerceOrder commerceOrder = _commerceOrderService.getCommerceOrder(
			_commerceOrderId);

		if (commerceOrder.isOpen()) {
			ServiceContext serviceContext = ServiceContextFactory.getInstance(
				CommerceOrder.class.getName(), httpServletRequest);

			CommerceContext commerceContext =
				(CommerceContext)httpServletRequest.getAttribute(
					CommerceWebKeys.COMMERCE_CONTEXT);

			return _commerceOrderService.checkoutCommerceOrder(
				_commerceOrderId, commerceContext, serviceContext);
		}
		else {
			return commerceOrder;
		}
	}

	private final CommerceCheckoutRequestHelper _commerceCheckoutRequestHelper;
	private final CommerceCheckoutStepServicesTracker
		_commerceCheckoutStepServicesTracker;
	private final long _commerceOrderId;
	private final CommerceOrderService _commerceOrderService;
	private final CommercePaymentEngine _commercePaymentEngine;
	private final Portal _portal;
	private String _url;

}