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
ScheduledTasksDataIntegrationDisplayContext scheduledTasksDataIntegrationDisplayContext = (ScheduledTasksDataIntegrationDisplayContext)request.getAttribute(WebKeys.PORTLET_DISPLAY_CONTEXT);

ResultRow row = (ResultRow)request.getAttribute(WebKeys.SEARCH_CONTAINER_RESULT_ROW);

ScheduledTask scheduledTask = (ScheduledTask)row.getObject();
String runTaskURL = scheduledTasksDataIntegrationDisplayContext.getRunScheduledTaskURL(scheduledTask.getScheduledTaskId());
boolean disabled = scheduledTask.isActive();
String iconSpinnerCssClass = disabled ? "icon-spinner icon-spin" : "hide icon-spinner icon-spin";
%>

<span aria-hidden="true" class="<%= iconSpinnerCssClass %>"></span>
<aui:button cssClass="btn-lg" disabled="<%= disabled %>" href="<%= runTaskURL %>" type="cancel" value="run-now" />