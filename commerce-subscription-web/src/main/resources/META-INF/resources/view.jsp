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
CommerceSubscriptionEntryDisplayContext commerceSubscriptionEntryDisplayContext = (CommerceSubscriptionEntryDisplayContext)request.getAttribute(WebKeys.PORTLET_DISPLAY_CONTEXT);

SearchContainer<CommerceSubscriptionEntry> commerceSubscriptionEntrySearchContainer = commerceSubscriptionEntryDisplayContext.getSearchContainer();

boolean hasManageCommerceSubscriptionEntryPermission = commerceSubscriptionEntryDisplayContext.hasManageCommerceSubscriptionEntryPermission();
%>

<portlet:actionURL name="editCommerceSubscriptionEntry" var="editCommerceSubscriptionEntryActionURL" />

<liferay-frontend:management-bar
	includeCheckBox="<%= true %>"
	searchContainerId="commerceSubscriptionEntries"
>
	<liferay-frontend:management-bar-filters>
		<liferay-frontend:management-bar-navigation
			navigationKeys='<%= new String[] {"all", "active", "suspended", "cancelled", "completed", "never-ends"} %>'
			portletURL="<%= commerceSubscriptionEntryDisplayContext.getPortletURL() %>"
		/>

		<liferay-frontend:management-bar-sort
			orderByCol="<%= commerceSubscriptionEntryDisplayContext.getOrderByCol() %>"
			orderByType="<%= commerceSubscriptionEntryDisplayContext.getOrderByType() %>"
			orderColumns='<%= new String[] {"create-date"} %>'
			portletURL="<%= commerceSubscriptionEntryDisplayContext.getPortletURL() %>"
		/>

		<li>
			<aui:form action="<%= String.valueOf(commerceSubscriptionEntryDisplayContext.getPortletURL()) %>" name="searchFm">
				<liferay-ui:input-search
					markupView="lexicon"
				/>
			</aui:form>
		</li>
	</liferay-frontend:management-bar-filters>

	<liferay-frontend:management-bar-buttons>
		<liferay-frontend:management-bar-display-buttons
			displayViews='<%= new String[] {"list"} %>'
			portletURL="<%= commerceSubscriptionEntryDisplayContext.getPortletURL() %>"
			selectedDisplayStyle="list"
		/>
	</liferay-frontend:management-bar-buttons>

	<c:if test="<%= hasManageCommerceSubscriptionEntryPermission %>">
		<liferay-frontend:management-bar-action-buttons>
			<liferay-frontend:management-bar-button
				href='<%= "javascript:" + renderResponse.getNamespace() + "deleteCommerceSubscriptionEntries();" %>'
				icon="times"
				label="delete"
			/>
		</liferay-frontend:management-bar-action-buttons>
	</c:if>
</liferay-frontend:management-bar>

<div class="container-fluid-1280" id="<portlet:namespace />subscriptionContainer">
	<aui:form action="<%= editCommerceSubscriptionEntryActionURL %>" method="post" name="fm">
		<aui:input name="<%= Constants.CMD %>" type="hidden" />
		<aui:input name="redirect" type="hidden" value="<%= currentURL %>" />
		<aui:input name="deleteCommerceSubscriptionEntryIds" type="hidden" />

		<div class="product-subscriptions-container" id="<portlet:namespace />entriesContainer">
			<liferay-ui:search-container
				id="commerceSubscriptionEntries"
				searchContainer="<%= commerceSubscriptionEntrySearchContainer %>"
			>
				<liferay-ui:search-container-row
					className="com.liferay.commerce.model.CommerceSubscriptionEntry"
					cssClass="entry-display-style"
					keyProperty="commerceSubscriptionEntryId"
					modelVar="commerceSubscriptionEntry"
				>

					<%
					PortletURL rowURL = renderResponse.createRenderURL();

					rowURL.setParameter("mvcRenderCommandName", "editCommerceSubscriptionEntry");
					rowURL.setParameter("redirect", currentURL);
					rowURL.setParameter("commerceSubscriptionEntryId", String.valueOf(commerceSubscriptionEntry.getCommerceSubscriptionEntryId()));

					CommerceOrderItem commerceOrderItem = commerceSubscriptionEntry.fetchCommerceOrderItem();

					CommerceOrder commerceOrder = null;

					if (commerceOrderItem != null) {
						commerceOrder = commerceOrderItem.getCommerceOrder();
					}
					%>

					<liferay-ui:search-container-column-text
						cssClass="important"
						href="<%= rowURL %>"
						name="subscription-id"
						property="commerceSubscriptionEntryId"
					/>

					<liferay-ui:search-container-column-text
						cssClass="table-cell-content"
						name="product"
						value="<%= commerceOrderItem.getName(languageId) %>"
					/>

					<liferay-ui:search-container-column-text
						name="subscription-info"
					>
						<liferay-commerce:subscription-info
							commerceOrderItemId="<%= commerceOrderItem.getCommerceOrderItemId() %>"
							CPInstanceId="<%= commerceSubscriptionEntry.getCPInstanceId() %>"
							showDuration="<%= false %>"
						/>
					</liferay-ui:search-container-column-text>

					<liferay-ui:search-container-column-text
						name="remaining-cycles"
						value="<%= commerceSubscriptionEntryDisplayContext.getCommerceSubscriptionEntryRemainingCycles(commerceSubscriptionEntry) %>"
					/>

					<liferay-ui:search-container-column-text
						name="ends"
					>
						<c:choose>
							<c:when test="<%= commerceSubscriptionEntry.getMaxSubscriptionCycles() == 0 %>">
								<%= LanguageUtil.get(request, "never-ends") %>
							</c:when>
							<c:when test="<%= commerceSubscriptionEntry.getMaxSubscriptionCycles() == 1 %>">
								<%= LanguageUtil.get(request, "after-1-cycle") %>
							</c:when>
							<c:otherwise>
								<%= LanguageUtil.format(request, "after-x-cycles", commerceSubscriptionEntry.getMaxSubscriptionCycles()) %>
							</c:otherwise>
						</c:choose>
					</liferay-ui:search-container-column-text>

					<liferay-ui:search-container-column-text
						name="status"
						value="<%= LanguageUtil.get(request, CommerceSubscriptionEntryConstants.getSubscriptionStatusLabel(commerceSubscriptionEntry.getSubscriptionStatus())) %>"
					/>

					<liferay-ui:search-container-column-text
						name="customer-name"
						value="<%= commerceOrder.getCommerceAccountName() %>"
					/>

					<liferay-ui:search-container-column-text
						name="customer-id"
						value="<%= String.valueOf(commerceOrder.getCommerceAccountId()) %>"
					/>

					<liferay-ui:search-container-column-jsp
						cssClass="entry-action-column"
						path="/subscription_entry_action.jsp"
					/>
				</liferay-ui:search-container-row>

				<liferay-ui:search-iterator
					markupView="lexicon"
				/>
			</liferay-ui:search-container>
		</div>
	</aui:form>
</div>

<aui:script>
	function <portlet:namespace />deleteCommerceSubscriptionEntries() {
		if (confirm('<liferay-ui:message key="are-you-sure-you-want-to-delete-the-selected-subscription-entries" />')) {
			var form = AUI.$(document.<portlet:namespace />fm);

			form.attr('method', 'post');
			form.fm('<%= Constants.CMD %>').val('<%= Constants.DELETE %>');
			form.fm('deleteCommerceSubscriptionEntryIds').val(Liferay.Util.listCheckedExcept(form, '<portlet:namespace />allRowIds'));

			submitForm(form);
		}
	}
</aui:script>