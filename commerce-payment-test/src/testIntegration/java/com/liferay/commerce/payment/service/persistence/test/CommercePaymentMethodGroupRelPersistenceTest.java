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

package com.liferay.commerce.payment.service.persistence.test;

import com.liferay.arquillian.extension.junit.bridge.junit.Arquillian;
import com.liferay.commerce.payment.exception.NoSuchPaymentMethodGroupRelException;
import com.liferay.commerce.payment.model.CommercePaymentMethodGroupRel;
import com.liferay.commerce.payment.service.CommercePaymentMethodGroupRelLocalServiceUtil;
import com.liferay.commerce.payment.service.persistence.CommercePaymentMethodGroupRelPersistence;
import com.liferay.commerce.payment.service.persistence.CommercePaymentMethodGroupRelUtil;
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
public class CommercePaymentMethodGroupRelPersistenceTest {

	@ClassRule
	@Rule
	public static final AggregateTestRule aggregateTestRule =
		new AggregateTestRule(
			new LiferayIntegrationTestRule(), PersistenceTestRule.INSTANCE,
			new TransactionalTestRule(
				Propagation.REQUIRED, "com.liferay.commerce.payment.service"));

	@Before
	public void setUp() {
		_persistence = CommercePaymentMethodGroupRelUtil.getPersistence();

		Class<?> clazz = _persistence.getClass();

		_dynamicQueryClassLoader = clazz.getClassLoader();
	}

	@After
	public void tearDown() throws Exception {
		Iterator<CommercePaymentMethodGroupRel> iterator =
			_commercePaymentMethodGroupRels.iterator();

		while (iterator.hasNext()) {
			_persistence.remove(iterator.next());

			iterator.remove();
		}
	}

	@Test
	public void testCreate() throws Exception {
		long pk = RandomTestUtil.nextLong();

		CommercePaymentMethodGroupRel commercePaymentMethodGroupRel =
			_persistence.create(pk);

		Assert.assertNotNull(commercePaymentMethodGroupRel);

		Assert.assertEquals(commercePaymentMethodGroupRel.getPrimaryKey(), pk);
	}

	@Test
	public void testRemove() throws Exception {
		CommercePaymentMethodGroupRel newCommercePaymentMethodGroupRel =
			addCommercePaymentMethodGroupRel();

		_persistence.remove(newCommercePaymentMethodGroupRel);

		CommercePaymentMethodGroupRel existingCommercePaymentMethodGroupRel =
			_persistence.fetchByPrimaryKey(
				newCommercePaymentMethodGroupRel.getPrimaryKey());

		Assert.assertNull(existingCommercePaymentMethodGroupRel);
	}

	@Test
	public void testUpdateNew() throws Exception {
		addCommercePaymentMethodGroupRel();
	}

