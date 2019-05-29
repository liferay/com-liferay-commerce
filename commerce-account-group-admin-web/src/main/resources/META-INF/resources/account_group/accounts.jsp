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
CommerceAccountGroupAdminDisplayContext commerceAccountGroupAdminDisplayContext = (CommerceAccountGroupAdminDisplayContext)request.getAttribute(WebKeys.PORTLET_DISPLAY_CONTEXT);

CommerceAccountGroup commerceAccountGroup = commerceAccountGroupAdminDisplayContext.getCommerceAccountGroup();
long commerceAccountGroupId = commerceAccountGroupAdminDisplayContext.getCommerceAccountGroupId();
SearchContainer<CommerceAccountGroupCommerceAccountRel> commerceAccountGroupCommerceAccountRelSearchContainer = commerceAccountGroupAdminDisplayContext.getCommerceAccountGroupCommerceAccountRelSearchContainer();

PortletURL portletURL = commerceAccountGroupAdminDisplayContext.getPortletURL();
%>

<c:if test="<%= commerceAccountGroupAdminDisplayContext.hasPermission(commerceAccountGroupId, ActionKeys.UPDATE) %>">
	<liferay-frontend:management-bar
		includeCheckBox="<%= true %>"
		searchContainerId="commerceAccountGroupCommerceAccountRels"
	>
		<liferay-frontend:management-bar-buttons>
			<liferay-frontend:management-bar-display-buttons
				displayViews='<%= new String[] {"list"} %>'
				portletURL="<%= portletURL %>"
				selectedDisplayStyle="list"
			/>

			<portlet:actionURL name="editCommerceAccountGroupCommerceAccountRel" var="addCommerceAccountGroupCommerceAccountRelURL" />

			<aui:form action="<%= addCommerceAccountGroupCommerceAccountRelURL %>" cssClass="hide" name="addCommerceAccountGroupCommerceAccountRelFm">
				<aui:input name="<%= Constants.CMD %>" type="hidden" value="<%= Constants.ADD_MULTIPLE %>" />
				<aui:input name="redirect" type="hidden" value="<%= currentURL %>" />
				<aui:input name="commerceAccountGroupId" type="hidden" value="<%= commerceAccountGroupId %>" />
				<aui:input name="commerceAccountIds" type="hidden" value="" />
			</aui:form>

			<liferay-frontend:add-menu
				inline="<%= true %>"
			>
				<liferay-frontend:add-menu-item
					id="addCommerceAccountGroupCommerceAccountRel"
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
				href='<%= "javascript:" + renderResponse.getNamespace() + "deleteCommerceAccountGroupCommerceAccountRels();" %>'
				icon="times"
				label="delete"
			/>
		</liferay-frontend:management-bar-action-buttons>
	</liferay-frontend:management-bar>

	<div class="container-fluid-1280">
		<aui:form action="<%= portletURL.toString() %>" method="post" name="fm">
			<aui:input name="<%= Constants.CMD %>" type="hidden" />
			<aui:input name="redirect" type="hidden" value="<%= currentURL %>" />
			<aui:input name="deleteCommerceAccountGroupCommerceAccountRelIds" type="hidden" />

			<liferay-ui:search-container
				id="commerceAccountGroupCommerceAccountRels"
				iteratorURL="<%= portletURL %>"
				searchContainer="<%= commerceAccountGroupCommerceAccountRelSearchContainer %>"
			>
				<liferay-ui:search-container-row
					className="com.liferay.commerce.account.model.CommerceAccountGroupCommerceAccountRel"
					cssClass="entry-display-style"
					keyProperty="commerceAccountGroupCommerceAccountRelId"
					modelVar="commerceAccountGroupCommerceAccountRel"
				>

					<%
					CommerceAccount commerceAccount = commerceAccountGroupCommerceAccountRel.getCommerceAccount();
					%>

					<liferay-ui:search-container-column-text
						cssClass="table-cell-content"
						name="id"
						value="<%= String.valueOf(commerceAccount.getCommerceAccountId()) %>"
					/>

					<liferay-ui:search-container-column-text
						cssClass="important table-cell-content"
						name="name"
						value="<%= HtmlUtil.escape(commerceAccount.getName()) %>"
					/>

					<liferay-ui:search-container-column-jsp
						cssClass="entry-action-column"
						path="/account_group_account_rel_action.jsp.jsp"
					/>
				</liferay-ui:search-container-row>

				<liferay-ui:search-iterator
					markupView="lexicon"
				/>
			</liferay-ui:search-container>
		</aui:form>
	</div>

	<aui:script>
		function <portlet:namespace />deleteCommerceAccountGroupCommerceAccountRels() {
			if (confirm('<liferay-ui:message key="are-you-sure-you-want-to-delete-the-selected-accounts" />')) {
				var form = AUI.$(document.<portlet:namespace />fm);

				form.attr('method', 'post');
				form.fm('<%= Constants.CMD %>').val('<%= Constants.DELETE %>');
				form.fm('deleteCommerceAccountGroupCommerceAccountRelIds').val(Liferay.Util.listCheckedExcept(form, '<portlet:namespace />allRowIds'));

				submitForm(form, '<portlet:actionURL name="editCommerceAccountGroupCommerceAccountRel" />');
			}
		}
	</aui:script>

	<aui:script use="liferay-item-selector-dialog">
		$('#<portlet:namespace />addCommerceAccountGroupCommerceAccountRel').on(
			'click',
			function(event) {
				event.preventDefault();

				var itemSelectorDialog = new A.LiferayItemSelectorDialog(
					{
						eventName: 'commerceAccountsSelectItem',
						on: {
							selectedItemChange: function(event) {
								var <portlet:namespace />addCommerceAccountIds = [];

								var selectedItems = event.newVal;

								if (selectedItems) {
									A.Array.each(
										selectedItems,
										function(item, index, selectedItems) {
											<portlet:namespace />addCommerceAccountIds.push(item.id);
										}
									);

									$('#<portlet:namespace />commerceAccountIds').val(<portlet:namespace />addCommerceAccountIds.join(','));

									var addCommerceAccountGroupCommerceAccountRelFm = $('#<portlet:namespace />addCommerceAccountGroupCommerceAccountRelFm');

									submitForm(addCommerceAccountGroupCommerceAccountRelFm);
								}
							}
						},
						title: '<liferay-ui:message arguments="<%= HtmlUtil.escape(commerceAccountGroup.getName()) %>" key="add-new-entry-to-x" />',
						url: '<%= commerceAccountGroupAdminDisplayContext.getItemSelectorUrl() %>'
					}
				);

				itemSelectorDialog.open();
			}
		);
	</aui:script>
</c:if>