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

package com.liferay.commerce.shipment.test;

import com.liferay.arquillian.extension.junit.bridge.junit.Arquillian;
import com.liferay.commerce.account.service.CommerceAccountLocalService;
import com.liferay.commerce.constants.CommerceOrderConstants;
import com.liferay.commerce.context.CommerceContext;
import com.liferay.commerce.currency.model.CommerceCurrency;
import com.liferay.commerce.currency.model.CommerceMoney;
import com.liferay.commerce.currency.test.util.CommerceCurrencyTestUtil;
import com.liferay.commerce.discount.CommerceDiscountValue;
import com.liferay.commerce.exception.CommerceOrderShippingAddressException;
import com.liferay.commerce.exception.CommerceOrderStatusException;
import com.liferay.commerce.exception.CommerceShipmentInactiveWarehouseException;
import com.liferay.commerce.exception.CommerceShipmentItemQuantityException;
import com.liferay.commerce.exception.NoSuchOrderException;
import com.liferay.commerce.exception.NoSuchShipmentItemException;
import com.liferay.commerce.inventory.model.CommerceInventoryWarehouse;
import com.liferay.commerce.model.CommerceOrder;
import com.liferay.commerce.model.CommerceOrderItem;
import com.liferay.commerce.model.CommerceShipment;
import com.liferay.commerce.model.CommerceShipmentItem;
import com.liferay.commerce.model.CommerceShippingEngine;
import com.liferay.commerce.model.CommerceShippingMethod;
import com.liferay.commerce.price.CommerceOrderPrice;
import com.liferay.commerce.price.CommerceOrderPriceCalculation;
import com.liferay.commerce.product.model.CPDefinition;
import com.liferay.commerce.product.model.CPInstance;
import com.liferay.commerce.product.model.CommerceChannel;
import com.liferay.commerce.product.service.CPDefinitionLocalService;
import com.liferay.commerce.product.service.CPInstanceLocalService;
import com.liferay.commerce.product.service.CommerceChannelRelLocalService;
import com.liferay.commerce.product.test.util.CPTestUtil;
import com.liferay.commerce.service.CommerceOrderItemLocalService;
import com.liferay.commerce.service.CommerceOrderLocalService;
import com.liferay.commerce.service.CommerceShipmentItemLocalService;
import com.liferay.commerce.shipment.test.util.CommerceShipmentTestUtil;
import com.liferay.commerce.test.util.CommerceInventoryTestUtil;
import com.liferay.commerce.test.util.CommerceTestUtil;
import com.liferay.commerce.test.util.TestCommerceContext;
import com.liferay.commerce.util.CommerceShippingHelper;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.model.Company;
import com.liferay.portal.kernel.model.Group;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.service.CompanyLocalService;
import com.liferay.portal.kernel.test.rule.AggregateTestRule;
import com.liferay.portal.kernel.test.util.CompanyTestUtil;
import com.liferay.portal.kernel.test.util.GroupTestUtil;
import com.liferay.portal.kernel.test.util.RandomTestUtil;
import com.liferay.portal.kernel.test.util.ServiceContextTestUtil;
import com.liferay.portal.kernel.test.util.UserTestUtil;
import com.liferay.portal.test.rule.Inject;
import com.liferay.portal.test.rule.LiferayIntegrationTestRule;
import com.liferay.portal.test.rule.PermissionCheckerTestRule;

import java.math.BigDecimal;

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
 * @author Riccardo Alberti
 */
@RunWith(Arquillian.class)
public class CommerceShipmentTest {

	@ClassRule
	@Rule
	public static AggregateTestRule aggregateTestRule = new AggregateTestRule(
		new LiferayIntegrationTestRule(), PermissionCheckerTestRule.INSTANCE);

	@Before
	public void setUp() throws Exception {
		_company = CompanyTestUtil.addCompany();

		_user = UserTestUtil.addUser(_company);

		_commerceCurrency = CommerceCurrencyTestUtil.addCommerceCurrency(
			_company.getGroupId());

		_commerceOrders = new ArrayList<>();
	}

	@After
	public void tearDown() throws Exception {
		for (CommerceOrder commerceOrder : _commerceOrders) {
			_commerceOrderLocalService.deleteCommerceOrder(commerceOrder);
		}

		_companyLocalService.deleteCompany(_company);
	}

