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
CommerceOrganizationDisplayContext commerceOrganizationDisplayContext = (CommerceOrganizationDisplayContext)request.getAttribute(WebKeys.PORTLET_DISPLAY_CONTEXT);

Organization organization = commerceOrganizationDisplayContext.getCurrentOrganization();
String keywords = commerceOrganizationDisplayContext.getKeywords();
PortletURL portletURL = commerceOrganizationDisplayContext.getPortletURL();
%>

<c:choose>
	<c:when test="<%= commerceOrganizationDisplayContext.isSelectedOrganization() %>">
		<%@ include file="/organization_details.jspf" %>
	</c:when>
	<c:otherwise>
		<%@ include file="/view_organizations.jspf" %>
	</c:otherwise>
</c:choose>