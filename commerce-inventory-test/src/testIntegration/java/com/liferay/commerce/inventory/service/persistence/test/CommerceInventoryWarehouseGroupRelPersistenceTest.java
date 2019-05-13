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

package com.liferay.commerce.inventory.service.persistence.test;

import com.liferay.arquillian.extension.junit.bridge.junit.Arquillian;

import com.liferay.commerce.inventory.exception.NoSuchInventoryWarehouseGroupRelException;
import com.liferay.commerce.inventory.model.CommerceInventoryWarehouseGroupRel;
import com.liferay.commerce.inventory.service.CommerceInventoryWarehouseGroupRelLocalServiceUtil;
import com.liferay.commerce.inventory.service.persistence.CommerceInventoryWarehouseGroupRelPersistence;
import com.liferay.commerce.inventory.service.persistence.CommerceInventoryWarehouseGroupRelUtil;

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
import java.util.Set;

/**
 * @generated
 */
@RunWith(Arquillian.class)
public class CommerceInventoryWarehouseGroupRelPersistenceTest {
	@ClassRule
	@Rule
	public static final AggregateTestRule aggregateTestRule = new AggregateTestRule(new LiferayIntegrationTestRule(),
			PersistenceTestRule.INSTANCE,
			new TransactionalTestRule(Propagation.REQUIRED,
				"com.liferay.commerce.inventory.service"));

	@Before
	public void setUp() {
		_persistence = CommerceInventoryWarehouseGroupRelUtil.getPersistence();

		Class<?> clazz = _persistence.getClass();

		_dynamicQueryClassLoader = clazz.getClassLoader();
	}

	@After
	public void tearDown() throws Exception {
		Iterator<CommerceInventoryWarehouseGroupRel> iterator = _commerceInventoryWarehouseGroupRels.iterator();

		while (iterator.hasNext()) {
			_persistence.remove(iterator.next());

			iterator.remove();
		}
	}

	@Test
	public void testCreate() throws Exception {
		long pk = RandomTestUtil.nextLong();

		CommerceInventoryWarehouseGroupRel commerceInventoryWarehouseGroupRel = _persistence.create(pk);

		Assert.assertNotNull(commerceInventoryWarehouseGroupRel);

		Assert.assertEquals(commerceInventoryWarehouseGroupRel.getPrimaryKey(),
			pk);
	}

	@Test
	public void testRemove() throws Exception {
		CommerceInventoryWarehouseGroupRel newCommerceInventoryWarehouseGroupRel =
			addCommerceInventoryWarehouseGroupRel();

		_persistence.remove(newCommerceInventoryWarehouseGroupRel);

		CommerceInventoryWarehouseGroupRel existingCommerceInventoryWarehouseGroupRel =
			_persistence.fetchByPrimaryKey(newCommerceInventoryWarehouseGroupRel.getPrimaryKey());

		Assert.assertNull(existingCommerceInventoryWarehouseGroupRel);
	}

	@Test
	public void testUpdateNew() throws Exception {
		addCommerceInventoryWarehouseGroupRel();
	}

