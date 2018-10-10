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

import com.liferay.commerce.exception.NoSuchSubscriptionEntryException;
import com.liferay.commerce.model.CommerceSubscriptionEntry;
import com.liferay.commerce.service.CommerceSubscriptionEntryLocalServiceUtil;
import com.liferay.commerce.service.persistence.CommerceSubscriptionEntryPersistence;
import com.liferay.commerce.service.persistence.CommerceSubscriptionEntryUtil;

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
public class CommerceSubscriptionEntryPersistenceTest {
	@ClassRule
	@Rule
	public static final AggregateTestRule aggregateTestRule = new AggregateTestRule(new LiferayIntegrationTestRule(),
			PersistenceTestRule.INSTANCE,
			new TransactionalTestRule(Propagation.REQUIRED,
				"com.liferay.commerce.service"));

	@Before
	public void setUp() {
		_persistence = CommerceSubscriptionEntryUtil.getPersistence();

		Class<?> clazz = _persistence.getClass();

		_dynamicQueryClassLoader = clazz.getClassLoader();
	}

	@After
	public void tearDown() throws Exception {
		Iterator<CommerceSubscriptionEntry> iterator = _commerceSubscriptionEntries.iterator();

		while (iterator.hasNext()) {
			_persistence.remove(iterator.next());

			iterator.remove();
		}
	}

	@Test
	public void testCreate() throws Exception {
		long pk = RandomTestUtil.nextLong();

		CommerceSubscriptionEntry commerceSubscriptionEntry = _persistence.create(pk);

		Assert.assertNotNull(commerceSubscriptionEntry);

		Assert.assertEquals(commerceSubscriptionEntry.getPrimaryKey(), pk);
	}

	@Test
	public void testRemove() throws Exception {
		CommerceSubscriptionEntry newCommerceSubscriptionEntry = addCommerceSubscriptionEntry();

		_persistence.remove(newCommerceSubscriptionEntry);

		CommerceSubscriptionEntry existingCommerceSubscriptionEntry = _persistence.fetchByPrimaryKey(newCommerceSubscriptionEntry.getPrimaryKey());

		Assert.assertNull(existingCommerceSubscriptionEntry);
	}

	@Test
	public void testUpdateNew() throws Exception {
		addCommerceSubscriptionEntry();
	}

