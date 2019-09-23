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

package com.liferay.commerce.product.service;

import org.osgi.framework.Bundle;
import org.osgi.framework.FrameworkUtil;
import org.osgi.util.tracker.ServiceTracker;

/**
 * Provides the remote service utility for CommerceChannelRel. This utility wraps
 * <code>com.liferay.commerce.product.service.impl.CommerceChannelRelServiceImpl</code> and is an
 * access point for service operations in application layer code running on a
 * remote server. Methods of this service are expected to have security checks
 * based on the propagated JAAS credentials because this service can be
 * accessed remotely.
 *
 * @author Marco Leo
 * @see CommerceChannelRelService
 * @generated
 */
public class CommerceChannelRelServiceUtil {

	/**
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to <code>com.liferay.commerce.product.service.impl.CommerceChannelRelServiceImpl</code> and rerun ServiceBuilder to regenerate this class.
	 */

	/**
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link CommerceChannelRelServiceUtil} to access the commerce channel rel remote service. Add custom service methods to <code>com.liferay.commerce.product.service.impl.CommerceChannelRelServiceImpl</code> and rerun ServiceBuilder to automatically copy the method declarations to this interface.
	 */
	public static com.liferay.commerce.product.model.CommerceChannelRel
			addCommerceChannelRel(
				String className, long classPK, long commerceChannelId,
				com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {

		return getService().addCommerceChannelRel(
			className, classPK, commerceChannelId, serviceContext);
	}

	public static void deleteCommerceChannelRels(
		String className, long classPK) {

		getService().deleteCommerceChannelRels(className, classPK);
	}

	public static java.util.List
		<com.liferay.commerce.product.model.CommerceChannelRel>
			getCommerceChannelRels(
				long commerceChannelId, int start, int end,
				com.liferay.portal.kernel.util.OrderByComparator
					<com.liferay.commerce.product.model.CommerceChannelRel>
						orderByComparator) {

		return getService().getCommerceChannelRels(
			commerceChannelId, start, end, orderByComparator);
	}

	public static java.util.List
		<com.liferay.commerce.product.model.CommerceChannelRel>
			getCommerceChannelRels(
				String className, long classPK, int start, int end,
				com.liferay.portal.kernel.util.OrderByComparator
					<com.liferay.commerce.product.model.CommerceChannelRel>
						orderByComparator) {

		return getService().getCommerceChannelRels(
			className, classPK, start, end, orderByComparator);
	}

	public static int getCommerceChannelRelsCount(long commerceChannelId) {
		return getService().getCommerceChannelRelsCount(commerceChannelId);
	}

	public static int getCommerceChannelRelsCount(
		String className, long classPK) {

		return getService().getCommerceChannelRelsCount(className, classPK);
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	public static String getOSGiServiceIdentifier() {
		return getService().getOSGiServiceIdentifier();
	}

	public static CommerceChannelRelService getService() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker
		<CommerceChannelRelService, CommerceChannelRelService> _serviceTracker;

	static {
		Bundle bundle = FrameworkUtil.getBundle(
			CommerceChannelRelService.class);

		ServiceTracker<CommerceChannelRelService, CommerceChannelRelService>
			serviceTracker =
				new ServiceTracker
					<CommerceChannelRelService, CommerceChannelRelService>(
						bundle.getBundleContext(),
						CommerceChannelRelService.class, null);

		serviceTracker.open();

		_serviceTracker = serviceTracker;
	}

}