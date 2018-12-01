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
HistoryDataIntegrationDisplayContext historyDataIntegrationDisplayContext = (HistoryDataIntegrationDisplayContext)request.getAttribute(WebKeys.PORTLET_DISPLAY_CONTEXT);

PortletURL portletURL = historyDataIntegrationDisplayContext.getPortletURL();

portletURL.setParameter("searchContainerId", "histories");

request.setAttribute("view.jsp-portletURL", portletURL);
%>

<c:if test="<%= historyDataIntegrationDisplayContext.hasAdminPermission() %>">
	<liferay-util:include page="/history_toolbar.jsp" servletContext="<%= application %>">
		<liferay-util:param name="searchContainerId" value="histories" />
	</liferay-util:include>

	<div id="<portlet:namespace />processesContainer">
		<div class="closed container-fluid-1280" id="<portlet:namespace />infoPanelId">
			<div class="container">
				<aui:form action="<%= portletURL.toString() %>" method="post" name="fm">
					<aui:input name="<%= Constants.CMD %>" type="hidden" />
					<aui:input name="redirect" type="hidden" value="<%= portletURL.toString() %>" />
					<aui:input name="deleteHistoryIds" type="hidden" />

					<div class="process-lists-container" id="<portlet:namespace />entriesContainer">
						<liferay-ui:search-container
							id="histories"
							searchContainer="<%= historyDataIntegrationDisplayContext.getSearchContainer() %>"
						>
							<liferay-ui:search-container-row
								className="com.liferay.commerce.data.integration.manager.model.History"
								cssClass="entry-display-style"
								keyProperty="historyId"
								modelVar="history"
							>

								<%
								PortletURL rowURL = renderResponse.createRenderURL();

								rowURL.setParameter("mvcRenderCommandName", "viewHistoryDetails");
								rowURL.setParameter("redirect", currentURL);
								rowURL.setParameter("historyId", String.valueOf(history.getHistoryId()));
								%>

								<liferay-ui:search-container-column-text
									cssClass="important table-cell-content"
									href="<%= rowURL %>"
									name="start-date"
								>
									<%= historyDataIntegrationDisplayContext.getFormattedDate(history.getStartDate()) %>
								</liferay-ui:search-container-column-text>

								<liferay-ui:search-container-column-text
									cssClass="table-cell-content"
									name="process"
									property="scheduledTaskName"
								/>

								<liferay-ui:search-container-column-text
									cssClass="table-cell-content"
									name="launch-type"
									property="launchType"
								/>

								<liferay-ui:search-container-column-status
									cssClass="table-cell-content"
									name="status"
									property="status"
								/>

								<liferay-ui:search-container-column-jsp
									cssClass="entry-action-column"
									path="/history/historical_task_action.jsp"
								/>
							</liferay-ui:search-container-row>

							<liferay-ui:search-iterator
								displayStyle="list"
								markupView="lexicon"
							/>
						</liferay-ui:search-container>
					</div>
				</aui:form>
			</div>
		</div>
	</div>
</c:if>