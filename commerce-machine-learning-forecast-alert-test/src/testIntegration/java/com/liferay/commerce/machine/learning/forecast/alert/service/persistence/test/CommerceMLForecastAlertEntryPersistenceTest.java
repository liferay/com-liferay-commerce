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

package com.liferay.commerce.machine.learning.forecast.alert.service.persistence.test;

import com.liferay.arquillian.extension.junit.bridge.junit.Arquillian;
import com.liferay.commerce.machine.learning.forecast.alert.exception.NoSuchMLForecastAlertEntryException;
import com.liferay.commerce.machine.learning.forecast.alert.model.CommerceMLForecastAlertEntry;
import com.liferay.commerce.machine.learning.forecast.alert.service.CommerceMLForecastAlertEntryLocalServiceUtil;
import com.liferay.commerce.machine.learning.forecast.alert.service.persistence.CommerceMLForecastAlertEntryPersistence;
import com.liferay.commerce.machine.learning.forecast.alert.service.persistence.CommerceMLForecastAlertEntryUtil;
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
public class CommerceMLForecastAlertEntryPersistenceTest {

	@ClassRule
	@Rule
	public static final AggregateTestRule aggregateTestRule =
		new AggregateTestRule(
			new LiferayIntegrationTestRule(), PersistenceTestRule.INSTANCE,
			new TransactionalTestRule(
				Propagation.REQUIRED,
				"com.liferay.commerce.machine.learning.forecast.alert.service"));

	@Before
	public void setUp() {
		_persistence = CommerceMLForecastAlertEntryUtil.getPersistence();

		Class<?> clazz = _persistence.getClass();

		_dynamicQueryClassLoader = clazz.getClassLoader();
	}

	@After
	public void tearDown() throws Exception {
		Iterator<CommerceMLForecastAlertEntry> iterator =
			_commerceMLForecastAlertEntries.iterator();

		while (iterator.hasNext()) {
			_persistence.remove(iterator.next());

			iterator.remove();
		}
	}

	@Test
	public void testCreate() throws Exception {
		long pk = RandomTestUtil.nextLong();

		CommerceMLForecastAlertEntry commerceMLForecastAlertEntry =
			_persistence.create(pk);

		Assert.assertNotNull(commerceMLForecastAlertEntry);

		Assert.assertEquals(commerceMLForecastAlertEntry.getPrimaryKey(), pk);
	}

	@Test
	public void testRemove() throws Exception {
		CommerceMLForecastAlertEntry newCommerceMLForecastAlertEntry =
			addCommerceMLForecastAlertEntry();

		_persistence.remove(newCommerceMLForecastAlertEntry);

		CommerceMLForecastAlertEntry existingCommerceMLForecastAlertEntry =
			_persistence.fetchByPrimaryKey(
				newCommerceMLForecastAlertEntry.getPrimaryKey());

		Assert.assertNull(existingCommerceMLForecastAlertEntry);
	}

	@Test
	public void testUpdateNew() throws Exception {
		addCommerceMLForecastAlertEntry();
	}

