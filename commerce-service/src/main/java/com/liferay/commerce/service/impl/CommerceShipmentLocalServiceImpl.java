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
import com.liferay.commerce.model.CommerceAddress;
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
import com.liferay.portal.kernel.service.ServiceContextThreadLocal;
import com.liferay.portal.kernel.systemevent.SystemEvent;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.PortalUtil;

import java.util.Date;
import java.util.List;
import java.util.Objects;

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
		commerceShipment.setCommerceAddressId(
			commerceOrder.getShippingAddressId());
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

		String name = null;
		String description = null;
		String street1 = null;
		String street2 = null;
		String street3 = null;
		String city = null;
		String zip = null;
		long commerceRegionId = 0;
		long commerceCountryId = 0;
		String phoneNumber = null;

		CommerceShipment commerceShipment =
			commerceShipmentPersistence.findByPrimaryKey(commerceShipmentId);

		CommerceAddress commerceAddress =
			commerceShipment.fetchCommerceAddress();

		if (commerceAddress != null) {
			name = commerceAddress.getName();
			description = commerceAddress.getDescription();
			street1 = commerceAddress.getStreet1();
			street2 = commerceAddress.getStreet2();
			street3 = commerceAddress.getStreet3();
			city = commerceAddress.getCity();
			zip = commerceAddress.getZip();
			commerceRegionId = commerceAddress.getCommerceRegionId();
			commerceCountryId = commerceAddress.getCommerceCountryId();
			phoneNumber = commerceAddress.getPhoneNumber();
		}

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

		CommerceAddress commerceAddress = updateCommerceShipmentAddress(
			commerceShipment, name, description, street1, street2, street3,
			city, zip, commerceRegionId, commerceCountryId, phoneNumber);

		commerceShipment.setCommerceAddressId(
			commerceAddress.getCommerceAddressId());

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

	protected CommerceAddress updateCommerceShipmentAddress(
			CommerceShipment commerceShipment, String name, String description,
			String street1, String street2, String street3, String city,
			String zip, long commerceRegionId, long commerceCountryId,
			String phoneNumber)
		throws PortalException {

		CommerceAddress commerceAddress =
			commerceShipment.fetchCommerceAddress();

		if (Objects.equals(name, commerceAddress.getName()) &&
			Objects.equals(description, commerceAddress.getDescription()) &&
			Objects.equals(street1, commerceAddress.getStreet1()) &&
			Objects.equals(street2, commerceAddress.getStreet2()) &&
			Objects.equals(street3, commerceAddress.getStreet3()) &&
			Objects.equals(city, commerceAddress.getCity()) &&
			Objects.equals(zip, commerceAddress.getZip()) &&
			Objects.equals(
				commerceRegionId, commerceAddress.getCommerceRegionId()) &&
			Objects.equals(
				commerceCountryId, commerceAddress.getCommerceCountryId()) &&
			Objects.equals(phoneNumber, commerceAddress.getPhoneNumber())) {

			return commerceAddress;
		}

		return commerceAddressLocalService.addCommerceAddress(
			commerceShipment.getModelClassName(),
			commerceShipment.getCommerceShipmentId(), name, description,
			street1, street2, street3, city, zip, commerceRegionId,
			commerceCountryId, phoneNumber, false, false,
			ServiceContextThreadLocal.getServiceContext());
	}

	protected void validate(int status, int oldStatus) throws PortalException {
		if (status < oldStatus) {
			throw new CommerceShipmentStatusException();
		}
	}

}