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

CommerceAddress billingCommerceAddress = commerceOrder.getBillingAddress();
CommerceAddress shippingCommerceAddress = commerceOrder.getShippingAddress();

CommerceOrderPrice commerceOrderPrice = commerceOrderContentDisplayContext.getCommerceOrderPrice();

CommerceMoney shippingValue = commerceOrderPrice.getShippingValue();
CommerceDiscountValue shippingDiscountValue = commerceOrderPrice.getShippingDiscountValue();
CommerceMoney subtotal = commerceOrderPrice.getSubtotal();
CommerceDiscountValue subtotalDiscountValue = commerceOrderPrice.getSubtotalDiscountValue();
CommerceMoney taxValue = commerceOrderPrice.getTaxValue();
CommerceDiscountValue totalDiscountValue = commerceOrderPrice.getTotalDiscountValue();
CommerceMoney totalOrder = commerceOrderPrice.getTotal();

CommerceAccount commerceAccount = commerceOrderContentDisplayContext.getCommerceAccount();

if (commerceOrder != null) {
	commerceAccount = commerceOrder.getCommerceAccount();
}
%>

<div class="commerce-panel">
	<div class="commerce-panel__content">
		<div class="align-items-center row">
			<div class="col-md-3">
				<div class="commerce-order-title">
					<%= HtmlUtil.escape(commerceAccount.getName()) %>
				</div>
			</div>

			<div class="col-md-3">
				<dl class="commerce-list">
					<dt><liferay-ui:message key="total" /></dt>
					<dd><%= HtmlUtil.escape(totalOrder.format(locale)) %></dd>
				</dl>
			</div>

			<div class="col-md-3">
				<dl class="commerce-list">
					<dt><liferay-ui:message key="notes" /></dt>
					<dd>

						<%
						request.setAttribute("order_notes.jsp-showLabel", Boolean.TRUE);
						request.setAttribute("order_notes.jsp-taglibLinkCssClass", "link-outline link-outline-borderless link-outline-secondary lfr-icon-item-reverse");
						%>

						<liferay-util:include page="/placed_orders/order_notes.jsp" servletContext="<%= application %>" />
					</dd>
				</dl>
			</div>
		</div>
	</div>

	<div class="commerce-panel__content">
		<div class="align-items-center row">
			<div class="col-md-3">
				<dl class="commerce-list">
					<dt><liferay-ui:message key="account-id" /></dt>
					<dd><%= commerceAccount.getCommerceAccountId() %></dd>
				</dl>
			</div>

			<div class="col-md-3">
				<dl class="commerce-list">
					<dt><liferay-ui:message key="order-id" /></dt>
					<dd><%= commerceOrder.getCommerceOrderId() %></dd>
				</dl>
			</div>

			<div class="col-md-3">
				<dl class="commerce-list">
					<dt><liferay-ui:message key="order-date" /></dt>
					<dd>
						<%= commerceOrderContentDisplayContext.getCommerceOrderDate(commerceOrder) %>
						<%= commerceOrderContentDisplayContext.getCommerceOrderTime(commerceOrder) %>
					</dd>
				</dl>
			</div>
		</div>
	</div>
</div>

<c:if test="<%= commerceOrderContentDisplayContext.isShowPurchaseOrderNumber() %>">
	<div class="row">
		<div class="col-md-12">
			<div class="commerce-panel">
				<div class="commerce-panel__title"><liferay-ui:message key="purchase-order-number" /></div>
				<div class="commerce-panel__content">
					<div class="row">
						<div class="col-md-6">
							<dl class="commerce-list">
								<%= commerceOrder.getPurchaseOrderNumber() %>
							</dl>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</c:if>

<div class="row">
	<div class="col-md-6">
		<div class="commerce-panel">
			<div class="commerce-panel__title"><liferay-ui:message key="billing-address" /></div>
			<div class="commerce-panel__content">
				<div class="row">
					<div class="col-md-12">
						<c:if test="<%= billingCommerceAddress != null %>">
							<%= billingCommerceAddress.getStreet1() %><br />
							<%= billingCommerceAddress.getCity() + StringPool.SPACE + billingCommerceAddress.getZip() %>
						</c:if>
					</div>
				</div>
			</div>
		</div>
	</div>

	<div class="col-md-6">
		<div class="commerce-panel">
			<div class="commerce-panel__title"><liferay-ui:message key="shipping-address" /></div>
			<div class="commerce-panel__content">
				<div class="row">
					<div class="col-md-12">
						<c:if test="<%= shippingCommerceAddress != null %>">
							<%= shippingCommerceAddress.getStreet1() %><br />
							<%= shippingCommerceAddress.getCity() + StringPool.SPACE + shippingCommerceAddress.getZip() %>
						</c:if>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>

