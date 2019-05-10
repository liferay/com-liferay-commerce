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

<%@ include file="/asset/init.jsp" %>

<%
CommerceOrder commerceOrder = (CommerceOrder)request.getAttribute(CommerceOrderConstants.COMMERCE_ORDER);
%>

<div class="container-fluid-1280">
	<h4><liferay-ui:message key="order-details" /></h4>

	<liferay-ui:search-container
		id="commerceOrderItems"
	>
		<liferay-ui:search-container-results
			results="<%= commerceOrder.getCommerceOrderItems() %>"
			resultsVar="commerceOrderItems"
		/>

		<liferay-ui:search-container-row
			className="com.liferay.commerce.model.CommerceOrderItem"
			escapedModel="<%= true %>"
			keyProperty="commerceOrderItemId"
			modelVar="commerceOrderItem"
		>
			<liferay-ui:search-container-column-text
				cssClass="important table-cell-content"
				property="sku"
			/>

			<liferay-ui:search-container-column-text
				cssClass="table-cell-content"
				name="name"
				value="<%= commerceOrderItem.getName(locale) %>"
			/>

			<liferay-ui:search-container-column-text
				cssClass="table-cell-content"
				property="quantity"
			/>

			<%
			CommerceMoney finalPriceMoney = commerceOrderItem.getFinalPriceMoney();
			%>

			<liferay-ui:search-container-column-text
				cssClass="table-cell-content"
				name="price"
				value="<%= finalPriceMoney.format(locale) %>"
			/>
		</liferay-ui:search-container-row>

		<liferay-ui:search-iterator
			markupView="lexicon"
		/>
	</liferay-ui:search-container>
</div>