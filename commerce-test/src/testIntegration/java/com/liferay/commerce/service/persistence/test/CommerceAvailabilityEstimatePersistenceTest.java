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

import com.liferay.commerce.exception.NoSuchAvailabilityEstimateException;
import com.liferay.commerce.model.CommerceAvailabilityEstimate;
import com.liferay.commerce.service.CommerceAvailabilityEstimateLocalServiceUtil;
import com.liferay.commerce.service.persistence.CommerceAvailabilityEstimatePersistence;
import com.liferay.commerce.service.persistence.CommerceAvailabilityEstimateUtil;

import com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.dao.orm.DynamicQueryFactoryUtil;
import com.liferay.portal.kernel.dao.orm.ProjectionFactoryUtil;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.dao.orm.RestrictionsFactoryUtil;
import com.liferay.portal.kernel.test.AssertUtils;
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
import org.junit.Ignore;
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
@Ignore
@RunWith(Arquillian.class)
public class CommerceAvailabilityEstimatePersistenceTest {
	@ClassRule
	@Rule
	public static final AggregateTestRule aggregateTestRule = new AggregateTestRule(new LiferayIntegrationTestRule(),
			PersistenceTestRule.INSTANCE,
			new TransactionalTestRule(Propagation.REQUIRED,
				"com.liferay.commerce.service"));

	@Before
	public void setUp() {
		_persistence = CommerceAvailabilityEstimateUtil.getPersistence();

		Class<?> clazz = _persistence.getClass();

		_dynamicQueryClassLoader = clazz.getClassLoader();
	}

	@After
	public void tearDown() throws Exception {
		Iterator<CommerceAvailabilityEstimate> iterator = _commerceAvailabilityEstimates.iterator();

		while (iterator.hasNext()) {
			_persistence.remove(iterator.next());

			iterator.remove();
		}
	}

	@Test
	public void testCreate() throws Exception {
		long pk = RandomTestUtil.nextLong();

		CommerceAvailabilityEstimate commerceAvailabilityEstimate = _persistence.create(pk);

		Assert.assertNotNull(commerceAvailabilityEstimate);

		Assert.assertEquals(commerceAvailabilityEstimate.getPrimaryKey(), pk);
	}

	@Test
	public void testRemove() throws Exception {
		CommerceAvailabilityEstimate newCommerceAvailabilityEstimate = addCommerceAvailabilityEstimate();

		_persistence.remove(newCommerceAvailabilityEstimate);

		CommerceAvailabilityEstimate existingCommerceAvailabilityEstimate = _persistence.fetchByPrimaryKey(newCommerceAvailabilityEstimate.getPrimaryKey());

		Assert.assertNull(existingCommerceAvailabilityEstimate);
	}

	@Test
	public void testUpdateNew() throws Exception {
		addCommerceAvailabilityEstimate();
	}

