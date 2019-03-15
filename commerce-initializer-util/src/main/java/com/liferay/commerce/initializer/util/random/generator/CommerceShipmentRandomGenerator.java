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

package com.liferay.commerce.initializer.util.random.generator;

import com.liferay.commerce.configuration.CommerceShippingGroupServiceConfiguration;
import com.liferay.commerce.constants.CommerceConstants;
import com.liferay.commerce.constants.CommerceShipmentConstants;
import com.liferay.commerce.model.CommerceOrder;
import com.liferay.commerce.model.CommerceOrderItem;
import com.liferay.commerce.model.CommerceShipment;
import com.liferay.commerce.model.CommerceWarehouse;
import com.liferay.commerce.service.CommerceOrderItemLocalService;
import com.liferay.commerce.service.CommerceOrderLocalService;
import com.liferay.commerce.service.CommerceShipmentItemLocalService;
import com.liferay.commerce.service.CommerceShipmentLocalService;
import com.liferay.commerce.service.CommerceWarehouseLocalService;
import com.liferay.commerce.util.comparator.CommerceWarehouseNameComparator;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.module.configuration.ConfigurationProvider;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.settings.GroupServiceSettingsLocator;
import com.liferay.portal.kernel.test.util.RandomTestUtil;
import com.liferay.portal.kernel.util.CalendarFactoryUtil;

import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Alessio Antonio Rendina
 */
@Component(service = CommerceShipmentRandomGenerator.class)
public class CommerceShipmentRandomGenerator {

	public void generate(long groupId, int shipmentsCount)
		throws PortalException {

		List<CommerceWarehouse> commerceWarehouses = _getCommerceWarehouses(
			groupId);

		if (commerceWarehouses.isEmpty()) {
			if (_log.isInfoEnabled()) {
				_log.info("There are no active warehouses");
			}

			return;
		}

		List<CommerceOrder> commerceOrders =
			_commerceOrderLocalService.getCommerceOrders(
				groupId, CommerceShipmentConstants.ALLOWED_ORDER_STATUSES);

		if (commerceOrders.isEmpty()) {
			if (_log.isInfoEnabled()) {
				_log.info("There are no commerceOrders ready to be shipped");
			}

			return;
		}

		for (int i = 0; i < shipmentsCount; i++) {
			List<CommerceOrderItem> commerceOrderItems =
				_getRandomAvailableForShipmentCommerceOrderItems(
					commerceOrders);

			if (commerceOrderItems == null) {
				continue;
			}

			_generateCommerceShipment(commerceOrderItems, commerceWarehouses);
		}
	}

	private Calendar _convertDateToCalendar(Date date, int days) {
		if (date == null) {
			date = new Date();
		}

		Calendar calendar = CalendarFactoryUtil.getCalendar();

		calendar.setTime(date);

		calendar.add(Calendar.DAY_OF_YEAR, days);

		return calendar;
	}

	private void _generateCommerceShipment(
			List<CommerceOrderItem> commerceOrderItems,
			List<CommerceWarehouse> commerceWarehouses)
		throws PortalException {

		if (commerceOrderItems.isEmpty()) {
			return;
		}

		CommerceOrderItem commerceOrderItem = commerceOrderItems.get(0);

		ServiceContext serviceContext = _getServiceContext(commerceOrderItem);

		CommerceShipment commerceShipment =
			_commerceShipmentLocalService.addCommerceShipment(
				commerceOrderItem.getCommerceOrderId(), serviceContext);

		_generateCommerceShipmentItems(
			commerceShipment.getCommerceShipmentId(), commerceOrderItems,
			commerceWarehouses, serviceContext);

		Calendar shippingDateCalendar = _convertDateToCalendar(
			commerceShipment.getShippingDate(), 0);
		Calendar expectedDateCalendar = _convertDateToCalendar(
			commerceShipment.getExpectedDate(),
			RandomTestUtil.randomInt(3, 10));

		_commerceShipmentLocalService.updateCommerceShipment(
			commerceShipment.getCommerceShipmentId(),
			commerceShipment.getCarrier(), commerceShipment.getTrackingNumber(),
			_getRandomCommerceShipmentStatus(),
			shippingDateCalendar.get(Calendar.MONTH),
			shippingDateCalendar.get(Calendar.DAY_OF_MONTH),
			shippingDateCalendar.get(Calendar.YEAR),
			shippingDateCalendar.get(Calendar.HOUR),
			shippingDateCalendar.get(Calendar.MINUTE),
			expectedDateCalendar.get(Calendar.MONTH),
			expectedDateCalendar.get(Calendar.DAY_OF_MONTH),
			expectedDateCalendar.get(Calendar.YEAR),
			expectedDateCalendar.get(Calendar.HOUR),
			expectedDateCalendar.get(Calendar.MINUTE));
	}

