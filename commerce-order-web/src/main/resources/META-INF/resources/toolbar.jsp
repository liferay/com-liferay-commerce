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
CommerceOrderListDisplayContext commerceOrderListDisplayContext = (CommerceOrderListDisplayContext)request.getAttribute(WebKeys.PORTLET_DISPLAY_CONTEXT);

SearchContainer<CommerceOrder> commerceOrderSearchContainer = commerceOrderListDisplayContext.getSearchContainer();

CommerceOrderDisplayTerms commerceOrderDisplayTerms = (CommerceOrderDisplayTerms)commerceOrderSearchContainer.getDisplayTerms();
boolean showFilter = commerceOrderListDisplayContext.isShowFilter();

List<CommerceChannel> channels = commerceOrderListDisplayContext.getCommerceChannels();

List<KeyValuePair> availableAdvanceStatusKVPs = commerceOrderListDisplayContext.getAvailableAdvanceStatusKVPs();
List<KeyValuePair> availableOrderStatusKVPs = commerceOrderListDisplayContext.getAvailableOrderStatusKVPs();

PortletURL searchURL = commerceOrderListDisplayContext.getPortletURL();

searchURL.setParameter("showFilter", String.valueOf(showFilter));

pageContext.setAttribute("searchURL", searchURL);
%>

<aui:form action="<%= searchURL %>" method="get" name="searchFm">
	<liferay-portlet:renderURLParams varImpl="searchURL" />

	<liferay-frontend:management-bar
		includeCheckBox="<%= true %>"
		searchContainerId="commerceOrders"
	>
		<liferay-frontend:management-bar-buttons>
			<liferay-frontend:management-bar-button
				cssClass='<%= showFilter ? "active" : StringPool.BLANK %>'
				href='<%= "javascript:" + renderResponse.getNamespace() + "toggleFilter();" %>'
				iconCssClass="icon-filter"
				id="filterButton"
				label="filter"
			/>
		</liferay-frontend:management-bar-buttons>

		<liferay-frontend:management-bar-filters>
			<li>
				<liferay-portlet:renderURLParams varImpl="searchURL" />

				<liferay-ui:input-search
					markupView="lexicon"
				/>
			</li>
		</liferay-frontend:management-bar-filters>

		<liferay-frontend:management-bar-action-buttons>
			<liferay-frontend:management-bar-button
				href='<%= "javascript:" + renderResponse.getNamespace() + "deleteCommerceOrders();" %>'
				icon="times"
				label="delete"
			/>
		</liferay-frontend:management-bar-action-buttons>
	</liferay-frontend:management-bar>

	<div class="container form-group-autofit panel p-2 <%= showFilter ? StringPool.BLANK : "hide" %>" id="<portlet:namespace />filterSettings">
		<div class="form-group-item">
			<label for="<portlet:namespace /><%= CommerceOrderDisplayTerms.START_CREATE_DATE %>">
				<liferay-ui:message key="from" />
			</label>

			<liferay-ui:input-date
				dayParam="<%= CommerceOrderDisplayTerms.START_CREATE_DATE_DAY %>"
				dayValue="<%= commerceOrderDisplayTerms.getStartCreateDateDay() %>"
				monthParam="<%= CommerceOrderDisplayTerms.START_CREATE_DATE_MONTH %>"
				monthValue="<%= commerceOrderDisplayTerms.getStartCreateDateMonth() %>"
				name="<%= CommerceOrderDisplayTerms.START_CREATE_DATE %>"
				nullable="<%= true %>"
				showDisableCheckbox="<%= false %>"
				yearParam="<%= CommerceOrderDisplayTerms.START_CREATE_DATE_YEAR %>"
				yearValue="<%= commerceOrderDisplayTerms.getStartCreateDateYear() %>"
			/>
		</div>

		<div class="form-group-item">
			<label for="<portlet:namespace /><%= CommerceOrderDisplayTerms.END_CREATE_DATE %>">
				<liferay-ui:message key="to" />
			</label>

			<liferay-ui:input-date
				dayParam="<%= CommerceOrderDisplayTerms.END_CREATE_DATE_DAY %>"
				dayValue="<%= commerceOrderDisplayTerms.getEndCreateDateDay() %>"
				monthParam="<%= CommerceOrderDisplayTerms.END_CREATE_DATE_MONTH %>"
				monthValue="<%= commerceOrderDisplayTerms.getEndCreateDateMonth() %>"
				name="<%= CommerceOrderDisplayTerms.END_CREATE_DATE %>"
				nullable="<%= true %>"
				showDisableCheckbox="<%= false %>"
				yearParam="<%= CommerceOrderDisplayTerms.END_CREATE_DATE_YEAR %>"
				yearValue="<%= commerceOrderDisplayTerms.getEndCreateDateYear() %>"
			/>
		</div>

		<div class="form-group-item">
			<aui:select name="<%= CommerceOrderDisplayTerms.CHANNEL %>" showEmptyOption="<%= true %>">

				<%
				for (CommerceChannel channel : channels) {
					String channelName = channel.getName();
				%>

					<aui:option label="<%= channelName %>" selected="<%= channelName.equals(commerceOrderDisplayTerms.getCommerceChannel()) %>" value="<%= channelName %>" />

				<%
				}
				%>

			</aui:select>
		</div>

		<c:if test="<%= !availableAdvanceStatusKVPs.isEmpty() %>">
			<div class="form-group-item">
				<aui:select name="<%= CommerceOrderDisplayTerms.ADVANCE_STATUS %>" showEmptyOption="<%= true %>">

					<%
					for (KeyValuePair keyValuePair : availableAdvanceStatusKVPs) {
						String advanceStatus = keyValuePair.getKey();
					%>

						<aui:option label="<%= keyValuePair.getValue() %>" localizeLabel="<%= false %>" selected="<%= advanceStatus.equals(commerceOrderDisplayTerms.getAdvanceStatus()) %>" value="<%= advanceStatus %>" />

					<%
					}
					%>

				</aui:select>
			</div>
		</c:if>

		<c:if test="<%= !availableOrderStatusKVPs.isEmpty() %>">
			<div class="form-group-item">
				<aui:select name="<%= CommerceOrderDisplayTerms.ORDER_STATUS %>">
					<aui:option label="" selected="<%= commerceOrderDisplayTerms.getOrderStatus() == CommerceOrderConstants.ORDER_STATUS_ANY %>" value="<%= CommerceOrderConstants.ORDER_STATUS_ANY %>" />

					<%
					for (KeyValuePair keyValuePair : availableOrderStatusKVPs) {
						String orderStatus = keyValuePair.getKey();
					%>

						<aui:option label="<%= keyValuePair.getValue() %>" localizeLabel="<%= false %>" selected="<%= orderStatus.equals(commerceOrderDisplayTerms.getOrderStatus()) %>" value="<%= orderStatus %>" />

					<%
					}
					%>

				</aui:select>
			</div>
		</c:if>

		<div class="form-group-item mt-auto">
			<aui:button cssClass="btn-outline-borderless btn-outline-primary" type="submit" value="apply-filters" />
		</div>
	</div>
</aui:form>

<aui:script>
	function <portlet:namespace />toggleFilter() {
		var showFilterInput = AUI.$('#<portlet:namespace />searchFm input[name=<portlet:namespace />showFilter]');

		var showFilter = (showFilterInput.val() == 'true');

		showFilter = !showFilter;

		showFilterInput.val(showFilter);

		AUI.$('#<portlet:namespace />filterButton').toggleClass('active', showFilter);
		AUI.$('#<portlet:namespace />filterSettings').toggleClass('hide', !showFilter);
	}
</aui:script>