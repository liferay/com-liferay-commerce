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
 * Provides a wrapper for {@link CommerceBOMDefinitionService}.
 *
 * @author Luca Pellizzon
 * @see CommerceBOMDefinitionService
 * @generated
 */
@ProviderType
public class CommerceBOMDefinitionServiceWrapper
	implements CommerceBOMDefinitionService,
		ServiceWrapper<CommerceBOMDefinitionService> {
	public CommerceBOMDefinitionServiceWrapper(
		CommerceBOMDefinitionService commerceBOMDefinitionService) {
		_commerceBOMDefinitionService = commerceBOMDefinitionService;
	}

	@Override
	public com.liferay.commerce.bom.model.CommerceBOMDefinition addCommerceBOMDefinition(
		long userId, long commerceBOMFolderId, long cpAttachmentFileEntryId,
		String name, String friendlyUrl)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _commerceBOMDefinitionService.addCommerceBOMDefinition(userId,
			commerceBOMFolderId, cpAttachmentFileEntryId, name, friendlyUrl);
	}

	@Override
	public void deleteCommerceBOMDefinition(long commerceBOMDefinitionId)
		throws com.liferay.portal.kernel.exception.PortalException {
		_commerceBOMDefinitionService.deleteCommerceBOMDefinition(commerceBOMDefinitionId);
	}

	@Override
	public com.liferay.commerce.bom.model.CommerceBOMDefinition getCommerceBOMDefinition(
		long commerceBOMDefinitionId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _commerceBOMDefinitionService.getCommerceBOMDefinition(commerceBOMDefinitionId);
	}

	@Override
	public java.util.List<com.liferay.commerce.bom.model.CommerceBOMDefinition> getCommerceBOMDefinitions(
		long commerceBOMFolderId, int start, int end) {
		return _commerceBOMDefinitionService.getCommerceBOMDefinitions(commerceBOMFolderId,
			start, end);
	}

	@Override
	public int getCommerceBOMDefinitionsCount(long commerceBOMFolderId) {
		return _commerceBOMDefinitionService.getCommerceBOMDefinitionsCount(commerceBOMFolderId);
	}

	/**
	* Returns the OSGi service identifier.
	*
	* @return the OSGi service identifier
	*/
	@Override
	public String getOSGiServiceIdentifier() {
		return _commerceBOMDefinitionService.getOSGiServiceIdentifier();
	}

	@Override
	public com.liferay.commerce.bom.model.CommerceBOMDefinition updateCommerceBOMDefinition(
		long commerceBOMDefinitionId, long cpAttachmentFileEntryId, String name)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _commerceBOMDefinitionService.updateCommerceBOMDefinition(commerceBOMDefinitionId,
			cpAttachmentFileEntryId, name);
	}

	@Override
	public CommerceBOMDefinitionService getWrappedService() {
		return _commerceBOMDefinitionService;
	}

	@Override
	public void setWrappedService(
		CommerceBOMDefinitionService commerceBOMDefinitionService) {
		_commerceBOMDefinitionService = commerceBOMDefinitionService;
	}

	private CommerceBOMDefinitionService _commerceBOMDefinitionService;
}