	@Test(expected = CommerceOrderShippingAddressException.class)
	public void testCheckoutWihoutShippingAddress() throws Exception {
		frutillaRule.scenario(
			"It should not be possible to create an order without shipment " +
				"address"
		).given(
			"An order is created without a shipping address"
		).when(
			"Checkout is executed"
		).then(
			"An exception is raised"
		);

		CommerceChannel commerceChannel = _createCommerceChannel();

		BigDecimal value = BigDecimal.valueOf(RandomTestUtil.nextDouble());

		CommerceOrder commerceOrder = CommerceTestUtil.addB2CCommerceOrder(
			_company.getGroupId(), _user.getUserId(),
			_commerceCurrency.getCommerceCurrencyId(),
			commerceChannel.getSiteGroupId());

		CommerceShippingMethod commerceShippingMethod =
			CommerceTestUtil.addFixedRateCommerceShippingMethod(
				commerceOrder.getGroupId(), value);

		commerceOrder.setCommerceShippingMethodId(
			commerceShippingMethod.getCommerceShippingMethodId());

		_commerceOrderLocalService.updateCommerceOrder(commerceOrder);

		_commerceOrders.add(commerceOrder);

		CommerceContext commerceContext = new TestCommerceContext(
			commerceOrder.getCommerceCurrency(), commerceChannel, null, null,
			null, commerceOrder);

		_commerceOrderLocalService.checkoutCommerceOrder(
			commerceOrder.getCommerceOrderId(), commerceContext,
			ServiceContextTestUtil.getServiceContext(
				commerceOrder.getGroupId()));
	}

	@Test
	public void testCreateMultipleShippingForSameOrder() throws Exception {
		frutillaRule.scenario(
			"An order with multiple shipments is created"
		).given(
			"An order is created with a shipment associated"
		).when(
			"I try to add a second shipment to the same order"
		).then(
			"Multiple shipments are created"
		);

		CommerceChannel commerceChannel = _createCommerceChannel();

		BigDecimal value = BigDecimal.valueOf(RandomTestUtil.nextDouble());

		CommerceOrder commerceOrder =
			CommerceTestUtil.createCommerceOrderForShipping(
				_company.getGroupId(), _user.getUserId(),
				_commerceCurrency.getCommerceCurrencyId(),
				commerceChannel.getSiteGroupId(), value);

		_commerceOrders.add(commerceOrder);

		CommerceShipment commerceShipment1 =
			CommerceShipmentTestUtil.createEmptyOrderShipment(
				commerceOrder.getGroupId(), commerceOrder.getCommerceOrderId());

		CommerceShipment commerceShipment2 =
			CommerceShipmentTestUtil.createEmptyOrderShipment(
				commerceOrder.getGroupId(), commerceOrder.getCommerceOrderId());

		Assert.assertEquals(
			commerceOrder.getGroupId(), commerceShipment1.getGroupId());
		Assert.assertEquals(
			commerceOrder.getGroupId(), commerceShipment2.getGroupId());
	}

	@Test
	public void testCreateOrderShipment() throws Exception {
		frutillaRule.scenario(
			"An order with a shipment is created"
		).given(
			"An order"
		).when(
			"I add a shipment on that order"
		).then(
			"The shipment is associated to the order"
		);

		CommerceChannel commerceChannel = _createCommerceChannel();

		BigDecimal value = BigDecimal.valueOf(RandomTestUtil.nextDouble());

		CommerceOrder commerceOrder =
			CommerceTestUtil.createCommerceOrderForShipping(
				_company.getGroupId(), _user.getUserId(),
				_commerceCurrency.getCommerceCurrencyId(),
				commerceChannel.getSiteGroupId(), value);

		_commerceOrders.add(commerceOrder);

		CommerceShipment commerceShipment =
			CommerceShipmentTestUtil.createEmptyOrderShipment(
				commerceOrder.getGroupId(), commerceOrder.getCommerceOrderId());

		Assert.assertEquals(
			commerceOrder.getCommerceAccount(),
			commerceShipment.getCommerceAccount());
		Assert.assertEquals(
			commerceOrder.getGroupId(), commerceShipment.getGroupId());
		Assert.assertEquals(
			commerceOrder.getShippingAddressId(),
			commerceShipment.getCommerceAddressId());
	}

