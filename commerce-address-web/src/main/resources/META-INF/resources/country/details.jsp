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
CommerceCountriesDisplayContext commerceCountriesDisplayContext = (CommerceCountriesDisplayContext)request.getAttribute(WebKeys.PORTLET_DISPLAY_CONTEXT);

CommerceCountry commerceCountry = commerceCountriesDisplayContext.getCommerceCountry();

long commerceCountryId = commerceCountriesDisplayContext.getCommerceCountryId();
%>

<portlet:actionURL name="editCommerceCountry" var="editCommerceCountryActionURL" />

<aui:form action="<%= editCommerceCountryActionURL %>" cssClass="container-fluid-1280" method="post" name="fm" onSubmit='<%= "event.preventDefault(); " + renderResponse.getNamespace() + "saveCommerceCountry();" %>'>
	<aui:input name="<%= Constants.CMD %>" type="hidden" value="<%= (commerceCountry == null) ? Constants.ADD : Constants.UPDATE %>" />
	<aui:input name="redirect" type="hidden" value="<%= currentURL %>" />
	<aui:input name="backURL" type="hidden" value="<%= redirect %>" />
	<aui:input name="commerceCountryId" type="hidden" value="<%= String.valueOf(commerceCountryId) %>" />

	<liferay-ui:error exception="<%= CommerceCountryAlreadyExistsException.class %>" message="the-two-letter-iso-code-is-already-used" />
	<liferay-ui:error exception="<%= CommerceCountryNameException.class %>" message="please-enter-a-valid-name" />
	<liferay-ui:error exception="<%= CommerceCountryThreeLettersISOCodeException.class %>" message="please-enter-a-valid-three-letter-iso-code" />
	<liferay-ui:error exception="<%= CommerceCountryTwoLettersISOCodeException.class %>" message="please-enter-a-valid-two-letter-iso-code" />

	<aui:model-context bean="<%= commerceCountry %>" model="<%= CommerceCountry.class %>" />

	<aui:fieldset-group markupView="lexicon">
		<aui:fieldset>
			<aui:fieldset>
				<aui:input autoFocus="<%= true %>" name="name" />

				<aui:input checked="<%= (commerceCountry == null) ? false : commerceCountry.getBillingAllowed() %>" name="billingAllowed" type="toggle-switch" />

				<aui:input checked="<%= (commerceCountry == null) ? false : commerceCountry.getShippingAllowed() %>" name="shippingAllowed" type="toggle-switch" />

				<aui:input label="two-letter-iso-code" name="twoLettersISOCode" />

				<aui:input label="three-letter-iso-code" name="threeLettersISOCode" />

				<aui:input name="numericISOCode" />

				<aui:input checked="<%= (commerceCountry == null) ? false : commerceCountry.getSubjectToVAT() %>" name="subjectToVAT" type="toggle-switch" />

				<aui:input name="priority" />

				<aui:input checked="<%= (commerceCountry == null) ? false : commerceCountry.isActive() %>" name="active" type="toggle-switch" />
			</aui:fieldset>
		</aui:fieldset>
	</aui:fieldset-group>

	<aui:button-row>
		<aui:button cssClass="btn-lg" type="submit" />

		<aui:button cssClass="btn-lg" href="<%= backURL %>" type="cancel" />
	</aui:button-row>
</aui:form>

<aui:script>
	function <portlet:namespace />saveCommerceCountry() {
		submitForm(document.<portlet:namespace />fm);
	}
</aui:script>