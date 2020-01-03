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
	id="add-shipment-modal"
	refreshPageOnClose="<%= true %>"
	size="lg"
	title='<%= LanguageUtil.get(request, "create-new-shipment") %>'
	url="<%= editCommerceShipmentURL %>"
/>

<!-- TODO: creationMenu to be passed in order to add a new shipment -->

<%
CommerceOrder commerceOrder = commerceOrderEditDisplayContext.getCommerceOrder();
java.util.Map<String, String> contextParams = new java.util.HashMap<>();
contextParams.put("commerceOrderId", String.valueOf(commerceOrder.getCommerceOrderId()));
%>

<commerce-ui:table-react
	contextParams="<%= contextParams %>"
	dataProviderKey="<%= CommerceShipmentClayTable.NAME %>"
	itemsPerPage="<%= 10 %>"
	namespace="<%= renderResponse.getNamespace() %>"
	pageNumber="<%= 1 %>"
	portletURL="<%= portletURL %>"
	stackedLayout="<%= true %>"
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

<aui:script require="commerce-frontend-js/components/side_panel/entry.es as sidePanel">
	sidePanel.default(
		"<portlet:namespace />sidePanel",
		"<portlet:namespace />side-panel-root",
		{
			portalWrapperId: "<portlet:namespace />side-panel-wrapper",
			spritemap: "<%= themeDisplay.getPathThemeImages() + "/clay/icons.svg" %>",
			topAnchorSelector: ".commerce-header"
		}
	);
</aui:script>