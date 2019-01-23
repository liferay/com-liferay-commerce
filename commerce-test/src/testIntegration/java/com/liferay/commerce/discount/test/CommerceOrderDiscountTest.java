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

package com.liferay.commerce.discount.test;

import com.liferay.arquillian.extension.junit.bridge.junit.Arquillian;
import com.liferay.commerce.context.CommerceContext;
import com.liferay.commerce.currency.model.CommerceCurrency;
import com.liferay.commerce.currency.model.CommerceMoney;
import com.liferay.commerce.currency.test.util.CommerceCurrencyTestUtil;
import com.liferay.commerce.discount.model.CommerceDiscount;
import com.liferay.commerce.discount.model.CommerceDiscountConstants;
import com.liferay.commerce.discount.test.util.CommerceDiscountTestUtil;
import com.liferay.commerce.model.CommerceOrder;
import com.liferay.commerce.model.CommerceWarehouse;
import com.liferay.commerce.price.CommerceOrderPriceCalculation;
import com.liferay.commerce.product.model.CPDefinition;
import com.liferay.commerce.product.model.CPInstance;
import com.liferay.commerce.product.service.CPInstanceLocalService;
import com.liferay.commerce.product.test.util.CPTestUtil;
import com.liferay.commerce.service.CommerceOrderLocalService;
import com.liferay.commerce.test.util.CommerceTestUtil;
import com.liferay.commerce.test.util.TestCommerceContext;
import com.liferay.portal.kernel.model.Group;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.test.rule.AggregateTestRule;
import com.liferay.portal.kernel.test.rule.DeleteAfterTestRun;
import com.liferay.portal.kernel.test.util.GroupTestUtil;
import com.liferay.portal.kernel.test.util.UserTestUtil;
import com.liferay.portal.test.rule.Inject;
import com.liferay.portal.test.rule.LiferayIntegrationTestRule;
import com.liferay.portal.test.rule.PermissionCheckerTestRule;

import java.math.BigDecimal;

import org.junit.Assert;
import org.junit.Before;
import org.junit.ClassRule;
import org.junit.Ignore;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 * @author Luca Pellizzon
 */
@RunWith(Arquillian.class)
public class CommerceOrderDiscountTest {

	@ClassRule
	@Rule
	public static AggregateTestRule aggregateTestRule = new AggregateTestRule(
		new LiferayIntegrationTestRule(), PermissionCheckerTestRule.INSTANCE);

	@Before
	public void setUp() throws Exception {
		_group = GroupTestUtil.addGroup();
		_user = UserTestUtil.addUser();
	}

	@Ignore
	@Test
	public void testMultiTargetDiscounts() throws Exception {
		CommerceCurrency commerceCurrency =
			CommerceCurrencyTestUtil.addCommerceCurrency(_group.getGroupId());

		CommerceOrder commerceOrder = CommerceTestUtil.addB2CCommerceOrder(
			_group.getGroupId(), _user.getUserId(),
			commerceCurrency.getCommerceCurrencyId());

		commerceOrder.setCommerceCurrencyId(
			commerceCurrency.getCommerceCurrencyId());

		_commerceOrderLocalService.updateCommerceOrder(commerceOrder);

		CPInstance cpInstanceDiscount = CPTestUtil.addCPInstance(
			_group.getGroupId());

		CPInstance cpInstancePlain = CPTestUtil.addCPInstance(
			_group.getGroupId());

		cpInstanceDiscount.setPrice(BigDecimal.valueOf(25));
		cpInstancePlain.setPrice(BigDecimal.valueOf(10));

		_cpInstanceLocalService.updateCPInstance(cpInstanceDiscount);
		_cpInstanceLocalService.updateCPInstance(cpInstancePlain);

		CPDefinition cpDefinition = cpInstanceDiscount.getCPDefinition();

		CommerceWarehouse commerceWarehouse =
			CommerceTestUtil.addCommerceWarehouse(_group.getGroupId());

		int quantity = 10;
		int orderedQuantity = 1;

		CommerceTestUtil.addCommerceWarehouseItem(
			commerceWarehouse, cpInstanceDiscount.getCPInstanceId(), quantity);

		CommerceTestUtil.addCommerceWarehouseItem(
			commerceWarehouse, cpInstancePlain.getCPInstanceId(), quantity);

		CommerceDiscount commerceDiscount1 =
			CommerceDiscountTestUtil.addFixedCommerceDiscount(
				_group.getGroupId(), 10,
				CommerceDiscountConstants.TARGET_PRODUCT,
				cpDefinition.getCPDefinitionId());

		CommerceDiscount commerceDiscount2 =
			CommerceDiscountTestUtil.addFixedCommerceDiscount(
				_group.getGroupId(), 10, CommerceDiscountConstants.TARGET_TOTAL,
				null);

		CommerceTestUtil.addCommerceOrderItem(
			commerceOrder.getCommerceOrderId(),
			cpInstanceDiscount.getCPInstanceId(), orderedQuantity);

		CommerceTestUtil.addCommerceOrderItem(
			commerceOrder.getCommerceOrderId(),
			cpInstancePlain.getCPInstanceId(), orderedQuantity);

		CommerceContext commerceContext = new TestCommerceContext(
			commerceCurrency, _user, _group, null, commerceOrder, null);

		CommerceMoney total = _commerceOrderPriceCalculation.getTotal(
			commerceOrder, commerceContext);
		CommerceMoney subtotal = _commerceOrderPriceCalculation.getSubtotal(
			commerceOrder, commerceContext);

		BigDecimal prod1Price = cpInstanceDiscount.getPrice();
		BigDecimal prod2Price = cpInstancePlain.getPrice();

		BigDecimal prod1TotalPrice = prod1Price.multiply(
			BigDecimal.valueOf(orderedQuantity));
		BigDecimal prod2TotalPrice = prod2Price.multiply(
			BigDecimal.valueOf(orderedQuantity));

		BigDecimal expectedCartValue = prod1TotalPrice.add(prod2TotalPrice);

		BigDecimal discount1Level1 = commerceDiscount1.getLevel1();
		BigDecimal discount2Level1 = commerceDiscount2.getLevel1();

		BigDecimal expectedSubtotal = expectedCartValue.subtract(
			discount1Level1);

		BigDecimal expectedTotal = expectedSubtotal.subtract(discount2Level1);

		BigDecimal subtotalPrice = subtotal.getPrice();
		BigDecimal totalPrice = total.getPrice();

		Assert.assertEquals(
			expectedSubtotal.stripTrailingZeros(),
			subtotalPrice.stripTrailingZeros());
		Assert.assertEquals(
			expectedTotal.stripTrailingZeros(),
			totalPrice.stripTrailingZeros());
	}

