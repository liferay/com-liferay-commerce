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

package com.liferay.headless.commerce.admin.order.internal.dto.v1_0.converter;

import com.liferay.commerce.account.model.CommerceAccount;
import com.liferay.commerce.currency.model.CommerceCurrency;
import com.liferay.commerce.model.CommerceOrder;
import com.liferay.commerce.model.CommerceOrderItem;
import com.liferay.commerce.model.CommerceShippingMethod;
import com.liferay.commerce.service.CommerceOrderService;
import com.liferay.expando.kernel.model.ExpandoBridge;
import com.liferay.headless.commerce.admin.order.dto.v1_0.BillingAddress;
import com.liferay.headless.commerce.admin.order.dto.v1_0.Order;
import com.liferay.headless.commerce.admin.order.dto.v1_0.OrderItem;
import com.liferay.headless.commerce.admin.order.dto.v1_0.ShippingAddress;
import com.liferay.headless.commerce.core.dto.v1_0.converter.DTOConverter;
import com.liferay.headless.commerce.core.dto.v1_0.converter.DTOConverterContext;
import com.liferay.headless.commerce.core.dto.v1_0.converter.DTOConverterRegistry;
import com.liferay.headless.commerce.core.dto.v1_0.converter.DefaultDTOConverterContext;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.stream.Stream;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Alessio Antonio Rendina
 */
@Component(
	property = "model.class.name=com.liferay.commerce.model.CommerceOrder",
	service = {DTOConverter.class, OrderDTOConverter.class}
)
public class OrderDTOConverter implements DTOConverter {

	@Override
	public String getContentType() {
		return Order.class.getSimpleName();
	}

	public Order toDTO(DTOConverterContext dtoConverterContext)
		throws Exception {

		CommerceOrder commerceOrder = _commerceOrderService.getCommerceOrder(
			dtoConverterContext.getResourcePrimKey());

		CommerceAccount commerceAccount = commerceOrder.getCommerceAccount();
		CommerceCurrency commerceCurrency = commerceOrder.getCommerceCurrency();
		CommerceShippingMethod commerceShippingMethod =
			commerceOrder.getCommerceShippingMethod();
		ExpandoBridge expandoBridge = commerceOrder.getExpandoBridge();

		return new Order() {
			{
				accountExternalReferenceCode =
					commerceAccount.getExternalReferenceCode();
				accountId = commerceOrder.getCommerceAccountId();
				advanceStatus = commerceOrder.getAdvanceStatus();
				billingAddress = _getBillingAddress(
					dtoConverterContext.getLocale(),
					commerceOrder.getBillingAddressId());
				billingAddressId = commerceOrder.getBillingAddressId();
				couponCode = commerceOrder.getCouponCode();
				currencyCode = commerceCurrency.getName(
					dtoConverterContext.getLocale());
				customFields = expandoBridge.getAttributes();
				externalReferenceCode =
					commerceOrder.getExternalReferenceCode();
				id = commerceOrder.getCommerceAccountId();
				items = _getOrderItems(commerceOrder, dtoConverterContext);
				lastPriceUpdateDate = commerceOrder.getLastPriceUpdateDate();
				orderStatus = commerceOrder.getOrderStatus();
				paymentMethod = commerceOrder.getCommercePaymentMethodKey();
				paymentStatus = commerceOrder.getPaymentStatus();
				printedNote = commerceOrder.getPrintedNote();
				purchaseOrderNumber = commerceOrder.getPurchaseOrderNumber();
				requestedDeliveryDate =
					commerceOrder.getRequestedDeliveryDate();
				shippingAddress = _getShippingAddress(
					dtoConverterContext.getLocale(),
					commerceOrder.getShippingAddressId());
				shippingAddressId = commerceOrder.getShippingAddressId();
				shippingAmount = commerceOrder.getShippingAmount();
				shippingDiscountAmount =
					commerceOrder.getShippingDiscountAmount();
				shippingDiscountPercentageLevel1 =
					commerceOrder.getShippingDiscountPercentageLevel1();
				shippingDiscountPercentageLevel2 =
					commerceOrder.getShippingDiscountPercentageLevel2();
				shippingDiscountPercentageLevel3 =
					commerceOrder.getShippingDiscountPercentageLevel3();
				shippingDiscountPercentageLevel4 =
					commerceOrder.getShippingDiscountPercentageLevel4();
				shippingMethod = _getShippingMethodEngineKey(
					commerceShippingMethod);
				shippingOption = commerceOrder.getShippingOptionName();
				subtotal = commerceOrder.getSubtotal();
				subtotalDiscountAmount =
					commerceOrder.getSubtotalDiscountAmount();
				subtotalDiscountPercentageLevel1 =
					commerceOrder.getSubtotalDiscountPercentageLevel1();
				subtotalDiscountPercentageLevel2 =
					commerceOrder.getSubtotalDiscountPercentageLevel2();
				subtotalDiscountPercentageLevel3 =
					commerceOrder.getSubtotalDiscountPercentageLevel3();
				subtotalDiscountPercentageLevel4 =
					commerceOrder.getSubtotalDiscountPercentageLevel4();
				taxAmount = commerceOrder.getTaxAmount();
				total = commerceOrder.getTotal();
				totalDiscountAmount = commerceOrder.getTotalDiscountAmount();
				totalDiscountPercentageLevel1 =
					commerceOrder.getTotalDiscountPercentageLevel1();
				totalDiscountPercentageLevel2 =
					commerceOrder.getTotalDiscountPercentageLevel2();
				totalDiscountPercentageLevel3 =
					commerceOrder.getTotalDiscountPercentageLevel3();
				totalDiscountPercentageLevel4 =
					commerceOrder.getTotalDiscountPercentageLevel4();
				transactionId = commerceOrder.getTransactionId();
			}
		};
	}

