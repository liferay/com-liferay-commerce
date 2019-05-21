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

package com.liferay.commerce.product.type.virtual.order.service.persistence.test;

import com.liferay.arquillian.extension.junit.bridge.junit.Arquillian;
import com.liferay.commerce.product.type.virtual.order.exception.NoSuchVirtualOrderItemException;
import com.liferay.commerce.product.type.virtual.order.model.CommerceVirtualOrderItem;
import com.liferay.commerce.product.type.virtual.order.service.CommerceVirtualOrderItemLocalServiceUtil;
import com.liferay.commerce.product.type.virtual.order.service.persistence.CommerceVirtualOrderItemPersistence;
import com.liferay.commerce.product.type.virtual.order.service.persistence.CommerceVirtualOrderItemUtil;
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
public class CommerceVirtualOrderItemPersistenceTest {

	@ClassRule
	@Rule
	public static final AggregateTestRule aggregateTestRule =
		new AggregateTestRule(
			new LiferayIntegrationTestRule(), PersistenceTestRule.INSTANCE,
			new TransactionalTestRule(
				Propagation.REQUIRED,
				"com.liferay.commerce.product.type.virtual.order.service"));

	@Before
	public void setUp() {
		_persistence = CommerceVirtualOrderItemUtil.getPersistence();

		Class<?> clazz = _persistence.getClass();

		_dynamicQueryClassLoader = clazz.getClassLoader();
	}

	@After
	public void tearDown() throws Exception {
		Iterator<CommerceVirtualOrderItem> iterator =
			_commerceVirtualOrderItems.iterator();

		while (iterator.hasNext()) {
			_persistence.remove(iterator.next());

			iterator.remove();
		}
	}

	@Test
	public void testCreate() throws Exception {
		long pk = RandomTestUtil.nextLong();

		CommerceVirtualOrderItem commerceVirtualOrderItem = _persistence.create(
			pk);

		Assert.assertNotNull(commerceVirtualOrderItem);

		Assert.assertEquals(commerceVirtualOrderItem.getPrimaryKey(), pk);
	}

	@Test
	public void testRemove() throws Exception {
		CommerceVirtualOrderItem newCommerceVirtualOrderItem =
			addCommerceVirtualOrderItem();

		_persistence.remove(newCommerceVirtualOrderItem);

		CommerceVirtualOrderItem existingCommerceVirtualOrderItem =
			_persistence.fetchByPrimaryKey(
				newCommerceVirtualOrderItem.getPrimaryKey());

		Assert.assertNull(existingCommerceVirtualOrderItem);
	}

	@Test
	public void testUpdateNew() throws Exception {
		addCommerceVirtualOrderItem();
	}

