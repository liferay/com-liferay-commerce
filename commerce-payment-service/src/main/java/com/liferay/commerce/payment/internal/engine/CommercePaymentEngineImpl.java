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

package com.liferay.commerce.payment.internal.engine;

import com.liferay.commerce.constants.CommerceOrderConstants;
import com.liferay.commerce.model.CommerceAddress;
import com.liferay.commerce.model.CommerceOrder;
import com.liferay.commerce.payment.engine.CommercePaymentEngine;
import com.liferay.commerce.payment.engine.CommerceSubscriptionEngine;
import com.liferay.commerce.payment.method.CommercePaymentMethod;
import com.liferay.commerce.payment.method.CommercePaymentMethodRegistry;
import com.liferay.commerce.payment.model.CommercePaymentMethodGroupRel;
import com.liferay.commerce.payment.request.CommercePaymentRequest;
import com.liferay.commerce.payment.request.CommercePaymentRequestProvider;
import com.liferay.commerce.payment.result.CommercePaymentResult;
import com.liferay.commerce.payment.result.CommerceSubscriptionStatusResult;
import com.liferay.commerce.payment.service.CommercePaymentMethodGroupRelLocalService;
import com.liferay.commerce.payment.util.CommercePaymentUtils;
import com.liferay.commerce.payment.util.comparator.CommercePaymentMethodPriorityComparator;
import com.liferay.commerce.service.CommerceOrderLocalService;
import com.liferay.commerce.service.CommerceOrderPaymentLocalService;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.language.LanguageUtil;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.transaction.Propagation;
import com.liferay.portal.kernel.transaction.Transactional;
import com.liferay.portal.kernel.util.ListUtil;
import com.liferay.portal.kernel.util.Portal;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.Validator;