	@Test
	public void testUpdateExisting() throws Exception {
		long pk = RandomTestUtil.nextLong();

		CommerceSubscriptionEntry newCommerceSubscriptionEntry = _persistence.create(pk);

		newCommerceSubscriptionEntry.setUuid(RandomTestUtil.randomString());

		newCommerceSubscriptionEntry.setGroupId(RandomTestUtil.nextLong());

		newCommerceSubscriptionEntry.setCompanyId(RandomTestUtil.nextLong());

		newCommerceSubscriptionEntry.setUserId(RandomTestUtil.nextLong());

		newCommerceSubscriptionEntry.setUserName(RandomTestUtil.randomString());

		newCommerceSubscriptionEntry.setCreateDate(RandomTestUtil.nextDate());

		newCommerceSubscriptionEntry.setModifiedDate(RandomTestUtil.nextDate());

		newCommerceSubscriptionEntry.setCPInstanceId(RandomTestUtil.nextLong());

		newCommerceSubscriptionEntry.setCommerceOrderItemId(RandomTestUtil.nextLong());

		newCommerceSubscriptionEntry.setSubscriptionCycleLength(RandomTestUtil.nextLong());

		newCommerceSubscriptionEntry.setSubscriptionCyclePeriod(RandomTestUtil.randomString());

		newCommerceSubscriptionEntry.setMaxSubscriptionCyclesNumber(RandomTestUtil.nextLong());

		newCommerceSubscriptionEntry.setActive(RandomTestUtil.randomBoolean());

		newCommerceSubscriptionEntry.setNextIterationDate(RandomTestUtil.nextDate());

		_commerceSubscriptionEntries.add(_persistence.update(
				newCommerceSubscriptionEntry));

		CommerceSubscriptionEntry existingCommerceSubscriptionEntry = _persistence.findByPrimaryKey(newCommerceSubscriptionEntry.getPrimaryKey());

		Assert.assertEquals(existingCommerceSubscriptionEntry.getUuid(),
			newCommerceSubscriptionEntry.getUuid());
		Assert.assertEquals(existingCommerceSubscriptionEntry.getCommerceSubscriptionEntryId(),
			newCommerceSubscriptionEntry.getCommerceSubscriptionEntryId());
		Assert.assertEquals(existingCommerceSubscriptionEntry.getGroupId(),
			newCommerceSubscriptionEntry.getGroupId());
		Assert.assertEquals(existingCommerceSubscriptionEntry.getCompanyId(),
			newCommerceSubscriptionEntry.getCompanyId());
		Assert.assertEquals(existingCommerceSubscriptionEntry.getUserId(),
			newCommerceSubscriptionEntry.getUserId());
		Assert.assertEquals(existingCommerceSubscriptionEntry.getUserName(),
			newCommerceSubscriptionEntry.getUserName());
		Assert.assertEquals(Time.getShortTimestamp(
				existingCommerceSubscriptionEntry.getCreateDate()),
			Time.getShortTimestamp(newCommerceSubscriptionEntry.getCreateDate()));
		Assert.assertEquals(Time.getShortTimestamp(
				existingCommerceSubscriptionEntry.getModifiedDate()),
			Time.getShortTimestamp(
				newCommerceSubscriptionEntry.getModifiedDate()));
		Assert.assertEquals(existingCommerceSubscriptionEntry.getCPInstanceId(),
			newCommerceSubscriptionEntry.getCPInstanceId());
		Assert.assertEquals(existingCommerceSubscriptionEntry.getCommerceOrderItemId(),
			newCommerceSubscriptionEntry.getCommerceOrderItemId());
		Assert.assertEquals(existingCommerceSubscriptionEntry.getSubscriptionCycleLength(),
			newCommerceSubscriptionEntry.getSubscriptionCycleLength());
		Assert.assertEquals(existingCommerceSubscriptionEntry.getSubscriptionCyclePeriod(),
			newCommerceSubscriptionEntry.getSubscriptionCyclePeriod());
		Assert.assertEquals(existingCommerceSubscriptionEntry.getMaxSubscriptionCyclesNumber(),
			newCommerceSubscriptionEntry.getMaxSubscriptionCyclesNumber());
		Assert.assertEquals(existingCommerceSubscriptionEntry.isActive(),
			newCommerceSubscriptionEntry.isActive());
		Assert.assertEquals(Time.getShortTimestamp(
				existingCommerceSubscriptionEntry.getNextIterationDate()),
			Time.getShortTimestamp(
				newCommerceSubscriptionEntry.getNextIterationDate()));
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
	public void testCountByactive() throws Exception {
		_persistence.countByactive(RandomTestUtil.randomBoolean());

		_persistence.countByactive(RandomTestUtil.randomBoolean());
	}

	@Test
	public void testCountByG_U() throws Exception {
		_persistence.countByG_U(RandomTestUtil.nextLong(),
			RandomTestUtil.nextLong());

		_persistence.countByG_U(0L, 0L);
	}

	@Test
	public void testFindByPrimaryKeyExisting() throws Exception {
		CommerceSubscriptionEntry newCommerceSubscriptionEntry = addCommerceSubscriptionEntry();

		CommerceSubscriptionEntry existingCommerceSubscriptionEntry = _persistence.findByPrimaryKey(newCommerceSubscriptionEntry.getPrimaryKey());

		Assert.assertEquals(existingCommerceSubscriptionEntry,
			newCommerceSubscriptionEntry);
	}

	@Test(expected = NoSuchSubscriptionEntryException.class)
	public void testFindByPrimaryKeyMissing() throws Exception {
		long pk = RandomTestUtil.nextLong();

		_persistence.findByPrimaryKey(pk);
	}

	@Test
	public void testFindAll() throws Exception {
		_persistence.findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS,
			getOrderByComparator());
	}

	protected OrderByComparator<CommerceSubscriptionEntry> getOrderByComparator() {
		return OrderByComparatorFactoryUtil.create("CommerceSubscriptionEntry",
			"uuid", true, "commerceSubscriptionEntryId", true, "groupId", true,
			"companyId", true, "userId", true, "userName", true, "createDate",
			true, "modifiedDate", true, "CPInstanceId", true,
			"commerceOrderItemId", true, "subscriptionCycleLength", true,
			"subscriptionCyclePeriod", true, "maxSubscriptionCyclesNumber",
			true, "active", true, "nextIterationDate", true);
	}

	@Test
	public void testFetchByPrimaryKeyExisting() throws Exception {
		CommerceSubscriptionEntry newCommerceSubscriptionEntry = addCommerceSubscriptionEntry();

		CommerceSubscriptionEntry existingCommerceSubscriptionEntry = _persistence.fetchByPrimaryKey(newCommerceSubscriptionEntry.getPrimaryKey());

		Assert.assertEquals(existingCommerceSubscriptionEntry,
			newCommerceSubscriptionEntry);
	}

