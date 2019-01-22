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

package com.liferay.commerce.service.persistence.test;

import com.liferay.arquillian.extension.junit.bridge.junit.Arquillian;

import com.liferay.commerce.exception.NoSuchSubscriptionCycleEntryException;
import com.liferay.commerce.model.CommerceSubscriptionCycleEntry;
import com.liferay.commerce.service.CommerceSubscriptionCycleEntryLocalServiceUtil;
import com.liferay.commerce.service.persistence.CommerceSubscriptionCycleEntryPersistence;
import com.liferay.commerce.service.persistence.CommerceSubscriptionCycleEntryUtil;

import com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.dao.orm.DynamicQueryFactoryUtil;
import com.liferay.portal.kernel.dao.orm.ProjectionFactoryUtil;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.dao.orm.RestrictionsFactoryUtil;
import com.liferay.portal.kernel.test.ReflectionTestUtil;
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
import java.util.Objects;
import java.util.Set;

/**
 * @generated
 */
@RunWith(Arquillian.class)
public class CommerceSubscriptionCycleEntryPersistenceTest {
	@ClassRule
	@Rule
	public static final AggregateTestRule aggregateTestRule = new AggregateTestRule(new LiferayIntegrationTestRule(),
			PersistenceTestRule.INSTANCE,
			new TransactionalTestRule(Propagation.REQUIRED,
				"com.liferay.commerce.service"));

	@Before
	public void setUp() {
		_persistence = CommerceSubscriptionCycleEntryUtil.getPersistence();

		Class<?> clazz = _persistence.getClass();

		_dynamicQueryClassLoader = clazz.getClassLoader();
	}

	@After
	public void tearDown() throws Exception {
		Iterator<CommerceSubscriptionCycleEntry> iterator = _commerceSubscriptionCycleEntries.iterator();

		while (iterator.hasNext()) {
			_persistence.remove(iterator.next());

			iterator.remove();
		}
	}

	@Test
	public void testCreate() throws Exception {
		long pk = RandomTestUtil.nextLong();

		CommerceSubscriptionCycleEntry commerceSubscriptionCycleEntry = _persistence.create(pk);

		Assert.assertNotNull(commerceSubscriptionCycleEntry);

		Assert.assertEquals(commerceSubscriptionCycleEntry.getPrimaryKey(), pk);
	}

	@Test
	public void testRemove() throws Exception {
		CommerceSubscriptionCycleEntry newCommerceSubscriptionCycleEntry = addCommerceSubscriptionCycleEntry();

		_persistence.remove(newCommerceSubscriptionCycleEntry);

		CommerceSubscriptionCycleEntry existingCommerceSubscriptionCycleEntry = _persistence.fetchByPrimaryKey(newCommerceSubscriptionCycleEntry.getPrimaryKey());

		Assert.assertNull(existingCommerceSubscriptionCycleEntry);
	}

	@Test
	public void testUpdateNew() throws Exception {
		addCommerceSubscriptionCycleEntry();
	}

