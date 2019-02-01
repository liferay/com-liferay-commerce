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
import com.liferay.commerce.constants.CommercePaymentConstants;
import com.liferay.commerce.constants.CommerceSubscriptionEntryConstants;
import com.liferay.commerce.model.CommerceAddress;
import com.liferay.commerce.model.CommerceOrder;
import com.liferay.commerce.model.CommerceOrderItem;
import com.liferay.commerce.model.CommerceSubscriptionEntry;
import com.liferay.commerce.payment.engine.CommercePaymentEngine;
import com.liferay.commerce.payment.method.CommercePaymentMethod;
import com.liferay.commerce.payment.method.CommercePaymentMethodRegistry;
import com.liferay.commerce.payment.model.CommercePaymentMethodGroupRel;
import com.liferay.commerce.payment.request.CommercePaymentRequest;
import com.liferay.commerce.payment.request.CommercePaymentRequestProvider;
import com.liferay.commerce.payment.request.CommercePaymentRequestProviderRegistry;
import com.liferay.commerce.payment.result.CommercePaymentResult;
import com.liferay.commerce.payment.result.CommerceSubscriptionStatusResult;
import com.liferay.commerce.payment.service.CommercePaymentMethodGroupRelLocalService;
import com.liferay.commerce.service.CommerceOrderLocalService;
import com.liferay.commerce.service.CommerceOrderPaymentLocalService;
import com.liferay.commerce.service.CommerceSubscriptionEntryLocalService;
import com.liferay.petra.string.CharPool;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.language.LanguageUtil;
import com.liferay.portal.kernel.transaction.Propagation;
import com.liferay.portal.kernel.transaction.Transactional;
import com.liferay.portal.kernel.util.Portal;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.URLCodec;
import com.liferay.portal.kernel.util.Validator;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Luca Pellizzon
 */
@Component(immediate = true, service = CommercePaymentEngine.class)
public class CommercePaymentEngineImpl implements CommercePaymentEngine {

	@Override
	@Transactional(
		propagation = Propagation.REQUIRED, readOnly = false,
		rollbackFor = Exception.class
	)
	public CommercePaymentResult cancelPayment(
			long commerceOrderId, String transactionId,
			HttpServletRequest httpServletRequest)
		throws Exception {

		CommercePaymentMethod commercePaymentMethod = _getCommercePaymentMethod(
			commerceOrderId);

		if ((commercePaymentMethod == null) ||
			!commercePaymentMethod.isCancelEnabled()) {

			return _emptyResult(commerceOrderId);
		}

		CommerceOrder commerceOrder =
			_commerceOrderLocalService.getCommerceOrder(commerceOrderId);

		CommercePaymentRequest commercePaymentRequest =
			_getCommercePaymentRequest(
				commerceOrder, _portal.getLocale(httpServletRequest),
				transactionId, null, httpServletRequest, commercePaymentMethod);

		CommercePaymentResult commercePaymentResult =
			commercePaymentMethod.cancelPayment(commercePaymentRequest);

		_commerceOrderLocalService.updatePaymentStatusAndTransactionId(
			commerceOrder.getUserId(), commerceOrderId,
			CommerceOrderConstants.ORDER_STATUS_CANCELLED, transactionId);

		_commerceOrderPaymentLocalService.addCommerceOrderPayment(
			commerceOrderId, commercePaymentResult.getNewPaymentStatus(),
			StringPool.BLANK);

		return commercePaymentResult;
	}

	@Override
	@Transactional(
		propagation = Propagation.REQUIRED, readOnly = false,
		rollbackFor = Exception.class
	)
	public CommercePaymentResult cancelRecurringPayment(long commerceOrderId) {
		return new CommercePaymentResult(
			null, commerceOrderId, -1, false, null, null,
			Collections.emptyList(), false);
	}

