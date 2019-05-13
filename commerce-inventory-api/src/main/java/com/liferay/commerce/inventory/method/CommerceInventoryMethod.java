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

package com.liferay.commerce.inventory.method;

import aQute.bnd.annotation.ProviderType;

import com.liferay.portal.kernel.exception.PortalException;

import java.util.List;
import java.util.Map;

/**
 * @author Luca Pellizzon
 */
@ProviderType
public interface CommerceInventoryMethod {

	public void consumeQuantity(
			long groupId, String sku, int quantity, long warehouseId,
			long bookedQuantityId)
		throws PortalException;

	public void decreaseStockQuantity(
			String sku, int quantity, long warehouseId)
		throws PortalException;

	public Map<String, Integer> getStockQuantities(
			long companyId, long groupId, List<String> skus)
		throws PortalException;

	public int getStockQuantity(long companyId, long groupId, String sku)
		throws PortalException;

	public void increaseStockQuantity(
			String sku, int quantity, long warehouseId)
		throws PortalException;

}