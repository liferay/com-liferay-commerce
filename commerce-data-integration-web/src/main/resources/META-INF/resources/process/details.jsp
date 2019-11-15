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
CommerceDataIntegrationProcessDisplayContext commerceDataIntegrationProcessDisplayContext = (CommerceDataIntegrationProcessDisplayContext)request.getAttribute(WebKeys.PORTLET_DISPLAY_CONTEXT);

long commerceDataIntegrationProcessId = 0;

CommerceDataIntegrationProcess commerceDataIntegrationProcess = commerceDataIntegrationProcessDisplayContext.getCommerceDataIntegrationProcess();

String processType = ParamUtil.getString(request, "processType");

String typeSettings = StringPool.BLANK;

if (commerceDataIntegrationProcess != null) {
	commerceDataIntegrationProcessId = commerceDataIntegrationProcess.getCommerceDataIntegrationProcessId();
	processType = commerceDataIntegrationProcess.getType();
	typeSettings = commerceDataIntegrationProcess.getTypeSettings();
}
%>

<portlet:actionURL name="editCommerceDataIntegrationProcess" var="editCommerceDataIntegrationProcessActionURL" />

<div class="closed container-fluid-1280" id="<portlet:namespace />editCommerceDataIntegrationProcessId">
	<div class="container main-content-body sheet">
		<liferay-ui:error exception="<%= NoSuchDataIntegrationProcessException.class %>" message="the-process-could-not-be-found" />
		<liferay-ui:error exception="<%= NoSuchDataIntegrationProcessLogException.class %>" message="the-log-could-not-be-found" />

		<liferay-ui:error-principal />

		<aui:form action="<%= editCommerceDataIntegrationProcessActionURL %>" cssClass="container-fluid-1280" method="post" name="fm">
			<aui:input name="<%= Constants.CMD %>" type="hidden" value="<%= Constants.UPDATE %>" />
			<aui:input name="redirect" type="hidden" value="<%= redirect %>" />
			<aui:input name="commerceDataIntegrationProcessId" type="hidden" value="<%= String.valueOf(commerceDataIntegrationProcessId) %>" />
			<aui:input name="processType" type="hidden" value="<%= processType %>" />
			<aui:input name="typeSettings" type="hidden" />

			<div class="lfr-form-content">
				<aui:model-context bean="<%= commerceDataIntegrationProcess %>" model="<%= CommerceDataIntegrationProcess.class %>" />

				<aui:fieldset>
					<aui:input disabled="<%= (commerceDataIntegrationProcess != null) && commerceDataIntegrationProcess.isSystem() %>" name="name" required="<%= true %>" />
				</aui:fieldset>

				<div id="<portlet:namespace />typeSettingsEditor"></div>

				<aui:button-row>

					<%
					String taglibSaveOnClick = "Liferay.fire('" + liferayPortletResponse.getNamespace() + "saveProcess');";
					%>

					<aui:button onClick="<%= taglibSaveOnClick %>" value="save" />

					<aui:button cssClass="btn-lg" href="<%= backURL %>" type="cancel" />
				</aui:button-row>
			</div>
		</aui:form>
	</div>
</div>

<aui:script>
	Liferay.provide(
		window,
		'<portlet:namespace />selectType',
		function() {
			var A = AUI();

			var processType = A.one(<portlet:namespace />type).val();

			var portletURL = new Liferay.PortletURL.createURL('<%= currentURLObj %>');

			portletURL.setParameter("type", processType);

			window.location.replace(portletURL.toString());
		},
		['liferay-portlet-url']
	);
</aui:script>

<aui:script use="aui-ace-editor,liferay-xml-formatter">
	var STR_VALUE = 'value';

	var contentEditor = new A.AceEditor(
		{
			boundingBox: '#<portlet:namespace />typeSettingsEditor',
			height: 600,
			mode: 'xml',
			tabSize: 4,
			width: '100%'
		}
	).render();

	var xmlFormatter = new Liferay.XMLFormatter();

	var content = xmlFormatter.format('<%= HtmlUtil.escapeJS(typeSettings) %>');

	if (content) {
		content = content.trim();
	}

	contentEditor.set(STR_VALUE, content);

	Liferay.on(
		'<portlet:namespace />saveProcess',
		function(event) {
			var form = AUI.$('#<portlet:namespace />fm');

			form.fm('typeSettings').val(contentEditor.get(STR_VALUE));

			submitForm(form);
		}
	);
</aui:script>