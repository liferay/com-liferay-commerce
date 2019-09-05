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
CommerceAccountAdminDisplayContext commerceAccountAdminDisplayContext = (CommerceAccountAdminDisplayContext)request.getAttribute(WebKeys.PORTLET_DISPLAY_CONTEXT);
%>

<c:if test="<%= commerceAccountAdminDisplayContext.hasPermission(CommerceAccountActionKeys.MANAGE_ALL_ACCOUNTS) %>">

	<%
	SearchContainer<CommerceAccount> commerceAccountSearchContainer = commerceAccountAdminDisplayContext.getSearchContainer();
	%>

	<liferay-frontend:management-bar
		includeCheckBox="<%= true %>"
		searchContainerId="commerceAccounts"
	>
		<liferay-frontend:management-bar-buttons>
			<liferay-frontend:management-bar-display-buttons
				disabled="<%= true %>"
				displayViews='<%= new String[] {"list"} %>'
				portletURL="<%= commerceAccountAdminDisplayContext.getPortletURL() %>"
				selectedDisplayStyle="list"
			/>

			<c:if test="<%= commerceAccountAdminDisplayContext.hasPermission(CommerceAccountActionKeys.ADD_ACCOUNT) %>">
				<portlet:renderURL var="addCommerceAccountURL">
					<portlet:param name="mvcRenderCommandName" value="editCommerceAccount" />
					<portlet:param name="redirect" value="<%= currentURL %>" />
				</portlet:renderURL>

				<liferay-frontend:add-menu
					inline="<%= true %>"
				>
					<liferay-frontend:add-menu-item
						title='<%= LanguageUtil.get(request, "add-account") %>'
						url="<%= addCommerceAccountURL.toString() %>"
					/>
				</liferay-frontend:add-menu>
			</c:if>
		</liferay-frontend:management-bar-buttons>

		<liferay-frontend:management-bar-filters>
			<liferay-frontend:management-bar-navigation
				navigationKeys='<%= new String[] {"all", "active", "inactive"} %>'
				navigationParam="activeNavigation"
				portletURL="<%= commerceAccountAdminDisplayContext.getPortletURL() %>"
			/>

			<liferay-frontend:management-bar-navigation
				navigationKeys='<%= new String[] {"all", "personal", "business"} %>'
				navigationParam="typeNavigation"
				portletURL="<%= commerceAccountAdminDisplayContext.getPortletURL() %>"
			/>

			<li>
				<aui:form action="<%= String.valueOf(commerceAccountAdminDisplayContext.getPortletURL()) %>" name="searchFm">
					<liferay-ui:input-search
						markupView="lexicon"
					/>
				</aui:form>
			</li>
		</liferay-frontend:management-bar-filters>

		<liferay-frontend:management-bar-action-buttons>
			<liferay-frontend:management-bar-button
				href='<%= "javascript:" + renderResponse.getNamespace() + "deleteCommerceAccounts();" %>'
				icon="times"
				label="delete"
			/>
		</liferay-frontend:management-bar-action-buttons>
	</liferay-frontend:management-bar>

	<div class="container-fluid-1280">
		<liferay-ui:error exception="<%= CommerceAccountOrdersException.class %>" message="accounts-with-orders-cannot-be-deleted" />

		<portlet:actionURL name="editCommerceAccount" var="editCommerceAccountActionURL" />

		<aui:form action="<%= editCommerceAccountActionURL %>" method="post" name="fm">
			<aui:input name="<%= Constants.CMD %>" type="hidden" value="<%= Constants.DELETE %>" />
			<aui:input name="redirect" type="hidden" value="<%= currentURL %>" />
			<aui:input name="deleteCommerceAccountIds" type="hidden" />

			<liferay-ui:search-container
				id="commerceAccounts"
				searchContainer="<%= commerceAccountSearchContainer %>"
			>
				<liferay-ui:search-container-row
					className="com.liferay.commerce.account.model.CommerceAccount"
					keyProperty="commerceAccountId"
					modelVar="commerceAccount"
				>
					<portlet:renderURL var="rowURL">
						<portlet:param name="mvcRenderCommandName" value="editCommerceAccount" />
						<portlet:param name="redirect" value="<%= currentURL %>" />
						<portlet:param name="commerceAccountId" value="<%= String.valueOf(commerceAccount.getCommerceAccountId()) %>" />
					</portlet:renderURL>

					<%
					long logoId = commerceAccount.getLogoId();
					%>

					<liferay-ui:search-container-column-image
						colspan="<%= 1 %>"
						name="logo"
						src='<%= themeDisplay.getPathImage() + "/organization_logo?img_id=" + logoId + "&t=" + WebServerServletTokenUtil.getToken(logoId) %>'
					/>

					<liferay-ui:search-container-column-text
						cssClass="important table-cell-content"
						href="<%= rowURL %>"
						property="name"
					/>

					<liferay-ui:search-container-column-text
						cssClass="table-cell-content"
						name="active"
						value='<%= LanguageUtil.get(request, commerceAccount.isActive() ? "yes" : "no") %>'
					/>

					<liferay-ui:search-container-column-text
						cssClass="table-cell-content"
						name="type"
						value="<%= LanguageUtil.get(request, CommerceAccountConstants.getAccountTypeLabel(commerceAccount.getType())) %>"
					/>

					<liferay-ui:search-container-column-jsp
						cssClass="entry-action-column"
						path="/account_action.jsp"
					/>
				</liferay-ui:search-container-row>

				<liferay-ui:search-iterator
					markupView="lexicon"
				/>
			</liferay-ui:search-container>
		</aui:form>
	</div>

	<aui:script>
		function <portlet:namespace />deleteCommerceAccounts() {
			if (confirm('<liferay-ui:message key="are-you-sure-you-want-to-delete-the-selected-accounts" />')) {
				var form = AUI.$(document.<portlet:namespace />fm);

				form.fm('deleteCommerceAccountIds').val(Liferay.Util.listCheckedExcept(form, '<portlet:namespace />allRowIds'));

				submitForm(form);
			}
		}
	</aui:script>
</c:if>