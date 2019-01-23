<%--
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
--%>

<%@ include file="/init.jsp" %>

<%
CommerceAccountUserDisplayContext commerceAccountUserDisplayContext = (CommerceAccountUserDisplayContext)request.getAttribute(WebKeys.PORTLET_DISPLAY_CONTEXT);

CommerceAccount commerceAccount = commerceAccountUserDisplayContext.getCurrentCommerceAccount();
User selectedUser = commerceAccountUserDisplayContext.getSelectedUser();
PortletURL portletURL = commerceAccountUserDisplayContext.getPortletURL();

portletURL.setParameter("mvcRenderCommandName", "viewCommerceAccountUser");
%>

<portlet:renderURL var="editCommerceAccountURL">
	<portlet:param name="mvcRenderCommandName" value="editCommerceAccountUser" />
	<portlet:param name="commerceAccountId" value="<%= String.valueOf(commerceAccount.getCommerceAccountId()) %>" />
	<portlet:param name="userId" value="<%= String.valueOf(selectedUser.getUserId()) %>" />
	<portlet:param name='<%= PortletQName.PUBLIC_RENDER_PARAMETER_NAMESPACE + "backURL" %>' value="<%= portletURL.toString() %>" />
</portlet:renderURL>

<div class="details-header">
	<section class="details-header__section details-header__primary">
		<div class="details-header__main-data">
			<div class="details-header__avatar">
				<img alt="avatar" src="https://via.placeholder.com/120" />
			</div>

			<div class="details-header__name">
				<%= selectedUser.getFullName() %>
			</div>

			<div class="details-header__email">
				<%= selectedUser.getEmailAddress() %>
			</div>
		</div>

		<div class="details-header__action">
			<aui:button cssClass="minium-button minium-button--big minium-button--outline" href="<%= editCommerceAccountURL %>" value='<%= LanguageUtil.get(request, "edit-user") %>' />
		</div>
	</section>
</div>
<div class="commerce-account-container">
	<commerce-ui:table
		dataProviderKey="commerceAccountUserRoles"
		filter="<%= commerceAccountUserDisplayContext.getAccountFilter() %>"
		itemPerPage="<%= 5 %>"
		namespace="<%= renderResponse.getNamespace() %>"
		pageNumber="1"
		portletURL="<%= portletURL %>"
		tableName="commerceAccountUserRoles"
	/>
</div>
