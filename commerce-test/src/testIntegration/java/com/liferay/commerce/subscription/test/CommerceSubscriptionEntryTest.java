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

package com.liferay.commerce.subscription.test;

import com.liferay.arquillian.extension.junit.bridge.junit.Arquillian;
import com.liferay.commerce.internal.test.util.CommerceTestUtil;
import com.liferay.commerce.model.CommerceOrder;
import com.liferay.commerce.model.CommerceSubscriptionEntry;
import com.liferay.commerce.product.model.CPDefinition;
import com.liferay.commerce.product.model.CPInstance;
import com.liferay.commerce.product.model.CPOption;
import com.liferay.commerce.product.service.CPDefinitionLocalServiceUtil;
import com.liferay.commerce.product.service.CPDefinitionOptionRelLocalServiceUtil;
import com.liferay.commerce.product.service.CPInstanceLocalServiceUtil;
import com.liferay.commerce.product.test.util.CPTestUtil;
import com.liferay.commerce.product.type.simple.constants.SimpleCPTypeConstants;
import com.liferay.commerce.service.CommerceSubscriptionCycleEntryLocalService;
import com.liferay.commerce.service.CommerceSubscriptionEntryLocalService;
import com.liferay.commerce.subscription.CommerceSubscriptionEntryHelper;
import com.liferay.commerce.subscription.test.util.CommerceSubscriptionEntryTestUtil;
import com.liferay.commerce.util.comparator.CommerceSubscriptionEntryCreateDateComparator;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.model.Group;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.test.rule.AggregateTestRule;
import com.liferay.portal.kernel.test.rule.DeleteAfterTestRun;
import com.liferay.portal.kernel.test.util.GroupTestUtil;
import com.liferay.portal.kernel.test.util.RandomTestUtil;
import com.liferay.portal.kernel.test.util.ServiceContextTestUtil;
import com.liferay.portal.kernel.test.util.UserTestUtil;
import com.liferay.portal.test.rule.Inject;
import com.liferay.portal.test.rule.LiferayIntegrationTestRule;
import com.liferay.portal.test.rule.PermissionCheckerTestRule;

import java.util.Date;
import java.util.List;

import org.frutilla.FrutillaRule;

import org.junit.Assert;
import org.junit.Before;
import org.junit.ClassRule;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 * @author Alessio Antonio Rendina
 */
@RunWith(Arquillian.class)
public class CommerceSubscriptionEntryTest {

	@ClassRule
	@Rule
	public static AggregateTestRule aggregateTestRule = new AggregateTestRule(
		new LiferayIntegrationTestRule(), PermissionCheckerTestRule.INSTANCE);

	@Before
	public void setUp() throws Exception {
		_group = GroupTestUtil.addGroup();
		_user = UserTestUtil.addUser();
	}

	@Test
	public void testAddCommerceSubscriptionEntry() throws Exception {
		frutillaRule.scenario(
			"Add a product subscription entry"
		).given(
			"A SKU that is subscription enabled"
		).and(
			"Has 1 max subscription cycle number"
		).when(
			"I buy that SKU"
		).and(
			"The order is ready to be shipped (orderStatus = To Transmit)"
		).then(
			"The product subscription entry is created"
		);

		CommerceSubscriptionEntryTestUtil.setUpCommerceSubscriptionEntry(
			_group.getGroupId(), _user.getUserId(), 1,
			_commerceSubscriptionEntryHelper);

		int commerceSubscriptionEntriesCount =
			_commerceSubscriptionEntryLocalService.
				getCommerceSubscriptionEntriesCount(
					_group.getGroupId(), _user.getUserId());

		Assert.assertEquals(1, commerceSubscriptionEntriesCount);
	}

	@Test
	public void testOverrideCPInstanceSubscriptionInfoAllDisabled()
		throws Exception {

		frutillaRule.scenario(
			"Add an order"
		).given(
			"One subscription disabled product with two SKUs"
		).but(
			"One of them overrides the subscription info"
		).when(
			"Order is placed"
		).then(
			"No subscription entries are created"
		);

		_testOverrideCPInstanceSubscriptionInfo(false, false);
	}

	@Test
	public void testOverrideCPInstanceSubscriptionInfoAllEnabled()
		throws Exception {

		frutillaRule.scenario(
			"Add an order"
		).given(
			"One subscription enabled product with two SKUs"
		).but(
			"One of them overrides the subscription info"
		).when(
			"Subscription entries are created"
		).then(
			"They should have right subscription info"
		);

		_testOverrideCPInstanceSubscriptionInfo(true, true);
	}

	@Test
	public void testOverrideCPInstanceSubscriptionInfoDefinitionEnabled()
		throws Exception {

		frutillaRule.scenario(
			"Add an order"
		).given(
			"One subscription enabled product with two SKUs"
		).but(
			"One of them overrides the subscription info"
		).when(
			"A subscription entry is created"
		).then(
			"It should have right subscription info"
		);

		_testOverrideCPInstanceSubscriptionInfo(true, false);
	}

