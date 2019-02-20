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

package com.liferay.commerce.headless.admin.site.setting.internal.resource.util.v1_0;

import com.liferay.commerce.exception.NoSuchWarehouseException;
import com.liferay.commerce.headless.admin.site.setting.internal.v1_0.DTOUtils;
import com.liferay.commerce.headless.admin.site.setting.model.v1_0.WarehouseDTO;
import com.liferay.commerce.model.CommerceWarehouse;
import com.liferay.commerce.openapi.core.context.Pagination;
import com.liferay.commerce.openapi.core.model.CollectionDTO;
import com.liferay.commerce.openapi.core.util.ServiceContextHelper;
import com.liferay.commerce.service.CommerceWarehouseService;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.GetterUtil;

import java.util.ArrayList;
import java.util.List;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Alessio Antonio Rendina
 */
@Component(immediate = true, service = WarehouseHelper.class)
public class WarehouseHelper {

	public void deleteWarehouse(String id) throws PortalException {
		_commerceWarehouseService.deleteCommerceWarehouse(
			GetterUtil.getLong(id));
	}

	public WarehouseDTO getWarehouseDTO(String id) throws PortalException {
		CommerceWarehouse commerceWarehouse =
			_commerceWarehouseService.getCommerceWarehouse(
				GetterUtil.getLong(id));

		return DTOUtils.modelToDTO(commerceWarehouse);
	}

	public CollectionDTO<WarehouseDTO> getWarehouseDTOs(
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

		List<WarehouseDTO> availabilityEstimateDTOs = new ArrayList<>();

		for (CommerceWarehouse commerceWarehouse : commerceWarehouses) {
			availabilityEstimateDTOs.add(
				DTOUtils.modelToDTO(commerceWarehouse));
		}

		return new CollectionDTO<>(availabilityEstimateDTOs, count);
	}

	public CommerceWarehouse updateWarehouse(
			String id, WarehouseDTO warehouseDTO, User user)
		throws PortalException {

		CommerceWarehouse commerceWarehouse =
			_commerceWarehouseService.getCommerceWarehouse(
				GetterUtil.getLong(id));

		ServiceContext serviceContext = _serviceContextHelper.getServiceContext(
			commerceWarehouse.getGroupId(), new long[0], user, true);

		return _commerceWarehouseService.updateCommerceWarehouse(
			commerceWarehouse.getCommerceWarehouseId(), warehouseDTO.getName(),
			GetterUtil.get(
				warehouseDTO.getDescription(),
				commerceWarehouse.getDescription()),
			GetterUtil.get(
				warehouseDTO.isActive(), commerceWarehouse.isActive()),
			GetterUtil.get(
				warehouseDTO.getStreet1(), commerceWarehouse.getStreet1()),
			GetterUtil.get(
				warehouseDTO.getStreet2(), commerceWarehouse.getStreet2()),
			GetterUtil.get(
				warehouseDTO.getStreet3(), commerceWarehouse.getStreet3()),
			GetterUtil.get(warehouseDTO.getCity(), commerceWarehouse.getCity()),
			GetterUtil.get(warehouseDTO.getZip(), commerceWarehouse.getZip()),
			GetterUtil.get(
				warehouseDTO.getCommerceRegionId(),
				commerceWarehouse.getCommerceRegionId()),
			warehouseDTO.getCommerceCountryId(),
			GetterUtil.get(
				warehouseDTO.getLatitude(), commerceWarehouse.getLatitude()),
			GetterUtil.get(
				warehouseDTO.getLongitude(), commerceWarehouse.getLongitude()),
			serviceContext);
	}

	public WarehouseDTO upsertWarehouse(
			Long groupId, WarehouseDTO warehouseDTO, User user)
		throws PortalException {

		try {
			CommerceWarehouse commerceWarehouse = updateWarehouse(
				String.valueOf(warehouseDTO.getId()), warehouseDTO, user);

			return DTOUtils.modelToDTO(commerceWarehouse);
		}
		catch (NoSuchWarehouseException nswe) {
			if (_log.isDebugEnabled()) {
				_log.debug(
					"Unable to find availabilityEstimate with ID: " +
						warehouseDTO.getId());
			}
		}

		ServiceContext serviceContext = _serviceContextHelper.getServiceContext(
			groupId, new long[0], user, true);

		CommerceWarehouse commerceWarehouse =
			_commerceWarehouseService.addCommerceWarehouse(
				warehouseDTO.getName(), warehouseDTO.getDescription(),
				GetterUtil.get(warehouseDTO.isActive(), false),
				warehouseDTO.getStreet1(), warehouseDTO.getStreet2(),
				warehouseDTO.getStreet3(), warehouseDTO.getCity(),
				warehouseDTO.getZip(),
				GetterUtil.get(warehouseDTO.getCommerceRegionId(), 0L),
				warehouseDTO.getCommerceCountryId(),
				GetterUtil.get(warehouseDTO.getLatitude(), 0D),
				GetterUtil.get(warehouseDTO.getLongitude(), 0D),
				serviceContext);

		return DTOUtils.modelToDTO(commerceWarehouse);
	}

	private static final Log _log = LogFactoryUtil.getLog(
		WarehouseHelper.class);

	@Reference
	private CommerceWarehouseService _commerceWarehouseService;

	@Reference
	private ServiceContextHelper _serviceContextHelper;

}