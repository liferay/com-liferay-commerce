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
import com.liferay.commerce.exception.NoSuchOrderItemException;
import com.liferay.commerce.model.CommerceOrderItem;
import com.liferay.commerce.service.CommerceOrderItemLocalServiceUtil;
import com.liferay.commerce.service.persistence.CommerceOrderItemPersistence;
import com.liferay.commerce.service.persistence.CommerceOrderItemUtil;
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

import java.math.BigDecimal;

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
public class CommerceOrderItemPersistenceTest {

	@ClassRule
	@Rule
	public static final AggregateTestRule aggregateTestRule =
		new AggregateTestRule(
			new LiferayIntegrationTestRule(), PersistenceTestRule.INSTANCE,
			new TransactionalTestRule(
				Propagation.REQUIRED, "com.liferay.commerce.service"));

	@Before
	public void setUp() {
		_persistence = CommerceOrderItemUtil.getPersistence();

		Class<?> clazz = _persistence.getClass();

		_dynamicQueryClassLoader = clazz.getClassLoader();
	}

	@After
	public void tearDown() throws Exception {
		Iterator<CommerceOrderItem> iterator = _commerceOrderItems.iterator();

		while (iterator.hasNext()) {
			_persistence.remove(iterator.next());

			iterator.remove();
		}
	}

	@Test
	public void testCreate() throws Exception {
		long pk = RandomTestUtil.nextLong();

		CommerceOrderItem commerceOrderItem = _persistence.create(pk);

		Assert.assertNotNull(commerceOrderItem);

		Assert.assertEquals(commerceOrderItem.getPrimaryKey(), pk);
	}

	@Test
	public void testRemove() throws Exception {
		CommerceOrderItem newCommerceOrderItem = addCommerceOrderItem();

		_persistence.remove(newCommerceOrderItem);

		CommerceOrderItem existingCommerceOrderItem =
			_persistence.fetchByPrimaryKey(
				newCommerceOrderItem.getPrimaryKey());

		Assert.assertNull(existingCommerceOrderItem);
	}

	@Test
	public void testUpdateNew() throws Exception {
		addCommerceOrderItem();
	}

