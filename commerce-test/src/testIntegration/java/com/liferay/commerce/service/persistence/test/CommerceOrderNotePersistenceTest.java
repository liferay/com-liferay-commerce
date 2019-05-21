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
import com.liferay.commerce.exception.NoSuchOrderNoteException;
import com.liferay.commerce.model.CommerceOrderNote;
import com.liferay.commerce.service.CommerceOrderNoteLocalServiceUtil;
import com.liferay.commerce.service.persistence.CommerceOrderNotePersistence;
import com.liferay.commerce.service.persistence.CommerceOrderNoteUtil;
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
public class CommerceOrderNotePersistenceTest {

	@ClassRule
	@Rule
	public static final AggregateTestRule aggregateTestRule =
		new AggregateTestRule(
			new LiferayIntegrationTestRule(), PersistenceTestRule.INSTANCE,
			new TransactionalTestRule(
				Propagation.REQUIRED, "com.liferay.commerce.service"));

	@Before
	public void setUp() {
		_persistence = CommerceOrderNoteUtil.getPersistence();

		Class<?> clazz = _persistence.getClass();

		_dynamicQueryClassLoader = clazz.getClassLoader();
	}

	@After
	public void tearDown() throws Exception {
		Iterator<CommerceOrderNote> iterator = _commerceOrderNotes.iterator();

		while (iterator.hasNext()) {
			_persistence.remove(iterator.next());

			iterator.remove();
		}
	}

	@Test
	public void testCreate() throws Exception {
		long pk = RandomTestUtil.nextLong();

		CommerceOrderNote commerceOrderNote = _persistence.create(pk);

		Assert.assertNotNull(commerceOrderNote);

		Assert.assertEquals(commerceOrderNote.getPrimaryKey(), pk);
	}

	@Test
	public void testRemove() throws Exception {
		CommerceOrderNote newCommerceOrderNote = addCommerceOrderNote();

		_persistence.remove(newCommerceOrderNote);

		CommerceOrderNote existingCommerceOrderNote =
			_persistence.fetchByPrimaryKey(
				newCommerceOrderNote.getPrimaryKey());

		Assert.assertNull(existingCommerceOrderNote);
	}

	@Test
	public void testUpdateNew() throws Exception {
		addCommerceOrderNote();
	}

	@Test
	public void testUpdateExisting() throws Exception {
		long pk = RandomTestUtil.nextLong();

		CommerceOrderNote newCommerceOrderNote = _persistence.create(pk);

		newCommerceOrderNote.setExternalReferenceCode(
			RandomTestUtil.randomString());

		newCommerceOrderNote.setGroupId(RandomTestUtil.nextLong());

		newCommerceOrderNote.setCompanyId(RandomTestUtil.nextLong());

		newCommerceOrderNote.setUserId(RandomTestUtil.nextLong());

		newCommerceOrderNote.setUserName(RandomTestUtil.randomString());

		newCommerceOrderNote.setCreateDate(RandomTestUtil.nextDate());

		newCommerceOrderNote.setModifiedDate(RandomTestUtil.nextDate());

		newCommerceOrderNote.setCommerceOrderId(RandomTestUtil.nextLong());

		newCommerceOrderNote.setContent(RandomTestUtil.randomString());

		newCommerceOrderNote.setRestricted(RandomTestUtil.randomBoolean());

		_commerceOrderNotes.add(_persistence.update(newCommerceOrderNote));

		CommerceOrderNote existingCommerceOrderNote =
			_persistence.findByPrimaryKey(newCommerceOrderNote.getPrimaryKey());

		Assert.assertEquals(
			existingCommerceOrderNote.getExternalReferenceCode(),
			newCommerceOrderNote.getExternalReferenceCode());
		Assert.assertEquals(
			existingCommerceOrderNote.getCommerceOrderNoteId(),
			newCommerceOrderNote.getCommerceOrderNoteId());
		Assert.assertEquals(
			existingCommerceOrderNote.getGroupId(),
			newCommerceOrderNote.getGroupId());
		Assert.assertEquals(
			existingCommerceOrderNote.getCompanyId(),
			newCommerceOrderNote.getCompanyId());
		Assert.assertEquals(
			existingCommerceOrderNote.getUserId(),
			newCommerceOrderNote.getUserId());
		Assert.assertEquals(
			existingCommerceOrderNote.getUserName(),
			newCommerceOrderNote.getUserName());
		Assert.assertEquals(
			Time.getShortTimestamp(existingCommerceOrderNote.getCreateDate()),
			Time.getShortTimestamp(newCommerceOrderNote.getCreateDate()));
		Assert.assertEquals(
			Time.getShortTimestamp(existingCommerceOrderNote.getModifiedDate()),
			Time.getShortTimestamp(newCommerceOrderNote.getModifiedDate()));
		Assert.assertEquals(
			existingCommerceOrderNote.getCommerceOrderId(),
			newCommerceOrderNote.getCommerceOrderId());
		Assert.assertEquals(
			existingCommerceOrderNote.getContent(),
			newCommerceOrderNote.getContent());
		Assert.assertEquals(
			existingCommerceOrderNote.isRestricted(),
			newCommerceOrderNote.isRestricted());
	}

