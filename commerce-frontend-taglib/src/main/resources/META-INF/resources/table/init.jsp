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

<%@ taglib uri="http://liferay.com/tld/aui" prefix="aui" %><%@
taglib uri="http://liferay.com/tld/theme" prefix="liferay-theme" %>

<%@ page import="com.liferay.commerce.frontend.Filter" %><%@
page import="com.liferay.commerce.frontend.taglib.internal.model.ClayPaginationEntry" %><%@
page import="com.liferay.petra.string.StringPool" %><%@
page import="com.liferay.portal.kernel.json.JSONFactoryUtil" %><%@
page import="com.liferay.portal.kernel.json.JSONSerializer" %><%@
page import="com.liferay.portal.kernel.util.PortalUtil" %>

<%@ page import="java.util.List" %><%@
page import="java.util.Map" %>

<%@ page import="javax.portlet.PortletURL" %>

<liferay-theme:defineObjects />

<%
Map<String, Object> clayTableContext = (Map<String, Object>)request.getAttribute("liferay-commerce:table:clayTableContext");
String dataProviderKey = (String)request.getAttribute("liferay-commerce:table:dataProviderKey");
String dataSetAPI = (String)request.getAttribute("liferay-commerce:table:dataSetAPI");
Filter filter = (Filter)request.getAttribute("liferay-commerce:table:filter");
int itemPerPage = (int)request.getAttribute("liferay-commerce:table:itemPerPage");
Object items = request.getAttribute("liferay-commerce:table:items");
String namespace = (String)request.getAttribute("liferay-commerce:table:namespace");
int pageNumber = (int)request.getAttribute("liferay-commerce:table:pageNumber");
List<ClayPaginationEntry> paginationEntries = (List<ClayPaginationEntry>)request.getAttribute("liferay-commerce:table:paginationEntries");
PortletURL portletURL = (PortletURL)request.getAttribute("liferay-commerce:table:portletURL");
boolean showPagination = (boolean)request.getAttribute("liferay-commerce:table:showPagination");
String spritemap = (String)request.getAttribute("liferay-commerce:table:spritemap");
String tableName = (String)request.getAttribute("liferay-commerce:table:tableName");
int totalItems = (int)request.getAttribute("liferay-commerce:table:totalItems");

JSONSerializer jsonSerializer = JSONFactoryUtil.createJSONSerializer();
String randomNamespace = PortalUtil.generateRandomKey(request, "taglib_step_tracker") + StringPool.UNDERLINE;

String containerId = randomNamespace + "table-id";
%>