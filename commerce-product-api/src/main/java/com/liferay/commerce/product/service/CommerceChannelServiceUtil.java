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

import aQute.bnd.annotation.ProviderType;

import org.osgi.framework.Bundle;
import org.osgi.framework.FrameworkUtil;

import org.osgi.util.tracker.ServiceTracker;

/**
 * Provides the remote service utility for CommerceChannel. This utility wraps
 * {@link com.liferay.commerce.product.service.impl.CommerceChannelServiceImpl} and is the
 * primary access point for service operations in application layer code running
 * on a remote server. Methods of this service are expected to have security
 * checks based on the propagated JAAS credentials because this service can be
 * accessed remotely.
 *
 * @author Marco Leo
 * @see CommerceChannelService
 * @see com.liferay.commerce.product.service.base.CommerceChannelServiceBaseImpl
 * @see com.liferay.commerce.product.service.impl.CommerceChannelServiceImpl
 * @generated
 */
@ProviderType
public class CommerceChannelServiceUtil {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to {@link com.liferay.commerce.product.service.impl.CommerceChannelServiceImpl} and rerun ServiceBuilder to regenerate this class.
	 */
	public static com.liferay.commerce.product.model.CommerceChannel addCommerceChannel(
		java.util.Map<java.util.Locale, String> nameMap, String filterType,
		String type, String typeSettings,
		com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService()
				   .addCommerceChannel(nameMap, filterType, type, typeSettings,
			serviceContext);
	}

	public static com.liferay.commerce.product.model.CommerceChannel addCommerceChannel(
		String name, String filterType, String type, String typeSettings,
		com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService()
				   .addCommerceChannel(name, filterType, type, typeSettings,
			serviceContext);
	}

	public static com.liferay.commerce.product.model.CommerceChannel deleteCommerceChannel(
		long commerceChannelId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().deleteCommerceChannel(commerceChannelId);
	}

	public static com.liferay.commerce.product.model.CommerceChannel fetchCommerceChannel(
		long commerceChannelId) {
		return getService().fetchCommerceChannel(commerceChannelId);
	}

	public static com.liferay.commerce.product.model.CommerceChannel getCommerceChannel(
		long commerceChannelId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().getCommerceChannel(commerceChannelId);
	}

	public static java.util.List<com.liferay.commerce.product.model.CommerceChannel> getCommerceChannels(
		int start, int end)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().getCommerceChannels(start, end);
	}

	/**
	* Returns the OSGi service identifier.
	*
	* @return the OSGi service identifier
	*/
	public static String getOSGiServiceIdentifier() {
		return getService().getOSGiServiceIdentifier();
	}

	public static com.liferay.commerce.product.model.CommerceChannel updateCommerceChannel(
		long commerceChannelId,
		java.util.Map<java.util.Locale, String> nameMap, String filterType,
		String type, String typeSettings,
		com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService()
				   .updateCommerceChannel(commerceChannelId, nameMap,
			filterType, type, typeSettings, serviceContext);
	}

	public static CommerceChannelService getService() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker<CommerceChannelService, CommerceChannelService> _serviceTracker;

	static {
		Bundle bundle = FrameworkUtil.getBundle(CommerceChannelService.class);

		ServiceTracker<CommerceChannelService, CommerceChannelService> serviceTracker =
			new ServiceTracker<CommerceChannelService, CommerceChannelService>(bundle.getBundleContext(),
				CommerceChannelService.class, null);

		serviceTracker.open();

		_serviceTracker = serviceTracker;
	}
}