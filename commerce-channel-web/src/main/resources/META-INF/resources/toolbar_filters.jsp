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
String searchContainerId = ParamUtil.getString(request, "searchContainerId", "commerceChannelFilters");

CPCatalogRuleDisplayContext cpCatalogRuleDisplayContext = (CPCatalogRuleDisplayContext)request.getAttribute(WebKeys.PORTLET_DISPLAY_CONTEXT);

long commerceChannelId = ParamUtil.getLong(request, "commerceChannelId");
%>

<div class="definition-toolbar-managment-bar">
	<liferay-frontend:management-bar
		includeCheckBox="<%= false %>"
		searchContainerId="<%= searchContainerId %>"
	>
		<liferay-frontend:management-bar-buttons>
			<liferay-frontend:management-bar-display-buttons
				displayViews='<%= new String[] {"list"} %>'
				portletURL="<%= cpCatalogRuleDisplayContext.getPortletURL() %>"
				selectedDisplayStyle="list"
			/>

			<portlet:renderURL var="addCommerceChannelFilterURL">
				<portlet:param name="mvcRenderCommandName" value="editCommerceChannelFilter" />
				<portlet:param name="commerceChannelId" value="<%= String.valueOf(commerceChannelId) %>" />
				<portlet:param name="redirect" value="<%= currentURL %>" />
			</portlet:renderURL>

			<liferay-frontend:add-menu
				inline="<%= true %>"
			>
				<liferay-frontend:add-menu-item
					title='<%= LanguageUtil.get(request, "add-filter") %>'
					url="<%= addCommerceChannelFilterURL.toString() %>"
				/>
			</liferay-frontend:add-menu>
		</liferay-frontend:management-bar-buttons>

		<liferay-frontend:management-bar-filters>
			<liferay-frontend:management-bar-sort
				orderByCol="<%= cpCatalogRuleDisplayContext.getOrderByCol() %>"
				orderByType="<%= cpCatalogRuleDisplayContext.getOrderByType() %>"
				orderColumns='<%= new String[] {"modified-date", "name"} %>'
				portletURL="<%= cpCatalogRuleDisplayContext.getPortletURL() %>"
			/>

			<li>
				<aui:form action="<%= String.valueOf(cpCatalogRuleDisplayContext.getPortletURL()) %>" name="searchFm">
					<liferay-ui:input-search
						markupView="lexicon"
					/>
				</aui:form>
			</li>
		</liferay-frontend:management-bar-filters>
	</liferay-frontend:management-bar>
</div>