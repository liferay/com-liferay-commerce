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

package com.liferay.commerce.test;

import com.liferay.arquillian.extension.junit.bridge.junit.Arquillian;
import com.liferay.commerce.context.CommerceContext;
import com.liferay.commerce.context.CommerceContextFactory;
import com.liferay.commerce.currency.model.CommerceCurrency;
import com.liferay.commerce.currency.model.CommerceMoney;
import com.liferay.commerce.currency.service.CommerceCurrencyServiceUtil;
import com.liferay.commerce.currency.test.util.CommerceCurrencyTestUtil;
import com.liferay.commerce.price.CommerceProductPriceCalculation;
import com.liferay.commerce.price.list.model.CommercePriceEntry;
import com.liferay.commerce.price.list.model.CommercePriceList;
import com.liferay.commerce.price.list.service.CommercePriceEntryLocalService;
import com.liferay.commerce.price.list.service.CommercePriceListLocalService;
import com.liferay.commerce.price.list.service.CommercePriceListUserSegmentEntryRelLocalService;
import com.liferay.commerce.price.list.service.CommerceTierPriceEntryLocalService;
import com.liferay.commerce.product.model.CPInstance;
import com.liferay.commerce.product.test.util.CPTestUtil;
import com.liferay.commerce.test.util.TestCommerceContext;
import com.liferay.commerce.user.segment.model.CommerceUserSegmentCriterionConstants;
import com.liferay.commerce.user.segment.model.CommerceUserSegmentEntry;
import com.liferay.commerce.user.segment.service.CommerceUserSegmentCriterionLocalService;
import com.liferay.commerce.user.segment.service.CommerceUserSegmentEntryLocalService;
import com.liferay.portal.kernel.model.Organization;
import com.liferay.portal.kernel.model.RoleConstants;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.test.rule.AggregateTestRule;
import com.liferay.portal.kernel.test.rule.DeleteAfterTestRun;
import com.liferay.portal.kernel.test.util.OrganizationTestUtil;
import com.liferay.portal.kernel.test.util.RandomTestUtil;
import com.liferay.portal.kernel.test.util.ServiceContextTestUtil;
import com.liferay.portal.kernel.test.util.UserTestUtil;
import com.liferay.portal.test.rule.Inject;
import com.liferay.portal.test.rule.LiferayIntegrationTestRule;
import com.liferay.portal.test.rule.PermissionCheckerTestRule;

import java.math.BigDecimal;

import java.util.Collections;
import java.util.List;
import java.util.Locale;

import org.frutilla.FrutillaRule;

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
public class CommerceProductPriceCalculationTest {

	@ClassRule
	@Rule
	public static final AggregateTestRule aggregateTestRule =
		new AggregateTestRule(
			new LiferayIntegrationTestRule(),
			PermissionCheckerTestRule.INSTANCE);

	@Before
	public void setUp() throws Exception {
		_organization = OrganizationTestUtil.addOrganization(true);

		_buyerUser1 = UserTestUtil.addOrganizationUser(
			_organization, RoleConstants.ORGANIZATION_USER);
		_buyerUser2 = UserTestUtil.addOrganizationUser(
			_organization, RoleConstants.ORGANIZATION_USER);
	}

