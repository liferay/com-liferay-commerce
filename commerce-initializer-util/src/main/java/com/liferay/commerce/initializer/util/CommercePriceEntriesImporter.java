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

package com.liferay.commerce.initializer.util;

import com.liferay.commerce.price.list.exception.NoSuchPriceListException;
import com.liferay.commerce.price.list.model.CommercePriceList;
import com.liferay.commerce.price.list.service.CommercePriceEntryLocalService;
import com.liferay.commerce.price.list.service.CommercePriceListLocalService;
import com.liferay.commerce.product.exception.NoSuchCPInstanceException;
import com.liferay.commerce.product.model.CPDefinition;
import com.liferay.commerce.product.model.CPInstance;
import com.liferay.commerce.product.service.CPDefinitionLocalService;
import com.liferay.commerce.product.service.CPInstanceLocalService;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.service.ServiceContext;

import java.math.BigDecimal;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Alec Sloan
 */
@Component(service = CommercePriceEntriesImporter.class)
public class CommercePriceEntriesImporter {

	public void importCommercePriceEntries(
			JSONArray jsonArray, ServiceContext serviceContext)
		throws PortalException {

		for (int i = 0; i < jsonArray.length(); i++) {
			JSONObject jsonObject = jsonArray.getJSONObject(i);

			_importCommercePriceEntry(jsonObject, serviceContext);
		}
	}

	private void _importCommercePriceEntry(
			JSONObject jsonObject, ServiceContext serviceContext)
		throws PortalException {

		String name = jsonObject.getString("PriceList");

		CommercePriceList commercePriceList =
			_commercePriceListLocalService.fetchByExternalReferenceCode(
				serviceContext.getCompanyId(), name);

		if (commercePriceList == null) {
			throw new NoSuchPriceListException(
				"No price list found with name " + name);
		}

		String sku = jsonObject.getString("Sku");

		CPInstance cpInstance =
			_cpInstanceLocalService.fetchByExternalReferenceCode(
				serviceContext.getCompanyId(), sku);

		if (cpInstance == null) {
			throw new NoSuchCPInstanceException(
				"No cpInstance found with sku " + sku);
		}

		CPDefinition cpDefinition = _cpDefinitionLocalService.fetchCPDefinition(
			cpInstance.getCPDefinitionId());

		double price = jsonObject.getDouble("Price", 0);
		double promoPrice = jsonObject.getDouble("PromoPrice", 0);

		_commercePriceEntryLocalService.addCommercePriceEntry(
			cpDefinition.getCProductId(), cpInstance.getCPInstanceUuid(),
			commercePriceList.getCommercePriceListId(),
			BigDecimal.valueOf(price), BigDecimal.valueOf(promoPrice),
			serviceContext);

	}

	@Reference
	private CommercePriceEntryLocalService _commercePriceEntryLocalService;

	@Reference
	private CommercePriceListLocalService _commercePriceListLocalService;

	@Reference
	private CPDefinitionLocalService _cpDefinitionLocalService;

	@Reference
	private CPInstanceLocalService _cpInstanceLocalService;

}