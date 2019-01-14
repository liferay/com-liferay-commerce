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

package com.liferay.commerce.discount.service.persistence.test;

import com.liferay.arquillian.extension.junit.bridge.junit.Arquillian;

import com.liferay.commerce.discount.exception.NoSuchDiscountUsageEntryException;
import com.liferay.commerce.discount.model.CommerceDiscountUsageEntry;
import com.liferay.commerce.discount.service.CommerceDiscountUsageEntryLocalServiceUtil;
import com.liferay.commerce.discount.service.persistence.CommerceDiscountUsageEntryPersistence;
import com.liferay.commerce.discount.service.persistence.CommerceDiscountUsageEntryUtil;

import com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.dao.orm.DynamicQueryFactoryUtil;
import com.liferay.portal.kernel.dao.orm.ProjectionFactoryUtil;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.dao.orm.RestrictionsFactoryUtil;
import com.liferay.portal.kernel.test.rule.AggregateTestRule;
import com.liferay.portal.kernel.test.util.RandomTestUtil;
import com.liferay.portal.kernel.transaction.Propagation;
import com.liferay.portal.kernel.util.IntegerWrapper;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.OrderByComparatorFactoryUtil;
import com.liferay.portal.kernel.util.Time;
import com.liferay.portal.test.rule.LiferayIntegrationTestRule;
import com.liferay.portal.test.rule.PersistenceTestRule;
import com.liferay.portal.test.rule.TransactionalTestRule;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.ClassRule;
import org.junit.Rule;
import org.junit.Test;

import org.junit.runner.RunWith;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @generated
 */
@RunWith(Arquillian.class)
public class CommerceDiscountUsageEntryPersistenceTest {
	@ClassRule
	@Rule
	public static final AggregateTestRule aggregateTestRule = new AggregateTestRule(new LiferayIntegrationTestRule(),
			PersistenceTestRule.INSTANCE,
			new TransactionalTestRule(Propagation.REQUIRED,
				"com.liferay.commerce.discount.service"));

	@Before
	public void setUp() {
		_persistence = CommerceDiscountUsageEntryUtil.getPersistence();

		Class<?> clazz = _persistence.getClass();

		_dynamicQueryClassLoader = clazz.getClassLoader();
	}

	@After
	public void tearDown() throws Exception {
		Iterator<CommerceDiscountUsageEntry> iterator = _commerceDiscountUsageEntries.iterator();

		while (iterator.hasNext()) {
			_persistence.remove(iterator.next());

			iterator.remove();
		}
	}

	@Test
	public void testCreate() throws Exception {
		long pk = RandomTestUtil.nextLong();

		CommerceDiscountUsageEntry commerceDiscountUsageEntry = _persistence.create(pk);

		Assert.assertNotNull(commerceDiscountUsageEntry);

		Assert.assertEquals(commerceDiscountUsageEntry.getPrimaryKey(), pk);
	}

	@Test
	public void testRemove() throws Exception {
		CommerceDiscountUsageEntry newCommerceDiscountUsageEntry = addCommerceDiscountUsageEntry();

		_persistence.remove(newCommerceDiscountUsageEntry);

		CommerceDiscountUsageEntry existingCommerceDiscountUsageEntry = _persistence.fetchByPrimaryKey(newCommerceDiscountUsageEntry.getPrimaryKey());

		Assert.assertNull(existingCommerceDiscountUsageEntry);
	}

	@Test
	public void testUpdateNew() throws Exception {
		addCommerceDiscountUsageEntry();
	}

