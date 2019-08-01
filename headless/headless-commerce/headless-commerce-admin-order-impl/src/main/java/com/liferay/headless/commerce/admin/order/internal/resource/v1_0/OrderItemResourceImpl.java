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

package com.liferay.headless.commerce.admin.order.internal.resource.v1_0;

import com.liferay.commerce.context.CommerceContextFactory;
import com.liferay.commerce.exception.NoSuchOrderException;
import com.liferay.commerce.exception.NoSuchOrderItemException;
import com.liferay.commerce.model.CommerceOrder;
import com.liferay.commerce.model.CommerceOrderItem;
import com.liferay.commerce.product.service.CPInstanceService;
import com.liferay.commerce.service.CommerceOrderItemService;
import com.liferay.commerce.service.CommerceOrderService;
import com.liferay.headless.commerce.admin.order.dto.v1_0.OrderItem;
import com.liferay.headless.commerce.admin.order.internal.util.v1_0.OrderItemUtil;
import com.liferay.headless.commerce.admin.order.resource.v1_0.OrderItemResource;
import com.liferay.headless.commerce.core.dto.v1_0.converter.DTOConverter;
import com.liferay.headless.commerce.core.dto.v1_0.converter.DTOConverterRegistry;
import com.liferay.headless.commerce.core.dto.v1_0.converter.DefaultDTOConverterContext;
import com.liferay.headless.commerce.core.util.ExpandoUtil;
import com.liferay.headless.commerce.core.util.ServiceContextHelper;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.vulcan.pagination.Page;
import com.liferay.portal.vulcan.pagination.Pagination;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.osgi.service.component.annotations.ServiceScope;

/**
 * @author Alessio Antonio Rendina
 */
@Component(
	properties = "OSGI-INF/liferay/rest/v1_0/order-item.properties",
	scope = ServiceScope.PROTOTYPE, service = OrderItemResource.class
)
public class OrderItemResourceImpl extends BaseOrderItemResourceImpl {

	@Override
	public Response deleteOrderItem(Long id) throws Exception {
		CommerceOrderItem commerceOrderItem =
			_commerceOrderItemService.getCommerceOrderItem(id);

		CommerceOrder commerceOrder = _commerceOrderService.getCommerceOrder(
			commerceOrderItem.getCommerceOrderId());

		_commerceOrderItemService.deleteCommerceOrderItem(
			commerceOrderItem.getCommerceOrderItemId(),
			_commerceContextFactory.create(
				contextCompany.getCompanyId(), commerceOrder.getGroupId(),
				_user.getUserId(), commerceOrder.getCommerceOrderId(),
				commerceOrder.getCommerceAccountId()));

		Response.ResponseBuilder responseBuilder = Response.ok();

		return responseBuilder.build();
	}

	@Override
	public Response deleteOrderItemByExternalReferenceCode(
			String externalReferenceCode)
		throws Exception {

		CommerceOrderItem commerceOrderItem =
			_commerceOrderItemService.fetchByExternalReferenceCode(
				contextCompany.getCompanyId(), externalReferenceCode);

		if (commerceOrderItem == null) {
			throw new NoSuchOrderItemException(
				"Unable to find OrderItem with externalReferenceCode: " +
					externalReferenceCode);
		}

		CommerceOrder commerceOrder = _commerceOrderService.getCommerceOrder(
			commerceOrderItem.getCommerceOrderId());

		_commerceOrderItemService.deleteCommerceOrderItem(
			commerceOrderItem.getCommerceOrderItemId(),
			_commerceContextFactory.create(
				contextCompany.getCompanyId(), commerceOrder.getGroupId(),
				_user.getUserId(), commerceOrder.getCommerceOrderId(),
				commerceOrder.getCommerceAccountId()));

		Response.ResponseBuilder responseBuilder = Response.ok();

		return responseBuilder.build();
	}

