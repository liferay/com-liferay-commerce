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

package com.liferay.commerce.application.service;

import aQute.bnd.annotation.ProviderType;

import com.liferay.portal.kernel.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link CommerceApplicationModelCProductRelService}.
 *
 * @author Luca Pellizzon
 * @see CommerceApplicationModelCProductRelService
 * @generated
 */
@ProviderType
public class CommerceApplicationModelCProductRelServiceWrapper
	implements CommerceApplicationModelCProductRelService,
		ServiceWrapper<CommerceApplicationModelCProductRelService> {
	public CommerceApplicationModelCProductRelServiceWrapper(
		CommerceApplicationModelCProductRelService commerceApplicationModelCProductRelService) {
		_commerceApplicationModelCProductRelService = commerceApplicationModelCProductRelService;
	}

	@Override
	public com.liferay.commerce.application.model.CommerceApplicationModelCProductRel addCommerceApplicationModelCProductRel(
		long userId, long commerceApplicationModelId, long cProductId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _commerceApplicationModelCProductRelService.addCommerceApplicationModelCProductRel(userId,
			commerceApplicationModelId, cProductId);
	}

	@Override
	public void deleteCommerceApplicationModelCProductRel(
		long commerceApplicationModelCProductRelId)
		throws com.liferay.portal.kernel.exception.PortalException {
		_commerceApplicationModelCProductRelService.deleteCommerceApplicationModelCProductRel(commerceApplicationModelCProductRelId);
	}

	@Override
	public java.util.List<com.liferay.commerce.application.model.CommerceApplicationModelCProductRel> getCommerceApplicationModelCProductRels(
		long commerceApplicationModelId, int start, int end)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _commerceApplicationModelCProductRelService.getCommerceApplicationModelCProductRels(commerceApplicationModelId,
			start, end);
	}

	@Override
	public int getCommerceApplicationModelCProductRelsCount(
		long commerceApplicationModelId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _commerceApplicationModelCProductRelService.getCommerceApplicationModelCProductRelsCount(commerceApplicationModelId);
	}

	/**
	* Returns the OSGi service identifier.
	*
	* @return the OSGi service identifier
	*/
	@Override
	public String getOSGiServiceIdentifier() {
		return _commerceApplicationModelCProductRelService.getOSGiServiceIdentifier();
	}

	@Override
	public CommerceApplicationModelCProductRelService getWrappedService() {
		return _commerceApplicationModelCProductRelService;
	}

	@Override
	public void setWrappedService(
		CommerceApplicationModelCProductRelService commerceApplicationModelCProductRelService) {
		_commerceApplicationModelCProductRelService = commerceApplicationModelCProductRelService;
	}

	private CommerceApplicationModelCProductRelService _commerceApplicationModelCProductRelService;
}