	@Test
	public void testCreateOrderWithItemsShipment() throws Exception {
		frutillaRule.scenario(
			"An order with a shipment is created"
		).given(
			"An order"
		).when(
			"I add an item to the order"
		).then(
			"The shipment is associated with the order and to the items"
		);

		CPInstance cpInstance = _createCPInstance();

		CommerceChannel commerceChannel = _createCommerceChannel();

		BigDecimal value = BigDecimal.valueOf(RandomTestUtil.nextDouble());

		CommerceOrder commerceOrder =
			CommerceTestUtil.createCommerceOrderForShipping(
				_company.getGroupId(), _user.getUserId(),
				_commerceCurrency.getCommerceCurrencyId(),
				commerceChannel.getSiteGroupId(), value);

		_commerceOrders.add(commerceOrder);

		CommerceContext commerceContext = new TestCommerceContext(
			commerceOrder.getCommerceCurrency(), commerceChannel, null, null,
			null, commerceOrder);

		CommerceInventoryWarehouse commerceInventoryWarehouse =
			_createCommerceInventoryWarehouse(
				commerceChannel.getCommerceChannelId(), true);

		int quantity = 10;

		CommerceInventoryTestUtil.addCommerceInventoryWarehouseItem(
			_user.getUserId(), commerceInventoryWarehouse, cpInstance.getSku(),
			quantity);

		int orderedQuantity = 1;

		CommerceOrderItem commerceOrderItem =
			CommerceTestUtil.addCommerceOrderItem(
				commerceOrder.getCommerceOrderId(),
				cpInstance.getCPInstanceId(), orderedQuantity, commerceContext);

		CommerceShipment commerceShipment =
			CommerceShipmentTestUtil.createOrderShipment(
				commerceOrder.getGroupId(), commerceOrder.getCommerceOrderId(),
				commerceInventoryWarehouse.getCommerceInventoryWarehouseId());

		Assert.assertEquals(
			commerceOrder.getCommerceAccount(),
			commerceShipment.getCommerceAccount());

		Assert.assertEquals(
			commerceOrder.getGroupId(), commerceShipment.getGroupId());

		int commerceShipmentItemsCount =
			_commerceShipmentItemLocalService.getCommerceShipmentItemsCount(
				commerceShipment.getCommerceShipmentId());

		Assert.assertEquals(orderedQuantity, commerceShipmentItemsCount);

		List<CommerceShipmentItem> commerceShipmentItems =
			_commerceShipmentItemLocalService.getCommerceShipmentItems(
				commerceShipment.getCommerceShipmentId(), QueryUtil.ALL_POS,
				QueryUtil.ALL_POS, null);

		CommerceShipmentItem commerceShipmentItem = commerceShipmentItems.get(
			0);

		Assert.assertEquals(
			commerceOrderItem.getCommerceOrderItemId(),
			commerceShipmentItem.getCommerceOrderItemId());

		Assert.assertEquals(
			commerceShipment, commerceShipmentItem.getCommerceShipment());
	}

	@Test
	public void testCreateOrderWithShippingAmount() throws Exception {
		frutillaRule.scenario(
			"When a shipping amount is associated with an order the price " +
				"object is correctly set"
		).given(
			"An order"
		).and(
			"A shipment with a shipping amount set"
		).when(
			"I attach the shipment to the order"
		).then(
			"The price object has the shipping amount correctly set"
		);

		CPInstance cpInstance = _createCPInstance();

		cpInstance.setPrice(BigDecimal.valueOf(25));

		_cpInstanceLocalService.updateCPInstance(cpInstance);

		CommerceChannel commerceChannel = _createCommerceChannel();

		CommerceInventoryWarehouse commerceInventoryWarehouse =
			_createCommerceInventoryWarehouse(
				commerceChannel.getCommerceChannelId(), true);

		int quantity = 10;

		CommerceInventoryTestUtil.addCommerceInventoryWarehouseItem(
			_user.getUserId(), commerceInventoryWarehouse, cpInstance.getSku(),
			quantity);

		BigDecimal value = BigDecimal.valueOf(5);

		CommerceOrder commerceOrder =
			CommerceTestUtil.createCommerceOrderForShipping(
				_company.getGroupId(), _user.getUserId(),
				_commerceCurrency.getCommerceCurrencyId(),
				commerceChannel.getSiteGroupId(), value);

		_commerceOrders.add(commerceOrder);

		int orderedQuantity = 1;

		CommerceContext commerceContext = new TestCommerceContext(
			commerceOrder.getCommerceCurrency(), commerceChannel, null, null,
			commerceOrder.getCommerceAccount(), commerceOrder);

		CommerceTestUtil.addCommerceOrderItem(
			commerceOrder.getCommerceOrderId(), cpInstance.getCPInstanceId(),
			orderedQuantity, commerceContext);

		CommerceOrderPrice commerceOrderPrice =
			_commerceOrderPriceCalculation.getCommerceOrderPrice(
				commerceOrder, false, commerceContext);

		CommerceMoney shippableValue = commerceOrderPrice.getShippingValue();

		Assert.assertEquals(value, shippableValue.getPrice());
	}

