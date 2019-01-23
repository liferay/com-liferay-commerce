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

request.setAttribute("view.jsp-filterPerAccount", false);
%>

<liferay-ui:error exception="<%= UserEmailAddressException.MustValidate.class %>" message="please-enter-a-valid-email-address" />

<div class="commerce-account-container" id="<portlet:namespace />entriesContainer">
	<commerce-ui:table
		dataProviderKey="commerceAccounts"
		filter="<%= commerceAccountDisplayContext.getAccountFilter() %>"
		itemPerPage="<%= 5 %>"
		namespace="<%= renderResponse.getNamespace() %>"
		pageNumber="1"
		portletURL="<%= commerceAccountDisplayContext.getPortletURL() %>"
		tableName="commerceAccounts"
	/>
</div>

<div class="minium-frame__cta is-visible">
	<aui:button cssClass="js-invite-user minium-button minium-button--big" onClick='<%= renderResponse.getNamespace() + "openAddAccountModal();" %>' value="add-account" />
</div>

<portlet:actionURL name="editCommerceAccount" var="editCommerceAccountActionURL" />

<aui:form action="<%= editCommerceAccountActionURL %>" method="post" name="commerceAccountFm">
	<aui:input name="<%= Constants.CMD %>" type="hidden" value="<%= Constants.ADD %>" />
	<aui:input name="active" type="hidden" value="<%= true %>" />
	<aui:input name="name" type="hidden" />
	<aui:input name="userIds" type="hidden" />
	<aui:input name="emailAddresses" type="hidden" />
</aui:form>

<commerce-ui:add-account-modal
	componentId="addAccountModal"
/>

<aui:script>

	Liferay.provide(
		window,
		'<portlet:namespace />openAddAccountModal',
		function(evt) {
			const addAccountModal = Liferay.component('addAccountModal');
			addAccountModal.open();
		}
	);

	Liferay.componentReady('addAccountModal').then(
		function(addAccountModal) {
			addAccountModal.on(
				'AddAccountModalSave',
				function(event) {

					let existingUsersIds = event.administratorsEmail.filter(
						function(el) {
							return el.userId;
						}
					).map(
						function(usr) {
							return usr.userId
						}
					).join(',');

					let newUsersEmails = event.administratorsEmail.filter(
						function(el) {
							return !el.userId;
						}
					).map(
						function(usr) {
							return usr.email
						}
					).join(',');

					document.querySelector('#<portlet:namespace />name').value = event.accountName;
					document.querySelector('#<portlet:namespace />userIds').value = existingUsersIds;
					document.querySelector('#<portlet:namespace />emailAddresses').value = newUsersEmails;

					addAccountModal.close();

					submitForm(document.<portlet:namespace />commerceAccountFm);
				}
			);
		}
	);

</aui:script>