	@Test
	public void testFetchByPrimaryKeyMissing() throws Exception {
		long pk = RandomTestUtil.nextLong();

		CommerceSubscriptionEntry missingCommerceSubscriptionEntry = _persistence.fetchByPrimaryKey(pk);

		Assert.assertNull(missingCommerceSubscriptionEntry);
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereAllPrimaryKeysExist()
		throws Exception {
		CommerceSubscriptionEntry newCommerceSubscriptionEntry1 = addCommerceSubscriptionEntry();
		CommerceSubscriptionEntry newCommerceSubscriptionEntry2 = addCommerceSubscriptionEntry();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newCommerceSubscriptionEntry1.getPrimaryKey());
		primaryKeys.add(newCommerceSubscriptionEntry2.getPrimaryKey());

		Map<Serializable, CommerceSubscriptionEntry> commerceSubscriptionEntries =
			_persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(2, commerceSubscriptionEntries.size());
		Assert.assertEquals(newCommerceSubscriptionEntry1,
			commerceSubscriptionEntries.get(
				newCommerceSubscriptionEntry1.getPrimaryKey()));
		Assert.assertEquals(newCommerceSubscriptionEntry2,
			commerceSubscriptionEntries.get(
				newCommerceSubscriptionEntry2.getPrimaryKey()));
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereNoPrimaryKeysExist()
		throws Exception {
		long pk1 = RandomTestUtil.nextLong();

		long pk2 = RandomTestUtil.nextLong();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(pk1);
		primaryKeys.add(pk2);

		Map<Serializable, CommerceSubscriptionEntry> commerceSubscriptionEntries =
			_persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertTrue(commerceSubscriptionEntries.isEmpty());
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereSomePrimaryKeysExist()
		throws Exception {
		CommerceSubscriptionEntry newCommerceSubscriptionEntry = addCommerceSubscriptionEntry();

		long pk = RandomTestUtil.nextLong();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newCommerceSubscriptionEntry.getPrimaryKey());
		primaryKeys.add(pk);

		Map<Serializable, CommerceSubscriptionEntry> commerceSubscriptionEntries =
			_persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(1, commerceSubscriptionEntries.size());
		Assert.assertEquals(newCommerceSubscriptionEntry,
			commerceSubscriptionEntries.get(
				newCommerceSubscriptionEntry.getPrimaryKey()));
	}

	@Test
	public void testFetchByPrimaryKeysWithNoPrimaryKeys()
		throws Exception {
		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		Map<Serializable, CommerceSubscriptionEntry> commerceSubscriptionEntries =
			_persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertTrue(commerceSubscriptionEntries.isEmpty());
	}

	@Test
	public void testFetchByPrimaryKeysWithOnePrimaryKey()
		throws Exception {
		CommerceSubscriptionEntry newCommerceSubscriptionEntry = addCommerceSubscriptionEntry();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newCommerceSubscriptionEntry.getPrimaryKey());

		Map<Serializable, CommerceSubscriptionEntry> commerceSubscriptionEntries =
			_persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(1, commerceSubscriptionEntries.size());
		Assert.assertEquals(newCommerceSubscriptionEntry,
			commerceSubscriptionEntries.get(
				newCommerceSubscriptionEntry.getPrimaryKey()));
	}

	@Test
	public void testActionableDynamicQuery() throws Exception {
		final IntegerWrapper count = new IntegerWrapper();

		ActionableDynamicQuery actionableDynamicQuery = CommerceSubscriptionEntryLocalServiceUtil.getActionableDynamicQuery();

		actionableDynamicQuery.setPerformActionMethod(new ActionableDynamicQuery.PerformActionMethod<CommerceSubscriptionEntry>() {
				@Override
				public void performAction(
					CommerceSubscriptionEntry commerceSubscriptionEntry) {
					Assert.assertNotNull(commerceSubscriptionEntry);

					count.increment();
				}
			});

		actionableDynamicQuery.performActions();

		Assert.assertEquals(count.getValue(), _persistence.countAll());
	}

	@Test
	public void testDynamicQueryByPrimaryKeyExisting()
		throws Exception {
		CommerceSubscriptionEntry newCommerceSubscriptionEntry = addCommerceSubscriptionEntry();

		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(CommerceSubscriptionEntry.class,
				_dynamicQueryClassLoader);

		dynamicQuery.add(RestrictionsFactoryUtil.eq(
				"commerceSubscriptionEntryId",
				newCommerceSubscriptionEntry.getCommerceSubscriptionEntryId()));

		List<CommerceSubscriptionEntry> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(1, result.size());

		CommerceSubscriptionEntry existingCommerceSubscriptionEntry = result.get(0);

		Assert.assertEquals(existingCommerceSubscriptionEntry,
			newCommerceSubscriptionEntry);
	}

