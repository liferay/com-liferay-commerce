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

package com.liferay.commerce.payment.engine;

import aQute.bnd.annotation.ProviderType;

import com.liferay.commerce.payment.method.CommercePaymentEngineMethod;
import com.liferay.commerce.payment.method.CommercePaymentRequest;
import com.liferay.commerce.payment.method.CommercePaymentResult;
import com.liferay.portal.kernel.exception.PortalException;

import java.util.List;
import java.util.Locale;

/**
 * @author Luca Pellizzon
 */
@ProviderType
public interface CommercePaymentEngine {

	public CommercePaymentResult cancelPayment(
			long commerceOrderId, String transactionId,
			HttpServletRequest httpServletRequest)
		throws Exception;

	public CommercePaymentResult cancelRecurringPayment(
		CommercePaymentRequest commercePaymentRequest);

	public CommercePaymentResult capturePayment(
			long commerceOrderId, String transactionId,
			HttpServletRequest httpServletRequest)
		throws Exception;

	public CommercePaymentResult completePayment(
			long commerceOrderId, String transactionId,
			HttpServletRequest httpServletRequest)
		throws Exception;

	public List<CommercePaymentEngineMethod> getCommercePaymentEngineMethods(
			long groupId)
		throws PortalException;

	public List<CommercePaymentEngineMethod> getCommercePaymentEngineMethods(
			long groupId, long commerceCountryId)
		throws PortalException;

	public int getCommercePaymentMethodType(
			CommercePaymentRequest commercePaymentRequest)
		throws Exception;

	public String getPaymentMethodName(String paymentMethodKey, Locale locale);

	public CommercePaymentResult partiallyRefundPayment(
		CommercePaymentRequest commercePaymentRequest);

	public CommercePaymentResult postProcessPayment(
			CommercePaymentRequest commercePaymentRequest)
		throws Exception;

	public CommercePaymentResult processPayment(
			long commerceOrderId, String checkoutStepUrl,
			HttpServletRequest httpServletRequest)
		throws Exception;

	public CommercePaymentResult processRecurringPayment(
		CommercePaymentRequest commercePaymentRequest);

	public CommercePaymentResult refundPayment(
			long commerceOrderId, String transactionId,
			HttpServletRequest httpServletRequest)
		throws Exception;

	public CommercePaymentResult voidTransaction(
			long commerceOrderId, String transactionId,
			HttpServletRequest httpServletRequest)
		throws Exception;

}