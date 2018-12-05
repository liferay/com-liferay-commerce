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

import com.liferay.commerce.model.CommerceWarehouse;
import com.liferay.commerce.model.CommerceWarehouseItem;
import com.liferay.commerce.product.model.CPDefinition;
import com.liferay.commerce.product.model.CPInstance;
import com.liferay.commerce.product.service.CPDefinitionLocalService;
import com.liferay.commerce.product.service.CPInstanceLocalService;
import com.liferay.commerce.service.base.CommerceWarehouseItemLocalServiceBaseImpl;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.spring.extender.service.ServiceReference;

import java.util.List;

/**
 * @author Andrea Di Giorgi
 * @author Alessio Antonio Rendina
 * @author Alec Sloan
 */
public class CommerceWarehouseItemLocalServiceImpl
	extends CommerceWarehouseItemLocalServiceBaseImpl {

	@Override
	public CommerceWarehouseItem addCommerceWarehouseItem(
			long commerceWarehouseId, long cpInstanceId, int quantity,
			ServiceContext serviceContext)
		throws PortalException {

		CommerceWarehouse commerceWarehouse =
			commerceWarehouseLocalService.getCommerceWarehouse(
				commerceWarehouseId);
		User user = userLocalService.getUser(serviceContext.getUserId());

		long commerceWarehouseItemId = counterLocalService.increment();

		CommerceWarehouseItem commerceWarehouseItem =
			commerceWarehouseItemPersistence.create(commerceWarehouseItemId);

		CPInstance cpInstance = _cpInstanceLocalService.getCPInstance(
			cpInstanceId);

		CPDefinition cpDefinition = _cpDefinitionLocalService.getCPDefinition(
			cpInstance.getCPDefinitionId());

		commerceWarehouseItem.setGroupId(commerceWarehouse.getGroupId());
		commerceWarehouseItem.setCompanyId(user.getCompanyId());
		commerceWarehouseItem.setUserId(user.getUserId());
		commerceWarehouseItem.setUserName(user.getFullName());
		commerceWarehouseItem.setCommerceWarehouseId(commerceWarehouseId);
		commerceWarehouseItem.setQuantity(quantity);
		commerceWarehouseItem.setCProductId(cpDefinition.getCProductId());
		commerceWarehouseItem.setCPInstanceUuid(cpInstance.getUuid());

		commerceWarehouseItemPersistence.update(commerceWarehouseItem);

		return commerceWarehouseItem;
	}

	@Override
	public void deleteCommerceWarehouseItems(long commerceWarehouseId) {
		commerceWarehouseItemPersistence.removeByCommerceWarehouseId(
			commerceWarehouseId);
	}

	/**
	 * @deprecated As of Judson (7.1.x)
	 */
	@Deprecated
	@Override
	public void deleteCommerceWarehouseItemsByCPInstanceId(long cpInstanceId)
		throws PortalException {

		CPInstance cpInstance = _cpInstanceLocalService.getCPInstance(
			cpInstanceId);

		CPDefinition cpDefinition = _cpDefinitionLocalService.getCPDefinition(
			cpInstance.getCPDefinitionId());

		commerceWarehouseItemPersistence.removeByCPI_CPIU(
			cpDefinition.getCProductId(), cpInstance.getUuid());
	}

	/**
	 * @deprecated As of Judson (7.1.x)
	 */
	@Deprecated
	@Override
	public CommerceWarehouseItem fetchCommerceWarehouseItem(
			long commerceWarehouseId, long cpInstanceId)
		throws PortalException {

		CPInstance cpInstance = _cpInstanceLocalService.getCPInstance(
			cpInstanceId);

		return commerceWarehouseItemPersistence.fetchByCWI_CPIU(
			commerceWarehouseId, cpInstance.getUuid());
	}

	/**
	 * @deprecated As of Judson (7.1.x)
	 */
	@Deprecated
	@Override
	public List<CommerceWarehouseItem> getCommerceWarehouseItems(
			long cpInstanceId)
		throws PortalException {

		CPInstance cpInstance = _cpInstanceLocalService.getCPInstance(
			cpInstanceId);

		CPDefinition cpDefinition = _cpDefinitionLocalService.getCPDefinition(
			cpInstance.getCPDefinitionId());

		return commerceWarehouseItemPersistence.findByCPI_CPIU(
			cpDefinition.getCProductId(), cpInstance.getUuid());
	}

	/**
	 * @deprecated As of Judson (7.1.x)
	 */
	@Deprecated
	@Override
	public List<CommerceWarehouseItem> getCommerceWarehouseItems(
			long cpInstanceId, int start, int end,
			OrderByComparator<CommerceWarehouseItem> orderByComparator)
		throws PortalException {

		CPInstance cpInstance = _cpInstanceLocalService.getCPInstance(
			cpInstanceId);

		CPDefinition cpDefinition = _cpDefinitionLocalService.getCPDefinition(
			cpInstance.getCPDefinitionId());

		return commerceWarehouseItemPersistence.findByCPI_CPIU(
			cpDefinition.getCProductId(), cpInstance.getUuid(), start, end);
	}

	@Override
	public List<CommerceWarehouseItem>
		getCommerceWarehouseItemsByCommerceWarehouseId(
			long commerceWarehouseId) {

		return commerceWarehouseItemPersistence.findByCommerceWarehouseId(
			commerceWarehouseId);
	}

	/**
	 * @deprecated As of Judson (7.1.x)
	 */
	@Deprecated
	@Override
	public int getCommerceWarehouseItemsCount(long cpInstanceId)
		throws PortalException {

		CPInstance cpInstance = _cpInstanceLocalService.getCPInstance(
			cpInstanceId);

		CPDefinition cpDefinition = _cpDefinitionLocalService.getCPDefinition(
			cpInstance.getCPDefinitionId());

		return commerceWarehouseItemPersistence.countByCPI_CPIU(
			cpDefinition.getCProductId(), cpInstance.getUuid());
	}

	/**
	 * @deprecated As of Mueller (7.2.x)
	 */
	@Deprecated
	@Override
	public int getCPInstanceQuantity(long cpInstanceId) {
		int quantity = 0;

		List<CommerceWarehouseItem> commerceWarehouseItems =
			getCommerceWarehouseItems(cpInstanceId);

		for (CommerceWarehouseItem commerceWarehouseItem :
				commerceWarehouseItems) {

			quantity += commerceWarehouseItem.getQuantity();
		}

		return quantity;
	}

	@Override
	public CommerceWarehouseItem updateCommerceWarehouseItem(
			long commerceWarehouseItemId, int quantity,
			ServiceContext serviceContext)
		throws PortalException {

		CommerceWarehouseItem commerceWarehouseItem =
			commerceWarehouseItemPersistence.findByPrimaryKey(
				commerceWarehouseItemId);

		commerceWarehouseItem.setQuantity(quantity);

		commerceWarehouseItemPersistence.update(commerceWarehouseItem);

		return commerceWarehouseItem;
	}

	@ServiceReference(type = CPDefinitionLocalService.class)
	private CPDefinitionLocalService _cpDefinitionLocalService;

	@ServiceReference(type = CPInstanceLocalService.class)
	private CPInstanceLocalService _cpInstanceLocalService;

}