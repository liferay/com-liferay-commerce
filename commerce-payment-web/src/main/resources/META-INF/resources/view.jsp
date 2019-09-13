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
CommercePaymentMethodGroupRelsDisplayContext commercePaymentMethodGroupRelsDisplayContext = (CommercePaymentMethodGroupRelsDisplayContext)request.getAttribute(WebKeys.PORTLET_DISPLAY_CONTEXT);
%>

<c:if test="<%= commercePaymentMethodGroupRelsDisplayContext.hasManageCommercePaymentMethodGroupRelsPermission() %>">
	<liferay-frontend:management-bar
		searchContainerId="commercePaymentMethodGroupRels"
	>
		<liferay-frontend:management-bar-filters>
			<liferay-frontend:management-bar-navigation
				navigationKeys='<%= new String[] {"all", "active", "inactive"} %>'
				portletURL="<%= commercePaymentMethodGroupRelsDisplayContext.getPortletURL() %>"
			/>
		</liferay-frontend:management-bar-filters>

		<liferay-frontend:management-bar-buttons>
			<liferay-frontend:management-bar-display-buttons
				displayViews='<%= new String[] {"list"} %>'
				portletURL="<%= commercePaymentMethodGroupRelsDisplayContext.getPortletURL() %>"
				selectedDisplayStyle="list"
			/>

			<liferay-frontend:add-menu
				inline="<%= true %>"
			>

				<%
				for (CommercePaymentMethodGroupRel curCommercePaymentMethodGroupRel : commercePaymentMethodGroupRelsDisplayContext.getDefaultCommercePaymentMethodGroupRels()) {
				%>

					<liferay-portlet:renderURL var="addCommercePaymentMethodGroupRelUrl">
						<portlet:param name="mvcRenderCommandName" value="editCommercePaymentMethodGroupRel" />
						<portlet:param name="commercePaymentMethodGroupRelId" value="<%= String.valueOf(curCommercePaymentMethodGroupRel.getCommercePaymentMethodGroupRelId()) %>" />
						<portlet:param name="engineKey" value="<%= curCommercePaymentMethodGroupRel.getEngineKey() %>" />
						<portlet:param name="redirect" value="<%= currentURL %>" />
					</liferay-portlet:renderURL>

					<liferay-frontend:add-menu-item
						title="<%= curCommercePaymentMethodGroupRel.getName(locale) %>"
						url="<%= addCommercePaymentMethodGroupRelUrl %>"
					/>

				<%
				}
				%>

			</liferay-frontend:add-menu>

		</liferay-frontend:management-bar-buttons>
	</liferay-frontend:management-bar>

	<div class="container-fluid-1280">
		<liferay-ui:search-container
			id="commercePaymentMethodGroupRels"
			searchContainer="<%= commercePaymentMethodGroupRelsDisplayContext.getSearchContainer() %>"
		>
			<liferay-ui:search-container-row
				className="com.liferay.commerce.payment.model.CommercePaymentMethodGroupRel"
				keyProperty="commercePaymentMethodGroupRelId"
				modelVar="commercePaymentMethodGroupRel"
			>

				<%
				String thumbnailSrc = commercePaymentMethodGroupRel.getImageURL(themeDisplay);
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
				rowURL.setParameter(ActionRequest.ACTION_NAME, "editCommercePaymentMethodGroupRel");
				rowURL.setParameter("redirect", currentURL);
				rowURL.setParameter("commercePaymentMethodGroupRelId", String.valueOf(commercePaymentMethodGroupRel.getCommercePaymentMethodGroupRelId()));
				rowURL.setParameter("engineKey", commercePaymentMethodGroupRel.getEngineKey());
				%>

				<liferay-ui:search-container-column-text
					cssClass="important table-cell-content"
					href="<%= rowURL %>"
					name="name"
					value="<%= commercePaymentMethodGroupRel.getName(locale) %>"
				/>

				<liferay-ui:search-container-column-text
					cssClass="table-cell-content"
					name="description"
					value="<%= commercePaymentMethodGroupRel.getDescription(locale) %>"
				/>

				<liferay-ui:search-container-column-text
					name="active"
				>
					<c:choose>
						<c:when test="<%= commercePaymentMethodGroupRel.isActive() %>">
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