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

<%@ include file="/subscription_info/init.jsp" %>

<%
long duration = (long)request.getAttribute("liferay-commerce-product:subscription-info:duration");
String durationSuffix = (String)request.getAttribute("liferay-commerce-product:subscription-info:durationSuffix");
long length = (long)request.getAttribute("liferay-commerce-product:subscription-info:length");
String periodSuffix = (String)request.getAttribute("liferay-commerce-product:subscription-info:periodSuffix");
boolean showDuration = (boolean)request.getAttribute("liferay-commerce-product:subscription-info:showDuration");
%>

<c:if test="<%= (length > 0) && Validator.isNotNull(periodSuffix) %>">
	<span class="product-subscription-period">
		(<liferay-ui:message key="every" /> <%= length %> <%= periodSuffix %>)
	</span>
</c:if>

<c:if test="<%= showDuration && (duration > 0) %>">
	<span class="product-subscription-duration"> <liferay-ui:message arguments="<%= duration %>" key="duration-x" /><%= durationSuffix %></span>
</c:if>