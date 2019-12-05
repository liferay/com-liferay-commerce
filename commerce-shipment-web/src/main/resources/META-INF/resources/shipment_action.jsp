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
CommerceShipmentDisplayContext commerceShipmentDisplayContext = (CommerceShipmentDisplayContext)request.getAttribute(WebKeys.PORTLET_DISPLAY_CONTEXT);

ResultRow row = (ResultRow)request.getAttribute(WebKeys.SEARCH_CONTAINER_RESULT_ROW);

CommerceShipment commerceShipment = (CommerceShipment)row.getObject();

String modalId = "modal" + commerceShipment.getCommerceShipmentId();
%>

<portlet:actionURL name="editCommerceShipment" var="deleteURL">
	<portlet:param name="<%= Constants.CMD %>" value="<%= Constants.DELETE %>" />
	<portlet:param name="redirect" value="<%= currentURL %>" />
	<portlet:param name="commerceShipmentId" value="<%= String.valueOf(commerceShipment.getCommerceShipmentId()) %>" />
</portlet:actionURL>

<portlet:actionURL name="editCommerceShipment" var="deleteAndRestockURL">
	<portlet:param name="<%= Constants.CMD %>" value="<%= Constants.DELETE %>" />
	<portlet:param name="redirect" value="<%= currentURL %>" />
	<portlet:param name="commerceShipmentId" value="<%= String.valueOf(commerceShipment.getCommerceShipmentId()) %>" />
	<portlet:param name="restoreStockQuantity" value="true" />
</portlet:actionURL>

<liferay-ui:icon-menu
	direction="left-side"
	icon="<%= StringPool.BLANK %>"
	markupView="lexicon"
	message="<%= StringPool.BLANK %>"
	showWhenSingleIcon="<%= true %>"
>
	<c:if test="<%= commerceShipmentDisplayContext.hasManageCommerceShipmentsPermission() %>">
		<portlet:renderURL var="editURL">
			<portlet:param name="mvcRenderCommandName" value="viewCommerceShipmentDetail" />
			<portlet:param name="commerceShipmentId" value="<%= String.valueOf(commerceShipment.getCommerceShipmentId()) %>" />
		</portlet:renderURL>

		<liferay-ui:icon
			message="edit"
			url="<%= editURL %>"
		/>

		<liferay-ui:icon
			id='<%= "deleteShipment-" + modalId %>'
			message="delete"
			url="<%= (commerceShipment.getStatus() > CommerceShipmentConstants.SHIPMENT_STATUS_READY_TO_BE_SHIPPED) ? StringPool.POUND : deleteAndRestockURL %>"
		/>
	</c:if>
</liferay-ui:icon-menu>

<c:if test="<%= commerceShipment.getStatus() > CommerceShipmentConstants.SHIPMENT_STATUS_READY_TO_BE_SHIPPED %>">
	<div class="warning-modal" id="<%= modalId %>"></div>

	<aui:script use="aui-base">
		A.use(
			'aui-modal',
			function(A) {
				var <%= modalId %> = new A.Modal(
					{
						bodyContent: '<p><liferay-ui:message key="you-are-deleting-a-shipment-that-has-been-marked-as-shipped" /></p>',
						centered: true,
						draggable: false,
						destroyOnHide: false,
						headerContent: '<h2><liferay-ui:message key="do-you-wish-to-restock-the-products-from-this-shipment" /></h2>',
						modal: true,
						boundingBox: '#<%= modalId %>',
						width: 450
					}
				);

				<%= modalId %>.addToolbar(
					[
						{
							cssClass: 'btn-primary',
							label: '<liferay-ui:message key="yes" />',
							on: {
								click: function() {
									window.location.replace('<%= deleteAndRestockURL %>');
								}
							}
						},
						{
							cssClass: 'btn-primary',
							label: '<liferay-ui:message key="no" />',
							on: {
								click: function() {
									window.location.replace('<%= deleteURL %>');
								}
							}
						},
						{
							label: '<liferay-ui:message key="cancel" />',
							on: {
								click: function() {
									<%= modalId %>.hide();
								}
							}
						}
					]
				);

				A.one("#<portlet:namespace />deleteShipment-<%= modalId %>").on(
					'click',
					function(e) {
						e.preventDefault();
						<%= modalId %>.render();
						<%= modalId %>.show();
					}
				);
			}
		);
	</aui:script>
</c:if>