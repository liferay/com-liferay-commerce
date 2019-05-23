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

import com.liferay.commerce.product.exception.NoSuchCatalogException;
import com.liferay.commerce.product.model.CommerceCatalog;
import com.liferay.commerce.product.service.CommerceCatalogLocalServiceUtil;
import com.liferay.commerce.product.service.persistence.CommerceCatalogPersistence;
import com.liferay.commerce.product.service.persistence.CommerceCatalogUtil;

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
public class CommerceCatalogPersistenceTest {
	@ClassRule
	@Rule
	public static final AggregateTestRule aggregateTestRule = new AggregateTestRule(new LiferayIntegrationTestRule(),
			PersistenceTestRule.INSTANCE,
			new TransactionalTestRule(Propagation.REQUIRED,
				"com.liferay.commerce.product.service"));

	@Before
	public void setUp() {
		_persistence = CommerceCatalogUtil.getPersistence();

		Class<?> clazz = _persistence.getClass();

		_dynamicQueryClassLoader = clazz.getClassLoader();
	}

	@After
	public void tearDown() throws Exception {
		Iterator<CommerceCatalog> iterator = _commerceCatalogs.iterator();

		while (iterator.hasNext()) {
			_persistence.remove(iterator.next());

			iterator.remove();
		}
	}

	@Test
	public void testCreate() throws Exception {
		long pk = RandomTestUtil.nextLong();

		CommerceCatalog commerceCatalog = _persistence.create(pk);

		Assert.assertNotNull(commerceCatalog);

		Assert.assertEquals(commerceCatalog.getPrimaryKey(), pk);
	}

	@Test
	public void testRemove() throws Exception {
		CommerceCatalog newCommerceCatalog = addCommerceCatalog();

		_persistence.remove(newCommerceCatalog);

		CommerceCatalog existingCommerceCatalog = _persistence.fetchByPrimaryKey(newCommerceCatalog.getPrimaryKey());

		Assert.assertNull(existingCommerceCatalog);
	}

	@Test
	public void testUpdateNew() throws Exception {
		addCommerceCatalog();
	}

	@Test
	public void testUpdateExisting() throws Exception {
		long pk = RandomTestUtil.nextLong();

		CommerceCatalog newCommerceCatalog = _persistence.create(pk);

		newCommerceCatalog.setCompanyId(RandomTestUtil.nextLong());

		newCommerceCatalog.setUserId(RandomTestUtil.nextLong());

		newCommerceCatalog.setUserName(RandomTestUtil.randomString());

		newCommerceCatalog.setCreateDate(RandomTestUtil.nextDate());

		newCommerceCatalog.setModifiedDate(RandomTestUtil.nextDate());

		newCommerceCatalog.setName(RandomTestUtil.randomString());

		newCommerceCatalog.setCatalogDefaultLanguageId(RandomTestUtil.randomString());

		_commerceCatalogs.add(_persistence.update(newCommerceCatalog));

		CommerceCatalog existingCommerceCatalog = _persistence.findByPrimaryKey(newCommerceCatalog.getPrimaryKey());

		Assert.assertEquals(existingCommerceCatalog.getCommerceCatalogId(),
			newCommerceCatalog.getCommerceCatalogId());
		Assert.assertEquals(existingCommerceCatalog.getCompanyId(),
			newCommerceCatalog.getCompanyId());
		Assert.assertEquals(existingCommerceCatalog.getUserId(),
			newCommerceCatalog.getUserId());
		Assert.assertEquals(existingCommerceCatalog.getUserName(),
			newCommerceCatalog.getUserName());
		Assert.assertEquals(Time.getShortTimestamp(
				existingCommerceCatalog.getCreateDate()),
			Time.getShortTimestamp(newCommerceCatalog.getCreateDate()));
		Assert.assertEquals(Time.getShortTimestamp(
				existingCommerceCatalog.getModifiedDate()),
			Time.getShortTimestamp(newCommerceCatalog.getModifiedDate()));
		Assert.assertEquals(existingCommerceCatalog.getName(),
			newCommerceCatalog.getName());
		Assert.assertEquals(existingCommerceCatalog.getCatalogDefaultLanguageId(),
			newCommerceCatalog.getCatalogDefaultLanguageId());
	}

