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

package com.liferay.commerce.bom.service.impl;

import com.liferay.commerce.application.model.CommerceApplicationModel;
import com.liferay.commerce.bom.model.CommerceBOMFolder;
import com.liferay.commerce.bom.model.CommerceBOMFolderApplicationRel;
import com.liferay.commerce.bom.service.base.CommerceBOMFolderApplicationRelServiceBaseImpl;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.security.permission.ActionKeys;
import com.liferay.portal.kernel.security.permission.resource.ModelResourcePermission;
import com.liferay.portal.kernel.security.permission.resource.ModelResourcePermissionFactory;

import java.util.List;

/**
 * @author Alessio Antonio Rendina
 */
public class CommerceBOMFolderApplicationRelServiceImpl
	extends CommerceBOMFolderApplicationRelServiceBaseImpl {

	@Override
	public CommerceBOMFolderApplicationRel addCommerceBOMFolderApplicationRel(
			long userId, long commerceBOMFolderId,
			long commerceApplicationModelId)
		throws PortalException {

		_commerceBOMFolderModelResourcePermission.check(
			getPermissionChecker(), commerceBOMFolderId, ActionKeys.UPDATE);

		_commerceApplicationModelModelResourcePermission.check(
			getPermissionChecker(), commerceApplicationModelId,
			ActionKeys.UPDATE);

		return commerceBOMFolderApplicationRelLocalService.
			addCommerceBOMFolderApplicationRel(
				userId, commerceBOMFolderId, commerceApplicationModelId);
	}

	@Override
	public void deleteCommerceBOMFolderApplicationRel(
			long commerceBOMFolderApplicationRelId)
		throws PortalException {

		CommerceBOMFolderApplicationRel commerceBOMFolderApplicationRel =
			commerceBOMFolderApplicationRelLocalService.
				getCommerceBOMFolderApplicationRel(
					commerceBOMFolderApplicationRelId);

		_commerceBOMFolderModelResourcePermission.check(
			getPermissionChecker(),
			commerceBOMFolderApplicationRel.getCommerceBOMFolderId(),
			ActionKeys.UPDATE);

		_commerceApplicationModelModelResourcePermission.check(
			getPermissionChecker(),
			commerceBOMFolderApplicationRel.getCommerceApplicationModelId(),
			ActionKeys.UPDATE);

		commerceBOMFolderApplicationRelLocalService.
			deleteCommerceBOMFolderApplicationRel(
				commerceBOMFolderApplicationRel);
	}

	@Override
	public List<CommerceBOMFolderApplicationRel>
			getCommerceBOMFolderApplicationRelsByCAMId(
				long commerceApplicationModelId, int start, int end)
		throws PortalException {

		_commerceApplicationModelModelResourcePermission.check(
			getPermissionChecker(), commerceApplicationModelId,
			ActionKeys.VIEW);

		return commerceBOMFolderApplicationRelLocalService.
			getCommerceBOMFolderApplicationRelsByCAMId(
				commerceApplicationModelId, start, end);
	}

	@Override
	public List<CommerceBOMFolderApplicationRel>
			getCommerceBOMFolderApplicationRelsByCommerceBOMFolderId(
				long commerceBOMFolderId, int start, int end)
		throws PortalException {

		_commerceBOMFolderModelResourcePermission.check(
			getPermissionChecker(), commerceBOMFolderId, ActionKeys.VIEW);

		return commerceBOMFolderApplicationRelLocalService.
			getCommerceBOMFolderApplicationRelsByCommerceBOMFolderId(
				commerceBOMFolderId, start, end);
	}

	@Override
	public int getCommerceBOMFolderApplicationRelsCountByCAMId(
			long commerceApplicationModelId)
		throws PortalException {

		_commerceApplicationModelModelResourcePermission.check(
			getPermissionChecker(), commerceApplicationModelId,
			ActionKeys.VIEW);

		return commerceBOMFolderApplicationRelLocalService.
			getCommerceBOMFolderApplicationRelsCountByCAMId(
				commerceApplicationModelId);
	}

	@Override
	public int getCommerceBOMFolderApplicationRelsCountByCommerceBOMFolderId(
			long commerceBOMFolderId)
		throws PortalException {

		_commerceBOMFolderModelResourcePermission.check(
			getPermissionChecker(), commerceBOMFolderId, ActionKeys.VIEW);

		return commerceBOMFolderApplicationRelLocalService.
			getCommerceBOMFolderApplicationRelsCountByCommerceBOMFolderId(
				commerceBOMFolderId);
	}

	private static volatile ModelResourcePermission<CommerceApplicationModel>
		_commerceApplicationModelModelResourcePermission =
			ModelResourcePermissionFactory.getInstance(
				CommerceBOMFolderApplicationRelServiceImpl.class,
				"_commerceApplicationModelModelResourcePermission",
				CommerceApplicationModel.class);
	private static volatile ModelResourcePermission<CommerceBOMFolder>
		_commerceBOMFolderModelResourcePermission =
			ModelResourcePermissionFactory.getInstance(
				CommerceBOMFolderApplicationRelServiceImpl.class,
				"_commerceBOMFolderModelResourcePermission",
				CommerceBOMFolder.class);

}