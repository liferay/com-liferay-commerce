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

import com.liferay.commerce.inventory.exception.CommerceInventoryInvalidDateException;
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
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.vulcan.fields.NestedField;
import com.liferay.portal.vulcan.pagination.Page;
import com.liferay.portal.vulcan.pagination.Pagination;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.validation.constraints.NotNull;

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
			Date end, Date start, Pagination pagination)
		throws Exception {

		if ((start != null) && (end != null) && (start.compareTo(end) > 0)) {
			throw new CommerceInventoryInvalidDateException(
				"End date should be after start date");
		}

		if ((start == null) && (end == null)) {
			throw new CommerceInventoryInvalidDateException(
				"At least one date should be defined");
		}

		if (start == null) {
			start = _addDaysToDate(end, -_DEFAULT_INCREMENT_DAYS);
		}

		if (end == null) {
			end = _addDaysToDate(start, _DEFAULT_INCREMENT_DAYS);
		}

		List<CommerceInventoryWarehouseItem> commerceInventoryWarehouseItems =
			_commerceInventoryWarehouseItemService.
				getCommerceInventoryWarehouseItemsCountByModifiedDate(
					contextCompany.getCompanyId(), start, end,
					pagination.getStartPosition(), pagination.getEndPosition());

		int totalItems =
			_commerceInventoryWarehouseItemService.
				getCommerceInventoryWarehouseItemsCountByModifiedDate(
					contextCompany.getCompanyId(), start, end);

		return Page.of(
			_toWarehouseItems(commerceInventoryWarehouseItems), pagination,
			totalItems);
	}

	@NestedField("items")
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

		CommerceInventoryWarehouseItem commerceInventoryWarehouseItem = null;

		if (warehouseItem.getExternalReferenceCode() != null) {
			commerceInventoryWarehouseItem =
				_commerceInventoryWarehouseItemService.
					upsertCommerceInventoryWarehouseItem(
						_user.getCompanyId(), _user.getUserId(),
						commerceInventoryWarehouse.
							getCommerceInventoryWarehouseId(),
						warehouseItem.getExternalReferenceCode(),
						warehouseItem.getSku(), warehouseItem.getQuantity());
		}
		else {
			commerceInventoryWarehouseItem =
				_commerceInventoryWarehouseItemService.
					upsertCommerceInventoryWarehouseItem(
						_user.getUserId(),
						commerceInventoryWarehouse.
							getCommerceInventoryWarehouseId(),
						warehouseItem.getSku(), warehouseItem.getQuantity());
		}

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
	public WarehouseItem postWarehouseItemByExternalReferenceCode(
			@NotNull String externalReferenceCode, WarehouseItem warehouseItem)
		throws Exception {

		CommerceInventoryWarehouse commerceInventoryWarehouse = null;

		if (warehouseItem.getWarehouseId() != null) {
			commerceInventoryWarehouse =
				_commerceInventoryWarehouseService.
					getCommerceInventoryWarehouse(
						warehouseItem.getWarehouseId());
		}
		else if (warehouseItem.getWarehouseExternalReferenceCode() != null) {
			commerceInventoryWarehouse =
				_commerceInventoryWarehouseService.fetchByExternalReferenceCode(
					_user.getCompanyId(),
					warehouseItem.getWarehouseExternalReferenceCode());
		}

		if (commerceInventoryWarehouse == null) {
			throw new NoSuchInventoryWarehouseException(
				"Unable to find Warehouse");
		}

		CommerceInventoryWarehouseItem commerceInventoryWarehouseItem =
			_commerceInventoryWarehouseItemService.
				addCommerceInventoryWarehouseItem(
					_user.getUserId(),
					commerceInventoryWarehouse.
						getCommerceInventoryWarehouseId(),
					externalReferenceCode, warehouseItem.getSku(),
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

	private Date _addDaysToDate(Date date, int increment) {
		Calendar cal = Calendar.getInstance();

		cal.setTime(date);

		cal.add(Calendar.DATE, increment);

		return cal.getTime();
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

	private static final int _DEFAULT_INCREMENT_DAYS = 30;

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