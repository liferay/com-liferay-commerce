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
DataIntegrationAdminModuleRegistry dataIntegrationAdminModuleRegistry =
	(DataIntegrationAdminModuleRegistry)request.getAttribute(DataIntegrationWebPortletKeys.DATA_INTEGRATION_ADMIN_MODULE_REGISTRY);

NavigableMap<String, DataIntegrationAdminModule> dataIntegrationAdminModules = dataIntegrationAdminModuleRegistry.getDataIntegrationAdminModules(scopeGroupId);

String selectedDataIntegrationAdminModuleKey = ParamUtil.getString(request, "dataIntegrationAdminModuleKey", dataIntegrationAdminModules.firstKey());

List<NavigationItem> navigationItems = new ArrayList<>();

for (Map.Entry<String, DataIntegrationAdminModule> entry : dataIntegrationAdminModules.entrySet()) {
	String dataIntegrationAdminModuleKey = entry.getKey();
	DataIntegrationAdminModule dataIntegrationAdminModule = entry.getValue();

	PortletURL dataIntegrationAdminModuleURL = renderResponse.createRenderURL();

	dataIntegrationAdminModuleURL.setParameter("dataIntegrationAdminModuleKey", dataIntegrationAdminModuleKey);

	NavigationItem navigationItem = new NavigationItem();

	navigationItem.setActive(dataIntegrationAdminModuleKey.equals(selectedDataIntegrationAdminModuleKey));
	navigationItem.setHref(dataIntegrationAdminModuleURL.toString());
	navigationItem.setLabel(dataIntegrationAdminModule.getLabel(locale));

	navigationItems.add(navigationItem);
}
%>

<clay:navigation-bar
	inverted="<%= true %>"
	navigationItems="<%= navigationItems %>"
/>