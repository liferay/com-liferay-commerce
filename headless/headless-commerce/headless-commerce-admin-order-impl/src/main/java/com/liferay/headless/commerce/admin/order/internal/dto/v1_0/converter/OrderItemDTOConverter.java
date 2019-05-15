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

import com.liferay.commerce.model.CommerceOrderItem;
import com.liferay.commerce.service.CommerceOrderItemService;
import com.liferay.headless.commerce.admin.order.dto.v1_0.OrderItem;
import com.liferay.headless.commerce.core.dto.v1_0.converter.DTOConverter;
import com.liferay.headless.commerce.core.dto.v1_0.converter.DTOConverterContext;
import com.liferay.headless.commerce.core.util.LanguageUtils;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Alessio Antonio Rendina
 */
@Component(
	property = "model.class.name=com.liferay.commerce.model.CommerceOrderItem",
	service = {DTOConverter.class, OrderItemDTOConverter.class}
)
public class OrderItemDTOConverter implements DTOConverter {

	@Override
	public String getContentType() {
		return OrderItem.class.getSimpleName();
	}

	public OrderItem toDTO(DTOConverterContext dtoConverterContext)
		throws Exception {

		CommerceOrderItem commerceOrderItem =
			_commerceOrderItemService.getCommerceOrderItem(
				dtoConverterContext.getResourcePrimKey());

		return new OrderItem() {
			{
				commerceOrderId = commerceOrderItem.getCommerceOrderId();
				discountAmount = commerceOrderItem.getDiscountAmount();
				externalReferenceCode =
					commerceOrderItem.getExternalReferenceCode();
				finalPrice = commerceOrderItem.getFinalPrice();
				id = commerceOrderItem.getCommerceOrderItemId();
				name = LanguageUtils.getLanguageIdMap(
					commerceOrderItem.getNameMap());
				quantity = commerceOrderItem.getQuantity();
				shippedQuantity = commerceOrderItem.getShippedQuantity();
				sku = commerceOrderItem.getSku();
				skuId = commerceOrderItem.getCPInstanceId();
				subscription = commerceOrderItem.isSubscription();
				unitPrice = commerceOrderItem.getUnitPrice();
			}
		};
	}

	@Reference
	private CommerceOrderItemService _commerceOrderItemService;

}