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
CommerceOrderEditDisplayContext commerceOrderEditDisplayContext = (CommerceOrderEditDisplayContext)request.getAttribute(WebKeys.PORTLET_DISPLAY_CONTEXT);

PortletURL portletURL = commerceOrderEditDisplayContext.getCommerceShipmentsPortletURL();
%>

<liferay-portlet:renderURL var="editCommerceShipmentURL" windowState="<%= LiferayWindowState.POP_UP.toString() %>">
	<portlet:param name="mvcRenderCommandName" value="editCommerceShipment" />
	<portlet:param name="commerceOrderId" value="<%= String.valueOf(commerceOrderEditDisplayContext.getCommerceOrderId()) %>" />
</liferay-portlet:renderURL>

<commerce-ui:modal
	closeOnSubmit="<%= true %>"
	id="add-shipment-modal"
	showCancel="<%= true %>"
	showSubmit="<%= true %>"
	size="lg"
	title='<%= LanguageUtil.get(request, "create-new-shipment") %>'
	url="<%= editCommerceShipmentURL %>"
/>

<liferay-frontend:management-bar
	searchContainerId="commerceShipments"
>
	<liferay-frontend:management-bar-filters>
		<liferay-frontend:management-bar-sort
			orderByCol="<%= commerceOrderEditDisplayContext.getOrderByCol() %>"
			orderByType="<%= commerceOrderEditDisplayContext.getOrderByType() %>"
			orderColumns='<%= new String[] {"create-date"} %>'
			portletURL="<%= portletURL %>"
		/>
	</liferay-frontend:management-bar-filters>

	<liferay-frontend:management-bar-buttons>
		<liferay-frontend:add-menu
			inline="<%= true %>"
		>
			<liferay-frontend:add-menu-item
				id="addCommerceShipment"
				title='<%= LanguageUtil.get(request, "add-shipment") %>'
				url="<%= editCommerceShipmentURL %>"
			/>
		</liferay-frontend:add-menu>
	</liferay-frontend:management-bar-buttons>

	<liferay-frontend:management-bar-action-buttons>
		<liferay-frontend:management-bar-button
			href='<%= "javascript:" + renderResponse.getNamespace() + "deleteCommerceShipments();" %>'
			icon="times"
			label="delete"
		/>
	</liferay-frontend:management-bar-action-buttons>
</liferay-frontend:management-bar>

<commerce-ui:table
	dataProviderKey="<%= CommerceShipmentClayTable.NAME %>"
	itemPerPage="<%= 5 %>"
	namespace="<%= renderResponse.getNamespace() %>"
	pageNumber="<%= 1 %>"
	portletURL="<%= portletURL %>"
	tableName="<%= CommerceShipmentClayTable.NAME %>"
/>

<aui:script>
	function <portlet:namespace />deleteCommerceShipments() {
		if (confirm('<liferay-ui:message key="are-you-sure-you-want-to-delete-the-selected-shipments" />')) {
			var form = AUI.$(document.<portlet:namespace />fm);

			form.attr('method', 'post');
			form.fm('<%= Constants.CMD %>').val('<%= Constants.DELETE %>');
			form.fm('deleteCommerceShipmentIds').val(Liferay.Util.listCheckedExcept(form, '<portlet:namespace />allRowIds'));

			submitForm(form, '<portlet:actionURL name="editCommerceShipment" />');
		}
	}
</aui:script>

<div id="<portlet:namespace />side-panel-root"></div>
<div id="<portlet:namespace />side-panel-wrapper"></div>

<aui:script require="commerce-frontend-js/js/side_panel/entry.es as SidePanel">
	new SidePanel.default(
		"<portlet:namespace />sidePanel",
		"<portlet:namespace />side-panel-root",
		{
			size: "lg",
			spritemap: "<%= themeDisplay.getPathThemeImages() + "/clay/icons.svg" %>",
			portalWrapperId: "<portlet:namespace />side-panel-wrapper",
			topAnchor: document.getElementById('commerce-admin-header'),
			onUpdate: function(e){console.log(e)}
		}
	);

	Promise.all(
		[
			Liferay.componentReady('commerceShipmentsCommerceTable'),
			Liferay.componentReady('<portlet:namespace />sidePanel')
		]
	).then(function(
		[
			table,
			panel
		]
	) {
		document.getElementById('commerceShipmentsCommerceTable')
			.addEventListener(
				'click',
				function(e) {
					e.preventDefault();

					if (e.target.dataset.target === '<portlet:namespace />sidePanel') {
						panel.open(e.target.dataset.panelUrl);
					}
				}
			)
		}
	)
</aui:script>