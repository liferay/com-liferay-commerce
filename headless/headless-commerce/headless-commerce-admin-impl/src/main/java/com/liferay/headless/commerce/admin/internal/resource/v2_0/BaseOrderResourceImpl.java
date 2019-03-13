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

package com.liferay.headless.commerce.admin.internal.resource.v2_0;

import com.liferay.headless.commerce.admin.dto.v2_0.Address;
import com.liferay.headless.commerce.admin.dto.v2_0.Order;
import com.liferay.headless.commerce.admin.dto.v2_0.OrderItem;
import com.liferay.headless.commerce.admin.dto.v2_0.OrderNote;
import com.liferay.headless.commerce.admin.resource.v2_0.OrderResource;
import com.liferay.petra.function.UnsafeFunction;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.model.Company;
import com.liferay.portal.vulcan.accept.language.AcceptLanguage;
import com.liferay.portal.vulcan.util.TransformUtil;

import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.tags.Tags;

import java.net.URI;

import java.util.List;

import javax.annotation.Generated;

import javax.validation.constraints.NotNull;

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
import javax.ws.rs.core.UriBuilder;
import javax.ws.rs.core.UriInfo;

/**
 * @author Igor Beslic
 * @generated
 */
@Generated("")
@Path("/v2.0")
public abstract class BaseOrderResourceImpl implements OrderResource {

	@Override
	@DELETE
	@Path("/order/{id}")
	@Produces({"application/json", "application/xml"})
	@Tags(value = {@Tag(name = "Order")})
	public Response deleteOrder(@NotNull @PathParam("id") String id)
		throws Exception {

		Response.ResponseBuilder responseBuilder = Response.ok();

		return responseBuilder.build();
	}

	@Override
	@GET
	@Path("/order/{id}")
	@Produces({"application/json", "application/xml"})
	@Tags(value = {@Tag(name = "Order")})
	public Response getOrder(@NotNull @PathParam("id") String id)
		throws Exception {

		Response.ResponseBuilder responseBuilder = Response.ok();

		return responseBuilder.build();
	}

	@Override
	@Consumes("application/json")
	@PUT
	@Path("/order/{id}")
	@Produces({"application/json", "application/xml"})
	@Tags(value = {@Tag(name = "Order")})
	public Response updateMediaType1Order(
			@NotNull @PathParam("id") String id, Order order)
		throws Exception {

		Response.ResponseBuilder responseBuilder = Response.ok();

		return responseBuilder.build();
	}

	@Override
	@Consumes("application/xml")
	@PUT
	@Path("/order/{id}")
	@Produces({"application/json", "application/xml"})
	@Tags(value = {@Tag(name = "Order")})
	public Response updateMediaType2Order(
			@NotNull @PathParam("id") String id, Order order)
		throws Exception {

		Response.ResponseBuilder responseBuilder = Response.ok();

		return responseBuilder.build();
	}

	@Override
	@DELETE
	@Path("/order/{id}/orderItem/{orderItemId}")
	@Produces({"application/json", "application/xml"})
	@Tags(value = {@Tag(name = "Order")})
	public Response deleteOrderItem(
			@NotNull @PathParam("id") String id,
			@NotNull @PathParam("orderItemId") String orderItemId)
		throws Exception {

		Response.ResponseBuilder responseBuilder = Response.ok();

		return responseBuilder.build();
	}

	@Override
	@GET
	@Path("/order/{id}/orderItem/{orderItemId}")
	@Produces({"application/json", "application/xml"})
	@Tags(value = {@Tag(name = "Order")})
	public Response getOrderItem(
			@NotNull @PathParam("id") String id,
			@NotNull @PathParam("orderItemId") String orderItemId)
		throws Exception {

		Response.ResponseBuilder responseBuilder = Response.ok();

		return responseBuilder.build();
	}

	@Override
	@Consumes("application/json")
	@POST
	@Path("/order/{id}/orderItem/{orderItemId}")
	@Produces({"application/json", "application/xml"})
	@Tags(value = {@Tag(name = "Order")})
	public Response updateMediaType1OrderItem(
			@NotNull @PathParam("id") String id,
			@NotNull @PathParam("orderItemId") String orderItemId,
			OrderItem orderItem)
		throws Exception {

		Response.ResponseBuilder responseBuilder = Response.ok();

		return responseBuilder.build();
	}

	@Override
	@Consumes("application/xml")
	@POST
	@Path("/order/{id}/orderItem/{orderItemId}")
	@Produces({"application/json", "application/xml"})
	@Tags(value = {@Tag(name = "Order")})
	public Response updateMediaType2OrderItem(
			@NotNull @PathParam("id") String id,
			@NotNull @PathParam("orderItemId") String orderItemId,
			OrderItem orderItem)
		throws Exception {

		Response.ResponseBuilder responseBuilder = Response.ok();

		return responseBuilder.build();
	}

