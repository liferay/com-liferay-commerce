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

package com.liferay.commerce.frontend.internal.cart;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import com.liferay.commerce.constants.CommerceWebKeys;
import com.liferay.commerce.context.CommerceContext;
import com.liferay.commerce.context.CommerceContextFactory;
import com.liferay.commerce.exception.CommerceOrderValidatorException;
import com.liferay.commerce.frontend.internal.cart.model.Cart;
import com.liferay.commerce.frontend.internal.cart.model.CartItemUpdate;
import com.liferay.commerce.model.CommerceOrder;
import com.liferay.commerce.model.CommerceOrderItem;
import com.liferay.commerce.order.CommerceOrderHttpHelper;
import com.liferay.commerce.order.CommerceOrderValidatorResult;
import com.liferay.commerce.service.CommerceOrderItemService;
import com.liferay.commerce.service.CommerceOrderService;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.service.ServiceContextFactory;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.ArrayUtil;
import com.liferay.portal.kernel.util.Portal;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.WebKeys;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Marco Leo
 */
@Component(service = CommerceCartResource.class)
public class CommerceCartResource {

	@Consumes(MediaType.APPLICATION_JSON)
	@DELETE
	@Path("/cart/cart-item/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response deleteOrderItem(
		@PathParam("id") long commerceOrderItemId,
		@Context HttpServletRequest httpServletRequest) {

		Cart cart = null;

		try {
			CommerceOrderItem commerceOrderItem =
				_commerceOrderItemService.getCommerceOrderItem(
					commerceOrderItemId);

			CommerceOrder commerceOrder = commerceOrderItem.getCommerceOrder();

			CommerceContext commerceContext = _commerceContextFactory.create(
				commerceOrder.getGroupId(),
				_portal.getUserId(httpServletRequest),
				commerceOrder.getCommerceOrderId(),
				commerceOrder.getCommerceAccountId());

			httpServletRequest.setAttribute(
				CommerceWebKeys.COMMERCE_CONTEXT, commerceContext);

			ThemeDisplay themeDisplay =
				(ThemeDisplay)httpServletRequest.getAttribute(
					WebKeys.THEME_DISPLAY);

			themeDisplay.setScopeGroupId(commerceOrder.getGroupId());

			ServiceContext serviceContext = ServiceContextFactory.getInstance(
				CommerceOrderItem.class.getName(), httpServletRequest);

			serviceContext.setScopeGroupId(commerceOrder.getGroupId());

			_commerceOrderItemService.deleteCommerceOrderItem(
				commerceOrderItem.getCommerceOrderItemId());

			cart = _commerceCartResourceUtil.getCart(
				commerceOrder.getCommerceOrderId(), themeDisplay.getLocale(),
				commerceContext);
		}
		catch (Exception e) {
			cart = new Cart(StringUtil.split(e.getLocalizedMessage()));
		}

		return getResponse(cart);
	}