	@Override
	public Page<OrderItem> getOrderByExternalReferenceCodeOrderItemsPage(
			String externalReferenceCode, Pagination pagination)
		throws Exception {

		CommerceOrder commerceOrder =
			_commerceOrderService.fetchByExternalReferenceCode(
				contextCompany.getCompanyId(), externalReferenceCode);

		if (commerceOrder == null) {
			throw new NoSuchOrderException(
				"Unable to find Order with externalReferenceCode: " +
					externalReferenceCode);
		}

		List<CommerceOrderItem> commerceOrderItems =
			_commerceOrderItemService.getCommerceOrderItems(
				commerceOrder.getCommerceOrderId(),
				pagination.getStartPosition(), pagination.getEndPosition());

		int totalItems = _commerceOrderItemService.getCommerceOrderItemsCount(
			commerceOrder.getCommerceOrderId());

		return Page.of(
			_toOrderItems(commerceOrderItems), pagination, totalItems);
	}

	@Override
	public Page<OrderItem> getOrderIdOrderItemsPage(
			Long id, Pagination pagination)
		throws Exception {

		List<CommerceOrderItem> commerceOrderItems =
			_commerceOrderItemService.getCommerceOrderItems(
				id, pagination.getStartPosition(), pagination.getEndPosition());

		int totalItems = _commerceOrderItemService.getCommerceOrderItemsCount(
			id);

		return Page.of(
			_toOrderItems(commerceOrderItems), pagination, totalItems);
	}

	@Override
	public OrderItem getOrderItem(Long id) throws Exception {
		DTOConverter orderItemDTOConverter =
			_dtoConverterRegistry.getDTOConverter(
				CommerceOrderItem.class.getName());

		return (OrderItem)orderItemDTOConverter.toDTO(
			new DefaultDTOConverterContext(
				contextAcceptLanguage.getPreferredLocale(), id));
	}

	@Override
	public OrderItem getOrderItemByExternalReferenceCode(
			String externalReferenceCode)
		throws Exception {

		CommerceOrderItem commerceOrderItem =
			_commerceOrderItemService.fetchByExternalReferenceCode(
				contextCompany.getCompanyId(), externalReferenceCode);

		if (commerceOrderItem == null) {
			throw new NoSuchOrderItemException(
				"Unable to find OrderItem with externalReferenceCode: " +
					externalReferenceCode);
		}

		DTOConverter orderItemDTOConverter =
			_dtoConverterRegistry.getDTOConverter(
				CommerceOrderItem.class.getName());

		return (OrderItem)orderItemDTOConverter.toDTO(
			new DefaultDTOConverterContext(
				contextAcceptLanguage.getPreferredLocale(),
				commerceOrderItem.getCommerceOrderItemId()));
	}

	@Override
	public Response patchOrderItem(Long id, OrderItem orderItem)
		throws Exception {

		_updateOrderItem(
			_commerceOrderItemService.getCommerceOrderItem(id), orderItem);

		Response.ResponseBuilder responseBuilder = Response.ok();

		return responseBuilder.build();
	}

	@Override
	public Response patchOrderItemByExternalReferenceCode(
			String externalReferenceCode, OrderItem orderItem)
		throws Exception {

		CommerceOrderItem commerceOrderItem =
			_commerceOrderItemService.fetchByExternalReferenceCode(
				contextCompany.getCompanyId(), externalReferenceCode);

		if (commerceOrderItem == null) {
			throw new NoSuchOrderItemException(
				"Unable to find OrderItem with externalReferenceCode: " +
					externalReferenceCode);
		}

		_updateOrderItem(commerceOrderItem, orderItem);

		Response.ResponseBuilder responseBuilder = Response.ok();

		return responseBuilder.build();
	}

	@Override
	public OrderItem postOrderByExternalReferenceCodeOrderItem(
			String externalReferenceCode, OrderItem orderItem)
		throws Exception {

		CommerceOrder commerceOrder =
			_commerceOrderService.fetchByExternalReferenceCode(
				contextCompany.getCompanyId(), externalReferenceCode);

		if (commerceOrder == null) {
			throw new NoSuchOrderException(
				"Unable to find Order with externalReferenceCode: " +
					externalReferenceCode);
		}

		return _upsertOrderItem(commerceOrder, orderItem);
	}

	@Override
	public OrderItem postOrderIdOrderItem(Long id, OrderItem orderItem)
		throws Exception {

		return _upsertOrderItem(
			_commerceOrderService.getCommerceOrder(id), orderItem);
	}

