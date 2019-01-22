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
CommerceSubscriptionEntryDisplayContext commerceSubscriptionEntryDisplayContext = (CommerceSubscriptionEntryDisplayContext)request.getAttribute(WebKeys.PORTLET_DISPLAY_CONTEXT);

SearchContainer<CommerceOrder> commerceSubscriptionEntryOrderSearchContainer = commerceSubscriptionEntryDisplayContext.getCommerceSubscriptionEntryOrderSearchContainer();

String redirect = ParamUtil.getString(request, "redirect");

if (Validator.isNotNull(redirect)) {
	portletDisplay.setShowBackIcon(true);
	portletDisplay.setURLBack(redirect);
}
%>

<div class="container-fluid-1280" id="<portlet:namespace />subscriptionOrderContainer">
	<div class="product-subscription-orders-container" id="<portlet:namespace />entriesContainer">
		<liferay-ui:search-container
			id="commerceSubscriptionEntryOrders"
			searchContainer="<%= commerceSubscriptionEntryOrderSearchContainer %>"
		>
			<liferay-ui:search-container-row
				className="com.liferay.commerce.model.CommerceOrder"
				cssClass="entry-display-style"
				keyProperty="commerceOrderId"
				modelVar="commerceOrder"
			>
				<liferay-ui:search-container-column-text
					cssClass="important table-cell-content"
					href="<%= commerceSubscriptionEntryDisplayContext.getEditCommerceOrderURL(commerceOrder.getCommerceOrderId()) %>"
					name="order-id"
					property="commerceOrderId"
				/>

				<liferay-ui:search-container-column-text
					cssClass="table-cell-content"
					name="order-date"
					value="<%= HtmlUtil.escape(commerceSubscriptionEntryDisplayContext.getCommerceOrderDateTime(commerceOrder)) %>"
				/>
			</liferay-ui:search-container-row>

			<liferay-ui:search-iterator
				markupView="lexicon"
			/>
		</liferay-ui:search-container>
	</div>
</div>