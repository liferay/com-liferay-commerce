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
import com.liferay.commerce.inventory.exception.NoSuchInventoryWarehouseItemException;
import com.liferay.commerce.inventory.model.CommerceInventoryWarehouseItem;
import com.liferay.commerce.inventory.service.CommerceInventoryWarehouseItemLocalServiceUtil;
import com.liferay.commerce.inventory.service.persistence.CommerceInventoryWarehouseItemPersistence;
import com.liferay.commerce.inventory.service.persistence.CommerceInventoryWarehouseItemUtil;
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
public class CommerceInventoryWarehouseItemPersistenceTest {

	@ClassRule
	@Rule
	public static final AggregateTestRule aggregateTestRule =
		new AggregateTestRule(
			new LiferayIntegrationTestRule(), PersistenceTestRule.INSTANCE,
			new TransactionalTestRule(
				Propagation.REQUIRED,
				"com.liferay.commerce.inventory.service"));

	@Before
	public void setUp() {
		_persistence = CommerceInventoryWarehouseItemUtil.getPersistence();

		Class<?> clazz = _persistence.getClass();

		_dynamicQueryClassLoader = clazz.getClassLoader();
	}

	@After
	public void tearDown() throws Exception {
		Iterator<CommerceInventoryWarehouseItem> iterator =
			_commerceInventoryWarehouseItems.iterator();

		while (iterator.hasNext()) {
			_persistence.remove(iterator.next());

			iterator.remove();
		}
	}

	@Test
	public void testCreate() throws Exception {
		long pk = RandomTestUtil.nextLong();

		CommerceInventoryWarehouseItem commerceInventoryWarehouseItem =
			_persistence.create(pk);

		Assert.assertNotNull(commerceInventoryWarehouseItem);

		Assert.assertEquals(commerceInventoryWarehouseItem.getPrimaryKey(), pk);
	}

	@Test
	public void testRemove() throws Exception {
		CommerceInventoryWarehouseItem newCommerceInventoryWarehouseItem =
			addCommerceInventoryWarehouseItem();

		_persistence.remove(newCommerceInventoryWarehouseItem);

		CommerceInventoryWarehouseItem existingCommerceInventoryWarehouseItem =
			_persistence.fetchByPrimaryKey(
				newCommerceInventoryWarehouseItem.getPrimaryKey());

		Assert.assertNull(existingCommerceInventoryWarehouseItem);
	}

	@Test
	public void testUpdateNew() throws Exception {
		addCommerceInventoryWarehouseItem();
	}

