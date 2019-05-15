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

import com.liferay.commerce.currency.model.CommerceCurrency;
import com.liferay.commerce.model.CommerceOrder;
import com.liferay.commerce.model.CommerceShippingMethod;
import com.liferay.commerce.service.CommerceOrderService;
import com.liferay.headless.commerce.admin.order.dto.v1_0.BillingAddress;
import com.liferay.headless.commerce.admin.order.dto.v1_0.Order;
import com.liferay.headless.commerce.admin.order.dto.v1_0.ShippingAddress;
import com.liferay.headless.commerce.core.dto.v1_0.converter.DTOConverter;
import com.liferay.headless.commerce.core.dto.v1_0.converter.DTOConverterContext;
import com.liferay.headless.commerce.core.dto.v1_0.converter.DTOConverterRegistry;
import com.liferay.headless.commerce.core.dto.v1_0.converter.DefaultDTOConverterContext;

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

		CommerceCurrency commerceCurrency = commerceOrder.getCommerceCurrency();
		CommerceShippingMethod commerceShippingMethod =
			commerceOrder.getCommerceShippingMethod();

		DTOConverter billingAddressDTOConverter =
			_dtoConverterRegistry.getDTOConverter("BillingAddress");
		DTOConverter shippingAddressDTOConverter =
			_dtoConverterRegistry.getDTOConverter("ShippingAddress");

		return new Order() {
			{
				advanceStatus = commerceOrder.getAdvanceStatus();
				billingAddress =
					(BillingAddress)billingAddressDTOConverter.toDTO(
						new DefaultDTOConverterContext(
							dtoConverterContext.getLocale(),
							commerceOrder.getBillingAddressId()));
				billingAddressId = commerceOrder.getBillingAddressId();
				commerceAccountId = commerceOrder.getCommerceAccountId();
				currency = commerceCurrency.getName(
					dtoConverterContext.getLocale());
				externalReferenceCode =
					commerceOrder.getExternalReferenceCode();
				id = commerceOrder.getCommerceAccountId();
				orderStatus = commerceOrder.getOrderStatus();
				paymentMethod = commerceOrder.getCommercePaymentMethodKey();
				purchaseOrderNumber = commerceOrder.getPurchaseOrderNumber();
				shippingAddress =
					(ShippingAddress)shippingAddressDTOConverter.toDTO(
						new DefaultDTOConverterContext(
							dtoConverterContext.getLocale(),
							commerceOrder.getShippingAddressId()));
				shippingAddressId = commerceOrder.getShippingAddressId();
				shippingAmount = commerceOrder.getShippingAmount();
				shippingMethod = _getShippingMethodEngineKey(
					commerceShippingMethod);
				shippingOption = commerceOrder.getShippingOptionName();
				subtotal = commerceOrder.getSubtotal();
				total = commerceOrder.getTotal();
			}
		};
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