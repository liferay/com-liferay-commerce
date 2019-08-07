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

package com.liferay.commerce.inventory.internal.engine;

import com.liferay.commerce.inventory.engine.CommerceInventoryEngine;
import com.liferay.commerce.inventory.model.CommerceInventoryBookedQuantity;
import com.liferay.commerce.inventory.model.CommerceInventoryWarehouseItem;
import com.liferay.commerce.inventory.service.CommerceInventoryAuditLocalService;
import com.liferay.commerce.inventory.service.CommerceInventoryBookedQuantityLocalService;
import com.liferay.commerce.inventory.service.CommerceInventoryWarehouseItemLocalService;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.json.JSONFactory;
import com.liferay.portal.kernel.transaction.Propagation;
import com.liferay.portal.kernel.transaction.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Luca Pellizzon
 * @author Alessio Antonio Rendina
 */
@Component(immediate = true, service = CommerceInventoryEngine.class)
public class CommerceInventoryEngineImpl implements CommerceInventoryEngine {

	@Override
	@Transactional(
		propagation = Propagation.REQUIRED, rollbackFor = Exception.class
	)
	public void consumeQuantity(
			long userId, long commerceInventoryWarehouseId, String sku,
			int quantity, long bookedQuantityId, Map<String, String> context)
		throws PortalException {

		if (bookedQuantityId > 0) {
			CommerceInventoryBookedQuantity commerceInventoryBookedQuantity =
				_commerceBookedQuantityLocalService.
					getCommerceInventoryBookedQuantity(bookedQuantityId);

			_commerceBookedQuantityLocalService.consumeCommerceBookedQuantity(
				commerceInventoryBookedQuantity.
					getCommerceInventoryBookedQuantityId(),
				quantity);

			decreaseStockQuantity(commerceInventoryWarehouseId, sku, quantity);
		}
		else {
			decreaseStockQuantity(commerceInventoryWarehouseId, sku, quantity);
		}

		String description =
			"Consume Quantity: " + _jsonFactory.serialize(context);

		_commerceInventoryAuditLocalService.addCommerceInventoryAudit(
			userId, sku, quantity, description);
	}

	@Override
	@Transactional(
		propagation = Propagation.REQUIRED, rollbackFor = Exception.class
	)
	public void decreaseStockQuantity(
			long commerceInventoryWarehouseId, String sku, int quantity)
		throws PortalException {

		CommerceInventoryWarehouseItem commerceInventoryWarehouseItem =
			_commerceInventoryWarehouseItemLocalService.
				fetchCommerceInventoryWarehouseItem(
					commerceInventoryWarehouseId, sku);

		_commerceInventoryWarehouseItemLocalService.
			updateCommerceInventoryWarehouseItem(
				commerceInventoryWarehouseItem.
					getCommerceInventoryWarehouseItemId(),
				commerceInventoryWarehouseItem.getQuantity() - quantity);
	}

	@Override
	public Map<String, Integer> getStockQuantities(
		long companyId, long channelGroupId, List<String> skus) {

		Map<String, Integer> results = new HashMap<>();

		for (String sku : skus) {
			int stockQuantity = getStockQuantity(
				companyId, channelGroupId, sku);

			results.put(sku, stockQuantity);
		}

		return results;
	}

	@Override
	public int getStockQuantity(
		long companyId, long channelGroupId, String sku) {

		int stockQuantity =
			_commerceInventoryWarehouseItemLocalService.getStockQuantity(
				companyId, channelGroupId, sku);

		int commerceBookedQuantity =
			_commerceBookedQuantityLocalService.getCommerceBookedQuantity(sku);

		return stockQuantity - commerceBookedQuantity;
	}

	@Override
	public int getStockQuantity(long companyId, String sku) {
		int stockQuantity =
			_commerceInventoryWarehouseItemLocalService.getStockQuantity(
				companyId, sku);

		int commerceBookedQuantity =
			_commerceBookedQuantityLocalService.getCommerceBookedQuantity(sku);

		return stockQuantity - commerceBookedQuantity;
	}

	@Override
	@Transactional(
		propagation = Propagation.REQUIRED, rollbackFor = Exception.class
	)
	public void increaseStockQuantity(
			long userId, long commerceInventoryWarehouseId, String sku,
			int quantity)
		throws PortalException {

		CommerceInventoryWarehouseItem commerceInventoryWarehouseItem =
			_commerceInventoryWarehouseItemLocalService.
				fetchCommerceInventoryWarehouseItem(
					commerceInventoryWarehouseId, sku);

		_commerceInventoryWarehouseItemLocalService.
			updateCommerceInventoryWarehouseItem(
				commerceInventoryWarehouseItem.
					getCommerceInventoryWarehouseItemId(),
				commerceInventoryWarehouseItem.getQuantity() + quantity);

		_commerceInventoryAuditLocalService.addCommerceInventoryAudit(
			userId, sku, quantity, "Increase Quantity");
	}

	@Reference
	private CommerceInventoryBookedQuantityLocalService
		_commerceBookedQuantityLocalService;

	@Reference
	private CommerceInventoryAuditLocalService
		_commerceInventoryAuditLocalService;

	@Reference
	private CommerceInventoryWarehouseItemLocalService
		_commerceInventoryWarehouseItemLocalService;

	@Reference
	private JSONFactory _jsonFactory;

}