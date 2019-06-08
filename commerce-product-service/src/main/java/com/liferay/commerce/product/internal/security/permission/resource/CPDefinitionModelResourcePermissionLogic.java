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

package com.liferay.commerce.product.internal.security.permission.resource;

import com.liferay.commerce.product.model.CPDefinition;
import com.liferay.commerce.product.model.CommerceCatalog;
import com.liferay.commerce.product.permission.CommerceCatalogPermission;
import com.liferay.commerce.product.service.CommerceCatalogLocalService;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.security.permission.ActionKeys;
import com.liferay.portal.kernel.security.permission.PermissionChecker;
import com.liferay.portal.kernel.security.permission.resource.ModelResourcePermissionLogic;

/**
 * @author Alessio Antonio Rendina
 * @author Andrea Di Giorgi
 * @author Ethan Bustad
 */
public class CPDefinitionModelResourcePermissionLogic
	implements ModelResourcePermissionLogic<CPDefinition> {

	public CPDefinitionModelResourcePermissionLogic(
		CommerceCatalogLocalService commerceCatalogLocalService,
		CommerceCatalogPermission commerceCatalogPermission) {

		_commerceCatalogLocalService = commerceCatalogLocalService;
		_commerceCatalogPermission = commerceCatalogPermission;
	}

	@Override
	public Boolean contains(
			PermissionChecker permissionChecker, String name,
			CPDefinition cpDefinition, String actionId)
		throws PortalException {

		CommerceCatalog commerceCatalog =
			_commerceCatalogLocalService.fetchCommerceCatalogByGroupId(
				cpDefinition.getGroupId());

		if (permissionChecker.isCompanyAdmin(cpDefinition.getCompanyId()) ||
			permissionChecker.isGroupAdmin(cpDefinition.getGroupId()) ||
			_commerceCatalogPermission.contains(
				permissionChecker, commerceCatalog.getCommerceCatalogId(),
				ActionKeys.VIEW)) {

			return true;
		}

		if (actionId.equals(ActionKeys.UPDATE)) {
			return _commerceCatalogPermission.contains(
				permissionChecker, commerceCatalog.getCommerceCatalogId(),
				ActionKeys.UPDATE);
		}

		if (!actionId.equals(ActionKeys.VIEW) || !cpDefinition.isApproved() ||
			!cpDefinition.isPublished()) {

			return false;
		}

		return true;
	}

	private final CommerceCatalogLocalService _commerceCatalogLocalService;
	private final CommerceCatalogPermission _commerceCatalogPermission;

}