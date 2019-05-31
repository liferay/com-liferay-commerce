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
 * Provides a wrapper for {@link CPOptionCategoryService}.
 *
 * @author Marco Leo
 * @see CPOptionCategoryService
 * @generated
 */
@ProviderType
public class CPOptionCategoryServiceWrapper implements CPOptionCategoryService,
	ServiceWrapper<CPOptionCategoryService> {
	public CPOptionCategoryServiceWrapper(
		CPOptionCategoryService cpOptionCategoryService) {
		_cpOptionCategoryService = cpOptionCategoryService;
	}

	@Override
	public com.liferay.commerce.product.model.CPOptionCategory addCPOptionCategory(
		java.util.Map<java.util.Locale, String> titleMap,
		java.util.Map<java.util.Locale, String> descriptionMap,
		double priority, String key,
		com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _cpOptionCategoryService.addCPOptionCategory(titleMap,
			descriptionMap, priority, key, serviceContext);
	}

	@Override
	public void deleteCPOptionCategory(long cpOptionCategoryId)
		throws com.liferay.portal.kernel.exception.PortalException {
		_cpOptionCategoryService.deleteCPOptionCategory(cpOptionCategoryId);
	}

	@Override
	public com.liferay.commerce.product.model.CPOptionCategory fetchCPOptionCategory(
		long cpOptionCategoryId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _cpOptionCategoryService.fetchCPOptionCategory(cpOptionCategoryId);
	}

	@Override
	public java.util.List<com.liferay.commerce.product.model.CPOptionCategory> getCPOptionCategories(
		long companyId, int start, int end)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _cpOptionCategoryService.getCPOptionCategories(companyId, start,
			end);
	}

	@Override
	public java.util.List<com.liferay.commerce.product.model.CPOptionCategory> getCPOptionCategories(
		long companyId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<com.liferay.commerce.product.model.CPOptionCategory> orderByComparator)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _cpOptionCategoryService.getCPOptionCategories(companyId, start,
			end, orderByComparator);
	}

	@Override
	public java.util.List<com.liferay.commerce.product.model.CPOptionCategory> getCPOptionCategoriesByCatalogGroupId(
		long groupId, int start, int end)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _cpOptionCategoryService.getCPOptionCategoriesByCatalogGroupId(groupId,
			start, end);
	}

	@Override
	public java.util.List<com.liferay.commerce.product.model.CPOptionCategory> getCPOptionCategoriesByCatalogGroupId(
		long groupId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<com.liferay.commerce.product.model.CPOptionCategory> orderByComparator)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _cpOptionCategoryService.getCPOptionCategoriesByCatalogGroupId(groupId,
			start, end, orderByComparator);
	}

	@Override
	public int getCPOptionCategoriesCount(long companyId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _cpOptionCategoryService.getCPOptionCategoriesCount(companyId);
	}

	@Override
	public int getCPOptionCategoriesCountByCatalogGroupId(long groupId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _cpOptionCategoryService.getCPOptionCategoriesCountByCatalogGroupId(groupId);
	}

	@Override
	public com.liferay.commerce.product.model.CPOptionCategory getCPOptionCategory(
		long cpOptionCategoryId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _cpOptionCategoryService.getCPOptionCategory(cpOptionCategoryId);
	}

	/**
	* Returns the OSGi service identifier.
	*
	* @return the OSGi service identifier
	*/
	@Override
	public String getOSGiServiceIdentifier() {
		return _cpOptionCategoryService.getOSGiServiceIdentifier();
	}

	@Override
	public com.liferay.commerce.product.model.CPOptionCategory updateCPOptionCategory(
		long cpOptionCategoryId,
		java.util.Map<java.util.Locale, String> titleMap,
		java.util.Map<java.util.Locale, String> descriptionMap,
		double priority, String key,
		com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _cpOptionCategoryService.updateCPOptionCategory(cpOptionCategoryId,
			titleMap, descriptionMap, priority, key, serviceContext);
	}

	@Override
	public CPOptionCategoryService getWrappedService() {
		return _cpOptionCategoryService;
	}

	@Override
	public void setWrappedService(
		CPOptionCategoryService cpOptionCategoryService) {
		_cpOptionCategoryService = cpOptionCategoryService;
	}

	private CPOptionCategoryService _cpOptionCategoryService;
}