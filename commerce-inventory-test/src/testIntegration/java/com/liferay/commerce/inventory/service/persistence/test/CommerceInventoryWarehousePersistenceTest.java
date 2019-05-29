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

import com.liferay.commerce.inventory.exception.NoSuchInventoryWarehouseException;
import com.liferay.commerce.inventory.model.CommerceInventoryWarehouse;
import com.liferay.commerce.inventory.service.CommerceInventoryWarehouseLocalServiceUtil;
import com.liferay.commerce.inventory.service.persistence.CommerceInventoryWarehousePersistence;
import com.liferay.commerce.inventory.service.persistence.CommerceInventoryWarehouseUtil;

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
import java.util.Objects;
import java.util.Set;

/**
 * @generated
 */
@RunWith(Arquillian.class)
public class CommerceInventoryWarehousePersistenceTest {
	@ClassRule
	@Rule
	public static final AggregateTestRule aggregateTestRule = new AggregateTestRule(new LiferayIntegrationTestRule(),
			PersistenceTestRule.INSTANCE,
			new TransactionalTestRule(Propagation.REQUIRED,
				"com.liferay.commerce.inventory.service"));

	@Before
	public void setUp() {
		_persistence = CommerceInventoryWarehouseUtil.getPersistence();

		Class<?> clazz = _persistence.getClass();

		_dynamicQueryClassLoader = clazz.getClassLoader();
	}

	@After
	public void tearDown() throws Exception {
		Iterator<CommerceInventoryWarehouse> iterator = _commerceInventoryWarehouses.iterator();

		while (iterator.hasNext()) {
			_persistence.remove(iterator.next());

			iterator.remove();
		}
	}

	@Test
	public void testCreate() throws Exception {
		long pk = RandomTestUtil.nextLong();

		CommerceInventoryWarehouse commerceInventoryWarehouse = _persistence.create(pk);

		Assert.assertNotNull(commerceInventoryWarehouse);

		Assert.assertEquals(commerceInventoryWarehouse.getPrimaryKey(), pk);
	}

	@Test
	public void testRemove() throws Exception {
		CommerceInventoryWarehouse newCommerceInventoryWarehouse = addCommerceInventoryWarehouse();

		_persistence.remove(newCommerceInventoryWarehouse);

		CommerceInventoryWarehouse existingCommerceInventoryWarehouse = _persistence.fetchByPrimaryKey(newCommerceInventoryWarehouse.getPrimaryKey());

		Assert.assertNull(existingCommerceInventoryWarehouse);
	}

	@Test
	public void testUpdateNew() throws Exception {
		addCommerceInventoryWarehouse();
	}

