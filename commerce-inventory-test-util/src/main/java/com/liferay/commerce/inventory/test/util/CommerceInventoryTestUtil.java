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

package com.liferay.commerce.inventory.test.util;

import com.liferay.commerce.inventory.model.CommerceInventoryWarehouse;
import com.liferay.commerce.inventory.model.CommerceInventoryWarehouseItem;
import com.liferay.commerce.inventory.service.CommerceInventoryWarehouseGroupRelLocalServiceUtil;
import com.liferay.commerce.inventory.service.CommerceInventoryWarehouseItemLocalServiceUtil;
import com.liferay.commerce.inventory.service.CommerceInventoryWarehouseLocalServiceUtil;
import com.liferay.commerce.model.CommerceCountry;
import com.liferay.commerce.model.CommerceRegion;
import com.liferay.commerce.service.CommerceCountryLocalServiceUtil;
import com.liferay.commerce.service.CommerceRegionLocalServiceUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.test.util.RandomTestUtil;

/**
 * @author Luca Pellizzon
 */
public class CommerceInventoryTestUtil {

	public static CommerceCountry addCommerceCountry(
			ServiceContext serviceContext)
		throws Exception {

		return CommerceCountryLocalServiceUtil.addCommerceCountry(
			RandomTestUtil.randomLocaleStringMap(), true, true,
			RandomTestUtil.randomString(2), RandomTestUtil.randomString(3),
			RandomTestUtil.nextInt(), false, 0, true, serviceContext);
	}

	public static CommerceRegion addCommerceRegion(
			long commerceCountryId, ServiceContext serviceContext)
		throws PortalException {

		return CommerceRegionLocalServiceUtil.addCommerceRegion(
			commerceCountryId, RandomTestUtil.randomString(),
			RandomTestUtil.randomString(), 0, true, serviceContext);
	}

	public static CommerceInventoryWarehouse addCommerceWarehouseAndGroupRel(
			ServiceContext serviceContext)
		throws Exception {

		CommerceCountry commerceCountry = addCommerceCountry(serviceContext);

		CommerceRegion commerceRegion = addCommerceRegion(
			commerceCountry.getCommerceCountryId(), serviceContext);

		CommerceInventoryWarehouse commerceWarehouse =
			CommerceInventoryWarehouseLocalServiceUtil.addCommerceWarehouse(
				RandomTestUtil.randomString(), RandomTestUtil.randomString(),
				true, RandomTestUtil.randomString(),
				RandomTestUtil.randomString(), RandomTestUtil.randomString(),
				RandomTestUtil.randomString(), RandomTestUtil.randomString(),
				commerceRegion.getCode(),
				commerceCountry.getTwoLettersISOCode(),
				RandomTestUtil.nextDouble(), RandomTestUtil.nextDouble(),
				serviceContext);

		CommerceInventoryWarehouseGroupRelLocalServiceUtil.
			addCommerceWarehouseGroupRel(
				commerceWarehouse.getCommerceInventoryWarehouseId(), false,
				serviceContext);

		return commerceWarehouse;
	}

	public static CommerceInventoryWarehouseItem addCommerceWarehouseItem(
			String sku, int quantity, ServiceContext serviceContext)
		throws Exception {

		CommerceInventoryWarehouse commerceWarehouse =
			addCommerceWarehouseAndGroupRel(serviceContext);

		return CommerceInventoryWarehouseItemLocalServiceUtil.
			addCommerceWarehouseItem(
				commerceWarehouse.getCommerceInventoryWarehouseId(), sku,
				quantity, serviceContext.getUserId());
	}

}