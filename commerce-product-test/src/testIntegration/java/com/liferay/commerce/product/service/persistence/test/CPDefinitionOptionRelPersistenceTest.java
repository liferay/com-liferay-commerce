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

import com.liferay.commerce.product.exception.NoSuchCPDefinitionOptionRelException;
import com.liferay.commerce.product.model.CPDefinitionOptionRel;
import com.liferay.commerce.product.service.CPDefinitionOptionRelLocalServiceUtil;
import com.liferay.commerce.product.service.persistence.CPDefinitionOptionRelPersistence;
import com.liferay.commerce.product.service.persistence.CPDefinitionOptionRelUtil;

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
public class CPDefinitionOptionRelPersistenceTest {
	@ClassRule
	@Rule
	public static final AggregateTestRule aggregateTestRule = new AggregateTestRule(new LiferayIntegrationTestRule(),
			PersistenceTestRule.INSTANCE,
			new TransactionalTestRule(Propagation.REQUIRED,
				"com.liferay.commerce.product.service"));

	@Before
	public void setUp() {
		_persistence = CPDefinitionOptionRelUtil.getPersistence();

		Class<?> clazz = _persistence.getClass();

		_dynamicQueryClassLoader = clazz.getClassLoader();
	}

	@After
	public void tearDown() throws Exception {
		Iterator<CPDefinitionOptionRel> iterator = _cpDefinitionOptionRels.iterator();

		while (iterator.hasNext()) {
			_persistence.remove(iterator.next());

			iterator.remove();
		}
	}

	@Test
	public void testCreate() throws Exception {
		long pk = RandomTestUtil.nextLong();

		CPDefinitionOptionRel cpDefinitionOptionRel = _persistence.create(pk);

		Assert.assertNotNull(cpDefinitionOptionRel);

		Assert.assertEquals(cpDefinitionOptionRel.getPrimaryKey(), pk);
	}

	@Test
	public void testRemove() throws Exception {
		CPDefinitionOptionRel newCPDefinitionOptionRel = addCPDefinitionOptionRel();

		_persistence.remove(newCPDefinitionOptionRel);

		CPDefinitionOptionRel existingCPDefinitionOptionRel = _persistence.fetchByPrimaryKey(newCPDefinitionOptionRel.getPrimaryKey());

		Assert.assertNull(existingCPDefinitionOptionRel);
	}

	@Test
	public void testUpdateNew() throws Exception {
		addCPDefinitionOptionRel();
	}