	@Test
	public void testUpdateExisting() throws Exception {
		long pk = RandomTestUtil.nextLong();

		CommercePaymentMethodGroupRel newCommercePaymentMethodGroupRel =
			_persistence.create(pk);

		newCommercePaymentMethodGroupRel.setGroupId(RandomTestUtil.nextLong());

		newCommercePaymentMethodGroupRel.setCompanyId(
			RandomTestUtil.nextLong());

		newCommercePaymentMethodGroupRel.setUserId(RandomTestUtil.nextLong());

		newCommercePaymentMethodGroupRel.setUserName(
			RandomTestUtil.randomString());

		newCommercePaymentMethodGroupRel.setCreateDate(
			RandomTestUtil.nextDate());

		newCommercePaymentMethodGroupRel.setModifiedDate(
			RandomTestUtil.nextDate());

		newCommercePaymentMethodGroupRel.setName(RandomTestUtil.randomString());

		newCommercePaymentMethodGroupRel.setDescription(
			RandomTestUtil.randomString());

		newCommercePaymentMethodGroupRel.setImageId(RandomTestUtil.nextLong());

		newCommercePaymentMethodGroupRel.setEngineKey(
			RandomTestUtil.randomString());

		newCommercePaymentMethodGroupRel.setPriority(
			RandomTestUtil.nextDouble());

		newCommercePaymentMethodGroupRel.setActive(
			RandomTestUtil.randomBoolean());

		_commercePaymentMethodGroupRels.add(
			_persistence.update(newCommercePaymentMethodGroupRel));

		CommercePaymentMethodGroupRel existingCommercePaymentMethodGroupRel =
			_persistence.findByPrimaryKey(
				newCommercePaymentMethodGroupRel.getPrimaryKey());

		Assert.assertEquals(
			existingCommercePaymentMethodGroupRel.
				getCommercePaymentMethodGroupRelId(),
			newCommercePaymentMethodGroupRel.
				getCommercePaymentMethodGroupRelId());
		Assert.assertEquals(
			existingCommercePaymentMethodGroupRel.getGroupId(),
			newCommercePaymentMethodGroupRel.getGroupId());
		Assert.assertEquals(
			existingCommercePaymentMethodGroupRel.getCompanyId(),
			newCommercePaymentMethodGroupRel.getCompanyId());
		Assert.assertEquals(
			existingCommercePaymentMethodGroupRel.getUserId(),
			newCommercePaymentMethodGroupRel.getUserId());
		Assert.assertEquals(
			existingCommercePaymentMethodGroupRel.getUserName(),
			newCommercePaymentMethodGroupRel.getUserName());
		Assert.assertEquals(
			Time.getShortTimestamp(
				existingCommercePaymentMethodGroupRel.getCreateDate()),
			Time.getShortTimestamp(
				newCommercePaymentMethodGroupRel.getCreateDate()));
		Assert.assertEquals(
			Time.getShortTimestamp(
				existingCommercePaymentMethodGroupRel.getModifiedDate()),
			Time.getShortTimestamp(
				newCommercePaymentMethodGroupRel.getModifiedDate()));
		Assert.assertEquals(
			existingCommercePaymentMethodGroupRel.getName(),
			newCommercePaymentMethodGroupRel.getName());
		Assert.assertEquals(
			existingCommercePaymentMethodGroupRel.getDescription(),
			newCommercePaymentMethodGroupRel.getDescription());
		Assert.assertEquals(
			existingCommercePaymentMethodGroupRel.getImageId(),
			newCommercePaymentMethodGroupRel.getImageId());
		Assert.assertEquals(
			existingCommercePaymentMethodGroupRel.getEngineKey(),
			newCommercePaymentMethodGroupRel.getEngineKey());
		AssertUtils.assertEquals(
			existingCommercePaymentMethodGroupRel.getPriority(),
			newCommercePaymentMethodGroupRel.getPriority());
		Assert.assertEquals(
			existingCommercePaymentMethodGroupRel.isActive(),
			newCommercePaymentMethodGroupRel.isActive());
	}

	@Test
	public void testCountByGroupId() throws Exception {
		_persistence.countByGroupId(RandomTestUtil.nextLong());

		_persistence.countByGroupId(0L);
	}

	@Test
	public void testCountByG_E() throws Exception {
		_persistence.countByG_E(RandomTestUtil.nextLong(), "");

		_persistence.countByG_E(0L, "null");

		_persistence.countByG_E(0L, (String)null);
	}

	@Test
	public void testCountByG_A() throws Exception {
		_persistence.countByG_A(
			RandomTestUtil.nextLong(), RandomTestUtil.randomBoolean());

		_persistence.countByG_A(0L, RandomTestUtil.randomBoolean());
	}

	@Test
	public void testFindByPrimaryKeyExisting() throws Exception {
		CommercePaymentMethodGroupRel newCommercePaymentMethodGroupRel =
			addCommercePaymentMethodGroupRel();

		CommercePaymentMethodGroupRel existingCommercePaymentMethodGroupRel =
			_persistence.findByPrimaryKey(
				newCommercePaymentMethodGroupRel.getPrimaryKey());

		Assert.assertEquals(
			existingCommercePaymentMethodGroupRel,
			newCommercePaymentMethodGroupRel);
	}

