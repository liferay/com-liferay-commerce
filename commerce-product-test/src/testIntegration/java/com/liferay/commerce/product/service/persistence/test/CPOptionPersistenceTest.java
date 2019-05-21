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
import com.liferay.commerce.product.exception.NoSuchCPOptionException;
import com.liferay.commerce.product.model.CPOption;
import com.liferay.commerce.product.service.CPOptionLocalServiceUtil;
import com.liferay.commerce.product.service.persistence.CPOptionPersistence;
import com.liferay.commerce.product.service.persistence.CPOptionUtil;
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
public class CPOptionPersistenceTest {

	@ClassRule
	@Rule
	public static final AggregateTestRule aggregateTestRule =
		new AggregateTestRule(
			new LiferayIntegrationTestRule(), PersistenceTestRule.INSTANCE,
			new TransactionalTestRule(
				Propagation.REQUIRED, "com.liferay.commerce.product.service"));

	@Before
	public void setUp() {
		_persistence = CPOptionUtil.getPersistence();

		Class<?> clazz = _persistence.getClass();

		_dynamicQueryClassLoader = clazz.getClassLoader();
	}

	@After
	public void tearDown() throws Exception {
		Iterator<CPOption> iterator = _cpOptions.iterator();

		while (iterator.hasNext()) {
			_persistence.remove(iterator.next());

			iterator.remove();
		}
	}

	@Test
	public void testCreate() throws Exception {
		long pk = RandomTestUtil.nextLong();

		CPOption cpOption = _persistence.create(pk);

		Assert.assertNotNull(cpOption);

		Assert.assertEquals(cpOption.getPrimaryKey(), pk);
	}

	@Test
	public void testRemove() throws Exception {
		CPOption newCPOption = addCPOption();

		_persistence.remove(newCPOption);

		CPOption existingCPOption = _persistence.fetchByPrimaryKey(
			newCPOption.getPrimaryKey());

		Assert.assertNull(existingCPOption);
	}

	@Test
	public void testUpdateNew() throws Exception {
		addCPOption();
	}

