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
CommerceAccountDisplayContext commerceAccountDisplayContext = (CommerceAccountDisplayContext)request.getAttribute(WebKeys.PORTLET_DISPLAY_CONTEXT);

CommerceAccount commerceAccount = commerceAccountDisplayContext.getCurrentCommerceAccount();
CommerceAddress commerceAddress = commerceAccountDisplayContext.getDefaultBillingCommerceAddress();

long commerceCountryId = 0;
long commerceRegionId = 0;

if (commerceAddress != null) {
	commerceCountryId = commerceAddress.getCommerceCountryId();
	commerceRegionId = commerceAddress.getCommerceRegionId();
}

String redirect = ParamUtil.getString(request, "redirect");

String backURL = ParamUtil.getString(request, "backURL", redirect);
%>

<portlet:actionURL name="editCommerceAccount" var="editCommerceAccountActionURL" />

<aui:form action="<%= editCommerceAccountActionURL %>" method="post" name="fm">
	<aui:input name="<%= Constants.CMD %>" type="hidden" value="<%= (commerceAccount == null) ? Constants.ADD : Constants.UPDATE %>" />
	<aui:input name="redirect" type="hidden" value="<%= currentURL %>" />
	<aui:input name="commerceAccountId" type="hidden" value="<%= (commerceAccount == null) ? 0 : commerceAccount.getCommerceAccountId() %>" />
	<aui:input name="commerceAddressId" type="hidden" value="<%= (commerceAddress == null) ? 0 : commerceAddress.getCommerceAddressId() %>" />

	<liferay-ui:error-marker
		key="<%= WebKeys.ERROR_SECTION %>"
		value="details"
	/>

	<aui:model-context bean="<%= commerceAccount %>" model="<%= CommerceAccount.class %>" />

	<section class="details-header__section">
		<div class="row">
			<div class="col-lg-4 u-vac">
				<aui:fieldset>
					<c:if test="<%= commerceAccount != null %>">

						<%
						long logoId = commerceAccount.getLogoId();

						UserFileUploadsConfiguration userFileUploadsConfiguration = commerceAccountDisplayContext.getUserFileUploadsConfiguration();
						%>

						<liferay-ui:logo-selector
							currentLogoURL='<%= themeDisplay.getPathImage() + "/organization_logo?img_id=" + logoId + "&t=" + WebServerServletTokenUtil.getToken(logoId) %>'
							defaultLogo="<%= logoId == 0 %>"
							defaultLogoURL='<%= themeDisplay.getPathImage() + "/organization_logo?img_id=0" %>'
							logoDisplaySelector=".organization-logo"
							maxFileSize="<%= userFileUploadsConfiguration.imageMaxSize() %>"
							tempImageFileName="<%= String.valueOf(themeDisplay.getScopeGroupId()) %>"
						/>
					</c:if>
				</aui:fieldset>
			</div>

			<div class="col-lg-4 mt-4 mt-lg-0 u-vac">
				<aui:input inlineLabel="true" name="name" />

				<aui:input inlineLabel="true" name="email" wrapperCssClass="mb-0" />
			</div>
		</div>
	</section>

	<section class="details-header__section pb-0">
		<aui:model-context bean="<%= commerceAddress %>" model="<%= CommerceAddress.class %>" />

		<div class="row">
			<div class="col-lg-4">
				<aui:select inlineLabel="true" label="country" name="commerceCountryId" showEmptyOption="<%= true %>">

					<%
					List<CommerceCountry> commerceCountries = commerceAccountDisplayContext.getCommerceCountries();

					for (CommerceCountry commerceCountry : commerceCountries) {
					%>

						<aui:option label="<%= commerceCountry.getName(locale) %>" selected="<%= (commerceAddress != null) && (commerceAddress.getCommerceCountryId() == commerceCountry.getCommerceCountryId()) %>" value="<%= commerceCountry.getCommerceCountryId() %>" />

					<%
					}
					%>

				</aui:select>

			</div>

			<div class="col-lg-4">
				<aui:select inlineLabel="true" label="region" name="commerceRegionId" showEmptyOption="<%= true %>">

					<%
					List<CommerceRegion> commerceRegions = commerceAccountDisplayContext.getCommerceRegions(commerceCountryId);

					for (CommerceRegion commerceRegion : commerceRegions) {
					%>

						<aui:option label="<%= commerceRegion.getName() %>" selected="<%= (commerceAddress != null) && (commerceAddress.getCommerceRegionId() == commerceRegion.getCommerceRegionId()) %>" value="<%= commerceRegion.getCommerceRegionId() %>" />

					<%
					}
					%>

				</aui:select>
			</div>

			<div class="col-lg-4">
				<aui:input inlineLabel="true" label="address" name="street1" />
			</div>

			<div class="col-lg-4">
				<aui:input inlineLabel="true" name="zip" />
			</div>

			<div class="col-lg-4">
				<aui:input inlineLabel="true" name="city" />
			</div>
		</div>
	</section>

	<section class="details-header__section pb-0">
		<aui:model-context bean="<%= commerceAccount %>" model="<%= CommerceAccount.class %>" />

		<div class="row">
			<div class="col-lg-4">
				<aui:input inlineLabel="true" label="vat-number" name="taxId" />
			</div>
		</div>
	</section>

	<div class="minium-frame__cta is-visible">
		<c:if test="<%= Validator.isNotNull(backURL) %>">
			<aui:button cssClass="minium-button minium-button--big minium-button--outline" href="<%= backURL %>" value="cancel" />
		</c:if>

		<aui:button cssClass="minium-button minium-button--big" type="submit" />
	</div>
</aui:form>

<aui:script use="liferay-dynamic-select">
	new Liferay.DynamicSelect(
		[
			{
				select: '<portlet:namespace />commerceCountryId',
				selectData: function(callback) {
					Liferay.Service(
						'/commerce.commercecountry/get-commerce-countries',
						{
							groupId: <%= scopeGroupId %>,
							active: true
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