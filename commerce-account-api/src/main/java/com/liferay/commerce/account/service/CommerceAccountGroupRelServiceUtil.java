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
 * Provides the remote service utility for CommerceAccountGroupRel. This utility wraps
 * {@link com.liferay.commerce.account.service.impl.CommerceAccountGroupRelServiceImpl} and is the
 * primary access point for service operations in application layer code running
 * on a remote server. Methods of this service are expected to have security
 * checks based on the propagated JAAS credentials because this service can be
 * accessed remotely.
 *
 * @author Marco Leo
 * @see CommerceAccountGroupRelService
 * @see com.liferay.commerce.account.service.base.CommerceAccountGroupRelServiceBaseImpl
 * @see com.liferay.commerce.account.service.impl.CommerceAccountGroupRelServiceImpl
 * @generated
 */
@ProviderType
public class CommerceAccountGroupRelServiceUtil {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to {@link com.liferay.commerce.account.service.impl.CommerceAccountGroupRelServiceImpl} and rerun ServiceBuilder to regenerate this class.
	 */
	public static com.liferay.commerce.account.model.CommerceAccountGroupRel addCommerceAccountGroupRel(
		String className, long classPK, long commerceAccountGroupId,
		com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService()
				   .addCommerceAccountGroupRel(className, classPK,
			commerceAccountGroupId, serviceContext);
	}

	public static void deleteCommerceAccountGroupRels(String className,
		long classPK) {
		getService().deleteCommerceAccountGroupRels(className, classPK);
	}

	public static java.util.List<com.liferay.commerce.account.model.CommerceAccountGroupRel> getCommerceAccountGroupRels(
		long commerceAccountGroupId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<com.liferay.commerce.account.model.CommerceAccountGroupRel> orderByComparator) {
		return getService()
				   .getCommerceAccountGroupRels(commerceAccountGroupId, start,
			end, orderByComparator);
	}

	public static java.util.List<com.liferay.commerce.account.model.CommerceAccountGroupRel> getCommerceAccountGroupRels(
		String className, long classPK, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<com.liferay.commerce.account.model.CommerceAccountGroupRel> orderByComparator) {
		return getService()
				   .getCommerceAccountGroupRels(className, classPK, start, end,
			orderByComparator);
	}

	public static int getCommerceAccountGroupRelsCount(
		long commerceAccountGroupId) {
		return getService()
				   .getCommerceAccountGroupRelsCount(commerceAccountGroupId);
	}

	public static int getCommerceAccountGroupRelsCount(String className,
		long classPK) {
		return getService().getCommerceAccountGroupRelsCount(className, classPK);
	}

	/**
	* Returns the OSGi service identifier.
	*
	* @return the OSGi service identifier
	*/
	public static String getOSGiServiceIdentifier() {
		return getService().getOSGiServiceIdentifier();
	}

	public static CommerceAccountGroupRelService getService() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker<CommerceAccountGroupRelService, CommerceAccountGroupRelService> _serviceTracker;

	static {
		Bundle bundle = FrameworkUtil.getBundle(CommerceAccountGroupRelService.class);

		ServiceTracker<CommerceAccountGroupRelService, CommerceAccountGroupRelService> serviceTracker =
			new ServiceTracker<CommerceAccountGroupRelService, CommerceAccountGroupRelService>(bundle.getBundleContext(),
				CommerceAccountGroupRelService.class, null);

		serviceTracker.open();

		_serviceTracker = serviceTracker;
	}
}