	@Test
	public void testCountByCompanyId() throws Exception {
		_persistence.countByCompanyId(RandomTestUtil.nextLong());

		_persistence.countByCompanyId(0L);
	}

	@Test
	public void testFindByPrimaryKeyExisting() throws Exception {
		CommerceCatalog newCommerceCatalog = addCommerceCatalog();

		CommerceCatalog existingCommerceCatalog = _persistence.findByPrimaryKey(newCommerceCatalog.getPrimaryKey());

		Assert.assertEquals(existingCommerceCatalog, newCommerceCatalog);
	}

	@Test(expected = NoSuchCatalogException.class)
	public void testFindByPrimaryKeyMissing() throws Exception {
		long pk = RandomTestUtil.nextLong();

		_persistence.findByPrimaryKey(pk);
	}

	@Test
	public void testFindAll() throws Exception {
		_persistence.findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS,
			getOrderByComparator());
	}

	protected OrderByComparator<CommerceCatalog> getOrderByComparator() {
		return OrderByComparatorFactoryUtil.create("CommerceCatalog",
			"commerceCatalogId", true, "companyId", true, "userId", true,
			"userName", true, "createDate", true, "modifiedDate", true, "name",
			true, "catalogDefaultLanguageId", true);
	}

	@Test
	public void testFetchByPrimaryKeyExisting() throws Exception {
		CommerceCatalog newCommerceCatalog = addCommerceCatalog();

		CommerceCatalog existingCommerceCatalog = _persistence.fetchByPrimaryKey(newCommerceCatalog.getPrimaryKey());

		Assert.assertEquals(existingCommerceCatalog, newCommerceCatalog);
	}

	@Test
	public void testFetchByPrimaryKeyMissing() throws Exception {
		long pk = RandomTestUtil.nextLong();

		CommerceCatalog missingCommerceCatalog = _persistence.fetchByPrimaryKey(pk);

		Assert.assertNull(missingCommerceCatalog);
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereAllPrimaryKeysExist()
		throws Exception {
		CommerceCatalog newCommerceCatalog1 = addCommerceCatalog();
		CommerceCatalog newCommerceCatalog2 = addCommerceCatalog();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newCommerceCatalog1.getPrimaryKey());
		primaryKeys.add(newCommerceCatalog2.getPrimaryKey());

		Map<Serializable, CommerceCatalog> commerceCatalogs = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(2, commerceCatalogs.size());
		Assert.assertEquals(newCommerceCatalog1,
			commerceCatalogs.get(newCommerceCatalog1.getPrimaryKey()));
		Assert.assertEquals(newCommerceCatalog2,
			commerceCatalogs.get(newCommerceCatalog2.getPrimaryKey()));
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereNoPrimaryKeysExist()
		throws Exception {
		long pk1 = RandomTestUtil.nextLong();

		long pk2 = RandomTestUtil.nextLong();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(pk1);
		primaryKeys.add(pk2);

		Map<Serializable, CommerceCatalog> commerceCatalogs = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertTrue(commerceCatalogs.isEmpty());
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereSomePrimaryKeysExist()
		throws Exception {
		CommerceCatalog newCommerceCatalog = addCommerceCatalog();

		long pk = RandomTestUtil.nextLong();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newCommerceCatalog.getPrimaryKey());
		primaryKeys.add(pk);

		Map<Serializable, CommerceCatalog> commerceCatalogs = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(1, commerceCatalogs.size());
		Assert.assertEquals(newCommerceCatalog,
			commerceCatalogs.get(newCommerceCatalog.getPrimaryKey()));
	}

	@Test
	public void testFetchByPrimaryKeysWithNoPrimaryKeys()
		throws Exception {
		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		Map<Serializable, CommerceCatalog> commerceCatalogs = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertTrue(commerceCatalogs.isEmpty());
	}

	@Test
	public void testFetchByPrimaryKeysWithOnePrimaryKey()
		throws Exception {
		CommerceCatalog newCommerceCatalog = addCommerceCatalog();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newCommerceCatalog.getPrimaryKey());

		Map<Serializable, CommerceCatalog> commerceCatalogs = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(1, commerceCatalogs.size());
		Assert.assertEquals(newCommerceCatalog,
			commerceCatalogs.get(newCommerceCatalog.getPrimaryKey()));
	}

	@Test
	public void testActionableDynamicQuery() throws Exception {
		final IntegerWrapper count = new IntegerWrapper();

		ActionableDynamicQuery actionableDynamicQuery = CommerceCatalogLocalServiceUtil.getActionableDynamicQuery();

		actionableDynamicQuery.setPerformActionMethod(new ActionableDynamicQuery.PerformActionMethod<CommerceCatalog>() {
				@Override
				public void performAction(CommerceCatalog commerceCatalog) {
					Assert.assertNotNull(commerceCatalog);

					count.increment();
				}
			});

		actionableDynamicQuery.performActions();

		Assert.assertEquals(count.getValue(), _persistence.countAll());
	}

	@Test
	public void testDynamicQueryByPrimaryKeyExisting()
		throws Exception {
		CommerceCatalog newCommerceCatalog = addCommerceCatalog();

		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(CommerceCatalog.class,
				_dynamicQueryClassLoader);

		dynamicQuery.add(RestrictionsFactoryUtil.eq("commerceCatalogId",
				newCommerceCatalog.getCommerceCatalogId()));

		List<CommerceCatalog> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(1, result.size());

		CommerceCatalog existingCommerceCatalog = result.get(0);

		Assert.assertEquals(existingCommerceCatalog, newCommerceCatalog);
	}

	@Test
	public void testDynamicQueryByPrimaryKeyMissing() throws Exception {
		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(CommerceCatalog.class,
				_dynamicQueryClassLoader);

		dynamicQuery.add(RestrictionsFactoryUtil.eq("commerceCatalogId",
				RandomTestUtil.nextLong()));

		List<CommerceCatalog> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(0, result.size());
	}

	@Test
	public void testDynamicQueryByProjectionExisting()
		throws Exception {
		CommerceCatalog newCommerceCatalog = addCommerceCatalog();

		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(CommerceCatalog.class,
				_dynamicQueryClassLoader);

		dynamicQuery.setProjection(ProjectionFactoryUtil.property(
				"commerceCatalogId"));

		Object newCommerceCatalogId = newCommerceCatalog.getCommerceCatalogId();

		dynamicQuery.add(RestrictionsFactoryUtil.in("commerceCatalogId",
				new Object[] { newCommerceCatalogId }));

		List<Object> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(1, result.size());

		Object existingCommerceCatalogId = result.get(0);

		Assert.assertEquals(existingCommerceCatalogId, newCommerceCatalogId);
	}

	@Test
	public void testDynamicQueryByProjectionMissing() throws Exception {
		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(CommerceCatalog.class,
				_dynamicQueryClassLoader);

		dynamicQuery.setProjection(ProjectionFactoryUtil.property(
				"commerceCatalogId"));

		dynamicQuery.add(RestrictionsFactoryUtil.in("commerceCatalogId",
				new Object[] { RandomTestUtil.nextLong() }));

		List<Object> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(0, result.size());
	}

	protected CommerceCatalog addCommerceCatalog() throws Exception {
		long pk = RandomTestUtil.nextLong();

		CommerceCatalog commerceCatalog = _persistence.create(pk);

		commerceCatalog.setCompanyId(RandomTestUtil.nextLong());

		commerceCatalog.setUserId(RandomTestUtil.nextLong());

		commerceCatalog.setUserName(RandomTestUtil.randomString());

		commerceCatalog.setCreateDate(RandomTestUtil.nextDate());

		commerceCatalog.setModifiedDate(RandomTestUtil.nextDate());

		commerceCatalog.setName(RandomTestUtil.randomString());

		commerceCatalog.setCatalogDefaultLanguageId(RandomTestUtil.randomString());

		_commerceCatalogs.add(_persistence.update(commerceCatalog));

		return commerceCatalog;
	}

	private List<CommerceCatalog> _commerceCatalogs = new ArrayList<CommerceCatalog>();
	private CommerceCatalogPersistence _persistence;
	private ClassLoader _dynamicQueryClassLoader;
}