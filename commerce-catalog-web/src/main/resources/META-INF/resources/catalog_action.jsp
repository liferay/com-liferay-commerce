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
CommerceCatalogDisplayContext commerceCatalogDisplayContext = (CommerceCatalogDisplayContext)request.getAttribute(WebKeys.PORTLET_DISPLAY_CONTEXT);

ResultRow row = (ResultRow)request.getAttribute(WebKeys.SEARCH_CONTAINER_RESULT_ROW);

CommerceCatalog commerceCatalog = (CommerceCatalog)row.getObject();

long commerceCatalogId = commerceCatalog.getCommerceCatalogId();

boolean hasUpdatePermissions = commerceCatalogDisplayContext.hasPermission(commerceCatalogId, ActionKeys.UPDATE);
%>

<liferay-ui:icon-menu
	direction="left-side"
	icon="<%= StringPool.BLANK %>"
	markupView="lexicon"
	message="<%= StringPool.BLANK %>"
	showWhenSingleIcon="<%= true %>"
>
	<liferay-ui:icon
		message='<%= (hasUpdatePermissions && !commerceCatalog.isSystem()) ? "edit" : "view" %>'
		url="<%= commerceCatalogDisplayContext.getCatalogURL(commerceCatalog) %>"
	/>

	<c:if test="<%= commerceCatalogDisplayContext.hasPermission(commerceCatalogId, ActionKeys.PERMISSIONS) %>">
		<liferay-security:permissionsURL
			modelResource="<%= CommerceCatalog.class.getName() %>"
			modelResourceDescription="<%= commerceCatalog.getName() %>"
			resourcePrimKey="<%= String.valueOf(commerceCatalogId) %>"
			var="permissionsCatalogURL"
			windowState="<%= LiferayWindowState.POP_UP.toString() %>"
		/>

		<liferay-ui:icon
			message="permissions"
			method="get"
			url="<%= permissionsCatalogURL %>"
			useDialog="<%= true %>"
		/>
	</c:if>

	<c:if test="<%= commerceCatalogDisplayContext.hasPermission(commerceCatalogId, ActionKeys.DELETE) && !commerceCatalog.isSystem() %>">
		<portlet:actionURL name="editCommerceCatalog" var="deleteURL">
			<portlet:param name="<%= Constants.CMD %>" value="<%= Constants.DELETE %>" />
			<portlet:param name="commerceCatalogId" value="<%= String.valueOf(commerceCatalogId) %>" />
		</portlet:actionURL>

		<liferay-ui:icon-delete
			message="delete"
			url="<%= deleteURL %>"
		/>
	</c:if>
</liferay-ui:icon-menu>