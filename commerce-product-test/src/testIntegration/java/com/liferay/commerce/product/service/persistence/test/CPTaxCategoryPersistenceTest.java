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

import com.liferay.commerce.product.exception.NoSuchCPTaxCategoryException;
import com.liferay.commerce.product.model.CPTaxCategory;
import com.liferay.commerce.product.service.CPTaxCategoryLocalServiceUtil;
import com.liferay.commerce.product.service.persistence.CPTaxCategoryPersistence;
import com.liferay.commerce.product.service.persistence.CPTaxCategoryUtil;

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
public class CPTaxCategoryPersistenceTest {
	@ClassRule
	@Rule
	public static final AggregateTestRule aggregateTestRule = new AggregateTestRule(new LiferayIntegrationTestRule(),
			PersistenceTestRule.INSTANCE,
			new TransactionalTestRule(Propagation.REQUIRED,
				"com.liferay.commerce.product.service"));

	@Before
	public void setUp() {
		_persistence = CPTaxCategoryUtil.getPersistence();

		Class<?> clazz = _persistence.getClass();

		_dynamicQueryClassLoader = clazz.getClassLoader();
	}

	@After
	public void tearDown() throws Exception {
		Iterator<CPTaxCategory> iterator = _cpTaxCategories.iterator();

		while (iterator.hasNext()) {
			_persistence.remove(iterator.next());

			iterator.remove();
		}
	}

	@Test
	public void testCreate() throws Exception {
		long pk = RandomTestUtil.nextLong();

		CPTaxCategory cpTaxCategory = _persistence.create(pk);

		Assert.assertNotNull(cpTaxCategory);

		Assert.assertEquals(cpTaxCategory.getPrimaryKey(), pk);
	}

	@Test
	public void testRemove() throws Exception {
		CPTaxCategory newCPTaxCategory = addCPTaxCategory();

		_persistence.remove(newCPTaxCategory);

		CPTaxCategory existingCPTaxCategory = _persistence.fetchByPrimaryKey(newCPTaxCategory.getPrimaryKey());

		Assert.assertNull(existingCPTaxCategory);
	}

	@Test
	public void testUpdateNew() throws Exception {
		addCPTaxCategory();
	}

	@Test
	public void testUpdateExisting() throws Exception {
		long pk = RandomTestUtil.nextLong();

		CPTaxCategory newCPTaxCategory = _persistence.create(pk);

		newCPTaxCategory.setGroupId(RandomTestUtil.nextLong());

		newCPTaxCategory.setCompanyId(RandomTestUtil.nextLong());

		newCPTaxCategory.setUserId(RandomTestUtil.nextLong());

		newCPTaxCategory.setUserName(RandomTestUtil.randomString());

		newCPTaxCategory.setCreateDate(RandomTestUtil.nextDate());

		newCPTaxCategory.setModifiedDate(RandomTestUtil.nextDate());

		newCPTaxCategory.setName(RandomTestUtil.randomString());

		newCPTaxCategory.setDescription(RandomTestUtil.randomString());

		_cpTaxCategories.add(_persistence.update(newCPTaxCategory));

		CPTaxCategory existingCPTaxCategory = _persistence.findByPrimaryKey(newCPTaxCategory.getPrimaryKey());

		Assert.assertEquals(existingCPTaxCategory.getCPTaxCategoryId(),
			newCPTaxCategory.getCPTaxCategoryId());
		Assert.assertEquals(existingCPTaxCategory.getGroupId(),
			newCPTaxCategory.getGroupId());
		Assert.assertEquals(existingCPTaxCategory.getCompanyId(),
			newCPTaxCategory.getCompanyId());
		Assert.assertEquals(existingCPTaxCategory.getUserId(),
			newCPTaxCategory.getUserId());
		Assert.assertEquals(existingCPTaxCategory.getUserName(),
			newCPTaxCategory.getUserName());
		Assert.assertEquals(Time.getShortTimestamp(
				existingCPTaxCategory.getCreateDate()),
			Time.getShortTimestamp(newCPTaxCategory.getCreateDate()));
		Assert.assertEquals(Time.getShortTimestamp(
				existingCPTaxCategory.getModifiedDate()),
			Time.getShortTimestamp(newCPTaxCategory.getModifiedDate()));
		Assert.assertEquals(existingCPTaxCategory.getName(),
			newCPTaxCategory.getName());
		Assert.assertEquals(existingCPTaxCategory.getDescription(),
			newCPTaxCategory.getDescription());
	}

