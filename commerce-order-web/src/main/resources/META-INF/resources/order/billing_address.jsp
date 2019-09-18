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

CommerceAddress commerceAddress = commerceOrder.getBillingAddress();

long commerceCountryId = BeanParamUtil.getLong(commerceAddress, request, "commerceCountryId", 0);
long commerceRegionId = BeanParamUtil.getLong(commerceAddress, request, "commerceRegionId", 0);
%>

<portlet:actionURL name="editCommerceOrder" var="editCommerceOrderBillingAddressActionURL" />

<aui:form action="<%= editCommerceOrderBillingAddressActionURL %>" cssClass="container-fluid-1280 p-0" method="post" name="fm">
	<aui:input name="<%= Constants.CMD %>" type="hidden" value="billingAddress" />
	<aui:input name="redirect" type="hidden" value="<%= currentURL %>" />
	<aui:input name="commerceOrderId" type="hidden" value="<%= commerceOrder.getCommerceOrderId() %>" />

	<aui:model-context bean="<%= commerceAddress %>" model="<%= CommerceAddress.class %>" />

	<div class="address-fields border-0 sheet">
		<aui:input name="name" wrapperCssClass="form-group-item" />

		<aui:input name="phoneNumber" wrapperCssClass="form-group-item" />

		<aui:input name="street1" wrapperCssClass="form-group-item" />

		<aui:select label="country" name="commerceCountryId" wrapperCssClass="form-group-item" />

		<aui:input name="zip" wrapperCssClass="form-group-item" />

		<aui:input name="city" wrapperCssClass="form-group-item" />

		<aui:select label="region" name="commerceRegionId" wrapperCssClass="form-group-item" />
	</div>

	<aui:button type="submit" />
</aui:form>

<aui:script use="liferay-dynamic-select">
	new Liferay.DynamicSelect(
			[
				{
					select: '<portlet:namespace />commerceCountryId',
					selectData: function(callback) {
						function injectCountryPlaceholder(list) {
							callback([
								{
									commerceCountryId: '0',
									nameCurrentValue: '- <liferay-ui:message key="select-country" />'
								},
								...list
							]);
						}

						Liferay.Service(
							'/commerce.commercecountry/get-billing-commerce-countries',
							{
								companyId: <%= company.getCompanyId() %>,
								billingAllowed: true,
								active: true
							},
							injectCountryPlaceholder
						);
					},
					selectDesc: 'nameCurrentValue',
					selectId: 'commerceCountryId',
					selectNullable: <%= false %>,
					selectSort: '<%= true %>',
					selectVal: '<%= commerceCountryId %>'
				},
				{
					select: '<portlet:namespace />commerceRegionId',
					selectData: function(callback, selectKey) {
						function injectRegionPlaceholder(list) {
							callback([
								{
									commerceRegionId: '0',
									name: '- <liferay-ui:message key="select-region" />'
								},
								...list
							]);
						}

						Liferay.Service(
								'/commerce.commerceregion/get-commerce-regions',
								{
									commerceCountryId: Number(selectKey),
									active: true
								},
								injectRegionPlaceholder
						);
					},
					selectDesc: 'name',
					selectId: 'commerceRegionId',
					selectNullable: <%= false %>,
					selectVal: '<%= commerceRegionId %>'
				}
			]
	);

</aui:script>