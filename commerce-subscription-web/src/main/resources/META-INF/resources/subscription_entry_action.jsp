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
CommerceSubscriptionEntryDisplayContext commerceSubscriptionEntryDisplayContext = (CommerceSubscriptionEntryDisplayContext)request.getAttribute(WebKeys.PORTLET_DISPLAY_CONTEXT);

ResultRow row = (ResultRow)request.getAttribute(WebKeys.SEARCH_CONTAINER_RESULT_ROW);

CommerceSubscriptionEntry commerceSubscriptionEntry = (CommerceSubscriptionEntry)row.getObject();
%>

<liferay-ui:icon-menu
	direction="left-side"
	icon="<%= StringPool.BLANK %>"
	markupView="lexicon"
	message="<%= StringPool.BLANK %>"
	showWhenSingleIcon="<%= true %>"
>
	<c:if test="<%= commerceSubscriptionEntryDisplayContext.hasManageCommerceSubscriptionEntryPermission() %>">
		<portlet:renderURL var="editURL">
			<portlet:param name="mvcRenderCommandName" value="editCommerceSubscriptionEntry" />
			<portlet:param name="redirect" value="<%= currentURL %>" />
			<portlet:param name="commerceSubscriptionEntryId" value="<%= String.valueOf(commerceSubscriptionEntry.getCommerceSubscriptionEntryId()) %>" />
		</portlet:renderURL>

		<liferay-ui:icon
			message="edit"
			url="<%= editURL %>"
		/>

		<portlet:actionURL name="editCommerceSubscriptionEntry" var="setActiveURL">
			<portlet:param name="<%= Constants.CMD %>" value="setActive" />
			<portlet:param name="redirect" value="<%= currentURL %>" />
			<portlet:param name="commerceSubscriptionEntryId" value="<%= String.valueOf(commerceSubscriptionEntry.getCommerceSubscriptionEntryId()) %>" />
			<portlet:param name="active" value="<%= String.valueOf(!commerceSubscriptionEntry.isActive()) %>" />
		</portlet:actionURL>

		<liferay-ui:icon
			message='<%= commerceSubscriptionEntry.isActive() ? "deactivate" : "activate" %>'
			url="<%= setActiveURL %>"
		/>

		<liferay-ui:icon
			message="edit"
			url="<%= editURL %>"
		/>

		<portlet:actionURL name="editCommerceSubscriptionEntry" var="deleteURL">
			<portlet:param name="<%= Constants.CMD %>" value="<%= Constants.DELETE %>" />
			<portlet:param name="redirect" value="<%= currentURL %>" />
			<portlet:param name="commerceSubscriptionEntryId" value="<%= String.valueOf(commerceSubscriptionEntry.getCommerceSubscriptionEntryId()) %>" />
		</portlet:actionURL>

		<liferay-ui:icon-delete
			url="<%= deleteURL %>"
		/>
	</c:if>
</liferay-ui:icon-menu>