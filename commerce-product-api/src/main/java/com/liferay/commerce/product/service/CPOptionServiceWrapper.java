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
 * Provides a wrapper for {@link CPOptionService}.
 *
 * @author Marco Leo
 * @see CPOptionService
 * @generated
 */
@ProviderType
public class CPOptionServiceWrapper implements CPOptionService,
	ServiceWrapper<CPOptionService> {
	public CPOptionServiceWrapper(CPOptionService cpOptionService) {
		_cpOptionService = cpOptionService;
	}

	@Override
	public com.liferay.commerce.product.model.CPOption addCPOption(
		java.util.Map<java.util.Locale, String> nameMap,
		java.util.Map<java.util.Locale, String> descriptionMap,
		String ddmFormFieldTypeName, boolean facetable, boolean required,
		boolean skuContributor, String key,
		com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _cpOptionService.addCPOption(nameMap, descriptionMap,
			ddmFormFieldTypeName, facetable, required, skuContributor, key,
			serviceContext);
	}

	@Override
	public void deleteCPOption(long cpOptionId)
		throws com.liferay.portal.kernel.exception.PortalException {
		_cpOptionService.deleteCPOption(cpOptionId);
	}

	@Override
	public com.liferay.commerce.product.model.CPOption fetchByExternalReferenceCode(
		long companyId, String externalReferenceCode)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _cpOptionService.fetchByExternalReferenceCode(companyId,
			externalReferenceCode);
	}

	@Override
	public com.liferay.commerce.product.model.CPOption fetchCPOption(
		long cpOptionId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _cpOptionService.fetchCPOption(cpOptionId);
	}

	@Override
	public com.liferay.commerce.product.model.CPOption fetchCPOption(
		long groupId, String key)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _cpOptionService.fetchCPOption(groupId, key);
	}

	@Override
	public com.liferay.commerce.product.model.CPOption getCPOption(
		long cpOptionId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _cpOptionService.getCPOption(cpOptionId);
	}

	@Override
	public java.util.List<com.liferay.commerce.product.model.CPOption> getCPOptions(
		long groupId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<com.liferay.commerce.product.model.CPOption> orderByComparator)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _cpOptionService.getCPOptions(groupId, start, end,
			orderByComparator);
	}

	@Override
	public int getCPOptionsCount(long groupId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _cpOptionService.getCPOptionsCount(groupId);
	}

	/**
	* Returns the OSGi service identifier.
	*
	* @return the OSGi service identifier
	*/
	@Override
	public String getOSGiServiceIdentifier() {
		return _cpOptionService.getOSGiServiceIdentifier();
	}

	@Override
	public com.liferay.portal.kernel.search.BaseModelSearchResult<com.liferay.commerce.product.model.CPOption> searchCPOptions(
		long companyId, long groupId, String keywords, int start, int end,
		com.liferay.portal.kernel.search.Sort sort)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _cpOptionService.searchCPOptions(companyId, groupId, keywords,
			start, end, sort);
	}

	@Override
	public com.liferay.commerce.product.model.CPOption updateCPOption(
		long cpOptionId, java.util.Map<java.util.Locale, String> nameMap,
		java.util.Map<java.util.Locale, String> descriptionMap,
		String ddmFormFieldTypeName, boolean facetable, boolean required,
		boolean skuContributor, String key,
		com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _cpOptionService.updateCPOption(cpOptionId, nameMap,
			descriptionMap, ddmFormFieldTypeName, facetable, required,
			skuContributor, key, serviceContext);
	}

	@Override
	public com.liferay.commerce.product.model.CPOption upsertCPOption(
		java.util.Map<java.util.Locale, String> nameMap,
		java.util.Map<java.util.Locale, String> descriptionMap,
		String ddmFormFieldTypeName, boolean facetable, boolean required,
		boolean skuContributor, String key, String externalReferenceCode,
		com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _cpOptionService.upsertCPOption(nameMap, descriptionMap,
			ddmFormFieldTypeName, facetable, required, skuContributor, key,
			externalReferenceCode, serviceContext);
	}

	@Override
	public CPOptionService getWrappedService() {
		return _cpOptionService;
	}

	@Override
	public void setWrappedService(CPOptionService cpOptionService) {
		_cpOptionService = cpOptionService;
	}

	private CPOptionService _cpOptionService;
}