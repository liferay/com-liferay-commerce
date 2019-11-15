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
CommerceBOMAdminDisplayContext commerceBOMAdminDisplayContext = (CommerceBOMAdminDisplayContext)request.getAttribute(WebKeys.PORTLET_DISPLAY_CONTEXT);
%>

<clay:navigation-bar
	inverted="<%= true %>"
	navigationItems="<%= CPNavigationItemRegistryUtil.getNavigationItems(renderRequest) %>"
/>

<liferay-frontend:management-bar
	searchContainerId="commerceBOMAdminSearchContainer"
>
	<liferay-frontend:management-bar-filters>
		<liferay-frontend:management-bar-navigation
			navigationKeys='<%= new String[] {"all"} %>'
			portletURL="<%= commerceBOMAdminDisplayContext.getPortletURL() %>"
		/>

		<liferay-frontend:management-bar-sort
			orderByCol="<%= commerceBOMAdminDisplayContext.getOrderByCol() %>"
			orderByType="<%= commerceBOMAdminDisplayContext.getOrderByType() %>"
			orderColumns='<%= new String[] {"name"} %>'
			portletURL="<%= commerceBOMAdminDisplayContext.getPortletURL() %>"
		/>

		<li>
			<aui:form action="<%= String.valueOf(commerceBOMAdminDisplayContext.getPortletURL()) %>" name="searchFm">
				<liferay-ui:input-search
					markupView="lexicon"
				/>
			</aui:form>
		</li>
	</liferay-frontend:management-bar-filters>

	<liferay-frontend:management-bar-buttons>
		<liferay-frontend:management-bar-display-buttons
			displayViews='<%= new String[] {"list"} %>'
			portletURL="<%= commerceBOMAdminDisplayContext.getPortletURL() %>"
			selectedDisplayStyle="list"
		/>

		<liferay-frontend:add-menu
			inline="<%= true %>"
		>
			<c:if test="<%= commerceBOMAdminDisplayContext.hasPermissions(CommerceBOMActionKeys.ADD_COMMERCE_BOM_FOLDER) %>">
				<liferay-frontend:add-menu-item
					id="addBOMFolderButton"
					title='<%= LanguageUtil.get(request, "add-folder") %>'
					url="javascript:;"
				/>
			</c:if>

			<c:if test="<%= commerceBOMAdminDisplayContext.hasPermissions(CommerceBOMActionKeys.ADD_COMMERCE_BOM_DEFINITION) %>">
				<portlet:renderURL var="addCommerceBOMDefinitionURL">
					<portlet:param name="mvcRenderCommandName" value="editCommerceBOMDefinition" />
					<portlet:param name="redirect" value="<%= currentURL %>" />
					<portlet:param name="commerceBOMFolderId" value="<%= String.valueOf(commerceBOMAdminDisplayContext.getCommerceBOMFolderId()) %>" />
				</portlet:renderURL>

				<liferay-frontend:add-menu-item
					title='<%= LanguageUtil.get(request, "add-definition") %>'
					url="<%= addCommerceBOMDefinitionURL.toString() %>"
				/>
			</c:if>
		</liferay-frontend:add-menu>
	</liferay-frontend:management-bar-buttons>
</liferay-frontend:management-bar>

<portlet:actionURL name="editCommerceBOMDefintion" var="editCommerceBOMDefintionActionURL" />