	@Test(expected = NoSuchPaymentMethodGroupRelException.class)
	public void testFindByPrimaryKeyMissing() throws Exception {
		long pk = RandomTestUtil.nextLong();

		_persistence.findByPrimaryKey(pk);
	}

	@Test
	public void testFindAll() throws Exception {
		_persistence.findAll(
			QueryUtil.ALL_POS, QueryUtil.ALL_POS, getOrderByComparator());
	}

	protected OrderByComparator<CommercePaymentMethodGroupRel>
		getOrderByComparator() {

		return OrderByComparatorFactoryUtil.create(
			"CommercePaymentMethodGroupRel", "commercePaymentMethodGroupRelId",
			true, "groupId", true, "companyId", true, "userId", true,
			"userName", true, "createDate", true, "modifiedDate", true, "name",
			true, "description", true, "imageId", true, "engineKey", true,
			"priority", true, "active", true);
	}

	@Test
	public void testFetchByPrimaryKeyExisting() throws Exception {
		CommercePaymentMethodGroupRel newCommercePaymentMethodGroupRel =
			addCommercePaymentMethodGroupRel();

		CommercePaymentMethodGroupRel existingCommercePaymentMethodGroupRel =
			_persistence.fetchByPrimaryKey(
				newCommercePaymentMethodGroupRel.getPrimaryKey());

		Assert.assertEquals(
			existingCommercePaymentMethodGroupRel,
			newCommercePaymentMethodGroupRel);
	}

	@Test
	public void testFetchByPrimaryKeyMissing() throws Exception {
		long pk = RandomTestUtil.nextLong();

		CommercePaymentMethodGroupRel missingCommercePaymentMethodGroupRel =
			_persistence.fetchByPrimaryKey(pk);

		Assert.assertNull(missingCommercePaymentMethodGroupRel);
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereAllPrimaryKeysExist()
		throws Exception {

		CommercePaymentMethodGroupRel newCommercePaymentMethodGroupRel1 =
			addCommercePaymentMethodGroupRel();
		CommercePaymentMethodGroupRel newCommercePaymentMethodGroupRel2 =
			addCommercePaymentMethodGroupRel();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newCommercePaymentMethodGroupRel1.getPrimaryKey());
		primaryKeys.add(newCommercePaymentMethodGroupRel2.getPrimaryKey());

		Map<Serializable, CommercePaymentMethodGroupRel>
			commercePaymentMethodGroupRels = _persistence.fetchByPrimaryKeys(
				primaryKeys);

		Assert.assertEquals(2, commercePaymentMethodGroupRels.size());
		Assert.assertEquals(
			newCommercePaymentMethodGroupRel1,
			commercePaymentMethodGroupRels.get(
				newCommercePaymentMethodGroupRel1.getPrimaryKey()));
		Assert.assertEquals(
			newCommercePaymentMethodGroupRel2,
			commercePaymentMethodGroupRels.get(
				newCommercePaymentMethodGroupRel2.getPrimaryKey()));
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereNoPrimaryKeysExist()
		throws Exception {

		long pk1 = RandomTestUtil.nextLong();

		long pk2 = RandomTestUtil.nextLong();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(pk1);
		primaryKeys.add(pk2);

		Map<Serializable, CommercePaymentMethodGroupRel>
			commercePaymentMethodGroupRels = _persistence.fetchByPrimaryKeys(
				primaryKeys);

		Assert.assertTrue(commercePaymentMethodGroupRels.isEmpty());
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereSomePrimaryKeysExist()
		throws Exception {

		CommercePaymentMethodGroupRel newCommercePaymentMethodGroupRel =
			addCommercePaymentMethodGroupRel();

		long pk = RandomTestUtil.nextLong();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newCommercePaymentMethodGroupRel.getPrimaryKey());
		primaryKeys.add(pk);

		Map<Serializable, CommercePaymentMethodGroupRel>
			commercePaymentMethodGroupRels = _persistence.fetchByPrimaryKeys(
				primaryKeys);

		Assert.assertEquals(1, commercePaymentMethodGroupRels.size());
		Assert.assertEquals(
			newCommercePaymentMethodGroupRel,
			commercePaymentMethodGroupRels.get(
				newCommercePaymentMethodGroupRel.getPrimaryKey()));
	}

	@Test
	public void testFetchByPrimaryKeysWithNoPrimaryKeys() throws Exception {
		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		Map<Serializable, CommercePaymentMethodGroupRel>
			commercePaymentMethodGroupRels = _persistence.fetchByPrimaryKeys(
				primaryKeys);

		Assert.assertTrue(commercePaymentMethodGroupRels.isEmpty());
	}

	@Test
	public void testFetchByPrimaryKeysWithOnePrimaryKey() throws Exception {
		CommercePaymentMethodGroupRel newCommercePaymentMethodGroupRel =
			addCommercePaymentMethodGroupRel();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newCommercePaymentMethodGroupRel.getPrimaryKey());

		Map<Serializable, CommercePaymentMethodGroupRel>
			commercePaymentMethodGroupRels = _persistence.fetchByPrimaryKeys(
				primaryKeys);

		Assert.assertEquals(1, commercePaymentMethodGroupRels.size());
		Assert.assertEquals(
			newCommercePaymentMethodGroupRel,
			commercePaymentMethodGroupRels.get(
				newCommercePaymentMethodGroupRel.getPrimaryKey()));
	}

