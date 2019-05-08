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
CommerceCatalogUsersDisplayContext commerceCatalogUsersDisplayContext = (CommerceCatalogUsersDisplayContext)request.getAttribute(WebKeys.PORTLET_DISPLAY_CONTEXT);

ResultRow row = (ResultRow)request.getAttribute(WebKeys.SEARCH_CONTAINER_RESULT_ROW);

User catalogUser = (User)row.getObject();

String userId = String.valueOf(catalogUser.getUserId());
%>

<liferay-ui:icon-menu
	direction="left-side"
	icon="<%= StringPool.BLANK %>"
	markupView="lexicon"
	message="<%= StringPool.BLANK %>"
	showWhenSingleIcon="<%= true %>"
>
	<liferay-ui:icon
		id="<%= userId %>"
		message="edit-roles"
		url="javascript:;"
	/>

	<portlet:actionURL name="editCommerceCatalogUsers" var="deleteURL">
		<portlet:param name="<%= Constants.CMD %>" value="<%= Constants.DELETE %>" />
		<portlet:param name="commerceCatalogUserIds" value="<%= userId %>" />
	</portlet:actionURL>

	<liferay-ui:icon-delete
		message="delete"
		url="<%= deleteURL %>"
	/>
</liferay-ui:icon-menu>

<commerce-ui:user-roles-modal
	componentId='<%= "userRolesModal" + userId %>'
	groupId="<%= commerceCatalogUsersDisplayContext.getCommerceCatalogGroupId() %>"
	subtype="<%= CommerceCatalogConstants.ROLE_SUBTYPE_CATALOG %>"
	userId="<%= catalogUser.getUserId() %>"
/>

<aui:script use="aui-base">

	Liferay.componentReady('userRolesModal<%= catalogUser.getUserId() %>').then(
		function(userRolesModal) {
			userRolesModal.on(
				'updateRoles',
				function(selectedRoles) {
					var selectedRoleIds = [];

					A.Array.each(
						selectedRoles,
						function(role) {
							selectedRoleIds.push(role.id);
						}
					);

					var form = AUI.$(document.<portlet:namespace />fm);

					form.fm('<%= Constants.CMD %>').val('<%= Constants.UPDATE %>');
					form.fm('selectedRoleIds').val(selectedRoleIds.join(','));

					userRolesModal.close();

					submitForm(form);
				}
			);
		}
	);

</aui:script>

<aui:script>
	$('#<portlet:namespace /><%= userId %>').on(
		'click',
		function() {
			var form = AUI.$(document.<portlet:namespace />fm);

			form.fm('commerceCatalogUserIds').val(<%= userId %>);

			const userRolesModal<%= userId %> = Liferay.component('<%= "userRolesModal" + userId %>');
			userRolesModal<%= userId %>.open();
		}
	);
</aui:script>