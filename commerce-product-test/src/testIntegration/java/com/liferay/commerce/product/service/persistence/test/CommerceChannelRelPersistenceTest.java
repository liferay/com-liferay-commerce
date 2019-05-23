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

import com.liferay.commerce.product.exception.NoSuchChannelRelException;
import com.liferay.commerce.product.model.CommerceChannelRel;
import com.liferay.commerce.product.service.CommerceChannelRelLocalServiceUtil;
import com.liferay.commerce.product.service.persistence.CommerceChannelRelPersistence;
import com.liferay.commerce.product.service.persistence.CommerceChannelRelUtil;

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
public class CommerceChannelRelPersistenceTest {
	@ClassRule
	@Rule
	public static final AggregateTestRule aggregateTestRule = new AggregateTestRule(new LiferayIntegrationTestRule(),
			PersistenceTestRule.INSTANCE,
			new TransactionalTestRule(Propagation.REQUIRED,
				"com.liferay.commerce.product.service"));

	@Before
	public void setUp() {
		_persistence = CommerceChannelRelUtil.getPersistence();

		Class<?> clazz = _persistence.getClass();

		_dynamicQueryClassLoader = clazz.getClassLoader();
	}

	@After
	public void tearDown() throws Exception {
		Iterator<CommerceChannelRel> iterator = _commerceChannelRels.iterator();

		while (iterator.hasNext()) {
			_persistence.remove(iterator.next());

			iterator.remove();
		}
	}

	@Test
	public void testCreate() throws Exception {
		long pk = RandomTestUtil.nextLong();

		CommerceChannelRel commerceChannelRel = _persistence.create(pk);

		Assert.assertNotNull(commerceChannelRel);

		Assert.assertEquals(commerceChannelRel.getPrimaryKey(), pk);
	}

	@Test
	public void testRemove() throws Exception {
		CommerceChannelRel newCommerceChannelRel = addCommerceChannelRel();

		_persistence.remove(newCommerceChannelRel);

		CommerceChannelRel existingCommerceChannelRel = _persistence.fetchByPrimaryKey(newCommerceChannelRel.getPrimaryKey());

		Assert.assertNull(existingCommerceChannelRel);
	}

	@Test
	public void testUpdateNew() throws Exception {
		addCommerceChannelRel();
	}

	@Test
	public void testUpdateExisting() throws Exception {
		long pk = RandomTestUtil.nextLong();

		CommerceChannelRel newCommerceChannelRel = _persistence.create(pk);

		newCommerceChannelRel.setCompanyId(RandomTestUtil.nextLong());

		newCommerceChannelRel.setUserId(RandomTestUtil.nextLong());

		newCommerceChannelRel.setUserName(RandomTestUtil.randomString());

		newCommerceChannelRel.setCreateDate(RandomTestUtil.nextDate());

		newCommerceChannelRel.setModifiedDate(RandomTestUtil.nextDate());

		newCommerceChannelRel.setClassNameId(RandomTestUtil.nextLong());

		newCommerceChannelRel.setClassPK(RandomTestUtil.nextLong());

		newCommerceChannelRel.setCommerceChannelId(RandomTestUtil.nextLong());

		_commerceChannelRels.add(_persistence.update(newCommerceChannelRel));

		CommerceChannelRel existingCommerceChannelRel = _persistence.findByPrimaryKey(newCommerceChannelRel.getPrimaryKey());

		Assert.assertEquals(existingCommerceChannelRel.getCommerceChannelRelId(),
			newCommerceChannelRel.getCommerceChannelRelId());
		Assert.assertEquals(existingCommerceChannelRel.getCompanyId(),
			newCommerceChannelRel.getCompanyId());
		Assert.assertEquals(existingCommerceChannelRel.getUserId(),
			newCommerceChannelRel.getUserId());
		Assert.assertEquals(existingCommerceChannelRel.getUserName(),
			newCommerceChannelRel.getUserName());
		Assert.assertEquals(Time.getShortTimestamp(
				existingCommerceChannelRel.getCreateDate()),
			Time.getShortTimestamp(newCommerceChannelRel.getCreateDate()));
		Assert.assertEquals(Time.getShortTimestamp(
				existingCommerceChannelRel.getModifiedDate()),
			Time.getShortTimestamp(newCommerceChannelRel.getModifiedDate()));
		Assert.assertEquals(existingCommerceChannelRel.getClassNameId(),
			newCommerceChannelRel.getClassNameId());
		Assert.assertEquals(existingCommerceChannelRel.getClassPK(),
			newCommerceChannelRel.getClassPK());
		Assert.assertEquals(existingCommerceChannelRel.getCommerceChannelId(),
			newCommerceChannelRel.getCommerceChannelId());
	}

	@Test
	public void testCountByCommerceChannelId() throws Exception {
		_persistence.countByCommerceChannelId(RandomTestUtil.nextLong());

		_persistence.countByCommerceChannelId(0L);
	}