	@Test
	public void testUpdateExisting() throws Exception {
		long pk = RandomTestUtil.nextLong();

		CommerceMLForecastAlertEntry newCommerceMLForecastAlertEntry =
			_persistence.create(pk);

		newCommerceMLForecastAlertEntry.setUuid(RandomTestUtil.randomString());

		newCommerceMLForecastAlertEntry.setCompanyId(RandomTestUtil.nextLong());

		newCommerceMLForecastAlertEntry.setUserId(RandomTestUtil.nextLong());

		newCommerceMLForecastAlertEntry.setUserName(
			RandomTestUtil.randomString());

		newCommerceMLForecastAlertEntry.setCreateDate(
			RandomTestUtil.nextDate());

		newCommerceMLForecastAlertEntry.setModifiedDate(
			RandomTestUtil.nextDate());

		newCommerceMLForecastAlertEntry.setCommerceAccountId(
			RandomTestUtil.nextLong());

		newCommerceMLForecastAlertEntry.setActual(RandomTestUtil.nextDouble());

		newCommerceMLForecastAlertEntry.setForecast(
			RandomTestUtil.nextDouble());

		newCommerceMLForecastAlertEntry.setTimestamp(RandomTestUtil.nextDate());

		newCommerceMLForecastAlertEntry.setRelativeChange(
			RandomTestUtil.nextDouble());

		newCommerceMLForecastAlertEntry.setStatus(RandomTestUtil.nextInt());

		_commerceMLForecastAlertEntries.add(
			_persistence.update(newCommerceMLForecastAlertEntry));

		CommerceMLForecastAlertEntry existingCommerceMLForecastAlertEntry =
			_persistence.findByPrimaryKey(
				newCommerceMLForecastAlertEntry.getPrimaryKey());

		Assert.assertEquals(
			existingCommerceMLForecastAlertEntry.getUuid(),
			newCommerceMLForecastAlertEntry.getUuid());
		Assert.assertEquals(
			existingCommerceMLForecastAlertEntry.
				getCommerceMLForecastAlertEntryId(),
			newCommerceMLForecastAlertEntry.
				getCommerceMLForecastAlertEntryId());
		Assert.assertEquals(
			existingCommerceMLForecastAlertEntry.getCompanyId(),
			newCommerceMLForecastAlertEntry.getCompanyId());
		Assert.assertEquals(
			existingCommerceMLForecastAlertEntry.getUserId(),
			newCommerceMLForecastAlertEntry.getUserId());
		Assert.assertEquals(
			existingCommerceMLForecastAlertEntry.getUserName(),
			newCommerceMLForecastAlertEntry.getUserName());
		Assert.assertEquals(
			Time.getShortTimestamp(
				existingCommerceMLForecastAlertEntry.getCreateDate()),
			Time.getShortTimestamp(
				newCommerceMLForecastAlertEntry.getCreateDate()));
		Assert.assertEquals(
			Time.getShortTimestamp(
				existingCommerceMLForecastAlertEntry.getModifiedDate()),
			Time.getShortTimestamp(
				newCommerceMLForecastAlertEntry.getModifiedDate()));
		Assert.assertEquals(
			existingCommerceMLForecastAlertEntry.getCommerceAccountId(),
			newCommerceMLForecastAlertEntry.getCommerceAccountId());
		AssertUtils.assertEquals(
			existingCommerceMLForecastAlertEntry.getActual(),
			newCommerceMLForecastAlertEntry.getActual());
		AssertUtils.assertEquals(
			existingCommerceMLForecastAlertEntry.getForecast(),
			newCommerceMLForecastAlertEntry.getForecast());
		Assert.assertEquals(
			Time.getShortTimestamp(
				existingCommerceMLForecastAlertEntry.getTimestamp()),
			Time.getShortTimestamp(
				newCommerceMLForecastAlertEntry.getTimestamp()));
		AssertUtils.assertEquals(
			existingCommerceMLForecastAlertEntry.getRelativeChange(),
			newCommerceMLForecastAlertEntry.getRelativeChange());
		Assert.assertEquals(
			existingCommerceMLForecastAlertEntry.getStatus(),
			newCommerceMLForecastAlertEntry.getStatus());
	}

	@Test
	public void testCountByUuid() throws Exception {
		_persistence.countByUuid("");

		_persistence.countByUuid("null");

		_persistence.countByUuid((String)null);
	}

	@Test
	public void testCountByUuid_C() throws Exception {
		_persistence.countByUuid_C("", RandomTestUtil.nextLong());

		_persistence.countByUuid_C("null", 0L);

		_persistence.countByUuid_C((String)null, 0L);
	}

	@Test
	public void testCountByC_C_T() throws Exception {
		_persistence.countByC_C_T(
			RandomTestUtil.nextLong(), RandomTestUtil.nextLong(),
			RandomTestUtil.nextDate());

		_persistence.countByC_C_T(0L, 0L, RandomTestUtil.nextDate());
	}

	@Test
	public void testCountByC_C_S() throws Exception {
		_persistence.countByC_C_S(
			RandomTestUtil.nextLong(), RandomTestUtil.nextLong(),
			RandomTestUtil.nextInt());

		_persistence.countByC_C_S(0L, 0L, 0);
	}

	@Test
	public void testCountByC_C_SArrayable() throws Exception {
		_persistence.countByC_C_S(
			RandomTestUtil.nextLong(),
			new long[] {RandomTestUtil.nextLong(), 0L},
			RandomTestUtil.nextInt());
	}

	@Test
	public void testCountByC_C_GtRc_S() throws Exception {
		_persistence.countByC_C_GtRc_S(
			RandomTestUtil.nextLong(), RandomTestUtil.nextLong(),
			RandomTestUtil.nextDouble(), RandomTestUtil.nextInt());

		_persistence.countByC_C_GtRc_S(0L, 0L, 0D, 0);
	}

	@Test
	public void testCountByC_C_GtRc_SArrayable() throws Exception {
		_persistence.countByC_C_GtRc_S(
			RandomTestUtil.nextLong(),
			new long[] {RandomTestUtil.nextLong(), 0L},
			RandomTestUtil.nextDouble(), RandomTestUtil.nextInt());
	}

