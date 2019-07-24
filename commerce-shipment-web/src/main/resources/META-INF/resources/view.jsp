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
CommerceShipmentDisplayContext commerceShipmentDisplayContext = (CommerceShipmentDisplayContext)request.getAttribute(WebKeys.PORTLET_DISPLAY_CONTEXT);

PortletURL portletURL = commerceShipmentDisplayContext.getPortletURL();

portletURL.setParameter("searchContainerId", "commerceShipments");

request.setAttribute("view.jsp-portletURL", portletURL);
%>

<c:if test="<%= commerceShipmentDisplayContext.hasManageCommerceShipmentsPermission() %>">
	<div class="container-fluid-1280">
		<liferay-frontend:management-bar
			searchContainerId="commerceShipments"
		>
			<liferay-frontend:management-bar-buttons>
				<liferay-frontend:management-bar-display-buttons
					displayViews='<%= new String[] {"list"} %>'
					portletURL="<%= PortletURLUtil.clone(portletURL, liferayPortletResponse) %>"
					selectedDisplayStyle="list"
				/>

				<portlet:renderURL var="addCommerceShipmentURL">
					<portlet:param name="mvcRenderCommandName" value="editCommerceShipment" />
					<portlet:param name="redirect" value="<%= currentURL %>" />
				</portlet:renderURL>

				<liferay-frontend:add-menu
					inline="<%= true %>"
				>
					<liferay-frontend:add-menu-item
						title='<%= LanguageUtil.get(request, "add-shipment") %>'
						url="<%= addCommerceShipmentURL %>"
					/>
				</liferay-frontend:add-menu>
			</liferay-frontend:management-bar-buttons>

			<liferay-frontend:management-bar-filters>
				<liferay-frontend:management-bar-navigation
					navigationKeys="<%= commerceShipmentDisplayContext.getNavigationKeys() %>"
					portletURL="<%= PortletURLUtil.clone(portletURL, liferayPortletResponse) %>"
				/>

				<liferay-frontend:management-bar-sort
					orderByCol="<%= commerceShipmentDisplayContext.getOrderByCol() %>"
					orderByType="<%= commerceShipmentDisplayContext.getOrderByType() %>"
					orderColumns='<%= new String[] {"create-date", "expected-delivery-date", "shipment-number", "shipping-date"} %>'
					portletURL="<%= PortletURLUtil.clone(portletURL, liferayPortletResponse) %>"
				/>
			</liferay-frontend:management-bar-filters>
		</liferay-frontend:management-bar>

		<div id="<portlet:namespace />shipmentsContainer">
			<aui:form action="<%= portletURL.toString() %>" method="post" name="fm">
				<aui:input name="<%= Constants.CMD %>" type="hidden" />
				<aui:input name="redirect" type="hidden" value="<%= portletURL.toString() %>" />
				<aui:input name="deleteCommerceShipmentIds" type="hidden" />

				<div class="orders-container" id="<portlet:namespace />entriesContainer">
					<liferay-ui:search-container
						id="commerceShipments"
						iteratorURL="<%= portletURL %>"
						searchContainer="<%= commerceShipmentDisplayContext.getSearchContainer() %>"
					>
						<liferay-ui:search-container-row
							className="com.liferay.commerce.model.CommerceShipment"
							cssClass="entry-display-style"
							keyProperty="commerceShipmentId"
							modelVar="commerceShipment"
						>

							<%
							PortletURL rowURL = renderResponse.createRenderURL();

							rowURL.setParameter("mvcRenderCommandName", "viewCommerceShipmentDetail");
							rowURL.setParameter("commerceShipmentId", String.valueOf(commerceShipment.getCommerceShipmentId()));
							%>

							<liferay-ui:search-container-column-text
								cssClass="important"
								href="<%= rowURL %>"
								name="shipment-number"
								property="commerceShipmentId"
							/>

							<%
							long commerceOrderId = commerceShipmentDisplayContext.getCommerceOrderId(commerceShipment.getCommerceShipmentId());
							%>

							<liferay-ui:search-container-column-text
								cssClass="important"
								href="<%= commerceShipmentDisplayContext.getCommerceOrderUrl(commerceOrderId) %>"
								name="order-number"
								value="<%= (commerceOrderId == 0) ? StringPool.BLANK : String.valueOf(commerceOrderId) %>"
							/>

							<liferay-ui:search-container-column-text
								cssClass="table-cell-content"
								name="shipping-address"
							>

								<%
								CommerceAddress commerceAddress = commerceShipment.fetchCommerceAddress();
								%>

								<c:if test="<%= commerceAddress != null %>">

									<%
									CommerceRegion commerceRegion = commerceAddress.getCommerceRegion();

									String commerceRegionCode = StringPool.BLANK;

									if (commerceRegion != null) {
										commerceRegionCode = commerceRegion.getCode() + StringPool.SPACE;
									}
									%>

									<p><%= HtmlUtil.escape(commerceAddress.getStreet1()) %></p>
									<p><%= HtmlUtil.escape(commerceAddress.getCity() + StringPool.COMMA_AND_SPACE + commerceRegionCode + commerceAddress.getZip()) %></p>
								</c:if>
							</liferay-ui:search-container-column-text>

							<liferay-ui:search-container-column-text
								cssClass="table-cell-content"
								name="carrier-and-tracking-number"
							>
								<p><%= HtmlUtil.escape(commerceShipment.getCarrier()) %></p>
								<p><%= HtmlUtil.escape(commerceShipment.getTrackingNumber()) %></p>
							</liferay-ui:search-container-column-text>

							<liferay-ui:search-container-column-date
								cssClass="table-cell-content"
								name="shipping-date"
								property="shippingDate"
							/>

							<liferay-ui:search-container-column-date
								cssClass="table-cell-content"
								name="expected-delivery-date"
								property="expectedDate"
							/>

							<liferay-ui:search-container-column-text
								name="status"
								value="<%= commerceShipmentDisplayContext.getCommerceShipmentStatusLabel(commerceShipment.getStatus()) %>"
							/>

							<liferay-ui:search-container-column-jsp
								cssClass="entry-action-column"
								path="/shipment_action.jsp"
							/>
						</liferay-ui:search-container-row>

						<liferay-ui:search-iterator
							displayStyle="<%= commerceShipmentDisplayContext.getDisplayStyle() %>"
							markupView="lexicon"
						/>
					</liferay-ui:search-container>
				</div>
			</aui:form>
		</div>
	</div>

	<aui:script>
		function <portlet:namespace />deleteCommerceShipments() {
			if (confirm('<liferay-ui:message key="are-you-sure-you-want-to-delete-the-selected-shipments" />')) {
				var form = AUI.$(document.<portlet:namespace />fm);

				form.fm('deleteCommerceShipmentIds').val(Liferay.Util.listCheckedExcept(form, '<portlet:namespace />allRowIds'));

				submitForm(form);
			}
		}
	</aui:script>
</c:if>