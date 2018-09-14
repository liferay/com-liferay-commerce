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

package com.liferay.commerce.product.service.persistence.test;

import com.liferay.arquillian.extension.junit.bridge.junit.Arquillian;

import com.liferay.commerce.product.exception.NoSuchCPSubscriptionEntryException;
import com.liferay.commerce.product.model.CPSubscriptionEntry;
import com.liferay.commerce.product.service.CPSubscriptionEntryLocalServiceUtil;
import com.liferay.commerce.product.service.persistence.CPSubscriptionEntryPersistence;
import com.liferay.commerce.product.service.persistence.CPSubscriptionEntryUtil;

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
public class CPSubscriptionEntryPersistenceTest {
	@ClassRule
	@Rule
	public static final AggregateTestRule aggregateTestRule = new AggregateTestRule(new LiferayIntegrationTestRule(),
			PersistenceTestRule.INSTANCE,
			new TransactionalTestRule(Propagation.REQUIRED,
				"com.liferay.commerce.product.service"));

	@Before
	public void setUp() {
		_persistence = CPSubscriptionEntryUtil.getPersistence();

		Class<?> clazz = _persistence.getClass();

		_dynamicQueryClassLoader = clazz.getClassLoader();
	}

	@After
	public void tearDown() throws Exception {
		Iterator<CPSubscriptionEntry> iterator = _cpSubscriptionEntries.iterator();

		while (iterator.hasNext()) {
			_persistence.remove(iterator.next());

			iterator.remove();
		}
	}

	@Test
	public void testCreate() throws Exception {
		long pk = RandomTestUtil.nextLong();

		CPSubscriptionEntry cpSubscriptionEntry = _persistence.create(pk);

		Assert.assertNotNull(cpSubscriptionEntry);

		Assert.assertEquals(cpSubscriptionEntry.getPrimaryKey(), pk);
	}

	@Test
	public void testRemove() throws Exception {
		CPSubscriptionEntry newCPSubscriptionEntry = addCPSubscriptionEntry();

		_persistence.remove(newCPSubscriptionEntry);

		CPSubscriptionEntry existingCPSubscriptionEntry = _persistence.fetchByPrimaryKey(newCPSubscriptionEntry.getPrimaryKey());

		Assert.assertNull(existingCPSubscriptionEntry);
	}

	@Test
	public void testUpdateNew() throws Exception {
		addCPSubscriptionEntry();
	}

