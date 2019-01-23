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
CommerceAccountDisplayContext commerceAccountDisplayContext = (CommerceAccountDisplayContext)request.getAttribute(WebKeys.PORTLET_DISPLAY_CONTEXT);
%>

<div class="minium-frame__cta is-visible">
	<aui:button cssClass="js-invite-user minium-button minium-button--big" onClick='<%= renderResponse.getNamespace() + "openAddOrganizationsModal();" %>' value="add-organizations" />
</div>

<commerce-ui:add-organizations-modal
	componentId="addOrganizationsModal"
/>

<div class="container-fluid-1280">
	<commerce-ui:table
		dataProviderKey="<%= CommerceAccountOrganizationClayTable.NAME %>"
		itemPerPage="<%= 5 %>"
		namespace="<%= renderResponse.getNamespace() %>"
		pageNumber="1"
		portletURL="<%= commerceAccountDisplayContext.getPortletURL() %>"
		tableName="<%= CommerceAccountOrganizationClayTable.NAME %>"
	/>
</div>

<portlet:actionURL name="editCommerceAccountOrganizationRel" var="editCommerceAccountOrganizationRelActionURL" />

<aui:form action="<%= editCommerceAccountOrganizationRelActionURL %>" method="post" name="commerceAccountOrganizationRelFm">
	<aui:input name="<%= Constants.CMD %>" type="hidden" value="<%= Constants.ASSIGN %>" />
	<aui:input name="redirect" type="hidden" value="<%= currentURL %>" />
	<aui:input name="commerceAccountId" type="hidden" value="<%= commerceAccountDisplayContext.getCurrentCommerceAccountId() %>" />
	<aui:input name="addOrganizationIds" type="hidden" />
</aui:form>

<aui:script>
	Liferay.provide(
		window,
		'<portlet:namespace />openAddOrganizationsModal',
		function(evt) {
			const addOrganizationsModal = Liferay.component('addOrganizationsModal');

			addOrganizationsModal.open();
		}
	);

	Liferay.componentReady('addOrganizationsModal').then(
		function(addOrganizationsModal) {
			addOrganizationsModal.on(
				'addOrganization',
				function(event) {
					let orgIds = event.map(
						function(org) {
							return org.id
						}
					).join(',');

					document.querySelector('#<portlet:namespace />addOrganizationIds').value = orgIds;

					addOrganizationsModal.close();

					submitForm(document.<portlet:namespace />commerceAccountOrganizationRelFm);
				}
			);
		}
	);

</aui:script>