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
import com.liferay.asset.kernel.model.AssetCategory;
import com.liferay.commerce.context.CommerceContext;
import com.liferay.commerce.currency.model.CommerceCurrency;
import com.liferay.commerce.currency.model.CommerceMoney;
import com.liferay.commerce.currency.test.util.CommerceCurrencyTestUtil;
import com.liferay.commerce.discount.CommerceDiscountValue;
import com.liferay.commerce.discount.model.CommerceDiscount;
import com.liferay.commerce.discount.model.CommerceDiscountConstants;
import com.liferay.commerce.discount.test.util.CommerceDiscountTestUtil;
import com.liferay.commerce.price.CommerceProductPrice;
import com.liferay.commerce.price.CommerceProductPriceCalculation;
import com.liferay.commerce.product.model.CPDefinition;
import com.liferay.commerce.product.model.CPInstance;
import com.liferay.commerce.product.service.CPInstanceLocalService;
import com.liferay.commerce.product.test.util.CPTestUtil;
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

import org.frutilla.FrutillaRule;

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
public class CommerceDiscountTest {

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
	public void testCommerceFixedDiscount() throws Exception {
		frutillaRule.scenario(
			"Apply a fixed price discount"
		).given(
			"A product with a base price"
		).and(
			"A discount with fixed value"
		).when(
			"I try to get the final price of the product"
		).then(
			"The final price will be calculated taking into consideration " +
				"the discount"
		);

		CPInstance cpInstance = CPTestUtil.addCPInstance(_group.getGroupId());

		cpInstance.setPrice(BigDecimal.valueOf(25));

		_cpInstanceLocalService.updateCPInstance(cpInstance);

		CPDefinition cpDefinition = cpInstance.getCPDefinition();

		CommerceDiscount commerceDiscount =
			CommerceDiscountTestUtil.addFixedCommerceDiscount(
				_group.getGroupId(), 10,
				CommerceDiscountConstants.TARGET_PRODUCT,
				cpDefinition.getCPDefinitionId());

		CommerceCurrency commerceCurrency =
			CommerceCurrencyTestUtil.addCommerceCurrency(_group.getGroupId());

		CommerceContext commerceContext = new TestCommerceContext(
			commerceCurrency, _user, _group, null, null, null);

		CommerceProductPrice commerceProductPrice =
			_commerceProductPriceCalculation.getCommerceProductPrice(
				cpInstance.getCPInstanceId(), 1, commerceContext);

		BigDecimal expectedPrice = cpInstance.getPrice().subtract(
			commerceDiscount.getLevel1());

		BigDecimal actualPrice = BigDecimal.ZERO;

		if (commerceProductPrice != null) {
			CommerceMoney finalPrice = commerceProductPrice.getFinalPrice();

			actualPrice = finalPrice.getPrice();
		}

		Assert.assertEquals(
			expectedPrice.stripTrailingZeros(),
			actualPrice.stripTrailingZeros());
	}

