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
import com.liferay.commerce.product.model.CommerceCatalog;
import com.liferay.commerce.product.model.CommerceChannel;
import com.liferay.commerce.product.service.CommerceChannelLocalService;
import com.liferay.commerce.service.CommerceOrderService;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.module.configuration.ConfigurationProvider;
import com.liferay.portal.kernel.settings.GroupServiceSettingsLocator;

import java.util.Optional;

/**
 * @author Marco Leo
 * @author Alessio Antonio Rendina
 */
public class CommerceContextImpl implements CommerceContext {

	public CommerceContextImpl(
		long companyId, long groupId, long userId, long orderId,
		long commerceAccountId, CommerceAccountService commerceAccountService,
		CommerceChannelLocalService commerceChannelLocalService,
		CommerceCurrencyLocalService commerceCurrencyLocalService,
		CommerceOrderService commerceOrderService,
		CommercePriceListLocalService commercePriceListLocalService,
		ConfigurationProvider configurationProvider) {

		_companyId = companyId;
		_groupId = groupId;
		_userId = userId;
		_orderId = orderId;
		_commerceAccountId = commerceAccountId;
		_commerceAccountService = commerceAccountService;
		_commerceChannelLocalService = commerceChannelLocalService;
		_commerceCurrencyLocalService = commerceCurrencyLocalService;
		_commerceOrderService = commerceOrderService;
		_commercePriceListLocalService = commercePriceListLocalService;

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
	public long[] getCommerceAccountGroupIds() {
		return new long[0];
	}

	@Override
	public CommerceCatalog getCommerceCatalog() {
		return null;
	}

	@Override
	public long getCommerceCatalogGroupId() throws PortalException {
		return 0;
	}

	@Override
	public CommerceCurrency getCommerceCurrency() throws PortalException {
		if (_commerceCurrency != null) {
			return _commerceCurrency;
		}

		CommerceChannel commerceChannel =
			_commerceChannelLocalService.fetchCommerceChannelByGroupId(
				_groupId);

		if (commerceChannel == null) {
			_commerceCurrency =
				_commerceCurrencyLocalService.fetchPrimaryCommerceCurrency(
					_companyId);
		}
		else {
			_commerceCurrency =
				_commerceCurrencyLocalService.getCommerceCurrency(
					_companyId, commerceChannel.getCommerceCurrencyCode());
		}

		return _commerceCurrency;
	}

	@Override
	public CommerceOrder getCommerceOrder() throws PortalException {
		if (_commerceOrder != null) {
			return _commerceOrder;
		}

		_commerceOrder = _commerceOrderService.fetchCommerceOrder(_orderId);

		return _commerceOrder;
	}

	@Override
	public Optional<CommercePriceList> getCommercePriceList()
		throws PortalException {

		if (_commercePriceList != null) {
			return _commercePriceList;
		}

		CommerceAccount commerceAccount = getCommerceAccount();

		_commercePriceList =
			_commercePriceListLocalService.getCommercePriceList(
				new long[] {getCommerceCatalogGroupId()}, _companyId,
				commerceAccount.getCommerceAccountId(),
				getCommerceAccountGroupIds());

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
	private final CommerceChannelLocalService _commerceChannelLocalService;
	private CommerceCurrency _commerceCurrency;
	private final CommerceCurrencyLocalService _commerceCurrencyLocalService;
	private CommerceOrder _commerceOrder;
	private final CommerceOrderService _commerceOrderService;
	private Optional<CommercePriceList> _commercePriceList;
	private final CommercePriceListLocalService _commercePriceListLocalService;
	private final long _companyId;
	private final long _groupId;
	private final long _orderId;
	private final long _userId;

}