	@Test
	public void testUpdateExisting() throws Exception {
		long pk = RandomTestUtil.nextLong();

		CommerceSubscriptionCycleEntry newCommerceSubscriptionCycleEntry = _persistence.create(pk);

		newCommerceSubscriptionCycleEntry.setUuid(RandomTestUtil.randomString());

		newCommerceSubscriptionCycleEntry.setGroupId(RandomTestUtil.nextLong());

		newCommerceSubscriptionCycleEntry.setCompanyId(RandomTestUtil.nextLong());

		newCommerceSubscriptionCycleEntry.setUserId(RandomTestUtil.nextLong());

		newCommerceSubscriptionCycleEntry.setUserName(RandomTestUtil.randomString());

		newCommerceSubscriptionCycleEntry.setCreateDate(RandomTestUtil.nextDate());

		newCommerceSubscriptionCycleEntry.setModifiedDate(RandomTestUtil.nextDate());

		newCommerceSubscriptionCycleEntry.setCommerceOrderItemId(RandomTestUtil.nextLong());

		newCommerceSubscriptionCycleEntry.setCommerceSubscriptionEntryId(RandomTestUtil.nextLong());

		newCommerceSubscriptionCycleEntry.setRenew(RandomTestUtil.randomBoolean());

		_commerceSubscriptionCycleEntries.add(_persistence.update(
				newCommerceSubscriptionCycleEntry));

		CommerceSubscriptionCycleEntry existingCommerceSubscriptionCycleEntry = _persistence.findByPrimaryKey(newCommerceSubscriptionCycleEntry.getPrimaryKey());

		Assert.assertEquals(existingCommerceSubscriptionCycleEntry.getUuid(),
			newCommerceSubscriptionCycleEntry.getUuid());
		Assert.assertEquals(existingCommerceSubscriptionCycleEntry.getCommerceSubscriptionCycleEntryId(),
			newCommerceSubscriptionCycleEntry.getCommerceSubscriptionCycleEntryId());
		Assert.assertEquals(existingCommerceSubscriptionCycleEntry.getGroupId(),
			newCommerceSubscriptionCycleEntry.getGroupId());
		Assert.assertEquals(existingCommerceSubscriptionCycleEntry.getCompanyId(),
			newCommerceSubscriptionCycleEntry.getCompanyId());
		Assert.assertEquals(existingCommerceSubscriptionCycleEntry.getUserId(),
			newCommerceSubscriptionCycleEntry.getUserId());
		Assert.assertEquals(existingCommerceSubscriptionCycleEntry.getUserName(),
			newCommerceSubscriptionCycleEntry.getUserName());
		Assert.assertEquals(Time.getShortTimestamp(
				existingCommerceSubscriptionCycleEntry.getCreateDate()),
			Time.getShortTimestamp(
				newCommerceSubscriptionCycleEntry.getCreateDate()));
		Assert.assertEquals(Time.getShortTimestamp(
				existingCommerceSubscriptionCycleEntry.getModifiedDate()),
			Time.getShortTimestamp(
				newCommerceSubscriptionCycleEntry.getModifiedDate()));
		Assert.assertEquals(existingCommerceSubscriptionCycleEntry.getCommerceOrderItemId(),
			newCommerceSubscriptionCycleEntry.getCommerceOrderItemId());
		Assert.assertEquals(existingCommerceSubscriptionCycleEntry.getCommerceSubscriptionEntryId(),
			newCommerceSubscriptionCycleEntry.getCommerceSubscriptionEntryId());
		Assert.assertEquals(existingCommerceSubscriptionCycleEntry.isRenew(),
			newCommerceSubscriptionCycleEntry.isRenew());
	}

	@Test
	public void testCountByUuid() throws Exception {
		_persistence.countByUuid("");

		_persistence.countByUuid("null");

		_persistence.countByUuid((String)null);
	}

	@Test
	public void testCountByUUID_G() throws Exception {
		_persistence.countByUUID_G("", RandomTestUtil.nextLong());

		_persistence.countByUUID_G("null", 0L);

		_persistence.countByUUID_G((String)null, 0L);
	}

	@Test
	public void testCountByUuid_C() throws Exception {
		_persistence.countByUuid_C("", RandomTestUtil.nextLong());

		_persistence.countByUuid_C("null", 0L);

		_persistence.countByUuid_C((String)null, 0L);
	}

	@Test
	public void testCountByGroupId() throws Exception {
		_persistence.countByGroupId(RandomTestUtil.nextLong());

		_persistence.countByGroupId(0L);
	}

	@Test
	public void testCountByCommerceOrderItemId() throws Exception {
		_persistence.countByCommerceOrderItemId(RandomTestUtil.nextLong());

		_persistence.countByCommerceOrderItemId(0L);
	}

	@Test
	public void testCountByCommerceSubscriptionEntryId()
		throws Exception {
		_persistence.countByCommerceSubscriptionEntryId(RandomTestUtil.nextLong());

		_persistence.countByCommerceSubscriptionEntryId(0L);
	}