	@Test
	public void testCountByCommerceOrderId() throws Exception {
		_persistence.countByCommerceOrderId(RandomTestUtil.nextLong());

		_persistence.countByCommerceOrderId(0L);
	}

	@Test
	public void testCountByC_R() throws Exception {
		_persistence.countByC_R(
			RandomTestUtil.nextLong(), RandomTestUtil.randomBoolean());

		_persistence.countByC_R(0L, RandomTestUtil.randomBoolean());
	}

	@Test
	public void testCountByC_ERC() throws Exception {
		_persistence.countByC_ERC(RandomTestUtil.nextLong(), "");

		_persistence.countByC_ERC(0L, "null");

		_persistence.countByC_ERC(0L, (String)null);
	}

	@Test
	public void testFindByPrimaryKeyExisting() throws Exception {
		CommerceOrderNote newCommerceOrderNote = addCommerceOrderNote();

		CommerceOrderNote existingCommerceOrderNote =
			_persistence.findByPrimaryKey(newCommerceOrderNote.getPrimaryKey());

		Assert.assertEquals(existingCommerceOrderNote, newCommerceOrderNote);
	}

	@Test(expected = NoSuchOrderNoteException.class)
	public void testFindByPrimaryKeyMissing() throws Exception {
		long pk = RandomTestUtil.nextLong();

		_persistence.findByPrimaryKey(pk);
	}

	@Test
	public void testFindAll() throws Exception {
		_persistence.findAll(
			QueryUtil.ALL_POS, QueryUtil.ALL_POS, getOrderByComparator());
	}

	protected OrderByComparator<CommerceOrderNote> getOrderByComparator() {
		return OrderByComparatorFactoryUtil.create(
			"CommerceOrderNote", "externalReferenceCode", true,
			"commerceOrderNoteId", true, "groupId", true, "companyId", true,
			"userId", true, "userName", true, "createDate", true,
			"modifiedDate", true, "commerceOrderId", true, "content", true,
			"restricted", true);
	}

	@Test
	public void testFetchByPrimaryKeyExisting() throws Exception {
		CommerceOrderNote newCommerceOrderNote = addCommerceOrderNote();

		CommerceOrderNote existingCommerceOrderNote =
			_persistence.fetchByPrimaryKey(
				newCommerceOrderNote.getPrimaryKey());

		Assert.assertEquals(existingCommerceOrderNote, newCommerceOrderNote);
	}