	@Test
	public void testMultiPriceList() throws Exception {
		frutillaRule.scenario(
			"Check Price List priority functionality"
		).given(
			"An organization with a site"
		).and(
			"A buyer user in that organization"
		).when(
			"I add a user segment"
		).and(
			"I add a user criterion for that user segment"
		).and(
			"I add 2 price lists with the previous user segment associated"
		).and(
			"I associate a product to these price lists with a price value"
		).then(
			"The final price will be the one from the price list with the " +
				"highest priority"
		);

		ServiceContext serviceContext =
			ServiceContextTestUtil.getServiceContext(
				_organization.getGroupId());

		CommerceUserSegmentEntry commerceUserSegmentEntry =
			_commerceUserSegmentEntryLocalService.addCommerceUserSegmentEntry(
				Collections.singletonMap(
					Locale.US, RandomTestUtil.randomString()),
				RandomTestUtil.randomString(), true, false, 0, serviceContext);

		_commerceUserSegmentCriterionLocalService.
			addCommerceUserSegmentCriterion(
				commerceUserSegmentEntry.getCommerceUserSegmentEntryId(),
				CommerceUserSegmentCriterionConstants.TYPE_USER,
				String.valueOf(_buyerUser1.getUserId()), 0, serviceContext);

		CommerceCurrency commerceCurrency =
			CommerceCurrencyTestUtil.addCommerceCurrency(
				_organization.getGroupId());

		CommercePriceList commercePriceList1 =
			_commercePriceListLocalService.addCommercePriceList(
				commerceCurrency.getCommerceCurrencyId(),
				RandomTestUtil.randomString(), 0, 1, 1, 2018, 3, 4, 1, 1, 2020,
				3, 4, true, serviceContext);

		CommercePriceList commercePriceList2 =
			_commercePriceListLocalService.addCommercePriceList(
				commerceCurrency.getCommerceCurrencyId(),
				RandomTestUtil.randomString(), 1, 1, 1, 2018, 3, 4, 1, 1, 2020,
				3, 4, true, serviceContext);

		_commercePriceListUserSegmentEntryRelLocalService.
			addCommercePriceListUserSegmentEntryRel(
				commercePriceList1.getCommercePriceListId(),
				commerceUserSegmentEntry.getCommerceUserSegmentEntryId(), 0,
				serviceContext);

		_commercePriceListUserSegmentEntryRelLocalService.
			addCommercePriceListUserSegmentEntryRel(
				commercePriceList2.getCommercePriceListId(),
				commerceUserSegmentEntry.getCommerceUserSegmentEntryId(), 0,
				serviceContext);

		CPInstance cpInstance = CPTestUtil.addCPInstance(
			_organization.getGroupId());

		cpInstance.setPrice(new BigDecimal(20));

		_commercePriceEntryLocalService.addCommercePriceEntry(
			cpInstance.getCPInstanceId(),
			commercePriceList1.getCommercePriceListId(), new BigDecimal(13),
			null, serviceContext);

		CommercePriceEntry commercePriceEntry2 =
			_commercePriceEntryLocalService.addCommercePriceEntry(
				cpInstance.getCPInstanceId(),
				commercePriceList2.getCommercePriceListId(), new BigDecimal(15),
				null, serviceContext);

		CommerceContext commerceContext = new TestCommerceContext(
			commerceCurrency, _buyerUser1, _organization, null, null);

		CommerceMoney commerceMoney =
			_commerceProductPriceCalculation.getUnitPrice(
				cpInstance.getCPInstanceId(), 1,
				commerceContext.getCommercePriceList(),
				commerceContext.getCommerceCurrency());

		Assert.assertEquals(
			commercePriceEntry2.getPrice(), commerceMoney.getPrice());
	}