	@Test
	public void testCountByC_C_LtRc_S() throws Exception {
		_persistence.countByC_C_LtRc_S(
			RandomTestUtil.nextLong(), RandomTestUtil.nextLong(),
			RandomTestUtil.nextDouble(), RandomTestUtil.nextInt());

		_persistence.countByC_C_LtRc_S(0L, 0L, 0D, 0);
	}

	@Test
	public void testCountByC_C_LtRc_SArrayable() throws Exception {
		_persistence.countByC_C_LtRc_S(
			RandomTestUtil.nextLong(),
			new long[] {RandomTestUtil.nextLong(), 0L},
			RandomTestUtil.nextDouble(), RandomTestUtil.nextInt());
	}

	@Test
	public void testFindByPrimaryKeyExisting() throws Exception {
		CommerceMLForecastAlertEntry newCommerceMLForecastAlertEntry =
			addCommerceMLForecastAlertEntry();

		CommerceMLForecastAlertEntry existingCommerceMLForecastAlertEntry =
			_persistence.findByPrimaryKey(
				newCommerceMLForecastAlertEntry.getPrimaryKey());

		Assert.assertEquals(
			existingCommerceMLForecastAlertEntry,
			newCommerceMLForecastAlertEntry);
	}

	@Test(expected = NoSuchMLForecastAlertEntryException.class)
	public void testFindByPrimaryKeyMissing() throws Exception {
		long pk = RandomTestUtil.nextLong();

		_persistence.findByPrimaryKey(pk);
	}

	@Test
	public void testFindAll() throws Exception {
		_persistence.findAll(
			QueryUtil.ALL_POS, QueryUtil.ALL_POS, getOrderByComparator());
	}

	protected OrderByComparator<CommerceMLForecastAlertEntry>
		getOrderByComparator() {

		return OrderByComparatorFactoryUtil.create(
			"CommerceMLForecastAlertEntry", "uuid", true,
			"commerceMLForecastAlertEntryId", true, "companyId", true, "userId",
			true, "userName", true, "createDate", true, "modifiedDate", true,
			"commerceAccountId", true, "actual", true, "forecast", true,
			"timestamp", true, "relativeChange", true, "status", true);
	}

	@Test
	public void testFetchByPrimaryKeyExisting() throws Exception {
		CommerceMLForecastAlertEntry newCommerceMLForecastAlertEntry =
			addCommerceMLForecastAlertEntry();

		CommerceMLForecastAlertEntry existingCommerceMLForecastAlertEntry =
			_persistence.fetchByPrimaryKey(
				newCommerceMLForecastAlertEntry.getPrimaryKey());

		Assert.assertEquals(
			existingCommerceMLForecastAlertEntry,
			newCommerceMLForecastAlertEntry);
	}