	@Test
	public void testFetchByPrimaryKeyMissing() throws Exception {
		long pk = RandomTestUtil.nextLong();

		CommerceOrderNote missingCommerceOrderNote =
			_persistence.fetchByPrimaryKey(pk);

		Assert.assertNull(missingCommerceOrderNote);
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereAllPrimaryKeysExist()
		throws Exception {

		CommerceOrderNote newCommerceOrderNote1 = addCommerceOrderNote();
		CommerceOrderNote newCommerceOrderNote2 = addCommerceOrderNote();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newCommerceOrderNote1.getPrimaryKey());
		primaryKeys.add(newCommerceOrderNote2.getPrimaryKey());

		Map<Serializable, CommerceOrderNote> commerceOrderNotes =
			_persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(2, commerceOrderNotes.size());
		Assert.assertEquals(
			newCommerceOrderNote1,
			commerceOrderNotes.get(newCommerceOrderNote1.getPrimaryKey()));
		Assert.assertEquals(
			newCommerceOrderNote2,
			commerceOrderNotes.get(newCommerceOrderNote2.getPrimaryKey()));
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereNoPrimaryKeysExist()
		throws Exception {

		long pk1 = RandomTestUtil.nextLong();

		long pk2 = RandomTestUtil.nextLong();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(pk1);
		primaryKeys.add(pk2);

		Map<Serializable, CommerceOrderNote> commerceOrderNotes =
			_persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertTrue(commerceOrderNotes.isEmpty());
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereSomePrimaryKeysExist()
		throws Exception {

		CommerceOrderNote newCommerceOrderNote = addCommerceOrderNote();

		long pk = RandomTestUtil.nextLong();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newCommerceOrderNote.getPrimaryKey());
		primaryKeys.add(pk);

		Map<Serializable, CommerceOrderNote> commerceOrderNotes =
			_persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(1, commerceOrderNotes.size());
		Assert.assertEquals(
			newCommerceOrderNote,
			commerceOrderNotes.get(newCommerceOrderNote.getPrimaryKey()));
	}

	@Test
	public void testFetchByPrimaryKeysWithNoPrimaryKeys() throws Exception {
		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		Map<Serializable, CommerceOrderNote> commerceOrderNotes =
			_persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertTrue(commerceOrderNotes.isEmpty());
	}

	@Test
	public void testFetchByPrimaryKeysWithOnePrimaryKey() throws Exception {
		CommerceOrderNote newCommerceOrderNote = addCommerceOrderNote();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newCommerceOrderNote.getPrimaryKey());

		Map<Serializable, CommerceOrderNote> commerceOrderNotes =
			_persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(1, commerceOrderNotes.size());
		Assert.assertEquals(
			newCommerceOrderNote,
			commerceOrderNotes.get(newCommerceOrderNote.getPrimaryKey()));
	}

	@Test
	public void testActionableDynamicQuery() throws Exception {
		final IntegerWrapper count = new IntegerWrapper();

		ActionableDynamicQuery actionableDynamicQuery =
			CommerceOrderNoteLocalServiceUtil.getActionableDynamicQuery();

		actionableDynamicQuery.setPerformActionMethod(
			new ActionableDynamicQuery.PerformActionMethod
				<CommerceOrderNote>() {

				@Override
				public void performAction(CommerceOrderNote commerceOrderNote) {
					Assert.assertNotNull(commerceOrderNote);

					count.increment();
				}

			});

		actionableDynamicQuery.performActions();

		Assert.assertEquals(count.getValue(), _persistence.countAll());
	}

	@Test
	public void testDynamicQueryByPrimaryKeyExisting() throws Exception {
		CommerceOrderNote newCommerceOrderNote = addCommerceOrderNote();

		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(
			CommerceOrderNote.class, _dynamicQueryClassLoader);

		dynamicQuery.add(
			RestrictionsFactoryUtil.eq(
				"commerceOrderNoteId",
				newCommerceOrderNote.getCommerceOrderNoteId()));

		List<CommerceOrderNote> result = _persistence.findWithDynamicQuery(
			dynamicQuery);

		Assert.assertEquals(1, result.size());

		CommerceOrderNote existingCommerceOrderNote = result.get(0);

		Assert.assertEquals(existingCommerceOrderNote, newCommerceOrderNote);
	}