	@Test
	public void testUpdateExisting() throws Exception {
		long pk = RandomTestUtil.nextLong();

		CommerceInventoryWarehouseItem newCommerceInventoryWarehouseItem =
			_persistence.create(pk);

		newCommerceInventoryWarehouseItem.setExternalReferenceCode(
			RandomTestUtil.randomString());

		newCommerceInventoryWarehouseItem.setCompanyId(
			RandomTestUtil.nextLong());

		newCommerceInventoryWarehouseItem.setUserId(RandomTestUtil.nextLong());

		newCommerceInventoryWarehouseItem.setUserName(
			RandomTestUtil.randomString());

		newCommerceInventoryWarehouseItem.setCreateDate(
			RandomTestUtil.nextDate());

		newCommerceInventoryWarehouseItem.setModifiedDate(
			RandomTestUtil.nextDate());

		newCommerceInventoryWarehouseItem.setCommerceInventoryWarehouseId(
			RandomTestUtil.nextLong());

		newCommerceInventoryWarehouseItem.setSku(RandomTestUtil.randomString());

		newCommerceInventoryWarehouseItem.setQuantity(RandomTestUtil.nextInt());

		newCommerceInventoryWarehouseItem.setReservedQuantity(
			RandomTestUtil.nextInt());

		_commerceInventoryWarehouseItems.add(
			_persistence.update(newCommerceInventoryWarehouseItem));

		CommerceInventoryWarehouseItem existingCommerceInventoryWarehouseItem =
			_persistence.findByPrimaryKey(
				newCommerceInventoryWarehouseItem.getPrimaryKey());

		Assert.assertEquals(
			existingCommerceInventoryWarehouseItem.getExternalReferenceCode(),
			newCommerceInventoryWarehouseItem.getExternalReferenceCode());
		Assert.assertEquals(
			existingCommerceInventoryWarehouseItem.
				getCommerceInventoryWarehouseItemId(),
			newCommerceInventoryWarehouseItem.
				getCommerceInventoryWarehouseItemId());
		Assert.assertEquals(
			existingCommerceInventoryWarehouseItem.getCompanyId(),
			newCommerceInventoryWarehouseItem.getCompanyId());
		Assert.assertEquals(
			existingCommerceInventoryWarehouseItem.getUserId(),
			newCommerceInventoryWarehouseItem.getUserId());
		Assert.assertEquals(
			existingCommerceInventoryWarehouseItem.getUserName(),
			newCommerceInventoryWarehouseItem.getUserName());
		Assert.assertEquals(
			Time.getShortTimestamp(
				existingCommerceInventoryWarehouseItem.getCreateDate()),
			Time.getShortTimestamp(
				newCommerceInventoryWarehouseItem.getCreateDate()));
		Assert.assertEquals(
			Time.getShortTimestamp(
				existingCommerceInventoryWarehouseItem.getModifiedDate()),
			Time.getShortTimestamp(
				newCommerceInventoryWarehouseItem.getModifiedDate()));
		Assert.assertEquals(
			existingCommerceInventoryWarehouseItem.
				getCommerceInventoryWarehouseId(),
			newCommerceInventoryWarehouseItem.
				getCommerceInventoryWarehouseId());
		Assert.assertEquals(
			existingCommerceInventoryWarehouseItem.getSku(),
			newCommerceInventoryWarehouseItem.getSku());
		Assert.assertEquals(
			existingCommerceInventoryWarehouseItem.getQuantity(),
			newCommerceInventoryWarehouseItem.getQuantity());
		Assert.assertEquals(
			existingCommerceInventoryWarehouseItem.getReservedQuantity(),
			newCommerceInventoryWarehouseItem.getReservedQuantity());
	}

	@Test
	public void testCountByCommerceInventoryWarehouseId() throws Exception {
		_persistence.countByCommerceInventoryWarehouseId(
			RandomTestUtil.nextLong());

		_persistence.countByCommerceInventoryWarehouseId(0L);
	}

	@Test
	public void testCountBySku() throws Exception {
		_persistence.countBySku("");

		_persistence.countBySku("null");

		_persistence.countBySku((String)null);
	}

	@Test
	public void testCountByC_S() throws Exception {
		_persistence.countByC_S(RandomTestUtil.nextLong(), "");

		_persistence.countByC_S(0L, "null");

		_persistence.countByC_S(0L, (String)null);
	}

	@Test
	public void testCountByC_ERC() throws Exception {
		_persistence.countByC_ERC(RandomTestUtil.nextLong(), "");

		_persistence.countByC_ERC(0L, "null");

		_persistence.countByC_ERC(0L, (String)null);
	}

	@Test
	public void testFindByPrimaryKeyExisting() throws Exception {
		CommerceInventoryWarehouseItem newCommerceInventoryWarehouseItem =
			addCommerceInventoryWarehouseItem();

		CommerceInventoryWarehouseItem existingCommerceInventoryWarehouseItem =
			_persistence.findByPrimaryKey(
				newCommerceInventoryWarehouseItem.getPrimaryKey());

		Assert.assertEquals(
			existingCommerceInventoryWarehouseItem,
			newCommerceInventoryWarehouseItem);
	}

	@Test(expected = NoSuchInventoryWarehouseItemException.class)
	public void testFindByPrimaryKeyMissing() throws Exception {
		long pk = RandomTestUtil.nextLong();

		_persistence.findByPrimaryKey(pk);
	}

	@Test
	public void testFindAll() throws Exception {
		_persistence.findAll(
			QueryUtil.ALL_POS, QueryUtil.ALL_POS, getOrderByComparator());
	}