	@Test
	public void testUpdateExisting() throws Exception {
		long pk = RandomTestUtil.nextLong();

		CommerceInventoryWarehouse newCommerceInventoryWarehouse = _persistence.create(pk);

		newCommerceInventoryWarehouse.setExternalReferenceCode(RandomTestUtil.randomString());

		newCommerceInventoryWarehouse.setCompanyId(RandomTestUtil.nextLong());

		newCommerceInventoryWarehouse.setUserId(RandomTestUtil.nextLong());

		newCommerceInventoryWarehouse.setUserName(RandomTestUtil.randomString());

		newCommerceInventoryWarehouse.setCreateDate(RandomTestUtil.nextDate());

		newCommerceInventoryWarehouse.setModifiedDate(RandomTestUtil.nextDate());

		newCommerceInventoryWarehouse.setName(RandomTestUtil.randomString());

		newCommerceInventoryWarehouse.setDescription(RandomTestUtil.randomString());

		newCommerceInventoryWarehouse.setActive(RandomTestUtil.randomBoolean());

		newCommerceInventoryWarehouse.setStreet1(RandomTestUtil.randomString());

		newCommerceInventoryWarehouse.setStreet2(RandomTestUtil.randomString());

		newCommerceInventoryWarehouse.setStreet3(RandomTestUtil.randomString());

		newCommerceInventoryWarehouse.setCity(RandomTestUtil.randomString());

		newCommerceInventoryWarehouse.setZip(RandomTestUtil.randomString());

		newCommerceInventoryWarehouse.setCommerceRegionCode(RandomTestUtil.randomString());

		newCommerceInventoryWarehouse.setCountryTwoLettersISOCode(RandomTestUtil.randomString());

		newCommerceInventoryWarehouse.setLatitude(RandomTestUtil.nextDouble());

		newCommerceInventoryWarehouse.setLongitude(RandomTestUtil.nextDouble());

		newCommerceInventoryWarehouse.setType(RandomTestUtil.randomString());

		_commerceInventoryWarehouses.add(_persistence.update(
				newCommerceInventoryWarehouse));

		CommerceInventoryWarehouse existingCommerceInventoryWarehouse = _persistence.findByPrimaryKey(newCommerceInventoryWarehouse.getPrimaryKey());

		Assert.assertEquals(existingCommerceInventoryWarehouse.getExternalReferenceCode(),
			newCommerceInventoryWarehouse.getExternalReferenceCode());
		Assert.assertEquals(existingCommerceInventoryWarehouse.getCommerceInventoryWarehouseId(),
			newCommerceInventoryWarehouse.getCommerceInventoryWarehouseId());
		Assert.assertEquals(existingCommerceInventoryWarehouse.getCompanyId(),
			newCommerceInventoryWarehouse.getCompanyId());
		Assert.assertEquals(existingCommerceInventoryWarehouse.getUserId(),
			newCommerceInventoryWarehouse.getUserId());
		Assert.assertEquals(existingCommerceInventoryWarehouse.getUserName(),
			newCommerceInventoryWarehouse.getUserName());
		Assert.assertEquals(Time.getShortTimestamp(
				existingCommerceInventoryWarehouse.getCreateDate()),
			Time.getShortTimestamp(
				newCommerceInventoryWarehouse.getCreateDate()));
		Assert.assertEquals(Time.getShortTimestamp(
				existingCommerceInventoryWarehouse.getModifiedDate()),
			Time.getShortTimestamp(
				newCommerceInventoryWarehouse.getModifiedDate()));
		Assert.assertEquals(existingCommerceInventoryWarehouse.getName(),
			newCommerceInventoryWarehouse.getName());
		Assert.assertEquals(existingCommerceInventoryWarehouse.getDescription(),
			newCommerceInventoryWarehouse.getDescription());
		Assert.assertEquals(existingCommerceInventoryWarehouse.isActive(),
			newCommerceInventoryWarehouse.isActive());
		Assert.assertEquals(existingCommerceInventoryWarehouse.getStreet1(),
			newCommerceInventoryWarehouse.getStreet1());
		Assert.assertEquals(existingCommerceInventoryWarehouse.getStreet2(),
			newCommerceInventoryWarehouse.getStreet2());
		Assert.assertEquals(existingCommerceInventoryWarehouse.getStreet3(),
			newCommerceInventoryWarehouse.getStreet3());
		Assert.assertEquals(existingCommerceInventoryWarehouse.getCity(),
			newCommerceInventoryWarehouse.getCity());
		Assert.assertEquals(existingCommerceInventoryWarehouse.getZip(),
			newCommerceInventoryWarehouse.getZip());
		Assert.assertEquals(existingCommerceInventoryWarehouse.getCommerceRegionCode(),
			newCommerceInventoryWarehouse.getCommerceRegionCode());
		Assert.assertEquals(existingCommerceInventoryWarehouse.getCountryTwoLettersISOCode(),
			newCommerceInventoryWarehouse.getCountryTwoLettersISOCode());
		AssertUtils.assertEquals(existingCommerceInventoryWarehouse.getLatitude(),
			newCommerceInventoryWarehouse.getLatitude());
		AssertUtils.assertEquals(existingCommerceInventoryWarehouse.getLongitude(),
			newCommerceInventoryWarehouse.getLongitude());
		Assert.assertEquals(existingCommerceInventoryWarehouse.getType(),
			newCommerceInventoryWarehouse.getType());
	}

	@Test
	public void testCountByactive() throws Exception {
		_persistence.countByactive(RandomTestUtil.randomBoolean());

		_persistence.countByactive(RandomTestUtil.randomBoolean());
	}

