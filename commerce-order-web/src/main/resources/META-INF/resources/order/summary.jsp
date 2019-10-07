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

CommerceOrder commerceOrder = commerceOrderEditDisplayContext.getCommerceOrder();
PortletURL portletURL = commerceOrderEditDisplayContext.getCommerceOrderItemsPortletURL();
%>

<liferay-portlet:renderURL var="editBillingAddressURL" windowState="<%= LiferayWindowState.POP_UP.toString() %>">
	<portlet:param name="mvcRenderCommandName" value="editCommerceOrderBillingAddress" />
	<portlet:param name="commerceOrderId" value="<%= String.valueOf(commerceOrderEditDisplayContext.getCommerceOrderId()) %>" />
</liferay-portlet:renderURL>

<commerce-ui:modal
	closeOnSubmit="<%= true %>"
	id="billing-address-modal"
	showCancel="<%= true %>"
	showSubmit="<%= true %>"
	size="lg"
	title='<%= LanguageUtil.get(request, "billing-address") %>'
	url="<%= editBillingAddressURL %>"
/>

<liferay-portlet:renderURL var="editShippingAddressURL" windowState="<%= LiferayWindowState.POP_UP.toString() %>">
	<portlet:param name="mvcRenderCommandName" value="editCommerceOrderShippingAddress" />
	<portlet:param name="commerceOrderId" value="<%= String.valueOf(commerceOrderEditDisplayContext.getCommerceOrderId()) %>" />
</liferay-portlet:renderURL>

<commerce-ui:modal
	closeOnSubmit="<%= true %>"
	id="shipping-address-modal"
	showCancel="<%= true %>"
	showSubmit="<%= true %>"
	size="lg"
	title='<%= LanguageUtil.get(request, "shipping-address") %>'
	url="<%= editShippingAddressURL %>"
/>

<liferay-portlet:renderURL var="editPurchaseOrderNumberURL" windowState="<%= LiferayWindowState.POP_UP.toString() %>">
	<portlet:param name="mvcRenderCommandName" value="editCommerceOrderPurchaseOrderNumber" />
	<portlet:param name="commerceOrderId" value="<%= String.valueOf(commerceOrderEditDisplayContext.getCommerceOrderId()) %>" />
</liferay-portlet:renderURL>

<commerce-ui:modal
	closeOnSubmit="<%= true %>"
	id="purchase-order-number-modal"
	showCancel="<%= true %>"
	showSubmit="<%= true %>"
	size="lg"
	title='<%= LanguageUtil.get(request, "purchase-order-number") %>'
	url="<%= editPurchaseOrderNumberURL %>"
/>

<liferay-portlet:renderURL var="editRequestedDeliveryDateURL" windowState="<%= LiferayWindowState.POP_UP.toString() %>">
	<portlet:param name="mvcRenderCommandName" value="editCommerceOrderRequestedDeliveryDate" />
	<portlet:param name="commerceOrderId" value="<%= String.valueOf(commerceOrderEditDisplayContext.getCommerceOrderId()) %>" />
</liferay-portlet:renderURL>

<commerce-ui:modal
	closeOnSubmit="<%= true %>"
	id="requested-delivery-date-modal"
	showCancel="<%= true %>"
	showSubmit="<%= true %>"
	size="lg"
	title='<%= LanguageUtil.get(request, "requested-delivery-date") %>'
	url="<%= editRequestedDeliveryDateURL %>"
/>

<liferay-portlet:renderURL var="editPrintedNoteURL" windowState="<%= LiferayWindowState.POP_UP.toString() %>">
	<portlet:param name="mvcRenderCommandName" value="editCommerceOrderPrintedNote" />
	<portlet:param name="commerceOrderId" value="<%= String.valueOf(commerceOrderEditDisplayContext.getCommerceOrderId()) %>" />
</liferay-portlet:renderURL>

<commerce-ui:modal
	closeOnSubmit="<%= true %>"
	id="printed-note-modal"
	showCancel="<%= true %>"
	showSubmit="<%= true %>"
	size="lg"
	title='<%= LanguageUtil.get(request, "printed-note") %>'
	url="<%= editPrintedNoteURL %>"
