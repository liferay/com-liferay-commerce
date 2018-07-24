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

package com.liferay.commerce.initializer.util;

import com.liferay.commerce.model.CommerceCountry;
import com.liferay.commerce.model.CommerceRegion;
import com.liferay.commerce.model.CommerceWarehouse;
import com.liferay.commerce.service.CommerceCountryLocalService;
import com.liferay.commerce.service.CommerceRegionLocalService;
import com.liferay.commerce.service.CommerceWarehouseLocalService;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.service.ServiceContext;

import java.util.ArrayList;
import java.util.List;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Andrea Di Giorgi
 */
@Component(service = CommerceWarehousesImporter.class)
public class CommerceWarehousesImporter {

	public List<CommerceWarehouse> importCommerceWarehouses(
			JSONArray jsonArray, ServiceContext serviceContext)
		throws PortalException {

		List<CommerceWarehouse> commerceWarehouses = new ArrayList<>(
			jsonArray.length());

		for (int i = 0; i < jsonArray.length(); i++) {
			JSONObject jsonObject = jsonArray.getJSONObject(i);

			CommerceWarehouse commerceWarehouse = _importCommerceWarehouse(
				jsonObject, serviceContext);

			commerceWarehouses.add(commerceWarehouse);
		}

		return commerceWarehouses;
	}

	public CommerceWarehouse importDefaultCommerceWarehouse(
			ServiceContext serviceContext)
		throws PortalException {

		return _commerceWarehouseLocalService.importDefaultCommerceWarehouse(
			serviceContext);
	}

	private CommerceWarehouse _importCommerceWarehouse(
			JSONObject jsonObject, ServiceContext serviceContext)
		throws PortalException {

		int countryNumericISOCode = jsonObject.getInt("Country");

		CommerceCountry commerceCountry =
			_commerceCountryLocalService.fetchCommerceCountry(
				serviceContext.getScopeGroupId(), countryNumericISOCode);

		String regionCode = jsonObject.getString("Region");

		CommerceRegion commerceRegion =
			_commerceRegionLocalService.getCommerceRegion(
				commerceCountry.getCommerceCountryId(), regionCode);

		String name = jsonObject.getString("Name");
		String description = jsonObject.getString("Description");
		boolean active = jsonObject.getBoolean("Active", true);
		String street1 = jsonObject.getString("Street1");
		String street2 = jsonObject.getString("Street2");
		String street3 = jsonObject.getString("Street3");
		String city = jsonObject.getString("City");
		String zip = jsonObject.getString("Zip");
		double latitude = jsonObject.getDouble("Latitude");
		double longitude = jsonObject.getDouble("Longitude");

		return _commerceWarehouseLocalService.addCommerceWarehouse(
			name, description, active, street1, street2, street3, city, zip,
			commerceRegion.getCommerceRegionId(),
			commerceRegion.getCommerceCountryId(), latitude, longitude,
			serviceContext);
	}

	@Reference
	private CommerceCountryLocalService _commerceCountryLocalService;

	@Reference
	private CommerceRegionLocalService _commerceRegionLocalService;

	@Reference
	private CommerceWarehouseLocalService _commerceWarehouseLocalService;

}