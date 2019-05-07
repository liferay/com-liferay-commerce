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
SiteCommerceChannelTypeDisplayContext siteCommerceChannelTypeDisplayContext = (SiteCommerceChannelTypeDisplayContext)request.getAttribute("site.jsp-portletDisplayContext");

List<Group> groups = siteCommerceChannelTypeDisplayContext.getGroups();
%>

<liferay-util:buffer
	var="removeCommerceChannelSiteIcon"
>
	<liferay-ui:icon
		icon="times"
		markupView="lexicon"
		message="remove"
	/>
</liferay-util:buffer>

<liferay-ui:search-container
	curParam="commerceChannelSiteCur"
	headerNames="null,null"
	id="CommerceChannelSitesSearchContainer"
	iteratorURL="<%= currentURLObj %>"
	total="<%= groups.size() %>"
>
	<liferay-ui:search-container-results
		results="<%= groups.subList(searchContainer.getStart(), searchContainer.getResultEnd()) %>"
	/>

	<liferay-ui:search-container-row
		className="com.liferay.portal.kernel.model.Group"
		keyProperty="groupId"
		modelVar="group"
	>
		<liferay-ui:search-container-column-text
			cssClass="table-cell-content"
			value="<%= HtmlUtil.escape(group.getName(locale)) %>"
		/>

		<liferay-ui:search-container-column-text>
			<a class="float-right modify-link" data-rowId="<%= group.getGroupId() %>" href="javascript:;"><%= removeCommerceChannelSiteIcon %></a>
		</liferay-ui:search-container-column-text>
	</liferay-ui:search-container-row>

	<liferay-ui:search-iterator
		markupView="lexicon"
	/>
</liferay-ui:search-container>

<aui:button name="selectSite" value="select" />

<aui:script use="liferay-item-selector-dialog">
	$('#<portlet:namespace />selectSite').on(
		'click',
		function(event) {
			event.preventDefault();

			var itemSelectorDialog = new A.LiferayItemSelectorDialog(
				{
					eventName: 'sitesSelectItem',
					on: {
						selectedItemChange: function(event) {
							var selectedItems = event.newVal;

							if (selectedItems) {
								var A = AUI();

								A.Array.each(
									selectedItems,
									function(item, index, selectedItems) {
										<portlet:namespace />addCommerceChannelTypeSite(item);
									}
								);
							}
						}
					},
					title: '<liferay-ui:message arguments="site" key="select-x" />',
					url: '<%= siteCommerceChannelTypeDisplayContext.getItemSelectorUrl() %>'
				}
			);

			itemSelectorDialog.open();
		}
	);
</aui:script>

<aui:script>
	var <portlet:namespace />addCommerceChannelTypeSiteIds = [];
	var <portlet:namespace />deleteCommerceChannelTypeSiteIds = [];

	function <portlet:namespace />addCommerceChannelTypeSite(item) {
		var A = AUI();

		var searchContainer = Liferay.SearchContainer.get('<portlet:namespace />CommerceChannelSitesSearchContainer');

		var rowColumns = [];

		rowColumns.push(item.name);
		rowColumns.push('<a class="float-right modify-link" data-rowId="' + item.id + '" href="javascript:;"><%= UnicodeFormatter.toString(removeCommerceChannelSiteIcon) %></a>');

		A.Array.removeItem(<portlet:namespace />deleteCommerceChannelTypeSiteIds, item.id);

		<portlet:namespace />addCommerceChannelTypeSiteIds.push(item.id);

		document.<portlet:namespace />fm.<portlet:namespace />addTypeSettings.value = <portlet:namespace />addCommerceChannelTypeSiteIds.join(',');
		document.<portlet:namespace />fm.<portlet:namespace />deleteTypeSettings.value = <portlet:namespace />deleteCommerceChannelTypeSiteIds.join(',');

		searchContainer.addRow(rowColumns, item.id);

		searchContainer.updateDataStore();
	}

	function <portlet:namespace />deleteCommerceChannelTypeSite(id) {
		var A = AUI();

		A.Array.removeItem(<portlet:namespace />addCommerceChannelTypeSiteIds, id);

		<portlet:namespace />deleteCommerceChannelTypeSiteIds.push(id);

		document.<portlet:namespace />fm.<portlet:namespace />addTypeSettings.value = <portlet:namespace />addCommerceChannelTypeSiteIds.join(',');
		document.<portlet:namespace />fm.<portlet:namespace />deleteTypeSettings.value = <portlet:namespace />deleteCommerceChannelTypeSiteIds.join(',');
	}
</aui:script>

<aui:script use="liferay-search-container">
	var searchContainer = Liferay.SearchContainer.get('<portlet:namespace />CommerceChannelSitesSearchContainer');

	var searchContainerContentBox = searchContainer.get('contentBox');

	searchContainerContentBox.delegate(
		'click',
		function(event) {
			var link = event.currentTarget;

			var rowId = link.attr('data-rowId');

			var tr = link.ancestor('tr');

			searchContainer.deleteRow(tr, link.getAttribute('data-rowId'));

			<portlet:namespace />deleteCommerceChannelTypeSite(rowId);
		},
		'.modify-link'
	);
</aui:script>