	@Test
	public void testUpdateExisting() throws Exception {
		long pk = RandomTestUtil.nextLong();

		CommerceAvailabilityEstimate newCommerceAvailabilityEstimate = _persistence.create(pk);

		newCommerceAvailabilityEstimate.setUuid(RandomTestUtil.randomString());

		newCommerceAvailabilityEstimate.setGroupId(RandomTestUtil.nextLong());

		newCommerceAvailabilityEstimate.setCompanyId(RandomTestUtil.nextLong());

		newCommerceAvailabilityEstimate.setUserId(RandomTestUtil.nextLong());

		newCommerceAvailabilityEstimate.setUserName(RandomTestUtil.randomString());

		newCommerceAvailabilityEstimate.setCreateDate(RandomTestUtil.nextDate());

		newCommerceAvailabilityEstimate.setModifiedDate(RandomTestUtil.nextDate());

		newCommerceAvailabilityEstimate.setTitle(RandomTestUtil.randomString());

		newCommerceAvailabilityEstimate.setPriority(RandomTestUtil.nextDouble());

		newCommerceAvailabilityEstimate.setLastPublishDate(RandomTestUtil.nextDate());

		_commerceAvailabilityEstimates.add(_persistence.update(
				newCommerceAvailabilityEstimate));

		CommerceAvailabilityEstimate existingCommerceAvailabilityEstimate = _persistence.findByPrimaryKey(newCommerceAvailabilityEstimate.getPrimaryKey());

		Assert.assertEquals(existingCommerceAvailabilityEstimate.getUuid(),
			newCommerceAvailabilityEstimate.getUuid());
		Assert.assertEquals(existingCommerceAvailabilityEstimate.getCommerceAvailabilityEstimateId(),
			newCommerceAvailabilityEstimate.getCommerceAvailabilityEstimateId());
		Assert.assertEquals(existingCommerceAvailabilityEstimate.getGroupId(),
			newCommerceAvailabilityEstimate.getGroupId());
		Assert.assertEquals(existingCommerceAvailabilityEstimate.getCompanyId(),
			newCommerceAvailabilityEstimate.getCompanyId());
		Assert.assertEquals(existingCommerceAvailabilityEstimate.getUserId(),
			newCommerceAvailabilityEstimate.getUserId());
		Assert.assertEquals(existingCommerceAvailabilityEstimate.getUserName(),
			newCommerceAvailabilityEstimate.getUserName());
		Assert.assertEquals(Time.getShortTimestamp(
				existingCommerceAvailabilityEstimate.getCreateDate()),
			Time.getShortTimestamp(
				newCommerceAvailabilityEstimate.getCreateDate()));
		Assert.assertEquals(Time.getShortTimestamp(
				existingCommerceAvailabilityEstimate.getModifiedDate()),
			Time.getShortTimestamp(
				newCommerceAvailabilityEstimate.getModifiedDate()));
		Assert.assertEquals(existingCommerceAvailabilityEstimate.getTitle(),
			newCommerceAvailabilityEstimate.getTitle());
		AssertUtils.assertEquals(existingCommerceAvailabilityEstimate.getPriority(),
			newCommerceAvailabilityEstimate.getPriority());
		Assert.assertEquals(Time.getShortTimestamp(
				existingCommerceAvailabilityEstimate.getLastPublishDate()),
			Time.getShortTimestamp(
				newCommerceAvailabilityEstimate.getLastPublishDate()));
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
	public void testFindByPrimaryKeyExisting() throws Exception {
		CommerceAvailabilityEstimate newCommerceAvailabilityEstimate = addCommerceAvailabilityEstimate();

		CommerceAvailabilityEstimate existingCommerceAvailabilityEstimate = _persistence.findByPrimaryKey(newCommerceAvailabilityEstimate.getPrimaryKey());

		Assert.assertEquals(existingCommerceAvailabilityEstimate,
			newCommerceAvailabilityEstimate);
	}

	@Test(expected = NoSuchAvailabilityEstimateException.class)
	public void testFindByPrimaryKeyMissing() throws Exception {
		long pk = RandomTestUtil.nextLong();

		_persistence.findByPrimaryKey(pk);
	}

	@Test
	public void testFindAll() throws Exception {
		_persistence.findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS,
			getOrderByComparator());
	}

	protected OrderByComparator<CommerceAvailabilityEstimate> getOrderByComparator() {
		return OrderByComparatorFactoryUtil.create("CommerceAvailabilityEstimate",
			"uuid", true, "commerceAvailabilityEstimateId", true, "groupId",
			true, "companyId", true, "userId", true, "userName", true,
			"createDate", true, "modifiedDate", true, "title", true,
			"priority", true, "lastPublishDate", true);
	}

	@Test
	public void testFetchByPrimaryKeyExisting() throws Exception {
		CommerceAvailabilityEstimate newCommerceAvailabilityEstimate = addCommerceAvailabilityEstimate();

		CommerceAvailabilityEstimate existingCommerceAvailabilityEstimate = _persistence.fetchByPrimaryKey(newCommerceAvailabilityEstimate.getPrimaryKey());

		Assert.assertEquals(existingCommerceAvailabilityEstimate,
			newCommerceAvailabilityEstimate);
	}