	@Test
	public void testCreateOrderWithShippingDiscount() throws Exception {
		frutillaRule.scenario(
			"When a shipping discount is associated with an order the price " +
				"object is correctly set"
		).given(
			"An order"
		).and(
			"A shipment with a shipping discount set"
		).when(
			"I attach the shipment to the order"
		).then(
			"The price object has the shipping discounts correctly set"
		);

		CPInstance cpInstance = _createCPInstance();

		cpInstance.setPrice(BigDecimal.valueOf(25));

		_cpInstanceLocalService.updateCPInstance(cpInstance);

		CommerceChannel commerceChannel = _createCommerceChannel();

		CommerceInventoryWarehouse commerceInventoryWarehouse =
			_createCommerceInventoryWarehouse(
				commerceChannel.getCommerceChannelId(), true);

		int quantity = 10;

		CommerceInventoryTestUtil.addCommerceInventoryWarehouseItem(
			_user.getUserId(), commerceInventoryWarehouse, cpInstance.getSku(),
			quantity);

		BigDecimal value = BigDecimal.valueOf(5);

		CommerceOrder commerceOrder =
			CommerceTestUtil.createCommerceOrderForShipping(
				_company.getGroupId(), _user.getUserId(),
				_commerceCurrency.getCommerceCurrencyId(),
				commerceChannel.getSiteGroupId(), value);

		BigDecimal expectedDiscountAmount = BigDecimal.valueOf(3);

		commerceOrder.setShippingDiscountAmount(expectedDiscountAmount);

		BigDecimal expectedL1Discount = BigDecimal.valueOf(1);

		commerceOrder.setShippingDiscountPercentageLevel1(expectedL1Discount);

		_commerceOrderLocalService.updateCommerceOrder(commerceOrder);

		_commerceOrders.add(commerceOrder);

		int orderedQuantity = 1;

		CommerceContext commerceContext = new TestCommerceContext(
			commerceOrder.getCommerceCurrency(), commerceChannel, null, null,
			commerceOrder.getCommerceAccount(), commerceOrder);

		CommerceTestUtil.addCommerceOrderItem(
			commerceOrder.getCommerceOrderId(), cpInstance.getCPInstanceId(),
			orderedQuantity, commerceContext);

		CommerceOrderPrice commerceOrderPrice =
			_commerceOrderPriceCalculation.getCommerceOrderPrice(
				commerceOrder, false, commerceContext);

		CommerceMoney shippableValue = commerceOrderPrice.getShippingValue();

		CommerceDiscountValue discountValue =
			commerceOrderPrice.getShippingDiscountValue();

		CommerceMoney discountAmount = discountValue.getDiscountAmount();
		BigDecimal[] percentages = discountValue.getPercentages();

		Assert.assertEquals(value, shippableValue.getPrice());
		Assert.assertEquals(expectedDiscountAmount, discountAmount.getPrice());
		Assert.assertEquals(expectedL1Discount, percentages[0]);
	}

	@Test
	public void testCreateSeparateItemShippings() throws Exception {
		frutillaRule.scenario(
			"An order with 2 different shipping items"
		).given(
			"An order"
		).and(
			"2 different CPInstance and 2 different shipments for the items"
		).when(
			"I attach the shipments to their respective items"
		).then(
			"The items shall be correctly associated with their shipping " +
				"details"
		);

		CPInstance cpInstance1 = _createCPInstance();

		CPInstance cpInstance2 = _createCPInstance(cpInstance1.getGroupId());

		CommerceChannel commerceChannel = _createCommerceChannel();

		BigDecimal value = BigDecimal.valueOf(RandomTestUtil.nextDouble());

		CommerceOrder commerceOrder =
			CommerceTestUtil.createCommerceOrderForShipping(
				_company.getGroupId(), _user.getUserId(),
				_commerceCurrency.getCommerceCurrencyId(),
				commerceChannel.getSiteGroupId(), value);

		_commerceOrders.add(commerceOrder);

		CommerceShipment commerceShipment1 =
			CommerceShipmentTestUtil.createEmptyOrderShipment(
				commerceOrder.getGroupId(), commerceOrder.getCommerceOrderId());

		CommerceShipment commerceShipment2 =
			CommerceShipmentTestUtil.createEmptyOrderShipment(
				commerceOrder.getGroupId(), commerceOrder.getCommerceOrderId());

		CommerceContext commerceContext = new TestCommerceContext(
			commerceOrder.getCommerceCurrency(), commerceChannel, null, null,
			null, commerceOrder);

		CommerceInventoryWarehouse commerceInventoryWarehouse =
			_createCommerceInventoryWarehouse(
				commerceChannel.getCommerceChannelId(), true);

		int quantity = 10;

		CommerceInventoryTestUtil.addCommerceInventoryWarehouseItem(
			_user.getUserId(), commerceInventoryWarehouse, cpInstance1.getSku(),
			quantity);
		CommerceInventoryTestUtil.addCommerceInventoryWarehouseItem(
			_user.getUserId(), commerceInventoryWarehouse, cpInstance2.getSku(),
			quantity);

		int orderedQuantity = 1;

		CommerceOrderItem commerceOrderItem1 =
			CommerceTestUtil.addCommerceOrderItem(
				commerceOrder.getCommerceOrderId(),
				cpInstance1.getCPInstanceId(), orderedQuantity, commerceContext);

		CommerceOrderItem commerceOrderItem2 =
			CommerceTestUtil.addCommerceOrderItem(
				commerceOrder.getCommerceOrderId(),
				cpInstance2.getCPInstanceId(), orderedQuantity,
				commerceContext);

		CommerceShipmentItem commerceShipmentItem1 =
			_commerceShipmentItemLocalService.addCommerceShipmentItem(
				commerceShipment1.getCommerceShipmentId(),
				commerceOrderItem1.getCommerceOrderItemId(),
				commerceInventoryWarehouse.getCommerceInventoryWarehouseId(),
				commerceOrderItem1.getQuantity(),
				ServiceContextTestUtil.getServiceContext(
					commerceOrder.getGroupId()));

		CommerceShipmentItem commerceShipmentItem2 =
			_commerceShipmentItemLocalService.addCommerceShipmentItem(
				commerceShipment2.getCommerceShipmentId(),
				commerceOrderItem2.getCommerceOrderItemId(),
				commerceInventoryWarehouse.getCommerceInventoryWarehouseId(),
				commerceOrderItem2.getQuantity(),
				ServiceContextTestUtil.getServiceContext(
					commerceOrder.getGroupId()));

		Assert.assertEquals(
			commerceShipment1, commerceShipmentItem1.getCommerceShipment());
		Assert.assertEquals(
			commerceShipment2, commerceShipmentItem2.getCommerceShipment());
	}

