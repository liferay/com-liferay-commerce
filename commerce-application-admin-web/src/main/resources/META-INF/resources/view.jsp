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
CommerceApplicationAdminDisplayContext commerceApplicationAdminDisplayContext = (CommerceApplicationAdminDisplayContext)request.getAttribute(WebKeys.PORTLET_DISPLAY_CONTEXT);
%>

<clay:navigation-bar
	inverted="<%= true %>"
	navigationItems="<%= CPNavigationItemRegistryUtil.getNavigationItems(renderRequest) %>"
/>

<liferay-frontend:management-bar
	includeCheckBox="<%= true %>"
	searchContainerId="commerceApplicationBrands"
>
	<liferay-frontend:management-bar-filters>
		<liferay-frontend:management-bar-navigation
			navigationKeys='<%= new String[] {"all"} %>'
			portletURL="<%= commerceApplicationAdminDisplayContext.getPortletURL() %>"
		/>

		<liferay-frontend:management-bar-sort
			orderByCol="<%= commerceApplicationAdminDisplayContext.getOrderByCol() %>"
			orderByType="<%= commerceApplicationAdminDisplayContext.getOrderByType() %>"
			orderColumns='<%= new String[] {"name"} %>'
			portletURL="<%= commerceApplicationAdminDisplayContext.getPortletURL() %>"
		/>
	</liferay-frontend:management-bar-filters>

	<liferay-frontend:management-bar-buttons>
		<liferay-frontend:management-bar-display-buttons
			displayViews='<%= new String[] {"list"} %>'
			portletURL="<%= commerceApplicationAdminDisplayContext.getPortletURL() %>"
			selectedDisplayStyle="list"
		/>

		<c:if test="<%= commerceApplicationAdminDisplayContext.hasPermissions(CommerceApplicationActionKeys.ADD_COMMERCE_BRAND) %>">
			<liferay-frontend:add-menu
				inline="<%= true %>"
			>
				<liferay-frontend:add-menu-item
					id="addApplicationBrandButton"
					title='<%= LanguageUtil.get(request, "add-brand") %>'
					url="javascript:;"
				/>
			</liferay-frontend:add-menu>
		</c:if>
	</liferay-frontend:management-bar-buttons>

	<liferay-frontend:management-bar-action-buttons>
		<liferay-frontend:management-bar-button
			href='<%= "javascript:" + renderResponse.getNamespace() + "deleteCommerceApplicationBrands();" %>'
			icon="times"
			label="delete"
		/>
	</liferay-frontend:management-bar-action-buttons>
</liferay-frontend:management-bar>

<portlet:actionURL name="editCommerceApplicationBrand" var="editCommerceApplicationBrandActionURL" />

<div class="container-fluid-1280" id="<portlet:namespace />commerceApplicationBrandContainer">
	<aui:form action="<%= editCommerceApplicationBrandActionURL %>" method="post" name="fm">
		<aui:input name="<%= Constants.CMD %>" type="hidden" value="<%= Constants.DELETE %>" />
		<aui:input name="redirect" type="hidden" value="<%= currentURL %>" />
		<aui:input name="deleteCommerceApplicationBrandIds" type="hidden" />

		<liferay-ui:search-container
			id="commerceApplicationBrands"
			searchContainer="<%= commerceApplicationAdminDisplayContext.getCommerceApplicationBrandSearchContainer() %>"
		>
			<liferay-ui:search-container-row
				className="com.liferay.commerce.application.model.CommerceApplicationBrand"
				cssClass="entry-display-style"
				keyProperty="commerceApplicationBrandId"
				modelVar="commerceApplicationBrand"
			>

				<%
				PortletURL rowURL = renderResponse.createRenderURL();

				rowURL.setParameter("mvcRenderCommandName", "editCommerceApplicationBrand");
				rowURL.setParameter("redirect", currentURL);
				rowURL.setParameter("commerceApplicationBrandId", String.valueOf(commerceApplicationBrand.getCommerceApplicationBrandId()));
				%>

				<liferay-ui:search-container-column-text
					cssClass="important table-cell-content"
					href="<%= rowURL %>"
					property="name"
				/>

				<liferay-ui:search-container-column-jsp
					cssClass="entry-action-column"
					path="/application_brand_action.jsp"
				/>
			</liferay-ui:search-container-row>

			<liferay-ui:search-iterator
				displayStyle="list"
				markupView="lexicon"
			/>
		</liferay-ui:search-container>
	</aui:form>
</div>

<aui:script>
	function <portlet:namespace />deleteCommerceApplicationBrands() {
		if (confirm('<liferay-ui:message key="are-you-sure-you-want-to-delete-the-selected-brands" />')) {
			var form = AUI.$(document.<portlet:namespace />fm);

			form.fm('deleteCommerceApplicationBrandIds').val(Liferay.Util.listCheckedExcept(form, '<portlet:namespace />allRowIds'));

			submitForm(form);
		}
	}
</aui:script>

<c:if test="<%= commerceApplicationAdminDisplayContext.hasPermissions(CommerceApplicationActionKeys.ADD_COMMERCE_BRAND) %>">
	<portlet:actionURL name="editCommerceApplicationBrand" var="editCommerceApplicationBrandActionURL">
		<portlet:param name="<%= Constants.CMD %>" value="<%= Constants.ADD %>" />
		<portlet:param name="redirect" value="<%= currentURL %>" />
	</portlet:actionURL>

	<aui:script require="metal-dom/src/all/dom as dom,frontend-js-web/liferay/modal/commands/OpenSimpleInputModal.es as modalCommands">
		var handleAddApplicationBrandButtonClick = dom.delegate(
			document.body,
			'click',
			'#<portlet:namespace />addApplicationBrandButton',
			function(event) {
				modalCommands.openSimpleInputModal(
					{
						dialogTitle: '<liferay-ui:message key="add-brand" />',
						formSubmitURL: '<%= editCommerceApplicationBrandActionURL %>',
						mainFieldLabel: '<liferay-ui:message key="name" />',
						mainFieldName: 'name',
						mainFieldPlaceholder: '<liferay-ui:message key="name" />',
						namespace: '<portlet:namespace />',
						spritemap: '<%= themeDisplay.getPathThemeImages() %>/lexicon/icons.svg'
					}
				);
			}
		);

		function handleDestroyPortlet () {
			handleAddApplicationBrandButtonClick.removeListener();

			Liferay.detach('destroyPortlet', handleDestroyPortlet);
		}

		Liferay.on('destroyPortlet', handleDestroyPortlet);
	</aui:script>
</c:if>