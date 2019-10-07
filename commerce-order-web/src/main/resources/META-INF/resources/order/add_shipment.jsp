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

<portlet:actionURL name="editCommerceOrder" var="editCommerceOrderShipmentActionURL" />

<aui:form action="<%= editCommerceOrderShipmentActionURL %>" cssClass="container-fluid-1280 p-0" method="post" name="fm">
	<aui:input name="<%= Constants.CMD %>" type="hidden" value="shipment" />
	<aui:input name="redirect" type="hidden" value="<%= currentURL %>" />
	<aui:input name="commerceOrderId" type="hidden" value="<%= commerceOrder.getCommerceOrderId() %>" />

	<div class="border-0 sheet">
		<aui:select label="send-to" name="commerceAddressId" required="<%= true %>">

			<%
			for (CommerceAddress commerceAddress : commerceOrderEditDisplayContext.getCommerceAddresses()) {
			%>

				<aui:option label="<%= commerceOrderEditDisplayContext.getDescriptiveCommerceAddress(commerceAddress) %>" selected="<%= commerceOrder.getShippingAddressId() == commerceAddress.getCommerceAddressId() %>" value="<%= commerceAddress.getCommerceAddressId() %>" />

			<%
			}
			%>

		</aui:select>
	</div>

	<aui:button type="submit" />
</aui:form>