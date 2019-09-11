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

<%
String containerId = randomNamespace + "modal-root";
%>

<div class="modal-root" id="<%= randomNamespace %>"></div>

<aui:script require="commerce-frontend-js/js/modal/entry.es as Modal">
	new Modal.default(
		"<%= containerId %>",
		"<%= randomNamespace %>",
		{
			id: "<%= id %>",
			portletId: "<%= portletDisplay.getRootPortletId() %>",
			url: "<%= url %>",
			size: "<%= size %>",
			title: "<%= title %>",
			spritemap: "<%= spritemap %>",
			showSubmit: <%= showSubmit %>,
			submitLabel: "<%= submitLabel %>",
			closeOnSubmit: <%= closeOnSubmit %>,
			submitAvailableAtLoading: <%= submitAvailableAtLoading %>,
			showDelete: <%= showDelete %>,
			deleteLabel: "<%= deleteLabel %>",
			showCancel: <%= showCancel %>,
			cancelLabel: "<%= cancelLabel %>"
		}
	);

	<c:if test="<%= Validator.isNotNull(id) %>">
		const modalTriggers = document.querySelectorAll('[data-toggle="modal"][data-target="<%= id %>"]')

		if (modalTriggers.length) {
			modalTriggers.forEach(
				function(trigger) {
					trigger.addEventListener(
						'click',
						function(e) {
							e.preventDefault();
							Liferay.fire("<%= id %>-open");
						}
					)
				}
			);
		}
	</c:if>
</aui:script>