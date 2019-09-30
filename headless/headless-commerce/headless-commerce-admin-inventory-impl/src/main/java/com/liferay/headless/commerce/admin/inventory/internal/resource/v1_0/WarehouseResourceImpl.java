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

package com.liferay.headless.commerce.admin.inventory.internal.resource.v1_0;

import com.liferay.commerce.inventory.exception.NoSuchInventoryWarehouseException;
import com.liferay.commerce.inventory.model.CommerceInventoryWarehouse;
import com.liferay.commerce.inventory.service.CommerceInventoryWarehouseItemService;
import com.liferay.commerce.inventory.service.CommerceInventoryWarehouseService;
import com.liferay.headless.commerce.admin.inventory.dto.v1_0.Warehouse;
import com.liferay.headless.commerce.admin.inventory.dto.v1_0.WarehouseItem;
import com.liferay.headless.commerce.admin.inventory.resource.v1_0.WarehouseResource;
import com.liferay.headless.commerce.core.dto.v1_0.converter.DTOConverter;
import com.liferay.headless.commerce.core.dto.v1_0.converter.DTOConverterRegistry;
import com.liferay.headless.commerce.core.dto.v1_0.converter.DefaultDTOConverterContext;
import com.liferay.headless.commerce.core.util.ServiceContextHelper;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.vulcan.pagination.Page;
import com.liferay.portal.vulcan.pagination.Pagination;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.osgi.service.component.annotations.ServiceScope;

/**
 * @author Alessio Antonio Rendina
 */
@Component(
	properties = "OSGI-INF/liferay/rest/v1_0/warehouse.properties",
	scope = ServiceScope.PROTOTYPE, service = WarehouseResource.class
)
public class WarehouseResourceImpl extends BaseWarehouseResourceImpl {

	@Override
	public Response deleteWarehousByExternalReferenceCode(
			String externalReferenceCode)
		throws Exception {

		CommerceInventoryWarehouse commerceInventoryWarehouse =
			_commerceInventoryWarehouseService.fetchByExternalReferenceCode(
				contextCompany.getCompanyId(), externalReferenceCode);

		if (commerceInventoryWarehouse == null) {
			throw new NoSuchInventoryWarehouseException(
				"Unable to find Warehouse with externalReferenceCode: " +
					externalReferenceCode);
		}

		_commerceInventoryWarehouseService.deleteCommerceInventoryWarehouse(
			commerceInventoryWarehouse.getCommerceInventoryWarehouseId());

		Response.ResponseBuilder responseBuilder = Response.ok();

		return responseBuilder.build();
	}

	@Override
	public Response deleteWarehousId(Long id) throws Exception {
		_commerceInventoryWarehouseService.deleteCommerceInventoryWarehouse(id);

		Response.ResponseBuilder responseBuilder = Response.ok();

		return responseBuilder.build();
	}

	@Override
	public Warehouse getWarehousByExternalReferenceCode(
			String externalReferenceCode)
		throws Exception {

		CommerceInventoryWarehouse commerceInventoryWarehouse =
			_commerceInventoryWarehouseService.fetchByExternalReferenceCode(
				contextCompany.getCompanyId(), externalReferenceCode);

		if (commerceInventoryWarehouse == null) {
			throw new NoSuchInventoryWarehouseException(
				"Unable to find Warehouse with externalReferenceCode: " +
					externalReferenceCode);
		}

		DTOConverter warehouseDTOConverter =
			_dtoConverterRegistry.getDTOConverter(
				CommerceInventoryWarehouse.class.getName());

		return (Warehouse)warehouseDTOConverter.toDTO(
			new DefaultDTOConverterContext(
				contextAcceptLanguage.getPreferredLocale(),
				commerceInventoryWarehouse.getCommerceInventoryWarehouseId()));
	}

	@Override
	public Page<Warehouse> getWarehousesPage(Pagination pagination)
		throws Exception {

		List<CommerceInventoryWarehouse> commerceInventoryWarehouses =
			_commerceInventoryWarehouseService.getCommerceInventoryWarehouses(
				contextCompany.getCompanyId(), pagination.getStartPosition(),
				pagination.getEndPosition(), null);

		int totalItems =
			_commerceInventoryWarehouseService.
				getCommerceInventoryWarehousesCount(
					contextCompany.getCompanyId());

		return Page.of(
			_toWarehouses(commerceInventoryWarehouses), pagination, totalItems);
	}

	@Override
	public Warehouse getWarehousId(Long id) throws Exception {
		DTOConverter warehouseDTOConverter =
			_dtoConverterRegistry.getDTOConverter(
				CommerceInventoryWarehouse.class.getName());

		return (Warehouse)warehouseDTOConverter.toDTO(
			new DefaultDTOConverterContext(
				contextAcceptLanguage.getPreferredLocale(),
				GetterUtil.getLong(id)));
	}

	@Override
	public Response patchWarehousByExternalReferenceCode(
			String externalReferenceCode, Warehouse warehouse)
		throws Exception {

		CommerceInventoryWarehouse commerceInventoryWarehouse =
			_commerceInventoryWarehouseService.fetchByExternalReferenceCode(
				contextCompany.getCompanyId(), externalReferenceCode);

		if (commerceInventoryWarehouse == null) {
			throw new NoSuchInventoryWarehouseException(
				"Unable to find Warehouse with externalReferenceCode: " +
					externalReferenceCode);
		}

		_updateWarehouse(commerceInventoryWarehouse, warehouse);

		Response.ResponseBuilder responseBuilder = Response.ok();

		return responseBuilder.build();
	}

