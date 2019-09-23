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

package com.liferay.commerce.price.list.model.impl;

import com.liferay.commerce.currency.model.CommerceMoney;
import com.liferay.commerce.currency.model.CommerceMoneyFactoryUtil;
import com.liferay.commerce.price.list.model.CommercePriceEntry;
import com.liferay.commerce.price.list.service.CommercePriceEntryLocalServiceUtil;
import com.liferay.portal.kernel.exception.PortalException;

/**
 * @author Alessio Antonio Rendina
 */
public class CommerceTierPriceEntryImpl extends CommerceTierPriceEntryBaseImpl {

	public CommerceTierPriceEntryImpl() {
	}

	@Override
	public CommercePriceEntry getCommercePriceEntry() throws PortalException {
		return CommercePriceEntryLocalServiceUtil.getCommercePriceEntry(
			getCommercePriceEntryId());
	}

	@Override
	public CommerceMoney getPriceMoney(long commerceCurrencyId)
		throws PortalException {

		return CommerceMoneyFactoryUtil.create(commerceCurrencyId, getPrice());
	}

	@Override
	public CommerceMoney getPromoPriceMoney(long commerceCurrencyId)
		throws PortalException {

		return CommerceMoneyFactoryUtil.create(
			commerceCurrencyId, getPromoPrice());
	}

}