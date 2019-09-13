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
CommercePaymentMethodGroupRelsDisplayContext commercePaymentMethodGroupRelsDisplayContext = (CommercePaymentMethodGroupRelsDisplayContext)request.getAttribute(WebKeys.PORTLET_DISPLAY_CONTEXT);

ResultRow row = (ResultRow)request.getAttribute(WebKeys.SEARCH_CONTAINER_RESULT_ROW);

CommercePaymentMethodGroupRel commercePaymentMethodGroupRel = (CommercePaymentMethodGroupRel)row.getObject();
%>

<liferay-ui:icon-menu
	direction="left-side"
	icon="<%= StringPool.BLANK %>"
	markupView="lexicon"
	message="<%= StringPool.BLANK %>"
	showWhenSingleIcon="<%= true %>"
>
	<c:if test="<%= commercePaymentMethodGroupRelsDisplayContext.hasManageCommercePaymentMethodGroupRelsPermission() %>">
		<portlet:actionURL name="editCommercePaymentMethodGroupRel" var="editURL">
			<portlet:param name="<%= Constants.CMD %>" value="<%= Constants.EDIT %>" />
			<portlet:param name="redirect" value="<%= currentURL %>" />
			<portlet:param name="commercePaymentMethodGroupRelId" value="<%= String.valueOf(commercePaymentMethodGroupRel.getCommercePaymentMethodGroupRelId()) %>" />
			<portlet:param name="engineKey" value="<%= commercePaymentMethodGroupRel.getEngineKey() %>" />
		</portlet:actionURL>

		<liferay-ui:icon
			message="edit"
			url="<%= editURL %>"
		/>

		<portlet:actionURL name="editCommercePaymentMethodGroupRel" var="setActiveURL">
			<portlet:param name="<%= Constants.CMD %>" value="setActive" />
			<portlet:param name="redirect" value="<%= currentURL %>" />
			<portlet:param name="commercePaymentMethodGroupRelId" value="<%= String.valueOf(commercePaymentMethodGroupRel.getCommercePaymentMethodGroupRelId()) %>" />
			<portlet:param name="active" value="<%= String.valueOf(!commercePaymentMethodGroupRel.isActive()) %>" />
			<portlet:param name="engineKey" value="<%= commercePaymentMethodGroupRel.getEngineKey() %>" />
		</portlet:actionURL>

		<liferay-ui:icon
			message='<%= commercePaymentMethodGroupRel.isActive() ? "deactivate" : "activate" %>'
			url="<%= setActiveURL %>"
		/>

		<portlet:actionURL name="editCommercePaymentMethodGroupRel" var="editURL">
			<portlet:param name="<%= Constants.CMD %>" value="<%= Constants.DELETE %>" />
			<portlet:param name="redirect" value="<%= currentURL %>" />
			<portlet:param name="commercePaymentMethodGroupRelId" value="<%= String.valueOf(commercePaymentMethodGroupRel.getCommercePaymentMethodGroupRelId()) %>" />
			<portlet:param name="engineKey" value="<%= commercePaymentMethodGroupRel.getEngineKey() %>" />
		</portlet:actionURL>

		<liferay-ui:icon
			message="delete"
			url="<%= editURL %>"
		/>
	</c:if>
</liferay-ui:icon-menu>