<div class="container-fluid-1280" id="<portlet:namespace />commerceBOMDefinitionContainer">

	<%
	commerceBOMAdminDisplayContext.addPortletBreadcrumbEntries();
	%>

	<liferay-ui:breadcrumb
		showCurrentGroup="<%= false %>"
		showGuestGroup="<%= false %>"
		showLayout="<%= false %>"
		showPortletBreadcrumb="<%= true %>"
	/>

	<liferay-ui:search-container
		id="commerceBOMAdminSearchContainer"
		searchContainer="<%= commerceBOMAdminDisplayContext.getSearchContainer() %>"
	>
		<liferay-ui:search-container-row
			className="Object"
			cssClass="entry-display-style"
			modelVar="object"
		>

			<%
			CommerceBOMDefinition commerceBOMDefinition = null;
			CommerceBOMFolder commerceBOMFolder = null;

			Object result = row.getObject();

			if (result instanceof CommerceBOMDefinition) {
				commerceBOMDefinition = (CommerceBOMDefinition)result;
			}
			else {
				commerceBOMFolder = (CommerceBOMFolder)result;
			}
			%>

			<c:choose>
				<c:when test="<%= commerceBOMDefinition != null %>">

					<%
					PortletURL rowURL = renderResponse.createRenderURL();

					rowURL.setParameter("mvcRenderCommandName", "editCommerceBOMDefinition");
					rowURL.setParameter("redirect", currentURL);
					rowURL.setParameter("commerceBOMDefinitionId", String.valueOf(commerceBOMDefinition.getCommerceBOMDefinitionId()));
					rowURL.setParameter("commerceBOMFolderId", String.valueOf(commerceBOMAdminDisplayContext.getCommerceBOMFolderId()));
					%>

					<liferay-ui:search-container-column-text
						cssClass="important table-cell-content"
						href="<%= rowURL %>"
						name="name"
						value="<%= commerceBOMDefinition.getName() %>"
					/>

					<liferay-ui:search-container-column-jsp
						cssClass="entry-action-column"
						path="/bom_definition_action.jsp"
					/>
				</c:when>
				<c:when test="<%= commerceBOMFolder != null %>">

					<%
					PortletURL rowURL = commerceBOMAdminDisplayContext.getPortletURL();

					rowURL.setParameter("commerceBOMFolderId", String.valueOf(commerceBOMFolder.getCommerceBOMFolderId()));
					%>

					<liferay-ui:search-container-column-text
						cssClass="important table-cell-content"
						href="<%= rowURL %>"
						name="name"
						value="<%= commerceBOMFolder.getName() %>"
					/>

					<liferay-ui:search-container-column-jsp
						cssClass="entry-action-column"
						path="/bom_folder_action.jsp"
					/>
				</c:when>
			</c:choose>
		</liferay-ui:search-container-row>

		<liferay-ui:search-iterator
			displayStyle="list"
			markupView="lexicon"
			resultRowSplitter="<%= new CommerceBOMAdminResultRowSplitter() %>"
		/>
	</liferay-ui:search-container>
</div>

<c:if test="<%= commerceBOMAdminDisplayContext.hasPermissions(CommerceBOMActionKeys.ADD_COMMERCE_BOM_FOLDER) %>">
	<portlet:actionURL name="editCommerceBOMFolder" var="editCommerceBOMFolderActionURL">
		<portlet:param name="<%= Constants.CMD %>" value="<%= Constants.ADD %>" />
		<portlet:param name="redirect" value="<%= currentURL %>" />
		<portlet:param name="parentCommerceBOMFolderId" value="<%= String.valueOf(commerceBOMAdminDisplayContext.getCommerceBOMFolderId()) %>" />
	</portlet:actionURL>

	<aui:script require="metal-dom/src/all/dom as dom,frontend-js-web/liferay/modal/commands/OpenSimpleInputModal.es as modalCommands">
		function handleAddBOMFolderButtonClick(event) {
			event.preventDefault();

			modalCommands.openSimpleInputModal(
				{
					dialogTitle: '<liferay-ui:message key="add-folder" />',
					formSubmitURL: '<%= editCommerceBOMFolderActionURL %>',
					mainFieldLabel: '<liferay-ui:message key="name" />',
					mainFieldName: 'name',
					mainFieldPlaceholder: '<liferay-ui:message key="name" />',
					namespace: '<portlet:namespace />',
					spritemap: '<%= themeDisplay.getPathThemeImages() %>/lexicon/icons.svg'
				}
			);
		}

		function handleDestroyPortlet () {
			addBOMFolderButton.removeEventListener('click', handleAddBOMFolderButtonClick);

			Liferay.detach('destroyPortlet', handleDestroyPortlet);
		}

		var addBOMFolderButton = document.getElementById('<portlet:namespace />addBOMFolderButton');

		addBOMFolderButton.addEventListener('click', handleAddBOMFolderButtonClick);

		Liferay.on('destroyPortlet', handleDestroyPortlet);
	</aui:script>
</c:if>