	@Override
	public Response patchWarehousId(Long id, Warehouse warehouse)
		throws Exception {

		_updateWarehouse(
			_commerceInventoryWarehouseService.getCommerceInventoryWarehouse(
				id),
			warehouse);

		Response.ResponseBuilder responseBuilder = Response.noContent();

		return responseBuilder.build();
	}

	@Override
	public Warehouse postWarehous(Warehouse warehouse) throws Exception {
		CommerceInventoryWarehouse commerceInventoryWarehouse =
			_commerceInventoryWarehouseService.fetchByExternalReferenceCode(
				contextCompany.getCompanyId(),
				warehouse.getExternalReferenceCode());

		if (commerceInventoryWarehouse == null) {
			commerceInventoryWarehouse =
				_commerceInventoryWarehouseService.
					addCommerceInventoryWarehouse(
						warehouse.getName(), warehouse.getDescription(),
						GetterUtil.get(warehouse.getActive(), true),
						warehouse.getStreet1(), warehouse.getStreet2(),
						warehouse.getStreet3(), warehouse.getCity(),
						warehouse.getZip(), warehouse.getRegionISOCode(),
						warehouse.getCountryISOCode(),
						GetterUtil.get(warehouse.getLatitude(), 0D),
						GetterUtil.get(warehouse.getLongitude(), 0D),
						warehouse.getExternalReferenceCode(),
						_serviceContextHelper.getServiceContext());
		}
		else {
			commerceInventoryWarehouse = _updateWarehouse(
				commerceInventoryWarehouse, warehouse);
		}

		// Update nested resources

		_updateNestedResources(warehouse, commerceInventoryWarehouse);

		DTOConverter warehouseDTOConverter =
			_dtoConverterRegistry.getDTOConverter(
				CommerceInventoryWarehouse.class.getName());

		return (Warehouse)warehouseDTOConverter.toDTO(
			new DefaultDTOConverterContext(
				contextAcceptLanguage.getPreferredLocale(),
				commerceInventoryWarehouse.getCommerceInventoryWarehouseId()));
	}

	private List<Warehouse> _toWarehouses(
			List<CommerceInventoryWarehouse> commerceInventoryWarehouses)
		throws Exception {

		List<Warehouse> warehouses = new ArrayList<>();

		DTOConverter warehouseDTOConverter =
			_dtoConverterRegistry.getDTOConverter(
				CommerceInventoryWarehouse.class.getName());

		for (CommerceInventoryWarehouse commerceInventoryWarehouse :
				commerceInventoryWarehouses) {

			warehouses.add(
				(Warehouse)warehouseDTOConverter.toDTO(
					new DefaultDTOConverterContext(
						contextAcceptLanguage.getPreferredLocale(),
						commerceInventoryWarehouse.
							getCommerceInventoryWarehouseId())));
		}

		return warehouses;
	}

	private void _updateNestedResources(
			Warehouse warehouse,
			CommerceInventoryWarehouse commerceInventoryWarehouse)
		throws PortalException {

		WarehouseItem[] warehouseItems = warehouse.getItems();

		if (warehouseItems != null) {
			for (WarehouseItem warehouseItem : warehouseItems) {
				_commerceInventoryWarehouseItemService.
					upsertCommerceInventoryWarehouseItem(
						_user.getUserId(),
						commerceInventoryWarehouse.
							getCommerceInventoryWarehouseId(),
						warehouseItem.getSku(), warehouseItem.getQuantity());
			}
		}
	}

	private CommerceInventoryWarehouse _updateWarehouse(
			CommerceInventoryWarehouse commerceInventoryWarehouse,
			Warehouse warehouse)
		throws Exception {

		commerceInventoryWarehouse =
			_commerceInventoryWarehouseService.updateCommerceInventoryWarehouse(
				commerceInventoryWarehouse.getCommerceInventoryWarehouseId(),
				GetterUtil.get(
					warehouse.getName(), commerceInventoryWarehouse.getName()),
				GetterUtil.get(
					warehouse.getDescription(),
					commerceInventoryWarehouse.getDescription()),
				GetterUtil.get(
					warehouse.getActive(),
					commerceInventoryWarehouse.isActive()),
				GetterUtil.get(
					warehouse.getStreet1(),
					commerceInventoryWarehouse.getStreet1()),
				GetterUtil.get(
					warehouse.getStreet2(),
					commerceInventoryWarehouse.getStreet2()),
				GetterUtil.get(
					warehouse.getStreet3(),
					commerceInventoryWarehouse.getStreet3()),
				GetterUtil.get(
					warehouse.getCity(), commerceInventoryWarehouse.getCity()),
				GetterUtil.get(
					warehouse.getZip(), commerceInventoryWarehouse.getZip()),
				GetterUtil.get(
					warehouse.getRegionISOCode(),
					commerceInventoryWarehouse.getCommerceRegionCode()),
				GetterUtil.get(
					warehouse.getCountryISOCode(),
					commerceInventoryWarehouse.getCountryTwoLettersISOCode()),
				GetterUtil.get(
					warehouse.getLatitude(),
					commerceInventoryWarehouse.getLatitude()),
				GetterUtil.get(
					warehouse.getLongitude(),
					commerceInventoryWarehouse.getLongitude()),
				_serviceContextHelper.getServiceContext());

		// Update nested resources

		_updateNestedResources(warehouse, commerceInventoryWarehouse);

		return commerceInventoryWarehouse;
	}

	@Reference
	private CommerceInventoryWarehouseItemService
		_commerceInventoryWarehouseItemService;

	@Reference
	private CommerceInventoryWarehouseService
		_commerceInventoryWarehouseService;

	@Reference
	private DTOConverterRegistry _dtoConverterRegistry;

	@Reference
	private ServiceContextHelper _serviceContextHelper;

	@Context
	private User _user;

}