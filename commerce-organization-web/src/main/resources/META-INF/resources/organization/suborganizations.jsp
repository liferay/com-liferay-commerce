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
CommerceOrganizationDisplayContext commerceOrganizationDisplayContext = (CommerceOrganizationDisplayContext)request.getAttribute(WebKeys.PORTLET_DISPLAY_CONTEXT);

request.setAttribute("view.jsp-filterPerOrganization", false);
%>

<div class="commerce-organization-container" id="<portlet:namespace />entriesContainer">
	<commerce-ui:table
		dataProviderKey="<%= CommerceOrganizationClayTable.NAME %>"
		filter="<%= commerceOrganizationDisplayContext.getOrganizationFilter() %>"
		itemPerPage="<%= 5 %>"
		namespace="<%= renderResponse.getNamespace() %>"
		pageNumber="1"
		portletURL="<%= commerceOrganizationDisplayContext.getPortletURL() %>"
		tableName="<%= CommerceOrganizationClayTable.NAME %>"
	/>
</div>

<c:if test="<%= commerceOrganizationDisplayContext.hasAddOrganizationPermissions() %>">
	<div class="commerce-cta is-visible">
		<aui:button cssClass="commerce-button commerce-button--big" name="addOrganizationButton" value="add-organization" />
	</div>

	<portlet:actionURL name="editCommerceOrganization" var="editCommerceOrganizationActionURL">
		<portlet:param name="<%= Constants.CMD %>" value="<%= Constants.ADD %>" />
		<portlet:param name="redirect" value="<%= currentURL %>" />
		<portlet:param name="organizationId" value="<%= String.valueOf(commerceOrganizationDisplayContext.getOrganizationId()) %>" />
	</portlet:actionURL>

	<aui:form action="<%= editCommerceOrganizationActionURL %>" method="post" name="organizationFm">
		<aui:input name="<%= Constants.CMD %>" type="hidden" value="<%= Constants.DELETE %>" />
		<aui:input name="redirect" type="hidden" value="<%= currentURL %>" />
		<aui:input name="organizationId" type="hidden" />
	</aui:form>

	<aui:script require="metal-dom/src/all/dom as dom,frontend-js-web/liferay/modal/commands/OpenSimpleInputModal.es as modalCommands">
		function handleAddOrganizationButtonClick(event) {
			event.preventDefault();

			modalCommands.openSimpleInputModal(
				{
					dialogTitle: '<liferay-ui:message key="add-organization" />',
					formSubmitURL: '<%= editCommerceOrganizationActionURL %>',
					mainFieldLabel: '<liferay-ui:message key="name" />',
					mainFieldName: 'name',
					mainFieldPlaceholder: '<liferay-ui:message key="name" />',
					namespace: '<portlet:namespace />',
					spritemap: '<%= themeDisplay.getPathThemeImages() %>/lexicon/icons.svg'
				}
			);
		}

		function handleDestroyPortlet () {
			addOrganizationButton.removeEventListener('click', handleAddOrganizationButtonClick);

			Liferay.detach('destroyPortlet', handleDestroyPortlet);
		}

		var addOrganizationButton = document.getElementById('<portlet:namespace />addOrganizationButton');

		addOrganizationButton.addEventListener('click', handleAddOrganizationButtonClick);

		Liferay.on('destroyPortlet', handleDestroyPortlet);
	</aui:script>

	<aui:script>
		Liferay.provide(
			window,
			'deleteCommerceOrganization',
			function(id) {
				document.querySelector('#<portlet:namespace />organizationId').value = id;

				submitForm(document.<portlet:namespace />organizationFm);
			}
		);
	</aui:script>
</c:if>