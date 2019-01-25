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

CommerceAccount commerceAccount = commerceAccountDisplayContext.getCurrentCommerceAccount();
User selectedUser = commerceAccountDisplayContext.getSelectedUser();
PortletURL portletURL = commerceAccountDisplayContext.getPortletURL();

portletURL.setParameter("mvcRenderCommandName", "viewCommerceAccountUser");
%>

<portlet:renderURL var="editCommerceAccountURL">
	<portlet:param name="mvcRenderCommandName" value="editCommerceAccountUser" />
	<portlet:param name="commerceAccountId" value="<%= String.valueOf(commerceAccount.getCommerceAccountId()) %>" />
	<portlet:param name="userId" value="<%= String.valueOf(selectedUser.getUserId()) %>" />
	<portlet:param name='<%= PortletQName.PUBLIC_RENDER_PARAMETER_NAMESPACE + "backURL" %>' value="<%= portletURL.toString() %>" />
</portlet:renderURL>

<div class="details-header">
	<section class="details-header__section details-header__primary">
		<div class="details-header__main-data">
			<div class="details-header__avatar">
				<img alt="avatar" src="<%= selectedUser.getPortraitURL(themeDisplay) %>" />
			</div>

			<div class="details-header__name">
				<%= selectedUser.getFullName() %>
			</div>

			<div class="details-header__email">
				<%= selectedUser.getEmailAddress() %>
			</div>
		</div>

		<div class="details-header__action">
			<aui:button cssClass="minium-button minium-button--big minium-button--outline" href="<%= editCommerceAccountURL %>" value='<%= LanguageUtil.get(request, "edit-user") %>' />
		</div>
	</section>
</div>

<div class="minium-frame__cta is-visible">
	<c:if test="<%= selectedUser.getUserId() != user.getUserId() %>">
		<aui:button cssClass="js-invite-user minium-button minium-button--big" onClick='<%= renderResponse.getNamespace() + "openUserRolesModal();" %>' value="roles" />
	</c:if>
</div>

<div class="commerce-account-container">
	<commerce-ui:table
		dataProviderKey="commerceAccountUserRoles"
		filter="<%= commerceAccountDisplayContext.getAccountFilter() %>"
		itemPerPage="<%= 5 %>"
		namespace="<%= renderResponse.getNamespace() %>"
		pageNumber="1"
		portletURL="<%= portletURL %>"
		tableName="commerceAccountUserRoles"
	/>
</div>

<portlet:actionURL name="editCommerceAccountUser" var="editCommerceAccountUserURL" />

<aui:form action="<%= editCommerceAccountUserURL %>" method="post" name="editCommerceAccountUserFm">
	<aui:input name="<%= Constants.CMD %>" type="hidden" value="EDIT_ROLES" />
	<aui:input name="commerceAccountId" type="hidden" value="<%= commerceAccount.getCommerceAccountId() %>" />
	<aui:input name="userId" type="hidden" value="<%= selectedUser.getUserId() %>" />
	<aui:input name="selectedRoleIds" type="hidden" />
</aui:form>

<c:if test="<%= selectedUser.getUserId() != user.getUserId() %>">
	<commerce-ui:user-roles-modal
		commerceAccountId="<%= commerceAccount.getCommerceAccountId() %>"
		componentId="userRolesModal"
		userId="<%= selectedUser.getUserId() %>"
	/>

	<aui:script>

		Liferay.provide(
			window,
			'<portlet:namespace />openUserRolesModal',
			function(evt) {
				const userRolesModal = Liferay.component('userRolesModal');
				userRolesModal.open();
			}
		);

		Liferay.componentReady('userRolesModal').then(
			function(userRolesModal) {
				userRolesModal.on(
					'updateRoles',
					function(selectedRoles) {
						let selectedRoleIds = selectedRoles.map(
							function(role) {
								return role.id
							}
						).join(',');

						document.querySelector('#<portlet:namespace />selectedRoleIds').value = selectedRoleIds;

						userRolesModal.close();

						submitForm(document.<portlet:namespace />editCommerceAccountUserFm);
					}
				);
			}
		);

	</aui:script>
</c:if>