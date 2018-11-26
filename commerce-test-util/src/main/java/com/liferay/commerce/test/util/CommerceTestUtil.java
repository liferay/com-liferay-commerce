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

package com.liferay.commerce.test.util;

import com.liferay.commerce.context.CommerceContext;
import com.liferay.commerce.currency.model.CommerceCurrency;
import com.liferay.commerce.currency.test.util.CommerceCurrencyTestUtil;
import com.liferay.commerce.model.CPDefinitionInventory;
import com.liferay.commerce.model.CommerceAddress;
import com.liferay.commerce.model.CommerceCountry;
import com.liferay.commerce.model.CommerceOrder;
import com.liferay.commerce.model.CommerceOrderItem;
import com.liferay.commerce.model.CommercePaymentMethod;
import com.liferay.commerce.model.CommerceRegion;
import com.liferay.commerce.model.CommerceShippingMethod;
import com.liferay.commerce.model.CommerceWarehouse;
import com.liferay.commerce.model.CommerceWarehouseItem;
import com.liferay.commerce.product.model.CPDefinition;
import com.liferay.commerce.product.model.CPInstance;
import com.liferay.commerce.product.service.CPInstanceLocalServiceUtil;
import com.liferay.commerce.product.test.util.CPTestUtil;
import com.liferay.commerce.service.CPDefinitionInventoryLocalServiceUtil;
import com.liferay.commerce.service.CommerceAddressLocalServiceUtil;
import com.liferay.commerce.service.CommerceCountryLocalServiceUtil;
import com.liferay.commerce.service.CommerceOrderItemLocalServiceUtil;
import com.liferay.commerce.service.CommerceOrderLocalServiceUtil;
import com.liferay.commerce.service.CommercePaymentMethodLocalServiceUtil;
import com.liferay.commerce.service.CommerceRegionLocalServiceUtil;
import com.liferay.commerce.service.CommerceShippingMethodLocalServiceUtil;
import com.liferay.commerce.service.CommerceWarehouseItemLocalServiceUtil;
import com.liferay.commerce.service.CommerceWarehouseLocalServiceUtil;
import com.liferay.commerce.shipping.engine.fixed.model.CommerceShippingFixedOption;
import com.liferay.commerce.shipping.engine.fixed.service.CommerceShippingFixedOptionLocalServiceUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.test.util.RandomTestUtil;
import com.liferay.portal.kernel.test.util.ServiceContextTestUtil;

import java.math.BigDecimal;

import java.util.Collections;

/**
 * @author Andrea Di Giorgi
 */
public class CommerceTestUtil {

	public static CPDefinitionInventory addBackOrderCPDefinitionInventory(
			CPDefinition cpDefinition)
		throws PortalException {

		ServiceContext serviceContext =
			ServiceContextTestUtil.getServiceContext(cpDefinition.getGroupId());

		return CPDefinitionInventoryLocalServiceUtil.addCPDefinitionInventory(
			cpDefinition.getCPDefinitionId(), "", "", false, false, 0, true, 1,
			10000, "", 0, serviceContext);
	}

	public static CommerceOrder addCheckoutDetailsToUserOrder(
			CommerceOrder commerceOrder, long userId)
		throws Exception {

		long groupId = commerceOrder.getGroupId();

		CPInstance cpInstance = CPTestUtil.addCPInstance(groupId);

		BigDecimal price = BigDecimal.valueOf(RandomTestUtil.randomDouble());

		cpInstance.setPrice(price);

		CPInstanceLocalServiceUtil.updateCPInstance(cpInstance);

		CommerceWarehouse commerceWarehouse = addCommerceWarehouse(groupId);

		addCommerceWarehouseItem(
			commerceWarehouse, cpInstance.getCPInstanceId(), 10);

		addCommerceOrderItem(
			commerceOrder.getCommerceOrderId(), cpInstance.getCPInstanceId(),
			4);

		CommerceAddress billingCommerceAddress = addUserCommerceAddress(
			groupId, userId);
		CommerceAddress shippingCommerceAddress = addUserCommerceAddress(
			groupId, userId);

		commerceOrder.setBillingAddressId(
			billingCommerceAddress.getCommerceAddressId());
		commerceOrder.setShippingAddressId(
			shippingCommerceAddress.getCommerceAddressId());

		CommercePaymentMethod commercePaymentMethod = addCommercePaymentMethod(
			groupId);

		commerceOrder.setCommercePaymentMethodId(
			commercePaymentMethod.getCommercePaymentMethodId());

		CommerceShippingMethod commerceShippingMethod =
			addCommerceShippingMethod(groupId);

		commerceOrder.setCommerceShippingMethodId(
			commerceShippingMethod.getCommerceShippingMethodId());

		CommerceShippingFixedOption commerceShippingFixedOption =
			addCommerceShippingFixedOption(commerceShippingMethod);

		commerceOrder.setShippingOptionName(
			commerceShippingFixedOption.getName());

		commerceOrder.setShippingAmount(
			commerceShippingFixedOption.getAmount());

		return CommerceOrderLocalServiceUtil.updateCommerceOrder(commerceOrder);
	}