	@Test
	public void testUpdateExisting() throws Exception {
		long pk = RandomTestUtil.nextLong();

		CPSubscriptionEntry newCPSubscriptionEntry = _persistence.create(pk);

		newCPSubscriptionEntry.setUuid(RandomTestUtil.randomString());

		newCPSubscriptionEntry.setGroupId(RandomTestUtil.nextLong());

		newCPSubscriptionEntry.setCompanyId(RandomTestUtil.nextLong());

		newCPSubscriptionEntry.setUserId(RandomTestUtil.nextLong());

		newCPSubscriptionEntry.setUserName(RandomTestUtil.randomString());

		newCPSubscriptionEntry.setCreateDate(RandomTestUtil.nextDate());

		newCPSubscriptionEntry.setModifiedDate(RandomTestUtil.nextDate());

		newCPSubscriptionEntry.setCPInstanceId(RandomTestUtil.nextLong());

		newCPSubscriptionEntry.setCommerceOrderItemId(RandomTestUtil.nextLong());

		newCPSubscriptionEntry.setSubscriptionCycleLength(RandomTestUtil.nextLong());

		newCPSubscriptionEntry.setSubscriptionCyclePeriod(RandomTestUtil.randomString());

		newCPSubscriptionEntry.setMaxSubscriptionCyclesNumber(RandomTestUtil.nextLong());

		newCPSubscriptionEntry.setActive(RandomTestUtil.randomBoolean());

		newCPSubscriptionEntry.setNextIterationDate(RandomTestUtil.nextDate());

		_cpSubscriptionEntries.add(_persistence.update(newCPSubscriptionEntry));

		CPSubscriptionEntry existingCPSubscriptionEntry = _persistence.findByPrimaryKey(newCPSubscriptionEntry.getPrimaryKey());

		Assert.assertEquals(existingCPSubscriptionEntry.getUuid(),
			newCPSubscriptionEntry.getUuid());
		Assert.assertEquals(existingCPSubscriptionEntry.getCPSubscriptionEntryId(),
			newCPSubscriptionEntry.getCPSubscriptionEntryId());
		Assert.assertEquals(existingCPSubscriptionEntry.getGroupId(),
			newCPSubscriptionEntry.getGroupId());
		Assert.assertEquals(existingCPSubscriptionEntry.getCompanyId(),
			newCPSubscriptionEntry.getCompanyId());
		Assert.assertEquals(existingCPSubscriptionEntry.getUserId(),
			newCPSubscriptionEntry.getUserId());
		Assert.assertEquals(existingCPSubscriptionEntry.getUserName(),
			newCPSubscriptionEntry.getUserName());
		Assert.assertEquals(Time.getShortTimestamp(
				existingCPSubscriptionEntry.getCreateDate()),
			Time.getShortTimestamp(newCPSubscriptionEntry.getCreateDate()));
		Assert.assertEquals(Time.getShortTimestamp(
				existingCPSubscriptionEntry.getModifiedDate()),
			Time.getShortTimestamp(newCPSubscriptionEntry.getModifiedDate()));
		Assert.assertEquals(existingCPSubscriptionEntry.getCPInstanceId(),
			newCPSubscriptionEntry.getCPInstanceId());
		Assert.assertEquals(existingCPSubscriptionEntry.getCommerceOrderItemId(),
			newCPSubscriptionEntry.getCommerceOrderItemId());
		Assert.assertEquals(existingCPSubscriptionEntry.getSubscriptionCycleLength(),
			newCPSubscriptionEntry.getSubscriptionCycleLength());
		Assert.assertEquals(existingCPSubscriptionEntry.getSubscriptionCyclePeriod(),
			newCPSubscriptionEntry.getSubscriptionCyclePeriod());
		Assert.assertEquals(existingCPSubscriptionEntry.getMaxSubscriptionCyclesNumber(),
			newCPSubscriptionEntry.getMaxSubscriptionCyclesNumber());
		Assert.assertEquals(existingCPSubscriptionEntry.isActive(),
			newCPSubscriptionEntry.isActive());
		Assert.assertEquals(Time.getShortTimestamp(
				existingCPSubscriptionEntry.getNextIterationDate()),
			Time.getShortTimestamp(
				newCPSubscriptionEntry.getNextIterationDate()));
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
	public void testCountByCPInstanceId() throws Exception {
		_persistence.countByCPInstanceId(RandomTestUtil.nextLong());

		_persistence.countByCPInstanceId(0L);
	}

	@Test
	public void testCountByG_U() throws Exception {
		_persistence.countByG_U(RandomTestUtil.nextLong(),
			RandomTestUtil.nextLong());

		_persistence.countByG_U(0L, 0L);
	}

	@Test
	public void testCountByactive() throws Exception {
		_persistence.countByactive(RandomTestUtil.randomBoolean());

		_persistence.countByactive(RandomTestUtil.randomBoolean());
	}

	@Test
	public void testFindByPrimaryKeyExisting() throws Exception {
		CPSubscriptionEntry newCPSubscriptionEntry = addCPSubscriptionEntry();

		CPSubscriptionEntry existingCPSubscriptionEntry = _persistence.findByPrimaryKey(newCPSubscriptionEntry.getPrimaryKey());

		Assert.assertEquals(existingCPSubscriptionEntry, newCPSubscriptionEntry);
	}

	@Test(expected = NoSuchCPSubscriptionEntryException.class)
	public void testFindByPrimaryKeyMissing() throws Exception {
		long pk = RandomTestUtil.nextLong();

		_persistence.findByPrimaryKey(pk);
	}

	@Test
	public void testFindAll() throws Exception {
		_persistence.findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS,
			getOrderByComparator());
	}

	protected OrderByComparator<CPSubscriptionEntry> getOrderByComparator() {
		return OrderByComparatorFactoryUtil.create("CPSubscriptionEntry",
			"uuid", true, "CPSubscriptionEntryId", true, "groupId", true,
			"companyId", true, "userId", true, "userName", true, "createDate",
			true, "modifiedDate", true, "CPInstanceId", true,
			"commerceOrderItemId", true, "subscriptionCycleLength", true,
			"subscriptionCyclePeriod", true, "maxSubscriptionCyclesNumber",
			true, "active", true, "nextIterationDate", true);
	}

	@Test
	public void testFetchByPrimaryKeyExisting() throws Exception {
		CPSubscriptionEntry newCPSubscriptionEntry = addCPSubscriptionEntry();

		CPSubscriptionEntry existingCPSubscriptionEntry = _persistence.fetchByPrimaryKey(newCPSubscriptionEntry.getPrimaryKey());

		Assert.assertEquals(existingCPSubscriptionEntry, newCPSubscriptionEntry);
	}

