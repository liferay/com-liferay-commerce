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

package com.liferay.commerce.initializer.util;

import com.liferay.commerce.configuration.CommerceShippingGroupServiceConfiguration;
import com.liferay.commerce.constants.CommerceConstants;
import com.liferay.commerce.constants.CommerceShipmentConstants;
import com.liferay.commerce.model.CommerceOrder;
import com.liferay.commerce.model.CommerceOrderItem;
import com.liferay.commerce.model.CommerceShipment;
import com.liferay.commerce.model.CommerceWarehouse;
import com.liferay.commerce.product.util.CPRulesThreadLocal;
import com.liferay.commerce.service.CommerceOrderItemService;
import com.liferay.commerce.service.CommerceOrderService;
import com.liferay.commerce.service.CommerceShipmentItemLocalService;
import com.liferay.commerce.service.CommerceShipmentLocalService;
import com.liferay.commerce.service.CommerceWarehouseLocalService;
import com.liferay.commerce.util.comparator.CommerceWarehouseNameComparator;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.Company;
import com.liferay.portal.kernel.model.Group;
import com.liferay.portal.kernel.model.Role;
import com.liferay.portal.kernel.model.RoleConstants;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.module.configuration.ConfigurationProvider;
import com.liferay.portal.kernel.security.auth.PrincipalThreadLocal;
import com.liferay.portal.kernel.security.permission.PermissionChecker;
import com.liferay.portal.kernel.security.permission.PermissionCheckerFactory;
import com.liferay.portal.kernel.security.permission.PermissionThreadLocal;
import com.liferay.portal.kernel.service.CompanyLocalService;
import com.liferay.portal.kernel.service.GroupLocalService;
import com.liferay.portal.kernel.service.RoleLocalService;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.service.UserLocalService;
import com.liferay.portal.kernel.settings.GroupServiceSettingsLocator;
import com.liferay.portal.kernel.transaction.Propagation;
import com.liferay.portal.kernel.transaction.TransactionConfig;
import com.liferay.portal.kernel.transaction.TransactionInvokerUtil;
import com.liferay.portal.kernel.util.CalendarFactoryUtil;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.concurrent.Callable;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Alessio Antonio Rendina
 */
@Component(service = CommerceShipmentGenerator.class)
public class CommerceShipmentGenerator {

	public void generate(long groupId, int shipmentsCount) {
		Callable<Object> generateShipmentsCallable =
			new GenerateShipmentsCallable(groupId, shipmentsCount);

		try {
			TransactionInvokerUtil.invoke(
				_transactionConfig, generateShipmentsCallable);
		}
		catch (Throwable t) {
			_log.error(t, t);
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
			CommerceOrder commerceOrder,
			List<CommerceWarehouse> commerceWarehouses)
		throws PortalException {

		// Commerce order items

		List<CommerceOrderItem> commerceOrderItems =
			_commerceOrderItemService.getAvailableForShipmentCommerceOrderItems(
				commerceOrder.getCommerceOrderId());

		if (commerceOrderItems == null) {
			_log.error(
				"There are no items ready to be shipped for order " +
					commerceOrder.getCommerceOrderId());

			return;
		}

		// Add commerce shipment

		ServiceContext serviceContext = _getServiceContext(commerceOrder);

		CommerceShipment commerceShipment =
			_commerceShipmentLocalService.addCommerceShipment(
				commerceOrder.getCommerceOrderId(), serviceContext);

		// Commerce shipment items

		_generateCommerceShipmentItems(
			commerceShipment.getCommerceShipmentId(), commerceOrderItems,
			commerceWarehouses, serviceContext);

		// Update commerce shipment

		Calendar shippingDateCalendar = _convertDateToCalendar(
			commerceShipment.getShippingDate(), 0);
		Calendar expectedDateCalendar = _convertDateToCalendar(
			commerceShipment.getExpectedDate(), _randomInt(3, 10));

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

			int quantity =
				commerceOrderItem.getQuantity() -
					commerceOrderItem.getShippedQuantity();

			int commerceWarehouseItemQuantity =
				_getRandomCommerceWarehouseItemQuantity(
					commerceOrderItem, commerceWarehouse, quantity);

			if (commerceWarehouseItemQuantity <= 0) {
				continue;
			}

			_commerceShipmentItemLocalService.addCommerceShipmentItem(
				commerceShipmentId, commerceOrderItem.getCommerceOrderItemId(),
				commerceWarehouse.getCommerceWarehouseId(),
				commerceWarehouseItemQuantity, serviceContext);
		}
	}