	@Override
	@Transactional(
		propagation = Propagation.REQUIRED, readOnly = false,
		rollbackFor = Exception.class
	)
	public CommercePaymentResult capturePayment(
			long commerceOrderId, String transactionId,
			HttpServletRequest httpServletRequest)
		throws Exception {

		CommercePaymentMethod commercePaymentMethod = _getCommercePaymentMethod(
			commerceOrderId);

		if ((commercePaymentMethod == null) ||
			!commercePaymentMethod.isCaptureEnabled()) {

			return _emptyResult(commerceOrderId);
		}

		CommerceOrder commerceOrder =
			_commerceOrderLocalService.getCommerceOrder(commerceOrderId);

		CommercePaymentRequest commercePaymentRequest =
			_getCommercePaymentRequest(
				commerceOrder, _portal.getLocale(httpServletRequest),
				transactionId, null, httpServletRequest, commercePaymentMethod);

		CommercePaymentResult commercePaymentResult =
			commercePaymentMethod.capturePayment(commercePaymentRequest);

		_commerceOrderLocalService.updatePaymentStatusAndTransactionId(
			commerceOrder.getUserId(), commerceOrderId,
			commercePaymentResult.getNewPaymentStatus(),
			commercePaymentResult.getAuthTransactionId());

		_commerceOrderPaymentLocalService.addCommerceOrderPayment(
			commerceOrderId, commercePaymentResult.getNewPaymentStatus(),
			StringPool.BLANK);

		return commercePaymentResult;
	}

	@Override
	@Transactional(
		propagation = Propagation.REQUIRED, readOnly = false,
		rollbackFor = Exception.class
	)
	public CommercePaymentResult completePayment(
			long commerceOrderId, String transactionId,
			HttpServletRequest httpServletRequest)
		throws Exception {

		CommercePaymentMethod commercePaymentMethod = _getCommercePaymentMethod(
			commerceOrderId);

		if ((commercePaymentMethod == null) ||
			!commercePaymentMethod.isCompleteEnabled()) {

			_completeOrderWithoutPaymentMethod(commerceOrderId);

			return _emptyResult(commerceOrderId);
		}

		CommerceOrder commerceOrder =
			_commerceOrderLocalService.getCommerceOrder(commerceOrderId);

		CommercePaymentRequest commercePaymentRequest =
			_getCommercePaymentRequest(
				commerceOrder, _portal.getLocale(httpServletRequest),
				transactionId, null, httpServletRequest, commercePaymentMethod);

		CommercePaymentResult commercePaymentResult =
			commercePaymentMethod.completePayment(commercePaymentRequest);

		_commerceOrderLocalService.updatePaymentStatusAndTransactionId(
			commerceOrder.getUserId(), commerceOrderId,
			commercePaymentResult.getNewPaymentStatus(),
			commercePaymentResult.getAuthTransactionId());

		_commerceOrderPaymentLocalService.addCommerceOrderPayment(
			commerceOrderId, commercePaymentResult.getNewPaymentStatus(),
			StringPool.BLANK);

		return commercePaymentResult;
	}

	@Override
	@Transactional(
		propagation = Propagation.REQUIRED, readOnly = false,
		rollbackFor = Exception.class
	)
	public CommercePaymentResult completeRecurringPayment(
			long commerceOrderId, String transactionId,
			HttpServletRequest httpServletRequest)
		throws Exception {

		CommercePaymentMethod commercePaymentMethod = _getCommercePaymentMethod(
			commerceOrderId);

		if ((commercePaymentMethod == null) ||
			!commercePaymentMethod.isCompleteRecurringEnabled()) {

			return _emptyResult(commerceOrderId);
		}

		CommerceOrder commerceOrder =
			_commerceOrderLocalService.getCommerceOrder(commerceOrderId);

		CommercePaymentRequest commercePaymentRequest =
			_getCommercePaymentRequest(
				commerceOrder, _portal.getLocale(httpServletRequest),
				transactionId, null, httpServletRequest, commercePaymentMethod);

		CommercePaymentResult commercePaymentResult =
			commercePaymentMethod.completeRecurringPayment(
				commercePaymentRequest);

		_commerceOrderLocalService.updatePaymentStatusAndTransactionId(
			commerceOrder.getUserId(), commerceOrderId,
			commercePaymentResult.getNewPaymentStatus(),
			commercePaymentResult.getAuthTransactionId());

		_commerceOrderPaymentLocalService.addCommerceOrderPayment(
			commerceOrderId, commercePaymentResult.getNewPaymentStatus(),
			StringPool.BLANK);

		return commercePaymentResult;
	}