	protected OrderByComparator<CommerceInventoryWarehouseItem>
		getOrderByComparator() {

		return OrderByComparatorFactoryUtil.create(
			"CIWarehouseItem", "externalReferenceCode", true,
			"commerceInventoryWarehouseItemId", true, "companyId", true,
			"userId", true, "userName", true, "createDate", true,
			"modifiedDate", true, "commerceInventoryWarehouseId", true, "sku",
			true, "quantity", true, "reservedQuantity", true);
	}

	@Test
	public void testFetchByPrimaryKeyExisting() throws Exception {
		CommerceInventoryWarehouseItem newCommerceInventoryWarehouseItem =
			addCommerceInventoryWarehouseItem();

		CommerceInventoryWarehouseItem existingCommerceInventoryWarehouseItem =
			_persistence.fetchByPrimaryKey(
				newCommerceInventoryWarehouseItem.getPrimaryKey());

		Assert.assertEquals(
			existingCommerceInventoryWarehouseItem,
			newCommerceInventoryWarehouseItem);
	}

	@Test
	public void testFetchByPrimaryKeyMissing() throws Exception {
		long pk = RandomTestUtil.nextLong();

		CommerceInventoryWarehouseItem missingCommerceInventoryWarehouseItem =
			_persistence.fetchByPrimaryKey(pk);

		Assert.assertNull(missingCommerceInventoryWarehouseItem);
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereAllPrimaryKeysExist()
		throws Exception {

		CommerceInventoryWarehouseItem newCommerceInventoryWarehouseItem1 =
			addCommerceInventoryWarehouseItem();
		CommerceInventoryWarehouseItem newCommerceInventoryWarehouseItem2 =
			addCommerceInventoryWarehouseItem();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newCommerceInventoryWarehouseItem1.getPrimaryKey());
		primaryKeys.add(newCommerceInventoryWarehouseItem2.getPrimaryKey());

		Map<Serializable, CommerceInventoryWarehouseItem>
			commerceInventoryWarehouseItems = _persistence.fetchByPrimaryKeys(
				primaryKeys);

		Assert.assertEquals(2, commerceInventoryWarehouseItems.size());
		Assert.assertEquals(
			newCommerceInventoryWarehouseItem1,
			commerceInventoryWarehouseItems.get(
				newCommerceInventoryWarehouseItem1.getPrimaryKey()));
		Assert.assertEquals(
			newCommerceInventoryWarehouseItem2,
			commerceInventoryWarehouseItems.get(
				newCommerceInventoryWarehouseItem2.getPrimaryKey()));
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereNoPrimaryKeysExist()
		throws Exception {

		long pk1 = RandomTestUtil.nextLong();

		long pk2 = RandomTestUtil.nextLong();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(pk1);
		primaryKeys.add(pk2);

		Map<Serializable, CommerceInventoryWarehouseItem>
			commerceInventoryWarehouseItems = _persistence.fetchByPrimaryKeys(
				primaryKeys);

		Assert.assertTrue(commerceInventoryWarehouseItems.isEmpty());
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereSomePrimaryKeysExist()
		throws Exception {

		CommerceInventoryWarehouseItem newCommerceInventoryWarehouseItem =
			addCommerceInventoryWarehouseItem();

		long pk = RandomTestUtil.nextLong();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newCommerceInventoryWarehouseItem.getPrimaryKey());
		primaryKeys.add(pk);

		Map<Serializable, CommerceInventoryWarehouseItem>
			commerceInventoryWarehouseItems = _persistence.fetchByPrimaryKeys(
				primaryKeys);

		Assert.assertEquals(1, commerceInventoryWarehouseItems.size());
		Assert.assertEquals(
			newCommerceInventoryWarehouseItem,
			commerceInventoryWarehouseItems.get(
				newCommerceInventoryWarehouseItem.getPrimaryKey()));
	}

	@Test
	public void testFetchByPrimaryKeysWithNoPrimaryKeys() throws Exception {
		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		Map<Serializable, CommerceInventoryWarehouseItem>
			commerceInventoryWarehouseItems = _persistence.fetchByPrimaryKeys(
				primaryKeys);

		Assert.assertTrue(commerceInventoryWarehouseItems.isEmpty());
	}

	@Test
	public void testFetchByPrimaryKeysWithOnePrimaryKey() throws Exception {
		CommerceInventoryWarehouseItem newCommerceInventoryWarehouseItem =
			addCommerceInventoryWarehouseItem();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newCommerceInventoryWarehouseItem.getPrimaryKey());

		Map<Serializable, CommerceInventoryWarehouseItem>
			commerceInventoryWarehouseItems = _persistence.fetchByPrimaryKeys(
				primaryKeys);

		Assert.assertEquals(1, commerceInventoryWarehouseItems.size());
		Assert.assertEquals(
			newCommerceInventoryWarehouseItem,
			commerceInventoryWarehouseItems.get(
				newCommerceInventoryWarehouseItem.getPrimaryKey()));
	}

