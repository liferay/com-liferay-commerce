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
CPOptionDisplayContext cpOptionDisplayContext = (CPOptionDisplayContext)request.getAttribute(WebKeys.PORTLET_DISPLAY_CONTEXT);

renderResponse.setTitle(LanguageUtil.get(request, "catalog"));
%>

<clay:navigation-bar
	inverted="<%= true %>"
	navigationItems="<%= CPNavigationItemRegistryUtil.getNavigationItems(renderRequest) %>"
/>

<%
NavigationItem navigationItem = new NavigationItem();

navigationItem.setActive(true);
navigationItem.setHref(currentURL);
navigationItem.setLabel(LanguageUtil.get(request, "option-templates"));
%>

<c:if test="<%= cpOptionDisplayContext.hasPermission(CPActionKeys.MANAGE_CATALOG) %>">
	<clay:navigation-bar
		navigationItems="<%= Collections.singletonList(navigationItem) %>"
	/>

	<div class="container-fluid-1280" id="<portlet:namespace />CPOptionsEditor">

	</div>

	<portlet:resourceURL id="cpOptions" var="cpOptionsURL">
	</portlet:resourceURL>

	<liferay-portlet:renderURL var="cpOptionURL" windowState="<%= LiferayWindowState.EXCLUSIVE.toString() %>">
		<portlet:param name="mvcRenderCommandName" value="cpOption" />
	</liferay-portlet:renderURL>

	<portlet:resourceURL id="cpOptionValues" var="cpOptionValuesURL">
	</portlet:resourceURL>

	<liferay-portlet:renderURL var="cpOptionValueURL" windowState="<%= LiferayWindowState.EXCLUSIVE.toString() %>">
		<portlet:param name="mvcRenderCommandName" value="editProductOptionValue" />
	</liferay-portlet:renderURL>

	<aui:script require="commerce-product-options-web@1.0.13/CPOptionsEditor.es as CPOptionsEditor">
		var cpOptionsEditor = new CPOptionsEditor.default(
			{
				hasEditPermission : <%= cpOptionDisplayContext.hasPermission(CPActionKeys.ADD_COMMERCE_PRODUCT_OPTION) %>,
				namespace : '<portlet:namespace />',
				optionURL : '<%= cpOptionURL %>',
				optionValueURL : '<%= cpOptionValueURL %>',
				optionValuesURL : '<%= cpOptionValuesURL %>',
				optionsURL : '<%= cpOptionsURL %>',
				pathThemeImages: '<%= themeDisplay.getPathThemeImages() %>',
				successMessage: '<liferay-ui:message key="your-request-completed-successfully" />'
			},
			'#<portlet:namespace />CPOptionsEditor'
		);
	</aui:script>
</c:if>