	@Test
	public void testUpdateExisting() throws Exception {
		long pk = RandomTestUtil.nextLong();

		CommerceOrderItem newCommerceOrderItem = _persistence.create(pk);

		newCommerceOrderItem.setExternalReferenceCode(
			RandomTestUtil.randomString());

		newCommerceOrderItem.setGroupId(RandomTestUtil.nextLong());

		newCommerceOrderItem.setCompanyId(RandomTestUtil.nextLong());

		newCommerceOrderItem.setUserId(RandomTestUtil.nextLong());

		newCommerceOrderItem.setUserName(RandomTestUtil.randomString());

		newCommerceOrderItem.setCreateDate(RandomTestUtil.nextDate());

		newCommerceOrderItem.setModifiedDate(RandomTestUtil.nextDate());

		newCommerceOrderItem.setCommerceOrderId(RandomTestUtil.nextLong());

		newCommerceOrderItem.setCProductId(RandomTestUtil.nextLong());

		newCommerceOrderItem.setCPInstanceId(RandomTestUtil.nextLong());

		newCommerceOrderItem.setQuantity(RandomTestUtil.nextInt());

		newCommerceOrderItem.setShippedQuantity(RandomTestUtil.nextInt());

		newCommerceOrderItem.setJson(RandomTestUtil.randomString());

		newCommerceOrderItem.setName(RandomTestUtil.randomString());

		newCommerceOrderItem.setSku(RandomTestUtil.randomString());

		newCommerceOrderItem.setUnitPrice(
			new BigDecimal(RandomTestUtil.nextDouble()));

		newCommerceOrderItem.setPromoPrice(
			new BigDecimal(RandomTestUtil.nextDouble()));

		newCommerceOrderItem.setDiscountAmount(
			new BigDecimal(RandomTestUtil.nextDouble()));

		newCommerceOrderItem.setFinalPrice(
			new BigDecimal(RandomTestUtil.nextDouble()));

		newCommerceOrderItem.setDiscountPercentageLevel1(
			new BigDecimal(RandomTestUtil.nextDouble()));

		newCommerceOrderItem.setDiscountPercentageLevel2(
			new BigDecimal(RandomTestUtil.nextDouble()));

		newCommerceOrderItem.setDiscountPercentageLevel3(
			new BigDecimal(RandomTestUtil.nextDouble()));

		newCommerceOrderItem.setDiscountPercentageLevel4(
			new BigDecimal(RandomTestUtil.nextDouble()));

		newCommerceOrderItem.setSubscription(RandomTestUtil.randomBoolean());

		newCommerceOrderItem.setDeliveryGroup(RandomTestUtil.randomString());

		newCommerceOrderItem.setShippingAddressId(RandomTestUtil.nextLong());

		newCommerceOrderItem.setPrintedNote(RandomTestUtil.randomString());

		newCommerceOrderItem.setRequestedDeliveryDate(
			RandomTestUtil.nextDate());

		newCommerceOrderItem.setBookedQuantityId(RandomTestUtil.nextLong());

		_commerceOrderItems.add(_persistence.update(newCommerceOrderItem));

		CommerceOrderItem existingCommerceOrderItem =
			_persistence.findByPrimaryKey(newCommerceOrderItem.getPrimaryKey());

		Assert.assertEquals(
			existingCommerceOrderItem.getExternalReferenceCode(),
			newCommerceOrderItem.getExternalReferenceCode());
		Assert.assertEquals(
			existingCommerceOrderItem.getCommerceOrderItemId(),
			newCommerceOrderItem.getCommerceOrderItemId());
		Assert.assertEquals(
			existingCommerceOrderItem.getGroupId(),
			newCommerceOrderItem.getGroupId());
		Assert.assertEquals(
			existingCommerceOrderItem.getCompanyId(),
			newCommerceOrderItem.getCompanyId());
		Assert.assertEquals(
			existingCommerceOrderItem.getUserId(),
			newCommerceOrderItem.getUserId());
		Assert.assertEquals(
			existingCommerceOrderItem.getUserName(),
			newCommerceOrderItem.getUserName());
		Assert.assertEquals(
			Time.getShortTimestamp(existingCommerceOrderItem.getCreateDate()),
			Time.getShortTimestamp(newCommerceOrderItem.getCreateDate()));
		Assert.assertEquals(
			Time.getShortTimestamp(existingCommerceOrderItem.getModifiedDate()),
			Time.getShortTimestamp(newCommerceOrderItem.getModifiedDate()));
		Assert.assertEquals(
			existingCommerceOrderItem.getCommerceOrderId(),
			newCommerceOrderItem.getCommerceOrderId());
		Assert.assertEquals(
			existingCommerceOrderItem.getCProductId(),
			newCommerceOrderItem.getCProductId());
		Assert.assertEquals(
			existingCommerceOrderItem.getCPInstanceId(),
			newCommerceOrderItem.getCPInstanceId());
		Assert.assertEquals(
			existingCommerceOrderItem.getQuantity(),
			newCommerceOrderItem.getQuantity());
		Assert.assertEquals(
			existingCommerceOrderItem.getShippedQuantity(),
			newCommerceOrderItem.getShippedQuantity());
		Assert.assertEquals(
			existingCommerceOrderItem.getJson(),
			newCommerceOrderItem.getJson());
		Assert.assertEquals(
			existingCommerceOrderItem.getName(),
			newCommerceOrderItem.getName());
		Assert.assertEquals(
			existingCommerceOrderItem.getSku(), newCommerceOrderItem.getSku());
		Assert.assertEquals(
			existingCommerceOrderItem.getUnitPrice(),
			newCommerceOrderItem.getUnitPrice());
		Assert.assertEquals(
			existingCommerceOrderItem.getPromoPrice(),
			newCommerceOrderItem.getPromoPrice());
		Assert.assertEquals(
			existingCommerceOrderItem.getDiscountAmount(),
			newCommerceOrderItem.getDiscountAmount());
		Assert.assertEquals(
			existingCommerceOrderItem.getFinalPrice(),
			newCommerceOrderItem.getFinalPrice());
		Assert.assertEquals(
			existingCommerceOrderItem.getDiscountPercentageLevel1(),
			newCommerceOrderItem.getDiscountPercentageLevel1());
		Assert.assertEquals(
			existingCommerceOrderItem.getDiscountPercentageLevel2(),
			newCommerceOrderItem.getDiscountPercentageLevel2());
		Assert.assertEquals(
			existingCommerceOrderItem.getDiscountPercentageLevel3(),
			newCommerceOrderItem.getDiscountPercentageLevel3());
		Assert.assertEquals(
			existingCommerceOrderItem.getDiscountPercentageLevel4(),
			newCommerceOrderItem.getDiscountPercentageLevel4());
		Assert.assertEquals(
			existingCommerceOrderItem.isSubscription(),
			newCommerceOrderItem.isSubscription());
		Assert.assertEquals(
			existingCommerceOrderItem.getDeliveryGroup(),
			newCommerceOrderItem.getDeliveryGroup());
		Assert.assertEquals(
			existingCommerceOrderItem.getShippingAddressId(),
			newCommerceOrderItem.getShippingAddressId());
		Assert.assertEquals(
			existingCommerceOrderItem.getPrintedNote(),
			newCommerceOrderItem.getPrintedNote());
		Assert.assertEquals(
			Time.getShortTimestamp(
				existingCommerceOrderItem.getRequestedDeliveryDate()),
			Time.getShortTimestamp(
				newCommerceOrderItem.getRequestedDeliveryDate()));
		Assert.assertEquals(
			existingCommerceOrderItem.getBookedQuantityId(),
			newCommerceOrderItem.getBookedQuantityId());
	}

