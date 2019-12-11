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
import com.liferay.commerce.currency.model.CommerceCurrency;
import com.liferay.commerce.model.CommerceOrder;
import com.liferay.commerce.model.CommerceOrderItem;
import com.liferay.commerce.product.model.CPDefinition;
import com.liferay.commerce.product.model.CPInstance;
import com.liferay.commerce.product.model.CommerceCatalog;
import com.liferay.commerce.product.model.CommerceChannel;
import com.liferay.commerce.product.service.CPInstanceLocalService;
import com.liferay.commerce.product.service.CommerceCatalogLocalService;
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
import com.liferay.portal.kernel.model.Company;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.service.CompanyLocalService;
import com.liferay.portal.kernel.test.rule.AggregateTestRule;
import com.liferay.portal.kernel.test.util.CompanyTestUtil;
import com.liferay.portal.kernel.test.util.RandomTestUtil;
import com.liferay.portal.kernel.test.util.ServiceContextTestUtil;
import com.liferay.portal.kernel.test.util.UserTestUtil;
import com.liferay.portal.kernel.util.LocaleUtil;
import com.liferay.portal.test.rule.Inject;
import com.liferay.portal.test.rule.LiferayIntegrationTestRule;
import com.liferay.portal.test.rule.PermissionCheckerTestRule;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import org.frutilla.FrutillaRule;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.ClassRule;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 * @author Alessio Antonio Rendina
 */
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
		_company = CompanyTestUtil.addCompany();

		_user = UserTestUtil.addUser(_company);

		_commerceOrders = new ArrayList<>();
	}

	@After
	public void tearDown() throws Exception {
		for (CommerceOrder commerceOrder : _commerceOrders) {
			_commerceOrderLocalService.deleteCommerceOrder(commerceOrder);
		}

		_companyLocalService.deleteCompany(_company);
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

		CommerceChannel commerceChannel = CommerceTestUtil.addCommerceChannel();

		CommerceOrder commerceOrder = CommerceTestUtil.addB2CCommerceOrder(
			_company.getGroupId(), _user.getUserId(), 0,
			commerceChannel.getSiteGroupId());

		_commerceOrders.add(commerceOrder);

		CommerceCurrency commerceCurrency = commerceOrder.getCommerceCurrency();

		CommerceCatalog commerceCatalog =
			_commerceCatalogLocalService.addCommerceCatalog(
				RandomTestUtil.randomString(), commerceCurrency.getCode(),
				LocaleUtil.toLanguageId(LocaleUtil.US), null,
				ServiceContextTestUtil.getServiceContext(
					_company.getGroupId()));

		CPDefinition cpDefinition = CPTestUtil.addCPDefinitionFromCatalog(
			commerceCatalog.getGroupId(), VirtualCPTypeConstants.NAME, true,
			true);

		VirtualCPTypeTestUtil.addCPDefinitionVirtualSetting(
			commerceCatalog.getGroupId(), cpDefinition.getModelClassName(),
			cpDefinition.getCPDefinitionId(), 0,
			CommerceOrderConstants.ORDER_STATUS_TO_TRANSMIT, 0, 0, 0);

		CommerceTestUtil.addBackOrderCPDefinitionInventory(cpDefinition);

		for (CPInstance cpInstance : cpDefinition.getCPInstances()) {
			CommerceTestUtil.addCommerceOrderItem(
				commerceOrder.getCommerceOrderId(),
				cpInstance.getCPInstanceId(), 1);
		}

		commerceOrder = _setCommerceOrderStatuses(
			commerceOrder, CommerceOrderConstants.PAYMENT_STATUS_PAID,
			CommerceOrderConstants.ORDER_STATUS_TO_TRANSMIT);

		_commerceVirtualOrderItemChecker.checkCommerceVirtualOrderItems(
			commerceOrder.getCommerceOrderId());

		List<CommerceOrderItem> commerceOrderItems =
			commerceOrder.getCommerceOrderItems();

		List<CommerceVirtualOrderItem> userCommerceVirtualOrderItems =
			_commerceVirtualOrderItemLocalService.getCommerceVirtualOrderItems(
				commerceChannel.getGroupId(),
				commerceOrder.getCommerceAccountId(), QueryUtil.ALL_POS,
				QueryUtil.ALL_POS, null);

		Assert.assertEquals(
			userCommerceVirtualOrderItems.toString(), commerceOrderItems.size(),
			userCommerceVirtualOrderItems.size());

		for (CommerceVirtualOrderItem commerceVirtualOrderItem :
				userCommerceVirtualOrderItems) {

			Assert.assertEquals(true, commerceVirtualOrderItem.isActive());
			Assert.assertEquals(
				CommerceOrderConstants.ORDER_STATUS_TO_TRANSMIT,
				commerceVirtualOrderItem.getActivationStatus());
			Assert.assertEquals(0L, commerceVirtualOrderItem.getDuration());

			if (Objects.equals(
					commerceVirtualOrderItem.getActivationStatus(),
					commerceOrder.getOrderStatus())) {

				Assert.assertEquals(
					null, commerceVirtualOrderItem.getEndDate());
			}
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

		CommerceChannel commerceChannel = CommerceTestUtil.addCommerceChannel();

		CommerceOrder commerceOrder = CommerceTestUtil.addB2CCommerceOrder(
			_company.getGroupId(), _user.getUserId(), 0,
			commerceChannel.getSiteGroupId());

		_commerceOrders.add(commerceOrder);

		CommerceCurrency commerceCurrency = commerceOrder.getCommerceCurrency();

		CommerceCatalog commerceCatalog =
			_commerceCatalogLocalService.addCommerceCatalog(
				RandomTestUtil.randomString(), commerceCurrency.getCode(),
				LocaleUtil.toLanguageId(LocaleUtil.US), null,
				ServiceContextTestUtil.getServiceContext(
					_company.getGroupId()));

		CPDefinition cpDefinition = CPTestUtil.addCPDefinitionFromCatalog(
			commerceCatalog.getGroupId(), VirtualCPTypeConstants.NAME, true,
			true);

		VirtualCPTypeTestUtil.addCPDefinitionVirtualSetting(
			commerceCatalog.getGroupId(), cpDefinition.getModelClassName(),
			cpDefinition.getCPDefinitionId(), 0L,
			CommerceOrderConstants.ORDER_STATUS_TO_TRANSMIT, 0L, 0L, 0L);

		CommerceTestUtil.addBackOrderCPDefinitionInventory(cpDefinition);

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
			CommerceOrderConstants.ORDER_STATUS_TO_TRANSMIT);

		_commerceSubscriptionEntryHelper.checkCommerceSubscriptions(
			commerceOrder);

		_commerceVirtualOrderItemChecker.checkCommerceVirtualOrderItems(
			commerceOrder.getCommerceOrderId());

		List<CommerceOrderItem> commerceOrderItems =
			commerceOrder.getCommerceOrderItems();

		List<CommerceVirtualOrderItem> userCommerceVirtualOrderItems =
			_commerceVirtualOrderItemLocalService.getCommerceVirtualOrderItems(
				commerceChannel.getGroupId(),
				commerceOrder.getCommerceAccountId(), QueryUtil.ALL_POS,
				QueryUtil.ALL_POS, null);

		Assert.assertEquals(
			userCommerceVirtualOrderItems.toString(), commerceOrderItems.size(),
			userCommerceVirtualOrderItems.size());

		for (CommerceVirtualOrderItem commerceVirtualOrderItem :
				userCommerceVirtualOrderItems) {

			Assert.assertEquals(true, commerceVirtualOrderItem.isActive());
			Assert.assertEquals(
				CommerceOrderConstants.ORDER_STATUS_TO_TRANSMIT,
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
	private CommerceCatalogLocalService _commerceCatalogLocalService;

	@Inject
	private CommerceOrderLocalService _commerceOrderLocalService;

	private List<CommerceOrder> _commerceOrders;

	@Inject
	private CommerceSubscriptionEntryHelper _commerceSubscriptionEntryHelper;

	@Inject
	private CommerceVirtualOrderItemChecker _commerceVirtualOrderItemChecker;

	@Inject
	private CommerceVirtualOrderItemLocalService
		_commerceVirtualOrderItemLocalService;

	private Company _company;

	@Inject
	private CompanyLocalService _companyLocalService;

	@Inject
	private CPInstanceLocalService _cpInstanceLocalService;

	private User _user;

}