	@Test
	public void testDynamicQueryByPrimaryKeyMissing() throws Exception {
		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(
			CommerceOrderNote.class, _dynamicQueryClassLoader);

		dynamicQuery.add(
			RestrictionsFactoryUtil.eq(
				"commerceOrderNoteId", RandomTestUtil.nextLong()));

		List<CommerceOrderNote> result = _persistence.findWithDynamicQuery(
			dynamicQuery);

		Assert.assertEquals(0, result.size());
	}

	@Test
	public void testDynamicQueryByProjectionExisting() throws Exception {
		CommerceOrderNote newCommerceOrderNote = addCommerceOrderNote();

		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(
			CommerceOrderNote.class, _dynamicQueryClassLoader);

		dynamicQuery.setProjection(
			ProjectionFactoryUtil.property("commerceOrderNoteId"));

		Object newCommerceOrderNoteId =
			newCommerceOrderNote.getCommerceOrderNoteId();

		dynamicQuery.add(
			RestrictionsFactoryUtil.in(
				"commerceOrderNoteId", new Object[] {newCommerceOrderNoteId}));

		List<Object> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(1, result.size());

		Object existingCommerceOrderNoteId = result.get(0);

		Assert.assertEquals(
			existingCommerceOrderNoteId, newCommerceOrderNoteId);
	}

	@Test
	public void testDynamicQueryByProjectionMissing() throws Exception {
		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(
			CommerceOrderNote.class, _dynamicQueryClassLoader);

		dynamicQuery.setProjection(
			ProjectionFactoryUtil.property("commerceOrderNoteId"));

		dynamicQuery.add(
			RestrictionsFactoryUtil.in(
				"commerceOrderNoteId",
				new Object[] {RandomTestUtil.nextLong()}));

		List<Object> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(0, result.size());
	}

	@Test
	public void testResetOriginalValues() throws Exception {
		CommerceOrderNote newCommerceOrderNote = addCommerceOrderNote();

		_persistence.clearCache();

		CommerceOrderNote existingCommerceOrderNote =
			_persistence.findByPrimaryKey(newCommerceOrderNote.getPrimaryKey());

		Assert.assertEquals(
			Long.valueOf(existingCommerceOrderNote.getCompanyId()),
			ReflectionTestUtil.<Long>invoke(
				existingCommerceOrderNote, "getOriginalCompanyId",
				new Class<?>[0]));
		Assert.assertTrue(
			Objects.equals(
				existingCommerceOrderNote.getExternalReferenceCode(),
				ReflectionTestUtil.invoke(
					existingCommerceOrderNote,
					"getOriginalExternalReferenceCode", new Class<?>[0])));
	}

	protected CommerceOrderNote addCommerceOrderNote() throws Exception {
		long pk = RandomTestUtil.nextLong();

		CommerceOrderNote commerceOrderNote = _persistence.create(pk);

		commerceOrderNote.setExternalReferenceCode(
			RandomTestUtil.randomString());

		commerceOrderNote.setGroupId(RandomTestUtil.nextLong());

		commerceOrderNote.setCompanyId(RandomTestUtil.nextLong());

		commerceOrderNote.setUserId(RandomTestUtil.nextLong());

		commerceOrderNote.setUserName(RandomTestUtil.randomString());

		commerceOrderNote.setCreateDate(RandomTestUtil.nextDate());

		commerceOrderNote.setModifiedDate(RandomTestUtil.nextDate());

		commerceOrderNote.setCommerceOrderId(RandomTestUtil.nextLong());

		commerceOrderNote.setContent(RandomTestUtil.randomString());

		commerceOrderNote.setRestricted(RandomTestUtil.randomBoolean());

		_commerceOrderNotes.add(_persistence.update(commerceOrderNote));

		return commerceOrderNote;
	}

	private List<CommerceOrderNote> _commerceOrderNotes =
		new ArrayList<CommerceOrderNote>();
	private CommerceOrderNotePersistence _persistence;
	private ClassLoader _dynamicQueryClassLoader;

}