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
import com.liferay.commerce.product.exception.NoSuchCProductException;
import com.liferay.commerce.product.model.CProduct;
import com.liferay.commerce.product.service.CProductLocalServiceUtil;
import com.liferay.commerce.product.service.persistence.CProductPersistence;
import com.liferay.commerce.product.service.persistence.CProductUtil;
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
public class CProductPersistenceTest {

	@ClassRule
	@Rule
	public static final AggregateTestRule aggregateTestRule =
		new AggregateTestRule(
			new LiferayIntegrationTestRule(), PersistenceTestRule.INSTANCE,
			new TransactionalTestRule(
				Propagation.REQUIRED, "com.liferay.commerce.product.service"));

	@Before
	public void setUp() {
		_persistence = CProductUtil.getPersistence();

		Class<?> clazz = _persistence.getClass();

		_dynamicQueryClassLoader = clazz.getClassLoader();
	}

	@After
	public void tearDown() throws Exception {
		Iterator<CProduct> iterator = _cProducts.iterator();

		while (iterator.hasNext()) {
			_persistence.remove(iterator.next());

			iterator.remove();
		}
	}

	@Test
	public void testCreate() throws Exception {
		long pk = RandomTestUtil.nextLong();

		CProduct cProduct = _persistence.create(pk);

		Assert.assertNotNull(cProduct);

		Assert.assertEquals(cProduct.getPrimaryKey(), pk);
	}

	@Test
	public void testRemove() throws Exception {
		CProduct newCProduct = addCProduct();

		_persistence.remove(newCProduct);

		CProduct existingCProduct = _persistence.fetchByPrimaryKey(
			newCProduct.getPrimaryKey());

		Assert.assertNull(existingCProduct);
	}

	@Test
	public void testUpdateNew() throws Exception {
		addCProduct();
	}

