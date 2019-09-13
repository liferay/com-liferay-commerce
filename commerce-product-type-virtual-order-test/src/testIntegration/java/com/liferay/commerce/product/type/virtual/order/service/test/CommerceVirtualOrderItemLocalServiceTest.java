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

package com.liferay.commerce.product.type.virtual.order.service.test;

import com.liferay.arquillian.extension.junit.bridge.junit.Arquillian;
import com.liferay.commerce.constants.CommerceOrderConstants;
import com.liferay.commerce.model.CommerceOrder;
import com.liferay.commerce.model.CommerceOrderItem;
import com.liferay.commerce.product.model.CPDefinition;
import com.liferay.commerce.product.model.CPInstance;
import com.liferay.commerce.product.service.CPInstanceLocalService;
import com.liferay.commerce.product.test.util.CPTestUtil;
import com.liferay.commerce.product.type.virtual.constants.VirtualCPTypeConstants;
import com.liferay.commerce.product.type.virtual.order.model.CommerceVirtualOrderItem;
import com.liferay.commerce.product.type.virtual.order.service.CommerceVirtualOrderItemLocalService;
import com.liferay.commerce.product.type.virtual.order.util.CommerceVirtualOrderItemChecker;
import com.liferay.commerce.product.type.virtual.test.util.VirtualCPTypeTestUtil;
import com.liferay.commerce.service.CommerceOrderLocalService;
import com.liferay.commerce.subscription.CommerceSubscriptionEntryHelper;
import com.liferay.commerce.test.util.CommerceTestUtil;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.model.Group;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.test.rule.AggregateTestRule;
import com.liferay.portal.kernel.test.rule.DeleteAfterTestRun;
import com.liferay.portal.kernel.test.util.GroupTestUtil;
import com.liferay.portal.kernel.test.util.UserTestUtil;
import com.liferay.portal.test.rule.Inject;
import com.liferay.portal.test.rule.LiferayIntegrationTestRule;
import com.liferay.portal.test.rule.PermissionCheckerTestRule;

import java.util.List;

import org.frutilla.FrutillaRule;

import org.junit.Assert;
import org.junit.Before;
import org.junit.ClassRule;
import org.junit.Ignore;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 * @author Alessio Antonio Rendina
 */