	@Test
	public void testUpdateExisting() throws Exception {
		long pk = RandomTestUtil.nextLong();

		CPOption newCPOption = _persistence.create(pk);

		newCPOption.setUuid(RandomTestUtil.randomString());

		newCPOption.setExternalReferenceCode(RandomTestUtil.randomString());

		newCPOption.setGroupId(RandomTestUtil.nextLong());

		newCPOption.setCompanyId(RandomTestUtil.nextLong());

		newCPOption.setUserId(RandomTestUtil.nextLong());

		newCPOption.setUserName(RandomTestUtil.randomString());

		newCPOption.setCreateDate(RandomTestUtil.nextDate());

		newCPOption.setModifiedDate(RandomTestUtil.nextDate());

		newCPOption.setName(RandomTestUtil.randomString());

		newCPOption.setDescription(RandomTestUtil.randomString());

		newCPOption.setDDMFormFieldTypeName(RandomTestUtil.randomString());

		newCPOption.setFacetable(RandomTestUtil.randomBoolean());

		newCPOption.setRequired(RandomTestUtil.randomBoolean());

		newCPOption.setSkuContributor(RandomTestUtil.randomBoolean());

		newCPOption.setKey(RandomTestUtil.randomString());

		newCPOption.setLastPublishDate(RandomTestUtil.nextDate());

		_cpOptions.add(_persistence.update(newCPOption));

		CPOption existingCPOption = _persistence.findByPrimaryKey(
			newCPOption.getPrimaryKey());

		Assert.assertEquals(existingCPOption.getUuid(), newCPOption.getUuid());
		Assert.assertEquals(
			existingCPOption.getExternalReferenceCode(),
			newCPOption.getExternalReferenceCode());
		Assert.assertEquals(
			existingCPOption.getCPOptionId(), newCPOption.getCPOptionId());
		Assert.assertEquals(
			existingCPOption.getGroupId(), newCPOption.getGroupId());
		Assert.assertEquals(
			existingCPOption.getCompanyId(), newCPOption.getCompanyId());
		Assert.assertEquals(
			existingCPOption.getUserId(), newCPOption.getUserId());
		Assert.assertEquals(
			existingCPOption.getUserName(), newCPOption.getUserName());
		Assert.assertEquals(
			Time.getShortTimestamp(existingCPOption.getCreateDate()),
			Time.getShortTimestamp(newCPOption.getCreateDate()));
		Assert.assertEquals(
			Time.getShortTimestamp(existingCPOption.getModifiedDate()),
			Time.getShortTimestamp(newCPOption.getModifiedDate()));
		Assert.assertEquals(existingCPOption.getName(), newCPOption.getName());
		Assert.assertEquals(
			existingCPOption.getDescription(), newCPOption.getDescription());
		Assert.assertEquals(
			existingCPOption.getDDMFormFieldTypeName(),
			newCPOption.getDDMFormFieldTypeName());
		Assert.assertEquals(
			existingCPOption.isFacetable(), newCPOption.isFacetable());
		Assert.assertEquals(
			existingCPOption.isRequired(), newCPOption.isRequired());
		Assert.assertEquals(
			existingCPOption.isSkuContributor(),
			newCPOption.isSkuContributor());
		Assert.assertEquals(existingCPOption.getKey(), newCPOption.getKey());
		Assert.assertEquals(
			Time.getShortTimestamp(existingCPOption.getLastPublishDate()),
			Time.getShortTimestamp(newCPOption.getLastPublishDate()));
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
	public void testCountByG_K() throws Exception {
		_persistence.countByG_K(RandomTestUtil.nextLong(), "");

		_persistence.countByG_K(0L, "null");

		_persistence.countByG_K(0L, (String)null);
	}

	@Test
	public void testCountByC_ERC() throws Exception {
		_persistence.countByC_ERC(RandomTestUtil.nextLong(), "");

		_persistence.countByC_ERC(0L, "null");

		_persistence.countByC_ERC(0L, (String)null);
	}

	@Test
	public void testFindByPrimaryKeyExisting() throws Exception {
		CPOption newCPOption = addCPOption();

		CPOption existingCPOption = _persistence.findByPrimaryKey(
			newCPOption.getPrimaryKey());

		Assert.assertEquals(existingCPOption, newCPOption);
	}

	@Test(expected = NoSuchCPOptionException.class)
	public void testFindByPrimaryKeyMissing() throws Exception {
		long pk = RandomTestUtil.nextLong();

		_persistence.findByPrimaryKey(pk);
	}

	@Test
	public void testFindAll() throws Exception {
		_persistence.findAll(
			QueryUtil.ALL_POS, QueryUtil.ALL_POS, getOrderByComparator());
	}

	protected OrderByComparator<CPOption> getOrderByComparator() {
		return OrderByComparatorFactoryUtil.create(
			"CPOption", "uuid", true, "externalReferenceCode", true,
			"CPOptionId", true, "groupId", true, "companyId", true, "userId",
			true, "userName", true, "createDate", true, "modifiedDate", true,
			"name", true, "description", true, "DDMFormFieldTypeName", true,
			"facetable", true, "required", true, "skuContributor", true, "key",
			true, "lastPublishDate", true);
	}

	@Test
	public void testFetchByPrimaryKeyExisting() throws Exception {
		CPOption newCPOption = addCPOption();

		CPOption existingCPOption = _persistence.fetchByPrimaryKey(
			newCPOption.getPrimaryKey());

		Assert.assertEquals(existingCPOption, newCPOption);
	}

	@Test
	public void testFetchByPrimaryKeyMissing() throws Exception {
		long pk = RandomTestUtil.nextLong();

		CPOption missingCPOption = _persistence.fetchByPrimaryKey(pk);

		Assert.assertNull(missingCPOption);
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereAllPrimaryKeysExist()
		throws Exception {

		CPOption newCPOption1 = addCPOption();
		CPOption newCPOption2 = addCPOption();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newCPOption1.getPrimaryKey());
		primaryKeys.add(newCPOption2.getPrimaryKey());

		Map<Serializable, CPOption> cpOptions = _persistence.fetchByPrimaryKeys(
			primaryKeys);

		Assert.assertEquals(2, cpOptions.size());
		Assert.assertEquals(
			newCPOption1, cpOptions.get(newCPOption1.getPrimaryKey()));
		Assert.assertEquals(
			newCPOption2, cpOptions.get(newCPOption2.getPrimaryKey()));
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereNoPrimaryKeysExist()
		throws Exception {

		long pk1 = RandomTestUtil.nextLong();

		long pk2 = RandomTestUtil.nextLong();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(pk1);
		primaryKeys.add(pk2);

		Map<Serializable, CPOption> cpOptions = _persistence.fetchByPrimaryKeys(
			primaryKeys);

		Assert.assertTrue(cpOptions.isEmpty());
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereSomePrimaryKeysExist()
		throws Exception {

		CPOption newCPOption = addCPOption();

		long pk = RandomTestUtil.nextLong();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newCPOption.getPrimaryKey());
		primaryKeys.add(pk);

		Map<Serializable, CPOption> cpOptions = _persistence.fetchByPrimaryKeys(
			primaryKeys);

		Assert.assertEquals(1, cpOptions.size());
		Assert.assertEquals(
			newCPOption, cpOptions.get(newCPOption.getPrimaryKey()));
	}

	@Test
	public void testFetchByPrimaryKeysWithNoPrimaryKeys() throws Exception {
		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		Map<Serializable, CPOption> cpOptions = _persistence.fetchByPrimaryKeys(
			primaryKeys);

		Assert.assertTrue(cpOptions.isEmpty());
	}

	@Test
	public void testFetchByPrimaryKeysWithOnePrimaryKey() throws Exception {
		CPOption newCPOption = addCPOption();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newCPOption.getPrimaryKey());

		Map<Serializable, CPOption> cpOptions = _persistence.fetchByPrimaryKeys(
			primaryKeys);

		Assert.assertEquals(1, cpOptions.size());
		Assert.assertEquals(
			newCPOption, cpOptions.get(newCPOption.getPrimaryKey()));
	}

	@Test
	public void testActionableDynamicQuery() throws Exception {
		final IntegerWrapper count = new IntegerWrapper();

		ActionableDynamicQuery actionableDynamicQuery =
			CPOptionLocalServiceUtil.getActionableDynamicQuery();

		actionableDynamicQuery.setPerformActionMethod(
			new ActionableDynamicQuery.PerformActionMethod<CPOption>() {

				@Override
				public void performAction(CPOption cpOption) {
					Assert.assertNotNull(cpOption);

					count.increment();
				}

			});

		actionableDynamicQuery.performActions();

		Assert.assertEquals(count.getValue(), _persistence.countAll());
	}

	@Test
	public void testDynamicQueryByPrimaryKeyExisting() throws Exception {
		CPOption newCPOption = addCPOption();

		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(
			CPOption.class, _dynamicQueryClassLoader);

		dynamicQuery.add(
			RestrictionsFactoryUtil.eq(
				"CPOptionId", newCPOption.getCPOptionId()));

		List<CPOption> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(1, result.size());

		CPOption existingCPOption = result.get(0);

		Assert.assertEquals(existingCPOption, newCPOption);
	}

	@Test
	public void testDynamicQueryByPrimaryKeyMissing() throws Exception {
		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(
			CPOption.class, _dynamicQueryClassLoader);

		dynamicQuery.add(
			RestrictionsFactoryUtil.eq(
				"CPOptionId", RandomTestUtil.nextLong()));

		List<CPOption> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(0, result.size());
	}

	@Test
	public void testDynamicQueryByProjectionExisting() throws Exception {
		CPOption newCPOption = addCPOption();

		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(
			CPOption.class, _dynamicQueryClassLoader);

		dynamicQuery.setProjection(
			ProjectionFactoryUtil.property("CPOptionId"));

		Object newCPOptionId = newCPOption.getCPOptionId();

		dynamicQuery.add(
			RestrictionsFactoryUtil.in(
				"CPOptionId", new Object[] {newCPOptionId}));

		List<Object> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(1, result.size());

		Object existingCPOptionId = result.get(0);

		Assert.assertEquals(existingCPOptionId, newCPOptionId);
	}

	@Test
	public void testDynamicQueryByProjectionMissing() throws Exception {
		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(
			CPOption.class, _dynamicQueryClassLoader);

		dynamicQuery.setProjection(
			ProjectionFactoryUtil.property("CPOptionId"));

		dynamicQuery.add(
			RestrictionsFactoryUtil.in(
				"CPOptionId", new Object[] {RandomTestUtil.nextLong()}));

		List<Object> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(0, result.size());
	}

	@Test
	public void testResetOriginalValues() throws Exception {
		CPOption newCPOption = addCPOption();

		_persistence.clearCache();

		CPOption existingCPOption = _persistence.findByPrimaryKey(
			newCPOption.getPrimaryKey());

		Assert.assertTrue(
			Objects.equals(
				existingCPOption.getUuid(),
				ReflectionTestUtil.invoke(
					existingCPOption, "getOriginalUuid", new Class<?>[0])));
		Assert.assertEquals(
			Long.valueOf(existingCPOption.getGroupId()),
			ReflectionTestUtil.<Long>invoke(
				existingCPOption, "getOriginalGroupId", new Class<?>[0]));

		Assert.assertEquals(
			Long.valueOf(existingCPOption.getGroupId()),
			ReflectionTestUtil.<Long>invoke(
				existingCPOption, "getOriginalGroupId", new Class<?>[0]));
		Assert.assertTrue(
			Objects.equals(
				existingCPOption.getKey(),
				ReflectionTestUtil.invoke(
					existingCPOption, "getOriginalKey", new Class<?>[0])));

		Assert.assertEquals(
			Long.valueOf(existingCPOption.getCompanyId()),
			ReflectionTestUtil.<Long>invoke(
				existingCPOption, "getOriginalCompanyId", new Class<?>[0]));
		Assert.assertTrue(
			Objects.equals(
				existingCPOption.getExternalReferenceCode(),
				ReflectionTestUtil.invoke(
					existingCPOption, "getOriginalExternalReferenceCode",
					new Class<?>[0])));
	}

	protected CPOption addCPOption() throws Exception {
		long pk = RandomTestUtil.nextLong();

		CPOption cpOption = _persistence.create(pk);

		cpOption.setUuid(RandomTestUtil.randomString());

		cpOption.setExternalReferenceCode(RandomTestUtil.randomString());

		cpOption.setGroupId(RandomTestUtil.nextLong());

		cpOption.setCompanyId(RandomTestUtil.nextLong());

		cpOption.setUserId(RandomTestUtil.nextLong());

		cpOption.setUserName(RandomTestUtil.randomString());

		cpOption.setCreateDate(RandomTestUtil.nextDate());

		cpOption.setModifiedDate(RandomTestUtil.nextDate());

		cpOption.setName(RandomTestUtil.randomString());

		cpOption.setDescription(RandomTestUtil.randomString());

		cpOption.setDDMFormFieldTypeName(RandomTestUtil.randomString());

		cpOption.setFacetable(RandomTestUtil.randomBoolean());

		cpOption.setRequired(RandomTestUtil.randomBoolean());

		cpOption.setSkuContributor(RandomTestUtil.randomBoolean());

		cpOption.setKey(RandomTestUtil.randomString());

		cpOption.setLastPublishDate(RandomTestUtil.nextDate());

		_cpOptions.add(_persistence.update(cpOption));

		return cpOption;
	}

	private List<CPOption> _cpOptions = new ArrayList<CPOption>();
	private CPOptionPersistence _persistence;
	private ClassLoader _dynamicQueryClassLoader;

}