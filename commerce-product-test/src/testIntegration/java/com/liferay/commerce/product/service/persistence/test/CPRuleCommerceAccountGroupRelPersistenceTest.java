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

import com.liferay.commerce.product.exception.NoSuchCPRuleCommerceAccountGroupRelException;
import com.liferay.commerce.product.model.CPRuleCommerceAccountGroupRel;
import com.liferay.commerce.product.service.CPRuleCommerceAccountGroupRelLocalServiceUtil;
import com.liferay.commerce.product.service.persistence.CPRuleCommerceAccountGroupRelPersistence;
import com.liferay.commerce.product.service.persistence.CPRuleCommerceAccountGroupRelUtil;

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
public class CPRuleCommerceAccountGroupRelPersistenceTest {
	@ClassRule
	@Rule
	public static final AggregateTestRule aggregateTestRule = new AggregateTestRule(new LiferayIntegrationTestRule(),
			PersistenceTestRule.INSTANCE,
			new TransactionalTestRule(Propagation.REQUIRED,
				"com.liferay.commerce.product.service"));

	@Before
	public void setUp() {
		_persistence = CPRuleCommerceAccountGroupRelUtil.getPersistence();

		Class<?> clazz = _persistence.getClass();

		_dynamicQueryClassLoader = clazz.getClassLoader();
	}

	@After
	public void tearDown() throws Exception {
		Iterator<CPRuleCommerceAccountGroupRel> iterator = _cpRuleCommerceAccountGroupRels.iterator();

		while (iterator.hasNext()) {
			_persistence.remove(iterator.next());

			iterator.remove();
		}
	}

	@Test
	public void testCreate() throws Exception {
		long pk = RandomTestUtil.nextLong();

		CPRuleCommerceAccountGroupRel cpRuleCommerceAccountGroupRel = _persistence.create(pk);

		Assert.assertNotNull(cpRuleCommerceAccountGroupRel);

		Assert.assertEquals(cpRuleCommerceAccountGroupRel.getPrimaryKey(), pk);
	}

	@Test
	public void testRemove() throws Exception {
		CPRuleCommerceAccountGroupRel newCPRuleCommerceAccountGroupRel = addCPRuleCommerceAccountGroupRel();

		_persistence.remove(newCPRuleCommerceAccountGroupRel);

		CPRuleCommerceAccountGroupRel existingCPRuleCommerceAccountGroupRel = _persistence.fetchByPrimaryKey(newCPRuleCommerceAccountGroupRel.getPrimaryKey());

		Assert.assertNull(existingCPRuleCommerceAccountGroupRel);
	}

	@Test
	public void testUpdateNew() throws Exception {
		addCPRuleCommerceAccountGroupRel();
	}

	@Test
	public void testUpdateExisting() throws Exception {
		long pk = RandomTestUtil.nextLong();

		CPRuleCommerceAccountGroupRel newCPRuleCommerceAccountGroupRel = _persistence.create(pk);

		newCPRuleCommerceAccountGroupRel.setGroupId(RandomTestUtil.nextLong());

		newCPRuleCommerceAccountGroupRel.setCompanyId(RandomTestUtil.nextLong());

		newCPRuleCommerceAccountGroupRel.setUserId(RandomTestUtil.nextLong());

		newCPRuleCommerceAccountGroupRel.setUserName(RandomTestUtil.randomString());

		newCPRuleCommerceAccountGroupRel.setCreateDate(RandomTestUtil.nextDate());

		newCPRuleCommerceAccountGroupRel.setModifiedDate(RandomTestUtil.nextDate());

		newCPRuleCommerceAccountGroupRel.setCPRuleId(RandomTestUtil.nextLong());

		newCPRuleCommerceAccountGroupRel.setCommerceAccountGroupId(RandomTestUtil.nextLong());

		_cpRuleCommerceAccountGroupRels.add(_persistence.update(
				newCPRuleCommerceAccountGroupRel));

		CPRuleCommerceAccountGroupRel existingCPRuleCommerceAccountGroupRel = _persistence.findByPrimaryKey(newCPRuleCommerceAccountGroupRel.getPrimaryKey());

		Assert.assertEquals(existingCPRuleCommerceAccountGroupRel.getCPRuleCommerceAccountGroupRelId(),
			newCPRuleCommerceAccountGroupRel.getCPRuleCommerceAccountGroupRelId());
		Assert.assertEquals(existingCPRuleCommerceAccountGroupRel.getGroupId(),
			newCPRuleCommerceAccountGroupRel.getGroupId());
		Assert.assertEquals(existingCPRuleCommerceAccountGroupRel.getCompanyId(),
			newCPRuleCommerceAccountGroupRel.getCompanyId());
		Assert.assertEquals(existingCPRuleCommerceAccountGroupRel.getUserId(),
			newCPRuleCommerceAccountGroupRel.getUserId());
		Assert.assertEquals(existingCPRuleCommerceAccountGroupRel.getUserName(),
			newCPRuleCommerceAccountGroupRel.getUserName());
		Assert.assertEquals(Time.getShortTimestamp(
				existingCPRuleCommerceAccountGroupRel.getCreateDate()),
			Time.getShortTimestamp(
				newCPRuleCommerceAccountGroupRel.getCreateDate()));
		Assert.assertEquals(Time.getShortTimestamp(
				existingCPRuleCommerceAccountGroupRel.getModifiedDate()),
			Time.getShortTimestamp(
				newCPRuleCommerceAccountGroupRel.getModifiedDate()));
		Assert.assertEquals(existingCPRuleCommerceAccountGroupRel.getCPRuleId(),
			newCPRuleCommerceAccountGroupRel.getCPRuleId());
		Assert.assertEquals(existingCPRuleCommerceAccountGroupRel.getCommerceAccountGroupId(),
			newCPRuleCommerceAccountGroupRel.getCommerceAccountGroupId());
	}

