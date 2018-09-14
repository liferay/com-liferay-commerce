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
CPSubscriptionEntryDisplayContext commerceSubscriptionEntryDisplayContext = (CPSubscriptionEntryDisplayContext)request.getAttribute(WebKeys.PORTLET_DISPLAY_CONTEXT);

CPSubscriptionEntry cpSubscriptionEntry = commerceSubscriptionEntryDisplayContext.getCpSubscriptionEntry();

String redirect = ParamUtil.getString(request, "redirect");
%>

<portlet:actionURL name="editCPSubscriptionEntry" var="editCPSubscriptionEntryActionURL" />

<aui:form action="<%= editCPSubscriptionEntryActionURL %>" cssClass="container-fluid-1280" method="post" name="fm">
	<aui:input name="<%= Constants.CMD %>" type="hidden" value="<%= Constants.UPDATE %>" />
	<aui:input name="redirect" type="hidden" value="<%= currentURL %>" />
	<aui:input name="cpSubscriptionEntryId" type="hidden" value="<%= String.valueOf(commerceSubscriptionEntryDisplayContext.getCPSubscriptionEntryId()) %>" />

	<aui:fieldset-group markupView="lexicon">
		<aui:fieldset>
			<aui:model-context bean="<%= cpSubscriptionEntry %>" model="<%= CPSubscriptionEntry.class %>" />

			<aui:input autoFocus="<%= true %>" name="subscriptionCycleLength" />

			<aui:input name="subscriptionCyclePeriod" />

			<aui:input name="maxSubscriptionCyclesNumber" />

			<aui:input name="active" type="toggle-switch" />
		</aui:fieldset>
	</aui:fieldset-group>

	<aui:button-row>
		<aui:button cssClass="btn-lg" type="submit" />

		<aui:button cssClass="btn-lg" href="<%= redirect %>" type="cancel" />
	</aui:button-row>
</aui:form>