	@Test
	public void testActionableDynamicQuery() throws Exception {
		final IntegerWrapper count = new IntegerWrapper();

		ActionableDynamicQuery actionableDynamicQuery =
			CommercePaymentMethodGroupRelLocalServiceUtil.
				getActionableDynamicQuery();

		actionableDynamicQuery.setPerformActionMethod(
			new ActionableDynamicQuery.PerformActionMethod
				<CommercePaymentMethodGroupRel>() {

				@Override
				public void performAction(
					CommercePaymentMethodGroupRel
						commercePaymentMethodGroupRel) {

					Assert.assertNotNull(commercePaymentMethodGroupRel);

					count.increment();
				}

			});

		actionableDynamicQuery.performActions();

		Assert.assertEquals(count.getValue(), _persistence.countAll());
	}

	@Test
	public void testDynamicQueryByPrimaryKeyExisting() throws Exception {
		CommercePaymentMethodGroupRel newCommercePaymentMethodGroupRel =
			addCommercePaymentMethodGroupRel();

		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(
			CommercePaymentMethodGroupRel.class, _dynamicQueryClassLoader);

		dynamicQuery.add(
			RestrictionsFactoryUtil.eq(
				"commercePaymentMethodGroupRelId",
				newCommercePaymentMethodGroupRel.
					getCommercePaymentMethodGroupRelId()));

		List<CommercePaymentMethodGroupRel> result =
			_persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(1, result.size());

		CommercePaymentMethodGroupRel existingCommercePaymentMethodGroupRel =
			result.get(0);

		Assert.assertEquals(
			existingCommercePaymentMethodGroupRel,
			newCommercePaymentMethodGroupRel);
	}

	@Test
	public void testDynamicQueryByPrimaryKeyMissing() throws Exception {
		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(
			CommercePaymentMethodGroupRel.class, _dynamicQueryClassLoader);

		dynamicQuery.add(
			RestrictionsFactoryUtil.eq(
				"commercePaymentMethodGroupRelId", RandomTestUtil.nextLong()));

		List<CommercePaymentMethodGroupRel> result =
			_persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(0, result.size());
	}

