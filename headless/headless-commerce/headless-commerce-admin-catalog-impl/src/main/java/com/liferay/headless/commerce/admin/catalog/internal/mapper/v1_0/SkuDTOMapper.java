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

package com.liferay.headless.commerce.admin.catalog.internal.mapper.v1_0;

import com.liferay.commerce.product.model.CPInstance;
import com.liferay.headless.commerce.admin.catalog.dto.v1_0.Sku;

import org.osgi.service.component.annotations.Component;

/**
 * @author Alessio Antonio Rendina
 */
@Component(immediate = true, service = SkuDTOMapper.class)
public class SkuDTOMapper {

	public Sku toSku(CPInstance cpInstance) {
		Sku sku = new Sku();

		if (cpInstance == null) {
			return sku;
		}

		sku.setCost(cpInstance.getCost());
		sku.setDepth(cpInstance.getDepth());
		sku.setDisplayDate(cpInstance.getDisplayDate());
		sku.setExpirationDate(cpInstance.getExpirationDate());
		sku.setExternalReferenceCode(cpInstance.getExternalReferenceCode());
		sku.setGtin(cpInstance.getGtin());
		sku.setHeight(cpInstance.getHeight());
		sku.setId(cpInstance.getCPInstanceId());
		sku.setManufacturerPartNumber(cpInstance.getManufacturerPartNumber());
		sku.setNeverExpire(cpInstance.isExpired());
		sku.setPrice(cpInstance.getPrice());
		sku.setPromoPrice(cpInstance.getPromoPrice());
		sku.setPublished(cpInstance.isPublished());
		sku.setPurchasable(cpInstance.isPurchasable());
		sku.setSku(cpInstance.getSku());
		sku.setWeight(cpInstance.getWeight());
		sku.setWidth(cpInstance.getWidth());

		return sku;
	}

}