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
CommerceWishListDisplayContext commerceWishListDisplayContext = (CommerceWishListDisplayContext)request.getAttribute(WebKeys.PORTLET_DISPLAY_CONTEXT);

SearchContainer<CommerceWishListItem> commerceWishListItemsSearchContainer = commerceWishListDisplayContext.getCommerceWishListItemsSearchContainer();

portletDisplay.setShowBackIcon(true);
portletDisplay.setURLBack(redirect);
%>

<c:if test="<%= commerceWishListDisplayContext.hasManageCommerceWishListsPermission() %>">
	<liferay-frontend:management-bar
		includeCheckBox="<%= true %>"
		searchContainerId="commerceWishListItems"
	>
		<liferay-frontend:management-bar-filters>
			<liferay-frontend:management-bar-sort
				orderByCol="<%= commerceWishListItemsSearchContainer.getOrderByCol() %>"
				orderByType="<%= commerceWishListItemsSearchContainer.getOrderByType() %>"
				orderColumns='<%= new String[] {"create-date"} %>'
				portletURL="<%= commerceWishListDisplayContext.getPortletURL() %>"
			/>
		</liferay-frontend:management-bar-filters>

		<liferay-frontend:management-bar-action-buttons>
			<liferay-frontend:management-bar-button
				href='<%= "javascript:" + renderResponse.getNamespace() + "deleteCommerceWishListItems();" %>'
				icon="times"
				label="delete"
			/>
		</liferay-frontend:management-bar-action-buttons>
	</liferay-frontend:management-bar>

	<portlet:actionURL name="editCommerceWishListItem" var="editCommerceWishListItemActionURL" />

	<div class="container-fluid-1280">
		<aui:form action="<%= editCommerceWishListItemActionURL %>" method="post" name="fm">
			<aui:input name="<%= Constants.CMD %>" type="hidden" value="<%= Constants.DELETE %>" />
			<aui:input name="redirect" type="hidden" value="<%= currentURL %>" />
			<aui:input name="deleteCommerceWishListItemIds" type="hidden" />

			<liferay-ui:search-container
				id="commerceWishListItems"
				searchContainer="<%= commerceWishListItemsSearchContainer %>"
			>
				<liferay-ui:search-container-row
					className="com.liferay.commerce.wish.list.model.CommerceWishListItem"
					keyProperty="commerceWishListItemId"
					modelVar="commerceWishListItem"
				>

					<%
					CPDefinition cpDefinition = commerceWishListItem.getCPDefinition();
					%>

					<liferay-ui:search-container-column-image
						name="product"
						src="<%= cpDefinition.getDefaultImageThumbnailSrc() %>"
					/>

					<liferay-ui:search-container-column-text
						cssClass="table-cell-content"
						name="description"
					>
						<a class="font-weight-bold" href="<%= commerceWishListDisplayContext.getCPDefinitionURL(cpDefinition.getCPDefinitionId(), themeDisplay) %>">
							<%= HtmlUtil.escape(cpDefinition.getName(themeDisplay.getLanguageId())) %>
						</a>

						<h6 class="text-default">
							<%= HtmlUtil.escape(commerceWishListDisplayContext.getCommerceWishListItemDescription(commerceWishListItem)) %>
						</h6>
					</liferay-ui:search-container-column-text>

					<liferay-ui:search-container-column-text
						name="price"
						value="<%= commerceWishListDisplayContext.getCommerceWishListItemPrice(commerceWishListItem) %>"
					/>

					<liferay-ui:search-container-column-jsp
						colspan="<%= 2 %>"
						path="/wish_list_item_action.jsp"
					/>
				</liferay-ui:search-container-row>

				<liferay-ui:search-iterator
					markupView="lexicon"
				/>
			</liferay-ui:search-container>
		</aui:form>
	</div>

	<aui:script>
		function <portlet:namespace />deleteCommerceWishListItems() {
			if (confirm('<liferay-ui:message key="are-you-sure-you-want-to-delete-the-selected-items" />')) {
				var form = AUI.$(document.<portlet:namespace />fm);

				form.fm('deleteCommerceWishListItemIds').val(Liferay.Util.listCheckedExcept(form, '<portlet:namespace />allRowIds'));

				submitForm(form);
			}
		}
	</aui:script>
</c:if>