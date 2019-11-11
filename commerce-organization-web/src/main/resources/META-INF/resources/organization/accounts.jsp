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
%>

<div class="commerce-organization-container" id="<portlet:namespace />entriesContainer">
	<commerce-ui:table
		dataProviderKey="<%= CommerceOrganizationAccountClayTable.NAME %>"
		filter="<%= commerceOrganizationDisplayContext.getOrganizationFilter() %>"
		itemPerPage="<%= 5 %>"
		namespace="<%= renderResponse.getNamespace() %>"
		pageNumber="1"
		portletURL="<%= commerceOrganizationDisplayContext.getPortletURL() %>"
		tableName="<%= CommerceOrganizationAccountClayTable.NAME %>"
	/>
</div>

<c:if test="<%= OrganizationPermissionUtil.contains(permissionChecker, commerceOrganizationDisplayContext.getOrganizationId(), ActionKeys.UPDATE) %>">
	<portlet:actionURL name="editCommerceAccountOrganizationRel" var="editCommerceAccountOrganizationRelActionURL" />

	<aui:form action="<%= editCommerceAccountOrganizationRelActionURL %>" method="post" name="commerceAccountOrganizationRelFm">
		<aui:input name="<%= Constants.CMD %>" type="hidden" />
		<aui:input name="redirect" type="hidden" value="<%= currentURL %>" />
		<aui:input name="organizationId" type="hidden" />
	</aui:form>

	<aui:script>
		Liferay.provide(
		window,
		'deleteCommerceOrganizationAccount',
			function(id) {
				document.querySelector('#<portlet:namespace /><%= Constants.CMD %>').value = '<%= Constants.REMOVE %>';
				document.querySelector('#<portlet:namespace />organizationId').value = id;

				submitForm(document.<portlet:namespace />commerceAccountOrganizationRelFm);
			}
		);
	</aui:script>
</c:if>