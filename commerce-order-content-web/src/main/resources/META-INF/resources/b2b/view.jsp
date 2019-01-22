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
CommerceOrderContentDisplayContext commerceOrderContentDisplayContext = (CommerceOrderContentDisplayContext)request.getAttribute(WebKeys.PORTLET_DISPLAY_CONTEXT);

CommerceContext commerceContext = (CommerceContext)request.getAttribute(CommerceWebKeys.COMMERCE_CONTEXT);

CommerceAccount commerceAccount = commerceContext.getCommerceAccount();
%>

<liferay-portlet:renderURL var="addCommerceOrderURL" windowState="<%= LiferayWindowState.EXCLUSIVE.toString() %>">
	<liferay-portlet:param name="mvcRenderCommandName" value="editCommerceOrderDetails" />
	<liferay-portlet:param name="redirect" value="<%= currentURL %>" />
</liferay-portlet:renderURL>

<portlet:actionURL name="editCommerceOrder" var="editCommerceOrderURL" />

<aui:form action="<%= editCommerceOrderURL %>" method="post" name="fm">
	<aui:input name="<%= Constants.CMD %>" type="hidden" />
	<aui:input name="redirect" type="hidden" value="<%= currentURL %>" />
	<aui:input name="deleteCommerceOrderIds" type="hidden" />

	<commerce-ui:table
		dataProviderKey="commerceOrders"
		itemPerPage="<%= 5 %>"
		namespace="<%= renderResponse.getNamespace() %>"
		pageNumber="1"
		portletURL="<%= commerceOrderContentDisplayContext.getPortletURL() %>"
		tableName="commerceOrders"
	/>

	<c:if test="<%= commerceOrderContentDisplayContext.hasPermission(CommerceOrderActionKeys.ADD_COMMERCE_ORDER) && (commerceAccount != null) %>">
		<div class="minium-frame__cta is-visible">
			<aui:button cssClass="js-invite-user minium-button minium-button--big" onClick="<%= renderResponse.getNamespace() %>addCommerceOrder();" value="add-order" />
		</div>
	</c:if>
</aui:form>

<aui:script>
	Liferay.provide(
		window,
		'<portlet:namespace />addCommerceOrder',
		function(A) {
			var A = AUI();

			var dialog = Liferay.Util.Window.getWindow(
				{
					dialog: {
						destroyOnClose: true,
						toolbars: {
							footer: [
								{
									cssClass: 'btn-cancel mr-2',
									label: '<liferay-ui:message key="cancel" />',
									on: {
										click: function() {
											dialog.hide();
										}
									}
								},
								{
									cssClass: 'btn-primary',
									label: '<liferay-ui:message key="add-order" />',
									on: {
										click: function() {
											submitForm(document.<portlet:namespace />editFm);
										}
									}
								}
							],
							header: [
								{
									cssClass: 'close',
									discardDefaultButtonCssClasses: true,
									labelHTML: '<clay:icon symbol="times" />',
									on: {
										click: function(event) {
											dialog.hide();
										}
									}
								}
							]
						},
						width: 600
					},
					title: '<liferay-ui:message key="add-order" />'
				}
			).plug(
				A.Plugin.IO,
				{
					uri: '<%= addCommerceOrderURL %>'
				}
			).render();
		},
		['aui-io-deprecated', 'liferay-util-window']
	);

	function <portlet:namespace />deleteCommerceOrders() {
		if (confirm('<liferay-ui:message key="are-you-sure-you-want-to-delete-the-selected-orders" />')) {
			var form = AUI.$(document.<portlet:namespace />fm);

			form.fm('<%= Constants.CMD %>').val('<%= Constants.DELETE %>');
			form.fm('deleteCommerceOrderIds').val(Liferay.Util.listCheckedExcept(form, '<portlet:namespace />allRowIds'));

			submitForm(form);
		}
	}
</aui:script>