	@Override
	public String getCommerceOrderPaymentMethodName(
			CommerceOrder commerceOrder, HttpServletRequest httpServletRequest,
			Locale locale)
		throws PortalException {

		String commercePaymentMethodKey =
			commerceOrder.getCommercePaymentMethodKey();

		if (Validator.isNull(commercePaymentMethodKey)) {
			return StringPool.BLANK;
		}

		CommercePaymentMethodGroupRel commercePaymentMethod =
			_commercePaymentMethodGroupRelLocalService.
				getCommercePaymentMethodGroupRel(
					commerceOrder.getGroupId(), commercePaymentMethodKey);

		if (commercePaymentMethod == null) {
			return StringPool.BLANK;
		}

		String name = commercePaymentMethod.getName(locale);

		if (!commercePaymentMethod.isActive()) {
			name = StringBundler.concat(
				name, " (", LanguageUtil.get(httpServletRequest, "inactive"),
				StringPool.CLOSE_PARENTHESIS);
		}

		return name;
	}

	@Override
	public int getCommercePaymentMethodGroupRelsCount(long groupId) {
		return _commercePaymentMethodGroupRelLocalService.
			getCommercePaymentMethodGroupRelsCount(groupId, true);
	}

	@Override
	public int getCommercePaymentMethodType(long commerceOrderId)
		throws PortalException {

		CommerceOrder commerceOrder =
			_commerceOrderLocalService.getCommerceOrder(commerceOrderId);

		String commercePaymentMethodKey =
			commerceOrder.getCommercePaymentMethodKey();

		if (commercePaymentMethodKey.isEmpty()) {
			return -1;
		}

		CommercePaymentMethod commercePaymentMethod =
			_commercePaymentMethodRegistry.getCommercePaymentMethod(
				commercePaymentMethodKey);

		return commercePaymentMethod.getPaymentType();
	}

	@Override
	public List<CommercePaymentMethod> getEnabledCommercePaymentMethodsForOrder(
			long commerceOrderId)
		throws PortalException {

		CommerceOrder commerceOrder =
			_commerceOrderLocalService.getCommerceOrder(commerceOrderId);

		boolean subscriptionOrder = commerceOrder.isSubscriptionOrder();

		CommerceAddress commerceAddress = commerceOrder.getBillingAddress();

		if (commerceAddress != null) {
			return _getCommercePaymentMethodsList(
				_commercePaymentMethodGroupRelLocalService.
					getCommercePaymentMethodGroupRels(
						commerceOrder.getGroupId(),
						commerceAddress.getCommerceCountryId(), true),
				subscriptionOrder);
		}

		return _getCommercePaymentMethodsList(
			_commercePaymentMethodGroupRelLocalService.
				getCommercePaymentMethodGroupRels(
					commerceOrder.getGroupId(), true),
			subscriptionOrder);
	}

	@Override
	public String getPaymentMethodName(String paymentMethodKey, Locale locale) {
		CommercePaymentMethod commercePaymentMethod =
			_commercePaymentMethodRegistry.getCommercePaymentMethod(
				paymentMethodKey);

		return commercePaymentMethod.getName(locale);
	}

