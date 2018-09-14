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

import com.liferay.commerce.product.exception.NoSuchCPSubscriptionCycleEntryException;
import com.liferay.commerce.product.model.CPSubscriptionCycleEntry;
import com.liferay.commerce.product.service.CPSubscriptionCycleEntryLocalServiceUtil;
import com.liferay.commerce.product.service.persistence.CPSubscriptionCycleEntryPersistence;
import com.liferay.commerce.product.service.persistence.CPSubscriptionCycleEntryUtil;

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
public class CPSubscriptionCycleEntryPersistenceTest {
	@ClassRule
	@Rule
	public static final AggregateTestRule aggregateTestRule = new AggregateTestRule(new LiferayIntegrationTestRule(),
			PersistenceTestRule.INSTANCE,
			new TransactionalTestRule(Propagation.REQUIRED,
				"com.liferay.commerce.product.service"));

	@Before
	public void setUp() {
		_persistence = CPSubscriptionCycleEntryUtil.getPersistence();

		Class<?> clazz = _persistence.getClass();

		_dynamicQueryClassLoader = clazz.getClassLoader();
	}

	@After
	public void tearDown() throws Exception {
		Iterator<CPSubscriptionCycleEntry> iterator = _cpSubscriptionCycleEntries.iterator();

		while (iterator.hasNext()) {
			_persistence.remove(iterator.next());

			iterator.remove();
		}
	}

	@Test
	public void testCreate() throws Exception {
		long pk = RandomTestUtil.nextLong();

		CPSubscriptionCycleEntry cpSubscriptionCycleEntry = _persistence.create(pk);

		Assert.assertNotNull(cpSubscriptionCycleEntry);

		Assert.assertEquals(cpSubscriptionCycleEntry.getPrimaryKey(), pk);
	}

	@Test
	public void testRemove() throws Exception {
		CPSubscriptionCycleEntry newCPSubscriptionCycleEntry = addCPSubscriptionCycleEntry();

		_persistence.remove(newCPSubscriptionCycleEntry);

		CPSubscriptionCycleEntry existingCPSubscriptionCycleEntry = _persistence.fetchByPrimaryKey(newCPSubscriptionCycleEntry.getPrimaryKey());

		Assert.assertNull(existingCPSubscriptionCycleEntry);
	}

	@Test
	public void testUpdateNew() throws Exception {
		addCPSubscriptionCycleEntry();
	}

