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

package com.liferay.commerce.openapi.admin.internal.resource.util;

import com.liferay.commerce.model.CommerceWarehouseItem;
import com.liferay.commerce.openapi.admin.internal.util.DTOUtils;
import com.liferay.commerce.openapi.admin.model.CollectionDTO;
import com.liferay.commerce.openapi.admin.model.InventoryDTO;
import com.liferay.commerce.openapi.core.context.Pagination;
import com.liferay.commerce.product.model.CPInstance;
import com.liferay.commerce.service.CommerceWarehouseItemService;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.model.Company;
import com.liferay.portal.kernel.util.GetterUtil;

import java.util.ArrayList;
import java.util.List;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Ivica Cardic
 */
@Component(immediate = true, service = InventoryHelper.class)
public class InventoryHelper {

	public void deleteInventory(String id) throws PortalException {
		_commerceWarehouseItemService.deleteCommerceWarehouseItem(
			GetterUtil.getLong(id));
	}

	public CollectionDTO<InventoryDTO> getInventories(
			String skuId, Company company, Pagination pagination)
		throws PortalException {

		CPInstance cpInstance = _skuHelper.getCPInstanceById(skuId, company);

		List<CommerceWarehouseItem> commerceWarehouseItems =
			_commerceWarehouseItemService.getCommerceWarehouseItems(
				cpInstance.getCPInstanceId(), pagination.getStartPosition(),
				pagination.getEndPosition(), null);

		int totalItems =
			_commerceWarehouseItemService.getCommerceWarehouseItemsCount(
				cpInstance.getCPInstanceId());

		List<InventoryDTO> inventoryDTOs = new ArrayList<>();

		for (CommerceWarehouseItem commerceWarehouseItem :
				commerceWarehouseItems) {

			inventoryDTOs.add(DTOUtils.modelToDTO(commerceWarehouseItem));
		}

		return new CollectionDTO<>(inventoryDTOs, totalItems);
	}

	public InventoryDTO getInventory(String id) throws PortalException {
		CommerceWarehouseItem commerceWarehouseItem =
			_commerceWarehouseItemService.getCommerceWarehouseItem(
				GetterUtil.getLong(id));

		return DTOUtils.modelToDTO(commerceWarehouseItem);
	}

	public InventoryDTO updateInventory(
			String id, long groupId, InventoryDTO inventoryDTO)
		throws PortalException {

		CommerceWarehouseItem commerceWarehouseItem =
			_commerceWarehouseItemService.updateCommerceWarehouseItem(
				GetterUtil.getLong(id), inventoryDTO.getQuantity(),
				_serviceContextHelper.getServiceContext(groupId));

		return DTOUtils.modelToDTO(commerceWarehouseItem);
	}

	public InventoryDTO upsertInventory(
			String skuId, long groupId, InventoryDTO inventoryDTO,
			Company company)
		throws PortalException {

		CPInstance cpInstance = _skuHelper.getCPInstanceById(skuId, company);

		CommerceWarehouseItem commerceWarehouseItem =
			_commerceWarehouseItemService.fetchCommerceWarehouseItem(
				inventoryDTO.getWarehouseId(), cpInstance.getCPInstanceId());

		if (commerceWarehouseItem == null) {
			commerceWarehouseItem =
				_commerceWarehouseItemService.addCommerceWarehouseItem(
					inventoryDTO.getWarehouseId(), cpInstance.getCPInstanceId(),
					inventoryDTO.getQuantity(),
					_serviceContextHelper.getServiceContext(groupId));
		}
		else {
			commerceWarehouseItem =
				_commerceWarehouseItemService.updateCommerceWarehouseItem(
					commerceWarehouseItem.getCommerceWarehouseItemId(),
					inventoryDTO.getQuantity(),
					_serviceContextHelper.getServiceContext(groupId));
		}

		return DTOUtils.modelToDTO(commerceWarehouseItem);
	}

	@Reference
	private CommerceWarehouseItemService _commerceWarehouseItemService;

	@Reference
	private ServiceContextHelper _serviceContextHelper;

	@Reference
	private SKUHelper _skuHelper;

}