	@Test
	public void testUpdateExisting() throws Exception {
		long pk = RandomTestUtil.nextLong();

		CommerceInventoryWarehouseGroupRel newCommerceInventoryWarehouseGroupRel =
			_persistence.create(pk);

		newCommerceInventoryWarehouseGroupRel.setGroupId(RandomTestUtil.nextLong());

		newCommerceInventoryWarehouseGroupRel.setCompanyId(RandomTestUtil.nextLong());

		newCommerceInventoryWarehouseGroupRel.setUserId(RandomTestUtil.nextLong());

		newCommerceInventoryWarehouseGroupRel.setUserName(RandomTestUtil.randomString());

		newCommerceInventoryWarehouseGroupRel.setCreateDate(RandomTestUtil.nextDate());

		newCommerceInventoryWarehouseGroupRel.setModifiedDate(RandomTestUtil.nextDate());

		newCommerceInventoryWarehouseGroupRel.setCommerceWarehouseId(RandomTestUtil.nextLong());

		newCommerceInventoryWarehouseGroupRel.setPrimary(RandomTestUtil.randomBoolean());

		_commerceInventoryWarehouseGroupRels.add(_persistence.update(
				newCommerceInventoryWarehouseGroupRel));

		CommerceInventoryWarehouseGroupRel existingCommerceInventoryWarehouseGroupRel =
			_persistence.findByPrimaryKey(newCommerceInventoryWarehouseGroupRel.getPrimaryKey());

		Assert.assertEquals(existingCommerceInventoryWarehouseGroupRel.getCommerceInventoryWarehouseGroupRelId(),
			newCommerceInventoryWarehouseGroupRel.getCommerceInventoryWarehouseGroupRelId());
		Assert.assertEquals(existingCommerceInventoryWarehouseGroupRel.getGroupId(),
			newCommerceInventoryWarehouseGroupRel.getGroupId());
		Assert.assertEquals(existingCommerceInventoryWarehouseGroupRel.getCompanyId(),
			newCommerceInventoryWarehouseGroupRel.getCompanyId());
		Assert.assertEquals(existingCommerceInventoryWarehouseGroupRel.getUserId(),
			newCommerceInventoryWarehouseGroupRel.getUserId());
		Assert.assertEquals(existingCommerceInventoryWarehouseGroupRel.getUserName(),
			newCommerceInventoryWarehouseGroupRel.getUserName());
		Assert.assertEquals(Time.getShortTimestamp(
				existingCommerceInventoryWarehouseGroupRel.getCreateDate()),
			Time.getShortTimestamp(
				newCommerceInventoryWarehouseGroupRel.getCreateDate()));
		Assert.assertEquals(Time.getShortTimestamp(
				existingCommerceInventoryWarehouseGroupRel.getModifiedDate()),
			Time.getShortTimestamp(
				newCommerceInventoryWarehouseGroupRel.getModifiedDate()));
		Assert.assertEquals(existingCommerceInventoryWarehouseGroupRel.getCommerceWarehouseId(),
			newCommerceInventoryWarehouseGroupRel.getCommerceWarehouseId());
		Assert.assertEquals(existingCommerceInventoryWarehouseGroupRel.isPrimary(),
			newCommerceInventoryWarehouseGroupRel.isPrimary());
	}

	@Test
	public void testCountBygroupId() throws Exception {
		_persistence.countBygroupId(RandomTestUtil.nextLong());

		_persistence.countBygroupId(0L);
	}

	@Test
	public void testCountByG_CWI() throws Exception {
		_persistence.countByG_CWI(RandomTestUtil.nextLong(),
			RandomTestUtil.nextLong());

		_persistence.countByG_CWI(0L, 0L);
	}

	@Test
	public void testCountByG_P() throws Exception {
		_persistence.countByG_P(RandomTestUtil.nextLong(),
			RandomTestUtil.randomBoolean());

		_persistence.countByG_P(0L, RandomTestUtil.randomBoolean());
	}

	@Test
	public void testCountByG_CWI_P() throws Exception {
		_persistence.countByG_CWI_P(RandomTestUtil.nextLong(),
			RandomTestUtil.nextLong(), RandomTestUtil.randomBoolean());

		_persistence.countByG_CWI_P(0L, 0L, RandomTestUtil.randomBoolean());
	}

	@Test
	public void testFindByPrimaryKeyExisting() throws Exception {
		CommerceInventoryWarehouseGroupRel newCommerceInventoryWarehouseGroupRel =
			addCommerceInventoryWarehouseGroupRel();

		CommerceInventoryWarehouseGroupRel existingCommerceInventoryWarehouseGroupRel =
			_persistence.findByPrimaryKey(newCommerceInventoryWarehouseGroupRel.getPrimaryKey());

		Assert.assertEquals(existingCommerceInventoryWarehouseGroupRel,
			newCommerceInventoryWarehouseGroupRel);
	}

	@Test(expected = NoSuchInventoryWarehouseGroupRelException.class)
	public void testFindByPrimaryKeyMissing() throws Exception {
		long pk = RandomTestUtil.nextLong();

		_persistence.findByPrimaryKey(pk);
	}

