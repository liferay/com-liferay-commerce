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

CommerceShipment commerceShipment = commerceOrderEditDisplayContext.getCommerceShipment();
%>

<commerce-ui:panel
	title="info"
>
	<div class="row">
		<div class="col-md-4">
			<commerce-ui:info-box
				title="courier-detail"
			>
				<liferay-ui:message key="shipping-id" /> <clay:link href="#" label="<%= commerceShipment.getTrackingNumber() %>" />

				<liferay-ui:message key="courier-name" /> <%= HtmlUtil.escape(commerceShipment.getCarrier()) %>
			</commerce-ui:info-box>
		</div>

		<div class="col-md-4">
			<commerce-ui:info-box
				title="barcode"
			>
				International Article Number 07053 23123
			</commerce-ui:info-box>
		</div>

		<div class="col-md-4">
			<commerce-ui:info-box
				title="shipment-status"
			>
				<clay:label
					label="<%= LanguageUtil.get(request, CommerceShipmentConstants.getShipmentStatusLabel(commerceShipment.getStatus())) %>"
					style="<%= CommerceShipmentConstants.getShipmentLabelStyle(commerceShipment.getStatus()) %>"
				/>
			</commerce-ui:info-box>
		</div>
	</div>
</commerce-ui:panel>

<div class="row">
	<commerce-ui:panel
		title='<%= LanguageUtil.get(request, "products") %>'
	>
		<liferay-frontend:management-bar
			searchContainerId="commerceOrderItems"
		>
			<liferay-frontend:management-bar-buttons>
				<liferay-frontend:add-menu
					inline="<%= true %>"
				>
					<liferay-frontend:add-menu-item
						id="addCommerceShipmentItem"
						title='<%= LanguageUtil.get(request, "add-item") %>'
						url="<%= String.valueOf(commerceOrderEditDisplayContext.getCommerceShipmentEditURL()) %>"
					/>
				</liferay-frontend:add-menu>
			</liferay-frontend:management-bar-buttons>
		</liferay-frontend:management-bar>

		<commerce-ui:table
			dataProviderKey="<%= CommerceShipmentItemClayTable.NAME %>"
			itemPerPage="<%= 5 %>"
			namespace="<%= renderResponse.getNamespace() %>"
			pageNumber="<%= 1 %>"
			portletURL="<%= currentURLObj %>"
			tableName="<%= CommerceShipmentItemClayTable.NAME %>"
		/>
	</commerce-ui:panel>
</div>