	@Test
	public void testDynamicQueryByPrimaryKeyMissing() throws Exception {
		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(CommerceSubscriptionEntry.class,
				_dynamicQueryClassLoader);

		dynamicQuery.add(RestrictionsFactoryUtil.eq(
				"commerceSubscriptionEntryId", RandomTestUtil.nextLong()));

		List<CommerceSubscriptionEntry> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(0, result.size());
	}

	@Test
	public void testDynamicQueryByProjectionExisting()
		throws Exception {
		CommerceSubscriptionEntry newCommerceSubscriptionEntry = addCommerceSubscriptionEntry();

		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(CommerceSubscriptionEntry.class,
				_dynamicQueryClassLoader);

		dynamicQuery.setProjection(ProjectionFactoryUtil.property(
				"commerceSubscriptionEntryId"));

		Object newCommerceSubscriptionEntryId = newCommerceSubscriptionEntry.getCommerceSubscriptionEntryId();

		dynamicQuery.add(RestrictionsFactoryUtil.in(
				"commerceSubscriptionEntryId",
				new Object[] { newCommerceSubscriptionEntryId }));

		List<Object> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(1, result.size());

		Object existingCommerceSubscriptionEntryId = result.get(0);

		Assert.assertEquals(existingCommerceSubscriptionEntryId,
			newCommerceSubscriptionEntryId);
	}

	@Test
	public void testDynamicQueryByProjectionMissing() throws Exception {
		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(CommerceSubscriptionEntry.class,
				_dynamicQueryClassLoader);

		dynamicQuery.setProjection(ProjectionFactoryUtil.property(
				"commerceSubscriptionEntryId"));

		dynamicQuery.add(RestrictionsFactoryUtil.in(
				"commerceSubscriptionEntryId",
				new Object[] { RandomTestUtil.nextLong() }));

		List<Object> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(0, result.size());
	}

	@Test
	public void testResetOriginalValues() throws Exception {
		CommerceSubscriptionEntry newCommerceSubscriptionEntry = addCommerceSubscriptionEntry();

		_persistence.clearCache();

		CommerceSubscriptionEntry existingCommerceSubscriptionEntry = _persistence.findByPrimaryKey(newCommerceSubscriptionEntry.getPrimaryKey());

		Assert.assertTrue(Objects.equals(
				existingCommerceSubscriptionEntry.getUuid(),
				ReflectionTestUtil.invoke(existingCommerceSubscriptionEntry,
					"getOriginalUuid", new Class<?>[0])));
		Assert.assertEquals(Long.valueOf(
				existingCommerceSubscriptionEntry.getGroupId()),
			ReflectionTestUtil.<Long>invoke(existingCommerceSubscriptionEntry,
				"getOriginalGroupId", new Class<?>[0]));
	}

	protected CommerceSubscriptionEntry addCommerceSubscriptionEntry()
		throws Exception {
		long pk = RandomTestUtil.nextLong();

		CommerceSubscriptionEntry commerceSubscriptionEntry = _persistence.create(pk);

		commerceSubscriptionEntry.setUuid(RandomTestUtil.randomString());

		commerceSubscriptionEntry.setGroupId(RandomTestUtil.nextLong());

		commerceSubscriptionEntry.setCompanyId(RandomTestUtil.nextLong());

		commerceSubscriptionEntry.setUserId(RandomTestUtil.nextLong());

		commerceSubscriptionEntry.setUserName(RandomTestUtil.randomString());

		commerceSubscriptionEntry.setCreateDate(RandomTestUtil.nextDate());

		commerceSubscriptionEntry.setModifiedDate(RandomTestUtil.nextDate());

		commerceSubscriptionEntry.setCPInstanceId(RandomTestUtil.nextLong());

		commerceSubscriptionEntry.setCommerceOrderItemId(RandomTestUtil.nextLong());

		commerceSubscriptionEntry.setSubscriptionCycleLength(RandomTestUtil.nextLong());

		commerceSubscriptionEntry.setSubscriptionCyclePeriod(RandomTestUtil.randomString());

		commerceSubscriptionEntry.setMaxSubscriptionCyclesNumber(RandomTestUtil.nextLong());

		commerceSubscriptionEntry.setActive(RandomTestUtil.randomBoolean());

		commerceSubscriptionEntry.setNextIterationDate(RandomTestUtil.nextDate());

		_commerceSubscriptionEntries.add(_persistence.update(
				commerceSubscriptionEntry));

		return commerceSubscriptionEntry;
	}

	private List<CommerceSubscriptionEntry> _commerceSubscriptionEntries = new ArrayList<CommerceSubscriptionEntry>();
	private CommerceSubscriptionEntryPersistence _persistence;
	private ClassLoader _dynamicQueryClassLoader;
}