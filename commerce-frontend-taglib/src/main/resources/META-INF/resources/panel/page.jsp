<%@ page import="com.liferay.commerce.frontend.model.PanelActionModel" %><%--
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
    String spritemap = (String)request.getAttribute("liferay-commerce:header:spritemap");
    PanelActionModel panelAction = (PanelActionModel)request.getAttribute("liferay-commerce:header:panelAction");
    String title = (String)request.getAttribute("liferay-commerce:header:title");
%>

<div class="card mb-4">
    <c:if test="<%= Validator.isNotNull(panelAction) || Validator.isNotNull(title) %>">
        <h5 class="card-header">
            <%= title %>

            <% String actionLabel = panelAction.getLabel(); %>
            <% String actionIcon = panelAction.getIcon(); %>

            <c:if test="<%= Validator.isNotNull(actionLabel) %>">

            </c:if>
            <c:choose>
                <c:when test="<%= Validator.isNotNull(actionLabel) %>">
                    <clay:button type="link" label="<%= actionLabel %>" />
                </c:when>
                <c:when test="<%= Validator.isNotNull(actionIcon) %>">
                    <clay:link
                        elementClasses="btn btn-primary btn-sm"
                        icon="<%= actionIcon %>"
                        href="#"
                    />
                </c:when>
            </c:choose>
        </h5>
    </c:if>
    <div class="card-body">
        <div class="order-shipping-address" id="order-shipping-address">
            PO Box 467 New York 10002
        </div>

        <div class="order-type-delivery" id="order-type-delivery">
            Standard delivery
        </div>
    </div>
</div>