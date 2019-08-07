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

package com.liferay.commerce.inventory.engine.test;

import com.liferay.arquillian.extension.junit.bridge.junit.Arquillian;
import com.liferay.commerce.inventory.engine.CommerceInventoryEngine;
import com.liferay.commerce.inventory.exception.NoSuchInventoryBookedQuantityException;
import com.liferay.commerce.inventory.model.CommerceInventoryBookedQuantity;
import com.liferay.commerce.inventory.model.CommerceInventoryWarehouseItem;
import com.liferay.commerce.inventory.service.CommerceInventoryBookedQuantityLocalService;
import com.liferay.commerce.product.model.CPInstance;
import com.liferay.commerce.product.model.CommerceChannel;
import com.liferay.commerce.product.model.CommerceChannelConstants;
import com.liferay.commerce.product.service.CPInstanceLocalService;
import com.liferay.commerce.product.service.CommerceChannelLocalService;
import com.liferay.commerce.product.test.util.CPTestUtil;
import com.liferay.commerce.service.CommerceCountryLocalService;
import com.liferay.commerce.test.util.CommerceInventoryTestUtil;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.model.Company;
import com.liferay.portal.kernel.model.Group;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.test.rule.AggregateTestRule;
import com.liferay.portal.kernel.test.rule.DeleteAfterTestRun;
import com.liferay.portal.kernel.test.util.CompanyTestUtil;
import com.liferay.portal.kernel.test.util.GroupTestUtil;
import com.liferay.portal.kernel.test.util.RandomTestUtil;
import com.liferay.portal.kernel.test.util.ServiceContextTestUtil;
import com.liferay.portal.kernel.test.util.UserTestUtil;
import com.liferay.portal.test.rule.Inject;
import com.liferay.portal.test.rule.LiferayIntegrationTestRule;
import com.liferay.portal.test.rule.PermissionCheckerTestRule;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

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
public class CommerceInventoryEngineTest {

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

		_group = GroupTestUtil.addGroup(
			_company.getCompanyId(), _user.getUserId(), 0);

		_serviceContext = ServiceContextTestUtil.getServiceContext(
			_company.getCompanyId(), _group.getGroupId(), _user.getUserId());

		_commerceChannel = _commerceChannelLocalService.addCommerceChannel(
			_group.getGroupId(),
			_group.getName(_serviceContext.getLanguageId()) + " Portal",
			CommerceChannelConstants.CHANNEL_TYPE_SITE, null, StringPool.BLANK,
			StringPool.BLANK, _serviceContext);

		_cpInstance1 = _randomCPInstanceSku();
		_cpInstance2 = _randomCPInstanceSku();
	}

	@Test(expected = NoSuchInventoryBookedQuantityException.class)
	public void testConsumeQuantity() throws Exception {
		_commerceInventoryWarehouseItem1 =
			CommerceInventoryTestUtil.addCommerceInventoryWarehouseItem(
				_commerceChannel.getCommerceChannelId(), _cpInstance1.getSku(),
				10, _serviceContext);

		int bookQuantity = 5;

		CommerceInventoryBookedQuantity commerceBookedQuantity =
			_commerceBookedQuantityLocalService.addCommerceBookedQuantity(
				_user.getUserId(), _cpInstance1.getSku(), bookQuantity, null,
				Collections.emptyMap());

		int stockQuantity = _commerceInventoryEngine.getStockQuantity(
			_company.getCompanyId(), _commerceChannel.getGroupId(),
			_cpInstance1.getSku());

		Assert.assertEquals(
			_commerceInventoryWarehouseItem1.getQuantity() - bookQuantity,
			stockQuantity);

		_commerceInventoryEngine.consumeQuantity(
			_user.getUserId(),
			_commerceInventoryWarehouseItem1.getCommerceInventoryWarehouseId(),
			_cpInstance1.getSku(), bookQuantity,
			commerceBookedQuantity.getCommerceInventoryBookedQuantityId(),
			Collections.emptyMap());

		stockQuantity = _commerceInventoryEngine.getStockQuantity(
			_company.getCompanyId(), _commerceChannel.getGroupId(),
			_cpInstance1.getSku());

		Assert.assertEquals(
			_commerceInventoryWarehouseItem1.getQuantity() - bookQuantity,
			stockQuantity);

		_commerceBookedQuantityLocalService.getCommerceInventoryBookedQuantity(
			commerceBookedQuantity.getCommerceInventoryBookedQuantityId());
	}

	@Test
	public void testGetStockQuantities() throws Exception {
		_commerceInventoryWarehouseItem1 =
			CommerceInventoryTestUtil.addCommerceInventoryWarehouseItem(
				_commerceChannel.getCommerceChannelId(), _cpInstance1.getSku(),
				10, _serviceContext);

		_commerceInventoryWarehouseItem2 =
			CommerceInventoryTestUtil.addCommerceInventoryWarehouseItem(
				_commerceChannel.getCommerceChannelId(), _cpInstance2.getSku(),
				15, _serviceContext);

		List<String> skuList = new ArrayList<>();

		skuList.add(_cpInstance1.getSku());
		skuList.add(_cpInstance2.getSku());

		Map stockQuantities = _commerceInventoryEngine.getStockQuantities(
			_company.getCompanyId(), _commerceChannel.getGroupId(), skuList);

		Set set = stockQuantities.keySet();

		Iterator iterator = set.iterator();

		while (iterator.hasNext()) {
			String sku = (String)iterator.next();

			if (Objects.equals(sku, _cpInstance1.getSku())) {
				Assert.assertEquals(
					_commerceInventoryWarehouseItem1.getQuantity(),
					stockQuantities.get(sku));
			}

			if (Objects.equals(sku, _cpInstance2.getSku())) {
				Assert.assertEquals(
					_commerceInventoryWarehouseItem2.getQuantity(),
					stockQuantities.get(sku));
			}
		}
	}

	@Test
	public void testGetStockQuantity() throws Exception {
		_commerceInventoryWarehouseItem1 =
			CommerceInventoryTestUtil.addCommerceInventoryWarehouseItem(
				_commerceChannel.getCommerceChannelId(), _cpInstance1.getSku(),
				10, _serviceContext);

		int stockQuantity = _commerceInventoryEngine.getStockQuantity(
			_company.getCompanyId(), _commerceChannel.getGroupId(),
			_cpInstance1.getSku());

		Assert.assertEquals(
			_commerceInventoryWarehouseItem1.getQuantity(), stockQuantity);
	}

	private CPInstance _randomCPInstanceSku() throws Exception {
		CPInstance cpInstance = CPTestUtil.addCPInstance(_group.getGroupId());

		cpInstance.setSku(RandomTestUtil.randomString());

		return _cpInstanceLocalService.updateCPInstance(cpInstance);
	}

	@Inject
	private CommerceInventoryBookedQuantityLocalService
		_commerceBookedQuantityLocalService;

	private CommerceChannel _commerceChannel;

	@Inject
	private CommerceChannelLocalService _commerceChannelLocalService;

	@Inject
	private CommerceCountryLocalService _commerceCountryLocalService;

	@Inject
	private CommerceInventoryEngine _commerceInventoryEngine;

	private CommerceInventoryWarehouseItem _commerceInventoryWarehouseItem1;
	private CommerceInventoryWarehouseItem _commerceInventoryWarehouseItem2;

	@DeleteAfterTestRun
	private Company _company;

	private CPInstance _cpInstance1;
	private CPInstance _cpInstance2;

	@Inject
	private CPInstanceLocalService _cpInstanceLocalService;

	private Group _group;
	private ServiceContext _serviceContext;
	private User _user;

}