	@Ignore
	@Test
	public void testCommerceFixedDiscounts() throws Exception {
		frutillaRule.scenario(
			"When few fixed discount are available check how they are applied"
		).given(
			"Some products with base prices"
		).and(
			"Some discounts linked to single product or to categories"
		).when(
			"I try to get the final prices of the products"
		).then(
			"The final prices are calculated based on the available " +
				"discounts. If more discount per type are available then the " +
					"best matching is applied"
		);

		CPInstance cpInstance1 = CPTestUtil.addCPInstance(_group.getGroupId());
		CPInstance cpInstance2 = CPTestUtil.addCPInstance(_group.getGroupId());
		CPInstance cpInstance3 = CPTestUtil.addCPInstance(_group.getGroupId());
		CPInstance cpInstance4 = CPTestUtil.addCPInstance(_group.getGroupId());
		CPInstance cpInstance5 = CPTestUtil.addCPInstance(_group.getGroupId());

		cpInstance1.setPrice(BigDecimal.valueOf(111));
		cpInstance2.setPrice(BigDecimal.valueOf(222));
		cpInstance3.setPrice(BigDecimal.valueOf(333));
		cpInstance4.setPrice(BigDecimal.valueOf(444));
		cpInstance5.setPrice(BigDecimal.valueOf(555));

		_cpInstanceLocalService.updateCPInstance(cpInstance1);
		_cpInstanceLocalService.updateCPInstance(cpInstance2);
		_cpInstanceLocalService.updateCPInstance(cpInstance3);
		_cpInstanceLocalService.updateCPInstance(cpInstance4);
		_cpInstanceLocalService.updateCPInstance(cpInstance5);

		CPDefinition cpDefinition1 = cpInstance1.getCPDefinition();
		CPDefinition cpDefinition2 = cpInstance2.getCPDefinition();
		CPDefinition cpDefinition3 = cpInstance3.getCPDefinition();
		CPDefinition cpDefinition4 = cpInstance4.getCPDefinition();
		CPDefinition cpDefinition5 = cpInstance5.getCPDefinition();

		long[] category1CPDefinitionIds = {
			cpDefinition1.getCPDefinitionId(),
			cpDefinition2.getCPDefinitionId(), cpDefinition3.getCPDefinitionId()
		};

		long[] category2CPDefinitionIds = {
			cpDefinition3.getCPDefinitionId(),
			cpDefinition4.getCPDefinitionId(), cpDefinition5.getCPDefinitionId()
		};

		AssetCategory assetCategory1 = CPTestUtil.addCategoryToCPDefinitions(
			_group.getGroupId(), category1CPDefinitionIds);

		AssetCategory assetCategory2 = CPTestUtil.addCategoryToCPDefinitions(
			_group.getGroupId(), category2CPDefinitionIds);

		CommerceDiscountTestUtil.addFixedCommerceDiscount(
			_group.getGroupId(), 11, CommerceDiscountConstants.TARGET_PRODUCT,
			cpDefinition1.getCPDefinitionId());

		CommerceDiscount commerceDiscount2 =
			CommerceDiscountTestUtil.addFixedCommerceDiscount(
				_group.getGroupId(), 22,
				CommerceDiscountConstants.TARGET_CATEGORIES,
				assetCategory1.getCategoryId());

		CommerceDiscount commerceDiscount3 =
			CommerceDiscountTestUtil.addFixedCommerceDiscount(
				_group.getGroupId(), 33,
				CommerceDiscountConstants.TARGET_CATEGORIES,
				assetCategory2.getCategoryId());

		CommerceCurrency commerceCurrency =
			CommerceCurrencyTestUtil.addCommerceCurrency(_group.getGroupId());

		CommerceContext commerceContext = new TestCommerceContext(
			commerceCurrency, _user, _group, null, null, null);

		CommerceProductPrice commerceProductPrice1 =
			_commerceProductPriceCalculation.getCommerceProductPrice(
				cpInstance1.getCPInstanceId(), 1, commerceContext);

		CommerceProductPrice commerceProductPrice2 =
			_commerceProductPriceCalculation.getCommerceProductPrice(
				cpInstance2.getCPInstanceId(), 1, commerceContext);

		CommerceProductPrice commerceProductPrice3 =
			_commerceProductPriceCalculation.getCommerceProductPrice(
				cpInstance3.getCPInstanceId(), 1, commerceContext);

		CommerceProductPrice commerceProductPrice4 =
			_commerceProductPriceCalculation.getCommerceProductPrice(
				cpInstance4.getCPInstanceId(), 1, commerceContext);

		CommerceProductPrice commerceProductPrice5 =
			_commerceProductPriceCalculation.getCommerceProductPrice(
				cpInstance5.getCPInstanceId(), 1, commerceContext);

		BigDecimal expectedPrice1 =
			cpInstance1.getPrice().subtract(commerceDiscount2.getLevel1());
		BigDecimal expectedPrice2 =
			cpInstance2.getPrice().subtract(commerceDiscount2.getLevel1());
		BigDecimal expectedPrice3 =
			cpInstance3.getPrice().subtract(commerceDiscount3.getLevel1());
		BigDecimal expectedPrice4 =
			cpInstance4.getPrice().subtract(commerceDiscount3.getLevel1());
		BigDecimal expectedPrice5 =
			cpInstance5.getPrice().subtract(commerceDiscount3.getLevel1());

		BigDecimal actualPrice1 = BigDecimal.ZERO;
		BigDecimal actualPrice2 = BigDecimal.ZERO;
		BigDecimal actualPrice3 = BigDecimal.ZERO;
		BigDecimal actualPrice4 = BigDecimal.ZERO;
		BigDecimal actualPrice5 = BigDecimal.ZERO;

		if (commerceProductPrice1 != null) {
			actualPrice1 = commerceProductPrice1.getFinalPrice().getPrice();
		}

		if (commerceProductPrice2 != null) {
			actualPrice2 = commerceProductPrice2.getFinalPrice().getPrice();
		}

		if (commerceProductPrice3 != null) {
			actualPrice3 = commerceProductPrice3.getFinalPrice().getPrice();
		}

		if (commerceProductPrice4 != null) {
			actualPrice4 = commerceProductPrice4.getFinalPrice().getPrice();
		}

		if (commerceProductPrice5 != null) {
			actualPrice5 = commerceProductPrice5.getFinalPrice().getPrice();
		}

		Assert.assertEquals(
			expectedPrice1.stripTrailingZeros(),
			actualPrice1.stripTrailingZeros());
		Assert.assertEquals(
			expectedPrice2.stripTrailingZeros(),
			actualPrice2.stripTrailingZeros());
		Assert.assertEquals(
			expectedPrice3.stripTrailingZeros(),
			actualPrice3.stripTrailingZeros());
		Assert.assertEquals(
			expectedPrice4.stripTrailingZeros(),
			actualPrice4.stripTrailingZeros());
		Assert.assertEquals(
			expectedPrice5.stripTrailingZeros(),
			actualPrice5.stripTrailingZeros());
	}

