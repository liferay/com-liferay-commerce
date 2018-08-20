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

import com.liferay.portal.kernel.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link CommerceShipmentService}.
 *
 * @author Alessio Antonio Rendina
 * @see CommerceShipmentService
 * @generated
 */
@ProviderType
public class CommerceShipmentServiceWrapper implements CommerceShipmentService,
	ServiceWrapper<CommerceShipmentService> {
	public CommerceShipmentServiceWrapper(
		CommerceShipmentService commerceShipmentService) {
		_commerceShipmentService = commerceShipmentService;
	}

	@Override
	public com.liferay.commerce.model.CommerceShipment addCommerceShipment(
		long commerceOrderId,
		com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _commerceShipmentService.addCommerceShipment(commerceOrderId,
			serviceContext);
	}

	@Override
	public void deleteCommerceShipment(long commerceShipmentId)
		throws com.liferay.portal.kernel.exception.PortalException {
		_commerceShipmentService.deleteCommerceShipment(commerceShipmentId);
	}

	@Override
	public com.liferay.commerce.model.CommerceShipment getCommerceShipment(
		long commerceShipmentId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _commerceShipmentService.getCommerceShipment(commerceShipmentId);
	}

	@Override
	public java.util.List<com.liferay.commerce.model.CommerceShipment> getCommerceShipmentsByG_S(
		long groupId, int status, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<com.liferay.commerce.model.CommerceShipment> orderByComparator)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _commerceShipmentService.getCommerceShipmentsByG_S(groupId,
			status, start, end, orderByComparator);
	}

	@Override
	public java.util.List<com.liferay.commerce.model.CommerceShipment> getCommerceShipmentsByGroupId(
		long groupId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<com.liferay.commerce.model.CommerceShipment> orderByComparator)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _commerceShipmentService.getCommerceShipmentsByGroupId(groupId,
			start, end, orderByComparator);
	}

	@Override
	public java.util.List<com.liferay.commerce.model.CommerceShipment> getCommerceShipmentsByS_S(
		long siteGroupId, int status, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<com.liferay.commerce.model.CommerceShipment> orderByComparator)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _commerceShipmentService.getCommerceShipmentsByS_S(siteGroupId,
			status, start, end, orderByComparator);
	}

	@Override
	public java.util.List<com.liferay.commerce.model.CommerceShipment> getCommerceShipmentsBySiteGroupId(
		long siteGroupId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<com.liferay.commerce.model.CommerceShipment> orderByComparator)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _commerceShipmentService.getCommerceShipmentsBySiteGroupId(siteGroupId,
			start, end, orderByComparator);
	}

	@Override
	public int getCommerceShipmentsCountByG_S(long groupId, int status)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _commerceShipmentService.getCommerceShipmentsCountByG_S(groupId,
			status);
	}

	@Override
	public int getCommerceShipmentsCountByGroupId(long groupId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _commerceShipmentService.getCommerceShipmentsCountByGroupId(groupId);
	}

	@Override
	public int getCommerceShipmentsCountByS_S(long siteGroupId, int status)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _commerceShipmentService.getCommerceShipmentsCountByS_S(siteGroupId,
			status);
	}

	@Override
	public int getCommerceShipmentsCountBySiteGroupId(long siteGroupId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _commerceShipmentService.getCommerceShipmentsCountBySiteGroupId(siteGroupId);
	}

	/**
	* Returns the OSGi service identifier.
	*
	* @return the OSGi service identifier
	*/
	@Override
	public String getOSGiServiceIdentifier() {
		return _commerceShipmentService.getOSGiServiceIdentifier();
	}

	@Override
	public com.liferay.commerce.model.CommerceShipment updateCommerceShipment(
		long commerceShipmentId, String carrier, String trackingNumber,
		int status, int shippingDateMonth, int shippingDateDay,
		int shippingDateYear, int shippingDateHour, int shippingDateMinute,
		int expectedDateMonth, int expectedDateDay, int expectedDateYear,
		int expectedDateHour, int expectedDateMinute)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _commerceShipmentService.updateCommerceShipment(commerceShipmentId,
			carrier, trackingNumber, status, shippingDateMonth,
			shippingDateDay, shippingDateYear, shippingDateHour,
			shippingDateMinute, expectedDateMonth, expectedDateDay,
			expectedDateYear, expectedDateHour, expectedDateMinute);
	}

	@Override
	public com.liferay.commerce.model.CommerceShipment updateCommerceShipment(
		long commerceShipmentId, String name, String description,
		String street1, String street2, String street3, String city,
		String zip, long commerceRegionId, long commerceCountryId,
		String phoneNumber, String carrier, String trackingNumber, int status,
		int shippingDateMonth, int shippingDateDay, int shippingDateYear,
		int shippingDateHour, int shippingDateMinute, int expectedDateMonth,
		int expectedDateDay, int expectedDateYear, int expectedDateHour,
		int expectedDateMinute)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _commerceShipmentService.updateCommerceShipment(commerceShipmentId,
			name, description, street1, street2, street3, city, zip,
			commerceRegionId, commerceCountryId, phoneNumber, carrier,
			trackingNumber, status, shippingDateMonth, shippingDateDay,
			shippingDateYear, shippingDateHour, shippingDateMinute,
			expectedDateMonth, expectedDateDay, expectedDateYear,
			expectedDateHour, expectedDateMinute);
	}

	@Override
	public CommerceShipmentService getWrappedService() {
		return _commerceShipmentService;
	}

	@Override
	public void setWrappedService(
		CommerceShipmentService commerceShipmentService) {
		_commerceShipmentService = commerceShipmentService;
	}

	private CommerceShipmentService _commerceShipmentService;
}