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
String searchContainerId = ParamUtil.getString(request, "searchContainerId", "commerceCatalogUsers");

CommerceCatalogUsersDisplayContext commerceCatalogUsersDisplayContext = (CommerceCatalogUsersDisplayContext)request.getAttribute(WebKeys.PORTLET_DISPLAY_CONTEXT);

long commerceCatalogId = ParamUtil.getLong(request, "commerceCatalogId");
%>

<div class="definition-toolbar-managment-bar">
	<liferay-frontend:management-bar
		includeCheckBox="<%= true %>"
		searchContainerId="<%= searchContainerId %>"
	>
		<liferay-frontend:management-bar-buttons>
			<liferay-frontend:management-bar-display-buttons
				displayViews='<%= new String[] {"list"} %>'
				portletURL="<%= commerceCatalogUsersDisplayContext.getPortletURL() %>"
				selectedDisplayStyle="list"
			/>

			<portlet:renderURL var="addCommerceCatalogUsersURL">
				<portlet:param name="mvcRenderCommandName" value="editCommerceCatalogUsers" />
				<portlet:param name="commerceCatalogId" value="<%= String.valueOf(commerceCatalogId) %>" />
				<portlet:param name="redirect" value="<%= currentURL %>" />
			</portlet:renderURL>

			<liferay-frontend:add-menu
				inline="<%= true %>"
			>
				<liferay-frontend:add-menu-item
					id="selectCommerceCatalogUser"
					title='<%= LanguageUtil.get(request, "add-user") %>'
					url="javascript:;"
				/>
			</liferay-frontend:add-menu>

			<liferay-frontend:management-bar-action-buttons>
				<liferay-frontend:management-bar-button
					href='<%= "javascript:" + renderResponse.getNamespace() + "deleteCommerceCatalogUsers();" %>'
					icon="times"
					label="delete"
				/>
			</liferay-frontend:management-bar-action-buttons>
		</liferay-frontend:management-bar-buttons>
	</liferay-frontend:management-bar>
</div>

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
	function <portlet:namespace />deleteCommerceCatalogUsers() {
		if (confirm('<liferay-ui:message key="are-you-sure-you-want-to-revoke-this-users-catalog-permissions" />')) {
			var form = AUI.$(document.<portlet:namespace />fm);

			form.fm('<%= Constants.CMD %>').val('<%= Constants.DELETE %>');
			form.fm('commerceCatalogUserIds').val(Liferay.Util.listCheckedExcept(form, '<portlet:namespace />allRowIds'));

			submitForm(form);
		}
	}
</aui:script>