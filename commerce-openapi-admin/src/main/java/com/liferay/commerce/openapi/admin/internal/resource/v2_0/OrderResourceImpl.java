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

package com.liferay.commerce.openapi.admin.internal.resource.v2_0;

import com.liferay.commerce.context.CommerceContext;
import com.liferay.commerce.model.CommerceAddress;
import com.liferay.commerce.model.CommerceOrder;
import com.liferay.commerce.openapi.admin.internal.mapper.v2_0.DTOMapper;
import com.liferay.commerce.openapi.admin.internal.resource.util.v2_0.AddressHelper;
import com.liferay.commerce.openapi.admin.internal.resource.util.v2_0.OrderHelper;
import com.liferay.commerce.openapi.admin.internal.resource.util.v2_0.OrderItemHelper;
import com.liferay.commerce.openapi.admin.internal.resource.util.v2_0.OrderNoteHelper;
import com.liferay.commerce.openapi.admin.model.v2_0.AddressDTO;
import com.liferay.commerce.openapi.admin.model.v2_0.OrderDTO;
import com.liferay.commerce.openapi.admin.model.v2_0.OrderItemDTO;
import com.liferay.commerce.openapi.admin.model.v2_0.OrderNoteDTO;
import com.liferay.commerce.openapi.admin.resource.v2_0.OrderResource;
import com.liferay.commerce.openapi.core.annotation.Status;
import com.liferay.commerce.openapi.core.context.Language;
import com.liferay.commerce.openapi.core.context.Pagination;
import com.liferay.commerce.openapi.core.model.CollectionDTO;
import com.liferay.oauth2.provider.scope.RequiresScope;
import com.liferay.portal.kernel.model.Company;

import javax.annotation.Generated;

import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.osgi.service.component.annotations.ServiceScope;
import org.osgi.service.jaxrs.whiteboard.JaxrsWhiteboardConstants;

/**
 * @author Alessio Antonio Rendina
 */
@Component(
	property = {
		JaxrsWhiteboardConstants.JAX_RS_APPLICATION_SELECT + "=(osgi.jaxrs.name=CommerceOpenApiAdmin.Rest)",
		JaxrsWhiteboardConstants.JAX_RS_RESOURCE + "=true", "api.version=v2.0"
	},
	scope = ServiceScope.PROTOTYPE, service = OrderResource.class
)
@Generated(value = "OSGiRESTModuleGenerator")
public class OrderResourceImpl implements OrderResource {

	@Override
	@RequiresScope("CommerceOpenApiAdmin.write")
	public Response deleteOrder(String id) throws Exception {
		_orderHelper.deleteOrder(id, _company);

		Response.ResponseBuilder responseBuilder = Response.noContent();

		return responseBuilder.build();
	}

	@Override
	@RequiresScope("CommerceOpenApiAdmin.write")
	public Response deleteOrderItem(String id, String orderItemId)
		throws Exception {

		_orderItemHelper.deleteOrderItem(
			orderItemId, _company, _commerceContext);

		Response.ResponseBuilder responseBuilder = Response.noContent();

		return responseBuilder.build();
	}

	@Override
	@RequiresScope("CommerceOpenApiAdmin.write")
	public Response deleteOrderNote(String id, String orderItemId)
		throws Exception {

		_orderNoteHelper.deleteOrderNote(id, _company);

		Response.ResponseBuilder responseBuilder = Response.noContent();

		return responseBuilder.build();
	}

	@Override
	@RequiresScope("CommerceOpenApiAdmin.read")
	public AddressDTO getBillingAddress(String id) throws Exception {
		CommerceOrder commerceOrder = _orderHelper.getOrderById(id, _company);

		CommerceAddress commerceAddress = _addressHelper.getAddressById(
			String.valueOf(commerceOrder.getBillingAddressId()));

		return _dtoMapper.modelToDTO(commerceAddress);
	}

	@Override
	@RequiresScope("CommerceOpenApiAdmin.read")
	public OrderDTO getOrder(String id, Language language) throws Exception {
		return _orderHelper.getOrder(id, language, _company);
	}

	@Override
	@RequiresScope("CommerceOpenApiAdmin.read")
	public OrderItemDTO getOrderItem(String id, String orderItemId)
		throws Exception {

		return _orderItemHelper.getOrderItem(orderItemId, _company);
	}

