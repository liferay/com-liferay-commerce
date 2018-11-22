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
CommerceShippingSettingsDisplayContext commerceShippingSettingsDisplayContext = (CommerceShippingSettingsDisplayContext)request.getAttribute(WebKeys.PORTLET_DISPLAY_CONTEXT);

CommerceShippingGroupServiceConfiguration commerceShippingGroupServiceConfiguration = commerceShippingSettingsDisplayContext.getCommerceShippingGroupServiceConfiguration();
Map<String, CommerceShippingOriginLocator> commerceShippingOriginLocators = commerceShippingSettingsDisplayContext.getCommerceShippingOriginLocators();
%>

<c:if test="<%= commerceShippingSettingsDisplayContext.hasManageCommerceWarehousesPermission() %>">
	<div class="container-fluid-1280 mt-4 sheet">
		<portlet:actionURL name="editCommerceShippingSettings" var="editCommerceShippingSettingsActionURL" />

		<%
		for (Map.Entry<String, CommerceShippingOriginLocator> entry : commerceShippingOriginLocators.entrySet()) {
			boolean checked = false;

			String key = entry.getKey();
			CommerceShippingOriginLocator commerceShippingOriginLocator = entry.getValue();

			if (key.equals(commerceShippingGroupServiceConfiguration.commerceShippingOriginLocatorKey())) {
				checked = true;
			}
		%>

			<aui:input checked="<%= checked %>" helpMessage="<%= commerceShippingOriginLocator.getDescription(locale) %>" id='<%= key + "Origin" %>' label="<%= commerceShippingOriginLocator.getName(locale) %>" name="commerceShippingOriginLocatorKeySelector" type="radio" value="<%= key %>" />

			<aui:form action="<%= editCommerceShippingSettingsActionURL %>" method="post" name='<%= key + "Fm" %>'>
				<aui:input name="redirect" type="hidden" value="<%= currentURL %>" />

				<liferay-ui:error exception="<%= CommerceWarehouseActiveException.class %>" message="please-geolocate-warehouse-to-activate" />
				<liferay-ui:error exception="<%= CommerceWarehouseNameException.class %>" message="please-enter-a-valid-name" />

				<div class="<%= checked ? StringPool.BLANK : "hide" %>" id="<portlet:namespace /><%= key %>OriginOptions">
					<aui:fieldset>
						<aui:input name="commerceShippingOriginLocatorKey" type="hidden" value="<%= key %>" />

						<%
						commerceShippingOriginLocator.renderConfiguration(renderRequest, renderResponse);
						%>

					</aui:fieldset>

					<aui:button-row>
						<aui:button cssClass="btn-lg" type="submit" value="save" />
					</aui:button-row>
				</div>
			</aui:form>

		<%
		}
		%>

	</div>

	<aui:script>

		<%
		for (String key : commerceShippingOriginLocators.keySet()) {
		%>

			Liferay.Util.toggleRadio('<portlet:namespace /><%= key %>Origin', '<portlet:namespace /><%= key %>OriginOptions', <%= commerceShippingSettingsDisplayContext.getCommerceShippingOriginLocatorHideBoxIds(key) %>);

		<%
		}
		%>

	</aui:script>
</c:if>