	@Test
	public void testUpdateExisting() throws Exception {
		long pk = RandomTestUtil.nextLong();

		CProduct newCProduct = _persistence.create(pk);

		newCProduct.setUuid(RandomTestUtil.randomString());

		newCProduct.setExternalReferenceCode(RandomTestUtil.randomString());

		newCProduct.setGroupId(RandomTestUtil.nextLong());

		newCProduct.setCompanyId(RandomTestUtil.nextLong());

		newCProduct.setUserId(RandomTestUtil.nextLong());

		newCProduct.setUserName(RandomTestUtil.randomString());

		newCProduct.setCreateDate(RandomTestUtil.nextDate());

		newCProduct.setModifiedDate(RandomTestUtil.nextDate());

		newCProduct.setPublishedCPDefinitionId(RandomTestUtil.nextLong());

		newCProduct.setLatestVersion(RandomTestUtil.nextInt());

		_cProducts.add(_persistence.update(newCProduct));

		CProduct existingCProduct = _persistence.findByPrimaryKey(
			newCProduct.getPrimaryKey());

		Assert.assertEquals(existingCProduct.getUuid(), newCProduct.getUuid());
		Assert.assertEquals(
			existingCProduct.getExternalReferenceCode(),
			newCProduct.getExternalReferenceCode());
		Assert.assertEquals(
			existingCProduct.getCProductId(), newCProduct.getCProductId());
		Assert.assertEquals(
			existingCProduct.getGroupId(), newCProduct.getGroupId());
		Assert.assertEquals(
			existingCProduct.getCompanyId(), newCProduct.getCompanyId());
		Assert.assertEquals(
			existingCProduct.getUserId(), newCProduct.getUserId());
		Assert.assertEquals(
			existingCProduct.getUserName(), newCProduct.getUserName());
		Assert.assertEquals(
			Time.getShortTimestamp(existingCProduct.getCreateDate()),
			Time.getShortTimestamp(newCProduct.getCreateDate()));
		Assert.assertEquals(
			Time.getShortTimestamp(existingCProduct.getModifiedDate()),
			Time.getShortTimestamp(newCProduct.getModifiedDate()));
		Assert.assertEquals(
			existingCProduct.getPublishedCPDefinitionId(),
			newCProduct.getPublishedCPDefinitionId());
		Assert.assertEquals(
			existingCProduct.getLatestVersion(),
			newCProduct.getLatestVersion());
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
	public void testCountByC_ERC() throws Exception {
		_persistence.countByC_ERC(RandomTestUtil.nextLong(), "");

		_persistence.countByC_ERC(0L, "null");

		_persistence.countByC_ERC(0L, (String)null);
	}

	@Test
	public void testFindByPrimaryKeyExisting() throws Exception {
		CProduct newCProduct = addCProduct();

		CProduct existingCProduct = _persistence.findByPrimaryKey(
			newCProduct.getPrimaryKey());

		Assert.assertEquals(existingCProduct, newCProduct);
	}

	@Test(expected = NoSuchCProductException.class)
	public void testFindByPrimaryKeyMissing() throws Exception {
		long pk = RandomTestUtil.nextLong();

		_persistence.findByPrimaryKey(pk);
	}

	@Test
	public void testFindAll() throws Exception {
		_persistence.findAll(
			QueryUtil.ALL_POS, QueryUtil.ALL_POS, getOrderByComparator());
	}

	protected OrderByComparator<CProduct> getOrderByComparator() {
		return OrderByComparatorFactoryUtil.create(
			"CProduct", "uuid", true, "externalReferenceCode", true,
			"CProductId", true, "groupId", true, "companyId", true, "userId",
			true, "userName", true, "createDate", true, "modifiedDate", true,
			"publishedCPDefinitionId", true, "latestVersion", true);
	}

	@Test
	public void testFetchByPrimaryKeyExisting() throws Exception {
		CProduct newCProduct = addCProduct();

		CProduct existingCProduct = _persistence.fetchByPrimaryKey(
			newCProduct.getPrimaryKey());

		Assert.assertEquals(existingCProduct, newCProduct);
	}

	@Test
	public void testFetchByPrimaryKeyMissing() throws Exception {
		long pk = RandomTestUtil.nextLong();

		CProduct missingCProduct = _persistence.fetchByPrimaryKey(pk);

		Assert.assertNull(missingCProduct);
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereAllPrimaryKeysExist()
		throws Exception {

		CProduct newCProduct1 = addCProduct();
		CProduct newCProduct2 = addCProduct();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newCProduct1.getPrimaryKey());
		primaryKeys.add(newCProduct2.getPrimaryKey());

		Map<Serializable, CProduct> cProducts = _persistence.fetchByPrimaryKeys(
			primaryKeys);

		Assert.assertEquals(2, cProducts.size());
		Assert.assertEquals(
			newCProduct1, cProducts.get(newCProduct1.getPrimaryKey()));
		Assert.assertEquals(
			newCProduct2, cProducts.get(newCProduct2.getPrimaryKey()));
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereNoPrimaryKeysExist()
		throws Exception {

		long pk1 = RandomTestUtil.nextLong();

		long pk2 = RandomTestUtil.nextLong();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(pk1);
		primaryKeys.add(pk2);

		Map<Serializable, CProduct> cProducts = _persistence.fetchByPrimaryKeys(
			primaryKeys);

		Assert.assertTrue(cProducts.isEmpty());
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereSomePrimaryKeysExist()
		throws Exception {

		CProduct newCProduct = addCProduct();

		long pk = RandomTestUtil.nextLong();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newCProduct.getPrimaryKey());
		primaryKeys.add(pk);

		Map<Serializable, CProduct> cProducts = _persistence.fetchByPrimaryKeys(
			primaryKeys);

		Assert.assertEquals(1, cProducts.size());
		Assert.assertEquals(
			newCProduct, cProducts.get(newCProduct.getPrimaryKey()));
	}

	@Test
	public void testFetchByPrimaryKeysWithNoPrimaryKeys() throws Exception {
		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		Map<Serializable, CProduct> cProducts = _persistence.fetchByPrimaryKeys(
			primaryKeys);

		Assert.assertTrue(cProducts.isEmpty());
	}

	@Test
	public void testFetchByPrimaryKeysWithOnePrimaryKey() throws Exception {
		CProduct newCProduct = addCProduct();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newCProduct.getPrimaryKey());

		Map<Serializable, CProduct> cProducts = _persistence.fetchByPrimaryKeys(
			primaryKeys);

		Assert.assertEquals(1, cProducts.size());
		Assert.assertEquals(
			newCProduct, cProducts.get(newCProduct.getPrimaryKey()));
	}

	@Test
	public void testActionableDynamicQuery() throws Exception {
		final IntegerWrapper count = new IntegerWrapper();

		ActionableDynamicQuery actionableDynamicQuery =
			CProductLocalServiceUtil.getActionableDynamicQuery();

		actionableDynamicQuery.setPerformActionMethod(
			new ActionableDynamicQuery.PerformActionMethod<CProduct>() {

				@Override
				public void performAction(CProduct cProduct) {
					Assert.assertNotNull(cProduct);

					count.increment();
				}

			});

		actionableDynamicQuery.performActions();

		Assert.assertEquals(count.getValue(), _persistence.countAll());
	}

	@Test
	public void testDynamicQueryByPrimaryKeyExisting() throws Exception {
		CProduct newCProduct = addCProduct();

		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(
			CProduct.class, _dynamicQueryClassLoader);

		dynamicQuery.add(
			RestrictionsFactoryUtil.eq(
				"CProductId", newCProduct.getCProductId()));

		List<CProduct> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(1, result.size());

		CProduct existingCProduct = result.get(0);

		Assert.assertEquals(existingCProduct, newCProduct);
	}

	@Test
	public void testDynamicQueryByPrimaryKeyMissing() throws Exception {
		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(
			CProduct.class, _dynamicQueryClassLoader);

		dynamicQuery.add(
			RestrictionsFactoryUtil.eq(
				"CProductId", RandomTestUtil.nextLong()));

		List<CProduct> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(0, result.size());
	}

	@Test
	public void testDynamicQueryByProjectionExisting() throws Exception {
		CProduct newCProduct = addCProduct();

		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(
			CProduct.class, _dynamicQueryClassLoader);

		dynamicQuery.setProjection(
			ProjectionFactoryUtil.property("CProductId"));

		Object newCProductId = newCProduct.getCProductId();

		dynamicQuery.add(
			RestrictionsFactoryUtil.in(
				"CProductId", new Object[] {newCProductId}));

		List<Object> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(1, result.size());

		Object existingCProductId = result.get(0);

		Assert.assertEquals(existingCProductId, newCProductId);
	}

	@Test
	public void testDynamicQueryByProjectionMissing() throws Exception {
		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(
			CProduct.class, _dynamicQueryClassLoader);

		dynamicQuery.setProjection(
			ProjectionFactoryUtil.property("CProductId"));

		dynamicQuery.add(
			RestrictionsFactoryUtil.in(
				"CProductId", new Object[] {RandomTestUtil.nextLong()}));

		List<Object> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(0, result.size());
	}

	@Test
	public void testResetOriginalValues() throws Exception {
		CProduct newCProduct = addCProduct();

		_persistence.clearCache();

		CProduct existingCProduct = _persistence.findByPrimaryKey(
			newCProduct.getPrimaryKey());

		Assert.assertTrue(
			Objects.equals(
				existingCProduct.getUuid(),
				ReflectionTestUtil.invoke(
					existingCProduct, "getOriginalUuid", new Class<?>[0])));
		Assert.assertEquals(
			Long.valueOf(existingCProduct.getGroupId()),
			ReflectionTestUtil.<Long>invoke(
				existingCProduct, "getOriginalGroupId", new Class<?>[0]));

		Assert.assertEquals(
			Long.valueOf(existingCProduct.getCompanyId()),
			ReflectionTestUtil.<Long>invoke(
				existingCProduct, "getOriginalCompanyId", new Class<?>[0]));
		Assert.assertTrue(
			Objects.equals(
				existingCProduct.getExternalReferenceCode(),
				ReflectionTestUtil.invoke(
					existingCProduct, "getOriginalExternalReferenceCode",
					new Class<?>[0])));
	}

	protected CProduct addCProduct() throws Exception {
		long pk = RandomTestUtil.nextLong();

		CProduct cProduct = _persistence.create(pk);

		cProduct.setUuid(RandomTestUtil.randomString());

		cProduct.setExternalReferenceCode(RandomTestUtil.randomString());

		cProduct.setGroupId(RandomTestUtil.nextLong());

		cProduct.setCompanyId(RandomTestUtil.nextLong());

		cProduct.setUserId(RandomTestUtil.nextLong());

		cProduct.setUserName(RandomTestUtil.randomString());

		cProduct.setCreateDate(RandomTestUtil.nextDate());

		cProduct.setModifiedDate(RandomTestUtil.nextDate());

		cProduct.setPublishedCPDefinitionId(RandomTestUtil.nextLong());

		cProduct.setLatestVersion(RandomTestUtil.nextInt());

		_cProducts.add(_persistence.update(cProduct));

		return cProduct;
	}

	private List<CProduct> _cProducts = new ArrayList<CProduct>();
	private CProductPersistence _persistence;
	private ClassLoader _dynamicQueryClassLoader;

}