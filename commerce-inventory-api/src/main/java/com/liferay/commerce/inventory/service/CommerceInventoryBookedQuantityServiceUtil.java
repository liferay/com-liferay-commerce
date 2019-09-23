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

package com.liferay.commerce.inventory.service;

import org.osgi.framework.Bundle;
import org.osgi.framework.FrameworkUtil;
import org.osgi.util.tracker.ServiceTracker;

/**
 * Provides the remote service utility for CommerceInventoryBookedQuantity. This utility wraps
 * <code>com.liferay.commerce.inventory.service.impl.CommerceInventoryBookedQuantityServiceImpl</code> and is an
 * access point for service operations in application layer code running on a
 * remote server. Methods of this service are expected to have security checks
 * based on the propagated JAAS credentials because this service can be
 * accessed remotely.
 *
 * @author Luca Pellizzon
 * @see CommerceInventoryBookedQuantityService
 * @generated
 */
public class CommerceInventoryBookedQuantityServiceUtil {

	/**
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to <code>com.liferay.commerce.inventory.service.impl.CommerceInventoryBookedQuantityServiceImpl</code> and rerun ServiceBuilder to regenerate this class.
	 */

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	public static String getOSGiServiceIdentifier() {
		return getService().getOSGiServiceIdentifier();
	}

	public static CommerceInventoryBookedQuantityService getService() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker
		<CommerceInventoryBookedQuantityService,
		 CommerceInventoryBookedQuantityService> _serviceTracker;

	static {
		Bundle bundle = FrameworkUtil.getBundle(
			CommerceInventoryBookedQuantityService.class);

		ServiceTracker
			<CommerceInventoryBookedQuantityService,
			 CommerceInventoryBookedQuantityService> serviceTracker =
				new ServiceTracker
					<CommerceInventoryBookedQuantityService,
					 CommerceInventoryBookedQuantityService>(
						 bundle.getBundleContext(),
						 CommerceInventoryBookedQuantityService.class, null);

		serviceTracker.open();

		_serviceTracker = serviceTracker;
	}

}