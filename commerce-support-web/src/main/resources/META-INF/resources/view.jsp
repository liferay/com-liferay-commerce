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
CommerceContextDisplayContext commerceContextDisplayContext = (CommerceContextDisplayContext)request.getAttribute(WebKeys.PORTLET_DISPLAY_CONTEXT);

CommerceContext commerceContext = commerceContextDisplayContext.getCommerceContext();

CommerceCurrency commerceCurrency = commerceContext.getCommerceCurrency();
CommerceOrder commerceOrder = commerceContext.getCommerceOrder();
String couponCode = commerceContext.getCouponCode();
List<CommerceUserSegmentEntry> commerceUserSegmentEntries = commerceContextDisplayContext.getCommerceUserSegmentEntries();
List<CPRule> cpRules = commerceContext.getCPRules();
Optional<CommercePriceList> optionalCommercePriceList = commerceContext.getCommercePriceList();
Organization organization = commerceContext.getOrganization();
%>

<div class="sheet-section">
	<h3 class="sheet-subtitle"><liferay-ui:message key="current-organization" /></h3>

	<c:choose>
		<c:when test="<%= organization == null %>">
			<div class="alert alert-info mx-auto">
				<liferay-ui:message arguments="organization" key="x-is-not-set" translateArguments="<%= true %>" />
			</div>
		</c:when>
		<c:otherwise>
				<table class="table table-sm">
					<tr>
						<th class="table-cell-content"><liferay-ui:message key="id" /></th>
						<th class="table-cell-content"><liferay-ui:message key="name" /></th>
						<th class="table-cell-content"><liferay-ui:message key="type" /></th>
					</tr>
					<tr>
						<td class="table-cell-content"><%= organization.getOrganizationId() %></td>
						<td class="table-cell-content"><%= organization.getName() %></td>
						<td class="table-cell-content"><%= organization.getType() %></td>
					</tr>
				</table>
		</c:otherwise>
	</c:choose>
</div>

<div class="sheet-section">
	<h3 class="sheet-subtitle"><liferay-ui:message key="current-order" /></h3>

	<c:choose>
		<c:when test="<%= commerceOrder == null %>">
			<div class="alert alert-info mx-auto">
				<liferay-ui:message arguments="order" key="x-is-not-set" translateArguments="<%= true %>" />
			</div>
		</c:when>
		<c:otherwise>
			<table class="table table-sm">
				<tr>
					<th class="table-cell-content"><liferay-ui:message key="id" /></th>
					<th class="table-cell-content"><liferay-ui:message key="create-date" /></th>
				</tr>
				<tr>
					<td class="table-cell-content"><%= commerceOrder.getCommerceOrderId() %></td>
					<td class="table-cell-content"><%= commerceOrder.getCreateDate() %></td>
				</tr>
			</table>
		</c:otherwise>
	</c:choose>
</div>

<div class="sheet-section">
	<h3 class="sheet-subtitle"><liferay-ui:message key="currency" /></h3>

	<c:choose>
		<c:when test="<%= commerceCurrency == null %>">
			<div class="alert alert-info mx-auto">
				<liferay-ui:message arguments="currency" key="x-is-not-set" translateArguments="<%= true %>" />
			</div>
		</c:when>
		<c:otherwise>
			<table class="table table-sm">
				<tr>
					<th class="table-cell-content"><liferay-ui:message key="id" /></th>
					<th class="table-cell-content"><liferay-ui:message key="name" /></th>
					<th class="table-cell-content"><liferay-ui:message key="code" /></th>
				</tr>
				<tr>
					<td class="table-cell-content"><%= commerceCurrency.getCommerceCurrencyId() %></td>
					<td class="table-cell-content"><%= commerceCurrency.getName(locale) %></td>
					<td class="table-cell-content"><%= commerceCurrency.getCode() %></td>
				</tr>
			</table>
		</c:otherwise>
	</c:choose>
</div>