	@Test
	public void testMultiPriceListWithDifferentUserSegment() throws Exception {
		frutillaRule.scenario(
			"Check Price List segmentation functionality"
		).given(
			"An organization with a site"
		).and(
			"A couple of buyer user in that organization"
		).when(
			"I add a couple of user segment"
		).and(
			"I add a user criterion for each user segment"
		).and(
			"I add 2 price lists, one for each user segment"
		).and(
			"I associate a product to these price lists with a price value"
		).then(
			"The final price will be the one from the price list with the " +
				"correct user segment fot that user"
		);

		ServiceContext serviceContext =
			ServiceContextTestUtil.getServiceContext(
				_organization.getGroupId());

		CommerceUserSegmentEntry commerceUserSegmentEntry1 =
			_commerceUserSegmentEntryLocalService.addCommerceUserSegmentEntry(
				Collections.singletonMap(
					Locale.US, RandomTestUtil.randomString()),
				RandomTestUtil.randomString(), true, false, 0, serviceContext);

		_commerceUserSegmentCriterionLocalService.
			addCommerceUserSegmentCriterion(
				commerceUserSegmentEntry1.getCommerceUserSegmentEntryId(),
				CommerceUserSegmentCriterionConstants.TYPE_USER,
				String.valueOf(_buyerUser1.getUserId()), 0, serviceContext);

		CommerceUserSegmentEntry commerceUserSegmentEntry2 =
			_commerceUserSegmentEntryLocalService.addCommerceUserSegmentEntry(
				Collections.singletonMap(
					Locale.US, RandomTestUtil.randomString()),
				RandomTestUtil.randomString(), true, false, 0, serviceContext);

		_commerceUserSegmentCriterionLocalService.
			addCommerceUserSegmentCriterion(
				commerceUserSegmentEntry2.getCommerceUserSegmentEntryId(),
				CommerceUserSegmentCriterionConstants.TYPE_USER,
				String.valueOf(_buyerUser2.getUserId()), 0, serviceContext);

		CommerceCurrency commerceCurrency =
			CommerceCurrencyTestUtil.addCommerceCurrency(
				_organization.getGroupId());

		CommercePriceList commercePriceList1 =
			_commercePriceListLocalService.addCommercePriceList(
				commerceCurrency.getCommerceCurrencyId(),
				RandomTestUtil.randomString(), 0, 1, 1, 2018, 3, 4, 1, 1, 2020,
				3, 4, true, serviceContext);

		CommercePriceList commercePriceList2 =
			_commercePriceListLocalService.addCommercePriceList(
				commerceCurrency.getCommerceCurrencyId(),
				RandomTestUtil.randomString(), 1, 1, 1, 2018, 3, 4, 1, 1, 2020,
				3, 4, true, serviceContext);

		_commercePriceListUserSegmentEntryRelLocalService.
			addCommercePriceListUserSegmentEntryRel(
				commercePriceList1.getCommercePriceListId(),
				commerceUserSegmentEntry1.getCommerceUserSegmentEntryId(), 0,
				serviceContext);

		_commercePriceListUserSegmentEntryRelLocalService.
			addCommercePriceListUserSegmentEntryRel(
				commercePriceList2.getCommercePriceListId(),
				commerceUserSegmentEntry2.getCommerceUserSegmentEntryId(), 0,
				serviceContext);

		CPInstance cpInstance = CPTestUtil.addCPInstance(
			_organization.getGroupId());

		cpInstance.setPrice(new BigDecimal(20));

		CommercePriceEntry commercePriceEntry1 =
			_commercePriceEntryLocalService.addCommercePriceEntry(
				cpInstance.getCPInstanceId(),
				commercePriceList1.getCommercePriceListId(), new BigDecimal(13),
				null, serviceContext);

		_commercePriceEntryLocalService.addCommercePriceEntry(
			cpInstance.getCPInstanceId(),
			commercePriceList2.getCommercePriceListId(), new BigDecimal(15),
			null, serviceContext);

		CommerceContext commerceContext = new TestCommerceContext(
			commerceCurrency, _buyerUser1, _organization, null, null);

		CommerceMoney commerceMoney =
			_commerceProductPriceCalculation.getUnitPrice(
				cpInstance.getCPInstanceId(), 1,
				commerceContext.getCommercePriceList(),
				commerceContext.getCommerceCurrency());

		Assert.assertEquals(
			commercePriceEntry1.getPrice(), commerceMoney.getPrice());
	}

	@Test
	public void testPriceListPrice() throws Exception {
		frutillaRule.scenario(
			"Check Price List price functionality"
		).given(
			"An organization with a site"
		).and(
			"A buyer user in that organization"
		).when(
			"I add a user segment"
		).and(
			"I add a user criterion for that user segment"
		).and(
			"I add a price list with the previous user segment associated"
		).and(
			"I associate a product to that price list with a price value"
		).then(
			"The user will be eligible to use the price set in the price " +
				"list when searching the associated product"
		);

		ServiceContext serviceContext =
			ServiceContextTestUtil.getServiceContext(
				_organization.getGroupId());

		CommerceUserSegmentEntry commerceUserSegmentEntry =
			_commerceUserSegmentEntryLocalService.addCommerceUserSegmentEntry(
				Collections.singletonMap(
					Locale.US, RandomTestUtil.randomString()),
				RandomTestUtil.randomString(), true, false, 0, serviceContext);

		_commerceUserSegmentCriterionLocalService.
			addCommerceUserSegmentCriterion(
				commerceUserSegmentEntry.getCommerceUserSegmentEntryId(),
				CommerceUserSegmentCriterionConstants.TYPE_USER,
				String.valueOf(_buyerUser1.getUserId()), 0, serviceContext);

		CommerceCurrency commerceCurrency =
			CommerceCurrencyTestUtil.addCommerceCurrency(
				_organization.getGroupId());

		CommercePriceList commercePriceList =
			_commercePriceListLocalService.addCommercePriceList(
				commerceCurrency.getCommerceCurrencyId(),
				RandomTestUtil.randomString(), 0, 1, 1, 2018, 3, 4, 1, 1, 2020,
				3, 4, true, serviceContext);

		_commercePriceListUserSegmentEntryRelLocalService.
			addCommercePriceListUserSegmentEntryRel(
				commercePriceList.getCommercePriceListId(),
				commerceUserSegmentEntry.getCommerceUserSegmentEntryId(), 0,
				serviceContext);

		CPInstance cpInstance = CPTestUtil.addCPInstance(
			_organization.getGroupId());

		cpInstance.setPrice(new BigDecimal(20));

		CommercePriceEntry commercePriceEntry =
			_commercePriceEntryLocalService.addCommercePriceEntry(
				cpInstance.getCPInstanceId(),
				commercePriceList.getCommercePriceListId(), new BigDecimal(13),
				null, serviceContext);

		CommerceContext commerceContext = new TestCommerceContext(
			commerceCurrency, _buyerUser1, _organization, null, null);

		CommerceMoney commerceMoney =
			_commerceProductPriceCalculation.getUnitPrice(
				cpInstance.getCPInstanceId(), 1,
				commerceContext.getCommercePriceList(),
				commerceContext.getCommerceCurrency());

		Assert.assertEquals(
			commercePriceEntry.getPrice(), commerceMoney.getPrice());
	}

