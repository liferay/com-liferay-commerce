<%--
/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * The contents of this file are subject to the terms of the Liferay Enterprise
 * Subscription License ("License"). You may not use this file except in
 * compliance with the License. You can obtain a copy of the License by
 * contacting Liferay, Inc. See the License for the specific language governing
 * permissions and limitations under the License, including but not limited to
 * distribution rights of the Software.
 *
 *
 *
 */
--%>

<%@ include file="/init.jsp" %>

<%
CPDataSourceResult cpDataSourceResult = (CPDataSourceResult)request.getAttribute(CPWebKeys.CP_DATA_SOURCE_RESULT);

List<CPCatalogEntry> cpCatalogEntries = cpDataSourceResult.getCPCatalogEntries();
%>

<c:choose>
	<c:when test="<%= !cpCatalogEntries.isEmpty() %>">
		<div class="minium-product-tiles">

			<%
			for (CPCatalogEntry cpCatalogEntry : cpCatalogEntries) {
			%>

				<div class="minium-product-tiles__item">
					<liferay-commerce-product:product-list-entry-renderer
						CPCatalogEntry = "<%= cpCatalogEntry %>"
					/>
				</div>

			<%
			}
			%>

		</div>
	</c:when>
	<c:otherwise>
		<div class="alert alert-info">
			<liferay-ui:message key="no-products-were-found" />
		</div>
	</c:otherwise>
</c:choose>