	@Test
	public void testUpdateExisting() throws Exception {
		long pk = RandomTestUtil.nextLong();

		CommerceDiscountUsageEntry newCommerceDiscountUsageEntry = _persistence.create(pk);

		newCommerceDiscountUsageEntry.setGroupId(RandomTestUtil.nextLong());

		newCommerceDiscountUsageEntry.setCompanyId(RandomTestUtil.nextLong());

		newCommerceDiscountUsageEntry.setUserId(RandomTestUtil.nextLong());

		newCommerceDiscountUsageEntry.setUserName(RandomTestUtil.randomString());

		newCommerceDiscountUsageEntry.setCreateDate(RandomTestUtil.nextDate());

		newCommerceDiscountUsageEntry.setModifiedDate(RandomTestUtil.nextDate());

		newCommerceDiscountUsageEntry.setCommerceAccountId(RandomTestUtil.nextLong());

		newCommerceDiscountUsageEntry.setCommerceOrderId(RandomTestUtil.nextLong());

		newCommerceDiscountUsageEntry.setCommerceDiscountId(RandomTestUtil.nextLong());

		_commerceDiscountUsageEntries.add(_persistence.update(
				newCommerceDiscountUsageEntry));

		CommerceDiscountUsageEntry existingCommerceDiscountUsageEntry = _persistence.findByPrimaryKey(newCommerceDiscountUsageEntry.getPrimaryKey());

		Assert.assertEquals(existingCommerceDiscountUsageEntry.getCommerceDiscountUsageEntryId(),
			newCommerceDiscountUsageEntry.getCommerceDiscountUsageEntryId());
		Assert.assertEquals(existingCommerceDiscountUsageEntry.getGroupId(),
			newCommerceDiscountUsageEntry.getGroupId());
		Assert.assertEquals(existingCommerceDiscountUsageEntry.getCompanyId(),
			newCommerceDiscountUsageEntry.getCompanyId());
		Assert.assertEquals(existingCommerceDiscountUsageEntry.getUserId(),
			newCommerceDiscountUsageEntry.getUserId());
		Assert.assertEquals(existingCommerceDiscountUsageEntry.getUserName(),
			newCommerceDiscountUsageEntry.getUserName());
		Assert.assertEquals(Time.getShortTimestamp(
				existingCommerceDiscountUsageEntry.getCreateDate()),
			Time.getShortTimestamp(
				newCommerceDiscountUsageEntry.getCreateDate()));
		Assert.assertEquals(Time.getShortTimestamp(
				existingCommerceDiscountUsageEntry.getModifiedDate()),
			Time.getShortTimestamp(
				newCommerceDiscountUsageEntry.getModifiedDate()));
		Assert.assertEquals(existingCommerceDiscountUsageEntry.getCommerceAccountId(),
			newCommerceDiscountUsageEntry.getCommerceAccountId());
		Assert.assertEquals(existingCommerceDiscountUsageEntry.getCommerceOrderId(),
			newCommerceDiscountUsageEntry.getCommerceOrderId());
		Assert.assertEquals(existingCommerceDiscountUsageEntry.getCommerceDiscountId(),
			newCommerceDiscountUsageEntry.getCommerceDiscountId());
	}

	@Test
	public void testCountByGroupId() throws Exception {
		_persistence.countByGroupId(RandomTestUtil.nextLong());

		_persistence.countByGroupId(0L);
	}

	@Test
	public void testFindByPrimaryKeyExisting() throws Exception {
		CommerceDiscountUsageEntry newCommerceDiscountUsageEntry = addCommerceDiscountUsageEntry();

		CommerceDiscountUsageEntry existingCommerceDiscountUsageEntry = _persistence.findByPrimaryKey(newCommerceDiscountUsageEntry.getPrimaryKey());

		Assert.assertEquals(existingCommerceDiscountUsageEntry,
			newCommerceDiscountUsageEntry);
	}

	@Test(expected = NoSuchDiscountUsageEntryException.class)
	public void testFindByPrimaryKeyMissing() throws Exception {
		long pk = RandomTestUtil.nextLong();

		_persistence.findByPrimaryKey(pk);
	}

