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
String channelNavigationItem = ParamUtil.getString(request, "channelNavigationItem", "view-filters");

CPCatalogRuleDisplayContext cpCatalogRuleDisplayContext = (CPCatalogRuleDisplayContext)request.getAttribute(WebKeys.PORTLET_DISPLAY_CONTEXT);

PortletURL portletURL = cpCatalogRuleDisplayContext.getPortletURL();

SearchContainer commerceChannelFiltersSearchContainer = cpCatalogRuleDisplayContext.getSearchContainer();

long commerceChannelId = ParamUtil.getLong(request, "commerceChannelId");
%>

<%@ include file="/navbar_definitions.jspf" %>

<liferay-util:include page="/toolbar_filters.jsp" servletContext="<%= application %>">
	<liferay-util:param name="searchContainerId" value="commerceChannelFilters" />
</liferay-util:include>

<portlet:actionURL name="editCommerceChannelFilter" var="editCommerceChannelFilterURL" />

<div class="closed container-fluid-1280 sidenav-container sidenav-right" id="<portlet:namespace />infoPanelId">
	<div class="sidenav-content">
		<aui:form action="<%= editCommerceChannelFilterURL %>" method="post" name="fm">
			<aui:input name="<%= Constants.CMD %>" type="hidden" value="<%= Constants.ADD %>" />
			<aui:input name="redirect" type="hidden" value="<%= portletURL.toString() %>" />
			<aui:input name="cpRuleIds" type="hidden" />

			<div class="products-container" id="<portlet:namespace />commerceCatalogsContainer">
				<liferay-ui:search-container
					emptyResultsMessage="no-filters-were-found"
					id="commerceChannelFilters"
					searchContainer="<%= commerceChannelFiltersSearchContainer %>"
				>
					<liferay-ui:search-container-row
						className="com.liferay.commerce.product.model.CPRule"
						cssClass="entry-display-style"
						keyProperty="cpRuleId"
						modelVar="cpRule"
					>
						<liferay-ui:search-container-column-text
							cssClass="important table-cell-content"
							href="<%= cpCatalogRuleDisplayContext.getCPRuleURL(cpRule) %>"
							name="name"
							value="<%= HtmlUtil.escape(LanguageUtil.get(request, cpRule.getName())) %>"
						/>

						<%
						CPRuleType cpRuleType = cpCatalogRuleDisplayContext.getCPRuleType(cpRule.getType());
						%>

						<liferay-ui:search-container-column-text
							cssClass="important table-cell-content"
							href="<%= cpCatalogRuleDisplayContext.getCPRuleURL(cpRule) %>"
							name="type"
							value="<%= HtmlUtil.escape(cpRuleType.getLabel(locale)) %>"
						/>

						<liferay-ui:search-container-column-jsp
							cssClass="entry-action-column"
							path="/filter_action.jsp"
						/>
					</liferay-ui:search-container-row>

					<liferay-ui:search-iterator
						displayStyle="list"
						markupView="lexicon"
					/>
				</liferay-ui:search-container>
			</div>
		</aui:form>
	</div>
</div>