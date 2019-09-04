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
CommerceContext commerceContext = (CommerceContext)request.getAttribute(CommerceWebKeys.COMMERCE_CONTEXT);

CommerceShipmentItemDisplayContext commerceShipmentItemDisplayContext = (CommerceShipmentItemDisplayContext)request.getAttribute(WebKeys.PORTLET_DISPLAY_CONTEXT);

CommerceShipment commerceShipment = commerceShipmentItemDisplayContext.getCommerceShipment();
long commerceShipmentId = commerceShipmentItemDisplayContext.getCommerceShipmentId();

long commerceCountryId = 0;
long commerceRegionId = 0;

CommerceAddress commerceAddress = commerceShipment.fetchCommerceAddress();

if (commerceAddress != null) {
	commerceCountryId = commerceAddress.getCommerceCountryId();
	commerceRegionId = commerceAddress.getCommerceRegionId();
}
%>

<portlet:actionURL name="editCommerceShipment" var="editCommerceShipmentActionURL" />

<aui:form action="<%= editCommerceShipmentActionURL %>" cssClass="container-fluid-1280" method="post" name="fm">
	<aui:input name="<%= Constants.CMD %>" type="hidden" value="<%= Constants.UPDATE %>" />
	<aui:input name="redirect" type="hidden" value="<%= currentURL %>" />
	<aui:input name="commerceShipmentId" type="hidden" value="<%= String.valueOf(commerceShipmentId) %>" />

	<aui:model-context bean="<%= commerceAddress %>" model="<%= CommerceAddress.class %>" />

	<liferay-ui:error exception="<%= CommerceAddressCityException.class %>" message="please-enter-a-valid-city" />
	<liferay-ui:error exception="<%= CommerceAddressCountryException.class %>" message="please-select-a-country" />
	<liferay-ui:error exception="<%= CommerceAddressStreetException.class %>" message="please-enter-a-valid-street" />
	<liferay-ui:error exception="<%= CommerceAddressZipException.class %>" message="please-enter-a-valid-zip" />
	<liferay-ui:error exception="<%= CommerceShipmentStatusException.class %>" message="please-select-a-valid-status" />

	<aui:fieldset-group markupView="lexicon">
		<aui:fieldset>
			<aui:row>
				<aui:col width="<%= 50 %>">
					<aui:input name="name" />

					<aui:input name="description" />

					<aui:input name="street1" />

					<aui:input name="street2" />

					<aui:input name="street3" />
				</aui:col>

				<aui:col width="<%= 50 %>">
					<aui:input name="city" />

					<aui:input label="postal-code" name="zip" />

					<aui:select label="country" name="commerceCountryId" showEmptyOption="<%= true %>">

						<%
						List<CommerceCountry> commerceCountries = commerceShipmentItemDisplayContext.getCommerceCountries();

						for (CommerceCountry commerceCountry : commerceCountries) {
						%>

							<aui:option label="<%= commerceCountry.getName(locale) %>" selected="<%= (commerceAddress != null) && (commerceAddress.getCommerceCountryId() == commerceCountry.getCommerceCountryId()) %>" value="<%= commerceCountry.getCommerceCountryId() %>" />

						<%
						}
						%>

					</aui:select>

					<aui:select label="region" name="commerceRegionId" showEmptyOption="<%= true %>">

						<%
						List<CommerceRegion> commerceRegions = commerceShipmentItemDisplayContext.getCommerceRegions();

						for (CommerceRegion commerceRegion : commerceRegions) {
						%>

							<aui:option label="<%= commerceRegion.getName() %>" selected="<%= (commerceAddress != null) && (commerceAddress.getCommerceRegionId() == commerceRegion.getCommerceRegionId()) %>" value="<%= commerceRegion.getCommerceRegionId() %>" />

						<%
						}
						%>

					</aui:select>

					<aui:input name="phoneNumber" />
				</aui:col>
			</aui:row>
		</aui:fieldset>

		<aui:model-context bean="<%= commerceShipment %>" model="<%= CommerceShipment.class %>" />

		<aui:fieldset>
			<aui:input name="carrier" />

			<aui:input name="trackingNumber" />

			<aui:select name="status">

				<%
				for (int status : CommerceShipmentConstants.SHIPMENT_STATUSES) {
				%>

					<aui:option label="<%= CommerceShipmentConstants.getShipmentStatusLabel(status) %>" selected="<%= status == commerceShipment.getStatus() %>" value="<%= status %>" />

				<%
				}
				%>

			</aui:select>

			<aui:input name="shippingDate" />

			<aui:input label="expected-delivery-date" name="expectedDate" />
		</aui:fieldset>
	</aui:fieldset-group>

	<aui:button-row>
		<aui:button cssClass="btn-lg" name="saveButton" type="submit" value="save" />

		<aui:button cssClass="btn-lg" href="<%= shipmentsURL %>" type="cancel" />
	</aui:button-row>
</aui:form>

<aui:script use="aui-base,liferay-dynamic-select">
	new Liferay.DynamicSelect(
		[
			{
				select: '<portlet:namespace />commerceCountryId',
				selectData: function(callback) {
					Liferay.Service(
						'/commerce.commercecountry/get-shipping-commerce-countries-by-channel-id',
						{
							commerceChannelId: <%= commerceContext.getCommerceChannelId() %>,
							start: -1,
							end: -1
						},
						callback
					);
				},
				selectDesc: 'nameCurrentValue',
				selectId: 'commerceCountryId',
				selectSort: '<%= true %>',
				selectVal: '<%= commerceCountryId %>'
			},
			{
				select: '<portlet:namespace />commerceRegionId',
				selectData: function(callback, selectKey) {
					Liferay.Service(
						'/commerce.commerceregion/get-commerce-regions',
						{
							commerceCountryId: Number(selectKey),
							active: true
						},
						callback
					);
				},
				selectDesc: 'name',
				selectId: 'commerceRegionId',
				selectVal: '<%= commerceRegionId %>'
			}
		]
	);
</aui:script>