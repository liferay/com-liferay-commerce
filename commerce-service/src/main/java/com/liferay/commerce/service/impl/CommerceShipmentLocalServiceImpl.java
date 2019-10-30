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

import com.liferay.commerce.constants.CommerceOrderConstants;
import com.liferay.commerce.constants.CommerceShipmentConstants;
import com.liferay.commerce.exception.CommerceShipmentExpectedDateException;
import com.liferay.commerce.exception.CommerceShipmentShippingDateException;
import com.liferay.commerce.exception.CommerceShipmentStatusException;
import com.liferay.commerce.model.CommerceOrder;
import com.liferay.commerce.model.CommerceOrderItem;
import com.liferay.commerce.model.CommerceShipment;
import com.liferay.commerce.model.CommerceShipmentItem;
import com.liferay.commerce.service.base.CommerceShipmentLocalServiceBaseImpl;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.model.SystemEventConstants;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.search.Indexable;
import com.liferay.portal.kernel.search.IndexableType;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.systemevent.SystemEvent;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.PortalUtil;

import java.util.Date;
import java.util.List;

/**
 * @author Alessio Antonio Rendina
 */
public class CommerceShipmentLocalServiceImpl
	extends CommerceShipmentLocalServiceBaseImpl {

	@Indexable(type = IndexableType.REINDEX)
	@Override
	public CommerceShipment addCommerceShipment(
			long commerceOrderId, ServiceContext serviceContext)
		throws PortalException {

		User user = userLocalService.getUser(serviceContext.getUserId());

		CommerceOrder commerceOrder =
			commerceOrderLocalService.getCommerceOrder(commerceOrderId);

		long commerceShipmentId = counterLocalService.increment();

		CommerceShipment commerceShipment = commerceShipmentPersistence.create(
			commerceShipmentId);

		commerceShipment.setGroupId(commerceOrder.getGroupId());
		commerceShipment.setCompanyId(user.getCompanyId());
		commerceShipment.setUserId(user.getUserId());
		commerceShipment.setUserName(user.getFullName());
		commerceShipment.setCommerceAccountId(
			commerceOrder.getCommerceAccountId());
		commerceShipment.setShippingName(commerceOrder.getShippingName());
		commerceShipment.setShippingDescription(
			commerceOrder.getShippingDescription());
		commerceShipment.setShippingStreet1(commerceOrder.getShippingStreet1());
		commerceShipment.setShippingStreet2(commerceOrder.getShippingStreet2());
		commerceShipment.setShippingStreet3(commerceOrder.getShippingStreet3());
		commerceShipment.setShippingCity(commerceOrder.getShippingCity());
		commerceShipment.setShippingZip(commerceOrder.getShippingZip());
		commerceShipment.setShippingRegionId(
			commerceOrder.getShippingRegionId());
		commerceShipment.setShippingCountryId(
			commerceOrder.getShippingCountryId());
		commerceShipment.setShippingPhoneNumber(
			commerceOrder.getShippingPhoneNumber());
		commerceShipment.setCommerceShippingMethodId(
			commerceOrder.getCommerceShippingMethodId());
		commerceShipment.setShippingOptionName(
			commerceOrder.getShippingOptionName());
		commerceShipment.setStatus(
			CommerceShipmentConstants.SHIPMENT_STATUS_PROCESSING);

		return commerceShipmentPersistence.update(commerceShipment);
	}

	@Indexable(type = IndexableType.DELETE)
	@Override
	@SystemEvent(type = SystemEventConstants.TYPE_DELETE)
	public CommerceShipment deleteCommerceShipment(
		CommerceShipment commerceShipment) {

		// Commerce shipment items

		commerceShipmentItemLocalService.deleteCommerceShipmentItems(
			commerceShipment.getCommerceShipmentId());

		// Commerce shipment

		commerceShipmentPersistence.remove(commerceShipment);

		return commerceShipment;
	}

	@Override
	public CommerceShipment deleteCommerceShipment(long commerceShipmentId)
		throws PortalException {

		CommerceShipment commerceShipment =
			commerceShipmentPersistence.findByPrimaryKey(commerceShipmentId);

		return commerceShipmentLocalService.deleteCommerceShipment(
			commerceShipment);
	}

	@Override
	public List<CommerceShipment> getCommerceShipments(
		long[] groupIds, int status, int start, int end,
		OrderByComparator<CommerceShipment> orderByComparator) {

		return commerceShipmentPersistence.findByG_S(
			groupIds, status, start, end, orderByComparator);
	}

	@Override
	public List<CommerceShipment> getCommerceShipments(
		long[] groupIds, int start, int end,
		OrderByComparator<CommerceShipment> orderByComparator) {

		return commerceShipmentPersistence.findByGroupIds(
			groupIds, start, end, orderByComparator);
	}

	@Override
	public int getCommerceShipmentsCount(long[] groupIds) {
		return commerceShipmentPersistence.countByGroupIds(groupIds);
	}

	@Override
	public int getCommerceShipmentsCount(long[] groupIds, int status) {
		return commerceShipmentPersistence.countByG_S(groupIds, status);
	}

	@Override
	public CommerceShipment updateCommerceShipment(
			long commerceShipmentId, String carrier, String trackingNumber,
			int status, int shippingDateMonth, int shippingDateDay,
			int shippingDateYear, int shippingDateHour, int shippingDateMinute,
			int expectedDateMonth, int expectedDateDay, int expectedDateYear,
			int expectedDateHour, int expectedDateMinute)
		throws PortalException {

		CommerceShipment commerceShipment =
			commerceShipmentPersistence.findByPrimaryKey(commerceShipmentId);

		String name = commerceShipment.getShippingName();
		String description = commerceShipment.getShippingDescription();
		String street1 = commerceShipment.getShippingStreet1();
		String street2 = commerceShipment.getShippingStreet2();
		String street3 = commerceShipment.getShippingStreet3();
		String city = commerceShipment.getShippingCity();
		String zip = commerceShipment.getShippingZip();
		long commerceRegionId = commerceShipment.getShippingRegionId();
		long commerceCountryId = commerceShipment.getShippingCountryId();
		String phoneNumber = commerceShipment.getShippingPhoneNumber();

		return commerceShipmentLocalService.updateCommerceShipment(
			commerceShipmentId, name, description, street1, street2, street3,
			city, zip, commerceRegionId, commerceCountryId, phoneNumber,
			carrier, trackingNumber, status, shippingDateMonth, shippingDateDay,
			shippingDateYear, shippingDateHour, shippingDateMinute,
			expectedDateMonth, expectedDateDay, expectedDateYear,
			expectedDateHour, expectedDateMinute);
	}

	@Indexable(type = IndexableType.REINDEX)
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

		// Commerce shipment

		CommerceShipment commerceShipment =
			commerceShipmentPersistence.findByPrimaryKey(commerceShipmentId);

		User user = userLocalService.getUser(commerceShipment.getUserId());

		int oldStatus = commerceShipment.getStatus();

		validate(status, oldStatus);

		Date shippingDate = PortalUtil.getDate(
			shippingDateMonth, shippingDateDay, shippingDateYear,
			shippingDateHour, shippingDateMinute, user.getTimeZone(),
			CommerceShipmentShippingDateException.class);

		Date expectedDate = PortalUtil.getDate(
			expectedDateMonth, expectedDateDay, expectedDateYear,
			expectedDateHour, expectedDateMinute, user.getTimeZone(),
			CommerceShipmentExpectedDateException.class);

		commerceShipment.setShippingName(name);
		commerceShipment.setShippingDescription(description);
		commerceShipment.setShippingStreet1(street1);
		commerceShipment.setShippingStreet2(street2);
		commerceShipment.setShippingStreet3(street3);
		commerceShipment.setShippingCity(city);
		commerceShipment.setShippingZip(zip);
		commerceShipment.setShippingRegionId(commerceRegionId);
		commerceShipment.setShippingCountryId(commerceCountryId);
		commerceShipment.setShippingPhoneNumber(phoneNumber);

		commerceShipment.setCarrier(carrier);
		commerceShipment.setTrackingNumber(trackingNumber);
		commerceShipment.setShippingDate(shippingDate);
		commerceShipment.setExpectedDate(expectedDate);
		commerceShipment.setStatus(status);

		commerceShipmentPersistence.update(commerceShipment);

		// Commerce order

		updateCommerceOrderStatus(
			commerceShipment.getCommerceShipmentId(), status, oldStatus);

		return commerceShipment;
	}

	protected void updateCommerceOrderStatus(
			long commerceShipmentId, int status, int oldStatus)
		throws PortalException {

		if (status <= oldStatus) {
			return;
		}

		List<CommerceShipmentItem> commerceShipmentItems =
			commerceShipmentItemLocalService.getCommerceShipmentItems(
				commerceShipmentId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);

		if (commerceShipmentItems.isEmpty()) {
			return;
		}

		CommerceShipmentItem commerceShipmentItem = commerceShipmentItems.get(
			0);

		CommerceOrderItem commerceOrderItem =
			commerceShipmentItem.fetchCommerceOrderItem();

		if (commerceOrderItem == null) {
			return;
		}

		long commerceOrderId = commerceOrderItem.getCommerceOrderId();

		List<CommerceOrderItem> commerceOrderItems =
			commerceOrderItemLocalService.
				getAvailableForShipmentCommerceOrderItems(commerceOrderId);

		if (status ==
				CommerceShipmentConstants.SHIPMENT_STATUS_READY_TO_BE_SHIPPED) {

			commerceOrderLocalService.updateOrderStatus(
				commerceOrderId,
				CommerceOrderConstants.ORDER_STATUS_AWAITING_SHIPMENT);
		}
		else if (status == CommerceShipmentConstants.SHIPMENT_STATUS_SHIPPED) {
			if (commerceOrderItems.isEmpty()) {
				commerceOrderLocalService.updateOrderStatus(
					commerceOrderId,
					CommerceOrderConstants.ORDER_STATUS_SHIPPED);
			}
			else {
				commerceOrderLocalService.updateOrderStatus(
					commerceOrderId,
					CommerceOrderConstants.ORDER_STATUS_PARTIALLY_SHIPPED);
			}
		}
		else if (status ==
					CommerceShipmentConstants.SHIPMENT_STATUS_DELIVERED) {

			if (commerceOrderItems.isEmpty()) {
				commerceOrderLocalService.updateOrderStatus(
					commerceOrderId,
					CommerceOrderConstants.ORDER_STATUS_COMPLETED);
			}
		}
	}

	protected void validate(int status, int oldStatus) throws PortalException {
		if (status < oldStatus) {
			throw new CommerceShipmentStatusException();
		}
	}

}