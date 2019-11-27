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

package com.liferay.commerce.order.stock.test;

import com.liferay.arquillian.extension.junit.bridge.junit.Arquillian;
import com.liferay.commerce.currency.model.CommerceCurrency;
import com.liferay.commerce.currency.service.CommerceCurrencyLocalServiceUtil;
import com.liferay.commerce.currency.test.util.CommerceCurrencyTestUtil;
import com.liferay.commerce.exception.CommerceOrderValidatorException;
import com.liferay.commerce.inventory.model.CommerceInventoryWarehouse;
import com.liferay.commerce.inventory.model.CommerceInventoryWarehouseItem;
import com.liferay.commerce.inventory.service.CommerceInventoryWarehouseItemLocalService;
import com.liferay.commerce.model.CommerceOrder;
import com.liferay.commerce.model.CommerceOrderItem;
import com.liferay.commerce.product.model.CPDefinition;
import com.liferay.commerce.product.model.CPInstance;
import com.liferay.commerce.product.model.CommerceChannel;
import com.liferay.commerce.product.service.CommerceChannelLocalServiceUtil;
import com.liferay.commerce.product.test.util.CPTestUtil;
import com.liferay.commerce.service.CommerceOrderLocalServiceUtil;
import com.liferay.commerce.shipment.test.util.CommerceShipmentTestUtil;
import com.liferay.commerce.test.util.CommerceInventoryTestUtil;
import com.liferay.commerce.test.util.CommerceTestUtil;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.service.UserLocalServiceUtil;
import com.liferay.portal.kernel.test.rule.AggregateTestRule;
import com.liferay.portal.kernel.test.util.UserTestUtil;
import com.liferay.portal.test.rule.Inject;
import com.liferay.portal.test.rule.LiferayIntegrationTestRule;
import com.liferay.portal.test.rule.PermissionCheckerTestRule;

import java.util.ArrayList;
import java.util.List;

import org.frutilla.FrutillaRule;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.ClassRule;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 * @author Luca Pellizzon
 */
@RunWith(Arquillian.class)
public class OrderStockManagementTest {

	@ClassRule
	@Rule
	public static final AggregateTestRule aggregateTestRule =
		new AggregateTestRule(
			new LiferayIntegrationTestRule(),
			PermissionCheckerTestRule.INSTANCE);

	@Before
	public void setUp() throws Exception {
		_user = UserTestUtil.addUser();
		_commerceOrders = new ArrayList<>();

		_commerceCurrency = CommerceCurrencyTestUtil.addCommerceCurrency();

		_commerceChannel = CommerceTestUtil.addCommerceChannel(
			_commerceCurrency.getCode());
	}

	@After
	public void tearDown() throws Exception {
		for (CommerceOrder commerceOrder : _commerceOrders) {
			CommerceOrderLocalServiceUtil.deleteCommerceOrder(commerceOrder);
		}

		CommerceCurrencyLocalServiceUtil.deleteCommerceCurrency(
			_commerceCurrency);
		CommerceChannelLocalServiceUtil.deleteCommerceChannel(_commerceChannel);
		UserLocalServiceUtil.deleteUser(_user);
	}

	@Test
	public void testBackOrder() throws Exception {
		frutillaRule.scenario(
			"A not available product can be added to an order if it is " +
				"flagged as backorder enabled"
		).given(
			"A product with backorder flag enabled"
		).when(
			"I try to add the product to an order"
		).then(
			"The product will be added even if no stock is available"
		);

		CommerceOrder commerceOrder = CommerceTestUtil.addB2CCommerceOrder(
			_user.getUserId(), _commerceChannel.getSiteGroupId(),
			_commerceCurrency);

		_commerceOrders.add(commerceOrder);

		CPInstance cpInstance = CPTestUtil.addCPInstance();

		CPDefinition cpDefinition = cpInstance.getCPDefinition();

		CommerceTestUtil.addBackOrderCPDefinitionInventory(cpDefinition);

		int orderedQuantity = 4;

		CommerceOrderItem commerceOrderItem =
			CommerceTestUtil.addCommerceOrderItem(
				commerceOrder.getCommerceOrderId(),
				cpInstance.getCPInstanceId(), orderedQuantity);

		Assert.assertEquals(
			commerceOrderItem.toString(), orderedQuantity,
			commerceOrderItem.getQuantity());
	}

