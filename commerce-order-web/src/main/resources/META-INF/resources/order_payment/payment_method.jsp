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
List<CommercePaymentMethodGroupRel> commercePaymentMethods = commerceOrderEditDisplayContext.getCommercePaymentMethods();
%>

<portlet:actionURL name="editCommerceOrder" var="editCommerceOrderPaymentMethodActionURL" />

<aui:form action="<%= editCommerceOrderPaymentMethodActionURL %>" cssClass="container-fluid-1280 p-0" method="post" name="fm">
	<aui:input name="<%= Constants.CMD %>" type="hidden" value="paymentMethod" />
	<aui:input name="redirect" type="hidden" value="<%= currentURL %>" />
	<aui:input name="commerceOrderId" type="hidden" value="<%= commerceOrder.getCommerceOrderId() %>" />

	<div class="border-0 sheet">
		<liferay-ui:error exception="<%= CommerceOrderPaymentMethodException.class %>" message="please-select-a-valid-payment-method" />

		<c:choose>
			<c:when test="<%= commercePaymentMethods.isEmpty() %>">
				<aui:row>
					<aui:col width="<%= 100 %>">
						<aui:alert closeable="<%= false %>" type="info">
							<liferay-ui:message key="there-are-no-available-payment-methods" />
						</aui:alert>
					</aui:col>
				</aui:row>
			</c:when>
			<c:otherwise>
				<ul class="list-group">

					<%
					for (CommercePaymentMethodGroupRel commercePaymentMethodGroupRel : commercePaymentMethods) {
					%>

						<li class="commerce-payment-types list-group-item list-group-item-flex">
							<div class="autofit-col autofit-col-expand">
								<aui:input checked="<%= commercePaymentMethodGroupRel.getEngineKey() == commerceOrder.getCommercePaymentMethodKey() %>" label="<%= commercePaymentMethodGroupRel.getName(locale) %>" name="commercePaymentMethodKey" type="radio" value="<%= commercePaymentMethodGroupRel.getEngineKey() %>" />
							</div>

							<%
							String thumbnailSrc = commercePaymentMethodGroupRel.getImageURL(themeDisplay);
							%>

							<c:if test="<%= Validator.isNotNull(thumbnailSrc) %>">
								<div class="autofit-col">
									<img alt="<%= commercePaymentMethodGroupRel.getName(locale) %>" class="payment-icon" src="<%= thumbnailSrc %>" style="height: 45px; width: auto" />
								</div>
							</c:if>
						</li>

					<%
					}
					%>

				</ul>
			</c:otherwise>
		</c:choose>
	</div>

	<aui:button type="submit" />
</aui:form>