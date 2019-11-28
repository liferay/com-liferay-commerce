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
String statusChartRootElementId = renderResponse.getNamespace() + "-status-chart";

NPMResolver npmResolver = NPMResolverProvider.getNPMResolver();

long commerceAccountId = (long)request.getAttribute("commerceAccountId");
%>

<div id="<%= statusChartRootElementId %>">
	<span aria-hidden="true" class="loading-animation"></span>
</div>

<aui:script require='<%= npmResolver.resolveModuleName("commerce-dashboard-web/js/status/index.es") + " as chart" %>'>
chart.default(
	'statusChart',
		'<%= statusChartRootElementId %>',
		{
	  commerceAccountId: '<%= commerceAccountId %>'
	}
);
</aui:script>