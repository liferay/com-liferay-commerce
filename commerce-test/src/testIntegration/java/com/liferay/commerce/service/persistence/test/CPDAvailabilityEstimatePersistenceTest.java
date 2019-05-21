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
import com.liferay.commerce.exception.NoSuchCPDAvailabilityEstimateException;
import com.liferay.commerce.model.CPDAvailabilityEstimate;
import com.liferay.commerce.service.CPDAvailabilityEstimateLocalServiceUtil;
import com.liferay.commerce.service.persistence.CPDAvailabilityEstimatePersistence;
import com.liferay.commerce.service.persistence.CPDAvailabilityEstimateUtil;
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

import java.io.Serializable;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.ClassRule;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 * @generated
 */
@RunWith(Arquillian.class)
public class CPDAvailabilityEstimatePersistenceTest {

	@ClassRule
	@Rule
	public static final AggregateTestRule aggregateTestRule =
		new AggregateTestRule(
			new LiferayIntegrationTestRule(), PersistenceTestRule.INSTANCE,
			new TransactionalTestRule(
				Propagation.REQUIRED, "com.liferay.commerce.service"));

	@Before
	public void setUp() {
		_persistence = CPDAvailabilityEstimateUtil.getPersistence();

		Class<?> clazz = _persistence.getClass();

		_dynamicQueryClassLoader = clazz.getClassLoader();
	}

	@After
	public void tearDown() throws Exception {
		Iterator<CPDAvailabilityEstimate> iterator =
			_cpdAvailabilityEstimates.iterator();

		while (iterator.hasNext()) {
			_persistence.remove(iterator.next());

			iterator.remove();
		}
	}

	@Test
	public void testCreate() throws Exception {
		long pk = RandomTestUtil.nextLong();

		CPDAvailabilityEstimate cpdAvailabilityEstimate = _persistence.create(
			pk);

		Assert.assertNotNull(cpdAvailabilityEstimate);

		Assert.assertEquals(cpdAvailabilityEstimate.getPrimaryKey(), pk);
	}

	@Test
	public void testRemove() throws Exception {
		CPDAvailabilityEstimate newCPDAvailabilityEstimate =
			addCPDAvailabilityEstimate();

		_persistence.remove(newCPDAvailabilityEstimate);

		CPDAvailabilityEstimate existingCPDAvailabilityEstimate =
			_persistence.fetchByPrimaryKey(
				newCPDAvailabilityEstimate.getPrimaryKey());

		Assert.assertNull(existingCPDAvailabilityEstimate);
	}

	@Test
	public void testUpdateNew() throws Exception {
		addCPDAvailabilityEstimate();
	}

