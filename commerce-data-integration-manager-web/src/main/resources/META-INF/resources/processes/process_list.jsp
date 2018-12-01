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
DataIntegrationProcessListDisplayContext dataIntegrationProcessListDisplayContext = (DataIntegrationProcessListDisplayContext)request.getAttribute(WebKeys.PORTLET_DISPLAY_CONTEXT);

PortletURL portletURL = dataIntegrationProcessListDisplayContext.getPortletURL();

portletURL.setParameter("searchContainerId", "processList");

request.setAttribute("view.jsp-portletURL", portletURL);
%>

<c:if test="<%= dataIntegrationProcessListDisplayContext.hasAdminPermission() %>">
	<liferay-util:include page="/process_toolbar.jsp" servletContext="<%= application %>">
		<liferay-util:param name="searchContainerId" value="processList" />
	</liferay-util:include>

	<div id="<portlet:namespace />processesContainer">
		<div class="closed container-fluid-1280" id="<portlet:namespace />infoPanelId">
			<div class="container">
				<aui:form action="<%= portletURL.toString() %>" method="post" name="fm">
					<aui:input name="<%= Constants.CMD %>" type="hidden" />
					<aui:input name="redirect" type="hidden" value="<%= portletURL.toString() %>" />
					<aui:input name="deleteProcessIds" type="hidden" />

					<div class="process-lists-container" id="<portlet:namespace />entriesContainer">
						<liferay-ui:search-container
							id="processList"
							searchContainer="<%= dataIntegrationProcessListDisplayContext.getSearchContainer() %>"
						>
							<liferay-ui:search-container-row
								className="com.liferay.commerce.data.integration.manager.model.Process"
								cssClass="entry-display-style"
								keyProperty="processId"
								modelVar="process"
							>

								<%
								PortletURL rowURL = renderResponse.createRenderURL();

								rowURL.setParameter("mvcRenderCommandName", "editProcess");
								rowURL.setParameter("redirect", currentURL);
								rowURL.setParameter("processId", String.valueOf(process.getProcessId()));
								rowURL.setParameter("dataIntegrationAdminModuleKey", ProcessListAdminModule.KEY);
								%>

								<liferay-ui:search-container-column-text
									cssClass="important table-cell-content"
									href="<%= rowURL %>"
									property="name"
								/>

								<liferay-ui:search-container-column-text
									cssClass="table-cell-content"
									name="name"
									property="name"
								/>

								<liferay-ui:search-container-column-text
									cssClass="table-cell-content"
									name="version"
									property="version"
								/>

								<liferay-ui:search-container-column-text
									cssClass="table-cell-content"
									name="process-type"
									property="processType"
								/>

								<liferay-ui:search-container-column-date
									cssClass="table-cell-content"
									name="last-update"
									property="modifiedDate"
								/>

								<liferay-ui:search-container-column-jsp
									cssClass="entry-action-column"
									path="/processes/process_list_action.jsp"
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