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

package com.liferay.commerce.inventory.engine;

import aQute.bnd.annotation.ProviderType;

import com.liferay.portal.kernel.exception.PortalException;

import java.util.List;
import java.util.Map;

/**
 * @author Luca Pellizzon
 */
@ProviderType
public interface CommerceInventoryEngine {

	/**
	 * @param userId
	 * @param groupId
	 * @param sku
	 * @param quantity
	 * @param warehouseId
	 * @param bookedQuantityId
	 * @param context
	 * @throws PortalException
	 */
	public void consumeQuantity(
			long userId, long groupId, String sku, int quantity,
			long warehouseId, long bookedQuantityId,
			Map<String, String> context)
		throws PortalException;

	/**
	 * @param groupId
	 * @param sku
	 * @param quantity
	 * @param warehouseId
	 * @throws PortalException
	 */
	public void decreaseStockQuantity(
			long groupId, String sku, int quantity, long warehouseId)
		throws PortalException;

	/**
	 * @param companyId
	 * @param groupId
	 * @param skus
	 * @return Map<sku, quantity> of a list of skus
	 * @throws PortalException
	 */
	public Map<String, Integer> getStockQuantities(
			long companyId, long groupId, List<String> skus)
		throws PortalException;

	/**
	 * @param companyId
	 * @param groupId
	 * @param sku
	 * @return stock quantity for the given sku
	 * @throws PortalException
	 */
	public int getStockQuantity(long companyId, long groupId, String sku)
		throws PortalException;

	/**
	 * @param groupId
	 * @param sku
	 * @param quantity
	 * @param warehouseId
	 * @throws PortalException
	 */
	public void increaseStockQuantity(
			long groupId, String sku, int quantity, long warehouseId)
		throws PortalException;

}