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

import com.liferay.commerce.account.model.CommerceAccount;
import com.liferay.commerce.account.service.CommerceAccountLocalServiceUtil;
import com.liferay.commerce.context.CommerceContext;
import com.liferay.commerce.currency.model.CommerceCurrency;
import com.liferay.commerce.currency.test.util.CommerceCurrencyTestUtil;
import com.liferay.commerce.inventory.model.CommerceInventoryWarehouse;
import com.liferay.commerce.model.CPDefinitionInventory;
import com.liferay.commerce.model.CommerceAddress;
import com.liferay.commerce.model.CommerceCountry;
import com.liferay.commerce.model.CommerceOrder;
import com.liferay.commerce.model.CommerceOrderItem;
import com.liferay.commerce.model.CommerceRegion;
import com.liferay.commerce.model.CommerceShippingMethod;
import com.liferay.commerce.payment.model.CommercePaymentMethodGroupRel;
import com.liferay.commerce.payment.service.CommercePaymentMethodGroupRelLocalServiceUtil;
import com.liferay.commerce.product.model.CPDefinition;
import com.liferay.commerce.product.model.CPInstance;
import com.liferay.commerce.product.service.CPInstanceLocalServiceUtil;
import com.liferay.commerce.product.service.CommerceChannelLocalServiceUtil;
import com.liferay.commerce.product.test.util.CPTestUtil;
import com.liferay.commerce.service.CPDefinitionInventoryLocalServiceUtil;
import com.liferay.commerce.service.CommerceAddressLocalServiceUtil;
import com.liferay.commerce.service.CommerceCountryLocalServiceUtil;
import com.liferay.commerce.service.CommerceOrderItemLocalServiceUtil;
import com.liferay.commerce.service.CommerceOrderLocalServiceUtil;
import com.liferay.commerce.service.CommerceRegionLocalServiceUtil;
import com.liferay.commerce.service.CommerceShippingMethodLocalServiceUtil;
import com.liferay.commerce.shipping.engine.fixed.model.CommerceShippingFixedOption;
import com.liferay.commerce.shipping.engine.fixed.service.CommerceShippingFixedOptionLocalServiceUtil;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.test.util.RandomTestUtil;
import com.liferay.portal.kernel.test.util.ServiceContextTestUtil;

import java.math.BigDecimal;

import java.util.Collections;

/**
 * @author Andrea Di Giorgi
 * @author Luca Pellizzon
 */
public class CommerceTestUtil {

	public static CommerceOrder addB2BCommerceOrder(
			long groupId, long userId, long commerceAccountId,
			long commerceCurrencyId)
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

		long commerceChannelGroupId =
			CommerceChannelLocalServiceUtil.
				getCommerceChannelGroupIdBySiteGroupId(groupId);

		return CommerceOrderLocalServiceUtil.addCommerceOrder(
			userId, commerceChannelGroupId, commerceAccountId,
			commerceCurrencyId);
	}

	public static CommerceOrder addB2CCommerceOrder(
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

		CommerceAccount commerceAccount =
			CommerceAccountLocalServiceUtil.addPersonalCommerceAccount(
				userId, StringPool.BLANK, StringPool.BLANK, serviceContext);

		long commerceChannelGroupId =
			CommerceChannelLocalServiceUtil.
				getCommerceChannelGroupIdBySiteGroupId(groupId);

		return CommerceOrderLocalServiceUtil.addCommerceOrder(
			userId, commerceChannelGroupId,
			commerceAccount.getCommerceAccountId(), commerceCurrencyId);
	}

	public static CPDefinitionInventory addBackOrderCPDefinitionInventory(
			CPDefinition cpDefinition)
		throws PortalException {

		ServiceContext serviceContext =
			ServiceContextTestUtil.getServiceContext(cpDefinition.getGroupId());

		return CPDefinitionInventoryLocalServiceUtil.addCPDefinitionInventory(
			cpDefinition.getCPDefinitionId(), StringPool.BLANK,
			StringPool.BLANK, false, false, 0, true, 1, 10000, StringPool.BLANK,
			0, serviceContext);
	}

	public static CommerceOrder addCheckoutDetailsToUserOrder(
			CommerceOrder commerceOrder, long userId)
		throws Exception {

		long groupId = commerceOrder.getGroupId();

		CPInstance cpInstance = CPTestUtil.addCPInstance(groupId);

		BigDecimal price = BigDecimal.valueOf(RandomTestUtil.randomDouble());

		cpInstance.setPrice(price);

		CPInstanceLocalServiceUtil.updateCPInstance(cpInstance);

		CommerceInventoryWarehouse commerceInventoryWarehouse =
			CommerceInventoryTestUtil.addCommerceInventoryWarehouse(groupId);

		CommerceInventoryTestUtil.addCommerceInventoryWarehouseItem(
			userId, commerceInventoryWarehouse, cpInstance.getSku(), 10);

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

		CommercePaymentMethodGroupRel commercePaymentMethodGroupRel =
			addCommercePaymentMethodGroupRel(groupId);

		commerceOrder.setCommercePaymentMethodKey(
			commercePaymentMethodGroupRel.getEngineKey());

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
			commerceOrder);

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

	public static CommercePaymentMethodGroupRel
			addCommercePaymentMethodGroupRel(long groupId)
		throws Exception {

		return CommercePaymentMethodGroupRelLocalServiceUtil.
			addCommercePaymentMethodGroupRel(
				RandomTestUtil.randomLocaleStringMap(),
				RandomTestUtil.randomLocaleStringMap(), null, "money-order",
				Collections.<String, String>emptyMap(), 1, true,
				ServiceContextTestUtil.getServiceContext(groupId));
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

		return CommerceShippingMethodLocalServiceUtil.addCommerceShippingMethod(
			RandomTestUtil.randomLocaleStringMap(),
			RandomTestUtil.randomLocaleStringMap(), null, "fixedPrice", 1, true,
			ServiceContextTestUtil.getServiceContext(groupId));
	}

	public static CommerceAddress addUserCommerceAddress(
			long groupId, long userId)
		throws Exception {

		ServiceContext serviceContext =
			ServiceContextTestUtil.getServiceContext(groupId);

		CommerceCountry commerceCountry = _setUpCountry(serviceContext);

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

	public static CommerceOrder checkoutOrder(CommerceOrder commerceOrder)
		throws Exception {

		ServiceContext serviceContext =
			ServiceContextTestUtil.getServiceContext(
				commerceOrder.getGroupId());

		CommerceContext commerceContext = new TestCommerceContext(
			commerceOrder.getCommerceCurrency(), null,
			serviceContext.getScopeGroup(), null, commerceOrder);

		return CommerceOrderLocalServiceUtil.checkoutCommerceOrder(
			commerceOrder.getCommerceOrderId(), commerceContext,
			serviceContext);
	}

	private static CommerceCountry _setUpCountry(ServiceContext serviceContext)
		throws PortalException {

		CommerceCountry commerceCountry =
			CommerceCountryLocalServiceUtil.fetchCommerceCountry(
				serviceContext.getCompanyId(), 000);

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