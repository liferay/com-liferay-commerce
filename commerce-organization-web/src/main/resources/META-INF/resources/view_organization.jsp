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
CommerceOrganizationDisplayContext commerceOrganizationDisplayContext = (CommerceOrganizationDisplayContext)request.getAttribute(WebKeys.PORTLET_DISPLAY_CONTEXT);

Organization organization = commerceOrganizationDisplayContext.getOrganization();
PortletURL portletURL = commerceOrganizationDisplayContext.getPortletURL();

portletURL.setParameter("mvcRenderCommandName", "viewCommerceOrganization");
%>

<portlet:renderURL var="editCommerceOrganizationURL">
	<portlet:param name="mvcRenderCommandName" value="editOrganization" />
	<portlet:param name="organizationId" value="<%= String.valueOf(organization.getOrganizationId()) %>" />
	<portlet:param name='<%= PortletQName.PUBLIC_RENDER_PARAMETER_NAMESPACE + "backURL" %>' value="<%= portletURL.toString() %>" />
</portlet:renderURL>

<div class="account-management">
	<div class="row">
		<div class="col-auto">
			<img alt="avatar" class="account-management__thumbnail img-fluid rounded-circle" src="<%= commerceOrganizationDisplayContext.getLogo(organization) %>" />
		</div>

		<div class="col d-flex flex-col justify-content-center">
			<span class="account-management__name">
				<%= HtmlUtil.escape(organization.getName()) %>
			</span>
		</div>

		<c:if test="<%= OrganizationPermissionUtil.contains(permissionChecker, organization.getOrganizationId(), ActionKeys.UPDATE) %>">
			<div class="align-items-center col-auto d-flex">
				<div class="account-management__action">
					<aui:button cssClass="commerce-button commerce-button--big commerce-button--outline" href="<%= editCommerceOrganizationURL %>" value='<%= LanguageUtil.get(request, "edit-organization") %>' />
				</div>
			</div>
		</c:if>
	</div>
</div>

<liferay-frontend:screen-navigation
	context="<%= organization %>"
	key="<%= CommerceOrganizationScreenNavigationConstants.SCREEN_NAVIGATION_KEY %>"
	portletURL="<%= portletURL %>"
/>