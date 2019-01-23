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
 * Provides the remote service utility for CommerceAccountUserRel. This utility wraps
 * {@link com.liferay.commerce.account.service.impl.CommerceAccountUserRelServiceImpl} and is the
 * primary access point for service operations in application layer code running
 * on a remote server. Methods of this service are expected to have security
 * checks based on the propagated JAAS credentials because this service can be
 * accessed remotely.
 *
 * @author Marco Leo
 * @see CommerceAccountUserRelService
 * @see com.liferay.commerce.account.service.base.CommerceAccountUserRelServiceBaseImpl
 * @see com.liferay.commerce.account.service.impl.CommerceAccountUserRelServiceImpl
 * @generated
 */
@ProviderType
public class CommerceAccountUserRelServiceUtil {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to {@link com.liferay.commerce.account.service.impl.CommerceAccountUserRelServiceImpl} and rerun ServiceBuilder to regenerate this class.
	 */
	public static void addCommerceAccountUserRels(long commerceAccountId,
		long[] userIds, String[] emailAddresses, long[] roleIds,
		com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {
		getService()
			.addCommerceAccountUserRels(commerceAccountId, userIds,
			emailAddresses, roleIds, serviceContext);
	}

	public static void deleteCommerceAccountUserRel(long commerceAccountId,
		long userId) throws com.liferay.portal.kernel.exception.PortalException {
		getService().deleteCommerceAccountUserRel(commerceAccountId, userId);
	}

	public static void deleteCommerceAccountUserRels(long commerceAccountId)
		throws com.liferay.portal.kernel.exception.PortalException {
		getService().deleteCommerceAccountUserRels(commerceAccountId);
	}

	public static void deleteCommerceAccountUserRels(long commerceAccountId,
		long[] userIds)
		throws com.liferay.portal.kernel.exception.PortalException {
		getService().deleteCommerceAccountUserRels(commerceAccountId, userIds);
	}

	public static java.util.List<com.liferay.commerce.account.model.CommerceAccountUserRel> getCommerceAccountUserRels(
		long commerceAccountId, int start, int end)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService()
				   .getCommerceAccountUserRels(commerceAccountId, start, end);
	}

	public static int getCommerceAccountUserRelsCount(long commerceAccountId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().getCommerceAccountUserRelsCount(commerceAccountId);
	}

	/**
	* Returns the OSGi service identifier.
	*
	* @return the OSGi service identifier
	*/
	public static String getOSGiServiceIdentifier() {
		return getService().getOSGiServiceIdentifier();
	}

	public static CommerceAccountUserRelService getService() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker<CommerceAccountUserRelService, CommerceAccountUserRelService> _serviceTracker;

	static {
		Bundle bundle = FrameworkUtil.getBundle(CommerceAccountUserRelService.class);

		ServiceTracker<CommerceAccountUserRelService, CommerceAccountUserRelService> serviceTracker =
			new ServiceTracker<CommerceAccountUserRelService, CommerceAccountUserRelService>(bundle.getBundleContext(),
				CommerceAccountUserRelService.class, null);

		serviceTracker.open();

		_serviceTracker = serviceTracker;
	}
}