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

CommerceSubscriptionEntry commerceSubscriptionEntry = commerceSubscriptionEntryDisplayContext.getCommerceSubscriptionEntry();

String redirect = ParamUtil.getString(request, "redirect");

long subscriptionCycleLength = BeanParamUtil.getLong(commerceSubscriptionEntry, request, "subscriptionCycleLength");
String subscriptionCyclePeriod = BeanParamUtil.getString(commerceSubscriptionEntry, request, "subscriptionCyclePeriod");
long maxSubscriptionCyclesNumber = BeanParamUtil.getLong(commerceSubscriptionEntry, request, "maxSubscriptionCyclesNumber");
%>

<portlet:actionURL name="editCommerceSubscriptionEntry" var="editCommerceSubscriptionEntryActionURL" />

<aui:form action="<%= editCommerceSubscriptionEntryActionURL %>" cssClass="container-fluid-1280" method="post" name="fm">
	<aui:input name="<%= Constants.CMD %>" type="hidden" value="<%= Constants.UPDATE %>" />
	<aui:input name="redirect" type="hidden" value="<%= currentURL %>" />
	<aui:input name="commerceSubscriptionEntryId" type="hidden" value="<%= String.valueOf(commerceSubscriptionEntryDisplayContext.getCommerceSubscriptionEntryId()) %>" />

	<aui:fieldset-group markupView="lexicon">
		<aui:fieldset>
			<aui:model-context bean="<%= commerceSubscriptionEntry %>" model="<%= CommerceSubscriptionEntry.class %>" />

			<aui:input autoFocus="<%= true %>" name="subscriptionCycleLength" value="<%= String.valueOf(subscriptionCycleLength) %>">
				<aui:validator name="digits" />
				<aui:validator name="min">1</aui:validator>
			</aui:input>

			<aui:select name="subscriptionCyclePeriod" showEmptyOption="<%= true %>">

				<%
				for (String subscriptionPeriod : CPConstants.SUBSCRIPTION_CYCLES) {
				%>

					<aui:option label="<%= subscriptionPeriod %>" selected="<%= subscriptionPeriod.equals(subscriptionCyclePeriod) %>" value="<%= subscriptionPeriod %>" />

				<%
				}
				%>

			</aui:select>

			<aui:input helpMessage="max-subscription-cycles-number-help" name="maxSubscriptionCyclesNumber" value="<%= String.valueOf(maxSubscriptionCyclesNumber) %>">
				<aui:validator name="digits" />
			</aui:input>

			<aui:input name="active" type="toggle-switch" />
		</aui:fieldset>
	</aui:fieldset-group>

	<aui:button-row>
		<aui:button cssClass="btn-lg" type="submit" />

		<aui:button cssClass="btn-lg" href="<%= redirect %>" type="cancel" />
	</aui:button-row>
</aui:form>