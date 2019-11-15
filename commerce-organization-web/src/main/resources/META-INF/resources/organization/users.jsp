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

PortletURL portletURL = currentURLObj;

portletURL.setParameter(PortletQName.PUBLIC_RENDER_PARAMETER_NAMESPACE + "backURL", backURL);
%>

<commerce-ui:table
	dataProviderKey="<%= CommerceOrganizationUserClayTable.NAME %>"
	filter="<%= commerceOrganizationDisplayContext.getOrganizationFilter() %>"
	itemPerPage="<%= 5 %>"
	namespace="<%= renderResponse.getNamespace() %>"
	pageNumber="1"
	portletURL="<%= commerceOrganizationDisplayContext.getPortletURL() %>"
	tableName="<%= CommerceOrganizationUserClayTable.NAME %>"
/>

<c:if test="<%= OrganizationPermissionUtil.contains(permissionChecker, commerceOrganizationDisplayContext.getOrganizationId(), ActionKeys.ASSIGN_MEMBERS) %>">
	<div class="commerce-cta is-visible">
		<aui:button cssClass="commerce-button commerce-button--big js-invite-user" onClick='<%= renderResponse.getNamespace() + "openUserInvitationModal();" %>' value="invite-user" />
	</div>

	<commerce-ui:user-invitation-modal
		componentId="userInvitationModal"
	/>

	<portlet:actionURL name="inviteUser" var="inviteUserActionURL" />

	<aui:form action="<%= inviteUserActionURL %>" method="post" name="inviteUserFm">
		<aui:input name="<%= Constants.CMD %>" type="hidden" value="<%= Constants.ASSIGN %>" />
		<aui:input name="redirect" type="hidden" value="<%= portletURL %>" />
		<aui:input name="organizationId" type="hidden" value="<%= commerceOrganizationDisplayContext.getOrganizationId() %>" />
		<aui:input name="userId" type="hidden" />
		<aui:input name="userIds" type="hidden" />
		<aui:input name="emailAddresses" type="hidden" />
	</aui:form>

	<aui:script>
		Liferay.provide(
			window,
			'<portlet:namespace />openUserInvitationModal',
			function(evt) {
				const userInvitationModal = Liferay.component('userInvitationModal');
				userInvitationModal.open();
			}
		);

		Liferay.provide(
			window,
			'deleteCommerceOrganizationUser',
			function(id) {
				document.querySelector('#<portlet:namespace /><%= Constants.CMD %>').value = '<%= Constants.REMOVE %>';
				document.querySelector('#<portlet:namespace />userId').value = id;

				submitForm(document.<portlet:namespace />inviteUserFm);
			}
		);

		Liferay.componentReady('userInvitationModal').then(
			function(userInvitationModal) {
				userInvitationModal.on(
					'inviteUserToAccount',
					function(users) {
						let existingUsersIds = users.filter(
							function(el) {
								return el.userId
							}
						).map(
							function(usr) {
								return usr.userId
							}
						).join(',');

						let newUsersEmails = users.filter(
							function(el) {
								return !el.userId
							}
						).map(
							function(usr) {
								return usr.email
							}
						).join(',');

						document.querySelector('#<portlet:namespace />userIds').value = existingUsersIds;
						document.querySelector('#<portlet:namespace />emailAddresses').value = newUsersEmails;

						userInvitationModal.close();

						submitForm(document.<portlet:namespace />inviteUserFm);
					}
				);
			}
		);
	</aui:script>
</c:if>