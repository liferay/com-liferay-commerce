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

package com.liferay.commerce.organization.rest.internal;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import com.liferay.commerce.organization.rest.internal.model.AccountList;
import com.liferay.commerce.organization.rest.internal.provider.CommerceOrganizationDataProvider;
import com.liferay.commerce.organization.util.CommerceOrganizationHelper;
import com.liferay.portal.events.EventsProcessorUtil;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.PropsKeys;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.WebKeys;
import com.liferay.portal.util.PropsValues;

import java.util.HashSet;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
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
		JaxrsWhiteboardConstants.JAX_RS_APPLICATION_BASE + "=/commerce-organization",
		JaxrsWhiteboardConstants.JAX_RS_NAME + "=CommerceOrganization.Rest",
		"auth.verifier.auth.verifier.PortalSessionAuthVerifier.urls.includes=/*",
		"auth.verifier.guest.allowed=true", "liferay.oauth2=false"
	},
	service = Application.class
)
public class CommerceOrganizationApplication extends Application {

	@GET
	@Path("/{groupId}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getCommerceAccounts(
		@PathParam("groupId") long groupId,
		@QueryParam("keywords") String keywords, @QueryParam("page") int page,
		@QueryParam("pageSize") int pageSize, @Context UriInfo uriInfo,
		@Context HttpServletRequest httpServletRequest,
		@Context HttpServletResponse httpServletResponse) {

		AccountList accountList;

		try {
			EventsProcessorUtil.process(
				PropsKeys.SERVLET_SERVICE_EVENTS_PRE,
				PropsValues.SERVLET_SERVICE_EVENTS_PRE, httpServletRequest,
				httpServletResponse);

			ThemeDisplay themeDisplay =
				(ThemeDisplay)httpServletRequest.getAttribute(
					WebKeys.THEME_DISPLAY);

			accountList = _commerceOrganizationDataProvider.getAccountList(
				groupId, themeDisplay.getUserId(), keywords, page, pageSize,
				themeDisplay.getPathImage());
		}
		catch (Exception e) {
			accountList = new AccountList(
				StringUtil.split(e.getLocalizedMessage()));
		}

		return getResponse(accountList);
	}

	public Set<Object> getSingletons() {
		Set<Object> singletons = new HashSet<>();

		singletons.add(this);

		return singletons;
	}

	@Path("/{groupId}/{organizationId}")
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	public Response setCurrentOrganization(
		@PathParam("groupId") long groupId,
		@PathParam("organizationId") long organizationId,
		@QueryParam("keywords") String keywords, @QueryParam("page") int page,
		@QueryParam("pageSize") int pageSize, @Context UriInfo uriInfo,
		@Context HttpServletRequest httpServletRequest,
		@Context HttpServletResponse httpServletResponse) {

		AccountList accountList;

		try {
			EventsProcessorUtil.process(
				PropsKeys.SERVLET_SERVICE_EVENTS_PRE,
				PropsValues.SERVLET_SERVICE_EVENTS_PRE, httpServletRequest,
				httpServletResponse);

			ThemeDisplay themeDisplay =
				(ThemeDisplay)httpServletRequest.getAttribute(
					WebKeys.THEME_DISPLAY);

			_commerceOrganizationHelper.setCurrentOrganization(
				httpServletRequest, organizationId);

			accountList = _commerceOrganizationDataProvider.getAccountList(
				groupId, themeDisplay.getUserId(), keywords, page, pageSize,
				themeDisplay.getPathImage());
		}
		catch (Exception e) {
			accountList = new AccountList(
				StringUtil.split(e.getLocalizedMessage()));
		}

		return getResponse(accountList);
	}

	protected Response getResponse(AccountList accountList) {
		if (accountList == null) {
			return Response.status(
				Response.Status.NOT_FOUND
			).build();
		}

		try {
			String json = _OBJECT_MAPPER.writeValueAsString(accountList);

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

	private static final ObjectMapper _OBJECT_MAPPER = new ObjectMapper() {
		{
			configure(MapperFeature.SORT_PROPERTIES_ALPHABETICALLY, true);
			enable(SerializationFeature.INDENT_OUTPUT);
		}
	};

	private static final Log _log = LogFactoryUtil.getLog(
		CommerceOrganizationApplication.class);

	@Reference
	private CommerceOrganizationDataProvider _commerceOrganizationDataProvider;

	@Reference
	private CommerceOrganizationHelper _commerceOrganizationHelper;

}