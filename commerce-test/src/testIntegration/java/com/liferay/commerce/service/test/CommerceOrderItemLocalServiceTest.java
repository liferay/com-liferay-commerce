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

package com.liferay.commerce.service.test;

import com.liferay.arquillian.extension.junit.bridge.junit.Arquillian;
import com.liferay.commerce.context.CommerceContext;
import com.liferay.commerce.currency.model.CommerceCurrency;
import com.liferay.commerce.currency.service.CommerceCurrencyLocalService;
import com.liferay.commerce.currency.test.util.CommerceCurrencyTestUtil;
import com.liferay.commerce.exception.CommerceOrderValidatorException;
import com.liferay.commerce.internal.test.util.CommerceTestUtil;
import com.liferay.commerce.model.CommerceOrder;
import com.liferay.commerce.model.CommerceOrderItem;
import com.liferay.commerce.model.CommerceWarehouse;
import com.liferay.commerce.product.model.CPDefinition;
import com.liferay.commerce.product.model.CPInstance;
import com.liferay.commerce.product.model.CPInstanceConstants;
import com.liferay.commerce.product.service.CPInstanceLocalService;
import com.liferay.commerce.product.test.util.CPTestUtil;
import com.liferay.commerce.service.CommerceOrderItemLocalService;
import com.liferay.commerce.service.CommerceOrderLocalService;
import com.liferay.commerce.test.util.TestCommerceContext;
import com.liferay.portal.kernel.model.Group;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.test.rule.AggregateTestRule;
import com.liferay.portal.kernel.test.rule.DeleteAfterTestRun;
import com.liferay.portal.kernel.test.util.GroupTestUtil;
import com.liferay.portal.kernel.test.util.ServiceContextTestUtil;
import com.liferay.portal.kernel.test.util.UserTestUtil;
import com.liferay.portal.kernel.workflow.WorkflowConstants;
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
 * @author Luca Pellizzon
 */
@RunWith(Arquillian.class)
public class CommerceOrderItemLocalServiceTest {

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
	public void testAddCommerceOrderItem() throws Exception {
		frutillaRule.scenario(
			"Add a SKU (product instance) to an order"
		).given(
			"A group"
		).and(
			"A user"
		).and(
			"A published SKU"
		).when(
			"There is availability for the SKU"
		).then(
			"I should be able to add the SKU to an order"
		);

		CPInstance cpInstance = CPTestUtil.addCPInstance(_group.getGroupId());

		ServiceContext serviceContext =
			ServiceContextTestUtil.getServiceContext(_group.getGroupId());

		CommerceWarehouse commerceWarehouse =
			CommerceTestUtil.addCommerceWarehouse(_group.getGroupId());

		CommerceTestUtil.addCommerceWarehouseItem(
			commerceWarehouse, cpInstance.getCPInstanceId(), 2);

		CommerceCurrency commerceCurrency =
			CommerceCurrencyTestUtil.addCommerceCurrency(_group.getGroupId());

		Assert.assertNotNull(commerceCurrency);

		CommerceOrder commerceOrder =
			_commerceOrderLocalService.addUserCommerceOrder(
				_group.getGroupId(), _user.getUserId(), _user.getUserId(),
				commerceCurrency.getCommerceCurrencyId());

		CommerceContext commerceContext = new TestCommerceContext(
			commerceCurrency, null, null, null, null);

		CommerceOrderItem commerceOrderItem =
			_commerceOrderItemLocalService.addCommerceOrderItem(
				commerceOrder.getCommerceOrderId(),
				cpInstance.getCPInstanceId(), 1, 0, null, commerceContext,
				serviceContext);

		List<CommerceOrderItem> commerceOrderItems =
			commerceOrder.getCommerceOrderItems();

		Assert.assertEquals(
			commerceOrderItems.toString(), 1, commerceOrderItems.size());

		CommerceOrderItem actualCommerceOrderItem = commerceOrderItems.get(0);

		Assert.assertEquals(
			commerceOrderItem.getCommerceOrderItemId(),
			actualCommerceOrderItem.getCommerceOrderItemId());
	}

