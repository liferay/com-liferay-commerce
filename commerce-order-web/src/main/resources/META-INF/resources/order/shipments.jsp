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

SearchContainer<CommerceShipment> commerceShipmentsSearchContainer = commerceOrderEditDisplayContext.getCommerceShipmentsSearchContainer();
PortletURL portletURL = commerceOrderEditDisplayContext.getCommerceShipmentsPortletURL();
%>

<liferay-frontend:management-bar
	includeCheckBox="<%= true %>"
	searchContainerId="commerceShipments"
>
	<liferay-frontend:management-bar-filters>
		<liferay-frontend:management-bar-sort
			orderByCol="<%= commerceShipmentsSearchContainer.getOrderByCol() %>"
			orderByType="<%= commerceShipmentsSearchContainer.getOrderByType() %>"
			orderColumns='<%= new String[] {"create-date"} %>'
			portletURL="<%= portletURL %>"
		/>
	</liferay-frontend:management-bar-filters>

	<liferay-frontend:management-bar-buttons>
		<portlet:actionURL name="editCommerceShipment" var="addCommerceShipmentURL" />

		<aui:form action="<%= addCommerceShipmentURL %>" cssClass="hide" name="addCommerceShipmentFm">
			<aui:input name="<%= Constants.CMD %>" type="hidden" value="<%= Constants.ADD %>" />
			<aui:input name="redirect" type="hidden" value="<%= currentURL %>" />
			<aui:input name="commerceOrderId" type="hidden" value="<%= commerceOrderEditDisplayContext.getCommerceOrderId() %>" />
			<aui:input name="cpInstanceIds" type="hidden" value="" />
		</aui:form>

		<liferay-frontend:add-menu
			inline="<%= true %>"
		>
			<liferay-frontend:add-menu-item
				id="addCommerceShipment"
				title='<%= LanguageUtil.get(request, "add-shipment") %>'
				url="javascript:;"
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

<liferay-ui:search-container
	id="commerceShipments"
	searchContainer="<%= commerceShipmentsSearchContainer %>"
>
	<liferay-ui:search-container-row
		className="com.liferay.commerce.model.CommerceShipment"
		escapedModel="<%= true %>"
		keyProperty="commerceShipmentId"
		modelVar="commerceShipment"
	>

		<%
		PortletURL rowURL = renderResponse.createRenderURL();

		rowURL.setParameter("mvcRenderCommandName", "editCommerceOrderShipment");
		rowURL.setParameter("redirect", currentURL);
		rowURL.setParameter("commerceOrderId", String.valueOf(commerceOrderEditDisplayContext.getCommerceOrderId()));
		rowURL.setParameter("commerceShipmentId", String.valueOf(commerceShipment.getCommerceShipmentId()));
		%>

		<liferay-ui:search-container-column-text
			cssClass="important table-cell-content"
			name="shipment-id"
		>

			<%
			Map<String, String> dataAttributes = new HashMap<>();
			dataAttributes.put("panel-url", rowURL.toString());
			dataAttributes.put("target", renderResponse.getNamespace() + "sidePanel");
			%>

			<clay:link
				data="<%= dataAttributes %>"
				href="<%= rowURL.toString() %>"
				label="<%= String.valueOf(commerceShipment.getCommerceShipmentId()) %>"
			/>
		</liferay-ui:search-container-column-text>

		<liferay-ui:search-container-column-text
			cssClass="table-cell-content"
			name="address"
			value="<%= commerceOrderEditDisplayContext.getDescriptiveCommerceAddress(commerceShipment.fetchCommerceAddress()) %>"
		/>

		<liferay-ui:search-container-column-date
			cssClass="table-cell-content"
			name="create-date"
			property="createDate"
		/>

		<liferay-ui:search-container-column-text
			name="status"
		>
			<clay:label
				label="<%= LanguageUtil.get(request, CommerceShipmentConstants.getShipmentStatusLabel(commerceShipment.getStatus())) %>"
				style="<%= CommerceShipmentConstants.getShipmentLabelStyle(commerceShipment.getStatus()) %>"
			/>
		</liferay-ui:search-container-column-text>

		<liferay-ui:search-container-column-text
			name="tracking"
			property="trackingNumber"
		/>

		<liferay-ui:search-container-column-jsp
			cssClass="entry-action-column"
			path="/order/shipment_action.jsp"
		/>
	</liferay-ui:search-container-row>

	<liferay-ui:search-iterator
		markupView="lexicon"
	/>
</liferay-ui:search-container>

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

<aui:script require="commerce-frontend-js/js/side_panel/entry.es as SidePanel">
	new SidePanel.default(
		"<portlet:namespace />sidePanel",
		"<portlet:namespace />side-panel-root",
		{
			size: "lg"
		}
	);

	Promise.all(
		[
			Liferay.componentReady('<portlet:namespace />commerceShipments'),
			Liferay.componentReady('<portlet:namespace />sidePanel')
		]
	).then(function(
		[
			table,
			panel
		]
	) {
		document.getElementById('<portlet:namespace />commerceShipments')
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