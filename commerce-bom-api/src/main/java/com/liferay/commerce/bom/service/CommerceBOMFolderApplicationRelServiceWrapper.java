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

package com.liferay.commerce.bom.service;

import aQute.bnd.annotation.ProviderType;

import com.liferay.portal.kernel.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link CommerceBOMFolderApplicationRelService}.
 *
 * @author Luca Pellizzon
 * @see CommerceBOMFolderApplicationRelService
 * @generated
 */
@ProviderType
public class CommerceBOMFolderApplicationRelServiceWrapper
	implements CommerceBOMFolderApplicationRelService,
		ServiceWrapper<CommerceBOMFolderApplicationRelService> {
	public CommerceBOMFolderApplicationRelServiceWrapper(
		CommerceBOMFolderApplicationRelService commerceBOMFolderApplicationRelService) {
		_commerceBOMFolderApplicationRelService = commerceBOMFolderApplicationRelService;
	}

	@Override
	public com.liferay.commerce.bom.model.CommerceBOMFolderApplicationRel addCommerceBOMFolderApplicationRel(
		long userId, long commerceBOMFolderId, long commerceApplicationModelId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _commerceBOMFolderApplicationRelService.addCommerceBOMFolderApplicationRel(userId,
			commerceBOMFolderId, commerceApplicationModelId);
	}

	@Override
	public void deleteCommerceBOMFolderApplicationRel(
		long commerceBOMFolderApplicationRelId)
		throws com.liferay.portal.kernel.exception.PortalException {
		_commerceBOMFolderApplicationRelService.deleteCommerceBOMFolderApplicationRel(commerceBOMFolderApplicationRelId);
	}

	@Override
	public java.util.List<com.liferay.commerce.bom.model.CommerceBOMFolderApplicationRel> getCommerceBOMFolderApplicationRelsByCAMId(
		long commerceApplicationModelId, int start, int end)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _commerceBOMFolderApplicationRelService.getCommerceBOMFolderApplicationRelsByCAMId(commerceApplicationModelId,
			start, end);
	}

	@Override
	public java.util.List<com.liferay.commerce.bom.model.CommerceBOMFolderApplicationRel> getCommerceBOMFolderApplicationRelsByCommerceBOMFolderId(
		long commerceBOMFolderId, int start, int end)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _commerceBOMFolderApplicationRelService.getCommerceBOMFolderApplicationRelsByCommerceBOMFolderId(commerceBOMFolderId,
			start, end);
	}

	@Override
	public int getCommerceBOMFolderApplicationRelsCountByCAMId(
		long commerceApplicationModelId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _commerceBOMFolderApplicationRelService.getCommerceBOMFolderApplicationRelsCountByCAMId(commerceApplicationModelId);
	}

	@Override
	public int getCommerceBOMFolderApplicationRelsCountByCommerceBOMFolderId(
		long commerceBOMFolderId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _commerceBOMFolderApplicationRelService.getCommerceBOMFolderApplicationRelsCountByCommerceBOMFolderId(commerceBOMFolderId);
	}

	/**
	* Returns the OSGi service identifier.
	*
	* @return the OSGi service identifier
	*/
	@Override
	public String getOSGiServiceIdentifier() {
		return _commerceBOMFolderApplicationRelService.getOSGiServiceIdentifier();
	}

	@Override
	public CommerceBOMFolderApplicationRelService getWrappedService() {
		return _commerceBOMFolderApplicationRelService;
	}

	@Override
	public void setWrappedService(
		CommerceBOMFolderApplicationRelService commerceBOMFolderApplicationRelService) {
		_commerceBOMFolderApplicationRelService = commerceBOMFolderApplicationRelService;
	}

	private CommerceBOMFolderApplicationRelService _commerceBOMFolderApplicationRelService;
}