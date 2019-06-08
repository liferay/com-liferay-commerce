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

import com.liferay.commerce.product.model.CPOptionCategory;
import com.liferay.commerce.product.permission.CPOptionCategoryPermission;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.security.permission.PermissionChecker;
import com.liferay.portal.kernel.security.permission.resource.ModelResourcePermission;
import com.liferay.portal.kernel.security.permission.resource.PortletResourcePermission;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Marco Leo
 */
@Component(
	immediate = true,
	property = "model.class.name=com.liferay.commerce.product.model.CPOptionCategory",
	service = ModelResourcePermission.class
)
public class CPOptionCategoryModelResourcePermission
	implements ModelResourcePermission<CPOptionCategory> {

	@Override
	public void check(
			PermissionChecker permissionChecker,
			CPOptionCategory cpOptionCategory, String actionId)
		throws PortalException {

		cpOptionCategoryPermission.check(
			permissionChecker, cpOptionCategory, actionId);
	}

	@Override
	public void check(
			PermissionChecker permissionChecker, long cpOptionCategoryId,
			String actionId)
		throws PortalException {

		cpOptionCategoryPermission.check(
			permissionChecker, cpOptionCategoryId, actionId);
	}

	@Override
	public boolean contains(
			PermissionChecker permissionChecker,
			CPOptionCategory cpOptionCategory, String actionId)
		throws PortalException {

		return cpOptionCategoryPermission.contains(
			permissionChecker, cpOptionCategory, actionId);
	}

	@Override
	public boolean contains(
			PermissionChecker permissionChecker, long cpOptionCategoryId,
			String actionId)
		throws PortalException {

		return cpOptionCategoryPermission.contains(
			permissionChecker, cpOptionCategoryId, actionId);
	}

	@Override
	public String getModelName() {
		return CPOptionCategory.class.getName();
	}

	@Override
	public PortletResourcePermission getPortletResourcePermission() {
		return null;
	}

	@Reference
	protected CPOptionCategoryPermission cpOptionCategoryPermission;

}