	@Test
	public void testUpdateExisting() throws Exception {
		long pk = RandomTestUtil.nextLong();

		CPDefinitionOptionRel newCPDefinitionOptionRel = _persistence.create(pk);

		newCPDefinitionOptionRel.setUuid(RandomTestUtil.randomString());

		newCPDefinitionOptionRel.setGroupId(RandomTestUtil.nextLong());

		newCPDefinitionOptionRel.setCompanyId(RandomTestUtil.nextLong());

		newCPDefinitionOptionRel.setUserId(RandomTestUtil.nextLong());

		newCPDefinitionOptionRel.setUserName(RandomTestUtil.randomString());

		newCPDefinitionOptionRel.setCreateDate(RandomTestUtil.nextDate());

		newCPDefinitionOptionRel.setModifiedDate(RandomTestUtil.nextDate());

		newCPDefinitionOptionRel.setCPDefinitionId(RandomTestUtil.nextLong());

		newCPDefinitionOptionRel.setCPOptionId(RandomTestUtil.nextLong());

		newCPDefinitionOptionRel.setName(RandomTestUtil.randomString());

		newCPDefinitionOptionRel.setDescription(RandomTestUtil.randomString());

		newCPDefinitionOptionRel.setDDMFormFieldTypeName(RandomTestUtil.randomString());

		newCPDefinitionOptionRel.setPriority(RandomTestUtil.nextDouble());

		newCPDefinitionOptionRel.setFacetable(RandomTestUtil.randomBoolean());

		newCPDefinitionOptionRel.setRequired(RandomTestUtil.randomBoolean());

		newCPDefinitionOptionRel.setSkuContributor(RandomTestUtil.randomBoolean());

		_cpDefinitionOptionRels.add(_persistence.update(
				newCPDefinitionOptionRel));

		CPDefinitionOptionRel existingCPDefinitionOptionRel = _persistence.findByPrimaryKey(newCPDefinitionOptionRel.getPrimaryKey());

		Assert.assertEquals(existingCPDefinitionOptionRel.getUuid(),
			newCPDefinitionOptionRel.getUuid());
		Assert.assertEquals(existingCPDefinitionOptionRel.getCPDefinitionOptionRelId(),
			newCPDefinitionOptionRel.getCPDefinitionOptionRelId());
		Assert.assertEquals(existingCPDefinitionOptionRel.getGroupId(),
			newCPDefinitionOptionRel.getGroupId());
		Assert.assertEquals(existingCPDefinitionOptionRel.getCompanyId(),
			newCPDefinitionOptionRel.getCompanyId());
		Assert.assertEquals(existingCPDefinitionOptionRel.getUserId(),
			newCPDefinitionOptionRel.getUserId());
		Assert.assertEquals(existingCPDefinitionOptionRel.getUserName(),
			newCPDefinitionOptionRel.getUserName());
		Assert.assertEquals(Time.getShortTimestamp(
				existingCPDefinitionOptionRel.getCreateDate()),
			Time.getShortTimestamp(newCPDefinitionOptionRel.getCreateDate()));
		Assert.assertEquals(Time.getShortTimestamp(
				existingCPDefinitionOptionRel.getModifiedDate()),
			Time.getShortTimestamp(newCPDefinitionOptionRel.getModifiedDate()));
		Assert.assertEquals(existingCPDefinitionOptionRel.getCPDefinitionId(),
			newCPDefinitionOptionRel.getCPDefinitionId());
		Assert.assertEquals(existingCPDefinitionOptionRel.getCPOptionId(),
			newCPDefinitionOptionRel.getCPOptionId());
		Assert.assertEquals(existingCPDefinitionOptionRel.getName(),
			newCPDefinitionOptionRel.getName());
		Assert.assertEquals(existingCPDefinitionOptionRel.getDescription(),
			newCPDefinitionOptionRel.getDescription());
		Assert.assertEquals(existingCPDefinitionOptionRel.getDDMFormFieldTypeName(),
			newCPDefinitionOptionRel.getDDMFormFieldTypeName());
		AssertUtils.assertEquals(existingCPDefinitionOptionRel.getPriority(),
			newCPDefinitionOptionRel.getPriority());
		Assert.assertEquals(existingCPDefinitionOptionRel.isFacetable(),
			newCPDefinitionOptionRel.isFacetable());
		Assert.assertEquals(existingCPDefinitionOptionRel.isRequired(),
			newCPDefinitionOptionRel.isRequired());
		Assert.assertEquals(existingCPDefinitionOptionRel.isSkuContributor(),
			newCPDefinitionOptionRel.isSkuContributor());
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
	public void testCountByCompanyId() throws Exception {
		_persistence.countByCompanyId(RandomTestUtil.nextLong());

		_persistence.countByCompanyId(0L);
	}

	@Test
	public void testCountByCPDefinitionId() throws Exception {
		_persistence.countByCPDefinitionId(RandomTestUtil.nextLong());

		_persistence.countByCPDefinitionId(0L);
	}

	@Test
	public void testCountByC_SC() throws Exception {
		_persistence.countByC_SC(RandomTestUtil.nextLong(),
			RandomTestUtil.randomBoolean());

		_persistence.countByC_SC(0L, RandomTestUtil.randomBoolean());
	}

	@Test
	public void testFindByPrimaryKeyExisting() throws Exception {
		CPDefinitionOptionRel newCPDefinitionOptionRel = addCPDefinitionOptionRel();

		CPDefinitionOptionRel existingCPDefinitionOptionRel = _persistence.findByPrimaryKey(newCPDefinitionOptionRel.getPrimaryKey());

		Assert.assertEquals(existingCPDefinitionOptionRel,
			newCPDefinitionOptionRel);
	}

	@Test(expected = NoSuchCPDefinitionOptionRelException.class)
	public void testFindByPrimaryKeyMissing() throws Exception {
		long pk = RandomTestUtil.nextLong();

		_persistence.findByPrimaryKey(pk);
	}

	@Test
	public void testFindAll() throws Exception {
		_persistence.findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS,
			getOrderByComparator());
	}

	protected OrderByComparator<CPDefinitionOptionRel> getOrderByComparator() {
		return OrderByComparatorFactoryUtil.create("CPDefinitionOptionRel",
			"uuid", true, "CPDefinitionOptionRelId", true, "groupId", true,
			"companyId", true, "userId", true, "userName", true, "createDate",
			true, "modifiedDate", true, "CPDefinitionId", true, "CPOptionId",
			true, "name", true, "description", true, "DDMFormFieldTypeName",
			true, "priority", true, "facetable", true, "required", true,
			"skuContributor", true);
	}

	@Test
	public void testFetchByPrimaryKeyExisting() throws Exception {
		CPDefinitionOptionRel newCPDefinitionOptionRel = addCPDefinitionOptionRel();

		CPDefinitionOptionRel existingCPDefinitionOptionRel = _persistence.fetchByPrimaryKey(newCPDefinitionOptionRel.getPrimaryKey());

		Assert.assertEquals(existingCPDefinitionOptionRel,
			newCPDefinitionOptionRel);
	}

