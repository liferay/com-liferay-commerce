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

package com.liferay.commerce.application.service.impl;

import com.liferay.commerce.application.constants.CommerceApplicationActionKeys;
import com.liferay.commerce.application.model.CommerceApplicationBrand;
import com.liferay.commerce.application.service.base.CommerceApplicationBrandServiceBaseImpl;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.security.permission.ActionKeys;
import com.liferay.portal.kernel.security.permission.resource.ModelResourcePermission;
import com.liferay.portal.kernel.security.permission.resource.ModelResourcePermissionFactory;
import com.liferay.portal.kernel.service.permission.PortalPermissionUtil;

import java.util.List;

/**
 * @author Luca Pellizzon
 * @author Alessio Antonio Rendina
 */
public class CommerceApplicationBrandServiceImpl
	extends CommerceApplicationBrandServiceBaseImpl {

	@Override
	public CommerceApplicationBrand addCommerceApplicationBrand(
			long userId, String name, boolean logo, byte[] logoBytes)
		throws PortalException {

		PortalPermissionUtil.check(
			getPermissionChecker(),
			CommerceApplicationActionKeys.ADD_COMMERCE_BRAND);

		return commerceApplicationBrandLocalService.addCommerceApplicationBrand(
			userId, name, logo, logoBytes);
	}

	@Override
	public void deleteCommerceApplicationBrand(long commerceApplicationBrandId)
		throws PortalException {

		_commerceApplicationBrandModelResourcePermission.check(
			getPermissionChecker(), commerceApplicationBrandId,
			ActionKeys.DELETE);

		commerceApplicationBrandLocalService.deleteCommerceApplicationBrand(
			commerceApplicationBrandId);
	}

	@Override
	public CommerceApplicationBrand getCommerceApplicationBrand(
			long commerceApplicationBrandId)
		throws PortalException {

		_commerceApplicationBrandModelResourcePermission.check(
			getPermissionChecker(), commerceApplicationBrandId,
			ActionKeys.VIEW);

		return commerceApplicationBrandLocalService.getCommerceApplicationBrand(
			commerceApplicationBrandId);
	}

	@Override
	public List<CommerceApplicationBrand> getCommerceApplicationBrands(
		long companyId, int start, int end) {

		return commerceApplicationBrandPersistence.filterFindByCompanyId(
			companyId, start, end);
	}

	@Override
	public int getCommerceApplicationBrandsCount(long companyId) {
		return commerceApplicationBrandPersistence.filterCountByCompanyId(
			companyId);
	}

	@Override
	public CommerceApplicationBrand updateCommerceApplicationBrand(
			long commerceApplicationBrandId, String name, boolean logo,
			byte[] logoBytes)
		throws PortalException {

		_commerceApplicationBrandModelResourcePermission.check(
			getPermissionChecker(), commerceApplicationBrandId,
			ActionKeys.UPDATE);

		return commerceApplicationBrandLocalService.
			updateCommerceApplicationBrand(
				commerceApplicationBrandId, name, logo, logoBytes);
	}

	private static volatile ModelResourcePermission<CommerceApplicationBrand>
		_commerceApplicationBrandModelResourcePermission =
			ModelResourcePermissionFactory.getInstance(
				CommerceApplicationBrandServiceImpl.class,
				"_commerceApplicationBrandModelResourcePermission",
				CommerceApplicationBrand.class);

}