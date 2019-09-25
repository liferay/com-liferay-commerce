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
import com.liferay.commerce.inventory.model.CommerceInventoryWarehouseItem;
import com.liferay.commerce.inventory.service.CommerceInventoryWarehouseItemService;
import com.liferay.commerce.inventory.service.CommerceInventoryWarehouseService;
import com.liferay.headless.commerce.admin.inventory.dto.v1_0.WarehouseItem;
import com.liferay.headless.commerce.admin.inventory.resource.v1_0.WarehouseItemResource;
import com.liferay.headless.commerce.core.dto.v1_0.converter.DTOConverter;
import com.liferay.headless.commerce.core.dto.v1_0.converter.DTOConverterRegistry;
import com.liferay.headless.commerce.core.dto.v1_0.converter.DefaultDTOConverterContext;
import com.liferay.headless.commerce.core.util.DateUtils;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.vulcan.pagination.Page;
import com.liferay.portal.vulcan.pagination.Pagination;

import java.util.ArrayList;
import java.util.Date;
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
	properties = "OSGI-INF/liferay/rest/v1_0/warehouse-item.properties",
	scope = ServiceScope.PROTOTYPE, service = WarehouseItemResource.class
)
public class WarehouseItemResourceImpl extends BaseWarehouseItemResourceImpl {

	@Override
	public Response deleteWarehouseItem(Long id) throws Exception {
		_commerceInventoryWarehouseItemService.
			deleteCommerceInventoryWarehouseItem(id);

		Response.ResponseBuilder responseBuilder = Response.ok();

		return responseBuilder.build();
	}

	@Override
	public Page<WarehouseItem>
			getWarehousByExternalReferenceCodeWarehouseItemsPage(
				String externalReferenceCode, Pagination pagination)
		throws Exception {

		CommerceInventoryWarehouse commerceInventoryWarehouse =
			_commerceInventoryWarehouseService.fetchByExternalReferenceCode(
				contextCompany.getCompanyId(), externalReferenceCode);

		if (commerceInventoryWarehouse == null) {
			throw new NoSuchInventoryWarehouseException(
				"Unable to find Warehouse with externalReferenceCode: " +
					externalReferenceCode);
		}

		List<CommerceInventoryWarehouseItem> commerceInventoryWarehouseItems =
			_commerceInventoryWarehouseItemService.
				getCommerceInventoryWarehouseItems(
					commerceInventoryWarehouse.
						getCommerceInventoryWarehouseId(),
					pagination.getStartPosition(), pagination.getEndPosition());

		int totalItems =
			_commerceInventoryWarehouseItemService.
				getCommerceInventoryWarehouseItemsCount(
					commerceInventoryWarehouse.
						getCommerceInventoryWarehouseId());

		return Page.of(
			_toWarehouseItems(commerceInventoryWarehouseItems), pagination,
			totalItems);
	}

	@Override
	public WarehouseItem getWarehouseItem(Long id) throws Exception {
		DTOConverter warehouseItemDTOConverter =
			_dtoConverterRegistry.getDTOConverter(
				CommerceInventoryWarehouseItem.class.getName());

		return (WarehouseItem)warehouseItemDTOConverter.toDTO(
			new DefaultDTOConverterContext(
				contextAcceptLanguage.getPreferredLocale(),
				GetterUtil.getLong(id)));
	}

	@Override
	public Page<WarehouseItem> getWarehouseItemsUpdatedPage(
			Date start, Date end, Pagination pagination)
		throws Exception {

		if ((start != null) && (end != null) && (start.compareTo(end) > 0)) {
			throw new IllegalArgumentException(
				"End date should be after start date");
		}

		if ((start == null) && (end == null)) {
			throw new IllegalArgumentException(
				"At least one date should be defined");
		}

		if (start == null) {
			start = DateUtils.addDays(end, -DateUtils.DEFAULT_INCREMENT_DAY);
		}

		if (end == null) {
			end = DateUtils.addDays(start, DateUtils.DEFAULT_INCREMENT_DAY);
		}

		List<CommerceInventoryWarehouseItem> commerceInventoryWarehouseItems =
			_commerceInventoryWarehouseItemService.findUpdatedItemsByM(
				contextCompany.getCompanyId(), start, end,
				pagination.getStartPosition(), pagination.getEndPosition());

		int totalItems =
			_commerceInventoryWarehouseItemService.countUpdatedItemsByM(
				contextCompany.getCompanyId(), start, end);

		return Page.of(
			_toWarehouseItems(commerceInventoryWarehouseItems), pagination,
			totalItems);
	}

