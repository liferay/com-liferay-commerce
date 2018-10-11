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

import org.osgi.framework.Bundle;
import org.osgi.framework.FrameworkUtil;

import org.osgi.util.tracker.ServiceTracker;

/**
 * Provides the remote service utility for CommerceSubscriptionEntry. This utility wraps
 * {@link com.liferay.commerce.service.impl.CommerceSubscriptionEntryServiceImpl} and is the
 * primary access point for service operations in application layer code running
 * on a remote server. Methods of this service are expected to have security
 * checks based on the propagated JAAS credentials because this service can be
 * accessed remotely.
 *
 * @author Alessio Antonio Rendina
 * @see CommerceSubscriptionEntryService
 * @see com.liferay.commerce.service.base.CommerceSubscriptionEntryServiceBaseImpl
 * @see com.liferay.commerce.service.impl.CommerceSubscriptionEntryServiceImpl
 * @generated
 */
@ProviderType
public class CommerceSubscriptionEntryServiceUtil {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to {@link com.liferay.commerce.service.impl.CommerceSubscriptionEntryServiceImpl} and rerun ServiceBuilder to regenerate this class.
	 */
	public static void deleteCommerceSubscriptionEntry(
		long commerceSubscriptionEntryId)
		throws com.liferay.portal.kernel.exception.PortalException {
		getService().deleteCommerceSubscriptionEntry(commerceSubscriptionEntryId);
	}

	public static com.liferay.commerce.model.CommerceSubscriptionEntry fetchCommerceSubscriptionEntry(
		long commerceSubscriptionEntryId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService()
				   .fetchCommerceSubscriptionEntry(commerceSubscriptionEntryId);
	}

	public static com.liferay.portal.kernel.search.BaseModelSearchResult<com.liferay.commerce.model.CommerceSubscriptionEntry> getCommerceSubscriptionEntries(
		long companyId, long groupId, Boolean active, String keywords,
		int start, int end, com.liferay.portal.kernel.search.Sort sort)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService()
				   .getCommerceSubscriptionEntries(companyId, groupId, active,
			keywords, start, end, sort);
	}

	public static java.util.List<com.liferay.commerce.model.CommerceSubscriptionEntry> getCommerceSubscriptionEntries(
		long groupId, long userId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<com.liferay.commerce.model.CommerceSubscriptionEntry> orderByComparator)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService()
				   .getCommerceSubscriptionEntries(groupId, userId, start, end,
			orderByComparator);
	}

	public static int getCommerceSubscriptionEntriesCount(long groupId,
		long userId) throws com.liferay.portal.kernel.exception.PortalException {
		return getService().getCommerceSubscriptionEntriesCount(groupId, userId);
	}

	/**
	* Returns the OSGi service identifier.
	*
	* @return the OSGi service identifier
	*/
	public static String getOSGiServiceIdentifier() {
		return getService().getOSGiServiceIdentifier();
	}

	public static com.liferay.commerce.model.CommerceSubscriptionEntry setActive(
		long commerceSubscriptionEntryId, boolean active)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().setActive(commerceSubscriptionEntryId, active);
	}

	public static com.liferay.commerce.model.CommerceSubscriptionEntry updateCommercePriceEntry(
		long commerceSubscriptionEntryId, long subscriptionCycleLength,
		String subscriptionCyclePeriod, long maxSubscriptionCyclesNumber,
		boolean active)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService()
				   .updateCommercePriceEntry(commerceSubscriptionEntryId,
			subscriptionCycleLength, subscriptionCyclePeriod,
			maxSubscriptionCyclesNumber, active);
	}

	public static CommerceSubscriptionEntryService getService() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker<CommerceSubscriptionEntryService, CommerceSubscriptionEntryService> _serviceTracker;

	static {
		Bundle bundle = FrameworkUtil.getBundle(CommerceSubscriptionEntryService.class);

		ServiceTracker<CommerceSubscriptionEntryService, CommerceSubscriptionEntryService> serviceTracker =
			new ServiceTracker<CommerceSubscriptionEntryService, CommerceSubscriptionEntryService>(bundle.getBundleContext(),
				CommerceSubscriptionEntryService.class, null);

		serviceTracker.open();

		_serviceTracker = serviceTracker;
	}
}