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

import com.liferay.commerce.product.exception.NoSuchChannelException;
import com.liferay.commerce.product.model.CommerceChannel;
import com.liferay.commerce.product.service.CommerceChannelLocalServiceUtil;
import com.liferay.commerce.product.service.persistence.CommerceChannelPersistence;
import com.liferay.commerce.product.service.persistence.CommerceChannelUtil;

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
import java.util.Set;

/**
 * @generated
 */
@RunWith(Arquillian.class)
public class CommerceChannelPersistenceTest {
	@ClassRule
	@Rule
	public static final AggregateTestRule aggregateTestRule = new AggregateTestRule(new LiferayIntegrationTestRule(),
			PersistenceTestRule.INSTANCE,
			new TransactionalTestRule(Propagation.REQUIRED,
				"com.liferay.commerce.product.service"));

	@Before
	public void setUp() {
		_persistence = CommerceChannelUtil.getPersistence();

		Class<?> clazz = _persistence.getClass();

		_dynamicQueryClassLoader = clazz.getClassLoader();
	}

	@After
	public void tearDown() throws Exception {
		Iterator<CommerceChannel> iterator = _commerceChannels.iterator();

		while (iterator.hasNext()) {
			_persistence.remove(iterator.next());

			iterator.remove();
		}
	}

	@Test
	public void testCreate() throws Exception {
		long pk = RandomTestUtil.nextLong();

		CommerceChannel commerceChannel = _persistence.create(pk);

		Assert.assertNotNull(commerceChannel);

		Assert.assertEquals(commerceChannel.getPrimaryKey(), pk);
	}

	@Test
	public void testRemove() throws Exception {
		CommerceChannel newCommerceChannel = addCommerceChannel();

		_persistence.remove(newCommerceChannel);

		CommerceChannel existingCommerceChannel = _persistence.fetchByPrimaryKey(newCommerceChannel.getPrimaryKey());

		Assert.assertNull(existingCommerceChannel);
	}

	@Test
	public void testUpdateNew() throws Exception {
		addCommerceChannel();
	}

	@Test
	public void testUpdateExisting() throws Exception {
		long pk = RandomTestUtil.nextLong();

		CommerceChannel newCommerceChannel = _persistence.create(pk);

		newCommerceChannel.setCompanyId(RandomTestUtil.nextLong());

		newCommerceChannel.setUserId(RandomTestUtil.nextLong());

		newCommerceChannel.setUserName(RandomTestUtil.randomString());

		newCommerceChannel.setCreateDate(RandomTestUtil.nextDate());

		newCommerceChannel.setModifiedDate(RandomTestUtil.nextDate());

		newCommerceChannel.setName(RandomTestUtil.randomString());

		newCommerceChannel.setFilterType(RandomTestUtil.randomString());

		newCommerceChannel.setType(RandomTestUtil.randomString());

		newCommerceChannel.setTypeSettings(RandomTestUtil.randomString());

		_commerceChannels.add(_persistence.update(newCommerceChannel));

		CommerceChannel existingCommerceChannel = _persistence.findByPrimaryKey(newCommerceChannel.getPrimaryKey());

		Assert.assertEquals(existingCommerceChannel.getCommerceChannelId(),
			newCommerceChannel.getCommerceChannelId());
		Assert.assertEquals(existingCommerceChannel.getCompanyId(),
			newCommerceChannel.getCompanyId());
		Assert.assertEquals(existingCommerceChannel.getUserId(),
			newCommerceChannel.getUserId());
		Assert.assertEquals(existingCommerceChannel.getUserName(),
			newCommerceChannel.getUserName());
		Assert.assertEquals(Time.getShortTimestamp(
				existingCommerceChannel.getCreateDate()),
			Time.getShortTimestamp(newCommerceChannel.getCreateDate()));
		Assert.assertEquals(Time.getShortTimestamp(
				existingCommerceChannel.getModifiedDate()),
			Time.getShortTimestamp(newCommerceChannel.getModifiedDate()));
		Assert.assertEquals(existingCommerceChannel.getName(),
			newCommerceChannel.getName());
		Assert.assertEquals(existingCommerceChannel.getFilterType(),
			newCommerceChannel.getFilterType());
		Assert.assertEquals(existingCommerceChannel.getType(),
			newCommerceChannel.getType());
		Assert.assertEquals(existingCommerceChannel.getTypeSettings(),
			newCommerceChannel.getTypeSettings());
	}

