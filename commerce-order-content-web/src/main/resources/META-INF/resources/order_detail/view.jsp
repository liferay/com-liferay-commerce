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
CommerceOrderDetailHelper commerceOrderDetailHelper = (CommerceOrderDetailHelper)request.getAttribute(CommerceOrderDetailWebKeys.COMMERCE_ORDER_DETAIL_HELPER);

CommerceOrder commerceOrder = commerceOrderDetailHelper.getCommerceOrder(request);
%>

<c:choose>
	<c:when test="<%= commerceOrder == null %>">
		<div class="alert alert-info">
			<liferay-ui:message key="there-is-no-order-to-display" />
		</div>
	</c:when>
	<c:otherwise>

		<%
		commerceOrderDetailHelper.renderCommerceOrderDetail(request, response);
		%>

	</c:otherwise>
</c:choose>