	@Test
	public void testPriceListPriceWithDifferentCurrency() throws Exception {
		frutillaRule.scenario(
			"Check Price List price functionality with currency conversion"
		).given(
			"An organization with a site"
		).and(
			"A buyer user in that organization"
		).when(
			"I add a user segment"
		).and(
			"I add a user criterion for that user segment"
		).and(
			"I add a price list with the previous user segment associated"
		).and(
			"I associate a product to that price list with a price value"
		).then(
			"The user will be eligible to use the price set in the price " +
				"list when searching the associated product"
		);

		ServiceContext serviceContext =
			ServiceContextTestUtil.getServiceContext(
				_organization.getGroupId());

		CommerceUserSegmentEntry commerceUserSegmentEntry =
			_commerceUserSegmentEntryLocalService.addCommerceUserSegmentEntry(
				Collections.singletonMap(
					Locale.US, RandomTestUtil.randomString()),
				RandomTestUtil.randomString(), true, false, 0, serviceContext);

		_commerceUserSegmentCriterionLocalService.
			addCommerceUserSegmentCriterion(
				commerceUserSegmentEntry.getCommerceUserSegmentEntryId(),
				CommerceUserSegmentCriterionConstants.TYPE_USER,
				String.valueOf(_buyerUser1.getUserId()), 0, serviceContext);

		CommerceCurrency commerceCurrency =
			CommerceCurrencyTestUtil.addCommerceCurrency(
				_organization.getGroupId());

		CommercePriceList commercePriceList =
			_commercePriceListLocalService.addCommercePriceList(
				commerceCurrency.getCommerceCurrencyId(),
				RandomTestUtil.randomString(), 0, 1, 1, 2018, 3, 4, 1, 1, 2020,
				3, 4, true, serviceContext);

		_commercePriceListUserSegmentEntryRelLocalService.
			addCommercePriceListUserSegmentEntryRel(
				commercePriceList.getCommercePriceListId(),
				commerceUserSegmentEntry.getCommerceUserSegmentEntryId(), 0,
				serviceContext);

		CPInstance cpInstance = CPTestUtil.addCPInstance(
			_organization.getGroupId());

		cpInstance.setPrice(new BigDecimal(20));

		CommercePriceEntry commercePriceEntry =
			_commercePriceEntryLocalService.addCommercePriceEntry(
				cpInstance.getCPInstanceId(),
				commercePriceList.getCommercePriceListId(), new BigDecimal(13),
				null, serviceContext);

		List<CommerceCurrency> targetCurrencies =
			CommerceCurrencyServiceUtil.getCommerceCurrencies(
				_organization.getGroupId(), true, 0,
				CommerceCurrencyServiceUtil.getCommerceCurrenciesCount(
					_organization.getGroupId(), true),
				null);

		CommerceCurrency targetCurrency = targetCurrencies.get(0);

		String targetCurrencyCode = targetCurrency.getCode();

		String commerceCurrencyCode = commerceCurrency.getCode();

		if (targetCurrencyCode.equals(commerceCurrencyCode) &&
			(targetCurrencies.size() > 1)) {

			targetCurrency = targetCurrencies.get(1);
		}

		CommerceContext commerceContext = new TestCommerceContext(
			commerceCurrency, _buyerUser1, _organization, null, null);

		CommerceMoney commerceMoney =
			_commerceProductPriceCalculation.getUnitPrice(
				cpInstance.getCPInstanceId(), 1,
				commerceContext.getCommercePriceList(),
				commerceContext.getCommerceCurrency());

		BigDecimal commercePrice = commercePriceEntry.getPrice();

		CommerceCurrency commerceMoneyCurrency =
			commerceMoney.getCommerceCurrency();

		BigDecimal expectedPrice = commercePrice.multiply(
			commerceMoneyCurrency.getRate());

		BigDecimal actualPrice = commerceMoney.getPrice();

		Assert.assertEquals(
			expectedPrice.doubleValue(), actualPrice.doubleValue(), 0.0000001);
	}