	@Test
	public void testFetchByPrimaryKeyMissing() throws Exception {
		long pk = RandomTestUtil.nextLong();

		CPDefinitionOptionRel missingCPDefinitionOptionRel = _persistence.fetchByPrimaryKey(pk);

		Assert.assertNull(missingCPDefinitionOptionRel);
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereAllPrimaryKeysExist()
		throws Exception {
		CPDefinitionOptionRel newCPDefinitionOptionRel1 = addCPDefinitionOptionRel();
		CPDefinitionOptionRel newCPDefinitionOptionRel2 = addCPDefinitionOptionRel();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newCPDefinitionOptionRel1.getPrimaryKey());
		primaryKeys.add(newCPDefinitionOptionRel2.getPrimaryKey());

		Map<Serializable, CPDefinitionOptionRel> cpDefinitionOptionRels = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(2, cpDefinitionOptionRels.size());
		Assert.assertEquals(newCPDefinitionOptionRel1,
			cpDefinitionOptionRels.get(
				newCPDefinitionOptionRel1.getPrimaryKey()));
		Assert.assertEquals(newCPDefinitionOptionRel2,
			cpDefinitionOptionRels.get(
				newCPDefinitionOptionRel2.getPrimaryKey()));
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereNoPrimaryKeysExist()
		throws Exception {
		long pk1 = RandomTestUtil.nextLong();

		long pk2 = RandomTestUtil.nextLong();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(pk1);
		primaryKeys.add(pk2);

		Map<Serializable, CPDefinitionOptionRel> cpDefinitionOptionRels = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertTrue(cpDefinitionOptionRels.isEmpty());
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereSomePrimaryKeysExist()
		throws Exception {
		CPDefinitionOptionRel newCPDefinitionOptionRel = addCPDefinitionOptionRel();

		long pk = RandomTestUtil.nextLong();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newCPDefinitionOptionRel.getPrimaryKey());
		primaryKeys.add(pk);

		Map<Serializable, CPDefinitionOptionRel> cpDefinitionOptionRels = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(1, cpDefinitionOptionRels.size());
		Assert.assertEquals(newCPDefinitionOptionRel,
			cpDefinitionOptionRels.get(newCPDefinitionOptionRel.getPrimaryKey()));
	}

	@Test
	public void testFetchByPrimaryKeysWithNoPrimaryKeys()
		throws Exception {
		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		Map<Serializable, CPDefinitionOptionRel> cpDefinitionOptionRels = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertTrue(cpDefinitionOptionRels.isEmpty());
	}

	@Test
	public void testFetchByPrimaryKeysWithOnePrimaryKey()
		throws Exception {
		CPDefinitionOptionRel newCPDefinitionOptionRel = addCPDefinitionOptionRel();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newCPDefinitionOptionRel.getPrimaryKey());

		Map<Serializable, CPDefinitionOptionRel> cpDefinitionOptionRels = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(1, cpDefinitionOptionRels.size());
		Assert.assertEquals(newCPDefinitionOptionRel,
			cpDefinitionOptionRels.get(newCPDefinitionOptionRel.getPrimaryKey()));
	}

	@Test
	public void testActionableDynamicQuery() throws Exception {
		final IntegerWrapper count = new IntegerWrapper();

		ActionableDynamicQuery actionableDynamicQuery = CPDefinitionOptionRelLocalServiceUtil.getActionableDynamicQuery();

		actionableDynamicQuery.setPerformActionMethod(new ActionableDynamicQuery.PerformActionMethod<CPDefinitionOptionRel>() {
				@Override
				public void performAction(
					CPDefinitionOptionRel cpDefinitionOptionRel) {
					Assert.assertNotNull(cpDefinitionOptionRel);

					count.increment();
				}
			});

		actionableDynamicQuery.performActions();

		Assert.assertEquals(count.getValue(), _persistence.countAll());
	}

	@Test
	public void testDynamicQueryByPrimaryKeyExisting()
		throws Exception {
		CPDefinitionOptionRel newCPDefinitionOptionRel = addCPDefinitionOptionRel();

		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(CPDefinitionOptionRel.class,
				_dynamicQueryClassLoader);

		dynamicQuery.add(RestrictionsFactoryUtil.eq("CPDefinitionOptionRelId",
				newCPDefinitionOptionRel.getCPDefinitionOptionRelId()));

		List<CPDefinitionOptionRel> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(1, result.size());

		CPDefinitionOptionRel existingCPDefinitionOptionRel = result.get(0);

		Assert.assertEquals(existingCPDefinitionOptionRel,
			newCPDefinitionOptionRel);
	}

