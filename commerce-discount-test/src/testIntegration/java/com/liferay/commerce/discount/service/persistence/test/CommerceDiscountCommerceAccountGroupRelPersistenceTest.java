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

package com.liferay.commerce.discount.service.persistence.test;

import com.liferay.arquillian.extension.junit.bridge.junit.Arquillian;
import com.liferay.commerce.discount.exception.NoSuchDiscountCommerceAccountGroupRelException;
import com.liferay.commerce.discount.model.CommerceDiscountCommerceAccountGroupRel;
import com.liferay.commerce.discount.service.CommerceDiscountCommerceAccountGroupRelLocalServiceUtil;
import com.liferay.commerce.discount.service.persistence.CommerceDiscountCommerceAccountGroupRelPersistence;
import com.liferay.commerce.discount.service.persistence.CommerceDiscountCommerceAccountGroupRelUtil;
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

import java.io.Serializable;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
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
public class CommerceDiscountCommerceAccountGroupRelPersistenceTest {

	@ClassRule
	@Rule
	public static final AggregateTestRule aggregateTestRule =
		new AggregateTestRule(
			new LiferayIntegrationTestRule(), PersistenceTestRule.INSTANCE,
			new TransactionalTestRule(
				Propagation.REQUIRED, "com.liferay.commerce.discount.service"));

	@Before
	public void setUp() {
		_persistence =
			CommerceDiscountCommerceAccountGroupRelUtil.getPersistence();

		Class<?> clazz = _persistence.getClass();

		_dynamicQueryClassLoader = clazz.getClassLoader();
	}

	@After
	public void tearDown() throws Exception {
		Iterator<CommerceDiscountCommerceAccountGroupRel> iterator =
			_commerceDiscountCommerceAccountGroupRels.iterator();

		while (iterator.hasNext()) {
			_persistence.remove(iterator.next());

			iterator.remove();
		}
	}

	@Test
	public void testCreate() throws Exception {
		long pk = RandomTestUtil.nextLong();

		CommerceDiscountCommerceAccountGroupRel
			commerceDiscountCommerceAccountGroupRel = _persistence.create(pk);

		Assert.assertNotNull(commerceDiscountCommerceAccountGroupRel);

		Assert.assertEquals(
			commerceDiscountCommerceAccountGroupRel.getPrimaryKey(), pk);
	}

	@Test
	public void testRemove() throws Exception {
		CommerceDiscountCommerceAccountGroupRel
			newCommerceDiscountCommerceAccountGroupRel =
				addCommerceDiscountCommerceAccountGroupRel();

		_persistence.remove(newCommerceDiscountCommerceAccountGroupRel);

		CommerceDiscountCommerceAccountGroupRel
			existingCommerceDiscountCommerceAccountGroupRel =
				_persistence.fetchByPrimaryKey(
					newCommerceDiscountCommerceAccountGroupRel.getPrimaryKey());

		Assert.assertNull(existingCommerceDiscountCommerceAccountGroupRel);
	}

	@Test
	public void testUpdateNew() throws Exception {
		addCommerceDiscountCommerceAccountGroupRel();
	}

