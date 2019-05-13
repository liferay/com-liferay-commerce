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

import aQute.bnd.annotation.ProviderType;

import org.osgi.framework.Bundle;
import org.osgi.framework.FrameworkUtil;

import org.osgi.util.tracker.ServiceTracker;

/**
 * Provides the remote service utility for CommerceInventoryWarehouse. This utility wraps
 * {@link com.liferay.commerce.inventory.service.impl.CommerceInventoryWarehouseServiceImpl} and is the
 * primary access point for service operations in application layer code running
 * on a remote server. Methods of this service are expected to have security
 * checks based on the propagated JAAS credentials because this service can be
 * accessed remotely.
 *
 * @author Luca Pellizzon
 * @see CommerceInventoryWarehouseService
 * @see com.liferay.commerce.inventory.service.base.CommerceInventoryWarehouseServiceBaseImpl
 * @see com.liferay.commerce.inventory.service.impl.CommerceInventoryWarehouseServiceImpl
 * @generated
 */
@ProviderType
public class CommerceInventoryWarehouseServiceUtil {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to {@link com.liferay.commerce.inventory.service.impl.CommerceInventoryWarehouseServiceImpl} and rerun ServiceBuilder to regenerate this class.
	 */
	public static com.liferay.commerce.inventory.model.CommerceInventoryWarehouse addCommerceWarehouse(
		String name, String description, boolean active, String street1,
		String street2, String street3, String city, String zip,
		String commerceRegionCode, String commerceCountryCode, double latitude,
		double longitude,
		com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService()
				   .addCommerceWarehouse(name, description, active, street1,
			street2, street3, city, zip, commerceRegionCode,
			commerceCountryCode, latitude, longitude, serviceContext);
	}

	public static com.liferay.commerce.inventory.model.CommerceInventoryWarehouse addCommerceWarehouseAndGroupRel(
		String name, String description, boolean active, String street1,
		String street2, String street3, String city, String zip,
		String commerceRegionCode, String commerceCountryCode, double latitude,
		double longitude,
		com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService()
				   .addCommerceWarehouseAndGroupRel(name, description, active,
			street1, street2, street3, city, zip, commerceRegionCode,
			commerceCountryCode, latitude, longitude, serviceContext);
	}

	public static com.liferay.commerce.inventory.model.CommerceInventoryWarehouse deleteCommerceWarehouse(
		long commerceWarehouseId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().deleteCommerceWarehouse(commerceWarehouseId);
	}

	public static com.liferay.commerce.inventory.model.CommerceInventoryWarehouse fetchDefaultCommerceWarehouse(
		long groupId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().fetchDefaultCommerceWarehouse(groupId);
	}

	public static com.liferay.commerce.inventory.model.CommerceInventoryWarehouse geolocateCommerceWarehouse(
		long commerceWarehouseId, double latitude, double longitude)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService()
				   .geolocateCommerceWarehouse(commerceWarehouseId, latitude,
			longitude);
	}

	public static com.liferay.commerce.inventory.model.CommerceInventoryWarehouse getCommerceWarehouse(
		long commerceWarehouseId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().getCommerceWarehouse(commerceWarehouseId);
	}

	/**
	* Returns the OSGi service identifier.
	*
	* @return the OSGi service identifier
	*/
	public static String getOSGiServiceIdentifier() {
		return getService().getOSGiServiceIdentifier();
	}

	public static com.liferay.commerce.inventory.model.CommerceInventoryWarehouse setActive(
		long commerceWarehouseId, boolean active)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().setActive(commerceWarehouseId, active);
	}

	public static com.liferay.commerce.inventory.model.CommerceInventoryWarehouse updateCommerceWarehouse(
		long commerceWarehouseId, String name, String description,
		boolean active, String street1, String street2, String street3,
		String city, String zip, String commerceRegionCode,
		String commerceCountryCode, double latitude, double longitude,
		com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService()
				   .updateCommerceWarehouse(commerceWarehouseId, name,
			description, active, street1, street2, street3, city, zip,
			commerceRegionCode, commerceCountryCode, latitude, longitude,
			serviceContext);
	}

	public static com.liferay.commerce.inventory.model.CommerceInventoryWarehouse updateDefaultCommerceWarehouse(
		String name, String street1, String street2, String street3,
		String city, String zip, String commerceRegionCode,
		String commerceCountryCode, double latitude, double longitude,
		com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService()
				   .updateDefaultCommerceWarehouse(name, street1, street2,
			street3, city, zip, commerceRegionCode, commerceCountryCode,
			latitude, longitude, serviceContext);
	}

	public static CommerceInventoryWarehouseService getService() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker<CommerceInventoryWarehouseService, CommerceInventoryWarehouseService> _serviceTracker;

	static {
		Bundle bundle = FrameworkUtil.getBundle(CommerceInventoryWarehouseService.class);

		ServiceTracker<CommerceInventoryWarehouseService, CommerceInventoryWarehouseService> serviceTracker =
			new ServiceTracker<CommerceInventoryWarehouseService, CommerceInventoryWarehouseService>(bundle.getBundleContext(),
				CommerceInventoryWarehouseService.class, null);

		serviceTracker.open();

		_serviceTracker = serviceTracker;
	}
}