	@Test
	public void testFetchByPrimaryKeyMissing() throws Exception {
		long pk = RandomTestUtil.nextLong();

		CommerceMLForecastAlertEntry missingCommerceMLForecastAlertEntry =
			_persistence.fetchByPrimaryKey(pk);

		Assert.assertNull(missingCommerceMLForecastAlertEntry);
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereAllPrimaryKeysExist()
		throws Exception {

		CommerceMLForecastAlertEntry newCommerceMLForecastAlertEntry1 =
			addCommerceMLForecastAlertEntry();
		CommerceMLForecastAlertEntry newCommerceMLForecastAlertEntry2 =
			addCommerceMLForecastAlertEntry();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newCommerceMLForecastAlertEntry1.getPrimaryKey());
		primaryKeys.add(newCommerceMLForecastAlertEntry2.getPrimaryKey());

		Map<Serializable, CommerceMLForecastAlertEntry>
			commerceMLForecastAlertEntries = _persistence.fetchByPrimaryKeys(
				primaryKeys);

		Assert.assertEquals(2, commerceMLForecastAlertEntries.size());
		Assert.assertEquals(
			newCommerceMLForecastAlertEntry1,
			commerceMLForecastAlertEntries.get(
				newCommerceMLForecastAlertEntry1.getPrimaryKey()));
		Assert.assertEquals(
			newCommerceMLForecastAlertEntry2,
			commerceMLForecastAlertEntries.get(
				newCommerceMLForecastAlertEntry2.getPrimaryKey()));
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereNoPrimaryKeysExist()
		throws Exception {

		long pk1 = RandomTestUtil.nextLong();

		long pk2 = RandomTestUtil.nextLong();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(pk1);
		primaryKeys.add(pk2);

		Map<Serializable, CommerceMLForecastAlertEntry>
			commerceMLForecastAlertEntries = _persistence.fetchByPrimaryKeys(
				primaryKeys);

		Assert.assertTrue(commerceMLForecastAlertEntries.isEmpty());
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereSomePrimaryKeysExist()
		throws Exception {

		CommerceMLForecastAlertEntry newCommerceMLForecastAlertEntry =
			addCommerceMLForecastAlertEntry();

		long pk = RandomTestUtil.nextLong();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newCommerceMLForecastAlertEntry.getPrimaryKey());
		primaryKeys.add(pk);

		Map<Serializable, CommerceMLForecastAlertEntry>
			commerceMLForecastAlertEntries = _persistence.fetchByPrimaryKeys(
				primaryKeys);

		Assert.assertEquals(1, commerceMLForecastAlertEntries.size());
		Assert.assertEquals(
			newCommerceMLForecastAlertEntry,
			commerceMLForecastAlertEntries.get(
				newCommerceMLForecastAlertEntry.getPrimaryKey()));
	}

	@Test
	public void testFetchByPrimaryKeysWithNoPrimaryKeys() throws Exception {
		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		Map<Serializable, CommerceMLForecastAlertEntry>
			commerceMLForecastAlertEntries = _persistence.fetchByPrimaryKeys(
				primaryKeys);

		Assert.assertTrue(commerceMLForecastAlertEntries.isEmpty());
	}

	@Test
	public void testFetchByPrimaryKeysWithOnePrimaryKey() throws Exception {
		CommerceMLForecastAlertEntry newCommerceMLForecastAlertEntry =
			addCommerceMLForecastAlertEntry();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newCommerceMLForecastAlertEntry.getPrimaryKey());

		Map<Serializable, CommerceMLForecastAlertEntry>
			commerceMLForecastAlertEntries = _persistence.fetchByPrimaryKeys(
				primaryKeys);

		Assert.assertEquals(1, commerceMLForecastAlertEntries.size());
		Assert.assertEquals(
			newCommerceMLForecastAlertEntry,
			commerceMLForecastAlertEntries.get(
				newCommerceMLForecastAlertEntry.getPrimaryKey()));
	}

	@Test
	public void testActionableDynamicQuery() throws Exception {
		final IntegerWrapper count = new IntegerWrapper();

		ActionableDynamicQuery actionableDynamicQuery =
			CommerceMLForecastAlertEntryLocalServiceUtil.
				getActionableDynamicQuery();

		actionableDynamicQuery.setPerformActionMethod(
			new ActionableDynamicQuery.PerformActionMethod
				<CommerceMLForecastAlertEntry>() {

				@Override
				public void performAction(
					CommerceMLForecastAlertEntry commerceMLForecastAlertEntry) {

					Assert.assertNotNull(commerceMLForecastAlertEntry);

					count.increment();
				}

			});

		actionableDynamicQuery.performActions();

		Assert.assertEquals(count.getValue(), _persistence.countAll());
	}

	@Test
	public void testDynamicQueryByPrimaryKeyExisting() throws Exception {
		CommerceMLForecastAlertEntry newCommerceMLForecastAlertEntry =
			addCommerceMLForecastAlertEntry();

		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(
			CommerceMLForecastAlertEntry.class, _dynamicQueryClassLoader);

		dynamicQuery.add(
			RestrictionsFactoryUtil.eq(
				"commerceMLForecastAlertEntryId",
				newCommerceMLForecastAlertEntry.
					getCommerceMLForecastAlertEntryId()));

		List<CommerceMLForecastAlertEntry> result =
			_persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(1, result.size());

		CommerceMLForecastAlertEntry existingCommerceMLForecastAlertEntry =
			result.get(0);

		Assert.assertEquals(
			existingCommerceMLForecastAlertEntry,
			newCommerceMLForecastAlertEntry);
	}

	@Test
	public void testDynamicQueryByPrimaryKeyMissing() throws Exception {
		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(
			CommerceMLForecastAlertEntry.class, _dynamicQueryClassLoader);

		dynamicQuery.add(
			RestrictionsFactoryUtil.eq(
				"commerceMLForecastAlertEntryId", RandomTestUtil.nextLong()));

		List<CommerceMLForecastAlertEntry> result =
			_persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(0, result.size());
	}

