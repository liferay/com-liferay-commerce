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
String forecastChartRootElementId = renderResponse.getNamespace() + "-forecast-chart";

NPMResolver npmResolver = NPMResolverProvider.getNPMResolver();

long commerceAccountId = (long)request.getAttribute("commerceAccountId");
%>

<div id="<%= forecastChartRootElementId %>">
	<span aria-hidden="true" class="loading-animation"></span>
</div>

<aui:script require='<%= npmResolver.resolveModuleName("commerce-dashboard-web/js/forecast/index.es") + " as chart" %>'>
chart.default(
	'forecastChart',
	'<%= forecastChartRootElementId %>',
	{
		APIBaseUrl: `/o/headless-commerce-machine-learning/v1.0/accountCategoryForecasts/by-monthlyRevenue`,
		accountIdParamName: 'accountIds',
		commerceAccountId: '<%= commerceAccountId %>',
		noAccountErrorMessage: Liferay.Language.get('no-account-selected'),
		noDataErrorMessage: Liferay.Language.get('no-data-available')
	}
);
</aui:script>