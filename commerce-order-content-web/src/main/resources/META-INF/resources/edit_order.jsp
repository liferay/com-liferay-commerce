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
CommerceOrderContentDisplayContext commerceOrderContentDisplayContext = (CommerceOrderContentDisplayContext)request.getAttribute(WebKeys.PORTLET_DISPLAY_CONTEXT);

CommerceOrder commerceOrder = commerceOrderContentDisplayContext.getCommerceOrder();

CommerceOrderPrice commerceOrderPrice = commerceOrderContentDisplayContext.getCommerceOrderPrice();

CommerceMoney shippingValue = commerceOrderPrice.getShippingValue();
CommerceDiscountValue shippingDiscountValue = commerceOrderPrice.getShippingDiscountValue();
CommerceMoney subtotal = commerceOrderPrice.getSubtotal();
CommerceDiscountValue subtotalDiscountValue = commerceOrderPrice.getSubtotalDiscountValue();
CommerceMoney taxValue = commerceOrderPrice.getTaxValue();
CommerceDiscountValue totalDiscountValue = commerceOrderPrice.getTotalDiscountValue();
CommerceMoney totalOrder = commerceOrderPrice.getTotal();

List<CommerceOrderValidatorResult> commerceOrderValidatorResults = new ArrayList<>();
%>

<portlet:actionURL name="editCommerceOrder" var="editCommerceOrderActionURL">
	<portlet:param name="mvcRenderCommandName" value="editCommerceOrder" />
</portlet:actionURL>

<liferay-ui:error exception="<%= CommerceOrderValidatorException.class %>">

	<%
	CommerceOrderValidatorException cove = (CommerceOrderValidatorException)errorException;

	if (cove != null) {
		commerceOrderValidatorResults = cove.getCommerceOrderValidatorResults();
	}

	for (CommerceOrderValidatorResult commerceOrderValidatorResult : commerceOrderValidatorResults) {
	%>

		<liferay-ui:message key="<%= commerceOrderValidatorResult.getLocalizedMessage() %>" />

	<%
	}
	%>

</liferay-ui:error>

<div class="minium-card">
	<div class="minium-card__content">
		<div class="row align-items-center">
			<div class="col-md-3">
				<div class="minium-order-title">
					Bob's Sporting Mx
				</div>
			</div>
			<div class="col-md-3">
				<dl class="minium-list">
					<dt>Subtotal:</dt>
					<dd class="text-right"><%= HtmlUtil.escape(subtotal.format(locale)) %></dd>
					<dt>Delivery:</dt>
					<dd class="text-right"><%= HtmlUtil.escape(shippingValue.format(locale)) %></dd>
					<dt>Tax:</dt>
					<dd class="text-right"><%= HtmlUtil.escape(taxValue.format(locale)) %></dd>
					<dt>Total:</dt>
					<dd class="text-right"><%= HtmlUtil.escape(totalOrder.format(locale)) %></dd>
				</dl>
			</div>
			<div class="col-md-3">
				<dl class="minium-list">
					<dt>Order notes</dt>
					<dd>
						<%
							request.setAttribute("order_notes.jsp-showLabel", Boolean.TRUE);
							request.setAttribute("order_notes.jsp-taglibLinkCssClass", "link-outline link-outline-borderless link-outline-secondary lfr-icon-item-reverse");
						%>

						<liferay-util:include page="/order_notes.jsp" servletContext="<%= application %>" />
					</dd>
				</dl>
			</div>
			<div class="col-md-3 text-right">
				<c:if test="<%= commerceOrder.isOpen() %>">
					<liferay-commerce:order-transitions
						commerceOrderId="<%= commerceOrder.getCommerceOrderId() %>"
						cssClass="minium-button"
					/>
				</c:if>
			</div>
		</div>
	</div>
	<div class="minium-card__content">
		<div class="row align-items-center">
			<div class="col-md-3">
				<dl class="minium-list">
					<dt>Account ID</dt>
					<dd>XXXXXX</dd>
				</dl>
			</div>
			<div class="col-md-3">
				<dl class="minium-list">
					<dt>Order ID</dt>
					<dd><%= commerceOrder.getCommerceOrderId() %></dd>
				</dl>
			</div>
			<div class="col-md-3">
				<dl class="minium-list">
					<dt>PO</dt>
					<dd>XXXXXX</dd>
				</dl>
			</div>
			<div class="col-md-3"></div>
		</div>
	</div>
</div>