	@Ignore
	@Test
	public void testCommercePercentageDiscounts() throws Exception {
		frutillaRule.scenario(
			"When few percentage discount are available check how they are " +
				"applied"
		).given(
			"Some products with base prices"
		).and(
			"Some discounts linked to single product or to categories"
		).when(
			"I try to get the final prices of the products"
		).then(
			"The final prices are calculated based on the available " +
				"discounts. If more discount per type are available then the " +
					"best matching is applied"
		);

		CPInstance cpInstance1 = CPTestUtil.addCPInstance(_group.getGroupId());
		CPInstance cpInstance2 = CPTestUtil.addCPInstance(_group.getGroupId());
		CPInstance cpInstance3 = CPTestUtil.addCPInstance(_group.getGroupId());
		CPInstance cpInstance4 = CPTestUtil.addCPInstance(_group.getGroupId());
		CPInstance cpInstance5 = CPTestUtil.addCPInstance(_group.getGroupId());

		cpInstance1.setPrice(BigDecimal.valueOf(125));
		cpInstance2.setPrice(BigDecimal.valueOf(160));
		cpInstance3.setPrice(BigDecimal.valueOf(300));
		cpInstance4.setPrice(BigDecimal.valueOf(109));
		cpInstance5.setPrice(BigDecimal.valueOf(405));

		_cpInstanceLocalService.updateCPInstance(cpInstance1);
		_cpInstanceLocalService.updateCPInstance(cpInstance2);
		_cpInstanceLocalService.updateCPInstance(cpInstance3);
		_cpInstanceLocalService.updateCPInstance(cpInstance4);
		_cpInstanceLocalService.updateCPInstance(cpInstance5);

		CPDefinition cpDefinition1 = cpInstance1.getCPDefinition();
		CPDefinition cpDefinition2 = cpInstance2.getCPDefinition();
		CPDefinition cpDefinition3 = cpInstance3.getCPDefinition();
		CPDefinition cpDefinition4 = cpInstance4.getCPDefinition();
		CPDefinition cpDefinition5 = cpInstance5.getCPDefinition();

		long[] category1CPDefinitionIds = {
			cpDefinition2.getCPDefinitionId(), cpDefinition3.getCPDefinitionId()
		};

		long[] category2CPDefinitionIds = {
			cpDefinition3.getCPDefinitionId(),
			cpDefinition4.getCPDefinitionId(), cpDefinition5.getCPDefinitionId()
		};

		AssetCategory assetCategory1 = CPTestUtil.addCategoryToCPDefinitions(
			_group.getGroupId(), category1CPDefinitionIds);

		AssetCategory assetCategory2 = CPTestUtil.addCategoryToCPDefinitions(
			_group.getGroupId(), category2CPDefinitionIds);

		CommerceDiscount commerceDiscount1 =
			CommerceDiscountTestUtil.addPercentageCommerceDiscount(
				_group.getGroupId(), 10, 15, 20, 0,
				CommerceDiscountConstants.TARGET_PRODUCT,
				cpDefinition1.getCPDefinitionId());

		CommerceDiscount commerceDiscount2 =
			CommerceDiscountTestUtil.addPercentageCommerceDiscount(
				_group.getGroupId(), 30, 0, 0, 0,
				CommerceDiscountConstants.TARGET_CATEGORIES,
				assetCategory1.getCategoryId());

		CommerceDiscount commerceDiscount3 =
			CommerceDiscountTestUtil.addPercentageCommerceDiscount(
				_group.getGroupId(), 50, 0, 0, 0,
				CommerceDiscountConstants.TARGET_CATEGORIES,
				assetCategory2.getCategoryId());

		CommerceCurrency commerceCurrency =
			CommerceCurrencyTestUtil.addCommerceCurrency(_group.getGroupId());

		CommerceContext commerceContext = new TestCommerceContext(
			commerceCurrency, _user, _group, null, null, null);

		CommerceProductPrice commerceProductPrice1 =
			_commerceProductPriceCalculation.getCommerceProductPrice(
				cpInstance1.getCPInstanceId(), 1, commerceContext);

		CommerceProductPrice commerceProductPrice2 =
			_commerceProductPriceCalculation.getCommerceProductPrice(
				cpInstance2.getCPInstanceId(), 1, commerceContext);

		CommerceProductPrice commerceProductPrice3 =
			_commerceProductPriceCalculation.getCommerceProductPrice(
				cpInstance3.getCPInstanceId(), 1, commerceContext);

		CommerceProductPrice commerceProductPrice4 =
			_commerceProductPriceCalculation.getCommerceProductPrice(
				cpInstance4.getCPInstanceId(), 1, commerceContext);

		CommerceProductPrice commerceProductPrice5 =
			_commerceProductPriceCalculation.getCommerceProductPrice(
				cpInstance5.getCPInstanceId(), 1, commerceContext);

		BigDecimal expectedPrice1_level1 = _subtractPercentage(
			cpInstance1.getPrice(), commerceDiscount1.getLevel1());

		BigDecimal expectedPrice1_level2 = _subtractPercentage(
			expectedPrice1_level1, commerceDiscount1.getLevel2());

		BigDecimal expectedPrice1_level3 = _subtractPercentage(
			expectedPrice1_level2, commerceDiscount1.getLevel3());

		BigDecimal expectedPrice2 = _subtractPercentage(
			cpInstance2.getPrice(), commerceDiscount2.getLevel1());
		BigDecimal expectedPrice3 = _subtractPercentage(
			cpInstance3.getPrice(), commerceDiscount3.getLevel1());
		BigDecimal expectedPrice4 = _subtractPercentage(
			cpInstance4.getPrice(), commerceDiscount3.getLevel1());
		BigDecimal expectedPrice5 = _subtractPercentage(
			cpInstance5.getPrice(), commerceDiscount3.getLevel1());

		BigDecimal actualPrice1 = BigDecimal.ZERO;
		BigDecimal actualPrice2 = BigDecimal.ZERO;
		BigDecimal actualPrice3 = BigDecimal.ZERO;
		BigDecimal actualPrice4 = BigDecimal.ZERO;
		BigDecimal actualPrice5 = BigDecimal.ZERO;

		if (commerceProductPrice1 != null) {
			actualPrice1 = commerceProductPrice1.getFinalPrice().getPrice();
		}

		if (commerceProductPrice2 != null) {
			actualPrice2 = commerceProductPrice2.getFinalPrice().getPrice();
		}

		if (commerceProductPrice3 != null) {
			actualPrice3 = commerceProductPrice3.getFinalPrice().getPrice();
		}

		if (commerceProductPrice4 != null) {
			actualPrice4 = commerceProductPrice4.getFinalPrice().getPrice();
		}

		if (commerceProductPrice5 != null) {
			actualPrice5 = commerceProductPrice5.getFinalPrice().getPrice();
		}

		Assert.assertEquals(
			expectedPrice1_level3.stripTrailingZeros(),
			actualPrice1.stripTrailingZeros());
		Assert.assertEquals(
			expectedPrice2.stripTrailingZeros(),
			actualPrice2.stripTrailingZeros());
		Assert.assertEquals(
			expectedPrice3.stripTrailingZeros(),
			actualPrice3.stripTrailingZeros());
		Assert.assertEquals(
			expectedPrice4.stripTrailingZeros(),
			actualPrice4.stripTrailingZeros());
		Assert.assertEquals(
			expectedPrice5.stripTrailingZeros(),
			actualPrice5.stripTrailingZeros());
	}

