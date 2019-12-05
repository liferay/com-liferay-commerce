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
CommerceShipmentDisplayContext commerceShipmentDisplayContext = (CommerceShipmentDisplayContext)request.getAttribute(WebKeys.PORTLET_DISPLAY_CONTEXT);

ResultRow row = (ResultRow)request.getAttribute(WebKeys.SEARCH_CONTAINER_RESULT_ROW);

CommerceShipment commerceShipment = (CommerceShipment)row.getObject();

long[] commerceOrderIds = commerceShipmentDisplayContext.getCommerceOrderIds(commerceShipment.getCommerceShipmentId());

for (long commerceOrderId : commerceOrderIds) {
%>

	<aui:a cssClass="important" href="<%= commerceShipmentDisplayContext.getCommerceOrderUrl(commerceOrderId) %>">
		<%= String.valueOf(commerceOrderId) %>
	</aui:a>

<%
}
%>