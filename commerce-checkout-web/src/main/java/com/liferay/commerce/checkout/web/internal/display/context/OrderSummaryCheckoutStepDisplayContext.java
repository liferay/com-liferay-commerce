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

import com.liferay.commerce.checkout.web.constants.CommerceCheckoutWebKeys;
import com.liferay.commerce.constants.CommerceWebKeys;
import com.liferay.commerce.context.CommerceContext;
import com.liferay.commerce.currency.model.CommerceCurrency;
import com.liferay.commerce.model.CommerceOrder;
import com.liferay.commerce.model.CommerceOrderItem;
import com.liferay.commerce.order.CommerceOrderHttpHelper;
import com.liferay.commerce.order.CommerceOrderValidatorRegistry;
import com.liferay.commerce.order.CommerceOrderValidatorResult;
import com.liferay.commerce.payment.engine.CommercePaymentEngine;
import com.liferay.commerce.price.CommerceOrderPrice;
import com.liferay.commerce.price.CommerceOrderPriceCalculation;
import com.liferay.commerce.price.CommerceProductPrice;
import com.liferay.commerce.price.CommerceProductPriceCalculation;
import com.liferay.commerce.product.util.CPInstanceHelper;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.KeyValuePair;
import com.liferay.portal.kernel.util.WebKeys;

import java.math.BigDecimal;

import java.text.DecimalFormat;

import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Marco Leo
 */
public class OrderSummaryCheckoutStepDisplayContext {

	public OrderSummaryCheckoutStepDisplayContext(
		CommerceOrderHttpHelper commerceOrderHttpHelper,
		CommerceOrderPriceCalculation commerceOrderPriceCalculation,
		CommerceOrderValidatorRegistry commerceOrderValidatorRegistry,
		CommercePaymentEngine commercePaymentEngine,
		CommerceProductPriceCalculation commerceProductPriceCalculation,
		CPInstanceHelper cpInstanceHelper,
		HttpServletRequest httpServletRequest) {

		_commerceOrderHttpHelper = commerceOrderHttpHelper;
		_commerceOrderPriceCalculation = commerceOrderPriceCalculation;
		_commerceOrderValidatorRegistry = commerceOrderValidatorRegistry;
		_commercePaymentEngine = commercePaymentEngine;
		_commerceProductPriceCalculation = commerceProductPriceCalculation;
		_cpInstanceHelper = cpInstanceHelper;
		_httpServletRequest = httpServletRequest;

		_commerceContext = (CommerceContext)httpServletRequest.getAttribute(
			CommerceWebKeys.COMMERCE_CONTEXT);
		_commerceOrder = (CommerceOrder)httpServletRequest.getAttribute(
			CommerceCheckoutWebKeys.COMMERCE_ORDER);
	}

	public CommerceOrder getCommerceOrder() {
		return _commerceOrder;
	}

	public int getCommerceOrderItemsQuantity() throws PortalException {
		return _commerceOrderHttpHelper.getCommerceOrderItemsQuantity(
			_httpServletRequest);
	}

	public String getCommerceOrderItemThumbnailSrc(
			CommerceOrderItem commerceOrderItem, ThemeDisplay themeDisplay)
		throws Exception {

		return _cpInstanceHelper.getCPInstanceThumbnailSrc(
			commerceOrderItem.getCPInstanceId(), themeDisplay);
	}

	public CommerceOrderPrice getCommerceOrderPrice() throws PortalException {
		CommerceOrderPrice commerceOrderPrice =
			_commerceOrderPriceCalculation.getCommerceOrderPrice(
				getCommerceOrder(), _commerceContext);

		if (commerceOrderPrice != null) {
			return commerceOrderPrice;
		}

		throw new PortalException(
			"There is no price for this order, or the current user does not " +
				"have permission to view it");
	}

	public Map<Long, List<CommerceOrderValidatorResult>>
			getCommerceOrderValidatorResults()
		throws PortalException {

		ThemeDisplay themeDisplay =
			(ThemeDisplay)_httpServletRequest.getAttribute(
				WebKeys.THEME_DISPLAY);

		return _commerceOrderValidatorRegistry.getCommerceOrderValidatorResults(
			themeDisplay.getLocale(), _commerceOrder);
	}

	public CommerceProductPrice getCommerceProductPrice(
			CommerceOrderItem commerceOrderItem)
		throws PortalException {

		return _commerceProductPriceCalculation.getCommerceProductPrice(
			commerceOrderItem.getCPInstanceId(),
			commerceOrderItem.getQuantity(), _commerceContext);
	}

	public String getFormattedPercentage(BigDecimal percentage)
		throws PortalException {

		if (_commerceOrder == null) {
			return StringPool.BLANK;
		}

		CommerceCurrency commerceCurrency =
			_commerceOrder.getCommerceCurrency();

		DecimalFormat decimalFormat = new DecimalFormat();

		decimalFormat.setMaximumFractionDigits(
			commerceCurrency.getMaxFractionDigits());
		decimalFormat.setMinimumFractionDigits(
			commerceCurrency.getMinFractionDigits());
		decimalFormat.setNegativeSuffix(StringPool.PERCENT);
		decimalFormat.setPositiveSuffix(StringPool.PERCENT);

		return decimalFormat.format(percentage);
	}

	public List<KeyValuePair> getKeyValuePairs(String json, Locale locale)
		throws PortalException {

		return _cpInstanceHelper.getKeyValuePairs(json, locale);
	}

	public String getPaymentMethodName(String paymentMethodKey, Locale locale) {
		if (paymentMethodKey.isEmpty() || (locale == null)) {
			return StringPool.BLANK;
		}

		return _commercePaymentEngine.getPaymentMethodName(
			paymentMethodKey, locale);
	}

	private final CommerceContext _commerceContext;
	private final CommerceOrder _commerceOrder;
	private final CommerceOrderHttpHelper _commerceOrderHttpHelper;
	private final CommerceOrderPriceCalculation _commerceOrderPriceCalculation;
	private final CommerceOrderValidatorRegistry
		_commerceOrderValidatorRegistry;
	private final CommercePaymentEngine _commercePaymentEngine;
	private final CommerceProductPriceCalculation
		_commerceProductPriceCalculation;
	private final CPInstanceHelper _cpInstanceHelper;
	private final HttpServletRequest _httpServletRequest;

}