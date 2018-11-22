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

package com.liferay.commerce.payment.engine.impl;

import com.liferay.commerce.constants.CommerceOrderConstants;
import com.liferay.commerce.constants.CommerceOrderPaymentConstants;
import com.liferay.commerce.model.CommerceOrder;
import com.liferay.commerce.payment.engine.CommercePaymentEngine;
import com.liferay.commerce.payment.method.CommercePaymentEngineMethod;
import com.liferay.commerce.payment.method.CommercePaymentEngineMethodRegistry;
import com.liferay.commerce.payment.method.CommercePaymentRequest;
import com.liferay.commerce.payment.method.CommercePaymentResult;
import com.liferay.commerce.payment.model.CommercePaymentMethodGroupRel;
import com.liferay.commerce.payment.service.CommercePaymentMethodGroupRelService;
import com.liferay.commerce.service.CommerceOrderLocalService;
import com.liferay.commerce.service.CommerceOrderPaymentLocalService;
import com.liferay.commerce.service.CommerceOrderService;
import com.liferay.portal.kernel.exception.PortalException;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Locale;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Luca Pellizzon
 */
@Component(immediate = true, service = CommercePaymentEngine.class)
public class CommercePaymentEngineImpl implements CommercePaymentEngine {

	@Override
	public CommercePaymentResult cancelPayment(
			long commerceOrderId, String transactionId,
			HttpServletRequest httpServletRequest)
		throws Exception {

		long commerceOrderId = commercePaymentRequest.getCommerceOrderId();

		CommercePaymentEngineMethod commercePaymentEngineMethod =
			_getCommercePaymentMethod(commerceOrderId);

		if ((commercePaymentEngineMethod == null) ||
			!commercePaymentEngineMethod.isCancelEnabled()) {

			return new CommercePaymentResult(
				null, commerceOrderId, -1, false, null, null,
				Collections.emptyList(), false);
		}

		CommerceOrder commerceOrder = _commerceOrderService.getCommerceOrder(
			commerceOrderId);

		CommercePaymentRequest commercePaymentRequest =
			_getCommercePaymentRequest(
				commerceOrder, _portal.getLocale(httpServletRequest),
				transactionId, null, httpServletRequest,
				commercePaymentEngineMethod);

		CommercePaymentResult completePaymentResult =
			commercePaymentEngineMethod.cancelPayment(commercePaymentRequest);

		int status = completePaymentResult.getNewPaymentStatus();

		try {
			_commerceOrderLocalService.updatePaymentStatus(
				commerceOrder.getUserId(), commerceOrderId,
				CommerceOrderConstants.ORDER_STATUS_CANCELLED);
		}
		catch (Exception e) {
			status = CommerceOrderPaymentConstants.STATUS_FAILED;
		}

		_commerceOrderPaymentLocalService.addCommerceOrderPayment(
			commerceOrderId, status, "");

		boolean success = false;

		if (status != CommerceOrderPaymentConstants.STATUS_FAILED) {
			success = true;
		}

		CommercePaymentResult commercePaymentResult = new CommercePaymentResult(
			completePaymentResult.getAuthTransactionId(), commerceOrderId,
			CommerceOrderConstants.ORDER_STATUS_CANCELLED,
			completePaymentResult.isOnlineRedirect(),
			completePaymentResult.getRedirectUrl(),
			completePaymentResult.getRefundId(),
			completePaymentResult.getResultMessages(), success);

		return commercePaymentResult;
	}

	@Override
	public CommercePaymentResult cancelRecurringPayment(
		CommercePaymentRequest commercePaymentRequest) {

		long commerceOrderId = commercePaymentRequest.getCommerceOrderId();

		return new CommercePaymentResult(
			null, commerceOrderId, -1, false, null, null,
			Collections.emptyList(), false);
	}

