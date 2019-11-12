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
String searchContainerId = ParamUtil.getString(request, "searchContainerId", "commerceDataIntegrationProcessLogs");

CommerceDataIntegrationProcessLogDisplayContext commerceDataIntegrationProcessLogDisplayContext = (CommerceDataIntegrationProcessLogDisplayContext)request.getAttribute(WebKeys.PORTLET_DISPLAY_CONTEXT);
%>

<liferay-frontend:management-bar
	includeCheckBox="<%= true %>"
	searchContainerId="<%= searchContainerId %>"
>
	<liferay-frontend:management-bar-buttons>
		<liferay-frontend:management-bar-display-buttons
			displayViews='<%= new String[] {"list"} %>'
			portletURL="<%= commerceDataIntegrationProcessLogDisplayContext.getPortletURL() %>"
			selectedDisplayStyle="list"
		/>
	</liferay-frontend:management-bar-buttons>

	<liferay-frontend:management-bar-filters>
		<liferay-frontend:management-bar-navigation
			navigationKeys='<%= new String[] {"all"} %>'
			portletURL="<%= commerceDataIntegrationProcessLogDisplayContext.getPortletURL() %>"
		/>
	</liferay-frontend:management-bar-filters>

	<c:if test="<%= CommerceDataintegrationProcessPermission.contains(permissionChecker, commerceDataIntegrationProcessLogDisplayContext.getCommerceDataIntegrationProcess(), ActionKeys.UPDATE) %>">
		<liferay-frontend:management-bar-action-buttons>
			<liferay-frontend:management-bar-button
				href='<%= "javascript:" + renderResponse.getNamespace() + "deleteCommerceDataIntegrationProcessLogs();" %>'
				icon="times"
				label="delete"
			/>
		</liferay-frontend:management-bar-action-buttons>
	</c:if>
</liferay-frontend:management-bar>

<aui:script>
	function <portlet:namespace />deleteCommerceDataIntegrationProcessLogs() {
		if (confirm('<liferay-ui:message key="are-you-sure-you-want-to-delete-the-selected-logs" />')) {
			var form = AUI.$(document.<portlet:namespace />fm);

			form.attr('method', 'post');

			form.fm('<%= Constants.CMD %>').val('<%= Constants.DELETE %>');
			form.fm('redirect').val('<%= currentURL %>');
			form.fm('deleteCDataIntegrationProcessLogIds').val(Liferay.Util.listCheckedExcept(form, '<portlet:namespace />allRowIds'));

			submitForm(form, '<portlet:actionURL name="editCommerceDataIntegrationProcessLog" />');
		}
	}
</aui:script>