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

package com.liferay.headless.commerce.admin.site.setting.internal.util.v1_0;

import com.liferay.commerce.exception.NoSuchWarehouseException;
import com.liferay.commerce.model.CommerceWarehouse;
import com.liferay.commerce.service.CommerceWarehouseService;
import com.liferay.headless.commerce.admin.site.setting.dto.v1_0.Warehouse;
import com.liferay.headless.commerce.admin.site.setting.internal.mapper.v1_0.DTOMapper;
import com.liferay.headless.commerce.core.util.ServiceContextHelper;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.vulcan.pagination.Page;
import com.liferay.portal.vulcan.pagination.Pagination;

import java.util.ArrayList;
import java.util.List;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Alessio Antonio Rendina
 * @author Zoltán Takács
 */
@Component(immediate = true, service = WarehouseHelper.class)
public class WarehouseHelper {

	public void deleteWarehouse(Long id) throws PortalException {
		_commerceWarehouseService.deleteCommerceWarehouse(id);
	}

	public Warehouse getWarehouse(Long id) throws PortalException {
		CommerceWarehouse commerceWarehouse =
			_commerceWarehouseService.getCommerceWarehouse(id);

		return _dtoMapper.modelToDTO(commerceWarehouse);
	}

	public Page<Warehouse> getWarehouses(
			Long groupId, Boolean active, Pagination pagination)
		throws PortalException {

		List<CommerceWarehouse> commerceWarehouses;
		int count;

		if (active == null) {
			commerceWarehouses =
				_commerceWarehouseService.getCommerceWarehouses(
					groupId, pagination.getStartPosition(),
					pagination.getEndPosition(), null);

			count = _commerceWarehouseService.getCommerceWarehousesCount(
				groupId);
		}
		else {
			commerceWarehouses =
				_commerceWarehouseService.getCommerceWarehouses(
					groupId, active, pagination.getStartPosition(),
					pagination.getEndPosition(), null);

			count = _commerceWarehouseService.getCommerceWarehousesCount(
				groupId, active);
		}

		List<Warehouse> availabilityEstimates = new ArrayList<>();

		for (CommerceWarehouse commerceWarehouse : commerceWarehouses) {
			availabilityEstimates.add(_dtoMapper.modelToDTO(commerceWarehouse));
		}

		return Page.of(availabilityEstimates, pagination, count);
	}

	public CommerceWarehouse updateWarehouse(
			Long id, Warehouse warehouse, User user)
		throws PortalException {

		CommerceWarehouse commerceWarehouse =
			_commerceWarehouseService.getCommerceWarehouse(id);

		ServiceContext serviceContext = _serviceContextHelper.getServiceContext(
			commerceWarehouse.getGroupId(), new long[0], user, true);

		return _commerceWarehouseService.updateCommerceWarehouse(
			commerceWarehouse.getCommerceWarehouseId(), warehouse.getName(),
			GetterUtil.get(
				warehouse.getDescription(), commerceWarehouse.getDescription()),
			GetterUtil.get(warehouse.getActive(), commerceWarehouse.isActive()),
			GetterUtil.get(
				warehouse.getStreet1(), commerceWarehouse.getStreet1()),
			GetterUtil.get(
				warehouse.getStreet2(), commerceWarehouse.getStreet2()),
			GetterUtil.get(
				warehouse.getStreet3(), commerceWarehouse.getStreet3()),
			GetterUtil.get(warehouse.getCity(), commerceWarehouse.getCity()),
			GetterUtil.get(warehouse.getZip(), commerceWarehouse.getZip()),
			GetterUtil.get(
				warehouse.getCommerceRegionId(),
				commerceWarehouse.getCommerceRegionId()),
			warehouse.getCommerceCountryId(),
			GetterUtil.get(
				warehouse.getLatitude(), commerceWarehouse.getLatitude()),
			GetterUtil.get(
				warehouse.getLongitude(), commerceWarehouse.getLongitude()),
			serviceContext);
	}

	public Warehouse upsertWarehouse(
			Long groupId, Warehouse warehouse, User user)
		throws PortalException {

		try {
			CommerceWarehouse commerceWarehouse = updateWarehouse(
				warehouse.getId(), warehouse, user);

			return _dtoMapper.modelToDTO(commerceWarehouse);
		}
		catch (NoSuchWarehouseException nswe) {
			if (_log.isDebugEnabled()) {
				_log.debug(
					"Unable to find availabilityEstimate with ID: " +
						warehouse.getId());
			}
		}

		ServiceContext serviceContext = _serviceContextHelper.getServiceContext(
			groupId, new long[0], user, true);

		CommerceWarehouse commerceWarehouse =
			_commerceWarehouseService.addCommerceWarehouse(
				warehouse.getName(), warehouse.getDescription(),
				GetterUtil.get(warehouse.getActive(), false),
				warehouse.getStreet1(), warehouse.getStreet2(),
				warehouse.getStreet3(), warehouse.getCity(), warehouse.getZip(),
				GetterUtil.get(warehouse.getCommerceRegionId(), 0L),
				warehouse.getCommerceCountryId(),
				GetterUtil.get(warehouse.getLatitude(), 0D),
				GetterUtil.get(warehouse.getLongitude(), 0D), serviceContext);

		return _dtoMapper.modelToDTO(commerceWarehouse);
	}

	private static final Log _log = LogFactoryUtil.getLog(
		WarehouseHelper.class);

	@Reference
	private CommerceWarehouseService _commerceWarehouseService;

	@Reference
	private DTOMapper _dtoMapper;

	@Reference
	private ServiceContextHelper _serviceContextHelper;

}