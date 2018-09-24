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

package com.liferay.commerce.service;

import aQute.bnd.annotation.ProviderType;

import com.liferay.portal.kernel.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link CPSubscriptionEntryService}.
 *
 * @author Alessio Antonio Rendina
 * @see CPSubscriptionEntryService
 * @generated
 */
@ProviderType
public class CPSubscriptionEntryServiceWrapper
	implements CPSubscriptionEntryService,
		ServiceWrapper<CPSubscriptionEntryService> {
	public CPSubscriptionEntryServiceWrapper(
		CPSubscriptionEntryService cpSubscriptionEntryService) {
		_cpSubscriptionEntryService = cpSubscriptionEntryService;
	}

	@Override
	public void deleteCPSubscriptionEntry(long cpSubscriptionEntryId)
		throws com.liferay.portal.kernel.exception.PortalException {
		_cpSubscriptionEntryService.deleteCPSubscriptionEntry(cpSubscriptionEntryId);
	}

	@Override
	public com.liferay.commerce.model.CPSubscriptionEntry fetchCPSubscriptionEntry(
		long cpSubscriptionEntryId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _cpSubscriptionEntryService.fetchCPSubscriptionEntry(cpSubscriptionEntryId);
	}

	@Override
	public java.util.List<com.liferay.commerce.model.CPSubscriptionEntry> getCPSubscriptionEntries(
		long groupId, long userId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<com.liferay.commerce.model.CPSubscriptionEntry> orderByComparator)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _cpSubscriptionEntryService.getCPSubscriptionEntries(groupId,
			userId, start, end, orderByComparator);
	}

	@Override
	public int getCPSubscriptionEntriesCount(long groupId, long userId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _cpSubscriptionEntryService.getCPSubscriptionEntriesCount(groupId,
			userId);
	}

	/**
	* Returns the OSGi service identifier.
	*
	* @return the OSGi service identifier
	*/
	@Override
	public String getOSGiServiceIdentifier() {
		return _cpSubscriptionEntryService.getOSGiServiceIdentifier();
	}

	@Override
	public com.liferay.portal.kernel.search.BaseModelSearchResult<com.liferay.commerce.model.CPSubscriptionEntry> searchCPSubscriptionEntries(
		long companyId, long groupId, Boolean active, String keywords,
		int start, int end, com.liferay.portal.kernel.search.Sort sort)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _cpSubscriptionEntryService.searchCPSubscriptionEntries(companyId,
			groupId, active, keywords, start, end, sort);
	}

	@Override
	public com.liferay.commerce.model.CPSubscriptionEntry setActive(
		long cpSubscriptionEntryId, boolean active)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _cpSubscriptionEntryService.setActive(cpSubscriptionEntryId,
			active);
	}

	@Override
	public com.liferay.commerce.model.CPSubscriptionEntry updateCommercePriceEntry(
		long cpSubscriptionEntryId, long subscriptionCycleLength,
		String subscriptionCyclePeriod, long maxSubscriptionCyclesNumber,
		boolean active)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _cpSubscriptionEntryService.updateCommercePriceEntry(cpSubscriptionEntryId,
			subscriptionCycleLength, subscriptionCyclePeriod,
			maxSubscriptionCyclesNumber, active);
	}

	@Override
	public CPSubscriptionEntryService getWrappedService() {
		return _cpSubscriptionEntryService;
	}

	@Override
	public void setWrappedService(
		CPSubscriptionEntryService cpSubscriptionEntryService) {
		_cpSubscriptionEntryService = cpSubscriptionEntryService;
	}

	private CPSubscriptionEntryService _cpSubscriptionEntryService;
}