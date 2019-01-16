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

package com.liferay.commerce.internal.context;

import com.liferay.commerce.account.service.CommerceAccountService;
import com.liferay.commerce.account.util.CommerceAccountHelper;
import com.liferay.commerce.context.CommerceContext;
import com.liferay.commerce.context.CommerceContextFactory;
import com.liferay.commerce.currency.service.CommerceCurrencyLocalService;
import com.liferay.commerce.discount.CommerceDiscountCouponCodeHelper;
import com.liferay.commerce.order.CommerceOrderHttpHelper;
import com.liferay.commerce.price.list.service.CommercePriceListLocalService;
import com.liferay.commerce.product.service.CPRuleLocalService;
import com.liferay.commerce.service.CommerceOrderService;
import com.liferay.commerce.user.segment.util.CommerceUserSegmentHelper;
import com.liferay.portal.kernel.module.configuration.ConfigurationProvider;
import com.liferay.portal.kernel.util.Portal;

import javax.servlet.http.HttpServletRequest;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Marco Leo
 */
@Component(service = CommerceContextFactory.class)
public class CommerceContextFactoryImpl implements CommerceContextFactory {

	@Override
	public CommerceContext create(HttpServletRequest httpServletRequest) {
		return new CommerceContextHttpImpl(
			httpServletRequest, _commerceAccountHelper,
			_commerceCurrencyLocalService, _commerceDiscountCouponCodeHelper,
			_commerceOrderHttpHelper, _commercePriceListLocalService,
			_commerceUserSegmentHelper, _configurationProvider,
			_cpRuleLocalService, _portal);
	}

	@Override
	public CommerceContext create(
		long groupId, long userId, long orderId, long commerceAccountId,
		String couponCode) {

		return new CommerceContextImpl(
			groupId, userId, orderId, commerceAccountId, couponCode,
			_commerceAccountService, _commerceCurrencyLocalService,
			_commerceOrderService, _commercePriceListLocalService,
			_commerceUserSegmentHelper, _configurationProvider,
			_cpRuleLocalService);
	}

	@Reference
	private CommerceAccountHelper _commerceAccountHelper;

	@Reference
	private CommerceAccountService _commerceAccountService;

	@Reference
	private CommerceCurrencyLocalService _commerceCurrencyLocalService;

	@Reference
	private CommerceDiscountCouponCodeHelper _commerceDiscountCouponCodeHelper;

	@Reference
	private CommerceOrderHttpHelper _commerceOrderHttpHelper;

	@Reference
	private CommerceOrderService _commerceOrderService;

	@Reference
	private CommercePriceListLocalService _commercePriceListLocalService;

	@Reference
	private CommerceUserSegmentHelper _commerceUserSegmentHelper;

	@Reference
	private ConfigurationProvider _configurationProvider;

	@Reference
	private CPRuleLocalService _cpRuleLocalService;

	@Reference
	private Portal _portal;

}