	@Override
	public CommerceSubscriptionStatusResult getSubscriptionPaymentDetails(
			long commerceOrderId)
		throws Exception {

		CommercePaymentMethod commercePaymentMethod = _getCommercePaymentMethod(
			commerceOrderId);

		if ((commercePaymentMethod == null) ||
			!commercePaymentMethod.isProcessRecurringEnabled()) {

			return null;
		}

		CommerceOrder commerceOrder =
			_commerceOrderLocalService.getCommerceOrder(commerceOrderId);

		CommercePaymentRequestProvider commercePaymentRequestProvider =
			_getCommercePaymentRequestProvider(commercePaymentMethod);

		CommercePaymentRequest commercePaymentRequest =
			commercePaymentRequestProvider.getCommercePaymentRequest(
				null, commerceOrderId, null, null, null,
				commerceOrder.getTransactionId());

		return commercePaymentMethod.getSubscriptionPaymentDetails(
			commercePaymentRequest);
	}

	@Override
	@Transactional(
		propagation = Propagation.REQUIRED, readOnly = false,
		rollbackFor = Exception.class
	)
	public CommercePaymentResult partiallyRefundPayment(long commerceOrderId) {
		return _emptyResult(commerceOrderId);
	}

	@Override
	public int payedOrderInterval(long commerceOrderId) throws PortalException {
		CommercePaymentMethod commercePaymentMethod = _getCommercePaymentMethod(
			commerceOrderId);

		return commercePaymentMethod.payedOrderInterval();
	}

	@Override
	@Transactional(
		propagation = Propagation.REQUIRED, readOnly = false,
		rollbackFor = Exception.class
	)
	public CommercePaymentResult postProcessPayment(long commerceOrderId)
		throws Exception {

		return _emptyResult(commerceOrderId);
	}

	@Override
	@Transactional(
		propagation = Propagation.REQUIRED, readOnly = false,
		rollbackFor = Exception.class
	)
	public CommercePaymentResult processPayment(
			long commerceOrderId, String checkoutStepUrl,
			HttpServletRequest httpServletRequest)
		throws Exception {

		CommercePaymentMethod commercePaymentMethod = _getCommercePaymentMethod(
			commerceOrderId);

		if ((commercePaymentMethod == null) ||
			!commercePaymentMethod.isProcessPaymentEnabled()) {

			return _emptyResult(commerceOrderId);
		}

		CommerceOrder commerceOrder =
			_commerceOrderLocalService.getCommerceOrder(commerceOrderId);

		CommercePaymentRequest commercePaymentRequest =
			_getCommercePaymentRequest(
				commerceOrder, _portal.getLocale(httpServletRequest), null,
				checkoutStepUrl, httpServletRequest, commercePaymentMethod);

		CommercePaymentResult commercePaymentResult =
			commercePaymentMethod.processPayment(commercePaymentRequest);

		_commerceOrderLocalService.updatePaymentStatusAndTransactionId(
			commerceOrder.getUserId(), commerceOrderId,
			commercePaymentResult.getNewPaymentStatus(),
			commercePaymentResult.getAuthTransactionId());

		return commercePaymentResult;
	}

	@Override
	@Transactional(
		propagation = Propagation.REQUIRED, readOnly = false,
		rollbackFor = Exception.class
	)
	public CommercePaymentResult processRecurringPayment(
			long commerceOrderId, String checkoutStepUrl,
			HttpServletRequest httpServletRequest)
		throws Exception {

		CommerceOrder commerceOrder =
			_commerceOrderLocalService.getCommerceOrder(commerceOrderId);

		CommercePaymentMethod commercePaymentMethod = _getCommercePaymentMethod(
			commerceOrderId);

		if ((commercePaymentMethod == null) ||
			!commercePaymentMethod.isProcessRecurringEnabled()) {

			return _emptyResult(commerceOrderId);
		}

		CommercePaymentRequest commercePaymentRequest =
			_getCommercePaymentRequest(
				commerceOrder, _portal.getLocale(httpServletRequest), null,
				checkoutStepUrl, httpServletRequest, commercePaymentMethod);

		CommercePaymentResult commercePaymentResult =
			commercePaymentMethod.processRecurringPayment(
				commercePaymentRequest);

		_commerceOrderLocalService.updatePaymentStatusAndTransactionId(
			commerceOrder.getUserId(), commerceOrderId,
			commercePaymentResult.getNewPaymentStatus(),
			commercePaymentResult.getAuthTransactionId());

		return commercePaymentResult;
	}