	public static CommerceOrderItem addCommerceOrderItem(
			long commerceOrderId, long cpInstanceId, int quantity)
		throws Exception {

		CommerceOrder commerceOrder =
			CommerceOrderLocalServiceUtil.getCommerceOrder(commerceOrderId);

		if (commerceOrder.getCommerceCurrency() == null) {
			CommerceCurrency commerceCurrency =
				CommerceCurrencyTestUtil.addCommerceCurrency(
					commerceOrder.getGroupId());

			commerceOrder.setCommerceCurrencyId(
				commerceCurrency.getCommerceCurrencyId());

			CommerceOrderLocalServiceUtil.updateCommerceOrder(commerceOrder);
		}

		CommerceContext commerceContext = new TestCommerceContext(
			commerceOrder.getCommerceCurrency(), null, null, null,
			commerceOrder, null);

		return addCommerceOrderItem(
			commerceOrderId, cpInstanceId, quantity, commerceContext);
	}

	public static CommerceOrderItem addCommerceOrderItem(
			long commerceOrderId, long cpInstanceId, int quantity,
			CommerceContext commerceContext)
		throws Exception {

		CommerceOrder commerceOrder =
			CommerceOrderLocalServiceUtil.getCommerceOrder(commerceOrderId);

		ServiceContext serviceContext =
			ServiceContextTestUtil.getServiceContext(
				commerceOrder.getGroupId());

		return CommerceOrderItemLocalServiceUtil.addCommerceOrderItem(
			commerceOrderId, cpInstanceId, quantity, 0, null, commerceContext,
			serviceContext);
	}

	public static CommercePaymentMethod addCommercePaymentMethod(long groupId)
		throws Exception {

		ServiceContext serviceContext =
			ServiceContextTestUtil.getServiceContext(groupId);

		return CommercePaymentMethodLocalServiceUtil.addCommercePaymentMethod(
			RandomTestUtil.randomLocaleStringMap(),
			RandomTestUtil.randomLocaleStringMap(), null, "money-order",
			Collections.<String, String>emptyMap(), 1, true, serviceContext);
	}

	public static CommerceShippingFixedOption addCommerceShippingFixedOption(
			CommerceShippingMethod commerceShippingMethod)
		throws Exception {

		BigDecimal value = BigDecimal.valueOf(RandomTestUtil.randomDouble());

		ServiceContext serviceContext =
			ServiceContextTestUtil.getServiceContext(
				commerceShippingMethod.getGroupId());

		return CommerceShippingFixedOptionLocalServiceUtil.
			addCommerceShippingFixedOption(
				commerceShippingMethod.getCommerceShippingMethodId(),
				RandomTestUtil.randomLocaleStringMap(),
				RandomTestUtil.randomLocaleStringMap(), value, 1,
				serviceContext);
	}

	public static CommerceShippingMethod addCommerceShippingMethod(long groupId)
		throws Exception {

		ServiceContext serviceContext =
			ServiceContextTestUtil.getServiceContext(groupId);

		return CommerceShippingMethodLocalServiceUtil.addCommerceShippingMethod(
			RandomTestUtil.randomLocaleStringMap(),
			RandomTestUtil.randomLocaleStringMap(), null, "fixedPrice", 1, true,
			serviceContext);
	}

	public static CommerceWarehouse addCommerceWarehouse(long groupId)
		throws PortalException {

		ServiceContext serviceContext =
			ServiceContextTestUtil.getServiceContext(groupId);

		CommerceCountry commerceCountry = _setUpCountry(
			groupId, serviceContext);

		CommerceRegion commerceRegion = _setUpRegion(
			commerceCountry, serviceContext);

		return CommerceWarehouseLocalServiceUtil.addCommerceWarehouse(
			RandomTestUtil.randomString(), RandomTestUtil.randomString(), true,
			RandomTestUtil.randomString(), RandomTestUtil.randomString(),
			RandomTestUtil.randomString(), RandomTestUtil.randomString(),
			RandomTestUtil.randomString(), commerceRegion.getCommerceRegionId(),
			commerceCountry.getCommerceCountryId(), 45.4386111, 12.3266667,
			serviceContext);
	}

