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
CatalogItemSelectorViewDisplayContext catalogItemSelectorViewDisplayContext = (CatalogItemSelectorViewDisplayContext)request.getAttribute(WebKeys.PORTLET_DISPLAY_CONTEXT);

String itemSelectedEventName = catalogItemSelectorViewDisplayContext.getItemSelectedEventName();
%>

<liferay-frontend:management-bar
	includeCheckBox="<%= true %>"
	searchContainerId="sites"
>
	<liferay-frontend:management-bar-filters>
		<liferay-frontend:management-bar-sort
			orderByCol="<%= catalogItemSelectorViewDisplayContext.getOrderByCol() %>"
			orderByType="<%= catalogItemSelectorViewDisplayContext.getOrderByType() %>"
			orderColumns='<%= new String[] {"name"} %>'
			portletURL="<%= catalogItemSelectorViewDisplayContext.getPortletURL() %>"
		/>
	</liferay-frontend:management-bar-filters>

	<liferay-frontend:management-bar-buttons>
		<liferay-frontend:management-bar-display-buttons
			displayViews='<%= new String[] {"list"} %>'
			portletURL="<%= renderResponse.createRenderURL() %>"
			selectedDisplayStyle="list"
		/>
	</liferay-frontend:management-bar-buttons>
</liferay-frontend:management-bar>

<div class="container-fluid-1280" id="<portlet:namespace />catalogSelectorWrapper">
	<liferay-ui:search-container
		id="catalogs"
		searchContainer="<%= catalogItemSelectorViewDisplayContext.getSearchContainer() %>"
	>
		<liferay-ui:search-container-row
			className="com.liferay.commerce.product.model.CommerceCatalog"
			keyProperty="commerceCatalogId"
			modelVar="commerceCatalog"
		>

			<%
			Map<String, Object> data = new HashMap<>();

			data.put("id", commerceCatalog.getCommerceCatalogId());
			data.put("name", commerceCatalog.getName());

			row.setData(data);
			%>

			<liferay-ui:search-container-column-text
				cssClass="table-cell-content"
				name="name"
				value="<%= HtmlUtil.escape(commerceCatalog.getName(locale)) %>"
			/>
		</liferay-ui:search-container-row>

		<liferay-ui:search-iterator
			markupView="lexicon"
		/>
	</liferay-ui:search-container>
</div>

<aui:script use="liferay-search-container">
	var searchContainer = Liferay.SearchContainer.get('<portlet:namespace />catalogs');

	searchContainer.on(
		'rowToggled',
		function(event) {
			var allSelectedElements = event.elements.allSelectedElements;
			var arr = [];

			allSelectedElements.each(
				function() {
					var row = this.ancestor('tr');

					var data = row.getDOM().dataset;

					arr.push({id: data.id, name: data.name});
				}
			);

			Liferay.Util.getOpener().Liferay.fire(
				'<%= HtmlUtil.escapeJS(itemSelectedEventName) %>',
				{
					data: arr
				}
			);
		}
	);
</aui:script>