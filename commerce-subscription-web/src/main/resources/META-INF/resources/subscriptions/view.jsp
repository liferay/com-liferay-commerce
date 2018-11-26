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
CommerceSubscriptionContentDisplayContext commerceSubscriptionContentDisplayContext = (CommerceSubscriptionContentDisplayContext)request.getAttribute(WebKeys.PORTLET_DISPLAY_CONTEXT);

SearchContainer<CommerceSubscriptionEntry> commerceSubscriptionEntrySearchContainer = commerceSubscriptionContentDisplayContext.getSearchContainer();
%>

<div class="container-fluid-1280" id="<portlet:namespace />subscriptionEntriesContainer">
	<div class="commerce-product-subscription-entries-container" id="<portlet:namespace />entriesContainer">
		<liferay-ui:search-container
			id="cpSubscriptionEntries"
			iteratorURL="<%= commerceSubscriptionContentDisplayContext.getPortletURL() %>"
			searchContainer="<%= commerceSubscriptionEntrySearchContainer %>"
		>
			<liferay-ui:search-container-row
				className="com.liferay.commerce.model.CommerceSubscriptionEntry"
				cssClass="entry-display-style"
				keyProperty="commerceSubscriptionEntryId"
				modelVar="commerceSubscriptionEntry"
			>

				<%
				CommerceOrderItem commerceOrderItem = commerceSubscriptionEntry.getCommerceOrderItem();

				String thumbnailSrc = commerceSubscriptionContentDisplayContext.getCommerceSubscriptionEntryThumbnailSrc(commerceSubscriptionEntry, themeDisplay);

				List<KeyValuePair> keyValuePairs = commerceSubscriptionContentDisplayContext.getKeyValuePairs(commerceSubscriptionEntry);

				StringJoiner stringJoiner = new StringJoiner(StringPool.COMMA);

				for (KeyValuePair keyValuePair : keyValuePairs) {
					stringJoiner.add(keyValuePair.getValue());
				}
				%>

				<liferay-ui:search-container-column-image
					name="product"
					src="<%= thumbnailSrc %>"
				/>

				<liferay-ui:search-container-column-text
					name="description"
				>
					<a class="font-weight-bold" href="<%= commerceSubscriptionContentDisplayContext.getCPDefinitionURL(commerceSubscriptionEntry, themeDisplay) %>">
						<%= HtmlUtil.escape(commerceOrderItem.getName(languageId)) %>
					</a>

					<h6 class="text-default">
						<%= HtmlUtil.escape(stringJoiner.toString()) %>
					</h6>
				</liferay-ui:search-container-column-text>

				<liferay-ui:search-container-column-text
					name="status"
				>
					<h6 class="text-default">
						<%= HtmlUtil.escape(LanguageUtil.get(request, (commerceSubscriptionEntry.isActive() ? "active" : "inactive"))) %>
					</h6>
				</liferay-ui:search-container-column-text>

				<liferay-ui:search-container-column-date
					name="create-date"
					property="createDate"
				/>

				<liferay-ui:search-container-column-date
					name="next-iteration-date"
					property="nextIterationDate"
				/>

				<liferay-ui:search-container-column-text>
					<portlet:actionURL name="cancelCommerceSubscriptionEntry" var="cancelURL">
						<portlet:param name="redirect" value="<%= currentURL %>" />
						<portlet:param name="commerceSubscriptionEntryId" value="<%= String.valueOf(commerceSubscriptionEntry.getCommerceSubscriptionEntryId()) %>" />
					</portlet:actionURL>

					<liferay-ui:icon
						message="cancel-subscription"
						url="<%= cancelURL %>"
					/>
				</liferay-ui:search-container-column-text>
			</liferay-ui:search-container-row>

			<liferay-ui:search-iterator
				displayStyle="list"
				markupView="lexicon"
			/>
		</liferay-ui:search-container>
	</div>
</div>