/>

<div class="container">
	<div class="col-12 mb-4">
		<commerce-ui:step-tracker
			steps="<%= commerceOrderEditDisplayContext.getOrderSteps() %>"
		/>
	</div>

	<div class="col-12">
		<commerce-ui:panel
			elementClasses="flex-fill"
			title='<%= LanguageUtil.get(request, "info") %>'
		>
			<div class="row vertically-divided">
				<div class="col-md-4">

					<%
					CommerceAddress billingAddress = commerceOrder.getBillingAddress();
					%>

					<commerce-ui:info-box
						actionLabel='<%= LanguageUtil.get(request, (billingAddress == null) ? "add" : "edit") %>'
						actionTargetId="billing-address-modal"
						actionUrl="<%= editBillingAddressURL %>"
						elementClasses="py-3"
						title='<%= LanguageUtil.get(request, "billing-address") %>'
					>
						<c:choose>
							<c:when test="<%= billingAddress == null %>">
								<span class="text-muted">
									<%= LanguageUtil.get(request, "click-add-to-insert") %>
								</span>
							</c:when>
							<c:otherwise>
								<%= HtmlUtil.escape(commerceOrderEditDisplayContext.getDescriptiveCommerceAddress(billingAddress)) %>
							</c:otherwise>
						</c:choose>
					</commerce-ui:info-box>

					<%
					CommerceAddress shippingAddress = commerceOrder.getShippingAddress();
					%>

					<commerce-ui:info-box
						actionLabel='<%= LanguageUtil.get(request, (shippingAddress == null) ? "add" : "edit") %>'
						actionTargetId="shipping-address-modal"
						actionUrl="<%= editShippingAddressURL %>"
						elementClasses="py-3"
						title='<%= LanguageUtil.get(request, "shipping-address") %>'
					>
						<c:choose>
							<c:when test="<%= shippingAddress == null %>">
								<span class="text-muted">
									<%= LanguageUtil.get(request, "click-add-to-insert") %>
								</span>
							</c:when>
							<c:otherwise>
								<%= HtmlUtil.escape(commerceOrderEditDisplayContext.getDescriptiveCommerceAddress(shippingAddress)) %>
							</c:otherwise>
						</c:choose>
					</commerce-ui:info-box>
				</div>

				<div class="col-md-4">

					<%
					String purchaseOrderNumber = commerceOrder.getPurchaseOrderNumber();
					%>

					<commerce-ui:info-box
						actionLabel='<%= LanguageUtil.get(request, Validator.isNull(purchaseOrderNumber) ? "add" : "edit") %>'
						actionTargetId="purchase-order-number-modal"
						actionUrl="<%= editPurchaseOrderNumberURL %>"
						elementClasses="py-3"
						title='<%= LanguageUtil.get(request, "purchase-order-number") %>'
					>
						<c:choose>
							<c:when test="<%= Validator.isNull(purchaseOrderNumber) %>">
								<span class="text-muted">
									<%= LanguageUtil.get(request, "click-add-to-insert") %>
								</span>
							</c:when>
							<c:otherwise>
								<%= HtmlUtil.escape(purchaseOrderNumber) %>
							</c:otherwise>
						</c:choose>
					</commerce-ui:info-box>

					<%
					Date requestedDeliveryDate = commerceOrder.getRequestedDeliveryDate();
					%>

					<commerce-ui:info-box
						actionLabel='<%= LanguageUtil.get(request, (requestedDeliveryDate == null) ? "add" : "edit") %>'
						actionTargetId="requested-delivery-date-modal"
						actionUrl="<%= editRequestedDeliveryDateURL %>"
						elementClasses="py-3"
						title='<%= LanguageUtil.get(request, "requested-delivery-date") %>'
					>
						<c:choose>
							<c:when test="<%= requestedDeliveryDate == null %>">
								<span class="text-muted">
									<%= LanguageUtil.get(request, "click-add-to-insert") %>
								</span>
							</c:when>
							<c:otherwise>
								<%= commerceOrderEditDisplayContext.getCommerceOrderDateTime(requestedDeliveryDate) %>
							</c:otherwise>
						</c:choose>
					</commerce-ui:info-box>
				</div>

				<%
				String printedNote = commerceOrder.getPrintedNote();
				%>

				<div class="col-md-4">
					<commerce-ui:info-box
						actionLabel='<%= LanguageUtil.get(request, Validator.isNull(printedNote) ? "add" : "edit") %>'
						actionTargetId="printed-note-modal"
						actionUrl="<%= editPrintedNoteURL %>"
						elementClasses="py-3"
						title='<%= LanguageUtil.get(request, "printed-note") %>'
					>
						<c:choose>
							<c:when test="<%= Validator.isNull(printedNote) %>">
								<span class="text-muted">
									<%= LanguageUtil.get(request, "click-add-to-insert") %>
								</span>
							</c:when>
							<c:otherwise>
								<%= HtmlUtil.escape(printedNote) %>
							</c:otherwise>
						</c:choose>
					</commerce-ui:info-box>
				</div>
			</div>
		</commerce-ui:panel>
	</div>

	<div class="col-12">
		<commerce-ui:panel
			title='<%= LanguageUtil.get(request, "items") %>'
		>
			<liferay-frontend:management-bar
				searchContainerId="commerceOrderItems"
			>
				<liferay-frontend:management-bar-filters>
					<liferay-frontend:management-bar-sort
						orderByCol="<%= commerceOrderEditDisplayContext.getOrderByCol() %>"
						orderByType="<%= commerceOrderEditDisplayContext.getOrderByType() %>"
						orderColumns='<%= new String[] {"create-date"} %>'
						portletURL="<%= portletURL %>"
					/>

					<li>
						<aui:form action="<%= portletURL %>" method="get" name="fm">
							<liferay-portlet:renderURLParams portletURL="<%= portletURL %>" />

							<liferay-ui:search-form
								page="/order/item_search.jsp"
								servletContext="<%= application %>"
							/>
						</aui:form>
					</li>
				</liferay-frontend:management-bar-filters>

				<liferay-frontend:management-bar-buttons>
					<portlet:actionURL name="editCommerceOrderItem" var="addCommerceOrderItemURL" />

					<aui:form action="<%= addCommerceOrderItemURL %>" cssClass="hide" name="addCommerceOrderItemFm">
						<aui:input name="<%= Constants.CMD %>" type="hidden" value="<%= Constants.ADD %>" />
						<aui:input name="redirect" type="hidden" value="<%= currentURL %>" />
						<aui:input name="commerceOrderId" type="hidden" value="<%= commerceOrderEditDisplayContext.getCommerceOrderId() %>" />
						<aui:input name="cpInstanceIds" type="hidden" value="" />
					</aui:form>

					<liferay-frontend:add-menu
						inline="<%= true %>"
					>
						<liferay-frontend:add-menu-item
							id="addCommerceOrderItem"
							title='<%= LanguageUtil.get(request, "add-item") %>'
							url="javascript:;"
						/>
					</liferay-frontend:add-menu>
				</liferay-frontend:management-bar-buttons>

				<liferay-frontend:management-bar-action-buttons>
					<liferay-frontend:management-bar-button
						href='<%= "javascript:" + renderResponse.getNamespace() + "deleteCommerceOrderItems();" %>'
						icon="times"
						label="delete"
					/>
				</liferay-frontend:management-bar-action-buttons>
			</liferay-frontend:management-bar>

			<commerce-ui:table
				dataProviderKey="<%= CommerceOrderItemClayTable.NAME %>"
				filter="<%= commerceOrderEditDisplayContext.getOrderItemFilter() %>"
				itemPerPage="<%= 5 %>"
				namespace="<%= renderResponse.getNamespace() %>"
				pageNumber="<%= 1 %>"
				portletURL="<%= commerceOrderEditDisplayContext.getCommerceOrderItemsPortletURL() %>"
				tableName="<%= CommerceOrderItemClayTable.NAME %>"
			/>
		</commerce-ui:panel>
	</div>

	<div class="col-12">
		<liferay-portlet:renderURL var="editOrderSummaryURL" windowState="<%= LiferayWindowState.POP_UP.toString() %>">
			<portlet:param name="mvcRenderCommandName" value="editCommerceOrderSummary" />
			<portlet:param name="commerceOrderId" value="<%= String.valueOf(commerceOrderEditDisplayContext.getCommerceOrderId()) %>" />
		</liferay-portlet:renderURL>

		<commerce-ui:modal
			closeOnSubmit="<%= true %>"
			id="order-summary-modal"
			showCancel="<%= true %>"
			showSubmit="<%= true %>"
			size="lg"
			title='<%= LanguageUtil.get(request, "order-summary") %>'
			url="<%= editOrderSummaryURL %>"
		/>

		<commerce-ui:panel
			headerActionId="order-summary-modal"
			headerActionLabel='<%= LanguageUtil.get(request, "edit") %>'
			headerActionTarget="order-summary-modal"
			headerActionUrl="<%= editOrderSummaryURL %>"
			title='<%= LanguageUtil.get(request, "order-summary") %>'
		>
			<commerce-ui:summary-table
				data="<%= commerceOrderEditDisplayContext.getSummary() %>"
			/>
		</commerce-ui:panel>
	</div>