	public static CommerceWarehouse addCommerceWarehouse(
			long groupId, String name)
		throws Exception {

		ServiceContext serviceContext =
			ServiceContextTestUtil.getServiceContext(groupId);

		return CommerceWarehouseLocalServiceUtil.addCommerceWarehouse(
			name, RandomTestUtil.randomString(), true,
			RandomTestUtil.randomString(), RandomTestUtil.randomString(),
			RandomTestUtil.randomString(), RandomTestUtil.randomString(),
			RandomTestUtil.randomString(), 0, 0, RandomTestUtil.randomDouble(),
			RandomTestUtil.randomDouble(), serviceContext);
	}

	public static CommerceWarehouseItem addCommerceWarehouseItem(
			CommerceWarehouse commerceWarehouse, long cpInstanceId,
			int quantity)
		throws Exception {

		ServiceContext serviceContext =
			ServiceContextTestUtil.getServiceContext(
				commerceWarehouse.getGroupId());

		return CommerceWarehouseItemLocalServiceUtil.addCommerceWarehouseItem(
			commerceWarehouse.getCommerceWarehouseId(), cpInstanceId, quantity,
			serviceContext);
	}

	public static CommerceAddress addUserCommerceAddress(
			long groupId, long userId)
		throws Exception {

		ServiceContext serviceContext =
			ServiceContextTestUtil.getServiceContext(groupId);

		CommerceCountry commerceCountry = _setUpCountry(
			groupId, serviceContext);

		CommerceRegion commerceRegion = _setUpRegion(
			commerceCountry, serviceContext);

		return CommerceAddressLocalServiceUtil.addCommerceAddress(
			User.class.getName(), userId, RandomTestUtil.randomString(),
			RandomTestUtil.randomString(), RandomTestUtil.randomString(),
			RandomTestUtil.randomString(), RandomTestUtil.randomString(),
			RandomTestUtil.randomString(), String.valueOf(30133),
			commerceRegion.getCommerceRegionId(),
			commerceCountry.getCommerceCountryId(),
			RandomTestUtil.randomString(), false, false, serviceContext);
	}

	public static CommerceOrder addUserCommerceOrder(
			long groupId, long userId, long commerceCurrencyId)
		throws Exception {

		ServiceContext serviceContext =
			ServiceContextTestUtil.getServiceContext(groupId);

		if (userId == 0) {
			userId = serviceContext.getUserId();
		}

		if (commerceCurrencyId == 0) {
			CommerceCurrency commerceCurrency =
				CommerceCurrencyTestUtil.addCommerceCurrency(groupId);

			commerceCurrencyId = commerceCurrency.getCommerceCurrencyId();
		}

		return CommerceOrderLocalServiceUtil.addUserCommerceOrder(
			groupId, userId, userId, commerceCurrencyId);
	}

	public static CommerceOrder checkoutOrder(CommerceOrder commerceOrder)
		throws Exception {

		ServiceContext serviceContext =
			ServiceContextTestUtil.getServiceContext(
				commerceOrder.getGroupId());

		CommerceCurrency commerceCurrency =
			CommerceCurrencyTestUtil.addCommerceCurrency(
				commerceOrder.getGroupId());

		CommerceContext commerceContext = new TestCommerceContext(
			commerceCurrency, null, serviceContext.getScopeGroup(), null,
			commerceOrder, null);

		return CommerceOrderLocalServiceUtil.checkoutCommerceOrder(
			commerceOrder.getCommerceOrderId(), commerceContext,
			serviceContext);
	}

	private static CommerceCountry _setUpCountry(
			long groupId, ServiceContext serviceContext)
		throws PortalException {

		CommerceCountry commerceCountry =
			CommerceCountryLocalServiceUtil.fetchCommerceCountry(groupId, 000);

		if (commerceCountry == null) {
			commerceCountry =
				CommerceCountryLocalServiceUtil.addCommerceCountry(
					RandomTestUtil.randomLocaleStringMap(), true, true, "ZZ",
					"ZZZ", 000, false, RandomTestUtil.randomDouble(), true,
					serviceContext);
		}

		return commerceCountry;
	}

	private static CommerceRegion _setUpRegion(
			CommerceCountry commerceCountry, ServiceContext serviceContext)
		throws PortalException {

		CommerceRegion commerceRegion;

		try {
			commerceRegion = CommerceRegionLocalServiceUtil.getCommerceRegion(
				commerceCountry.getCommerceCountryId(), "ZZ");
		}
		catch (Exception e) {
			commerceRegion = CommerceRegionLocalServiceUtil.addCommerceRegion(
				commerceCountry.getCommerceCountryId(),
				RandomTestUtil.randomString(), RandomTestUtil.randomString(),
				RandomTestUtil.randomDouble(), true, serviceContext);
		}

		return commerceRegion;
	}

}