	@Test(expected = CommerceOrderValidatorException.class)
	public void testAddCommerceOrderItemWithDraftCPDefinition()
		throws Exception {

		frutillaRule.scenario(
			"Add a SKU (product instance) to an order"
		).given(
			"A group"
		).and(
			"A user"
		).and(
			"A SKU linked to a not published product"
		).when(
			"There is availability for the SKU"
		).then(
			"I should be able to add the SKU to an order"
		);

		CPDefinition cpDefinition = CPTestUtil.addCPDefinition(
			_group.getGroupId(), false, true,
			WorkflowConstants.ACTION_SAVE_DRAFT);

		ServiceContext serviceContext =
			ServiceContextTestUtil.getServiceContext(_group.getGroupId());

		CommerceWarehouse commerceWarehouse =
			CommerceTestUtil.addCommerceWarehouse(_group.getGroupId());

		CPInstance cpInstance = _cpInstanceLocalService.getCPInstance(
			cpDefinition.getCPDefinitionId(), CPInstanceConstants.DEFAULT_SKU);

		CommerceTestUtil.addCommerceWarehouseItem(
			commerceWarehouse, cpInstance.getCPInstanceId(), 2);

		CommerceCurrency commerceCurrency =
			CommerceCurrencyTestUtil.addCommerceCurrency(_group.getGroupId());

		Assert.assertNotNull(commerceCurrency);

		CommerceOrder commerceOrder =
			_commerceOrderLocalService.addUserCommerceOrder(
				_group.getGroupId(), _user.getUserId(), _user.getUserId(),
				commerceCurrency.getCommerceCurrencyId());

		CommerceContext commerceContext = new TestCommerceContext(
			commerceCurrency, null, null, null, null);

		_commerceOrderItemLocalService.addCommerceOrderItem(
			commerceOrder.getCommerceOrderId(), cpInstance.getCPInstanceId(), 1,
			0, null, commerceContext, serviceContext);
	}

	@Test(expected = CommerceOrderValidatorException.class)
	public void testAddCommerceOrderItemWithDraftCPInstance() throws Exception {
		frutillaRule.scenario(
			"Add a SKU (product instance) to an order"
		).given(
			"A group"
		).and(
			"A user"
		).and(
			"A not pubblished SKU"
		).when(
			"There is availability for the SKU"
		).then(
			"I should not be able to add the SKU to an order"
		);

		CPInstance cpInstance = CPTestUtil.addCPInstance(_group.getGroupId());

		ServiceContext serviceContext =
			ServiceContextTestUtil.getServiceContext(_group.getGroupId());

		_cpInstanceLocalService.updateStatus(
			_user.getUserId(), cpInstance.getCPInstanceId(),
			WorkflowConstants.STATUS_DRAFT, serviceContext, null);

		CommerceWarehouse commerceWarehouse =
			CommerceTestUtil.addCommerceWarehouse(_group.getGroupId());

		CommerceTestUtil.addCommerceWarehouseItem(
			commerceWarehouse, cpInstance.getCPInstanceId(), 2);

		CommerceCurrency commerceCurrency =
			CommerceCurrencyTestUtil.addCommerceCurrency(_group.getGroupId());

		Assert.assertNotNull(commerceCurrency);

		CommerceOrder commerceOrder =
			_commerceOrderLocalService.addUserCommerceOrder(
				_group.getGroupId(), _user.getUserId(), _user.getUserId(),
				commerceCurrency.getCommerceCurrencyId());

		CommerceContext commerceContext = new TestCommerceContext(
			commerceCurrency, null, null, null, null);

		_commerceOrderItemLocalService.addCommerceOrderItem(
			commerceOrder.getCommerceOrderId(), cpInstance.getCPInstanceId(), 1,
			0, null, commerceContext, serviceContext);
	}

	@Rule
	public FrutillaRule frutillaRule = new FrutillaRule();

	@Inject
	private CommerceCurrencyLocalService _commerceCurrencyLocalService;

	@Inject
	private CommerceOrderItemLocalService _commerceOrderItemLocalService;

	@Inject
	private CommerceOrderLocalService _commerceOrderLocalService;

	@Inject
	private CPInstanceLocalService _cpInstanceLocalService;

	@DeleteAfterTestRun
	private Group _group;

	@DeleteAfterTestRun
	private User _user;

}