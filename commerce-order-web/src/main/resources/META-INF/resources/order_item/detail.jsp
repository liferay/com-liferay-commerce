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
CommerceOrderEditDisplayContext commerceOrderEditDisplayContext = (CommerceOrderEditDisplayContext)request.getAttribute(WebKeys.PORTLET_DISPLAY_CONTEXT);

CommerceOrder commerceOrder = commerceOrderEditDisplayContext.getCommerceOrder();
CommerceOrderItem commerceOrderItem = commerceOrderEditDisplayContext.getCommerceOrderItem();

CommerceCurrency commerceCurrency = commerceOrder.getCommerceCurrency();

portletDisplay.setShowBackIcon(true);

if (Validator.isNull(redirect)) {
	redirect = String.valueOf(commerceOrderEditDisplayContext.getCommerceOrderItemsPortletURL());
}

portletDisplay.setURLBack(redirect);

String title = LanguageUtil.format(request, "order-x", commerceOrder.getCommerceOrderId()) + " - " + HtmlUtil.escape(commerceOrderItem.getSku());

renderResponse.setTitle(title);

ExpandoBridge expandoBridge = commerceOrderItem.getExpandoBridge();

Map<String, Serializable> attributes = expandoBridge.getAttributes();
%>

<portlet:actionURL name="editCommerceOrderItem" var="editCommerceOrderItemActionURL" />

<aui:form action="<%= editCommerceOrderItemActionURL %>" cssClass="container-fluid-1280" method="post" name="fm">
	<aui:input name="<%= Constants.CMD %>" type="hidden" value="<%= Constants.UPDATE %>" />
	<aui:input name="redirect" type="hidden" value="<%= currentURL %>" />
	<aui:input name="commerceOrderId" type="hidden" value="<%= commerceOrderItem.getCommerceOrderId() %>" />
	<aui:input name="commerceOrderItemId" type="hidden" value="<%= commerceOrderItem.getCommerceOrderItemId() %>" />

	<aui:fieldset-group markupView="lexicon">
		<aui:fieldset>
			<aui:input bean="<%= commerceOrderItem %>" name="quantity" />

			<c:if test="<%= !commerceOrder.isOpen() %>">
				<aui:input name="price" suffix="<%= HtmlUtil.escape(commerceCurrency.getCode()) %>" type="text" value="<%= commerceCurrency.round(commerceOrderItem.getUnitPrice()) %>">
					<aui:validator name="number" />
				</aui:input>
			</c:if>
		</aui:fieldset>
	</aui:fieldset-group>

	<c:if test="<%= attributes.size() > 0 %>">
		<liferay-ui:form-navigator
			formModelBean="<%= commerceOrderItem %>"
			id="<%= CommerceOrderFormNavigatorConstants.FORM_NAVIGATOR_ID_COMMERCE_ORDER_ITEM_DETAILS %>"
			markupView="lexicon"
			showButtons="<%= false %>"
		/>
	</c:if>

	<aui:button-row>
		<aui:button cssClass="btn-lg" type="submit" />

		<aui:button cssClass="btn-lg" href="<%= redirect %>" type="cancel" />
	</aui:button-row>
</aui:form>