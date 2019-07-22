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

package com.liferay.headless.commerce.admin.pricing.internal.util.v1_0;

import com.liferay.commerce.price.list.model.CommercePriceEntry;
import com.liferay.commerce.price.list.model.CommercePriceList;
import com.liferay.commerce.price.list.service.CommercePriceEntryService;
import com.liferay.headless.commerce.admin.pricing.dto.v1_0.PriceEntry;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.GetterUtil;

/**
 * @author Alessio Antonio Rendina
 */
public class PriceEntryUtil {

	public static CommercePriceEntry upsertCommercePriceEntry(
			CommercePriceEntryService commercePriceEntryService,
			PriceEntry priceEntry, CommercePriceList commercePriceList,
			ServiceContext serviceContext)
		throws PortalException {

		return commercePriceEntryService.upsertCommercePriceEntry(
			GetterUtil.getLong(priceEntry.getId()),
			GetterUtil.getLong(priceEntry.getSkuId()), null,
			commercePriceList.getCommercePriceListId(),
			priceEntry.getExternalReferenceCode(), priceEntry.getPrice(),
			priceEntry.getPromoPrice(),
			priceEntry.getSkuExternalReferenceCode(), serviceContext);
	}

}