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

<%@ include file="/side_panel_content/init.jsp" %>

<aui:script require="commerce-frontend-js/utilities/iframes.es as iframesUtils">
	iframesUtils.initializeIframeListeners();

	var pageMenu = document.querySelector(".page-header");
	var pageHeader = document.querySelector(".side-panel-iframe-header");

	if(!pageMenu && pageHeader) {
	    pageHeader.classList.add('side-panel-iframe-header-sticked');
	}

</aui:script>

<c:if test="<%= Validator.isNotNull(title) %>">
	<div class="side-panel-iframe-body card">
		<h4 class="side-panel-iframe-header card-header py-3">
			<%= title %>
		</h4>

		<div class="side-panel-iframe-content card-body">
</c:if>