	@Test
	public void testCountByCommerceOrderId() throws Exception {
		_persistence.countByCommerceOrderId(RandomTestUtil.nextLong());

		_persistence.countByCommerceOrderId(0L);
	}

	@Test
	public void testCountByCProductId() throws Exception {
		_persistence.countByCProductId(RandomTestUtil.nextLong());

		_persistence.countByCProductId(0L);
	}

	@Test
	public void testCountByCPInstanceId() throws Exception {
		_persistence.countByCPInstanceId(RandomTestUtil.nextLong());

		_persistence.countByCPInstanceId(0L);
	}

	@Test
	public void testCountByC_I() throws Exception {
		_persistence.countByC_I(
			RandomTestUtil.nextLong(), RandomTestUtil.nextLong());

		_persistence.countByC_I(0L, 0L);
	}

	@Test
	public void testCountByC_S() throws Exception {
		_persistence.countByC_S(
			RandomTestUtil.nextLong(), RandomTestUtil.randomBoolean());

		_persistence.countByC_S(0L, RandomTestUtil.randomBoolean());
	}

	@Test
	public void testCountByC_ERC() throws Exception {
		_persistence.countByC_ERC(RandomTestUtil.nextLong(), "");

		_persistence.countByC_ERC(0L, "null");

		_persistence.countByC_ERC(0L, (String)null);
	}

	@Test
	public void testFindByPrimaryKeyExisting() throws Exception {
		CommerceOrderItem newCommerceOrderItem = addCommerceOrderItem();

		CommerceOrderItem existingCommerceOrderItem =
			_persistence.findByPrimaryKey(newCommerceOrderItem.getPrimaryKey());

		Assert.assertEquals(existingCommerceOrderItem, newCommerceOrderItem);
	}

	@Test(expected = NoSuchOrderItemException.class)
	public void testFindByPrimaryKeyMissing() throws Exception {
		long pk = RandomTestUtil.nextLong();

		_persistence.findByPrimaryKey(pk);
	}

