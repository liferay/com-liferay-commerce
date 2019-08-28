<%@ taglib prefix="liferay-ui" uri="http://java.sun.com/jsp/jstl/fmt" %>
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

<%@ include file="/modal/init.jsp" %>

<div id="<%= modalId %>" class="modal-root"></div>

<aui:script require="commerce-frontend-react@1.0.0/js/modal/entry.es as Modal">
	var modal = new Modal.default(
		"<%= modalId %>",
		"<%= modalId %>",
		{
			url: "<%= url %>",
			size: "<%= size %>",
			title: "<%= title %>",
			spritemap: "<%= spritemap %>",
			showSubmit: "<%= showSubmit %>",
			submitLabel: "<%= submitLabel %>",
			closeOnSubmit: "<%= closeOnSubmit %>",
			submitAvailableAtLoading: "<%= submitAvailableAtLoading %>",
			showDelete: "<%= showDelete %>",
			deleteLabel: "<%= deleteLabel %>",
			showCancel: "<%= showCancel %>",
			cancelLabel: "<%= cancelLabel %>"
		}
	);

	document.getElementById('<%= triggerId %>')
		.addEventListener(
			'click',
			(e) => {
				e.preventDefault();
				modal.open();
			}
	);
</aui:script>