	@Test
	public void testDynamicQueryByPrimaryKeyMissing() throws Exception {
		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(CPDefinitionOptionRel.class,
				_dynamicQueryClassLoader);

		dynamicQuery.add(RestrictionsFactoryUtil.eq("CPDefinitionOptionRelId",
				RandomTestUtil.nextLong()));

		List<CPDefinitionOptionRel> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(0, result.size());
	}

	@Test
	public void testDynamicQueryByProjectionExisting()
		throws Exception {
		CPDefinitionOptionRel newCPDefinitionOptionRel = addCPDefinitionOptionRel();

		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(CPDefinitionOptionRel.class,
				_dynamicQueryClassLoader);

		dynamicQuery.setProjection(ProjectionFactoryUtil.property(
				"CPDefinitionOptionRelId"));

		Object newCPDefinitionOptionRelId = newCPDefinitionOptionRel.getCPDefinitionOptionRelId();

		dynamicQuery.add(RestrictionsFactoryUtil.in("CPDefinitionOptionRelId",
				new Object[] { newCPDefinitionOptionRelId }));

		List<Object> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(1, result.size());

		Object existingCPDefinitionOptionRelId = result.get(0);

		Assert.assertEquals(existingCPDefinitionOptionRelId,
			newCPDefinitionOptionRelId);
	}

	@Test
	public void testDynamicQueryByProjectionMissing() throws Exception {
		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(CPDefinitionOptionRel.class,
				_dynamicQueryClassLoader);

		dynamicQuery.setProjection(ProjectionFactoryUtil.property(
				"CPDefinitionOptionRelId"));

		dynamicQuery.add(RestrictionsFactoryUtil.in("CPDefinitionOptionRelId",
				new Object[] { RandomTestUtil.nextLong() }));

		List<Object> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(0, result.size());
	}

	@Test
	public void testResetOriginalValues() throws Exception {
		CPDefinitionOptionRel newCPDefinitionOptionRel = addCPDefinitionOptionRel();

		_persistence.clearCache();

		CPDefinitionOptionRel existingCPDefinitionOptionRel = _persistence.findByPrimaryKey(newCPDefinitionOptionRel.getPrimaryKey());

		Assert.assertTrue(Objects.equals(
				existingCPDefinitionOptionRel.getUuid(),
				ReflectionTestUtil.invoke(existingCPDefinitionOptionRel,
					"getOriginalUuid", new Class<?>[0])));
		Assert.assertEquals(Long.valueOf(
				existingCPDefinitionOptionRel.getGroupId()),
			ReflectionTestUtil.<Long>invoke(existingCPDefinitionOptionRel,
				"getOriginalGroupId", new Class<?>[0]));
	}

	protected CPDefinitionOptionRel addCPDefinitionOptionRel()
		throws Exception {
		long pk = RandomTestUtil.nextLong();

		CPDefinitionOptionRel cpDefinitionOptionRel = _persistence.create(pk);

		cpDefinitionOptionRel.setUuid(RandomTestUtil.randomString());

		cpDefinitionOptionRel.setGroupId(RandomTestUtil.nextLong());

		cpDefinitionOptionRel.setCompanyId(RandomTestUtil.nextLong());

		cpDefinitionOptionRel.setUserId(RandomTestUtil.nextLong());

		cpDefinitionOptionRel.setUserName(RandomTestUtil.randomString());

		cpDefinitionOptionRel.setCreateDate(RandomTestUtil.nextDate());

		cpDefinitionOptionRel.setModifiedDate(RandomTestUtil.nextDate());

		cpDefinitionOptionRel.setCPDefinitionId(RandomTestUtil.nextLong());

		cpDefinitionOptionRel.setCPOptionId(RandomTestUtil.nextLong());

		cpDefinitionOptionRel.setName(RandomTestUtil.randomString());

		cpDefinitionOptionRel.setDescription(RandomTestUtil.randomString());

		cpDefinitionOptionRel.setDDMFormFieldTypeName(RandomTestUtil.randomString());

		cpDefinitionOptionRel.setPriority(RandomTestUtil.nextDouble());

		cpDefinitionOptionRel.setFacetable(RandomTestUtil.randomBoolean());

		cpDefinitionOptionRel.setRequired(RandomTestUtil.randomBoolean());

		cpDefinitionOptionRel.setSkuContributor(RandomTestUtil.randomBoolean());

		_cpDefinitionOptionRels.add(_persistence.update(cpDefinitionOptionRel));

		return cpDefinitionOptionRel;
	}

	private List<CPDefinitionOptionRel> _cpDefinitionOptionRels = new ArrayList<CPDefinitionOptionRel>();
	private CPDefinitionOptionRelPersistence _persistence;
	private ClassLoader _dynamicQueryClassLoader;
}