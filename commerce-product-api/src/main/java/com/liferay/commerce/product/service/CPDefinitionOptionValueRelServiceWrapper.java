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
 * Provides a wrapper for {@link CPDefinitionOptionValueRelService}.
 *
 * @author Marco Leo
 * @see CPDefinitionOptionValueRelService
 * @generated
 */
public class CPDefinitionOptionValueRelServiceWrapper
	implements CPDefinitionOptionValueRelService,
			   ServiceWrapper<CPDefinitionOptionValueRelService> {

	public CPDefinitionOptionValueRelServiceWrapper(
		CPDefinitionOptionValueRelService cpDefinitionOptionValueRelService) {

		_cpDefinitionOptionValueRelService = cpDefinitionOptionValueRelService;
	}

	/**
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link CPDefinitionOptionValueRelServiceUtil} to access the cp definition option value rel remote service. Add custom service methods to <code>com.liferay.commerce.product.service.impl.CPDefinitionOptionValueRelServiceImpl</code> and rerun ServiceBuilder to automatically copy the method declarations to this interface.
	 */
	@Override
	public com.liferay.commerce.product.model.CPDefinitionOptionValueRel
			addCPDefinitionOptionValueRel(
				long cpDefinitionOptionRelId,
				java.util.Map<java.util.Locale, String> nameMap,
				double priority, String key,
				com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _cpDefinitionOptionValueRelService.addCPDefinitionOptionValueRel(
			cpDefinitionOptionRelId, nameMap, priority, key, serviceContext);
	}

	@Override
	public com.liferay.commerce.product.model.CPDefinitionOptionValueRel
			deleteCPDefinitionOptionValueRel(long cpDefinitionOptionValueRelId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _cpDefinitionOptionValueRelService.
			deleteCPDefinitionOptionValueRel(cpDefinitionOptionValueRelId);
	}

	@Override
	public com.liferay.commerce.product.model.CPDefinitionOptionValueRel
			fetchCPDefinitionOptionValueRel(long cpDefinitionOptionValueRelId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _cpDefinitionOptionValueRelService.
			fetchCPDefinitionOptionValueRel(cpDefinitionOptionValueRelId);
	}

	@Override
	public com.liferay.commerce.product.model.CPDefinitionOptionValueRel
			fetchCPDefinitionOptionValueRel(
				long cpDefinitionOptionRelId, String key)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _cpDefinitionOptionValueRelService.
			fetchCPDefinitionOptionValueRel(cpDefinitionOptionRelId, key);
	}

	@Override
	public com.liferay.commerce.product.model.CPDefinitionOptionValueRel
			getCPDefinitionOptionValueRel(long cpDefinitionOptionValueRelId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _cpDefinitionOptionValueRelService.getCPDefinitionOptionValueRel(
			cpDefinitionOptionValueRelId);
	}

	@Override
	public java.util.List
		<com.liferay.commerce.product.model.CPDefinitionOptionValueRel>
				getCPDefinitionOptionValueRels(
					long cpDefinitionOptionRelId, int start, int end)
			throws com.liferay.portal.kernel.exception.PortalException {

		return _cpDefinitionOptionValueRelService.
			getCPDefinitionOptionValueRels(cpDefinitionOptionRelId, start, end);
	}

	@Override
	public java.util.List
		<com.liferay.commerce.product.model.CPDefinitionOptionValueRel>
				getCPDefinitionOptionValueRels(
					long cpDefinitionOptionRelId, int start, int end,
					com.liferay.portal.kernel.util.OrderByComparator
						<com.liferay.commerce.product.model.
							CPDefinitionOptionValueRel> orderByComparator)
			throws com.liferay.portal.kernel.exception.PortalException {

		return _cpDefinitionOptionValueRelService.
			getCPDefinitionOptionValueRels(
				cpDefinitionOptionRelId, start, end, orderByComparator);
	}

	@Override
	public java.util.List
		<com.liferay.commerce.product.model.CPDefinitionOptionValueRel>
				getCPDefinitionOptionValueRels(
					long groupId, String key, int start, int end)
			throws com.liferay.portal.kernel.exception.PortalException {

		return _cpDefinitionOptionValueRelService.
			getCPDefinitionOptionValueRels(groupId, key, start, end);
	}

	@Override
	public int getCPDefinitionOptionValueRelsCount(long cpDefinitionOptionRelId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _cpDefinitionOptionValueRelService.
			getCPDefinitionOptionValueRelsCount(cpDefinitionOptionRelId);
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	@Override
	public String getOSGiServiceIdentifier() {
		return _cpDefinitionOptionValueRelService.getOSGiServiceIdentifier();
	}

	@Override
	public com.liferay.portal.kernel.search.BaseModelSearchResult
		<com.liferay.commerce.product.model.CPDefinitionOptionValueRel>
				searchCPDefinitionOptionValueRels(
					long companyId, long groupId, long cpDefinitionOptionRelId,
					String keywords, int start, int end,
					com.liferay.portal.kernel.search.Sort sort)
			throws com.liferay.portal.kernel.exception.PortalException {

		return _cpDefinitionOptionValueRelService.
			searchCPDefinitionOptionValueRels(
				companyId, groupId, cpDefinitionOptionRelId, keywords, start,
				end, sort);
	}

	@Override
	public com.liferay.commerce.product.model.CPDefinitionOptionValueRel
			updateCPDefinitionOptionValueRel(
				long cpDefinitionOptionValueRelId,
				java.util.Map<java.util.Locale, String> nameMap,
				double priority, String key,
				com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _cpDefinitionOptionValueRelService.
			updateCPDefinitionOptionValueRel(
				cpDefinitionOptionValueRelId, nameMap, priority, key,
				serviceContext);
	}

	@Override
	public CPDefinitionOptionValueRelService getWrappedService() {
		return _cpDefinitionOptionValueRelService;
	}

	@Override
	public void setWrappedService(
		CPDefinitionOptionValueRelService cpDefinitionOptionValueRelService) {

		_cpDefinitionOptionValueRelService = cpDefinitionOptionValueRelService;
	}

	private CPDefinitionOptionValueRelService
		_cpDefinitionOptionValueRelService;

}