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

import com.liferay.commerce.account.configuration.CommerceAccountGroupServiceConfiguration;
import com.liferay.commerce.account.constants.CommerceAccountConstants;
import com.liferay.commerce.account.model.CommerceAccount;
import com.liferay.commerce.account.util.CommerceAccountHelper;
import com.liferay.commerce.context.CommerceContext;
import com.liferay.commerce.currency.model.CommerceCurrency;
import com.liferay.commerce.currency.service.CommerceCurrencyLocalService;
import com.liferay.commerce.discount.CommerceDiscountCouponCodeHelper;
import com.liferay.commerce.model.CommerceOrder;
import com.liferay.commerce.order.CommerceOrderHttpHelper;
import com.liferay.commerce.price.list.model.CommercePriceList;
import com.liferay.commerce.price.list.service.CommercePriceListLocalService;
import com.liferay.commerce.product.model.CPRule;
import com.liferay.commerce.product.service.CPRuleLocalService;
import com.liferay.commerce.user.segment.util.CommerceUserSegmentHelper;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.module.configuration.ConfigurationProvider;
import com.liferay.portal.kernel.settings.GroupServiceSettingsLocator;
import com.liferay.portal.kernel.util.Portal;

import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Marco Leo
 */
public class CommerceContextHttpImpl implements CommerceContext {

	public CommerceContextHttpImpl(
		HttpServletRequest httpServletRequest,
		CommerceAccountHelper commerceAccountHelper,
		CommerceCurrencyLocalService commerceCurrencyLocalService,
		CommerceDiscountCouponCodeHelper commerceDiscountCouponCodeHelper,
		CommerceOrderHttpHelper commerceOrderHttpHelper,
		CommercePriceListLocalService commercePriceListLocalService,
		CommerceUserSegmentHelper commerceUserSegmentHelper,
		ConfigurationProvider configurationProvider,
		CPRuleLocalService cpRuleLocalService, Portal portal) {

		_httpServletRequest = httpServletRequest;
		_commerceAccountHelper = commerceAccountHelper;
		_commerceCurrencyLocalService = commerceCurrencyLocalService;
		_commerceDiscountCouponCodeHelper = commerceDiscountCouponCodeHelper;
		_commerceOrderHttpHelper = commerceOrderHttpHelper;
		_commercePriceListLocalService = commercePriceListLocalService;
		_commerceUserSegmentHelper = commerceUserSegmentHelper;
		_cpRuleLocalService = cpRuleLocalService;
		_portal = portal;

		try {
			_commerceAccountGroupServiceConfiguration =
				configurationProvider.getConfiguration(
					CommerceAccountGroupServiceConfiguration.class,
					new GroupServiceSettingsLocator(
						_portal.getScopeGroupId(httpServletRequest),
						CommerceAccountConstants.SERVICE_NAME));
		}
		catch (PortalException pe) {
			_log.error(pe, pe);
		}
	}

	@Override
	public CommerceAccount getCommerceAccount() throws PortalException {
		if (_commerceAccount != null) {
			return _commerceAccount;
		}

		_commerceAccount = _commerceAccountHelper.getCurrentCommerceAccount(
			_httpServletRequest);

		return _commerceAccount;
	}

	@Override
	public CommerceCurrency getCommerceCurrency() throws PortalException {
		if (_commerceCurrency != null) {
			return _commerceCurrency;
		}

		long groupId = _portal.getScopeGroupId(_httpServletRequest);

		_commerceCurrency =
			_commerceCurrencyLocalService.fetchPrimaryCommerceCurrency(groupId);

		return _commerceCurrency;
	}

	@Override
	public CommerceOrder getCommerceOrder() throws PortalException {
		if (_commerceOrder != null) {
			return _commerceOrder;
		}

		_commerceOrder = _commerceOrderHttpHelper.getCurrentCommerceOrder(
			_httpServletRequest);

		return _commerceOrder;
	}

	@Override
	public Optional<CommercePriceList> getCommercePriceList()
		throws PortalException {

		if (_commercePriceList != null) {
			return _commercePriceList;
		}

		long groupId = _portal.getScopeGroupId(_httpServletRequest);

		_commercePriceList =
			_commercePriceListLocalService.getCommercePriceList(
				groupId, getCommerceUserSegmentEntryIds());

		return _commercePriceList;
	}

	@Override
	public int getCommerceSiteType() {
		if (_commerceAccountGroupServiceConfiguration == null) {
			return CommerceAccountConstants.SITE_TYPE_B2C;
		}

		return _commerceAccountGroupServiceConfiguration.commerceSiteType();
	}

	@Override
	public long[] getCommerceUserSegmentEntryIds() throws PortalException {
		if (_commerceUserSegmentEntryIds != null) {
			return _commerceUserSegmentEntryIds;
		}

		_commerceUserSegmentEntryIds =
			_commerceUserSegmentHelper.getCommerceUserSegmentIds(
				_httpServletRequest);

		return _commerceUserSegmentEntryIds;
	}

	@Override
	public String getCouponCode() throws PortalException {
		return _commerceDiscountCouponCodeHelper.getCommerceDiscountCouponCode(
			_httpServletRequest);
	}

	@Override
	public List<CPRule> getCPRules() throws PortalException {
		if (_cpRules != null) {
			return _cpRules;
		}

		long groupId = _portal.getScopeGroupId(_httpServletRequest);

		_cpRules = _cpRuleLocalService.getCPRules(
			groupId, getCommerceUserSegmentEntryIds());

		return null;
	}

	@Override
	public long getSiteGroupId() throws PortalException {
		return _portal.getScopeGroupId(_httpServletRequest);
	}

	@Override
	public long getUserId() {
		return _portal.getUserId(_httpServletRequest);
	}

	private static final Log _log = LogFactoryUtil.getLog(
		CommerceContextHttpImpl.class);

	private CommerceAccount _commerceAccount;
	private CommerceAccountGroupServiceConfiguration
		_commerceAccountGroupServiceConfiguration;
	private final CommerceAccountHelper _commerceAccountHelper;
	private CommerceCurrency _commerceCurrency;
	private final CommerceCurrencyLocalService _commerceCurrencyLocalService;
	private final CommerceDiscountCouponCodeHelper
		_commerceDiscountCouponCodeHelper;
	private CommerceOrder _commerceOrder;
	private final CommerceOrderHttpHelper _commerceOrderHttpHelper;
	private Optional<CommercePriceList> _commercePriceList;
	private final CommercePriceListLocalService _commercePriceListLocalService;
	private long[] _commerceUserSegmentEntryIds;
	private final CommerceUserSegmentHelper _commerceUserSegmentHelper;
	private final CPRuleLocalService _cpRuleLocalService;
	private List<CPRule> _cpRules;
	private final HttpServletRequest _httpServletRequest;
	private final Portal _portal;

}