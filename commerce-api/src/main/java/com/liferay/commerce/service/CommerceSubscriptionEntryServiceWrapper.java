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

import com.liferay.portal.kernel.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link CommerceSubscriptionEntryService}.
 *
 * @author Alessio Antonio Rendina
 * @see CommerceSubscriptionEntryService
 * @generated
 */
public class CommerceSubscriptionEntryServiceWrapper
	implements CommerceSubscriptionEntryService,
			   ServiceWrapper<CommerceSubscriptionEntryService> {

	public CommerceSubscriptionEntryServiceWrapper(
		CommerceSubscriptionEntryService commerceSubscriptionEntryService) {

		_commerceSubscriptionEntryService = commerceSubscriptionEntryService;
	}

	/**
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link CommerceSubscriptionEntryServiceUtil} to access the commerce subscription entry remote service. Add custom service methods to <code>com.liferay.commerce.service.impl.CommerceSubscriptionEntryServiceImpl</code> and rerun ServiceBuilder to automatically copy the method declarations to this interface.
	 */
	@Override
	public void deleteCommerceSubscriptionEntry(
			long commerceSubscriptionEntryId)
		throws com.liferay.portal.kernel.exception.PortalException {

		_commerceSubscriptionEntryService.deleteCommerceSubscriptionEntry(
			commerceSubscriptionEntryId);
	}

	@Override
	public com.liferay.commerce.model.CommerceSubscriptionEntry
			fetchCommerceSubscriptionEntry(long commerceSubscriptionEntryId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _commerceSubscriptionEntryService.fetchCommerceSubscriptionEntry(
			commerceSubscriptionEntryId);
	}

	@Override
	public java.util.List<com.liferay.commerce.model.CommerceSubscriptionEntry>
			getCommerceSubscriptionEntries(
				long companyId, long userId, int start, int end,
				com.liferay.portal.kernel.util.OrderByComparator
					<com.liferay.commerce.model.CommerceSubscriptionEntry>
						orderByComparator)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _commerceSubscriptionEntryService.getCommerceSubscriptionEntries(
			companyId, userId, start, end, orderByComparator);
	}

	@Override
	public java.util.List<com.liferay.commerce.model.CommerceSubscriptionEntry>
			getCommerceSubscriptionEntries(
				long companyId, long groupId, long userId, int start, int end,
				com.liferay.portal.kernel.util.OrderByComparator
					<com.liferay.commerce.model.CommerceSubscriptionEntry>
						orderByComparator)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _commerceSubscriptionEntryService.getCommerceSubscriptionEntries(
			companyId, groupId, userId, start, end, orderByComparator);
	}

	@Override
	public int getCommerceSubscriptionEntriesCount(long companyId, long userId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _commerceSubscriptionEntryService.
			getCommerceSubscriptionEntriesCount(companyId, userId);
	}

	@Override
	public int getCommerceSubscriptionEntriesCount(
			long companyId, long groupId, long userId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _commerceSubscriptionEntryService.
			getCommerceSubscriptionEntriesCount(companyId, groupId, userId);
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
	public com.liferay.portal.kernel.search.BaseModelSearchResult
		<com.liferay.commerce.model.CommerceSubscriptionEntry>
				searchCommerceSubscriptionEntries(
					long companyId, Long maxSubscriptionCycles,
					Integer subscriptionStatus, String keywords, int start,
					int end, com.liferay.portal.kernel.search.Sort sort)
			throws com.liferay.portal.kernel.exception.PortalException {

		return _commerceSubscriptionEntryService.
			searchCommerceSubscriptionEntries(
				companyId, maxSubscriptionCycles, subscriptionStatus, keywords,
				start, end, sort);
	}

	@Override
	public com.liferay.portal.kernel.search.BaseModelSearchResult
		<com.liferay.commerce.model.CommerceSubscriptionEntry>
				searchCommerceSubscriptionEntries(
					long companyId, long groupId, Long maxSubscriptionCycles,
					Integer subscriptionStatus, String keywords, int start,
					int end, com.liferay.portal.kernel.search.Sort sort)
			throws com.liferay.portal.kernel.exception.PortalException {

		return _commerceSubscriptionEntryService.
			searchCommerceSubscriptionEntries(
				companyId, groupId, maxSubscriptionCycles, subscriptionStatus,
				keywords, start, end, sort);
	}

	@Override
	public com.liferay.commerce.model.CommerceSubscriptionEntry
			updateCommerceSubscriptionEntry(
				long commerceSubscriptionEntryId, int subscriptionLength,
				String subscriptionType,
				com.liferay.portal.kernel.util.UnicodeProperties
					subscriptionTypeSettingsProperties,
				long maxSubscriptionCycles, int subscriptionStatus,
				int startDateMonth, int startDateDay, int startDateYear,
				int startDateHour, int startDateMinute,
				int nextIterationDateMonth, int nextIterationDateDay,
				int nextIterationDateYear, int nextIterationDateHour,
				int nextIterationDateMinute)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _commerceSubscriptionEntryService.
			updateCommerceSubscriptionEntry(
				commerceSubscriptionEntryId, subscriptionLength,
				subscriptionType, subscriptionTypeSettingsProperties,
				maxSubscriptionCycles, subscriptionStatus, startDateMonth,
				startDateDay, startDateYear, startDateHour, startDateMinute,
				nextIterationDateMonth, nextIterationDateDay,
				nextIterationDateYear, nextIterationDateHour,
				nextIterationDateMinute);
	}

	@Override
	public com.liferay.commerce.model.CommerceSubscriptionEntry
			updateSubscriptionStatus(
				long commerceSubscriptionEntryId, int subscriptionStatus)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _commerceSubscriptionEntryService.updateSubscriptionStatus(
			commerceSubscriptionEntryId, subscriptionStatus);
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