	@Test
	public void testFetchByPrimaryKeyMissing() throws Exception {
		long pk = RandomTestUtil.nextLong();

		CommerceAvailabilityEstimate missingCommerceAvailabilityEstimate = _persistence.fetchByPrimaryKey(pk);

		Assert.assertNull(missingCommerceAvailabilityEstimate);
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereAllPrimaryKeysExist()
		throws Exception {
		CommerceAvailabilityEstimate newCommerceAvailabilityEstimate1 = addCommerceAvailabilityEstimate();
		CommerceAvailabilityEstimate newCommerceAvailabilityEstimate2 = addCommerceAvailabilityEstimate();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newCommerceAvailabilityEstimate1.getPrimaryKey());
		primaryKeys.add(newCommerceAvailabilityEstimate2.getPrimaryKey());

		Map<Serializable, CommerceAvailabilityEstimate> commerceAvailabilityEstimates =
			_persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(2, commerceAvailabilityEstimates.size());
		Assert.assertEquals(newCommerceAvailabilityEstimate1,
			commerceAvailabilityEstimates.get(
				newCommerceAvailabilityEstimate1.getPrimaryKey()));
		Assert.assertEquals(newCommerceAvailabilityEstimate2,
			commerceAvailabilityEstimates.get(
				newCommerceAvailabilityEstimate2.getPrimaryKey()));
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereNoPrimaryKeysExist()
		throws Exception {
		long pk1 = RandomTestUtil.nextLong();

		long pk2 = RandomTestUtil.nextLong();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(pk1);
		primaryKeys.add(pk2);

		Map<Serializable, CommerceAvailabilityEstimate> commerceAvailabilityEstimates =
			_persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertTrue(commerceAvailabilityEstimates.isEmpty());
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereSomePrimaryKeysExist()
		throws Exception {
		CommerceAvailabilityEstimate newCommerceAvailabilityEstimate = addCommerceAvailabilityEstimate();

		long pk = RandomTestUtil.nextLong();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newCommerceAvailabilityEstimate.getPrimaryKey());
		primaryKeys.add(pk);

		Map<Serializable, CommerceAvailabilityEstimate> commerceAvailabilityEstimates =
			_persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(1, commerceAvailabilityEstimates.size());
		Assert.assertEquals(newCommerceAvailabilityEstimate,
			commerceAvailabilityEstimates.get(
				newCommerceAvailabilityEstimate.getPrimaryKey()));
	}

	@Test
	public void testFetchByPrimaryKeysWithNoPrimaryKeys()
		throws Exception {
		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		Map<Serializable, CommerceAvailabilityEstimate> commerceAvailabilityEstimates =
			_persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertTrue(commerceAvailabilityEstimates.isEmpty());
	}

	@Test
	public void testFetchByPrimaryKeysWithOnePrimaryKey()
		throws Exception {
		CommerceAvailabilityEstimate newCommerceAvailabilityEstimate = addCommerceAvailabilityEstimate();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newCommerceAvailabilityEstimate.getPrimaryKey());

		Map<Serializable, CommerceAvailabilityEstimate> commerceAvailabilityEstimates =
			_persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(1, commerceAvailabilityEstimates.size());
		Assert.assertEquals(newCommerceAvailabilityEstimate,
			commerceAvailabilityEstimates.get(
				newCommerceAvailabilityEstimate.getPrimaryKey()));
	}

	@Test
	public void testActionableDynamicQuery() throws Exception {
		final IntegerWrapper count = new IntegerWrapper();

		ActionableDynamicQuery actionableDynamicQuery = CommerceAvailabilityEstimateLocalServiceUtil.getActionableDynamicQuery();

		actionableDynamicQuery.setPerformActionMethod(new ActionableDynamicQuery.PerformActionMethod<CommerceAvailabilityEstimate>() {
				@Override
				public void performAction(
					CommerceAvailabilityEstimate commerceAvailabilityEstimate) {
					Assert.assertNotNull(commerceAvailabilityEstimate);

					count.increment();
				}
			});

		actionableDynamicQuery.performActions();

		Assert.assertEquals(count.getValue(), _persistence.countAll());
	}

	@Test
	public void testDynamicQueryByPrimaryKeyExisting()
		throws Exception {
		CommerceAvailabilityEstimate newCommerceAvailabilityEstimate = addCommerceAvailabilityEstimate();

		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(CommerceAvailabilityEstimate.class,
				_dynamicQueryClassLoader);

		dynamicQuery.add(RestrictionsFactoryUtil.eq(
				"commerceAvailabilityEstimateId",
				newCommerceAvailabilityEstimate.getCommerceAvailabilityEstimateId()));

		List<CommerceAvailabilityEstimate> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(1, result.size());

		CommerceAvailabilityEstimate existingCommerceAvailabilityEstimate = result.get(0);

		Assert.assertEquals(existingCommerceAvailabilityEstimate,
			newCommerceAvailabilityEstimate);
	}

