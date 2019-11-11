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

package com.liferay.commerce.bom.service;

import aQute.bnd.annotation.ProviderType;

import org.osgi.framework.Bundle;
import org.osgi.framework.FrameworkUtil;

import org.osgi.util.tracker.ServiceTracker;

/**
 * Provides the remote service utility for CommerceBOMEntry. This utility wraps
 * {@link com.liferay.commerce.bom.service.impl.CommerceBOMEntryServiceImpl} and is the
 * primary access point for service operations in application layer code running
 * on a remote server. Methods of this service are expected to have security
 * checks based on the propagated JAAS credentials because this service can be
 * accessed remotely.
 *
 * @author Luca Pellizzon
 * @see CommerceBOMEntryService
 * @see com.liferay.commerce.bom.service.base.CommerceBOMEntryServiceBaseImpl
 * @see com.liferay.commerce.bom.service.impl.CommerceBOMEntryServiceImpl
 * @generated
 */
@ProviderType
public class CommerceBOMEntryServiceUtil {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to {@link com.liferay.commerce.bom.service.impl.CommerceBOMEntryServiceImpl} and rerun ServiceBuilder to regenerate this class.
	 */
	public static com.liferay.commerce.bom.model.CommerceBOMEntry addCommerceBOMEntry(
		long userId, int number, String cpInstanceUuid, long cProductId,
		long commerceBOMDefinitionId, double positionX, double positionY,
		double radius)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService()
				   .addCommerceBOMEntry(userId, number, cpInstanceUuid,
			cProductId, commerceBOMDefinitionId, positionX, positionY, radius);
	}

	public static void deleteCommerceBOMEntry(long commerceBOMEntryId)
		throws com.liferay.portal.kernel.exception.PortalException {
		getService().deleteCommerceBOMEntry(commerceBOMEntryId);
	}

	public static java.util.List<com.liferay.commerce.bom.model.CommerceBOMEntry> getCommerceBOMEntries(
		long commerceBOMDefinitionId, int start, int end)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService()
				   .getCommerceBOMEntries(commerceBOMDefinitionId, start, end);
	}

	public static int getCommerceBOMEntriesCount(long commerceBOMDefinitionId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().getCommerceBOMEntriesCount(commerceBOMDefinitionId);
	}

	public static com.liferay.commerce.bom.model.CommerceBOMEntry getCommerceBOMEntry(
		long commerceBOMEntryId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().getCommerceBOMEntry(commerceBOMEntryId);
	}

	/**
	* Returns the OSGi service identifier.
	*
	* @return the OSGi service identifier
	*/
	public static String getOSGiServiceIdentifier() {
		return getService().getOSGiServiceIdentifier();
	}

	public static com.liferay.commerce.bom.model.CommerceBOMEntry updateCommerceBOMEntry(
		long commerceBOMEntryId, int number, String cpInstanceUuid,
		long cProductId, double positionX, double positionY, double radius)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService()
				   .updateCommerceBOMEntry(commerceBOMEntryId, number,
			cpInstanceUuid, cProductId, positionX, positionY, radius);
	}

	public static CommerceBOMEntryService getService() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker<CommerceBOMEntryService, CommerceBOMEntryService> _serviceTracker;

	static {
		Bundle bundle = FrameworkUtil.getBundle(CommerceBOMEntryService.class);

		ServiceTracker<CommerceBOMEntryService, CommerceBOMEntryService> serviceTracker =
			new ServiceTracker<CommerceBOMEntryService, CommerceBOMEntryService>(bundle.getBundleContext(),
				CommerceBOMEntryService.class, null);

		serviceTracker.open();

		_serviceTracker = serviceTracker;
	}
}