	private void _generateCommerceShipments(long groupId, int shipmentsCount)
		throws Exception {

		// Initialize permission checker

		_setPermissionChecker(groupId);

		// Commerce warehouses

		List<CommerceWarehouse> commerceWarehouses = _getCommerceWarehouses(
			groupId);

		if (commerceWarehouses.isEmpty()) {
			_log.error("There are no active warehouses");

			return;
		}

		// Commerce orders

		List<CommerceOrder> commerceOrders =
			_commerceOrderService.getCommerceOrders(
				groupId, CommerceShipmentConstants.ALLOWED_ORDER_STATUSES, 0,
				shipmentsCount - 1);

		if (commerceOrders.isEmpty()) {
			_log.error("There are no commerceOrders ready to be shipped");

			return;
		}

		// Commerce Shipments

		for (CommerceOrder commerceOrder : commerceOrders) {
			_generateCommerceShipment(commerceOrder, commerceWarehouses);
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

	private int _getRandomCommerceShipmentStatus() {
		int i = _randomInt(
			0, CommerceShipmentConstants.SHIPMENT_STATUSES.length - 1);

		return CommerceShipmentConstants.SHIPMENT_STATUSES[i];
	}

	private CommerceWarehouse _getRandomCommerceWarehouse(
		List<CommerceWarehouse> commerceWarehouses) {

		return commerceWarehouses.get(
			_randomInt(0, commerceWarehouses.size() - 1));
	}

	private int _getRandomCommerceWarehouseItemQuantity(
			CommerceOrderItem commerceOrderItem,
			CommerceWarehouse commerceWarehouse, int quantity)
		throws PortalException {

		int commerceWarehouseItemQuantity =
			_commerceOrderItemService.getCommerceWarehouseItemQuantity(
				commerceOrderItem.getCommerceOrderItemId(),
				commerceWarehouse.getCommerceWarehouseId());

		if (quantity < commerceWarehouseItemQuantity) {
			commerceWarehouseItemQuantity = quantity;
		}

		if (commerceWarehouseItemQuantity <= 0) {
			return commerceWarehouseItemQuantity;
		}

		return _randomInt(1, commerceWarehouseItemQuantity);
	}

	private ServiceContext _getServiceContext(CommerceOrder commerceOrder) {
		ServiceContext serviceContext = new ServiceContext();

		serviceContext.setCompanyId(commerceOrder.getCompanyId());
		serviceContext.setScopeGroupId(commerceOrder.getGroupId());
		serviceContext.setUserId(commerceOrder.getUserId());

		return serviceContext;
	}

	private int _randomInt(int min, int max) {
		if (max < min) {
			throw new IllegalArgumentException(
				"Max value must be greater than or equal to the min value");
		}

		int value = _random.nextInt();

		long range = max + 1 - min;

		if (range == 0) {
			return value;
		}

		return (int)(Math.abs(value) % range + min);
	}

	private void _setPermissionChecker(long groupId) throws Exception {
		Group group = _groupLocalService.getGroup(groupId);

		Company company = _companyLocalService.getCompanyById(
			group.getCompanyId());

		Role role = _roleLocalService.fetchRole(
			company.getCompanyId(), RoleConstants.ADMINISTRATOR);

		List<User> roleUsers = _userLocalService.getRoleUsers(role.getRoleId());

		User user = roleUsers.get(0);

		CPRulesThreadLocal.setCPRules(new ArrayList<>());

		PermissionChecker permissionChecker = _permissionCheckerFactory.create(
			user);

		PrincipalThreadLocal.setName(user.getUserId());

		PermissionThreadLocal.setPermissionChecker(permissionChecker);
	}

	private static final Log _log = LogFactoryUtil.getLog(
		CommerceShipmentGenerator.class);

	private static final TransactionConfig _transactionConfig =
		TransactionConfig.Factory.create(
			Propagation.REQUIRED, new Class<?>[] {Exception.class});

	@Reference
	private CommerceOrderItemService _commerceOrderItemService;

	@Reference
	private CommerceOrderService _commerceOrderService;

	@Reference
	private CommerceShipmentItemLocalService _commerceShipmentItemLocalService;

	@Reference
	private CommerceShipmentLocalService _commerceShipmentLocalService;

	@Reference
	private CommerceWarehouseLocalService _commerceWarehouseLocalService;

	@Reference
	private CompanyLocalService _companyLocalService;

	@Reference
	private ConfigurationProvider _configurationProvider;

	@Reference
	private GroupLocalService _groupLocalService;

	@Reference
	private PermissionCheckerFactory _permissionCheckerFactory;

	private final Random _random = new Random();

	@Reference
	private RoleLocalService _roleLocalService;

	@Reference
	private UserLocalService _userLocalService;

	private class GenerateShipmentsCallable implements Callable<Object> {

		@Override
		public Object call() throws Exception {
			_generateCommerceShipments(_groupId, _shipmentsCount);

			return null;
		}

		private GenerateShipmentsCallable(long groupId, int shipmentsCount) {
			_groupId = groupId;
			_shipmentsCount = shipmentsCount;
		}

		private final long _groupId;
		private final int _shipmentsCount;

	}

}