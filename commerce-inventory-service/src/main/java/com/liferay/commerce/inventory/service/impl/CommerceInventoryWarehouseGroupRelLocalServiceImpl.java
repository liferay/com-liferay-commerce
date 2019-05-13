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

package com.liferay.commerce.inventory.service.impl;

import com.liferay.commerce.inventory.model.CommerceInventoryWarehouseGroupRel;
import com.liferay.commerce.inventory.service.base.CommerceInventoryWarehouseGroupRelLocalServiceBaseImpl;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.service.ServiceContext;

import java.util.List;

/**
 * @author Luca Pellizzon
 */
public class CommerceInventoryWarehouseGroupRelLocalServiceImpl
	extends CommerceInventoryWarehouseGroupRelLocalServiceBaseImpl {

	@Override
	public CommerceInventoryWarehouseGroupRel addCommerceWarehouseGroupRel(
			long commerceWarehouseId, boolean primary,
			ServiceContext serviceContext)
		throws PortalException {

		User user = userLocalService.getUser(serviceContext.getUserId());

		long commerceWarehouseGroupRelId = counterLocalService.increment();

		CommerceInventoryWarehouseGroupRel commerceWarehouseGroupRel =
			commerceInventoryWarehouseGroupRelPersistence.create(
				commerceWarehouseGroupRelId);

		commerceWarehouseGroupRel.setGroupId(serviceContext.getScopeGroupId());
		commerceWarehouseGroupRel.setCompanyId(user.getCompanyId());
		commerceWarehouseGroupRel.setUserId(user.getUserId());
		commerceWarehouseGroupRel.setUserName(user.getFullName());
		commerceWarehouseGroupRel.setCommerceWarehouseId(commerceWarehouseId);
		commerceWarehouseGroupRel.setPrimary(primary);

		return commerceInventoryWarehouseGroupRelPersistence.update(
			commerceWarehouseGroupRel);
	}

	@Override
	public List<CommerceInventoryWarehouseGroupRel>
		getCommerceWarehouseGroupRels(long groupId) {

		return commerceInventoryWarehouseGroupRelPersistence.findBygroupId(
			groupId);
	}

	@Override
	public long getPrimaryCommerceWarehouseId(long groupId) {
		List<CommerceInventoryWarehouseGroupRel> groupRelPersistence =
			commerceInventoryWarehouseGroupRelPersistence.findByG_P(
				groupId, true);

		if (groupRelPersistence.isEmpty()) {
			return 0;
		}

		CommerceInventoryWarehouseGroupRel commerceWarehouseGroupRel =
			groupRelPersistence.get(0);

		return commerceWarehouseGroupRel.getCommerceWarehouseId();
	}

}