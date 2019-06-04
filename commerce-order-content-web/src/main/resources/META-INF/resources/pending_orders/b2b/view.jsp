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
CommerceOrderContentDisplayContext commerceOrderContentDisplayContext = (CommerceOrderContentDisplayContext)request.getAttribute(WebKeys.PORTLET_DISPLAY_CONTEXT);
%>

<portlet:actionURL name="editCommerceOrder" var="editCommerceOrderURL" />

<span class="accounts-filter"></span>

<aui:script require="commerce-frontend-taglib@2.1.1/datalist/Datalist.es as Datalist">

	var filterInitialized = false;

	Liferay.componentReady('commercePendingOrders').then(
		(pendingOrdersTable) => {
			
			pendingOrdersTable.showPagination = true;

			pendingOrdersTable.connectorSettings = {
				remote: {
					read: pendingOrdersTable.apiUrl
				},
				pagination: {
					page: 1,
					pageSize: 5
				},
				filters: [
					{
						field: 'groupId',
						value: themeDisplay.getScopeGroupId()
					},
					{
						field: 'accountId',
						value: '<%= commerceOrderContentDisplayContext.getCommerceAccountId() %>'
					}
				],
				on: {
					mapParameters: (data, component) => {
						const defaultParams = 'p_auth=' + Liferay.authToken;
						const paginationParams = 'page=' + data.pagination.page + '&pageSize=' + data.pagination.pageSize;
						const filters = (component.connector.searchForRelatedConnectorsValues() || []).reduce(
							(acc, filter) => {
								return filter.value 
									? acc.concat(filter.name + '=' + filter.value)
									: acc
							},
							[]
						).join('&')
						return defaultParams + '&' + paginationParams + '&' + filters;
					}
				}
			}
			
			if(!filterInitialized) {
				filterInitialized = true;
				initializeFilters(pendingOrdersTable);
			}
		}
	);

	function initializeFilters(tableInstance) {
		const ordersAccounts = new Datalist.default(
			{
				additionalClasses: 'mb-4 d-block',
				name: 'accountId',
				valueField: 'accountId',
				labelField: 'name',
				spritemap: '/o/minium-theme/images/commerce-icons.svg',
				events: {
					'selected': () => {
						tableInstance.reload()
					}
				},
				connectorSettings: {
					remote: {
						read: '/o/commerce-ui/search-accounts'
					},
					pagination: {
						page: 1,
						pageSize: 5
					},
					filters: [
						{
							field: 'groupId',
							value: themeDisplay.getScopeGroupId()
						}
					],
					on: {
						mapParameters: (data) => {
							const groupId = 'groupId=' + (
								data.filters.reduce(
									(id, filter) => id || (filter.field === 'groupId' && filter.value),
									false
								)
							) || '';
							const paginationParams = 'page=' + data.pagination.page + '&pageSize=' + data.pagination.pageSize;
							const queryParams = 'q=' + (data.filters.reduce(
								(id, filter) => id || (filter.field === 'keyWord' && filter.value),
								false
							) || '');
							return groupId + '&' + paginationParams + '&' + queryParams;
						},
						parseResponse: (response) => response.accounts
					}
				}
			},
			'.accounts-filter'
		)
	};

</aui:script>
<commerce-ui:smart-table
	dataProviderKey="commercePendingOrders"
	filter="<%= commerceOrderContentDisplayContext.getOrderFilter() %>"
	itemPerPage="<%= 5 %>"
	namespace="<%= renderResponse.getNamespace() %>"
	pageNumber="1"
	portletURL="<%= commerceOrderContentDisplayContext.getPortletURL() %>"
	tableName="commercePendingOrders"
/>

<div class="commerce-cta is-visible">
	<c:if test="<%= commerceOrderContentDisplayContext.hasPermission(CommerceOrderActionKeys.ADD_COMMERCE_ORDER) %>">
		<aui:form action="<%= editCommerceOrderURL %>" method="post" name="fm">
			<aui:input name="<%= Constants.CMD %>" type="hidden" value="<%= Constants.ADD %>" />
			<aui:input name="redirect" type="hidden" value="<%= currentURL %>" />
			<aui:input name="deleteCommerceOrderIds" type="hidden" />
			<aui:button cssClass="commerce-button commerce-button--big" type="submit" value="add-order" />
		</aui:form>
	</c:if>
</div>