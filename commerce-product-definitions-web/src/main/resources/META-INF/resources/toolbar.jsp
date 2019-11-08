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
String searchContainerId = ParamUtil.getString(request, "searchContainerId", "cpDefinitions");

CPDefinitionsDisplayContext cpDefinitionsDisplayContext = (CPDefinitionsDisplayContext)request.getAttribute(WebKeys.PORTLET_DISPLAY_CONTEXT);

NPMResolver npmResolver = (NPMResolver)request.getAttribute("NPMResolver");

String definitionToolbarFilterRequire = npmResolver.resolveModuleName("commerce-product-definitions-web/DefinitionToolbarFilter.es") + " as DefinitionToolbarFilter";
%>

<div class="definition-toolbar-managment-bar">
	<liferay-frontend:management-bar
		includeCheckBox="<%= true %>"
		searchContainerId="<%= searchContainerId %>"
	>
		<liferay-frontend:management-bar-buttons>
			<c:if test="<%= cpDefinitionsDisplayContext.isShowInfoPanel() %>">
				<liferay-frontend:management-bar-sidenav-toggler-button
					icon="info-circle"
					label="info"
				/>
			</c:if>

			<liferay-frontend:management-bar-display-buttons
				displayViews='<%= new String[] {"icon", "descriptive", "list"} %>'
				portletURL="<%= cpDefinitionsDisplayContext.getPortletURL() %>"
				selectedDisplayStyle="<%= cpDefinitionsDisplayContext.getDisplayStyle() %>"
			/>

			<liferay-frontend:add-menu
				inline="<%= true %>"
			>

				<%
				for (CPType curCPType : cpDefinitionsDisplayContext.getCPTypes()) {
				%>

					<liferay-portlet:renderURL var="addProductDefinitionURL">
						<portlet:param name="<%= Constants.CMD %>" value="<%= Constants.ADD %>" />
						<portlet:param name="mvcRenderCommandName" value="editProductDefinition" />
						<portlet:param name="backURL" value="<%= PortalUtil.getCurrentCompleteURL(request) %>" />
						<portlet:param name="productTypeName" value="<%= curCPType.getName() %>" />
					</liferay-portlet:renderURL>

					<liferay-frontend:add-menu-item
						title="<%= curCPType.getLabel(locale) %>"
						url="<%= addProductDefinitionURL.toString() %>"
					/>

				<%
				}
				%>

			</liferay-frontend:add-menu>
		</liferay-frontend:management-bar-buttons>

		<liferay-frontend:management-bar-filters>
			<liferay-frontend:management-bar-sort
				orderByCol="<%= cpDefinitionsDisplayContext.getOrderByCol() %>"
				orderByType="<%= cpDefinitionsDisplayContext.getOrderByType() %>"
				orderColumns='<%= new String[] {"modified-date", "display-date", "name"} %>'
				portletURL="<%= cpDefinitionsDisplayContext.getPortletURL() %>"
			/>

			<li>
				<liferay-commerce:search-input
					actionURL="<%= cpDefinitionsDisplayContext.getPortletURL() %>"
					formName="searchFm"
				/>
			</li>
		</liferay-frontend:management-bar-filters>

		<liferay-frontend:management-bar-action-buttons>
			<liferay-frontend:management-bar-sidenav-toggler-button
				icon="info-circle"
				label="info"
			/>

			<liferay-frontend:management-bar-button
				href='<%= "javascript:" + renderResponse.getNamespace() + "deleteCPDefinitions();" %>'
				icon="times"
				label="delete"
			/>
		</liferay-frontend:management-bar-action-buttons>
	</liferay-frontend:management-bar>
</div>

<div id="<portlet:namespace />DefinitionToolbarFilter">
</div>

<liferay-portlet:resourceURL id="cpDefinitionsFacets" var="cpDefinitionsFacetsURL" />

<aui:script require="<%= definitionToolbarFilterRequire %>">
	var definitionToolbarFilter = new DefinitionToolbarFilter.default(
		{
			categorySelectorURL: '<%= cpDefinitionsDisplayContext.getCategorySelectorURL(renderResponse.getNamespace() + "selectCategory") %>',
			cpDefinitionsFacetsURL : '<%= cpDefinitionsFacetsURL.toString() %>',
			groupIds: '<%= themeDisplay.getScopeGroupId() %>',
			namespace : '<portlet:namespace />',
			pathThemeImages: '<%= themeDisplay.getPathThemeImages() %>',
			portletURL: '<%= cpDefinitionsDisplayContext.getPortletURL() %>',
			vocabularyIds: '<%= cpDefinitionsDisplayContext.getVocabularyIds() %>'
		},
		'#<portlet:namespace />DefinitionToolbarFilter'
	);
</aui:script>

<aui:script>
	function <portlet:namespace />deleteCPDefinitions() {
		if (confirm('<liferay-ui:message key="are-you-sure-you-want-to-delete-the-selected-products" />')) {
			var form = AUI.$(document.<portlet:namespace />fm);

			form.attr('method', 'post');
			form.fm('<%= Constants.CMD %>').val('<%= Constants.DELETE %>');
			form.fm('deleteCPDefinitionIds').val(Liferay.Util.listCheckedExcept(form, '<portlet:namespace />allRowIds'));

			submitForm(form, '<portlet:actionURL name="editProductDefinition" />');
		}
	}
</aui:script>