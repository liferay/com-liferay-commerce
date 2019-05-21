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

package com.liferay.commerce.wish.list.service.persistence.test;

import com.liferay.arquillian.extension.junit.bridge.junit.Arquillian;
import com.liferay.commerce.wish.list.exception.NoSuchWishListException;
import com.liferay.commerce.wish.list.model.CommerceWishList;
import com.liferay.commerce.wish.list.service.CommerceWishListLocalServiceUtil;
import com.liferay.commerce.wish.list.service.persistence.CommerceWishListPersistence;
import com.liferay.commerce.wish.list.service.persistence.CommerceWishListUtil;
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
public class CommerceWishListPersistenceTest {

	@ClassRule
	@Rule
	public static final AggregateTestRule aggregateTestRule =
		new AggregateTestRule(
			new LiferayIntegrationTestRule(), PersistenceTestRule.INSTANCE,
			new TransactionalTestRule(
				Propagation.REQUIRED,
				"com.liferay.commerce.wish.list.service"));

	@Before
	public void setUp() {
		_persistence = CommerceWishListUtil.getPersistence();

		Class<?> clazz = _persistence.getClass();

		_dynamicQueryClassLoader = clazz.getClassLoader();
	}

	@After
	public void tearDown() throws Exception {
		Iterator<CommerceWishList> iterator = _commerceWishLists.iterator();

		while (iterator.hasNext()) {
			_persistence.remove(iterator.next());

			iterator.remove();
		}
	}

	@Test
	public void testCreate() throws Exception {
		long pk = RandomTestUtil.nextLong();

		CommerceWishList commerceWishList = _persistence.create(pk);

		Assert.assertNotNull(commerceWishList);

		Assert.assertEquals(commerceWishList.getPrimaryKey(), pk);
	}

	@Test
	public void testRemove() throws Exception {
		CommerceWishList newCommerceWishList = addCommerceWishList();

		_persistence.remove(newCommerceWishList);

		CommerceWishList existingCommerceWishList =
			_persistence.fetchByPrimaryKey(newCommerceWishList.getPrimaryKey());

		Assert.assertNull(existingCommerceWishList);
	}

	@Test
	public void testUpdateNew() throws Exception {
		addCommerceWishList();
	}

