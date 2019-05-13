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

package com.liferay.commerce.inventory.internal.method;

import com.liferay.commerce.inventory.constants.CommerceInventoryConstants;
import com.liferay.commerce.inventory.method.CommerceInventoryMethod;
import com.liferay.commerce.inventory.model.CommerceInventoryBookedQuantity;
import com.liferay.commerce.inventory.model.CommerceInventoryWarehouseItem;
import com.liferay.commerce.inventory.service.CommerceInventoryBookedQuantityLocalService;
import com.liferay.commerce.inventory.service.CommerceInventoryWarehouseItemLocalService;
import com.liferay.portal.kernel.exception.PortalException;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Luca Pellizzon
 */
@Component(
	immediate = true,
	property = "commerce.inventory.method.key=" + DefaultCommerceInventoryMethod.KEY,
	service = CommerceInventoryMethod.class
)
public class DefaultCommerceInventoryMethod implements CommerceInventoryMethod {

	public static final String KEY =
		CommerceInventoryConstants.DEFAULT_METHOD_NAME;

	@Override
	public void consumeQuantity(
			long groupId, String sku, int quantity, long warehouseId,
			long bookedQuantityId)
		throws PortalException {

		if (bookedQuantityId > 0) {
			CommerceInventoryBookedQuantity commerceInventoryBookedQuantity =
				_commerceBookedQuantityLocalService.
					getCommerceInventoryBookedQuantity(bookedQuantityId);

			_commerceBookedQuantityLocalService.consumeCommerceBookedQuantity(
				commerceInventoryBookedQuantity.
					getCommerceInventoryBookedQuantityId(),
				quantity);

			decreaseStockQuantity(sku, quantity, warehouseId);
		}

		decreaseStockQuantity(sku, quantity, warehouseId);
	}

	@Override
	public void decreaseStockQuantity(
			String sku, int quantity, long warehouseId)
		throws PortalException {

		CommerceInventoryWarehouseItem commerceWarehouseItem =
			_commerceWarehouseItemLocalService.getCommerceWarehouseItem(
				warehouseId, sku);

		_commerceWarehouseItemLocalService.removeStockQuantity(
			commerceWarehouseItem.getCommerceInventoryWarehouseItemId(),
			quantity);
	}

	@Override
	public Map<String, Integer> getStockQuantities(
		long companyID, long groupId, List<String> skus) {

		Map<String, Integer> results = new HashMap<>();

		for (String sku : skus) {
			int stockQuantity = getStockQuantity(companyID, groupId, sku);

			results.put(sku, stockQuantity);
		}

		return results;
	}

	@Override
	public int getStockQuantity(long companyID, long groupId, String sku) {
		int stockQuantityByGroupIdAndSku =
			_commerceWarehouseItemLocalService.getStockQuantityByGroupIdAndSku(
				companyID, groupId, sku);

		int commerceBookedQuantity =
			_commerceBookedQuantityLocalService.getCommerceBookedQuantity(sku);

		return stockQuantityByGroupIdAndSku - commerceBookedQuantity;
	}

	@Override
	public void increaseStockQuantity(
			String sku, int quantity, long warehouseId)
		throws PortalException {

		_commerceWarehouseItemLocalService.addCommerceWarehouseItem(
			warehouseId, sku, quantity, 0);
	}

	@Reference
	private CommerceInventoryBookedQuantityLocalService
		_commerceBookedQuantityLocalService;

	@Reference
	private CommerceInventoryWarehouseItemLocalService
		_commerceWarehouseItemLocalService;

}