	@Test
	public void testCreateShipmentWithNonshippableItem() throws Exception {
		frutillaRule.scenario(
			"Attach a shipment to a non-shippable item"
		).given(
			"An order with an order item created as non-shippable"
		).when(
			"I attach the shipment to the order and its items"
		).then(
			"The order shall be non-shippable"
		);

		CPInstance cpInstance = _createCPInstance();

		CPDefinition cpDefinition = cpInstance.getCPDefinition();

		cpDefinition.setShippable(false);

		_cpDefinitionLocalService.updateCPDefinition(cpDefinition);

		cpInstance = _cpInstanceLocalService.getCPInstance(
			cpDefinition.getCPDefinitionId(), cpInstance.getSku());

		CommerceChannel commerceChannel = _createCommerceChannel();

		BigDecimal value = BigDecimal.valueOf(RandomTestUtil.nextDouble());

		CommerceOrder commerceOrder =
			CommerceTestUtil.createCommerceOrderForShipping(
				_company.getGroupId(), _user.getUserId(),
				_commerceCurrency.getCommerceCurrencyId(),
				commerceChannel.getSiteGroupId(), value);

		_commerceOrders.add(commerceOrder);

		CommerceContext commerceContext = new TestCommerceContext(
			commerceOrder.getCommerceCurrency(), commerceChannel, null, null,
			null, commerceOrder);

		CommerceInventoryWarehouse commerceInventoryWarehouse =
			_createCommerceInventoryWarehouse(
				commerceChannel.getCommerceChannelId(), true);

		int quantity = 10;

		CommerceInventoryTestUtil.addCommerceInventoryWarehouseItem(
			_user.getUserId(), commerceInventoryWarehouse, cpInstance.getSku(),
			quantity);

		int orderedQuantity = 1;

		CommerceTestUtil.addCommerceOrderItem(
			commerceOrder.getCommerceOrderId(), cpInstance.getCPInstanceId(),
			orderedQuantity, commerceContext);

		CommerceShipmentTestUtil.createOrderShipment(
			commerceOrder.getGroupId(), commerceOrder.getCommerceOrderId(),
			commerceInventoryWarehouse.getCommerceInventoryWarehouseId());

		Assert.assertEquals(
			false, _commerceShippingHelper.isShippable(commerceOrder));
	}

