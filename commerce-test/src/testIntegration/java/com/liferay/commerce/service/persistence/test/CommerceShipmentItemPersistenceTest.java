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

package com.liferay.commerce.service.persistence.test;

import com.liferay.arquillian.extension.junit.bridge.junit.Arquillian;
import com.liferay.commerce.exception.NoSuchShipmentItemException;
import com.liferay.commerce.model.CommerceShipmentItem;
import com.liferay.commerce.service.CommerceShipmentItemLocalServiceUtil;
import com.liferay.commerce.service.persistence.CommerceShipmentItemPersistence;
import com.liferay.commerce.service.persistence.CommerceShipmentItemUtil;
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
public class CommerceShipmentItemPersistenceTest {

	@ClassRule
	@Rule
	public static final AggregateTestRule aggregateTestRule =
		new AggregateTestRule(
			new LiferayIntegrationTestRule(), PersistenceTestRule.INSTANCE,
			new TransactionalTestRule(
				Propagation.REQUIRED, "com.liferay.commerce.service"));

	@Before
	public void setUp() {
		_persistence = CommerceShipmentItemUtil.getPersistence();

		Class<?> clazz = _persistence.getClass();

		_dynamicQueryClassLoader = clazz.getClassLoader();
	}

	@After
	public void tearDown() throws Exception {
		Iterator<CommerceShipmentItem> iterator =
			_commerceShipmentItems.iterator();

		while (iterator.hasNext()) {
			_persistence.remove(iterator.next());

			iterator.remove();
		}
	}

	@Test
	public void testCreate() throws Exception {
		long pk = RandomTestUtil.nextLong();

		CommerceShipmentItem commerceShipmentItem = _persistence.create(pk);

		Assert.assertNotNull(commerceShipmentItem);

		Assert.assertEquals(commerceShipmentItem.getPrimaryKey(), pk);
	}

	@Test
	public void testRemove() throws Exception {
		CommerceShipmentItem newCommerceShipmentItem =
			addCommerceShipmentItem();

		_persistence.remove(newCommerceShipmentItem);

		CommerceShipmentItem existingCommerceShipmentItem =
			_persistence.fetchByPrimaryKey(
				newCommerceShipmentItem.getPrimaryKey());

		Assert.assertNull(existingCommerceShipmentItem);
	}

	@Test
	public void testUpdateNew() throws Exception {
		addCommerceShipmentItem();
	}

	@Test
	public void testUpdateExisting() throws Exception {
		long pk = RandomTestUtil.nextLong();

		CommerceShipmentItem newCommerceShipmentItem = _persistence.create(pk);

		newCommerceShipmentItem.setGroupId(RandomTestUtil.nextLong());

		newCommerceShipmentItem.setCompanyId(RandomTestUtil.nextLong());

		newCommerceShipmentItem.setUserId(RandomTestUtil.nextLong());

		newCommerceShipmentItem.setUserName(RandomTestUtil.randomString());

		newCommerceShipmentItem.setCreateDate(RandomTestUtil.nextDate());

		newCommerceShipmentItem.setModifiedDate(RandomTestUtil.nextDate());

		newCommerceShipmentItem.setCommerceShipmentId(
			RandomTestUtil.nextLong());

		newCommerceShipmentItem.setCommerceOrderItemId(
			RandomTestUtil.nextLong());

		newCommerceShipmentItem.setCommerceWarehouseId(
			RandomTestUtil.nextLong());

		newCommerceShipmentItem.setQuantity(RandomTestUtil.nextInt());

		_commerceShipmentItems.add(
			_persistence.update(newCommerceShipmentItem));

		CommerceShipmentItem existingCommerceShipmentItem =
			_persistence.findByPrimaryKey(
				newCommerceShipmentItem.getPrimaryKey());

		Assert.assertEquals(
			existingCommerceShipmentItem.getCommerceShipmentItemId(),
			newCommerceShipmentItem.getCommerceShipmentItemId());
		Assert.assertEquals(
			existingCommerceShipmentItem.getGroupId(),
			newCommerceShipmentItem.getGroupId());
		Assert.assertEquals(
			existingCommerceShipmentItem.getCompanyId(),
			newCommerceShipmentItem.getCompanyId());
		Assert.assertEquals(
			existingCommerceShipmentItem.getUserId(),
			newCommerceShipmentItem.getUserId());
		Assert.assertEquals(
			existingCommerceShipmentItem.getUserName(),
			newCommerceShipmentItem.getUserName());
		Assert.assertEquals(
			Time.getShortTimestamp(
				existingCommerceShipmentItem.getCreateDate()),
			Time.getShortTimestamp(newCommerceShipmentItem.getCreateDate()));
		Assert.assertEquals(
			Time.getShortTimestamp(
				existingCommerceShipmentItem.getModifiedDate()),
			Time.getShortTimestamp(newCommerceShipmentItem.getModifiedDate()));
		Assert.assertEquals(
			existingCommerceShipmentItem.getCommerceShipmentId(),
			newCommerceShipmentItem.getCommerceShipmentId());
		Assert.assertEquals(
			existingCommerceShipmentItem.getCommerceOrderItemId(),
			newCommerceShipmentItem.getCommerceOrderItemId());
		Assert.assertEquals(
			existingCommerceShipmentItem.getCommerceWarehouseId(),
			newCommerceShipmentItem.getCommerceWarehouseId());
		Assert.assertEquals(
			existingCommerceShipmentItem.getQuantity(),
			newCommerceShipmentItem.getQuantity());
	}