	@Test
	public void testUpdateExisting() throws Exception {
		long pk = RandomTestUtil.nextLong();

		CPSubscriptionCycleEntry newCPSubscriptionCycleEntry = _persistence.create(pk);

		newCPSubscriptionCycleEntry.setUuid(RandomTestUtil.randomString());

		newCPSubscriptionCycleEntry.setGroupId(RandomTestUtil.nextLong());

		newCPSubscriptionCycleEntry.setCompanyId(RandomTestUtil.nextLong());

		newCPSubscriptionCycleEntry.setUserId(RandomTestUtil.nextLong());

		newCPSubscriptionCycleEntry.setUserName(RandomTestUtil.randomString());

		newCPSubscriptionCycleEntry.setCreateDate(RandomTestUtil.nextDate());

		newCPSubscriptionCycleEntry.setModifiedDate(RandomTestUtil.nextDate());

		newCPSubscriptionCycleEntry.setCPSubscriptionEntryId(RandomTestUtil.nextLong());

		newCPSubscriptionCycleEntry.setCommerceOrderItemId(RandomTestUtil.nextLong());

		newCPSubscriptionCycleEntry.setRenew(RandomTestUtil.randomBoolean());

		_cpSubscriptionCycleEntries.add(_persistence.update(
				newCPSubscriptionCycleEntry));

		CPSubscriptionCycleEntry existingCPSubscriptionCycleEntry = _persistence.findByPrimaryKey(newCPSubscriptionCycleEntry.getPrimaryKey());

		Assert.assertEquals(existingCPSubscriptionCycleEntry.getUuid(),
			newCPSubscriptionCycleEntry.getUuid());
		Assert.assertEquals(existingCPSubscriptionCycleEntry.getCPSubscriptionCycleEntryId(),
			newCPSubscriptionCycleEntry.getCPSubscriptionCycleEntryId());
		Assert.assertEquals(existingCPSubscriptionCycleEntry.getGroupId(),
			newCPSubscriptionCycleEntry.getGroupId());
		Assert.assertEquals(existingCPSubscriptionCycleEntry.getCompanyId(),
			newCPSubscriptionCycleEntry.getCompanyId());
		Assert.assertEquals(existingCPSubscriptionCycleEntry.getUserId(),
			newCPSubscriptionCycleEntry.getUserId());
		Assert.assertEquals(existingCPSubscriptionCycleEntry.getUserName(),
			newCPSubscriptionCycleEntry.getUserName());
		Assert.assertEquals(Time.getShortTimestamp(
				existingCPSubscriptionCycleEntry.getCreateDate()),
			Time.getShortTimestamp(newCPSubscriptionCycleEntry.getCreateDate()));
		Assert.assertEquals(Time.getShortTimestamp(
				existingCPSubscriptionCycleEntry.getModifiedDate()),
			Time.getShortTimestamp(
				newCPSubscriptionCycleEntry.getModifiedDate()));
		Assert.assertEquals(existingCPSubscriptionCycleEntry.getCPSubscriptionEntryId(),
			newCPSubscriptionCycleEntry.getCPSubscriptionEntryId());
		Assert.assertEquals(existingCPSubscriptionCycleEntry.getCommerceOrderItemId(),
			newCPSubscriptionCycleEntry.getCommerceOrderItemId());
		Assert.assertEquals(existingCPSubscriptionCycleEntry.isRenew(),
			newCPSubscriptionCycleEntry.isRenew());
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
	public void testCountByCPSubscriptionEntryId() throws Exception {
		_persistence.countByCPSubscriptionEntryId(RandomTestUtil.nextLong());

		_persistence.countByCPSubscriptionEntryId(0L);
	}

	@Test
	public void testFindByPrimaryKeyExisting() throws Exception {
		CPSubscriptionCycleEntry newCPSubscriptionCycleEntry = addCPSubscriptionCycleEntry();

		CPSubscriptionCycleEntry existingCPSubscriptionCycleEntry = _persistence.findByPrimaryKey(newCPSubscriptionCycleEntry.getPrimaryKey());

		Assert.assertEquals(existingCPSubscriptionCycleEntry,
			newCPSubscriptionCycleEntry);
	}

	@Test(expected = NoSuchCPSubscriptionCycleEntryException.class)
	public void testFindByPrimaryKeyMissing() throws Exception {
		long pk = RandomTestUtil.nextLong();

		_persistence.findByPrimaryKey(pk);
	}

	@Test
	public void testFindAll() throws Exception {
		_persistence.findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS,
			getOrderByComparator());
	}

	protected OrderByComparator<CPSubscriptionCycleEntry> getOrderByComparator() {
		return OrderByComparatorFactoryUtil.create("CPSubscriptionCycleEntry",
			"uuid", true, "CPSubscriptionCycleEntryId", true, "groupId", true,
			"companyId", true, "userId", true, "userName", true, "createDate",
			true, "modifiedDate", true, "CPSubscriptionEntryId", true,
			"commerceOrderItemId", true, "renew", true);
	}

	@Test
	public void testFetchByPrimaryKeyExisting() throws Exception {
		CPSubscriptionCycleEntry newCPSubscriptionCycleEntry = addCPSubscriptionCycleEntry();

		CPSubscriptionCycleEntry existingCPSubscriptionCycleEntry = _persistence.fetchByPrimaryKey(newCPSubscriptionCycleEntry.getPrimaryKey());

		Assert.assertEquals(existingCPSubscriptionCycleEntry,
			newCPSubscriptionCycleEntry);
	}

