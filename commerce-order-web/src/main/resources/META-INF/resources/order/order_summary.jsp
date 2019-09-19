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
%>

<portlet:actionURL name="editCommerceOrder" var="editCommerceOrderSummaryActionURL" />

<aui:form action="<%= editCommerceOrderSummaryActionURL %>" cssClass="container-fluid-1280 p-0" method="post" name="fm">
	<aui:input name="<%= Constants.CMD %>" type="hidden" value="orderSummary" />
	<aui:input name="redirect" type="hidden" value="<%= currentURL %>" />
	<aui:input name="commerceOrderId" type="hidden" value="<%= commerceOrder.getCommerceOrderId() %>" />

	<aui:model-context bean="<%= commerceOrder %>" model="<%= CommerceOrder.class %>" />

	<div class="border-0 sheet">
		<aui:input label="items-subtotal" name="subtotal" wrapperCssClass="form-group-item" />

		<aui:input label="items-subtotal-discount" name="subtotalDiscountAmount" wrapperCssClass="form-group-item" />

		<aui:input label="order-discount" name="totalDiscountAmount" wrapperCssClass="form-group-item" />

		<aui:input label="promotion-code" name="couponCode" wrapperCssClass="form-group-item" />

		<aui:input label="estimated-tax" name="taxAmount" wrapperCssClass="form-group-item" />

		<aui:input label="shipping-and-handing" name="shippingAmount" wrapperCssClass="form-group-item" />

		<aui:input label="shipping-and-handing-discount" name="shippingDiscountAmount" wrapperCssClass="form-group-item" />

		<aui:input label="grand-total" name="total" wrapperCssClass="form-group-item" />
	</div>

	<aui:button type="submit" />
</aui:form>