	@Test
	public void testTierPrice() throws Exception {
		frutillaRule.scenario(
			"Check Tier Price functionality"
		).given(
			"An organization with a site"
		).and(
			"A buyer user in that organization"
		).when(
			"I add a user segment"
		).and(
			"I add a user criterion for that user segment"
		).and(
			"I add a price list with the previous user segment associated"
		).and(
			"I associate a product to that price list with a price value"
		).and(
			"I add a tier price with a given minimum quantity"
		).then(
			"The user will be eligible to use the tier price when all " +
				"requirements are met"
		);

		ServiceContext serviceContext =
			ServiceContextTestUtil.getServiceContext(
				_organization.getGroupId());

		CommerceUserSegmentEntry commerceUserSegmentEntry =
			_commerceUserSegmentEntryLocalService.addCommerceUserSegmentEntry(
				Collections.singletonMap(
					Locale.US, RandomTestUtil.randomString()),
				RandomTestUtil.randomString(), true, false, 0, serviceContext);

		_commerceUserSegmentCriterionLocalService.
			addCommerceUserSegmentCriterion(
				commerceUserSegmentEntry.getCommerceUserSegmentEntryId(),
				CommerceUserSegmentCriterionConstants.TYPE_USER,
				String.valueOf(_buyerUser1.getUserId()), 0, serviceContext);

		CommerceCurrency commerceCurrency =
			CommerceCurrencyTestUtil.addCommerceCurrency(
				_organization.getGroupId());

		CommercePriceList commercePriceList =
			_commercePriceListLocalService.addCommercePriceList(
				commerceCurrency.getCommerceCurrencyId(),
				RandomTestUtil.randomString(), 0, 1, 1, 2018, 3, 4, 1, 1, 2020,
				3, 4, true, serviceContext);

		CPInstance cpInstance = CPTestUtil.addCPInstance(
			_organization.getGroupId());

		cpInstance.setPrice(new BigDecimal(20));

		CommercePriceEntry commercePriceEntry =
			_commercePriceEntryLocalService.addCommercePriceEntry(
				cpInstance.getCPInstanceId(),
				commercePriceList.getCommercePriceListId(), new BigDecimal(13),
				null, serviceContext);

		int quantity = 5;

		_commerceTierPriceEntryLocalService.addCommerceTierPriceEntry(
			commercePriceEntry.getCommercePriceEntryId(), new BigDecimal(12),
			null, quantity, serviceContext);

		CommerceContext commerceContext = new TestCommerceContext(
			commerceCurrency, _buyerUser1, _organization, null, null);

		CommerceMoney commerceMoney =
			_commerceProductPriceCalculation.getUnitPrice(
				cpInstance.getCPInstanceId(), quantity,
				commerceContext.getCommercePriceList(),
				commerceContext.getCommerceCurrency());

		BigDecimal commercePrice = commercePriceEntry.getPrice();

		CommerceCurrency commerceMoneyCurrency =
			commerceMoney.getCommerceCurrency();

		BigDecimal expectedPrice = commercePrice.multiply(
			commerceMoneyCurrency.getRate());

		BigDecimal actualPrice = commerceMoney.getPrice();

		Assert.assertEquals(
			expectedPrice.doubleValue(), actualPrice.doubleValue(), 0.0000001);
	}

	@Rule
	public FrutillaRule frutillaRule = new FrutillaRule();

	@DeleteAfterTestRun
	private User _buyerUser1;

	@DeleteAfterTestRun
	private User _buyerUser2;

	@Inject
	private CommerceContextFactory _commerceContextFactory;

	@Inject
	private CommercePriceEntryLocalService _commercePriceEntryLocalService;

	@Inject
	private CommercePriceListLocalService _commercePriceListLocalService;

	@Inject
	private CommercePriceListUserSegmentEntryRelLocalService
		_commercePriceListUserSegmentEntryRelLocalService;

	@Inject
	private CommerceProductPriceCalculation _commerceProductPriceCalculation;

	@Inject
	private CommerceTierPriceEntryLocalService
		_commerceTierPriceEntryLocalService;

	@Inject
	private CommerceUserSegmentCriterionLocalService
		_commerceUserSegmentCriterionLocalService;

	@Inject
	private CommerceUserSegmentEntryLocalService
		_commerceUserSegmentEntryLocalService;

	@DeleteAfterTestRun
	private Organization _organization;

}