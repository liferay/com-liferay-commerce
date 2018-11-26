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
 * Provides a wrapper for {@link CommerceSubscriptionEntryService}.
 *
 * @author Alessio Antonio Rendina
 * @see CommerceSubscriptionEntryService
 * @generated
 */
@ProviderType
public class CommerceSubscriptionEntryServiceWrapper
	implements CommerceSubscriptionEntryService,
		ServiceWrapper<CommerceSubscriptionEntryService> {
	public CommerceSubscriptionEntryServiceWrapper(
		CommerceSubscriptionEntryService commerceSubscriptionEntryService) {
		_commerceSubscriptionEntryService = commerceSubscriptionEntryService;
	}

	@Override
	public void deleteCommerceSubscriptionEntry(
		long commerceSubscriptionEntryId)
		throws com.liferay.portal.kernel.exception.PortalException {
		_commerceSubscriptionEntryService.deleteCommerceSubscriptionEntry(commerceSubscriptionEntryId);
	}

	@Override
	public com.liferay.commerce.model.CommerceSubscriptionEntry fetchCommerceSubscriptionEntry(
		long commerceSubscriptionEntryId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _commerceSubscriptionEntryService.fetchCommerceSubscriptionEntry(commerceSubscriptionEntryId);
	}

	@Override
	public java.util.List<com.liferay.commerce.model.CommerceSubscriptionEntry> getCommerceSubscriptionEntries(
		long groupId, long userId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<com.liferay.commerce.model.CommerceSubscriptionEntry> orderByComparator)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _commerceSubscriptionEntryService.getCommerceSubscriptionEntries(groupId,
			userId, start, end, orderByComparator);
	}

	@Override
	public com.liferay.portal.kernel.search.BaseModelSearchResult<com.liferay.commerce.model.CommerceSubscriptionEntry> getCommerceSubscriptionEntries(
		long companyId, long groupId, Long maxSubscriptionCyclesNumber,
		Boolean active, String keywords, int start, int end,
		com.liferay.portal.kernel.search.Sort sort)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _commerceSubscriptionEntryService.getCommerceSubscriptionEntries(companyId,
			groupId, maxSubscriptionCyclesNumber, active, keywords, start, end,
			sort);
	}

	@Override
	public int getCommerceSubscriptionEntriesCount(long groupId, long userId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _commerceSubscriptionEntryService.getCommerceSubscriptionEntriesCount(groupId,
			userId);
	}

	/**
	* Returns the OSGi service identifier.
	*
	* @return the OSGi service identifier
	*/
	@Override
	public String getOSGiServiceIdentifier() {
		return _commerceSubscriptionEntryService.getOSGiServiceIdentifier();
	}

	@Override
	public com.liferay.commerce.model.CommerceSubscriptionEntry setActive(
		long commerceSubscriptionEntryId, boolean active)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _commerceSubscriptionEntryService.setActive(commerceSubscriptionEntryId,
			active);
	}

	@Override
	public com.liferay.commerce.model.CommerceSubscriptionEntry updateCommercePriceEntry(
		long commerceSubscriptionEntryId, long subscriptionCycleLength,
		String subscriptionCyclePeriod, long maxSubscriptionCyclesNumber,
		boolean active)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _commerceSubscriptionEntryService.updateCommercePriceEntry(commerceSubscriptionEntryId,
			subscriptionCycleLength, subscriptionCyclePeriod,
			maxSubscriptionCyclesNumber, active);
	}

	@Override
	public CommerceSubscriptionEntryService getWrappedService() {
		return _commerceSubscriptionEntryService;
	}

	@Override
	public void setWrappedService(
		CommerceSubscriptionEntryService commerceSubscriptionEntryService) {
		_commerceSubscriptionEntryService = commerceSubscriptionEntryService;
	}

	private CommerceSubscriptionEntryService _commerceSubscriptionEntryService;
}