	private BillingAddress _getBillingAddress(
			Locale locale, long billingAddressId)
		throws Exception {

		if (billingAddressId <= 0) {
			return new BillingAddress();
		}

		DTOConverter billingAddressDTOConverter =
			_dtoConverterRegistry.getDTOConverter("BillingAddress");

		return (BillingAddress)billingAddressDTOConverter.toDTO(
			new DefaultDTOConverterContext(locale, billingAddressId));
	}

	private OrderItem[] _getOrderItems(
			CommerceOrder commerceOrder,
			DTOConverterContext dtoConverterContext)
		throws Exception {

		List<OrderItem> orderItems = new ArrayList<>();

		DTOConverter orderItemDTOConverter =
			_dtoConverterRegistry.getDTOConverter(
				CommerceOrderItem.class.getName());

		for (CommerceOrderItem commerceOrderItem :
				commerceOrder.getCommerceOrderItems()) {

			orderItems.add(
				(OrderItem)orderItemDTOConverter.toDTO(
					new DefaultDTOConverterContext(
						dtoConverterContext.getLocale(),
						commerceOrderItem.getCommerceOrderItemId())));
		}

		Stream<OrderItem> stream = orderItems.stream();

		return stream.toArray(OrderItem[]::new);
	}

	private ShippingAddress _getShippingAddress(
			Locale locale, long shippingAddressId)
		throws Exception {

		if (shippingAddressId <= 0) {
			return new ShippingAddress();
		}

		DTOConverter shippingAddressDTOConverter =
			_dtoConverterRegistry.getDTOConverter("ShippingAddress");

		return (ShippingAddress)shippingAddressDTOConverter.toDTO(
			new DefaultDTOConverterContext(locale, shippingAddressId));
	}

	private String _getShippingMethodEngineKey(
		CommerceShippingMethod commerceShippingMethod) {

		if (commerceShippingMethod == null) {
			return null;
		}

		return commerceShippingMethod.getEngineKey();
	}

	@Reference
	private CommerceOrderService _commerceOrderService;

	@Reference
	private DTOConverterRegistry _dtoConverterRegistry;

}