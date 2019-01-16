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
import com.liferay.commerce.account.service.CommerceAccountService;
import com.liferay.commerce.context.CommerceContext;
import com.liferay.commerce.currency.model.CommerceCurrency;
import com.liferay.commerce.currency.service.CommerceCurrencyLocalService;
import com.liferay.commerce.model.CommerceOrder;
import com.liferay.commerce.price.list.model.CommercePriceList;
import com.liferay.commerce.price.list.service.CommercePriceListLocalService;
import com.liferay.commerce.product.model.CPRule;
import com.liferay.commerce.product.service.CPRuleLocalService;
import com.liferay.commerce.service.CommerceOrderService;
import com.liferay.commerce.user.segment.util.CommerceUserSegmentHelper;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.module.configuration.ConfigurationProvider;
import com.liferay.portal.kernel.settings.GroupServiceSettingsLocator;

import java.util.List;
import java.util.Optional;

/**
 * @author Marco Leo
 */
public class CommerceContextImpl implements CommerceContext {

	public CommerceContextImpl(
		long groupId, long userId, long orderId, long commerceAccountId,
		String couponCode, CommerceAccountService commerceAccountService,
		CommerceCurrencyLocalService commerceCurrencyLocalService,
		CommerceOrderService commerceOrderService,
		CommercePriceListLocalService commercePriceListLocalService,
		CommerceUserSegmentHelper commerceUserSegmentHelper,
		ConfigurationProvider configurationProvider,
		CPRuleLocalService cpRuleLocalService) {

		_groupId = groupId;
		_userId = userId;
		_orderId = orderId;
		_commerceAccountId = commerceAccountId;
		_couponCode = couponCode;
		_commerceAccountService = commerceAccountService;
		_commerceCurrencyLocalService = commerceCurrencyLocalService;
		_commerceOrderService = commerceOrderService;
		_commercePriceListLocalService = commercePriceListLocalService;
		_commerceUserSegmentHelper = commerceUserSegmentHelper;
		_cpRuleLocalService = cpRuleLocalService;

		try {
			_commerceAccountGroupServiceConfiguration =
				configurationProvider.getConfiguration(
					CommerceAccountGroupServiceConfiguration.class,
					new GroupServiceSettingsLocator(
						_groupId, CommerceAccountConstants.SERVICE_NAME));
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

		_commerceAccount = _commerceAccountService.getCommerceAccount(
			_commerceAccountId);

		return _commerceAccount;
	}

	@Override
	public CommerceCurrency getCommerceCurrency() throws PortalException {
		if (_commerceCurrency != null) {
			return _commerceCurrency;
		}

		_commerceCurrency =
			_commerceCurrencyLocalService.fetchPrimaryCommerceCurrency(
				_groupId);

		return _commerceCurrency;
	}

	@Override
	public CommerceOrder getCommerceOrder() throws PortalException {
		if (_commerceOrder != null) {
			return _commerceOrder;
		}

		_commerceOrder = _commerceOrderService.getCommerceOrder(_orderId);

		return _commerceOrder;
	}

	@Override
	public Optional<CommercePriceList> getCommercePriceList()
		throws PortalException {

		if (_commercePriceList != null) {
			return _commercePriceList;
		}

		_commercePriceList =
			_commercePriceListLocalService.getCommercePriceList(
				_groupId, getCommerceUserSegmentEntryIds());

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
				_groupId, _commerceAccountId, _userId);

		return _commerceUserSegmentEntryIds;
	}

	@Override
	public String getCouponCode() throws PortalException {
		return _couponCode;
	}

	@Override
	public List<CPRule> getCPRules() throws PortalException {
		if (_cpRules != null) {
			return _cpRules;
		}

		_cpRules = _cpRuleLocalService.getCPRules(
			_groupId, getCommerceUserSegmentEntryIds());

		return null;
	}

	@Override
	public long getSiteGroupId() throws PortalException {
		return _groupId;
	}

	@Override
	public long getUserId() {
		return _userId;
	}

	private static final Log _log = LogFactoryUtil.getLog(
		CommerceContextImpl.class);

	private CommerceAccount _commerceAccount;
	private CommerceAccountGroupServiceConfiguration
		_commerceAccountGroupServiceConfiguration;
	private final long _commerceAccountId;
	private final CommerceAccountService _commerceAccountService;
	private CommerceCurrency _commerceCurrency;
	private final CommerceCurrencyLocalService _commerceCurrencyLocalService;
	private CommerceOrder _commerceOrder;
	private final CommerceOrderService _commerceOrderService;
	private Optional<CommercePriceList> _commercePriceList;
	private final CommercePriceListLocalService _commercePriceListLocalService;
	private long[] _commerceUserSegmentEntryIds;
	private final CommerceUserSegmentHelper _commerceUserSegmentHelper;
	private final String _couponCode;
	private final CPRuleLocalService _cpRuleLocalService;
	private List<CPRule> _cpRules;
	private final long _groupId;
	private final long _orderId;
	private final long _userId;

}