	@Test
	public void testFetchByPrimaryKeyMissing() throws Exception {
		long pk = RandomTestUtil.nextLong();

		CPSubscriptionEntry missingCPSubscriptionEntry = _persistence.fetchByPrimaryKey(pk);

		Assert.assertNull(missingCPSubscriptionEntry);
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereAllPrimaryKeysExist()
		throws Exception {
		CPSubscriptionEntry newCPSubscriptionEntry1 = addCPSubscriptionEntry();
		CPSubscriptionEntry newCPSubscriptionEntry2 = addCPSubscriptionEntry();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newCPSubscriptionEntry1.getPrimaryKey());
		primaryKeys.add(newCPSubscriptionEntry2.getPrimaryKey());

		Map<Serializable, CPSubscriptionEntry> cpSubscriptionEntries = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(2, cpSubscriptionEntries.size());
		Assert.assertEquals(newCPSubscriptionEntry1,
			cpSubscriptionEntries.get(newCPSubscriptionEntry1.getPrimaryKey()));
		Assert.assertEquals(newCPSubscriptionEntry2,
			cpSubscriptionEntries.get(newCPSubscriptionEntry2.getPrimaryKey()));
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereNoPrimaryKeysExist()
		throws Exception {
		long pk1 = RandomTestUtil.nextLong();

		long pk2 = RandomTestUtil.nextLong();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(pk1);
		primaryKeys.add(pk2);

		Map<Serializable, CPSubscriptionEntry> cpSubscriptionEntries = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertTrue(cpSubscriptionEntries.isEmpty());
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereSomePrimaryKeysExist()
		throws Exception {
		CPSubscriptionEntry newCPSubscriptionEntry = addCPSubscriptionEntry();

		long pk = RandomTestUtil.nextLong();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newCPSubscriptionEntry.getPrimaryKey());
		primaryKeys.add(pk);

		Map<Serializable, CPSubscriptionEntry> cpSubscriptionEntries = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(1, cpSubscriptionEntries.size());
		Assert.assertEquals(newCPSubscriptionEntry,
			cpSubscriptionEntries.get(newCPSubscriptionEntry.getPrimaryKey()));
	}

	@Test
	public void testFetchByPrimaryKeysWithNoPrimaryKeys()
		throws Exception {
		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		Map<Serializable, CPSubscriptionEntry> cpSubscriptionEntries = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertTrue(cpSubscriptionEntries.isEmpty());
	}

	@Test
	public void testFetchByPrimaryKeysWithOnePrimaryKey()
		throws Exception {
		CPSubscriptionEntry newCPSubscriptionEntry = addCPSubscriptionEntry();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newCPSubscriptionEntry.getPrimaryKey());

		Map<Serializable, CPSubscriptionEntry> cpSubscriptionEntries = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(1, cpSubscriptionEntries.size());
		Assert.assertEquals(newCPSubscriptionEntry,
			cpSubscriptionEntries.get(newCPSubscriptionEntry.getPrimaryKey()));
	}

	@Test
	public void testActionableDynamicQuery() throws Exception {
		final IntegerWrapper count = new IntegerWrapper();

		ActionableDynamicQuery actionableDynamicQuery = CPSubscriptionEntryLocalServiceUtil.getActionableDynamicQuery();

		actionableDynamicQuery.setPerformActionMethod(new ActionableDynamicQuery.PerformActionMethod<CPSubscriptionEntry>() {
				@Override
				public void performAction(
					CPSubscriptionEntry cpSubscriptionEntry) {
					Assert.assertNotNull(cpSubscriptionEntry);

					count.increment();
				}
			});

		actionableDynamicQuery.performActions();

		Assert.assertEquals(count.getValue(), _persistence.countAll());
	}

	@Test
	public void testDynamicQueryByPrimaryKeyExisting()
		throws Exception {
		CPSubscriptionEntry newCPSubscriptionEntry = addCPSubscriptionEntry();

		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(CPSubscriptionEntry.class,
				_dynamicQueryClassLoader);

		dynamicQuery.add(RestrictionsFactoryUtil.eq("CPSubscriptionEntryId",
				newCPSubscriptionEntry.getCPSubscriptionEntryId()));

		List<CPSubscriptionEntry> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(1, result.size());

		CPSubscriptionEntry existingCPSubscriptionEntry = result.get(0);

		Assert.assertEquals(existingCPSubscriptionEntry, newCPSubscriptionEntry);
	}

