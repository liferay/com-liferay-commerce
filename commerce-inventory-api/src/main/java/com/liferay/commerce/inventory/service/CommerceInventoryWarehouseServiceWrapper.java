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

import com.liferay.portal.kernel.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link CommerceInventoryWarehouseService}.
 *
 * @author Luca Pellizzon
 * @see CommerceInventoryWarehouseService
 * @generated
 */
@ProviderType
public class CommerceInventoryWarehouseServiceWrapper
	implements CommerceInventoryWarehouseService,
		ServiceWrapper<CommerceInventoryWarehouseService> {
	public CommerceInventoryWarehouseServiceWrapper(
		CommerceInventoryWarehouseService commerceInventoryWarehouseService) {
		_commerceInventoryWarehouseService = commerceInventoryWarehouseService;
	}

	@Override
	public com.liferay.commerce.inventory.model.CommerceInventoryWarehouse addCommerceWarehouse(
		String name, String description, boolean active, String street1,
		String street2, String street3, String city, String zip,
		String commerceRegionCode, String commerceCountryCode, double latitude,
		double longitude,
		com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _commerceInventoryWarehouseService.addCommerceWarehouse(name,
			description, active, street1, street2, street3, city, zip,
			commerceRegionCode, commerceCountryCode, latitude, longitude,
			serviceContext);
	}

	@Override
	public com.liferay.commerce.inventory.model.CommerceInventoryWarehouse addCommerceWarehouseAndGroupRel(
		String name, String description, boolean active, String street1,
		String street2, String street3, String city, String zip,
		String commerceRegionCode, String commerceCountryCode, double latitude,
		double longitude,
		com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _commerceInventoryWarehouseService.addCommerceWarehouseAndGroupRel(name,
			description, active, street1, street2, street3, city, zip,
			commerceRegionCode, commerceCountryCode, latitude, longitude,
			serviceContext);
	}

	@Override
	public com.liferay.commerce.inventory.model.CommerceInventoryWarehouse deleteCommerceWarehouse(
		long commerceWarehouseId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _commerceInventoryWarehouseService.deleteCommerceWarehouse(commerceWarehouseId);
	}

	@Override
	public com.liferay.commerce.inventory.model.CommerceInventoryWarehouse fetchDefaultCommerceWarehouse(
		long groupId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _commerceInventoryWarehouseService.fetchDefaultCommerceWarehouse(groupId);
	}

	@Override
	public com.liferay.commerce.inventory.model.CommerceInventoryWarehouse geolocateCommerceWarehouse(
		long commerceWarehouseId, double latitude, double longitude)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _commerceInventoryWarehouseService.geolocateCommerceWarehouse(commerceWarehouseId,
			latitude, longitude);
	}

	@Override
	public com.liferay.commerce.inventory.model.CommerceInventoryWarehouse getCommerceWarehouse(
		long commerceWarehouseId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _commerceInventoryWarehouseService.getCommerceWarehouse(commerceWarehouseId);
	}

	/**
	* Returns the OSGi service identifier.
	*
	* @return the OSGi service identifier
	*/
	@Override
	public String getOSGiServiceIdentifier() {
		return _commerceInventoryWarehouseService.getOSGiServiceIdentifier();
	}

	@Override
	public com.liferay.commerce.inventory.model.CommerceInventoryWarehouse setActive(
		long commerceWarehouseId, boolean active)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _commerceInventoryWarehouseService.setActive(commerceWarehouseId,
			active);
	}

	@Override
	public com.liferay.commerce.inventory.model.CommerceInventoryWarehouse updateCommerceWarehouse(
		long commerceWarehouseId, String name, String description,
		boolean active, String street1, String street2, String street3,
		String city, String zip, String commerceRegionCode,
		String commerceCountryCode, double latitude, double longitude,
		com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _commerceInventoryWarehouseService.updateCommerceWarehouse(commerceWarehouseId,
			name, description, active, street1, street2, street3, city, zip,
			commerceRegionCode, commerceCountryCode, latitude, longitude,
			serviceContext);
	}

	@Override
	public com.liferay.commerce.inventory.model.CommerceInventoryWarehouse updateDefaultCommerceWarehouse(
		String name, String street1, String street2, String street3,
		String city, String zip, String commerceRegionCode,
		String commerceCountryCode, double latitude, double longitude,
		com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _commerceInventoryWarehouseService.updateDefaultCommerceWarehouse(name,
			street1, street2, street3, city, zip, commerceRegionCode,
			commerceCountryCode, latitude, longitude, serviceContext);
	}

	@Override
	public CommerceInventoryWarehouseService getWrappedService() {
		return _commerceInventoryWarehouseService;
	}

	@Override
	public void setWrappedService(
		CommerceInventoryWarehouseService commerceInventoryWarehouseService) {
		_commerceInventoryWarehouseService = commerceInventoryWarehouseService;
	}

	private CommerceInventoryWarehouseService _commerceInventoryWarehouseService;
}