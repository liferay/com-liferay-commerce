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
String catalogNavigationItem = ParamUtil.getString(request, "catalogNavigationItem", "view-all-users");

CommerceCatalogUsersDisplayContext commerceCatalogUsersDisplayContext = (CommerceCatalogUsersDisplayContext)request.getAttribute(WebKeys.PORTLET_DISPLAY_CONTEXT);

List<User> users = commerceCatalogUsersDisplayContext.getSelectedUsers();

int usersCount = users.size();
%>

<%@ include file="/navbar_definitions.jspf" %>

<div class="container-fluid-1280">
	<aui:button-row>
		<aui:button name="selectCommerceCatalogUser" value='<%= LanguageUtil.format(locale, "add-x", "users") %>' />
	</aui:button-row>

	<portlet:actionURL name="editCommerceCatalogUsers" var="editCommerceCatalogUsersActionURL" />

	<aui:form action="<%= editCommerceCatalogUsersActionURL %>" cssClass="container-fluid-1280" method="post" name="fm">
		<aui:input name="<%= Constants.CMD %>" type="hidden" value="<%= Constants.ADD %>" />
		<aui:input name="commerceCatalogId" type="hidden" value='<%= ParamUtil.getLong(request, "commerceCatalogId") %>' />
		<aui:input name="commerceCatalogUserIds" type="hidden" />
		<aui:input name="selectedRoleIds" type="hidden" />

		<liferay-ui:search-container
			curParam="commerceCatalogUserCur"
			headerNames="null,null"
			id="commerceCatalogUserSearchContainer"
			iteratorURL="<%= currentURLObj %>"
			total="<%= usersCount %>"
		>

			<%
			users = users.subList(searchContainer.getStart(), searchContainer.getResultEnd());
			%>

			<liferay-ui:search-container-results
				results="<%= users %>"
			/>

			<liferay-ui:search-container-row
				className="com.liferay.portal.kernel.model.User"
				keyProperty="userId"
				modelVar="user"
			>
				<liferay-ui:search-container-column-text
					cssClass="table-cell-content"
					value="<%= HtmlUtil.escape(user.getFullName()) %>"
				/>

				<%
				List<Role> userRoles = commerceCatalogUsersDisplayContext.getUserRoles(user.getUserId());
				%>

				<liferay-ui:search-container-column-text
					cssClass="table-cell-content"
					value="<%= ListUtil.toString(userRoles, Role.TITLE_ACCESSOR, StringPool.COMMA_AND_SPACE) %>"
				/>

				<liferay-ui:search-container-column-text>
					<aui:button cssClass="selectCommerceCatalogUserRoles" data-rowId="<%= user.getUserId() %>" value='<%= LanguageUtil.get(request, "edit-roles") %>' />
				</liferay-ui:search-container-column-text>

				<liferay-ui:search-container-column-text>
					<a class="float-right modify-link" data-rowId="<%= user.getUserId() %>" href="javascript:;">
						<liferay-ui:icon
							icon="times"
							markupView="lexicon"
							message="remove"
						/>
					</a>
				</liferay-ui:search-container-column-text>
			</liferay-ui:search-container-row>

			<liferay-ui:search-iterator
				markupView="lexicon"
			/>
		</liferay-ui:search-container>
	</aui:form>
</div>

<%
for (User catalogUser : users) {
	String componentId = "userRolesModal" + catalogUser.getUserId();
%>

	<commerce-ui:user-roles-modal
		componentId="<%= componentId %>"
		groupId="<%= commerceCatalogUsersDisplayContext.getCommerceCatalogGroupId() %>"
		subtype="<%= CommerceCatalogConstants.ROLE_SUBTYPE_CATALOG %>"
		userId="<%= catalogUser.getUserId() %>"
	/>

<%
}
%>

<aui:script use="aui-base">

	<%
	for (User catalogUser : users) {
	%>

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

	<%
	}
	%>

</aui:script>

<aui:script use="liferay-item-selector-dialog">
	$('#<portlet:namespace />selectCommerceCatalogUser').on(
		'click',
		function(event) {
			event.preventDefault();

			var itemSelectorDialog = new A.LiferayItemSelectorDialog(
				{
					eventName: 'usersSelectItem',
					on: {
						selectedItemChange: function(event) {
							var addCommerceCatalogUserIds = [];

							var selectedItems = event.newVal;

							if (selectedItems) {
								A.Array.each(
									selectedItems,
									function(item) {
										addCommerceCatalogUserIds.push(item.id);
									}
								);

								var form = AUI.$(document.<portlet:namespace />fm);

								form.fm('commerceCatalogUserIds').val(addCommerceCatalogUserIds.join(','));

								submitForm(form);
							}
						}
					},
					title: '<liferay-ui:message arguments="users" key="add-x" />',
					url: '<%= commerceCatalogUsersDisplayContext.getItemSelectorUrl() %>'
				}
			);

			itemSelectorDialog.open();
		}
	);
</aui:script>

<aui:script>
	function <portlet:namespace />deleteCommerceCatalogUser(commerceCatalogUserId) {
		if (confirm('<liferay-ui:message key="are-you-sure-you-want-to-revoke-this-users-catalog-permissions" />')) {
			var form = AUI.$(document.<portlet:namespace />fm);

			form.fm('<%= Constants.CMD %>').val('<%= Constants.DELETE %>');
			form.fm('commerceCatalogUserIds').val(commerceCatalogUserId);

			submitForm(form);
		}
	}
</aui:script>

<aui:script use="liferay-search-container">
	var form = AUI.$(document.<portlet:namespace />fm);

	var searchContainer = Liferay.SearchContainer.get('<portlet:namespace />commerceCatalogUserSearchContainer');

	var searchContainerContentBox = searchContainer.get('contentBox');

	searchContainerContentBox.delegate(
		'click',
		function(event) {
			var link = event.currentTarget;

			var rowId = link.attr('data-rowId');

			<portlet:namespace />deleteCommerceCatalogUser(rowId);
		},
		'.modify-link'
	);

	searchContainerContentBox.delegate(
		'click',
		function(event) {
			form.fm('commerceCatalogUserIds').val(event.currentTarget.getAttribute('data-rowId'));

			<%
			for (User catalogUser : users) {
			%>

				if (event.currentTarget.getAttribute('data-rowId') == <%= catalogUser.getUserId() %>) {
					const userRolesModal<%= catalogUser.getUserId() %> = Liferay.component('<%= "userRolesModal" + catalogUser.getUserId() %>');
					userRolesModal<%= catalogUser.getUserId() %>.open();
				}

			<%
			}
			%>

		},
		'.selectCommerceCatalogUserRoles'
	);
</aui:script>