	@Ignore
	@Test
	public void testCouponCodeDiscount() throws Exception {
		frutillaRule.scenario(
			"Discounts can be applied by coupon code"
		).given(
			"A product with a base price"
		).and(
			"A discount that needs a coupon code to be available to use"
		).when(
			"I insert the correct coupon code in my context"
		).then(
			"The coupon discount is available to use and is applied for " +
				"calculating the final price"
		);

		CPInstance cpInstance = CPTestUtil.addCPInstance(_group.getGroupId());

		CPDefinition cpDefinition = cpInstance.getCPDefinition();

		cpInstance.setPrice(BigDecimal.valueOf(35));

		_cpInstanceLocalService.updateCPInstance(cpInstance);

		String couponCode = "SCONTO";

		CommerceDiscount commerceDiscount =
			CommerceDiscountTestUtil.addCouponDiscount(
				_group.getGroupId(), 10, couponCode,
				CommerceDiscountConstants.TARGET_PRODUCT,
				cpDefinition.getCPDefinitionId());

		CommerceCurrency commerceCurrency =
			CommerceCurrencyTestUtil.addCommerceCurrency(_group.getGroupId());

		CommerceContext commerceContext = new TestCommerceContext(
			commerceCurrency, _user, _group, null, null, couponCode);

		CommerceProductPrice commerceProductPrice =
			_commerceProductPriceCalculation.getCommerceProductPrice(
				cpInstance.getCPInstanceId(), 1, commerceContext);

		BigDecimal actualPrice = BigDecimal.ZERO;
		BigDecimal discountPrice = BigDecimal.ZERO;

		if (commerceProductPrice != null) {
			CommerceMoney finalPrice = commerceProductPrice.getFinalPrice();

			actualPrice = finalPrice.getPrice();

			CommerceDiscountValue discountValue =
				commerceProductPrice.getDiscountValue();

			CommerceMoney discountAmount = discountValue.getDiscountAmount();

			discountPrice = discountAmount.getPrice();
		}

		Assert.assertEquals(commerceDiscount.getLevel1(), discountPrice);

		BigDecimal expectedPrice = cpInstance.getPrice().subtract(
			commerceDiscount.getLevel1());

		Assert.assertEquals(
			expectedPrice.stripTrailingZeros(),
			actualPrice.stripTrailingZeros());
	}

