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
CommerceChannelDisplayContext commerceChannelDisplayContext = (CommerceChannelDisplayContext)request.getAttribute(WebKeys.PORTLET_DISPLAY_CONTEXT);

PortletURL portletURL = commerceChannelDisplayContext.getPortletURL();

SearchContainer commerceCatalogChannelsSearchContainer = commerceChannelDisplayContext.getSearchContainer();
%>

<liferay-util:include page="/toolbar.jsp" servletContext="<%= application %>">
	<liferay-util:param name="searchContainerId" value="commerceChannels" />
</liferay-util:include>

<portlet:actionURL name="editCommerceChannel" var="editCommerceChannelURL" />

<div class="closed container-fluid-1280 sidenav-container sidenav-right" id="<portlet:namespace />infoPanelId">
	<div class="sidenav-content">
		<aui:form action="<%= editCommerceChannelURL %>" method="post" name="fm">
			<aui:input name="<%= Constants.CMD %>" type="hidden" value="<%= Constants.ADD %>" />
			<aui:input name="redirect" type="hidden" value="<%= portletURL.toString() %>" />
			<aui:input name="commerceChannelIds" type="hidden" />

			<div class="products-container" id="<portlet:namespace />commerceCatalogsContainer">
				<liferay-ui:search-container
					emptyResultsMessage="no-channels-were-found"
					id="commerceChannels"
					searchContainer="<%= commerceCatalogChannelsSearchContainer %>"
				>
					<liferay-ui:search-container-row
						className="com.liferay.commerce.product.model.CommerceChannel"
						cssClass="entry-display-style"
						keyProperty="commerceChannelId"
						modelVar="commerceChannel"
					>
						<liferay-ui:search-container-column-text
							cssClass="important table-cell-content"
							href="<%= commerceChannelDisplayContext.getChannelURL(commerceChannel) %>"
							name="name"
							value="<%= HtmlUtil.escape(commerceChannel.getName()) %>"
						/>

						<liferay-ui:search-container-column-jsp
							cssClass="entry-action-column"
							path="/channel_action.jsp"
						/>
					</liferay-ui:search-container-row>

					<liferay-ui:search-iterator
						displayStyle="list"
						markupView="lexicon"
					/>
				</liferay-ui:search-container>
			</div>
		</aui:form>
	</div>
</div>