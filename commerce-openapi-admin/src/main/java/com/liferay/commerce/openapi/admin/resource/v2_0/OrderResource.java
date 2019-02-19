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

package com.liferay.commerce.openapi.admin.resource.v2_0;

import com.liferay.commerce.openapi.admin.model.v2_0.AddressDTO;
import com.liferay.commerce.openapi.admin.model.v2_0.OrderDTO;
import com.liferay.commerce.openapi.admin.model.v2_0.OrderItemDTO;
import com.liferay.commerce.openapi.admin.model.v2_0.OrderNoteDTO;
import com.liferay.commerce.openapi.core.context.Language;
import com.liferay.commerce.openapi.core.context.Pagination;
import com.liferay.commerce.openapi.core.model.CollectionDTO;

import javax.annotation.Generated;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;

/**
 * @author Igor Beslic
 */
@Generated(value = "OSGiRESTModuleGenerator")
@Path("/v2.0/order")
public interface OrderResource {

	@DELETE
	@Path("/{id}")
	public Response deleteOrder(@PathParam("id") String id) throws Exception;

	@DELETE
	@Path("/{id}/orderItem/{id2}")
	public Response deleteOrderItem(
			@PathParam("id") String id, @PathParam("id2") String id2)
		throws Exception;

	@DELETE
	@Path("/{id}/orderNote/{id2}")
	public Response deleteOrderNote(
			@PathParam("id") String id, @PathParam("id2") String id2)
		throws Exception;

	@GET
	@Path("/{id}/billingAddress")
	@Produces({"application/json", "application/xml"})
	public AddressDTO getBillingAddress(@PathParam("id") String id)
		throws Exception;

	@GET
	@Path("/{id}")
	@Produces({"application/json", "application/xml"})
	public OrderDTO getOrder(
			@PathParam("id") String id, @Context Language language)
		throws Exception;

	@GET
	@Path("/{id}/orderItem/{id2}")
	@Produces({"application/json", "application/xml"})
	public OrderItemDTO getOrderItem(
			@PathParam("id") String id, @PathParam("id2") String id2)
		throws Exception;

	@GET
	@Path("/{id}/orderItem")
	@Produces({"application/json", "application/xml"})
	public CollectionDTO<OrderItemDTO> getOrderItems(
			@PathParam("id") String id, @Context Pagination pagination)
		throws Exception;

	@GET
	@Path("/{id}/orderNote/{id2}")
	@Produces({"application/json", "application/xml"})
	public OrderNoteDTO getOrderNote(
			@PathParam("id") String id, @PathParam("id2") String id2)
		throws Exception;

	@GET
	@Path("/{id}/orderNote")
	@Produces({"application/json", "application/xml"})
	public CollectionDTO<OrderNoteDTO> getOrderNotes(
			@PathParam("id") String id, @Context Pagination pagination)
		throws Exception;

	@GET
	@Produces({"application/json", "application/xml"})
	public CollectionDTO<OrderDTO> getOrders(
			@QueryParam("groupId") Long groupId, @Context Language language,
			@Context Pagination pagination)
		throws Exception;

	@GET
	@Path("/{id}/shippingAddress")
	@Produces({"application/json", "application/xml"})
	public AddressDTO getShippingAddress(@PathParam("id") String id)
		throws Exception;

	@Consumes({"application/json", "application/xml"})
	@Path("/{id}/billingAddress")
	@POST
	@Produces({"application/json", "application/xml"})
	public OrderDTO updateBillingAddress(
			@PathParam("id") String id, AddressDTO addressDTO,
			@Context Language language)
		throws Exception;

	@Consumes({"application/json", "application/xml"})
	@Path("/{id}")
	@PUT
	public Response updateOrder(
			@PathParam("id") String id, OrderDTO orderDTO,
			@Context Language language)
		throws Exception;

	@Consumes({"application/json", "application/xml"})
	@Path("/{id}/orderItem/{id2}")
	@POST
	@Produces({"application/json", "application/xml"})
	public OrderItemDTO updateOrderItem(
			@PathParam("id") String id, @PathParam("id2") String id2,
			OrderItemDTO orderItemDTO)
		throws Exception;

	@Consumes({"application/json", "application/xml"})
	@Path("/{id}/orderNote/{id2}")
	@POST
	@Produces({"application/json", "application/xml"})
	public OrderNoteDTO updateOrderNote(
			@PathParam("id") String id, @PathParam("id2") String id2,
			OrderNoteDTO orderNoteDTO)
		throws Exception;

	@Consumes({"application/json", "application/xml"})
	@Path("/{id}/shippingAddress")
	@POST
	@Produces({"application/json", "application/xml"})
	public OrderDTO updateShippingAddress(
			@PathParam("id") String id, AddressDTO addressDTO,
			@Context Language language)
		throws Exception;

	@Consumes({"application/json", "application/xml"})
	@Path("/{id}/orderItem")
	@POST
	@Produces({"application/json", "application/xml"})
	public OrderItemDTO upsertOrderItem(
			@PathParam("id") String id, OrderItemDTO orderItemDTO)
		throws Exception;

	@Consumes({"application/json", "application/xml"})
	@Path("/{id}/orderNote")
	@POST
	@Produces({"application/json", "application/xml"})
	public OrderNoteDTO upsertOrderNote(
			@PathParam("id") String id, OrderNoteDTO orderNoteDTO)
		throws Exception;

}