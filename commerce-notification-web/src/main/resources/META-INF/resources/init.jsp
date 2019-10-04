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
taglib uri="http://liferay.com/tld/expando" prefix="liferay-expando" %><%@
taglib uri="http://liferay.com/tld/frontend" prefix="liferay-frontend" %><%@
taglib uri="http://liferay.com/tld/portlet" prefix="liferay-portlet" %><%@
taglib uri="http://liferay.com/tld/theme" prefix="liferay-theme" %><%@
taglib uri="http://liferay.com/tld/ui" prefix="liferay-ui" %><%@
taglib uri="http://liferay.com/tld/util" prefix="liferay-util" %>

<%@ page import="com.liferay.commerce.account.model.CommerceAccountGroup" %><%@
page import="com.liferay.commerce.admin.constants.CommerceAdminPortletKeys" %><%@
page import="com.liferay.commerce.admin.constants.CommerceAdminWebKeys" %><%@
page import="com.liferay.commerce.notification.constants.CommerceNotificationActionKeys" %><%@
page import="com.liferay.commerce.notification.exception.CommerceNotificationTemplateFromException" %><%@
page import="com.liferay.commerce.notification.exception.CommerceNotificationTemplateNameException" %><%@
page import="com.liferay.commerce.notification.exception.CommerceNotificationTemplateTypeException" %><%@
page import="com.liferay.commerce.notification.model.CommerceNotificationQueueEntry" %><%@
page import="com.liferay.commerce.notification.model.CommerceNotificationTemplate" %><%@
page import="com.liferay.commerce.notification.model.CommerceNotificationTemplateCommerceAccountGroupRel" %><%@
page import="com.liferay.commerce.notification.type.CommerceNotificationType" %><%@
page import="com.liferay.commerce.notification.web.internal.admin.NotificationsCommerceAdminModule" %><%@
page import="com.liferay.commerce.notification.web.internal.display.context.CommerceNotificationQueueEntriesDisplayContext" %><%@
page import="com.liferay.commerce.notification.web.internal.display.context.CommerceNotificationTemplatesDisplayContext" %><%@
page import="com.liferay.commerce.notification.web.internal.servlet.taglib.ui.CommerceNotificationTemplateFormNavigatorConstants" %><%@
page import="com.liferay.frontend.taglib.clay.servlet.taglib.util.NavigationItem" %><%@
page import="com.liferay.petra.string.StringPool" %><%@
page import="com.liferay.portal.kernel.bean.BeanParamUtil" %><%@
page import="com.liferay.portal.kernel.dao.search.ResultRow" %><%@
page import="com.liferay.portal.kernel.language.LanguageUtil" %><%@
page import="com.liferay.portal.kernel.security.permission.ActionKeys" %><%@
page import="com.liferay.portal.kernel.util.Constants" %><%@
page import="com.liferay.portal.kernel.util.HtmlUtil" %><%@
page import="com.liferay.portal.kernel.util.ParamUtil" %><%@
page import="com.liferay.portal.kernel.util.PortalUtil" %><%@
page import="com.liferay.portal.kernel.util.PropsUtil" %><%@
page import="com.liferay.portal.kernel.util.UnicodeFormatter" %><%@
page import="com.liferay.portal.kernel.util.WebKeys" %>

<%@ page import="java.util.ArrayList" %><%@
page import="java.util.HashMap" %><%@
page import="java.util.List" %><%@
page import="java.util.Map" %>

<%@ page import="javax.portlet.PortletURL" %>

<liferay-frontend:defineObjects />

<liferay-theme:defineObjects />

<portlet:defineObjects />

<%
String commerceAdminModuleKey = NotificationsCommerceAdminModule.KEY;
%>