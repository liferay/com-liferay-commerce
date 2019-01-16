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
CommerceContext commerceContext = (CommerceContext)request.getAttribute(CommerceWebKeys.COMMERCE_CONTEXT);
%>

<div class="container-fluid-1280 mt-4 sheet">
	<portlet:actionURL name="editCommerceAccountSettings" var="editCommerceAccountSettingsActionURL" />

	<aui:form action="<%= editCommerceAccountSettingsActionURL %>" method="post" name="fm">
		<aui:input name="redirect" type="hidden" value="<%= currentURL %>" />

		<aui:fieldset>
			<aui:select label="commerce-site-type" name="settings--commerceSiteType--">

				<%
				for (int commerceSiteType : CommerceAccountConstants.SITE_TYPES) {
				%>

					<aui:option label="<%= StringUtil.toUpperCase(CommerceAccountConstants.getSiteTypeLabel(commerceSiteType)) %>" selected="<%= (commerceSiteType == commerceContext.getCommerceSiteType()) %>" value="<%= commerceSiteType %>" />

				<%
				}
				%>

			</aui:select>
		</aui:fieldset>

		<aui:button-row>
			<aui:button cssClass="btn-lg" type="submit" value="save" />
		</aui:button-row>
	</aui:form>
</div>