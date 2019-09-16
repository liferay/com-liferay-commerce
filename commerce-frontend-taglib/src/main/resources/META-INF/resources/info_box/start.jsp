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

<%@ include file="/info_box/init.jsp" %>

<%
String infoxBoxCssClasses = "info-box" + (Validator.isNotNull(elementClasses) ? " " + elementClasses : "");
%>

<div class="<%= infoxBoxCssClasses %>">
	<header class="header">
		<c:if test="<%= Validator.isNotNull(title) %>">
			<h4 class="title"><%= title %></h4>
		</c:if>

		<c:if test="<%= Validator.isNotNull(actionLabel) %>">

			<%
				Map<String, String> data = new HashMap<>();

				if (Validator.isNotNull(actionTargetId)) {
					data.put("target", actionTargetId);
					data.put("toggle", "modal");
				}
			%>

			<clay:link
				data="<%= data %>"
				href='<%= Validator.isNotNull(actionUrl) ? actionUrl : "#" %>'
				label="<%= actionLabel %>"
			/>
		</c:if>
	</header>

	<div class="description">