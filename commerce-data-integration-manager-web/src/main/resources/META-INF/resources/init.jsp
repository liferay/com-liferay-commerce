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
taglib uri="http://liferay.com/tld/clay" prefix="clay" %><%@
taglib uri="http://liferay.com/tld/frontend" prefix="liferay-frontend" %><%@
taglib uri="http://liferay.com/tld/portlet" prefix="liferay-portlet" %><%@
taglib uri="http://liferay.com/tld/theme" prefix="liferay-theme" %><%@
taglib uri="http://liferay.com/tld/ui" prefix="liferay-ui" %><%@
taglib uri="http://liferay.com/tld/util" prefix="liferay-util" %>

<%@ page import="com.liferay.commerce.data.integration.manager.constants.Frequency" %><%@
page import="com.liferay.commerce.data.integration.manager.model.History" %><%@
page import="com.liferay.commerce.data.integration.manager.model.Process" %><%@
page import="com.liferay.commerce.data.integration.manager.model.ScheduledTask" %><%@
page import="com.liferay.commerce.data.integration.manager.web.internal.admin.ProcessListAdminModule" %><%@
page import="com.liferay.commerce.data.integration.manager.web.internal.admin.ScheduledTasksAdminModule" %><%@
page import="com.liferay.commerce.data.integration.manager.web.internal.admin.api.DataIntegrationAdminModule" %><%@
page import="com.liferay.commerce.data.integration.manager.web.internal.display.context.DataIntegrationProcessListDisplayContext" %><%@
page import="com.liferay.commerce.data.integration.manager.web.internal.display.context.HistoryDataIntegrationDisplayContext" %><%@
page import="com.liferay.commerce.data.integration.manager.web.internal.display.context.ScheduledTasksDataIntegrationDisplayContext" %><%@
page import="com.liferay.commerce.data.integration.manager.web.internal.portlet.constants.DataIntegrationWebPortletKeys" %><%@
page import="com.liferay.commerce.data.integration.manager.web.internal.util.DataIntegrationAdminModuleRegistry" %><%@
page import="com.liferay.frontend.taglib.clay.servlet.taglib.util.NavigationItem" %><%@
page import="com.liferay.petra.string.StringPool" %><%@
page import="com.liferay.portal.kernel.dao.search.ResultRow" %><%@
page import="com.liferay.portal.kernel.language.LanguageUtil" %><%@
page import="com.liferay.portal.kernel.util.Constants" %><%@
page import="com.liferay.portal.kernel.util.HtmlUtil" %><%@
page import="com.liferay.portal.kernel.util.ParamUtil" %><%@
page import="com.liferay.portal.kernel.util.WebKeys" %><%@
page import="com.liferay.portal.kernel.workflow.WorkflowConstants" %>

<%@ page import="java.util.ArrayList" %><%@
page import="java.util.Date" %><%@
page import="java.util.HashMap" %><%@
page import="java.util.List" %><%@
page import="java.util.Map" %><%@
page import="java.util.NavigableMap" %>

<%@ page import="javax.portlet.PortletURL" %>

<liferay-frontend:defineObjects />

<liferay-theme:defineObjects />

<portlet:defineObjects />