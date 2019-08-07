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
CPDefinitionDisplayLayoutDisplayContext cpDefinitionDisplayLayoutDisplayContext = (CPDefinitionDisplayLayoutDisplayContext)request.getAttribute(WebKeys.PORTLET_DISPLAY_CONTEXT);

PortletURL portletURL = cpDefinitionDisplayLayoutDisplayContext.getPortletURL();
%>

<div class="container-fluid-1280" id="<portlet:namespace />productLayoutsContainer">
	<liferay-frontend:management-bar
		includeCheckBox="<%= true %>"
		searchContainerId="cpDisplayLayouts"
	>
		<liferay-frontend:management-bar-buttons>
			<liferay-frontend:management-bar-display-buttons
				displayViews='<%= new String[] {"list"} %>'
				portletURL="<%= portletURL %>"
				selectedDisplayStyle="list"
			/>

			<liferay-portlet:renderURL var="addProductDefinitionDisplayLayoutURL">
				<portlet:param name="mvcRenderCommandName" value="editProductDisplayLayout" />
				<portlet:param name="redirect" value="<%= currentURL %>" />
				<portlet:param name="commerceAdminModuleKey" value="<%= commerceAdminModuleKey %>" />
			</liferay-portlet:renderURL>

			<liferay-frontend:add-menu
				inline="<%= true %>"
			>
				<liferay-frontend:add-menu-item
					title='<%= LanguageUtil.get(request, "add-display-layout") %>'
					url="<%= addProductDefinitionDisplayLayoutURL.toString() %>"
				/>
			</liferay-frontend:add-menu>
		</liferay-frontend:management-bar-buttons>

		<liferay-frontend:management-bar-filters>
			<liferay-frontend:management-bar-navigation
				navigationKeys='<%= new String[] {"all"} %>'
				portletURL="<%= portletURL %>"
			/>
		</liferay-frontend:management-bar-filters>

		<liferay-frontend:management-bar-action-buttons>
			<liferay-frontend:management-bar-button
				href='<%= "javascript:" + renderResponse.getNamespace() + "deleteCPDisplayLayouts();" %>'
				icon="times"
				label="delete"
			/>
		</liferay-frontend:management-bar-action-buttons>
	</liferay-frontend:management-bar>

	<aui:form action="<%= portletURL.toString() %>" method="post" name="fm">
		<aui:input name="<%= Constants.CMD %>" type="hidden" />
		<aui:input name="redirect" type="hidden" value="<%= portletURL.toString() %>" />
		<aui:input name="deleteCPDisplayLayoutIds" type="hidden" />

		<div class="product-layouts-container" id="<portlet:namespace />entriesContainer">
			<liferay-ui:search-container
				emptyResultsMessage="no-product-layouts-were-found"
				id="cpDisplayLayouts"
				searchContainer="<%= cpDefinitionDisplayLayoutDisplayContext.getSearchContainer() %>"
			>
				<liferay-ui:search-container-row
					className="com.liferay.commerce.product.model.CPDisplayLayout"
					cssClass="entry-display-style"
					keyProperty="CPDisplayLayoutId"
					modelVar="cpDisplayLayout"
				>

					<%
					CPDefinition cpDefinition = cpDisplayLayout.fetchCPDefinition();
					Layout curLayout = cpDisplayLayout.fetchLayout();
					%>

					<liferay-ui:search-container-column-text
						cssClass="important table-cell-content"
						name="product-name"
						value="<%= (cpDefinition == null) ? StringPool.BLANK : HtmlUtil.escape(cpDefinition.getName(languageId)) %>"
					/>

					<liferay-ui:search-container-column-text
						cssClass="table-cell-content"
						name="layout"
						value="<%= (curLayout == null) ? StringPool.BLANK : HtmlUtil.escape(curLayout.getName(languageId)) %>"
					/>

					<liferay-ui:search-container-column-jsp
						cssClass="entry-action-column"
						path="/display_layout/display_layout_action.jsp"
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

<aui:script>
	function <portlet:namespace />deleteCPDisplayLayouts() {
		if (confirm('<liferay-ui:message key="are-you-sure-you-want-to-delete-the-selected-diplay-layouts" />')) {
			var form = AUI.$(document.<portlet:namespace />fm);

			form.attr('method', 'post');
			form.fm('<%= Constants.CMD %>').val('<%= Constants.DELETE %>');
			form.fm('deleteCPDisplayLayoutIds').val(Liferay.Util.listCheckedExcept(form, '<portlet:namespace />allRowIds'));

			submitForm(form, '<portlet:actionURL name="editProductDisplayLayout" />');
		}
	}
</aui:script>