</div>

<aui:script>
	function <portlet:namespace />deleteCommerceOrderItems() {
		if (confirm('<liferay-ui:message key="are-you-sure-you-want-to-delete-the-selected-order-items" />')) {
			var form = AUI.$(document.<portlet:namespace />fm);

			form.attr('method', 'post');
			form.fm('<%= Constants.CMD %>').val('<%= Constants.DELETE %>');
			form.fm('deleteCommerceOrderItemIds').val(Liferay.Util.listCheckedExcept(form, '<portlet:namespace />allRowIds'));

			submitForm(form, '<portlet:actionURL name="editCommerceOrderItem" />');
		}
	}
</aui:script>

<aui:script use="liferay-item-selector-dialog">
	$('#<portlet:namespace />addCommerceOrderItem').on(
		'click',
		function(event) {
			event.preventDefault();

			var itemSelectorDialog = new A.LiferayItemSelectorDialog(
				{
					eventName: 'productInstancesSelectItem',
					on: {
						selectedItemChange: function(event) {
							var selectedItems = event.newVal;

							if (selectedItems) {
								$('#<portlet:namespace />cpInstanceIds').val(selectedItems);

								var addCommerceOrderItemFm = $('#<portlet:namespace />addCommerceOrderItemFm');

								submitForm(addCommerceOrderItemFm);
							}
						}
					},
					title: '<liferay-ui:message arguments="<%= commerceOrderEditDisplayContext.getCommerceOrderId() %>" key="add-new-product-to-order-x" />',
					url: '<%= commerceOrderEditDisplayContext.getItemSelectorUrl() %>'
				}
			);

			itemSelectorDialog.open();
		}
	);
</aui:script>

<div id="<portlet:namespace />side-panel-root"></div>
<div id="<portlet:namespace />side-panel-wrapper"></div>

<aui:script require="commerce-frontend-js/js/side_panel/entry.es as SidePanel">
	new SidePanel.default(
		"<portlet:namespace />sidePanel",
		"<portlet:namespace />side-panel-root",
		{
			portalWrapperId: "<portlet:namespace />side-panel-wrapper",
			size: "lg",
			spritemap: "<%= themeDisplay.getPathThemeImages() + "/clay/icons.svg" %>",
			topAnchor: document.getElementById('commerce-admin-header')
		}
	);

	Promise.all(
		[
			Liferay.componentReady('commerceOrderItemsCommerceTable'),
			Liferay.componentReady('<portlet:namespace />sidePanel')
		]
	).then(function(
		[
			table,
			panel
		]
		) {
			document.getElementById('commerceOrderItemsCommerceTable')
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