	@Test
	public void testUpdateExisting() throws Exception {
		long pk = RandomTestUtil.nextLong();

		CommerceDiscountCommerceAccountGroupRel
			newCommerceDiscountCommerceAccountGroupRel = _persistence.create(
				pk);

		newCommerceDiscountCommerceAccountGroupRel.setCompanyId(
			RandomTestUtil.nextLong());

		newCommerceDiscountCommerceAccountGroupRel.setUserId(
			RandomTestUtil.nextLong());

		newCommerceDiscountCommerceAccountGroupRel.setUserName(
			RandomTestUtil.randomString());

		newCommerceDiscountCommerceAccountGroupRel.setCreateDate(
			RandomTestUtil.nextDate());

		newCommerceDiscountCommerceAccountGroupRel.setModifiedDate(
			RandomTestUtil.nextDate());

		newCommerceDiscountCommerceAccountGroupRel.setCommerceDiscountId(
			RandomTestUtil.nextLong());

		newCommerceDiscountCommerceAccountGroupRel.setCommerceAccountGroupId(
			RandomTestUtil.nextLong());

		_commerceDiscountCommerceAccountGroupRels.add(
			_persistence.update(newCommerceDiscountCommerceAccountGroupRel));

		CommerceDiscountCommerceAccountGroupRel
			existingCommerceDiscountCommerceAccountGroupRel =
				_persistence.findByPrimaryKey(
					newCommerceDiscountCommerceAccountGroupRel.getPrimaryKey());

		Assert.assertEquals(
			existingCommerceDiscountCommerceAccountGroupRel.
				getCommerceDiscountCommerceAccountGroupRelId(),
			newCommerceDiscountCommerceAccountGroupRel.
				getCommerceDiscountCommerceAccountGroupRelId());
		Assert.assertEquals(
			existingCommerceDiscountCommerceAccountGroupRel.getCompanyId(),
			newCommerceDiscountCommerceAccountGroupRel.getCompanyId());
		Assert.assertEquals(
			existingCommerceDiscountCommerceAccountGroupRel.getUserId(),
			newCommerceDiscountCommerceAccountGroupRel.getUserId());
		Assert.assertEquals(
			existingCommerceDiscountCommerceAccountGroupRel.getUserName(),
			newCommerceDiscountCommerceAccountGroupRel.getUserName());
		Assert.assertEquals(
			Time.getShortTimestamp(
				existingCommerceDiscountCommerceAccountGroupRel.
					getCreateDate()),
			Time.getShortTimestamp(
				newCommerceDiscountCommerceAccountGroupRel.getCreateDate()));
		Assert.assertEquals(
			Time.getShortTimestamp(
				existingCommerceDiscountCommerceAccountGroupRel.
					getModifiedDate()),
			Time.getShortTimestamp(
				newCommerceDiscountCommerceAccountGroupRel.getModifiedDate()));
		Assert.assertEquals(
			existingCommerceDiscountCommerceAccountGroupRel.
				getCommerceDiscountId(),
			newCommerceDiscountCommerceAccountGroupRel.getCommerceDiscountId());
		Assert.assertEquals(
			existingCommerceDiscountCommerceAccountGroupRel.
				getCommerceAccountGroupId(),
			newCommerceDiscountCommerceAccountGroupRel.
				getCommerceAccountGroupId());
	}

	@Test
	public void testCountByCommerceDiscountId() throws Exception {
		_persistence.countByCommerceDiscountId(RandomTestUtil.nextLong());

		_persistence.countByCommerceDiscountId(0L);
	}

	@Test
	public void testCountByCommerceAccountGroupId() throws Exception {
		_persistence.countByCommerceAccountGroupId(RandomTestUtil.nextLong());

		_persistence.countByCommerceAccountGroupId(0L);
	}

	@Test
	public void testCountByC_C() throws Exception {
		_persistence.countByC_C(
			RandomTestUtil.nextLong(), RandomTestUtil.nextLong());

		_persistence.countByC_C(0L, 0L);
	}

	@Test
	public void testFindByPrimaryKeyExisting() throws Exception {
		CommerceDiscountCommerceAccountGroupRel
			newCommerceDiscountCommerceAccountGroupRel =
				addCommerceDiscountCommerceAccountGroupRel();

		CommerceDiscountCommerceAccountGroupRel
			existingCommerceDiscountCommerceAccountGroupRel =
				_persistence.findByPrimaryKey(
					newCommerceDiscountCommerceAccountGroupRel.getPrimaryKey());

		Assert.assertEquals(
			existingCommerceDiscountCommerceAccountGroupRel,
			newCommerceDiscountCommerceAccountGroupRel);
	}

	@Test(expected = NoSuchDiscountCommerceAccountGroupRelException.class)
	public void testFindByPrimaryKeyMissing() throws Exception {
		long pk = RandomTestUtil.nextLong();

		_persistence.findByPrimaryKey(pk);
	}

	@Test
	public void testFindAll() throws Exception {
		_persistence.findAll(
			QueryUtil.ALL_POS, QueryUtil.ALL_POS, getOrderByComparator());
	}

