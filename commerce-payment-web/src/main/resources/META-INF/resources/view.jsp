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
CommercePaymentMethodsDisplayContext commercePaymentMethodsDisplayContext = (CommercePaymentMethodsDisplayContext)request.getAttribute(WebKeys.PORTLET_DISPLAY_CONTEXT);
%>

<c:if test="<%= commercePaymentMethodsDisplayContext.hasManageCommercePaymentMethodsPermission() %>">
	<liferay-frontend:management-bar
		searchContainerId="commercePaymentMethods"
	>
		<liferay-frontend:management-bar-filters>
			<liferay-frontend:management-bar-navigation
				navigationKeys='<%= new String[] {"all", "active", "inactive"} %>'
				portletURL="<%= commercePaymentMethodsDisplayContext.getPortletURL() %>"
			/>
		</liferay-frontend:management-bar-filters>

		<liferay-frontend:management-bar-buttons>
			<liferay-frontend:management-bar-display-buttons
				displayViews='<%= new String[] {"list"} %>'
				portletURL="<%= commercePaymentMethodsDisplayContext.getPortletURL() %>"
				selectedDisplayStyle="list"
			/>
		</liferay-frontend:management-bar-buttons>
	</liferay-frontend:management-bar>

	<div class="container-fluid-1280">
		<liferay-ui:search-container
			id="commercePaymentMethods"
			searchContainer="<%= commercePaymentMethodsDisplayContext.getSearchContainer() %>"
		>
			<liferay-ui:search-container-row
				className="com.liferay.commerce.model.CommercePaymentMethod"
				keyProperty="commercePaymentMethodId"
				modelVar="commercePaymentMethod"
			>

				<%
				String thumbnailSrc = commercePaymentMethod.getImageURL(themeDisplay);
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
				rowURL.setParameter(ActionRequest.ACTION_NAME, "editCommercePaymentMethod");
				rowURL.setParameter("redirect", currentURL);
				rowURL.setParameter("commercePaymentMethodId", String.valueOf(commercePaymentMethod.getCommercePaymentMethodId()));
				rowURL.setParameter("engineKey", commercePaymentMethod.getEngineKey());
				%>

				<liferay-ui:search-container-column-text
					cssClass="important table-cell-content"
					href="<%= rowURL %>"
					property="name"
				/>

				<liferay-ui:search-container-column-text
					cssClass="table-cell-content"
					property="description"
				/>

				<liferay-ui:search-container-column-text
					name="active"
				>
					<c:choose>
						<c:when test="<%= commercePaymentMethod.isActive() %>">
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
					path="/payment_method_action.jsp"
				/>
			</liferay-ui:search-container-row>

			<liferay-ui:search-iterator
				markupView="lexicon"
			/>
		</liferay-ui:search-container>
	</div>
</c:if>