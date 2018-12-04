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
DataIntegrationAdminModule selectedDataIntegrationAdminModule = dataIntegrationAdminModules.get(selectedDataIntegrationAdminModuleKey);
%>

<liferay-util:include page="/navbar.jsp" servletContext="<%= application %>" />

<%
selectedDataIntegrationAdminModule.render(renderRequest, renderResponse);
%>