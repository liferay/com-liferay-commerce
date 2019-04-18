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

import com.liferay.commerce.product.exception.NoSuchCPRuleChannelRelException;
import com.liferay.commerce.product.model.CPRuleChannelRel;
import com.liferay.commerce.product.service.CPRuleChannelRelLocalServiceUtil;
import com.liferay.commerce.product.service.persistence.CPRuleChannelRelPersistence;
import com.liferay.commerce.product.service.persistence.CPRuleChannelRelUtil;

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
public class CPRuleChannelRelPersistenceTest {
	@ClassRule
	@Rule
	public static final AggregateTestRule aggregateTestRule = new AggregateTestRule(new LiferayIntegrationTestRule(),
			PersistenceTestRule.INSTANCE,
			new TransactionalTestRule(Propagation.REQUIRED,
				"com.liferay.commerce.product.service"));

	@Before
	public void setUp() {
		_persistence = CPRuleChannelRelUtil.getPersistence();

		Class<?> clazz = _persistence.getClass();

		_dynamicQueryClassLoader = clazz.getClassLoader();
	}

	@After
	public void tearDown() throws Exception {
		Iterator<CPRuleChannelRel> iterator = _cpRuleChannelRels.iterator();

		while (iterator.hasNext()) {
			_persistence.remove(iterator.next());

			iterator.remove();
		}
	}

	@Test
	public void testCreate() throws Exception {
		long pk = RandomTestUtil.nextLong();

		CPRuleChannelRel cpRuleChannelRel = _persistence.create(pk);

		Assert.assertNotNull(cpRuleChannelRel);

		Assert.assertEquals(cpRuleChannelRel.getPrimaryKey(), pk);
	}

	@Test
	public void testRemove() throws Exception {
		CPRuleChannelRel newCPRuleChannelRel = addCPRuleChannelRel();

		_persistence.remove(newCPRuleChannelRel);

		CPRuleChannelRel existingCPRuleChannelRel = _persistence.fetchByPrimaryKey(newCPRuleChannelRel.getPrimaryKey());

		Assert.assertNull(existingCPRuleChannelRel);
	}

	@Test
	public void testUpdateNew() throws Exception {
		addCPRuleChannelRel();
	}

	@Test
	public void testUpdateExisting() throws Exception {
		long pk = RandomTestUtil.nextLong();

		CPRuleChannelRel newCPRuleChannelRel = _persistence.create(pk);

		newCPRuleChannelRel.setGroupId(RandomTestUtil.nextLong());

		newCPRuleChannelRel.setCompanyId(RandomTestUtil.nextLong());

		newCPRuleChannelRel.setUserId(RandomTestUtil.nextLong());

		newCPRuleChannelRel.setUserName(RandomTestUtil.randomString());

		newCPRuleChannelRel.setCreateDate(RandomTestUtil.nextDate());

		newCPRuleChannelRel.setModifiedDate(RandomTestUtil.nextDate());

		newCPRuleChannelRel.setCPRuleId(RandomTestUtil.nextLong());

		newCPRuleChannelRel.setCommerceChannelId(RandomTestUtil.nextLong());

		_cpRuleChannelRels.add(_persistence.update(newCPRuleChannelRel));

		CPRuleChannelRel existingCPRuleChannelRel = _persistence.findByPrimaryKey(newCPRuleChannelRel.getPrimaryKey());

		Assert.assertEquals(existingCPRuleChannelRel.getCPRuleChannelRelId(),
			newCPRuleChannelRel.getCPRuleChannelRelId());
		Assert.assertEquals(existingCPRuleChannelRel.getGroupId(),
			newCPRuleChannelRel.getGroupId());
		Assert.assertEquals(existingCPRuleChannelRel.getCompanyId(),
			newCPRuleChannelRel.getCompanyId());
		Assert.assertEquals(existingCPRuleChannelRel.getUserId(),
			newCPRuleChannelRel.getUserId());
		Assert.assertEquals(existingCPRuleChannelRel.getUserName(),
			newCPRuleChannelRel.getUserName());
		Assert.assertEquals(Time.getShortTimestamp(
				existingCPRuleChannelRel.getCreateDate()),
			Time.getShortTimestamp(newCPRuleChannelRel.getCreateDate()));
		Assert.assertEquals(Time.getShortTimestamp(
				existingCPRuleChannelRel.getModifiedDate()),
			Time.getShortTimestamp(newCPRuleChannelRel.getModifiedDate()));
		Assert.assertEquals(existingCPRuleChannelRel.getCPRuleId(),
			newCPRuleChannelRel.getCPRuleId());
		Assert.assertEquals(existingCPRuleChannelRel.getCommerceChannelId(),
			newCPRuleChannelRel.getCommerceChannelId());
	}

