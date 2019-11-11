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

import com.liferay.commerce.application.model.CommerceApplicationModel;
import com.liferay.commerce.application.service.base.CommerceApplicationModelLocalServiceBaseImpl;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.model.GroupConstants;
import com.liferay.portal.kernel.model.ResourceConstants;
import com.liferay.portal.kernel.model.SystemEventConstants;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.systemevent.SystemEvent;

import java.util.List;

/**
 * @author Luca Pellizzon
 * @author Alessio Antonio Rendina
 */
public class CommerceApplicationModelLocalServiceImpl
	extends CommerceApplicationModelLocalServiceBaseImpl {

	@Override
	public CommerceApplicationModel addCommerceApplicationModel(
			long userId, long commerceApplicationBrandId, String name,
			String year)
		throws PortalException {

		User user = userLocalService.getUser(userId);

		long commerceApplicationModelId = counterLocalService.increment();

		CommerceApplicationModel commerceApplicationModel =
			commerceApplicationModelPersistence.create(
				commerceApplicationModelId);

		commerceApplicationModel.setCompanyId(user.getCompanyId());
		commerceApplicationModel.setUserId(user.getUserId());
		commerceApplicationModel.setUserName(user.getFullName());
		commerceApplicationModel.setCommerceApplicationBrandId(
			commerceApplicationBrandId);
		commerceApplicationModel.setName(name);
		commerceApplicationModel.setYear(year);

		commerceApplicationModel = commerceApplicationModelPersistence.update(
			commerceApplicationModel);

		// Resources

		resourceLocalService.addResources(
			user.getCompanyId(), GroupConstants.DEFAULT_LIVE_GROUP_ID,
			user.getUserId(), CommerceApplicationModel.class.getName(),
			commerceApplicationModel.getCommerceApplicationModelId(), false,
			false, false);

		return commerceApplicationModel;
	}

	@Override
	@SystemEvent(type = SystemEventConstants.TYPE_DELETE)
	public CommerceApplicationModel deleteCommerceApplicationModel(
			CommerceApplicationModel commerceApplicationModel)
		throws PortalException {

		// Commerce application model product rels

		commerceApplicationModelCProductRelLocalService.
			deleteCommerceApplicationModelCProductRels(
				commerceApplicationModel.getCommerceApplicationModelId());

		// Resources

		resourceLocalService.deleteResource(
			commerceApplicationModel, ResourceConstants.SCOPE_INDIVIDUAL);

		// Commerce application model

		return commerceApplicationModelPersistence.remove(
			commerceApplicationModel);
	}

	@Override
	public CommerceApplicationModel deleteCommerceApplicationModel(
			long commerceApplicationModelId)
		throws PortalException {

		CommerceApplicationModel commerceApplicationModel =
			commerceApplicationModelPersistence.findByPrimaryKey(
				commerceApplicationModelId);

		return commerceApplicationModelLocalService.
			deleteCommerceApplicationModel(commerceApplicationModel);
	}

	@Override
	public void deleteCommerceApplicationModels(long commerceApplicationBrandId)
		throws PortalException {

		List<CommerceApplicationModel> commerceApplicationModels =
			commerceApplicationModelPersistence.
				findByCommerceApplicationBrandId(commerceApplicationBrandId);

		for (CommerceApplicationModel commerceApplicationModel :
				commerceApplicationModels) {

			commerceApplicationModelLocalService.deleteCommerceApplicationModel(
				commerceApplicationModel);
		}
	}

	@Override
	public CommerceApplicationModel updateCommerceApplicationModel(
			long commerceApplicationModelId, String name, String year)
		throws PortalException {

		CommerceApplicationModel commerceApplicationModel =
			commerceApplicationModelLocalService.getCommerceApplicationModel(
				commerceApplicationModelId);

		commerceApplicationModel.setName(name);
		commerceApplicationModel.setYear(year);

		return commerceApplicationModelPersistence.update(
			commerceApplicationModel);
	}

}