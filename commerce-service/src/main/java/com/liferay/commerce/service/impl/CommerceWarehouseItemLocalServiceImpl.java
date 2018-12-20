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

	/**
	 * @deprecated As of Judson (7.1.x)
	 */
	@Deprecated
	@Override
	public CommerceWarehouseItem addCommerceWarehouseItem(
			long commerceWarehouseId, long cpInstanceId, int quantity,
			ServiceContext serviceContext)
		throws PortalException {

		CPInstance cpInstance = _cpInstanceLocalService.getCPInstance(
			cpInstanceId);

		CPDefinition cpDefinition = _cpDefinitionLocalService.getCPDefinition(
			cpInstance.getCPDefinitionId());

		return commerceWarehouseItemLocalService.addCommerceWarehouseItem(
			commerceWarehouseId, cpDefinition.getCProductId(),
			cpInstance.getCPInstanceUuid(), quantity, serviceContext);
	}

	@Override
	public CommerceWarehouseItem addCommerceWarehouseItem(
			long commerceWarehouseId, long cProductId, String cpInstanceUuid,
			int quantity, ServiceContext serviceContext)
		throws PortalException {

		CommerceWarehouse commerceWarehouse =
			commerceWarehouseLocalService.getCommerceWarehouse(
				commerceWarehouseId);
		User user = userLocalService.getUser(serviceContext.getUserId());

		long commerceWarehouseItemId = counterLocalService.increment();

		CommerceWarehouseItem commerceWarehouseItem =
			commerceWarehouseItemPersistence.create(commerceWarehouseItemId);

		commerceWarehouseItem.setGroupId(commerceWarehouse.getGroupId());
		commerceWarehouseItem.setCompanyId(user.getCompanyId());
		commerceWarehouseItem.setUserId(user.getUserId());
		commerceWarehouseItem.setUserName(user.getFullName());
		commerceWarehouseItem.setCommerceWarehouseId(commerceWarehouseId);
		commerceWarehouseItem.setCProductId(cProductId);
		commerceWarehouseItem.setCPInstanceUuid(cpInstanceUuid);
		commerceWarehouseItem.setQuantity(quantity);

		commerceWarehouseItemPersistence.update(commerceWarehouseItem);

		return commerceWarehouseItem;
	}

	@Override
	public void deleteCommerceWarehouseItems(long commerceWarehouseId) {
		commerceWarehouseItemPersistence.removeByCommerceWarehouseId(
			commerceWarehouseId);
	}

	@Override
	public void deleteCommerceWarehouseItemsByCPI_CPIU(
			long cProductId, String cpInstanceUuid)
		throws PortalException {

		commerceWarehouseItemPersistence.removeByCPI_CPIU(
			cProductId, cpInstanceUuid);
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

		commerceWarehouseItemLocalService.
			deleteCommerceWarehouseItemsByCPI_CPIU(
				cpDefinition.getCProductId(), cpInstance.getCPInstanceUuid());
	}

	@Override
	public void deleteCommerceWarehouseItemsByCWI_CPIU(
			long commerceWarehouseId, String cpInstanceUuid)
		throws PortalException {

		commerceWarehouseItemPersistence.removeByCWI_CPIU(
			commerceWarehouseId, cpInstanceUuid);
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

		return commerceWarehouseItemLocalService.
			fetchCommerceWarehouseItemByCWI_CPIU(
				commerceWarehouseId, cpInstance.getCPInstanceUuid());
	}

	@Override
	public CommerceWarehouseItem fetchCommerceWarehouseItemByCWI_CPIU(
			long commerceWarehouseId, String cpInstanceUuid)
		throws PortalException {

		return commerceWarehouseItemPersistence.fetchByCWI_CPIU(
			commerceWarehouseId, cpInstanceUuid);
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

		CPDefinition cpDefinition = _cpDefinitionLocalService.fetchCPDefinition(
			cpInstance.getCPDefinitionId());

		if (cpDefinition != null) {
			return commerceWarehouseItemLocalService.
				getCommerceWarehouseItemsByCPI_CPIU(
					cpDefinition.getCProductId(),
					cpInstance.getCPInstanceUuid());
		}

		return null;
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

		return commerceWarehouseItemLocalService.
			getCommerceWarehouseItemsByCPI_CPIU(
				cpDefinition.getCProductId(), cpInstance.getCPInstanceUuid(),
				start, end, orderByComparator);
	}

	@Override
	public List<CommerceWarehouseItem>
		getCommerceWarehouseItemsByCommerceWarehouseId(
			long commerceWarehouseId) {

		return commerceWarehouseItemPersistence.findByCommerceWarehouseId(
			commerceWarehouseId);
	}

	@Override
	public List<CommerceWarehouseItem> getCommerceWarehouseItemsByCPI_CPIU(
			long cProductId, String cpInstanceUuid)
		throws PortalException {

		return commerceWarehouseItemPersistence.findByCPI_CPIU(
			cProductId, cpInstanceUuid);
	}

	@Override
	public List<CommerceWarehouseItem> getCommerceWarehouseItemsByCPI_CPIU(
			long cProductId, String cpInstanceUuid, int start, int end,
			OrderByComparator<CommerceWarehouseItem> orderByComparator)
		throws PortalException {

		return commerceWarehouseItemPersistence.findByCPI_CPIU(
			cProductId, cpInstanceUuid, start, end);
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

		return commerceWarehouseItemLocalService.getCommerceWarehouseItemsCount(
			cpDefinition.getCProductId(), cpInstance.getCPInstanceUuid());
	}

	/**
	 * @deprecated As of Mueller (7.2.x)
	 */
	@Deprecated
	@Override
	public int getCommerceWarehouseItemsCount(
			long cProductId, String cpInstanceUuid)
		throws PortalException {

		return commerceWarehouseItemPersistence.countByCPI_CPIU(
			cProductId, cpInstanceUuid);
	}

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