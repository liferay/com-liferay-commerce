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
import com.liferay.commerce.model.CPDefinitionInventory;
import com.liferay.commerce.service.base.CPDefinitionInventoryServiceBaseImpl;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.security.permission.resource.PortletResourcePermission;
import com.liferay.portal.kernel.security.permission.resource.PortletResourcePermissionFactory;

/**
 * @author Alessio Antonio Rendina
 */
public class CPDefinitionInventoryServiceImpl
	extends CPDefinitionInventoryServiceBaseImpl {

	@Override
	public CPDefinitionInventory addCPDefinitionInventory(
			long groupId, long cpDefinitionId,
			String cpDefinitionInventoryEngine, String lowStockActivity,
			boolean displayAvailability, boolean displayStockQuantity,
			int minStockQuantity, boolean backOrders, int minOrderQuantity,
			int maxOrderQuantity, String allowedOrderQuantities,
			int multipleOrderQuantity)
		throws PortalException {

		_portletResourcePermission.check(
			getPermissionChecker(), groupId,
			CommerceActionKeys.MANAGE_COMMERCE_PRODUCT_DEFINITION_INVENTORY);

		return cpDefinitionInventoryLocalService.addCPDefinitionInventory(
			groupId, cpDefinitionId, cpDefinitionInventoryEngine,
			lowStockActivity, displayAvailability, displayStockQuantity,
			minStockQuantity, backOrders, minOrderQuantity, maxOrderQuantity,
			allowedOrderQuantities, multipleOrderQuantity);
	}

	@Override
	public void deleteCPDefinitionInventory(long cpDefinitionInventoryId)
		throws PortalException {

		CPDefinitionInventory cpDefinitionInventory =
			cpDefinitionInventoryPersistence.findByPrimaryKey(
				cpDefinitionInventoryId);

		_portletResourcePermission.check(
			getPermissionChecker(), cpDefinitionInventory.getGroupId(),
			CommerceActionKeys.MANAGE_COMMERCE_PRODUCT_DEFINITION_INVENTORY);

		cpDefinitionInventoryLocalService.deleteCPDefinitionInventory(
			cpDefinitionInventoryId);
	}

	@Override
	public CPDefinitionInventory fetchCPDefinitionInventoryByCPDefinitionId(
			long cpDefinitionId)
		throws PortalException {

		CPDefinitionInventory cpDefinitionInventory =
			cpDefinitionInventoryLocalService.
				fetchCPDefinitionInventoryByCPDefinitionId(cpDefinitionId);

		if (cpDefinitionInventory != null) {
			_portletResourcePermission.check(
				getPermissionChecker(), cpDefinitionInventory.getGroupId(),
				CommerceActionKeys.
					MANAGE_COMMERCE_PRODUCT_DEFINITION_INVENTORY);
		}

		return cpDefinitionInventory;
	}

	@Override
	public CPDefinitionInventory updateCPDefinitionInventory(
			long groupId, long cpDefinitionInventoryId,
			String cpDefinitionInventoryEngine, String lowStockActivity,
			boolean displayAvailability, boolean displayStockQuantity,
			int minStockQuantity, boolean backOrders, int minOrderQuantity,
			int maxOrderQuantity, String allowedOrderQuantities,
			int multipleOrderQuantity)
		throws PortalException {

		_portletResourcePermission.check(
			getPermissionChecker(), groupId,
			CommerceActionKeys.MANAGE_COMMERCE_PRODUCT_DEFINITION_INVENTORY);

		return cpDefinitionInventoryLocalService.updateCPDefinitionInventory(
			cpDefinitionInventoryId, cpDefinitionInventoryEngine,
			lowStockActivity, displayAvailability, displayStockQuantity,
			minStockQuantity, backOrders, minOrderQuantity, maxOrderQuantity,
			allowedOrderQuantities, multipleOrderQuantity);
	}

	private static volatile PortletResourcePermission
		_portletResourcePermission =
			PortletResourcePermissionFactory.getInstance(
				CPDefinitionInventoryServiceImpl.class,
				"_portletResourcePermission", CommerceConstants.RESOURCE_NAME);

}