	@Test(expected = CommerceOrderStatusException.class)
	public void testCreateShippingForCancelledOrder() throws Exception {
		frutillaRule.scenario(
			"Attach a shipment to an order with order status CANCELLED "
		).given(
			"An order is created"
		).and(
			"The order status is updated to CANCELLED"
		).when(
			"I attach the shipment to the order"
		).then(
			"An exception shall be raised"
		);

		CommerceChannel commerceChannel = _createCommerceChannel();

		BigDecimal value = BigDecimal.valueOf(RandomTestUtil.nextDouble());

		CommerceOrder commerceOrder =
			CommerceTestUtil.createCommerceOrderForShipping(
				_company.getGroupId(), _user.getUserId(),
				_commerceCurrency.getCommerceCurrencyId(),
				commerceChannel.getSiteGroupId(), value);

		_commerceOrders.add(commerceOrder);

		commerceOrder.setOrderStatus(
			CommerceOrderConstants.ORDER_STATUS_CANCELLED);

		_commerceOrderLocalService.updateCommerceOrder(commerceOrder);

		CommerceContext commerceContext = new TestCommerceContext(
			commerceOrder.getCommerceCurrency(), commerceChannel, null, null,
			null, commerceOrder);

		CommerceShipmentTestUtil.createEmptyOrderShipment(
			commerceOrder.getGroupId(), commerceOrder.getCommerceOrderId());

		_commerceOrderLocalService.checkoutCommerceOrder(
			commerceOrder.getCommerceOrderId(), commerceContext,
			ServiceContextTestUtil.getServiceContext(
				commerceOrder.getGroupId()));
	}

	@Test
	public void testCreateShippingForItemsOnly() throws Exception {
		frutillaRule.scenario(
			"Attach a shipment on the order items without a shipment to the " +
				"order"
		).given(
			"An order is created"
		).and(
			"2 order items are added to the order"
		).when(
			"I attach the shipment to the items"
		).then(
			"Each item shall have a shipment item associated"
		);

		CPInstance cpInstance1 = _createCPInstance();

		CPInstance cpInstance2 = _createCPInstance(cpInstance1.getGroupId());

		CommerceChannel commerceChannel = _createCommerceChannel();

		BigDecimal value = BigDecimal.valueOf(RandomTestUtil.nextDouble());

		CommerceOrder commerceOrder =
			CommerceTestUtil.createCommerceOrderForShipping(
				_company.getGroupId(), _user.getUserId(),
				_commerceCurrency.getCommerceCurrencyId(),
				commerceChannel.getSiteGroupId(), value);

		_commerceOrders.add(commerceOrder);

		CommerceContext commerceContext = new TestCommerceContext(
			commerceOrder.getCommerceCurrency(), commerceChannel, null, null,
			null, commerceOrder);

		CommerceInventoryWarehouse commerceInventoryWarehouse =
			_createCommerceInventoryWarehouse(
				commerceChannel.getCommerceChannelId(), true);

		int quantity = 10;

		CommerceInventoryTestUtil.addCommerceInventoryWarehouseItem(
			_user.getUserId(), commerceInventoryWarehouse, cpInstance1.getSku(),
			quantity);
		CommerceInventoryTestUtil.addCommerceInventoryWarehouseItem(
			_user.getUserId(), commerceInventoryWarehouse, cpInstance2.getSku(),
			quantity);

		int orderedQuantity = 1;

		CommerceTestUtil.addCommerceOrderItem(
			commerceOrder.getCommerceOrderId(), cpInstance1.getCPInstanceId(),
			orderedQuantity, commerceContext);

		CommerceTestUtil.addCommerceOrderItem(
			commerceOrder.getCommerceOrderId(), cpInstance2.getCPInstanceId(),
			orderedQuantity, commerceContext);

		CommerceShipmentTestUtil.createOrderItemsOnlyShipment(
			commerceOrder.getGroupId(), commerceOrder.getCommerceOrderId(),
			commerceInventoryWarehouse.getCommerceInventoryWarehouseId());

		List<CommerceOrderItem> commerceOrderItems =
			_commerceOrderItemLocalService.getCommerceOrderItems(
				commerceOrder.getCommerceOrderId(), QueryUtil.ALL_POS,
				QueryUtil.ALL_POS);

		for (CommerceOrderItem commerceOrderItem : commerceOrderItems) {
			List<CommerceShipmentItem> commerceShipmentItems =
				_commerceShipmentItemLocalService.getCommerceShipmentItems(
					commerceOrderItem.getCommerceOrderItemId());

			Assert.assertEquals(
				commerceShipmentItems.toString(), 1,
				commerceShipmentItems.size());
		}
	}

