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
CommerceOrderDetailHelper commerceOrderDetailHelper = (CommerceOrderDetailHelper)request.getAttribute(CommerceOrderDetailWebKeys.COMMERCE_ORDER_DETAIL_HELPER);
%>

<liferay-portlet:actionURL portletConfiguration="<%= true %>" var="configurationActionURL" />

<liferay-portlet:renderURL portletConfiguration="<%= true %>" var="configurationRenderURL" />

<aui:form action="<%= configurationActionURL %>" method="post" name="fm">
	<aui:input name="<%= Constants.CMD %>" type="hidden" value="<%= Constants.UPDATE %>" />
	<aui:input name="redirect" type="hidden" value="<%= configurationRenderURL %>" />

	<aui:fieldset markupView="lexicon">
		<aui:select label='<%= LanguageUtil.get(request, "placed-commerce-order-detail-renderer-key") %>' name='<%= "preferences--pendingCommerceOrderDetailRendererKey" %>'>

			<%
			for (CommerceOrderDetailRenderer commerceOrderDetailRenderer : commerceOrderDetailHelper.getCommerceOrderDetailRenderers(CommerceOrderDetailConstants.ORDER_DETAIL_STATUS_PENDING)) {
				String key = commerceOrderDetailRenderer.getKey();
			%>

				<aui:option label="<%= HtmlUtil.escape(commerceOrderDetailRenderer.getLabel(locale)) %>" selected="<%= key.equals(commerceOrderDetailHelper.getCommerceOrderDetailRendererKey(CommerceOrderDetailConstants.ORDER_DETAIL_STATUS_PENDING, renderRequest)) %>" value="<%= key %>" />

			<%
			}
			%>

		</aui:select>
	</aui:fieldset>

	<aui:fieldset markupView="lexicon">
		<aui:select label='<%= LanguageUtil.get(request, "placed-commerce-order-detail-renderer-key") %>' name='<%= "preferences--placedCommerceOrderDetailRendererKey" %>'>

			<%
			for (CommerceOrderDetailRenderer commerceOrderDetailRenderer : commerceOrderDetailHelper.getCommerceOrderDetailRenderers(CommerceOrderDetailConstants.ORDER_DETAIL_STATUS_PLACED)) {
				String key = commerceOrderDetailRenderer.getKey();
			%>

				<aui:option label="<%= HtmlUtil.escape(commerceOrderDetailRenderer.getLabel(locale)) %>" selected="<%= key.equals(commerceOrderDetailHelper.getCommerceOrderDetailRendererKey(CommerceOrderDetailConstants.ORDER_DETAIL_STATUS_PLACED, renderRequest)) %>" value="<%= key %>" />

			<%
			}
			%>

		</aui:select>
	</aui:fieldset>

	<aui:button-row>
		<aui:button cssClass="btn-lg" name="submitButton" type="submit" value="save" />
	</aui:button-row>
</aui:form>