<div class="row">
	<div class="col-md-6">
		<div class="minium-card">
			<div class="minium-card__title">Billing address</div>
			<div class="minium-card__content">
				<div class="row">
					<div class="col-md-8">
						<dl class="minium-list">
							<dt>Address</dt>
							<dd>
								<input type="text" class="minium-input">
							</dd>
						</dl>
					</div>
					<div class="col-md-4">
						<div class="minium-button minium-button--outline minium-button--small">Add address</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	<div class="col-md-6">
		<div class="minium-card">
			<div class="minium-card__title">Shipping address & date</div>
			<div class="minium-card__content">
				<div class="row">
					<div class="col-md-8">
						<dl class="minium-list">
							<dt>Address</dt>
							<dd>
								<input type="text" class="minium-input">
							</dd>
						</dl>
						<dl class="minium-list">
							<dt>Request delivery date</dt>
							<dd>
								<input type="text" class="minium-input">
							</dd>
						</dl>
					</div>
					<div class="col-md-4">
						<div class="minium-button minium-button--outline minium-button--small">Add address</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>




		<%-- <div class="col-md-auto">
			<liferay-ui:icon
				icon="print"
				iconCssClass="inline-item inline-item-before"
				label="<%= true %>"
				linkCssClass="minium-button minium-button--outline minium-button--small"
				markupView="lexicon"
				message="print"
				method="get"
				url="javascript:window.print();"
			/>
		</div> --%>

		<%-- <div class="col-md-auto">

			<%
			request.setAttribute("order_notes.jsp-showLabel", Boolean.TRUE);
			request.setAttribute("order_notes.jsp-taglibLinkCssClass", "link-outline link-outline-borderless link-outline-secondary lfr-icon-item-reverse");
			%>

			<liferay-util:include page="/order_notes.jsp" servletContext="<%= application %>" />
		</div> --%>



<div class="minium-thumb-menu">
		<c:if test="<%= commerceOrder.isOpen() %>">
			<%-- <div class="col-md-auto">
				<liferay-commerce:order-transitions
					commerceOrderId="<%= commerceOrder.getCommerceOrderId() %>"
					cssClass="minium-button minium-button--small"
				/>
			</div> --%>

			<c:if test="<%= commerceOrderContentDisplayContext.hasModelPermission(commerceOrder, ActionKeys.UPDATE) %>">
				<div class="col-md-auto">
					<liferay-ui:icon-menu
						direction="right"
						icon="<%= StringPool.BLANK %>"
						markupView="lexicon"
						message="<%= StringPool.BLANK %>"
						showWhenSingleIcon="<%= true %>"
						triggerCssClass="component-action"
					>
						<liferay-ui:icon
							message="print"
							url='<%= "javascript:window.print();" %>'
						/>

						<liferay-ui:icon
							message="edit"
							url='<%= "javascript:" + renderResponse.getNamespace() + "editCommerceOrder();" %>'
						/>

						<portlet:actionURL name="editCommerceOrderItem" var="deleteURL">
							<portlet:param name="<%= Constants.CMD %>" value="<%= Constants.RESET %>" />
							<portlet:param name="redirect" value="<%= currentURL %>" />
							<portlet:param name="commerceOrderId" value="<%= String.valueOf(commerceOrder.getCommerceOrderId()) %>" />
						</portlet:actionURL>

						<liferay-ui:icon-delete
							message="delete-all"
							url="<%= deleteURL %>"
						/>
					</liferay-ui:icon-menu>
				</div>
			</c:if>
		</c:if>
</div>



<aui:form action="<%= editCommerceOrderActionURL %>" cssClass="order-details-container" method="post" name="fm">
	<aui:input name="<%= Constants.CMD %>" type="hidden" />
	<aui:input name="commerceOrderId" type="hidden" value="<%= String.valueOf(commerceOrder.getCommerceOrderId()) %>" />
