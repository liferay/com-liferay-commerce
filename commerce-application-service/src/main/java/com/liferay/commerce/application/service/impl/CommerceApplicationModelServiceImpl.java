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
import com.liferay.commerce.application.model.CommerceApplicationModel;
import com.liferay.commerce.application.service.base.CommerceApplicationModelServiceBaseImpl;
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
public class CommerceApplicationModelServiceImpl
	extends CommerceApplicationModelServiceBaseImpl {

	@Override
	public CommerceApplicationModel addCommerceApplicationModel(
			long userId, long commerceApplicationBrandId, String name,
			String year)
		throws PortalException {

		PortalPermissionUtil.check(
			getPermissionChecker(),
			CommerceApplicationActionKeys.ADD_COMMERCE_MODEL);

		return commerceApplicationModelLocalService.addCommerceApplicationModel(
			userId, commerceApplicationBrandId, name, year);
	}

	@Override
	public void deleteCommerceApplicationModel(long commerceApplicationModelId)
		throws PortalException {

		_commerceApplicationModelModelResourcePermission.check(
			getPermissionChecker(), commerceApplicationModelId,
			ActionKeys.DELETE);

		commerceApplicationModelLocalService.deleteCommerceApplicationModel(
			commerceApplicationModelId);
	}

	@Override
	public CommerceApplicationModel getCommerceApplicationModel(
			long commerceApplicationModelId)
		throws PortalException {

		_commerceApplicationModelModelResourcePermission.check(
			getPermissionChecker(), commerceApplicationModelId,
			ActionKeys.VIEW);

		return commerceApplicationModelLocalService.getCommerceApplicationModel(
			commerceApplicationModelId);
	}

	@Override
	public List<CommerceApplicationModel> getCommerceApplicationModels(
		long commerceApplicationBrandId, int start, int end) {

		return commerceApplicationModelPersistence.
			filterFindByCommerceApplicationBrandId(
				commerceApplicationBrandId, start, end);
	}

	@Override
	public List<CommerceApplicationModel>
		getCommerceApplicationModelsByCompanyId(
			long companyId, int start, int end) {

		return commerceApplicationModelPersistence.filterFindByCompanyId(
			companyId, start, end);
	}

	@Override
	public int getCommerceApplicationModelsCount(
		long commerceApplicationBrandId) {

		return commerceApplicationModelPersistence.
			filterCountByCommerceApplicationBrandId(commerceApplicationBrandId);
	}

	@Override
	public int getCommerceApplicationModelsCountByCompanyId(long companyId) {
		return commerceApplicationModelPersistence.filterCountByCompanyId(
			companyId);
	}

	@Override
	public CommerceApplicationModel updateCommerceApplicationModel(
			long commerceApplicationModelId, String name, String year)
		throws PortalException {

		_commerceApplicationModelModelResourcePermission.check(
			getPermissionChecker(), commerceApplicationModelId,
			ActionKeys.UPDATE);

		return commerceApplicationModelLocalService.
			updateCommerceApplicationModel(
				commerceApplicationModelId, name, year);
	}

	private static volatile ModelResourcePermission<CommerceApplicationModel>
		_commerceApplicationModelModelResourcePermission =
			ModelResourcePermissionFactory.getInstance(
				CommerceApplicationModelServiceImpl.class,
				"_commerceApplicationModelModelResourcePermission",
				CommerceApplicationModel.class);

}