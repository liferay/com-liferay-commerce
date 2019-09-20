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
CommerceAccountUserRelAdminDisplayContext commerceAccountUserRelAdminDisplayContext = (CommerceAccountUserRelAdminDisplayContext)request.getAttribute(WebKeys.PORTLET_DISPLAY_CONTEXT);

CommerceAccount commerceAccount = commerceAccountUserRelAdminDisplayContext.getCommerceAccount();
long commerceAccountId = commerceAccountUserRelAdminDisplayContext.getCommerceAccountId();
SearchContainer<CommerceAccountUserRel> commerceAccountUserRelSearchContainer = commerceAccountUserRelAdminDisplayContext.getSearchContainer();

PortletURL portletURL = commerceAccountUserRelAdminDisplayContext.getPortletURL();
%>

<c:if test="<%= commerceAccountUserRelAdminDisplayContext.hasPermission(commerceAccountId, ActionKeys.UPDATE) %>">
	<liferay-frontend:management-bar
		includeCheckBox="<%= true %>"
		searchContainerId="commerceAccountUserRels"
	>
		<liferay-frontend:management-bar-buttons>
			<liferay-frontend:management-bar-display-buttons
				displayViews='<%= new String[] {"list"} %>'
				portletURL="<%= portletURL %>"
				selectedDisplayStyle="list"
			/>

			<portlet:actionURL name="editCommerceAccountUserRel" var="addCommerceAccountUserRelURL" />

			<aui:form action="<%= addCommerceAccountUserRelURL %>" cssClass="hide" name="addCommerceAccountUserRelFm">
				<aui:input name="<%= Constants.CMD %>" type="hidden" value="<%= Constants.ADD_MULTIPLE %>" />
				<aui:input name="redirect" type="hidden" value="<%= currentURL %>" />
				<aui:input name="commerceAccountId" type="hidden" value="<%= commerceAccountId %>" />
				<aui:input name="userIds" type="hidden" value="" />
			</aui:form>

			<liferay-frontend:add-menu
				inline="<%= true %>"
			>
				<liferay-frontend:add-menu-item
					id="addCommerceAccountUserRel"
					title='<%= LanguageUtil.get(request, "add-entry") %>'
					url="javascript:;"
				/>
			</liferay-frontend:add-menu>
		</liferay-frontend:management-bar-buttons>

		<liferay-frontend:management-bar-filters>
			<liferay-frontend:management-bar-navigation
				navigationKeys='<%= new String[] {"all"} %>'
				portletURL="<%= portletURL %>"
			/>
		</liferay-frontend:management-bar-filters>

		<liferay-frontend:management-bar-action-buttons>
			<liferay-frontend:management-bar-button
				href='<%= "javascript:" + renderResponse.getNamespace() + "deleteCommerceAccountUserRels();" %>'
				icon="times"
				label="remove"
			/>
		</liferay-frontend:management-bar-action-buttons>
	</liferay-frontend:management-bar>

	<div class="container-fluid-1280">
		<aui:form action="<%= portletURL.toString() %>" method="post" name="fm">
			<aui:input name="<%= Constants.CMD %>" type="hidden" />
			<aui:input name="redirect" type="hidden" value="<%= currentURL %>" />
			<aui:input name="commerceAccountId" type="hidden" value="<%= commerceAccountId %>" />
			<aui:input name="commerceAccountGroupId" type="hidden" value="<%= commerceAccount.getCommerceAccountGroupId() %>" />
			<aui:input name="commerceAccountUserId" type="hidden" />
			<aui:input name="deleteCommerceAccountUserRelIds" type="hidden" />
			<aui:input name="roleIds" type="hidden" />

			<liferay-ui:search-container
				id="commerceAccountUserRels"
				iteratorURL="<%= portletURL %>"
				searchContainer="<%= commerceAccountUserRelSearchContainer %>"
			>
				<liferay-ui:search-container-row
					className="com.liferay.commerce.account.model.CommerceAccountUserRel"
					cssClass="entry-display-style"
					keyProperty="commerceAccountUserId"
					modelVar="commerceAccountUserRel"
				>

					<%
					User accountUser = commerceAccountUserRel.getUser();
					%>

					<liferay-ui:search-container-column-text
						cssClass="table-cell-content"
						name="id"
						value="<%= String.valueOf(accountUser.getUserId()) %>"
					/>

					<liferay-ui:search-container-column-text
						cssClass="important table-cell-content"
						href="<%= commerceAccountUserRelAdminDisplayContext.getEditUserURL(accountUser.getUserId()) %>"
						name="name"
						value="<%= HtmlUtil.escape(accountUser.getFullName()) %>"
					/>

					<liferay-ui:search-container-column-text
						cssClass="table-cell-content"
						name="roles"
						value="<%= commerceAccountUserRelAdminDisplayContext.getUserRoles(commerceAccountUserRel) %>"
					/>

					<liferay-ui:search-container-column-jsp
						cssClass="entry-action-column"
						path="/user_action.jsp"
					/>
				</liferay-ui:search-container-row>

				<liferay-ui:search-iterator
					markupView="lexicon"
				/>
			</liferay-ui:search-container>
		</aui:form>
	</div>

	<aui:script>
		function <portlet:namespace />deleteCommerceAccountUserRels() {
			if (confirm('<liferay-ui:message key="are-you-sure-you-want-to-delete-the-selected-users" />')) {
				var form = AUI.$(document.<portlet:namespace />fm);

				form.attr('method', 'post');
				form.fm('<%= Constants.CMD %>').val('<%= Constants.DELETE %>');
				form.fm('deleteCommerceAccountUserRelIds').val(Liferay.Util.listCheckedExcept(form, '<portlet:namespace />allRowIds'));

				submitForm(form, '<portlet:actionURL name="editCommerceAccountUserRel" />');
			}
		}
	</aui:script>

	<aui:script use="liferay-item-selector-dialog">
		$('#<portlet:namespace />addCommerceAccountUserRel').on(
			'click',
			function(event) {
				event.preventDefault();

				var itemSelectorDialog = new A.LiferayItemSelectorDialog(
					{
						eventName: 'usersSelectItem',
						on: {
							selectedItemChange: function(event) {
								var <portlet:namespace />addUserIds = [];

								var selectedItems = event.newVal;

								if (selectedItems) {
									A.Array.each(
										selectedItems,
										function(item, index, selectedItems) {
											<portlet:namespace />addUserIds.push(item.id);
										}
									);

									$('#<portlet:namespace />userIds').val(<portlet:namespace />addUserIds.join(','));

									var addCommerceAccountUserRelFm = $('#<portlet:namespace />addCommerceAccountUserRelFm');

									submitForm(addCommerceAccountUserRelFm);
								}
							}
						},
						title: '<liferay-ui:message arguments="<%= HtmlUtil.escape(commerceAccount.getName()) %>" key="add-new-entry-to-x" />',
						url: '<%= commerceAccountUserRelAdminDisplayContext.getItemSelectorUrl() %>'
					}
				);

				itemSelectorDialog.open();
			}
		);
	</aui:script>
</c:if>