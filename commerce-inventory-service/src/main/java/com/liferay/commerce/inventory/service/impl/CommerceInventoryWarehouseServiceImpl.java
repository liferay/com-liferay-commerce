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

import com.liferay.commerce.inventory.constants.CommerceInventoryActionKeys;
import com.liferay.commerce.inventory.model.CommerceInventoryWarehouse;
import com.liferay.commerce.inventory.service.base.CommerceInventoryWarehouseServiceBaseImpl;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.security.permission.ActionKeys;
import com.liferay.portal.kernel.security.permission.resource.ModelResourcePermission;
import com.liferay.portal.kernel.security.permission.resource.ModelResourcePermissionFactory;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.service.permission.PortalPermissionUtil;

/**
 * @author Luca Pellizzon
 */
public class CommerceInventoryWarehouseServiceImpl
	extends CommerceInventoryWarehouseServiceBaseImpl {

	@Override
	public CommerceInventoryWarehouse addCommerceWarehouse(
			String name, String description, boolean active, String street1,
			String street2, String street3, String city, String zip,
			String commerceRegionCode, String commerceCountryCode,
			double latitude, double longitude, ServiceContext serviceContext)
		throws PortalException {

		PortalPermissionUtil.check(
			getPermissionChecker(), CommerceInventoryActionKeys.ADD_WAREHOUSE);

		return commerceInventoryWarehouseLocalService.addCommerceWarehouse(
			name, description, active, street1, street2, street3, city, zip,
			commerceRegionCode, commerceCountryCode, latitude, longitude,
			serviceContext);
	}

	@Override
	public CommerceInventoryWarehouse addCommerceWarehouseAndGroupRel(
			String name, String description, boolean active, String street1,
			String street2, String street3, String city, String zip,
			String commerceRegionCode, String commerceCountryCode,
			double latitude, double longitude, ServiceContext serviceContext)
		throws PortalException {

		PortalPermissionUtil.check(
			getPermissionChecker(), CommerceInventoryActionKeys.ADD_WAREHOUSE);

		CommerceInventoryWarehouse commerceWarehouse = addCommerceWarehouse(
			name, description, active, street1, street2, street3, city, zip,
			commerceRegionCode, commerceCountryCode, latitude, longitude,
			serviceContext);

		commerceInventoryWarehouseGroupRelLocalService.
			addCommerceWarehouseGroupRel(
				commerceWarehouse.getCommerceInventoryWarehouseId(), false,
				serviceContext);

		return commerceWarehouse;
	}

	@Override
	public CommerceInventoryWarehouse deleteCommerceWarehouse(
			long commerceWarehouseId)
		throws PortalException {

		_warehouseModelResourcePermission.check(
			getPermissionChecker(), commerceWarehouseId, ActionKeys.DELETE);

		return commerceInventoryWarehouseLocalService.
			deleteCommerceInventoryWarehouse(commerceWarehouseId);
	}

	@Override
	public CommerceInventoryWarehouse fetchDefaultCommerceWarehouse(
			long groupId)
		throws PortalException {

		CommerceInventoryWarehouse commerceWarehouse =
			commerceInventoryWarehouseLocalService.
				fetchDefaultCommerceWarehouse(groupId);

		if (commerceWarehouse != null) {
			_warehouseModelResourcePermission.check(
				getPermissionChecker(),
				commerceWarehouse.getCommerceInventoryWarehouseId(),
				ActionKeys.VIEW);
		}

		return commerceWarehouse;
	}

	@Override
	public CommerceInventoryWarehouse geolocateCommerceWarehouse(
			long commerceWarehouseId, double latitude, double longitude)
		throws PortalException {

		_warehouseModelResourcePermission.check(
			getPermissionChecker(), commerceWarehouseId, ActionKeys.UPDATE);

		return commerceInventoryWarehouseLocalService.
			geolocateCommerceWarehouse(
				commerceWarehouseId, latitude, longitude);
	}

	@Override
	public CommerceInventoryWarehouse getCommerceWarehouse(
			long commerceWarehouseId)
		throws PortalException {

		_warehouseModelResourcePermission.check(
			getPermissionChecker(), commerceWarehouseId, ActionKeys.UPDATE);

		return commerceInventoryWarehouseLocalService.
			getCommerceInventoryWarehouse(commerceWarehouseId);
	}

	@Override
	public CommerceInventoryWarehouse setActive(
			long commerceWarehouseId, boolean active)
		throws PortalException {

		_warehouseModelResourcePermission.check(
			getPermissionChecker(), commerceWarehouseId, ActionKeys.UPDATE);

		return commerceInventoryWarehouseLocalService.setActive(
			commerceWarehouseId, active);
	}

	@Override
	public CommerceInventoryWarehouse updateCommerceWarehouse(
			long commerceWarehouseId, String name, String description,
			boolean active, String street1, String street2, String street3,
			String city, String zip, String commerceRegionCode,
			String commerceCountryCode, double latitude, double longitude,
			ServiceContext serviceContext)
		throws PortalException {

		_warehouseModelResourcePermission.check(
			getPermissionChecker(), commerceWarehouseId, ActionKeys.UPDATE);

		return commerceInventoryWarehouseLocalService.updateCommerceWarehouse(
			commerceWarehouseId, name, description, active, street1, street2,
			street3, city, zip, commerceRegionCode, commerceCountryCode,
			latitude, longitude, serviceContext);
	}

	@Override
	public CommerceInventoryWarehouse updateDefaultCommerceWarehouse(
			String name, String street1, String street2, String street3,
			String city, String zip, String commerceRegionCode,
			String commerceCountryCode, double latitude, double longitude,
			ServiceContext serviceContext)
		throws PortalException {

		CommerceInventoryWarehouse defaultCommerceWarehouse =
			commerceInventoryWarehouseLocalService.getDefaultCommerceWarehouse(
				serviceContext.getScopeGroupId());

		_warehouseModelResourcePermission.check(
			getPermissionChecker(),
			defaultCommerceWarehouse.getCommerceInventoryWarehouseId(),
			ActionKeys.UPDATE);

		return commerceInventoryWarehouseLocalService.updateCommerceWarehouse(
			defaultCommerceWarehouse.getCommerceInventoryWarehouseId(), name,
			defaultCommerceWarehouse.getDescription(),
			defaultCommerceWarehouse.isActive(), street1, street2, street3,
			city, zip, commerceRegionCode, commerceCountryCode, latitude,
			longitude, serviceContext);
	}

	private static volatile ModelResourcePermission<CommerceInventoryWarehouse>
		_warehouseModelResourcePermission =
			ModelResourcePermissionFactory.getInstance(
				CommerceInventoryWarehouseServiceImpl.class,
				"_warehouseModelResourcePermission",
				CommerceInventoryWarehouse.class);

}