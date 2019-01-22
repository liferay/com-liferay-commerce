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

<c:choose>
	<c:when test="<%= portletName.equals(CommercePortletKeys.COMMERCE_OPEN_ORDER_CONTENT) %>">
		<commerce-ui:table
			dataProviderKey="commerceOrders"
			itemPerPage="<%= 5 %>"
			namespace="<%= renderResponse.getNamespace() %>"
			pageNumber="1"
			portletURL="<%= commerceOrderContentDisplayContext.getPortletURL() %>"
			tableName="commerceOrders"
		/>
	</c:when>
	<c:when test="<%= portletName.equals(CommercePortletKeys.COMMERCE_ORDER_CONTENT) %>">
		<c:choose>
			<c:when test="<%= commerceOrderContentDisplayContext.isCommerceSiteTypeB2C() %>">
				<liferay-util:include page="/b2c/view.jsp" servletContext="<%= application %>" />
			</c:when>
			<c:otherwise>
				<liferay-util:include page="/b2b/view.jsp" servletContext="<%= application %>" />
			</c:otherwise>
		</c:choose>
	</c:when>
	<c:otherwise>
	</c:otherwise>
</c:choose>