	private void _generateCommerceShipmentItems(
			long commerceShipmentId, List<CommerceOrderItem> commerceOrderItems,
			List<CommerceWarehouse> commerceWarehouses,
			ServiceContext serviceContext)
		throws PortalException {

		for (CommerceOrderItem commerceOrderItem : commerceOrderItems) {
			CommerceWarehouse commerceWarehouse = _getRandomCommerceWarehouse(
				commerceWarehouses);

			_commerceShipmentItemLocalService.addCommerceShipmentItem(
				commerceShipmentId, commerceOrderItem.getCommerceOrderItemId(),
				commerceWarehouse.getCommerceWarehouseId(),
				_getRandomCommerceWarehouseItemQuantity(
					commerceOrderItem, commerceWarehouse),
				serviceContext);
		}
	}

	private List<CommerceWarehouse> _getCommerceWarehouses(long groupId)
		throws PortalException {

		CommerceShippingGroupServiceConfiguration
			commerceShippingGroupServiceConfiguration =
				_configurationProvider.getConfiguration(
					CommerceShippingGroupServiceConfiguration.class,
					new GroupServiceSettingsLocator(
						groupId, CommerceConstants.SHIPPING_SERVICE_NAME));

		String commerceShippingOriginLocatorKey =
			commerceShippingGroupServiceConfiguration.
				commerceShippingOriginLocatorKey();

		if (commerceShippingOriginLocatorKey.equals("address")) {
			CommerceWarehouse commerceWarehouse =
				_commerceWarehouseLocalService.fetchDefaultCommerceWarehouse(
					groupId);

			if (commerceWarehouse == null) {
				return Collections.emptyList();
			}

			return Collections.singletonList(commerceWarehouse);
		}

		return _commerceWarehouseLocalService.getCommerceWarehouses(
			groupId, true, QueryUtil.ALL_POS, QueryUtil.ALL_POS,
			new CommerceWarehouseNameComparator(true));
	}

	private List<CommerceOrderItem>
		_getRandomAvailableForShipmentCommerceOrderItems(
			List<CommerceOrder> commerceOrders) {

		return _getRandomAvailableForShipmentCommerceOrderItems(
			commerceOrders, 1);
	}

	private List<CommerceOrderItem>
		_getRandomAvailableForShipmentCommerceOrderItems(
			List<CommerceOrder> commerceOrders, int attempt) {

		if (attempt > 10) {
			if (_log.isInfoEnabled()) {
				_log.info(
					"Unable to find available for shipment " +
						"CommerceOrderItems after 10 attempts");
			}

			return null;
		}

		CommerceOrder commerceOrder = _getRandomCommerceOrder(commerceOrders);

		List<CommerceOrderItem> availableForShipmentCommerceOrderItems =
			_commerceOrderItemLocalService.
				getAvailableForShipmentCommerceOrderItems(
					commerceOrder.getCommerceOrderId());

		if (!availableForShipmentCommerceOrderItems.isEmpty()) {
			return availableForShipmentCommerceOrderItems;
		}

		return _getRandomAvailableForShipmentCommerceOrderItems(
			commerceOrders, attempt++);
	}

	private CommerceOrder _getRandomCommerceOrder(
		List<CommerceOrder> commerceOrders) {

		return commerceOrders.get(_getRandomPosition(commerceOrders.size()));
	}

	private int _getRandomCommerceShipmentStatus() {
		int i = _getRandomPosition(
			CommerceShipmentConstants.SHIPMENT_STATUSES.length - 1);

		return CommerceShipmentConstants.SHIPMENT_STATUSES[i];
	}

	private CommerceWarehouse _getRandomCommerceWarehouse(
		List<CommerceWarehouse> commerceWarehouses) {

		return commerceWarehouses.get(
			_getRandomPosition(commerceWarehouses.size()));
	}

	private int _getRandomCommerceWarehouseItemQuantity(
			CommerceOrderItem commerceOrderItem,
			CommerceWarehouse commerceWarehouse)
		throws PortalException {

		int commerceWarehouseItemQuantity =
			_commerceOrderItemLocalService.getCommerceWarehouseItemQuantity(
				commerceOrderItem.getCommerceOrderItemId(),
				commerceWarehouse.getCommerceWarehouseId());

		return RandomTestUtil.randomInt(1, commerceWarehouseItemQuantity);
	}

	private int _getRandomPosition(int max) {
		return RandomTestUtil.randomInt(0, max);
	}

	private ServiceContext _getServiceContext(
		CommerceOrderItem commerceOrderItem) {

		ServiceContext serviceContext = new ServiceContext();

		serviceContext.setCompanyId(commerceOrderItem.getCompanyId());
		serviceContext.setScopeGroupId(commerceOrderItem.getGroupId());
		serviceContext.setUserId(commerceOrderItem.getUserId());

		return serviceContext;
	}

	private static final Log _log = LogFactoryUtil.getLog(
		CommerceShipmentRandomGenerator.class);

	@Reference
	private CommerceOrderItemLocalService _commerceOrderItemLocalService;

	@Reference
	private CommerceOrderLocalService _commerceOrderLocalService;

	@Reference
	private CommerceShipmentItemLocalService _commerceShipmentItemLocalService;

	@Reference
	private CommerceShipmentLocalService _commerceShipmentLocalService;

	@Reference
	private CommerceWarehouseLocalService _commerceWarehouseLocalService;

	@Reference
	private ConfigurationProvider _configurationProvider;

}