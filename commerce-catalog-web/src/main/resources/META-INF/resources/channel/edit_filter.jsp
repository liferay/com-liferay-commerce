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
String channelNavigationItem = ParamUtil.getString(request, "channelNavigationItem", "view-filters");

CPCatalogRuleDisplayContext cpCatalogRuleDisplayContext = (CPCatalogRuleDisplayContext)request.getAttribute(WebKeys.PORTLET_DISPLAY_CONTEXT);

CPRule cpRule = cpCatalogRuleDisplayContext.getCPRule();
long cpRuleId = cpCatalogRuleDisplayContext.getCPRuleId();
List<CPRuleType> cpRuleTypes = cpCatalogRuleDisplayContext.getCPRuleTypes();

long commerceChannelId = ParamUtil.getLong(request, "commerceChannelId");

String name = BeanParamUtil.getString(cpRule, request, "name");
String type = BeanParamUtil.getString(cpRule, request, "type");
%>

<%@ include file="/channel/navbar_definitions.jspf" %>

<portlet:actionURL name="editCommerceChannelFilter" var="editCommerceChannelActionURL" />

<aui:form action="<%= editCommerceChannelActionURL %>" cssClass="container-fluid-1280" method="post" name="fm">
	<aui:input name="<%= Constants.CMD %>" type="hidden" value="<%= Constants.UPDATE %>" />
	<aui:input name="commerceChannelId" type="hidden" value="<%= commerceChannelId %>" />
	<aui:input name="cpRuleId" type="hidden" value="<%= cpRuleId %>" />

	<div class="lfr-form-content">
		<aui:fieldset-group markupView="lexicon">
			<aui:fieldset>
				<aui:input autoFocus="<%= true %>" name="name" required="<%= true %>" value="<%= name %>" />

				<aui:select name="type" onChange='<%= renderResponse.getNamespace() + "selectType();" %>' showEmptyOption="<%= false %>">

					<%
					for (CPRuleType cpRuleType : cpRuleTypes) {
						String cpRuleTypeKey = cpRuleType.getKey();
					%>

						<aui:option label="<%= cpRuleType.getLabel(locale) %>" selected="<%= (cpRule != null) && cpRuleTypeKey.equals(type) %>" value="<%= cpRuleTypeKey %>" />

					<%
					}
					%>

				</aui:select>

				<%
				CPRuleTypeJSPContributor cpRuleTypeJSPContributor = cpCatalogRuleDisplayContext.getCPRuleTypeJSPContributor(type);
				%>

				<c:if test="<%= cpRuleTypeJSPContributor != null %>">

					<%
					cpRuleTypeJSPContributor.render(cpRuleId, request, response);
					%>

				</c:if>

				<liferay-ui:error-marker
					key="<%= WebKeys.ERROR_SECTION %>"
					value="custom-fields"
				/>

				<liferay-expando:custom-attribute-list
					className="<%= CPRule.class.getName() %>"
					classPK="<%= cpRuleId %>"
					editable="<%= true %>"
					label="<%= true %>"
				/>
			</aui:fieldset>
		</aui:fieldset-group>
	</div>

	<aui:button-row>
		<aui:button cssClass="btn-lg" type="submit" />
	</aui:button-row>
</aui:form>

<aui:script>
	Liferay.provide(
		window,
		'<portlet:namespace />selectType',
		function() {
			var A = AUI();

			var name = A.one('#<portlet:namespace />name').val();
			var type = A.one('#<portlet:namespace />type').val();

			var portletURL = new Liferay.PortletURL.createURL('<%= currentURLObj %>');

			portletURL.setParameter('name', name);
			portletURL.setParameter('type', type);

			window.location.replace(portletURL.toString());
		},
		['liferay-portlet-url']
	);
</aui:script>