	@Override
	@GET
	@Path("/order/{id}/orderItem")
	@Produces({"application/json", "application/xml"})
	@Tags(value = {@Tag(name = "Order")})
	public Response getOrderItems(@NotNull @PathParam("id") String id)
		throws Exception {

		Response.ResponseBuilder responseBuilder = Response.ok();

		return responseBuilder.build();
	}

	@Override
	@Consumes("application/json")
	@POST
	@Path("/order/{id}/orderItem")
	@Produces({"application/json", "application/xml"})
	@Tags(value = {@Tag(name = "Order")})
	public Response upsertMediaType1OrderItem(
			@NotNull @PathParam("id") String id, OrderItem orderItem)
		throws Exception {

		Response.ResponseBuilder responseBuilder = Response.ok();

		return responseBuilder.build();
	}

	@Override
	@Consumes("application/xml")
	@POST
	@Path("/order/{id}/orderItem")
	@Produces({"application/json", "application/xml"})
	@Tags(value = {@Tag(name = "Order")})
	public Response upsertMediaType2OrderItem(
			@NotNull @PathParam("id") String id, OrderItem orderItem)
		throws Exception {

		Response.ResponseBuilder responseBuilder = Response.ok();

		return responseBuilder.build();
	}

	@Override
	@DELETE
	@Path("/order/{id}/orderNote/{orderNoteId}")
	@Produces({"application/json", "application/xml"})
	@Tags(value = {@Tag(name = "Order")})
	public Response deleteOrderNote(
			@NotNull @PathParam("id") String id,
			@NotNull @PathParam("orderNoteId") String orderNoteId)
		throws Exception {

		Response.ResponseBuilder responseBuilder = Response.ok();

		return responseBuilder.build();
	}

	@Override
	@GET
	@Path("/order/{id}/orderNote/{orderNoteId}")
	@Produces({"application/json", "application/xml"})
	@Tags(value = {@Tag(name = "Order")})
	public Response getOrderNote(
			@NotNull @PathParam("id") String id,
			@NotNull @PathParam("orderNoteId") String orderNoteId)
		throws Exception {

		Response.ResponseBuilder responseBuilder = Response.ok();

		return responseBuilder.build();
	}

	@Override
	@Consumes("application/json")
	@POST
	@Path("/order/{id}/orderNote/{orderNoteId}")
	@Produces({"application/json", "application/xml"})
	@Tags(value = {@Tag(name = "Order")})
	public Response updateMediaType1OrderNote(
			@NotNull @PathParam("id") String id,
			@NotNull @PathParam("orderNoteId") String orderNoteId,
			OrderNote orderNote)
		throws Exception {

		Response.ResponseBuilder responseBuilder = Response.ok();

		return responseBuilder.build();
	}

	@Override
	@Consumes("application/xml")
	@POST
	@Path("/order/{id}/orderNote/{orderNoteId}")
	@Produces({"application/json", "application/xml"})
	@Tags(value = {@Tag(name = "Order")})
	public Response updateMediaType2OrderNote(
			@NotNull @PathParam("id") String id,
			@NotNull @PathParam("orderNoteId") String orderNoteId,
			OrderNote orderNote)
		throws Exception {

		Response.ResponseBuilder responseBuilder = Response.ok();

		return responseBuilder.build();
	}

	@Override
	@GET
	@Path("/order/{id}/orderNote")
	@Produces({"application/json", "application/xml"})
	@Tags(value = {@Tag(name = "Order")})
	public Response getOrderNotes(@NotNull @PathParam("id") String id)
		throws Exception {

		Response.ResponseBuilder responseBuilder = Response.ok();

		return responseBuilder.build();
	}

	@Override
	@Consumes("application/json")
	@POST
	@Path("/order/{id}/orderNote")
	@Produces({"application/json", "application/xml"})
	@Tags(value = {@Tag(name = "Order")})
	public Response upsertMediaType1OrderNote(
			@NotNull @PathParam("id") String id, OrderNote orderNote)
		throws Exception {

		Response.ResponseBuilder responseBuilder = Response.ok();

		return responseBuilder.build();
	}

	@Override
	@Consumes("application/xml")
	@POST
	@Path("/order/{id}/orderNote")
	@Produces({"application/json", "application/xml"})
	@Tags(value = {@Tag(name = "Order")})
	public Response upsertMediaType2OrderNote(
			@NotNull @PathParam("id") String id, OrderNote orderNote)
		throws Exception {

		Response.ResponseBuilder responseBuilder = Response.ok();

		return responseBuilder.build();
	}

