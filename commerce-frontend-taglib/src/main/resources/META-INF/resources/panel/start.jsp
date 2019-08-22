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
    String cardCssClasses = "card" + (Validator.isNotNull(elementClasses) ? " " + elementClasses : "");
%>

<div class="<%= cardCssClasses %>">
    <c:if test="<%= Validator.isNotNull(actionLabel) || Validator.isNotNull(actionIcon) || Validator.isNotNull(title) %>">
        <h5 class="card-header d-flex align-items-center justify-content-between">
            <%= title %>

            <c:choose>
                <c:when test="<%= Validator.isNotNull(actionLabel) %>">
                    <clay:link
                            href="#"
                        id="<%= actionLinkId %>"
                        label="<%= actionLabel %>"
                    />
                </c:when>
                <c:when test="<%= Validator.isNotNull(actionIcon) %>">
                    <clay:link
                            href="#"
                        id="<%= actionLinkId %>"
                        elementClasses="btn btn-primary btn-sm"
                        icon="<%= actionIcon %>"
                    />
                </c:when>
            </c:choose>
        </h5>
    </c:if>
    <div class="card-body">