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
CommerceSubscriptionEntryDisplayContext commerceSubscriptionEntryDisplayContext = (CommerceSubscriptionEntryDisplayContext)request.getAttribute(WebKeys.PORTLET_DISPLAY_CONTEXT);

ResultRow row = (ResultRow)request.getAttribute(WebKeys.SEARCH_CONTAINER_RESULT_ROW);

int subscriptionStatus = CommerceSubscriptionEntryConstants.SUBSCRIPTION_STATUS_ANY;

CommerceSubscriptionEntry commerceSubscriptionEntry = (CommerceSubscriptionEntry)row.getObject();

if (commerceSubscriptionEntry != null) {
	subscriptionStatus = commerceSubscriptionEntry.getSubscriptionStatus();
}

CommerceOrder commerceOrder = (CommerceOrder)request.getAttribute(CommerceOrderConstants.COMMERCE_ORDER);
%>

<liferay-ui:icon-menu
	direction="left-side"
	icon="<%= StringPool.BLANK %>"
	markupView="lexicon"
	message="<%= StringPool.BLANK %>"
	showWhenSingleIcon="<%= true %>"
>
	<c:if test="<%= commerceSubscriptionEntryDisplayContext.hasManageCommerceSubscriptionEntryPermission() %>">
		<portlet:renderURL var="editURL">
			<portlet:param name="mvcRenderCommandName" value="editCommerceSubscriptionEntry" />
			<portlet:param name="redirect" value="<%= currentURL %>" />
			<portlet:param name="commerceSubscriptionEntryId" value="<%= String.valueOf(commerceSubscriptionEntry.getCommerceSubscriptionEntryId()) %>" />
		</portlet:renderURL>

		<liferay-ui:icon
			message="edit"
			url="<%= editURL %>"
		/>

		<c:if test="<%= commerceSubscriptionEntryDisplayContext.isPaymentMethodActive(commerceOrder.getCommercePaymentMethodKey()) %>">
			<c:if test="<%= subscriptionStatus != CommerceSubscriptionEntryConstants.SUBSCRIPTION_STATUS_ACTIVE %>">
				<portlet:actionURL name="editCommerceSubscriptionEntry" var="activateURL">
					<portlet:param name="<%= Constants.CMD %>" value="activate" />
					<portlet:param name="redirect" value="<%= currentURL %>" />
					<portlet:param name="commerceSubscriptionEntryId" value="<%= String.valueOf(commerceSubscriptionEntry.getCommerceSubscriptionEntryId()) %>" />
				</portlet:actionURL>

				<liferay-ui:icon
					message="activate"
					url="<%= activateURL %>"
				/>
			</c:if>

			<c:if test="<%= subscriptionStatus != CommerceSubscriptionEntryConstants.SUBSCRIPTION_STATUS_SUSPENDED %>">
				<portlet:actionURL name="editCommerceSubscriptionEntry" var="suspendURL">
					<portlet:param name="<%= Constants.CMD %>" value="suspend" />
					<portlet:param name="redirect" value="<%= currentURL %>" />
					<portlet:param name="commerceSubscriptionEntryId" value="<%= String.valueOf(commerceSubscriptionEntry.getCommerceSubscriptionEntryId()) %>" />
				</portlet:actionURL>

				<liferay-ui:icon
					message="suspend"
					url="<%= suspendURL %>"
				/>
			</c:if>

			<c:if test="<%= subscriptionStatus != CommerceSubscriptionEntryConstants.SUBSCRIPTION_STATUS_CANCELLED %>">
				<portlet:actionURL name="editCommerceSubscriptionEntry" var="cancelURL">
					<portlet:param name="<%= Constants.CMD %>" value="cancel" />
					<portlet:param name="redirect" value="<%= currentURL %>" />
					<portlet:param name="commerceSubscriptionEntryId" value="<%= String.valueOf(commerceSubscriptionEntry.getCommerceSubscriptionEntryId()) %>" />
				</portlet:actionURL>

				<liferay-ui:icon
					message="cancel"
					url="<%= cancelURL %>"
				/>
			</c:if>
		</c:if>

		<portlet:actionURL name="editCommerceSubscriptionEntry" var="deleteURL">
			<portlet:param name="<%= Constants.CMD %>" value="<%= Constants.DELETE %>" />
			<portlet:param name="redirect" value="<%= currentURL %>" />
			<portlet:param name="commerceSubscriptionEntryId" value="<%= String.valueOf(commerceSubscriptionEntry.getCommerceSubscriptionEntryId()) %>" />
		</portlet:actionURL>

		<liferay-ui:icon-delete
			url="<%= deleteURL %>"
		/>
	</c:if>
</liferay-ui:icon-menu>