<%-- 
	<div class="order-details-header row">
		<div class="col-3">
			<div class="minium-card">
				<h3 class="minium-card__title"><liferay-ui:message key="order-date" /></h3>

				<div class="minium-card__content">
					<p>
						<%= commerceOrderContentDisplayContext.getCommerceOrderDate(commerceOrder) %>
						<%= commerceOrderContentDisplayContext.getCommerceOrderTime(commerceOrder) %>
					</p>
				</div>
			</div>
		</div>

		<div class="col-3">
			<div class="minium-card">
				<h3 class="minium-card__title"><liferay-ui:message key="customer" /></h3>

				<div class="minium-card__content">
					<p>
						<%= commerceOrder.getCommerceAccountId() %>:
						<%= HtmlUtil.escape(commerceOrder.getCommerceAccountName()) %>
					</p>
				</div>
			</div>
		</div>

		<div class="col-3">
			<div class="minium-card">
				<h3 class="minium-card__title"><liferay-ui:message key="payment" /></h3>

				<div class="minium-card__content">

					<%
					String paymentMethodName = commerceOrderContentDisplayContext.getCommerceOrderPaymentMethodName(commerceOrder);
					%>

					<c:if test="<%= Validator.isNotNull(paymentMethodName) %>">
						<div class="order-details-subtitle payment-method-name">
							<%= HtmlUtil.escape(paymentMethodName) %>
						</div>
					</c:if>

					<p>
						<liferay-ui:message arguments="<%= HtmlUtil.escape(commerceOrder.getPurchaseOrderNumber()) %>" key="purchase-order-number-x" translateArguments="<%= false %>" />
					</p>
				</div>
			</div>
		</div>

		<div class="col-3">
			<div class="minium-card">
				<h3 class="minium-card__title"><liferay-ui:message key="status" /></h3>

				<div class="minium-card__content">
					<p>
						<%= commerceOrderContentDisplayContext.getCommerceOrderStatus(commerceOrder) %>
					</p>
				</div>
			</div>
		</div>

		<c:if test="<%= !commerceOrder.isOpen() %>">
			<div class="col-3 order-details-reorder">
				<div class="autofit-section">
					<aui:button icon="icon-refresh" iconAlign="right" onClick='<%= renderResponse.getNamespace() + "reorderCommerceOrder();" %>' primary="<%= true %>" value="reorder" />
				</div>
			</div>
		</c:if>
	</div> --%>
</aui:form>

<commerce-ui:table
	dataProviderKey="commerceOrderItems"
	itemPerPage="<%= 5 %>"
	namespace="<%= renderResponse.getNamespace() %>"
	pageNumber="1"
	portletURL="<%= commerceOrderContentDisplayContext.getPortletURL() %>"
	tableName="commerceOrderItems"
/>
<%-- 
<aui:row>
	<aui:col width="<%= 70 %>" />

	<aui:col width="<%= 30 %>">
		<ul class="order-details-footer">
			<li class="autofit-row commerce-subtotal">
				<div class="autofit-col autofit-col-expand">
					<div class="commerce-description"><liferay-ui:message key="subtotal" /></div>
				</div>

				<div class="autofit-col">
					<div class="commerce-value"><%= HtmlUtil.escape(subtotal.format(locale)) %></div>
				</div>
			</li>

			<c:if test="<%= (subtotalDiscountValue != null) && (BigDecimal.ZERO.compareTo(subtotalDiscountValue.getDiscountPercentage()) < 0) %>">

				<%
				CommerceMoney subtotalDiscountAmount = subtotalDiscountValue.getDiscountAmount();
				%>

				<li class="autofit-row commerce-subtotal-discount">
					<div class="autofit-col autofit-col-expand">
						<div class="commerce-description"><liferay-ui:message key="subtotal-discount" /></div>
					</div>

					<div class="order-details-subtitle order-value">
						<%= HtmlUtil.escape(subtotalDiscountAmount.format(locale)) %>
					</div>
				</li>
				<li class="autofit-row commerce-subtotal-discount">
					<div class="autofit-col autofit-col-expand"></div>

					<div class="order-details-subtitle order-value">
						<%= HtmlUtil.escape(commerceOrderContentDisplayContext.getFormattedPercentage(subtotalDiscountValue.getDiscountPercentage())) %>
					</div>
				</li>
			</c:if>

			<li class="autofit-row commerce-delivery">
				<div class="autofit-col autofit-col-expand">
					<div class="commerce-description"><liferay-ui:message key="delivery" /></div>
				</div>

				<div class="autofit-col">
					<div class="commerce-value"><%= HtmlUtil.escape(shippingValue.format(locale)) %></div>
				</div>
			</li>

			<c:if test="<%= (shippingDiscountValue != null) && (BigDecimal.ZERO.compareTo(shippingDiscountValue.getDiscountPercentage()) < 0) %>">

				<%
				CommerceMoney shippingDiscountAmount = shippingDiscountValue.getDiscountAmount();
				%>

				<li class="autofit-row commerce-delivery-discount">
					<div class="autofit-col autofit-col-expand">
						<div class="commerce-description"><liferay-ui:message key="delivery-discount" /></div>
					</div>

					<div class="order-details-subtitle order-value">
						<%= HtmlUtil.escape(shippingDiscountAmount.format(locale)) %>
					</div>
				</li>
				<li class="autofit-row commerce-delivery-discount">
					<div class="autofit-col autofit-col-expand"></div>

					<div class="order-details-subtitle order-value">
						<%= HtmlUtil.escape(commerceOrderContentDisplayContext.getFormattedPercentage(shippingDiscountValue.getDiscountPercentage())) %>
					</div>
				</li>
			</c:if>

			<li class="autofit-row commerce-tax">
				<div class="autofit-col autofit-col-expand">
					<div class="commerce-description"><liferay-ui:message key="tax" /></div>
				</div>

				<div class="autofit-col">
					<div class="commerce-value"><%= HtmlUtil.escape(taxValue.format(locale)) %></div>
				</div>
			</li>

			<c:if test="<%= (totalDiscountValue != null) && (BigDecimal.ZERO.compareTo(totalDiscountValue.getDiscountPercentage()) < 0) %>">

				<%
				CommerceMoney totalDiscountAmount = totalDiscountValue.getDiscountAmount();
				%>

				<li class="autofit-row commerce-total-discount">
					<div class="autofit-col autofit-col-expand">
						<div class="commerce-description"><liferay-ui:message key="total-discount" /></div>
					</div>

					<div class="order-details-subtitle order-value">
						<%= HtmlUtil.escape(totalDiscountAmount.format(locale)) %>
					</div>
				</li>
				<li class="autofit-row commerce-total-discount">
					<div class="autofit-col autofit-col-expand"></div>

					<div class="order-details-subtitle order-value">
						<%= HtmlUtil.escape(commerceOrderContentDisplayContext.getFormattedPercentage(totalDiscountValue.getDiscountPercentage())) %>
					</div>
				</li>
			</c:if>

			<li class="autofit-row commerce-total">
				<div class="autofit-col autofit-col-expand">
					<div class="commerce-description order-total"><liferay-ui:message key="total" /></div>
				</div>

				<div class="autofit-col">
					<div class="commerce-value order-total"><%= HtmlUtil.escape(totalOrder.format(locale)) %></div>
				</div>
			</li>
		</ul>
	</aui:col>
