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
import com.liferay.commerce.model.CommercePaymentMethodGroupRel;
import com.liferay.commerce.payment.engine.CommercePaymentEngine;
import com.liferay.commerce.payment.method.CommercePaymentEngineMethod;
import com.liferay.commerce.payment.method.CommercePaymentEngineMethodRegistry;
import com.liferay.commerce.payment.method.CommercePaymentRequest;
import com.liferay.commerce.payment.method.CommercePaymentResult;
import com.liferay.commerce.service.CommerceOrderPaymentLocalService;
import com.liferay.commerce.service.CommerceOrderService;
import com.liferay.commerce.service.CommercePaymentMethodGroupRelService;
import com.liferay.portal.kernel.exception.PortalException;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Luca Pellizzon
 */
@Component(immediate = true, service = CommercePaymentEngine.class)
public class CommercePaymentEngineImpl implements CommercePaymentEngine {

	@Override
	public CommercePaymentResult cancelPayment(
			CommercePaymentRequest commercePaymentRequest)
		throws Exception {

		long commerceOrderId = commercePaymentRequest.getCommerceOrderId();

		CommerceOrder commerceOrder = _commerceOrderService.getCommerceOrder(
			commerceOrderId);

		long CommercePaymentMethodGroupRelId =
			commerceOrder.getCommercePaymentMethodGroupRelId();

		CommercePaymentMethodGroupRel CommercePaymentMethodGroupRel =
			_CommercePaymentMethodGroupRelService.getCommercePaymentMethodGroupRel(
				CommercePaymentMethodGroupRelId);

		int paymentStatus = CommerceOrderConstants.ORDER_STATUS_CANCELLED;

		if (CommercePaymentMethodGroupRel.isActive()) {
			CommercePaymentEngineMethod commercePaymentEngineMethod =
				_commercePaymentEngineMethodRegistry.getCommercePaymentMethodGroupRel(
					CommercePaymentMethodGroupRel.getEngineKey());

			if (commercePaymentEngineMethod.isCancelEnabled()) {
				CommercePaymentResult completePaymentResult =
					commercePaymentEngineMethod.cancelPayment(
						commercePaymentRequest);

				int status = completePaymentResult.getNewPaymentStatus();

				try {
					_commerceOrderService.updatePaymentStatus(
						commerceOrder.getCommerceOrderId(), paymentStatus);
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

				CommercePaymentResult commercePaymentResult =
					new CommercePaymentResult(
						completePaymentResult.getAuthTransactionId(),
						commerceOrderId, paymentStatus,
						completePaymentResult.isOnlineRedirect(),
						completePaymentResult.getRedirectUrl(),
						completePaymentResult.getRefundId(),
						completePaymentResult.getResultMessages(), success);

				return commercePaymentResult;
			}
		}

		return new CommercePaymentResult(
			null, commerceOrderId, -1, false, null, null,
			Collections.emptyList(), false);
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
			CommercePaymentRequest commercePaymentRequest)
		throws Exception {

		long commerceOrderId = commercePaymentRequest.getCommerceOrderId();

		CommerceOrder commerceOrder = _commerceOrderService.getCommerceOrder(
			commerceOrderId);

		long CommercePaymentMethodGroupRelId =
			commerceOrder.getCommercePaymentMethodGroupRelId();

		CommercePaymentMethodGroupRel CommercePaymentMethodGroupRel =
			_CommercePaymentMethodGroupRelService.getCommercePaymentMethodGroupRel(
				CommercePaymentMethodGroupRelId);

		if (CommercePaymentMethodGroupRel.isActive()) {
			CommercePaymentEngineMethod commercePaymentEngineMethod =
				_commercePaymentEngineMethodRegistry.getCommercePaymentMethodGroupRel(
					CommercePaymentMethodGroupRel.getEngineKey());

			if (commercePaymentEngineMethod.isCaptureEnabled()) {
				return commercePaymentEngineMethod.capturePayment(
					commercePaymentRequest);
			}
		}

		return new CommercePaymentResult(
			null, commerceOrderId, -1, false, null, null,
			Collections.emptyList(), false);
	}

	@Override
	public CommercePaymentResult completePayment(
			CommercePaymentRequest commercePaymentRequest)
		throws Exception {

		long commerceOrderId = commercePaymentRequest.getCommerceOrderId();

		CommerceOrder commerceOrder = _commerceOrderService.getCommerceOrder(
			commerceOrderId);

		long CommercePaymentMethodGroupRelId =
			commerceOrder.getCommercePaymentMethodGroupRelId();

		CommercePaymentMethodGroupRel CommercePaymentMethodGroupRel =
			_CommercePaymentMethodGroupRelService.getCommercePaymentMethodGroupRel(
				CommercePaymentMethodGroupRelId);

		if (CommercePaymentMethodGroupRel.isActive()) {
			CommercePaymentEngineMethod commercePaymentEngineMethod =
				_commercePaymentEngineMethodRegistry.getCommercePaymentMethodGroupRel(
					CommercePaymentMethodGroupRel.getEngineKey());

			if (commercePaymentEngineMethod.isCompleteEnabled()) {
				CommercePaymentResult completePaymentResult =
					commercePaymentEngineMethod.completePayment(
						commercePaymentRequest);

				int paymentStatus = completePaymentResult.getNewPaymentStatus();

				int status = completePaymentResult.getNewPaymentStatus();

				try {
					_commerceOrderService.updatePaymentStatusAndTransactionId(
						commerceOrderId, paymentStatus,
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

				CommercePaymentResult commercePaymentResult =
					new CommercePaymentResult(
						completePaymentResult.getAuthTransactionId(),
						commerceOrderId, paymentStatus,
						completePaymentResult.isOnlineRedirect(),
						completePaymentResult.getRedirectUrl(),
						completePaymentResult.getRefundId(),
						completePaymentResult.getResultMessages(), success);

				return commercePaymentResult;
			}
		}

		return new CommercePaymentResult(
			null, commerceOrderId, -1, false, null, null,
			Collections.emptyList(), false);
	}

	@Override
	public List<CommercePaymentEngineMethod> getCommercePaymentEngineMethods(
			long groupId, long commerceCountryId)
		throws PortalException {

		List<CommercePaymentMethodGroupRel> CommercePaymentMethodGroupRels =
			_CommercePaymentMethodGroupRelService.getCommercePaymentMethodGroupRels(
				groupId, commerceCountryId, true);

		List<CommercePaymentEngineMethod> commercePaymentEngineMethods =
			new ArrayList<>();

		for(CommercePaymentMethodGroupRel CommercePaymentMethodGroupRel :
				CommercePaymentMethodGroupRels){

			CommercePaymentEngineMethod commercePaymentEngineMethod =
				_commercePaymentEngineMethodRegistry.getCommercePaymentMethodGroupRel(
					CommercePaymentMethodGroupRel.getEngineKey());

			commercePaymentEngineMethods.add(commercePaymentEngineMethod);
		}

		return commercePaymentEngineMethods;
	}

	@Override
	public int getCommercePaymentMethodGroupRelType(
			CommercePaymentRequest commercePaymentRequest)
		throws PortalException {

		long commerceOrderId = commercePaymentRequest.getCommerceOrderId();

		CommerceOrder commerceOrder = _commerceOrderService.getCommerceOrder(
			commerceOrderId);

		long CommercePaymentMethodGroupRelId =
			commerceOrder.getCommercePaymentMethodGroupRelId();

		CommercePaymentMethodGroupRel CommercePaymentMethodGroupRel =
			_CommercePaymentMethodGroupRelService.getCommercePaymentMethodGroupRel(
				CommercePaymentMethodGroupRelId);

		if (CommercePaymentMethodGroupRel.isActive()) {
			CommercePaymentEngineMethod commercePaymentEngineMethod =
				_commercePaymentEngineMethodRegistry.getCommercePaymentMethodGroupRel(
					CommercePaymentMethodGroupRel.getEngineKey());

			return commercePaymentEngineMethod.getPaymentType();
		}

		return -1;
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
			CommercePaymentRequest commercePaymentRequest)
		throws Exception {

		long commerceOrderId = commercePaymentRequest.getCommerceOrderId();

		CommerceOrder commerceOrder = _commerceOrderService.getCommerceOrder(
			commerceOrderId);

		long CommercePaymentMethodGroupRelId =
			commerceOrder.getCommercePaymentMethodGroupRelId();

		CommercePaymentMethodGroupRel CommercePaymentMethodGroupRel =
			_CommercePaymentMethodGroupRelService.getCommercePaymentMethodGroupRel(
				CommercePaymentMethodGroupRelId);

		if (CommercePaymentMethodGroupRel.isActive()) {
			CommercePaymentEngineMethod commercePaymentEngineMethod =
				_commercePaymentEngineMethodRegistry.getCommercePaymentMethodGroupRel(
					CommercePaymentMethodGroupRel.getEngineKey());

			if (commercePaymentEngineMethod.isProcessPaymentEnabled()) {
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
			CommercePaymentRequest commercePaymentRequest)
		throws Exception {

		long commerceOrderId = commercePaymentRequest.getCommerceOrderId();

		CommerceOrder commerceOrder = _commerceOrderService.getCommerceOrder(
			commerceOrderId);

		long CommercePaymentMethodGroupRelId =
			commerceOrder.getCommercePaymentMethodGroupRelId();

		CommercePaymentMethodGroupRel CommercePaymentMethodGroupRel =
			_CommercePaymentMethodGroupRelService.getCommercePaymentMethodGroupRel(
				CommercePaymentMethodGroupRelId);

		if (CommercePaymentMethodGroupRel.isActive()) {
			CommercePaymentEngineMethod commercePaymentEngineMethod =
				_commercePaymentEngineMethodRegistry.getCommercePaymentMethodGroupRel(
					CommercePaymentMethodGroupRel.getEngineKey());

			if (commercePaymentEngineMethod.isRefundEnabled()) {
				return commercePaymentEngineMethod.refundPayment(
					commercePaymentRequest);
			}
		}

		return new CommercePaymentResult(
			null, commerceOrderId, -1, false, null, null,
			Collections.emptyList(), false);
	}

	@Override
	public CommercePaymentResult voidTransaction(
			CommercePaymentRequest commercePaymentRequest)
		throws Exception {

		long commerceOrderId = commercePaymentRequest.getCommerceOrderId();

		CommerceOrder commerceOrder = _commerceOrderService.getCommerceOrder(
			commerceOrderId);

		long CommercePaymentMethodGroupRelId =
			commerceOrder.getCommercePaymentMethodGroupRelId();

		CommercePaymentMethodGroupRel CommercePaymentMethodGroupRel =
			_CommercePaymentMethodGroupRelService.getCommercePaymentMethodGroupRel(
				CommercePaymentMethodGroupRelId);

		if (CommercePaymentMethodGroupRel.isActive()) {
			CommercePaymentEngineMethod commercePaymentEngineMethod =
				_commercePaymentEngineMethodRegistry.getCommercePaymentMethodGroupRel(
					CommercePaymentMethodGroupRel.getEngineKey());

			if (commercePaymentEngineMethod.isVoidEnabled()) {
				return commercePaymentEngineMethod.voidTransaction(
					commercePaymentRequest);
			}
		}

		return new CommercePaymentResult(
			null, commerceOrderId, -1, false, null, null,
			Collections.emptyList(), false);
	}

	@Reference
	private CommerceOrderPaymentLocalService _commerceOrderPaymentLocalService;

	@Reference
	private CommerceOrderService _commerceOrderService;

	@Reference
	private CommercePaymentEngineMethodRegistry
		_commercePaymentEngineMethodRegistry;

	@Reference
	private CommercePaymentMethodGroupRelService _CommercePaymentMethodGroupRelService;

}