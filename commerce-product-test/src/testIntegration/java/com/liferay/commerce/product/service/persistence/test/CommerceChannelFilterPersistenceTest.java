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

import com.liferay.commerce.product.exception.NoSuchChannelFilterException;
import com.liferay.commerce.product.model.CommerceChannelFilter;
import com.liferay.commerce.product.service.CommerceChannelFilterLocalServiceUtil;
import com.liferay.commerce.product.service.persistence.CommerceChannelFilterPersistence;
import com.liferay.commerce.product.service.persistence.CommerceChannelFilterUtil;

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
public class CommerceChannelFilterPersistenceTest {
	@ClassRule
	@Rule
	public static final AggregateTestRule aggregateTestRule = new AggregateTestRule(new LiferayIntegrationTestRule(),
			PersistenceTestRule.INSTANCE,
			new TransactionalTestRule(Propagation.REQUIRED,
				"com.liferay.commerce.product.service"));

	@Before
	public void setUp() {
		_persistence = CommerceChannelFilterUtil.getPersistence();

		Class<?> clazz = _persistence.getClass();

		_dynamicQueryClassLoader = clazz.getClassLoader();
	}

	@After
	public void tearDown() throws Exception {
		Iterator<CommerceChannelFilter> iterator = _commerceChannelFilters.iterator();

		while (iterator.hasNext()) {
			_persistence.remove(iterator.next());

			iterator.remove();
		}
	}

	@Test
	public void testCreate() throws Exception {
		long pk = RandomTestUtil.nextLong();

		CommerceChannelFilter commerceChannelFilter = _persistence.create(pk);

		Assert.assertNotNull(commerceChannelFilter);

		Assert.assertEquals(commerceChannelFilter.getPrimaryKey(), pk);
	}

	@Test
	public void testRemove() throws Exception {
		CommerceChannelFilter newCommerceChannelFilter = addCommerceChannelFilter();

		_persistence.remove(newCommerceChannelFilter);

		CommerceChannelFilter existingCommerceChannelFilter = _persistence.fetchByPrimaryKey(newCommerceChannelFilter.getPrimaryKey());

		Assert.assertNull(existingCommerceChannelFilter);
	}

	@Test
	public void testUpdateNew() throws Exception {
		addCommerceChannelFilter();
	}

	@Test
	public void testUpdateExisting() throws Exception {
		long pk = RandomTestUtil.nextLong();

		CommerceChannelFilter newCommerceChannelFilter = _persistence.create(pk);

		newCommerceChannelFilter.setCompanyId(RandomTestUtil.nextLong());

		newCommerceChannelFilter.setUserId(RandomTestUtil.nextLong());

		newCommerceChannelFilter.setUserName(RandomTestUtil.randomString());

		newCommerceChannelFilter.setCreateDate(RandomTestUtil.nextDate());

		newCommerceChannelFilter.setModifiedDate(RandomTestUtil.nextDate());

		newCommerceChannelFilter.setCommerceChannelId(RandomTestUtil.nextLong());

		newCommerceChannelFilter.setType(RandomTestUtil.randomString());

		newCommerceChannelFilter.setTypeSettings(RandomTestUtil.randomString());

		_commerceChannelFilters.add(_persistence.update(
				newCommerceChannelFilter));

		CommerceChannelFilter existingCommerceChannelFilter = _persistence.findByPrimaryKey(newCommerceChannelFilter.getPrimaryKey());

		Assert.assertEquals(existingCommerceChannelFilter.getCommerceChannelFilterId(),
			newCommerceChannelFilter.getCommerceChannelFilterId());
		Assert.assertEquals(existingCommerceChannelFilter.getCompanyId(),
			newCommerceChannelFilter.getCompanyId());
		Assert.assertEquals(existingCommerceChannelFilter.getUserId(),
			newCommerceChannelFilter.getUserId());
		Assert.assertEquals(existingCommerceChannelFilter.getUserName(),
			newCommerceChannelFilter.getUserName());
		Assert.assertEquals(Time.getShortTimestamp(
				existingCommerceChannelFilter.getCreateDate()),
			Time.getShortTimestamp(newCommerceChannelFilter.getCreateDate()));
		Assert.assertEquals(Time.getShortTimestamp(
				existingCommerceChannelFilter.getModifiedDate()),
			Time.getShortTimestamp(newCommerceChannelFilter.getModifiedDate()));
		Assert.assertEquals(existingCommerceChannelFilter.getCommerceChannelId(),
			newCommerceChannelFilter.getCommerceChannelId());
		Assert.assertEquals(existingCommerceChannelFilter.getType(),
			newCommerceChannelFilter.getType());
		Assert.assertEquals(existingCommerceChannelFilter.getTypeSettings(),
			newCommerceChannelFilter.getTypeSettings());
	}

