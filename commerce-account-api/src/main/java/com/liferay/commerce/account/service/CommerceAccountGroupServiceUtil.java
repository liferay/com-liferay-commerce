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

package com.liferay.commerce.account.service;

import aQute.bnd.annotation.ProviderType;

import org.osgi.framework.Bundle;
import org.osgi.framework.FrameworkUtil;

import org.osgi.util.tracker.ServiceTracker;

/**
 * Provides the remote service utility for CommerceAccountGroup. This utility wraps
 * {@link com.liferay.commerce.account.service.impl.CommerceAccountGroupServiceImpl} and is the
 * primary access point for service operations in application layer code running
 * on a remote server. Methods of this service are expected to have security
 * checks based on the propagated JAAS credentials because this service can be
 * accessed remotely.
 *
 * @author Marco Leo
 * @see CommerceAccountGroupService
 * @see com.liferay.commerce.account.service.base.CommerceAccountGroupServiceBaseImpl
 * @see com.liferay.commerce.account.service.impl.CommerceAccountGroupServiceImpl
 * @generated
 */
@ProviderType
public class CommerceAccountGroupServiceUtil {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to {@link com.liferay.commerce.account.service.impl.CommerceAccountGroupServiceImpl} and rerun ServiceBuilder to regenerate this class.
	 */
	public static com.liferay.commerce.account.model.CommerceAccountGroup getCommerceAccountGroup(
		long commerceAccountGroupId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().getCommerceAccountGroup(commerceAccountGroupId);
	}

	/**
	* Returns the OSGi service identifier.
	*
	* @return the OSGi service identifier
	*/
	public static String getOSGiServiceIdentifier() {
		return getService().getOSGiServiceIdentifier();
	}

	public static CommerceAccountGroupService getService() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker<CommerceAccountGroupService, CommerceAccountGroupService> _serviceTracker;

	static {
		Bundle bundle = FrameworkUtil.getBundle(CommerceAccountGroupService.class);

		ServiceTracker<CommerceAccountGroupService, CommerceAccountGroupService> serviceTracker =
			new ServiceTracker<CommerceAccountGroupService, CommerceAccountGroupService>(bundle.getBundleContext(),
				CommerceAccountGroupService.class, null);

		serviceTracker.open();

		_serviceTracker = serviceTracker;
	}
}