	@Test
	public void testFindAll() throws Exception {
		_persistence.findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS,
			getOrderByComparator());
	}

	protected OrderByComparator<CommerceInventoryWarehouseGroupRel> getOrderByComparator() {
		return OrderByComparatorFactoryUtil.create("CIWarehouseGroupRel",
			"commerceInventoryWarehouseGroupRelId", true, "groupId", true,
			"companyId", true, "userId", true, "userName", true, "createDate",
			true, "modifiedDate", true, "commerceWarehouseId", true, "primary",
			true);
	}

	@Test
	public void testFetchByPrimaryKeyExisting() throws Exception {
		CommerceInventoryWarehouseGroupRel newCommerceInventoryWarehouseGroupRel =
			addCommerceInventoryWarehouseGroupRel();

		CommerceInventoryWarehouseGroupRel existingCommerceInventoryWarehouseGroupRel =
			_persistence.fetchByPrimaryKey(newCommerceInventoryWarehouseGroupRel.getPrimaryKey());

		Assert.assertEquals(existingCommerceInventoryWarehouseGroupRel,
			newCommerceInventoryWarehouseGroupRel);
	}

	@Test
	public void testFetchByPrimaryKeyMissing() throws Exception {
		long pk = RandomTestUtil.nextLong();

		CommerceInventoryWarehouseGroupRel missingCommerceInventoryWarehouseGroupRel =
			_persistence.fetchByPrimaryKey(pk);

		Assert.assertNull(missingCommerceInventoryWarehouseGroupRel);
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereAllPrimaryKeysExist()
		throws Exception {
		CommerceInventoryWarehouseGroupRel newCommerceInventoryWarehouseGroupRel1 =
			addCommerceInventoryWarehouseGroupRel();
		CommerceInventoryWarehouseGroupRel newCommerceInventoryWarehouseGroupRel2 =
			addCommerceInventoryWarehouseGroupRel();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newCommerceInventoryWarehouseGroupRel1.getPrimaryKey());
		primaryKeys.add(newCommerceInventoryWarehouseGroupRel2.getPrimaryKey());

		Map<Serializable, CommerceInventoryWarehouseGroupRel> commerceInventoryWarehouseGroupRels =
			_persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(2, commerceInventoryWarehouseGroupRels.size());
		Assert.assertEquals(newCommerceInventoryWarehouseGroupRel1,
			commerceInventoryWarehouseGroupRels.get(
				newCommerceInventoryWarehouseGroupRel1.getPrimaryKey()));
		Assert.assertEquals(newCommerceInventoryWarehouseGroupRel2,
			commerceInventoryWarehouseGroupRels.get(
				newCommerceInventoryWarehouseGroupRel2.getPrimaryKey()));
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereNoPrimaryKeysExist()
		throws Exception {
		long pk1 = RandomTestUtil.nextLong();

		long pk2 = RandomTestUtil.nextLong();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(pk1);
		primaryKeys.add(pk2);

		Map<Serializable, CommerceInventoryWarehouseGroupRel> commerceInventoryWarehouseGroupRels =
			_persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertTrue(commerceInventoryWarehouseGroupRels.isEmpty());
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereSomePrimaryKeysExist()
		throws Exception {
		CommerceInventoryWarehouseGroupRel newCommerceInventoryWarehouseGroupRel =
			addCommerceInventoryWarehouseGroupRel();

		long pk = RandomTestUtil.nextLong();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newCommerceInventoryWarehouseGroupRel.getPrimaryKey());
		primaryKeys.add(pk);

		Map<Serializable, CommerceInventoryWarehouseGroupRel> commerceInventoryWarehouseGroupRels =
			_persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(1, commerceInventoryWarehouseGroupRels.size());
		Assert.assertEquals(newCommerceInventoryWarehouseGroupRel,
			commerceInventoryWarehouseGroupRels.get(
				newCommerceInventoryWarehouseGroupRel.getPrimaryKey()));
	}

	@Test
	public void testFetchByPrimaryKeysWithNoPrimaryKeys()
		throws Exception {
		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		Map<Serializable, CommerceInventoryWarehouseGroupRel> commerceInventoryWarehouseGroupRels =
			_persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertTrue(commerceInventoryWarehouseGroupRels.isEmpty());
	}

	@Test
	public void testFetchByPrimaryKeysWithOnePrimaryKey()
		throws Exception {
		CommerceInventoryWarehouseGroupRel newCommerceInventoryWarehouseGroupRel =
			addCommerceInventoryWarehouseGroupRel();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newCommerceInventoryWarehouseGroupRel.getPrimaryKey());

		Map<Serializable, CommerceInventoryWarehouseGroupRel> commerceInventoryWarehouseGroupRels =
			_persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(1, commerceInventoryWarehouseGroupRels.size());
		Assert.assertEquals(newCommerceInventoryWarehouseGroupRel,
			commerceInventoryWarehouseGroupRels.get(
				newCommerceInventoryWarehouseGroupRel.getPrimaryKey()));
	}

	@Test
	public void testActionableDynamicQuery() throws Exception {
		final IntegerWrapper count = new IntegerWrapper();

		ActionableDynamicQuery actionableDynamicQuery = CommerceInventoryWarehouseGroupRelLocalServiceUtil.getActionableDynamicQuery();

		actionableDynamicQuery.setPerformActionMethod(new ActionableDynamicQuery.PerformActionMethod<CommerceInventoryWarehouseGroupRel>() {
				@Override
				public void performAction(
					CommerceInventoryWarehouseGroupRel commerceInventoryWarehouseGroupRel) {
					Assert.assertNotNull(commerceInventoryWarehouseGroupRel);

					count.increment();
				}
			});

		actionableDynamicQuery.performActions();

		Assert.assertEquals(count.getValue(), _persistence.countAll());
	}

	@Test
	public void testDynamicQueryByPrimaryKeyExisting()
		throws Exception {
		CommerceInventoryWarehouseGroupRel newCommerceInventoryWarehouseGroupRel =
			addCommerceInventoryWarehouseGroupRel();

		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(CommerceInventoryWarehouseGroupRel.class,
				_dynamicQueryClassLoader);

		dynamicQuery.add(RestrictionsFactoryUtil.eq(
				"commerceInventoryWarehouseGroupRelId",
				newCommerceInventoryWarehouseGroupRel.getCommerceInventoryWarehouseGroupRelId()));

		List<CommerceInventoryWarehouseGroupRel> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(1, result.size());

		CommerceInventoryWarehouseGroupRel existingCommerceInventoryWarehouseGroupRel =
			result.get(0);

		Assert.assertEquals(existingCommerceInventoryWarehouseGroupRel,
			newCommerceInventoryWarehouseGroupRel);
	}

	@Test
	public void testDynamicQueryByPrimaryKeyMissing() throws Exception {
		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(CommerceInventoryWarehouseGroupRel.class,
				_dynamicQueryClassLoader);

		dynamicQuery.add(RestrictionsFactoryUtil.eq(
				"commerceInventoryWarehouseGroupRelId",
				RandomTestUtil.nextLong()));

		List<CommerceInventoryWarehouseGroupRel> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(0, result.size());
	}

	@Test
	public void testDynamicQueryByProjectionExisting()
		throws Exception {
		CommerceInventoryWarehouseGroupRel newCommerceInventoryWarehouseGroupRel =
			addCommerceInventoryWarehouseGroupRel();

		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(CommerceInventoryWarehouseGroupRel.class,
				_dynamicQueryClassLoader);

		dynamicQuery.setProjection(ProjectionFactoryUtil.property(
				"commerceInventoryWarehouseGroupRelId"));

		Object newCommerceInventoryWarehouseGroupRelId = newCommerceInventoryWarehouseGroupRel.getCommerceInventoryWarehouseGroupRelId();

		dynamicQuery.add(RestrictionsFactoryUtil.in(
				"commerceInventoryWarehouseGroupRelId",
				new Object[] { newCommerceInventoryWarehouseGroupRelId }));

		List<Object> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(1, result.size());

		Object existingCommerceInventoryWarehouseGroupRelId = result.get(0);

		Assert.assertEquals(existingCommerceInventoryWarehouseGroupRelId,
			newCommerceInventoryWarehouseGroupRelId);
	}

	@Test
	public void testDynamicQueryByProjectionMissing() throws Exception {
		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(CommerceInventoryWarehouseGroupRel.class,
				_dynamicQueryClassLoader);

		dynamicQuery.setProjection(ProjectionFactoryUtil.property(
				"commerceInventoryWarehouseGroupRelId"));

		dynamicQuery.add(RestrictionsFactoryUtil.in(
				"commerceInventoryWarehouseGroupRelId",
				new Object[] { RandomTestUtil.nextLong() }));

		List<Object> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(0, result.size());
	}

	@Test
	public void testResetOriginalValues() throws Exception {
		CommerceInventoryWarehouseGroupRel newCommerceInventoryWarehouseGroupRel =
			addCommerceInventoryWarehouseGroupRel();

		_persistence.clearCache();

		CommerceInventoryWarehouseGroupRel existingCommerceInventoryWarehouseGroupRel =
			_persistence.findByPrimaryKey(newCommerceInventoryWarehouseGroupRel.getPrimaryKey());

		Assert.assertEquals(Long.valueOf(
				existingCommerceInventoryWarehouseGroupRel.getGroupId()),
			ReflectionTestUtil.<Long>invoke(
				existingCommerceInventoryWarehouseGroupRel,
				"getOriginalGroupId", new Class<?>[0]));
		Assert.assertEquals(Long.valueOf(
				existingCommerceInventoryWarehouseGroupRel.getCommerceWarehouseId()),
			ReflectionTestUtil.<Long>invoke(
				existingCommerceInventoryWarehouseGroupRel,
				"getOriginalCommerceWarehouseId", new Class<?>[0]));

		Assert.assertEquals(Long.valueOf(
				existingCommerceInventoryWarehouseGroupRel.getGroupId()),
			ReflectionTestUtil.<Long>invoke(
				existingCommerceInventoryWarehouseGroupRel,
				"getOriginalGroupId", new Class<?>[0]));
		Assert.assertEquals(Long.valueOf(
				existingCommerceInventoryWarehouseGroupRel.getCommerceWarehouseId()),
			ReflectionTestUtil.<Long>invoke(
				existingCommerceInventoryWarehouseGroupRel,
				"getOriginalCommerceWarehouseId", new Class<?>[0]));
		Assert.assertEquals(Boolean.valueOf(
				existingCommerceInventoryWarehouseGroupRel.getPrimary()),
			ReflectionTestUtil.<Boolean>invoke(
				existingCommerceInventoryWarehouseGroupRel,
				"getOriginalPrimary", new Class<?>[0]));
	}

	protected CommerceInventoryWarehouseGroupRel addCommerceInventoryWarehouseGroupRel()
		throws Exception {
		long pk = RandomTestUtil.nextLong();

		CommerceInventoryWarehouseGroupRel commerceInventoryWarehouseGroupRel = _persistence.create(pk);

		commerceInventoryWarehouseGroupRel.setGroupId(RandomTestUtil.nextLong());

		commerceInventoryWarehouseGroupRel.setCompanyId(RandomTestUtil.nextLong());

		commerceInventoryWarehouseGroupRel.setUserId(RandomTestUtil.nextLong());

		commerceInventoryWarehouseGroupRel.setUserName(RandomTestUtil.randomString());

		commerceInventoryWarehouseGroupRel.setCreateDate(RandomTestUtil.nextDate());

		commerceInventoryWarehouseGroupRel.setModifiedDate(RandomTestUtil.nextDate());

		commerceInventoryWarehouseGroupRel.setCommerceWarehouseId(RandomTestUtil.nextLong());

		commerceInventoryWarehouseGroupRel.setPrimary(RandomTestUtil.randomBoolean());

		_commerceInventoryWarehouseGroupRels.add(_persistence.update(
				commerceInventoryWarehouseGroupRel));

		return commerceInventoryWarehouseGroupRel;
	}

	private List<CommerceInventoryWarehouseGroupRel> _commerceInventoryWarehouseGroupRels =
		new ArrayList<CommerceInventoryWarehouseGroupRel>();
	private CommerceInventoryWarehouseGroupRelPersistence _persistence;
	private ClassLoader _dynamicQueryClassLoader;
}