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
String catalogNavigationItem = ParamUtil.getString(request, "catalogNavigationItem", "view-all-users");

CommerceCatalogUsersDisplayContext commerceCatalogUsersDisplayContext = (CommerceCatalogUsersDisplayContext)request.getAttribute(WebKeys.PORTLET_DISPLAY_CONTEXT);

CommerceCatalog commerceCatalog = commerceCatalogUsersDisplayContext.getCommerceCatalog();

String title = LanguageUtil.get(request, "users");

Map<String, Object> data = new HashMap<>();

data.put("direction-right", StringPool.TRUE);

PortletURL editCatalogURL = renderResponse.createRenderURL();

editCatalogURL.setParameter("commerceCatalogId", String.valueOf(commerceCatalog.getCommerceCatalogId()));
editCatalogURL.setParameter("mvcRenderCommandName", "editCommerceCatalog");

PortalUtil.addPortletBreadcrumbEntry(request, LanguageUtil.get(request, "catalogs"), String.valueOf(renderResponse.createRenderURL()), data);
PortalUtil.addPortletBreadcrumbEntry(request, commerceCatalog.getName(), editCatalogURL.toString(), data);
PortalUtil.addPortletBreadcrumbEntry(request, title, StringPool.BLANK, data);
%>

<%@ include file="/navbar_definitions.jspf" %>

<liferay-util:include page="/toolbar_users.jsp" servletContext="<%= application %>">
	<liferay-util:param name="searchContainerId" value="commerceCatalogUsers" />
</liferay-util:include>

<div class="closed container-fluid-1280 sidenav-container sidenav-right" id="<portlet:namespace />infoPanelId">
	<div class="sidenav-content">
		<portlet:actionURL name="editCommerceCatalogUsers" var="editCommerceCatalogUsersActionURL" />

		<aui:form action="<%= editCommerceCatalogUsersActionURL %>" method="post" name="fm">
			<aui:input name="<%= Constants.CMD %>" type="hidden" value="<%= Constants.ADD %>" />
			<aui:input name="commerceCatalogId" type="hidden" value="<%= commerceCatalog.getCommerceCatalogId() %>" />
			<aui:input name="commerceCatalogUserIds" type="hidden" />
			<aui:input name="selectedRoleIds" type="hidden" />

			<div class="products-container" id="<portlet:namespace />commerceCatalogsContainer">
				<liferay-ui:search-container
					emptyResultsMessage="no-users-were-found"
					id="commerceCatalogUsers"
					searchContainer="<%= commerceCatalogUsersDisplayContext.getSearchContainer() %>"
				>
					<liferay-ui:search-container-row
						className="com.liferay.portal.kernel.model.User"
						cssClass="entry-display-style"
						keyProperty="userId"
						modelVar="user"
					>
						<liferay-ui:search-container-column-text
							cssClass="important table-cell-content"
							name="name"
							value="<%= HtmlUtil.escape(user.getFullName()) %>"
						/>

						<%
						List<Role> userRoles = commerceCatalogUsersDisplayContext.getUserRoles(user.getUserId());
						%>

						<liferay-ui:search-container-column-text
							cssClass="important table-cell-content"
							name="roles"
							value="<%= ListUtil.toString(userRoles, Role.TITLE_ACCESSOR, StringPool.COMMA_AND_SPACE) %>"
						/>

						<liferay-ui:search-container-column-jsp
							cssClass="entry-action-column"
							path="/user_action.jsp"
						/>
					</liferay-ui:search-container-row>

					<liferay-ui:search-iterator
						displayStyle="list"
						markupView="lexicon"
					/>
				</liferay-ui:search-container>
			</div>
		</aui:form>
	</div>
</div>

<%
List<User> users = commerceCatalogUsersDisplayContext.getSelectedUsers();

for (User catalogUser : users) {
%>

	<commerce-ui:user-roles-modal
		componentId='<%= "userRolesModal" + String.valueOf(catalogUser.getUserId()) %>'
		groupId="<%= commerceCatalogUsersDisplayContext.getCommerceCatalogGroupId() %>"
		subtype="<%= CommerceCatalogConstants.ROLE_SUBTYPE_CATALOG %>"
		userId="<%= catalogUser.getUserId() %>"
	/>

<%
}
%>