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
import com.liferay.commerce.price.list.exception.NoSuchPriceEntryException;
import com.liferay.commerce.price.list.model.CommercePriceEntry;
import com.liferay.commerce.price.list.service.CommercePriceEntryLocalServiceUtil;
import com.liferay.commerce.price.list.service.persistence.CommercePriceEntryPersistence;
import com.liferay.commerce.price.list.service.persistence.CommercePriceEntryUtil;
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
public class CommercePriceEntryPersistenceTest {

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
		_persistence = CommercePriceEntryUtil.getPersistence();

		Class<?> clazz = _persistence.getClass();

		_dynamicQueryClassLoader = clazz.getClassLoader();
	}

	@After
	public void tearDown() throws Exception {
		Iterator<CommercePriceEntry> iterator =
			_commercePriceEntries.iterator();

		while (iterator.hasNext()) {
			_persistence.remove(iterator.next());

			iterator.remove();
		}
	}

	@Test
	public void testCreate() throws Exception {
		long pk = RandomTestUtil.nextLong();

		CommercePriceEntry commercePriceEntry = _persistence.create(pk);

		Assert.assertNotNull(commercePriceEntry);

		Assert.assertEquals(commercePriceEntry.getPrimaryKey(), pk);
	}

	@Test
	public void testRemove() throws Exception {
		CommercePriceEntry newCommercePriceEntry = addCommercePriceEntry();

		_persistence.remove(newCommercePriceEntry);

		CommercePriceEntry existingCommercePriceEntry =
			_persistence.fetchByPrimaryKey(
				newCommercePriceEntry.getPrimaryKey());

		Assert.assertNull(existingCommercePriceEntry);
	}

	@Test
	public void testUpdateNew() throws Exception {
		addCommercePriceEntry();
	}

	@Test
	public void testUpdateExisting() throws Exception {
		long pk = RandomTestUtil.nextLong();

		CommercePriceEntry newCommercePriceEntry = _persistence.create(pk);

		newCommercePriceEntry.setUuid(RandomTestUtil.randomString());

		newCommercePriceEntry.setExternalReferenceCode(
			RandomTestUtil.randomString());

		newCommercePriceEntry.setGroupId(RandomTestUtil.nextLong());

		newCommercePriceEntry.setCompanyId(RandomTestUtil.nextLong());

		newCommercePriceEntry.setUserId(RandomTestUtil.nextLong());

		newCommercePriceEntry.setUserName(RandomTestUtil.randomString());

		newCommercePriceEntry.setCreateDate(RandomTestUtil.nextDate());

		newCommercePriceEntry.setModifiedDate(RandomTestUtil.nextDate());

		newCommercePriceEntry.setCommercePriceListId(RandomTestUtil.nextLong());

		newCommercePriceEntry.setCPInstanceUuid(RandomTestUtil.randomString());

		newCommercePriceEntry.setCProductId(RandomTestUtil.nextLong());

		newCommercePriceEntry.setPrice(
			new BigDecimal(RandomTestUtil.nextDouble()));

		newCommercePriceEntry.setPromoPrice(
			new BigDecimal(RandomTestUtil.nextDouble()));

		newCommercePriceEntry.setHasTierPrice(RandomTestUtil.randomBoolean());

		newCommercePriceEntry.setLastPublishDate(RandomTestUtil.nextDate());

		_commercePriceEntries.add(_persistence.update(newCommercePriceEntry));

		CommercePriceEntry existingCommercePriceEntry =
			_persistence.findByPrimaryKey(
				newCommercePriceEntry.getPrimaryKey());

		Assert.assertEquals(
			existingCommercePriceEntry.getUuid(),
			newCommercePriceEntry.getUuid());
		Assert.assertEquals(
			existingCommercePriceEntry.getExternalReferenceCode(),
			newCommercePriceEntry.getExternalReferenceCode());
		Assert.assertEquals(
			existingCommercePriceEntry.getCommercePriceEntryId(),
			newCommercePriceEntry.getCommercePriceEntryId());
		Assert.assertEquals(
			existingCommercePriceEntry.getGroupId(),
			newCommercePriceEntry.getGroupId());
		Assert.assertEquals(
			existingCommercePriceEntry.getCompanyId(),
			newCommercePriceEntry.getCompanyId());
		Assert.assertEquals(
			existingCommercePriceEntry.getUserId(),
			newCommercePriceEntry.getUserId());
		Assert.assertEquals(
			existingCommercePriceEntry.getUserName(),
			newCommercePriceEntry.getUserName());
		Assert.assertEquals(
			Time.getShortTimestamp(existingCommercePriceEntry.getCreateDate()),
			Time.getShortTimestamp(newCommercePriceEntry.getCreateDate()));
		Assert.assertEquals(
			Time.getShortTimestamp(
				existingCommercePriceEntry.getModifiedDate()),
			Time.getShortTimestamp(newCommercePriceEntry.getModifiedDate()));
		Assert.assertEquals(
			existingCommercePriceEntry.getCommercePriceListId(),
			newCommercePriceEntry.getCommercePriceListId());
		Assert.assertEquals(
			existingCommercePriceEntry.getCPInstanceUuid(),
			newCommercePriceEntry.getCPInstanceUuid());
		Assert.assertEquals(
			existingCommercePriceEntry.getCProductId(),
			newCommercePriceEntry.getCProductId());
		Assert.assertEquals(
			existingCommercePriceEntry.getPrice(),
			newCommercePriceEntry.getPrice());
		Assert.assertEquals(
			existingCommercePriceEntry.getPromoPrice(),
			newCommercePriceEntry.getPromoPrice());
		Assert.assertEquals(
			existingCommercePriceEntry.isHasTierPrice(),
			newCommercePriceEntry.isHasTierPrice());
		Assert.assertEquals(
			Time.getShortTimestamp(
				existingCommercePriceEntry.getLastPublishDate()),
			Time.getShortTimestamp(newCommercePriceEntry.getLastPublishDate()));
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
	public void testCountByCompanyId() throws Exception {
		_persistence.countByCompanyId(RandomTestUtil.nextLong());

		_persistence.countByCompanyId(0L);
	}

	@Test
	public void testCountByCommercePriceListId() throws Exception {
		_persistence.countByCommercePriceListId(RandomTestUtil.nextLong());

		_persistence.countByCommercePriceListId(0L);
	}

	@Test
	public void testCountByCPInstanceUuid() throws Exception {
		_persistence.countByCPInstanceUuid("");

		_persistence.countByCPInstanceUuid("null");

		_persistence.countByCPInstanceUuid((String)null);
	}

	@Test
	public void testCountByC_C() throws Exception {
		_persistence.countByC_C(RandomTestUtil.nextLong(), "");

		_persistence.countByC_C(0L, "null");

		_persistence.countByC_C(0L, (String)null);
	}

	@Test
	public void testCountByC_ERC() throws Exception {
		_persistence.countByC_ERC(RandomTestUtil.nextLong(), "");

		_persistence.countByC_ERC(0L, "null");

		_persistence.countByC_ERC(0L, (String)null);
	}

	@Test
	public void testFindByPrimaryKeyExisting() throws Exception {
		CommercePriceEntry newCommercePriceEntry = addCommercePriceEntry();

		CommercePriceEntry existingCommercePriceEntry =
			_persistence.findByPrimaryKey(
				newCommercePriceEntry.getPrimaryKey());

		Assert.assertEquals(existingCommercePriceEntry, newCommercePriceEntry);
	}

	@Test(expected = NoSuchPriceEntryException.class)
	public void testFindByPrimaryKeyMissing() throws Exception {
		long pk = RandomTestUtil.nextLong();

		_persistence.findByPrimaryKey(pk);
	}

	@Test
	public void testFindAll() throws Exception {
		_persistence.findAll(
			QueryUtil.ALL_POS, QueryUtil.ALL_POS, getOrderByComparator());
	}

	protected OrderByComparator<CommercePriceEntry> getOrderByComparator() {
		return OrderByComparatorFactoryUtil.create(
			"CommercePriceEntry", "uuid", true, "externalReferenceCode", true,
			"commercePriceEntryId", true, "groupId", true, "companyId", true,
			"userId", true, "userName", true, "createDate", true,
			"modifiedDate", true, "commercePriceListId", true, "CPInstanceUuid",
			true, "CProductId", true, "price", true, "promoPrice", true,
			"hasTierPrice", true, "lastPublishDate", true);
	}

	@Test
	public void testFetchByPrimaryKeyExisting() throws Exception {
		CommercePriceEntry newCommercePriceEntry = addCommercePriceEntry();

		CommercePriceEntry existingCommercePriceEntry =
			_persistence.fetchByPrimaryKey(
				newCommercePriceEntry.getPrimaryKey());

		Assert.assertEquals(existingCommercePriceEntry, newCommercePriceEntry);
	}

	@Test
	public void testFetchByPrimaryKeyMissing() throws Exception {
		long pk = RandomTestUtil.nextLong();

		CommercePriceEntry missingCommercePriceEntry =
			_persistence.fetchByPrimaryKey(pk);

		Assert.assertNull(missingCommercePriceEntry);
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereAllPrimaryKeysExist()
		throws Exception {

		CommercePriceEntry newCommercePriceEntry1 = addCommercePriceEntry();
		CommercePriceEntry newCommercePriceEntry2 = addCommercePriceEntry();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newCommercePriceEntry1.getPrimaryKey());
		primaryKeys.add(newCommercePriceEntry2.getPrimaryKey());

		Map<Serializable, CommercePriceEntry> commercePriceEntries =
			_persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(2, commercePriceEntries.size());
		Assert.assertEquals(
			newCommercePriceEntry1,
			commercePriceEntries.get(newCommercePriceEntry1.getPrimaryKey()));
		Assert.assertEquals(
			newCommercePriceEntry2,
			commercePriceEntries.get(newCommercePriceEntry2.getPrimaryKey()));
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereNoPrimaryKeysExist()
		throws Exception {

		long pk1 = RandomTestUtil.nextLong();

		long pk2 = RandomTestUtil.nextLong();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(pk1);
		primaryKeys.add(pk2);

		Map<Serializable, CommercePriceEntry> commercePriceEntries =
			_persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertTrue(commercePriceEntries.isEmpty());
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereSomePrimaryKeysExist()
		throws Exception {

		CommercePriceEntry newCommercePriceEntry = addCommercePriceEntry();

		long pk = RandomTestUtil.nextLong();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newCommercePriceEntry.getPrimaryKey());
		primaryKeys.add(pk);

		Map<Serializable, CommercePriceEntry> commercePriceEntries =
			_persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(1, commercePriceEntries.size());
		Assert.assertEquals(
			newCommercePriceEntry,
			commercePriceEntries.get(newCommercePriceEntry.getPrimaryKey()));
	}

	@Test
	public void testFetchByPrimaryKeysWithNoPrimaryKeys() throws Exception {
		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		Map<Serializable, CommercePriceEntry> commercePriceEntries =
			_persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertTrue(commercePriceEntries.isEmpty());
	}

	@Test
	public void testFetchByPrimaryKeysWithOnePrimaryKey() throws Exception {
		CommercePriceEntry newCommercePriceEntry = addCommercePriceEntry();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newCommercePriceEntry.getPrimaryKey());

		Map<Serializable, CommercePriceEntry> commercePriceEntries =
			_persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(1, commercePriceEntries.size());
		Assert.assertEquals(
			newCommercePriceEntry,
			commercePriceEntries.get(newCommercePriceEntry.getPrimaryKey()));
	}

	@Test
	public void testActionableDynamicQuery() throws Exception {
		final IntegerWrapper count = new IntegerWrapper();

		ActionableDynamicQuery actionableDynamicQuery =
			CommercePriceEntryLocalServiceUtil.getActionableDynamicQuery();

		actionableDynamicQuery.setPerformActionMethod(
			new ActionableDynamicQuery.PerformActionMethod
				<CommercePriceEntry>() {

				@Override
				public void performAction(
					CommercePriceEntry commercePriceEntry) {

					Assert.assertNotNull(commercePriceEntry);

					count.increment();
				}

			});

		actionableDynamicQuery.performActions();

		Assert.assertEquals(count.getValue(), _persistence.countAll());
	}

	@Test
	public void testDynamicQueryByPrimaryKeyExisting() throws Exception {
		CommercePriceEntry newCommercePriceEntry = addCommercePriceEntry();

		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(
			CommercePriceEntry.class, _dynamicQueryClassLoader);

		dynamicQuery.add(
			RestrictionsFactoryUtil.eq(
				"commercePriceEntryId",
				newCommercePriceEntry.getCommercePriceEntryId()));

		List<CommercePriceEntry> result = _persistence.findWithDynamicQuery(
			dynamicQuery);

		Assert.assertEquals(1, result.size());

		CommercePriceEntry existingCommercePriceEntry = result.get(0);

		Assert.assertEquals(existingCommercePriceEntry, newCommercePriceEntry);
	}

	@Test
	public void testDynamicQueryByPrimaryKeyMissing() throws Exception {
		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(
			CommercePriceEntry.class, _dynamicQueryClassLoader);

		dynamicQuery.add(
			RestrictionsFactoryUtil.eq(
				"commercePriceEntryId", RandomTestUtil.nextLong()));

		List<CommercePriceEntry> result = _persistence.findWithDynamicQuery(
			dynamicQuery);

		Assert.assertEquals(0, result.size());
	}

	@Test
	public void testDynamicQueryByProjectionExisting() throws Exception {
		CommercePriceEntry newCommercePriceEntry = addCommercePriceEntry();

		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(
			CommercePriceEntry.class, _dynamicQueryClassLoader);

		dynamicQuery.setProjection(
			ProjectionFactoryUtil.property("commercePriceEntryId"));

		Object newCommercePriceEntryId =
			newCommercePriceEntry.getCommercePriceEntryId();

		dynamicQuery.add(
			RestrictionsFactoryUtil.in(
				"commercePriceEntryId",
				new Object[] {newCommercePriceEntryId}));

		List<Object> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(1, result.size());

		Object existingCommercePriceEntryId = result.get(0);

		Assert.assertEquals(
			existingCommercePriceEntryId, newCommercePriceEntryId);
	}

	@Test
	public void testDynamicQueryByProjectionMissing() throws Exception {
		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(
			CommercePriceEntry.class, _dynamicQueryClassLoader);

		dynamicQuery.setProjection(
			ProjectionFactoryUtil.property("commercePriceEntryId"));

		dynamicQuery.add(
			RestrictionsFactoryUtil.in(
				"commercePriceEntryId",
				new Object[] {RandomTestUtil.nextLong()}));

		List<Object> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(0, result.size());
	}

	@Test
	public void testResetOriginalValues() throws Exception {
		CommercePriceEntry newCommercePriceEntry = addCommercePriceEntry();

		_persistence.clearCache();

		CommercePriceEntry existingCommercePriceEntry =
			_persistence.findByPrimaryKey(
				newCommercePriceEntry.getPrimaryKey());

		Assert.assertTrue(
			Objects.equals(
				existingCommercePriceEntry.getUuid(),
				ReflectionTestUtil.invoke(
					existingCommercePriceEntry, "getOriginalUuid",
					new Class<?>[0])));
		Assert.assertEquals(
			Long.valueOf(existingCommercePriceEntry.getGroupId()),
			ReflectionTestUtil.<Long>invoke(
				existingCommercePriceEntry, "getOriginalGroupId",
				new Class<?>[0]));

		Assert.assertEquals(
			Long.valueOf(existingCommercePriceEntry.getCommercePriceListId()),
			ReflectionTestUtil.<Long>invoke(
				existingCommercePriceEntry, "getOriginalCommercePriceListId",
				new Class<?>[0]));
		Assert.assertTrue(
			Objects.equals(
				existingCommercePriceEntry.getCPInstanceUuid(),
				ReflectionTestUtil.invoke(
					existingCommercePriceEntry, "getOriginalCPInstanceUuid",
					new Class<?>[0])));

		Assert.assertEquals(
			Long.valueOf(existingCommercePriceEntry.getCompanyId()),
			ReflectionTestUtil.<Long>invoke(
				existingCommercePriceEntry, "getOriginalCompanyId",
				new Class<?>[0]));
		Assert.assertTrue(
			Objects.equals(
				existingCommercePriceEntry.getExternalReferenceCode(),
				ReflectionTestUtil.invoke(
					existingCommercePriceEntry,
					"getOriginalExternalReferenceCode", new Class<?>[0])));
	}

	protected CommercePriceEntry addCommercePriceEntry() throws Exception {
		long pk = RandomTestUtil.nextLong();

		CommercePriceEntry commercePriceEntry = _persistence.create(pk);

		commercePriceEntry.setUuid(RandomTestUtil.randomString());

		commercePriceEntry.setExternalReferenceCode(
			RandomTestUtil.randomString());

		commercePriceEntry.setGroupId(RandomTestUtil.nextLong());

		commercePriceEntry.setCompanyId(RandomTestUtil.nextLong());

		commercePriceEntry.setUserId(RandomTestUtil.nextLong());

		commercePriceEntry.setUserName(RandomTestUtil.randomString());

		commercePriceEntry.setCreateDate(RandomTestUtil.nextDate());

		commercePriceEntry.setModifiedDate(RandomTestUtil.nextDate());

		commercePriceEntry.setCommercePriceListId(RandomTestUtil.nextLong());

		commercePriceEntry.setCPInstanceUuid(RandomTestUtil.randomString());

		commercePriceEntry.setCProductId(RandomTestUtil.nextLong());

		commercePriceEntry.setPrice(
			new BigDecimal(RandomTestUtil.nextDouble()));

		commercePriceEntry.setPromoPrice(
			new BigDecimal(RandomTestUtil.nextDouble()));

		commercePriceEntry.setHasTierPrice(RandomTestUtil.randomBoolean());

		commercePriceEntry.setLastPublishDate(RandomTestUtil.nextDate());

		_commercePriceEntries.add(_persistence.update(commercePriceEntry));

		return commercePriceEntry;
	}

	private List<CommercePriceEntry> _commercePriceEntries =
		new ArrayList<CommercePriceEntry>();
	private CommercePriceEntryPersistence _persistence;
	private ClassLoader _dynamicQueryClassLoader;

}