	@Test
	public void testUpdateExisting() throws Exception {
		long pk = RandomTestUtil.nextLong();

		CPDAvailabilityEstimate newCPDAvailabilityEstimate =
			_persistence.create(pk);

		newCPDAvailabilityEstimate.setUuid(RandomTestUtil.randomString());

		newCPDAvailabilityEstimate.setGroupId(RandomTestUtil.nextLong());

		newCPDAvailabilityEstimate.setCompanyId(RandomTestUtil.nextLong());

		newCPDAvailabilityEstimate.setUserId(RandomTestUtil.nextLong());

		newCPDAvailabilityEstimate.setUserName(RandomTestUtil.randomString());

		newCPDAvailabilityEstimate.setCreateDate(RandomTestUtil.nextDate());

		newCPDAvailabilityEstimate.setModifiedDate(RandomTestUtil.nextDate());

		newCPDAvailabilityEstimate.setCommerceAvailabilityEstimateId(
			RandomTestUtil.nextLong());

		newCPDAvailabilityEstimate.setCProductId(RandomTestUtil.nextLong());

		newCPDAvailabilityEstimate.setLastPublishDate(
			RandomTestUtil.nextDate());

		_cpdAvailabilityEstimates.add(
			_persistence.update(newCPDAvailabilityEstimate));

		CPDAvailabilityEstimate existingCPDAvailabilityEstimate =
			_persistence.findByPrimaryKey(
				newCPDAvailabilityEstimate.getPrimaryKey());

		Assert.assertEquals(
			existingCPDAvailabilityEstimate.getUuid(),
			newCPDAvailabilityEstimate.getUuid());
		Assert.assertEquals(
			existingCPDAvailabilityEstimate.getCPDAvailabilityEstimateId(),
			newCPDAvailabilityEstimate.getCPDAvailabilityEstimateId());
		Assert.assertEquals(
			existingCPDAvailabilityEstimate.getGroupId(),
			newCPDAvailabilityEstimate.getGroupId());
		Assert.assertEquals(
			existingCPDAvailabilityEstimate.getCompanyId(),
			newCPDAvailabilityEstimate.getCompanyId());
		Assert.assertEquals(
			existingCPDAvailabilityEstimate.getUserId(),
			newCPDAvailabilityEstimate.getUserId());
		Assert.assertEquals(
			existingCPDAvailabilityEstimate.getUserName(),
			newCPDAvailabilityEstimate.getUserName());
		Assert.assertEquals(
			Time.getShortTimestamp(
				existingCPDAvailabilityEstimate.getCreateDate()),
			Time.getShortTimestamp(newCPDAvailabilityEstimate.getCreateDate()));
		Assert.assertEquals(
			Time.getShortTimestamp(
				existingCPDAvailabilityEstimate.getModifiedDate()),
			Time.getShortTimestamp(
				newCPDAvailabilityEstimate.getModifiedDate()));
		Assert.assertEquals(
			existingCPDAvailabilityEstimate.getCommerceAvailabilityEstimateId(),
			newCPDAvailabilityEstimate.getCommerceAvailabilityEstimateId());
		Assert.assertEquals(
			existingCPDAvailabilityEstimate.getCProductId(),
			newCPDAvailabilityEstimate.getCProductId());
		Assert.assertEquals(
			Time.getShortTimestamp(
				existingCPDAvailabilityEstimate.getLastPublishDate()),
			Time.getShortTimestamp(
				newCPDAvailabilityEstimate.getLastPublishDate()));
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
	public void testCountByCommerceAvailabilityEstimateId() throws Exception {
		_persistence.countByCommerceAvailabilityEstimateId(
			RandomTestUtil.nextLong());

		_persistence.countByCommerceAvailabilityEstimateId(0L);
	}

	@Test
	public void testCountByCProductId() throws Exception {
		_persistence.countByCProductId(RandomTestUtil.nextLong());

		_persistence.countByCProductId(0L);
	}

	@Test
	public void testFindByPrimaryKeyExisting() throws Exception {
		CPDAvailabilityEstimate newCPDAvailabilityEstimate =
			addCPDAvailabilityEstimate();

		CPDAvailabilityEstimate existingCPDAvailabilityEstimate =
			_persistence.findByPrimaryKey(
				newCPDAvailabilityEstimate.getPrimaryKey());

		Assert.assertEquals(
			existingCPDAvailabilityEstimate, newCPDAvailabilityEstimate);
	}

	@Test(expected = NoSuchCPDAvailabilityEstimateException.class)
	public void testFindByPrimaryKeyMissing() throws Exception {
		long pk = RandomTestUtil.nextLong();

		_persistence.findByPrimaryKey(pk);
	}

	@Test
	public void testFindAll() throws Exception {
		_persistence.findAll(
			QueryUtil.ALL_POS, QueryUtil.ALL_POS, getOrderByComparator());
	}

	protected OrderByComparator<CPDAvailabilityEstimate>
		getOrderByComparator() {

		return OrderByComparatorFactoryUtil.create(
			"CPDAvailabilityEstimate", "uuid", true,
			"CPDAvailabilityEstimateId", true, "groupId", true, "companyId",
			true, "userId", true, "userName", true, "createDate", true,
			"modifiedDate", true, "commerceAvailabilityEstimateId", true,
			"CProductId", true, "lastPublishDate", true);
	}

	@Test
	public void testFetchByPrimaryKeyExisting() throws Exception {
		CPDAvailabilityEstimate newCPDAvailabilityEstimate =
			addCPDAvailabilityEstimate();

		CPDAvailabilityEstimate existingCPDAvailabilityEstimate =
			_persistence.fetchByPrimaryKey(
				newCPDAvailabilityEstimate.getPrimaryKey());

		Assert.assertEquals(
			existingCPDAvailabilityEstimate, newCPDAvailabilityEstimate);
	}

	@Test
	public void testFetchByPrimaryKeyMissing() throws Exception {
		long pk = RandomTestUtil.nextLong();

		CPDAvailabilityEstimate missingCPDAvailabilityEstimate =
			_persistence.fetchByPrimaryKey(pk);

		Assert.assertNull(missingCPDAvailabilityEstimate);
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereAllPrimaryKeysExist()
		throws Exception {

		CPDAvailabilityEstimate newCPDAvailabilityEstimate1 =
			addCPDAvailabilityEstimate();
		CPDAvailabilityEstimate newCPDAvailabilityEstimate2 =
			addCPDAvailabilityEstimate();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newCPDAvailabilityEstimate1.getPrimaryKey());
		primaryKeys.add(newCPDAvailabilityEstimate2.getPrimaryKey());

		Map<Serializable, CPDAvailabilityEstimate> cpdAvailabilityEstimates =
			_persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(2, cpdAvailabilityEstimates.size());
		Assert.assertEquals(
			newCPDAvailabilityEstimate1,
			cpdAvailabilityEstimates.get(
				newCPDAvailabilityEstimate1.getPrimaryKey()));
		Assert.assertEquals(
			newCPDAvailabilityEstimate2,
			cpdAvailabilityEstimates.get(
				newCPDAvailabilityEstimate2.getPrimaryKey()));
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereNoPrimaryKeysExist()
		throws Exception {

		long pk1 = RandomTestUtil.nextLong();

		long pk2 = RandomTestUtil.nextLong();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(pk1);
		primaryKeys.add(pk2);

		Map<Serializable, CPDAvailabilityEstimate> cpdAvailabilityEstimates =
			_persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertTrue(cpdAvailabilityEstimates.isEmpty());
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereSomePrimaryKeysExist()
		throws Exception {

		CPDAvailabilityEstimate newCPDAvailabilityEstimate =
			addCPDAvailabilityEstimate();

		long pk = RandomTestUtil.nextLong();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newCPDAvailabilityEstimate.getPrimaryKey());
		primaryKeys.add(pk);

		Map<Serializable, CPDAvailabilityEstimate> cpdAvailabilityEstimates =
			_persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(1, cpdAvailabilityEstimates.size());
		Assert.assertEquals(
			newCPDAvailabilityEstimate,
			cpdAvailabilityEstimates.get(
				newCPDAvailabilityEstimate.getPrimaryKey()));
	}

	@Test
	public void testFetchByPrimaryKeysWithNoPrimaryKeys() throws Exception {
		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		Map<Serializable, CPDAvailabilityEstimate> cpdAvailabilityEstimates =
			_persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertTrue(cpdAvailabilityEstimates.isEmpty());
	}

	@Test
	public void testFetchByPrimaryKeysWithOnePrimaryKey() throws Exception {
		CPDAvailabilityEstimate newCPDAvailabilityEstimate =
			addCPDAvailabilityEstimate();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newCPDAvailabilityEstimate.getPrimaryKey());

		Map<Serializable, CPDAvailabilityEstimate> cpdAvailabilityEstimates =
			_persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(1, cpdAvailabilityEstimates.size());
		Assert.assertEquals(
			newCPDAvailabilityEstimate,
			cpdAvailabilityEstimates.get(
				newCPDAvailabilityEstimate.getPrimaryKey()));
	}

	@Test
	public void testActionableDynamicQuery() throws Exception {
		final IntegerWrapper count = new IntegerWrapper();

		ActionableDynamicQuery actionableDynamicQuery =
			CPDAvailabilityEstimateLocalServiceUtil.getActionableDynamicQuery();

		actionableDynamicQuery.setPerformActionMethod(
			new ActionableDynamicQuery.PerformActionMethod
				<CPDAvailabilityEstimate>() {

				@Override
				public void performAction(
					CPDAvailabilityEstimate cpdAvailabilityEstimate) {

					Assert.assertNotNull(cpdAvailabilityEstimate);

					count.increment();
				}

			});

		actionableDynamicQuery.performActions();

		Assert.assertEquals(count.getValue(), _persistence.countAll());
	}

	@Test
	public void testDynamicQueryByPrimaryKeyExisting() throws Exception {
		CPDAvailabilityEstimate newCPDAvailabilityEstimate =
			addCPDAvailabilityEstimate();

		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(
			CPDAvailabilityEstimate.class, _dynamicQueryClassLoader);

		dynamicQuery.add(
			RestrictionsFactoryUtil.eq(
				"CPDAvailabilityEstimateId",
				newCPDAvailabilityEstimate.getCPDAvailabilityEstimateId()));

		List<CPDAvailabilityEstimate> result =
			_persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(1, result.size());

		CPDAvailabilityEstimate existingCPDAvailabilityEstimate = result.get(0);

		Assert.assertEquals(
			existingCPDAvailabilityEstimate, newCPDAvailabilityEstimate);
	}

	@Test
	public void testDynamicQueryByPrimaryKeyMissing() throws Exception {
		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(
			CPDAvailabilityEstimate.class, _dynamicQueryClassLoader);

		dynamicQuery.add(
			RestrictionsFactoryUtil.eq(
				"CPDAvailabilityEstimateId", RandomTestUtil.nextLong()));

		List<CPDAvailabilityEstimate> result =
			_persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(0, result.size());
	}

	@Test
	public void testDynamicQueryByProjectionExisting() throws Exception {
		CPDAvailabilityEstimate newCPDAvailabilityEstimate =
			addCPDAvailabilityEstimate();

		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(
			CPDAvailabilityEstimate.class, _dynamicQueryClassLoader);

		dynamicQuery.setProjection(
			ProjectionFactoryUtil.property("CPDAvailabilityEstimateId"));

		Object newCPDAvailabilityEstimateId =
			newCPDAvailabilityEstimate.getCPDAvailabilityEstimateId();

		dynamicQuery.add(
			RestrictionsFactoryUtil.in(
				"CPDAvailabilityEstimateId",
				new Object[] {newCPDAvailabilityEstimateId}));

		List<Object> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(1, result.size());

		Object existingCPDAvailabilityEstimateId = result.get(0);

		Assert.assertEquals(
			existingCPDAvailabilityEstimateId, newCPDAvailabilityEstimateId);
	}

	@Test
	public void testDynamicQueryByProjectionMissing() throws Exception {
		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(
			CPDAvailabilityEstimate.class, _dynamicQueryClassLoader);

		dynamicQuery.setProjection(
			ProjectionFactoryUtil.property("CPDAvailabilityEstimateId"));

		dynamicQuery.add(
			RestrictionsFactoryUtil.in(
				"CPDAvailabilityEstimateId",
				new Object[] {RandomTestUtil.nextLong()}));

		List<Object> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(0, result.size());
	}

	@Test
	public void testResetOriginalValues() throws Exception {
		CPDAvailabilityEstimate newCPDAvailabilityEstimate =
			addCPDAvailabilityEstimate();

		_persistence.clearCache();

		CPDAvailabilityEstimate existingCPDAvailabilityEstimate =
			_persistence.findByPrimaryKey(
				newCPDAvailabilityEstimate.getPrimaryKey());

		Assert.assertTrue(
			Objects.equals(
				existingCPDAvailabilityEstimate.getUuid(),
				ReflectionTestUtil.invoke(
					existingCPDAvailabilityEstimate, "getOriginalUuid",
					new Class<?>[0])));
		Assert.assertEquals(
			Long.valueOf(existingCPDAvailabilityEstimate.getGroupId()),
			ReflectionTestUtil.<Long>invoke(
				existingCPDAvailabilityEstimate, "getOriginalGroupId",
				new Class<?>[0]));

		Assert.assertEquals(
			Long.valueOf(existingCPDAvailabilityEstimate.getCProductId()),
			ReflectionTestUtil.<Long>invoke(
				existingCPDAvailabilityEstimate, "getOriginalCProductId",
				new Class<?>[0]));
	}

	protected CPDAvailabilityEstimate addCPDAvailabilityEstimate()
		throws Exception {

		long pk = RandomTestUtil.nextLong();

		CPDAvailabilityEstimate cpdAvailabilityEstimate = _persistence.create(
			pk);

		cpdAvailabilityEstimate.setUuid(RandomTestUtil.randomString());

		cpdAvailabilityEstimate.setGroupId(RandomTestUtil.nextLong());

		cpdAvailabilityEstimate.setCompanyId(RandomTestUtil.nextLong());

		cpdAvailabilityEstimate.setUserId(RandomTestUtil.nextLong());

		cpdAvailabilityEstimate.setUserName(RandomTestUtil.randomString());

		cpdAvailabilityEstimate.setCreateDate(RandomTestUtil.nextDate());

		cpdAvailabilityEstimate.setModifiedDate(RandomTestUtil.nextDate());

		cpdAvailabilityEstimate.setCommerceAvailabilityEstimateId(
			RandomTestUtil.nextLong());

		cpdAvailabilityEstimate.setCProductId(RandomTestUtil.nextLong());

		cpdAvailabilityEstimate.setLastPublishDate(RandomTestUtil.nextDate());

		_cpdAvailabilityEstimates.add(
			_persistence.update(cpdAvailabilityEstimate));

		return cpdAvailabilityEstimate;
	}

	private List<CPDAvailabilityEstimate> _cpdAvailabilityEstimates =
		new ArrayList<CPDAvailabilityEstimate>();
	private CPDAvailabilityEstimatePersistence _persistence;
	private ClassLoader _dynamicQueryClassLoader;

}