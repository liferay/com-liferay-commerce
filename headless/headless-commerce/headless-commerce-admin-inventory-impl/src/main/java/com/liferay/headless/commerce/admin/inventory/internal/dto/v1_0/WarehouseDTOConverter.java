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
import com.liferay.commerce.inventory.service.CommerceInventoryWarehouseService;
import com.liferay.headless.commerce.admin.inventory.dto.v1_0.Warehouse;
import com.liferay.headless.commerce.admin.inventory.dto.v1_0.WarehouseItem;
import com.liferay.headless.commerce.core.dto.v1_0.converter.DTOConverter;
import com.liferay.headless.commerce.core.dto.v1_0.converter.DTOConverterContext;
import com.liferay.headless.commerce.core.dto.v1_0.converter.DTOConverterRegistry;
import com.liferay.headless.commerce.core.dto.v1_0.converter.DefaultDTOConverterContext;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Alessio Antonio Rendina
 */
@Component(
	property = "model.class.name=com.liferay.commerce.inventory.model.CommerceInventoryWarehouse",
	service = {DTOConverter.class, WarehouseDTOConverter.class}
)
public class WarehouseDTOConverter implements DTOConverter {

	@Override
	public String getContentType() {
		return Warehouse.class.getSimpleName();
	}

	@Override
	public Warehouse toDTO(DTOConverterContext dtoConverterContext)
		throws Exception {

		CommerceInventoryWarehouse commerceInventoryWarehouse =
			_commerceInventoryWarehouseService.getCommerceInventoryWarehouse(
				dtoConverterContext.getResourcePrimKey());

		return new Warehouse() {
			{
				active = commerceInventoryWarehouse.isActive();
				city = commerceInventoryWarehouse.getCity();
				countryISOCode =
					commerceInventoryWarehouse.getCountryTwoLettersISOCode();
				description = commerceInventoryWarehouse.getDescription();
				externalReferenceCode =
					commerceInventoryWarehouse.getExternalReferenceCode();
				id =
					commerceInventoryWarehouse.
						getCommerceInventoryWarehouseId();
				latitude = commerceInventoryWarehouse.getLatitude();
				longitude = commerceInventoryWarehouse.getLongitude();
				name = commerceInventoryWarehouse.getName();
				regionISOCode =
					commerceInventoryWarehouse.getCommerceRegionCode();
				street1 = commerceInventoryWarehouse.getStreet1();
				street2 = commerceInventoryWarehouse.getStreet2();
				street3 = commerceInventoryWarehouse.getStreet3();
				type = commerceInventoryWarehouse.getType();
				zip = commerceInventoryWarehouse.getZip();
			}
		};
	}

	private WarehouseItem[] _getItems(
			CommerceInventoryWarehouse commerceInventoryWarehouse,
			DTOConverterContext dtoConverterContext)
		throws Exception {

		List<WarehouseItem> warehouseItems = new ArrayList<>();

		DTOConverter warehouseItemDTOConverter =
			_dtoConverterRegistry.getDTOConverter(
				CommerceInventoryWarehouseItem.class.getName());

		for (CommerceInventoryWarehouseItem commerceInventoryWarehouseItem :
				commerceInventoryWarehouse.
					getCommerceInventoryWarehouseItems()) {

			warehouseItems.add(
				(WarehouseItem)warehouseItemDTOConverter.toDTO(
					new DefaultDTOConverterContext(
						dtoConverterContext.getLocale(),
						commerceInventoryWarehouseItem.
							getCommerceInventoryWarehouseItemId())));
		}

		Stream<WarehouseItem> stream = warehouseItems.stream();

		return stream.toArray(WarehouseItem[]::new);
	}

	@Reference
	private CommerceInventoryWarehouseService
		_commerceInventoryWarehouseService;

	@Reference
	private DTOConverterRegistry _dtoConverterRegistry;

}