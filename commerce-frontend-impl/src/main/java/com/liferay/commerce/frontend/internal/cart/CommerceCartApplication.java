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
import com.liferay.commerce.context.CommerceContext;
import com.liferay.commerce.exception.CommerceOrderValidatorException;
import com.liferay.commerce.frontend.internal.cart.model.Cart;
import com.liferay.commerce.model.CommerceOrderItem;
import com.liferay.commerce.order.CommerceOrderValidatorResult;
import com.liferay.commerce.service.CommerceOrderItemService;
import com.liferay.portal.events.ServicePreAction;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.service.ServiceContextFactory;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.ArrayUtil;
import com.liferay.portal.kernel.util.InstancePool;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.WebKeys;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.osgi.service.jaxrs.whiteboard.JaxrsWhiteboardConstants;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.DELETE;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Application;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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

	@DELETE
	@Path("/{commerceOrderItemId}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response deleteCommerceOrderItem(
		@PathParam("commerceOrderItemId") long commerceOrderItemId,
		@Context UriInfo uriInfo, @Context CommerceContext commerceContext,
		@Context HttpServletRequest httpServletRequest,
		@Context HttpServletResponse httpServletResponse) {

		Cart cart;

		try {
			ServicePreAction servicePreAction =
				(ServicePreAction)InstancePool.get(
					ServicePreAction.class.getName());

			ThemeDisplay themeDisplay = servicePreAction.initThemeDisplay(
				httpServletRequest, httpServletResponse);

			httpServletRequest.setAttribute(
				WebKeys.THEME_DISPLAY, themeDisplay);

			CommerceOrderItem commerceOrderItem =
				_commerceOrderItemService.getCommerceOrderItem(
					commerceOrderItemId);

			long commerceOrderId = commerceOrderItem.getCommerceOrderId();

			_commerceOrderItemService.deleteCommerceOrderItem(
				commerceOrderItem.getCommerceOrderItemId());

			cart = _commerceCartDataProvider.getCart(
				commerceOrderId, themeDisplay, commerceContext);
		}
		catch (Exception e) {
			cart = new Cart(StringUtil.split(e.getLocalizedMessage()));
		}

		return getResponse(cart);
	}

	@GET
	@Path("/{commerceOrderId}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getCommerceOrder(
		@PathParam("commerceOrderId") long commerceOrderId,
		@Context UriInfo uriInfo, @Context CommerceContext commerceContext,
		@Context HttpServletRequest httpServletRequest,
		@Context HttpServletResponse httpServletResponse) {

		Cart cart;

		try {
			ServicePreAction servicePreAction =
				(ServicePreAction)InstancePool.get(
					ServicePreAction.class.getName());

			ThemeDisplay themeDisplay = servicePreAction.initThemeDisplay(
				httpServletRequest, httpServletResponse);

			httpServletRequest.setAttribute(
				WebKeys.THEME_DISPLAY, themeDisplay);

			cart = _commerceCartDataProvider.getCart(
				commerceOrderId, themeDisplay, commerceContext);
		}
		catch (Exception e) {
			cart = new Cart(StringUtil.split(e.getLocalizedMessage()));
		}

		return getResponse(cart);
	}

	public Set<Object> getSingletons() {
		Set<Object> singletons = new HashSet<>();

		singletons.add(this);

		return singletons;
	}

	@Path("/{commerceOrderItemId}")
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	public Response updateOrderItem(
		@PathParam("commerceOrderItemId") long commerceOrderItemId,
		@FormParam("quantity") int quantity, @Context UriInfo uriInfo,
		@Context CommerceContext commerceContext,
		@Context HttpServletRequest httpServletRequest,
		@Context HttpServletResponse httpServletResponse) {

		Cart cart;

		try {
			ServicePreAction servicePreAction =
				(ServicePreAction)InstancePool.get(
					ServicePreAction.class.getName());

			ThemeDisplay themeDisplay = servicePreAction.initThemeDisplay(
				httpServletRequest, httpServletResponse);

			httpServletRequest.setAttribute(
				WebKeys.THEME_DISPLAY, themeDisplay);

			ServiceContext serviceContext = ServiceContextFactory.getInstance(
				CommerceOrderItem.class.getName(), httpServletRequest);

			CommerceOrderItem commerceOrderItem =
				_commerceOrderItemService.updateCommerceOrderItem(
					commerceOrderItemId, quantity, commerceContext,
					serviceContext);

			cart = _commerceCartDataProvider.getCart(
				commerceOrderItem.getCommerceOrderId(), themeDisplay,
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

	protected Response getResponse(Cart cart) {
		if (cart == null) {
			return Response.status(
				Response.Status.NOT_FOUND
			).build();
		}

		try {
			String json = _OBJECT_MAPPER.writeValueAsString(cart);

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
	private CommerceOrderItemService _commerceOrderItemService;

}