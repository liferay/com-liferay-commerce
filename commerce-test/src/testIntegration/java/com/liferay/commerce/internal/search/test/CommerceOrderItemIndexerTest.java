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

package com.liferay.commerce.internal.search.test;

import com.liferay.arquillian.extension.junit.bridge.junit.Arquillian;
import com.liferay.commerce.currency.model.CommerceCurrency;
import com.liferay.commerce.currency.test.util.CommerceCurrencyTestUtil;
import com.liferay.commerce.internal.search.CommerceOrderItemIndexer;
import com.liferay.commerce.model.CommerceOrder;
import com.liferay.commerce.model.CommerceOrderItem;
import com.liferay.commerce.product.model.CPInstance;
import com.liferay.commerce.product.service.CPInstanceLocalService;
import com.liferay.commerce.product.test.util.CPTestUtil;
import com.liferay.commerce.service.CommerceOrderItemLocalService;
import com.liferay.commerce.test.util.CommerceTestUtil;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.model.Group;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.search.Document;
import com.liferay.portal.kernel.search.Field;
import com.liferay.portal.kernel.search.Hits;
import com.liferay.portal.kernel.search.Indexer;
import com.liferay.portal.kernel.search.IndexerRegistry;
import com.liferay.portal.kernel.search.SearchContext;
import com.liferay.portal.kernel.search.SortFactoryUtil;
import com.liferay.portal.kernel.test.rule.AggregateTestRule;
import com.liferay.portal.kernel.test.rule.DeleteAfterTestRun;
import com.liferay.portal.kernel.test.rule.Sync;
import com.liferay.portal.kernel.test.rule.SynchronousDestinationTestRule;
import com.liferay.portal.kernel.test.util.GroupTestUtil;
import com.liferay.portal.kernel.test.util.UserTestUtil;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.test.rule.Inject;
import com.liferay.portal.test.rule.LiferayIntegrationTestRule;
import com.liferay.portal.test.rule.PermissionCheckerTestRule;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.frutilla.FrutillaRule;

import org.junit.Assert;
import org.junit.Before;
import org.junit.ClassRule;
import org.junit.Ignore;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 * @author Andrea Di Giorgi
 */
@Ignore
@RunWith(Arquillian.class)
@Sync
public class CommerceOrderItemIndexerTest {

	@ClassRule
	@Rule
	public static final AggregateTestRule aggregateTestRule =
		new AggregateTestRule(
			new LiferayIntegrationTestRule(),
			PermissionCheckerTestRule.INSTANCE,
			SynchronousDestinationTestRule.INSTANCE);

	@Before
	public void setUp() throws Exception {
		_group = GroupTestUtil.addGroup();
		_indexer = _indexerRegistry.getIndexer(CommerceOrderItem.class);
	}

	@Test
	public void testEmptyQuery() throws Exception {
		_addCommerceOrderItems(1);
		_addCommerceOrderItems(2);

		CommerceOrderItem[] commerceOrderItems = _addCommerceOrderItems(3);

		_assertSearch(StringPool.BLANK, commerceOrderItems);
	}

	@Rule
	public FrutillaRule frutillaRule = new FrutillaRule();

	private CommerceOrderItem[] _addCommerceOrderItems(int count)
		throws Exception {

		User user = UserTestUtil.addUser();

		CommerceOrderItem[] commerceOrderItems = new CommerceOrderItem[count];

		CommerceCurrency commerceCurrency =
			CommerceCurrencyTestUtil.addCommerceCurrency();

		CommerceTestUtil.addCommerceChannel(
			_group.getGroupId(), commerceCurrency.getCode());

		CommerceOrder commerceOrder = CommerceTestUtil.addB2CCommerceOrder(
			_group.getGroupId(), user.getUserId(),
			commerceCurrency.getCommerceCurrencyId());

		for (int i = 0; i < count; i++) {
			CPInstance cpInstance = CPTestUtil.addCPInstance(
				commerceOrder.getGroupId());

			cpInstance.setPurchasable(true);

			_cpInstanceLocalService.updateCPInstance(cpInstance);

			CommerceTestUtil.addBackOrderCPDefinitionInventory(
				cpInstance.getCPDefinition());

			commerceOrderItems[i] = CommerceTestUtil.addCommerceOrderItem(
				commerceOrder.getCommerceOrderId(),
				cpInstance.getCPInstanceId(), 1);
		}

		return commerceOrderItems;
	}

	private void _assertSearch(
			Hits hits, CommerceOrderItem... expectedCommerceOrderItems)
		throws Exception {

		List<CommerceOrderItem> actualCommerceOrderItems =
			_getCommerceOrderItems(hits);

		long[] actualCommerceOrderItemIds = _getCommerceOrderItemIds(
			actualCommerceOrderItems);

		long[] expectedCommerceOrderItemIds = _getCommerceOrderItemIds(
			Arrays.asList(expectedCommerceOrderItems));

		Assert.assertArrayEquals(
			expectedCommerceOrderItemIds, actualCommerceOrderItemIds);
	}

	private void _assertSearch(
			String keywords, CommerceOrderItem... expectedCommerceOrderItems)
		throws Exception {

		CommerceOrderItem commerceOrderItem = expectedCommerceOrderItems[0];

		SearchContext searchContext = _getSearchContext(
			commerceOrderItem.getCommerceOrderId());

		searchContext.setKeywords(keywords);

		Hits hits = _indexer.search(searchContext);

		_assertSearch(hits, expectedCommerceOrderItems);
	}

	private CommerceOrderItem _getCommerceOrderItem(Document document)
		throws Exception {

		long commerceOrderItemId = GetterUtil.getLong(
			document.get(Field.ENTRY_CLASS_PK));

		return _commerceOrderItemLocalService.getCommerceOrderItem(
			commerceOrderItemId);
	}

	private long[] _getCommerceOrderItemIds(
		List<CommerceOrderItem> commerceOrderItems) {

		long[] commerceOrderItemIds = new long[commerceOrderItems.size()];

		for (int i = 0; i < commerceOrderItems.size(); i++) {
			CommerceOrderItem commerceOrderItem = commerceOrderItems.get(i);

			commerceOrderItemIds[i] =
				commerceOrderItem.getCommerceOrderItemId();
		}

		Arrays.sort(commerceOrderItemIds);

		return commerceOrderItemIds;
	}

	private List<CommerceOrderItem> _getCommerceOrderItems(Hits hits)
		throws Exception {

		Document[] documents = hits.getDocs();

		List<CommerceOrderItem> commerceOrderItems = new ArrayList<>(
			documents.length);

		for (Document document : documents) {
			commerceOrderItems.add(_getCommerceOrderItem(document));
		}

		return commerceOrderItems;
	}

	private SearchContext _getSearchContext(long commerceOrderId) {
		SearchContext searchContext = new SearchContext();

		searchContext.setAttribute(
			CommerceOrderItemIndexer.FIELD_COMMERCE_ORDER_ID, commerceOrderId);
		searchContext.setCompanyId(_group.getCompanyId());
		searchContext.setSorts(SortFactoryUtil.getDefaultSorts());

		return searchContext;
	}

	@Inject
	private static CommerceOrderItemLocalService _commerceOrderItemLocalService;

	@Inject
	private static IndexerRegistry _indexerRegistry;

	@Inject
	private CPInstanceLocalService _cpInstanceLocalService;

	@DeleteAfterTestRun
	private Group _group;

	private Indexer<CommerceOrderItem> _indexer;

}