	@Test
	public void testCountBycountryTwoLettersISOCode() throws Exception {
		_persistence.countBycountryTwoLettersISOCode("");

		_persistence.countBycountryTwoLettersISOCode("null");

		_persistence.countBycountryTwoLettersISOCode((String)null);
	}

	@Test
	public void testCountByA_C() throws Exception {
		_persistence.countByA_C(RandomTestUtil.randomBoolean(), "");

		_persistence.countByA_C(RandomTestUtil.randomBoolean(), "null");

		_persistence.countByA_C(RandomTestUtil.randomBoolean(), (String)null);
	}

	@Test
	public void testCountByC_ERC() throws Exception {
		_persistence.countByC_ERC(RandomTestUtil.nextLong(), "");

		_persistence.countByC_ERC(0L, "null");

		_persistence.countByC_ERC(0L, (String)null);
	}

	@Test
	public void testFindByPrimaryKeyExisting() throws Exception {
		CommerceInventoryWarehouse newCommerceInventoryWarehouse = addCommerceInventoryWarehouse();

		CommerceInventoryWarehouse existingCommerceInventoryWarehouse = _persistence.findByPrimaryKey(newCommerceInventoryWarehouse.getPrimaryKey());

		Assert.assertEquals(existingCommerceInventoryWarehouse,
			newCommerceInventoryWarehouse);
	}

	@Test(expected = NoSuchInventoryWarehouseException.class)
	public void testFindByPrimaryKeyMissing() throws Exception {
		long pk = RandomTestUtil.nextLong();

		_persistence.findByPrimaryKey(pk);
	}