	@Test(expected = CommerceShipmentInactiveWarehouseException.class)
	public void testCreateShippingFromInactiveWarehouse() throws Exception {
		frutillaRule.scenario(
			"Attach a shipment to a item that is stocked in an inactive " +
				"warehouse"
		).given(
			"An order with an order item associated with an inactive warehouse"
		).when(
			"I attach the shipment to the order and its items"
		).then(
			"An exception shall be raised"
		);

		CPInstance cpInstance = _createCPInstance();

		CommerceChannel commerceChannel = _createCommerceChannel();

		BigDecimal value = BigDecimal.valueOf(RandomTestUtil.nextDouble());

		CommerceOrder commerceOrder =
			CommerceTestUtil.createCommerceOrderForShipping(
				_company.getGroupId(), _user.getUserId(),
				_commerceCurrency.getCommerceCurrencyId(),
				commerceChannel.getSiteGroupId(), value);

		_commerceOrders.add(commerceOrder);

		CommerceContext commerceContext = new TestCommerceContext(
			commerceOrder.getCommerceCurrency(), commerceChannel, null, null,
			null, commerceOrder);

		CommerceInventoryWarehouse commerceInventoryWarehouse =
			_createCommerceInventoryWarehouse(
				commerceChannel.getCommerceChannelId(), false);

		int quantity = 10;

		CommerceInventoryTestUtil.addCommerceInventoryWarehouseItem(
			_user.getUserId(), commerceInventoryWarehouse, cpInstance.getSku(),
			quantity);

		int orderedQuantity = 1;

		CommerceOrderItem commerceOrderItem =
			CommerceTestUtil.addCommerceOrderItem(
				commerceOrder.getCommerceOrderId(),
				cpInstance.getCPInstanceId(), orderedQuantity, commerceContext);

		CommerceShipment commerceShipment =
			CommerceShipmentTestUtil.createEmptyOrderShipment(
				commerceOrder.getGroupId(), commerceOrder.getCommerceOrderId());

		_commerceShipmentItemLocalService.addCommerceShipmentItem(
			commerceShipment.getCommerceShipmentId(),
			commerceOrderItem.getCommerceOrderItemId(),
			commerceInventoryWarehouse.getCommerceInventoryWarehouseId(),
			orderedQuantity,
			ServiceContextTestUtil.getServiceContext(
				commerceOrder.getGroupId()));
	}

	@Test(expected = NoSuchOrderException.class)
	public void testCreateShippingWithNoOrder() throws Exception {
		frutillaRule.scenario(
			"Attach a shipment to a nonexistent order"
		).given(
			"A channel"
		).when(
			"I attach the shipment using the channel groupId"
		).then(
			"An exception shall be raised"
		);

		CommerceChannel commerceChannel = _createCommerceChannel();

		CommerceShipmentTestUtil.createEmptyOrderShipment(
			commerceChannel.getGroupId(), 0);
	}

	@Test(expected = CommerceShipmentItemQuantityException.class)
	public void testUpdateShippingItemQuantity() throws Exception {
		frutillaRule.scenario(
			"Update ordered quantity for shipping item to be greater than " +
				"inventory availability"
		).given(
			"An order with an order item associated with an active warehouse"
		).when(
			"I update the ordered quantity to a value greater than inventory " +
				"availability"
		).then(
			"An exception shall be raised"
		);

		CPInstance cpInstance = _createCPInstance();

		CommerceChannel commerceChannel = _createCommerceChannel();

		BigDecimal value = BigDecimal.valueOf(RandomTestUtil.nextDouble());

		CommerceOrder commerceOrder =
			CommerceTestUtil.createCommerceOrderForShipping(
				_company.getGroupId(), _user.getUserId(),
				_commerceCurrency.getCommerceCurrencyId(),
				commerceChannel.getSiteGroupId(), value);

		_commerceOrders.add(commerceOrder);

		CommerceContext commerceContext = new TestCommerceContext(
			commerceOrder.getCommerceCurrency(), commerceChannel, null, null,
			null, commerceOrder);

		CommerceInventoryWarehouse commerceInventoryWarehouse =
			_createCommerceInventoryWarehouse(
				commerceChannel.getCommerceChannelId(), true);

		int quantity = 5;

		CommerceInventoryTestUtil.addCommerceInventoryWarehouseItem(
			_user.getUserId(), commerceInventoryWarehouse, cpInstance.getSku(),
			quantity);

		int orderedQuantity = 1;

		CommerceOrderItem commerceOrderItem =
			CommerceTestUtil.addCommerceOrderItem(
				commerceOrder.getCommerceOrderId(),
				cpInstance.getCPInstanceId(), orderedQuantity, commerceContext);

		CommerceShipmentTestUtil.createOrderShipment(
			commerceOrder.getGroupId(), commerceOrder.getCommerceOrderId(),
			commerceInventoryWarehouse.getCommerceInventoryWarehouseId());

		List<CommerceShipmentItem> commerceShipmentItems =
			_commerceShipmentItemLocalService.getCommerceShipmentItems(
				commerceOrderItem.getCommerceOrderItemId());

		CommerceShipmentItem commerceShipmentItem = commerceShipmentItems.get(
			0);

		int newOrderedQuantity = 10;

		_commerceShipmentItemLocalService.updateCommerceShipmentItem(
			commerceShipmentItem.getCommerceShipmentItemId(),
			newOrderedQuantity);
	}