	@Override
	public CommercePaymentResult capturePayment(
			long commerceOrderId, String transactionId,
			HttpServletRequest httpServletRequest)
		throws Exception {

		long commerceOrderId = commercePaymentRequest.getCommerceOrderId();

		CommercePaymentEngineMethod commercePaymentEngineMethod =
			_getCommercePaymentMethod(commerceOrderId);

		if ((commercePaymentEngineMethod == null) ||
			!commercePaymentEngineMethod.isCaptureEnabled()) {

			return new CommercePaymentResult(
				null, commerceOrderId, -1, false, null, null,
				Collections.emptyList(), false);
		}

		CommerceOrder commerceOrder = _commerceOrderService.getCommerceOrder(
			commerceOrderId);

		CommercePaymentRequest commercePaymentRequest =
			_getCommercePaymentRequest(
				commerceOrder, _portal.getLocale(httpServletRequest),
				transactionId, null, httpServletRequest,
				commercePaymentEngineMethod);

		return commercePaymentEngineMethod.capturePayment(
			commercePaymentRequest);
	}

	@Override
	public CommercePaymentResult completePayment(
			long commerceOrderId, String transactionId,
			HttpServletRequest httpServletRequest)
		throws Exception {

		long commerceOrderId = commercePaymentRequest.getCommerceOrderId();

		CommercePaymentEngineMethod commercePaymentEngineMethod =
			_getCommercePaymentMethod(commerceOrderId);

		if ((commercePaymentEngineMethod == null) ||
			!commercePaymentEngineMethod.isCompleteEnabled()) {

			return new CommercePaymentResult(
				null, commerceOrderId, -1, false, null, null,
				Collections.emptyList(), false);
		}

		CommerceOrder commerceOrder = _commerceOrderService.getCommerceOrder(
			commerceOrderId);

		CommercePaymentRequest commercePaymentRequest =
			_getCommercePaymentRequest(
				commerceOrder, _portal.getLocale(httpServletRequest),
				transactionId, null, httpServletRequest,
				commercePaymentEngineMethod);

		CommercePaymentResult completePaymentResult =
			commercePaymentEngineMethod.completePayment(commercePaymentRequest);

		int paymentStatus = completePaymentResult.getNewPaymentStatus();

		int status = completePaymentResult.getNewPaymentStatus();

		try {
			_commerceOrderLocalService.updatePaymentStatusAndTransactionId(
				commerceOrder.getUserId(), commerceOrderId, paymentStatus,
				completePaymentResult.getAuthTransactionId());
		}
		catch (Exception e) {
			status = CommerceOrderPaymentConstants.STATUS_FAILED;
		}

		_commerceOrderPaymentLocalService.addCommerceOrderPayment(
			commerceOrderId, status, "");

		boolean success = false;

		if (status != CommerceOrderPaymentConstants.STATUS_FAILED) {
			success = true;
		}

		CommercePaymentResult commercePaymentResult = new CommercePaymentResult(
			completePaymentResult.getAuthTransactionId(), commerceOrderId,
			paymentStatus, completePaymentResult.isOnlineRedirect(),
			completePaymentResult.getRedirectUrl(),
			completePaymentResult.getRefundId(),
			completePaymentResult.getResultMessages(), success);

		return commercePaymentResult;
	}

	@Override
	public List<CommercePaymentEngineMethod> getCommercePaymentEngineMethods(
			long groupId)
		throws PortalException {

		List<CommercePaymentMethodGroupRel> commercePaymentMethodGroupRels =
			_commercePaymentMethodGroupRelService.
				getCommercePaymentMethodGroupRels(groupId, true);

		List<CommercePaymentEngineMethod> commercePaymentEngineMethods =
			new ArrayList<>();

		for (CommercePaymentMethodGroupRel commercePaymentMethodGroupRel :
				commercePaymentMethodGroupRels) {

			CommercePaymentEngineMethod commercePaymentEngineMethod =
				_commercePaymentEngineMethodRegistry.
					getCommercePaymentEngineMethod(
						commercePaymentMethodGroupRel.getEngineKey());

			commercePaymentEngineMethods.add(commercePaymentEngineMethod);
		}

		return commercePaymentEngineMethods;
	}

	@Override
	public List<CommercePaymentEngineMethod> getCommercePaymentEngineMethods(
			long groupId, long commerceCountryId)
		throws PortalException {

		List<CommercePaymentMethodGroupRel> commercePaymentMethodGroupRels =
			_commercePaymentMethodGroupRelService.
				getCommercePaymentMethodGroupRels(
					groupId, commerceCountryId, true);

		List<CommercePaymentEngineMethod> commercePaymentEngineMethods =
			new ArrayList<>();

		for (CommercePaymentMethodGroupRel commercePaymentMethodGroupRel :
				commercePaymentMethodGroupRels) {

			CommercePaymentEngineMethod commercePaymentEngineMethod =
				_commercePaymentEngineMethodRegistry.
					getCommercePaymentEngineMethod(
						commercePaymentMethodGroupRel.getEngineKey());

			commercePaymentEngineMethods.add(commercePaymentEngineMethod);
		}

		return commercePaymentEngineMethods;
	}

