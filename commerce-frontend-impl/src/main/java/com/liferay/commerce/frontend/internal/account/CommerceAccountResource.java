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

import com.liferay.commerce.account.constants.CommerceAccountConstants;
import com.liferay.commerce.account.model.CommerceAccount;
import com.liferay.commerce.account.service.CommerceAccountService;
import com.liferay.commerce.account.util.CommerceAccountHelper;
import com.liferay.commerce.context.CommerceContext;
import com.liferay.commerce.context.CommerceContextFactory;
import com.liferay.commerce.frontend.internal.account.model.Account;
import com.liferay.commerce.frontend.internal.account.model.AccountList;
import com.liferay.commerce.frontend.internal.account.model.AccountOrganization;
import com.liferay.commerce.frontend.internal.account.model.AccountOrganizationList;
import com.liferay.commerce.frontend.internal.account.model.AccountUser;
import com.liferay.commerce.frontend.internal.account.model.AccountUserList;
import com.liferay.commerce.frontend.internal.account.model.Order;
import com.liferay.commerce.frontend.internal.account.model.OrderList;
import com.liferay.commerce.model.CommerceOrder;
import com.liferay.commerce.product.service.CommerceChannelLocalService;
import com.liferay.commerce.service.CommerceOrderService;
import com.liferay.petra.string.StringBundler;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.language.LanguageUtil;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.Organization;
import com.liferay.portal.kernel.model.OrganizationConstants;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.portlet.PortletProvider;
import com.liferay.portal.kernel.portlet.PortletProviderUtil;
import com.liferay.portal.kernel.search.BaseModelSearchResult;
import com.liferay.portal.kernel.search.SortFactoryUtil;
import com.liferay.portal.kernel.service.OrganizationLocalService;
import com.liferay.portal.kernel.service.UserLocalService;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.Portal;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.webserver.WebServerServletTokenUtil;
import com.liferay.portal.kernel.workflow.WorkflowConstants;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.portlet.PortletURL;

import javax.servlet.http.HttpServletRequest;