	@Test
	public void testCountByGroupId() throws Exception {
		_persistence.countByGroupId(RandomTestUtil.nextLong());

		_persistence.countByGroupId(0L);
	}

	@Test
	public void testCountByCommerceShipment() throws Exception {
		_persistence.countByCommerceShipment(RandomTestUtil.nextLong());

		_persistence.countByCommerceShipment(0L);
	}

	@Test
	public void testFindByPrimaryKeyExisting() throws Exception {
		CommerceShipmentItem newCommerceShipmentItem =
			addCommerceShipmentItem();

		CommerceShipmentItem existingCommerceShipmentItem =
			_persistence.findByPrimaryKey(
				newCommerceShipmentItem.getPrimaryKey());

		Assert.assertEquals(
			existingCommerceShipmentItem, newCommerceShipmentItem);
	}

	@Test(expected = NoSuchShipmentItemException.class)
	public void testFindByPrimaryKeyMissing() throws Exception {
		long pk = RandomTestUtil.nextLong();

		_persistence.findByPrimaryKey(pk);
	}

	@Test
	public void testFindAll() throws Exception {
		_persistence.findAll(
			QueryUtil.ALL_POS, QueryUtil.ALL_POS, getOrderByComparator());
	}

	protected OrderByComparator<CommerceShipmentItem> getOrderByComparator() {
		return OrderByComparatorFactoryUtil.create(
			"CommerceShipmentItem", "commerceShipmentItemId", true, "groupId",
			true, "companyId", true, "userId", true, "userName", true,
			"createDate", true, "modifiedDate", true, "commerceShipmentId",
			true, "commerceOrderItemId", true, "commerceWarehouseId", true,
			"quantity", true);
	}

	@Test
	public void testFetchByPrimaryKeyExisting() throws Exception {
		CommerceShipmentItem newCommerceShipmentItem =
			addCommerceShipmentItem();

		CommerceShipmentItem existingCommerceShipmentItem =
			_persistence.fetchByPrimaryKey(
				newCommerceShipmentItem.getPrimaryKey());

		Assert.assertEquals(
			existingCommerceShipmentItem, newCommerceShipmentItem);
	}

	@Test
	public void testFetchByPrimaryKeyMissing() throws Exception {
		long pk = RandomTestUtil.nextLong();

		CommerceShipmentItem missingCommerceShipmentItem =
			_persistence.fetchByPrimaryKey(pk);

		Assert.assertNull(missingCommerceShipmentItem);
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereAllPrimaryKeysExist()
		throws Exception {

		CommerceShipmentItem newCommerceShipmentItem1 =
			addCommerceShipmentItem();
		CommerceShipmentItem newCommerceShipmentItem2 =
			addCommerceShipmentItem();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newCommerceShipmentItem1.getPrimaryKey());
		primaryKeys.add(newCommerceShipmentItem2.getPrimaryKey());

		Map<Serializable, CommerceShipmentItem> commerceShipmentItems =
			_persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(2, commerceShipmentItems.size());
		Assert.assertEquals(
			newCommerceShipmentItem1,
			commerceShipmentItems.get(
				newCommerceShipmentItem1.getPrimaryKey()));
		Assert.assertEquals(
			newCommerceShipmentItem2,
			commerceShipmentItems.get(
				newCommerceShipmentItem2.getPrimaryKey()));
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereNoPrimaryKeysExist()
		throws Exception {

		long pk1 = RandomTestUtil.nextLong();

		long pk2 = RandomTestUtil.nextLong();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(pk1);
		primaryKeys.add(pk2);

		Map<Serializable, CommerceShipmentItem> commerceShipmentItems =
			_persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertTrue(commerceShipmentItems.isEmpty());
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereSomePrimaryKeysExist()
		throws Exception {

		CommerceShipmentItem newCommerceShipmentItem =
			addCommerceShipmentItem();

		long pk = RandomTestUtil.nextLong();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newCommerceShipmentItem.getPrimaryKey());
		primaryKeys.add(pk);

		Map<Serializable, CommerceShipmentItem> commerceShipmentItems =
			_persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(1, commerceShipmentItems.size());
		Assert.assertEquals(
			newCommerceShipmentItem,
			commerceShipmentItems.get(newCommerceShipmentItem.getPrimaryKey()));
	}

	@Test
	public void testFetchByPrimaryKeysWithNoPrimaryKeys() throws Exception {
		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		Map<Serializable, CommerceShipmentItem> commerceShipmentItems =
			_persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertTrue(commerceShipmentItems.isEmpty());
	}

	@Test
	public void testFetchByPrimaryKeysWithOnePrimaryKey() throws Exception {
		CommerceShipmentItem newCommerceShipmentItem =
			addCommerceShipmentItem();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newCommerceShipmentItem.getPrimaryKey());

		Map<Serializable, CommerceShipmentItem> commerceShipmentItems =
			_persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(1, commerceShipmentItems.size());
		Assert.assertEquals(
			newCommerceShipmentItem,
			commerceShipmentItems.get(newCommerceShipmentItem.getPrimaryKey()));
	}