import java.util.Collections;
import java.util.LinkedList;
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

		CommercePaymentMethod commercePaymentMethod =
			_commercePaymentUtils.getCommercePaymentMethod(commerceOrderId);

		if ((commercePaymentMethod == null) ||
			!commercePaymentMethod.isCancelEnabled()) {

			return _commercePaymentUtils.emptyResult(commerceOrderId);
		}

		CommercePaymentRequest commercePaymentRequest =
			_commercePaymentUtils.getCommercePaymentRequest(
				_commerceOrderLocalService.getCommerceOrder(commerceOrderId),
				_portal.getLocale(httpServletRequest), transactionId, null,
				httpServletRequest, commercePaymentMethod);

		CommercePaymentResult commercePaymentResult =
			commercePaymentMethod.cancelPayment(commercePaymentRequest);

		updateOrderPaymentStatus(
			commerceOrderId, commercePaymentResult.getNewPaymentStatus(),
			commercePaymentResult.getAuthTransactionId());

		return commercePaymentResult;
	}

	/**
	 * @param commerceOrderId
	 * @return
	 * @deprecated As of Mueller (7.2.x), this method is being moved to Subscription Engine
	 */
	@Deprecated
	@Override
	public CommercePaymentResult cancelRecurringPayment(long commerceOrderId) {
		try {
			CommerceOrder commerceOrder =
				_commerceOrderLocalService.getCommerceOrder(commerceOrderId);

			boolean cancelRecurringPayment =
				_commerceSubscriptionEngine.cancelRecurringPayment(
					commerceOrderId);

			return new CommercePaymentResult(
				commerceOrder.getTransactionId(), commerceOrderId,
				CommerceOrderConstants.ORDER_STATUS_CANCELLED, false, null,
				null, Collections.emptyList(), cancelRecurringPayment);
		}
		catch (Exception e) {
			_log.error(e, e);
		}

		return _commercePaymentUtils.emptyResult(commerceOrderId);
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

		CommercePaymentMethod commercePaymentMethod =
			_commercePaymentUtils.getCommercePaymentMethod(commerceOrderId);

		if ((commercePaymentMethod == null) ||
			!commercePaymentMethod.isCaptureEnabled()) {

			return _commercePaymentUtils.emptyResult(commerceOrderId);
		}

		CommercePaymentRequest commercePaymentRequest =
			_commercePaymentUtils.getCommercePaymentRequest(
				_commerceOrderLocalService.getCommerceOrder(commerceOrderId),
				_portal.getLocale(httpServletRequest), transactionId, null,
				httpServletRequest, commercePaymentMethod);

		CommercePaymentResult commercePaymentResult =
			commercePaymentMethod.capturePayment(commercePaymentRequest);

		updateOrderPaymentStatus(
			commerceOrderId, commercePaymentResult.getNewPaymentStatus(),
			commercePaymentResult.getAuthTransactionId());

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

		CommercePaymentMethod commercePaymentMethod =
			_commercePaymentUtils.getCommercePaymentMethod(commerceOrderId);

		if ((commercePaymentMethod == null) ||
			!commercePaymentMethod.isCompleteEnabled()) {

			_completeOrderWithoutPaymentMethod(commerceOrderId);

			return _commercePaymentUtils.emptyResult(commerceOrderId);
		}

		CommercePaymentRequest commercePaymentRequest =
			_commercePaymentUtils.getCommercePaymentRequest(
				_commerceOrderLocalService.getCommerceOrder(commerceOrderId),
				_portal.getLocale(httpServletRequest), transactionId, null,
				httpServletRequest, commercePaymentMethod);

		CommercePaymentResult commercePaymentResult =
			commercePaymentMethod.completePayment(commercePaymentRequest);

		updateOrderPaymentStatus(
			commerceOrderId, commercePaymentResult.getNewPaymentStatus(),
			commercePaymentResult.getAuthTransactionId());

		return commercePaymentResult;
	}

	/**
	 * @param commerceOrderId
	 * @param transactionId
	 * @param httpServletRequest
	 * @return
	 * @throws Exception
	 * @deprecated As of Mueller (7.2.x), this method is being moved to Subscription Engine
	 */
	@Deprecated
	@Override
	public CommercePaymentResult completeRecurringPayment(
			long commerceOrderId, String transactionId,
			HttpServletRequest httpServletRequest)
		throws Exception {

		return _commerceSubscriptionEngine.completeRecurringPayment(
			commerceOrderId, transactionId, httpServletRequest);
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

	/**
	 * @param commerceOrderId
	 * @return
	 * @throws PortalException
	 * @deprecated As of Mueller (7.2.x), this method is being replaced
	 */
	@Deprecated
	@Override
	public List<CommercePaymentMethod> getEnabledCommercePaymentMethodsForOrder(
			long commerceOrderId)
		throws PortalException {

		CommerceOrder commerceOrder =
			_commerceOrderLocalService.getCommerceOrder(commerceOrderId);

		boolean subscriptionOrder = commerceOrder.isSubscriptionOrder();

		CommerceAddress commerceAddress = commerceOrder.getBillingAddress();

		if (commerceAddress == null) {
			commerceAddress = commerceOrder.getShippingAddress();
		}

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
	public List<CommercePaymentMethod> getEnabledCommercePaymentMethodsForOrder(
			long groupId, long commerceOrderId)
		throws PortalException {

		CommerceOrder commerceOrder =
			_commerceOrderLocalService.getCommerceOrder(commerceOrderId);

		boolean subscriptionOrder = commerceOrder.isSubscriptionOrder();

		CommerceAddress commerceAddress = commerceOrder.getBillingAddress();

		if (commerceAddress == null) {
			commerceAddress = commerceOrder.getShippingAddress();
		}

		if (commerceAddress != null) {
			return _getCommercePaymentMethodsList(
				_commercePaymentMethodGroupRelLocalService.
					getCommercePaymentMethodGroupRels(
						groupId, commerceAddress.getCommerceCountryId(), true),
				subscriptionOrder);
		}

		return _getCommercePaymentMethodsList(
			_commercePaymentMethodGroupRelLocalService.
				getCommercePaymentMethodGroupRels(groupId, true),
			subscriptionOrder);
	}

	/**
	 * @param commerceOrderId
	 * @return
	 * @throws PortalException
	 * @deprecated As of Mueller (7.2.x), this method will be removed
	 */
	@Deprecated
	@Override
	public int getOrderStatusUpdateMaxIntervalMinutes(long commerceOrderId)
		throws PortalException {

		return 0;
	}

	@Override
	public String getPaymentMethodImageURL(
			ThemeDisplay themeDisplay, String paymentMethodKey)
		throws PortalException {

		CommercePaymentMethodGroupRel commercePaymentMethodGroupRel =
			_commercePaymentMethodGroupRelLocalService.
				getCommercePaymentMethodGroupRel(
					themeDisplay.getSiteGroupId(), paymentMethodKey);

		return commercePaymentMethodGroupRel.getImageURL(themeDisplay);
	}

	@Override
	public String getPaymentMethodName(String paymentMethodKey, Locale locale) {
		CommercePaymentMethod commercePaymentMethod =
			_commercePaymentMethodRegistry.getCommercePaymentMethod(
				paymentMethodKey);

		return commercePaymentMethod.getName(locale);
	}

	/**
	 * @param commerceOrderId
	 * @return
	 * @throws Exception
	 * @deprecated As of Mueller (7.2.x), this method will be removed
	 */
	@Deprecated
	@Override
	public CommerceSubscriptionStatusResult getSubscriptionPaymentDetails(
			long commerceOrderId)
		throws Exception {

		CommercePaymentMethod commercePaymentMethod =
			_commercePaymentUtils.getCommercePaymentMethod(commerceOrderId);

		if ((commercePaymentMethod == null) ||
			!commercePaymentMethod.isProcessRecurringEnabled()) {

			return null;
		}

		CommercePaymentRequestProvider commercePaymentRequestProvider =
			_commercePaymentUtils.getCommercePaymentRequestProvider(
				commercePaymentMethod);

		CommerceOrder commerceOrder =
			_commerceOrderLocalService.getCommerceOrder(commerceOrderId);

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
		return _commercePaymentUtils.emptyResult(commerceOrderId);
	}

	@Override
	@Transactional(
		propagation = Propagation.REQUIRED, readOnly = false,
		rollbackFor = Exception.class
	)
	public CommercePaymentResult postProcessPayment(long commerceOrderId)
		throws Exception {

		return _commercePaymentUtils.emptyResult(commerceOrderId);
	}

	@Override
	@Transactional(
		propagation = Propagation.REQUIRED, readOnly = false,
		rollbackFor = Exception.class
	)
	public CommercePaymentResult processPayment(
			long commerceOrderId, String nextUrl,
			HttpServletRequest httpServletRequest)
		throws Exception {

		CommercePaymentMethod commercePaymentMethod =
			_commercePaymentUtils.getCommercePaymentMethod(commerceOrderId);

		if ((commercePaymentMethod == null) ||
			!commercePaymentMethod.isProcessPaymentEnabled()) {

			_completeOrderWithoutPaymentMethod(commerceOrderId);

			return _commercePaymentUtils.emptyResult(commerceOrderId);
		}

		CommercePaymentRequest commercePaymentRequest =
			_commercePaymentUtils.getCommercePaymentRequest(
				_commerceOrderLocalService.getCommerceOrder(commerceOrderId),
				_portal.getLocale(httpServletRequest), null, nextUrl,
				httpServletRequest, commercePaymentMethod);

		CommercePaymentResult commercePaymentResult =
			commercePaymentMethod.processPayment(commercePaymentRequest);

		updateOrderPaymentStatus(
			commerceOrderId, commercePaymentResult.getNewPaymentStatus(),
			commercePaymentResult.getAuthTransactionId());

		return commercePaymentResult;
	}

	/**
	 * @param commerceOrderId
	 * @param nextUrl
	 * @param httpServletRequest
	 * @return
	 * @throws Exception
	 * @deprecated As of Mueller (7.2.x), this method is being moved to Subscription Engine
	 */
	@Deprecated
	@Override
	public CommercePaymentResult processRecurringPayment(
			long commerceOrderId, String nextUrl,
			HttpServletRequest httpServletRequest)
		throws Exception {

		return _commerceSubscriptionEngine.processRecurringPayment(
			commerceOrderId, nextUrl, httpServletRequest);
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

		CommercePaymentMethod commercePaymentMethod =
			_commercePaymentUtils.getCommercePaymentMethod(commerceOrderId);

		if ((commercePaymentMethod == null) ||
			!commercePaymentMethod.isRefundEnabled()) {

			return _commercePaymentUtils.emptyResult(commerceOrderId);
		}

		CommercePaymentRequest commercePaymentRequest =
			_commercePaymentUtils.getCommercePaymentRequest(
				_commerceOrderLocalService.getCommerceOrder(commerceOrderId),
				_portal.getLocale(httpServletRequest), transactionId, null,
				httpServletRequest, commercePaymentMethod);

		return commercePaymentMethod.refundPayment(commercePaymentRequest);
	}

	@Override
	public CommercePaymentResult startPayment(
			long commerceOrderId, String nextUrl,
			HttpServletRequest httpServletRequest)
		throws Exception {

		CommerceOrder commerceOrder =
			_commerceOrderLocalService.getCommerceOrder(commerceOrderId);

		if (commerceOrder.isSubscriptionOrder()) {
			return processRecurringPayment(
				commerceOrderId, nextUrl, httpServletRequest);
		}

		return processPayment(commerceOrderId, nextUrl, httpServletRequest);
	}

	/**
	 * @param commerceSubscriptionEntryId
	 * @return
	 * @throws Exception
	 * @deprecated As of Mueller (7.2.x), this method is being moved to Subscription Engine
	 */
	@Deprecated
	@Override
	public boolean suspendSubscription(long commerceSubscriptionEntryId)
		throws Exception {

		return _commerceSubscriptionEngine.suspendRecurringPayment(
			commerceSubscriptionEntryId);
	}

	@Override
	public CommerceOrder updateOrderPaymentStatus(
			long commerceOrderId, int paymentStatus, String transactionId)
		throws PortalException {

		CommerceOrder commerceOrder =
			_commerceOrderLocalService.getCommerceOrder(commerceOrderId);

		commerceOrder =
			_commerceOrderLocalService.updatePaymentStatusAndTransactionId(
				commerceOrder.getUserId(), commerceOrderId, paymentStatus,
				transactionId);

		_commerceOrderPaymentLocalService.addCommerceOrderPayment(
			commerceOrderId, paymentStatus, StringPool.BLANK);

		return commerceOrder;
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

		CommercePaymentMethod commercePaymentMethod =
			_commercePaymentUtils.getCommercePaymentMethod(commerceOrderId);

		if ((commercePaymentMethod == null) ||
			!commercePaymentMethod.isVoidEnabled()) {

			return _commercePaymentUtils.emptyResult(commerceOrderId);
		}

		CommercePaymentRequest commercePaymentRequest =
			_commercePaymentUtils.getCommercePaymentRequest(
				_commerceOrderLocalService.getCommerceOrder(commerceOrderId),
				_portal.getLocale(httpServletRequest), null, null,
				httpServletRequest, commercePaymentMethod);

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

	private List<CommercePaymentMethod> _getCommercePaymentMethodsList(
		List<CommercePaymentMethodGroupRel> commercePaymentMethodGroupRels,
		boolean subscriptionOrder) {

		ListUtil.sort(
			commercePaymentMethodGroupRels,
			new CommercePaymentMethodPriorityComparator());

		List<CommercePaymentMethod> commercePaymentMethods = new LinkedList<>();

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

	private static final Log _log = LogFactoryUtil.getLog(
		CommercePaymentEngineImpl.class);

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
	private CommercePaymentUtils _commercePaymentUtils;

	@Reference
	private CommerceSubscriptionEngine _commerceSubscriptionEngine;

	@Reference
	private Portal _portal;

}