	@Override
	@RequiresScope("CommerceOpenApiAdmin.read")
	public CollectionDTO<OrderItemDTO> getOrderItems(
			String id, Pagination pagination)
		throws Exception {

		return _orderItemHelper.getOrderItems(id, pagination, _company);
	}

	@Override
	@RequiresScope("CommerceOpenApiAdmin.read")
	public OrderNoteDTO getOrderNote(String id, String orderItemId)
		throws Exception {

		return _orderNoteHelper.getOrderNote(orderItemId, _company);
	}

	@Override
	@RequiresScope("CommerceOpenApiAdmin.read")
	public CollectionDTO<OrderNoteDTO> getOrderNotes(
			String id, Pagination pagination)
		throws Exception {

		return _orderNoteHelper.getOrderNotes(id, pagination, _company);
	}

	@Override
	@RequiresScope("CommerceOpenApiAdmin.read")
	public CollectionDTO<OrderDTO> getOrders(
			Long groupId, Language language, Pagination pagination)
		throws Exception {

		return _orderHelper.getOrders(
			null, groupId, language, pagination, _company);
	}

	@Override
	@RequiresScope("CommerceOpenApiAdmin.read")
	public AddressDTO getShippingAddress(String id) throws Exception {
		CommerceOrder commerceOrder = _orderHelper.getOrderById(id, _company);

		CommerceAddress commerceAddress = _addressHelper.getAddressById(
			String.valueOf(commerceOrder.getShippingAddressId()));

		return _dtoMapper.modelToDTO(commerceAddress);
	}

	@Override
	@RequiresScope("CommerceOpenApiAdmin.write")
	public OrderDTO updateBillingAddress(
			String id, AddressDTO addressDTO, Language language)
		throws Exception {

		return _orderHelper.updateOrderBillingAddress(
			id, addressDTO, language, _company);
	}

	@Override
	@RequiresScope("CommerceOpenApiAdmin.write")
	public Response updateOrder(String id, OrderDTO orderDTO, Language language)
		throws Exception {

		_orderHelper.updateOrder(
			id, orderDTO, language, _company, _commerceContext);

		Response.ResponseBuilder responseBuilder = Response.noContent();

		return responseBuilder.build();
	}

	@Override
	@RequiresScope("CommerceOpenApiAdmin.write")
	public OrderItemDTO updateOrderItem(
			String id, String orderItemId, OrderItemDTO orderItemDTO)
		throws Exception {

		return _orderItemHelper.updateOrderItem(
			orderItemId, orderItemDTO, _company, _commerceContext);
	}

	@Override
	@RequiresScope("CommerceOpenApiAdmin.write")
	public OrderNoteDTO updateOrderNote(
			String id, String orderItemId, OrderNoteDTO orderNoteDTO)
		throws Exception {

		return _orderNoteHelper.updateOrderNote(
			orderItemId, orderNoteDTO, _company);
	}

	@Override
	@RequiresScope("CommerceOpenApiAdmin.write")
	public OrderDTO updateShippingAddress(
			String id, AddressDTO addressDTO, Language language)
		throws Exception {

		return _orderHelper.updateOrderShippingAddress(
			id, addressDTO, language, _company);
	}

	@Override
	@RequiresScope("CommerceOpenApiAdmin.write")
	@Status(Response.Status.CREATED)
	public OrderItemDTO upsertOrderItem(String id, OrderItemDTO orderItemDTO)
		throws Exception {

		return _orderItemHelper.upsertOrderItem(
			id, orderItemDTO, _company, _commerceContext);
	}

	@Override
	@RequiresScope("CommerceOpenApiAdmin.write")
	@Status(Response.Status.CREATED)
	public OrderNoteDTO upsertOrderNote(String id, OrderNoteDTO orderNoteDTO)
		throws Exception {

		return _orderNoteHelper.upsertOrder(id, orderNoteDTO, _company);
	}

	@Reference
	private AddressHelper _addressHelper;

	@Context
	private CommerceContext _commerceContext;

	@Context
	private Company _company;

	@Reference
	private DTOMapper _dtoMapper;

	@Reference
	private OrderHelper _orderHelper;

	@Reference
	private OrderItemHelper _orderItemHelper;

	@Reference
	private OrderNoteHelper _orderNoteHelper;

}