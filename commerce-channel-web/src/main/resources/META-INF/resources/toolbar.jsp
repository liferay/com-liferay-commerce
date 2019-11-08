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
String searchContainerId = ParamUtil.getString(request, "searchContainerId", "commerceChannels");

CommerceChannelDisplayContext commerceChannelDisplayContext = (CommerceChannelDisplayContext)request.getAttribute(WebKeys.PORTLET_DISPLAY_CONTEXT);
%>

<div class="definition-toolbar-managment-bar">
	<liferay-frontend:management-bar
		includeCheckBox="<%= true %>"
		searchContainerId="<%= searchContainerId %>"
	>
		<liferay-frontend:management-bar-buttons>
			<liferay-frontend:management-bar-display-buttons
				displayViews='<%= new String[] {"list"} %>'
				portletURL="<%= commerceChannelDisplayContext.getPortletURL() %>"
				selectedDisplayStyle="<%= commerceChannelDisplayContext.getDisplayStyle() %>"
			/>

			<portlet:renderURL var="addCommerceChannelURL">
				<portlet:param name="mvcRenderCommandName" value="editCommerceChannel" />
				<portlet:param name="redirect" value="<%= currentURL %>" />
			</portlet:renderURL>

			<c:if test="<%= commerceChannelDisplayContext.hasAddChannelPermission() %>">
				<liferay-frontend:add-menu
					inline="<%= true %>"
				>
					<liferay-frontend:add-menu-item
						title='<%= LanguageUtil.get(request, "add-channel") %>'
						url="<%= addCommerceChannelURL.toString() %>"
					/>
				</liferay-frontend:add-menu>
			</c:if>
		</liferay-frontend:management-bar-buttons>

		<liferay-frontend:management-bar-filters>
			<liferay-frontend:management-bar-sort
				orderByCol="<%= commerceChannelDisplayContext.getOrderByCol() %>"
				orderByType="<%= commerceChannelDisplayContext.getOrderByType() %>"
				orderColumns='<%= new String[] {"modified-date", "name"} %>'
				portletURL="<%= commerceChannelDisplayContext.getPortletURL() %>"
			/>

			<li>
				<liferay-commerce:search-input
					actionURL="<%= commerceChannelDisplayContext.getPortletURL() %>"
					formName="searchFm"
				/>
			</li>
		</liferay-frontend:management-bar-filters>

		<liferay-frontend:management-bar-action-buttons>
			<liferay-frontend:management-bar-button
				href='<%= "javascript:" + renderResponse.getNamespace() + "deleteCommerceChannels();" %>'
				icon="times"
				label="delete"
			/>
		</liferay-frontend:management-bar-action-buttons>
	</liferay-frontend:management-bar>
</div>

<aui:script>
	function <portlet:namespace />deleteCommerceChannels() {
		if (confirm('<liferay-ui:message key="are-you-sure-you-want-to-delete-the-selected-channels" />')) {
			var form = AUI.$(document.<portlet:namespace />fm);

			form.fm('<%= Constants.CMD %>').val('<%= Constants.DELETE %>');
			form.fm('commerceChannelIds').val(Liferay.Util.listCheckedExcept(form, '<portlet:namespace />allRowIds'));

			submitForm(form);
		}
	}
</aui:script>