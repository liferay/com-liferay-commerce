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
CPDefinitionLinkDisplayContext cpDefinitionLinkDisplayContext = (CPDefinitionLinkDisplayContext)request.getAttribute(WebKeys.PORTLET_DISPLAY_CONTEXT);

ResultRow row = (ResultRow)request.getAttribute(WebKeys.SEARCH_CONTAINER_RESULT_ROW);

CPDefinitionLink cpDefinitionLink = null;

if (row != null) {
	cpDefinitionLink = (CPDefinitionLink)row.getObject();
}
else {
	cpDefinitionLink = (CPDefinitionLink)request.getAttribute("info_panel.jsp-entry");
}
%>

<c:if test="<%= CommerceCatalogPermission.contains(permissionChecker, cpDefinitionLinkDisplayContext.getCPDefinition(), ActionKeys.UPDATE) %>">
	<liferay-ui:icon-menu
		direction="left-side"
		icon="<%= StringPool.BLANK %>"
		markupView="lexicon"
		message="<%= StringPool.BLANK %>"
		showWhenSingleIcon="<%= true %>"
	>
		<portlet:renderURL var="editURL">
			<portlet:param name="mvcRenderCommandName" value="editCPDefinitionLink" />
			<portlet:param name="cpDefinitionId" value="<%= String.valueOf(cpDefinitionLink.getCPDefinitionId()) %>" />
			<portlet:param name="cpDefinitionLinkId" value="<%= String.valueOf(cpDefinitionLink.getCPDefinitionLinkId()) %>" />
			<portlet:param name="type" value="<%= String.valueOf(cpDefinitionLink.getType()) %>" />
		</portlet:renderURL>

		<liferay-ui:icon
			message="edit"
			url="<%= editURL %>"
		/>

		<portlet:actionURL name="editCPDefinitionLink" var="deleteURL">
			<portlet:param name="<%= Constants.CMD %>" value="<%= Constants.DELETE %>" />
			<portlet:param name="redirect" value="<%= currentURL %>" />
			<portlet:param name="cpDefinitionId" value="<%= String.valueOf(cpDefinitionLink.getCPDefinitionId()) %>" />
			<portlet:param name="cpDefinitionLinkId" value="<%= String.valueOf(cpDefinitionLink.getCPDefinitionLinkId()) %>" />
			<portlet:param name="type" value="<%= String.valueOf(cpDefinitionLink.getType()) %>" />
		</portlet:actionURL>

		<liferay-ui:icon-delete
			url="<%= deleteURL %>"
		/>
	</liferay-ui:icon-menu>
</c:if>