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

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ taglib uri="http://java.sun.com/portlet_2_0" prefix="portlet" %>

<%@ taglib uri="http://liferay.com/tld/theme" prefix="liferay-theme" %><%@
taglib uri="http://liferay.com/tld/ui" prefix="liferay-ui" %>
<%@taglib uri="http://liferay.com/tld/clay" prefix="clay" %>
<%@ taglib prefix="aui" uri="http://liferay.com/tld/aui" %>

<%@ page import="com.liferay.portal.kernel.util.PortalUtil" %>
<%@ page import="com.liferay.petra.string.StringPool" %>

<%@ page import="java.util.List" %>
<%@ page import="com.liferay.portal.kernel.model.WorkflowedModel" %>
<%@ page import="com.liferay.frontend.taglib.clay.servlet.taglib.util.DropdownItem" %>
<%@ page import="com.liferay.commerce.frontend.model.HeaderButtonModel" %>

<%@ page import="com.liferay.portal.kernel.util.Validator" %>

<%@ page import="java.math.BigDecimal" %>

<%@ page import="java.text.DecimalFormat" %>

<liferay-theme:defineObjects />

<portlet:defineObjects />

<%
    String spritemap = (String)request.getAttribute("liferay-commerce:panel:spritemap");
    String title = (String)request.getAttribute("liferay-commerce:panel:title");
    String headerActionUrl = (String)request.getAttribute("liferay-commerce:panel:headerActionUrl");
    String headerActionLabel = (String)request.getAttribute("liferay-commerce:panel:headerActionLabel");
    String headerActionIcon = (String)request.getAttribute("liferay-commerce:panel:headerActionIcon");
    String headerActionId = (String)request.getAttribute("liferay-commerce:panel:headerActionId");
    String showMoreRefId = (String)request.getAttribute("liferay-commerce:panel:showMoreRefId");
    String showMoreId = (String)request.getAttribute("liferay-commerce:panel:showMoreId");
    String showMoreUrl = (String)request.getAttribute("liferay-commerce:panel:showMoreUrl");

    String elementClasses = (String)request.getAttribute("liferay-commerce:panel:elementClasses");
    String randomNamespace = PortalUtil.generateRandomKey(request, "commerce_panel") + StringPool.UNDERLINE;

    String headerActionLinkId = Validator.isNotNull(headerActionId) ? headerActionId : (randomNamespace + "header-button");
    String showMoreButtonId = Validator.isNotNull(showMoreId) ? showMoreId : (randomNamespace + "show-more-button");
    String showMoreButtonWrapperId = randomNamespace + "show-more-button-wrapper";

%>