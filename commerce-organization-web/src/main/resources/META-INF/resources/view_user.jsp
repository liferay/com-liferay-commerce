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

User selectedUser = commerceOrganizationDisplayContext.getSelectedUser();
PortletURL portletURL = commerceOrganizationDisplayContext.getPortletURL();

portletURL.setParameter("mvcRenderCommandName", "viewCommerceOrganizationUser");
%>

<div class="account-management">
	<section class="panel panel-secondary">
		<div class="panel-body">
			<div class="row">
				<div class="col-auto">
					<img alt="avatar" class="account-management__thumbnail img-fluid rounded-circle" src="<%= selectedUser.getPortraitURL(themeDisplay) %>" />
				</div>

				<div class="col d-flex flex-col justify-content-center">
					<span class="account-management__name">
						<%= selectedUser.getFullName() %>
					</span>
					<span class="account-management__email">
						<%= selectedUser.getEmailAddress() %>
					</span>
				</div>
			</div>
		</div>
	</section>
</div>