	@Test
	public void testFindAll() throws Exception {
		_persistence.findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS,
			getOrderByComparator());
	}

	protected OrderByComparator<CommerceDiscountUsageEntry> getOrderByComparator() {
		return OrderByComparatorFactoryUtil.create("CommerceDiscountUsageEntry",
			"commerceDiscountUsageEntryId", true, "groupId", true, "companyId",
			true, "userId", true, "userName", true, "createDate", true,
			"modifiedDate", true, "commerceAccountId", true, "commerceOrderId",
			true, "commerceDiscountId", true);
	}

	@Test
	public void testFetchByPrimaryKeyExisting() throws Exception {
		CommerceDiscountUsageEntry newCommerceDiscountUsageEntry = addCommerceDiscountUsageEntry();

		CommerceDiscountUsageEntry existingCommerceDiscountUsageEntry = _persistence.fetchByPrimaryKey(newCommerceDiscountUsageEntry.getPrimaryKey());

		Assert.assertEquals(existingCommerceDiscountUsageEntry,
			newCommerceDiscountUsageEntry);
	}

	@Test
	public void testFetchByPrimaryKeyMissing() throws Exception {
		long pk = RandomTestUtil.nextLong();

		CommerceDiscountUsageEntry missingCommerceDiscountUsageEntry = _persistence.fetchByPrimaryKey(pk);

		Assert.assertNull(missingCommerceDiscountUsageEntry);
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereAllPrimaryKeysExist()
		throws Exception {
		CommerceDiscountUsageEntry newCommerceDiscountUsageEntry1 = addCommerceDiscountUsageEntry();
		CommerceDiscountUsageEntry newCommerceDiscountUsageEntry2 = addCommerceDiscountUsageEntry();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newCommerceDiscountUsageEntry1.getPrimaryKey());
		primaryKeys.add(newCommerceDiscountUsageEntry2.getPrimaryKey());

		Map<Serializable, CommerceDiscountUsageEntry> commerceDiscountUsageEntries =
			_persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(2, commerceDiscountUsageEntries.size());
		Assert.assertEquals(newCommerceDiscountUsageEntry1,
			commerceDiscountUsageEntries.get(
				newCommerceDiscountUsageEntry1.getPrimaryKey()));
		Assert.assertEquals(newCommerceDiscountUsageEntry2,
			commerceDiscountUsageEntries.get(
				newCommerceDiscountUsageEntry2.getPrimaryKey()));
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereNoPrimaryKeysExist()
		throws Exception {
		long pk1 = RandomTestUtil.nextLong();

		long pk2 = RandomTestUtil.nextLong();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(pk1);
		primaryKeys.add(pk2);

		Map<Serializable, CommerceDiscountUsageEntry> commerceDiscountUsageEntries =
			_persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertTrue(commerceDiscountUsageEntries.isEmpty());
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereSomePrimaryKeysExist()
		throws Exception {
		CommerceDiscountUsageEntry newCommerceDiscountUsageEntry = addCommerceDiscountUsageEntry();

		long pk = RandomTestUtil.nextLong();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newCommerceDiscountUsageEntry.getPrimaryKey());
		primaryKeys.add(pk);

		Map<Serializable, CommerceDiscountUsageEntry> commerceDiscountUsageEntries =
			_persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(1, commerceDiscountUsageEntries.size());
		Assert.assertEquals(newCommerceDiscountUsageEntry,
			commerceDiscountUsageEntries.get(
				newCommerceDiscountUsageEntry.getPrimaryKey()));
	}

	@Test
	public void testFetchByPrimaryKeysWithNoPrimaryKeys()
		throws Exception {
		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		Map<Serializable, CommerceDiscountUsageEntry> commerceDiscountUsageEntries =
			_persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertTrue(commerceDiscountUsageEntries.isEmpty());
	}

	@Test
	public void testFetchByPrimaryKeysWithOnePrimaryKey()
		throws Exception {
		CommerceDiscountUsageEntry newCommerceDiscountUsageEntry = addCommerceDiscountUsageEntry();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newCommerceDiscountUsageEntry.getPrimaryKey());

		Map<Serializable, CommerceDiscountUsageEntry> commerceDiscountUsageEntries =
			_persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(1, commerceDiscountUsageEntries.size());
		Assert.assertEquals(newCommerceDiscountUsageEntry,
			commerceDiscountUsageEntries.get(
				newCommerceDiscountUsageEntry.getPrimaryKey()));
	}

	@Test
	public void testActionableDynamicQuery() throws Exception {
		final IntegerWrapper count = new IntegerWrapper();

		ActionableDynamicQuery actionableDynamicQuery = CommerceDiscountUsageEntryLocalServiceUtil.getActionableDynamicQuery();

		actionableDynamicQuery.setPerformActionMethod(new ActionableDynamicQuery.PerformActionMethod<CommerceDiscountUsageEntry>() {
				@Override
				public void performAction(
					CommerceDiscountUsageEntry commerceDiscountUsageEntry) {
					Assert.assertNotNull(commerceDiscountUsageEntry);

					count.increment();
				}
			});

		actionableDynamicQuery.performActions();

		Assert.assertEquals(count.getValue(), _persistence.countAll());
	}

	@Test
	public void testDynamicQueryByPrimaryKeyExisting()
		throws Exception {
		CommerceDiscountUsageEntry newCommerceDiscountUsageEntry = addCommerceDiscountUsageEntry();

		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(CommerceDiscountUsageEntry.class,
				_dynamicQueryClassLoader);

		dynamicQuery.add(RestrictionsFactoryUtil.eq(
				"commerceDiscountUsageEntryId",
				newCommerceDiscountUsageEntry.getCommerceDiscountUsageEntryId()));

		List<CommerceDiscountUsageEntry> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(1, result.size());

		CommerceDiscountUsageEntry existingCommerceDiscountUsageEntry = result.get(0);

		Assert.assertEquals(existingCommerceDiscountUsageEntry,
			newCommerceDiscountUsageEntry);
	}

	@Test
	public void testDynamicQueryByPrimaryKeyMissing() throws Exception {
		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(CommerceDiscountUsageEntry.class,
				_dynamicQueryClassLoader);

		dynamicQuery.add(RestrictionsFactoryUtil.eq(
				"commerceDiscountUsageEntryId", RandomTestUtil.nextLong()));

		List<CommerceDiscountUsageEntry> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(0, result.size());
	}

	@Test
	public void testDynamicQueryByProjectionExisting()
		throws Exception {
		CommerceDiscountUsageEntry newCommerceDiscountUsageEntry = addCommerceDiscountUsageEntry();

		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(CommerceDiscountUsageEntry.class,
				_dynamicQueryClassLoader);

		dynamicQuery.setProjection(ProjectionFactoryUtil.property(
				"commerceDiscountUsageEntryId"));

		Object newCommerceDiscountUsageEntryId = newCommerceDiscountUsageEntry.getCommerceDiscountUsageEntryId();

		dynamicQuery.add(RestrictionsFactoryUtil.in(
				"commerceDiscountUsageEntryId",
				new Object[] { newCommerceDiscountUsageEntryId }));

		List<Object> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(1, result.size());

		Object existingCommerceDiscountUsageEntryId = result.get(0);

		Assert.assertEquals(existingCommerceDiscountUsageEntryId,
			newCommerceDiscountUsageEntryId);
	}

	@Test
	public void testDynamicQueryByProjectionMissing() throws Exception {
		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(CommerceDiscountUsageEntry.class,
				_dynamicQueryClassLoader);

		dynamicQuery.setProjection(ProjectionFactoryUtil.property(
				"commerceDiscountUsageEntryId"));

		dynamicQuery.add(RestrictionsFactoryUtil.in(
				"commerceDiscountUsageEntryId",
				new Object[] { RandomTestUtil.nextLong() }));

		List<Object> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(0, result.size());
	}

	protected CommerceDiscountUsageEntry addCommerceDiscountUsageEntry()
		throws Exception {
		long pk = RandomTestUtil.nextLong();

		CommerceDiscountUsageEntry commerceDiscountUsageEntry = _persistence.create(pk);

		commerceDiscountUsageEntry.setGroupId(RandomTestUtil.nextLong());

		commerceDiscountUsageEntry.setCompanyId(RandomTestUtil.nextLong());

		commerceDiscountUsageEntry.setUserId(RandomTestUtil.nextLong());

		commerceDiscountUsageEntry.setUserName(RandomTestUtil.randomString());

		commerceDiscountUsageEntry.setCreateDate(RandomTestUtil.nextDate());

		commerceDiscountUsageEntry.setModifiedDate(RandomTestUtil.nextDate());

		commerceDiscountUsageEntry.setCommerceAccountId(RandomTestUtil.nextLong());

		commerceDiscountUsageEntry.setCommerceOrderId(RandomTestUtil.nextLong());

		commerceDiscountUsageEntry.setCommerceDiscountId(RandomTestUtil.nextLong());

		_commerceDiscountUsageEntries.add(_persistence.update(
				commerceDiscountUsageEntry));

		return commerceDiscountUsageEntry;
	}

	private List<CommerceDiscountUsageEntry> _commerceDiscountUsageEntries = new ArrayList<CommerceDiscountUsageEntry>();
	private CommerceDiscountUsageEntryPersistence _persistence;
	private ClassLoader _dynamicQueryClassLoader;
}