	@Override
	@GET
	@Path("/order/{id}/billingAddress")
	@Produces({"application/json", "application/xml"})
	@Tags(value = {@Tag(name = "Order")})
	public Response getBillingAddress(@NotNull @PathParam("id") String id)
		throws Exception {

		Response.ResponseBuilder responseBuilder = Response.ok();

		return responseBuilder.build();
	}

	@Override
	@Consumes("application/json")
	@POST
	@Path("/order/{id}/billingAddress")
	@Produces({"application/json", "application/xml"})
	@Tags(value = {@Tag(name = "Order")})
	public Response updateMediaType1BillingAddress(
			@NotNull @PathParam("id") String id, Address address)
		throws Exception {

		Response.ResponseBuilder responseBuilder = Response.ok();

		return responseBuilder.build();
	}

	@Override
	@Consumes("application/xml")
	@POST
	@Path("/order/{id}/billingAddress")
	@Produces({"application/json", "application/xml"})
	@Tags(value = {@Tag(name = "Order")})
	public Response updateMediaType2BillingAddress(
			@NotNull @PathParam("id") String id, Address address)
		throws Exception {

		Response.ResponseBuilder responseBuilder = Response.ok();

		return responseBuilder.build();
	}

	@Override
	@GET
	@Path("/order/{id}/shippingAddress")
	@Produces({"application/json", "application/xml"})
	@Tags(value = {@Tag(name = "Order")})
	public Response getShippingAddress(@NotNull @PathParam("id") String id)
		throws Exception {

		Response.ResponseBuilder responseBuilder = Response.ok();

		return responseBuilder.build();
	}

	@Override
	@Consumes("application/json")
	@POST
	@Path("/order/{id}/shippingAddress")
	@Produces({"application/json", "application/xml"})
	@Tags(value = {@Tag(name = "Order")})
	public Response updateMediaType1ShippingAddress(
			@NotNull @PathParam("id") String id, Address address)
		throws Exception {

		Response.ResponseBuilder responseBuilder = Response.ok();

		return responseBuilder.build();
	}

	@Override
	@Consumes("application/xml")
	@POST
	@Path("/order/{id}/shippingAddress")
	@Produces({"application/json", "application/xml"})
	@Tags(value = {@Tag(name = "Order")})
	public Response updateMediaType2ShippingAddress(
			@NotNull @PathParam("id") String id, Address address)
		throws Exception {

		Response.ResponseBuilder responseBuilder = Response.ok();

		return responseBuilder.build();
	}

	@Override
	@GET
	@Path("/order")
	@Produces({"application/json", "application/xml"})
	@Tags(value = {@Tag(name = "Order")})
	public Response getOrders(@NotNull @QueryParam("groupId") Long groupId)
		throws Exception {

		Response.ResponseBuilder responseBuilder = Response.ok();

		return responseBuilder.build();
	}

	public void setContextCompany(Company contextCompany) {
		this.contextCompany = contextCompany;
	}

	protected String getJAXRSLink(String methodName, Object... values) {
		String baseURIString = String.valueOf(contextUriInfo.getBaseUri());

		if (baseURIString.endsWith(StringPool.FORWARD_SLASH)) {
			baseURIString = baseURIString.substring(
				0, baseURIString.length() - 1);
		}

		URI resourceURI = UriBuilder.fromResource(
			BaseOrderResourceImpl.class
		).build();

		URI methodURI = UriBuilder.fromMethod(
			BaseOrderResourceImpl.class, methodName
		).build(
			values
		);

		return baseURIString + resourceURI.toString() + methodURI.toString();
	}

	protected void preparePatch(Order order) {
	}

	protected <T, R> List<R> transform(
		List<T> list, UnsafeFunction<T, R, Exception> unsafeFunction) {

		return TransformUtil.transform(list, unsafeFunction);
	}

	protected <T, R> R[] transform(
		T[] array, UnsafeFunction<T, R, Exception> unsafeFunction,
		Class<?> clazz) {

		return TransformUtil.transform(array, unsafeFunction, clazz);
	}

	protected <T, R> R[] transformToArray(
		List<T> list, UnsafeFunction<T, R, Exception> unsafeFunction,
		Class<?> clazz) {

		return TransformUtil.transformToArray(list, unsafeFunction, clazz);
	}

	protected <T, R> List<R> transformToList(
		T[] array, UnsafeFunction<T, R, Exception> unsafeFunction) {

		return TransformUtil.transformToList(array, unsafeFunction);
	}

	@Context
	protected AcceptLanguage contextAcceptLanguage;

	@Context
	protected Company contextCompany;

	@Context
	protected UriInfo contextUriInfo;

}