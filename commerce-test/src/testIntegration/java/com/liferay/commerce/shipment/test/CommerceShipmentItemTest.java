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

package com.liferay.commerce.shipment.test;

import com.liferay.arquillian.extension.junit.bridge.junit.Arquillian;
import com.liferay.commerce.constants.CommerceShipmentConstants;
import com.liferay.commerce.context.CommerceContext;
import com.liferay.commerce.currency.model.CommerceCurrency;
import com.liferay.commerce.currency.test.util.CommerceCurrencyTestUtil;
import com.liferay.commerce.exception.CommerceShipmentStatusException;
import com.liferay.commerce.model.CommerceOrder;
import com.liferay.commerce.model.CommerceShipment;
import com.liferay.commerce.model.CommerceShipmentItem;
import com.liferay.commerce.product.model.CommerceChannel;
import com.liferay.commerce.product.model.CommerceChannelConstants;
import com.liferay.commerce.product.service.CommerceChannelLocalServiceUtil;
import com.liferay.commerce.product.test.util.CPTestUtil;
import com.liferay.commerce.service.CommerceShipmentItemLocalService;
import com.liferay.commerce.service.CommerceShipmentItemLocalServiceUtil;
import com.liferay.commerce.service.CommerceShipmentLocalServiceUtil;
import com.liferay.commerce.shipment.test.util.CommerceShipmentTestUtil;
import com.liferay.commerce.test.util.CommerceTestUtil;
import com.liferay.commerce.test.util.TestCommerceContext;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.model.Group;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.test.rule.AggregateTestRule;
import com.liferay.portal.kernel.test.rule.DeleteAfterTestRun;
import com.liferay.portal.kernel.test.util.GroupTestUtil;
import com.liferay.portal.kernel.test.util.ServiceContextTestUtil;
import com.liferay.portal.kernel.test.util.UserTestUtil;
import com.liferay.portal.test.rule.Inject;
import com.liferay.portal.test.rule.LiferayIntegrationTestRule;
import com.liferay.portal.test.rule.PermissionCheckerTestRule;

import java.util.List;

import org.frutilla.FrutillaRule;

import org.junit.Assert;
import org.junit.Before;
import org.junit.ClassRule;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 * @author Alec Sloan
 */
@RunWith(Arquillian.class)
public class CommerceShipmentItemTest {

	@ClassRule
	@Rule
	public static final AggregateTestRule aggregateTestRule =
		new AggregateTestRule(
			new LiferayIntegrationTestRule(),
			PermissionCheckerTestRule.INSTANCE);

	@Before
	public void setUp() throws Exception {
		_group = GroupTestUtil.addGroup();
		_user = UserTestUtil.addUser();

		CommerceCurrency commerceCurrency =
			CommerceCurrencyTestUtil.addCommerceCurrency(_group.getGroupId());

		ServiceContext serviceContext =
			ServiceContextTestUtil.getServiceContext(_group.getGroupId());

		CommerceChannel commerceChannel =
			CommerceChannelLocalServiceUtil.addCommerceChannel(
				_group.getGroupId(), "Test Channel",
				CommerceChannelConstants.CHANNEL_TYPE_SITE, null,
				commerceCurrency.getCode(), null, serviceContext);

		_commerceOrder = CommerceTestUtil.addB2CCommerceOrder(
			_group.getGroupId(), _user.getUserId(),
			commerceCurrency.getCommerceCurrencyId());

		_commerceShipment = CommerceShipmentTestUtil.createEmptyOrderShipment(
			_group.getGroupId(), _commerceOrder.getCommerceOrderId());

		_commerceContext = new TestCommerceContext(
			commerceCurrency, commerceChannel, _user, _group,
			_commerceOrder.getCommerceAccount(), _commerceOrder);

		_commerceShipmentItem =
			CommerceShipmentTestUtil.addCommerceShipmentItem(
				_commerceContext, CPTestUtil.addCPInstance(_group.getGroupId()),
				_group.getGroupId(), _user.getUserId(),
				_commerceOrder.getCommerceOrderId(),
				_commerceShipment.getCommerceShipmentId());

		CommerceChannelLocalServiceUtil.deleteCommerceChannel(commerceChannel);
	}

	@Test
	public void testAddCommerceShipmentItem() throws Exception {
		frutillaRule.scenario(
			"Create a Shipment Item for an Order"
		).given(
			"A Group"
		).and(
			"An Order Item"
		).and(
			"A CPInstance"
		).when(
			"A Shipment Item is added to a shipment"
		).then(
			"That shipment should contain the shipment Item"
		);

		List<CommerceShipmentItem> commerceShipmentItems =
			_commerceShipmentItemLocalService.getCommerceShipmentItems(
				_commerceShipment.getCommerceShipmentId(), QueryUtil.ALL_POS,
				QueryUtil.ALL_POS, null);

		Assert.assertEquals(
			commerceShipmentItems.toString(), 1, commerceShipmentItems.size());

		CommerceShipmentItem actualCommerceShipmentItem =
			commerceShipmentItems.get(0);

		Assert.assertEquals(_commerceShipmentItem, actualCommerceShipmentItem);

		_resetCommerceShipment();
	}

