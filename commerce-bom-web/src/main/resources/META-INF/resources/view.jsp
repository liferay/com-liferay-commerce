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
String carPartsFinderRootElementId = renderResponse.getNamespace() + "-car-parts-finder";

NPMResolver npmResolver = NPMResolverProvider.getNPMResolver();
%>

<div class="car-parts-finder-module" id="<%= carPartsFinderRootElementId %>">
	<div class="inline-item my-5 p-5 w-100">
		<span aria-hidden="true" class="loading-animation"></span>
	</div>
</div>

<aui:script require='<%= npmResolver.resolveModuleName("commerce-bom-web/js/index.es") + " as CarPartsFinder" %>'>
	CarPartsFinder.default(
		'partFinder',
		'<%= carPartsFinderRootElementId %>',
		{
			basename: window.location.pathname,
			basePathUrl: '<%= PortalUtil.getGroupFriendlyURL(layout.getLayoutSet(), themeDisplay, locale) %>',
			areasEndpoint: '<%= PortalUtil.getPortalURL(request) + "/o/commerce-bom/1.0/areas" %>',
			foldersEndpoint: '<%= PortalUtil.getPortalURL(request) + "/o/commerce-bom/1.0/folders" %>',
			spritemap: '<%= themeDisplay.getPathThemeImages() + "/lexicon/icons.svg" %>'
		}
	);
</aui:script>