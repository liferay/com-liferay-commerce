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
import com.liferay.commerce.currency.test.util.CommerceCurrencyTestUtil;
import com.liferay.commerce.model.CommerceOrder;
import com.liferay.commerce.model.CommerceWarehouse;
import com.liferay.commerce.payment.engine.CommercePaymentEngine;
import com.liferay.commerce.payment.service.CommercePaymentMethodGroupRelLocalService;
import com.liferay.commerce.payment.test.util.TestCommercePaymentMethod;
import com.liferay.commerce.product.model.CPInstance;
import com.liferay.commerce.product.test.util.CPTestUtil;
import com.liferay.commerce.service.CommerceOrderLocalService;
import com.liferay.commerce.test.util.CommerceTestUtil;
import com.liferay.portal.kernel.model.Group;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.test.rule.AggregateTestRule;
import com.liferay.portal.kernel.test.rule.DeleteAfterTestRun;
import com.liferay.portal.kernel.test.util.GroupTestUtil;
import com.liferay.portal.kernel.test.util.RandomTestUtil;
import com.liferay.portal.kernel.test.util.ServiceContextTestUtil;
import com.liferay.portal.kernel.test.util.UserTestUtil;
import com.liferay.portal.kernel.util.LocaleUtil;
import com.liferay.portal.test.rule.Inject;
import com.liferay.portal.test.rule.LiferayIntegrationTestRule;
import com.liferay.portal.test.rule.PermissionCheckerTestRule;

import java.util.Collections;

import javax.servlet.http.HttpServletRequest;

import org.frutilla.FrutillaRule;

import org.junit.Assert;
import org.junit.Before;
import org.junit.ClassRule;
import org.junit.Ignore;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import org.springframework.mock.web.MockHttpServletRequest;

/**
 * @author Luca Pellizzon
 */
@Ignore
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
		_group = GroupTestUtil.addGroup();

		_user = UserTestUtil.addUser();

		_commerceCurrency = CommerceCurrencyTestUtil.addCommerceCurrency(
			_group.getGroupId());

		ServiceContext serviceContext =
			ServiceContextTestUtil.getServiceContext(
				_group.getCompanyId(), _group.getGroupId(), _user.getUserId());

		_commercePaymentMethodGroupRelLocalService.
			addCommercePaymentMethodGroupRel(
				RandomTestUtil.randomLocaleStringMap(),
				RandomTestUtil.randomLocaleStringMap(), null,
				TestCommercePaymentMethod.KEY, Collections.emptyMap(), 99, true,
				serviceContext);

		_httpServletRequest = new MockHttpServletRequest("GET", "");

		_httpServletRequest.setAttribute("LOCALE", LocaleUtil.ITALY);
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
			_group.getGroupId(), _user.getUserId(),
			_commerceCurrency.getCommerceCurrencyId());

		commerceOrder.setCommercePaymentMethodKey(
			TestCommercePaymentMethod.KEY);

		_commerceOrderLocalService.updateCommerceOrder(commerceOrder);

		CPInstance cpInstance = CPTestUtil.addCPInstance(_group.getGroupId());

		CommerceWarehouse commerceWarehouse =
			CommerceTestUtil.addCommerceWarehouse(_group.getGroupId());

		CommerceTestUtil.addCommerceWarehouseItem(
			commerceWarehouse, cpInstance.getCPInstanceId(), 10);

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
			_group.getGroupId(), _user.getUserId(),
			_commerceCurrency.getCommerceCurrencyId());

		commerceOrder.setCommercePaymentMethodKey(
			TestCommercePaymentMethod.KEY);

		_commerceOrderLocalService.updateCommerceOrder(commerceOrder);

		CPInstance cpInstance = CPTestUtil.addCPInstance(_group.getGroupId());

		CommerceWarehouse commerceWarehouse =
			CommerceTestUtil.addCommerceWarehouse(_group.getGroupId());

		CommerceTestUtil.addCommerceWarehouseItem(
			commerceWarehouse, cpInstance.getCPInstanceId(), 10);

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

	@DeleteAfterTestRun
	private CommerceCurrency _commerceCurrency;

	@Inject
	private CommerceOrderLocalService _commerceOrderLocalService;

	@Inject
	private CommercePaymentEngine _commercePaymentEngine;

	@Inject
	private CommercePaymentMethodGroupRelLocalService
		_commercePaymentMethodGroupRelLocalService;

	@DeleteAfterTestRun
	private Group _group;

	private HttpServletRequest _httpServletRequest;

	@DeleteAfterTestRun
	private User _user;

}