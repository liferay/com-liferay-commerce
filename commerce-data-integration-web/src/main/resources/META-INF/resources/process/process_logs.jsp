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
CommerceDataIntegrationProcessLogDisplayContext commerceDataIntegrationProcessLogDisplayContext = (CommerceDataIntegrationProcessLogDisplayContext)request.getAttribute(WebKeys.PORTLET_DISPLAY_CONTEXT);

CommerceDataIntegrationProcess commerceDataIntegrationProcess = commerceDataIntegrationProcessLogDisplayContext.getCommerceDataIntegrationProcess();
PortletURL portletURL = commerceDataIntegrationProcessLogDisplayContext.getPortletURL();

portletURL.setParameter("searchContainerId", "commerceDataIntegrationProcessLogs");

request.setAttribute("view.jsp-portletURL", portletURL);
%>

<liferay-util:include page="/process_log_toolbar.jsp" servletContext="<%= application %>">
	<liferay-util:param name="searchContainerId" value="commerceDataIntegrationProcessLogs" />
</liferay-util:include>

<div id="<portlet:namespace />processLogsContainer">
	<div class="closed container-fluid-1280" id="<portlet:namespace />infoPanelId">
		<aui:form action="<%= portletURL.toString() %>" method="post" name="fm">
			<aui:input name="<%= Constants.CMD %>" type="hidden" />
			<aui:input name="redirect" type="hidden" value="<%= portletURL.toString() %>" />
			<aui:input name="deleteCDataIntegrationProcessLogIds" type="hidden" />

			<div class="process-lists-container" id="<portlet:namespace />entriesContainer">
				<liferay-ui:search-container
					id="commerceDataIntegrationProcessLogs"
					searchContainer="<%= commerceDataIntegrationProcessLogDisplayContext.getSearchContainer() %>"
				>
					<liferay-ui:search-container-row
						className="com.liferay.commerce.data.integration.model.CommerceDataIntegrationProcessLog"
						cssClass="entry-display-style"
						keyProperty="commerceDataIntegrationProcessLogId"
						modelVar="commerceDataIntegrationProcessLog"
					>

						<%
						PortletURL rowURL = renderResponse.createRenderURL();

						rowURL.setParameter("mvcRenderCommandName", "viewCommerceDataIntegrationProcessLog");
						rowURL.setParameter("redirect", currentURL);
						rowURL.setParameter("cDataIntegrationProcessLogId", String.valueOf(commerceDataIntegrationProcessLog.getCommerceDataIntegrationProcessLogId()));
						%>

						<liferay-ui:search-container-column-text
							cssClass="important table-cell-content"
							href="<%= rowURL %>"
							name="start-date"
						>
							<%= commerceDataIntegrationProcessLogDisplayContext.getFormattedDate(commerceDataIntegrationProcessLog.getStartDate()) %>
						</liferay-ui:search-container-column-text>

						<liferay-ui:search-container-column-text
							cssClass="table-cell-content"
							name="runtime"
						>
							<%= (commerceDataIntegrationProcessLog.getEndDate() == null) ? StringPool.DASH : String.valueOf(commerceDataIntegrationProcessLog.getEndDate().getTime() - commerceDataIntegrationProcessLog.getStartDate().getTime()) + " ms" %>
						</liferay-ui:search-container-column-text>

						<liferay-ui:search-container-column-text
							cssClass="table-cell-content"
							name="process"
							value="<%= HtmlUtil.escape(commerceDataIntegrationProcess.getName()) %>"
						/>

						<liferay-ui:search-container-column-text
							name="status"
						>
							<h6 class="background-task-status-row background-task-status-<%= BackgroundTaskConstants.getStatusLabel(commerceDataIntegrationProcessLog.getStatus()) %> <%= BackgroundTaskConstants.getStatusCssClass(commerceDataIntegrationProcessLog.getStatus()) %>">
								<liferay-ui:message key="<%= BackgroundTaskConstants.getStatusLabel(commerceDataIntegrationProcessLog.getStatus()) %>" />
							</h6>
						</liferay-ui:search-container-column-text>

						<liferay-ui:search-container-column-jsp
							cssClass="entry-action-column"
							path="/process_log_action.jsp"
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