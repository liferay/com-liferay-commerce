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

package com.liferay.commerce.cart.rest.internal;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import com.liferay.commerce.cart.rest.internal.context.provider.CommerceContextProvider;
import com.liferay.commerce.cart.rest.internal.model.AddToCartResponse;
import com.liferay.commerce.cart.rest.internal.model.Cart;
import com.liferay.commerce.cart.rest.internal.provider.CommerceCartDataProvider;
import com.liferay.commerce.context.CommerceContext;
import com.liferay.commerce.exception.CommerceOrderValidatorException;
import com.liferay.commerce.model.CommerceOrderItem;
import com.liferay.commerce.order.CommerceOrderValidatorResult;
import com.liferay.commerce.service.CommerceOrderItemService;
import com.liferay.portal.events.EventsProcessorUtil;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.service.ServiceContextFactory;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.ArrayUtil;
import com.liferay.portal.kernel.util.PropsKeys;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.WebKeys;
import com.liferay.portal.util.PropsValues;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import javax.ws.rs.DELETE;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Application;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.osgi.service.jaxrs.whiteboard.JaxrsWhiteboardConstants;

/**
 * @author Alessio Antonio Rendina
 */
@Component(
	property = {
		JaxrsWhiteboardConstants.JAX_RS_APPLICATION_BASE + "=/commerce-cart",
		JaxrsWhiteboardConstants.JAX_RS_NAME + "=CommerceCart.Rest",
		"auth.verifier.auth.verifier.PortalSessionAuthVerifier.urls.includes=/*",
		"auth.verifier.guest.allowed=true", "liferay.oauth2=false"
	},
	service = Application.class
)
public class CommerceCartApplication extends Application {

	@Consumes(MediaType.APPLICATION_JSON)
	@Path("/{cartId}")
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	public Response addToCart(
		@PathParam("cartId") long cartId, @Context UriInfo uriInfo,
		@Context CommerceContext commerceContext,
		@Context HttpServletRequest httpServletRequest,
		@Context HttpServletResponse httpServletResponse,
		CartItemUpdate cartItemUpdate) {

		AddToCartResponse addToCartResponse;

		try {
			EventsProcessorUtil.process(
				PropsKeys.SERVLET_SERVICE_EVENTS_PRE,
				PropsValues.SERVLET_SERVICE_EVENTS_PRE, httpServletRequest,
				httpServletResponse);

			ServiceContext serviceContext = ServiceContextFactory.getInstance(
				CommerceOrderItem.class.getName(), httpServletRequest);

			CommerceOrderItem commerceOrderItem =
				_commerceOrderItemService.upsertCommerceOrderItem(
					cartId, cartItemUpdate.getProductId(),
					cartItemUpdate.getQuantity(),
					cartItemUpdate.getOptionsJSON(), commerceContext,
					serviceContext);

			addToCartResponse = _commerceCartDataProvider.getAddToCartResponse(
				commerceOrderItem.getCommerceOrderId(), httpServletRequest,
				commerceContext);
		}
		catch (Exception e) {
			if (e instanceof CommerceOrderValidatorException) {
				addToCartResponse = new AddToCartResponse(
					_getCommerceOrderValidatorResultsMessages(
						(CommerceOrderValidatorException)e));
			}
			else {
				addToCartResponse = new AddToCartResponse(
					StringUtil.split(e.getLocalizedMessage()));
			}
		}

		return getResponse(addToCartResponse);
	}

