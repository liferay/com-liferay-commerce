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
CPCatalogRuleDisplayContext cpCatalogRuleDisplayContext = (CPCatalogRuleDisplayContext)request.getAttribute(WebKeys.PORTLET_DISPLAY_CONTEXT);

CPRule cpRule = cpCatalogRuleDisplayContext.getCPRule();
long cpRuleId = cpCatalogRuleDisplayContext.getCPRuleId();
SearchContainer<CPRuleCommerceAccountGroupRel> cpRuleCommerceAccountGroupRelSearchContainer = cpCatalogRuleDisplayContext.getCPRuleCommerceAccountGroupRelsSearchContainer();
PortletURL portletURL = cpCatalogRuleDisplayContext.getPortletURL();

portletURL.setParameter("mvcRenderCommandName", "editCPRule");
%>

<liferay-frontend:management-bar
	includeCheckBox="<%= true %>"
	searchContainerId="cpRuleCommerceAccountGroupRels"
>
	<liferay-frontend:management-bar-buttons>
		<liferay-frontend:management-bar-display-buttons
			disabled="<%= true %>"
			displayViews='<%= new String[] {"list"} %>'
			portletURL="<%= portletURL %>"
			selectedDisplayStyle="list"
		/>

		<c:if test="<%= cpCatalogRuleDisplayContext.hasPermission(cpRuleId, CPActionKeys.ADD_COMMERCE_PRODUCT_RULE_ACCOUNT_GROUP) %>">
			<portlet:actionURL name="editCPRuleCommerceAccountGroupRel" var="addCPRuleCommerceAccountGroupRelURL" />

			<aui:form action="<%= addCPRuleCommerceAccountGroupRelURL %>" cssClass="hide" name="addCPRuleCommerceAccountGroupRelFm">
				<aui:input name="<%= Constants.CMD %>" type="hidden" value="<%= Constants.ADD %>" />
				<aui:input name="redirect" type="hidden" value="<%= currentURL %>" />
				<aui:input name="cpRuleId" type="hidden" value="<%= cpRuleId %>" />
				<aui:input name="commerceAccountGroupIds" type="hidden" value="" />
			</aui:form>

			<liferay-frontend:add-menu
				inline="<%= true %>"
			>
				<liferay-frontend:add-menu-item
					id="addCPRuleCommerceAccountGroupRelMenuItem"
					title='<%= LanguageUtil.get(request, "add-account-group-catalog-rule") %>'
					url="javascript:;"
				/>
			</liferay-frontend:add-menu>
		</c:if>
	</liferay-frontend:management-bar-buttons>

	<liferay-frontend:management-bar-filters>
		<liferay-frontend:management-bar-sort
			orderByCol="<%= cpRuleCommerceAccountGroupRelSearchContainer.getOrderByCol() %>"
			orderByType="<%= cpRuleCommerceAccountGroupRelSearchContainer.getOrderByType() %>"
			orderColumns='<%= new String[] {"create-date"} %>'
			portletURL="<%= portletURL %>"
		/>
	</liferay-frontend:management-bar-filters>

	<liferay-frontend:management-bar-action-buttons>
		<liferay-frontend:management-bar-button
			href='<%= "javascript:" + renderResponse.getNamespace() + "deleteCPRuleCommerceAccountGroupRels();" %>'
			icon="times"
			label="delete"
		/>
	</liferay-frontend:management-bar-action-buttons>
</liferay-frontend:management-bar>

<portlet:actionURL name="editCPRuleCommerceAccountGroupRel" var="editCPRuleCommerceAccountGroupRelActionURL" />

<aui:form action="<%= editCPRuleCommerceAccountGroupRelActionURL %>" method="post" name="fm">
	<aui:input name="<%= Constants.CMD %>" type="hidden" value="<%= Constants.DELETE %>" />
	<aui:input name="redirect" type="hidden" value="<%= currentURL %>" />
	<aui:input name="deleteCPRuleCommerceAccountGroupRelIds" type="hidden" />

	<liferay-ui:search-container
		id="cpRuleCommerceAccountGroupRels"
		searchContainer="<%= cpRuleCommerceAccountGroupRelSearchContainer %>"
	>
		<liferay-ui:search-container-row
			className="com.liferay.commerce.product.model.CPRuleCommerceAccountGroupRel"
			keyProperty="CPRuleCommerceAccountGroupRelId"
			modelVar="cpRuleCommerceAccountGroupRel"
		>

			<%
			CommerceAccountGroup commerceAccountGroup = cpRuleCommerceAccountGroupRel.getCommerceAccountGroup();
			%>

			<liferay-ui:search-container-column-text
				cssClass="table-cell-content"
				name="name"
				value="<%= HtmlUtil.escape(commerceAccountGroup.getName()) %>"
			/>

			<liferay-ui:search-container-column-jsp
				cssClass="entry-action-column"
				path="/rule_account_group_rel_action.jsp"
			/>
		</liferay-ui:search-container-row>

		<liferay-ui:search-iterator
			markupView="lexicon"
		/>
	</liferay-ui:search-container>
</aui:form>

<aui:script>
	function <portlet:namespace />deleteCPRuleCommerceAccountGroupRels() {
		if (confirm('<liferay-ui:message key="are-you-sure-you-want-to-delete-the-selected-account-group-catalog-rules" />')) {
			var form = AUI.$(document.<portlet:namespace />fm);

			form.fm('deleteCPRuleCommerceAccountGroupRelIds').val(Liferay.Util.listCheckedExcept(form, '<portlet:namespace />allRowIds'));

			submitForm(form);
		}
	}
</aui:script>

<aui:script use="liferay-item-selector-dialog">
	$('#<portlet:namespace />addCPRuleCommerceAccountGroupRelMenuItem').on(
		'click',
		function(event) {
			event.preventDefault();

			var itemSelectorDialog = new A.LiferayItemSelectorDialog(
				{
					eventName: 'accountGroupSelectItem',
					on: {
						selectedItemChange: function(event) {
							var <portlet:namespace />addcommerceAccountGroupIds = [];

							var selectedItems = event.newVal;

							if (selectedItems) {
								A.Array.each(
									selectedItems,
									function(item, index, selectedItems) {
										<portlet:namespace />addcommerceAccountGroupIds.push(item.commerceAccountGroupId);
									}
								);

								$('#<portlet:namespace />commerceAccountGroupIds').val(<portlet:namespace />addcommerceAccountGroupIds.join(','));

								var addCPRuleCommerceAccountGroupRelFm = $('#<portlet:namespace />addCPRuleCommerceAccountGroupRelFm');

								submitForm(addCPRuleCommerceAccountGroupRelFm);
							}
						}
					},
					title: '<liferay-ui:message arguments="<%= cpRule.getName() %>" key="add-new-account-group-to-x" />',
					url: '<%= cpCatalogRuleDisplayContext.getItemSelectorUrl() %>'
				}
			);

			itemSelectorDialog.open();
		}
	);
</aui:script>