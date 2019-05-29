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

import com.liferay.commerce.inventory.exception.NoSuchInventoryBookedQuantityException;
import com.liferay.commerce.inventory.model.CommerceInventoryBookedQuantity;
import com.liferay.commerce.inventory.service.base.CommerceInventoryBookedQuantityLocalServiceBaseImpl;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.model.User;

import java.io.ByteArrayOutputStream;
import java.io.ObjectOutputStream;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @author Luca Pellizzon
 */
public class CommerceInventoryBookedQuantityLocalServiceImpl
	extends CommerceInventoryBookedQuantityLocalServiceBaseImpl {

	@Override
	public CommerceInventoryBookedQuantity addCommerceBookedQuantity(
			long userId, String sku, int quantity, Date expireDate,
			Map<String, String> context)
		throws PortalException {

		User user = userLocalService.getUser(userId);

		long commerceBookedQuantityId = counterLocalService.increment();

		CommerceInventoryBookedQuantity commerceBookedQuantity =
			commerceInventoryBookedQuantityPersistence.create(
				commerceBookedQuantityId);

		commerceBookedQuantity.setCompanyId(user.getCompanyId());
		commerceBookedQuantity.setUserId(user.getUserId());
		commerceBookedQuantity.setUserName(user.getFullName());
		commerceBookedQuantity.setSku(sku);
		commerceBookedQuantity.setQuantity(quantity);
		commerceBookedQuantity.setExpireDate(expireDate);

		String description = "Book Quantity ";

		try {
			ByteArrayOutputStream byteArrayOutputStream =
				new ByteArrayOutputStream();

			ObjectOutputStream oos = new ObjectOutputStream(
				byteArrayOutputStream);

			oos.writeObject(context);
			oos.close();

			description += byteArrayOutputStream.toString();
		}
		catch (Exception e) {
			throw new PortalException(e.getMessage());
		}

		commerceInventoryAuditLocalService.addCommerceInventoryItemEntry(
			description, sku, quantity, userId);

		return commerceInventoryBookedQuantityPersistence.update(
			commerceBookedQuantity);
	}

	@Override
	public CommerceInventoryBookedQuantity consumeCommerceBookedQuantity(
			long commerceBookedQuantityId, int quantity)
		throws NoSuchInventoryBookedQuantityException {

		CommerceInventoryBookedQuantity commerceInventoryBookedQuantity =
			commerceInventoryBookedQuantityPersistence.findByPrimaryKey(
				commerceBookedQuantityId);

		if (quantity < commerceInventoryBookedQuantity.getQuantity()) {
			int newQuantity =
				commerceInventoryBookedQuantity.getQuantity() - quantity;

			commerceInventoryBookedQuantity.setQuantity(newQuantity);

			return commerceInventoryBookedQuantityPersistence.update(
				commerceInventoryBookedQuantity);
		}

		return commerceInventoryBookedQuantityPersistence.remove(
			commerceBookedQuantityId);
	}

	@Override
	public int getCommerceBookedQuantity(String sku) {
		List<CommerceInventoryBookedQuantity>
			commerceInventoryBookedQuantities =
				commerceInventoryBookedQuantityPersistence.findBysku(sku);

		int resultQuantity = 0;

		for (CommerceInventoryBookedQuantity commerceInventoryBookedQuantity :
				commerceInventoryBookedQuantities) {

			resultQuantity += commerceInventoryBookedQuantity.getQuantity();
		}

		return resultQuantity;
	}

	@Override
	public void removeAllCommerceBookedQuantities() {
		commerceInventoryBookedQuantityPersistence.removeAll();
	}

	@Override
	public void removeOldTemporaryBookedQuantities() {
		List<CommerceInventoryBookedQuantity> oldTempBookedQuantities =
			commerceInventoryBookedQuantityFinder.findOldTempBookedQuantities();

		for (CommerceInventoryBookedQuantity commerceInventoryBookedQuantity :
				oldTempBookedQuantities) {

			commerceInventoryBookedQuantityPersistence.remove(
				commerceInventoryBookedQuantity);
		}
	}

}