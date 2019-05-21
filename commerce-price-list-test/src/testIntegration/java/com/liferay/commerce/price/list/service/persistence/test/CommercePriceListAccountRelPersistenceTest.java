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

package com.liferay.commerce.price.list.service.persistence.test;

import com.liferay.arquillian.extension.junit.bridge.junit.Arquillian;
import com.liferay.commerce.price.list.exception.NoSuchPriceListAccountRelException;
import com.liferay.commerce.price.list.model.CommercePriceListAccountRel;
import com.liferay.commerce.price.list.service.CommercePriceListAccountRelLocalServiceUtil;
import com.liferay.commerce.price.list.service.persistence.CommercePriceListAccountRelPersistence;
import com.liferay.commerce.price.list.service.persistence.CommercePriceListAccountRelUtil;
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
public class CommercePriceListAccountRelPersistenceTest {

	@ClassRule
	@Rule
	public static final AggregateTestRule aggregateTestRule =
		new AggregateTestRule(
			new LiferayIntegrationTestRule(), PersistenceTestRule.INSTANCE,
			new TransactionalTestRule(
				Propagation.REQUIRED,
				"com.liferay.commerce.price.list.service"));

	@Before
	public void setUp() {
		_persistence = CommercePriceListAccountRelUtil.getPersistence();

		Class<?> clazz = _persistence.getClass();

		_dynamicQueryClassLoader = clazz.getClassLoader();
	}

	@After
	public void tearDown() throws Exception {
		Iterator<CommercePriceListAccountRel> iterator =
			_commercePriceListAccountRels.iterator();

		while (iterator.hasNext()) {
			_persistence.remove(iterator.next());

			iterator.remove();
		}
	}

	@Test
	public void testCreate() throws Exception {
		long pk = RandomTestUtil.nextLong();

		CommercePriceListAccountRel commercePriceListAccountRel =
			_persistence.create(pk);

		Assert.assertNotNull(commercePriceListAccountRel);

		Assert.assertEquals(commercePriceListAccountRel.getPrimaryKey(), pk);
	}

	@Test
	public void testRemove() throws Exception {
		CommercePriceListAccountRel newCommercePriceListAccountRel =
			addCommercePriceListAccountRel();

		_persistence.remove(newCommercePriceListAccountRel);

		CommercePriceListAccountRel existingCommercePriceListAccountRel =
			_persistence.fetchByPrimaryKey(
				newCommercePriceListAccountRel.getPrimaryKey());

		Assert.assertNull(existingCommercePriceListAccountRel);
	}

	@Test
	public void testUpdateNew() throws Exception {
		addCommercePriceListAccountRel();
	}