	@Test
	public void testOverrideCPInstanceSubscriptionInfoInstanceEnabled()
		throws Exception {

		frutillaRule.scenario(
			"Add an order"
		).given(
			"One subscription disabled product with two SKUs"
		).but(
			"One of them overrides the subscription info"
		).when(
			"A subscription entry is created"
		).then(
			"It should have right subscription info"
		);

		_testOverrideCPInstanceSubscriptionInfo(false, true);
	}

	@Test
	public void testRenewCommerceSubscriptionEntry() throws Exception {
		frutillaRule.scenario(
			"Renew a subscription entry"
		).given(
			"An enabled subscription that has 1 max subscription cycle number"
		).when(
			"The next iteration date of the subscription is outdated"
		).then(
			"The product subscription entry is renewed"
		).and(
			"The product subscription entry is no more active"
		);

		CommerceSubscriptionEntryTestUtil.setUpCommerceSubscriptionEntry(
			_group.getGroupId(), _user.getUserId(), 1,
			_commerceSubscriptionEntryHelper);

		List<CommerceSubscriptionEntry> commerceSubscriptionEntries =
			_commerceSubscriptionEntryLocalService.
				getCommerceSubscriptionEntries(
					_group.getGroupId(), _user.getUserId(), QueryUtil.ALL_POS,
					QueryUtil.ALL_POS, null);

		CommerceSubscriptionEntry commerceSubscriptionEntry =
			commerceSubscriptionEntries.get(0);

		// Renew with the original next iteration date

		int commerceSubscriptionCycleEntriesCount =
			_getRenewedCommerceSubscriptionEntriesCount(
				commerceSubscriptionEntry);

		Assert.assertEquals(1, commerceSubscriptionCycleEntriesCount);

		Assert.assertEquals(true, commerceSubscriptionEntry.isActive());

		// Renew with the actual date

		commerceSubscriptionEntry.setNextIterationDate(new Date());

		commerceSubscriptionEntry =
			_commerceSubscriptionEntryLocalService.
				updateCommerceSubscriptionEntry(commerceSubscriptionEntry);

		commerceSubscriptionCycleEntriesCount =
			_getRenewedCommerceSubscriptionEntriesCount(
				commerceSubscriptionEntry);

		commerceSubscriptionEntry =
			_commerceSubscriptionEntryLocalService.getCommerceSubscriptionEntry(
				commerceSubscriptionEntry.getCommerceSubscriptionEntryId());

		Assert.assertEquals(2, commerceSubscriptionCycleEntriesCount);
		Assert.assertEquals(false, commerceSubscriptionEntry.isActive());
	}

	@Rule
	public FrutillaRule frutillaRule = new FrutillaRule();

	private int _getRenewedCommerceSubscriptionEntriesCount(
			CommerceSubscriptionEntry commerceSubscriptionEntry)
		throws PortalException {

		_commerceSubscriptionEntryHelper.renewSubscriptionEntry(
			commerceSubscriptionEntry);

		return _commerceSubscriptionCycleEntryLocalService.
			getCommerceSubscriptionCycleEntriesCount(
				commerceSubscriptionEntry.getCommerceSubscriptionEntryId());
	}

