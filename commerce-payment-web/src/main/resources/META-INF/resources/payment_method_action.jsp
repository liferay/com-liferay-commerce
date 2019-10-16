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

String engineKey = commercePaymentMethodGroupRel.getEngineKey();

String modalId = "modal" + engineKey;

int subscriptionEntryCount = commercePaymentMethodGroupRelsDisplayContext.getSubscriptionEntryCount(engineKey);
int recurringCPDefinitionsCount = commercePaymentMethodGroupRelsDisplayContext.getRecurringCPDefinitionsCount(commercePaymentMethodGroupRel);

boolean isLastRecurringPaymentMethod = commercePaymentMethodGroupRelsDisplayContext.isLastRecurringPaymentMethod(engineKey);
%>

<portlet:actionURL name="editCommercePaymentMethodGroupRel" var="setActiveURL">
	<portlet:param name="<%= Constants.CMD %>" value="setActive" />
	<portlet:param name="redirect" value="<%= currentURL %>" />
	<portlet:param name="commercePaymentMethodGroupRelId" value="<%= String.valueOf(commercePaymentMethodGroupRel.getCommercePaymentMethodGroupRelId()) %>" />
	<portlet:param name="active" value="<%= String.valueOf(!commercePaymentMethodGroupRel.isActive()) %>" />
	<portlet:param name="engineKey" value="<%= engineKey %>" />
</portlet:actionURL>

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
			<portlet:param name="engineKey" value="<%= engineKey %>" />
		</portlet:actionURL>

		<liferay-ui:icon
			message="edit"
			url="<%= editURL %>"
		/>

		<liferay-ui:icon
			id='<%= "togglePaymentMethod-" + modalId %>'
			message='<%= commercePaymentMethodGroupRel.isActive() ? "deactivate" : "activate" %>'
			url="<%= commercePaymentMethodGroupRel.isActive() ? StringPool.POUND : setActiveURL %>"
		/>

		<portlet:actionURL name="editCommercePaymentMethodGroupRel" var="editURL">
			<portlet:param name="<%= Constants.CMD %>" value="<%= Constants.DELETE %>" />
			<portlet:param name="redirect" value="<%= currentURL %>" />
			<portlet:param name="commercePaymentMethodGroupRelId" value="<%= String.valueOf(commercePaymentMethodGroupRel.getCommercePaymentMethodGroupRelId()) %>" />
			<portlet:param name="engineKey" value="<%= engineKey %>" />
		</portlet:actionURL>

		<liferay-ui:icon
			message="delete"
			url="<%= editURL %>"
		/>
	</c:if>
</liferay-ui:icon-menu>

<c:choose>
	<c:when test="<%= ((subscriptionEntryCount > 0) || isLastRecurringPaymentMethod) && commercePaymentMethodGroupRel.isActive() %>">
		<style>
			.warning-modal .modal-header {
				background-color: #feefef;
				border-color: #f48989;
				color: #da1414;
			}

			.warning-modal p {
				font-size: 16px;
			}
		</style>

		<div class="warning-modal" id="<%= modalId %>"></div>

		<%
			String deactivationWarning = StringPool.BLANK;

			if (subscriptionEntryCount > 0) {
				deactivationWarning += LanguageUtil.format(locale, "there-are-x-subscriptions-that-depend-on-this-payment-method-if-you-deactivate-it-you-will-have-to-manually-manage-those-subscriptions", subscriptionEntryCount);
			}

			if (isLastRecurringPaymentMethod) {
				deactivationWarning += "<br /><br />" + LanguageUtil.format(locale, "deactivating-this-payment-method-will-disable-x-subcription-products", recurringCPDefinitionsCount);
			}
		%>

		<aui:script use="aui-base">
			A.use(
				'aui-modal',
				function(A) {
					var <%= modalId %> = new A.Modal(
						{
							bodyContent: '<p><liferay-ui:message key="<%= deactivationWarning %>" /><p>',
							centered: true,
							draggable: false,
							destroyOnHide: false,
							headerContent: '<h2><liferay-ui:message key="are-you-sure" /></h2>',
							modal: true,
							boundingBox: '#<%= modalId %>',
							width: 450
						}
					);

					<%= modalId %>.addToolbar(
						[
							{
								label: '<liferay-ui:message key="cancel" />',
								on: {
									click: function() {
										<%= modalId %>.hide();
									}
								}
							},
							{
								cssClass: 'btn-danger',
								label: '<liferay-ui:message key="proceed" />',
								on: {
									click: function() {
										window.location.replace('<%= setActiveURL %>');
									}
								}
							}
						]
					);

					A.one("#<portlet:namespace />togglePaymentMethod-<%= modalId %>").on(
						'click',
						function(e) {
							e.preventDefault();
							<%= modalId %>.render();
							<%= modalId %>.show();
						}
					);
				}
			);
		</aui:script>
	</c:when>
	<c:otherwise>
		<aui:script use="aui-base">
			A.one("#<portlet:namespace />togglePaymentMethod-<%= modalId %>").on(
				'click',
				function() {
					window.location.replace('<%= setActiveURL %>');
				}
			);
		</aui:script>
	</c:otherwise>
</c:choose>