	@Test
	public void testFindAll() throws Exception {
		_persistence.findAll(
			QueryUtil.ALL_POS, QueryUtil.ALL_POS, getOrderByComparator());
	}

	protected OrderByComparator<CommerceOrderItem> getOrderByComparator() {
		return OrderByComparatorFactoryUtil.create(
			"CommerceOrderItem", "externalReferenceCode", true,
			"commerceOrderItemId", true, "groupId", true, "companyId", true,
			"userId", true, "userName", true, "createDate", true,
			"modifiedDate", true, "commerceOrderId", true, "CProductId", true,
			"CPInstanceId", true, "quantity", true, "shippedQuantity", true,
			"name", true, "sku", true, "unitPrice", true, "promoPrice", true,
			"discountAmount", true, "finalPrice", true,
			"discountPercentageLevel1", true, "discountPercentageLevel2", true,
			"discountPercentageLevel3", true, "discountPercentageLevel4", true,
			"subscription", true, "deliveryGroup", true, "shippingAddressId",
			true, "printedNote", true, "requestedDeliveryDate", true,
			"bookedQuantityId", true);
	}

	@Test
	public void testFetchByPrimaryKeyExisting() throws Exception {
		CommerceOrderItem newCommerceOrderItem = addCommerceOrderItem();

		CommerceOrderItem existingCommerceOrderItem =
			_persistence.fetchByPrimaryKey(
				newCommerceOrderItem.getPrimaryKey());

		Assert.assertEquals(existingCommerceOrderItem, newCommerceOrderItem);
	}

	@Test
	public void testFetchByPrimaryKeyMissing() throws Exception {
		long pk = RandomTestUtil.nextLong();

		CommerceOrderItem missingCommerceOrderItem =
			_persistence.fetchByPrimaryKey(pk);

		Assert.assertNull(missingCommerceOrderItem);
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereAllPrimaryKeysExist()
		throws Exception {

		CommerceOrderItem newCommerceOrderItem1 = addCommerceOrderItem();
		CommerceOrderItem newCommerceOrderItem2 = addCommerceOrderItem();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newCommerceOrderItem1.getPrimaryKey());
		primaryKeys.add(newCommerceOrderItem2.getPrimaryKey());

		Map<Serializable, CommerceOrderItem> commerceOrderItems =
			_persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(2, commerceOrderItems.size());
		Assert.assertEquals(
			newCommerceOrderItem1,
			commerceOrderItems.get(newCommerceOrderItem1.getPrimaryKey()));
		Assert.assertEquals(
			newCommerceOrderItem2,
			commerceOrderItems.get(newCommerceOrderItem2.getPrimaryKey()));
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereNoPrimaryKeysExist()
		throws Exception {

		long pk1 = RandomTestUtil.nextLong();

		long pk2 = RandomTestUtil.nextLong();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(pk1);
		primaryKeys.add(pk2);

		Map<Serializable, CommerceOrderItem> commerceOrderItems =
			_persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertTrue(commerceOrderItems.isEmpty());
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereSomePrimaryKeysExist()
		throws Exception {

		CommerceOrderItem newCommerceOrderItem = addCommerceOrderItem();

		long pk = RandomTestUtil.nextLong();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newCommerceOrderItem.getPrimaryKey());
		primaryKeys.add(pk);

		Map<Serializable, CommerceOrderItem> commerceOrderItems =
			_persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(1, commerceOrderItems.size());
		Assert.assertEquals(
			newCommerceOrderItem,
			commerceOrderItems.get(newCommerceOrderItem.getPrimaryKey()));
	}

	@Test
	public void testFetchByPrimaryKeysWithNoPrimaryKeys() throws Exception {
		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		Map<Serializable, CommerceOrderItem> commerceOrderItems =
			_persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertTrue(commerceOrderItems.isEmpty());
	}

	@Test
	public void testFetchByPrimaryKeysWithOnePrimaryKey() throws Exception {
		CommerceOrderItem newCommerceOrderItem = addCommerceOrderItem();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newCommerceOrderItem.getPrimaryKey());

		Map<Serializable, CommerceOrderItem> commerceOrderItems =
			_persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(1, commerceOrderItems.size());
		Assert.assertEquals(
			newCommerceOrderItem,
			commerceOrderItems.get(newCommerceOrderItem.getPrimaryKey()));
	}

