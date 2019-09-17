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

portletDisplay.setShowBackIcon(true);

if (Validator.isNull(redirect)) {
	portletDisplay.setURLBack(String.valueOf(renderResponse.createRenderURL()));
}
else {
	portletDisplay.setURLBack(redirect);
}

String headerTitle = null;

if (commerceOrder != null) {
	headerTitle = LanguageUtil.format(request, "order-x", commerceOrder.getCommerceOrderId());
}
else {
	headerTitle = LanguageUtil.get(request, "add-order");
}
%>

<commerce-ui:header
	bean="<%= commerceOrder %>"
	dropdownItems="<%= commerceOrderEditDisplayContext.getDropdownItems() %>"
	headerActions="<%= commerceOrderEditDisplayContext.getHeaderActionModels() %>"
	model="<%= CommerceOrder.class %>"
	thumbnailUrl="<%= commerceOrderEditDisplayContext.getCommerceAccountThumbnailURL() %>"
	title="<%= headerTitle %>"
/>

<div id="<portlet:namespace />editOrderContainer">
	<liferay-frontend:screen-navigation
		fullContainerCssClass="mt-4 w-100"
		key="<%= CommerceOrderScreenNavigationConstants.SCREEN_NAVIGATION_KEY_COMMERCE_ORDER_GENERAL %>"
		modelBean="<%= commerceOrder %>"
		portletURL="<%= currentURLObj %>"
	/>
</div>