	@Test
	public void testDynamicQueryByProjectionExisting() throws Exception {
		CommercePaymentMethodGroupRel newCommercePaymentMethodGroupRel =
			addCommercePaymentMethodGroupRel();

		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(
			CommercePaymentMethodGroupRel.class, _dynamicQueryClassLoader);

		dynamicQuery.setProjection(
			ProjectionFactoryUtil.property("commercePaymentMethodGroupRelId"));

		Object newCommercePaymentMethodGroupRelId =
			newCommercePaymentMethodGroupRel.
				getCommercePaymentMethodGroupRelId();

		dynamicQuery.add(
			RestrictionsFactoryUtil.in(
				"commercePaymentMethodGroupRelId",
				new Object[] {newCommercePaymentMethodGroupRelId}));

		List<Object> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(1, result.size());

		Object existingCommercePaymentMethodGroupRelId = result.get(0);

		Assert.assertEquals(
			existingCommercePaymentMethodGroupRelId,
			newCommercePaymentMethodGroupRelId);
	}

	@Test
	public void testDynamicQueryByProjectionMissing() throws Exception {
		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(
			CommercePaymentMethodGroupRel.class, _dynamicQueryClassLoader);

		dynamicQuery.setProjection(
			ProjectionFactoryUtil.property("commercePaymentMethodGroupRelId"));

		dynamicQuery.add(
			RestrictionsFactoryUtil.in(
				"commercePaymentMethodGroupRelId",
				new Object[] {RandomTestUtil.nextLong()}));

		List<Object> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(0, result.size());
	}

	@Test
	public void testResetOriginalValues() throws Exception {
		CommercePaymentMethodGroupRel newCommercePaymentMethodGroupRel =
			addCommercePaymentMethodGroupRel();

		_persistence.clearCache();

		CommercePaymentMethodGroupRel existingCommercePaymentMethodGroupRel =
			_persistence.findByPrimaryKey(
				newCommercePaymentMethodGroupRel.getPrimaryKey());

		Assert.assertEquals(
			Long.valueOf(existingCommercePaymentMethodGroupRel.getGroupId()),
			ReflectionTestUtil.<Long>invoke(
				existingCommercePaymentMethodGroupRel, "getOriginalGroupId",
				new Class<?>[0]));
		Assert.assertTrue(
			Objects.equals(
				existingCommercePaymentMethodGroupRel.getEngineKey(),
				ReflectionTestUtil.invoke(
					existingCommercePaymentMethodGroupRel,
					"getOriginalEngineKey", new Class<?>[0])));
	}

	protected CommercePaymentMethodGroupRel addCommercePaymentMethodGroupRel()
		throws Exception {

		long pk = RandomTestUtil.nextLong();

		CommercePaymentMethodGroupRel commercePaymentMethodGroupRel =
			_persistence.create(pk);

		commercePaymentMethodGroupRel.setGroupId(RandomTestUtil.nextLong());

		commercePaymentMethodGroupRel.setCompanyId(RandomTestUtil.nextLong());

		commercePaymentMethodGroupRel.setUserId(RandomTestUtil.nextLong());

		commercePaymentMethodGroupRel.setUserName(
			RandomTestUtil.randomString());

		commercePaymentMethodGroupRel.setCreateDate(RandomTestUtil.nextDate());

		commercePaymentMethodGroupRel.setModifiedDate(
			RandomTestUtil.nextDate());

		commercePaymentMethodGroupRel.setName(RandomTestUtil.randomString());

		commercePaymentMethodGroupRel.setDescription(
			RandomTestUtil.randomString());

		commercePaymentMethodGroupRel.setImageId(RandomTestUtil.nextLong());

		commercePaymentMethodGroupRel.setEngineKey(
			RandomTestUtil.randomString());

		commercePaymentMethodGroupRel.setPriority(RandomTestUtil.nextDouble());

		commercePaymentMethodGroupRel.setActive(RandomTestUtil.randomBoolean());

		_commercePaymentMethodGroupRels.add(
			_persistence.update(commercePaymentMethodGroupRel));

		return commercePaymentMethodGroupRel;
	}

	private List<CommercePaymentMethodGroupRel>
		_commercePaymentMethodGroupRels =
			new ArrayList<CommercePaymentMethodGroupRel>();
	private CommercePaymentMethodGroupRelPersistence _persistence;
	private ClassLoader _dynamicQueryClassLoader;

}