	@Test
	public void testFetchByPrimaryKeyMissing() throws Exception {
		long pk = RandomTestUtil.nextLong();

		CPSubscriptionCycleEntry missingCPSubscriptionCycleEntry = _persistence.fetchByPrimaryKey(pk);

		Assert.assertNull(missingCPSubscriptionCycleEntry);
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereAllPrimaryKeysExist()
		throws Exception {
		CPSubscriptionCycleEntry newCPSubscriptionCycleEntry1 = addCPSubscriptionCycleEntry();
		CPSubscriptionCycleEntry newCPSubscriptionCycleEntry2 = addCPSubscriptionCycleEntry();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newCPSubscriptionCycleEntry1.getPrimaryKey());
		primaryKeys.add(newCPSubscriptionCycleEntry2.getPrimaryKey());

		Map<Serializable, CPSubscriptionCycleEntry> cpSubscriptionCycleEntries = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(2, cpSubscriptionCycleEntries.size());
		Assert.assertEquals(newCPSubscriptionCycleEntry1,
			cpSubscriptionCycleEntries.get(
				newCPSubscriptionCycleEntry1.getPrimaryKey()));
		Assert.assertEquals(newCPSubscriptionCycleEntry2,
			cpSubscriptionCycleEntries.get(
				newCPSubscriptionCycleEntry2.getPrimaryKey()));
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereNoPrimaryKeysExist()
		throws Exception {
		long pk1 = RandomTestUtil.nextLong();

		long pk2 = RandomTestUtil.nextLong();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(pk1);
		primaryKeys.add(pk2);

		Map<Serializable, CPSubscriptionCycleEntry> cpSubscriptionCycleEntries = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertTrue(cpSubscriptionCycleEntries.isEmpty());
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereSomePrimaryKeysExist()
		throws Exception {
		CPSubscriptionCycleEntry newCPSubscriptionCycleEntry = addCPSubscriptionCycleEntry();

		long pk = RandomTestUtil.nextLong();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newCPSubscriptionCycleEntry.getPrimaryKey());
		primaryKeys.add(pk);

		Map<Serializable, CPSubscriptionCycleEntry> cpSubscriptionCycleEntries = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(1, cpSubscriptionCycleEntries.size());
		Assert.assertEquals(newCPSubscriptionCycleEntry,
			cpSubscriptionCycleEntries.get(
				newCPSubscriptionCycleEntry.getPrimaryKey()));
	}

	@Test
	public void testFetchByPrimaryKeysWithNoPrimaryKeys()
		throws Exception {
		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		Map<Serializable, CPSubscriptionCycleEntry> cpSubscriptionCycleEntries = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertTrue(cpSubscriptionCycleEntries.isEmpty());
	}

	@Test
	public void testFetchByPrimaryKeysWithOnePrimaryKey()
		throws Exception {
		CPSubscriptionCycleEntry newCPSubscriptionCycleEntry = addCPSubscriptionCycleEntry();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newCPSubscriptionCycleEntry.getPrimaryKey());

		Map<Serializable, CPSubscriptionCycleEntry> cpSubscriptionCycleEntries = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(1, cpSubscriptionCycleEntries.size());
		Assert.assertEquals(newCPSubscriptionCycleEntry,
			cpSubscriptionCycleEntries.get(
				newCPSubscriptionCycleEntry.getPrimaryKey()));
	}

	@Test
	public void testActionableDynamicQuery() throws Exception {
		final IntegerWrapper count = new IntegerWrapper();

		ActionableDynamicQuery actionableDynamicQuery = CPSubscriptionCycleEntryLocalServiceUtil.getActionableDynamicQuery();

		actionableDynamicQuery.setPerformActionMethod(new ActionableDynamicQuery.PerformActionMethod<CPSubscriptionCycleEntry>() {
				@Override
				public void performAction(
					CPSubscriptionCycleEntry cpSubscriptionCycleEntry) {
					Assert.assertNotNull(cpSubscriptionCycleEntry);

					count.increment();
				}
			});

		actionableDynamicQuery.performActions();

		Assert.assertEquals(count.getValue(), _persistence.countAll());
	}

	@Test
	public void testDynamicQueryByPrimaryKeyExisting()
		throws Exception {
		CPSubscriptionCycleEntry newCPSubscriptionCycleEntry = addCPSubscriptionCycleEntry();

		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(CPSubscriptionCycleEntry.class,
				_dynamicQueryClassLoader);

		dynamicQuery.add(RestrictionsFactoryUtil.eq(
				"CPSubscriptionCycleEntryId",
				newCPSubscriptionCycleEntry.getCPSubscriptionCycleEntryId()));

		List<CPSubscriptionCycleEntry> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(1, result.size());

		CPSubscriptionCycleEntry existingCPSubscriptionCycleEntry = result.get(0);

		Assert.assertEquals(existingCPSubscriptionCycleEntry,
			newCPSubscriptionCycleEntry);
	}

