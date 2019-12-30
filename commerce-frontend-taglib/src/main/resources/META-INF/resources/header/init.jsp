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

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%@ taglib uri="http://liferay.com/tld/aui" prefix="aui" %><%@
taglib uri="http://liferay.com/tld/clay" prefix="clay" %><%@
taglib uri="http://liferay.com/tld/theme" prefix="liferay-theme" %><%@
taglib uri="http://liferay.com/tld/ui" prefix="liferay-ui" %>

<%@ page import="com.liferay.commerce.frontend.model.HeaderActionModel" %><%@
page import="com.liferay.frontend.taglib.clay.servlet.taglib.util.DropdownItem" %><%@
page import="com.liferay.petra.string.StringPool" %><%@
page import="com.liferay.portal.kernel.model.WorkflowedModel" %><%@
page import="com.liferay.portal.kernel.util.HtmlUtil" %><%@
page import="com.liferay.portal.kernel.util.Validator" %>

<%@ page import="java.util.List" %>

<liferay-theme:defineObjects />

<%
boolean fullWidth = (boolean)request.getAttribute("liferay-commerce:header:fullWidth");
Object bean = request.getAttribute("liferay-commerce:header:bean");
String cssClasses = (String)request.getAttribute("liferay-commerce:header:cssClasses");
String wrapperCssClasses = (String)request.getAttribute("liferay-commerce:header:wrapperCssClasses");
Class<?> model = (Class<?>)request.getAttribute("liferay-commerce:header:model");
String title = (String)request.getAttribute("liferay-commerce:header:title");
String spritemap = (String)request.getAttribute("liferay-commerce:header:spritemap");
String version = (String)request.getAttribute("liferay-commerce:header:version");
String previewUrl = (String)request.getAttribute("liferay-commerce:header:previewUrl");
String thumbnailUrl = (String)request.getAttribute("liferay-commerce:header:thumbnailUrl");
List<HeaderActionModel> actions = (List<HeaderActionModel>)request.getAttribute("liferay-commerce:header:actions");
List<DropdownItem> dropdownItems = (List<DropdownItem>)request.getAttribute("liferay-commerce:header:dropdownItems");
String erc = (String)request.getAttribute("liferay-commerce:header:externalReferenceCode");
String ercEditUrl = (String)request.getAttribute("liferay-commerce:header:externalReferenceCodeEditUrl");
String assignerModalUrl = (String)request.getAttribute("liferay-commerce:header:assignerModalUrl");
%>