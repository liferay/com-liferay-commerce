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
import com.liferay.commerce.model.CommerceOrder;
import com.liferay.commerce.order.CommerceOrderHttpHelper;
import com.liferay.commerce.price.list.model.CommercePriceList;
import com.liferay.commerce.price.list.service.CommercePriceListLocalService;
import com.liferay.commerce.product.model.CommerceCatalog;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.module.configuration.ConfigurationProvider;
import com.liferay.portal.kernel.settings.GroupServiceSettingsLocator;
import com.liferay.portal.kernel.util.Portal;

import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Marco Leo
 * @author Alessio Antonio Rendina
 */
public class CommerceContextHttpImpl implements CommerceContext {

	public CommerceContextHttpImpl(
		HttpServletRequest httpServletRequest,
		CommerceAccountHelper commerceAccountHelper,
		CommerceCurrencyLocalService commerceCurrencyLocalService,
		CommerceOrderHttpHelper commerceOrderHttpHelper,
		CommercePriceListLocalService commercePriceListLocalService,
		ConfigurationProvider configurationProvider, Portal portal) {

		_httpServletRequest = httpServletRequest;
		_commerceAccountHelper = commerceAccountHelper;
		_commerceCurrencyLocalService = commerceCurrencyLocalService;
		_commerceOrderHttpHelper = commerceOrderHttpHelper;
		_commercePriceListLocalService = commercePriceListLocalService;
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

		_commerceCurrency =
			_commerceCurrencyLocalService.fetchPrimaryCommerceCurrency(
				_portal.getCompanyId(_httpServletRequest));

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

		CommerceAccount commerceAccount = getCommerceAccount();

		if (commerceAccount == null) {
			return Optional.empty();
		}

		_commercePriceList =
			_commercePriceListLocalService.getCommercePriceList(
				new long[] {getCommerceCatalogGroupId()},
				_portal.getCompanyId(_httpServletRequest),
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
	private CommerceOrder _commerceOrder;
	private final CommerceOrderHttpHelper _commerceOrderHttpHelper;
	private Optional<CommercePriceList> _commercePriceList;
	private final CommercePriceListLocalService _commercePriceListLocalService;
	private final HttpServletRequest _httpServletRequest;
	private final Portal _portal;

}