	@Override
	public Page<WarehouseItem> getWarehousIdWarehouseItemsPage(
			Long id, Pagination pagination)
		throws Exception {

		List<CommerceInventoryWarehouseItem> commerceInventoryWarehouseItems =
			_commerceInventoryWarehouseItemService.
				getCommerceInventoryWarehouseItems(
					id, pagination.getStartPosition(),
					pagination.getEndPosition());

		int totalItems =
			_commerceInventoryWarehouseItemService.
				getCommerceInventoryWarehouseItemsCount(id);

		return Page.of(
			_toWarehouseItems(commerceInventoryWarehouseItems), pagination,
			totalItems);
	}

	@Override
	public Response patchWarehouseItem(Long id, WarehouseItem warehouseItem)
		throws Exception {

		_commerceInventoryWarehouseItemService.
			updateCommerceInventoryWarehouseItem(
				id, warehouseItem.getQuantity());

		Response.ResponseBuilder responseBuilder = Response.ok();

		return responseBuilder.build();
	}

	@Override
	public WarehouseItem postWarehousByExternalReferenceCodeWarehouseItem(
			String externalReferenceCode, WarehouseItem warehouseItem)
		throws Exception {

		CommerceInventoryWarehouse commerceInventoryWarehouse =
			_commerceInventoryWarehouseService.fetchByExternalReferenceCode(
				contextCompany.getCompanyId(), externalReferenceCode);

		if (commerceInventoryWarehouse == null) {
			throw new NoSuchInventoryWarehouseException(
				"Unable to find Warehouse with externalReferenceCode: " +
					externalReferenceCode);
		}

		CommerceInventoryWarehouseItem commerceInventoryWarehouseItem =
			_commerceInventoryWarehouseItemService.
				addCommerceInventoryWarehouseItem(
					_user.getUserId(),
					commerceInventoryWarehouse.
						getCommerceInventoryWarehouseId(),
					warehouseItem.getSku(), warehouseItem.getQuantity());

		DTOConverter warehouseItemDTOConverter =
			_dtoConverterRegistry.getDTOConverter(
				CommerceInventoryWarehouseItem.class.getName());

		return (WarehouseItem)warehouseItemDTOConverter.toDTO(
			new DefaultDTOConverterContext(
				contextAcceptLanguage.getPreferredLocale(),
				commerceInventoryWarehouseItem.
					getCommerceInventoryWarehouseItemId()));
	}

	@Override
	public WarehouseItem postWarehousIdWarehouseItem(
			Long id, WarehouseItem warehouseItem)
		throws Exception {

		CommerceInventoryWarehouseItem commerceInventoryWarehouseItem =
			_commerceInventoryWarehouseItemService.
				addCommerceInventoryWarehouseItem(
					_user.getUserId(), id, warehouseItem.getSku(),
					warehouseItem.getQuantity());

		DTOConverter warehouseItemDTOConverter =
			_dtoConverterRegistry.getDTOConverter(
				CommerceInventoryWarehouseItem.class.getName());

		return (WarehouseItem)warehouseItemDTOConverter.toDTO(
			new DefaultDTOConverterContext(
				contextAcceptLanguage.getPreferredLocale(),
				commerceInventoryWarehouseItem.
					getCommerceInventoryWarehouseItemId()));
	}

	private List<WarehouseItem> _toWarehouseItems(
			List<CommerceInventoryWarehouseItem>
				commerceInventoryWarehouseItems)
		throws Exception {

		List<WarehouseItem> warehouseItems = new ArrayList<>();

		DTOConverter warehouseItemDTOConverter =
			_dtoConverterRegistry.getDTOConverter(
				CommerceInventoryWarehouseItem.class.getName());

		for (CommerceInventoryWarehouseItem commerceInventoryWarehouseItem :
				commerceInventoryWarehouseItems) {

			warehouseItems.add(
				(WarehouseItem)warehouseItemDTOConverter.toDTO(
					new DefaultDTOConverterContext(
						contextAcceptLanguage.getPreferredLocale(),
						commerceInventoryWarehouseItem.
							getCommerceInventoryWarehouseItemId())));
		}

		return warehouseItems;
	}

	@Reference
	private CommerceInventoryWarehouseItemService
		_commerceInventoryWarehouseItemService;

	@Reference
	private CommerceInventoryWarehouseService
		_commerceInventoryWarehouseService;

	@Reference
	private DTOConverterRegistry _dtoConverterRegistry;

	@Context
	private User _user;

}