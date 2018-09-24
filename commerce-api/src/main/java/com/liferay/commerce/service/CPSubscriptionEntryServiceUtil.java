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
 * Provides the remote service utility for CPSubscriptionEntry. This utility wraps
 * {@link com.liferay.commerce.service.impl.CPSubscriptionEntryServiceImpl} and is the
 * primary access point for service operations in application layer code running
 * on a remote server. Methods of this service are expected to have security
 * checks based on the propagated JAAS credentials because this service can be
 * accessed remotely.
 *
 * @author Alessio Antonio Rendina
 * @see CPSubscriptionEntryService
 * @see com.liferay.commerce.service.base.CPSubscriptionEntryServiceBaseImpl
 * @see com.liferay.commerce.service.impl.CPSubscriptionEntryServiceImpl
 * @generated
 */
@ProviderType
public class CPSubscriptionEntryServiceUtil {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to {@link com.liferay.commerce.service.impl.CPSubscriptionEntryServiceImpl} and rerun ServiceBuilder to regenerate this class.
	 */
	public static void deleteCPSubscriptionEntry(long cpSubscriptionEntryId)
		throws com.liferay.portal.kernel.exception.PortalException {
		getService().deleteCPSubscriptionEntry(cpSubscriptionEntryId);
	}

	public static com.liferay.commerce.model.CPSubscriptionEntry fetchCPSubscriptionEntry(
		long cpSubscriptionEntryId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().fetchCPSubscriptionEntry(cpSubscriptionEntryId);
	}

	public static java.util.List<com.liferay.commerce.model.CPSubscriptionEntry> getCPSubscriptionEntries(
		long groupId, long userId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<com.liferay.commerce.model.CPSubscriptionEntry> orderByComparator)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService()
				   .getCPSubscriptionEntries(groupId, userId, start, end,
			orderByComparator);
	}

	public static int getCPSubscriptionEntriesCount(long groupId, long userId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().getCPSubscriptionEntriesCount(groupId, userId);
	}

	/**
	* Returns the OSGi service identifier.
	*
	* @return the OSGi service identifier
	*/
	public static String getOSGiServiceIdentifier() {
		return getService().getOSGiServiceIdentifier();
	}

	public static com.liferay.portal.kernel.search.BaseModelSearchResult<com.liferay.commerce.model.CPSubscriptionEntry> searchCPSubscriptionEntries(
		long companyId, long groupId, Boolean active, String keywords,
		int start, int end, com.liferay.portal.kernel.search.Sort sort)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService()
				   .searchCPSubscriptionEntries(companyId, groupId, active,
			keywords, start, end, sort);
	}

	public static com.liferay.commerce.model.CPSubscriptionEntry setActive(
		long cpSubscriptionEntryId, boolean active)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().setActive(cpSubscriptionEntryId, active);
	}

	public static com.liferay.commerce.model.CPSubscriptionEntry updateCommercePriceEntry(
		long cpSubscriptionEntryId, long subscriptionCycleLength,
		String subscriptionCyclePeriod, long maxSubscriptionCyclesNumber,
		boolean active)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService()
				   .updateCommercePriceEntry(cpSubscriptionEntryId,
			subscriptionCycleLength, subscriptionCyclePeriod,
			maxSubscriptionCyclesNumber, active);
	}

	public static CPSubscriptionEntryService getService() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker<CPSubscriptionEntryService, CPSubscriptionEntryService> _serviceTracker;

	static {
		Bundle bundle = FrameworkUtil.getBundle(CPSubscriptionEntryService.class);

		ServiceTracker<CPSubscriptionEntryService, CPSubscriptionEntryService> serviceTracker =
			new ServiceTracker<CPSubscriptionEntryService, CPSubscriptionEntryService>(bundle.getBundleContext(),
				CPSubscriptionEntryService.class, null);

		serviceTracker.open();

		_serviceTracker = serviceTracker;
	}
}