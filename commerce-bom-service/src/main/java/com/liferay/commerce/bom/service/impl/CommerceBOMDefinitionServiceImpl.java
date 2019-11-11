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

import com.liferay.commerce.bom.constants.CommerceBOMActionKeys;
import com.liferay.commerce.bom.model.CommerceBOMDefinition;
import com.liferay.commerce.bom.service.base.CommerceBOMDefinitionServiceBaseImpl;
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
public class CommerceBOMDefinitionServiceImpl
	extends CommerceBOMDefinitionServiceBaseImpl {

	@Override
	public CommerceBOMDefinition addCommerceBOMDefinition(
			long userId, long commerceBOMFolderId, long cpAttachmentFileEntryId,
			String name, String friendlyUrl)
		throws PortalException {

		PortalPermissionUtil.check(
			getPermissionChecker(),
			CommerceBOMActionKeys.ADD_COMMERCE_BOM_DEFINITION);

		return commerceBOMDefinitionLocalService.addCommerceBOMDefinition(
			userId, commerceBOMFolderId, cpAttachmentFileEntryId, name,
			friendlyUrl);
	}

	@Override
	public void deleteCommerceBOMDefinition(long commerceBOMDefinitionId)
		throws PortalException {

		_commerceBOMDefinitionModelResourcePermission.check(
			getPermissionChecker(), commerceBOMDefinitionId, ActionKeys.DELETE);

		commerceBOMDefinitionLocalService.deleteCommerceBOMDefinition(
			commerceBOMDefinitionId);
	}

	@Override
	public CommerceBOMDefinition getCommerceBOMDefinition(
			long commerceBOMDefinitionId)
		throws PortalException {

		_commerceBOMDefinitionModelResourcePermission.check(
			getPermissionChecker(), commerceBOMDefinitionId, ActionKeys.VIEW);

		return commerceBOMDefinitionLocalService.getCommerceBOMDefinition(
			commerceBOMDefinitionId);
	}

	@Override
	public List<CommerceBOMDefinition> getCommerceBOMDefinitions(
		long commerceBOMFolderId, int start, int end) {

		return commerceBOMDefinitionPersistence.filterFindByCommerceBOMFolderId(
			commerceBOMFolderId, start, end);
	}

	@Override
	public int getCommerceBOMDefinitionsCount(long commerceBOMFolderId) {
		return commerceBOMDefinitionPersistence.
			filterCountByCommerceBOMFolderId(commerceBOMFolderId);
	}

	@Override
	public CommerceBOMDefinition updateCommerceBOMDefinition(
			long commerceBOMDefinitionId, long cpAttachmentFileEntryId,
			String name)
		throws PortalException {

		_commerceBOMDefinitionModelResourcePermission.check(
			getPermissionChecker(), commerceBOMDefinitionId, ActionKeys.UPDATE);

		return commerceBOMDefinitionLocalService.updateCommerceBOMDefinition(
			commerceBOMDefinitionId, cpAttachmentFileEntryId, name);
	}

	private static volatile ModelResourcePermission<CommerceBOMDefinition>
		_commerceBOMDefinitionModelResourcePermission =
			ModelResourcePermissionFactory.getInstance(
				CommerceBOMDefinitionServiceImpl.class,
				"_commerceBOMDefinitionModelResourcePermission",
				CommerceBOMDefinition.class);

}