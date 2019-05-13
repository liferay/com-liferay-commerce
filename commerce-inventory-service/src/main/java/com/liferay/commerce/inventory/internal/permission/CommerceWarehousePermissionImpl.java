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

package com.liferay.commerce.inventory.internal.permission;

import com.liferay.commerce.inventory.model.CommerceInventoryWarehouse;
import com.liferay.commerce.inventory.permission.CommerceWarehousePermission;
import com.liferay.commerce.inventory.service.CommerceInventoryWarehouseLocalService;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.security.auth.PrincipalException;
import com.liferay.portal.kernel.security.permission.PermissionChecker;
import com.liferay.portal.kernel.util.ArrayUtil;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Luca Pellizzon
 */
@Component(immediate = true, service = CommerceWarehousePermission.class)
public class CommerceWarehousePermissionImpl
	implements CommerceWarehousePermission {

	@Override
	public void check(
			PermissionChecker permissionChecker,
			CommerceInventoryWarehouse commerceWarehouse, String actionId)
		throws PortalException {

		if (!contains(permissionChecker, commerceWarehouse, actionId)) {
			throw new PrincipalException.MustHavePermission(
				permissionChecker, CommerceInventoryWarehouse.class.getName(),
				commerceWarehouse.getCommerceInventoryWarehouseId(), actionId);
		}
	}

	@Override
	public void check(
			PermissionChecker permissionChecker, long commerceWarehouseId,
			String actionId)
		throws PortalException {

		if (!contains(permissionChecker, commerceWarehouseId, actionId)) {
			throw new PrincipalException.MustHavePermission(
				permissionChecker, CommerceInventoryWarehouse.class.getName(),
				commerceWarehouseId, actionId);
		}
	}

	@Override
	public boolean contains(
			PermissionChecker permissionChecker,
			CommerceInventoryWarehouse commerceWarehouse, String actionId)
		throws PortalException {

		if (contains(
				permissionChecker,
				commerceWarehouse.getCommerceInventoryWarehouseId(),
				actionId)) {

			return true;
		}

		return false;
	}

	@Override
	public boolean contains(
			PermissionChecker permissionChecker, long commerceWarehouseId,
			String actionId)
		throws PortalException {

		CommerceInventoryWarehouse commerceWarehouse =
			_commerceWarehouseLocalService.getCommerceInventoryWarehouse(
				commerceWarehouseId);

		if (commerceWarehouse == null) {
			return false;
		}

		return _contains(permissionChecker, commerceWarehouse, actionId);
	}

	@Override
	public boolean contains(
			PermissionChecker permissionChecker, long[] commerceAccountIds,
			String actionId)
		throws PortalException {

		if (ArrayUtil.isEmpty(commerceAccountIds)) {
			return false;
		}

		for (long commerceWarehouseId : commerceAccountIds) {
			if (!contains(permissionChecker, commerceWarehouseId, actionId)) {
				return false;
			}
		}

		return true;
	}

	private boolean _contains(
			PermissionChecker permissionChecker,
			CommerceInventoryWarehouse commerceWarehouse, String actionId)
		throws PortalException {

		if (permissionChecker.isCompanyAdmin(
				commerceWarehouse.getCompanyId()) ||
			permissionChecker.isOmniadmin()) {

			return true;
		}

		return permissionChecker.hasPermission(
			null, CommerceInventoryWarehouse.class.getName(),
			commerceWarehouse.getCommerceInventoryWarehouseId(), actionId);
	}

	@Reference
	private CommerceInventoryWarehouseLocalService
		_commerceWarehouseLocalService;

}