	@Test(expected = CommerceShipmentStatusException.class)
	public void testAddCommerceShipmentItemOnShippedShipment()
		throws Exception {

		frutillaRule.scenario(
			"Try to add a new Shipment Item after a shipment is marked as " +
				"shipped"
		).given(
			"A Group"
		).and(
			"A ShipmentItem"
		).and(
			"A CPInstance"
		).when(
			"A Shipment is marked as shipped"
		).then(
			"Shipment Items should not be able to be added"
		);

		_commerceShipment.setStatus(
			CommerceShipmentConstants.SHIPMENT_STATUS_SHIPPED);

		_commerceShipment =
			CommerceShipmentLocalServiceUtil.updateCommerceShipment(
				_commerceShipment);

		CommerceShipmentTestUtil.addCommerceShipmentItem(
			_commerceContext, CPTestUtil.addCPInstance(_group.getGroupId()),
			_group.getGroupId(), _user.getUserId(),
			_commerceOrder.getCommerceOrderId(),
			_commerceShipment.getCommerceShipmentId());

		Assert.assertEquals(
			_commerceShipment.getStatus(),
			CommerceShipmentConstants.SHIPMENT_STATUS_SHIPPED);

		_resetCommerceShipment();
	}

	@Test
	public void testDeleteCommerceShipmentItemOnShippedShipment()
		throws Exception {

		frutillaRule.scenario(
			"Try to delete a Shipment Item after a shipment is marked as " +
				"shipped"
		).given(
			"A Group"
		).and(
			"A ShipmentItem"
		).and(
			"A CPInstance"
		).when(
			"A Shipment is marked as shipped"
		).then(
			"Shipment Items should not be able to be added"
		);

		_commerceShipment.setStatus(
			CommerceShipmentConstants.SHIPMENT_STATUS_SHIPPED);

		_commerceShipment =
			CommerceShipmentLocalServiceUtil.updateCommerceShipment(
				_commerceShipment);

		CommerceShipmentItemLocalServiceUtil.deleteCommerceShipmentItem(
			_commerceShipmentItem);

		Assert.assertEquals(
			_commerceShipment.getStatus(),
			CommerceShipmentConstants.SHIPMENT_STATUS_SHIPPED);

		CommerceShipmentItem actualCommerceShipmentItem =
			_commerceShipmentItemLocalService.fetchCommerceShipmentItem(
				_commerceShipmentItem.getCommerceShipmentItemId());

		Assert.assertEquals(_commerceShipmentItem, actualCommerceShipmentItem);

		_resetCommerceShipment();
	}

	@Test(expected = CommerceShipmentStatusException.class)
	public void testUpdateCommerceShipmentItemOnShippedShipment()
		throws Exception {

		frutillaRule.scenario(
			"Try to increase a Shipment Item's quantity after a shipment is " +
				"marked as shipped"
		).given(
			"A Group"
		).and(
			"A ShipmentItem"
		).and(
			"A CPInstance"
		).when(
			"A Shipment is marked as shipped"
		).then(
			"Shipment Items should not be able to be added"
		);

		_commerceShipment.setStatus(
			CommerceShipmentConstants.SHIPMENT_STATUS_SHIPPED);

		_commerceShipment =
			CommerceShipmentLocalServiceUtil.updateCommerceShipment(
				_commerceShipment);

		CommerceShipmentItem newCommerceShipmentItem =
			CommerceShipmentItemLocalServiceUtil.updateCommerceShipmentItem(
				_commerceShipmentItem.getCommerceShipmentItemId(), 2);

		Assert.assertEquals(
			_commerceShipment.getStatus(),
			CommerceShipmentConstants.SHIPMENT_STATUS_SHIPPED);
		Assert.assertEquals(
			_commerceShipmentItem.getQuantity(),
			newCommerceShipmentItem.getQuantity());

		_resetCommerceShipment();
	}

	@Rule
	public FrutillaRule frutillaRule = new FrutillaRule();

	private void _resetCommerceShipment() {
		_commerceShipment.setStatus(
			CommerceShipmentConstants.SHIPMENT_STATUS_PROCESSING);

		_commerceShipment =
			CommerceShipmentLocalServiceUtil.updateCommerceShipment(
				_commerceShipment);

		_commerceShipmentItemLocalService.deleteCommerceShipmentItems(
			_commerceShipment.getCommerceShipmentId());

		_commerceShipmentItemLocalService.addCommerceShipmentItem(
			_commerceShipmentItem);
	}

	private CommerceContext _commerceContext;

	@DeleteAfterTestRun
	private CommerceOrder _commerceOrder;

	@DeleteAfterTestRun
	private CommerceShipment _commerceShipment;

	@DeleteAfterTestRun
	private CommerceShipmentItem _commerceShipmentItem;

	@Inject
	private CommerceShipmentItemLocalService _commerceShipmentItemLocalService;

	@DeleteAfterTestRun
	private Group _group;

	@DeleteAfterTestRun
	private User _user;

}