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
CommerceContext commerceContext = (CommerceContext)request.getAttribute(CommerceWebKeys.COMMERCE_CONTEXT);

boolean disabled = true;

if (commerceContext.getCommerceChannelId() > 0) {
	disabled = false;
}
%>

<div class="container-fluid-1280 mt-4 sheet">
	<portlet:actionURL name="editCommerceAccountSettings" var="editCommerceAccountSettingsActionURL" />

	<aui:form action="<%= editCommerceAccountSettingsActionURL %>" method="post" name="fm">
		<aui:input name="redirect" type="hidden" value="<%= currentURL %>" />

		<aui:fieldset>
			<aui:select disabled="<%= disabled %>" label="commerce-site-type" name="settings--commerceSiteType--">

				<%
				for (int commerceSiteType : CommerceAccountConstants.SITE_TYPES) {
				%>

					<aui:option label="<%= CommerceAccountConstants.getSiteTypeLabel(commerceSiteType) %>" selected="<%= commerceSiteType == commerceContext.getCommerceSiteType() %>" value="<%= commerceSiteType %>" />

				<%
				}
				%>

			</aui:select>
		</aui:fieldset>

		<c:choose>
			<c:when test="<%= disabled %>">
				<aui:alert closeable="<%= false %>" cssClass="mt-3" type="warning">
					<liferay-ui:message key="this-site-does-not-have-a-channel" />
				</aui:alert>
			</c:when>
			<c:otherwise>
				<aui:button-row>
					<aui:button cssClass="btn-lg" type="submit" value="save" />
				</aui:button-row>
			</c:otherwise>
		</c:choose>
	</aui:form>
</div>