	@Test
	public void testActionableDynamicQuery() throws Exception {
		final IntegerWrapper count = new IntegerWrapper();

		ActionableDynamicQuery actionableDynamicQuery =
			CommerceShipmentItemLocalServiceUtil.getActionableDynamicQuery();

		actionableDynamicQuery.setPerformActionMethod(
			new ActionableDynamicQuery.PerformActionMethod
				<CommerceShipmentItem>() {

				@Override
				public void performAction(
					CommerceShipmentItem commerceShipmentItem) {

					Assert.assertNotNull(commerceShipmentItem);

					count.increment();
				}

			});

		actionableDynamicQuery.performActions();

		Assert.assertEquals(count.getValue(), _persistence.countAll());
	}

	@Test
	public void testDynamicQueryByPrimaryKeyExisting() throws Exception {
		CommerceShipmentItem newCommerceShipmentItem =
			addCommerceShipmentItem();

		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(
			CommerceShipmentItem.class, _dynamicQueryClassLoader);

		dynamicQuery.add(
			RestrictionsFactoryUtil.eq(
				"commerceShipmentItemId",
				newCommerceShipmentItem.getCommerceShipmentItemId()));

		List<CommerceShipmentItem> result = _persistence.findWithDynamicQuery(
			dynamicQuery);

		Assert.assertEquals(1, result.size());

		CommerceShipmentItem existingCommerceShipmentItem = result.get(0);

		Assert.assertEquals(
			existingCommerceShipmentItem, newCommerceShipmentItem);
	}

	@Test
	public void testDynamicQueryByPrimaryKeyMissing() throws Exception {
		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(
			CommerceShipmentItem.class, _dynamicQueryClassLoader);

		dynamicQuery.add(
			RestrictionsFactoryUtil.eq(
				"commerceShipmentItemId", RandomTestUtil.nextLong()));

		List<CommerceShipmentItem> result = _persistence.findWithDynamicQuery(
			dynamicQuery);

		Assert.assertEquals(0, result.size());
	}

	@Test
	public void testDynamicQueryByProjectionExisting() throws Exception {
		CommerceShipmentItem newCommerceShipmentItem =
			addCommerceShipmentItem();

		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(
			CommerceShipmentItem.class, _dynamicQueryClassLoader);

		dynamicQuery.setProjection(
			ProjectionFactoryUtil.property("commerceShipmentItemId"));

		Object newCommerceShipmentItemId =
			newCommerceShipmentItem.getCommerceShipmentItemId();

		dynamicQuery.add(
			RestrictionsFactoryUtil.in(
				"commerceShipmentItemId",
				new Object[] {newCommerceShipmentItemId}));

		List<Object> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(1, result.size());

		Object existingCommerceShipmentItemId = result.get(0);

		Assert.assertEquals(
			existingCommerceShipmentItemId, newCommerceShipmentItemId);
	}

	@Test
	public void testDynamicQueryByProjectionMissing() throws Exception {
		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(
			CommerceShipmentItem.class, _dynamicQueryClassLoader);

		dynamicQuery.setProjection(
			ProjectionFactoryUtil.property("commerceShipmentItemId"));

		dynamicQuery.add(
			RestrictionsFactoryUtil.in(
				"commerceShipmentItemId",
				new Object[] {RandomTestUtil.nextLong()}));

		List<Object> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(0, result.size());
	}

	protected CommerceShipmentItem addCommerceShipmentItem() throws Exception {
		long pk = RandomTestUtil.nextLong();

		CommerceShipmentItem commerceShipmentItem = _persistence.create(pk);

		commerceShipmentItem.setGroupId(RandomTestUtil.nextLong());

		commerceShipmentItem.setCompanyId(RandomTestUtil.nextLong());

		commerceShipmentItem.setUserId(RandomTestUtil.nextLong());

		commerceShipmentItem.setUserName(RandomTestUtil.randomString());

		commerceShipmentItem.setCreateDate(RandomTestUtil.nextDate());

		commerceShipmentItem.setModifiedDate(RandomTestUtil.nextDate());

		commerceShipmentItem.setCommerceShipmentId(RandomTestUtil.nextLong());

		commerceShipmentItem.setCommerceOrderItemId(RandomTestUtil.nextLong());

		commerceShipmentItem.setCommerceWarehouseId(RandomTestUtil.nextLong());

		commerceShipmentItem.setQuantity(RandomTestUtil.nextInt());

		_commerceShipmentItems.add(_persistence.update(commerceShipmentItem));

		return commerceShipmentItem;
	}

	private List<CommerceShipmentItem> _commerceShipmentItems =
		new ArrayList<CommerceShipmentItem>();
	private CommerceShipmentItemPersistence _persistence;
	private ClassLoader _dynamicQueryClassLoader;

}