	@Override
	@Transactional(
		propagation = Propagation.REQUIRED, readOnly = false,
		rollbackFor = Exception.class
	)
	public CommercePaymentResult refundPayment(
			long commerceOrderId, String transactionId,
			HttpServletRequest httpServletRequest)
		throws Exception {

		CommercePaymentMethod commercePaymentMethod = _getCommercePaymentMethod(
			commerceOrderId);

		if ((commercePaymentMethod == null) ||
			!commercePaymentMethod.isRefundEnabled()) {

			return _emptyResult(commerceOrderId);
		}

		CommerceOrder commerceOrder =
			_commerceOrderLocalService.getCommerceOrder(commerceOrderId);

		CommercePaymentRequest commercePaymentRequest =
			_getCommercePaymentRequest(
				commerceOrder, _portal.getLocale(httpServletRequest),
				transactionId, null, httpServletRequest, commercePaymentMethod);

		return commercePaymentMethod.refundPayment(commercePaymentRequest);
	}

	@Override
	public CommercePaymentResult startPayment(
			long commerceOrderId, String checkoutStepUrl,
			HttpServletRequest httpServletRequest)
		throws Exception {

		CommerceOrder commerceOrder =
			_commerceOrderLocalService.getCommerceOrder(commerceOrderId);

		if (commerceOrder.isSubscriptionOrder()) {
			return processRecurringPayment(
				commerceOrderId, checkoutStepUrl, httpServletRequest);
		}

		return processPayment(
			commerceOrderId, checkoutStepUrl, httpServletRequest);
	}

	@Override
	@Transactional(
		propagation = Propagation.REQUIRED, readOnly = false,
		rollbackFor = Exception.class
	)
	public boolean suspendSubscription(long commerceSubscriptionEntryId)
		throws Exception {

		CommerceSubscriptionEntry commerceSubscriptionEntry =
			_commerceSubscriptionEntryLocalService.getCommerceSubscriptionEntry(
				commerceSubscriptionEntryId);

		CommerceOrderItem commerceOrderItem =
			commerceSubscriptionEntry.fetchCommerceOrderItem();

		CommerceOrder commerceOrder = commerceOrderItem.getCommerceOrder();

		CommercePaymentMethod commercePaymentMethod = _getCommercePaymentMethod(
			commerceOrder.getCommerceOrderId());

		if ((commercePaymentMethod == null) ||
			!commercePaymentMethod.isProcessRecurringEnabled()) {

			return false;
		}

		CommercePaymentRequestProvider commercePaymentRequestProvider =
			_getCommercePaymentRequestProvider(commercePaymentMethod);

		CommercePaymentRequest commercePaymentRequest =
			commercePaymentRequestProvider.getCommercePaymentRequest(
				null, commerceOrder.getCommerceOrderId(), null, null, null,
				commerceOrder.getTransactionId());

		boolean suspendSubscription = commercePaymentMethod.suspendSubscription(
			commercePaymentRequest);

		if (suspendSubscription) {
			_commerceSubscriptionEntryLocalService.updateSubscriptionStatus(
				commerceSubscriptionEntry.getCommerceSubscriptionEntryId(),
				CommerceSubscriptionEntryConstants.
					SUBSCRIPTION_STATUS_SUSPENDED);
		}

		return suspendSubscription;
	}

