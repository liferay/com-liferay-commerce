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

import com.liferay.commerce.application.model.CommerceApplicationBrand;
import com.liferay.commerce.application.service.base.CommerceApplicationBrandLocalServiceBaseImpl;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.model.GroupConstants;
import com.liferay.portal.kernel.model.ResourceConstants;
import com.liferay.portal.kernel.model.SystemEventConstants;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.systemevent.SystemEvent;
import com.liferay.portal.kernel.util.Portal;
import com.liferay.portal.kernel.util.ProxyFactory;
import com.liferay.portal.spring.extender.service.ServiceReference;
import com.liferay.users.admin.kernel.file.uploads.UserFileUploadsSettings;

import java.util.List;

/**
 * @author Luca Pellizzon
 * @author Alessio Antonio Rendina
 */
public class CommerceApplicationBrandLocalServiceImpl
	extends CommerceApplicationBrandLocalServiceBaseImpl {

	@Override
	public CommerceApplicationBrand addCommerceApplicationBrand(
			long userId, String name, boolean logo, byte[] logoBytes)
		throws PortalException {

		User user = userLocalService.getUser(userId);

		long commerceBOMDefinitionId = counterLocalService.increment();

		CommerceApplicationBrand commerceApplicationBrand =
			commerceApplicationBrandPersistence.create(commerceBOMDefinitionId);

		commerceApplicationBrand.setCompanyId(user.getCompanyId());
		commerceApplicationBrand.setUserId(user.getUserId());
		commerceApplicationBrand.setUserName(user.getFullName());
		commerceApplicationBrand.setName(name);

		_portal.updateImageId(
			commerceApplicationBrand, logo, logoBytes, "logoId",
			_userFileUploadsSettings.getImageMaxSize(),
			_userFileUploadsSettings.getImageMaxHeight(),
			_userFileUploadsSettings.getImageMaxWidth());

		commerceApplicationBrand = commerceApplicationBrandPersistence.update(
			commerceApplicationBrand);

		// Resources

		resourceLocalService.addResources(
			user.getCompanyId(), GroupConstants.DEFAULT_LIVE_GROUP_ID,
			user.getUserId(), CommerceApplicationBrand.class.getName(),
			commerceApplicationBrand.getCommerceApplicationBrandId(), false,
			false, false);

		return commerceApplicationBrand;
	}

	@Override
	@SystemEvent(type = SystemEventConstants.TYPE_DELETE)
	public CommerceApplicationBrand deleteCommerceApplicationBrand(
			CommerceApplicationBrand commerceApplicationBrand)
		throws PortalException {

		// Commerce application model

		commerceApplicationModelLocalService.deleteCommerceApplicationModels(
			commerceApplicationBrand.getCommerceApplicationBrandId());

		// Resources

		resourceLocalService.deleteResource(
			commerceApplicationBrand, ResourceConstants.SCOPE_INDIVIDUAL);

		// Commerce application brand

		return commerceApplicationBrandPersistence.remove(
			commerceApplicationBrand);
	}

	@Override
	public CommerceApplicationBrand deleteCommerceApplicationBrand(
			long commerceApplicationBrandId)
		throws PortalException {

		CommerceApplicationBrand commerceApplicationBrand =
			commerceApplicationBrandPersistence.findByPrimaryKey(
				commerceApplicationBrandId);

		return commerceApplicationBrandLocalService.
			deleteCommerceApplicationBrand(commerceApplicationBrand);
	}

	@Override
	public void deleteCommerceApplicationBrands(long companyId)
		throws PortalException {

		List<CommerceApplicationBrand> commerceApplicationBrands =
			commerceApplicationBrandPersistence.findByCompanyId(companyId);

		for (CommerceApplicationBrand commerceApplicationBrand :
				commerceApplicationBrands) {

			commerceApplicationBrandLocalService.deleteCommerceApplicationBrand(
				commerceApplicationBrand);
		}
	}

	@Override
	public CommerceApplicationBrand updateCommerceApplicationBrand(
			long commerceApplicationBrandId, String name, boolean logo,
			byte[] logoBytes)
		throws PortalException {

		CommerceApplicationBrand commerceApplicationBrand =
			commerceApplicationBrandLocalService.getCommerceApplicationBrand(
				commerceApplicationBrandId);

		commerceApplicationBrand.setName(name);

		_portal.updateImageId(
			commerceApplicationBrand, logo, logoBytes, "logoId",
			_userFileUploadsSettings.getImageMaxSize(),
			_userFileUploadsSettings.getImageMaxHeight(),
			_userFileUploadsSettings.getImageMaxWidth());

		return commerceApplicationBrandPersistence.update(
			commerceApplicationBrand);
	}

	private static volatile UserFileUploadsSettings _userFileUploadsSettings =
		ProxyFactory.newServiceTrackedInstance(
			UserFileUploadsSettings.class,
			CommerceApplicationBrandLocalServiceImpl.class,
			"_userFileUploadsSettings");

	@ServiceReference(type = Portal.class)
	private Portal _portal;

}