	private List<OrderItem> _toOrderItems(
			List<CommerceOrderItem> commerceOrderItems)
		throws Exception {

		List<OrderItem> orderItems = new ArrayList<>();

		DTOConverter orderItemDTOConverter =
			_dtoConverterRegistry.getDTOConverter(
				CommerceOrderItem.class.getName());

		for (CommerceOrderItem commerceOrderItem : commerceOrderItems) {
			orderItems.add(
				(OrderItem)orderItemDTOConverter.toDTO(
					new DefaultDTOConverterContext(
						contextAcceptLanguage.getPreferredLocale(),
						commerceOrderItem.getCommerceOrderItemId())));
		}

		return orderItems;
	}

	private OrderItem _updateOrderItem(
			CommerceOrderItem commerceOrderItem, OrderItem orderItem)
		throws Exception {

		CommerceOrder commerceOrder = _commerceOrderService.getCommerceOrder(
			commerceOrderItem.getCommerceOrderId());

		commerceOrderItem = _commerceOrderItemService.updateCommerceOrderItem(
			commerceOrderItem.getCommerceOrderItemId(),
			GetterUtil.get(
				orderItem.getQuantity(), commerceOrderItem.getQuantity()),
			_commerceContextFactory.create(
				contextCompany.getCompanyId(), commerceOrder.getGroupId(),
				_user.getUserId(), commerceOrder.getCommerceOrderId(),
				commerceOrder.getCommerceAccountId()),
			_serviceContextHelper.getServiceContext(
				commerceOrderItem.getGroupId()));

		// Expando

		Map<String, ?> customFields = orderItem.getCustomFields();

		if ((customFields != null) && !customFields.isEmpty()) {
			ExpandoUtil.updateExpando(
				contextCompany.getCompanyId(), CommerceOrderItem.class,
				commerceOrderItem.getPrimaryKey(), customFields);
		}

		DTOConverter orderItemDTOConverter =
			_dtoConverterRegistry.getDTOConverter(
				CommerceOrderItem.class.getName());

		return (OrderItem)orderItemDTOConverter.toDTO(
			new DefaultDTOConverterContext(
				contextAcceptLanguage.getPreferredLocale(),
				commerceOrderItem.getCommerceOrderItemId()));
	}

	private OrderItem _upsertOrderItem(
			CommerceOrder commerceOrder, OrderItem orderItem)
		throws Exception {

		CommerceOrderItem commerceOrderItem =
			OrderItemUtil.upsertCommerceOrderItem(
				_cpInstanceService, _commerceOrderItemService, orderItem,
				commerceOrder,
				_commerceContextFactory.create(
					contextCompany.getCompanyId(), commerceOrder.getGroupId(),
					_user.getUserId(), commerceOrder.getCommerceOrderId(),
					commerceOrder.getCommerceAccountId()),
				_serviceContextHelper.getServiceContext(
					commerceOrder.getGroupId()));

		// Expando

		Map<String, ?> customFields = orderItem.getCustomFields();

		if ((customFields != null) && !customFields.isEmpty()) {
			ExpandoUtil.updateExpando(
				contextCompany.getCompanyId(), CommerceOrderItem.class,
				commerceOrderItem.getPrimaryKey(), customFields);
		}

		DTOConverter orderItemDTOConverter =
			_dtoConverterRegistry.getDTOConverter(
				CommerceOrderItem.class.getName());

		return (OrderItem)orderItemDTOConverter.toDTO(
			new DefaultDTOConverterContext(
				contextAcceptLanguage.getPreferredLocale(),
				commerceOrderItem.getCommerceOrderItemId()));
	}

	@Reference
	private CommerceContextFactory _commerceContextFactory;

	@Reference
	private CommerceOrderItemService _commerceOrderItemService;

	@Reference
	private CommerceOrderService _commerceOrderService;

	@Reference
	private CPInstanceService _cpInstanceService;

	@Reference
	private DTOConverterRegistry _dtoConverterRegistry;

	@Reference
	private ServiceContextHelper _serviceContextHelper;

	@Context
	private User _user;

}