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
CommerceChannelDisplayContext commerceChannelDisplayContext = (CommerceChannelDisplayContext)request.getAttribute(WebKeys.PORTLET_DISPLAY_CONTEXT);

ResultRow row = (ResultRow)request.getAttribute(WebKeys.SEARCH_CONTAINER_RESULT_ROW);

CommerceChannel commerceChannel = (CommerceChannel)row.getObject();

long commerceChannelId = commerceChannel.getCommerceChannelId();
%>

<liferay-ui:icon-menu
	direction="left-side"
	icon="<%= StringPool.BLANK %>"
	markupView="lexicon"
	message="<%= StringPool.BLANK %>"
	showWhenSingleIcon="<%= true %>"
>
	<liferay-ui:icon
		message='<%= commerceChannelDisplayContext.hasPermission(commerceChannelId, ActionKeys.UPDATE) ? "edit" : "view" %>'
		url="<%= commerceChannelDisplayContext.getChannelURL(commerceChannel) %>"
	/>

	<c:if test="<%= commerceChannelDisplayContext.hasPermission(commerceChannelId, ActionKeys.DELETE) %>">
		<portlet:actionURL name="editCommerceChannel" var="deleteURL">
			<portlet:param name="<%= Constants.CMD %>" value="<%= Constants.DELETE %>" />
			<portlet:param name="commerceChannelId" value="<%= String.valueOf(commerceChannelId) %>" />
		</portlet:actionURL>

		<liferay-ui:icon-delete
			message="delete"
			url="<%= deleteURL %>"
		/>
	</c:if>

	<c:if test="<%= commerceChannelDisplayContext.hasPermission(commerceChannelId, ActionKeys.PERMISSIONS) %>">
		<liferay-security:permissionsURL
			modelResource="<%= CommerceChannel.class.getName() %>"
			modelResourceDescription="<%= commerceChannel.getName() %>"
			resourcePrimKey="<%= String.valueOf(commerceChannelId) %>"
			var="permissionsChannelURL"
			windowState="<%= LiferayWindowState.POP_UP.toString() %>"
		/>

		<liferay-ui:icon
			message="permissions"
			method="get"
			url="<%= permissionsChannelURL %>"
			useDialog="<%= true %>"
		/>
	</c:if>
</liferay-ui:icon-menu>