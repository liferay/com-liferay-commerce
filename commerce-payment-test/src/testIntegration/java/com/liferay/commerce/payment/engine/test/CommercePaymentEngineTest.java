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

package com.liferay.commerce.payment.engine.test;

import com.liferay.arquillian.extension.junit.bridge.junit.Arquillian;
import com.liferay.commerce.constants.CommerceOrderConstants;
import com.liferay.commerce.currency.model.CommerceCurrency;
import com.liferay.commerce.inventory.model.CommerceInventoryWarehouse;
import com.liferay.commerce.model.CommerceOrder;
import com.liferay.commerce.payment.engine.CommercePaymentEngine;
import com.liferay.commerce.payment.service.CommercePaymentMethodGroupRelLocalService;
import com.liferay.commerce.payment.test.util.TestCommercePaymentMethod;
import com.liferay.commerce.product.model.CPInstance;
import com.liferay.commerce.product.model.CommerceCatalog;
import com.liferay.commerce.product.model.CommerceChannel;
import com.liferay.commerce.product.service.CommerceCatalogLocalServiceUtil;
import com.liferay.commerce.product.test.util.CPTestUtil;
import com.liferay.commerce.service.CommerceOrderLocalService;
import com.liferay.commerce.test.util.CommerceInventoryTestUtil;
import com.liferay.commerce.test.util.CommerceTestUtil;
import com.liferay.portal.kernel.model.Company;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.service.CompanyLocalService;
import com.liferay.portal.kernel.service.ServiceContext;
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
import java.util.Collections;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.frutilla.FrutillaRule;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.ClassRule;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import org.springframework.mock.web.MockHttpServletRequest;

/**
 * @author Luca Pellizzon
 */
@RunWith(Arquillian.class)
public class CommercePaymentEngineTest {

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

		_commerceChannel = CommerceTestUtil.addCommerceChannel();

		ServiceContext serviceContext =
			ServiceContextTestUtil.getServiceContext(
				_company.getCompanyId(), _company.getGroupId(),
				_user.getUserId());

		serviceContext.setScopeGroupId(_commerceChannel.getSiteGroupId());

		_commercePaymentMethodGroupRelLocalService.
			addCommercePaymentMethodGroupRel(
				RandomTestUtil.randomLocaleStringMap(),
				RandomTestUtil.randomLocaleStringMap(), null,
				TestCommercePaymentMethod.KEY, Collections.emptyMap(), 99, true,
				serviceContext);

		_commerceOrders = new ArrayList<>();

		_httpServletRequest = new MockHttpServletRequest("GET", "");