	@Test
	public void testCountByCPRuleId() throws Exception {
		_persistence.countByCPRuleId(RandomTestUtil.nextLong());

		_persistence.countByCPRuleId(0L);
	}

	@Test
	public void testCountByCommerceAccountGroupId() throws Exception {
		_persistence.countByCommerceAccountGroupId(RandomTestUtil.nextLong());

		_persistence.countByCommerceAccountGroupId(0L);
	}

	@Test
	public void testFindByPrimaryKeyExisting() throws Exception {
		CPRuleCommerceAccountGroupRel newCPRuleCommerceAccountGroupRel = addCPRuleCommerceAccountGroupRel();

		CPRuleCommerceAccountGroupRel existingCPRuleCommerceAccountGroupRel = _persistence.findByPrimaryKey(newCPRuleCommerceAccountGroupRel.getPrimaryKey());

		Assert.assertEquals(existingCPRuleCommerceAccountGroupRel,
			newCPRuleCommerceAccountGroupRel);
	}

	@Test(expected = NoSuchCPRuleCommerceAccountGroupRelException.class)
	public void testFindByPrimaryKeyMissing() throws Exception {
		long pk = RandomTestUtil.nextLong();

		_persistence.findByPrimaryKey(pk);
	}

	@Test
	public void testFindAll() throws Exception {
		_persistence.findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS,
			getOrderByComparator());
	}

	protected OrderByComparator<CPRuleCommerceAccountGroupRel> getOrderByComparator() {
		return OrderByComparatorFactoryUtil.create("CPRuleCommerceAccountGroupRel",
			"CPRuleCommerceAccountGroupRelId", true, "groupId", true,
			"companyId", true, "userId", true, "userName", true, "createDate",
			true, "modifiedDate", true, "CPRuleId", true,
			"commerceAccountGroupId", true);
	}

	@Test
	public void testFetchByPrimaryKeyExisting() throws Exception {
		CPRuleCommerceAccountGroupRel newCPRuleCommerceAccountGroupRel = addCPRuleCommerceAccountGroupRel();

		CPRuleCommerceAccountGroupRel existingCPRuleCommerceAccountGroupRel = _persistence.fetchByPrimaryKey(newCPRuleCommerceAccountGroupRel.getPrimaryKey());

		Assert.assertEquals(existingCPRuleCommerceAccountGroupRel,
			newCPRuleCommerceAccountGroupRel);
	}

	@Test
	public void testFetchByPrimaryKeyMissing() throws Exception {
		long pk = RandomTestUtil.nextLong();

		CPRuleCommerceAccountGroupRel missingCPRuleCommerceAccountGroupRel = _persistence.fetchByPrimaryKey(pk);

		Assert.assertNull(missingCPRuleCommerceAccountGroupRel);
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereAllPrimaryKeysExist()
		throws Exception {
		CPRuleCommerceAccountGroupRel newCPRuleCommerceAccountGroupRel1 = addCPRuleCommerceAccountGroupRel();
		CPRuleCommerceAccountGroupRel newCPRuleCommerceAccountGroupRel2 = addCPRuleCommerceAccountGroupRel();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newCPRuleCommerceAccountGroupRel1.getPrimaryKey());
		primaryKeys.add(newCPRuleCommerceAccountGroupRel2.getPrimaryKey());

		Map<Serializable, CPRuleCommerceAccountGroupRel> cpRuleCommerceAccountGroupRels =
			_persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(2, cpRuleCommerceAccountGroupRels.size());
		Assert.assertEquals(newCPRuleCommerceAccountGroupRel1,
			cpRuleCommerceAccountGroupRels.get(
				newCPRuleCommerceAccountGroupRel1.getPrimaryKey()));
		Assert.assertEquals(newCPRuleCommerceAccountGroupRel2,
			cpRuleCommerceAccountGroupRels.get(
				newCPRuleCommerceAccountGroupRel2.getPrimaryKey()));
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereNoPrimaryKeysExist()
		throws Exception {
		long pk1 = RandomTestUtil.nextLong();

		long pk2 = RandomTestUtil.nextLong();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(pk1);
		primaryKeys.add(pk2);

		Map<Serializable, CPRuleCommerceAccountGroupRel> cpRuleCommerceAccountGroupRels =
			_persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertTrue(cpRuleCommerceAccountGroupRels.isEmpty());
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereSomePrimaryKeysExist()
		throws Exception {
		CPRuleCommerceAccountGroupRel newCPRuleCommerceAccountGroupRel = addCPRuleCommerceAccountGroupRel();

		long pk = RandomTestUtil.nextLong();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newCPRuleCommerceAccountGroupRel.getPrimaryKey());
		primaryKeys.add(pk);

		Map<Serializable, CPRuleCommerceAccountGroupRel> cpRuleCommerceAccountGroupRels =
			_persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(1, cpRuleCommerceAccountGroupRels.size());
		Assert.assertEquals(newCPRuleCommerceAccountGroupRel,
			cpRuleCommerceAccountGroupRels.get(
				newCPRuleCommerceAccountGroupRel.getPrimaryKey()));
	}

	@Test
	public void testFetchByPrimaryKeysWithNoPrimaryKeys()
		throws Exception {
		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		Map<Serializable, CPRuleCommerceAccountGroupRel> cpRuleCommerceAccountGroupRels =
			_persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertTrue(cpRuleCommerceAccountGroupRels.isEmpty());
	}

	@Test
	public void testFetchByPrimaryKeysWithOnePrimaryKey()
		throws Exception {
		CPRuleCommerceAccountGroupRel newCPRuleCommerceAccountGroupRel = addCPRuleCommerceAccountGroupRel();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newCPRuleCommerceAccountGroupRel.getPrimaryKey());

		Map<Serializable, CPRuleCommerceAccountGroupRel> cpRuleCommerceAccountGroupRels =
			_persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(1, cpRuleCommerceAccountGroupRels.size());
		Assert.assertEquals(newCPRuleCommerceAccountGroupRel,
			cpRuleCommerceAccountGroupRels.get(
				newCPRuleCommerceAccountGroupRel.getPrimaryKey()));
	}

	@Test
	public void testActionableDynamicQuery() throws Exception {
		final IntegerWrapper count = new IntegerWrapper();

		ActionableDynamicQuery actionableDynamicQuery = CPRuleCommerceAccountGroupRelLocalServiceUtil.getActionableDynamicQuery();

		actionableDynamicQuery.setPerformActionMethod(new ActionableDynamicQuery.PerformActionMethod<CPRuleCommerceAccountGroupRel>() {
				@Override
				public void performAction(
					CPRuleCommerceAccountGroupRel cpRuleCommerceAccountGroupRel) {
					Assert.assertNotNull(cpRuleCommerceAccountGroupRel);

					count.increment();
				}
			});

		actionableDynamicQuery.performActions();

		Assert.assertEquals(count.getValue(), _persistence.countAll());
	}

	@Test
	public void testDynamicQueryByPrimaryKeyExisting()
		throws Exception {
		CPRuleCommerceAccountGroupRel newCPRuleCommerceAccountGroupRel = addCPRuleCommerceAccountGroupRel();

		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(CPRuleCommerceAccountGroupRel.class,
				_dynamicQueryClassLoader);

		dynamicQuery.add(RestrictionsFactoryUtil.eq(
				"CPRuleCommerceAccountGroupRelId",
				newCPRuleCommerceAccountGroupRel.getCPRuleCommerceAccountGroupRelId()));

		List<CPRuleCommerceAccountGroupRel> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(1, result.size());

		CPRuleCommerceAccountGroupRel existingCPRuleCommerceAccountGroupRel = result.get(0);

		Assert.assertEquals(existingCPRuleCommerceAccountGroupRel,
			newCPRuleCommerceAccountGroupRel);
	}

	@Test
	public void testDynamicQueryByPrimaryKeyMissing() throws Exception {
		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(CPRuleCommerceAccountGroupRel.class,
				_dynamicQueryClassLoader);

		dynamicQuery.add(RestrictionsFactoryUtil.eq(
				"CPRuleCommerceAccountGroupRelId", RandomTestUtil.nextLong()));

		List<CPRuleCommerceAccountGroupRel> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(0, result.size());
	}

	@Test
	public void testDynamicQueryByProjectionExisting()
		throws Exception {
		CPRuleCommerceAccountGroupRel newCPRuleCommerceAccountGroupRel = addCPRuleCommerceAccountGroupRel();

		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(CPRuleCommerceAccountGroupRel.class,
				_dynamicQueryClassLoader);

		dynamicQuery.setProjection(ProjectionFactoryUtil.property(
				"CPRuleCommerceAccountGroupRelId"));

		Object newCPRuleCommerceAccountGroupRelId = newCPRuleCommerceAccountGroupRel.getCPRuleCommerceAccountGroupRelId();

		dynamicQuery.add(RestrictionsFactoryUtil.in(
				"CPRuleCommerceAccountGroupRelId",
				new Object[] { newCPRuleCommerceAccountGroupRelId }));

		List<Object> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(1, result.size());

		Object existingCPRuleCommerceAccountGroupRelId = result.get(0);

		Assert.assertEquals(existingCPRuleCommerceAccountGroupRelId,
			newCPRuleCommerceAccountGroupRelId);
	}

	@Test
	public void testDynamicQueryByProjectionMissing() throws Exception {
		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(CPRuleCommerceAccountGroupRel.class,
				_dynamicQueryClassLoader);

		dynamicQuery.setProjection(ProjectionFactoryUtil.property(
				"CPRuleCommerceAccountGroupRelId"));

		dynamicQuery.add(RestrictionsFactoryUtil.in(
				"CPRuleCommerceAccountGroupRelId",
				new Object[] { RandomTestUtil.nextLong() }));

		List<Object> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(0, result.size());
	}

	protected CPRuleCommerceAccountGroupRel addCPRuleCommerceAccountGroupRel()
		throws Exception {
		long pk = RandomTestUtil.nextLong();

		CPRuleCommerceAccountGroupRel cpRuleCommerceAccountGroupRel = _persistence.create(pk);

		cpRuleCommerceAccountGroupRel.setGroupId(RandomTestUtil.nextLong());

		cpRuleCommerceAccountGroupRel.setCompanyId(RandomTestUtil.nextLong());

		cpRuleCommerceAccountGroupRel.setUserId(RandomTestUtil.nextLong());

		cpRuleCommerceAccountGroupRel.setUserName(RandomTestUtil.randomString());

		cpRuleCommerceAccountGroupRel.setCreateDate(RandomTestUtil.nextDate());

		cpRuleCommerceAccountGroupRel.setModifiedDate(RandomTestUtil.nextDate());

		cpRuleCommerceAccountGroupRel.setCPRuleId(RandomTestUtil.nextLong());

		cpRuleCommerceAccountGroupRel.setCommerceAccountGroupId(RandomTestUtil.nextLong());

		_cpRuleCommerceAccountGroupRels.add(_persistence.update(
				cpRuleCommerceAccountGroupRel));

		return cpRuleCommerceAccountGroupRel;
	}

	private List<CPRuleCommerceAccountGroupRel> _cpRuleCommerceAccountGroupRels = new ArrayList<CPRuleCommerceAccountGroupRel>();
	private CPRuleCommerceAccountGroupRelPersistence _persistence;
	private ClassLoader _dynamicQueryClassLoader;
}