	@Test
	public void testCountByC_C() throws Exception {
		_persistence.countByC_C(RandomTestUtil.nextLong(),
			RandomTestUtil.nextLong());

		_persistence.countByC_C(0L, 0L);
	}

	@Test
	public void testCountByC_C_C() throws Exception {
		_persistence.countByC_C_C(RandomTestUtil.nextLong(),
			RandomTestUtil.nextLong(), RandomTestUtil.nextLong());

		_persistence.countByC_C_C(0L, 0L, 0L);
	}

	@Test
	public void testFindByPrimaryKeyExisting() throws Exception {
		CommerceChannelRel newCommerceChannelRel = addCommerceChannelRel();

		CommerceChannelRel existingCommerceChannelRel = _persistence.findByPrimaryKey(newCommerceChannelRel.getPrimaryKey());

		Assert.assertEquals(existingCommerceChannelRel, newCommerceChannelRel);
	}

	@Test(expected = NoSuchChannelRelException.class)
	public void testFindByPrimaryKeyMissing() throws Exception {
		long pk = RandomTestUtil.nextLong();

		_persistence.findByPrimaryKey(pk);
	}

	@Test
	public void testFindAll() throws Exception {
		_persistence.findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS,
			getOrderByComparator());
	}

	protected OrderByComparator<CommerceChannelRel> getOrderByComparator() {
		return OrderByComparatorFactoryUtil.create("CommerceChannelRel",
			"commerceChannelRelId", true, "companyId", true, "userId", true,
			"userName", true, "createDate", true, "modifiedDate", true,
			"classNameId", true, "classPK", true, "commerceChannelId", true);
	}

	@Test
	public void testFetchByPrimaryKeyExisting() throws Exception {
		CommerceChannelRel newCommerceChannelRel = addCommerceChannelRel();

		CommerceChannelRel existingCommerceChannelRel = _persistence.fetchByPrimaryKey(newCommerceChannelRel.getPrimaryKey());

		Assert.assertEquals(existingCommerceChannelRel, newCommerceChannelRel);
	}

	@Test
	public void testFetchByPrimaryKeyMissing() throws Exception {
		long pk = RandomTestUtil.nextLong();

		CommerceChannelRel missingCommerceChannelRel = _persistence.fetchByPrimaryKey(pk);

		Assert.assertNull(missingCommerceChannelRel);
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereAllPrimaryKeysExist()
		throws Exception {
		CommerceChannelRel newCommerceChannelRel1 = addCommerceChannelRel();
		CommerceChannelRel newCommerceChannelRel2 = addCommerceChannelRel();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newCommerceChannelRel1.getPrimaryKey());
		primaryKeys.add(newCommerceChannelRel2.getPrimaryKey());

		Map<Serializable, CommerceChannelRel> commerceChannelRels = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(2, commerceChannelRels.size());
		Assert.assertEquals(newCommerceChannelRel1,
			commerceChannelRels.get(newCommerceChannelRel1.getPrimaryKey()));
		Assert.assertEquals(newCommerceChannelRel2,
			commerceChannelRels.get(newCommerceChannelRel2.getPrimaryKey()));
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereNoPrimaryKeysExist()
		throws Exception {
		long pk1 = RandomTestUtil.nextLong();

		long pk2 = RandomTestUtil.nextLong();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(pk1);
		primaryKeys.add(pk2);

		Map<Serializable, CommerceChannelRel> commerceChannelRels = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertTrue(commerceChannelRels.isEmpty());
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereSomePrimaryKeysExist()
		throws Exception {
		CommerceChannelRel newCommerceChannelRel = addCommerceChannelRel();

		long pk = RandomTestUtil.nextLong();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newCommerceChannelRel.getPrimaryKey());
		primaryKeys.add(pk);

		Map<Serializable, CommerceChannelRel> commerceChannelRels = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(1, commerceChannelRels.size());
		Assert.assertEquals(newCommerceChannelRel,
			commerceChannelRels.get(newCommerceChannelRel.getPrimaryKey()));
	}

	@Test
	public void testFetchByPrimaryKeysWithNoPrimaryKeys()
		throws Exception {
		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		Map<Serializable, CommerceChannelRel> commerceChannelRels = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertTrue(commerceChannelRels.isEmpty());
	}

	@Test
	public void testFetchByPrimaryKeysWithOnePrimaryKey()
		throws Exception {
		CommerceChannelRel newCommerceChannelRel = addCommerceChannelRel();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newCommerceChannelRel.getPrimaryKey());

		Map<Serializable, CommerceChannelRel> commerceChannelRels = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(1, commerceChannelRels.size());
		Assert.assertEquals(newCommerceChannelRel,
			commerceChannelRels.get(newCommerceChannelRel.getPrimaryKey()));
	}

	@Test
	public void testActionableDynamicQuery() throws Exception {
		final IntegerWrapper count = new IntegerWrapper();

		ActionableDynamicQuery actionableDynamicQuery = CommerceChannelRelLocalServiceUtil.getActionableDynamicQuery();

		actionableDynamicQuery.setPerformActionMethod(new ActionableDynamicQuery.PerformActionMethod<CommerceChannelRel>() {
				@Override
				public void performAction(CommerceChannelRel commerceChannelRel) {
					Assert.assertNotNull(commerceChannelRel);

					count.increment();
				}
			});

		actionableDynamicQuery.performActions();

		Assert.assertEquals(count.getValue(), _persistence.countAll());
	}

	@Test
	public void testDynamicQueryByPrimaryKeyExisting()
		throws Exception {
		CommerceChannelRel newCommerceChannelRel = addCommerceChannelRel();

		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(CommerceChannelRel.class,
				_dynamicQueryClassLoader);

		dynamicQuery.add(RestrictionsFactoryUtil.eq("commerceChannelRelId",
				newCommerceChannelRel.getCommerceChannelRelId()));

		List<CommerceChannelRel> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(1, result.size());

		CommerceChannelRel existingCommerceChannelRel = result.get(0);

		Assert.assertEquals(existingCommerceChannelRel, newCommerceChannelRel);
	}

	@Test
	public void testDynamicQueryByPrimaryKeyMissing() throws Exception {
		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(CommerceChannelRel.class,
				_dynamicQueryClassLoader);

		dynamicQuery.add(RestrictionsFactoryUtil.eq("commerceChannelRelId",
				RandomTestUtil.nextLong()));

		List<CommerceChannelRel> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(0, result.size());
	}

	@Test
	public void testDynamicQueryByProjectionExisting()
		throws Exception {
		CommerceChannelRel newCommerceChannelRel = addCommerceChannelRel();

		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(CommerceChannelRel.class,
				_dynamicQueryClassLoader);

		dynamicQuery.setProjection(ProjectionFactoryUtil.property(
				"commerceChannelRelId"));

		Object newCommerceChannelRelId = newCommerceChannelRel.getCommerceChannelRelId();

		dynamicQuery.add(RestrictionsFactoryUtil.in("commerceChannelRelId",
				new Object[] { newCommerceChannelRelId }));

		List<Object> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(1, result.size());

		Object existingCommerceChannelRelId = result.get(0);

		Assert.assertEquals(existingCommerceChannelRelId,
			newCommerceChannelRelId);
	}

	@Test
	public void testDynamicQueryByProjectionMissing() throws Exception {
		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(CommerceChannelRel.class,
				_dynamicQueryClassLoader);

		dynamicQuery.setProjection(ProjectionFactoryUtil.property(
				"commerceChannelRelId"));

		dynamicQuery.add(RestrictionsFactoryUtil.in("commerceChannelRelId",
				new Object[] { RandomTestUtil.nextLong() }));

		List<Object> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(0, result.size());
	}

	@Test
	public void testResetOriginalValues() throws Exception {
		CommerceChannelRel newCommerceChannelRel = addCommerceChannelRel();

		_persistence.clearCache();

		CommerceChannelRel existingCommerceChannelRel = _persistence.findByPrimaryKey(newCommerceChannelRel.getPrimaryKey());

		Assert.assertEquals(Long.valueOf(
				existingCommerceChannelRel.getClassNameId()),
			ReflectionTestUtil.<Long>invoke(existingCommerceChannelRel,
				"getOriginalClassNameId", new Class<?>[0]));
		Assert.assertEquals(Long.valueOf(
				existingCommerceChannelRel.getClassPK()),
			ReflectionTestUtil.<Long>invoke(existingCommerceChannelRel,
				"getOriginalClassPK", new Class<?>[0]));
		Assert.assertEquals(Long.valueOf(
				existingCommerceChannelRel.getCommerceChannelId()),
			ReflectionTestUtil.<Long>invoke(existingCommerceChannelRel,
				"getOriginalCommerceChannelId", new Class<?>[0]));
	}

	protected CommerceChannelRel addCommerceChannelRel()
		throws Exception {
		long pk = RandomTestUtil.nextLong();

		CommerceChannelRel commerceChannelRel = _persistence.create(pk);

		commerceChannelRel.setCompanyId(RandomTestUtil.nextLong());

		commerceChannelRel.setUserId(RandomTestUtil.nextLong());

		commerceChannelRel.setUserName(RandomTestUtil.randomString());

		commerceChannelRel.setCreateDate(RandomTestUtil.nextDate());

		commerceChannelRel.setModifiedDate(RandomTestUtil.nextDate());

		commerceChannelRel.setClassNameId(RandomTestUtil.nextLong());

		commerceChannelRel.setClassPK(RandomTestUtil.nextLong());

		commerceChannelRel.setCommerceChannelId(RandomTestUtil.nextLong());

		_commerceChannelRels.add(_persistence.update(commerceChannelRel));

		return commerceChannelRel;
	}

	private List<CommerceChannelRel> _commerceChannelRels = new ArrayList<CommerceChannelRel>();
	private CommerceChannelRelPersistence _persistence;
	private ClassLoader _dynamicQueryClassLoader;
}