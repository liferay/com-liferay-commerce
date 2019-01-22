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

<portlet:actionURL name="editCommerceOrder" var="editCommerceOrderURL" />

<aui:form action="<%= editCommerceOrderURL %>" method="post" name="fm">
	<aui:input name="<%= Constants.CMD %>" type="hidden" value="<%= Constants.ADD %>" />
	<aui:input name="redirect" type="hidden" value="<%= currentURL %>" />
	<aui:input name="commerceAccountId" type="hidden" value="<%= (commerceAccount == null) ? 0 : commerceAccount.getCommerceAccountId() %>" />
	<aui:input name="deleteCommerceOrderIds" type="hidden" />

	<commerce-ui:table
		dataProviderKey="commerceOrders"
		itemPerPage="<%= 5 %>"
		namespace="<%= renderResponse.getNamespace() %>"
		pageNumber="1"
		portletURL="<%= commerceOrderContentDisplayContext.getPortletURL() %>"
		tableName="commerceOrders"
	/>

	<c:if test="<%= commerceOrderContentDisplayContext.isOpenOrderContentPortlet() && commerceOrderContentDisplayContext.hasPermission(CommerceOrderActionKeys.ADD_COMMERCE_ORDER) && (commerceAccount != null) %>">
		<div class="minium-frame__cta is-visible">
			<aui:button cssClass="js-invite-user minium-button minium-button--big" type="submit" value="add-order" />
		</div>
	</c:if>
</aui:form>

<aui:script>
	function <portlet:namespace />deleteCommerceOrders() {
		if (confirm('<liferay-ui:message key="are-you-sure-you-want-to-delete-the-selected-orders" />')) {
			var form = AUI.$(document.<portlet:namespace />fm);

			form.fm('<%= Constants.CMD %>').val('<%= Constants.DELETE %>');
			form.fm('deleteCommerceOrderIds').val(Liferay.Util.listCheckedExcept(form, '<portlet:namespace />allRowIds'));

			submitForm(form);
		}
	}
</aui:script>