<div class="commerce-cta is-visible">
	<portlet:actionURL name="editCommerceOrder" var="editCommerceOrderActionURL">
		<portlet:param name="mvcRenderCommandName" value="viewCommerceOrderDetails" />
	</portlet:actionURL>

	<aui:form action="<%= editCommerceOrderActionURL %>" method="post" name="fm">
		<aui:input name="<%= Constants.CMD %>" type="hidden" />
		<aui:input name="commerceOrderId" type="hidden" value="<%= String.valueOf(commerceOrder.getCommerceOrderId()) %>" />
	</aui:form>

	<aui:button cssClass="commerce-button commerce-button--big commerce-button--outline" onClick='<%= renderResponse.getNamespace() + "reorderCommerceOrder();" %>' value="reorder" />
</div>

<div class="row">
	<div class="col-md-12">
		<commerce-ui:table
			dataProviderKey="commercePlacedOrderItems"
			itemPerPage="<%= 5 %>"
			namespace="<%= renderResponse.getNamespace() %>"
			pageNumber="1"
			portletURL="<%= commerceOrderContentDisplayContext.getPortletURL() %>"
			tableName="commercePlacedOrderItems"
		/>
	</div>
</div>

<div class="row">
	<div class="col-md-9">
	</div>

	<div class="col-md-3">
		<div class="commerce-panel">
			<div class="commerce-panel__content">
				<dl class="commerce-list">
					<dt><liferay-ui:message key="subtotal" /></dt>
					<dd class="text-right"><%= HtmlUtil.escape(subtotal.format(locale)) %></dd>

					<c:if test="<%= (subtotalDiscountValue != null) && (BigDecimal.ZERO.compareTo(subtotalDiscountValue.getDiscountPercentage()) < 0) %>">

						<%
						CommerceMoney subtotalDiscountAmount = subtotalDiscountValue.getDiscountAmount();
						%>

						<dt><liferay-ui:message key="subtotal-discount" /></dt>
						<dd class="text-right"><%= HtmlUtil.escape(subtotalDiscountAmount.format(locale)) %></dd>
						<dt></dt>
						<dd class="text-right"><%= HtmlUtil.escape(commerceOrderContentDisplayContext.getFormattedPercentage(subtotalDiscountValue.getDiscountPercentage())) %></dd>
					</c:if>

					<dt><liferay-ui:message key="delivery" /></dt>
					<dd class="text-right"><%= HtmlUtil.escape(shippingValue.format(locale)) %></dd>

					<c:if test="<%= (shippingDiscountValue != null) && (BigDecimal.ZERO.compareTo(shippingDiscountValue.getDiscountPercentage()) < 0) %>">

						<%
						CommerceMoney shippingDiscountAmount = shippingDiscountValue.getDiscountAmount();
						%>

						<dt><liferay-ui:message key="delivery-discount" /></dt>
						<dd class="text-right"><%= HtmlUtil.escape(shippingDiscountAmount.format(locale)) %></dd>
						<dt></dt>
						<dd class="text-right"><%= HtmlUtil.escape(commerceOrderContentDisplayContext.getFormattedPercentage(shippingDiscountValue.getDiscountPercentage())) %></dd>
					</c:if>

					<dt><liferay-ui:message key="tax" /></dt>
					<dd class="text-right"><%= HtmlUtil.escape(taxValue.format(locale)) %></dd>

					<c:if test="<%= (totalDiscountValue != null) && (BigDecimal.ZERO.compareTo(totalDiscountValue.getDiscountPercentage()) < 0) %>">

						<%
						CommerceMoney totalDiscountAmount = totalDiscountValue.getDiscountAmount();
						%>

						<dt><liferay-ui:message key="delivery-discount" /></dt>
						<dd class="text-right"><%= HtmlUtil.escape(totalDiscountAmount.format(locale)) %></dd>
						<dt></dt>
						<dd class="text-right"><%= HtmlUtil.escape(commerceOrderContentDisplayContext.getFormattedPercentage(totalDiscountValue.getDiscountPercentage())) %></dd>
					</c:if>
				</dl>
			</div>

			<div class="commerce-panel__content">
				<dl class="commerce-list">
					<dt><liferay-ui:message key="total" /></dt>
					<dd class="text-right"><%= HtmlUtil.escape(totalOrder.format(locale)) %></dd>
				</dl>
			</div>
		</div>
	</div>
</div>

<aui:script>
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