	@Override
	public int getCommercePaymentMethodType(
			CommercePaymentRequest commercePaymentRequest)
		throws PortalException {

		long commerceOrderId = commercePaymentRequest.getCommerceOrderId();

		CommerceOrder commerceOrder = _commerceOrderService.getCommerceOrder(
			commerceOrderId);

		String commercePaymentMethodKey =
			commerceOrder.getCommercePaymentMethodKey();

		if (commercePaymentMethodKey.isEmpty()) {
			return -1;
		}

		CommercePaymentEngineMethod commercePaymentEngineMethod =
			_commercePaymentEngineMethodRegistry.getCommercePaymentEngineMethod(
				commercePaymentMethodKey);

		return commercePaymentEngineMethod.getPaymentType();
	}

	@Override
	public String getPaymentMethodName(String paymentMethodKey, Locale locale) {
		CommercePaymentEngineMethod commercePaymentEngineMethod =
			_commercePaymentEngineMethodRegistry.getCommercePaymentEngineMethod(
				paymentMethodKey);

		return commercePaymentEngineMethod.getName(locale);
	}

	@Override
	public CommercePaymentResult partiallyRefundPayment(
		CommercePaymentRequest commercePaymentRequest) {

		long commerceOrderId = commercePaymentRequest.getCommerceOrderId();

		return new CommercePaymentResult(
			null, commerceOrderId, -1, false, null, null,
			Collections.emptyList(), false);
	}

	@Override
	public CommercePaymentResult postProcessPayment(
			CommercePaymentRequest commercePaymentRequest)
		throws Exception {

		long commerceOrderId = commercePaymentRequest.getCommerceOrderId();

		return new CommercePaymentResult(
			null, commerceOrderId, -1, false, null, null,
			Collections.emptyList(), false);
	}

	@Override
	public CommercePaymentResult processPayment(
			long commerceOrderId, String checkoutStepUrl,
			HttpServletRequest httpServletRequest)
		throws Exception {

		long commerceOrderId = commercePaymentRequest.getCommerceOrderId();

		CommercePaymentEngineMethod commercePaymentEngineMethod =
			_getCommercePaymentMethod(commerceOrderId);

		if (commercePaymentEngineMethod == null) {
			return new CommercePaymentResult(
				null, commerceOrderId, -1, false, null, null,
				Collections.emptyList(), false);
		}

		if (commercePaymentEngineMethod.isProcessPaymentEnabled()) {
			CommerceOrder commerceOrder =
				_commerceOrderService.getCommerceOrder(commerceOrderId);

			CommercePaymentRequest commercePaymentRequest =
				_getCommercePaymentRequest(
					commerceOrder, _portal.getLocale(httpServletRequest), null,
					checkoutStepUrl, httpServletRequest,
					commercePaymentEngineMethod);

			CommercePaymentResult processPaymentResult =
				commercePaymentEngineMethod.processPayment(
					commercePaymentRequest);

			int paymentStatus = processPaymentResult.getNewPaymentStatus();

			int status = CommerceOrderPaymentConstants.STATUS_PENDING;

			try {
				_commerceOrderService.updatePaymentStatusAndTransactionId(
					commerceOrderId, paymentStatus,
					processPaymentResult.getAuthTransactionId());
			}
			catch (Exception e) {
				status = CommerceOrderPaymentConstants.STATUS_FAILED;
			}

			boolean success = false;

			if (status != CommerceOrderPaymentConstants.STATUS_FAILED) {
				success = true;
			}

			CommercePaymentResult commercePaymentResult =
				new CommercePaymentResult(
					processPaymentResult.getAuthTransactionId(),
					commerceOrderId, paymentStatus,
					processPaymentResult.isOnlineRedirect(),
					processPaymentResult.getRedirectUrl(),
					processPaymentResult.getRefundId(),
					processPaymentResult.getResultMessages(), success);

			return commercePaymentResult;
		}

		return new CommercePaymentResult(
			null, commerceOrderId, -1, false, null, null,
			Collections.emptyList(), false);
	}

