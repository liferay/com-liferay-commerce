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

CommerceSubscriptionEntry commerceSubscriptionEntry = commerceSubscriptionEntryDisplayContext.getCommerceSubscriptionEntry();

renderResponse.setTitle(LanguageUtil.format(request, "subscription-x", commerceSubscriptionEntry.getCommerceSubscriptionEntryId()));
%>

<liferay-frontend:screen-navigation
	containerCssClass="col-md-10"
	key="<%= CommerceSubscriptionEntryScreenNavigationConstants.SCREEN_NAVIGATION_KEY_COMMERCE_SUBSCRIPTION_ENTRY %>"
	modelBean="<%= commerceSubscriptionEntry %>"
	navCssClass="col-md-2"
	portletURL="<%= currentURLObj %>"
/>