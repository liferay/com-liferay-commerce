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
ServletContext commerceAdminServletContext = (ServletContext)request.getAttribute(CommerceAdminWebKeys.COMMERCE_ADMIN_SERVLET_CONTEXT);

CommercePaymentMethodsDisplayContext commercePaymentMethodsDisplayContext = (CommercePaymentMethodsDisplayContext)request.getAttribute(WebKeys.PORTLET_DISPLAY_CONTEXT);

CommercePaymentMethod commercePaymentMethod = commercePaymentMethodsDisplayContext.getCommercePaymentMethod();

String title = LanguageUtil.format(request, "edit-x", commercePaymentMethod.getName(locale), false);

Map<String, Object> data = new HashMap<>();

data.put("direction-right", StringPool.TRUE);

PortalUtil.addPortletBreadcrumbEntry(request, LanguageUtil.get(request, commerceAdminModuleKey), redirect, data);
PortalUtil.addPortletBreadcrumbEntry(request, title, currentURL, data);
PortalUtil.addPortletBreadcrumbEntry(request, LanguageUtil.get(request, commercePaymentMethodsDisplayContext.getSelectedScreenNavigationCategoryKey()), StringPool.BLANK, data);
%>

<liferay-util:include page="/navbar.jsp" servletContext="<%= commerceAdminServletContext %>">
	<liferay-util:param name="commerceAdminModuleKey" value="<%= commerceAdminModuleKey %>" />
</liferay-util:include>

<%@ include file="/breadcrumb.jspf" %>

<liferay-frontend:screen-navigation
	containerCssClass="col-md-10"
	key="<%= CommercePaymentScreenNavigationConstants.SCREEN_NAVIGATION_KEY_COMMERCE_PAYMENT_METHOD %>"
	modelBean="<%= commercePaymentMethod %>"
	navCssClass="col-md-2"
	portletURL="<%= currentURLObj %>"
/>