	@Test
	public void testDynamicQueryByPrimaryKeyMissing() throws Exception {
		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(CPSubscriptionCycleEntry.class,
				_dynamicQueryClassLoader);

		dynamicQuery.add(RestrictionsFactoryUtil.eq(
				"CPSubscriptionCycleEntryId", RandomTestUtil.nextLong()));

		List<CPSubscriptionCycleEntry> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(0, result.size());
	}

	@Test
	public void testDynamicQueryByProjectionExisting()
		throws Exception {
		CPSubscriptionCycleEntry newCPSubscriptionCycleEntry = addCPSubscriptionCycleEntry();

		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(CPSubscriptionCycleEntry.class,
				_dynamicQueryClassLoader);

		dynamicQuery.setProjection(ProjectionFactoryUtil.property(
				"CPSubscriptionCycleEntryId"));

		Object newCPSubscriptionCycleEntryId = newCPSubscriptionCycleEntry.getCPSubscriptionCycleEntryId();

		dynamicQuery.add(RestrictionsFactoryUtil.in(
				"CPSubscriptionCycleEntryId",
				new Object[] { newCPSubscriptionCycleEntryId }));

		List<Object> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(1, result.size());

		Object existingCPSubscriptionCycleEntryId = result.get(0);

		Assert.assertEquals(existingCPSubscriptionCycleEntryId,
			newCPSubscriptionCycleEntryId);
	}

	@Test
	public void testDynamicQueryByProjectionMissing() throws Exception {
		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(CPSubscriptionCycleEntry.class,
				_dynamicQueryClassLoader);

		dynamicQuery.setProjection(ProjectionFactoryUtil.property(
				"CPSubscriptionCycleEntryId"));

		dynamicQuery.add(RestrictionsFactoryUtil.in(
				"CPSubscriptionCycleEntryId",
				new Object[] { RandomTestUtil.nextLong() }));

		List<Object> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(0, result.size());
	}

	@Test
	public void testResetOriginalValues() throws Exception {
		CPSubscriptionCycleEntry newCPSubscriptionCycleEntry = addCPSubscriptionCycleEntry();

		_persistence.clearCache();

		CPSubscriptionCycleEntry existingCPSubscriptionCycleEntry = _persistence.findByPrimaryKey(newCPSubscriptionCycleEntry.getPrimaryKey());

		Assert.assertTrue(Objects.equals(
				existingCPSubscriptionCycleEntry.getUuid(),
				ReflectionTestUtil.invoke(existingCPSubscriptionCycleEntry,
					"getOriginalUuid", new Class<?>[0])));
		Assert.assertEquals(Long.valueOf(
				existingCPSubscriptionCycleEntry.getGroupId()),
			ReflectionTestUtil.<Long>invoke(existingCPSubscriptionCycleEntry,
				"getOriginalGroupId", new Class<?>[0]));
	}

	protected CPSubscriptionCycleEntry addCPSubscriptionCycleEntry()
		throws Exception {
		long pk = RandomTestUtil.nextLong();

		CPSubscriptionCycleEntry cpSubscriptionCycleEntry = _persistence.create(pk);

		cpSubscriptionCycleEntry.setUuid(RandomTestUtil.randomString());

		cpSubscriptionCycleEntry.setGroupId(RandomTestUtil.nextLong());

		cpSubscriptionCycleEntry.setCompanyId(RandomTestUtil.nextLong());

		cpSubscriptionCycleEntry.setUserId(RandomTestUtil.nextLong());

		cpSubscriptionCycleEntry.setUserName(RandomTestUtil.randomString());

		cpSubscriptionCycleEntry.setCreateDate(RandomTestUtil.nextDate());

		cpSubscriptionCycleEntry.setModifiedDate(RandomTestUtil.nextDate());

		cpSubscriptionCycleEntry.setCPSubscriptionEntryId(RandomTestUtil.nextLong());

		cpSubscriptionCycleEntry.setCommerceOrderItemId(RandomTestUtil.nextLong());

		cpSubscriptionCycleEntry.setRenew(RandomTestUtil.randomBoolean());

		_cpSubscriptionCycleEntries.add(_persistence.update(
				cpSubscriptionCycleEntry));

		return cpSubscriptionCycleEntry;
	}

	private List<CPSubscriptionCycleEntry> _cpSubscriptionCycleEntries = new ArrayList<CPSubscriptionCycleEntry>();
	private CPSubscriptionCycleEntryPersistence _persistence;
	private ClassLoader _dynamicQueryClassLoader;
}