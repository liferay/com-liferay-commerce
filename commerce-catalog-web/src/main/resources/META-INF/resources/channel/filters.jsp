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

CommerceChannelDisplayContext commerceChannelDisplayContext = (CommerceChannelDisplayContext)request.getAttribute(WebKeys.PORTLET_DISPLAY_CONTEXT);

CommerceChannel commerceChannel = commerceChannelDisplayContext.getCommerceChannel();

long commerceChannelId = commerceChannelDisplayContext.getCommerceChannelId();
%>

<%@ include file="/channel/navbar_definitions.jspf" %>

<portlet:actionURL name="editCommerceChannel" var="editCommerceChannelActionURL" />

<aui:form action="<%= editCommerceChannelActionURL %>" cssClass="container-fluid-1280" method="post" name="fm">
	<aui:input name="<%= Constants.CMD %>" type="hidden" value="<%= (commerceChannel == null) ? Constants.ADD : Constants.UPDATE %>" />
	<aui:input name="addTypeSettings" type="hidden" />
	<aui:input name="commerceChannelId" type="hidden" value="<%= commerceChannelId %>" />
	<aui:input name="deleteTypeSettings" type="hidden" />

	<div class="lfr-form-content">
		<aui:model-context bean="<%= commerceChannel %>" model="<%= CommerceChannel.class %>" />

		<aui:fieldset-group markupView="lexicon">

		</aui:fieldset-group>
	</div>

	<aui:button-row>
		<aui:button cssClass="btn-lg" type="submit" />
	</aui:button-row>
</aui:form>