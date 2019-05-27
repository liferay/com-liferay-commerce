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

package com.liferay.commerce.test.util;

import com.liferay.commerce.account.model.CommerceAccount;
import com.liferay.commerce.context.CommerceContext;
import com.liferay.commerce.currency.model.CommerceCurrency;
import com.liferay.commerce.model.CommerceOrder;
import com.liferay.commerce.price.list.model.CommercePriceList;
import com.liferay.commerce.price.list.service.CommercePriceListLocalServiceUtil;
import com.liferay.commerce.product.model.CommerceCatalog;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.model.Group;
import com.liferay.portal.kernel.model.User;

import java.util.Optional;

/**
 * @author Luca Pellizzon
 * @author Alessio Antonio Rendina
 */
public class TestCommerceContext implements CommerceContext {

	public TestCommerceContext(
		CommerceCurrency commerceCurrency, User contextUser, Group contextGroup,
		CommerceAccount commerceAccount, CommerceOrder commerceOrder) {

		_commerceCurrency = commerceCurrency;
		_commerceCatalog = null;
		_contextUser = contextUser;
		_contextGroup = contextGroup;
		_commerceAccount = commerceAccount;
		_commerceOrder = commerceOrder;
	}

	@Override
	public CommerceAccount getCommerceAccount() {
		return _commerceAccount;
	}

	@Override
	public long[] getCommerceAccountGroupIds() {
		return new long[0];
	}

	@Override
	public CommerceCatalog getCommerceCatalog() {
		return _commerceCatalog;
	}

	@Override
	public long getCommerceCatalogGroupId() throws PortalException {
		if (_commerceCatalog == null) {
			return 0;
		}

		return _commerceCatalog.getCommerceCatalogGroupId();
	}

	@Override
	public CommerceCurrency getCommerceCurrency() {
		return _commerceCurrency;
	}

	@Override
	public CommerceOrder getCommerceOrder() {
		return _commerceOrder;
	}

	@Override
	public Optional<CommercePriceList> getCommercePriceList()
		throws PortalException {

		if (_commerceAccount == null) {
			return Optional.empty();
		}

		return CommercePriceListLocalServiceUtil.getCommercePriceList(
			new long[] {getCommerceCatalogGroupId()},
			_contextUser.getCompanyId(),
			_commerceAccount.getCommerceAccountId(),
			getCommerceAccountGroupIds());
	}

	@Override
	public int getCommerceSiteType() {
		return 0;
	}

	@Override
	public long getSiteGroupId() throws PortalException {
		return _contextGroup.getGroupId();
	}

	@Override
	public long getUserId() {
		return _contextUser.getUserId();
	}

	private final CommerceAccount _commerceAccount;
	private final CommerceCatalog _commerceCatalog;
	private final CommerceCurrency _commerceCurrency;
	private final CommerceOrder _commerceOrder;
	private final Group _contextGroup;
	private final User _contextUser;

}