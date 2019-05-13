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
String channelNavigationItem = ParamUtil.getString(request, "channelNavigationItem", "view-details");

CommerceChannelDisplayContext commerceChannelDisplayContext = (CommerceChannelDisplayContext)request.getAttribute(WebKeys.PORTLET_DISPLAY_CONTEXT);

CommerceChannel commerceChannel = commerceChannelDisplayContext.getCommerceChannel();

long commerceChannelId = commerceChannelDisplayContext.getCommerceChannelId();
List<CommerceChannelType> commerceChannelTypes = commerceChannelDisplayContext.getCommerceChannelTypes();

String name = BeanParamUtil.getString(commerceChannel, request, "name");
String filterType = BeanParamUtil.getString(commerceChannel, request, "filterType");
String type = BeanParamUtil.getString(commerceChannel, request, "type");

String title = LanguageUtil.get(request, "add-channel");

if (commerceChannel != null) {
	title = HtmlUtil.escape(commerceChannel.getName());
}

Map<String, Object> data = new HashMap<>();

data.put("direction-right", StringPool.TRUE);

PortletURL editChannelURL = renderResponse.createRenderURL();

editChannelURL.setParameter("commerceChannelId", String.valueOf(commerceChannelId));
editChannelURL.setParameter("mvcRenderCommandName", "editCommerceChannel");

PortalUtil.addPortletBreadcrumbEntry(request, LanguageUtil.get(request, "channels"), String.valueOf(renderResponse.createRenderURL()), data);
PortalUtil.addPortletBreadcrumbEntry(request, title, editChannelURL.toString(), data);
%>

<%@ include file="/navbar_definitions.jspf" %>

<portlet:actionURL name="editCommerceChannel" var="editCommerceChannelActionURL" />

<aui:form action="<%= editCommerceChannelActionURL %>" cssClass="container-fluid-1280" method="post" name="fm">
	<aui:input name="<%= Constants.CMD %>" type="hidden" value="<%= (commerceChannel == null) ? Constants.ADD : Constants.UPDATE %>" />
	<aui:input name="addTypeSettings" type="hidden" />
	<aui:input name="commerceChannelId" type="hidden" value="<%= commerceChannelId %>" />
	<aui:input name="deleteTypeSettings" type="hidden" />

	<div class="lfr-form-content">
		<aui:model-context bean="<%= commerceChannel %>" model="<%= CommerceChannel.class %>" />

		<aui:fieldset-group markupView="lexicon">
			<aui:fieldset>
				<aui:input autoFocus="<%= true %>" name="name" value="<%= name %>" />

				<aui:input checked='<%= filterType.equals("orSearch") %>' label="search-type" labelOff="match-all-filters" labelOn="match-any-filter" name="filterType" type="toggle-switch" />

				<aui:select name="type" onChange='<%= renderResponse.getNamespace() + "selectType();" %>' showEmptyOption="<%= true %>">

					<%
					for (CommerceChannelType commerceChannelType : commerceChannelTypes) {
						String commerceChannelTypeKey = commerceChannelType.getKey();
					%>

						<aui:option label="<%= commerceChannelType.getLabel(locale) %>" selected="<%= (commerceChannel != null) && commerceChannelTypeKey.equals(type) %>" value="<%= commerceChannelTypeKey %>" />

					<%
					}
					%>

				</aui:select>

				<%
				CommerceChannelTypeJSPContributor commerceChannelTypeJSPContributor = commerceChannelDisplayContext.getCommerceChannelTypeJSPContributor(type);
				%>

				<c:if test="<%= commerceChannelTypeJSPContributor != null %>">

					<%
					commerceChannelTypeJSPContributor.render(commerceChannelId, request, response);
					%>

				</c:if>

				<liferay-ui:error-marker
					key="<%= WebKeys.ERROR_SECTION %>"
					value="custom-fields"
				/>

				<liferay-expando:custom-attribute-list
					className="<%= CommerceChannel.class.getName() %>"
					classPK="<%= commerceChannelId %>"
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
			var filterType = A.one('#<portlet:namespace />filterType').get('checked') ? 'orSearch' : '';
			var type = A.one('#<portlet:namespace />type').val();

			var portletURL = new Liferay.PortletURL.createURL('<%= currentURLObj %>');

			portletURL.setParameter('name', name);
			portletURL.setParameter('filterType', filterType);
			portletURL.setParameter('type', type);

			window.location.replace(portletURL.toString());
		},
		['liferay-portlet-url']
	);
</aui:script>