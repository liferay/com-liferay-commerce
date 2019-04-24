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
 * Provides a wrapper for {@link CPRuleCommerceAccountGroupRelService}.
 *
 * @author Marco Leo
 * @see CPRuleCommerceAccountGroupRelService
 * @generated
 */
@ProviderType
public class CPRuleCommerceAccountGroupRelServiceWrapper
	implements CPRuleCommerceAccountGroupRelService,
		ServiceWrapper<CPRuleCommerceAccountGroupRelService> {
	public CPRuleCommerceAccountGroupRelServiceWrapper(
		CPRuleCommerceAccountGroupRelService cpRuleCommerceAccountGroupRelService) {
		_cpRuleCommerceAccountGroupRelService = cpRuleCommerceAccountGroupRelService;
	}

	@Override
	public com.liferay.commerce.product.model.CPRuleCommerceAccountGroupRel addCPRuleCommerceAccountGroupRel(
		long cpRuleId, long commerceAccountGroupId,
		com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _cpRuleCommerceAccountGroupRelService.addCPRuleCommerceAccountGroupRel(cpRuleId,
			commerceAccountGroupId, serviceContext);
	}

	@Override
	public void deleteCPRuleCommerceAccountGroupRel(
		long cpRuleCommerceAccountGroupRelId)
		throws com.liferay.portal.kernel.exception.PortalException {
		_cpRuleCommerceAccountGroupRelService.deleteCPRuleCommerceAccountGroupRel(cpRuleCommerceAccountGroupRelId);
	}

	@Override
	public java.util.List<com.liferay.commerce.product.model.CPRuleCommerceAccountGroupRel> getCPRuleCommerceAccountGroupRels(
		long cpRuleId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<com.liferay.commerce.product.model.CPRuleCommerceAccountGroupRel> orderByComparator)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _cpRuleCommerceAccountGroupRelService.getCPRuleCommerceAccountGroupRels(cpRuleId,
			start, end, orderByComparator);
	}

	@Override
	public int getCPRuleCommerceAccountGroupRelsCount(long cpRuleId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _cpRuleCommerceAccountGroupRelService.getCPRuleCommerceAccountGroupRelsCount(cpRuleId);
	}

	/**
	* Returns the OSGi service identifier.
	*
	* @return the OSGi service identifier
	*/
	@Override
	public String getOSGiServiceIdentifier() {
		return _cpRuleCommerceAccountGroupRelService.getOSGiServiceIdentifier();
	}

	@Override
	public CPRuleCommerceAccountGroupRelService getWrappedService() {
		return _cpRuleCommerceAccountGroupRelService;
	}

	@Override
	public void setWrappedService(
		CPRuleCommerceAccountGroupRelService cpRuleCommerceAccountGroupRelService) {
		_cpRuleCommerceAccountGroupRelService = cpRuleCommerceAccountGroupRelService;
	}

	private CPRuleCommerceAccountGroupRelService _cpRuleCommerceAccountGroupRelService;
}