	@Consumes(MediaType.APPLICATION_JSON)
	@Path("/cart/cart-item/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	@PUT
	public Response updateOrderItem(
		@PathParam("id") long commerceOrderItemId,
		CartItemUpdate cartItemUpdate,
		@Context HttpServletRequest httpServletRequest) {

		Cart cart = null;

		try {
			CommerceOrderItem commerceOrderItem =
				_commerceOrderItemService.getCommerceOrderItem(
					commerceOrderItemId);

			CommerceOrder commerceOrder = commerceOrderItem.getCommerceOrder();

			CommerceContext commerceContext = _commerceContextFactory.create(
				commerceOrder.getGroupId(),
				_portal.getUserId(httpServletRequest),
				commerceOrder.getCommerceOrderId(),
				commerceOrder.getCommerceAccountId());

			httpServletRequest.setAttribute(
				CommerceWebKeys.COMMERCE_CONTEXT, commerceContext);

			ThemeDisplay themeDisplay =
				(ThemeDisplay)httpServletRequest.getAttribute(
					WebKeys.THEME_DISPLAY);

			themeDisplay.setScopeGroupId(commerceOrder.getGroupId());

			ServiceContext serviceContext = ServiceContextFactory.getInstance(
				CommerceOrderItem.class.getName(), httpServletRequest);

			serviceContext.setScopeGroupId(commerceOrder.getGroupId());

			_commerceOrderItemService.updateCommerceOrderItem(
				commerceOrderItem.getCommerceOrderItemId(),
				cartItemUpdate.getQuantity(), commerceContext, serviceContext);

			cart = _commerceCartResourceUtil.getCart(
				commerceOrder.getCommerceOrderId(), themeDisplay.getLocale(),
				commerceContext);
		}
		catch (Exception e) {
			if (e instanceof CommerceOrderValidatorException) {
				cart = new Cart(
					_getCommerceOrderValidatorResultsMessages(
						(CommerceOrderValidatorException)e));
			}
			else {
				cart = new Cart(StringUtil.split(e.getLocalizedMessage()));
			}
		}

		return getResponse(cart);
	}

	@GET
	@Path("/cart/{orderId}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response updateOrderItem(
		@PathParam("orderId") long commerceOrderId,
		@Context HttpServletRequest httpServletRequest) {

		Cart cart = null;

		try {
			CommerceOrder commerceOrder =
				_commerceOrderService.getCommerceOrder(commerceOrderId);

			CommerceContext commerceContext = _commerceContextFactory.create(
				commerceOrder.getGroupId(),
				_portal.getUserId(httpServletRequest),
				commerceOrder.getCommerceOrderId(),
				commerceOrder.getCommerceAccountId());

			httpServletRequest.setAttribute(
				CommerceWebKeys.COMMERCE_CONTEXT, commerceContext);

			ThemeDisplay themeDisplay =
				(ThemeDisplay)httpServletRequest.getAttribute(
					WebKeys.THEME_DISPLAY);

			themeDisplay.setScopeGroupId(commerceOrder.getGroupId());

			ServiceContext serviceContext = ServiceContextFactory.getInstance(
				CommerceOrderItem.class.getName(), httpServletRequest);

			serviceContext.setScopeGroupId(commerceOrder.getGroupId());

			cart = _commerceCartResourceUtil.getCart(
				commerceOrderId, themeDisplay.getLocale(), commerceContext);
		}
		catch (Exception e) {
			_log.error(e, e);

			cart = new Cart(StringUtil.split(e.getLocalizedMessage()));
		}

		return getResponse(cart);
	}

	@Path("/cart-item")
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	public Response updateOrderItem(
		@FormParam("groupId") long groupId,
		@FormParam("languageId") String languageId,
		@FormParam("commerceAccountId") long commerceAccountId,
		@FormParam("quantity") int quantity,
		@FormParam("productId") long cpInstanceId,
		@FormParam("options") String options,
		@FormParam("orderId") long orderId,
		@Context HttpServletRequest httpServletRequest) {

		Cart cart = null;

		try {
			CommerceContext commerceContext = _commerceContextFactory.create(
				groupId, _portal.getUserId(httpServletRequest), orderId,
				commerceAccountId);

			httpServletRequest.setAttribute(
				CommerceWebKeys.COMMERCE_CONTEXT, commerceContext);

			ThemeDisplay themeDisplay =
				(ThemeDisplay)httpServletRequest.getAttribute(
					WebKeys.THEME_DISPLAY);

			themeDisplay.setScopeGroupId(groupId);
			themeDisplay.setLanguageId(languageId);

			CommerceOrder commerceOrder =
				_commerceOrderService.fetchCommerceOrder(orderId);

			if (commerceOrder == null) {
				commerceOrder =
					_commerceOrderHttpHelper.getCurrentCommerceOrder(
						httpServletRequest);
			}

			if (commerceOrder == null) {
				commerceOrder = _commerceOrderHttpHelper.addCommerceOrder(
					httpServletRequest);
			}

			commerceContext = _commerceContextFactory.create(
				groupId, _portal.getUserId(httpServletRequest),
				commerceOrder.getCommerceOrderId(), commerceAccountId);

			httpServletRequest.setAttribute(
				CommerceWebKeys.COMMERCE_CONTEXT, commerceContext);

			ServiceContext serviceContext = ServiceContextFactory.getInstance(
				CommerceOrderItem.class.getName(), httpServletRequest);

			serviceContext.setScopeGroupId(commerceOrder.getGroupId());

			CommerceOrderItem commerceOrderItem =
				_commerceOrderItemService.upsertCommerceOrderItem(
					commerceOrder.getCommerceOrderId(), cpInstanceId, quantity,
					0, options, commerceContext, serviceContext);

			cart = _commerceCartResourceUtil.getCart(
				commerceOrderItem.getCommerceOrderId(),
				themeDisplay.getLocale(), commerceContext);
		}
		catch (Exception e) {
			if (e instanceof CommerceOrderValidatorException) {
				cart = new Cart(
					_getCommerceOrderValidatorResultsMessages(
						(CommerceOrderValidatorException)e));
			}
			else {
				cart = new Cart(StringUtil.split(e.getLocalizedMessage()));
			}
		}

		return getResponse(cart);
	}

	protected Response getResponse(Object object) {
		if (object == null) {
			return Response.status(
				Response.Status.NOT_FOUND
			).build();
		}

		try {
			String json = _OBJECT_MAPPER.writeValueAsString(object);

			return Response.ok(
				json, MediaType.APPLICATION_JSON
			).build();
		}
		catch (JsonProcessingException jpe) {
			_log.error(jpe, jpe);
		}

		return Response.status(
			Response.Status.NOT_FOUND
		).build();
	}

	private String[] _getCommerceOrderValidatorResultsMessages(
		CommerceOrderValidatorException commerceOrderValidatorException) {

		String[] errorMessages = new String[0];

		List<CommerceOrderValidatorResult> commerceOrderValidatorResults =
			commerceOrderValidatorException.getCommerceOrderValidatorResults();

		for (CommerceOrderValidatorResult commerceOrderValidatorResult :
				commerceOrderValidatorResults) {

			if (commerceOrderValidatorResult.hasMessageResult()) {
				errorMessages = ArrayUtil.append(
					errorMessages,
					commerceOrderValidatorResult.getLocalizedMessage());
			}
		}

		return errorMessages;
	}

	private static final ObjectMapper _OBJECT_MAPPER = new ObjectMapper() {
		{
			configure(MapperFeature.SORT_PROPERTIES_ALPHABETICALLY, true);
			disable(SerializationFeature.INDENT_OUTPUT);
		}
	};

	private static final Log _log = LogFactoryUtil.getLog(
		CommerceCartResource.class);

	@Reference
	private CommerceCartResourceUtil _commerceCartResourceUtil;

	@Reference
	private CommerceContextFactory _commerceContextFactory;

	@Reference
	private CommerceOrderHttpHelper _commerceOrderHttpHelper;

	@Reference
	private CommerceOrderItemService _commerceOrderItemService;

	@Reference
	private CommerceOrderService _commerceOrderService;

	@Reference
	private Portal _portal;

}