</aui:row> --%>

<liferay-portlet:renderURL var="editCommerceOrderDetailsURL" windowState="<%= LiferayWindowState.EXCLUSIVE.toString() %>">
	<liferay-portlet:param name="mvcRenderCommandName" value="editCommerceOrderDetails" />
	<liferay-portlet:param name="redirect" value="<%= currentURL %>" />
	<liferay-portlet:param name="commerceOrderId" value="<%= String.valueOf(commerceOrder.getCommerceOrderId()) %>" />
</liferay-portlet:renderURL>

<aui:script>
	Liferay.provide(
		window,
		'<portlet:namespace />editCommerceOrder',
		function(A) {
			var A = AUI();

			var dialog = Liferay.Util.Window.getWindow(
				{
					dialog: {
						destroyOnClose: true,
						toolbars: {
							footer: [
								{
									cssClass: 'btn-cancel mr-2',
									label: '<liferay-ui:message key="cancel" />',
									on: {
										click: function() {
											dialog.hide();
										}
									}
								},
								{
								cssClass: 'btn-primary',
								label: '<liferay-ui:message key="edit-order" />',
								on: {
									click: function() {
										submitForm(document.<portlet:namespace />editFm);
									}
								}
							}
						],
						header: [
							{
								cssClass: 'close',
								discardDefaultButtonCssClasses: true,
								labelHTML: '<clay:icon symbol="times" />',
								on: {
									click: function(event) {
										dialog.hide();
									}
								}
							}
						]
					},
					width: 600
				},
				title: '<liferay-ui:message key="edit-order" />'
			}
		).plug(
			A.Plugin.IO,
				{
					uri: '<%= editCommerceOrderDetailsURL %>'
				}
			).render();
		},
		['aui-io-deprecated', 'liferay-util-window']
	);

	function <portlet:namespace/>viewCommerceOrderShipments(uri) {
		Liferay.Util.openWindow(
			{
				dialog: {
					centered: true,
					destroyOnClose: true,
					modal: true
				},
				dialogIframe: {
					bodyCssClass: 'dialog'
				},
				id: 'viewCommerceOrderShipmentsDialog',
				title: '',
				uri: uri
			}
		);
	}

	function <portlet:namespace />reorderCommerceOrder() {
		document.<portlet:namespace />fm.<portlet:namespace /><%= Constants.CMD %>.value = 'reorder';

		submitForm(document.<portlet:namespace />fm);
	}
</aui:script>