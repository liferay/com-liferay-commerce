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
import com.liferay.commerce.product.model.CPDefinition;
import com.liferay.commerce.product.service.CPDefinitionLocalService;
import com.liferay.commerce.service.base.CPDefinitionInventoryServiceBaseImpl;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.security.permission.resource.PortletResourcePermission;
import com.liferay.portal.kernel.security.permission.resource.PortletResourcePermissionFactory;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.spring.extender.service.ServiceReference;

/**
 * @author Alessio Antonio Rendina
 */
public class CPDefinitionInventoryServiceImpl
	extends CPDefinitionInventoryServiceBaseImpl {

	@Override
	public CPDefinitionInventory addCPDefinitionInventory(
			long cpDefinitionId, String cpDefinitionInventoryEngine,
			String lowStockActivity, boolean displayAvailability,
			boolean displayStockQuantity, int minStockQuantity,
			boolean backOrders, int minOrderQuantity, int maxOrderQuantity,
			String allowedOrderQuantities, int multipleOrderQuantity)
		throws PortalException {

		CPDefinition cpDefinition = _cpDefinitionLocalService.getCPDefinition(
			cpDefinitionId);

		_portletResourcePermission.check(
			getPermissionChecker(), cpDefinition.getGroupId(),
			CommerceActionKeys.MANAGE_COMMERCE_PRODUCT_DEFINITION_INVENTORY);

		return cpDefinitionInventoryLocalService.addCPDefinitionInventory(
			cpDefinitionId, cpDefinitionInventoryEngine, lowStockActivity,
			displayAvailability, displayStockQuantity, minStockQuantity,
			backOrders, minOrderQuantity, maxOrderQuantity,
			allowedOrderQuantities, multipleOrderQuantity);
	}

	/**
	 * Adds new CP definition inventory.
	 *
	 * @param      cpDefinitionId
	 * @param      cpDefinitionInventoryEngine
	 * @param      lowStockActivity
	 * @param      displayAvailability
	 * @param      displayStockQuantity
	 * @param      minStockQuantity
	 * @param      backOrders
	 * @param      minOrderQuantity
	 * @param      maxOrderQuantity
	 * @param      allowedOrderQuantities
	 * @param      multipleOrderQuantity
	 * @param      serviceContext
	 * @throws     PortalException
	 * @deprecated As of Mueller (7.2.x), see {@link
	 *             #addCPDefinitionInventory(long, String, String, boolean,
	 *             boolean, int, boolean, int, int, String, int)}
	 */
	@Deprecated
	@Override
	public CPDefinitionInventory addCPDefinitionInventory(
			long cpDefinitionId, String cpDefinitionInventoryEngine,
			String lowStockActivity, boolean displayAvailability,
			boolean displayStockQuantity, int minStockQuantity,
			boolean backOrders, int minOrderQuantity, int maxOrderQuantity,
			String allowedOrderQuantities, int multipleOrderQuantity,
			ServiceContext serviceContext)
		throws PortalException {

		_portletResourcePermission.check(
			getPermissionChecker(), serviceContext.getScopeGroupId(),
			CommerceActionKeys.MANAGE_COMMERCE_PRODUCT_DEFINITION_INVENTORY);

		return cpDefinitionInventoryService.addCPDefinitionInventory(
			cpDefinitionId, cpDefinitionInventoryEngine, lowStockActivity,
			displayAvailability, displayStockQuantity, minStockQuantity,
			backOrders, minOrderQuantity, maxOrderQuantity,
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

	/**
	 * Updates CP definition inventory
	 *
	 * @param      cpDefinitionInventoryId
	 * @param      cpDefinitionInventoryEngine
	 * @param      lowStockActivity
	 * @param      displayAvailability
	 * @param      displayStockQuantity
	 * @param      minStockQuantity
	 * @param      backOrders
	 * @param      minOrderQuantity
	 * @param      maxOrderQuantity
	 * @param      allowedOrderQuantities
	 * @param      multipleOrderQuantity
	 * @param      serviceContext
	 * @throws     PortalException
	 * @deprecated As of Mueller (7.2.x), see {@link
	 *             #updateCPDefinitionInventory(long, long, String, String,
	 *             boolean, boolean, int, boolean, int, int, String, int)}
	 */
	@Deprecated
	@Override
	public CPDefinitionInventory updateCPDefinitionInventory(
			long cpDefinitionInventoryId, String cpDefinitionInventoryEngine,
			String lowStockActivity, boolean displayAvailability,
			boolean displayStockQuantity, int minStockQuantity,
			boolean backOrders, int minOrderQuantity, int maxOrderQuantity,
			String allowedOrderQuantities, int multipleOrderQuantity,
			ServiceContext serviceContext)
		throws PortalException {

		return cpDefinitionInventoryService.updateCPDefinitionInventory(
			serviceContext.getScopeGroupId(), cpDefinitionInventoryId,
			cpDefinitionInventoryEngine, lowStockActivity, displayAvailability,
			displayStockQuantity, minStockQuantity, backOrders,
			minOrderQuantity, maxOrderQuantity, allowedOrderQuantities,
			multipleOrderQuantity);
	}

	private static volatile PortletResourcePermission
		_portletResourcePermission =
			PortletResourcePermissionFactory.getInstance(
				CPDefinitionInventoryServiceImpl.class,
				"_portletResourcePermission", CommerceConstants.RESOURCE_NAME);

	@ServiceReference(type = CPDefinitionLocalService.class)
	private CPDefinitionLocalService _cpDefinitionLocalService;

}