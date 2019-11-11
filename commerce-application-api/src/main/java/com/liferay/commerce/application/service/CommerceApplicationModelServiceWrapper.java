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
 * Provides a wrapper for {@link CommerceApplicationModelService}.
 *
 * @author Luca Pellizzon
 * @see CommerceApplicationModelService
 * @generated
 */
@ProviderType
public class CommerceApplicationModelServiceWrapper
	implements CommerceApplicationModelService,
		ServiceWrapper<CommerceApplicationModelService> {
	public CommerceApplicationModelServiceWrapper(
		CommerceApplicationModelService commerceApplicationModelService) {
		_commerceApplicationModelService = commerceApplicationModelService;
	}

	@Override
	public com.liferay.commerce.application.model.CommerceApplicationModel addCommerceApplicationModel(
		long userId, long commerceApplicationBrandId, String name, String year)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _commerceApplicationModelService.addCommerceApplicationModel(userId,
			commerceApplicationBrandId, name, year);
	}

	@Override
	public void deleteCommerceApplicationModel(long commerceApplicationModelId)
		throws com.liferay.portal.kernel.exception.PortalException {
		_commerceApplicationModelService.deleteCommerceApplicationModel(commerceApplicationModelId);
	}

	@Override
	public com.liferay.commerce.application.model.CommerceApplicationModel getCommerceApplicationModel(
		long commerceApplicationModelId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _commerceApplicationModelService.getCommerceApplicationModel(commerceApplicationModelId);
	}

	@Override
	public java.util.List<com.liferay.commerce.application.model.CommerceApplicationModel> getCommerceApplicationModels(
		long commerceApplicationBrandId, int start, int end) {
		return _commerceApplicationModelService.getCommerceApplicationModels(commerceApplicationBrandId,
			start, end);
	}

	@Override
	public java.util.List<com.liferay.commerce.application.model.CommerceApplicationModel> getCommerceApplicationModelsByCompanyId(
		long companyId, int start, int end) {
		return _commerceApplicationModelService.getCommerceApplicationModelsByCompanyId(companyId,
			start, end);
	}

	@Override
	public int getCommerceApplicationModelsCount(
		long commerceApplicationBrandId) {
		return _commerceApplicationModelService.getCommerceApplicationModelsCount(commerceApplicationBrandId);
	}

	@Override
	public int getCommerceApplicationModelsCountByCompanyId(long companyId) {
		return _commerceApplicationModelService.getCommerceApplicationModelsCountByCompanyId(companyId);
	}

	/**
	* Returns the OSGi service identifier.
	*
	* @return the OSGi service identifier
	*/
	@Override
	public String getOSGiServiceIdentifier() {
		return _commerceApplicationModelService.getOSGiServiceIdentifier();
	}

	@Override
	public com.liferay.commerce.application.model.CommerceApplicationModel updateCommerceApplicationModel(
		long commerceApplicationModelId, String name, String year)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _commerceApplicationModelService.updateCommerceApplicationModel(commerceApplicationModelId,
			name, year);
	}

	@Override
	public CommerceApplicationModelService getWrappedService() {
		return _commerceApplicationModelService;
	}

	@Override
	public void setWrappedService(
		CommerceApplicationModelService commerceApplicationModelService) {
		_commerceApplicationModelService = commerceApplicationModelService;
	}

	private CommerceApplicationModelService _commerceApplicationModelService;
}