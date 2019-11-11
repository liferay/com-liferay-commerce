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
 * Provides a wrapper for {@link CommerceApplicationBrandService}.
 *
 * @author Luca Pellizzon
 * @see CommerceApplicationBrandService
 * @generated
 */
@ProviderType
public class CommerceApplicationBrandServiceWrapper
	implements CommerceApplicationBrandService,
		ServiceWrapper<CommerceApplicationBrandService> {
	public CommerceApplicationBrandServiceWrapper(
		CommerceApplicationBrandService commerceApplicationBrandService) {
		_commerceApplicationBrandService = commerceApplicationBrandService;
	}

	@Override
	public com.liferay.commerce.application.model.CommerceApplicationBrand addCommerceApplicationBrand(
		long userId, String name, boolean logo, byte[] logoBytes)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _commerceApplicationBrandService.addCommerceApplicationBrand(userId,
			name, logo, logoBytes);
	}

	@Override
	public void deleteCommerceApplicationBrand(long commerceApplicationBrandId)
		throws com.liferay.portal.kernel.exception.PortalException {
		_commerceApplicationBrandService.deleteCommerceApplicationBrand(commerceApplicationBrandId);
	}

	@Override
	public com.liferay.commerce.application.model.CommerceApplicationBrand getCommerceApplicationBrand(
		long commerceApplicationBrandId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _commerceApplicationBrandService.getCommerceApplicationBrand(commerceApplicationBrandId);
	}

	@Override
	public java.util.List<com.liferay.commerce.application.model.CommerceApplicationBrand> getCommerceApplicationBrands(
		long companyId, int start, int end) {
		return _commerceApplicationBrandService.getCommerceApplicationBrands(companyId,
			start, end);
	}

	@Override
	public int getCommerceApplicationBrandsCount(long companyId) {
		return _commerceApplicationBrandService.getCommerceApplicationBrandsCount(companyId);
	}

	/**
	* Returns the OSGi service identifier.
	*
	* @return the OSGi service identifier
	*/
	@Override
	public String getOSGiServiceIdentifier() {
		return _commerceApplicationBrandService.getOSGiServiceIdentifier();
	}

	@Override
	public com.liferay.commerce.application.model.CommerceApplicationBrand updateCommerceApplicationBrand(
		long commerceApplicationBrandId, String name, boolean logo,
		byte[] logoBytes)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _commerceApplicationBrandService.updateCommerceApplicationBrand(commerceApplicationBrandId,
			name, logo, logoBytes);
	}

	@Override
	public CommerceApplicationBrandService getWrappedService() {
		return _commerceApplicationBrandService;
	}

	@Override
	public void setWrappedService(
		CommerceApplicationBrandService commerceApplicationBrandService) {
		_commerceApplicationBrandService = commerceApplicationBrandService;
	}

	private CommerceApplicationBrandService _commerceApplicationBrandService;
}