	@Test
	public void testFindByPrimaryKeyExisting() throws Exception {
		CommerceChannel newCommerceChannel = addCommerceChannel();

		CommerceChannel existingCommerceChannel = _persistence.findByPrimaryKey(newCommerceChannel.getPrimaryKey());

		Assert.assertEquals(existingCommerceChannel, newCommerceChannel);
	}

	@Test(expected = NoSuchChannelException.class)
	public void testFindByPrimaryKeyMissing() throws Exception {
		long pk = RandomTestUtil.nextLong();

		_persistence.findByPrimaryKey(pk);
	}

	@Test
	public void testFindAll() throws Exception {
		_persistence.findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS,
			getOrderByComparator());
	}

	protected OrderByComparator<CommerceChannel> getOrderByComparator() {
		return OrderByComparatorFactoryUtil.create("CommerceChannel",
			"commerceChannelId", true, "companyId", true, "userId", true,
			"userName", true, "createDate", true, "modifiedDate", true, "name",
			true, "filterType", true, "type", true, "typeSettings", true);
	}

	@Test
	public void testFetchByPrimaryKeyExisting() throws Exception {
		CommerceChannel newCommerceChannel = addCommerceChannel();

		CommerceChannel existingCommerceChannel = _persistence.fetchByPrimaryKey(newCommerceChannel.getPrimaryKey());

		Assert.assertEquals(existingCommerceChannel, newCommerceChannel);
	}

	@Test
	public void testFetchByPrimaryKeyMissing() throws Exception {
		long pk = RandomTestUtil.nextLong();

		CommerceChannel missingCommerceChannel = _persistence.fetchByPrimaryKey(pk);

		Assert.assertNull(missingCommerceChannel);
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereAllPrimaryKeysExist()
		throws Exception {
		CommerceChannel newCommerceChannel1 = addCommerceChannel();
		CommerceChannel newCommerceChannel2 = addCommerceChannel();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newCommerceChannel1.getPrimaryKey());
		primaryKeys.add(newCommerceChannel2.getPrimaryKey());

		Map<Serializable, CommerceChannel> commerceChannels = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(2, commerceChannels.size());
		Assert.assertEquals(newCommerceChannel1,
			commerceChannels.get(newCommerceChannel1.getPrimaryKey()));
		Assert.assertEquals(newCommerceChannel2,
			commerceChannels.get(newCommerceChannel2.getPrimaryKey()));
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereNoPrimaryKeysExist()
		throws Exception {
		long pk1 = RandomTestUtil.nextLong();

		long pk2 = RandomTestUtil.nextLong();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(pk1);
		primaryKeys.add(pk2);

		Map<Serializable, CommerceChannel> commerceChannels = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertTrue(commerceChannels.isEmpty());
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereSomePrimaryKeysExist()
		throws Exception {
		CommerceChannel newCommerceChannel = addCommerceChannel();

		long pk = RandomTestUtil.nextLong();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newCommerceChannel.getPrimaryKey());
		primaryKeys.add(pk);

		Map<Serializable, CommerceChannel> commerceChannels = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(1, commerceChannels.size());
		Assert.assertEquals(newCommerceChannel,
			commerceChannels.get(newCommerceChannel.getPrimaryKey()));
	}

	@Test
	public void testFetchByPrimaryKeysWithNoPrimaryKeys()
		throws Exception {
		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		Map<Serializable, CommerceChannel> commerceChannels = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertTrue(commerceChannels.isEmpty());
	}

	@Test
	public void testFetchByPrimaryKeysWithOnePrimaryKey()
		throws Exception {
		CommerceChannel newCommerceChannel = addCommerceChannel();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newCommerceChannel.getPrimaryKey());

		Map<Serializable, CommerceChannel> commerceChannels = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(1, commerceChannels.size());
		Assert.assertEquals(newCommerceChannel,
			commerceChannels.get(newCommerceChannel.getPrimaryKey()));
	}

	@Test
	public void testActionableDynamicQuery() throws Exception {
		final IntegerWrapper count = new IntegerWrapper();

		ActionableDynamicQuery actionableDynamicQuery = CommerceChannelLocalServiceUtil.getActionableDynamicQuery();

		actionableDynamicQuery.setPerformActionMethod(new ActionableDynamicQuery.PerformActionMethod<CommerceChannel>() {
				@Override
				public void performAction(CommerceChannel commerceChannel) {
					Assert.assertNotNull(commerceChannel);

					count.increment();
				}
			});

		actionableDynamicQuery.performActions();

		Assert.assertEquals(count.getValue(), _persistence.countAll());
	}

	@Test
	public void testDynamicQueryByPrimaryKeyExisting()
		throws Exception {
		CommerceChannel newCommerceChannel = addCommerceChannel();

		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(CommerceChannel.class,
				_dynamicQueryClassLoader);

		dynamicQuery.add(RestrictionsFactoryUtil.eq("commerceChannelId",
				newCommerceChannel.getCommerceChannelId()));

		List<CommerceChannel> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(1, result.size());

		CommerceChannel existingCommerceChannel = result.get(0);

		Assert.assertEquals(existingCommerceChannel, newCommerceChannel);
	}

	@Test
	public void testDynamicQueryByPrimaryKeyMissing() throws Exception {
		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(CommerceChannel.class,
				_dynamicQueryClassLoader);

		dynamicQuery.add(RestrictionsFactoryUtil.eq("commerceChannelId",
				RandomTestUtil.nextLong()));

		List<CommerceChannel> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(0, result.size());
	}

	@Test
	public void testDynamicQueryByProjectionExisting()
		throws Exception {
		CommerceChannel newCommerceChannel = addCommerceChannel();

		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(CommerceChannel.class,
				_dynamicQueryClassLoader);

		dynamicQuery.setProjection(ProjectionFactoryUtil.property(
				"commerceChannelId"));

		Object newCommerceChannelId = newCommerceChannel.getCommerceChannelId();

		dynamicQuery.add(RestrictionsFactoryUtil.in("commerceChannelId",
				new Object[] { newCommerceChannelId }));

		List<Object> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(1, result.size());

		Object existingCommerceChannelId = result.get(0);

		Assert.assertEquals(existingCommerceChannelId, newCommerceChannelId);
	}

	@Test
	public void testDynamicQueryByProjectionMissing() throws Exception {
		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(CommerceChannel.class,
				_dynamicQueryClassLoader);

		dynamicQuery.setProjection(ProjectionFactoryUtil.property(
				"commerceChannelId"));

		dynamicQuery.add(RestrictionsFactoryUtil.in("commerceChannelId",
				new Object[] { RandomTestUtil.nextLong() }));

		List<Object> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(0, result.size());
	}

	protected CommerceChannel addCommerceChannel() throws Exception {
		long pk = RandomTestUtil.nextLong();

		CommerceChannel commerceChannel = _persistence.create(pk);

		commerceChannel.setCompanyId(RandomTestUtil.nextLong());

		commerceChannel.setUserId(RandomTestUtil.nextLong());

		commerceChannel.setUserName(RandomTestUtil.randomString());

		commerceChannel.setCreateDate(RandomTestUtil.nextDate());

		commerceChannel.setModifiedDate(RandomTestUtil.nextDate());

		commerceChannel.setName(RandomTestUtil.randomString());

		commerceChannel.setFilterType(RandomTestUtil.randomString());

		commerceChannel.setType(RandomTestUtil.randomString());

		commerceChannel.setTypeSettings(RandomTestUtil.randomString());

		_commerceChannels.add(_persistence.update(commerceChannel));

		return commerceChannel;
	}

	private List<CommerceChannel> _commerceChannels = new ArrayList<CommerceChannel>();
	private CommerceChannelPersistence _persistence;
	private ClassLoader _dynamicQueryClassLoader;
}