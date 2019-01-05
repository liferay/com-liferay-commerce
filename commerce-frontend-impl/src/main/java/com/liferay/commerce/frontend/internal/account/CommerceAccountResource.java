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

package com.liferay.commerce.frontend.internal.account;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import com.liferay.commerce.account.model.CommerceAccount;
import com.liferay.commerce.account.service.CommerceAccountService;
import com.liferay.commerce.frontend.internal.account.model.Account;
import com.liferay.commerce.frontend.internal.account.model.AccountList;
import com.liferay.petra.string.StringBundler;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.webserver.WebServerServletTokenUtil;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
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
@Component(service = CommerceAccountResource.class)
public class CommerceAccountResource {

	@GET
	@Path("/{parentAccountId}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getCommerceAccounts(
		@PathParam("parentAccountId") long parentAccountId,
		@QueryParam("page") int page, @QueryParam("pageSize") int pageSize,
		@Context UriInfo uriInfo, @Context ThemeDisplay themeDisplay) {

		AccountList accountList;

		try {
			accountList = getAccountList(
				parentAccountId, page, pageSize, themeDisplay.getPathImage());
		}
		catch (Exception e) {
			accountList = new AccountList(
				StringUtil.split(e.getLocalizedMessage()));
		}

		return getResponse(accountList);
	}

	protected AccountList getAccountList(
			long parentAccountId, int page, int pageSize, String imagePath)
		throws PortalException {

		List<Account> accounts = getAccounts(
			parentAccountId, page, pageSize, imagePath);

		return new AccountList(accounts, accounts.size());
	}

	protected List<Account> getAccounts(
			long parentAccountId, int page, int pageSize, String imagePath)
		throws PortalException {

		List<Account> accounts = new ArrayList<>();

		int start = (page - 1) * pageSize;
		int end = page * pageSize;

		List<CommerceAccount> userCommerceAccounts =
			_commerceAccountService.getUserCommerceAccounts(
				parentAccountId, start, end);

		for (CommerceAccount commerceAccount : userCommerceAccounts) {
			accounts.add(
				new Account(
					commerceAccount.getCommerceAccountId(),
					commerceAccount.getName(),
					getLogoThumbnailSrc(
						commerceAccount.getLogoId(), imagePath)));
		}

		return accounts;
	}

	protected String getLogoThumbnailSrc(long logoId, String imagePath) {
		StringBundler sb = new StringBundler(5);

		sb.append(imagePath);
		sb.append("/organization_logo?img_id=");
		sb.append(logoId);
		sb.append("&t=");
		sb.append(WebServerServletTokenUtil.getToken(logoId));

		return sb.toString();
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

	private static final ObjectMapper _OBJECT_MAPPER = new ObjectMapper() {
		{
			configure(MapperFeature.SORT_PROPERTIES_ALPHABETICALLY, true);
			enable(SerializationFeature.INDENT_OUTPUT);
		}
	};

	private static final Log _log = LogFactoryUtil.getLog(
		CommerceAccountResource.class);

	@Reference
	private CommerceAccountService _commerceAccountService;

}