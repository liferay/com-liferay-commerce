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
import com.liferay.commerce.frontend.internal.account.model.AccountUser;
import com.liferay.commerce.frontend.internal.account.model.AccountUserList;
import com.liferay.petra.string.StringBundler;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.service.UserLocalService;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.webserver.WebServerServletTokenUtil;
import com.liferay.portal.kernel.workflow.WorkflowConstants;

import java.util.ArrayList;
import java.util.List;

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
@Component(service = CommerceAccountResource.class)
public class CommerceAccountResource {

	public AccountList getAccountList(
			Long parentAccountId, String keywords, int page, int pageSize,
			String imagePath)
		throws PortalException {

		List<Account> accounts = getAccounts(
			parentAccountId, keywords, page, pageSize, imagePath);

		return new AccountList(
			accounts, getAccountsCount(parentAccountId, keywords));
	}

	public AccountUserList getAccountUserList(
			long companyId, String keywords, String imagePath)
		throws PortalException {

		List<AccountUser> accountUsers = _searchUsers(
			companyId, keywords, imagePath);

		return new AccountUserList(accountUsers, accountUsers.size());
	}

	@GET
	@Path("/search-accounts")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getCommerceAccounts(
		@QueryParam("parentAccountId") Long parentAccountId,
		@QueryParam("q") String queryString, @QueryParam("page") int page,
		@QueryParam("pageSize") int pageSize, @Context UriInfo uriInfo,
		@Context ThemeDisplay themeDisplay) {

		AccountList accountList;

		try {
			accountList = getAccountList(
				parentAccountId, queryString, page, pageSize,
				themeDisplay.getPathImage());
		}
		catch (Exception e) {
			accountList = new AccountList(
				StringUtil.split(e.getLocalizedMessage()));
		}

		return getResponse(accountList);
	}

	@GET
	@Path("/search-users")
	@Produces(MediaType.APPLICATION_JSON)
	public Response searchUsers(
		@QueryParam("q") String queryString,
		@Context ThemeDisplay themeDisplay) {

		AccountUserList accountUserList;

		try {
			accountUserList = getAccountUserList(
				themeDisplay.getCompanyId(), queryString,
				themeDisplay.getPathImage());
		}
		catch (Exception e) {
			accountUserList = new AccountUserList(
				StringUtil.split(e.getLocalizedMessage()));
		}

		return getResponse(accountUserList);
	}

	protected List<Account> getAccounts(
			Long parentAccountId, String keywords, int page, int pageSize,
			String imagePath)
		throws PortalException {

		List<Account> accounts = new ArrayList<>();

		int start = (page - 1) * pageSize;
		int end = page * pageSize;

		List<CommerceAccount> userCommerceAccounts =
			_commerceAccountService.getUserCommerceAccounts(
				parentAccountId, keywords, start, end);

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

	protected int getAccountsCount(Long parentAccountId, String keywords)
		throws PortalException {

		return _commerceAccountService.getUserCommerceAccountsCount(
			parentAccountId, keywords);
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

	protected String getUserPortraitSrc(User user, String imagePath) {
		StringBundler sb = new StringBundler(5);

		sb.append(imagePath);
		sb.append("/user_portrait?screenName=");
		sb.append(user.getScreenName());
		sb.append("&amp;companyId=");
		sb.append(user.getCompanyId());

		return sb.toString();
	}

	private List<AccountUser> _searchUsers(
			long companyId, String keywords, String imagePath)
		throws PortalException {

		List<AccountUser> accountUsers = new ArrayList<>();

		List<User> users = _userLocalService.search(
			companyId, keywords, WorkflowConstants.STATUS_APPROVED, null, 0, 10,
			(OrderByComparator<User>)null);

		for (User user : users) {
			accountUsers.add(
				new AccountUser(
					user.getUserId(), user.getFullName(),
					user.getEmailAddress(),
					getUserPortraitSrc(user, imagePath)));
		}

		return accountUsers;
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

	@Reference
	private UserLocalService _userLocalService;

}