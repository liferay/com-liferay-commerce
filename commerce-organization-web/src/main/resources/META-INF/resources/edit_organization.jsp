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
CommerceOrganizationDisplayContext commerceOrganizationDisplayContext = (CommerceOrganizationDisplayContext)request.getAttribute(WebKeys.PORTLET_DISPLAY_CONTEXT);

Organization organization = commerceOrganizationDisplayContext.getOrganization();
%>

<portlet:actionURL name="editCommerceOrganization" var="editCommerceOrganizationActionURL" />

<div class="account-management">
	<aui:form action="<%= editCommerceOrganizationActionURL %>" method="post" name="fm">
		<aui:input name="<%= Constants.CMD %>" type="hidden" value="<%= (organization == null) ? Constants.ADD : Constants.UPDATE %>" />
		<aui:input name="redirect" type="hidden" value="<%= currentURL %>" />
		<aui:input name="organizationId" type="hidden" value="<%= (organization == null) ? 0 : organization.getOrganizationId() %>" />

		<liferay-ui:error-marker
			key="<%= WebKeys.ERROR_SECTION %>"
			value="details"
		/>

		<aui:model-context bean="<%= organization %>" model="<%= Organization.class %>" />

		<div class="container row">
			<div class="col-lg-4 account-management__thumbnail-container">
				<aui:fieldset>
					<c:if test="<%= organization != null %>">

						<%
						long logoId = organization.getLogoId();

						UserFileUploadsConfiguration userFileUploadsConfiguration = commerceOrganizationDisplayContext.getUserFileUploadsConfiguration();
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

			<div class="col-lg-4">
				<aui:input name="name" />
			</div>
		</div>

		<div class="commerce-cta is-visible">
			<c:if test="<%= Validator.isNotNull(backURL) %>">
				<aui:button cssClass="commerce-button commerce-button--big commerce-button--outline" href="<%= backURL %>" value="cancel" />
			</c:if>

			<aui:button cssClass="commerce-button commerce-button--big" type="submit" />
		</div>
	</aui:form>
</div>