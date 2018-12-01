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

String redirect = ParamUtil.getString(request, "redirect");

String backURL = ParamUtil.getString(request, "backURL", redirect);

Process process = dataIntegrationProcessListDisplayContext.getProcess();

long processId = dataIntegrationProcessListDisplayContext.getProcessId();

String title = LanguageUtil.get(request, "add-process");

if (process != null) {
	title = process.getName();
}

Map<String, String> processTypes = (HashMap)request.getAttribute("processTypes");
%>

<liferay-util:include page="/navbar.jsp" servletContext="<%= application %>" />

<%@ include file="/breadcrumb.jspf" %>

<portlet:actionURL name="editProcess" var="editProcessActionURL" />

<div class="closed container-fluid-1280" id="<portlet:namespace />edditProcessId">
	<div class="container main-content-body sheet">
		<aui:form action="<%= editProcessActionURL %>" cssClass="container-fluid-1280" method="post" name="fm">
			<aui:input name="<%= Constants.CMD %>" type="hidden" value="<%= Constants.UPDATE %>" />
			<aui:input name="redirect" type="hidden" value="<%= redirect %>" />
			<aui:input name="processId" type="hidden" value="<%= String.valueOf(processId) %>" />

			<div class="lfr-form-content">
				<c:if test="<%= process != null %>">
					<aui:model-context bean="<%= process %>" model="<%= Process.class %>" />
				</c:if>

				<aui:fieldset>
					<aui:input name="name" required="<%= true %>" />
					<aui:input name="version" />

					<c:if test="<%= processTypes != null %>">
						<aui:select label="process-type" name="processType">
							<%for(String processTypeKey : processTypes.keySet()){ %>
							<aui:option value="<%= processTypeKey %>"><%= processTypeKey %></aui:option>
							<%} %>
						</aui:select>
					</c:if>

					<aui:input name="className" required="<%= true %>" />
					<aui:input name="contextProperties" type="file" />
					<aui:input name="srcArchive" required="<%= true %>" type="file" />
				</aui:fieldset>

				<aui:button-row>
					<aui:button cssClass="btn-lg" type="submit" value="save" />

					<aui:button cssClass="btn-lg" href="<%= backURL %>" type="cancel" />
				</aui:button-row>
			</div>
		</aui:form>
	</div>
</div>