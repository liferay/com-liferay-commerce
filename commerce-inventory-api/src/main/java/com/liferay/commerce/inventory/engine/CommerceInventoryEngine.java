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
 * @author Alessio Antonio Rendina
 */
@ProviderType
public interface CommerceInventoryEngine {

	public void consumeQuantity(
			long userId, long commerceInventoryWarehouseId, String sku,
			int quantity, long bookedQuantityId, Map<String, String> context)
		throws PortalException;

	public void decreaseStockQuantity(
			long commerceInventoryWarehouseId, String sku, int quantity)
		throws PortalException;

	public Map<String, Integer> getStockQuantities(
			long companyId, long channelGroupId, List<String> skus)
		throws PortalException;

	public int getStockQuantity(long companyId, long channelGroupId, String sku)
		throws PortalException;

	public int getStockQuantity(long companyId, String sku)
		throws PortalException;

	public void increaseStockQuantity(
			long userId, long commerceInventoryWarehouseId, String sku,
			int quantity)
		throws PortalException;

}