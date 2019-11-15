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
taglib uri="http://liferay.com/tld/commerce-ui" prefix="commerce-ui" %><%@
taglib uri="http://liferay.com/tld/frontend" prefix="liferay-frontend" %><%@
taglib uri="http://liferay.com/tld/theme" prefix="liferay-theme" %><%@
taglib uri="http://liferay.com/tld/ui" prefix="liferay-ui" %>

<%@ page import="com.liferay.commerce.organization.web.internal.constants.CommerceOrganizationConstants" %><%@
page import="com.liferay.commerce.organization.web.internal.display.context.CommerceOrganizationDisplayContext" %><%@
page import="com.liferay.commerce.organization.web.internal.frontend.CommerceOrganizationAccountClayTable" %><%@
page import="com.liferay.commerce.organization.web.internal.frontend.CommerceOrganizationClayTable" %><%@
page import="com.liferay.commerce.organization.web.internal.frontend.CommerceOrganizationUserClayTable" %><%@
page import="com.liferay.commerce.organization.web.internal.js.loader.modules.extender.npm.NPMResolverProvider" %><%@
page import="com.liferay.commerce.organization.web.internal.servlet.taglib.ui.CommerceOrganizationScreenNavigationConstants" %><%@
page import="com.liferay.frontend.js.loader.modules.extender.npm.NPMResolver" %><%@
page import="com.liferay.portal.kernel.exception.NoSuchOrganizationException" %><%@
page import="com.liferay.portal.kernel.exception.NoSuchUserException" %><%@
page import="com.liferay.portal.kernel.language.LanguageUtil" %><%@
page import="com.liferay.portal.kernel.model.Organization" %><%@
page import="com.liferay.portal.kernel.model.User" %><%@
page import="com.liferay.portal.kernel.portlet.PortletQName" %><%@
page import="com.liferay.portal.kernel.security.permission.ActionKeys" %><%@
page import="com.liferay.portal.kernel.service.permission.OrganizationPermissionUtil" %><%@
page import="com.liferay.portal.kernel.util.Constants" %><%@
page import="com.liferay.portal.kernel.util.HtmlUtil" %><%@
page import="com.liferay.portal.kernel.util.ParamUtil" %><%@
page import="com.liferay.portal.kernel.util.PortalUtil" %><%@
page import="com.liferay.portal.kernel.util.Validator" %><%@
page import="com.liferay.portal.kernel.util.WebKeys" %><%@
page import="com.liferay.portal.kernel.webserver.WebServerServletTokenUtil" %><%@
page import="com.liferay.users.admin.configuration.UserFileUploadsConfiguration" %>

<%@ page import="java.util.HashMap" %><%@
page import="java.util.Map" %>

<%@ page import="javax.portlet.PortletURL" %>

<liferay-frontend:defineObjects />

<liferay-theme:defineObjects />

<portlet:defineObjects />

<%
	String redirect = ParamUtil.getString(request, "redirect");

	String backURL = ParamUtil.getString(request, PortletQName.PUBLIC_RENDER_PARAMETER_NAMESPACE + "backURL", redirect);
%>