	@Test
	public void testDynamicQueryByProjectionExisting() throws Exception {
		CommerceMLForecastAlertEntry newCommerceMLForecastAlertEntry =
			addCommerceMLForecastAlertEntry();

		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(
			CommerceMLForecastAlertEntry.class, _dynamicQueryClassLoader);

		dynamicQuery.setProjection(
			ProjectionFactoryUtil.property("commerceMLForecastAlertEntryId"));

		Object newCommerceMLForecastAlertEntryId =
			newCommerceMLForecastAlertEntry.getCommerceMLForecastAlertEntryId();

		dynamicQuery.add(
			RestrictionsFactoryUtil.in(
				"commerceMLForecastAlertEntryId",
				new Object[] {newCommerceMLForecastAlertEntryId}));

		List<Object> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(1, result.size());

		Object existingCommerceMLForecastAlertEntryId = result.get(0);

		Assert.assertEquals(
			existingCommerceMLForecastAlertEntryId,
			newCommerceMLForecastAlertEntryId);
	}

	@Test
	public void testDynamicQueryByProjectionMissing() throws Exception {
		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(
			CommerceMLForecastAlertEntry.class, _dynamicQueryClassLoader);

		dynamicQuery.setProjection(
			ProjectionFactoryUtil.property("commerceMLForecastAlertEntryId"));

		dynamicQuery.add(
			RestrictionsFactoryUtil.in(
				"commerceMLForecastAlertEntryId",
				new Object[] {RandomTestUtil.nextLong()}));

		List<Object> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(0, result.size());
	}

	@Test
	public void testResetOriginalValues() throws Exception {
		CommerceMLForecastAlertEntry newCommerceMLForecastAlertEntry =
			addCommerceMLForecastAlertEntry();

		_persistence.clearCache();

		CommerceMLForecastAlertEntry existingCommerceMLForecastAlertEntry =
			_persistence.findByPrimaryKey(
				newCommerceMLForecastAlertEntry.getPrimaryKey());

		Assert.assertEquals(
			Long.valueOf(existingCommerceMLForecastAlertEntry.getCompanyId()),
			ReflectionTestUtil.<Long>invoke(
				existingCommerceMLForecastAlertEntry, "getOriginalCompanyId",
				new Class<?>[0]));
		Assert.assertEquals(
			Long.valueOf(
				existingCommerceMLForecastAlertEntry.getCommerceAccountId()),
			ReflectionTestUtil.<Long>invoke(
				existingCommerceMLForecastAlertEntry,
				"getOriginalCommerceAccountId", new Class<?>[0]));
		Assert.assertTrue(
			Objects.equals(
				existingCommerceMLForecastAlertEntry.getTimestamp(),
				ReflectionTestUtil.invoke(
					existingCommerceMLForecastAlertEntry,
					"getOriginalTimestamp", new Class<?>[0])));
	}

	protected CommerceMLForecastAlertEntry addCommerceMLForecastAlertEntry()
		throws Exception {

		long pk = RandomTestUtil.nextLong();

		CommerceMLForecastAlertEntry commerceMLForecastAlertEntry =
			_persistence.create(pk);

		commerceMLForecastAlertEntry.setUuid(RandomTestUtil.randomString());

		commerceMLForecastAlertEntry.setCompanyId(RandomTestUtil.nextLong());

		commerceMLForecastAlertEntry.setUserId(RandomTestUtil.nextLong());

		commerceMLForecastAlertEntry.setUserName(RandomTestUtil.randomString());

		commerceMLForecastAlertEntry.setCreateDate(RandomTestUtil.nextDate());

		commerceMLForecastAlertEntry.setModifiedDate(RandomTestUtil.nextDate());

		commerceMLForecastAlertEntry.setCommerceAccountId(
			RandomTestUtil.nextLong());

		commerceMLForecastAlertEntry.setActual(RandomTestUtil.nextDouble());

		commerceMLForecastAlertEntry.setForecast(RandomTestUtil.nextDouble());

		commerceMLForecastAlertEntry.setTimestamp(RandomTestUtil.nextDate());

		commerceMLForecastAlertEntry.setRelativeChange(
			RandomTestUtil.nextDouble());

		commerceMLForecastAlertEntry.setStatus(RandomTestUtil.nextInt());

		_commerceMLForecastAlertEntries.add(
			_persistence.update(commerceMLForecastAlertEntry));

		return commerceMLForecastAlertEntry;
	}

	private List<CommerceMLForecastAlertEntry> _commerceMLForecastAlertEntries =
		new ArrayList<CommerceMLForecastAlertEntry>();
	private CommerceMLForecastAlertEntryPersistence _persistence;
	private ClassLoader _dynamicQueryClassLoader;

}