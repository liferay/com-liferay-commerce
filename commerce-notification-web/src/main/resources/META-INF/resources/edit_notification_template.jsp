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
String redirect = ParamUtil.getString(request, "redirect");

ServletContext commerceAdminServletContext = (ServletContext)request.getAttribute(CommerceAdminWebKeys.COMMERCE_ADMIN_SERVLET_CONTEXT);

CommerceNotificationTemplatesDisplayContext commerceNotificationTemplatesDisplayContext = (CommerceNotificationTemplatesDisplayContext)request.getAttribute(WebKeys.PORTLET_DISPLAY_CONTEXT);

CommerceNotificationTemplate commerceNotificationTemplate = commerceNotificationTemplatesDisplayContext.getCommerceNotificationTemplate();

String title = LanguageUtil.get(resourceBundle, "add-notification-template");

if (commerceNotificationTemplate != null) {
	title = LanguageUtil.format(request, "edit-x", commerceNotificationTemplate.getName(), false);
}

Map<String, Object> data = new HashMap<>();

data.put("direction-right", StringPool.TRUE);

PortalUtil.addPortletBreadcrumbEntry(request, LanguageUtil.get(resourceBundle, commerceAdminModuleKey), redirect, data);
PortalUtil.addPortletBreadcrumbEntry(request, title, StringPool.BLANK, data);
%>

<liferay-util:include page="/navbar.jsp" servletContext="<%= commerceAdminServletContext %>">
	<liferay-util:param name="commerceAdminModuleKey" value="<%= commerceAdminModuleKey %>" />
</liferay-util:include>

<%@ include file="/breadcrumb.jspf" %>

<portlet:actionURL name="editCommerceNotificationTemplate" var="editCommerceNotificationTemplateActionURL" />

<aui:form action="<%= editCommerceNotificationTemplateActionURL %>" cssClass="container-fluid-1280" method="post" name="fm" onSubmit='<%= "event.preventDefault(); " + renderResponse.getNamespace() + "saveCommerceNotificationTemplate();" %>'>
	<aui:input name="<%= Constants.CMD %>" type="hidden" value="<%= (commerceNotificationTemplate == null) ? Constants.ADD : Constants.UPDATE %>" />
	<aui:input name="redirect" type="hidden" value="<%= redirect %>" />
	<aui:input name="addCommerceAccountGroupIds" type="hidden" value="" />
	<aui:input name="commerceNotificationTemplateId" type="hidden" value="<%= (commerceNotificationTemplate == null) ? 0 : commerceNotificationTemplate.getCommerceNotificationTemplateId() %>" />
	<aui:input name="deleteCommerceNotificationTemplateCommerceAccountGroupRelIds" type="hidden" value="" />

	<div class="col-md-8 offset-md-2">
		<liferay-ui:form-navigator
			backURL="<%= redirect %>"
			formModelBean="<%= commerceNotificationTemplate %>"
			id="<%= CommerceNotificationTemplateFormNavigatorConstants.FORM_NAVIGATOR_ID_COMMERCE_NOTIFICATION_TEMPLATE %>"
			markupView="lexicon"
		/>
	</div>
</aui:form>

<aui:script>
	function <portlet:namespace />saveCommerceNotificationTemplate() {
		submitForm(document.<portlet:namespace />fm);
	}

	Liferay.provide(
	window,
	'<portlet:namespace />selectType',
		function() {
			var A = AUI();

			var name = A.one('#<portlet:namespace />name').val();
			var description = A.one('#<portlet:namespace />description').val();
			var from = A.one('#<portlet:namespace />from').val();
			var fromName = A.one('#<portlet:namespace />fromName').val();
			var cc = A.one('#<portlet:namespace />cc').val();
			var bcc = A.one('#<portlet:namespace />bcc').val();
			var type = A.one('#<portlet:namespace />type').val();

			var portletURL = new Liferay.PortletURL.createURL('<%= currentURLObj %>');

			portletURL.setParameter('name', name);
			portletURL.setParameter('description', description);
			portletURL.setParameter('from', from);
			portletURL.setParameter('fromName', fromName);
			portletURL.setParameter('cc', cc);
			portletURL.setParameter('bcc', bcc);
			portletURL.setParameter('type', type);

			window.location.replace(portletURL.toString());
		},
		['liferay-portlet-url']
	);
</aui:script>