	@Test
	public void testDynamicQueryByPrimaryKeyMissing() throws Exception {
		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(CommerceAvailabilityEstimate.class,
				_dynamicQueryClassLoader);

		dynamicQuery.add(RestrictionsFactoryUtil.eq(
				"commerceAvailabilityEstimateId", RandomTestUtil.nextLong()));

		List<CommerceAvailabilityEstimate> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(0, result.size());
	}

	@Test
	public void testDynamicQueryByProjectionExisting()
		throws Exception {
		CommerceAvailabilityEstimate newCommerceAvailabilityEstimate = addCommerceAvailabilityEstimate();

		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(CommerceAvailabilityEstimate.class,
				_dynamicQueryClassLoader);

		dynamicQuery.setProjection(ProjectionFactoryUtil.property(
				"commerceAvailabilityEstimateId"));

		Object newCommerceAvailabilityEstimateId = newCommerceAvailabilityEstimate.getCommerceAvailabilityEstimateId();

		dynamicQuery.add(RestrictionsFactoryUtil.in(
				"commerceAvailabilityEstimateId",
				new Object[] { newCommerceAvailabilityEstimateId }));

		List<Object> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(1, result.size());

		Object existingCommerceAvailabilityEstimateId = result.get(0);

		Assert.assertEquals(existingCommerceAvailabilityEstimateId,
			newCommerceAvailabilityEstimateId);
	}

	@Test
	public void testDynamicQueryByProjectionMissing() throws Exception {
		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(CommerceAvailabilityEstimate.class,
				_dynamicQueryClassLoader);

		dynamicQuery.setProjection(ProjectionFactoryUtil.property(
				"commerceAvailabilityEstimateId"));

		dynamicQuery.add(RestrictionsFactoryUtil.in(
				"commerceAvailabilityEstimateId",
				new Object[] { RandomTestUtil.nextLong() }));

		List<Object> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(0, result.size());
	}

	@Test
	public void testResetOriginalValues() throws Exception {
		CommerceAvailabilityEstimate newCommerceAvailabilityEstimate = addCommerceAvailabilityEstimate();

		_persistence.clearCache();

		CommerceAvailabilityEstimate existingCommerceAvailabilityEstimate = _persistence.findByPrimaryKey(newCommerceAvailabilityEstimate.getPrimaryKey());

		Assert.assertTrue(Objects.equals(
				existingCommerceAvailabilityEstimate.getUuid(),
				ReflectionTestUtil.invoke(
					existingCommerceAvailabilityEstimate, "getOriginalUuid",
					new Class<?>[0])));
		Assert.assertEquals(Long.valueOf(
				existingCommerceAvailabilityEstimate.getGroupId()),
			ReflectionTestUtil.<Long>invoke(
				existingCommerceAvailabilityEstimate, "getOriginalGroupId",
				new Class<?>[0]));
	}

	protected CommerceAvailabilityEstimate addCommerceAvailabilityEstimate()
		throws Exception {
		long pk = RandomTestUtil.nextLong();

		CommerceAvailabilityEstimate commerceAvailabilityEstimate = _persistence.create(pk);

		commerceAvailabilityEstimate.setUuid(RandomTestUtil.randomString());

		commerceAvailabilityEstimate.setGroupId(RandomTestUtil.nextLong());

		commerceAvailabilityEstimate.setCompanyId(RandomTestUtil.nextLong());

		commerceAvailabilityEstimate.setUserId(RandomTestUtil.nextLong());

		commerceAvailabilityEstimate.setUserName(RandomTestUtil.randomString());

		commerceAvailabilityEstimate.setCreateDate(RandomTestUtil.nextDate());

		commerceAvailabilityEstimate.setModifiedDate(RandomTestUtil.nextDate());

		commerceAvailabilityEstimate.setTitle(RandomTestUtil.randomString());

		commerceAvailabilityEstimate.setPriority(RandomTestUtil.nextDouble());

		commerceAvailabilityEstimate.setLastPublishDate(RandomTestUtil.nextDate());

		_commerceAvailabilityEstimates.add(_persistence.update(
				commerceAvailabilityEstimate));

		return commerceAvailabilityEstimate;
	}

	private List<CommerceAvailabilityEstimate> _commerceAvailabilityEstimates = new ArrayList<CommerceAvailabilityEstimate>();
	private CommerceAvailabilityEstimatePersistence _persistence;
	private ClassLoader _dynamicQueryClassLoader;
}