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
ResultRow row = (ResultRow)request.getAttribute(WebKeys.SEARCH_CONTAINER_RESULT_ROW);

CommerceMLForecastAlertEntry commerceMLForecastAlertEntry = (CommerceMLForecastAlertEntry)row.getObject();

CommerceMLForecastAlertEntryListDisplayContext commerceMLForecastAlertEntryListDisplayContext = (CommerceMLForecastAlertEntryListDisplayContext)request.getAttribute(WebKeys.PORTLET_DISPLAY_CONTEXT);
%>

<liferay-ui:icon-menu
	direction="left-side"
	icon="<%= StringPool.BLANK %>"
	markupView="lexicon"
	message="<%= StringPool.BLANK %>"
	showWhenSingleIcon="<%= false %>"
>
	<c:if test="<%= commerceMLForecastAlertEntryListDisplayContext.hasUpdatePermission() %>">

		<%
		int currentStatus = commerceMLForecastAlertEntry.getStatus();

		int newStatus = CommerceMLForecastAlertConstants.STATUS_ARCHIVE;

		if (currentStatus == CommerceMLForecastAlertConstants.STATUS_ARCHIVE) {
			newStatus = CommerceMLForecastAlertConstants.STATUS_NEW;
		}
		%>

		<portlet:actionURL name="updateStatus" var="updateStatusURL">
			<portlet:param name="<%= Constants.CMD %>" value="<%= CommerceMLForecastAlertActionKeys.MANAGE_ALERT_STATUS %>" />
			<portlet:param name="redirect" value="<%= currentURL %>" />
			<portlet:param name="commerceMLForecastAlertEntryId" value="<%= String.valueOf(commerceMLForecastAlertEntry.getCommerceMLForecastAlertEntryId()) %>" />
			<portlet:param name="status" value="<%= String.valueOf(newStatus) %>" />
		</portlet:actionURL>

		<liferay-ui:icon
			message="updateStatus"
			url="<%= updateStatusURL %>"
		/>
	</c:if>
</liferay-ui:icon-menu>