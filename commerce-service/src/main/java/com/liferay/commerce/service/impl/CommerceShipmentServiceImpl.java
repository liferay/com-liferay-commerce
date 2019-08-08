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

package com.liferay.commerce.service.impl;

import com.liferay.commerce.constants.CommerceActionKeys;
import com.liferay.commerce.model.CommerceShipment;
import com.liferay.commerce.product.model.CommerceChannel;
import com.liferay.commerce.product.service.CommerceChannelService;
import com.liferay.commerce.service.base.CommerceShipmentServiceBaseImpl;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.service.permission.PortalPermissionUtil;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.spring.extender.service.ServiceReference;

import java.util.List;
import java.util.stream.Stream;

/**
 * @author Alessio Antonio Rendina
 */
public class CommerceShipmentServiceImpl
	extends CommerceShipmentServiceBaseImpl {

	@Override
	public CommerceShipment addCommerceShipment(
			long commerceOrderId, ServiceContext serviceContext)
		throws PortalException {

		PortalPermissionUtil.check(
			getPermissionChecker(),
			CommerceActionKeys.MANAGE_COMMERCE_SHIPMENTS);

		return commerceShipmentLocalService.addCommerceShipment(
			commerceOrderId, serviceContext);
	}

	@Override
	public void deleteCommerceShipment(long commerceShipmentId)
		throws PortalException {

		CommerceShipment commerceShipment =
			commerceShipmentPersistence.findByPrimaryKey(commerceShipmentId);

		PortalPermissionUtil.check(
			getPermissionChecker(),
			CommerceActionKeys.MANAGE_COMMERCE_SHIPMENTS);

		commerceShipmentLocalService.deleteCommerceShipment(commerceShipment);
	}

	@Override
	public CommerceShipment getCommerceShipment(long commerceShipmentId)
		throws PortalException {

		PortalPermissionUtil.check(
			getPermissionChecker(),
			CommerceActionKeys.MANAGE_COMMERCE_SHIPMENTS);

		return commerceShipmentLocalService.getCommerceShipment(
			commerceShipmentId);
	}

	@Override
	public List<CommerceShipment> getCommerceShipments(
			long companyId, int status, int start, int end,
			OrderByComparator<CommerceShipment> orderByComparator)
		throws PortalException {

		PortalPermissionUtil.check(
			getPermissionChecker(),
			CommerceActionKeys.MANAGE_COMMERCE_SHIPMENTS);

		List<CommerceChannel> commerceChannels =
			_commerceChannelService.searchCommerceChannels(companyId);

		Stream<CommerceChannel> stream = commerceChannels.stream();

		long[] commerceChannelGroupIds = stream.mapToLong(
			CommerceChannel::getGroupId
		).toArray();

		return commerceShipmentLocalService.getCommerceShipments(
			commerceChannelGroupIds, status, start, end, orderByComparator);
	}

	@Override
	public List<CommerceShipment> getCommerceShipments(
			long companyId, int start, int end,
			OrderByComparator<CommerceShipment> orderByComparator)
		throws PortalException {

		PortalPermissionUtil.check(
			getPermissionChecker(),
			CommerceActionKeys.MANAGE_COMMERCE_SHIPMENTS);

		List<CommerceChannel> commerceChannels =
			_commerceChannelService.searchCommerceChannels(companyId);

		Stream<CommerceChannel> stream = commerceChannels.stream();

		long[] commerceChannelGroupIds = stream.mapToLong(
			CommerceChannel::getGroupId
		).toArray();

		return commerceShipmentLocalService.getCommerceShipments(
			commerceChannelGroupIds, start, end, orderByComparator);
	}

	@Override
	public int getCommerceShipmentsCount(long companyId)
		throws PortalException {

		PortalPermissionUtil.check(
			getPermissionChecker(),
			CommerceActionKeys.MANAGE_COMMERCE_SHIPMENTS);

		List<CommerceChannel> commerceChannels =
			_commerceChannelService.searchCommerceChannels(companyId);

		if (commerceChannels.isEmpty()) {
			return 0;
		}

		Stream<CommerceChannel> stream = commerceChannels.stream();

		long[] commerceChannelGroupIds = stream.mapToLong(
			CommerceChannel::getGroupId
		).toArray();

		return commerceShipmentLocalService.getCommerceShipmentsCount(
			commerceChannelGroupIds);
	}

	@Override
	public int getCommerceShipmentsCount(long companyId, int status)
		throws PortalException {

		PortalPermissionUtil.check(
			getPermissionChecker(),
			CommerceActionKeys.MANAGE_COMMERCE_SHIPMENTS);

		List<CommerceChannel> commerceChannels =
			_commerceChannelService.searchCommerceChannels(companyId);

		Stream<CommerceChannel> stream = commerceChannels.stream();

		long[] commerceChannelGroupIds = stream.mapToLong(
			CommerceChannel::getGroupId
		).toArray();

		return commerceShipmentLocalService.getCommerceShipmentsCount(
			commerceChannelGroupIds, status);
	}

	@Override
	public CommerceShipment updateCommerceShipment(
			long commerceShipmentId, String carrier, String trackingNumber,
			int status, int shippingDateMonth, int shippingDateDay,
			int shippingDateYear, int shippingDateHour, int shippingDateMinute,
			int expectedDateMonth, int expectedDateDay, int expectedDateYear,
			int expectedDateHour, int expectedDateMinute)
		throws PortalException {

		PortalPermissionUtil.check(
			getPermissionChecker(),
			CommerceActionKeys.MANAGE_COMMERCE_SHIPMENTS);

		return commerceShipmentLocalService.updateCommerceShipment(
			commerceShipmentId, carrier, trackingNumber, status,
			shippingDateMonth, shippingDateDay, shippingDateYear,
			shippingDateHour, shippingDateMinute, expectedDateMonth,
			expectedDateDay, expectedDateYear, expectedDateHour,
			expectedDateMinute);
	}

	@Override
	public CommerceShipment updateCommerceShipment(
			long commerceShipmentId, String name, String description,
			String street1, String street2, String street3, String city,
			String zip, long commerceRegionId, long commerceCountryId,
			String phoneNumber, String carrier, String trackingNumber,
			int status, int shippingDateMonth, int shippingDateDay,
			int shippingDateYear, int shippingDateHour, int shippingDateMinute,
			int expectedDateMonth, int expectedDateDay, int expectedDateYear,
			int expectedDateHour, int expectedDateMinute)
		throws PortalException {

		PortalPermissionUtil.check(
			getPermissionChecker(),
			CommerceActionKeys.MANAGE_COMMERCE_SHIPMENTS);

		return commerceShipmentLocalService.updateCommerceShipment(
			commerceShipmentId, name, description, street1, street2, street3,
			city, zip, commerceRegionId, commerceCountryId, phoneNumber,
			carrier, trackingNumber, status, shippingDateMonth, shippingDateDay,
			shippingDateYear, shippingDateHour, shippingDateMinute,
			expectedDateMonth, expectedDateDay, expectedDateYear,
			expectedDateHour, expectedDateMinute);
	}

	@ServiceReference(type = CommerceChannelService.class)
	private CommerceChannelService _commerceChannelService;

}