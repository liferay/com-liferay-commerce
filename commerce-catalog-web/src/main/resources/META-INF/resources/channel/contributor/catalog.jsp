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
CatalogCommerceChannelTypeDisplayContext catalogCommerceChannelTypeDisplayContext = (CatalogCommerceChannelTypeDisplayContext)request.getAttribute("catalog.jsp-portletDisplayContext");

List<CommerceCatalog> catalogs = catalogCommerceChannelTypeDisplayContext.getCatalogs();
%>

<liferay-util:buffer
	var="removeCommerceChannelCatalogIcon"
>
	<liferay-ui:icon
		icon="times"
		markupView="lexicon"
		message="remove"
	/>
</liferay-util:buffer>

<liferay-ui:search-container
	curParam="commerceChannelCatalogCur"
	headerNames="null,null"
	id="CommerceChannelCatalogsSearchContainer"
	iteratorURL="<%= currentURLObj %>"
	total="<%= catalogs.size() %>"
>
	<liferay-ui:search-container-results
		results="<%= catalogs.subList(searchContainer.getStart(), searchContainer.getResultEnd()) %>"
	/>

	<liferay-ui:search-container-row
		className="com.liferay.commerce.product.model.CommerceCatalog"
		keyProperty="commerceCatalogId"
		modelVar="commerceCatalog"
	>
		<liferay-ui:search-container-column-text
			cssClass="table-cell-content"
			value="<%= HtmlUtil.escape(commerceCatalog.getName(locale)) %>"
		/>

		<liferay-ui:search-container-column-text>
			<a class="float-right modify-link" data-rowId="<%= commerceCatalog.getCommerceCatalogId() %>" href="javascript:;"><%= removeCommerceChannelCatalogIcon %></a>
		</liferay-ui:search-container-column-text>
	</liferay-ui:search-container-row>

	<liferay-ui:search-iterator
		markupView="lexicon"
	/>
</liferay-ui:search-container>

<aui:button name="selectCatalog" value="select" />

<aui:script use="liferay-item-selector-dialog">
	$('#<portlet:namespace />selectCatalog').on(
		'click',
		function(event) {
			event.preventDefault();

			var itemSelectorDialog = new A.LiferayItemSelectorDialog(
				{
					eventName: 'catalogsSelectItem',
					on: {
						selectedItemChange: function(event) {
							var selectedItems = event.newVal;

							if (selectedItems) {
								var A = AUI();

								A.Array.each(
									selectedItems,
									function(item, index, selectedItems) {
										<portlet:namespace />addCommerceChannelTypeCatalog(item);
									}
								);
							}
						}
					},
					title: '<liferay-ui:message arguments="catalog" key="select-x" />',
					url: '<%= catalogCommerceChannelTypeDisplayContext.getItemSelectorUrl() %>'
				}
			);

			itemSelectorDialog.open();
		}
	);
</aui:script>

<aui:script>
	var <portlet:namespace />addCommerceChannelTypeCatalogIds = [];
	var <portlet:namespace />deleteCommerceChannelTypeCatalogIds = [];

	function <portlet:namespace />addCommerceChannelTypeCatalog(item) {
		var A = AUI();

		var searchContainer = Liferay.SearchContainer.get('<portlet:namespace />CommerceChannelCatalogsSearchContainer');

		var rowColumns = [];

		rowColumns.push(item.name);
		rowColumns.push('<a class="float-right modify-link" data-rowId="' + item.id + '" href="javascript:;"><%= UnicodeFormatter.toString(removeCommerceChannelCatalogIcon) %></a>');

		A.Array.removeItem(<portlet:namespace />deleteCommerceChannelTypeCatalogIds, item.id);

		<portlet:namespace />addCommerceChannelTypeCatalogIds.push(item.id);

		document.<portlet:namespace />fm.<portlet:namespace />addTypeSettings.value = <portlet:namespace />addCommerceChannelTypeCatalogIds.join(',');
		document.<portlet:namespace />fm.<portlet:namespace />deleteTypeSettings.value = <portlet:namespace />deleteCommerceChannelTypeCatalogIds.join(',');

		searchContainer.addRow(rowColumns, item.id);

		searchContainer.updateDataStore();
	}

	function <portlet:namespace />deleteCommerceChannelTypeCatalog(id) {
		var A = AUI();

		A.Array.removeItem(<portlet:namespace />addCommerceChannelTypeCatalogIds, id);

		<portlet:namespace />deleteCommerceChannelTypeCatalogIds.push(id);

		document.<portlet:namespace />fm.<portlet:namespace />addTypeSettings.value = <portlet:namespace />addCommerceChannelTypeCatalogIds.join(',');
		document.<portlet:namespace />fm.<portlet:namespace />deleteTypeSettings.value = <portlet:namespace />deleteCommerceChannelTypeCatalogIds.join(',');
	}
</aui:script>

<aui:script use="liferay-search-container">
	var searchContainer = Liferay.SearchContainer.get('<portlet:namespace />CommerceChannelCatalogsSearchContainer');

	var searchContainerContentBox = searchContainer.get('contentBox');

	searchContainerContentBox.delegate(
		'click',
		function(event) {
			var link = event.currentTarget;

			var rowId = link.attr('data-rowId');

			var tr = link.ancestor('tr');

			searchContainer.deleteRow(tr, link.getAttribute('data-rowId'));

			<portlet:namespace />deleteCommerceChannelTypeCatalog(rowId);
		},
		'.modify-link'
	);
</aui:script>