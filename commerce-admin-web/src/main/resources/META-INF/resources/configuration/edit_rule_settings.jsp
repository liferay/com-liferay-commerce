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
int catalogRuleApplicationType = (Integer)request.getAttribute("catalogRuleApplicationType");
%>

<div class="container-fluid-1280 mt-4 sheet">
	<portlet:actionURL name="editCPRuleSettings" var="editCPRuleSettingsActionURL" />

	<aui:form action="<%= editCPRuleSettingsActionURL %>" method="post" name="fm">
		<aui:input name="redirect" type="hidden" value="<%= currentURL %>" />

		<aui:fieldset>
			<aui:select label="catalog-rule-application-type" name="settings--catalogRuleApplicationType--">

				<%
				for (int applicationType : CPRuleConstants.APPLICATION_TYPES) {
				%>

					<aui:option label="<%= CPRuleConstants.getApplicationTypeLabel(applicationType) %>" selected="<%= applicationType == catalogRuleApplicationType %>" value="<%= applicationType %>" />

				<%
				}
				%>

			</aui:select>
		</aui:fieldset>

		<aui:button-row>
			<aui:button cssClass="btn-lg" type="submit" value="save" />
		</aui:button-row>
	</aui:form>
</div>