	protected OrderByComparator<CommerceDiscountCommerceAccountGroupRel>
		getOrderByComparator() {

		return OrderByComparatorFactoryUtil.create(
			"CDiscountCAccountGroupRel",
			"commerceDiscountCommerceAccountGroupRelId", true, "companyId",
			true, "userId", true, "userName", true, "createDate", true,
			"modifiedDate", true, "commerceDiscountId", true,
			"commerceAccountGroupId", true);
	}

	@Test
	public void testFetchByPrimaryKeyExisting() throws Exception {
		CommerceDiscountCommerceAccountGroupRel
			newCommerceDiscountCommerceAccountGroupRel =
				addCommerceDiscountCommerceAccountGroupRel();

		CommerceDiscountCommerceAccountGroupRel
			existingCommerceDiscountCommerceAccountGroupRel =
				_persistence.fetchByPrimaryKey(
					newCommerceDiscountCommerceAccountGroupRel.getPrimaryKey());

		Assert.assertEquals(
			existingCommerceDiscountCommerceAccountGroupRel,
			newCommerceDiscountCommerceAccountGroupRel);
	}

	@Test
	public void testFetchByPrimaryKeyMissing() throws Exception {
		long pk = RandomTestUtil.nextLong();

		CommerceDiscountCommerceAccountGroupRel
			missingCommerceDiscountCommerceAccountGroupRel =
				_persistence.fetchByPrimaryKey(pk);

		Assert.assertNull(missingCommerceDiscountCommerceAccountGroupRel);
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereAllPrimaryKeysExist()
		throws Exception {

		CommerceDiscountCommerceAccountGroupRel
			newCommerceDiscountCommerceAccountGroupRel1 =
				addCommerceDiscountCommerceAccountGroupRel();
		CommerceDiscountCommerceAccountGroupRel
			newCommerceDiscountCommerceAccountGroupRel2 =
				addCommerceDiscountCommerceAccountGroupRel();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(
			newCommerceDiscountCommerceAccountGroupRel1.getPrimaryKey());
		primaryKeys.add(
			newCommerceDiscountCommerceAccountGroupRel2.getPrimaryKey());

		Map<Serializable, CommerceDiscountCommerceAccountGroupRel>
			commerceDiscountCommerceAccountGroupRels =
				_persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(2, commerceDiscountCommerceAccountGroupRels.size());
		Assert.assertEquals(
			newCommerceDiscountCommerceAccountGroupRel1,
			commerceDiscountCommerceAccountGroupRels.get(
				newCommerceDiscountCommerceAccountGroupRel1.getPrimaryKey()));
		Assert.assertEquals(
			newCommerceDiscountCommerceAccountGroupRel2,
			commerceDiscountCommerceAccountGroupRels.get(
				newCommerceDiscountCommerceAccountGroupRel2.getPrimaryKey()));
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereNoPrimaryKeysExist()
		throws Exception {

		long pk1 = RandomTestUtil.nextLong();

		long pk2 = RandomTestUtil.nextLong();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(pk1);
		primaryKeys.add(pk2);

		Map<Serializable, CommerceDiscountCommerceAccountGroupRel>
			commerceDiscountCommerceAccountGroupRels =
				_persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertTrue(commerceDiscountCommerceAccountGroupRels.isEmpty());
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereSomePrimaryKeysExist()
		throws Exception {

		CommerceDiscountCommerceAccountGroupRel
			newCommerceDiscountCommerceAccountGroupRel =
				addCommerceDiscountCommerceAccountGroupRel();

		long pk = RandomTestUtil.nextLong();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(
			newCommerceDiscountCommerceAccountGroupRel.getPrimaryKey());
		primaryKeys.add(pk);

		Map<Serializable, CommerceDiscountCommerceAccountGroupRel>
			commerceDiscountCommerceAccountGroupRels =
				_persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(1, commerceDiscountCommerceAccountGroupRels.size());
		Assert.assertEquals(
			newCommerceDiscountCommerceAccountGroupRel,
			commerceDiscountCommerceAccountGroupRels.get(
				newCommerceDiscountCommerceAccountGroupRel.getPrimaryKey()));
	}

	@Test
	public void testFetchByPrimaryKeysWithNoPrimaryKeys() throws Exception {
		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		Map<Serializable, CommerceDiscountCommerceAccountGroupRel>
			commerceDiscountCommerceAccountGroupRels =
				_persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertTrue(commerceDiscountCommerceAccountGroupRels.isEmpty());
	}

	@Test
	public void testFetchByPrimaryKeysWithOnePrimaryKey() throws Exception {
		CommerceDiscountCommerceAccountGroupRel
			newCommerceDiscountCommerceAccountGroupRel =
				addCommerceDiscountCommerceAccountGroupRel();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(
			newCommerceDiscountCommerceAccountGroupRel.getPrimaryKey());

		Map<Serializable, CommerceDiscountCommerceAccountGroupRel>
			commerceDiscountCommerceAccountGroupRels =
				_persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(1, commerceDiscountCommerceAccountGroupRels.size());
		Assert.assertEquals(
			newCommerceDiscountCommerceAccountGroupRel,
			commerceDiscountCommerceAccountGroupRels.get(
				newCommerceDiscountCommerceAccountGroupRel.getPrimaryKey()));
	}

	@Test
	public void testActionableDynamicQuery() throws Exception {
		final IntegerWrapper count = new IntegerWrapper();

		ActionableDynamicQuery actionableDynamicQuery =
			CommerceDiscountCommerceAccountGroupRelLocalServiceUtil.
				getActionableDynamicQuery();

		actionableDynamicQuery.setPerformActionMethod(
			new ActionableDynamicQuery.PerformActionMethod
				<CommerceDiscountCommerceAccountGroupRel>() {

				@Override
				public void performAction(
					CommerceDiscountCommerceAccountGroupRel
						commerceDiscountCommerceAccountGroupRel) {

					Assert.assertNotNull(
						commerceDiscountCommerceAccountGroupRel);

					count.increment();
				}

			});

		actionableDynamicQuery.performActions();

		Assert.assertEquals(count.getValue(), _persistence.countAll());
	}

	@Test
	public void testDynamicQueryByPrimaryKeyExisting() throws Exception {
		CommerceDiscountCommerceAccountGroupRel
			newCommerceDiscountCommerceAccountGroupRel =
				addCommerceDiscountCommerceAccountGroupRel();

		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(
			CommerceDiscountCommerceAccountGroupRel.class,
			_dynamicQueryClassLoader);

		dynamicQuery.add(
			RestrictionsFactoryUtil.eq(
				"commerceDiscountCommerceAccountGroupRelId",
				newCommerceDiscountCommerceAccountGroupRel.
					getCommerceDiscountCommerceAccountGroupRelId()));

		List<CommerceDiscountCommerceAccountGroupRel> result =
			_persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(1, result.size());

		CommerceDiscountCommerceAccountGroupRel
			existingCommerceDiscountCommerceAccountGroupRel = result.get(0);

		Assert.assertEquals(
			existingCommerceDiscountCommerceAccountGroupRel,
			newCommerceDiscountCommerceAccountGroupRel);
	}

	@Test
	public void testDynamicQueryByPrimaryKeyMissing() throws Exception {
		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(
			CommerceDiscountCommerceAccountGroupRel.class,
			_dynamicQueryClassLoader);

		dynamicQuery.add(
			RestrictionsFactoryUtil.eq(
				"commerceDiscountCommerceAccountGroupRelId",
				RandomTestUtil.nextLong()));

		List<CommerceDiscountCommerceAccountGroupRel> result =
			_persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(0, result.size());
	}

	@Test
	public void testDynamicQueryByProjectionExisting() throws Exception {
		CommerceDiscountCommerceAccountGroupRel
			newCommerceDiscountCommerceAccountGroupRel =
				addCommerceDiscountCommerceAccountGroupRel();

		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(
			CommerceDiscountCommerceAccountGroupRel.class,
			_dynamicQueryClassLoader);

		dynamicQuery.setProjection(
			ProjectionFactoryUtil.property(
				"commerceDiscountCommerceAccountGroupRelId"));

		Object newCommerceDiscountCommerceAccountGroupRelId =
			newCommerceDiscountCommerceAccountGroupRel.
				getCommerceDiscountCommerceAccountGroupRelId();

		dynamicQuery.add(
			RestrictionsFactoryUtil.in(
				"commerceDiscountCommerceAccountGroupRelId",
				new Object[] {newCommerceDiscountCommerceAccountGroupRelId}));

		List<Object> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(1, result.size());

		Object existingCommerceDiscountCommerceAccountGroupRelId = result.get(
			0);

		Assert.assertEquals(
			existingCommerceDiscountCommerceAccountGroupRelId,
			newCommerceDiscountCommerceAccountGroupRelId);
	}

	@Test
	public void testDynamicQueryByProjectionMissing() throws Exception {
		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(
			CommerceDiscountCommerceAccountGroupRel.class,
			_dynamicQueryClassLoader);

		dynamicQuery.setProjection(
			ProjectionFactoryUtil.property(
				"commerceDiscountCommerceAccountGroupRelId"));

		dynamicQuery.add(
			RestrictionsFactoryUtil.in(
				"commerceDiscountCommerceAccountGroupRelId",
				new Object[] {RandomTestUtil.nextLong()}));

		List<Object> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(0, result.size());
	}

	@Test
	public void testResetOriginalValues() throws Exception {
		CommerceDiscountCommerceAccountGroupRel
			newCommerceDiscountCommerceAccountGroupRel =
				addCommerceDiscountCommerceAccountGroupRel();

		_persistence.clearCache();

		CommerceDiscountCommerceAccountGroupRel
			existingCommerceDiscountCommerceAccountGroupRel =
				_persistence.findByPrimaryKey(
					newCommerceDiscountCommerceAccountGroupRel.getPrimaryKey());

		Assert.assertEquals(
			Long.valueOf(
				existingCommerceDiscountCommerceAccountGroupRel.
					getCommerceDiscountId()),
			ReflectionTestUtil.<Long>invoke(
				existingCommerceDiscountCommerceAccountGroupRel,
				"getOriginalCommerceDiscountId", new Class<?>[0]));
		Assert.assertEquals(
			Long.valueOf(
				existingCommerceDiscountCommerceAccountGroupRel.
					getCommerceAccountGroupId()),
			ReflectionTestUtil.<Long>invoke(
				existingCommerceDiscountCommerceAccountGroupRel,
				"getOriginalCommerceAccountGroupId", new Class<?>[0]));
	}

	protected CommerceDiscountCommerceAccountGroupRel
			addCommerceDiscountCommerceAccountGroupRel()
		throws Exception {

		long pk = RandomTestUtil.nextLong();

		CommerceDiscountCommerceAccountGroupRel
			commerceDiscountCommerceAccountGroupRel = _persistence.create(pk);

		commerceDiscountCommerceAccountGroupRel.setCompanyId(
			RandomTestUtil.nextLong());

		commerceDiscountCommerceAccountGroupRel.setUserId(
			RandomTestUtil.nextLong());

		commerceDiscountCommerceAccountGroupRel.setUserName(
			RandomTestUtil.randomString());

		commerceDiscountCommerceAccountGroupRel.setCreateDate(
			RandomTestUtil.nextDate());

		commerceDiscountCommerceAccountGroupRel.setModifiedDate(
			RandomTestUtil.nextDate());

		commerceDiscountCommerceAccountGroupRel.setCommerceDiscountId(
			RandomTestUtil.nextLong());

		commerceDiscountCommerceAccountGroupRel.setCommerceAccountGroupId(
			RandomTestUtil.nextLong());

		_commerceDiscountCommerceAccountGroupRels.add(
			_persistence.update(commerceDiscountCommerceAccountGroupRel));

		return commerceDiscountCommerceAccountGroupRel;
	}

	private List<CommerceDiscountCommerceAccountGroupRel>
		_commerceDiscountCommerceAccountGroupRels =
			new ArrayList<CommerceDiscountCommerceAccountGroupRel>();
	private CommerceDiscountCommerceAccountGroupRelPersistence _persistence;
	private ClassLoader _dynamicQueryClassLoader;

}