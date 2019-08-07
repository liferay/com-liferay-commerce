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
%>

<div class="container-fluid-1280 mt-4 sheet">
	<portlet:actionURL name="editCommerceOrderConfiguration" var="editCommerceOrderConfigurationActionURL" />

	<aui:form action="<%= editCommerceOrderConfigurationActionURL %>" method="post" name="fm">
		<aui:input name="redirect" type="hidden" value="<%= currentURL %>" />

		<aui:fieldset>
			<aui:input checked="<%= commerceOrderContentDisplayContext.isShowPurchaseOrderNumber() %>" helpMessage="configures-whether-purchase-order-number-is-shown-or-hidden-in-placed-and-pending-order-details" label="purchase-order-number" labelOff="hide" labelOn="show" name="settings--showPurchaseOrderNumber--" type="toggle-switch" />
		</aui:fieldset>

		<aui:button-row>
			<aui:button cssClass="btn-lg" type="submit" value="save" />
		</aui:button-row>
	</aui:form>
</div>