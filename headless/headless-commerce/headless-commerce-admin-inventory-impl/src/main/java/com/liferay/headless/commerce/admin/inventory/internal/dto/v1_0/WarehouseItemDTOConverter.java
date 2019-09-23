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

package com.liferay.headless.commerce.admin.inventory.internal.dto.v1_0;

import com.liferay.commerce.inventory.model.CommerceInventoryWarehouse;
import com.liferay.commerce.inventory.model.CommerceInventoryWarehouseItem;
import com.liferay.commerce.inventory.service.CommerceInventoryWarehouseItemService;
import com.liferay.headless.commerce.admin.inventory.dto.v1_0.WarehouseItem;
import com.liferay.headless.commerce.core.dto.v1_0.converter.DTOConverter;
import com.liferay.headless.commerce.core.dto.v1_0.converter.DTOConverterContext;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Alessio Antonio Rendina
 */
@Component(
	property = "model.class.name=com.liferay.commerce.inventory.model.CommerceInventoryWarehouseItem",
	service = {DTOConverter.class, WarehouseItemDTOConverter.class}
)
public class WarehouseItemDTOConverter implements DTOConverter {

	@Override
	public String getContentType() {
		return WarehouseItem.class.getSimpleName();
	}

	public WarehouseItem toDTO(DTOConverterContext dtoConverterContext)
		throws Exception {

		CommerceInventoryWarehouseItem commerceInventoryWarehouseItem =
			_commerceInventoryWarehouseItemService.
				getCommerceInventoryWarehouseItem(
					dtoConverterContext.getResourcePrimKey());

		CommerceInventoryWarehouse commerceInventoryWarehouse =
			commerceInventoryWarehouseItem.getCommerceInventoryWarehouse();

		return new WarehouseItem() {
			{
				id =
					commerceInventoryWarehouseItem.
						getCommerceInventoryWarehouseItemId();
				quantity = commerceInventoryWarehouseItem.getQuantity();
				reservedQuantity =
					commerceInventoryWarehouseItem.getReservedQuantity();
				sku = commerceInventoryWarehouseItem.getSku();
				warehouseExternalReferenceCode =
					commerceInventoryWarehouse.getExternalReferenceCode();
				warehouseId =
					commerceInventoryWarehouse.
						getCommerceInventoryWarehouseId();
			}
		};
	}

	@Reference
	private CommerceInventoryWarehouseItemService
		_commerceInventoryWarehouseItemService;

}