	@Test
	public void testActionableDynamicQuery() throws Exception {
		final IntegerWrapper count = new IntegerWrapper();

		ActionableDynamicQuery actionableDynamicQuery =
			CommerceInventoryWarehouseItemLocalServiceUtil.
				getActionableDynamicQuery();

		actionableDynamicQuery.setPerformActionMethod(
			new ActionableDynamicQuery.PerformActionMethod
				<CommerceInventoryWarehouseItem>() {

				@Override
				public void performAction(
					CommerceInventoryWarehouseItem
						commerceInventoryWarehouseItem) {

					Assert.assertNotNull(commerceInventoryWarehouseItem);

					count.increment();
				}

			});

		actionableDynamicQuery.performActions();

		Assert.assertEquals(count.getValue(), _persistence.countAll());
	}

	@Test
	public void testDynamicQueryByPrimaryKeyExisting() throws Exception {
		CommerceInventoryWarehouseItem newCommerceInventoryWarehouseItem =
			addCommerceInventoryWarehouseItem();

		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(
			CommerceInventoryWarehouseItem.class, _dynamicQueryClassLoader);

		dynamicQuery.add(
			RestrictionsFactoryUtil.eq(
				"commerceInventoryWarehouseItemId",
				newCommerceInventoryWarehouseItem.
					getCommerceInventoryWarehouseItemId()));

		List<CommerceInventoryWarehouseItem> result =
			_persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(1, result.size());

		CommerceInventoryWarehouseItem existingCommerceInventoryWarehouseItem =
			result.get(0);

		Assert.assertEquals(
			existingCommerceInventoryWarehouseItem,
			newCommerceInventoryWarehouseItem);
	}

	@Test
	public void testDynamicQueryByPrimaryKeyMissing() throws Exception {
		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(
			CommerceInventoryWarehouseItem.class, _dynamicQueryClassLoader);

		dynamicQuery.add(
			RestrictionsFactoryUtil.eq(
				"commerceInventoryWarehouseItemId", RandomTestUtil.nextLong()));

		List<CommerceInventoryWarehouseItem> result =
			_persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(0, result.size());
	}

	@Test
	public void testDynamicQueryByProjectionExisting() throws Exception {
		CommerceInventoryWarehouseItem newCommerceInventoryWarehouseItem =
			addCommerceInventoryWarehouseItem();

		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(
			CommerceInventoryWarehouseItem.class, _dynamicQueryClassLoader);

		dynamicQuery.setProjection(
			ProjectionFactoryUtil.property("commerceInventoryWarehouseItemId"));

		Object newCommerceInventoryWarehouseItemId =
			newCommerceInventoryWarehouseItem.
				getCommerceInventoryWarehouseItemId();

		dynamicQuery.add(
			RestrictionsFactoryUtil.in(
				"commerceInventoryWarehouseItemId",
				new Object[] {newCommerceInventoryWarehouseItemId}));

		List<Object> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(1, result.size());

		Object existingCommerceInventoryWarehouseItemId = result.get(0);

		Assert.assertEquals(
			existingCommerceInventoryWarehouseItemId,
			newCommerceInventoryWarehouseItemId);
	}