	@Test
	public void testActionableDynamicQuery() throws Exception {
		final IntegerWrapper count = new IntegerWrapper();

		ActionableDynamicQuery actionableDynamicQuery =
			CommerceOrderItemLocalServiceUtil.getActionableDynamicQuery();

		actionableDynamicQuery.setPerformActionMethod(
			new ActionableDynamicQuery.PerformActionMethod
				<CommerceOrderItem>() {

				@Override
				public void performAction(CommerceOrderItem commerceOrderItem) {
					Assert.assertNotNull(commerceOrderItem);

					count.increment();
				}

			});

		actionableDynamicQuery.performActions();

		Assert.assertEquals(count.getValue(), _persistence.countAll());
	}

	@Test
	public void testDynamicQueryByPrimaryKeyExisting() throws Exception {
		CommerceOrderItem newCommerceOrderItem = addCommerceOrderItem();

		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(
			CommerceOrderItem.class, _dynamicQueryClassLoader);

		dynamicQuery.add(
			RestrictionsFactoryUtil.eq(
				"commerceOrderItemId",
				newCommerceOrderItem.getCommerceOrderItemId()));

		List<CommerceOrderItem> result = _persistence.findWithDynamicQuery(
			dynamicQuery);

		Assert.assertEquals(1, result.size());

		CommerceOrderItem existingCommerceOrderItem = result.get(0);

		Assert.assertEquals(existingCommerceOrderItem, newCommerceOrderItem);
	}

	@Test
	public void testDynamicQueryByPrimaryKeyMissing() throws Exception {
		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(
			CommerceOrderItem.class, _dynamicQueryClassLoader);

		dynamicQuery.add(
			RestrictionsFactoryUtil.eq(
				"commerceOrderItemId", RandomTestUtil.nextLong()));

		List<CommerceOrderItem> result = _persistence.findWithDynamicQuery(
			dynamicQuery);

		Assert.assertEquals(0, result.size());
	}

	@Test
	public void testDynamicQueryByProjectionExisting() throws Exception {
		CommerceOrderItem newCommerceOrderItem = addCommerceOrderItem();

		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(
			CommerceOrderItem.class, _dynamicQueryClassLoader);

		dynamicQuery.setProjection(
			ProjectionFactoryUtil.property("commerceOrderItemId"));

		Object newCommerceOrderItemId =
			newCommerceOrderItem.getCommerceOrderItemId();

		dynamicQuery.add(
			RestrictionsFactoryUtil.in(
				"commerceOrderItemId", new Object[] {newCommerceOrderItemId}));

		List<Object> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(1, result.size());

		Object existingCommerceOrderItemId = result.get(0);

		Assert.assertEquals(
			existingCommerceOrderItemId, newCommerceOrderItemId);
	}

	@Test
	public void testDynamicQueryByProjectionMissing() throws Exception {
		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(
			CommerceOrderItem.class, _dynamicQueryClassLoader);

		dynamicQuery.setProjection(
			ProjectionFactoryUtil.property("commerceOrderItemId"));

		dynamicQuery.add(
			RestrictionsFactoryUtil.in(
				"commerceOrderItemId",
				new Object[] {RandomTestUtil.nextLong()}));

		List<Object> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(0, result.size());
	}

