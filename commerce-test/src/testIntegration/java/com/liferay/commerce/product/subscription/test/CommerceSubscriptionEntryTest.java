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

package com.liferay.commerce.product.subscription.test;

import com.liferay.arquillian.extension.junit.bridge.junit.Arquillian;
import com.liferay.commerce.product.subscription.test.util.CommerceSubscriptionEntryTestUtil;
import com.liferay.commerce.service.CommerceSubscriptionEntryLocalService;
import com.liferay.portal.kernel.model.Group;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.test.rule.AggregateTestRule;
import com.liferay.portal.kernel.test.rule.DeleteAfterTestRun;
import com.liferay.portal.kernel.test.util.GroupTestUtil;
import com.liferay.portal.kernel.test.util.UserTestUtil;
import com.liferay.portal.test.rule.Inject;
import com.liferay.portal.test.rule.LiferayIntegrationTestRule;

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
	public static AggregateTestRule aggregateTestRule =
		new LiferayIntegrationTestRule();

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
		).and(
			"The product subscription entry is no more active"
		);

		CommerceSubscriptionEntryTestUtil.setUpCommerceSubscriptionEntry(
			_group.getGroupId(), _user.getUserId(), 1);

		int cpSubscriptionEntriesCount =
			_commerceSubscriptionEntryLocalService.
				getCPSubscriptionEntriesCount(
					_group.getGroupId(), _user.getUserId());

		Assert.assertEquals(1, cpSubscriptionEntriesCount);
	}

	@Rule
	public FrutillaRule frutillaRule = new FrutillaRule();

	@Inject
	private CommerceSubscriptionEntryLocalService
		_commerceSubscriptionEntryLocalService;

	@DeleteAfterTestRun
	private Group _group;

	@DeleteAfterTestRun
	private User _user;

}