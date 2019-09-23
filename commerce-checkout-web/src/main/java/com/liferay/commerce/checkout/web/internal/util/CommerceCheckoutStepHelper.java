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

import com.liferay.commerce.constants.CommerceCheckoutWebKeys;
import com.liferay.commerce.constants.CommerceWebKeys;
import com.liferay.commerce.context.CommerceContext;
import com.liferay.commerce.currency.model.CommerceMoney;
import com.liferay.commerce.model.CommerceAddress;
import com.liferay.commerce.model.CommerceOrder;
import com.liferay.commerce.order.CommerceOrderHttpHelper;
import com.liferay.commerce.payment.engine.CommercePaymentEngine;
import com.liferay.commerce.payment.method.CommercePaymentMethod;
import com.liferay.commerce.price.CommerceOrderPrice;
import com.liferay.commerce.price.CommerceOrderPriceCalculation;
import com.liferay.commerce.service.CommerceOrderService;
import com.liferay.commerce.service.CommerceShippingMethodLocalService;
import com.liferay.commerce.util.CommerceShippingHelper;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.util.Portal;

import java.math.BigDecimal;

import java.util.List;
import java.util.Objects;

import javax.portlet.PortletURL;

import javax.servlet.http.HttpServletRequest;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Alessio Antonio Rendina
 * @author Luca Pellizzon
 */
@Component(immediate = true, service = CommerceCheckoutStepHelper.class)
public class CommerceCheckoutStepHelper {

	public String getOrderDetailURL(
			HttpServletRequest httpServletRequest, CommerceOrder commerceOrder)
		throws PortalException {

		PortletURL portletURL =
			_commerceOrderHttpHelper.getCommerceCartPortletURL(
				httpServletRequest, commerceOrder);

		if (portletURL == null) {
			return StringPool.BLANK;
		}

		return portletURL.toString();
	}

	public boolean isActiveBillingAddressCommerceCheckoutStep(
			HttpServletRequest httpServletRequest)
		throws PortalException {

		CommerceOrder commerceOrder =
			(CommerceOrder)httpServletRequest.getAttribute(
				CommerceCheckoutWebKeys.COMMERCE_ORDER);

		if ((commerceOrder.getBillingAddressId() > 0) &&
			Objects.equals(
				commerceOrder.getBillingAddressId(),
				commerceOrder.getShippingAddressId())) {

			return false;
		}

		return true;
	}

	public boolean isActivePaymentMethodCommerceCheckoutStep(
			HttpServletRequest httpServletRequest)
		throws PortalException {

		CommerceOrder commerceOrder =
			(CommerceOrder)httpServletRequest.getAttribute(
				CommerceCheckoutWebKeys.COMMERCE_ORDER);

		if (_commercePaymentEngine.getCommercePaymentMethodGroupRelsCount(
				_portal.getScopeGroupId(httpServletRequest)) < 1) {

			return false;
		}

		CommerceContext commerceContext =
			(CommerceContext)httpServletRequest.getAttribute(
				CommerceWebKeys.COMMERCE_CONTEXT);

		CommerceOrderPrice commerceOrderPrice =
			_commerceOrderPriceCalculation.getCommerceOrderPrice(
				commerceOrder, commerceContext);

		CommerceMoney orderPriceTotal = commerceOrderPrice.getTotal();

		if (BigDecimal.ZERO.compareTo(orderPriceTotal.getPrice()) == 0) {
			return false;
		}

		List<CommercePaymentMethod> commercePaymentMethods =
			_commercePaymentEngine.getEnabledCommercePaymentMethodsForOrder(
				commerceContext.getSiteGroupId(),
				commerceOrder.getCommerceOrderId());

		if (commercePaymentMethods.isEmpty()) {
			return false;
		}

		if (commercePaymentMethods.size() == 1) {
			CommercePaymentMethod commercePaymentMethod =
				commercePaymentMethods.get(0);

			_updateCommerceOrder(
				httpServletRequest, commerceOrder,
				commercePaymentMethod.getKey());

			return false;
		}

		return true;
	}

	public boolean isActiveShippingMethodCommerceCheckoutStep(
			HttpServletRequest httpServletRequest)
		throws PortalException {

		CommerceOrder commerceOrder =
			(CommerceOrder)httpServletRequest.getAttribute(
				CommerceCheckoutWebKeys.COMMERCE_ORDER);

		if (!_commerceShippingHelper.isShippable(commerceOrder) ||
			_commerceShippingHelper.isFreeShipping(commerceOrder)) {

			return false;
		}

		if (_commerceShippingMethodLocalService.getCommerceShippingMethodsCount(
				_portal.getScopeGroupId(httpServletRequest), true) > 0) {

			return true;
		}

		return false;
	}

	private void _updateCommerceOrder(
			HttpServletRequest httpServletRequest, CommerceOrder commerceOrder,
			String commercePaymentMethodKey)
		throws PortalException {

		CommerceAddress commerceAddress = commerceOrder.getBillingAddress();

		if (commerceAddress == null) {
			commerceAddress = commerceOrder.getShippingAddress();
		}

		if (commerceAddress == null) {
			return;
		}

		if (commercePaymentMethodKey.equals(
				commerceOrder.getCommercePaymentMethodKey())) {

			return;
		}

		commerceOrder = _commerceOrderService.updateCommercePaymentMethodKey(
			commerceOrder.getCommerceOrderId(), commercePaymentMethodKey);

		httpServletRequest.setAttribute(
			CommerceCheckoutWebKeys.COMMERCE_ORDER, commerceOrder);
	}

	@Reference
	private CommerceOrderHttpHelper _commerceOrderHttpHelper;

	@Reference
	private CommerceOrderPriceCalculation _commerceOrderPriceCalculation;

	@Reference
	private CommerceOrderService _commerceOrderService;

	@Reference
	private CommercePaymentEngine _commercePaymentEngine;

	@Reference
	private CommerceShippingHelper _commerceShippingHelper;

	@Reference
	private CommerceShippingMethodLocalService
		_commerceShippingMethodLocalService;

	@Reference
	private Portal _portal;

}