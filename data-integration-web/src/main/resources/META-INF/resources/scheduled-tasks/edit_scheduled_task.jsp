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

String redirect = ParamUtil.getString(request, "redirect");

String backURL = ParamUtil.getString(request, "backURL", redirect);

ScheduledTask scheduledTask = scheduledTasksDataIntegrationDisplayContext.getScheduledTask();

long scheduledTaskId = scheduledTasksDataIntegrationDisplayContext.getScheduledTaskId();

String title = LanguageUtil.get(request, "add-scheduled-task");
Date defaultDate = new Date();
int startDay, startMonth, startYear;

startDay = defaultDate.getDate();
startYear = defaultDate.getYear() + 1900;
startMonth = defaultDate.getMonth();

if (scheduledTask != null) {
	title = scheduledTask.getName();
	startDay = scheduledTask.getStartDate().getDate();
	startYear = scheduledTask.getStartDate().getYear() + 1900;
	startMonth = scheduledTask.getStartDate().getMonth();

}

List<Process> processList = scheduledTasksDataIntegrationDisplayContext.getProcessList();
%>

<liferay-util:include page="/navbar.jsp" servletContext="<%= application %>" />

<%@ include file="/breadcrumb.jspf" %>

<portlet:actionURL name="editScheduledTask" var="editScheduledTaskActionURL" />
<div class="closed container-fluid-1280" id="<portlet:namespace />editScheduledTaskId">
	<div class="container main-content-body sheet">
		<aui:form action="<%= editScheduledTaskActionURL %>" cssClass="container-fluid-1280" method="post" name="fm">
			<aui:input name="<%= Constants.CMD %>" type="hidden" value="<%= Constants.UPDATE %>" />
			<aui:input name="redirect" type="hidden" value="<%= redirect %>" />
			<aui:input name="scheduledTaskId" type="hidden" value="<%= String.valueOf(scheduledTaskId) %>" />

			<div class="lfr-form-content">
				<c:if test="<%= scheduledTask != null %>">
					<aui:model-context bean="<%= scheduledTask %>" model="<%= ScheduledTask.class %>" />
				</c:if>

				<aui:fieldset>
					<aui:input name="name" required="<%= true %>" />

					<aui:select name="frequency">
						<%for(Frequency frequency : Frequency.values()) { %>
						<aui:option value="<%= frequency.getName() %>"><%= LanguageUtil.get(request, frequency.getName()) %></aui:option>

						<%} %>
					</aui:select>

					<c:if test="<%= processList != null %>">
						<aui:select label="process" name="processId">
							<%for(Process process : processList ) { %>
							<aui:option value="<%= String.valueOf(process.getProcessId()) %>"><%= process.getName() %></aui:option>
							<%} %>
						</aui:select>
					</c:if>

					<label class="control-label">
						<%= LanguageUtil.get(request, "start-date") %>
					</label>

					<liferay-ui:input-date
						dayValue="<%= startDay %>"
						firstEnabledDate="<%= defaultDate %>"
						monthValue="<%= startMonth %>"
						name="startDate"
						yearValue="<%= startYear %>"
					/>

					<aui:select name="startHour">
						<aui:option value="1">1 AM</aui:option>
						<aui:option value="2">2 AM</aui:option>
						<aui:option value="3">3 AM</aui:option>
						<aui:option value="4">4 AM</aui:option>
						<aui:option value="5">5 AM</aui:option>
						<aui:option value="6">6 AM</aui:option>
						<aui:option value="7">7 AM</aui:option>
						<aui:option value="8">8 AM</aui:option>
						<aui:option value="9">9 AM</aui:option>
						<aui:option value="10">10 AM</aui:option>
						<aui:option value="11">11 AM</aui:option>
						<aui:option value="12">12 AM</aui:option>
						<aui:option value="13">1 PM</aui:option>
						<aui:option value="14">2 PM</aui:option>
						<aui:option value="15">3 PM</aui:option>
						<aui:option value="16">4 PM</aui:option>
						<aui:option value="17">5 PM</aui:option>
						<aui:option value="18">6 PM</aui:option>
						<aui:option value="19">7 PM</aui:option>
						<aui:option value="20">8 PM</aui:option>
						<aui:option value="21">9 PM</aui:option>
						<aui:option value="22">10 PM</aui:option>
						<aui:option value="23">11 PM</aui:option>
						<aui:option value="0">12 PM</aui:option>
					</aui:select>
				</aui:fieldset>

				<aui:button-row>
					<aui:button cssClass="btn-lg" type="submit" value="save" />

					<aui:button cssClass="btn-lg" href="<%= backURL %>" type="cancel" />
				</aui:button-row>
			</div>
		</aui:form>
	</div>
</div>