	@Override
	public CommercePaymentResult processRecurringPayment(
		CommercePaymentRequest commercePaymentRequest) {

		long commerceOrderId = commercePaymentRequest.getCommerceOrderId();

		return new CommercePaymentResult(
			null, commerceOrderId, -1, false, null, null,
			Collections.emptyList(), false);
	}

	@Override
	public CommercePaymentResult refundPayment(
			long commerceOrderId, String transactionId,
			HttpServletRequest httpServletRequest)
		throws Exception {

		long commerceOrderId = commercePaymentRequest.getCommerceOrderId();

		CommercePaymentEngineMethod commercePaymentEngineMethod =
			_getCommercePaymentMethod(commerceOrderId);

		if ((commercePaymentEngineMethod == null) ||
			!commercePaymentEngineMethod.isRefundEnabled()) {

			return new CommercePaymentResult(
				null, commerceOrderId, -1, false, null, null,
				Collections.emptyList(), false);
		}

		CommerceOrder commerceOrder = _commerceOrderService.getCommerceOrder(
			commerceOrderId);

		CommercePaymentRequest commercePaymentRequest =
			_getCommercePaymentRequest(
				commerceOrder, _portal.getLocale(httpServletRequest),
				transactionId, null, httpServletRequest,
				commercePaymentEngineMethod);

		return commercePaymentEngineMethod.refundPayment(
			commercePaymentRequest);
	}

	@Override
	public CommercePaymentResult voidTransaction(
			long commerceOrderId, String transactionId,
			HttpServletRequest httpServletRequest)
		throws Exception {

		long commerceOrderId = commercePaymentRequest.getCommerceOrderId();

		CommercePaymentEngineMethod commercePaymentEngineMethod =
			_getCommercePaymentMethod(commerceOrderId);

		if (commercePaymentEngineMethod == null) {
			return new CommercePaymentResult(
				null, commerceOrderId, -1, false, null, null,
				Collections.emptyList(), false);
		}

		if (commercePaymentEngineMethod.isVoidEnabled()) {
			CommerceOrder commerceOrder =
				_commerceOrderService.getCommerceOrder(commerceOrderId);

			CommercePaymentRequest commercePaymentRequest =
				commercePaymentEngineMethod.enrichPaymentRequest(
					commerceOrder, _portal.getLocale(httpServletRequest),
					transactionId, null, null, null);

			return commercePaymentEngineMethod.voidTransaction(
				commercePaymentRequest);
		}

		return new CommercePaymentResult(
			null, commerceOrderId, -1, false, null, null,
			Collections.emptyList(), false);
	}

	private CommercePaymentEngineMethod _getCommercePaymentMethod(
			long commerceOrderId)
		throws PortalException {

		CommerceOrder commerceOrder = _commerceOrderService.getCommerceOrder(
			commerceOrderId);

		String commercePaymentMethodKey =
			commerceOrder.getCommercePaymentMethodKey();

		if (commercePaymentMethodKey.isEmpty()) {
			return null;
		}

		CommercePaymentMethodGroupRel commercePaymentMethodGroupRel =
			_commercePaymentMethodGroupRelService.
				getCommercePaymentMethodGroupRel(
					commerceOrder.getGroupId(), commercePaymentMethodKey);

		if ((commercePaymentMethodGroupRel != null) &&
			commercePaymentMethodGroupRel.isActive()) {

			return _commercePaymentEngineMethodRegistry.
				getCommercePaymentEngineMethod(
					commercePaymentMethodGroupRel.getEngineKey());
		}

		return null;
	}

	@Reference
	private CommerceOrderLocalService _commerceOrderLocalService;

	@Reference
	private CommerceOrderPaymentLocalService _commerceOrderPaymentLocalService;

	@Reference
	private CommerceOrderService _commerceOrderService;

	@Reference
	private CommercePaymentEngineMethodRegistry
		_commercePaymentEngineMethodRegistry;

	@Reference
	private CommercePaymentMethodGroupRelService
		_commercePaymentMethodGroupRelService;

}