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
CPOptionCategory cpOptionCategory = (CPOptionCategory)request.getAttribute(CPWebKeys.CP_OPTION_CATEGORY);
%>

<liferay-ui:error-marker
	key="<%= WebKeys.ERROR_SECTION %>"
	value="details"
/>

<aui:model-context bean="<%= cpOptionCategory %>" model="<%= CPOptionCategory.class %>" />

<liferay-ui:error exception="<%= CPOptionCategoryKeyException.class %>" message="that-key-is-already-being-used" />

<aui:fieldset>
	<aui:input autoFocus="<%= true %>" name="title" />

	<aui:input name="description" />

	<aui:input name="priority" />

	<aui:input helpMessage="key-help" name="key" />
</aui:fieldset>

<c:if test="<%= cpOptionCategory == null %>">
	<aui:script require="frontend-js-web/liferay/debounce/debounce.es as debounceModule">
		const form = document.getElementById('<portlet:namespace />fm');

		const keyInput = form.querySelector('#<portlet:namespace />key');
		const titleInput = form.querySelector('#<portlet:namespace />title');

		const debounce = debounceModule.default;

		var handleOnTitleInput = function(event) {
			keyInput.value = event.target.value;
		};

		titleInput.addEventListener(
			'input',
			debounce(handleOnTitleInput, 200)
		);
	</aui:script>
</c:if>