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
OrganizationCommerceChannelTypeDisplayContext organizationCommerceChannelTypeDisplayContext = (OrganizationCommerceChannelTypeDisplayContext)request.getAttribute("organization.jsp-portletDisplayContext");

List<Organization> organizations = organizationCommerceChannelTypeDisplayContext.getOrganizations();
%>

<liferay-util:buffer
	var="removeCommerceChannelOrganizationIcon"
>
	<liferay-ui:icon
		icon="times"
		markupView="lexicon"
		message="remove"
	/>
</liferay-util:buffer>

<liferay-ui:search-container
	curParam="commerceChannelOrganizationCur"
	headerNames="null,null"
	id="CommerceChannelOrganizationsSearchContainer"
	iteratorURL="<%= currentURLObj %>"
	total="<%= organizations.size() %>"
>
	<liferay-ui:search-container-results
		results="<%= organizations.subList(searchContainer.getStart(), searchContainer.getResultEnd()) %>"
	/>

	<liferay-ui:search-container-row
		className="com.liferay.portal.kernel.model.Organization"
		keyProperty="organizationId"
		modelVar="organization"
	>
		<liferay-ui:search-container-column-text
			cssClass="table-cell-content"
			value="<%= HtmlUtil.escape(organization.getName()) %>"
		/>

		<liferay-ui:search-container-column-text>
			<a class="float-right modify-link" data-rowId="<%= organization.getOrganizationId() %>" href="javascript:;"><%= removeCommerceChannelOrganizationIcon %></a>
		</liferay-ui:search-container-column-text>
	</liferay-ui:search-container-row>

	<liferay-ui:search-iterator
		markupView="lexicon"
	/>
</liferay-ui:search-container>

<aui:button name="selectOrganization" value="select" />

<aui:script use="liferay-item-selector-dialog">
	$('#<portlet:namespace />selectOrganization').on(
		'click',
		function(event) {
			event.preventDefault();

			var itemSelectorDialog = new A.LiferayItemSelectorDialog(
			{
				eventName: 'organizationsSelectItem',
				on: {
					selectedItemChange: function(event) {
					var selectedItems = event.newVal;

					if (selectedItems) {
						var A = AUI();

						A.Array.each(
							selectedItems,
							function(item, index, selectedItems) {
								<portlet:namespace />addCommerceChannelTypeOrganization(item);
							}
						);
					}
					}
				},
				title: '<liferay-ui:message arguments="organization" key="select-x" />',
				url: '<%= organizationCommerceChannelTypeDisplayContext.getItemSelectorUrl() %>'
			}
			);

			itemSelectorDialog.open();
		}
	);
</aui:script>

<aui:script>
	var <portlet:namespace />addCommerceChannelTypeOrganizationIds = [];
	var <portlet:namespace />deleteCommerceChannelTypeOrganizationIds = [];

	function <portlet:namespace />addCommerceChannelTypeOrganization(item) {
		var A = AUI();

		var searchContainer = Liferay.SearchContainer.get('<portlet:namespace />CommerceChannelOrganizationsSearchContainer');

		var rowColumns = [];

		rowColumns.push(item.name);
		rowColumns.push('<a class="float-right modify-link" data-rowId="' + item.id + '" href="javascript:;"><%= UnicodeFormatter.toString(removeCommerceChannelOrganizationIcon) %></a>');

		A.Array.removeItem(<portlet:namespace />deleteCommerceChannelTypeOrganizationIds, item.id);

		<portlet:namespace />addCommerceChannelTypeOrganizationIds.push(item.id);

		document.<portlet:namespace />fm.<portlet:namespace />addTypeSettings.value = <portlet:namespace />addCommerceChannelTypeOrganizationIds.join(',');
		document.<portlet:namespace />fm.<portlet:namespace />deleteTypeSettings.value = <portlet:namespace />deleteCommerceChannelTypeOrganizationIds.join(',');

		searchContainer.addRow(rowColumns, item.id);

		searchContainer.updateDataStore();
	}

	function <portlet:namespace />deleteCommerceChannelTypeOrganization(id) {
		var A = AUI();

		A.Array.removeItem(<portlet:namespace />addCommerceChannelTypeOrganizationIds, id);

		<portlet:namespace />deleteCommerceChannelTypeOrganizationIds.push(id);

		document.<portlet:namespace />fm.<portlet:namespace />addTypeSettings.value = <portlet:namespace />addCommerceChannelTypeOrganizationIds.join(',');
		document.<portlet:namespace />fm.<portlet:namespace />deleteTypeSettings.value = <portlet:namespace />deleteCommerceChannelTypeOrganizationIds.join(',');
	}
</aui:script>

<aui:script use="liferay-search-container">
	var searchContainer = Liferay.SearchContainer.get('<portlet:namespace />CommerceChannelOrganizationsSearchContainer');

	var searchContainerContentBox = searchContainer.get('contentBox');

	searchContainerContentBox.delegate(
		'click',
		function(event) {
			var link = event.currentTarget;

			var rowId = link.attr('data-rowId');

			var tr = link.ancestor('tr');

			searchContainer.deleteRow(tr, link.getAttribute('data-rowId'));

			<portlet:namespace />deleteCommerceChannelTypeOrganization(rowId);
		},
		'.modify-link'
	);
</aui:script>