	@Test
	public void testUpdateExisting() throws Exception {
		long pk = RandomTestUtil.nextLong();

		CommerceVirtualOrderItem newCommerceVirtualOrderItem =
			_persistence.create(pk);

		newCommerceVirtualOrderItem.setUuid(RandomTestUtil.randomString());

		newCommerceVirtualOrderItem.setGroupId(RandomTestUtil.nextLong());

		newCommerceVirtualOrderItem.setCompanyId(RandomTestUtil.nextLong());

		newCommerceVirtualOrderItem.setUserId(RandomTestUtil.nextLong());

		newCommerceVirtualOrderItem.setUserName(RandomTestUtil.randomString());

		newCommerceVirtualOrderItem.setCreateDate(RandomTestUtil.nextDate());

		newCommerceVirtualOrderItem.setModifiedDate(RandomTestUtil.nextDate());

		newCommerceVirtualOrderItem.setCommerceOrderItemId(
			RandomTestUtil.nextLong());

		newCommerceVirtualOrderItem.setFileEntryId(RandomTestUtil.nextLong());

		newCommerceVirtualOrderItem.setUrl(RandomTestUtil.randomString());

		newCommerceVirtualOrderItem.setActivationStatus(
			RandomTestUtil.nextInt());

		newCommerceVirtualOrderItem.setDuration(RandomTestUtil.nextLong());

		newCommerceVirtualOrderItem.setUsages(RandomTestUtil.nextInt());

		newCommerceVirtualOrderItem.setMaxUsages(RandomTestUtil.nextInt());

		newCommerceVirtualOrderItem.setActive(RandomTestUtil.randomBoolean());

		newCommerceVirtualOrderItem.setStartDate(RandomTestUtil.nextDate());

		newCommerceVirtualOrderItem.setEndDate(RandomTestUtil.nextDate());

		_commerceVirtualOrderItems.add(
			_persistence.update(newCommerceVirtualOrderItem));

		CommerceVirtualOrderItem existingCommerceVirtualOrderItem =
			_persistence.findByPrimaryKey(
				newCommerceVirtualOrderItem.getPrimaryKey());

		Assert.assertEquals(
			existingCommerceVirtualOrderItem.getUuid(),
			newCommerceVirtualOrderItem.getUuid());
		Assert.assertEquals(
			existingCommerceVirtualOrderItem.getCommerceVirtualOrderItemId(),
			newCommerceVirtualOrderItem.getCommerceVirtualOrderItemId());
		Assert.assertEquals(
			existingCommerceVirtualOrderItem.getGroupId(),
			newCommerceVirtualOrderItem.getGroupId());
		Assert.assertEquals(
			existingCommerceVirtualOrderItem.getCompanyId(),
			newCommerceVirtualOrderItem.getCompanyId());
		Assert.assertEquals(
			existingCommerceVirtualOrderItem.getUserId(),
			newCommerceVirtualOrderItem.getUserId());
		Assert.assertEquals(
			existingCommerceVirtualOrderItem.getUserName(),
			newCommerceVirtualOrderItem.getUserName());
		Assert.assertEquals(
			Time.getShortTimestamp(
				existingCommerceVirtualOrderItem.getCreateDate()),
			Time.getShortTimestamp(
				newCommerceVirtualOrderItem.getCreateDate()));
		Assert.assertEquals(
			Time.getShortTimestamp(
				existingCommerceVirtualOrderItem.getModifiedDate()),
			Time.getShortTimestamp(
				newCommerceVirtualOrderItem.getModifiedDate()));
		Assert.assertEquals(
			existingCommerceVirtualOrderItem.getCommerceOrderItemId(),
			newCommerceVirtualOrderItem.getCommerceOrderItemId());
		Assert.assertEquals(
			existingCommerceVirtualOrderItem.getFileEntryId(),
			newCommerceVirtualOrderItem.getFileEntryId());
		Assert.assertEquals(
			existingCommerceVirtualOrderItem.getUrl(),
			newCommerceVirtualOrderItem.getUrl());
		Assert.assertEquals(
			existingCommerceVirtualOrderItem.getActivationStatus(),
			newCommerceVirtualOrderItem.getActivationStatus());
		Assert.assertEquals(
			existingCommerceVirtualOrderItem.getDuration(),
			newCommerceVirtualOrderItem.getDuration());
		Assert.assertEquals(
			existingCommerceVirtualOrderItem.getUsages(),
			newCommerceVirtualOrderItem.getUsages());
		Assert.assertEquals(
			existingCommerceVirtualOrderItem.getMaxUsages(),
			newCommerceVirtualOrderItem.getMaxUsages());
		Assert.assertEquals(
			existingCommerceVirtualOrderItem.isActive(),
			newCommerceVirtualOrderItem.isActive());
		Assert.assertEquals(
			Time.getShortTimestamp(
				existingCommerceVirtualOrderItem.getStartDate()),
			Time.getShortTimestamp(newCommerceVirtualOrderItem.getStartDate()));
		Assert.assertEquals(
			Time.getShortTimestamp(
				existingCommerceVirtualOrderItem.getEndDate()),
			Time.getShortTimestamp(newCommerceVirtualOrderItem.getEndDate()));
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
	public void testCountByCommerceOrderItemId() throws Exception {
		_persistence.countByCommerceOrderItemId(RandomTestUtil.nextLong());

		_persistence.countByCommerceOrderItemId(0L);
	}

	@Test
	public void testFindByPrimaryKeyExisting() throws Exception {
		CommerceVirtualOrderItem newCommerceVirtualOrderItem =
			addCommerceVirtualOrderItem();

		CommerceVirtualOrderItem existingCommerceVirtualOrderItem =
			_persistence.findByPrimaryKey(
				newCommerceVirtualOrderItem.getPrimaryKey());

		Assert.assertEquals(
			existingCommerceVirtualOrderItem, newCommerceVirtualOrderItem);
	}

	@Test(expected = NoSuchVirtualOrderItemException.class)
	public void testFindByPrimaryKeyMissing() throws Exception {
		long pk = RandomTestUtil.nextLong();

		_persistence.findByPrimaryKey(pk);
	}

	@Test
	public void testFindAll() throws Exception {
		_persistence.findAll(
			QueryUtil.ALL_POS, QueryUtil.ALL_POS, getOrderByComparator());
	}

	protected OrderByComparator<CommerceVirtualOrderItem>
		getOrderByComparator() {

		return OrderByComparatorFactoryUtil.create(
			"CommerceVirtualOrderItem", "uuid", true,
			"commerceVirtualOrderItemId", true, "groupId", true, "companyId",
			true, "userId", true, "userName", true, "createDate", true,
			"modifiedDate", true, "commerceOrderItemId", true, "fileEntryId",
			true, "url", true, "activationStatus", true, "duration", true,
			"usages", true, "maxUsages", true, "active", true, "startDate",
			true, "endDate", true);
	}

	@Test
	public void testFetchByPrimaryKeyExisting() throws Exception {
		CommerceVirtualOrderItem newCommerceVirtualOrderItem =
			addCommerceVirtualOrderItem();

		CommerceVirtualOrderItem existingCommerceVirtualOrderItem =
			_persistence.fetchByPrimaryKey(
				newCommerceVirtualOrderItem.getPrimaryKey());

		Assert.assertEquals(
			existingCommerceVirtualOrderItem, newCommerceVirtualOrderItem);
	}

	@Test
	public void testFetchByPrimaryKeyMissing() throws Exception {
		long pk = RandomTestUtil.nextLong();

		CommerceVirtualOrderItem missingCommerceVirtualOrderItem =
			_persistence.fetchByPrimaryKey(pk);

		Assert.assertNull(missingCommerceVirtualOrderItem);
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereAllPrimaryKeysExist()
		throws Exception {

		CommerceVirtualOrderItem newCommerceVirtualOrderItem1 =
			addCommerceVirtualOrderItem();
		CommerceVirtualOrderItem newCommerceVirtualOrderItem2 =
			addCommerceVirtualOrderItem();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newCommerceVirtualOrderItem1.getPrimaryKey());
		primaryKeys.add(newCommerceVirtualOrderItem2.getPrimaryKey());

		Map<Serializable, CommerceVirtualOrderItem> commerceVirtualOrderItems =
			_persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(2, commerceVirtualOrderItems.size());
		Assert.assertEquals(
			newCommerceVirtualOrderItem1,
			commerceVirtualOrderItems.get(
				newCommerceVirtualOrderItem1.getPrimaryKey()));
		Assert.assertEquals(
			newCommerceVirtualOrderItem2,
			commerceVirtualOrderItems.get(
				newCommerceVirtualOrderItem2.getPrimaryKey()));
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereNoPrimaryKeysExist()
		throws Exception {

		long pk1 = RandomTestUtil.nextLong();

		long pk2 = RandomTestUtil.nextLong();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(pk1);
		primaryKeys.add(pk2);

		Map<Serializable, CommerceVirtualOrderItem> commerceVirtualOrderItems =
			_persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertTrue(commerceVirtualOrderItems.isEmpty());
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereSomePrimaryKeysExist()
		throws Exception {

		CommerceVirtualOrderItem newCommerceVirtualOrderItem =
			addCommerceVirtualOrderItem();

		long pk = RandomTestUtil.nextLong();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newCommerceVirtualOrderItem.getPrimaryKey());
		primaryKeys.add(pk);

		Map<Serializable, CommerceVirtualOrderItem> commerceVirtualOrderItems =
			_persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(1, commerceVirtualOrderItems.size());
		Assert.assertEquals(
			newCommerceVirtualOrderItem,
			commerceVirtualOrderItems.get(
				newCommerceVirtualOrderItem.getPrimaryKey()));
	}

	@Test
	public void testFetchByPrimaryKeysWithNoPrimaryKeys() throws Exception {
		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		Map<Serializable, CommerceVirtualOrderItem> commerceVirtualOrderItems =
			_persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertTrue(commerceVirtualOrderItems.isEmpty());
	}

	@Test
	public void testFetchByPrimaryKeysWithOnePrimaryKey() throws Exception {
		CommerceVirtualOrderItem newCommerceVirtualOrderItem =
			addCommerceVirtualOrderItem();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newCommerceVirtualOrderItem.getPrimaryKey());

		Map<Serializable, CommerceVirtualOrderItem> commerceVirtualOrderItems =
			_persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(1, commerceVirtualOrderItems.size());
		Assert.assertEquals(
			newCommerceVirtualOrderItem,
			commerceVirtualOrderItems.get(
				newCommerceVirtualOrderItem.getPrimaryKey()));
	}

	@Test
	public void testActionableDynamicQuery() throws Exception {
		final IntegerWrapper count = new IntegerWrapper();

		ActionableDynamicQuery actionableDynamicQuery =
			CommerceVirtualOrderItemLocalServiceUtil.
				getActionableDynamicQuery();

		actionableDynamicQuery.setPerformActionMethod(
			new ActionableDynamicQuery.PerformActionMethod
				<CommerceVirtualOrderItem>() {

				@Override
				public void performAction(
					CommerceVirtualOrderItem commerceVirtualOrderItem) {

					Assert.assertNotNull(commerceVirtualOrderItem);

					count.increment();
				}

			});

		actionableDynamicQuery.performActions();

		Assert.assertEquals(count.getValue(), _persistence.countAll());
	}

	@Test
	public void testDynamicQueryByPrimaryKeyExisting() throws Exception {
		CommerceVirtualOrderItem newCommerceVirtualOrderItem =
			addCommerceVirtualOrderItem();

		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(
			CommerceVirtualOrderItem.class, _dynamicQueryClassLoader);

		dynamicQuery.add(
			RestrictionsFactoryUtil.eq(
				"commerceVirtualOrderItemId",
				newCommerceVirtualOrderItem.getCommerceVirtualOrderItemId()));

		List<CommerceVirtualOrderItem> result =
			_persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(1, result.size());

		CommerceVirtualOrderItem existingCommerceVirtualOrderItem = result.get(
			0);

		Assert.assertEquals(
			existingCommerceVirtualOrderItem, newCommerceVirtualOrderItem);
	}

	@Test
	public void testDynamicQueryByPrimaryKeyMissing() throws Exception {
		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(
			CommerceVirtualOrderItem.class, _dynamicQueryClassLoader);

		dynamicQuery.add(
			RestrictionsFactoryUtil.eq(
				"commerceVirtualOrderItemId", RandomTestUtil.nextLong()));

		List<CommerceVirtualOrderItem> result =
			_persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(0, result.size());
	}

	@Test
	public void testDynamicQueryByProjectionExisting() throws Exception {
		CommerceVirtualOrderItem newCommerceVirtualOrderItem =
			addCommerceVirtualOrderItem();

		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(
			CommerceVirtualOrderItem.class, _dynamicQueryClassLoader);

		dynamicQuery.setProjection(
			ProjectionFactoryUtil.property("commerceVirtualOrderItemId"));

		Object newCommerceVirtualOrderItemId =
			newCommerceVirtualOrderItem.getCommerceVirtualOrderItemId();

		dynamicQuery.add(
			RestrictionsFactoryUtil.in(
				"commerceVirtualOrderItemId",
				new Object[] {newCommerceVirtualOrderItemId}));

		List<Object> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(1, result.size());

		Object existingCommerceVirtualOrderItemId = result.get(0);

		Assert.assertEquals(
			existingCommerceVirtualOrderItemId, newCommerceVirtualOrderItemId);
	}

	@Test
	public void testDynamicQueryByProjectionMissing() throws Exception {
		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(
			CommerceVirtualOrderItem.class, _dynamicQueryClassLoader);

		dynamicQuery.setProjection(
			ProjectionFactoryUtil.property("commerceVirtualOrderItemId"));

		dynamicQuery.add(
			RestrictionsFactoryUtil.in(
				"commerceVirtualOrderItemId",
				new Object[] {RandomTestUtil.nextLong()}));

		List<Object> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(0, result.size());
	}

	@Test
	public void testResetOriginalValues() throws Exception {
		CommerceVirtualOrderItem newCommerceVirtualOrderItem =
			addCommerceVirtualOrderItem();

		_persistence.clearCache();

		CommerceVirtualOrderItem existingCommerceVirtualOrderItem =
			_persistence.findByPrimaryKey(
				newCommerceVirtualOrderItem.getPrimaryKey());

		Assert.assertTrue(
			Objects.equals(
				existingCommerceVirtualOrderItem.getUuid(),
				ReflectionTestUtil.invoke(
					existingCommerceVirtualOrderItem, "getOriginalUuid",
					new Class<?>[0])));
		Assert.assertEquals(
			Long.valueOf(existingCommerceVirtualOrderItem.getGroupId()),
			ReflectionTestUtil.<Long>invoke(
				existingCommerceVirtualOrderItem, "getOriginalGroupId",
				new Class<?>[0]));

		Assert.assertEquals(
			Long.valueOf(
				existingCommerceVirtualOrderItem.getCommerceOrderItemId()),
			ReflectionTestUtil.<Long>invoke(
				existingCommerceVirtualOrderItem,
				"getOriginalCommerceOrderItemId", new Class<?>[0]));
	}

	protected CommerceVirtualOrderItem addCommerceVirtualOrderItem()
		throws Exception {

		long pk = RandomTestUtil.nextLong();

		CommerceVirtualOrderItem commerceVirtualOrderItem = _persistence.create(
			pk);

		commerceVirtualOrderItem.setUuid(RandomTestUtil.randomString());

		commerceVirtualOrderItem.setGroupId(RandomTestUtil.nextLong());

		commerceVirtualOrderItem.setCompanyId(RandomTestUtil.nextLong());

		commerceVirtualOrderItem.setUserId(RandomTestUtil.nextLong());

		commerceVirtualOrderItem.setUserName(RandomTestUtil.randomString());

		commerceVirtualOrderItem.setCreateDate(RandomTestUtil.nextDate());

		commerceVirtualOrderItem.setModifiedDate(RandomTestUtil.nextDate());

		commerceVirtualOrderItem.setCommerceOrderItemId(
			RandomTestUtil.nextLong());

		commerceVirtualOrderItem.setFileEntryId(RandomTestUtil.nextLong());

		commerceVirtualOrderItem.setUrl(RandomTestUtil.randomString());

		commerceVirtualOrderItem.setActivationStatus(RandomTestUtil.nextInt());

		commerceVirtualOrderItem.setDuration(RandomTestUtil.nextLong());

		commerceVirtualOrderItem.setUsages(RandomTestUtil.nextInt());

		commerceVirtualOrderItem.setMaxUsages(RandomTestUtil.nextInt());

		commerceVirtualOrderItem.setActive(RandomTestUtil.randomBoolean());

		commerceVirtualOrderItem.setStartDate(RandomTestUtil.nextDate());

		commerceVirtualOrderItem.setEndDate(RandomTestUtil.nextDate());

		_commerceVirtualOrderItems.add(
			_persistence.update(commerceVirtualOrderItem));

		return commerceVirtualOrderItem;
	}

	private List<CommerceVirtualOrderItem> _commerceVirtualOrderItems =
		new ArrayList<CommerceVirtualOrderItem>();
	private CommerceVirtualOrderItemPersistence _persistence;
	private ClassLoader _dynamicQueryClassLoader;

}