	@Test
	public void testFindAll() throws Exception {
		_persistence.findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS,
			getOrderByComparator());
	}

	protected OrderByComparator<CommerceInventoryWarehouse> getOrderByComparator() {
		return OrderByComparatorFactoryUtil.create("CIWarehouse",
			"externalReferenceCode", true, "commerceInventoryWarehouseId",
			true, "companyId", true, "userId", true, "userName", true,
			"createDate", true, "modifiedDate", true, "name", true,
			"description", true, "active", true, "street1", true, "street2",
			true, "street3", true, "city", true, "zip", true,
			"commerceRegionCode", true, "countryTwoLettersISOCode", true,
			"latitude", true, "longitude", true, "type", true);
	}

	@Test
	public void testFetchByPrimaryKeyExisting() throws Exception {
		CommerceInventoryWarehouse newCommerceInventoryWarehouse = addCommerceInventoryWarehouse();

		CommerceInventoryWarehouse existingCommerceInventoryWarehouse = _persistence.fetchByPrimaryKey(newCommerceInventoryWarehouse.getPrimaryKey());

		Assert.assertEquals(existingCommerceInventoryWarehouse,
			newCommerceInventoryWarehouse);
	}

	@Test
	public void testFetchByPrimaryKeyMissing() throws Exception {
		long pk = RandomTestUtil.nextLong();

		CommerceInventoryWarehouse missingCommerceInventoryWarehouse = _persistence.fetchByPrimaryKey(pk);

		Assert.assertNull(missingCommerceInventoryWarehouse);
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereAllPrimaryKeysExist()
		throws Exception {
		CommerceInventoryWarehouse newCommerceInventoryWarehouse1 = addCommerceInventoryWarehouse();
		CommerceInventoryWarehouse newCommerceInventoryWarehouse2 = addCommerceInventoryWarehouse();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newCommerceInventoryWarehouse1.getPrimaryKey());
		primaryKeys.add(newCommerceInventoryWarehouse2.getPrimaryKey());

		Map<Serializable, CommerceInventoryWarehouse> commerceInventoryWarehouses =
			_persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(2, commerceInventoryWarehouses.size());
		Assert.assertEquals(newCommerceInventoryWarehouse1,
			commerceInventoryWarehouses.get(
				newCommerceInventoryWarehouse1.getPrimaryKey()));
		Assert.assertEquals(newCommerceInventoryWarehouse2,
			commerceInventoryWarehouses.get(
				newCommerceInventoryWarehouse2.getPrimaryKey()));
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereNoPrimaryKeysExist()
		throws Exception {
		long pk1 = RandomTestUtil.nextLong();

		long pk2 = RandomTestUtil.nextLong();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(pk1);
		primaryKeys.add(pk2);

		Map<Serializable, CommerceInventoryWarehouse> commerceInventoryWarehouses =
			_persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertTrue(commerceInventoryWarehouses.isEmpty());
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereSomePrimaryKeysExist()
		throws Exception {
		CommerceInventoryWarehouse newCommerceInventoryWarehouse = addCommerceInventoryWarehouse();

		long pk = RandomTestUtil.nextLong();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newCommerceInventoryWarehouse.getPrimaryKey());
		primaryKeys.add(pk);

		Map<Serializable, CommerceInventoryWarehouse> commerceInventoryWarehouses =
			_persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(1, commerceInventoryWarehouses.size());
		Assert.assertEquals(newCommerceInventoryWarehouse,
			commerceInventoryWarehouses.get(
				newCommerceInventoryWarehouse.getPrimaryKey()));
	}

	@Test
	public void testFetchByPrimaryKeysWithNoPrimaryKeys()
		throws Exception {
		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		Map<Serializable, CommerceInventoryWarehouse> commerceInventoryWarehouses =
			_persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertTrue(commerceInventoryWarehouses.isEmpty());
	}

	@Test
	public void testFetchByPrimaryKeysWithOnePrimaryKey()
		throws Exception {
		CommerceInventoryWarehouse newCommerceInventoryWarehouse = addCommerceInventoryWarehouse();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newCommerceInventoryWarehouse.getPrimaryKey());

		Map<Serializable, CommerceInventoryWarehouse> commerceInventoryWarehouses =
			_persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(1, commerceInventoryWarehouses.size());
		Assert.assertEquals(newCommerceInventoryWarehouse,
			commerceInventoryWarehouses.get(
				newCommerceInventoryWarehouse.getPrimaryKey()));
	}

	@Test
	public void testActionableDynamicQuery() throws Exception {
		final IntegerWrapper count = new IntegerWrapper();

		ActionableDynamicQuery actionableDynamicQuery = CommerceInventoryWarehouseLocalServiceUtil.getActionableDynamicQuery();

		actionableDynamicQuery.setPerformActionMethod(new ActionableDynamicQuery.PerformActionMethod<CommerceInventoryWarehouse>() {
				@Override
				public void performAction(
					CommerceInventoryWarehouse commerceInventoryWarehouse) {
					Assert.assertNotNull(commerceInventoryWarehouse);

					count.increment();
				}
			});

		actionableDynamicQuery.performActions();

		Assert.assertEquals(count.getValue(), _persistence.countAll());
	}

	@Test
	public void testDynamicQueryByPrimaryKeyExisting()
		throws Exception {
		CommerceInventoryWarehouse newCommerceInventoryWarehouse = addCommerceInventoryWarehouse();

		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(CommerceInventoryWarehouse.class,
				_dynamicQueryClassLoader);

		dynamicQuery.add(RestrictionsFactoryUtil.eq(
				"commerceInventoryWarehouseId",
				newCommerceInventoryWarehouse.getCommerceInventoryWarehouseId()));

		List<CommerceInventoryWarehouse> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(1, result.size());

		CommerceInventoryWarehouse existingCommerceInventoryWarehouse = result.get(0);

		Assert.assertEquals(existingCommerceInventoryWarehouse,
			newCommerceInventoryWarehouse);
	}

	@Test
	public void testDynamicQueryByPrimaryKeyMissing() throws Exception {
		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(CommerceInventoryWarehouse.class,
				_dynamicQueryClassLoader);

		dynamicQuery.add(RestrictionsFactoryUtil.eq(
				"commerceInventoryWarehouseId", RandomTestUtil.nextLong()));

		List<CommerceInventoryWarehouse> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(0, result.size());
	}

	@Test
	public void testDynamicQueryByProjectionExisting()
		throws Exception {
		CommerceInventoryWarehouse newCommerceInventoryWarehouse = addCommerceInventoryWarehouse();

		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(CommerceInventoryWarehouse.class,
				_dynamicQueryClassLoader);

		dynamicQuery.setProjection(ProjectionFactoryUtil.property(
				"commerceInventoryWarehouseId"));

		Object newCommerceInventoryWarehouseId = newCommerceInventoryWarehouse.getCommerceInventoryWarehouseId();

		dynamicQuery.add(RestrictionsFactoryUtil.in(
				"commerceInventoryWarehouseId",
				new Object[] { newCommerceInventoryWarehouseId }));

		List<Object> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(1, result.size());

		Object existingCommerceInventoryWarehouseId = result.get(0);

		Assert.assertEquals(existingCommerceInventoryWarehouseId,
			newCommerceInventoryWarehouseId);
	}

	@Test
	public void testDynamicQueryByProjectionMissing() throws Exception {
		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(CommerceInventoryWarehouse.class,
				_dynamicQueryClassLoader);

		dynamicQuery.setProjection(ProjectionFactoryUtil.property(
				"commerceInventoryWarehouseId"));

		dynamicQuery.add(RestrictionsFactoryUtil.in(
				"commerceInventoryWarehouseId",
				new Object[] { RandomTestUtil.nextLong() }));

		List<Object> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(0, result.size());
	}

	@Test
	public void testResetOriginalValues() throws Exception {
		CommerceInventoryWarehouse newCommerceInventoryWarehouse = addCommerceInventoryWarehouse();

		_persistence.clearCache();

		CommerceInventoryWarehouse existingCommerceInventoryWarehouse = _persistence.findByPrimaryKey(newCommerceInventoryWarehouse.getPrimaryKey());

		Assert.assertEquals(Long.valueOf(
				existingCommerceInventoryWarehouse.getCompanyId()),
			ReflectionTestUtil.<Long>invoke(
				existingCommerceInventoryWarehouse, "getOriginalCompanyId",
				new Class<?>[0]));
		Assert.assertTrue(Objects.equals(
				existingCommerceInventoryWarehouse.getExternalReferenceCode(),
				ReflectionTestUtil.invoke(existingCommerceInventoryWarehouse,
					"getOriginalExternalReferenceCode", new Class<?>[0])));
	}

	protected CommerceInventoryWarehouse addCommerceInventoryWarehouse()
		throws Exception {
		long pk = RandomTestUtil.nextLong();

		CommerceInventoryWarehouse commerceInventoryWarehouse = _persistence.create(pk);

		commerceInventoryWarehouse.setExternalReferenceCode(RandomTestUtil.randomString());

		commerceInventoryWarehouse.setCompanyId(RandomTestUtil.nextLong());

		commerceInventoryWarehouse.setUserId(RandomTestUtil.nextLong());

		commerceInventoryWarehouse.setUserName(RandomTestUtil.randomString());

		commerceInventoryWarehouse.setCreateDate(RandomTestUtil.nextDate());

		commerceInventoryWarehouse.setModifiedDate(RandomTestUtil.nextDate());

		commerceInventoryWarehouse.setName(RandomTestUtil.randomString());

		commerceInventoryWarehouse.setDescription(RandomTestUtil.randomString());

		commerceInventoryWarehouse.setActive(RandomTestUtil.randomBoolean());

		commerceInventoryWarehouse.setStreet1(RandomTestUtil.randomString());

		commerceInventoryWarehouse.setStreet2(RandomTestUtil.randomString());

		commerceInventoryWarehouse.setStreet3(RandomTestUtil.randomString());

		commerceInventoryWarehouse.setCity(RandomTestUtil.randomString());

		commerceInventoryWarehouse.setZip(RandomTestUtil.randomString());

		commerceInventoryWarehouse.setCommerceRegionCode(RandomTestUtil.randomString());

		commerceInventoryWarehouse.setCountryTwoLettersISOCode(RandomTestUtil.randomString());

		commerceInventoryWarehouse.setLatitude(RandomTestUtil.nextDouble());

		commerceInventoryWarehouse.setLongitude(RandomTestUtil.nextDouble());

		commerceInventoryWarehouse.setType(RandomTestUtil.randomString());

		_commerceInventoryWarehouses.add(_persistence.update(
				commerceInventoryWarehouse));

		return commerceInventoryWarehouse;
	}

	private List<CommerceInventoryWarehouse> _commerceInventoryWarehouses = new ArrayList<CommerceInventoryWarehouse>();
	private CommerceInventoryWarehousePersistence _persistence;
	private ClassLoader _dynamicQueryClassLoader;
}