	@Test
	public void testDynamicQueryByPrimaryKeyMissing() throws Exception {
		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(CPSubscriptionEntry.class,
				_dynamicQueryClassLoader);

		dynamicQuery.add(RestrictionsFactoryUtil.eq("CPSubscriptionEntryId",
				RandomTestUtil.nextLong()));

		List<CPSubscriptionEntry> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(0, result.size());
	}

	@Test
	public void testDynamicQueryByProjectionExisting()
		throws Exception {
		CPSubscriptionEntry newCPSubscriptionEntry = addCPSubscriptionEntry();

		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(CPSubscriptionEntry.class,
				_dynamicQueryClassLoader);

		dynamicQuery.setProjection(ProjectionFactoryUtil.property(
				"CPSubscriptionEntryId"));

		Object newCPSubscriptionEntryId = newCPSubscriptionEntry.getCPSubscriptionEntryId();

		dynamicQuery.add(RestrictionsFactoryUtil.in("CPSubscriptionEntryId",
				new Object[] { newCPSubscriptionEntryId }));

		List<Object> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(1, result.size());

		Object existingCPSubscriptionEntryId = result.get(0);

		Assert.assertEquals(existingCPSubscriptionEntryId,
			newCPSubscriptionEntryId);
	}

	@Test
	public void testDynamicQueryByProjectionMissing() throws Exception {
		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(CPSubscriptionEntry.class,
				_dynamicQueryClassLoader);

		dynamicQuery.setProjection(ProjectionFactoryUtil.property(
				"CPSubscriptionEntryId"));

		dynamicQuery.add(RestrictionsFactoryUtil.in("CPSubscriptionEntryId",
				new Object[] { RandomTestUtil.nextLong() }));

		List<Object> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(0, result.size());
	}

	@Test
	public void testResetOriginalValues() throws Exception {
		CPSubscriptionEntry newCPSubscriptionEntry = addCPSubscriptionEntry();

		_persistence.clearCache();

		CPSubscriptionEntry existingCPSubscriptionEntry = _persistence.findByPrimaryKey(newCPSubscriptionEntry.getPrimaryKey());

		Assert.assertTrue(Objects.equals(
				existingCPSubscriptionEntry.getUuid(),
				ReflectionTestUtil.invoke(existingCPSubscriptionEntry,
					"getOriginalUuid", new Class<?>[0])));
		Assert.assertEquals(Long.valueOf(
				existingCPSubscriptionEntry.getGroupId()),
			ReflectionTestUtil.<Long>invoke(existingCPSubscriptionEntry,
				"getOriginalGroupId", new Class<?>[0]));
	}

	protected CPSubscriptionEntry addCPSubscriptionEntry()
		throws Exception {
		long pk = RandomTestUtil.nextLong();

		CPSubscriptionEntry cpSubscriptionEntry = _persistence.create(pk);

		cpSubscriptionEntry.setUuid(RandomTestUtil.randomString());

		cpSubscriptionEntry.setGroupId(RandomTestUtil.nextLong());

		cpSubscriptionEntry.setCompanyId(RandomTestUtil.nextLong());

		cpSubscriptionEntry.setUserId(RandomTestUtil.nextLong());

		cpSubscriptionEntry.setUserName(RandomTestUtil.randomString());

		cpSubscriptionEntry.setCreateDate(RandomTestUtil.nextDate());

		cpSubscriptionEntry.setModifiedDate(RandomTestUtil.nextDate());

		cpSubscriptionEntry.setCPInstanceId(RandomTestUtil.nextLong());

		cpSubscriptionEntry.setCommerceOrderItemId(RandomTestUtil.nextLong());

		cpSubscriptionEntry.setSubscriptionCycleLength(RandomTestUtil.nextLong());

		cpSubscriptionEntry.setSubscriptionCyclePeriod(RandomTestUtil.randomString());

		cpSubscriptionEntry.setMaxSubscriptionCyclesNumber(RandomTestUtil.nextLong());

		cpSubscriptionEntry.setActive(RandomTestUtil.randomBoolean());

		cpSubscriptionEntry.setNextIterationDate(RandomTestUtil.nextDate());

		_cpSubscriptionEntries.add(_persistence.update(cpSubscriptionEntry));

		return cpSubscriptionEntry;
	}

	private List<CPSubscriptionEntry> _cpSubscriptionEntries = new ArrayList<CPSubscriptionEntry>();
	private CPSubscriptionEntryPersistence _persistence;
	private ClassLoader _dynamicQueryClassLoader;
}