	@Ignore
	@Test
	public void testMultiTargetDiscountsWithCoupon() throws Exception {
		CommerceCurrency commerceCurrency =
			CommerceCurrencyTestUtil.addCommerceCurrency(_group.getGroupId());

		CommerceOrder commerceOrder = CommerceTestUtil.addB2CCommerceOrder(
			_group.getGroupId(), _user.getUserId(),
			commerceCurrency.getCommerceCurrencyId());

		commerceOrder.setCommerceCurrencyId(
			commerceCurrency.getCommerceCurrencyId());

		_commerceOrderLocalService.updateCommerceOrder(commerceOrder);

		CPInstance cpInstanceDiscount = CPTestUtil.addCPInstance(
			_group.getGroupId());

		CPInstance cpInstancePlain = CPTestUtil.addCPInstance(
			_group.getGroupId());

		cpInstanceDiscount.setPrice(BigDecimal.valueOf(25));
		cpInstancePlain.setPrice(BigDecimal.valueOf(10));

		_cpInstanceLocalService.updateCPInstance(cpInstanceDiscount);
		_cpInstanceLocalService.updateCPInstance(cpInstancePlain);

		CPDefinition cpDefinition = cpInstanceDiscount.getCPDefinition();

		CommerceWarehouse commerceWarehouse =
			CommerceTestUtil.addCommerceWarehouse(_group.getGroupId());

		int quantity = 10;
		int orderedQuantity = 1;

		CommerceTestUtil.addCommerceWarehouseItem(
			commerceWarehouse, cpInstanceDiscount.getCPInstanceId(), quantity);

		CommerceTestUtil.addCommerceWarehouseItem(
			commerceWarehouse, cpInstancePlain.getCPInstanceId(), quantity);

		CommerceDiscount commerceDiscount1 =
			CommerceDiscountTestUtil.addFixedCommerceDiscount(
				_group.getGroupId(), 10,
				CommerceDiscountConstants.TARGET_PRODUCT,
				cpDefinition.getCPDefinitionId());

		String couponCode = "SCONTO";

		CommerceDiscount commerceDiscount2 =
			CommerceDiscountTestUtil.addCouponDiscount(
				_group.getGroupId(), 10, couponCode,
				CommerceDiscountConstants.TARGET_TOTAL, null);

		CommerceContext commerceContext = new TestCommerceContext(
			commerceCurrency, _user, _group, null, commerceOrder, couponCode);

		CommerceTestUtil.addCommerceOrderItem(
			commerceOrder.getCommerceOrderId(),
			cpInstanceDiscount.getCPInstanceId(), orderedQuantity,
			commerceContext);

		CommerceTestUtil.addCommerceOrderItem(
			commerceOrder.getCommerceOrderId(),
			cpInstancePlain.getCPInstanceId(), orderedQuantity,
			commerceContext);

		CommerceMoney total = _commerceOrderPriceCalculation.getTotal(
			commerceOrder, commerceContext);
		CommerceMoney subtotal = _commerceOrderPriceCalculation.getSubtotal(
			commerceOrder, commerceContext);

		BigDecimal prod1Price = cpInstanceDiscount.getPrice();
		BigDecimal prod2Price = cpInstancePlain.getPrice();

		BigDecimal prod1TotalPrice = prod1Price.multiply(
			BigDecimal.valueOf(orderedQuantity));
		BigDecimal prod2TotalPrice = prod2Price.multiply(
			BigDecimal.valueOf(orderedQuantity));

		BigDecimal expectedCartValue = prod1TotalPrice.add(prod2TotalPrice);

		BigDecimal discount1Level1 = commerceDiscount1.getLevel1();
		BigDecimal discount2Level1 = commerceDiscount2.getLevel1();

		BigDecimal expectedSubtotal = expectedCartValue.subtract(
			discount1Level1);

		BigDecimal expectedTotal = expectedSubtotal.subtract(discount2Level1);

		BigDecimal subtotalPrice = subtotal.getPrice();
		BigDecimal totalPrice = total.getPrice();

		Assert.assertEquals(
			expectedSubtotal.stripTrailingZeros(),
			subtotalPrice.stripTrailingZeros());
		Assert.assertEquals(
			expectedTotal.stripTrailingZeros(),
			totalPrice.stripTrailingZeros());
	}

	@Inject
	private CommerceOrderLocalService _commerceOrderLocalService;

	@Inject
	private CommerceOrderPriceCalculation _commerceOrderPriceCalculation;

	@Inject
	private CPInstanceLocalService _cpInstanceLocalService;

	@DeleteAfterTestRun
	private Group _group;

	@DeleteAfterTestRun
	private User _user;

}