	@Override
	@Transactional(
		propagation = Propagation.REQUIRED, readOnly = false,
		rollbackFor = Exception.class
	)
	public CommercePaymentResult voidTransaction(
			long commerceOrderId, String transactionId,
			HttpServletRequest httpServletRequest)
		throws Exception {

		CommercePaymentMethod commercePaymentMethod = _getCommercePaymentMethod(
			commerceOrderId);

		if ((commercePaymentMethod == null) ||
			!commercePaymentMethod.isVoidEnabled()) {

			return _emptyResult(commerceOrderId);
		}

		CommerceOrder commerceOrder =
			_commerceOrderLocalService.getCommerceOrder(commerceOrderId);

		CommercePaymentRequest commercePaymentRequest =
			_getCommercePaymentRequest(
				commerceOrder, _portal.getLocale(httpServletRequest), null,
				null, httpServletRequest, commercePaymentMethod);

		return commercePaymentMethod.voidTransaction(commercePaymentRequest);
	}

	private void _completeOrderWithoutPaymentMethod(long commerceOrderId)
		throws PortalException {

		CommerceOrder commerceOrder =
			_commerceOrderLocalService.getCommerceOrder(commerceOrderId);

		_commerceOrderLocalService.updatePaymentStatusAndTransactionId(
			commerceOrder.getUserId(), commerceOrderId,
			CommerceOrderConstants.PAYMENT_STATUS_PAID, StringPool.BLANK);

		_commerceOrderPaymentLocalService.addCommerceOrderPayment(
			commerceOrderId, CommerceOrderConstants.PAYMENT_STATUS_PAID,
			StringPool.BLANK);
	}

	private CommercePaymentResult _emptyResult(long commerceOrderId) {
		return new CommercePaymentResult(
			null, commerceOrderId, -1, false, null, null,
			Collections.emptyList(), false);
	}

	private StringBundler _getBaseUrl(
		HttpServletRequest httpServletRequest, CommerceOrder commerceOrder,
		String redirect, CommercePaymentMethod commercePaymentMethod,
		int extraCapacity) {

		StringBundler sb = new StringBundler(
			extraCapacity + (Validator.isNotNull(redirect) ? 13 : 11));

		sb.append(_portal.getPortalURL(httpServletRequest));
		sb.append(_portal.getPathModule());
		sb.append(CharPool.SLASH);
		sb.append(commercePaymentMethod.getServletPath());
		sb.append(CharPool.QUESTION);
		sb.append("groupId=");
		sb.append(commerceOrder.getGroupId());
		sb.append("&uuid=");
		sb.append(URLCodec.encodeURL(commerceOrder.getUuid()));

		if (Validator.isNotNull(redirect)) {
			sb.append("&redirect=");
			sb.append(URLCodec.encodeURL(redirect));
		}

		return sb;
	}

	private String _getCancelUrl(
		HttpServletRequest httpServletRequest, CommerceOrder commerceOrder,
		String redirect, CommercePaymentMethod commercePaymentMethod) {

		StringBundler sb = _getBaseUrl(
			httpServletRequest, commerceOrder, redirect, commercePaymentMethod,
			2);

		sb.append("&cancel=");
		sb.append(StringPool.TRUE);

		return sb.toString();
	}

	private CommercePaymentMethod _getCommercePaymentMethod(
			long commerceOrderId)
		throws PortalException {

		CommerceOrder commerceOrder =
			_commerceOrderLocalService.getCommerceOrder(commerceOrderId);

		String commercePaymentMethodKey =
			commerceOrder.getCommercePaymentMethodKey();

		if (commercePaymentMethodKey.isEmpty()) {
			return null;
		}

		CommercePaymentMethodGroupRel commercePaymentMethodGroupRel =
			_commercePaymentMethodGroupRelLocalService.
				getCommercePaymentMethodGroupRel(
					commerceOrder.getGroupId(), commercePaymentMethodKey);

		if ((commercePaymentMethodGroupRel != null) &&
			commercePaymentMethodGroupRel.isActive()) {

			return _commercePaymentMethodRegistry.getCommercePaymentMethod(
				commercePaymentMethodGroupRel.getEngineKey());
		}

		return null;
	}