	@DELETE
	@Path("/{productId}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response deleteCommerceOrderItem(
		@PathParam("productId") long productId, @Context UriInfo uriInfo,
		@Context CommerceContext commerceContext,
		@Context HttpServletRequest httpServletRequest,
		@Context HttpServletResponse httpServletResponse) {

		Cart cart;

		try {
			EventsProcessorUtil.process(
				PropsKeys.SERVLET_SERVICE_EVENTS_PRE,
				PropsValues.SERVLET_SERVICE_EVENTS_PRE, httpServletRequest,
				httpServletResponse);

			ThemeDisplay themeDisplay =
				(ThemeDisplay)httpServletRequest.getAttribute(
					WebKeys.THEME_DISPLAY);

			CommerceOrderItem commerceOrderItem =
				_commerceOrderItemService.getCommerceOrderItem(productId);

			long commerceOrderId = commerceOrderItem.getCommerceOrderId();

			_commerceOrderItemService.deleteCommerceOrderItem(
				commerceOrderItem.getCommerceOrderItemId());

			cart = _commerceCartDataProvider.getCart(
				commerceOrderId, themeDisplay, commerceContext);
		}
		catch (Exception e) {
			cart = new Cart(
				null, null, false, StringUtil.split(e.getLocalizedMessage()));
		}

		return getResponse(cart);
	}

	@GET
	@Path("/{cartId}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getCommerceOrder(
		@PathParam("cartId") long cartId, @Context UriInfo uriInfo,
		@Context CommerceContext commerceContext,
		@Context HttpServletRequest httpServletRequest,
		@Context HttpServletResponse httpServletResponse) {

		Cart cart;

		try {
			EventsProcessorUtil.process(
				PropsKeys.SERVLET_SERVICE_EVENTS_PRE,
				PropsValues.SERVLET_SERVICE_EVENTS_PRE, httpServletRequest,
				httpServletResponse);

			ThemeDisplay themeDisplay =
				(ThemeDisplay)httpServletRequest.getAttribute(
					WebKeys.THEME_DISPLAY);

			cart = _commerceCartDataProvider.getCart(
				cartId, themeDisplay, commerceContext);
		}
		catch (Exception e) {
			cart = new Cart(
				null, null, false, StringUtil.split(e.getLocalizedMessage()));
		}

		return getResponse(cart);
	}

	public Set<Object> getSingletons() {
		Set<Object> singletons = new HashSet<>();

		singletons.add(_commerceContextProvider);
		singletons.add(this);

		return singletons;
	}

	@Consumes(MediaType.APPLICATION_JSON)
	@Path("/{productId}")
	@Produces(MediaType.APPLICATION_JSON)
	@PUT
	public Response updateOrderItem(
		@PathParam("productId") long productId, @Context UriInfo uriInfo,
		@Context CommerceContext commerceContext,
		@Context HttpServletRequest httpServletRequest,
		@Context HttpServletResponse httpServletResponse,
		CartItemUpdate cartItemUpdate) {

		Cart cart;

		try {
			EventsProcessorUtil.process(
				PropsKeys.SERVLET_SERVICE_EVENTS_PRE,
				PropsValues.SERVLET_SERVICE_EVENTS_PRE, httpServletRequest,
				httpServletResponse);

			ThemeDisplay themeDisplay =
				(ThemeDisplay)httpServletRequest.getAttribute(
					WebKeys.THEME_DISPLAY);

			ServiceContext serviceContext = ServiceContextFactory.getInstance(
				CommerceOrderItem.class.getName(), httpServletRequest);

			CommerceOrderItem commerceOrderItem =
				_commerceOrderItemService.updateCommerceOrderItem(
					productId, cartItemUpdate.getQuantity(), commerceContext);

			cart = _commerceCartDataProvider.getCart(
				commerceOrderItem.getCommerceOrderId(), themeDisplay,
				commerceContext);
		}
		catch (Exception e) {
			if (e instanceof CommerceOrderValidatorException) {
				cart = new Cart(
					null, null, false,
					_getCommerceOrderValidatorResultsMessages(
						(CommerceOrderValidatorException)e));
			}
			else {
				cart = new Cart(
					null, null, false,
					StringUtil.split(e.getLocalizedMessage()));
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
			enable(SerializationFeature.INDENT_OUTPUT);
		}
	};

	private static final Log _log = LogFactoryUtil.getLog(
		CommerceCartApplication.class);

	@Reference
	private CommerceCartDataProvider _commerceCartDataProvider;

	@Reference
	private CommerceContextProvider _commerceContextProvider;

	@Reference
	private CommerceOrderItemService _commerceOrderItemService;

}