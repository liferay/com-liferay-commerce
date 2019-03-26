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

package com.liferay.commerce.service.impl;

import com.liferay.commerce.constants.CommerceActionKeys;
import com.liferay.commerce.constants.CommerceConstants;
import com.liferay.commerce.model.CommerceWarehouse;
import com.liferay.commerce.service.base.CommerceWarehouseServiceBaseImpl;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.security.permission.resource.PortletResourcePermission;
import com.liferay.portal.kernel.security.permission.resource.PortletResourcePermissionFactory;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.OrderByComparator;

import java.util.List;

/**
 * @author Andrea Di Giorgi
 */
public class CommerceWarehouseServiceImpl
	extends CommerceWarehouseServiceBaseImpl {

	@Override
	public CommerceWarehouse addCommerceWarehouse(
			String name, String description, boolean active, String street1,
			String street2, String street3, String city, String zip,
			long commerceRegionId, long commerceCountryId, double latitude,
			double longitude, ServiceContext serviceContext)
		throws PortalException {

		_portletResourcePermission.check(
			getPermissionChecker(), serviceContext.getScopeGroupId(),
			CommerceActionKeys.MANAGE_COMMERCE_WAREHOUSES);

		return commerceWarehouseLocalService.addCommerceWarehouse(
			name, description, active, street1, street2, street3, city, zip,
			commerceRegionId, commerceCountryId, latitude, longitude,
			serviceContext);
	}

	@Override
	public void deleteCommerceWarehouse(long commerceWarehouseId)
		throws PortalException {

		CommerceWarehouse commerceWarehouse =
			commerceWarehouseLocalService.getCommerceWarehouse(
				commerceWarehouseId);

		_portletResourcePermission.check(
			getPermissionChecker(), commerceWarehouse.getGroupId(),
			CommerceActionKeys.MANAGE_COMMERCE_WAREHOUSES);

		commerceWarehouseLocalService.deleteCommerceWarehouse(
			commerceWarehouse);
	}

	@Override
	public CommerceWarehouse fetchDefaultCommerceWarehouse(long groupId)
		throws PortalException {

		_portletResourcePermission.check(
			getPermissionChecker(), groupId,
			CommerceActionKeys.MANAGE_COMMERCE_WAREHOUSES);

		return commerceWarehouseLocalService.fetchDefaultCommerceWarehouse(
			groupId);
	}

	@Override
	public CommerceWarehouse geolocateCommerceWarehouse(
			long commerceWarehouseId)
		throws PortalException {

		CommerceWarehouse commerceWarehouse =
			commerceWarehouseLocalService.getCommerceWarehouse(
				commerceWarehouseId);

		_portletResourcePermission.check(
			getPermissionChecker(), commerceWarehouse.getGroupId(),
			CommerceActionKeys.MANAGE_COMMERCE_WAREHOUSES);

		return commerceWarehouseLocalService.geolocateCommerceWarehouse(
			commerceWarehouse.getCommerceWarehouseId());
	}

	@Override
	public CommerceWarehouse getCommerceWarehouse(long commerceWarehouseId)
		throws PortalException {

		CommerceWarehouse commerceWarehouse =
			commerceWarehouseLocalService.getCommerceWarehouse(
				commerceWarehouseId);

		_portletResourcePermission.check(
			getPermissionChecker(), commerceWarehouse.getGroupId(),
			CommerceActionKeys.MANAGE_COMMERCE_WAREHOUSES);

		return commerceWarehouse;
	}

	@Override
	public List<CommerceWarehouse> getCommerceWarehouses(
			long groupId, boolean active, int start, int end,
			OrderByComparator<CommerceWarehouse> orderByComparator)
		throws PortalException {

		_portletResourcePermission.check(
			getPermissionChecker(), groupId,
			CommerceActionKeys.MANAGE_COMMERCE_WAREHOUSES);

		return commerceWarehouseLocalService.getCommerceWarehouses(
			groupId, active, start, end, orderByComparator);
	}

	@Override
	public List<CommerceWarehouse> getCommerceWarehouses(
			long groupId, boolean active, long commerceCountryId,
			boolean primary, int start, int end,
			OrderByComparator<CommerceWarehouse> orderByComparator)
		throws PortalException {

		_portletResourcePermission.check(
			getPermissionChecker(), groupId,
			CommerceActionKeys.MANAGE_COMMERCE_WAREHOUSES);

		return commerceWarehouseLocalService.getCommerceWarehouses(
			groupId, active, commerceCountryId, start, end, orderByComparator);
	}

	@Override
	public List<CommerceWarehouse> getCommerceWarehouses(
			long groupId, boolean active, long commerceCountryId, int start,
			int end, OrderByComparator<CommerceWarehouse> orderByComparator)
		throws PortalException {

		_portletResourcePermission.check(
			getPermissionChecker(), groupId,
			CommerceActionKeys.MANAGE_COMMERCE_WAREHOUSES);

		return commerceWarehouseLocalService.getCommerceWarehouses(
			groupId, active, commerceCountryId, start, end, orderByComparator);
	}

	@Override
	public List<CommerceWarehouse> getCommerceWarehouses(
			long groupId, int start, int end,
			OrderByComparator<CommerceWarehouse> orderByComparator)
		throws PortalException {

		_portletResourcePermission.check(
			getPermissionChecker(), groupId,
			CommerceActionKeys.MANAGE_COMMERCE_WAREHOUSES);

		return commerceWarehouseLocalService.getCommerceWarehouses(
			groupId, start, end, orderByComparator);
	}

	@Override
	public List<CommerceWarehouse> getCommerceWarehouses(
			long groupId, long commerceCountryId, int start, int end,
			OrderByComparator<CommerceWarehouse> orderByComparator)
		throws PortalException {

		_portletResourcePermission.check(
			getPermissionChecker(), groupId,
			CommerceActionKeys.MANAGE_COMMERCE_WAREHOUSES);

		return commerceWarehouseLocalService.getCommerceWarehouses(
			groupId, commerceCountryId, start, end, orderByComparator);
	}

	@Override
	public int getCommerceWarehousesCount(long groupId) throws PortalException {
		_portletResourcePermission.check(
			getPermissionChecker(), groupId,
			CommerceActionKeys.MANAGE_COMMERCE_WAREHOUSES);

		return commerceWarehouseLocalService.getCommerceWarehousesCount(
			groupId);
	}

	@Override
	public int getCommerceWarehousesCount(long groupId, boolean active)
		throws PortalException {

		_portletResourcePermission.check(
			getPermissionChecker(), groupId,
			CommerceActionKeys.MANAGE_COMMERCE_WAREHOUSES);

		return commerceWarehouseLocalService.getCommerceWarehousesCount(
			groupId, active);
	}

	@Override
	public int getCommerceWarehousesCount(
			long groupId, boolean active, long commerceCountryId)
		throws PortalException {

		_portletResourcePermission.check(
			getPermissionChecker(), groupId,
			CommerceActionKeys.MANAGE_COMMERCE_WAREHOUSES);

		return commerceWarehouseLocalService.getCommerceWarehousesCount(
			groupId, active, commerceCountryId);
	}

	@Override
	public int getCommerceWarehousesCount(long groupId, long commerceCountryId)
		throws PortalException {

		_portletResourcePermission.check(
			getPermissionChecker(), groupId,
			CommerceActionKeys.MANAGE_COMMERCE_WAREHOUSES);

		return commerceWarehouseLocalService.getCommerceWarehousesCount(
			groupId, commerceCountryId);
	}

	@Override
	public List<CommerceWarehouse> search(
			long groupId, String keywords, boolean all, long commerceCountryId,
			int start, int end,
			OrderByComparator<CommerceWarehouse> orderByComparator)
		throws PortalException {

		_portletResourcePermission.check(
			getPermissionChecker(), groupId,
			CommerceActionKeys.MANAGE_COMMERCE_WAREHOUSES);

		return commerceWarehouseLocalService.search(
			groupId, keywords, all, commerceCountryId, start, end,
			orderByComparator);
	}

	@Override
	public int searchCount(
			long groupId, String keywords, Boolean active,
			long commerceCountryId)
		throws PortalException {

		_portletResourcePermission.check(
			getPermissionChecker(), groupId,
			CommerceActionKeys.MANAGE_COMMERCE_WAREHOUSES);

		return commerceWarehouseLocalService.searchCount(
			groupId, keywords, active, commerceCountryId);
	}

	@Override
	public CommerceWarehouse setActive(long commerceWarehouseId, boolean active)
		throws PortalException {

		CommerceWarehouse commerceWarehouse =
			commerceWarehouseLocalService.fetchCommerceWarehouse(
				commerceWarehouseId);

		if (commerceWarehouse != null) {
			_portletResourcePermission.check(
				getPermissionChecker(), commerceWarehouse.getGroupId(),
				CommerceActionKeys.MANAGE_COMMERCE_WAREHOUSES);
		}

		return commerceWarehouseLocalService.setActive(
			commerceWarehouseId, active);
	}

	@Override
	public CommerceWarehouse updateCommerceWarehouse(
			long commerceWarehouseId, String name, String description,
			boolean active, String street1, String street2, String street3,
			String city, String zip, long commerceRegionId,
			long commerceCountryId, double latitude, double longitude,
			ServiceContext serviceContext)
		throws PortalException {

		_portletResourcePermission.check(
			getPermissionChecker(), serviceContext.getScopeGroupId(),
			CommerceActionKeys.MANAGE_COMMERCE_WAREHOUSES);

		return commerceWarehouseLocalService.updateCommerceWarehouse(
			commerceWarehouseId, name, description, active, street1, street2,
			street3, city, zip, commerceRegionId, commerceCountryId, latitude,
			longitude, serviceContext);
	}

	@Override
	public CommerceWarehouse updateDefaultCommerceWarehouse(
			String name, String street1, String street2, String street3,
			String city, String zip, long commerceRegionId,
			long commerceCountryId, double latitude, double longitude,
			ServiceContext serviceContext)
		throws PortalException {

		_portletResourcePermission.check(
			getPermissionChecker(), serviceContext.getScopeGroupId(),
			CommerceActionKeys.MANAGE_COMMERCE_WAREHOUSES);

		return commerceWarehouseLocalService.updateDefaultCommerceWarehouse(
			name, street1, street2, street3, city, zip, commerceRegionId,
			commerceCountryId, latitude, longitude, serviceContext);
	}

	private static volatile PortletResourcePermission
		_portletResourcePermission =
			PortletResourcePermissionFactory.getInstance(
				CommerceWarehouseServiceImpl.class,
				"_portletResourcePermission", CommerceConstants.RESOURCE_NAME);

}