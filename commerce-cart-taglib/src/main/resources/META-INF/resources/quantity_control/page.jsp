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

<%@ include file="/quantity_control/init.jsp" %>

<%
CommerceOrderItem commerceOrderItem = (CommerceOrderItem)request.getAttribute("liferay-commerce-cart:quantity-control:commerceOrderItem");
boolean showInputLabel = (boolean)request.getAttribute("liferay-commerce-cart:quantity-control:showInputLabel");
boolean updateOnChange = (boolean)request.getAttribute("liferay-commerce-cart:quantity-control:updateOnChange");
boolean useSelect = (boolean)request.getAttribute("liferay-commerce-cart:quantity-control:useSelect");

String randomNamespace = PortalUtil.generateRandomKey(request, "taglib_commerce_cart_quantity_control_page") + StringPool.UNDERLINE;
%>

<liferay-portlet:actionURL name="editCommerceOrderItem" portletName="<%= CommercePortletKeys.COMMERCE_CART_CONTENT %>" var="editCommerceOrderItemURL" />

<aui:form action="<%= editCommerceOrderItemURL %>" method="post" name='<%= randomNamespace + "Fm" %>' portletNamespace="<%= PortalUtil.getPortletNamespace(CommercePortletKeys.COMMERCE_CART_CONTENT) %>">
	<aui:input name="<%= Constants.CMD %>" type="hidden" value="<%= Constants.UPDATE %>" />
	<aui:input name="redirect" type="hidden" value="<%= PortalUtil.getCurrentURL(request) %>" />
	<aui:input name="commerceOrderItemId" type="hidden" value="<%= commerceOrderItem.getCommerceOrderItemId() %>" />
	<aui:input name="quantity" type="hidden" value="<%= commerceOrderItem.getQuantity() %>" />

	<aui:model-context bean="<%= commerceOrderItem %>" model="<%= CommerceOrderItem.class %>" />

	<liferay-commerce:quantity-input
		CPDefinitionId="<%= commerceOrderItem.getCPDefinitionId() %>"
		name='<%= randomNamespace + "Quantity" %>'
		showLabel="<%= showInputLabel %>"
		useSelect="<%= useSelect %>"
		value="<%= commerceOrderItem.getQuantity() %>"
	/>

	<c:if test="<%= !updateOnChange %>">
		<aui:button type="submit" value="update" />
	</c:if>
</aui:form>

<c:if test="<%= updateOnChange %>">
	<aui:script use="aui-base">
		var form = A.one('#<portlet:namespace /><%= randomNamespace + "Fm" %>');

		form.delegate(
			'change',
			function() {
				var quantity = form.one('#<portlet:namespace /><%= randomNamespace + "Quantity" %>')

				form.one('#<portlet:namespace />quantity').val(quantity.val());

				submitForm(document.<portlet:namespace /><%= randomNamespace + "Fm" %>);
			},
			'select'
		);
	</aui:script>
</c:if>