	@Test
	public void testFindByPrimaryKeyExisting() throws Exception {
		CommerceSubscriptionCycleEntry newCommerceSubscriptionCycleEntry = addCommerceSubscriptionCycleEntry();

		CommerceSubscriptionCycleEntry existingCommerceSubscriptionCycleEntry = _persistence.findByPrimaryKey(newCommerceSubscriptionCycleEntry.getPrimaryKey());

		Assert.assertEquals(existingCommerceSubscriptionCycleEntry,
			newCommerceSubscriptionCycleEntry);
	}

	@Test(expected = NoSuchSubscriptionCycleEntryException.class)
	public void testFindByPrimaryKeyMissing() throws Exception {
		long pk = RandomTestUtil.nextLong();

		_persistence.findByPrimaryKey(pk);
	}

	@Test
	public void testFindAll() throws Exception {
		_persistence.findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS,
			getOrderByComparator());
	}

	protected OrderByComparator<CommerceSubscriptionCycleEntry> getOrderByComparator() {
		return OrderByComparatorFactoryUtil.create("CSubscriptionCycleEntry",
			"uuid", true, "commerceSubscriptionCycleEntryId", true, "groupId",
			true, "companyId", true, "userId", true, "userName", true,
			"createDate", true, "modifiedDate", true, "commerceOrderItemId",
			true, "commerceSubscriptionEntryId", true, "renew", true);
	}

	@Test
	public void testFetchByPrimaryKeyExisting() throws Exception {
		CommerceSubscriptionCycleEntry newCommerceSubscriptionCycleEntry = addCommerceSubscriptionCycleEntry();

		CommerceSubscriptionCycleEntry existingCommerceSubscriptionCycleEntry = _persistence.fetchByPrimaryKey(newCommerceSubscriptionCycleEntry.getPrimaryKey());

		Assert.assertEquals(existingCommerceSubscriptionCycleEntry,
			newCommerceSubscriptionCycleEntry);
	}

	@Test
	public void testFetchByPrimaryKeyMissing() throws Exception {
		long pk = RandomTestUtil.nextLong();

		CommerceSubscriptionCycleEntry missingCommerceSubscriptionCycleEntry = _persistence.fetchByPrimaryKey(pk);

		Assert.assertNull(missingCommerceSubscriptionCycleEntry);
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereAllPrimaryKeysExist()
		throws Exception {
		CommerceSubscriptionCycleEntry newCommerceSubscriptionCycleEntry1 = addCommerceSubscriptionCycleEntry();
		CommerceSubscriptionCycleEntry newCommerceSubscriptionCycleEntry2 = addCommerceSubscriptionCycleEntry();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newCommerceSubscriptionCycleEntry1.getPrimaryKey());
		primaryKeys.add(newCommerceSubscriptionCycleEntry2.getPrimaryKey());

		Map<Serializable, CommerceSubscriptionCycleEntry> commerceSubscriptionCycleEntries =
			_persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(2, commerceSubscriptionCycleEntries.size());
		Assert.assertEquals(newCommerceSubscriptionCycleEntry1,
			commerceSubscriptionCycleEntries.get(
				newCommerceSubscriptionCycleEntry1.getPrimaryKey()));
		Assert.assertEquals(newCommerceSubscriptionCycleEntry2,
			commerceSubscriptionCycleEntries.get(
				newCommerceSubscriptionCycleEntry2.getPrimaryKey()));
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereNoPrimaryKeysExist()
		throws Exception {
		long pk1 = RandomTestUtil.nextLong();

		long pk2 = RandomTestUtil.nextLong();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(pk1);
		primaryKeys.add(pk2);

		Map<Serializable, CommerceSubscriptionCycleEntry> commerceSubscriptionCycleEntries =
			_persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertTrue(commerceSubscriptionCycleEntries.isEmpty());
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereSomePrimaryKeysExist()
		throws Exception {
		CommerceSubscriptionCycleEntry newCommerceSubscriptionCycleEntry = addCommerceSubscriptionCycleEntry();

		long pk = RandomTestUtil.nextLong();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newCommerceSubscriptionCycleEntry.getPrimaryKey());
		primaryKeys.add(pk);

		Map<Serializable, CommerceSubscriptionCycleEntry> commerceSubscriptionCycleEntries =
			_persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(1, commerceSubscriptionCycleEntries.size());
		Assert.assertEquals(newCommerceSubscriptionCycleEntry,
			commerceSubscriptionCycleEntries.get(
				newCommerceSubscriptionCycleEntry.getPrimaryKey()));
	}

	@Test
	public void testFetchByPrimaryKeysWithNoPrimaryKeys()
		throws Exception {
		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		Map<Serializable, CommerceSubscriptionCycleEntry> commerceSubscriptionCycleEntries =
			_persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertTrue(commerceSubscriptionCycleEntries.isEmpty());
	}

	@Test
	public void testFetchByPrimaryKeysWithOnePrimaryKey()
		throws Exception {
		CommerceSubscriptionCycleEntry newCommerceSubscriptionCycleEntry = addCommerceSubscriptionCycleEntry();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newCommerceSubscriptionCycleEntry.getPrimaryKey());

		Map<Serializable, CommerceSubscriptionCycleEntry> commerceSubscriptionCycleEntries =
			_persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(1, commerceSubscriptionCycleEntries.size());
		Assert.assertEquals(newCommerceSubscriptionCycleEntry,
			commerceSubscriptionCycleEntries.get(
				newCommerceSubscriptionCycleEntry.getPrimaryKey()));
	}

	@Test
	public void testActionableDynamicQuery() throws Exception {
		final IntegerWrapper count = new IntegerWrapper();

		ActionableDynamicQuery actionableDynamicQuery = CommerceSubscriptionCycleEntryLocalServiceUtil.getActionableDynamicQuery();

		actionableDynamicQuery.setPerformActionMethod(new ActionableDynamicQuery.PerformActionMethod<CommerceSubscriptionCycleEntry>() {
				@Override
				public void performAction(
					CommerceSubscriptionCycleEntry commerceSubscriptionCycleEntry) {
					Assert.assertNotNull(commerceSubscriptionCycleEntry);

					count.increment();
				}
			});

		actionableDynamicQuery.performActions();

		Assert.assertEquals(count.getValue(), _persistence.countAll());
	}

	@Test
	public void testDynamicQueryByPrimaryKeyExisting()
		throws Exception {
		CommerceSubscriptionCycleEntry newCommerceSubscriptionCycleEntry = addCommerceSubscriptionCycleEntry();

		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(CommerceSubscriptionCycleEntry.class,
				_dynamicQueryClassLoader);

		dynamicQuery.add(RestrictionsFactoryUtil.eq(
				"commerceSubscriptionCycleEntryId",
				newCommerceSubscriptionCycleEntry.getCommerceSubscriptionCycleEntryId()));

		List<CommerceSubscriptionCycleEntry> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(1, result.size());

		CommerceSubscriptionCycleEntry existingCommerceSubscriptionCycleEntry = result.get(0);

		Assert.assertEquals(existingCommerceSubscriptionCycleEntry,
			newCommerceSubscriptionCycleEntry);
	}

	@Test
	public void testDynamicQueryByPrimaryKeyMissing() throws Exception {
		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(CommerceSubscriptionCycleEntry.class,
				_dynamicQueryClassLoader);

		dynamicQuery.add(RestrictionsFactoryUtil.eq(
				"commerceSubscriptionCycleEntryId", RandomTestUtil.nextLong()));

		List<CommerceSubscriptionCycleEntry> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(0, result.size());
	}

	@Test
	public void testDynamicQueryByProjectionExisting()
		throws Exception {
		CommerceSubscriptionCycleEntry newCommerceSubscriptionCycleEntry = addCommerceSubscriptionCycleEntry();

		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(CommerceSubscriptionCycleEntry.class,
				_dynamicQueryClassLoader);

		dynamicQuery.setProjection(ProjectionFactoryUtil.property(
				"commerceSubscriptionCycleEntryId"));

		Object newCommerceSubscriptionCycleEntryId = newCommerceSubscriptionCycleEntry.getCommerceSubscriptionCycleEntryId();

		dynamicQuery.add(RestrictionsFactoryUtil.in(
				"commerceSubscriptionCycleEntryId",
				new Object[] { newCommerceSubscriptionCycleEntryId }));

		List<Object> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(1, result.size());

		Object existingCommerceSubscriptionCycleEntryId = result.get(0);

		Assert.assertEquals(existingCommerceSubscriptionCycleEntryId,
			newCommerceSubscriptionCycleEntryId);
	}

	@Test
	public void testDynamicQueryByProjectionMissing() throws Exception {
		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(CommerceSubscriptionCycleEntry.class,
				_dynamicQueryClassLoader);

		dynamicQuery.setProjection(ProjectionFactoryUtil.property(
				"commerceSubscriptionCycleEntryId"));

		dynamicQuery.add(RestrictionsFactoryUtil.in(
				"commerceSubscriptionCycleEntryId",
				new Object[] { RandomTestUtil.nextLong() }));

		List<Object> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(0, result.size());
	}

	@Test
	public void testResetOriginalValues() throws Exception {
		CommerceSubscriptionCycleEntry newCommerceSubscriptionCycleEntry = addCommerceSubscriptionCycleEntry();

		_persistence.clearCache();

		CommerceSubscriptionCycleEntry existingCommerceSubscriptionCycleEntry = _persistence.findByPrimaryKey(newCommerceSubscriptionCycleEntry.getPrimaryKey());

		Assert.assertTrue(Objects.equals(
				existingCommerceSubscriptionCycleEntry.getUuid(),
				ReflectionTestUtil.invoke(
					existingCommerceSubscriptionCycleEntry, "getOriginalUuid",
					new Class<?>[0])));
		Assert.assertEquals(Long.valueOf(
				existingCommerceSubscriptionCycleEntry.getGroupId()),
			ReflectionTestUtil.<Long>invoke(
				existingCommerceSubscriptionCycleEntry, "getOriginalGroupId",
				new Class<?>[0]));

		Assert.assertEquals(Long.valueOf(
				existingCommerceSubscriptionCycleEntry.getCommerceOrderItemId()),
			ReflectionTestUtil.<Long>invoke(
				existingCommerceSubscriptionCycleEntry,
				"getOriginalCommerceOrderItemId", new Class<?>[0]));
	}

	protected CommerceSubscriptionCycleEntry addCommerceSubscriptionCycleEntry()
		throws Exception {
		long pk = RandomTestUtil.nextLong();

		CommerceSubscriptionCycleEntry commerceSubscriptionCycleEntry = _persistence.create(pk);

		commerceSubscriptionCycleEntry.setUuid(RandomTestUtil.randomString());

		commerceSubscriptionCycleEntry.setGroupId(RandomTestUtil.nextLong());

		commerceSubscriptionCycleEntry.setCompanyId(RandomTestUtil.nextLong());

		commerceSubscriptionCycleEntry.setUserId(RandomTestUtil.nextLong());

		commerceSubscriptionCycleEntry.setUserName(RandomTestUtil.randomString());

		commerceSubscriptionCycleEntry.setCreateDate(RandomTestUtil.nextDate());

		commerceSubscriptionCycleEntry.setModifiedDate(RandomTestUtil.nextDate());

		commerceSubscriptionCycleEntry.setCommerceOrderItemId(RandomTestUtil.nextLong());

		commerceSubscriptionCycleEntry.setCommerceSubscriptionEntryId(RandomTestUtil.nextLong());

		commerceSubscriptionCycleEntry.setRenew(RandomTestUtil.randomBoolean());

		_commerceSubscriptionCycleEntries.add(_persistence.update(
				commerceSubscriptionCycleEntry));

		return commerceSubscriptionCycleEntry;
	}

	private List<CommerceSubscriptionCycleEntry> _commerceSubscriptionCycleEntries =
		new ArrayList<CommerceSubscriptionCycleEntry>();
	private CommerceSubscriptionCycleEntryPersistence _persistence;
	private ClassLoader _dynamicQueryClassLoader;
}