import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
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

	public AccountList getAccountList(
			long userId, long parentAccountId, int commerceSiteType,
			String keywords, int page, int pageSize, String imagePath)
		throws PortalException {

		List<Account> accounts = getAccounts(
			userId, parentAccountId, commerceSiteType, keywords, page, pageSize,
			imagePath);

		return new AccountList(
			accounts,
			getAccountsCount(
				userId, parentAccountId, commerceSiteType, keywords));
	}

	public AccountOrganizationList getAccountOrganizationList(
			long companyId, String keywords, String imagePath)
		throws PortalException {

		List<AccountOrganization> accountOrganizations = _searchOrganizations(
			companyId, keywords, imagePath);

		return new AccountOrganizationList(
			accountOrganizations, accountOrganizations.size());
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
		@QueryParam("groupId") long groupId,
		@QueryParam("q") String queryString, @QueryParam("page") int page,
		@QueryParam("pageSize") int pageSize, @Context UriInfo uriInfo,
		@Context ThemeDisplay themeDisplay) {

		AccountList accountList = null;

		themeDisplay.setScopeGroupId(groupId);

		HttpServletRequest httpServletRequest = themeDisplay.getRequest();

		CommerceContext commerceContext = _commerceContextFactory.create(
			_portal.getCompanyId(httpServletRequest), groupId,
			_portal.getUserId(httpServletRequest), 0, 0);

		try {
			accountList = getAccountList(
				themeDisplay.getUserId(),
				CommerceAccountConstants.DEFAULT_PARENT_ACCOUNT_ID,
				commerceContext.getCommerceSiteType(), queryString, page,
				pageSize, themeDisplay.getPathImage());
		}
		catch (Exception e) {
			_log.error(e, e);
			accountList = new AccountList(
				StringUtil.split(e.getLocalizedMessage()));
		}

		return getResponse(accountList);
	}

	@GET
	@Path("/search-accounts/{accountId}/orders/")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getCommerceOrders(
		@QueryParam("groupId") long groupId,
		@PathParam("accountId") long accountId,
		@QueryParam("q") String queryString, @QueryParam("page") int page,
		@QueryParam("pageSize") int pageSize,
		@Context ThemeDisplay themeDisplay) {

		themeDisplay.setScopeGroupId(groupId);

		OrderList orderList = null;

		try {
			orderList = getOrderList(
				groupId, accountId, page, pageSize, themeDisplay.getRequest());
		}
		catch (Exception e) {
			orderList = new OrderList(
				StringUtil.split(e.getLocalizedMessage()));
		}

		return getResponse(orderList);
	}

	public OrderList getOrderList(
			long groupId, long accountId, int page, int pageSize,
			HttpServletRequest httpServletRequest)
		throws PortalException {

		List<Order> orders = getOrders(
			groupId, accountId, page, pageSize, httpServletRequest);

		return new OrderList(orders, orders.size());
	}

	@GET
	@Path("/search-organizations")
	@Produces(MediaType.APPLICATION_JSON)
	public Response searchOrganizations(
		@QueryParam("q") String queryString,
		@Context ThemeDisplay themeDisplay) {

		AccountOrganizationList accountOrganizationList = null;

		try {
			accountOrganizationList = getAccountOrganizationList(
				themeDisplay.getCompanyId(), queryString,
				themeDisplay.getPathImage());
		}
		catch (Exception e) {
			accountOrganizationList = new AccountOrganizationList(
				StringUtil.split(e.getLocalizedMessage()));
		}

		return getResponse(accountOrganizationList);
	}

	@GET
	@Path("/search-users")
	@Produces(MediaType.APPLICATION_JSON)
	public Response searchUsers(
		@QueryParam("q") String queryString,
		@Context ThemeDisplay themeDisplay) {

		AccountUserList accountUserList = null;

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

	@Path("/set-current-account")
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	public Response setCurrentAccount(
		@QueryParam("groupId") long groupId,
		@FormParam("accountId") long accountId,
		@Context HttpServletRequest httpServletRequest) {

		try {
			_commerceAccountHelper.setCurrentCommerceAccount(
				httpServletRequest,
				_commerceChannelLocalService.
					getCommerceChannelGroupIdBySiteGroupId(groupId),
				accountId);
		}
		catch (Exception e) {
			_log.error(e, e);

			Response.ResponseBuilder responseBuilder = Response.serverError();

			return responseBuilder.build();
		}

		return Response.ok(
		).build();
	}

	protected List<Account> getAccounts(
			long userId, long parentAccountId, int commerceSiteType,
			String keywords, int page, int pageSize, String imagePath)
		throws PortalException {

		List<Account> accounts = new ArrayList<>();

		int start = (page - 1) * pageSize;
		int end = page * pageSize;

		List<CommerceAccount> userCommerceAccounts =
			_commerceAccountService.getUserCommerceAccounts(
				userId, parentAccountId, commerceSiteType, keywords, start,
				end);

		for (CommerceAccount commerceAccount : userCommerceAccounts) {
			accounts.add(
				new Account(
					String.valueOf(commerceAccount.getCommerceAccountId()),
					commerceAccount.getName(),
					getLogoThumbnailSrc(
						commerceAccount.getLogoId(), imagePath)));
		}

		return accounts;
	}

	protected int getAccountsCount(
			long userId, Long parentAccountId, int commerceSiteType,
			String keywords)
		throws PortalException {

		return _commerceAccountService.getUserCommerceAccountsCount(
			userId, parentAccountId, commerceSiteType, keywords);
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

	protected List<Order> getOrders(
			long groupId, long commerceAccountId, int page, int pageSize,
			HttpServletRequest httpServletRequest)
		throws PortalException {

		List<Order> orders = new ArrayList<>();

		int start = (page - 1) * pageSize;
		int end = page * pageSize;

		long commerceChannelGroupId =
			_commerceChannelLocalService.getCommerceChannelGroupIdBySiteGroupId(
				groupId);

		List<CommerceOrder> userCommerceOrders =
			_commerceOrderService.getPendingCommerceOrders(
				commerceChannelGroupId, commerceAccountId, StringPool.BLANK,
				start, end);

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

	private String _getOrderLinkURL(
			long commerceOrderId, HttpServletRequest httpServletRequest)
		throws PortalException {

		PortletURL editURL = PortletProviderUtil.getPortletURL(
			httpServletRequest, CommerceOrder.class.getName(),
			PortletProvider.Action.EDIT);

		editURL.setParameter("mvcRenderCommandName", "editCommerceOrder");
		editURL.setParameter(
			"commerceOrderId", String.valueOf(commerceOrderId));

		editURL.setParameter(
			"backURL", httpServletRequest.getHeader("Referer"));

		return editURL.toString();
	}

	private List<AccountOrganization> _searchOrganizations(
			long companyId, String keywords, String imagePath)
		throws PortalException {

		List<AccountOrganization> accountOrganizations = new ArrayList<>();

		BaseModelSearchResult<Organization> baseModelSearchResult =
			_organizationLocalService.searchOrganizations(
				companyId, OrganizationConstants.ANY_PARENT_ORGANIZATION_ID,
				keywords, null, 0, 10, SortFactoryUtil.create("name", false));

		for (Organization organization :
				baseModelSearchResult.getBaseModels()) {

			accountOrganizations.add(
				new AccountOrganization(
					organization.getOrganizationId(), organization.getName(),
					StringPool.BLANK,
					getLogoThumbnailSrc(organization.getLogoId(), imagePath)));
		}

		return accountOrganizations;
	}

	private List<AccountUser> _searchUsers(
		long companyId, String keywords, String imagePath) {

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
			disable(SerializationFeature.INDENT_OUTPUT);
		}
	};

	private static final Log _log = LogFactoryUtil.getLog(
		CommerceAccountResource.class);

	@Reference
	private CommerceAccountHelper _commerceAccountHelper;

	@Reference
	private CommerceAccountService _commerceAccountService;

	@Reference
	private CommerceChannelLocalService _commerceChannelLocalService;

	@Reference
	private CommerceContextFactory _commerceContextFactory;

	@Reference
	private CommerceOrderService _commerceOrderService;

	@Reference
	private OrganizationLocalService _organizationLocalService;

	@Reference
	private Portal _portal;

	@Reference
	private UserLocalService _userLocalService;

}