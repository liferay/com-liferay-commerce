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
CommerceShippingMethodsDisplayContext commerceShippingMethodsDisplayContext = (CommerceShippingMethodsDisplayContext)request.getAttribute(WebKeys.PORTLET_DISPLAY_CONTEXT);
%>

<c:if test="<%= commerceShippingMethodsDisplayContext.hasManageCommerceShippingMethodsPermission() %>">
	<liferay-frontend:management-bar
		searchContainerId="commerceShippingMethods"
	>
		<liferay-frontend:management-bar-filters>
			<liferay-frontend:management-bar-navigation
				navigationKeys='<%= new String[] {"all", "active", "inactive"} %>'
				portletURL="<%= commerceShippingMethodsDisplayContext.getPortletURL() %>"
			/>
		</liferay-frontend:management-bar-filters>

		<liferay-frontend:management-bar-buttons>
			<liferay-frontend:management-bar-display-buttons
				displayViews='<%= new String[] {"list"} %>'
				portletURL="<%= commerceShippingMethodsDisplayContext.getPortletURL() %>"
				selectedDisplayStyle="list"
			/>
		</liferay-frontend:management-bar-buttons>
	</liferay-frontend:management-bar>

	<div class="container-fluid-1280">
		<liferay-ui:search-container
			id="commerceShippingMethods"
			searchContainer="<%= commerceShippingMethodsDisplayContext.getSearchContainer() %>"
		>
			<liferay-ui:search-container-row
				className="com.liferay.commerce.model.CommerceShippingMethod"
				keyProperty="commerceShippingMethodId"
				modelVar="commerceShippingMethod"
			>

				<%
				String thumbnailSrc = commerceShippingMethod.getImageURL(themeDisplay);
				%>

				<c:choose>
					<c:when test="<%= Validator.isNotNull(thumbnailSrc) %>">
						<liferay-ui:search-container-column-image
							cssClass="table-cell-content"
							name="icon"
							src="<%= thumbnailSrc %>"
						/>
					</c:when>
					<c:otherwise>
						<liferay-ui:search-container-column-icon
							icon="documents-and-media"
							name="icon"
						/>
					</c:otherwise>
				</c:choose>

				<%
				PortletURL rowURL = renderResponse.createActionURL();

				rowURL.setParameter(Constants.CMD, Constants.EDIT);
				rowURL.setParameter(ActionRequest.ACTION_NAME, "editCommerceShippingMethod");
				rowURL.setParameter("redirect", currentURL);
				rowURL.setParameter("commerceShippingMethodId", String.valueOf(commerceShippingMethod.getCommerceShippingMethodId()));
				rowURL.setParameter("engineKey", commerceShippingMethod.getEngineKey());
				%>

				<liferay-ui:search-container-column-text
					cssClass="important table-cell-content"
					href="<%= rowURL %>"
					name="name"
					value="<%= HtmlUtil.escape(commerceShippingMethod.getName(locale)) %>"
				/>

				<liferay-ui:search-container-column-text
					cssClass="table-cell-content"
					name="description"
					value="<%= HtmlUtil.escape(commerceShippingMethod.getDescription(locale)) %>"
				/>

				<liferay-ui:search-container-column-text
					name="active"
				>
					<c:choose>
						<c:when test="<%= commerceShippingMethod.isActive() %>">
							<liferay-ui:icon
								cssClass="commerce-admin-icon-check"
								icon="check"
								markupView="lexicon"
							/>
						</c:when>
						<c:otherwise>
							<liferay-ui:icon
								cssClass="commerce-admin-icon-times"
								icon="times"
								markupView="lexicon"
							/>
						</c:otherwise>
					</c:choose>
				</liferay-ui:search-container-column-text>

				<liferay-ui:search-container-column-text
					property="priority"
				/>

				<liferay-ui:search-container-column-jsp
					cssClass="entry-action-column"
					path="/shipping_method_action.jsp"
				/>
			</liferay-ui:search-container-row>

			<liferay-ui:search-iterator
				markupView="lexicon"
			/>
		</liferay-ui:search-container>
	</div>
</c:if>