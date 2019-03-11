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
CommerceOrderContentDisplayContext commerceOrderContentDisplayContext = (CommerceOrderContentDisplayContext)request.getAttribute(WebKeys.PORTLET_DISPLAY_CONTEXT);
%>

<portlet:actionURL name="editCommerceOrder" var="editCommerceOrderURL" />

<commerce-ui:table
	dataProviderKey="commercePendingOrders"
	filter="<%= commerceOrderContentDisplayContext.getOrderFilter() %>"
	itemPerPage="<%= 5 %>"
	namespace="<%= renderResponse.getNamespace() %>"
	pageNumber="1"
	portletURL="<%= commerceOrderContentDisplayContext.getPortletURL() %>"
	tableName="commercePendingOrders"
/>

<div class="commerce-cta is-visible">
	<c:if test="<%= commerceOrderContentDisplayContext.hasPermission(CommerceOrderActionKeys.ADD_COMMERCE_ORDER) %>">
		<aui:form action="<%= editCommerceOrderURL %>" method="post" name="fm">
			<aui:input name="<%= Constants.CMD %>" type="hidden" value="<%= Constants.ADD %>" />
			<aui:input name="redirect" type="hidden" value="<%= currentURL %>" />
			<aui:input name="deleteCommerceOrderIds" type="hidden" />
			<aui:button cssClass="commerce-button commerce-button--big" type="submit" value="add-order" />
		</aui:form>
	</c:if>
</div>