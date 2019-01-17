<%--
/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * The contents of this file are subject to the terms of the Liferay Enterprise
 * Subscription License ("License"). You may not use this file except in
 * compliance with the License. You can obtain a copy of the License by
 * contacting Liferay, Inc. See the License for the specific language governing
 * permissions and limitations under the License, including but not limited to
 * distribution rights of the Software.
 *
 *
 *
 */
--%>

<%@ include file="/init.jsp" %>

<%
String redirect = ParamUtil.getString(request, "redirect");

FedExCommerceShippingEngineGroupServiceConfiguration fedExCommerceShippingEngineGroupServiceConfiguration = (FedExCommerceShippingEngineGroupServiceConfiguration)request.getAttribute(FedExCommerceShippingEngineGroupServiceConfiguration.class.getName());

String[] serviceTypes = StringUtil.split(fedExCommerceShippingEngineGroupServiceConfiguration.serviceTypes());
%>

<portlet:actionURL name="editCommerceShippingMethodFedExConfiguration" var="editCommerceShippingMethodConfigurationActionURL" />

<aui:form action="<%= editCommerceShippingMethodConfigurationActionURL %>" cssClass="container-fluid-1280" method="post" name="fm" onSubmit='<%= "event.preventDefault(); " + renderResponse.getNamespace() + "saveCommerceShippingMethodConfiguration();" %>'>
	<aui:input name="redirect" type="hidden" value="<%= currentURL %>" />

	<aui:fieldset-group markupView="lexicon">
		<aui:fieldset>
			<aui:input name="settings--url--" value="<%= fedExCommerceShippingEngineGroupServiceConfiguration.url() %>" />

			<aui:input name="settings--key--" value="<%= fedExCommerceShippingEngineGroupServiceConfiguration.key() %>" />

			<aui:input name="settings--password--" value="<%= fedExCommerceShippingEngineGroupServiceConfiguration.password() %>" />

			<aui:input label="account-number" name="settings--accountNumber--" value="<%= fedExCommerceShippingEngineGroupServiceConfiguration.accountNumber() %>" />

			<aui:input label="meter-number" name="settings--meterNumber--" value="<%= fedExCommerceShippingEngineGroupServiceConfiguration.meterNumber() %>" />

			<aui:select label="dropoff-type" name="settings--dropoffType--">

				<%
				for (String dropoffType : FedExCommerceShippingEngineConstants.DROPOFF_TYPES) {
				%>

					<aui:option label="<%= FedExCommerceShippingEngineConstants.getDropoffTypeLabel(dropoffType) %>" selected="<%= dropoffType.equals(fedExCommerceShippingEngineGroupServiceConfiguration.dropoffType()) %>" value="<%= dropoffType %>" />

				<%
				}
				%>

			</aui:select>

			<aui:input checked="<%= fedExCommerceShippingEngineGroupServiceConfiguration.useResidentialRates() %>" label="use-residential-rates" name="settings--useResidentialRates--" type="checkbox" />

			<aui:input checked="<%= fedExCommerceShippingEngineGroupServiceConfiguration.useDiscountedRates() %>" label="use-discounted-rates" name="settings--useDiscountedRates--" type="checkbox" />

			<aui:field-wrapper label="service-types">

				<%
				for (String serviceType : FedExCommerceShippingEngineConstants.SERVICE_TYPES) {
				%>

					<div>
						<aui:input checked="<%= ArrayUtil.contains(serviceTypes, serviceType) %>" label="<%= FedExCommerceShippingEngineConstants.getServiceTypeLabel(serviceType) %>" name="settings--serviceTypes--" type="checkbox" value="<%= serviceType %>" />
					</div>

				<%
				}
				%>

			</aui:field-wrapper>

			<aui:select label="packing-type" name="settings--packingType--">

				<%
				for (String packingType : FedExCommerceShippingEngineConstants.PACKING_TYPES) {
				%>

					<aui:option label="<%= packingType %>" selected="<%= packingType.equals(fedExCommerceShippingEngineGroupServiceConfiguration.packingType()) %>" value="<%= packingType %>" />

				<%
				}
				%>

			</aui:select>

			<aui:field-wrapper label="max-weight">
				<aui:input label="" name="settings--maxWeightPounds--" suffix="lb" value="<%= fedExCommerceShippingEngineGroupServiceConfiguration.maxWeightPounds() %>" />

				<aui:input label="" name="settings--maxWeightKilograms--" suffix="kg" value="<%= fedExCommerceShippingEngineGroupServiceConfiguration.maxWeightKilograms() %>" />
			</aui:field-wrapper>

			<aui:field-wrapper label="max-size">
				<aui:input label="" name="settings--maxmaxSizeInches--" suffix="in" value="<%= fedExCommerceShippingEngineGroupServiceConfiguration.maxSizeInches() %>" />

				<aui:input label="" name="settings--maxSizeCentimeters--" suffix="cm" value="<%= fedExCommerceShippingEngineGroupServiceConfiguration.maxSizeCentimeters() %>" />
			</aui:field-wrapper>
		</aui:fieldset>
	</aui:fieldset-group>

	<aui:button-row>
		<aui:button cssClass="btn-lg" type="submit" />

		<aui:button cssClass="btn-lg" href="<%= redirect %>" type="cancel" />
	</aui:button-row>
</aui:form>

<aui:script>
	function <portlet:namespace />saveCommerceShippingMethodConfiguration() {
		submitForm(document.<portlet:namespace />fm);
	}
</aui:script>