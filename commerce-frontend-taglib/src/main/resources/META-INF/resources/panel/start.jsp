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

<%@ include file="/panel/init.jsp" %>

<%
	String cardCssClasses = "card d-flex" + (Validator.isNotNull(elementClasses) ? " " + elementClasses : "");
	String bodyCssClasses = "card-body" + (Validator.isNotNull(bodyClasses) ? " " + bodyClasses : "");

	Map<String, String> data = new HashMap<>();

	if (Validator.isNotNull(headerActionTarget)) {
		data.put("target", headerActionTarget);
	}
%>

<div class="<%= cardCssClasses %>">
	<c:if test="<%= Validator.isNotNull(headerActionLabel) || Validator.isNotNull(headerActionIcon) || Validator.isNotNull(title) %>">
		<div class="align-items-center card-header d-flex justify-content-between">
			<%= title %>

			<c:choose>
				<c:when test="<%= Validator.isNotNull(headerActionLabel) %>">
					<clay:link
						data="<%= data %>"
						href="<%= headerActionUrl %>"
						id="<%= headerActionLinkId %>"
						label="<%= headerActionLabel %>"
					/>
				</c:when>
				<c:when test="<%= Validator.isNotNull(headerActionIcon) %>">
					<clay:link
						data="<%= data %>"
						elementClasses="btn btn-monospaced btn-primary btn-sm text-white"
						href="<%= headerActionUrl %>"
						icon="<%= headerActionIcon %>"
						id="<%= headerActionLinkId %>"
					/>
				</c:when>
			</c:choose>
		</div>
	</c:if>

	<div class="<%= bodyCssClasses %>">