	@Test
	public void testProductAvailability() throws Exception {
		frutillaRule.scenario(
			"Product availability is updated when the product is actually " +
				"picked up"
		).given(
			"A product with stock availability"
		).when(
			"I add the product to an order"
		).and(
			"The order is 'shipped'"
		).then(
			"The product's stock availability is updated"
		);

		CommerceOrder commerceOrder = CommerceTestUtil.addB2CCommerceOrder(
			_user.getUserId(), _commerceChannel.getSiteGroupId(),
			_commerceCurrency);

		_commerceOrders.add(commerceOrder);

		CPInstance cpInstance = CPTestUtil.addCPInstance();

		CommerceInventoryWarehouse commerceInventoryWarehouse =
			CommerceInventoryTestUtil.addCommerceInventoryWarehouse();

		CommerceTestUtil.addWarehouseCommerceChannelRel(
			commerceInventoryWarehouse.getCommerceInventoryWarehouseId(),
			_commerceChannel.getCommerceChannelId());

		int quantity = 10;
		int orderedQuantity = 4;

		CommerceInventoryWarehouseItem commerceInventoryWarehouseItem =
			CommerceInventoryTestUtil.addCommerceInventoryWarehouseItem(
				_user.getUserId(), commerceInventoryWarehouse,
				cpInstance.getSku(), quantity);

		CommerceOrderItem commerceOrderItem =
			CommerceTestUtil.addCommerceOrderItem(
				commerceOrder.getCommerceOrderId(),
				cpInstance.getCPInstanceId(), orderedQuantity);

		Assert.assertEquals(
			commerceOrderItem.toString(), orderedQuantity,
			commerceOrderItem.getQuantity());

		commerceInventoryWarehouseItem =
			_commerceInventoryWarehouseItemLocalService.
				getCommerceInventoryWarehouseItem(
					commerceInventoryWarehouseItem.
						getCommerceInventoryWarehouseItemId());

		Assert.assertEquals(
			commerceInventoryWarehouseItem.toString(), quantity,
			commerceInventoryWarehouseItem.getQuantity());

		CommerceShipmentTestUtil.createOrderShipment(
			commerceOrder.getCommerceOrderId(),
			commerceInventoryWarehouse.getCommerceInventoryWarehouseId());

		commerceInventoryWarehouseItem =
			_commerceInventoryWarehouseItemLocalService.
				getCommerceInventoryWarehouseItem(
					commerceInventoryWarehouseItem.
						getCommerceInventoryWarehouseItemId());

		Assert.assertEquals(
			commerceInventoryWarehouseItem.toString(),
			quantity - orderedQuantity,
			commerceInventoryWarehouseItem.getQuantity());
	}

	@Test
	public void testProductAvailable() throws Exception {
		frutillaRule.scenario(
			"An available product can be added to an order"
		).given(
			"A product with stock availability"
		).when(
			"I try to add the product to an order"
		).then(
			"The product will be successfully added to the order"
		);

		CommerceOrder commerceOrder = CommerceTestUtil.addB2CCommerceOrder(
			_user.getUserId(), _commerceChannel.getSiteGroupId(),
			_commerceCurrency);

		_commerceOrders.add(commerceOrder);

		CPInstance cpInstance = CPTestUtil.addCPInstance();

		CommerceInventoryWarehouse commerceInventoryWarehouse =
			CommerceInventoryTestUtil.addCommerceInventoryWarehouse();

		CommerceTestUtil.addWarehouseCommerceChannelRel(
			commerceInventoryWarehouse.getCommerceInventoryWarehouseId(),
			_commerceChannel.getCommerceChannelId());

		CommerceInventoryTestUtil.addCommerceInventoryWarehouseItem(
			_user.getUserId(), commerceInventoryWarehouse, cpInstance.getSku(),
			10);

		int orderedQuantity = 2;

		CommerceOrderItem commerceOrderItem =
			CommerceTestUtil.addCommerceOrderItem(
				commerceOrder.getCommerceOrderId(),
				cpInstance.getCPInstanceId(), orderedQuantity);

		Assert.assertEquals(
			commerceOrderItem.toString(), orderedQuantity,
			commerceOrderItem.getQuantity());
	}