@Ignore
@RunWith(Arquillian.class)
public class CommerceVirtualOrderItemLocalServiceTest {

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
	}

	@Test
	public void testAddCommerceVirtualOrderItem() throws Exception {
		frutillaRule.scenario(
			"Add a virtual order item"
		).given(
			"An order item"
		).when(
			"An order is paid"
		).then(
			"I should be able to see the created virtual order item"
		);

		CPDefinition cpDefinition = CPTestUtil.addCPDefinition(
			_group.getGroupId(), VirtualCPTypeConstants.NAME, true, true);

		VirtualCPTypeTestUtil.addCPDefinitionVirtualSetting(
			_group.getGroupId(), cpDefinition.getModelClassName(),
			cpDefinition.getCPDefinitionId(), 0L,
			CommerceOrderConstants.ORDER_STATUS_TO_FULFILL, 0L, 0L, 0L);

		CommerceTestUtil.addBackOrderCPDefinitionInventory(cpDefinition);

		CommerceOrder commerceOrder = CommerceTestUtil.addB2CCommerceOrder(
			_group.getGroupId(), _user.getUserId(), 0);

		for (CPInstance cpInstance : cpDefinition.getCPInstances()) {
			CommerceTestUtil.addCommerceOrderItem(
				commerceOrder.getCommerceOrderId(),
				cpInstance.getCPInstanceId(), 1);
		}

		commerceOrder = _setCommerceOrderStatuses(
			commerceOrder, CommerceOrderConstants.PAYMENT_STATUS_PAID,
			CommerceOrderConstants.ORDER_STATUS_TO_FULFILL);

		_commerceVirtualOrderItemChecker.checkCommerceVirtualOrderItems(
			commerceOrder.getCommerceOrderId());

		List<CommerceOrderItem> commerceOrderItems =
			commerceOrder.getCommerceOrderItems();

		List<CommerceVirtualOrderItem> userCommerceVirtualOrderItems =
			_commerceVirtualOrderItemLocalService.getCommerceVirtualOrderItems(
				_group.getGroupId(), commerceOrder.getCommerceAccountId(),
				QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);

		Assert.assertEquals(
			userCommerceVirtualOrderItems.toString(), commerceOrderItems.size(),
			userCommerceVirtualOrderItems.size());

		for (CommerceVirtualOrderItem commerceVirtualOrderItem :
				userCommerceVirtualOrderItems) {

			Assert.assertEquals(true, commerceVirtualOrderItem.isActive());
			Assert.assertEquals(
				CommerceOrderConstants.ORDER_STATUS_TO_FULFILL,
				commerceVirtualOrderItem.getActivationStatus());
			Assert.assertEquals(0L, commerceVirtualOrderItem.getDuration());
			Assert.assertEquals(null, commerceVirtualOrderItem.getEndDate());
		}
	}

	@Test
	public void testAddCommerceVirtualOrderItemWithSubscription()
		throws Exception {

		frutillaRule.scenario(
			"Add a virtual order item"
		).given(
			"A subscription order item"
		).when(
			"An order is paid"
		).then(
			"I should be able to see the created virtual order item"
		);

		CPDefinition cpDefinition = CPTestUtil.addCPDefinition(
			_group.getGroupId(), VirtualCPTypeConstants.NAME, true, true);

		VirtualCPTypeTestUtil.addCPDefinitionVirtualSetting(
			_group.getGroupId(), cpDefinition.getModelClassName(),
			cpDefinition.getCPDefinitionId(), 0L,
			CommerceOrderConstants.ORDER_STATUS_TO_FULFILL, 0L, 0L, 0L);

		CommerceTestUtil.addBackOrderCPDefinitionInventory(cpDefinition);

		CommerceOrder commerceOrder = CommerceTestUtil.addB2CCommerceOrder(
			_group.getGroupId(), _user.getUserId(), 0);

		int subscriptionLength = 1;

		for (CPInstance cpInstance : cpDefinition.getCPInstances()) {
			cpInstance = _setCPInstanceSubscriptionInfo(
				cpInstance, subscriptionLength, "daily");

			CommerceTestUtil.addCommerceOrderItem(
				commerceOrder.getCommerceOrderId(),
				cpInstance.getCPInstanceId(), 1);
		}

		commerceOrder = _setCommerceOrderStatuses(
			commerceOrder, CommerceOrderConstants.PAYMENT_STATUS_PAID,
			CommerceOrderConstants.ORDER_STATUS_TO_FULFILL);

		_commerceSubscriptionEntryHelper.checkCommerceSubscriptions(
			commerceOrder);

		_commerceVirtualOrderItemChecker.checkCommerceVirtualOrderItems(
			commerceOrder.getCommerceOrderId());

		List<CommerceOrderItem> commerceOrderItems =
			commerceOrder.getCommerceOrderItems();

		List<CommerceVirtualOrderItem> userCommerceVirtualOrderItems =
			_commerceVirtualOrderItemLocalService.getCommerceVirtualOrderItems(
				_group.getGroupId(), commerceOrder.getCommerceAccountId(),
				QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);

		Assert.assertEquals(
			userCommerceVirtualOrderItems.toString(), commerceOrderItems.size(),
			userCommerceVirtualOrderItems.size());

		for (CommerceVirtualOrderItem commerceVirtualOrderItem :
				userCommerceVirtualOrderItems) {

			Assert.assertEquals(true, commerceVirtualOrderItem.isActive());
			Assert.assertEquals(
				CommerceOrderConstants.ORDER_STATUS_TO_FULFILL,
				commerceVirtualOrderItem.getActivationStatus());
			Assert.assertEquals(0L, commerceVirtualOrderItem.getDuration());

			CommerceOrderItem commerceOrderItem =
				commerceVirtualOrderItem.getCommerceOrderItem();

			Assert.assertEquals(true, commerceOrderItem.isSubscription());
		}
	}

	@Rule
	public FrutillaRule frutillaRule = new FrutillaRule();

	private CommerceOrder _setCommerceOrderStatuses(
		CommerceOrder commerceOrder, int paymentStatus, int orderStatus) {

		commerceOrder.setPaymentStatus(paymentStatus);
		commerceOrder.setOrderStatus(orderStatus);

		return _commerceOrderLocalService.updateCommerceOrder(commerceOrder);
	}

	private CPInstance _setCPInstanceSubscriptionInfo(
		CPInstance cpInstance, int subscriptionLength,
		String subscriptionType) {

		cpInstance.setOverrideSubscriptionInfo(true);
		cpInstance.setSubscriptionEnabled(true);
		cpInstance.setSubscriptionLength(subscriptionLength);
		cpInstance.setSubscriptionType(subscriptionType);
		cpInstance.setMaxSubscriptionCycles(1);

		return _cpInstanceLocalService.updateCPInstance(cpInstance);
	}

	@Inject
	private CommerceOrderLocalService _commerceOrderLocalService;

	@Inject
	private CommerceSubscriptionEntryHelper _commerceSubscriptionEntryHelper;

	@Inject
	private CommerceVirtualOrderItemChecker _commerceVirtualOrderItemChecker;

	@Inject
	private CommerceVirtualOrderItemLocalService
		_commerceVirtualOrderItemLocalService;

	@Inject
	private CPInstanceLocalService _cpInstanceLocalService;

	@DeleteAfterTestRun
	private Group _group;

	@DeleteAfterTestRun
	private User _user;

}