	@Test
	public void testCountByCommerceChannelId() throws Exception {
		_persistence.countByCommerceChannelId(RandomTestUtil.nextLong());

		_persistence.countByCommerceChannelId(0L);
	}

	@Test
	public void testFindByPrimaryKeyExisting() throws Exception {
		CommerceChannelFilter newCommerceChannelFilter = addCommerceChannelFilter();

		CommerceChannelFilter existingCommerceChannelFilter = _persistence.findByPrimaryKey(newCommerceChannelFilter.getPrimaryKey());

		Assert.assertEquals(existingCommerceChannelFilter,
			newCommerceChannelFilter);
	}

	@Test(expected = NoSuchChannelFilterException.class)
	public void testFindByPrimaryKeyMissing() throws Exception {
		long pk = RandomTestUtil.nextLong();

		_persistence.findByPrimaryKey(pk);
	}

	@Test
	public void testFindAll() throws Exception {
		_persistence.findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS,
			getOrderByComparator());
	}

	protected OrderByComparator<CommerceChannelFilter> getOrderByComparator() {
		return OrderByComparatorFactoryUtil.create("CommerceChannelFilter",
			"commerceChannelFilterId", true, "companyId", true, "userId", true,
			"userName", true, "createDate", true, "modifiedDate", true,
			"commerceChannelId", true, "type", true, "typeSettings", true);
	}

	@Test
	public void testFetchByPrimaryKeyExisting() throws Exception {
		CommerceChannelFilter newCommerceChannelFilter = addCommerceChannelFilter();

		CommerceChannelFilter existingCommerceChannelFilter = _persistence.fetchByPrimaryKey(newCommerceChannelFilter.getPrimaryKey());

		Assert.assertEquals(existingCommerceChannelFilter,
			newCommerceChannelFilter);
	}

	@Test
	public void testFetchByPrimaryKeyMissing() throws Exception {
		long pk = RandomTestUtil.nextLong();

		CommerceChannelFilter missingCommerceChannelFilter = _persistence.fetchByPrimaryKey(pk);

		Assert.assertNull(missingCommerceChannelFilter);
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereAllPrimaryKeysExist()
		throws Exception {
		CommerceChannelFilter newCommerceChannelFilter1 = addCommerceChannelFilter();
		CommerceChannelFilter newCommerceChannelFilter2 = addCommerceChannelFilter();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newCommerceChannelFilter1.getPrimaryKey());
		primaryKeys.add(newCommerceChannelFilter2.getPrimaryKey());

		Map<Serializable, CommerceChannelFilter> commerceChannelFilters = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(2, commerceChannelFilters.size());
		Assert.assertEquals(newCommerceChannelFilter1,
			commerceChannelFilters.get(
				newCommerceChannelFilter1.getPrimaryKey()));
		Assert.assertEquals(newCommerceChannelFilter2,
			commerceChannelFilters.get(
				newCommerceChannelFilter2.getPrimaryKey()));
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereNoPrimaryKeysExist()
		throws Exception {
		long pk1 = RandomTestUtil.nextLong();

		long pk2 = RandomTestUtil.nextLong();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(pk1);
		primaryKeys.add(pk2);

		Map<Serializable, CommerceChannelFilter> commerceChannelFilters = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertTrue(commerceChannelFilters.isEmpty());
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereSomePrimaryKeysExist()
		throws Exception {
		CommerceChannelFilter newCommerceChannelFilter = addCommerceChannelFilter();

		long pk = RandomTestUtil.nextLong();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newCommerceChannelFilter.getPrimaryKey());
		primaryKeys.add(pk);

		Map<Serializable, CommerceChannelFilter> commerceChannelFilters = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(1, commerceChannelFilters.size());
		Assert.assertEquals(newCommerceChannelFilter,
			commerceChannelFilters.get(newCommerceChannelFilter.getPrimaryKey()));
	}

	@Test
	public void testFetchByPrimaryKeysWithNoPrimaryKeys()
		throws Exception {
		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		Map<Serializable, CommerceChannelFilter> commerceChannelFilters = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertTrue(commerceChannelFilters.isEmpty());
	}

	@Test
	public void testFetchByPrimaryKeysWithOnePrimaryKey()
		throws Exception {
		CommerceChannelFilter newCommerceChannelFilter = addCommerceChannelFilter();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newCommerceChannelFilter.getPrimaryKey());

		Map<Serializable, CommerceChannelFilter> commerceChannelFilters = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(1, commerceChannelFilters.size());
		Assert.assertEquals(newCommerceChannelFilter,
			commerceChannelFilters.get(newCommerceChannelFilter.getPrimaryKey()));
	}

	@Test
	public void testActionableDynamicQuery() throws Exception {
		final IntegerWrapper count = new IntegerWrapper();

		ActionableDynamicQuery actionableDynamicQuery = CommerceChannelFilterLocalServiceUtil.getActionableDynamicQuery();

		actionableDynamicQuery.setPerformActionMethod(new ActionableDynamicQuery.PerformActionMethod<CommerceChannelFilter>() {
				@Override
				public void performAction(
					CommerceChannelFilter commerceChannelFilter) {
					Assert.assertNotNull(commerceChannelFilter);

					count.increment();
				}
			});

		actionableDynamicQuery.performActions();

		Assert.assertEquals(count.getValue(), _persistence.countAll());
	}

	@Test
	public void testDynamicQueryByPrimaryKeyExisting()
		throws Exception {
		CommerceChannelFilter newCommerceChannelFilter = addCommerceChannelFilter();

		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(CommerceChannelFilter.class,
				_dynamicQueryClassLoader);

		dynamicQuery.add(RestrictionsFactoryUtil.eq("commerceChannelFilterId",
				newCommerceChannelFilter.getCommerceChannelFilterId()));

		List<CommerceChannelFilter> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(1, result.size());

		CommerceChannelFilter existingCommerceChannelFilter = result.get(0);

		Assert.assertEquals(existingCommerceChannelFilter,
			newCommerceChannelFilter);
	}

	@Test
	public void testDynamicQueryByPrimaryKeyMissing() throws Exception {
		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(CommerceChannelFilter.class,
				_dynamicQueryClassLoader);

		dynamicQuery.add(RestrictionsFactoryUtil.eq("commerceChannelFilterId",
				RandomTestUtil.nextLong()));

		List<CommerceChannelFilter> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(0, result.size());
	}

	@Test
	public void testDynamicQueryByProjectionExisting()
		throws Exception {
		CommerceChannelFilter newCommerceChannelFilter = addCommerceChannelFilter();

		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(CommerceChannelFilter.class,
				_dynamicQueryClassLoader);

		dynamicQuery.setProjection(ProjectionFactoryUtil.property(
				"commerceChannelFilterId"));

		Object newCommerceChannelFilterId = newCommerceChannelFilter.getCommerceChannelFilterId();

		dynamicQuery.add(RestrictionsFactoryUtil.in("commerceChannelFilterId",
				new Object[] { newCommerceChannelFilterId }));

		List<Object> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(1, result.size());

		Object existingCommerceChannelFilterId = result.get(0);

		Assert.assertEquals(existingCommerceChannelFilterId,
			newCommerceChannelFilterId);
	}

	@Test
	public void testDynamicQueryByProjectionMissing() throws Exception {
		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(CommerceChannelFilter.class,
				_dynamicQueryClassLoader);

		dynamicQuery.setProjection(ProjectionFactoryUtil.property(
				"commerceChannelFilterId"));

		dynamicQuery.add(RestrictionsFactoryUtil.in("commerceChannelFilterId",
				new Object[] { RandomTestUtil.nextLong() }));

		List<Object> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(0, result.size());
	}

	protected CommerceChannelFilter addCommerceChannelFilter()
		throws Exception {
		long pk = RandomTestUtil.nextLong();

		CommerceChannelFilter commerceChannelFilter = _persistence.create(pk);

		commerceChannelFilter.setCompanyId(RandomTestUtil.nextLong());

		commerceChannelFilter.setUserId(RandomTestUtil.nextLong());

		commerceChannelFilter.setUserName(RandomTestUtil.randomString());

		commerceChannelFilter.setCreateDate(RandomTestUtil.nextDate());

		commerceChannelFilter.setModifiedDate(RandomTestUtil.nextDate());

		commerceChannelFilter.setCommerceChannelId(RandomTestUtil.nextLong());

		commerceChannelFilter.setType(RandomTestUtil.randomString());

		commerceChannelFilter.setTypeSettings(RandomTestUtil.randomString());

		_commerceChannelFilters.add(_persistence.update(commerceChannelFilter));

		return commerceChannelFilter;
	}

	private List<CommerceChannelFilter> _commerceChannelFilters = new ArrayList<CommerceChannelFilter>();
	private CommerceChannelFilterPersistence _persistence;
	private ClassLoader _dynamicQueryClassLoader;
}