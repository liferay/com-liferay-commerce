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

import com.liferay.commerce.inventory.model.CommerceInventoryReplenishmentItem;
import com.liferay.commerce.inventory.service.base.CommerceInventoryReplenishmentItemLocalServiceBaseImpl;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.util.OrderByComparator;

import java.util.Date;
import java.util.List;

/**
 * @author Luca Pellizzon
 */
public class CommerceInventoryReplenishmentItemLocalServiceImpl
	extends CommerceInventoryReplenishmentItemLocalServiceBaseImpl {

	@Override
	public CommerceInventoryReplenishmentItem addCommerceReplenishmentItem(
			long commerceWarehouseId, long userId, String sku,
			Date availabilityDate, int quantity)
		throws PortalException {

		User user = userLocalService.getUser(userId);

		long commerceReplenishmentItemId = counterLocalService.increment();

		CommerceInventoryReplenishmentItem commerceReplenishmentItem =
			commerceInventoryReplenishmentItemPersistence.create(
				commerceReplenishmentItemId);

		commerceReplenishmentItem.setCompanyId(user.getCompanyId());
		commerceReplenishmentItem.setUserId(user.getUserId());
		commerceReplenishmentItem.setUserName(user.getFullName());
		commerceReplenishmentItem.setCommerceWarehouseId(commerceWarehouseId);
		commerceReplenishmentItem.setSku(sku);
		commerceReplenishmentItem.setAvailabilityDate(availabilityDate);
		commerceReplenishmentItem.setQuantity(quantity);

		return commerceInventoryReplenishmentItemPersistence.updateImpl(
			commerceReplenishmentItem);
	}

	@Override
	public List<CommerceInventoryReplenishmentItem>
		getCommerceReplenishmentItems(String sku) {

		return commerceInventoryReplenishmentItemPersistence.findBysku(sku);
	}

	@Override
	public List<CommerceInventoryReplenishmentItem>
		getCommerceReplenishmentItemsByGroupIdAndSku(
			long companyId, long groupId, String sku) {

		return commerceInventoryReplenishmentItemFinder.findByGroupAndSku(
			companyId, groupId, sku);
	}

	@Override
	public List<CommerceInventoryReplenishmentItem>
		getCommerceReplenishmentItemsOrderedByAvailabilityDate(
			String sku,
			OrderByComparator<CommerceInventoryReplenishmentItem>
				orderByComparator) {

		return commerceInventoryReplenishmentItemPersistence.findBysku(
			sku, QueryUtil.ALL_POS, QueryUtil.ALL_POS, orderByComparator);
	}

}