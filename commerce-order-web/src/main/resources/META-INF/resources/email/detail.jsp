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
CommerceOrderEditDisplayContext commerceOrderEditDisplayContext = (CommerceOrderEditDisplayContext)request.getAttribute(WebKeys.PORTLET_DISPLAY_CONTEXT);

CommerceNotificationQueueEntry commerceNotificationQueueEntry = commerceOrderEditDisplayContext.getCommerceNotificationQueueEntry();
%>

<div class="border-0 sheet">
	<div class="commerce-notification-queue-entry-header">

	</div>

	<div class="commerce-notification-queue-entry-type">
		<clay:label
			label="<%= commerceNotificationQueueEntry.getCommerceNotificationTemplateType() %>"
			style="success"
		/>
	</div>

	<div class="commerce-notification-queue-entry-subject">
		<h3><%= commerceNotificationQueueEntry.getSubject() %></h3>
	</div>

	<div class="commerce-notification-queue-entry-body">
		<%= commerceNotificationQueueEntry.getBody() %>
	</div>
</div>