	@Test(expected = CommerceOrderValidatorException.class)
	public void testProductNotAvailable() throws Exception {
		frutillaRule.scenario(
			"A not available product cannot be added to an order"
		).given(
			"A product with no stock availability"
		).when(
			"I try to add the product to an order"
		).then(
			"The action will fail due to stock unavailability"
		);

		CommerceOrder commerceOrder = CommerceTestUtil.addB2CCommerceOrder(
			_user.getUserId(), _commerceChannel.getSiteGroupId(),
			_commerceCurrency);

		_commerceOrders.add(commerceOrder);

		CPInstance cpInstance = CPTestUtil.addCPInstance();

		CommerceTestUtil.addCommerceOrderItem(
			commerceOrder.getCommerceOrderId(), cpInstance.getCPInstanceId(),
			2);
	}

	@Test(expected = CommerceOrderValidatorException.class)
	public void testProductNotEnoughQuantity() throws Exception {
		frutillaRule.scenario(
			"A product quantity greater than available cannot be added to an " +
				"order"
		).given(
			"A product with stock availability"
		).when(
			"I try to add the product to an order"
		).and(
			"The amount I'm trying to add is greater than the stock " +
				"availability"
		).then(
			"The action will fail due to stock unavailability"
		);

		CommerceOrder commerceOrder = CommerceTestUtil.addB2CCommerceOrder(
			_user.getUserId(), _commerceChannel.getSiteGroupId(),
			_commerceCurrency);

		_commerceOrders.add(commerceOrder);

		CPInstance cpInstance = CPTestUtil.addCPInstance();

		CommerceInventoryWarehouse commerceInventoryWarehouse =
			CommerceInventoryTestUtil.addCommerceInventoryWarehouse();

		CommerceTestUtil.addWarehouseCommerceChannelRel(
			commerceInventoryWarehouse.getCommerceInventoryWarehouseId(),
			_commerceChannel.getCommerceChannelId());

		CommerceInventoryTestUtil.addCommerceInventoryWarehouseItem(
			_user.getUserId(), commerceInventoryWarehouse, cpInstance.getSku(),
			10);

		CommerceTestUtil.addCommerceOrderItem(
			commerceOrder.getCommerceOrderId(), cpInstance.getCPInstanceId(),
			20);
	}

	@Test(expected = CommerceOrderValidatorException.class)
	public void testProductReservedQuantity() throws Exception {
		frutillaRule.scenario(
			"A product quantity greater than available-reserved cannot be " +
				"added to an order"
		).given(
			"A product with stock availability"
		).when(
			"I try to add the product to a couple of orders"
		).and(
			"The sum of the amounts added to the orders is exceeding the " +
				"total availability"
		).then(
			"The action will fail due to stock unavailability"
		);

		CommerceOrder commerceOrder1 = CommerceTestUtil.addB2CCommerceOrder(
			_user.getUserId(), _commerceChannel.getSiteGroupId(),
			_commerceCurrency);

		_commerceOrders.add(commerceOrder1);

		CommerceOrder commerceOrder2 = CommerceTestUtil.addB2CCommerceOrder(
			_user.getUserId(), commerceOrder1.getCommerceAccountId(),
			_commerceChannel.getSiteGroupId(), _commerceCurrency);

		_commerceOrders.add(commerceOrder2);

		CPInstance cpInstance = CPTestUtil.addCPInstance();

		CommerceInventoryWarehouse commerceInventoryWarehouse =
			CommerceInventoryTestUtil.addCommerceInventoryWarehouse();

		CommerceTestUtil.addWarehouseCommerceChannelRel(
			commerceInventoryWarehouse.getCommerceInventoryWarehouseId(),
			_commerceChannel.getCommerceChannelId());

		CommerceInventoryTestUtil.addCommerceInventoryWarehouseItem(
			_user.getUserId(), commerceInventoryWarehouse, cpInstance.getSku(),
			10);

		CommerceTestUtil.addCommerceOrderItem(
			commerceOrder1.getCommerceOrderId(), cpInstance.getCPInstanceId(),
			4);

		CommerceShipmentTestUtil.createOrderShipment(
			commerceOrder1.getCommerceOrderId(),
			commerceInventoryWarehouse.getCommerceInventoryWarehouseId());

		CommerceTestUtil.addCommerceOrderItem(
			commerceOrder2.getCommerceOrderId(), cpInstance.getCPInstanceId(),
			8);
	}

	@Rule
	public FrutillaRule frutillaRule = new FrutillaRule();

	private CommerceChannel _commerceChannel;
	private CommerceCurrency _commerceCurrency;

	@Inject
	private CommerceInventoryWarehouseItemLocalService
		_commerceInventoryWarehouseItemLocalService;

	private List<CommerceOrder> _commerceOrders;
	private User _user;

}