	@Test
	public void testCountByCPRuleId() throws Exception {
		_persistence.countByCPRuleId(RandomTestUtil.nextLong());

		_persistence.countByCPRuleId(0L);
	}

	@Test
	public void testCountByCommerceChannelId() throws Exception {
		_persistence.countByCommerceChannelId(RandomTestUtil.nextLong());

		_persistence.countByCommerceChannelId(0L);
	}

	@Test
	public void testFindByPrimaryKeyExisting() throws Exception {
		CPRuleChannelRel newCPRuleChannelRel = addCPRuleChannelRel();

		CPRuleChannelRel existingCPRuleChannelRel = _persistence.findByPrimaryKey(newCPRuleChannelRel.getPrimaryKey());

		Assert.assertEquals(existingCPRuleChannelRel, newCPRuleChannelRel);
	}

	@Test(expected = NoSuchCPRuleChannelRelException.class)
	public void testFindByPrimaryKeyMissing() throws Exception {
		long pk = RandomTestUtil.nextLong();

		_persistence.findByPrimaryKey(pk);
	}

	@Test
	public void testFindAll() throws Exception {
		_persistence.findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS,
			getOrderByComparator());
	}

	protected OrderByComparator<CPRuleChannelRel> getOrderByComparator() {
		return OrderByComparatorFactoryUtil.create("CPRuleChannelRel",
			"CPRuleChannelRelId", true, "groupId", true, "companyId", true,
			"userId", true, "userName", true, "createDate", true,
			"modifiedDate", true, "CPRuleId", true, "commerceChannelId", true);
	}

	@Test
	public void testFetchByPrimaryKeyExisting() throws Exception {
		CPRuleChannelRel newCPRuleChannelRel = addCPRuleChannelRel();

		CPRuleChannelRel existingCPRuleChannelRel = _persistence.fetchByPrimaryKey(newCPRuleChannelRel.getPrimaryKey());

		Assert.assertEquals(existingCPRuleChannelRel, newCPRuleChannelRel);
	}

	@Test
	public void testFetchByPrimaryKeyMissing() throws Exception {
		long pk = RandomTestUtil.nextLong();

		CPRuleChannelRel missingCPRuleChannelRel = _persistence.fetchByPrimaryKey(pk);

		Assert.assertNull(missingCPRuleChannelRel);
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereAllPrimaryKeysExist()
		throws Exception {
		CPRuleChannelRel newCPRuleChannelRel1 = addCPRuleChannelRel();
		CPRuleChannelRel newCPRuleChannelRel2 = addCPRuleChannelRel();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newCPRuleChannelRel1.getPrimaryKey());
		primaryKeys.add(newCPRuleChannelRel2.getPrimaryKey());

		Map<Serializable, CPRuleChannelRel> cpRuleChannelRels = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(2, cpRuleChannelRels.size());
		Assert.assertEquals(newCPRuleChannelRel1,
			cpRuleChannelRels.get(newCPRuleChannelRel1.getPrimaryKey()));
		Assert.assertEquals(newCPRuleChannelRel2,
			cpRuleChannelRels.get(newCPRuleChannelRel2.getPrimaryKey()));
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereNoPrimaryKeysExist()
		throws Exception {
		long pk1 = RandomTestUtil.nextLong();

		long pk2 = RandomTestUtil.nextLong();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(pk1);
		primaryKeys.add(pk2);

		Map<Serializable, CPRuleChannelRel> cpRuleChannelRels = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertTrue(cpRuleChannelRels.isEmpty());
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereSomePrimaryKeysExist()
		throws Exception {
		CPRuleChannelRel newCPRuleChannelRel = addCPRuleChannelRel();

		long pk = RandomTestUtil.nextLong();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newCPRuleChannelRel.getPrimaryKey());
		primaryKeys.add(pk);

		Map<Serializable, CPRuleChannelRel> cpRuleChannelRels = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(1, cpRuleChannelRels.size());
		Assert.assertEquals(newCPRuleChannelRel,
			cpRuleChannelRels.get(newCPRuleChannelRel.getPrimaryKey()));
	}

	@Test
	public void testFetchByPrimaryKeysWithNoPrimaryKeys()
		throws Exception {
		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		Map<Serializable, CPRuleChannelRel> cpRuleChannelRels = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertTrue(cpRuleChannelRels.isEmpty());
	}

	@Test
	public void testFetchByPrimaryKeysWithOnePrimaryKey()
		throws Exception {
		CPRuleChannelRel newCPRuleChannelRel = addCPRuleChannelRel();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newCPRuleChannelRel.getPrimaryKey());

		Map<Serializable, CPRuleChannelRel> cpRuleChannelRels = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(1, cpRuleChannelRels.size());
		Assert.assertEquals(newCPRuleChannelRel,
			cpRuleChannelRels.get(newCPRuleChannelRel.getPrimaryKey()));
	}

	@Test
	public void testActionableDynamicQuery() throws Exception {
		final IntegerWrapper count = new IntegerWrapper();

		ActionableDynamicQuery actionableDynamicQuery = CPRuleChannelRelLocalServiceUtil.getActionableDynamicQuery();

		actionableDynamicQuery.setPerformActionMethod(new ActionableDynamicQuery.PerformActionMethod<CPRuleChannelRel>() {
				@Override
				public void performAction(CPRuleChannelRel cpRuleChannelRel) {
					Assert.assertNotNull(cpRuleChannelRel);

					count.increment();
				}
			});

		actionableDynamicQuery.performActions();

		Assert.assertEquals(count.getValue(), _persistence.countAll());
	}

	@Test
	public void testDynamicQueryByPrimaryKeyExisting()
		throws Exception {
		CPRuleChannelRel newCPRuleChannelRel = addCPRuleChannelRel();

		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(CPRuleChannelRel.class,
				_dynamicQueryClassLoader);

		dynamicQuery.add(RestrictionsFactoryUtil.eq("CPRuleChannelRelId",
				newCPRuleChannelRel.getCPRuleChannelRelId()));

		List<CPRuleChannelRel> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(1, result.size());

		CPRuleChannelRel existingCPRuleChannelRel = result.get(0);

		Assert.assertEquals(existingCPRuleChannelRel, newCPRuleChannelRel);
	}

	@Test
	public void testDynamicQueryByPrimaryKeyMissing() throws Exception {
		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(CPRuleChannelRel.class,
				_dynamicQueryClassLoader);

		dynamicQuery.add(RestrictionsFactoryUtil.eq("CPRuleChannelRelId",
				RandomTestUtil.nextLong()));

		List<CPRuleChannelRel> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(0, result.size());
	}

	@Test
	public void testDynamicQueryByProjectionExisting()
		throws Exception {
		CPRuleChannelRel newCPRuleChannelRel = addCPRuleChannelRel();

		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(CPRuleChannelRel.class,
				_dynamicQueryClassLoader);

		dynamicQuery.setProjection(ProjectionFactoryUtil.property(
				"CPRuleChannelRelId"));

		Object newCPRuleChannelRelId = newCPRuleChannelRel.getCPRuleChannelRelId();

		dynamicQuery.add(RestrictionsFactoryUtil.in("CPRuleChannelRelId",
				new Object[] { newCPRuleChannelRelId }));

		List<Object> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(1, result.size());

		Object existingCPRuleChannelRelId = result.get(0);

		Assert.assertEquals(existingCPRuleChannelRelId, newCPRuleChannelRelId);
	}

	@Test
	public void testDynamicQueryByProjectionMissing() throws Exception {
		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(CPRuleChannelRel.class,
				_dynamicQueryClassLoader);

		dynamicQuery.setProjection(ProjectionFactoryUtil.property(
				"CPRuleChannelRelId"));

		dynamicQuery.add(RestrictionsFactoryUtil.in("CPRuleChannelRelId",
				new Object[] { RandomTestUtil.nextLong() }));

		List<Object> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(0, result.size());
	}

	protected CPRuleChannelRel addCPRuleChannelRel() throws Exception {
		long pk = RandomTestUtil.nextLong();

		CPRuleChannelRel cpRuleChannelRel = _persistence.create(pk);

		cpRuleChannelRel.setGroupId(RandomTestUtil.nextLong());

		cpRuleChannelRel.setCompanyId(RandomTestUtil.nextLong());

		cpRuleChannelRel.setUserId(RandomTestUtil.nextLong());

		cpRuleChannelRel.setUserName(RandomTestUtil.randomString());

		cpRuleChannelRel.setCreateDate(RandomTestUtil.nextDate());

		cpRuleChannelRel.setModifiedDate(RandomTestUtil.nextDate());

		cpRuleChannelRel.setCPRuleId(RandomTestUtil.nextLong());

		cpRuleChannelRel.setCommerceChannelId(RandomTestUtil.nextLong());

		_cpRuleChannelRels.add(_persistence.update(cpRuleChannelRel));

		return cpRuleChannelRel;
	}

	private List<CPRuleChannelRel> _cpRuleChannelRels = new ArrayList<CPRuleChannelRel>();
	private CPRuleChannelRelPersistence _persistence;
	private ClassLoader _dynamicQueryClassLoader;
}