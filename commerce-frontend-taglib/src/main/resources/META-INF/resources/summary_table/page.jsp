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

<%@ include file="/summary_table/init.jsp" %>

<%
List<SummaryElement> data = (List<SummaryElement>)request.getAttribute("liferay-commerce:summary-table:data");
%>

<div class="row summary-table text-right">
	<c:forEach items="<%= data %>" var="rowElement">
		<c:choose>
			<c:when test="${rowElement.style == 'divider'}">
				<div class="col-12"><hr /></div>
			</c:when>
			<c:when test="${rowElement.style == 'big'}">
				<div class="col-6 col-md-9"><h3 class="my-2">${rowElement.label}</h3></div>
				<div class="col-6 col-md-3"><h3 class="my-2">${rowElement.value}</h3></div>
			</c:when>
			<c:when test="${rowElement.style == 'danger'}">
				<div class="col-6 col-md-9 text-danger"><p class="m-0">${rowElement.label}</p></div>
				<div class="col-6 col-md-3 text-danger"><p class="m-0">${rowElement.value}</p></div>
			</c:when>
			<c:otherwise>
				<div class="col-6 col-md-9"><p class="m-0">${rowElement.label}</p></div>
				<div class="col-6 col-md-3"><p class="m-0">${rowElement.value}</p></div>
			</c:otherwise>
		</c:choose>
	</c:forEach>
</div>