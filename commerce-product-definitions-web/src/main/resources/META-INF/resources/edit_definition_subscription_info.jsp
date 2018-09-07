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
CPDefinitionSubscriptionInfoDisplayContext cpDefinitionSubscriptionInfoDisplayContext = (CPDefinitionSubscriptionInfoDisplayContext)request.getAttribute(WebKeys.PORTLET_DISPLAY_CONTEXT);

CPDefinition cpDefinition = cpDefinitionSubscriptionInfoDisplayContext.getCPDefinition();

long cpDefinitionId = cpDefinitionSubscriptionInfoDisplayContext.getCPDefinitionId();

boolean subscriptionEnabled = BeanParamUtil.getBoolean(cpDefinition, request, "subscriptionEnabled", false);
long subscriptionCycleLength = BeanParamUtil.getLong(cpDefinition, request, "subscriptionCycleLength");
String subscriptionCyclePeriod = BeanParamUtil.getString(cpDefinition, request, "subscriptionCyclePeriod");
long maxSubscriptionCyclesNumber = BeanParamUtil.getLong(cpDefinition, request, "maxSubscriptionCyclesNumber");
%>

<portlet:actionURL name="editProductDefinition" var="editProductDefinitionSubscriptionInfoActionURL" />

<aui:form action="<%= editProductDefinitionSubscriptionInfoActionURL %>" cssClass="container-fluid-1280" method="post" name="fm">
	<aui:input name="<%= Constants.CMD %>" type="hidden" value="updateSubscriptionInfo" />
	<aui:input name="redirect" type="hidden" value="<%= currentURL %>" />
	<aui:input name="cpDefinitionId" type="hidden" value="<%= cpDefinitionId %>" />

	<aui:model-context bean="<%= cpDefinition %>" model="<%= CPDefinition.class %>" />

	<aui:fieldset-group markupView="lexicon">
		<aui:fieldset>
			<aui:input checked="<%= subscriptionEnabled %>" name="subscriptionEnabled" type="toggle-switch" value="<%= subscriptionEnabled %>" />

			<div class="<%= subscriptionEnabled ? StringPool.BLANK : "hide" %>" id="<portlet:namespace />subscriptionOptions">
				<aui:input label="subscription-cycle-length" name="subscriptionCycleLength" value="<%= String.valueOf(subscriptionCycleLength) %>" />

				<aui:select label="subscription-cycle-period" name="subscriptionCyclePeriod" showEmptyOption="<%= true %>">

					<%
					for (String subscriptionPeriod : cpDefinitionSubscriptionInfoDisplayContext.getSubscriptionCyclePeriods()) {
					%>

						<aui:option label="<%= subscriptionPeriod %>" selected="" value="<%= subscriptionPeriod %>" />

					<%
					}
					%>

				</aui:select>

				<aui:input label="max-subscription-cycles-number" name="maxSubscriptionCyclesNumber" value="<%= String.valueOf(maxSubscriptionCyclesNumber) %>" />
			</div>
		</aui:fieldset>
	</aui:fieldset-group>

	<aui:button-row>
		<aui:button cssClass="btn-lg" type="submit" />

		<aui:button cssClass="btn-lg" href="<%= catalogURL %>" type="cancel" />
	</aui:button-row>
</aui:form>

<aui:script>
	Liferay.Util.toggleBoxes('<portlet:namespace />subscriptionEnabled', '<portlet:namespace />subscriptionOptions');
</aui:script>