	@Test
	public void testResetOriginalValues() throws Exception {
		CommerceOrderItem newCommerceOrderItem = addCommerceOrderItem();

		_persistence.clearCache();

		CommerceOrderItem existingCommerceOrderItem =
			_persistence.findByPrimaryKey(newCommerceOrderItem.getPrimaryKey());

		Assert.assertEquals(
			Long.valueOf(existingCommerceOrderItem.getCompanyId()),
			ReflectionTestUtil.<Long>invoke(
				existingCommerceOrderItem, "getOriginalCompanyId",
				new Class<?>[0]));
		Assert.assertTrue(
			Objects.equals(
				existingCommerceOrderItem.getExternalReferenceCode(),
				ReflectionTestUtil.invoke(
					existingCommerceOrderItem,
					"getOriginalExternalReferenceCode", new Class<?>[0])));
	}

	protected CommerceOrderItem addCommerceOrderItem() throws Exception {
		long pk = RandomTestUtil.nextLong();

		CommerceOrderItem commerceOrderItem = _persistence.create(pk);

		commerceOrderItem.setExternalReferenceCode(
			RandomTestUtil.randomString());

		commerceOrderItem.setGroupId(RandomTestUtil.nextLong());

		commerceOrderItem.setCompanyId(RandomTestUtil.nextLong());

		commerceOrderItem.setUserId(RandomTestUtil.nextLong());

		commerceOrderItem.setUserName(RandomTestUtil.randomString());

		commerceOrderItem.setCreateDate(RandomTestUtil.nextDate());

		commerceOrderItem.setModifiedDate(RandomTestUtil.nextDate());

		commerceOrderItem.setCommerceOrderId(RandomTestUtil.nextLong());

		commerceOrderItem.setCProductId(RandomTestUtil.nextLong());

		commerceOrderItem.setCPInstanceId(RandomTestUtil.nextLong());

		commerceOrderItem.setQuantity(RandomTestUtil.nextInt());

		commerceOrderItem.setShippedQuantity(RandomTestUtil.nextInt());

		commerceOrderItem.setJson(RandomTestUtil.randomString());

		commerceOrderItem.setName(RandomTestUtil.randomString());

		commerceOrderItem.setSku(RandomTestUtil.randomString());

		commerceOrderItem.setUnitPrice(
			new BigDecimal(RandomTestUtil.nextDouble()));

		commerceOrderItem.setPromoPrice(
			new BigDecimal(RandomTestUtil.nextDouble()));

		commerceOrderItem.setDiscountAmount(
			new BigDecimal(RandomTestUtil.nextDouble()));

		commerceOrderItem.setFinalPrice(
			new BigDecimal(RandomTestUtil.nextDouble()));

		commerceOrderItem.setDiscountPercentageLevel1(
			new BigDecimal(RandomTestUtil.nextDouble()));

		commerceOrderItem.setDiscountPercentageLevel2(
			new BigDecimal(RandomTestUtil.nextDouble()));

		commerceOrderItem.setDiscountPercentageLevel3(
			new BigDecimal(RandomTestUtil.nextDouble()));

		commerceOrderItem.setDiscountPercentageLevel4(
			new BigDecimal(RandomTestUtil.nextDouble()));

		commerceOrderItem.setSubscription(RandomTestUtil.randomBoolean());

		commerceOrderItem.setDeliveryGroup(RandomTestUtil.randomString());

		commerceOrderItem.setShippingAddressId(RandomTestUtil.nextLong());

		commerceOrderItem.setPrintedNote(RandomTestUtil.randomString());

		commerceOrderItem.setRequestedDeliveryDate(RandomTestUtil.nextDate());

		commerceOrderItem.setBookedQuantityId(RandomTestUtil.nextLong());

		_commerceOrderItems.add(_persistence.update(commerceOrderItem));

		return commerceOrderItem;
	}

	private List<CommerceOrderItem> _commerceOrderItems =
		new ArrayList<CommerceOrderItem>();
	private CommerceOrderItemPersistence _persistence;
	private ClassLoader _dynamicQueryClassLoader;

}