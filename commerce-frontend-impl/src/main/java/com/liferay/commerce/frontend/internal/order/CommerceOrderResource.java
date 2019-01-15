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

package com.liferay.commerce.frontend.internal.order;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import com.liferay.commerce.frontend.internal.account.model.Order;
import com.liferay.commerce.frontend.internal.account.model.OrderList;
import com.liferay.commerce.model.CommerceOrder;
import com.liferay.commerce.order.CommerceOrderHttpHelper;
import com.liferay.commerce.service.CommerceOrderService;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.language.LanguageUtil;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.portlet.PortletProvider;
import com.liferay.portal.kernel.portlet.PortletProviderUtil;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.Constants;
import com.liferay.portal.kernel.util.Portal;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.workflow.WorkflowConstants;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.portlet.ActionRequest;
import javax.portlet.PortletURL;

import javax.servlet.http.HttpServletRequest;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Alessio Antonio Rendina
 */
@Component(service = CommerceOrderResource.class)
public class CommerceOrderResource {

	@GET
	@Path("/search-orders")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getCommerceOrders(
		@QueryParam("q") String queryString, @QueryParam("page") int page,
		@QueryParam("pageSize") int pageSize, @Context UriInfo uriInfo,
		@Context ThemeDisplay themeDisplay) {

		OrderList orderList;

		try {
			orderList = getOrderList(
				themeDisplay.getScopeGroupId(), queryString, page, pageSize,
				themeDisplay.getRequest());
		}
		catch (Exception e) {
			orderList = new OrderList(
				StringUtil.split(e.getLocalizedMessage()));
		}

		return getResponse(orderList);
	}

	public OrderList getOrderList(
			long groupId, String keywords, int page, int pageSize,
			HttpServletRequest httpServletRequest)
		throws PortalException {

		List<Order> orders = getOrders(
			groupId, keywords, page, pageSize, httpServletRequest);

		return new OrderList(orders, getOrdersCount(groupId, keywords));
	}

	protected List<Order> getOrders(
			long groupId, String keywords, int page, int pageSize,
			HttpServletRequest httpServletRequest)
		throws PortalException {

		List<Order> orders = new ArrayList<>();

		int start = (page - 1) * pageSize;
		int end = page * pageSize;

		List<CommerceOrder> userCommerceOrders =
			_commerceOrderService.getUserCommerceOrders(
				groupId, keywords, start, end);

		for (CommerceOrder commerceOrder : userCommerceOrders) {
			Date modifiedDate = commerceOrder.getModifiedDate();

			String modifiedDateTimeDescription =
				LanguageUtil.getTimeDescription(
					httpServletRequest,
					System.currentTimeMillis() - modifiedDate.getTime(), true);

			orders.add(
				new Order(
					commerceOrder.getCommerceOrderId(),
					commerceOrder.getCommerceAccountId(),
					commerceOrder.getPurchaseOrderNumber(),
					LanguageUtil.format(
						httpServletRequest, "x-ago",
						modifiedDateTimeDescription),
					WorkflowConstants.getStatusLabel(commerceOrder.getStatus()),
					_getOrderLinkURL(
						commerceOrder.getCommerceOrderId(),
						httpServletRequest)));
		}

		return orders;
	}

	protected int getOrdersCount(long groupId, String keywords)
		throws PortalException {

		return _commerceOrderService.getUserCommerceOrdersCount(
			groupId, keywords);
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

	private String _getOrderLinkURL(
			long commerceOrderId, HttpServletRequest httpServletRequest)
		throws PortalException {

		PortletURL editURL = PortletProviderUtil.getPortletURL(
			httpServletRequest, CommerceOrder.class.getName(),
			PortletProvider.Action.EDIT);

		editURL.setParameter(ActionRequest.ACTION_NAME, "editCommerceOrder");
		editURL.setParameter(Constants.CMD, "setCurrent");
		editURL.setParameter(
			"commerceOrderId", String.valueOf(commerceOrderId));

		String redirect = _portal.getCurrentURL(httpServletRequest);

		editURL.setParameter("redirect", redirect);

		return editURL.toString();
	}

	private static final ObjectMapper _OBJECT_MAPPER = new ObjectMapper() {
		{
			configure(MapperFeature.SORT_PROPERTIES_ALPHABETICALLY, true);
			enable(SerializationFeature.INDENT_OUTPUT);
		}
	};

	private static final Log _log = LogFactoryUtil.getLog(
		CommerceOrderResource.class);

	@Reference
	private CommerceOrderHttpHelper _commerceOrderHttpHelper;

	@Reference
	private CommerceOrderService _commerceOrderService;

	@Reference
	private Portal _portal;

}