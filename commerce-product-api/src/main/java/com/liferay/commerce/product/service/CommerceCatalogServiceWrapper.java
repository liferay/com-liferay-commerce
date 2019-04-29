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

package com.liferay.commerce.product.service;

import aQute.bnd.annotation.ProviderType;

import com.liferay.portal.kernel.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link CommerceCatalogService}.
 *
 * @author Marco Leo
 * @see CommerceCatalogService
 * @generated
 */
@ProviderType
public class CommerceCatalogServiceWrapper implements CommerceCatalogService,
	ServiceWrapper<CommerceCatalogService> {
	public CommerceCatalogServiceWrapper(
		CommerceCatalogService commerceCatalogService) {
		_commerceCatalogService = commerceCatalogService;
	}

	@Override
	public com.liferay.commerce.product.model.CommerceCatalog addCommerceCatalog(
		java.util.Map<java.util.Locale, String> nameMap,
		String catalogDefaultLanguageId,
		com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _commerceCatalogService.addCommerceCatalog(nameMap,
			catalogDefaultLanguageId, serviceContext);
	}

	@Override
	public com.liferay.commerce.product.model.CommerceCatalog deleteCommerceCatalog(
		long commerceCatalogId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _commerceCatalogService.deleteCommerceCatalog(commerceCatalogId);
	}

	@Override
	public com.liferay.commerce.product.model.CommerceCatalog fetchCommerceCatalog(
		long commerceCatalogId) {
		return _commerceCatalogService.fetchCommerceCatalog(commerceCatalogId);
	}

	@Override
	public com.liferay.portal.kernel.model.Group getCommerceCatalogGroup(
		long commerceCatalogId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _commerceCatalogService.getCommerceCatalogGroup(commerceCatalogId);
	}

	/**
	* Returns the OSGi service identifier.
	*
	* @return the OSGi service identifier
	*/
	@Override
	public String getOSGiServiceIdentifier() {
		return _commerceCatalogService.getOSGiServiceIdentifier();
	}

	@Override
	public java.util.List<com.liferay.commerce.product.model.CommerceCatalog> searchCommerceCatalogs(
		long companyId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _commerceCatalogService.searchCommerceCatalogs(companyId);
	}

	@Override
	public java.util.List<com.liferay.commerce.product.model.CommerceCatalog> searchCommerceCatalogs(
		long companyId, String keywords, int start, int end,
		com.liferay.portal.kernel.search.Sort sort)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _commerceCatalogService.searchCommerceCatalogs(companyId,
			keywords, start, end, sort);
	}

	@Override
	public com.liferay.commerce.product.model.CommerceCatalog updateCommerceCatalog(
		long commerceCatalogId, String catalogDefaultLanguageId,
		java.util.Map<java.util.Locale, String> nameMap,
		com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _commerceCatalogService.updateCommerceCatalog(commerceCatalogId,
			catalogDefaultLanguageId, nameMap, serviceContext);
	}

	@Override
	public CommerceCatalogService getWrappedService() {
		return _commerceCatalogService;
	}

	@Override
	public void setWrappedService(CommerceCatalogService commerceCatalogService) {
		_commerceCatalogService = commerceCatalogService;
	}

	private CommerceCatalogService _commerceCatalogService;
}