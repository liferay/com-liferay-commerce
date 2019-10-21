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
%>

<div class="<%= cardCssClasses %>">
	<c:if test="<%= Validator.isNotNull(actionLabel) || Validator.isNotNull(actionIcon) || Validator.isNotNull(title) %>">
		<div class="align-items-center card-header d-flex justify-content-between">
			<%= title %>

			<c:if test="<%= Validator.isNotNull(actionTargetId) %>">
				<aui:script require="commerce-frontend-js/utilities/eventsDefinitions.es as eventsDefinitions">
					const link = document.getElementById('<%= linkId %>');

					if(link) {
						link.addEventListener('click', function(e) {
							e.preventDefault();
							Liferay.fire(
								eventsDefinitions.OPEN,
								{
									id: "<%= actionTargetId %>"
								}
							)
						})
					}
				</aui:script>
			</c:if>

			<c:choose>
				<c:when test="<%= Validator.isNotNull(actionLabel) %>">
					<clay:link
						href='<%= Validator.isNotNull(actionUrl) ? actionUrl : "#" %>'
						id="<%= linkId %>"
						label="<%= actionLabel %>"
					/>
				</c:when>
				<c:when test="<%= Validator.isNotNull(actionIcon) %>">
					<clay:link
						elementClasses="btn btn-monospaced btn-primary btn-sm text-white"
						href='<%= Validator.isNotNull(actionUrl) ? actionUrl : "#" %>'
						icon="<%= actionIcon %>"
						id="<%= linkId %>"
					/>
				</c:when>
			</c:choose>
		</div>
	</c:if>

	<div class="<%= bodyCssClasses %>">