	@Test
	public void testUpdateExisting() throws Exception {
		long pk = RandomTestUtil.nextLong();

		CommerceWishList newCommerceWishList = _persistence.create(pk);

		newCommerceWishList.setUuid(RandomTestUtil.randomString());

		newCommerceWishList.setGroupId(RandomTestUtil.nextLong());

		newCommerceWishList.setCompanyId(RandomTestUtil.nextLong());

		newCommerceWishList.setUserId(RandomTestUtil.nextLong());

		newCommerceWishList.setUserName(RandomTestUtil.randomString());

		newCommerceWishList.setCreateDate(RandomTestUtil.nextDate());

		newCommerceWishList.setModifiedDate(RandomTestUtil.nextDate());

		newCommerceWishList.setName(RandomTestUtil.randomString());

		newCommerceWishList.setDefaultWishList(RandomTestUtil.randomBoolean());

		_commerceWishLists.add(_persistence.update(newCommerceWishList));

		CommerceWishList existingCommerceWishList =
			_persistence.findByPrimaryKey(newCommerceWishList.getPrimaryKey());

		Assert.assertEquals(
			existingCommerceWishList.getUuid(), newCommerceWishList.getUuid());
		Assert.assertEquals(
			existingCommerceWishList.getCommerceWishListId(),
			newCommerceWishList.getCommerceWishListId());
		Assert.assertEquals(
			existingCommerceWishList.getGroupId(),
			newCommerceWishList.getGroupId());
		Assert.assertEquals(
			existingCommerceWishList.getCompanyId(),
			newCommerceWishList.getCompanyId());
		Assert.assertEquals(
			existingCommerceWishList.getUserId(),
			newCommerceWishList.getUserId());
		Assert.assertEquals(
			existingCommerceWishList.getUserName(),
			newCommerceWishList.getUserName());
		Assert.assertEquals(
			Time.getShortTimestamp(existingCommerceWishList.getCreateDate()),
			Time.getShortTimestamp(newCommerceWishList.getCreateDate()));
		Assert.assertEquals(
			Time.getShortTimestamp(existingCommerceWishList.getModifiedDate()),
			Time.getShortTimestamp(newCommerceWishList.getModifiedDate()));
		Assert.assertEquals(
			existingCommerceWishList.getName(), newCommerceWishList.getName());
		Assert.assertEquals(
			existingCommerceWishList.isDefaultWishList(),
			newCommerceWishList.isDefaultWishList());
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
	public void testCountByUserId() throws Exception {
		_persistence.countByUserId(RandomTestUtil.nextLong());

		_persistence.countByUserId(0L);
	}

	@Test
	public void testCountByG_U() throws Exception {
		_persistence.countByG_U(
			RandomTestUtil.nextLong(), RandomTestUtil.nextLong());

		_persistence.countByG_U(0L, 0L);
	}

	@Test
	public void testCountByU_LtC() throws Exception {
		_persistence.countByU_LtC(
			RandomTestUtil.nextLong(), RandomTestUtil.nextDate());

		_persistence.countByU_LtC(0L, RandomTestUtil.nextDate());
	}

	@Test
	public void testCountByG_U_D() throws Exception {
		_persistence.countByG_U_D(
			RandomTestUtil.nextLong(), RandomTestUtil.nextLong(),
			RandomTestUtil.randomBoolean());

		_persistence.countByG_U_D(0L, 0L, RandomTestUtil.randomBoolean());
	}

	@Test
	public void testFindByPrimaryKeyExisting() throws Exception {
		CommerceWishList newCommerceWishList = addCommerceWishList();

		CommerceWishList existingCommerceWishList =
			_persistence.findByPrimaryKey(newCommerceWishList.getPrimaryKey());

		Assert.assertEquals(existingCommerceWishList, newCommerceWishList);
	}

	@Test(expected = NoSuchWishListException.class)
	public void testFindByPrimaryKeyMissing() throws Exception {
		long pk = RandomTestUtil.nextLong();

		_persistence.findByPrimaryKey(pk);
	}

	@Test
	public void testFindAll() throws Exception {
		_persistence.findAll(
			QueryUtil.ALL_POS, QueryUtil.ALL_POS, getOrderByComparator());
	}

	protected OrderByComparator<CommerceWishList> getOrderByComparator() {
		return OrderByComparatorFactoryUtil.create(
			"CommerceWishList", "uuid", true, "commerceWishListId", true,
			"groupId", true, "companyId", true, "userId", true, "userName",
			true, "createDate", true, "modifiedDate", true, "name", true,
			"defaultWishList", true);
	}

	@Test
	public void testFetchByPrimaryKeyExisting() throws Exception {
		CommerceWishList newCommerceWishList = addCommerceWishList();

		CommerceWishList existingCommerceWishList =
			_persistence.fetchByPrimaryKey(newCommerceWishList.getPrimaryKey());

		Assert.assertEquals(existingCommerceWishList, newCommerceWishList);
	}

	@Test
	public void testFetchByPrimaryKeyMissing() throws Exception {
		long pk = RandomTestUtil.nextLong();

		CommerceWishList missingCommerceWishList =
			_persistence.fetchByPrimaryKey(pk);

		Assert.assertNull(missingCommerceWishList);
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereAllPrimaryKeysExist()
		throws Exception {

		CommerceWishList newCommerceWishList1 = addCommerceWishList();
		CommerceWishList newCommerceWishList2 = addCommerceWishList();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newCommerceWishList1.getPrimaryKey());
		primaryKeys.add(newCommerceWishList2.getPrimaryKey());

		Map<Serializable, CommerceWishList> commerceWishLists =
			_persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(2, commerceWishLists.size());
		Assert.assertEquals(
			newCommerceWishList1,
			commerceWishLists.get(newCommerceWishList1.getPrimaryKey()));
		Assert.assertEquals(
			newCommerceWishList2,
			commerceWishLists.get(newCommerceWishList2.getPrimaryKey()));
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereNoPrimaryKeysExist()
		throws Exception {

		long pk1 = RandomTestUtil.nextLong();

		long pk2 = RandomTestUtil.nextLong();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(pk1);
		primaryKeys.add(pk2);

		Map<Serializable, CommerceWishList> commerceWishLists =
			_persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertTrue(commerceWishLists.isEmpty());
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereSomePrimaryKeysExist()
		throws Exception {

		CommerceWishList newCommerceWishList = addCommerceWishList();

		long pk = RandomTestUtil.nextLong();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newCommerceWishList.getPrimaryKey());
		primaryKeys.add(pk);

		Map<Serializable, CommerceWishList> commerceWishLists =
			_persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(1, commerceWishLists.size());
		Assert.assertEquals(
			newCommerceWishList,
			commerceWishLists.get(newCommerceWishList.getPrimaryKey()));
	}

	@Test
	public void testFetchByPrimaryKeysWithNoPrimaryKeys() throws Exception {
		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		Map<Serializable, CommerceWishList> commerceWishLists =
			_persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertTrue(commerceWishLists.isEmpty());
	}

	@Test
	public void testFetchByPrimaryKeysWithOnePrimaryKey() throws Exception {
		CommerceWishList newCommerceWishList = addCommerceWishList();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newCommerceWishList.getPrimaryKey());

		Map<Serializable, CommerceWishList> commerceWishLists =
			_persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(1, commerceWishLists.size());
		Assert.assertEquals(
			newCommerceWishList,
			commerceWishLists.get(newCommerceWishList.getPrimaryKey()));
	}

	@Test
	public void testActionableDynamicQuery() throws Exception {
		final IntegerWrapper count = new IntegerWrapper();

		ActionableDynamicQuery actionableDynamicQuery =
			CommerceWishListLocalServiceUtil.getActionableDynamicQuery();

		actionableDynamicQuery.setPerformActionMethod(
			new ActionableDynamicQuery.PerformActionMethod<CommerceWishList>() {

				@Override
				public void performAction(CommerceWishList commerceWishList) {
					Assert.assertNotNull(commerceWishList);

					count.increment();
				}

			});

		actionableDynamicQuery.performActions();

		Assert.assertEquals(count.getValue(), _persistence.countAll());
	}

	@Test
	public void testDynamicQueryByPrimaryKeyExisting() throws Exception {
		CommerceWishList newCommerceWishList = addCommerceWishList();

		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(
			CommerceWishList.class, _dynamicQueryClassLoader);

		dynamicQuery.add(
			RestrictionsFactoryUtil.eq(
				"commerceWishListId",
				newCommerceWishList.getCommerceWishListId()));

		List<CommerceWishList> result = _persistence.findWithDynamicQuery(
			dynamicQuery);

		Assert.assertEquals(1, result.size());

		CommerceWishList existingCommerceWishList = result.get(0);

		Assert.assertEquals(existingCommerceWishList, newCommerceWishList);
	}

	@Test
	public void testDynamicQueryByPrimaryKeyMissing() throws Exception {
		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(
			CommerceWishList.class, _dynamicQueryClassLoader);

		dynamicQuery.add(
			RestrictionsFactoryUtil.eq(
				"commerceWishListId", RandomTestUtil.nextLong()));

		List<CommerceWishList> result = _persistence.findWithDynamicQuery(
			dynamicQuery);

		Assert.assertEquals(0, result.size());
	}

	@Test
	public void testDynamicQueryByProjectionExisting() throws Exception {
		CommerceWishList newCommerceWishList = addCommerceWishList();

		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(
			CommerceWishList.class, _dynamicQueryClassLoader);

		dynamicQuery.setProjection(
			ProjectionFactoryUtil.property("commerceWishListId"));

		Object newCommerceWishListId =
			newCommerceWishList.getCommerceWishListId();

		dynamicQuery.add(
			RestrictionsFactoryUtil.in(
				"commerceWishListId", new Object[] {newCommerceWishListId}));

		List<Object> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(1, result.size());

		Object existingCommerceWishListId = result.get(0);

		Assert.assertEquals(existingCommerceWishListId, newCommerceWishListId);
	}

	@Test
	public void testDynamicQueryByProjectionMissing() throws Exception {
		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(
			CommerceWishList.class, _dynamicQueryClassLoader);

		dynamicQuery.setProjection(
			ProjectionFactoryUtil.property("commerceWishListId"));

		dynamicQuery.add(
			RestrictionsFactoryUtil.in(
				"commerceWishListId",
				new Object[] {RandomTestUtil.nextLong()}));

		List<Object> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(0, result.size());
	}

	@Test
	public void testResetOriginalValues() throws Exception {
		CommerceWishList newCommerceWishList = addCommerceWishList();

		_persistence.clearCache();

		CommerceWishList existingCommerceWishList =
			_persistence.findByPrimaryKey(newCommerceWishList.getPrimaryKey());

		Assert.assertTrue(
			Objects.equals(
				existingCommerceWishList.getUuid(),
				ReflectionTestUtil.invoke(
					existingCommerceWishList, "getOriginalUuid",
					new Class<?>[0])));
		Assert.assertEquals(
			Long.valueOf(existingCommerceWishList.getGroupId()),
			ReflectionTestUtil.<Long>invoke(
				existingCommerceWishList, "getOriginalGroupId",
				new Class<?>[0]));
	}

	protected CommerceWishList addCommerceWishList() throws Exception {
		long pk = RandomTestUtil.nextLong();

		CommerceWishList commerceWishList = _persistence.create(pk);

		commerceWishList.setUuid(RandomTestUtil.randomString());

		commerceWishList.setGroupId(RandomTestUtil.nextLong());

		commerceWishList.setCompanyId(RandomTestUtil.nextLong());

		commerceWishList.setUserId(RandomTestUtil.nextLong());

		commerceWishList.setUserName(RandomTestUtil.randomString());

		commerceWishList.setCreateDate(RandomTestUtil.nextDate());

		commerceWishList.setModifiedDate(RandomTestUtil.nextDate());

		commerceWishList.setName(RandomTestUtil.randomString());

		commerceWishList.setDefaultWishList(RandomTestUtil.randomBoolean());

		_commerceWishLists.add(_persistence.update(commerceWishList));

		return commerceWishList;
	}

	private List<CommerceWishList> _commerceWishLists =
		new ArrayList<CommerceWishList>();
	private CommerceWishListPersistence _persistence;
	private ClassLoader _dynamicQueryClassLoader;

}