	@Test(expected = NoSuchShipmentItemException.class)
	public void testUpdateShippingItemQuantityWithNoShipping()
		throws Exception {

		frutillaRule.scenario(
			"It should not be possible to update the shipping quantity of an " +
				"item not associated with a shipment"
		).given(
			"An order with an order item associated with an active warehouse"
		).when(
			"I update the ordered quantity"
		).then(
			"An exception shall be raised"
		);

		CPInstance cpInstance = _createCPInstance();

		CommerceChannel commerceChannel = _createCommerceChannel();

		BigDecimal value = BigDecimal.valueOf(RandomTestUtil.nextDouble());

		CommerceOrder commerceOrder =
			CommerceTestUtil.createCommerceOrderForShipping(
				_company.getGroupId(), _user.getUserId(),
				_commerceCurrency.getCommerceCurrencyId(),
				commerceChannel.getSiteGroupId(), value);

		_commerceOrders.add(commerceOrder);

		CommerceContext commerceContext = new TestCommerceContext(
			commerceOrder.getCommerceCurrency(), commerceChannel, null, null,
			null, commerceOrder);

		CommerceInventoryWarehouse commerceInventoryWarehouse =
			_createCommerceInventoryWarehouse(
				commerceChannel.getCommerceChannelId(), true);

		int quantity = 5;

		CommerceInventoryTestUtil.addCommerceInventoryWarehouseItem(
			_user.getUserId(), commerceInventoryWarehouse, cpInstance.getSku(),
			quantity);

		int orderedQuantity = 1;

		CommerceTestUtil.addCommerceOrderItem(
			commerceOrder.getCommerceOrderId(), cpInstance.getCPInstanceId(),
			orderedQuantity, commerceContext);

		_commerceShipmentItemLocalService.updateCommerceShipmentItem(
			0, orderedQuantity);
	}

	@Rule
	public FrutillaRule frutillaRule = new FrutillaRule();

	private CommerceChannel _createCommerceChannel() throws Exception {
		Group group = GroupTestUtil.addGroup(
			_company.getGroupId(), _user.getUserId(), 0);

		return CommerceTestUtil.addCommerceChannel(
			group.getGroupId(), _commerceCurrency.getCode());
	}

	private CommerceInventoryWarehouse _createCommerceInventoryWarehouse(
			long commerceChannelId, boolean active)
		throws Exception {

		Group group = GroupTestUtil.addGroup(
			_company.getGroupId(), _user.getUserId(), 0);

		CommerceInventoryWarehouse commerceInventoryWarehouse =
			CommerceInventoryTestUtil.addCommerceInventoryWarehouse(
				group.getGroupId(), active);

		_commerceChannelRelLocalService.addCommerceChannelRel(
			CommerceInventoryWarehouse.class.getName(),
			commerceInventoryWarehouse.getCommerceInventoryWarehouseId(),
			commerceChannelId,
			ServiceContextTestUtil.getServiceContext(group.getGroupId()));

		return commerceInventoryWarehouse;
	}

	private CPInstance _createCPInstance() throws Exception {
		Group group = GroupTestUtil.addGroup(
			_company.getGroupId(), _user.getUserId(), 0);

		return CPTestUtil.addCPInstanceWithRandomSku(group.getGroupId());
	}

	private CPInstance _createCPInstance(long groupId) throws Exception {
		return CPTestUtil.addCPInstanceWithRandomSku(groupId);
	}

	@Inject
	private CommerceAccountLocalService _commerceAccountLocalService;

	@Inject
	private CommerceChannelRelLocalService _commerceChannelRelLocalService;

	private CommerceCurrency _commerceCurrency;

	@Inject
	private CommerceOrderItemLocalService _commerceOrderItemLocalService;

	@Inject
	private CommerceOrderLocalService _commerceOrderLocalService;

	@Inject
	private CommerceOrderPriceCalculation _commerceOrderPriceCalculation;

	private List<CommerceOrder> _commerceOrders;

	@Inject
	private CommerceShipmentItemLocalService _commerceShipmentItemLocalService;

	@Inject
	private CommerceShippingEngine _commerceShippingEngine;

	@Inject
	private CommerceShippingHelper _commerceShippingHelper;

	private Company _company;

	@Inject
	private CompanyLocalService _companyLocalService;

	@Inject
	private CPDefinitionLocalService _cpDefinitionLocalService;

	@Inject
	private CPInstanceLocalService _cpInstanceLocalService;

	private User _user;

}