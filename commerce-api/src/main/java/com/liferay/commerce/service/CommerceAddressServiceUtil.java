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
 * Provides the remote service utility for CommerceAddress. This utility wraps
 * {@link com.liferay.commerce.service.impl.CommerceAddressServiceImpl} and is the
 * primary access point for service operations in application layer code running
 * on a remote server. Methods of this service are expected to have security
 * checks based on the propagated JAAS credentials because this service can be
 * accessed remotely.
 *
 * @author Alessio Antonio Rendina
 * @see CommerceAddressService
 * @see com.liferay.commerce.service.base.CommerceAddressServiceBaseImpl
 * @see com.liferay.commerce.service.impl.CommerceAddressServiceImpl
 * @generated
 */
@ProviderType
public class CommerceAddressServiceUtil {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to {@link com.liferay.commerce.service.impl.CommerceAddressServiceImpl} and rerun ServiceBuilder to regenerate this class.
	 */
	public static com.liferay.commerce.model.CommerceAddress addCommerceAddress(
		String className, long classPK, String name, String description,
		String street1, String street2, String street3, String city,
		String zip, long commerceRegionId, long commerceCountryId,
		String phoneNumber, boolean defaultBilling, boolean defaultShipping,
		com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService()
				   .addCommerceAddress(className, classPK, name, description,
			street1, street2, street3, city, zip, commerceRegionId,
			commerceCountryId, phoneNumber, defaultBilling, defaultShipping,
			serviceContext);
	}

	public static void deleteCommerceAddress(long commerceAddressId)
		throws com.liferay.portal.kernel.exception.PortalException {
		getService().deleteCommerceAddress(commerceAddressId);
	}

	public static com.liferay.commerce.model.CommerceAddress fetchCommerceAddress(
		long commerceAddressId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().fetchCommerceAddress(commerceAddressId);
	}

	public static com.liferay.commerce.model.CommerceAddress getCommerceAddress(
		long commerceAddressId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().getCommerceAddress(commerceAddressId);
	}

	public static java.util.List<com.liferay.commerce.model.CommerceAddress> getCommerceAddresses(
		long groupId, String className, long classPK)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().getCommerceAddresses(groupId, className, classPK);
	}

	public static java.util.List<com.liferay.commerce.model.CommerceAddress> getCommerceAddresses(
		long groupId, String className, long classPK, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<com.liferay.commerce.model.CommerceAddress> orderByComparator)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService()
				   .getCommerceAddresses(groupId, className, classPK, start,
			end, orderByComparator);
	}

	public static java.util.List<com.liferay.commerce.model.CommerceAddress> getCommerceAddresses(
		String className, long classPK, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<com.liferay.commerce.model.CommerceAddress> orderByComparator)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService()
				   .getCommerceAddresses(className, classPK, start, end,
			orderByComparator);
	}

	public static int getCommerceAddressesCount(long groupId, String className,
		long classPK)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService()
				   .getCommerceAddressesCount(groupId, className, classPK);
	}

	public static int getCommerceAddressesCount(String className, long classPK)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().getCommerceAddressesCount(className, classPK);
	}

	/**
	* Returns the OSGi service identifier.
	*
	* @return the OSGi service identifier
	*/
	public static String getOSGiServiceIdentifier() {
		return getService().getOSGiServiceIdentifier();
	}

	public static com.liferay.portal.kernel.search.BaseModelSearchResult<com.liferay.commerce.model.CommerceAddress> searchCommerceAddresses(
		long companyId, long groupId, String className, long classPK,
		String keywords, int start, int end,
		com.liferay.portal.kernel.search.Sort sort)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService()
				   .searchCommerceAddresses(companyId, groupId, className,
			classPK, keywords, start, end, sort);
	}

	public static com.liferay.commerce.model.CommerceAddress updateCommerceAddress(
		long commerceAddressId, String name, String description,
		String street1, String street2, String street3, String city,
		String zip, long commerceRegionId, long commerceCountryId,
		String phoneNumber, boolean defaultBilling, boolean defaultShipping,
		com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService()
				   .updateCommerceAddress(commerceAddressId, name, description,
			street1, street2, street3, city, zip, commerceRegionId,
			commerceCountryId, phoneNumber, defaultBilling, defaultShipping,
			serviceContext);
	}

	public static CommerceAddressService getService() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker<CommerceAddressService, CommerceAddressService> _serviceTracker;

	static {
		Bundle bundle = FrameworkUtil.getBundle(CommerceAddressService.class);

		ServiceTracker<CommerceAddressService, CommerceAddressService> serviceTracker =
			new ServiceTracker<CommerceAddressService, CommerceAddressService>(bundle.getBundleContext(),
				CommerceAddressService.class, null);

		serviceTracker.open();

		_serviceTracker = serviceTracker;
	}
}