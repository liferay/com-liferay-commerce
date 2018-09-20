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
CommerceOrganizationDetailDisplayContext commerceOrganizationDetailDisplayContext = (CommerceOrganizationDetailDisplayContext)request.getAttribute(WebKeys.PORTLET_DISPLAY_CONTEXT);

Organization organization = commerceOrganizationDetailDisplayContext.getCurrentOrganization();

String[] organizationTypes = commerceOrganizationDetailDisplayContext.getOrganizationTypes();

String defaultType = StringPool.BLANK;

if ((organizationTypes != null) && (organizationTypes.length > 0)) {
	defaultType = organizationTypes[0];
}

String type = BeanParamUtil.getString(organization, request, "type", defaultType);

long groupId = 0;

if (organization != null) {
	groupId = organization.getGroupId();
}

Address address = commerceOrganizationDetailDisplayContext.getOrganizationPrimaryAddress();

long countryId = address.getCountryId();
long regionId = address.getRegionId();

EmailAddress emailAddress = commerceOrganizationDetailDisplayContext.getOrganizationPrimaryEmailAddress();
%>

<portlet:actionURL name="editOrganization" var="editOrganizationActionURL" />

<aui:form action="<%= editOrganizationActionURL %>" method="post" name="fm">
	<aui:input name="<%= Constants.CMD %>" type="hidden" value="<%= (organization == null) ? Constants.ADD : Constants.UPDATE %>" />
	<aui:input name="redirect" type="hidden" value="<%= currentURL %>" />
	<aui:input name="organizationId" type="hidden" value="<%= organization.getOrganizationId() %>" />

	<liferay-ui:error-marker
		key="<%= WebKeys.ERROR_SECTION %>"
		value="details"
	/>

	<aui:model-context bean="<%= organization %>" model="<%= Organization.class %>" />

	<div class="row">
		<aui:fieldset cssClass="col-md-6">
			<liferay-ui:error exception="<%= DuplicateOrganizationException.class %>" message="the-organization-name-is-already-taken" />

			<liferay-ui:error exception="<%= OrganizationNameException.class %>">
				<liferay-ui:message arguments="<%= new String[] {OrganizationConstants.NAME_LABEL, OrganizationConstants.NAME_GENERAL_RESTRICTIONS, OrganizationConstants.NAME_RESERVED_WORDS} %>" key="the-x-cannot-be-x-or-a-reserved-word-such-as-x" />
			</liferay-ui:error>

			<aui:input autoFocus="<%= windowState.equals(WindowState.MAXIMIZED) %>" name="name" />

			<c:choose>
				<c:when test="<%= PropsValues.FIELD_ENABLE_COM_LIFERAY_PORTAL_KERNEL_MODEL_ORGANIZATION_STATUS %>">
					<liferay-ui:error key="<%= NoSuchListTypeException.class.getName() + Organization.class.getName() + ListTypeConstants.ORGANIZATION_STATUS %>" message="please-select-a-type" />

					<aui:select label="status" listType="<%= ListTypeConstants.ORGANIZATION_STATUS %>" listTypeFieldName="statusId" name="statusId" showEmptyOption="<%= true %>" />
				</c:when>
				<c:otherwise>
					<aui:input name="statusId" type="hidden" value="<%= (organization != null) ? organization.getStatusId() : ListTypeConstants.ORGANIZATION_STATUS_DEFAULT %>" />
				</c:otherwise>
			</c:choose>

			<c:choose>
				<c:when test="<%= (organization == null) && (organizationTypes.length > 1) %>">
					<aui:select name="type">

						<%
						for (String curType : organizationTypes) {
						%>

							<aui:option label="<%= curType %>" selected="<%= type.equals(curType) %>" />

						<%
						}
						%>

					</aui:select>
				</c:when>
				<c:when test="<%= organization == null %>">
					<aui:input name="type" type="hidden" value="<%= defaultType %>" />
				</c:when>
				<c:otherwise>
					<c:if test="<%= organizationTypes.length > 1 %>">
						<aui:input name="typeLabel" type="resource" value="<%= LanguageUtil.get(request, organization.getType()) %>" />
					</c:if>

					<aui:input name="type" type="hidden" value="<%= organization.getType() %>" />
				</c:otherwise>
			</c:choose>

			<aui:model-context bean="<%= emailAddress %>" model="<%= EmailAddress.class %>" />

			<liferay-ui:error exception="<%= EmailAddressException.class %>" message="please-enter-a-valid-email-address" />

			<aui:input name="emailAddressId" type="hidden" value="<%= emailAddress.getEmailAddressId() %>" />

			<aui:input cssClass="email-field" inlineField="<%= true %>" label="email-address" name="address" required="<%= true %>" width="150px" />

			<aui:model-context bean="<%= address %>" model="<%= Address.class %>" />

			<aui:input name="addressId" type="hidden" value="<%= address.getAddressId() %>" />

			<liferay-ui:error exception="<%= AddressStreetException.class %>" message="please-enter-a-valid-street" />

			<aui:input name="street1" required="<%= true %>" />

			<aui:input name="street2" />

			<aui:input name="street3" />

			<liferay-ui:error exception="<%= NoSuchCountryException.class %>" message="please-select-a-country" />

			<aui:select label="country" name="countryId" required="<%= true %>" width="150px" />

			<liferay-ui:error exception="<%= NoSuchRegionException.class %>" message="please-select-a-region" />

			<aui:select label="region" name="regionId" width="150px" />

			<liferay-ui:error exception="<%= AddressZipException.class %>" message="please-enter-a-valid-postal-code" />

			<aui:input label="postal-code" name="zip" required="<%= true %>" width="150px" />

			<liferay-ui:error exception="<%= AddressCityException.class %>" message="please-enter-a-valid-city" />

			<aui:input name="city" required="<%= true %>" />
		</aui:fieldset>

		<aui:fieldset cssClass="col-md-6">
			<div>
				<c:if test="<%= organization != null %>">

					<%
					long logoId = organization.getLogoId();

					UserFileUploadsConfiguration userFileUploadsConfiguration = commerceOrganizationDetailDisplayContext.getUserFileUploadsConfiguration();
					%>

					<liferay-ui:logo-selector
						currentLogoURL='<%= themeDisplay.getPathImage() + "/organization_logo?img_id=" + logoId + "&t=" + WebServerServletTokenUtil.getToken(logoId) %>'
						defaultLogo="<%= logoId == 0 %>"
						defaultLogoURL='<%= themeDisplay.getPathImage() + "/organization_logo?img_id=0" %>'
						logoDisplaySelector=".organization-logo"
						maxFileSize="<%= userFileUploadsConfiguration.imageMaxSize() %>"
						tempImageFileName="<%= String.valueOf(groupId) %>"
					/>
				</c:if>
			</div>
		</aui:fieldset>

		<aui:button-row>
			<aui:button type="submit" />
		</aui:button-row>
	</div>
</aui:form>

<aui:script use="liferay-address,liferay-dynamic-select">
	new Liferay.DynamicSelect(
		[
			{
				select: '<portlet:namespace />countryId',
				selectData: Liferay.Address.getCountries,
				selectDesc: 'nameCurrentValue',
				selectId: 'countryId',
				selectSort: '<%= true %>',
				selectVal: '<%= countryId %>'
			},
			{
				select: '<portlet:namespace />regionId',
				selectData: Liferay.Address.getRegions,
				selectDesc: 'name',
				selectId: 'regionId',
				selectVal: '<%= regionId %>'
			}
		]
	);
</aui:script>