<div class="sheet-section">
	<h3 class="sheet-subtitle"><liferay-ui:message key="price-list" /></h3>

	<c:choose>
		<c:when test="<%= !optionalCommercePriceList.isPresent() %>">
			<div class="alert alert-info mx-auto">
				<liferay-ui:message arguments="price-list" key="x-is-not-set" translateArguments="<%= true %>" />
			</div>
		</c:when>
		<c:otherwise>

				<%
				CommercePriceList commercePriceList = optionalCommercePriceList.get();

				CommerceCurrency commercePriceListCurrency = commercePriceList.getCommerceCurrency();
				%>

				<table class="table table-sm">
					<tr>
						<th class="table-cell-content"><liferay-ui:message key="id" /></th>
						<th class="table-cell-content"><liferay-ui:message key="name" /></th>

						<c:if test="<%= commercePriceListCurrency != null %>">
							<th class="table-cell-content"><liferay-ui:message key="currency-id" /></th>
							<th class="table-cell-content"><liferay-ui:message key="currency-name" /></th>
							<th class="table-cell-content"><liferay-ui:message key="currency-code" /></th>
						</c:if>
					</tr>
					<tr>
						<td class="table-cell-content"><%= commercePriceList.getCommercePriceListId() %></td>
						<td class="table-cell-content"><%= commercePriceList.getName() %></td>

						<c:if test="<%= commercePriceListCurrency != null %>">
							<td class="table-cell-content"><%= commercePriceListCurrency.getCommerceCurrencyId() %></td>
							<td class="table-cell-content"><%= commercePriceListCurrency.getName(locale) %></td>
							<td class="table-cell-content"><%= commercePriceListCurrency.getCode() %></td>
						</c:if>
					</tr>
				</table>
		</c:otherwise>
	</c:choose>
</div>

<div class="sheet-section">
	<h3 class="sheet-subtitle"><liferay-ui:message key="user-segments" /></h3>

	<c:choose>
		<c:when test="<%= commerceUserSegmentEntries.isEmpty() %>">
			<div class="alert alert-info mx-auto">
				<liferay-ui:message arguments="user-segments" key="x-are-not-set" translateArguments="<%= true %>" />
			</div>
		</c:when>
		<c:otherwise>
				<table class="table table-sm">
					<tr>
						<th class="table-cell-content"><liferay-ui:message key="id" /></th>
						<th class="table-cell-content"><liferay-ui:message key="name" /></th>
					</tr>

					<%
					for (CommerceUserSegmentEntry commerceUserSegmentEntry : commerceUserSegmentEntries) {
					%>

						<tr>
							<td class="table-cell-content"><%= commerceUserSegmentEntry.getCommerceUserSegmentEntryId() %></td>
							<td class="table-cell-content"><%= commerceUserSegmentEntry.getName(locale) %></td>
						</tr>

					<%
					}
					%>

				</table>
		</c:otherwise>
	</c:choose>
</div>

<div class="sheet-section">
	<h3 class="sheet-subtitle"><liferay-ui:message key="catalog-rules" /></h3>

	<c:choose>
		<c:when test="<%= (cpRules == null) || cpRules.isEmpty() %>">
			<div class="alert alert-info mx-auto">
				<liferay-ui:message arguments="catalog-rules" key="x-are-not-set" translateArguments="<%= true %>" />
			</div>
		</c:when>
		<c:otherwise>
			<table class="table table-sm">
				<tr>
					<th class="table-cell-content"><liferay-ui:message key="id" /></th>
					<th class="table-cell-content"><liferay-ui:message key="name" /></th>
					<th class="table-cell-content"><liferay-ui:message key="type" /></th>
				</tr>

				<%
				for (CPRule cpRule : cpRules) {
				%>

					<tr>
						<td class="table-cell-content"><%= cpRule.getCPRuleId() %></td>
						<td class="table-cell-content"><%= cpRule.getName() %></td>
						<td class="table-cell-content"><%= LanguageUtil.get(request, cpRule.getType()) %></td>
					</tr>

				<%
				}
				%>

			</table>
		</c:otherwise>
	</c:choose>
</div>

<div class="sheet-section">
	<h3 class="sheet-subtitle"><liferay-ui:message key="coupon-code" /></h3>

	<c:choose>
		<c:when test="<%= Validator.isNull(couponCode) %>">
			<div class="alert alert-info mx-auto">
				<liferay-ui:message arguments="coupon-code" key="x-is-not-set" translateArguments="<%= true %>" />
			</div>
		</c:when>
		<c:otherwise>
			<table class="table table-sm">
				<tr>
					<th class="table-cell-content"><liferay-ui:message key="value" /></th>
				</tr>
				<tr>
					<td class="table-cell-content"><%= couponCode %></td>
				</tr>
			</table>
		</c:otherwise>
	</c:choose>
</div>