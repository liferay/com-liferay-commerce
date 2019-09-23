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

package com.liferay.commerce.payment.internal.util;

import com.liferay.commerce.constants.CommercePaymentConstants;
import com.liferay.commerce.model.CommerceOrder;
import com.liferay.commerce.payment.method.CommercePaymentMethod;
import com.liferay.commerce.payment.method.CommercePaymentMethodRegistry;
import com.liferay.commerce.payment.model.CommercePaymentMethodGroupRel;
import com.liferay.commerce.payment.request.CommercePaymentRequest;
import com.liferay.commerce.payment.request.CommercePaymentRequestProvider;
import com.liferay.commerce.payment.request.CommercePaymentRequestProviderRegistry;
import com.liferay.commerce.payment.result.CommercePaymentResult;
import com.liferay.commerce.payment.service.CommercePaymentMethodGroupRelLocalService;
import com.liferay.commerce.payment.util.CommercePaymentUtils;
import com.liferay.commerce.product.model.CommerceChannel;
import com.liferay.commerce.product.service.CommerceChannelLocalService;
import com.liferay.commerce.service.CommerceOrderLocalService;
import com.liferay.petra.string.CharPool;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.util.Portal;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.URLCodec;
import com.liferay.portal.kernel.util.Validator;

import java.util.Collections;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Luca Pellizzon
 */
@Component(immediate = true, service = CommercePaymentUtils.class)
public class CommercePaymentUtilsImpl implements CommercePaymentUtils {

	@Override
	public CommercePaymentResult emptyResult(long commerceOrderId) {
		return new CommercePaymentResult(
			null, commerceOrderId, -1, false, null, null,
			Collections.emptyList(), false);
	}

	@Override
	public CommercePaymentMethod getCommercePaymentMethod(long commerceOrderId)
		throws PortalException {

		CommerceOrder commerceOrder =
			_commerceOrderLocalService.getCommerceOrder(commerceOrderId);

		String commercePaymentMethodKey =
			commerceOrder.getCommercePaymentMethodKey();

		if (commercePaymentMethodKey.isEmpty()) {
			return null;
		}

		CommerceChannel commerceChannel =
			_commerceChannelLocalService.getCommerceChannelByOrderGroupId(
				commerceOrder.getGroupId());

		CommercePaymentMethodGroupRel commercePaymentMethodGroupRel =
			_commercePaymentMethodGroupRelLocalService.
				getCommercePaymentMethodGroupRel(
					commerceChannel.getSiteGroupId(), commercePaymentMethodKey);

		if ((commercePaymentMethodGroupRel != null) &&
			commercePaymentMethodGroupRel.isActive()) {

			return _commercePaymentMethodRegistry.getCommercePaymentMethod(
				commercePaymentMethodGroupRel.getEngineKey());
		}

		return null;
	}

	@Override
	public CommercePaymentRequest getCommercePaymentRequest(
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
			getCommercePaymentRequestProvider(commercePaymentMethod);

		return commercePaymentRequestProvider.getCommercePaymentRequest(
			cancelUrl, commerceOrder.getCommerceOrderId(), httpServletRequest,
			locale, returnUrl, transactionId);
	}

	@Override
	public CommercePaymentRequestProvider getCommercePaymentRequestProvider(
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

	private String _getReturnUrl(
		HttpServletRequest httpServletRequest, CommerceOrder commerceOrder,
		String redirect, CommercePaymentMethod commercePaymentMethod) {

		StringBundler sb = _getBaseUrl(
			httpServletRequest, commerceOrder, redirect, commercePaymentMethod,
			0);

		return sb.toString();
	}

	@Reference
	private CommerceChannelLocalService _commerceChannelLocalService;

	@Reference
	private CommerceOrderLocalService _commerceOrderLocalService;

	@Reference
	private CommercePaymentMethodGroupRelLocalService
		_commercePaymentMethodGroupRelLocalService;

	@Reference
	private CommercePaymentMethodRegistry _commercePaymentMethodRegistry;

	@Reference
	private CommercePaymentRequestProviderRegistry
		_commercePaymentRequestProviderRegistry;

	@Reference
	private Portal _portal;

}