		_httpServletRequest.setAttribute("LOCALE", LocaleUtil.ITALY);
	}

	@After
	public void tearDown() throws Exception {
		for (CommerceOrder commerceOrder : _commerceOrders) {
			_commerceOrderLocalService.deleteCommerceOrder(commerceOrder);
		}

		_companyLocalService.deleteCompany(_company);
	}

	@Test
	public void testCompletePayment() throws Exception {
		frutillaRule.scenario(
			"When a payment is completed the payment status should be 'paid'"
		).given(
			"An order with valid products"
		).when(
			"I complete the order payment"
		).then(
			"The order payment status should be 'paid'"
		);

		CommerceOrder commerceOrder = CommerceTestUtil.addB2CCommerceOrder(
			_company.getGroupId(), _user.getUserId(), 0,
			_commerceChannel.getSiteGroupId());

		_commerceOrders.add(commerceOrder);

		commerceOrder.setCommercePaymentMethodKey(
			TestCommercePaymentMethod.KEY);

		_commerceOrderLocalService.updateCommerceOrder(commerceOrder);

		CommerceCurrency commerceCurrency = commerceOrder.getCommerceCurrency();

		CommerceCatalog commerceCatalog =
			CommerceCatalogLocalServiceUtil.addCommerceCatalog(
				RandomTestUtil.randomString(), commerceCurrency.getCode(),
				LocaleUtil.toLanguageId(LocaleUtil.US), null,
				ServiceContextTestUtil.getServiceContext(
					_company.getGroupId()));

		CPInstance cpInstance = CPTestUtil.addCPInstanceFromCatalog(
			commerceCatalog.getGroupId());

		CommerceInventoryWarehouse commerceInventoryWarehouse =
			CommerceInventoryTestUtil.addCommerceInventoryWarehouse(
				_company.getGroupId());

		CommerceInventoryTestUtil.addCommerceInventoryWarehouseItem(
			_user.getUserId(), commerceInventoryWarehouse, cpInstance.getSku(),
			10);

		CommerceTestUtil.addWarehouseCommerceChannelRel(
			commerceInventoryWarehouse.getCommerceInventoryWarehouseId(),
			_commerceChannel.getCommerceChannelId());

		CommerceTestUtil.addCommerceOrderItem(
			commerceOrder.getCommerceOrderId(), cpInstance.getCPInstanceId(),
			1);

		CommerceOrder checkoutOrder = CommerceTestUtil.checkoutOrder(
			commerceOrder);

		_commercePaymentEngine.processPayment(
			commerceOrder.getCommerceOrderId(), null, _httpServletRequest);

		CommerceOrder paymentOrder =
			_commerceOrderLocalService.getCommerceOrder(
				checkoutOrder.getCommerceOrderId());

		Assert.assertEquals(
			CommerceOrderConstants.PAYMENT_STATUS_AUTHORIZED,
			paymentOrder.getPaymentStatus());

		Assert.assertNotNull(paymentOrder.getTransactionId());

		_commercePaymentEngine.completePayment(
			paymentOrder.getCommerceOrderId(), paymentOrder.getTransactionId(),
			_httpServletRequest);

		paymentOrder = _commerceOrderLocalService.getCommerceOrder(
			checkoutOrder.getCommerceOrderId());

		Assert.assertEquals(
			CommerceOrderConstants.PAYMENT_STATUS_PAID,
			paymentOrder.getPaymentStatus());
	}

	@Test
	public void testProcessPayment() throws Exception {
		frutillaRule.scenario(
			"When a payment is started the payment status should be " +
				"'authorized'"
		).given(
			"An order with valid products"
		).when(
			"I start the order payment"
		).then(
			"The order payment status should be 'authorized'"
		);

		CommerceOrder commerceOrder = CommerceTestUtil.addB2CCommerceOrder(
			_company.getGroupId(), _user.getUserId(), 0,
			_commerceChannel.getSiteGroupId());

		_commerceOrders.add(commerceOrder);

		commerceOrder.setCommercePaymentMethodKey(
			TestCommercePaymentMethod.KEY);

		_commerceOrderLocalService.updateCommerceOrder(commerceOrder);

		CommerceCurrency commerceCurrency = commerceOrder.getCommerceCurrency();

		CommerceCatalog commerceCatalog =
			CommerceCatalogLocalServiceUtil.addCommerceCatalog(
				RandomTestUtil.randomString(), commerceCurrency.getCode(),
				LocaleUtil.toLanguageId(LocaleUtil.US), null,
				ServiceContextTestUtil.getServiceContext(
					_company.getGroupId()));

		CPInstance cpInstance = CPTestUtil.addCPInstanceFromCatalog(
			commerceCatalog.getGroupId());

		CommerceInventoryWarehouse commerceInventoryWarehouse =
			CommerceInventoryTestUtil.addCommerceInventoryWarehouse(
				_company.getGroupId());

		CommerceInventoryTestUtil.addCommerceInventoryWarehouseItem(
			_user.getUserId(), commerceInventoryWarehouse, cpInstance.getSku(),
			10);

		CommerceTestUtil.addWarehouseCommerceChannelRel(
			commerceInventoryWarehouse.getCommerceInventoryWarehouseId(),
			_commerceChannel.getCommerceChannelId());

		CommerceTestUtil.addCommerceOrderItem(
			commerceOrder.getCommerceOrderId(), cpInstance.getCPInstanceId(),
			1);

		CommerceOrder checkoutOrder = CommerceTestUtil.checkoutOrder(
			commerceOrder);

		_commercePaymentEngine.processPayment(
			commerceOrder.getCommerceOrderId(), null, _httpServletRequest);

		CommerceOrder paymentOrder =
			_commerceOrderLocalService.getCommerceOrder(
				checkoutOrder.getCommerceOrderId());

		Assert.assertEquals(
			CommerceOrderConstants.PAYMENT_STATUS_AUTHORIZED,
			paymentOrder.getPaymentStatus());

		Assert.assertNotNull(paymentOrder.getTransactionId());
	}

	@Rule
	public FrutillaRule frutillaRule = new FrutillaRule();

	private CommerceChannel _commerceChannel;

	@Inject
	private CommerceOrderLocalService _commerceOrderLocalService;

	private List<CommerceOrder> _commerceOrders;

	@Inject
	private CommercePaymentEngine _commercePaymentEngine;

	@Inject
	private CommercePaymentMethodGroupRelLocalService
		_commercePaymentMethodGroupRelLocalService;

	private Company _company;

	@Inject
	private CompanyLocalService _companyLocalService;

	private HttpServletRequest _httpServletRequest;
	private User _user;

}