	@Test
	public void testCountByGroupId() throws Exception {
		_persistence.countByGroupId(RandomTestUtil.nextLong());

		_persistence.countByGroupId(0L);
	}

	@Test
	public void testFindByPrimaryKeyExisting() throws Exception {
		CPTaxCategory newCPTaxCategory = addCPTaxCategory();

		CPTaxCategory existingCPTaxCategory = _persistence.findByPrimaryKey(newCPTaxCategory.getPrimaryKey());

		Assert.assertEquals(existingCPTaxCategory, newCPTaxCategory);
	}

	@Test(expected = NoSuchCPTaxCategoryException.class)
	public void testFindByPrimaryKeyMissing() throws Exception {
		long pk = RandomTestUtil.nextLong();

		_persistence.findByPrimaryKey(pk);
	}

	@Test
	public void testFindAll() throws Exception {
		_persistence.findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS,
			getOrderByComparator());
	}

	protected OrderByComparator<CPTaxCategory> getOrderByComparator() {
		return OrderByComparatorFactoryUtil.create("CPTaxCategory",
			"CPTaxCategoryId", true, "groupId", true, "companyId", true,
			"userId", true, "userName", true, "createDate", true,
			"modifiedDate", true, "name", true, "description", true);
	}

	@Test
	public void testFetchByPrimaryKeyExisting() throws Exception {
		CPTaxCategory newCPTaxCategory = addCPTaxCategory();

		CPTaxCategory existingCPTaxCategory = _persistence.fetchByPrimaryKey(newCPTaxCategory.getPrimaryKey());

		Assert.assertEquals(existingCPTaxCategory, newCPTaxCategory);
	}

	@Test
	public void testFetchByPrimaryKeyMissing() throws Exception {
		long pk = RandomTestUtil.nextLong();

		CPTaxCategory missingCPTaxCategory = _persistence.fetchByPrimaryKey(pk);

		Assert.assertNull(missingCPTaxCategory);
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereAllPrimaryKeysExist()
		throws Exception {
		CPTaxCategory newCPTaxCategory1 = addCPTaxCategory();
		CPTaxCategory newCPTaxCategory2 = addCPTaxCategory();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newCPTaxCategory1.getPrimaryKey());
		primaryKeys.add(newCPTaxCategory2.getPrimaryKey());

		Map<Serializable, CPTaxCategory> cpTaxCategories = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(2, cpTaxCategories.size());
		Assert.assertEquals(newCPTaxCategory1,
			cpTaxCategories.get(newCPTaxCategory1.getPrimaryKey()));
		Assert.assertEquals(newCPTaxCategory2,
			cpTaxCategories.get(newCPTaxCategory2.getPrimaryKey()));
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereNoPrimaryKeysExist()
		throws Exception {
		long pk1 = RandomTestUtil.nextLong();

		long pk2 = RandomTestUtil.nextLong();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(pk1);
		primaryKeys.add(pk2);

		Map<Serializable, CPTaxCategory> cpTaxCategories = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertTrue(cpTaxCategories.isEmpty());
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereSomePrimaryKeysExist()
		throws Exception {
		CPTaxCategory newCPTaxCategory = addCPTaxCategory();

		long pk = RandomTestUtil.nextLong();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newCPTaxCategory.getPrimaryKey());
		primaryKeys.add(pk);

		Map<Serializable, CPTaxCategory> cpTaxCategories = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(1, cpTaxCategories.size());
		Assert.assertEquals(newCPTaxCategory,
			cpTaxCategories.get(newCPTaxCategory.getPrimaryKey()));
	}

	@Test
	public void testFetchByPrimaryKeysWithNoPrimaryKeys()
		throws Exception {
		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		Map<Serializable, CPTaxCategory> cpTaxCategories = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertTrue(cpTaxCategories.isEmpty());
	}

	@Test
	public void testFetchByPrimaryKeysWithOnePrimaryKey()
		throws Exception {
		CPTaxCategory newCPTaxCategory = addCPTaxCategory();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newCPTaxCategory.getPrimaryKey());

		Map<Serializable, CPTaxCategory> cpTaxCategories = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(1, cpTaxCategories.size());
		Assert.assertEquals(newCPTaxCategory,
			cpTaxCategories.get(newCPTaxCategory.getPrimaryKey()));
	}

	@Test
	public void testActionableDynamicQuery() throws Exception {
		final IntegerWrapper count = new IntegerWrapper();

		ActionableDynamicQuery actionableDynamicQuery = CPTaxCategoryLocalServiceUtil.getActionableDynamicQuery();

		actionableDynamicQuery.setPerformActionMethod(new ActionableDynamicQuery.PerformActionMethod<CPTaxCategory>() {
				@Override
				public void performAction(CPTaxCategory cpTaxCategory) {
					Assert.assertNotNull(cpTaxCategory);

					count.increment();
				}
			});

		actionableDynamicQuery.performActions();

		Assert.assertEquals(count.getValue(), _persistence.countAll());
	}

	@Test
	public void testDynamicQueryByPrimaryKeyExisting()
		throws Exception {
		CPTaxCategory newCPTaxCategory = addCPTaxCategory();

		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(CPTaxCategory.class,
				_dynamicQueryClassLoader);

		dynamicQuery.add(RestrictionsFactoryUtil.eq("CPTaxCategoryId",
				newCPTaxCategory.getCPTaxCategoryId()));

		List<CPTaxCategory> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(1, result.size());

		CPTaxCategory existingCPTaxCategory = result.get(0);

		Assert.assertEquals(existingCPTaxCategory, newCPTaxCategory);
	}

	@Test
	public void testDynamicQueryByPrimaryKeyMissing() throws Exception {
		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(CPTaxCategory.class,
				_dynamicQueryClassLoader);

		dynamicQuery.add(RestrictionsFactoryUtil.eq("CPTaxCategoryId",
				RandomTestUtil.nextLong()));

		List<CPTaxCategory> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(0, result.size());
	}

	@Test
	public void testDynamicQueryByProjectionExisting()
		throws Exception {
		CPTaxCategory newCPTaxCategory = addCPTaxCategory();

		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(CPTaxCategory.class,
				_dynamicQueryClassLoader);

		dynamicQuery.setProjection(ProjectionFactoryUtil.property(
				"CPTaxCategoryId"));

		Object newCPTaxCategoryId = newCPTaxCategory.getCPTaxCategoryId();

		dynamicQuery.add(RestrictionsFactoryUtil.in("CPTaxCategoryId",
				new Object[] { newCPTaxCategoryId }));

		List<Object> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(1, result.size());

		Object existingCPTaxCategoryId = result.get(0);

		Assert.assertEquals(existingCPTaxCategoryId, newCPTaxCategoryId);
	}

	@Test
	public void testDynamicQueryByProjectionMissing() throws Exception {
		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(CPTaxCategory.class,
				_dynamicQueryClassLoader);

		dynamicQuery.setProjection(ProjectionFactoryUtil.property(
				"CPTaxCategoryId"));

		dynamicQuery.add(RestrictionsFactoryUtil.in("CPTaxCategoryId",
				new Object[] { RandomTestUtil.nextLong() }));

		List<Object> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(0, result.size());
	}

	protected CPTaxCategory addCPTaxCategory() throws Exception {
		long pk = RandomTestUtil.nextLong();

		CPTaxCategory cpTaxCategory = _persistence.create(pk);

		cpTaxCategory.setGroupId(RandomTestUtil.nextLong());

		cpTaxCategory.setCompanyId(RandomTestUtil.nextLong());

		cpTaxCategory.setUserId(RandomTestUtil.nextLong());

		cpTaxCategory.setUserName(RandomTestUtil.randomString());

		cpTaxCategory.setCreateDate(RandomTestUtil.nextDate());

		cpTaxCategory.setModifiedDate(RandomTestUtil.nextDate());

		cpTaxCategory.setName(RandomTestUtil.randomString());

		cpTaxCategory.setDescription(RandomTestUtil.randomString());

		_cpTaxCategories.add(_persistence.update(cpTaxCategory));

		return cpTaxCategory;
	}

	private List<CPTaxCategory> _cpTaxCategories = new ArrayList<CPTaxCategory>();
	private CPTaxCategoryPersistence _persistence;
	private ClassLoader _dynamicQueryClassLoader;
}