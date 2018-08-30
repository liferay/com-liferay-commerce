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
CPCatalogRuleDisplayContext cpCatalogRuleDisplayContext = (CPCatalogRuleDisplayContext)request.getAttribute(WebKeys.PORTLET_DISPLAY_CONTEXT);

boolean orSearch = GetterUtil.getBoolean(cpCatalogRuleDisplayContext.getCPRuleTypeSettingsProperty("orSearch"));

orSearch = ParamUtil.getBoolean(request, "orSearch", orSearch);

String assetCategoryIds = cpCatalogRuleDisplayContext.getAssetCategoryIds();
%>

<aui:input checked="<%= orSearch %>" label="search-type" labelOff="match-all" labelOn="match-any" name="orSearch" type="toggle-switch" />

<liferay-ui:asset-categories-error />

<liferay-asset:asset-categories-selector
	categoryIds="<%= assetCategoryIds %>"
	hiddenInput="assetCategoryIds"
/>