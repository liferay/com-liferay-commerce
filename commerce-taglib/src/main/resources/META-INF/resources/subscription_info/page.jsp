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
long duration = (long)request.getAttribute("liferay-commerce:subscription-info:duration");
String durationPeriodKey = (String)request.getAttribute("liferay-commerce:subscription-info:durationPeriodKey");
long length = (long)request.getAttribute("liferay-commerce:subscription-info:length");
boolean showDuration = (boolean)request.getAttribute("liferay-commerce:subscription-info:showDuration");
String subscriptionPeriodKey = (String)request.getAttribute("liferay-commerce:subscription-info:subscriptionPeriodKey");
%>

<c:if test="<%= (length > 0) && Validator.isNotNull(subscriptionPeriodKey) %>">
	<span class="product-subscription-period">
		(<liferay-ui:message arguments="<%= new Object[] {length, subscriptionPeriodKey} %>" key="every-x-x" />)
	</span>
</c:if>

<c:if test="<%= showDuration && (duration > 0) %>">
	<span class="product-subscription-duration"> <liferay-ui:message arguments="<%= new Object[] {duration, durationPeriodKey} %>" key="duration-x-x" /></span>
</c:if>