	@Test
	public void testDynamicQueryByProjectionMissing() throws Exception {
		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(
			CommerceInventoryWarehouseItem.class, _dynamicQueryClassLoader);

		dynamicQuery.setProjection(
			ProjectionFactoryUtil.property("commerceInventoryWarehouseItemId"));

		dynamicQuery.add(
			RestrictionsFactoryUtil.in(
				"commerceInventoryWarehouseItemId",
				new Object[] {RandomTestUtil.nextLong()}));

		List<Object> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(0, result.size());
	}

	@Test
	public void testResetOriginalValues() throws Exception {
		CommerceInventoryWarehouseItem newCommerceInventoryWarehouseItem =
			addCommerceInventoryWarehouseItem();

		_persistence.clearCache();

		CommerceInventoryWarehouseItem existingCommerceInventoryWarehouseItem =
			_persistence.findByPrimaryKey(
				newCommerceInventoryWarehouseItem.getPrimaryKey());

		Assert.assertEquals(
			Long.valueOf(
				existingCommerceInventoryWarehouseItem.
					getCommerceInventoryWarehouseId()),
			ReflectionTestUtil.<Long>invoke(
				existingCommerceInventoryWarehouseItem,
				"getOriginalCommerceInventoryWarehouseId", new Class<?>[0]));
		Assert.assertTrue(
			Objects.equals(
				existingCommerceInventoryWarehouseItem.getSku(),
				ReflectionTestUtil.invoke(
					existingCommerceInventoryWarehouseItem, "getOriginalSku",
					new Class<?>[0])));

		Assert.assertEquals(
			Long.valueOf(existingCommerceInventoryWarehouseItem.getCompanyId()),
			ReflectionTestUtil.<Long>invoke(
				existingCommerceInventoryWarehouseItem, "getOriginalCompanyId",
				new Class<?>[0]));
		Assert.assertTrue(
			Objects.equals(
				existingCommerceInventoryWarehouseItem.
					getExternalReferenceCode(),
				ReflectionTestUtil.invoke(
					existingCommerceInventoryWarehouseItem,
					"getOriginalExternalReferenceCode", new Class<?>[0])));
	}

	protected CommerceInventoryWarehouseItem addCommerceInventoryWarehouseItem()
		throws Exception {

		long pk = RandomTestUtil.nextLong();

		CommerceInventoryWarehouseItem commerceInventoryWarehouseItem =
			_persistence.create(pk);

		commerceInventoryWarehouseItem.setExternalReferenceCode(
			RandomTestUtil.randomString());

		commerceInventoryWarehouseItem.setCompanyId(RandomTestUtil.nextLong());

		commerceInventoryWarehouseItem.setUserId(RandomTestUtil.nextLong());

		commerceInventoryWarehouseItem.setUserName(
			RandomTestUtil.randomString());

		commerceInventoryWarehouseItem.setCreateDate(RandomTestUtil.nextDate());

		commerceInventoryWarehouseItem.setModifiedDate(
			RandomTestUtil.nextDate());

		commerceInventoryWarehouseItem.setCommerceInventoryWarehouseId(
			RandomTestUtil.nextLong());

		commerceInventoryWarehouseItem.setSku(RandomTestUtil.randomString());

		commerceInventoryWarehouseItem.setQuantity(RandomTestUtil.nextInt());

		commerceInventoryWarehouseItem.setReservedQuantity(
			RandomTestUtil.nextInt());

		_commerceInventoryWarehouseItems.add(
			_persistence.update(commerceInventoryWarehouseItem));

		return commerceInventoryWarehouseItem;
	}

	private List<CommerceInventoryWarehouseItem>
		_commerceInventoryWarehouseItems =
			new ArrayList<CommerceInventoryWarehouseItem>();
	private CommerceInventoryWarehouseItemPersistence _persistence;
	private ClassLoader _dynamicQueryClassLoader;

}