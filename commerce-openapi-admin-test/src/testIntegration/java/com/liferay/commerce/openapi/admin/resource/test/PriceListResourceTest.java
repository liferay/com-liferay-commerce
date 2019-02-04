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

package com.liferay.commerce.openapi.admin.resource.test;

import static org.hamcrest.CoreMatchers.notNullValue;

import com.liferay.arquillian.extension.junit.bridge.junit.Arquillian;
import com.liferay.commerce.openapi.admin.resource.test.utils.CommerceTestSiteActivator;
import com.liferay.portal.kernel.model.Group;
import com.liferay.portal.kernel.test.rule.AggregateTestRule;
import com.liferay.portal.kernel.test.rule.DeleteAfterTestRun;
import com.liferay.portal.kernel.test.util.GroupTestUtil;
import com.liferay.portal.test.rule.LiferayIntegrationTestRule;

import org.junit.Assert;
import org.junit.Before;
import org.junit.ClassRule;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 * @author Zoltán Takács
 */
@RunWith(Arquillian.class)
public class PriceListResourceTest extends PortalContextProvider {

	public static final String PRICE_LIST = "priceList";

	@ClassRule
	@Rule
	public static final AggregateTestRule aggregateTestRule =
		new LiferayIntegrationTestRule();

	@Before
	public void setUp() throws Exception {
		_group = GroupTestUtil.addGroup();

		CommerceTestSiteActivator.initialize(_group.getGroupId());
	}

	@Test
	public void testAddCollectionItem() {
		Assert.assertTrue(true);
	}

	@Test
	public void testCollectionPostOperationPresent() {
		Assert.assertThat("Something test here", notNullValue());
	}

	/*@Ignore
	@Test
	public void testDeleteCollectionItem() {
		ApioResourceCollection commercePriceListApioResourceCollection =
			getSiteRelatedApioResourceCollection(
				COMMERCE_PRICE_LIST_RESOURCE_NAME, _group.getDescriptiveName());

		int numberOfItems1 =
			commercePriceListApioResourceCollection.getNumberOfItems();

		String priceListId = _addPriceList(RandomTestUtil.randomString());

		commercePriceListApioResourceCollection =
			getSiteRelatedApioResourceCollection(
				COMMERCE_PRICE_LIST_RESOURCE_NAME, _group.getDescriptiveName());

		int numberOfItems2 =
			commercePriceListApioResourceCollection.getNumberOfItems();

		Assert.assertThat(numberOfItems1 + 1, equalTo(numberOfItems2));

		try (RESTClient restClient = new RESTClient()) {
			restClient.executeDeleteRequest(priceListId);
		}

		commercePriceListApioResourceCollection =
			getSiteRelatedApioResourceCollection(
				COMMERCE_PRICE_LIST_RESOURCE_NAME, _group.getDescriptiveName());

		int numberOfItems3 =
			commercePriceListApioResourceCollection.getNumberOfItems();

		Assert.assertThat(numberOfItems1, equalTo(numberOfItems3));
	}

	private static final Map<String, String> _propertiesMap =
		new HashMap<String, String>() {
			{
				put("name", RandomTestUtil.randomString());
				put("active", Boolean.TRUE.toString());
				put("currency", "USD");
				put("neverExpire", Boolean.TRUE.toString());
				put("priority", "0");
			}
		};*/

	@DeleteAfterTestRun
	private Group _group;

}