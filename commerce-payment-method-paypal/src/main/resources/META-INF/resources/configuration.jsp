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
PayPalGroupServiceConfiguration payPalGroupServiceConfiguration = (PayPalGroupServiceConfiguration)request.getAttribute(PayPalGroupServiceConfiguration.class.getName());
%>

<portlet:actionURL name="editPayPalCommercePaymentMethodConfiguration" var="editCommercePaymentMethodActionURL" />

<aui:form action="<%= editCommercePaymentMethodActionURL %>" cssClass="container-fluid-1280" method="post" name="fm">
	<aui:input name="<%= Constants.CMD %>" type="hidden" value="<%= Constants.UPDATE %>" />
	<aui:input name="redirect" type="hidden" value="<%= currentURL %>" />

	<aui:fieldset-group markupView="lexicon">
		<aui:fieldset label="authentication">
			<div class="alert alert-info">
				<%= LanguageUtil.format(resourceBundle, "paypal-configuration-help", new Object[] {"<a href=\"https://developer.paypal.com/developer/applications/create\" target=\"_blank\">", "</a>"}, false) %>
			</div>

			<aui:input id="paypal-client-id" label="client-id" name="settings--clientId--" value="<%= payPalGroupServiceConfiguration.clientId() %>" />

			<aui:input id="paypal-client-secret" label="client-secret" name="settings--clientSecret--" value="<%= payPalGroupServiceConfiguration.clientSecret() %>" />

			<aui:select id="paypal-settings--mode" name="settings--mode--">

				<%
				for (String mode : PayPalCommercePaymentMethodConstants.MODES) {
				%>

					<aui:option label="<%= mode %>" selected="<%= mode.equals(payPalGroupServiceConfiguration.mode()) %>" value="<%= mode %>" />

				<%
				}
				%>

			</aui:select>
		</aui:fieldset>

		<aui:fieldset label="options">
			<aui:input id="paypal-payment-attempts-max-count" label="payment-attempts-max-count" name="settings--paymentAttempts--" value="<%= payPalGroupServiceConfiguration.paymentAttemptsMaxCount() %>" />
		</aui:fieldset>
	</aui:fieldset-group>

	<aui:button-row>
		<aui:button cssClass="btn-lg" type="submit" />

		<aui:button cssClass="btn-lg" href="<%= redirect %>" type="cancel" />
	</aui:button-row>
</aui:form>