	@Ignore
	@Test
	public void testUserSegmentDiscount() throws Exception {
		frutillaRule.scenario(
			"When a discount has an user segment associated to it, it will " +
				"be applied only if the criteria are fulfilled"
		).given(
			"A product with a base price"
		).and(
			"A discount with a user segment associated. The user segment is " +
				"associated to a specific user"
		).when(
			"I try to get the final price of the product and I am the user " +
				"associated to the user segment"
		).then(
			"The final price will be calculated taking into consideration " +
				"the discount"
		);

		CPInstance cpInstance = CPTestUtil.addCPInstance(_group.getGroupId());

		CPDefinition cpDefinition = cpInstance.getCPDefinition();

		cpInstance.setPrice(BigDecimal.valueOf(35));

		_cpInstanceLocalService.updateCPInstance(cpInstance);

		CommerceDiscount commerceDiscount =
			CommerceDiscountTestUtil.addFixedCommerceDiscount(
				_group.getGroupId(), 10,
				CommerceDiscountConstants.TARGET_PRODUCT,
				cpDefinition.getCPDefinitionId());

		CommerceDiscountTestUtil.addDiscountUserSegmentRel(
			commerceDiscount, _user.getUserId());

		CommerceCurrency commerceCurrency =
			CommerceCurrencyTestUtil.addCommerceCurrency(_group.getGroupId());

		CommerceContext commerceContext = new TestCommerceContext(
			commerceCurrency, _user, _group, null, null, null);

		CommerceProductPrice commerceProductPrice =
			_commerceProductPriceCalculation.getCommerceProductPrice(
				cpInstance.getCPInstanceId(), 1, commerceContext);

		CommerceDiscountValue discountValue =
			commerceProductPrice.getDiscountValue();

		CommerceMoney discountAmount = discountValue.getDiscountAmount();

		Assert.assertEquals(
			commerceDiscount.getLevel1(), discountAmount.getPrice());

		BigDecimal expectedPrice = cpInstance.getPrice().subtract(
			commerceDiscount.getLevel1());

		CommerceMoney finalPrice = commerceProductPrice.getFinalPrice();

		BigDecimal actualPrice = finalPrice.getPrice();

		Assert.assertEquals(
			expectedPrice.stripTrailingZeros(),
			actualPrice.stripTrailingZeros());
	}

	@Rule
	public FrutillaRule frutillaRule = new FrutillaRule();

	private BigDecimal _subtractPercentage(
		BigDecimal base, BigDecimal percentage) {

		BigDecimal percentageValue = percentage.divide(_ONE_HUNDRED);

		BigDecimal calculatedPercentage = percentageValue.multiply(base);

		return base.subtract(calculatedPercentage);
	}

	private static final BigDecimal _ONE_HUNDRED = BigDecimal.valueOf(100);

	@Inject
	private CommerceProductPriceCalculation _commerceProductPriceCalculation;

	@Inject
	private CPInstanceLocalService _cpInstanceLocalService;

	@DeleteAfterTestRun
	private Group _group;

	@DeleteAfterTestRun
	private User _user;

}