	private void _testOverrideCPInstanceSubscriptionInfo(
			boolean cpDefinitionSubscriptionEnabled,
			boolean cpInstanceSubscriptionEnabled)
		throws Exception {

		long groupId = _group.getGroupId();

		CPDefinition cpDefinition = CPTestUtil.addCPDefinition(
			groupId, SimpleCPTypeConstants.NAME, false, false);

		CommerceTestUtil.addBackOrderCPDefinitionInventory(cpDefinition);

		CPOption cpOption = CPTestUtil.addCPOption(groupId, true);

		CPTestUtil.addCPOptionValue(cpOption);
		CPTestUtil.addCPOptionValue(cpOption);

		CPDefinitionOptionRelLocalServiceUtil.addCPDefinitionOptionRel(
			cpDefinition.getCPDefinitionId(), cpOption.getCPOptionId(), true,
			ServiceContextTestUtil.getServiceContext(groupId));

		long cpDefinitionSubscriptionCycleLength = RandomTestUtil.randomLong();
		String cpDefinitionSubscriptionCyclePeriod =
			RandomTestUtil.randomString();
		long cpDefinitionMaxSubscriptionCyclesNumber =
			RandomTestUtil.randomLong();

		if (cpDefinitionSubscriptionEnabled) {
			cpDefinition.setSubscriptionEnabled(true);
			cpDefinition.setSubscriptionCycleLength(
				cpDefinitionSubscriptionCycleLength);
			cpDefinition.setSubscriptionCyclePeriod(
				cpDefinitionSubscriptionCyclePeriod);
			cpDefinition.setMaxSubscriptionCyclesNumber(
				cpDefinitionMaxSubscriptionCyclesNumber);

			cpDefinition = CPDefinitionLocalServiceUtil.updateCPDefinition(
				cpDefinition);
		}

		long cpInstanceSubscriptionCycleLength = RandomTestUtil.randomLong();
		String cpInstanceSubscriptionCyclePeriod =
			RandomTestUtil.randomString();
		long cpInstanceMaxSubscriptionCyclesNumber =
			RandomTestUtil.randomLong();

		CPTestUtil.buildCPInstances(cpDefinition);

		CommerceOrder commerceOrder = CommerceTestUtil.addUserCommerceOrder(
			groupId, _user.getUserId(), 0);

		List<CPInstance> cpInstances = cpDefinition.getCPInstances();

		for (CPInstance cpInstance : cpInstances) {
			if (cpInstances.indexOf(cpInstance) == 0) {
				cpInstance.setOverrideSubscriptionInfo(true);
				cpInstance.setSubscriptionEnabled(
					cpInstanceSubscriptionEnabled);
				cpInstance.setSubscriptionCycleLength(
					cpInstanceSubscriptionCycleLength);
				cpInstance.setSubscriptionCyclePeriod(
					cpInstanceSubscriptionCyclePeriod);
				cpInstance.setMaxSubscriptionCyclesNumber(
					cpInstanceMaxSubscriptionCyclesNumber);

				cpInstance = CPInstanceLocalServiceUtil.updateCPInstance(
					cpInstance);
			}

			CommerceTestUtil.addCommerceOrderItem(
				commerceOrder.getCommerceOrderId(),
				cpInstance.getCPInstanceId(), 1);
		}

		_commerceSubscriptionEntryHelper.checkCommerceSubscriptions(
			commerceOrder);

		List<CommerceSubscriptionEntry> commerceSubscriptionEntries =
			_commerceSubscriptionEntryLocalService.
				getCommerceSubscriptionEntries(
					groupId, _user.getUserId(), QueryUtil.ALL_POS,
					QueryUtil.ALL_POS,
					new CommerceSubscriptionEntryCreateDateComparator());

		if (cpDefinitionSubscriptionEnabled && cpInstanceSubscriptionEnabled) {
			Assert.assertEquals(
				commerceSubscriptionEntries.toString(), 2,
				commerceSubscriptionEntries.size());

			CommerceSubscriptionEntry commerceSubscriptionEntry1 =
				commerceSubscriptionEntries.get(0);
			CommerceSubscriptionEntry commerceSubscriptionEntry2 =
				commerceSubscriptionEntries.get(1);

			Assert.assertEquals(
				cpInstanceSubscriptionCycleLength,
				commerceSubscriptionEntry1.getSubscriptionCycleLength());
			Assert.assertEquals(
				cpInstanceSubscriptionCyclePeriod,
				commerceSubscriptionEntry1.getSubscriptionCyclePeriod());
			Assert.assertEquals(
				cpInstanceMaxSubscriptionCyclesNumber,
				commerceSubscriptionEntry1.getMaxSubscriptionCyclesNumber());

			Assert.assertEquals(
				cpDefinitionSubscriptionCycleLength,
				commerceSubscriptionEntry2.getSubscriptionCycleLength());
			Assert.assertEquals(
				cpDefinitionSubscriptionCyclePeriod,
				commerceSubscriptionEntry2.getSubscriptionCyclePeriod());
			Assert.assertEquals(
				cpDefinitionMaxSubscriptionCyclesNumber,
				commerceSubscriptionEntry2.getMaxSubscriptionCyclesNumber());
		}
		else if (!cpDefinitionSubscriptionEnabled &&
				 !cpInstanceSubscriptionEnabled) {

			Assert.assertEquals(
				commerceSubscriptionEntries.toString(), 0,
				commerceSubscriptionEntries.size());
		}
		else {
			Assert.assertEquals(
				commerceSubscriptionEntries.toString(), 1,
				commerceSubscriptionEntries.size());

			CommerceSubscriptionEntry commerceSubscriptionEntry =
				commerceSubscriptionEntries.get(0);

			if (cpInstanceSubscriptionEnabled) {
				Assert.assertEquals(
					cpInstanceSubscriptionCycleLength,
					commerceSubscriptionEntry.getSubscriptionCycleLength());
				Assert.assertEquals(
					cpInstanceSubscriptionCyclePeriod,
					commerceSubscriptionEntry.getSubscriptionCyclePeriod());
				Assert.assertEquals(
					cpInstanceMaxSubscriptionCyclesNumber,
					commerceSubscriptionEntry.getMaxSubscriptionCyclesNumber());
			}
			else {
				Assert.assertEquals(
					cpDefinitionSubscriptionCycleLength,
					commerceSubscriptionEntry.getSubscriptionCycleLength());
				Assert.assertEquals(
					cpDefinitionSubscriptionCyclePeriod,
					commerceSubscriptionEntry.getSubscriptionCyclePeriod());
				Assert.assertEquals(
					cpDefinitionMaxSubscriptionCyclesNumber,
					commerceSubscriptionEntry.getMaxSubscriptionCyclesNumber());
			}
		}
	}

	@Inject
	private CommerceSubscriptionCycleEntryLocalService
		_commerceSubscriptionCycleEntryLocalService;

	@Inject
	private CommerceSubscriptionEntryHelper _commerceSubscriptionEntryHelper;

	@Inject
	private CommerceSubscriptionEntryLocalService
		_commerceSubscriptionEntryLocalService;

	@DeleteAfterTestRun
	private Group _group;

	@DeleteAfterTestRun
	private User _user;

}