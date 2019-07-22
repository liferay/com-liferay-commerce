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
import com.liferay.commerce.price.list.model.CommerceTierPriceEntry;
import com.liferay.commerce.price.list.service.CommerceTierPriceEntryService;
import com.liferay.headless.commerce.admin.pricing.dto.v1_0.TierPrice;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.GetterUtil;

/**
 * @author Alessio Antonio Rendina
 */
public class TierPriceUtil {

	public static CommerceTierPriceEntry upsertCommerceTierPriceEntry(
			CommerceTierPriceEntryService commerceTierPriceEntryService,
			TierPrice tierPrice, CommercePriceEntry commercePriceEntry,
			ServiceContext serviceContext)
		throws PortalException {

		return commerceTierPriceEntryService.upsertCommerceTierPriceEntry(
			GetterUtil.getLong(tierPrice.getId()),
			commercePriceEntry.getCommercePriceEntryId(),
			tierPrice.getExternalReferenceCode(), tierPrice.getPrice(),
			tierPrice.getPromoPrice(), tierPrice.getMinimumQuantity(),
			tierPrice.getPriceEntryExternalReferenceCode(), serviceContext);
	}

}