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

package com.liferay.commerce.inventory.service.impl;

import com.liferay.commerce.inventory.exception.CommerceInventoryWarehouseItemQuantityBelowZeroException;
import com.liferay.commerce.inventory.exception.NoSuchInventoryWarehouseItemException;
import com.liferay.commerce.inventory.model.CommerceInventoryWarehouseItem;
import com.liferay.commerce.inventory.service.base.CommerceInventoryWarehouseItemLocalServiceBaseImpl;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.model.User;

import java.util.List;

/**
 * @author Luca Pellizzon
 */
public class CommerceInventoryWarehouseItemLocalServiceImpl
	extends CommerceInventoryWarehouseItemLocalServiceBaseImpl {

	@Override
	public CommerceInventoryWarehouseItem addCommerceWarehouseItem(
			long commerceWarehouseId, String sku, int quantity, long userId)
		throws PortalException {

		try {
			CommerceInventoryWarehouseItem existingItem =
				commerceInventoryWarehouseItemPersistence.findByC_S(
					commerceWarehouseId, sku);

			existingItem.setQuantity(existingItem.getQuantity() + quantity);

			return commerceInventoryWarehouseItemPersistence.update(
				existingItem);
		}
		catch (NoSuchInventoryWarehouseItemException nsiwie) {
			User user = userLocalService.getUser(userId);

			long commerceWarehouseItemId = counterLocalService.increment();

			CommerceInventoryWarehouseItem commerceWarehouseItem =
				commerceInventoryWarehouseItemPersistence.create(
					commerceWarehouseItemId);

			commerceWarehouseItem.setCompanyId(user.getCompanyId());
			commerceWarehouseItem.setUserId(user.getUserId());
			commerceWarehouseItem.setUserName(user.getFullName());
			commerceWarehouseItem.setCommerceWarehouseId(commerceWarehouseId);
			commerceWarehouseItem.setSku(sku);
			commerceWarehouseItem.setQuantity(quantity);

			commerceInventoryWarehouseItemPersistence.update(
				commerceWarehouseItem);

			return commerceWarehouseItem;
		}
	}

	@Override
	public CommerceInventoryWarehouseItem addStockQuantity(
			long commerceWarehouseItemId, int quantity)
		throws NoSuchInventoryWarehouseItemException {

		CommerceInventoryWarehouseItem commerceWarehouseItem =
			commerceInventoryWarehouseItemPersistence.findByPrimaryKey(
				commerceWarehouseItemId);

		commerceWarehouseItem.setQuantity(
			commerceWarehouseItem.getQuantity() + quantity);

		return commerceInventoryWarehouseItemPersistence.update(
			commerceWarehouseItem);
	}

	@Override
	public void deleteCommerceWarehouseItems(long commerceWarehouseId) {
		commerceInventoryWarehouseItemPersistence.removeByCommerceWarehouseId(
			commerceWarehouseId);
	}

	@Override
	public CommerceInventoryWarehouseItem fetchCommerceWarehouseItem(
		long commerceWarehouseId, String sku) {

		return commerceInventoryWarehouseItemPersistence.fetchByC_S(
			commerceWarehouseId, sku);
	}

	@Override
	public CommerceInventoryWarehouseItem getCommerceWarehouseItem(
			long commerceWarehouseId, String sku)
		throws NoSuchInventoryWarehouseItemException {

		return commerceInventoryWarehouseItemPersistence.findByC_S(
			commerceWarehouseId, sku);
	}

	@Override
	public List<CommerceInventoryWarehouseItem> getCommerceWarehouseItems(
		String sku) {

		return commerceInventoryWarehouseItemPersistence.findBysku(sku);
	}

	@Override
	public List<CommerceInventoryWarehouseItem>
		getCommerceWarehouseItemsByCommerceWarehouseId(
			long commerceWarehouseId) {

		return commerceInventoryWarehouseItemPersistence.
			findByCommerceWarehouseId(commerceWarehouseId);
	}

	@Override
	public int getCommerceWarehouseItemsCount(String sku) {
		return commerceInventoryWarehouseItemPersistence.countBysku(sku);
	}

	@Override
	public int getStockQuantityByGroupIdAndSku(
		long companyId, long groupId, String sku) {

		return commerceInventoryWarehouseItemFinder.
			findStockQuantityByGroupIdAndSku(companyId, groupId, sku);
	}

	@Override
	public CommerceInventoryWarehouseItem removeStockQuantity(
			long commerceWarehouseItemId, int quantity)
		throws PortalException {

		CommerceInventoryWarehouseItem commerceWarehouseItem =
			commerceInventoryWarehouseItemPersistence.findByPrimaryKey(
				commerceWarehouseItemId);

		int newQuantity = commerceWarehouseItem.getQuantity() - quantity;

		if (newQuantity < 0) {
			throw new CommerceInventoryWarehouseItemQuantityBelowZeroException();
		}

		commerceWarehouseItem.setQuantity(newQuantity);

		return commerceInventoryWarehouseItemPersistence.update(
			commerceWarehouseItem);
	}

	@Override
	public CommerceInventoryWarehouseItem updateCommerceWarehouseItem(
			long commerceWarehouseItemId, int quantity)
		throws PortalException {

		CommerceInventoryWarehouseItem commerceWarehouseItem =
			commerceInventoryWarehouseItemPersistence.findByPrimaryKey(
				commerceWarehouseItemId);

		commerceWarehouseItem.setQuantity(quantity);

		commerceInventoryWarehouseItemPersistence.update(commerceWarehouseItem);

		return commerceWarehouseItem;
	}

}