	@Test
	public void testUpdateExisting() throws Exception {
		long pk = RandomTestUtil.nextLong();

		CommercePriceListAccountRel newCommercePriceListAccountRel =
			_persistence.create(pk);

		newCommercePriceListAccountRel.setUuid(RandomTestUtil.randomString());

		newCommercePriceListAccountRel.setGroupId(RandomTestUtil.nextLong());

		newCommercePriceListAccountRel.setCompanyId(RandomTestUtil.nextLong());

		newCommercePriceListAccountRel.setUserId(RandomTestUtil.nextLong());

		newCommercePriceListAccountRel.setUserName(
			RandomTestUtil.randomString());

		newCommercePriceListAccountRel.setCreateDate(RandomTestUtil.nextDate());

		newCommercePriceListAccountRel.setModifiedDate(
			RandomTestUtil.nextDate());

		newCommercePriceListAccountRel.setCommerceAccountId(
			RandomTestUtil.nextLong());

		newCommercePriceListAccountRel.setCommercePriceListId(
			RandomTestUtil.nextLong());

		newCommercePriceListAccountRel.setOrder(RandomTestUtil.nextInt());

		newCommercePriceListAccountRel.setLastPublishDate(
			RandomTestUtil.nextDate());

		_commercePriceListAccountRels.add(
			_persistence.update(newCommercePriceListAccountRel));

		CommercePriceListAccountRel existingCommercePriceListAccountRel =
			_persistence.findByPrimaryKey(
				newCommercePriceListAccountRel.getPrimaryKey());

		Assert.assertEquals(
			existingCommercePriceListAccountRel.getUuid(),
			newCommercePriceListAccountRel.getUuid());
		Assert.assertEquals(
			existingCommercePriceListAccountRel.
				getCommercePriceListAccountRelId(),
			newCommercePriceListAccountRel.getCommercePriceListAccountRelId());
		Assert.assertEquals(
			existingCommercePriceListAccountRel.getGroupId(),
			newCommercePriceListAccountRel.getGroupId());
		Assert.assertEquals(
			existingCommercePriceListAccountRel.getCompanyId(),
			newCommercePriceListAccountRel.getCompanyId());
		Assert.assertEquals(
			existingCommercePriceListAccountRel.getUserId(),
			newCommercePriceListAccountRel.getUserId());
		Assert.assertEquals(
			existingCommercePriceListAccountRel.getUserName(),
			newCommercePriceListAccountRel.getUserName());
		Assert.assertEquals(
			Time.getShortTimestamp(
				existingCommercePriceListAccountRel.getCreateDate()),
			Time.getShortTimestamp(
				newCommercePriceListAccountRel.getCreateDate()));
		Assert.assertEquals(
			Time.getShortTimestamp(
				existingCommercePriceListAccountRel.getModifiedDate()),
			Time.getShortTimestamp(
				newCommercePriceListAccountRel.getModifiedDate()));
		Assert.assertEquals(
			existingCommercePriceListAccountRel.getCommerceAccountId(),
			newCommercePriceListAccountRel.getCommerceAccountId());
		Assert.assertEquals(
			existingCommercePriceListAccountRel.getCommercePriceListId(),
			newCommercePriceListAccountRel.getCommercePriceListId());
		Assert.assertEquals(
			existingCommercePriceListAccountRel.getOrder(),
			newCommercePriceListAccountRel.getOrder());
		Assert.assertEquals(
			Time.getShortTimestamp(
				existingCommercePriceListAccountRel.getLastPublishDate()),
			Time.getShortTimestamp(
				newCommercePriceListAccountRel.getLastPublishDate()));
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
	public void testCountByCommercePriceListId() throws Exception {
		_persistence.countByCommercePriceListId(RandomTestUtil.nextLong());

		_persistence.countByCommercePriceListId(0L);
	}

	@Test
	public void testCountByC_C() throws Exception {
		_persistence.countByC_C(
			RandomTestUtil.nextLong(), RandomTestUtil.nextLong());

		_persistence.countByC_C(0L, 0L);
	}

	@Test
	public void testFindByPrimaryKeyExisting() throws Exception {
		CommercePriceListAccountRel newCommercePriceListAccountRel =
			addCommercePriceListAccountRel();

		CommercePriceListAccountRel existingCommercePriceListAccountRel =
			_persistence.findByPrimaryKey(
				newCommercePriceListAccountRel.getPrimaryKey());

		Assert.assertEquals(
			existingCommercePriceListAccountRel,
			newCommercePriceListAccountRel);
	}

	@Test(expected = NoSuchPriceListAccountRelException.class)
	public void testFindByPrimaryKeyMissing() throws Exception {
		long pk = RandomTestUtil.nextLong();

		_persistence.findByPrimaryKey(pk);
	}

	@Test
	public void testFindAll() throws Exception {
		_persistence.findAll(
			QueryUtil.ALL_POS, QueryUtil.ALL_POS, getOrderByComparator());
	}

	protected OrderByComparator<CommercePriceListAccountRel>
		getOrderByComparator() {

		return OrderByComparatorFactoryUtil.create(
			"CommercePriceListAccountRel", "uuid", true,
			"commercePriceListAccountRelId", true, "groupId", true, "companyId",
			true, "userId", true, "userName", true, "createDate", true,
			"modifiedDate", true, "commerceAccountId", true,
			"commercePriceListId", true, "order", true, "lastPublishDate",
			true);
	}

	@Test
	public void testFetchByPrimaryKeyExisting() throws Exception {
		CommercePriceListAccountRel newCommercePriceListAccountRel =
			addCommercePriceListAccountRel();

		CommercePriceListAccountRel existingCommercePriceListAccountRel =
			_persistence.fetchByPrimaryKey(
				newCommercePriceListAccountRel.getPrimaryKey());

		Assert.assertEquals(
			existingCommercePriceListAccountRel,
			newCommercePriceListAccountRel);
	}

	@Test
	public void testFetchByPrimaryKeyMissing() throws Exception {
		long pk = RandomTestUtil.nextLong();

		CommercePriceListAccountRel missingCommercePriceListAccountRel =
			_persistence.fetchByPrimaryKey(pk);

		Assert.assertNull(missingCommercePriceListAccountRel);
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereAllPrimaryKeysExist()
		throws Exception {

		CommercePriceListAccountRel newCommercePriceListAccountRel1 =
			addCommercePriceListAccountRel();
		CommercePriceListAccountRel newCommercePriceListAccountRel2 =
			addCommercePriceListAccountRel();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newCommercePriceListAccountRel1.getPrimaryKey());
		primaryKeys.add(newCommercePriceListAccountRel2.getPrimaryKey());

		Map<Serializable, CommercePriceListAccountRel>
			commercePriceListAccountRels = _persistence.fetchByPrimaryKeys(
				primaryKeys);

		Assert.assertEquals(2, commercePriceListAccountRels.size());
		Assert.assertEquals(
			newCommercePriceListAccountRel1,
			commercePriceListAccountRels.get(
				newCommercePriceListAccountRel1.getPrimaryKey()));
		Assert.assertEquals(
			newCommercePriceListAccountRel2,
			commercePriceListAccountRels.get(
				newCommercePriceListAccountRel2.getPrimaryKey()));
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereNoPrimaryKeysExist()
		throws Exception {

		long pk1 = RandomTestUtil.nextLong();

		long pk2 = RandomTestUtil.nextLong();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(pk1);
		primaryKeys.add(pk2);

		Map<Serializable, CommercePriceListAccountRel>
			commercePriceListAccountRels = _persistence.fetchByPrimaryKeys(
				primaryKeys);

		Assert.assertTrue(commercePriceListAccountRels.isEmpty());
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereSomePrimaryKeysExist()
		throws Exception {

		CommercePriceListAccountRel newCommercePriceListAccountRel =
			addCommercePriceListAccountRel();

		long pk = RandomTestUtil.nextLong();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newCommercePriceListAccountRel.getPrimaryKey());
		primaryKeys.add(pk);

		Map<Serializable, CommercePriceListAccountRel>
			commercePriceListAccountRels = _persistence.fetchByPrimaryKeys(
				primaryKeys);

		Assert.assertEquals(1, commercePriceListAccountRels.size());
		Assert.assertEquals(
			newCommercePriceListAccountRel,
			commercePriceListAccountRels.get(
				newCommercePriceListAccountRel.getPrimaryKey()));
	}

	@Test
	public void testFetchByPrimaryKeysWithNoPrimaryKeys() throws Exception {
		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		Map<Serializable, CommercePriceListAccountRel>
			commercePriceListAccountRels = _persistence.fetchByPrimaryKeys(
				primaryKeys);

		Assert.assertTrue(commercePriceListAccountRels.isEmpty());
	}

	@Test
	public void testFetchByPrimaryKeysWithOnePrimaryKey() throws Exception {
		CommercePriceListAccountRel newCommercePriceListAccountRel =
			addCommercePriceListAccountRel();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newCommercePriceListAccountRel.getPrimaryKey());

		Map<Serializable, CommercePriceListAccountRel>
			commercePriceListAccountRels = _persistence.fetchByPrimaryKeys(
				primaryKeys);

		Assert.assertEquals(1, commercePriceListAccountRels.size());
		Assert.assertEquals(
			newCommercePriceListAccountRel,
			commercePriceListAccountRels.get(
				newCommercePriceListAccountRel.getPrimaryKey()));
	}

	@Test
	public void testActionableDynamicQuery() throws Exception {
		final IntegerWrapper count = new IntegerWrapper();

		ActionableDynamicQuery actionableDynamicQuery =
			CommercePriceListAccountRelLocalServiceUtil.
				getActionableDynamicQuery();

		actionableDynamicQuery.setPerformActionMethod(
			new ActionableDynamicQuery.PerformActionMethod
				<CommercePriceListAccountRel>() {

				@Override
				public void performAction(
					CommercePriceListAccountRel commercePriceListAccountRel) {

					Assert.assertNotNull(commercePriceListAccountRel);

					count.increment();
				}

			});

		actionableDynamicQuery.performActions();

		Assert.assertEquals(count.getValue(), _persistence.countAll());
	}

	@Test
	public void testDynamicQueryByPrimaryKeyExisting() throws Exception {
		CommercePriceListAccountRel newCommercePriceListAccountRel =
			addCommercePriceListAccountRel();

		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(
			CommercePriceListAccountRel.class, _dynamicQueryClassLoader);

		dynamicQuery.add(
			RestrictionsFactoryUtil.eq(
				"commercePriceListAccountRelId",
				newCommercePriceListAccountRel.
					getCommercePriceListAccountRelId()));

		List<CommercePriceListAccountRel> result =
			_persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(1, result.size());

		CommercePriceListAccountRel existingCommercePriceListAccountRel =
			result.get(0);

		Assert.assertEquals(
			existingCommercePriceListAccountRel,
			newCommercePriceListAccountRel);
	}

	@Test
	public void testDynamicQueryByPrimaryKeyMissing() throws Exception {
		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(
			CommercePriceListAccountRel.class, _dynamicQueryClassLoader);

		dynamicQuery.add(
			RestrictionsFactoryUtil.eq(
				"commercePriceListAccountRelId", RandomTestUtil.nextLong()));

		List<CommercePriceListAccountRel> result =
			_persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(0, result.size());
	}

	@Test
	public void testDynamicQueryByProjectionExisting() throws Exception {
		CommercePriceListAccountRel newCommercePriceListAccountRel =
			addCommercePriceListAccountRel();

		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(
			CommercePriceListAccountRel.class, _dynamicQueryClassLoader);

		dynamicQuery.setProjection(
			ProjectionFactoryUtil.property("commercePriceListAccountRelId"));

		Object newCommercePriceListAccountRelId =
			newCommercePriceListAccountRel.getCommercePriceListAccountRelId();

		dynamicQuery.add(
			RestrictionsFactoryUtil.in(
				"commercePriceListAccountRelId",
				new Object[] {newCommercePriceListAccountRelId}));

		List<Object> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(1, result.size());

		Object existingCommercePriceListAccountRelId = result.get(0);

		Assert.assertEquals(
			existingCommercePriceListAccountRelId,
			newCommercePriceListAccountRelId);
	}

	@Test
	public void testDynamicQueryByProjectionMissing() throws Exception {
		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(
			CommercePriceListAccountRel.class, _dynamicQueryClassLoader);

		dynamicQuery.setProjection(
			ProjectionFactoryUtil.property("commercePriceListAccountRelId"));

		dynamicQuery.add(
			RestrictionsFactoryUtil.in(
				"commercePriceListAccountRelId",
				new Object[] {RandomTestUtil.nextLong()}));

		List<Object> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(0, result.size());
	}

	@Test
	public void testResetOriginalValues() throws Exception {
		CommercePriceListAccountRel newCommercePriceListAccountRel =
			addCommercePriceListAccountRel();

		_persistence.clearCache();

		CommercePriceListAccountRel existingCommercePriceListAccountRel =
			_persistence.findByPrimaryKey(
				newCommercePriceListAccountRel.getPrimaryKey());

		Assert.assertTrue(
			Objects.equals(
				existingCommercePriceListAccountRel.getUuid(),
				ReflectionTestUtil.invoke(
					existingCommercePriceListAccountRel, "getOriginalUuid",
					new Class<?>[0])));
		Assert.assertEquals(
			Long.valueOf(existingCommercePriceListAccountRel.getGroupId()),
			ReflectionTestUtil.<Long>invoke(
				existingCommercePriceListAccountRel, "getOriginalGroupId",
				new Class<?>[0]));

		Assert.assertEquals(
			Long.valueOf(
				existingCommercePriceListAccountRel.getCommerceAccountId()),
			ReflectionTestUtil.<Long>invoke(
				existingCommercePriceListAccountRel,
				"getOriginalCommerceAccountId", new Class<?>[0]));
		Assert.assertEquals(
			Long.valueOf(
				existingCommercePriceListAccountRel.getCommercePriceListId()),
			ReflectionTestUtil.<Long>invoke(
				existingCommercePriceListAccountRel,
				"getOriginalCommercePriceListId", new Class<?>[0]));
	}

	protected CommercePriceListAccountRel addCommercePriceListAccountRel()
		throws Exception {

		long pk = RandomTestUtil.nextLong();

		CommercePriceListAccountRel commercePriceListAccountRel =
			_persistence.create(pk);

		commercePriceListAccountRel.setUuid(RandomTestUtil.randomString());

		commercePriceListAccountRel.setGroupId(RandomTestUtil.nextLong());

		commercePriceListAccountRel.setCompanyId(RandomTestUtil.nextLong());

		commercePriceListAccountRel.setUserId(RandomTestUtil.nextLong());

		commercePriceListAccountRel.setUserName(RandomTestUtil.randomString());

		commercePriceListAccountRel.setCreateDate(RandomTestUtil.nextDate());

		commercePriceListAccountRel.setModifiedDate(RandomTestUtil.nextDate());

		commercePriceListAccountRel.setCommerceAccountId(
			RandomTestUtil.nextLong());

		commercePriceListAccountRel.setCommercePriceListId(
			RandomTestUtil.nextLong());

		commercePriceListAccountRel.setOrder(RandomTestUtil.nextInt());

		commercePriceListAccountRel.setLastPublishDate(
			RandomTestUtil.nextDate());

		_commercePriceListAccountRels.add(
			_persistence.update(commercePriceListAccountRel));

		return commercePriceListAccountRel;
	}

	private List<CommercePriceListAccountRel> _commercePriceListAccountRels =
		new ArrayList<CommercePriceListAccountRel>();
	private CommercePriceListAccountRelPersistence _persistence;
	private ClassLoader _dynamicQueryClassLoader;

}