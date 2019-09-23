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

import com.liferay.portal.kernel.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link CPSpecificationOptionService}.
 *
 * @author Marco Leo
 * @see CPSpecificationOptionService
 * @generated
 */
public class CPSpecificationOptionServiceWrapper
	implements CPSpecificationOptionService,
			   ServiceWrapper<CPSpecificationOptionService> {

	public CPSpecificationOptionServiceWrapper(
		CPSpecificationOptionService cpSpecificationOptionService) {

		_cpSpecificationOptionService = cpSpecificationOptionService;
	}

	/**
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link CPSpecificationOptionServiceUtil} to access the cp specification option remote service. Add custom service methods to <code>com.liferay.commerce.product.service.impl.CPSpecificationOptionServiceImpl</code> and rerun ServiceBuilder to automatically copy the method declarations to this interface.
	 */
	@Override
	public com.liferay.commerce.product.model.CPSpecificationOption
			addCPSpecificationOption(
				long cpOptionCategoryId,
				java.util.Map<java.util.Locale, String> titleMap,
				java.util.Map<java.util.Locale, String> descriptionMap,
				boolean facetable, String key,
				com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _cpSpecificationOptionService.addCPSpecificationOption(
			cpOptionCategoryId, titleMap, descriptionMap, facetable, key,
			serviceContext);
	}

	@Override
	public void deleteCPSpecificationOption(long cpSpecificationOptionId)
		throws com.liferay.portal.kernel.exception.PortalException {

		_cpSpecificationOptionService.deleteCPSpecificationOption(
			cpSpecificationOptionId);
	}

	@Override
	public com.liferay.commerce.product.model.CPSpecificationOption
			fetchCPSpecificationOption(long companyId, String key)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _cpSpecificationOptionService.fetchCPSpecificationOption(
			companyId, key);
	}

	@Override
	public com.liferay.commerce.product.model.CPSpecificationOption
			getCPSpecificationOption(long cpSpecificationOptionId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _cpSpecificationOptionService.getCPSpecificationOption(
			cpSpecificationOptionId);
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	@Override
	public String getOSGiServiceIdentifier() {
		return _cpSpecificationOptionService.getOSGiServiceIdentifier();
	}

	@Override
	public com.liferay.portal.kernel.search.BaseModelSearchResult
		<com.liferay.commerce.product.model.CPSpecificationOption>
				searchCPSpecificationOptions(
					long companyId, Boolean facetable, String keywords,
					int start, int end,
					com.liferay.portal.kernel.search.Sort sort)
			throws com.liferay.portal.kernel.exception.PortalException {

		return _cpSpecificationOptionService.searchCPSpecificationOptions(
			companyId, facetable, keywords, start, end, sort);
	}

	@Override
	public com.liferay.commerce.product.model.CPSpecificationOption
			updateCPSpecificationOption(
				long cpSpecificationOptionId, long cpOptionCategoryId,
				java.util.Map<java.util.Locale, String> titleMap,
				java.util.Map<java.util.Locale, String> descriptionMap,
				boolean facetable, String key,
				com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _cpSpecificationOptionService.updateCPSpecificationOption(
			cpSpecificationOptionId, cpOptionCategoryId, titleMap,
			descriptionMap, facetable, key, serviceContext);
	}

	@Override
	public CPSpecificationOptionService getWrappedService() {
		return _cpSpecificationOptionService;
	}

	@Override
	public void setWrappedService(
		CPSpecificationOptionService cpSpecificationOptionService) {

		_cpSpecificationOptionService = cpSpecificationOptionService;
	}

	private CPSpecificationOptionService _cpSpecificationOptionService;

}