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
CPInstanceDisplayContext cpInstanceDisplayContext = (CPInstanceDisplayContext)request.getAttribute(WebKeys.PORTLET_DISPLAY_CONTEXT);

CPDefinition cpDefinition = cpInstanceDisplayContext.getCPDefinition();

CPInstance cpInstance = cpInstanceDisplayContext.getCPInstance();

PortletURL productSkusURL = renderResponse.createRenderURL();

productSkusURL.setParameter("mvcRenderCommandName", "editProductDefinition");
productSkusURL.setParameter("cpDefinitionId", String.valueOf(cpDefinition.getCPDefinitionId()));
productSkusURL.setParameter("screenNavigationCategoryKey", cpInstanceDisplayContext.getScreenNavigationCategoryKey());
productSkusURL.setParameter("commerceCatalogId", commerceCatalogId);

String title = (cpInstance == null) ? LanguageUtil.get(request, "add-sku") : cpInstance.getSku();

Map<String, Object> data = new HashMap<>();

data.put("direction-right", StringPool.TRUE);

PortalUtil.addPortletBreadcrumbEntry(request, LanguageUtil.get(request, "catalogs"), viewCatalogURL.toString(), data);
PortalUtil.addPortletBreadcrumbEntry(request, commerceCatalog.getName(), editCatalogURL.toString(), data);
PortalUtil.addPortletBreadcrumbEntry(request, LanguageUtil.get(request, "products"), catalogURL, data);
PortalUtil.addPortletBreadcrumbEntry(request, cpDefinition.getName(languageId), String.valueOf(cpInstanceDisplayContext.getEditProductDefinitionURL()), data);
PortalUtil.addPortletBreadcrumbEntry(request, LanguageUtil.get(request, CPDefinitionScreenNavigationConstants.CATEGORY_KEY_SKUS), productSkusURL.toString(), data);
PortalUtil.addPortletBreadcrumbEntry(request, title, StringPool.BLANK, data);
%>

<clay:navigation-bar
	inverted="<%= true %>"
	navigationItems="<%= CPNavigationItemRegistryUtil.getNavigationItems(renderRequest) %>"
/>

<%@ include file="/breadcrumb.jspf" %>

<liferay-frontend:screen-navigation
	containerCssClass="col-md-10 mt-4"
	key="<%= CPInstanceScreenNavigationConstants.SCREEN_NAVIGATION_KEY_CP_INSTANCE_GENERAL %>"
	modelBean="<%= cpInstance %>"
	navCssClass="col-md-2"
	portletURL="<%= currentURLObj %>"
/>