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

<%@ taglib uri="http://java.sun.com/portlet_2_0" prefix="portlet" %>

<%@ taglib uri="http://liferay.com/tld/aui" prefix="aui" %><%@
taglib uri="http://liferay.com/tld/frontend" prefix="liferay-frontend" %><%@
taglib uri="http://liferay.com/tld/portlet" prefix="liferay-portlet" %><%@
taglib uri="http://liferay.com/tld/theme" prefix="liferay-theme" %><%@
taglib uri="http://liferay.com/tld/ui" prefix="liferay-ui" %><%@
taglib uri="http://liferay.com/tld/util" prefix="liferay-util" %>

<%@ page import="com.liferay.commerce.data.integration.constants.CommerceDataIntegrationConstants" %><%@
page import="com.liferay.commerce.data.integration.constants.CommerceDataIntegrationWebKeys" %><%@
page import="com.liferay.commerce.data.integration.exception.NoSuchDataIntegrationProcessException" %><%@
page import="com.liferay.commerce.data.integration.exception.NoSuchDataIntegrationProcessLogException" %><%@
page import="com.liferay.commerce.data.integration.model.CommerceDataIntegrationProcess" %><%@
page import="com.liferay.commerce.data.integration.model.CommerceDataIntegrationProcessLog" %><%@
page import="com.liferay.commerce.data.integration.process.type.ProcessType" %><%@
page import="com.liferay.commerce.data.integration.web.internal.display.context.CommerceDataIntegrationProcessDisplayContext" %><%@
page import="com.liferay.commerce.data.integration.web.internal.display.context.CommerceDataIntegrationProcessLogDisplayContext" %><%@
page import="com.liferay.commerce.data.integration.web.internal.security.permisison.resource.CommerceDataintegrationProcessPermission" %><%@
page import="com.liferay.petra.string.StringPool" %><%@
page import="com.liferay.portal.kernel.backgroundtask.BackgroundTaskConstants" %><%@
page import="com.liferay.portal.kernel.dao.search.ResultRow" %><%@
page import="com.liferay.portal.kernel.language.LanguageUtil" %><%@
page import="com.liferay.portal.kernel.security.permission.ActionKeys" %><%@
page import="com.liferay.portal.kernel.util.Constants" %><%@
page import="com.liferay.portal.kernel.util.HtmlUtil" %><%@
page import="com.liferay.portal.kernel.util.ParamUtil" %><%@
page import="com.liferay.portal.kernel.util.WebKeys" %>

<%@ page import="javax.portlet.PortletURL" %>

<liferay-frontend:defineObjects />

<liferay-theme:defineObjects />

<portlet:defineObjects />

<%
String redirect = ParamUtil.getString(request, "redirect");

String backURL = ParamUtil.getString(request, "backURL", redirect);
%>