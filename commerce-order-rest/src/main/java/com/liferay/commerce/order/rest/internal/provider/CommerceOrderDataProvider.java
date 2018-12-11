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

package com.liferay.commerce.order.rest.internal.provider;

import com.liferay.commerce.model.CommerceOrder;
import com.liferay.commerce.order.rest.model.Order;
import com.liferay.commerce.order.rest.model.OrderList;
import com.liferay.commerce.service.CommerceOrderService;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.search.BaseModelSearchResult;

import java.util.ArrayList;
import java.util.List;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Alessio Antonio Rendina
 */
@Component(immediate = true, service = CommerceOrderDataProvider.class)
public class CommerceOrderDataProvider {

	public OrderList getOrderList(
			long companyId, long groupId, long orderOrganizationId,
			int orderStatus, String keywords, int page, int pageSize)
		throws PortalException {

		List<Order> orders = getOrders(
			companyId, groupId, orderOrganizationId, orderStatus, keywords,
			page, pageSize);

		return new OrderList(orders, orders.size());
	}

	protected List<Order> getOrders(
			long companyId, long groupId, long orderOrganizationId,
			int orderStatus, String keywords, int page, int pageSize)
		throws PortalException {

		List<Order> orders = new ArrayList<>();

		int start = (page - 1) * pageSize;
		int end = page * pageSize;

		BaseModelSearchResult<CommerceOrder> baseModelSearchResult =
			_commerceOrderService.searchCommerceOrders(
				companyId, groupId, orderOrganizationId, orderStatus, keywords,
				start, end, null);

		for (CommerceOrder commerceOrder :
				baseModelSearchResult.getBaseModels()) {

			orders.add(
				new Order(
					commerceOrder.getCommerceOrderId(),
					commerceOrder.getOrderStatus(),
					commerceOrder.getModifiedDate()));
		}

		return orders;
	}

	@Reference
	private CommerceOrderService _commerceOrderService;

}