	private List<CommercePaymentMethod> _getCommercePaymentMethodsList(
		List<CommercePaymentMethodGroupRel> commercePaymentMethodGroupRels,
		boolean subscriptionOrder) {

		List<CommercePaymentMethod> commercePaymentMethods = new ArrayList<>();

		for (CommercePaymentMethodGroupRel commercePaymentMethodGroupRel :
				commercePaymentMethodGroupRels) {

			CommercePaymentMethod commercePaymentMethod =
				_commercePaymentMethodRegistry.getCommercePaymentMethod(
					commercePaymentMethodGroupRel.getEngineKey());

			if (subscriptionOrder &&
				!commercePaymentMethod.isProcessRecurringEnabled()) {

				continue;
			}

			commercePaymentMethods.add(commercePaymentMethod);
		}

		return commercePaymentMethods;
	}

	private CommercePaymentRequest _getCommercePaymentRequest(
			CommerceOrder commerceOrder, Locale locale, String transactionId,
			String checkoutStepUrl, HttpServletRequest httpServletRequest,
			CommercePaymentMethod commercePaymentMethod)
		throws PortalException {

		String cancelUrl = null;
		String returnUrl = null;

		if (CommercePaymentConstants.
				COMMERCE_PAYMENT_METHOD_TYPE_ONLINE_REDIRECT ==
					commercePaymentMethod.getPaymentType()) {

			cancelUrl = _getCancelUrl(
				httpServletRequest, commerceOrder, checkoutStepUrl,
				commercePaymentMethod);
			returnUrl = _getReturnUrl(
				httpServletRequest, commerceOrder, checkoutStepUrl,
				commercePaymentMethod);
		}

		CommercePaymentRequestProvider commercePaymentRequestProvider =
			_getCommercePaymentRequestProvider(commercePaymentMethod);

		return commercePaymentRequestProvider.getCommercePaymentRequest(
			cancelUrl, commerceOrder.getCommerceOrderId(), httpServletRequest,
			locale, returnUrl, transactionId);
	}

	private CommercePaymentRequestProvider _getCommercePaymentRequestProvider(
		CommercePaymentMethod commercePaymentMethod) {

		CommercePaymentRequestProvider commercePaymentRequestProvider =
			_commercePaymentRequestProviderRegistry.
				getCommercePaymentRequestProvider(
					commercePaymentMethod.getKey());

		if (commercePaymentRequestProvider == null) {
			commercePaymentRequestProvider =
				_commercePaymentRequestProviderRegistry.
					getCommercePaymentRequestProvider(
						CommercePaymentConstants.
							DEFAULT_PAYMENT_REQUEST_PROVIDER_KEY);
		}

		return commercePaymentRequestProvider;
	}

	private String _getReturnUrl(
		HttpServletRequest httpServletRequest, CommerceOrder commerceOrder,
		String redirect, CommercePaymentMethod commercePaymentMethod) {

		StringBundler sb = _getBaseUrl(
			httpServletRequest, commerceOrder, redirect, commercePaymentMethod,
			0);

		return sb.toString();
	}

	@Reference
	private CommerceOrderLocalService _commerceOrderLocalService;

	@Reference
	private CommerceOrderPaymentLocalService _commerceOrderPaymentLocalService;

	@Reference
	private CommercePaymentMethodGroupRelLocalService
		_commercePaymentMethodGroupRelLocalService;

	@Reference
	private CommercePaymentMethodRegistry _commercePaymentMethodRegistry;

	@Reference
	private CommercePaymentRequestProviderRegistry
		_commercePaymentRequestProviderRegistry;

	@Reference
	private CommerceSubscriptionEntryLocalService
		_commerceSubscriptionEntryLocalService;

	@Reference
	private Portal _portal;

}