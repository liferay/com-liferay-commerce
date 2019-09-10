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
CommerceOrderEditDisplayContext commerceOrderEditDisplayContext = (CommerceOrderEditDisplayContext)request.getAttribute(WebKeys.PORTLET_DISPLAY_CONTEXT);

SearchContainer<CommerceOrderItem> commerceOrderItemsSearchContainer = commerceOrderEditDisplayContext.getCommerceOrderItemsSearchContainer();
PortletURL portletURL = commerceOrderEditDisplayContext.getCommerceOrderItemsPortletURL();
%>

<liferay-portlet:renderURL var="editURL" windowState="<%= LiferayWindowState.POP_UP.toString() %>">
	<portlet:param name="mvcRenderCommandName" value="editCommerceOrderShippingAddress" />
	<portlet:param name="commerceOrderId" value="<%= String.valueOf(commerceOrderEditDisplayContext.getCommerceOrderId()) %>" />
</liferay-portlet:renderURL>

<div class="container">
	<div class="row">
		<div class="col-md-4 d-flex">
			<commerce-ui:modal
				closeOnSubmit="<%= true %>"
				showCancel="<%= true %>"
				showSubmit="<%= true %>"
				size="lg"
				title="PLACEHOLDER Shipping address and method"
				triggerId="shipping-modal-id"
				url="<%= editURL %>"
			/>
			<commerce-ui:panel
				elementClasses="flex-fill"
				headerActionId="shipping-modal-id"
				headerActionLabel="edit"
				headerActionUrl="<%= editURL %>"
				title="shipping-address-and-method"
			>
				<div class="card-text">PO Box 467 New York NY 10002</div>
				<div class="card-text mt-3">
					<clay:icon
						symbol="geolocation"
					/>
					(Standard delivery)
				</div>
			</commerce-ui:panel>
		</div>

		<div class="col-md-4 d-flex">

			<!-- modal -->
			<commerce-ui:modal
				closeOnSubmit="<%= true %>"
				size="sm"
				title="shipping-address-and-method"
				triggerId="small-modal-id"
				url="<%= editURL %>"
			/>
			<!-- / modal -->
			<commerce-ui:panel
				elementClasses="flex-fill"
				headerActionIcon="plus"
				headerActionId="small-modal-id"
				title="billing-address"
			>
				<div class="card-text">PO Box 467 New York NY 10002</div>
			</commerce-ui:panel>
		</div>

		<div class="col-md-4 d-flex">
			<commerce-ui:panel
				elementClasses="flex-fill"
				title="order-timeline"
			>
				<div id="order-timeline-root">
					<ol class="multi-step-indicator-label-top multi-step-nav multi-step-nav-collapse-sm">
						<li class="active multi-step-item multi-step-item-expand">
							<div class="multi-step-divider"></div>
							<div class="multi-step-indicator">
								<div class="multi-step-indicator-label">Confirmed</div>

								<span class="multi-step-icon" data-multi-step-icon="1"></span>
							</div>
						</li>
						<li class="disabled multi-step-item multi-step-item-expand">
							<div class="multi-step-divider"></div>
							<div class="multi-step-indicator">
								<div class="multi-step-indicator-label">Transmitted</div>

								<span class="multi-step-icon" data-multi-step-icon="2"></span>
							</div>
						</li>
						<li class="disabled multi-step-item multi-step-item-expand">
							<div class="multi-step-divider"></div>
							<div class="multi-step-indicator">
								<div class="multi-step-indicator-label">Shipped</div>

								<span class="multi-step-icon" data-multi-step-icon="3"></span>
							</div>
						</li>
						<li class="disabled multi-step-item">
							<div class="multi-step-divider"></div>
							<div class="multi-step-indicator">
								<div class="multi-step-indicator-label">Completed</div>

								<span class="multi-step-icon" data-multi-step-icon="4"></span>
							</div>
						</li>
					</ol>
				</div>
			</commerce-ui:panel>
		</div>
	</div>

	<div class="row">
		<div class="col-md-8">
			<commerce-ui:panel
				title="Items"
			>
				<liferay-frontend:management-bar
					includeCheckBox="<%= true %>"
					searchContainerId="commerceOrderItems"
				>
					<liferay-frontend:management-bar-filters>
						<liferay-frontend:management-bar-sort
							orderByCol="<%= commerceOrderItemsSearchContainer.getOrderByCol() %>"
							orderByType="<%= commerceOrderItemsSearchContainer.getOrderByType() %>"
							orderColumns="<%= commerceOrderItemsSearchContainer.getOrderableHeaders() %>"
							portletURL="<%= portletURL %>"
						/>

						<li>
							<aui:form action="<%= portletURL %>" method="get" name="fm">
								<liferay-portlet:renderURLParams portletURL="<%= portletURL %>" />

								<liferay-ui:search-form
									page="/order/item_search.jsp"
									servletContext="<%= application %>"
								/>
							</aui:form>
						</li>
					</liferay-frontend:management-bar-filters>

					<liferay-frontend:management-bar-buttons>
						<portlet:actionURL name="editCommerceOrderItem" var="addCommerceOrderItemURL" />

						<aui:form action="<%= addCommerceOrderItemURL %>" cssClass="hide" name="addCommerceOrderItemFm">
							<aui:input name="<%= Constants.CMD %>" type="hidden" value="<%= Constants.ADD %>" />
							<aui:input name="redirect" type="hidden" value="<%= currentURL %>" />
							<aui:input name="commerceOrderId" type="hidden" value="<%= commerceOrderEditDisplayContext.getCommerceOrderId() %>" />
							<aui:input name="cpInstanceIds" type="hidden" value="" />
						</aui:form>

						<liferay-frontend:add-menu
							inline="<%= true %>"
						>
							<liferay-frontend:add-menu-item
								id="addCommerceOrderItem"
								title='<%= LanguageUtil.get(request, "add-item") %>'
								url="javascript:;"
							/>
						</liferay-frontend:add-menu>
					</liferay-frontend:management-bar-buttons>

					<liferay-frontend:management-bar-action-buttons>
						<liferay-frontend:management-bar-button
							href='<%= "javascript:" + renderResponse.getNamespace() + "deleteCommerceOrderItems();" %>'
							icon="times"
							label="delete"
						/>
					</liferay-frontend:management-bar-action-buttons>
				</liferay-frontend:management-bar>

				<div class="container-fluid-1280">
					<liferay-ui:search-container
						id="commerceOrderItems"
						searchContainer="<%= commerceOrderItemsSearchContainer %>"
					>
						<liferay-ui:search-container-row
							className="com.liferay.commerce.model.CommerceOrderItem"
							escapedModel="<%= true %>"
							keyProperty="commerceOrderItemId"
							modelVar="commerceOrderItem"
						>

							<%
							PortletURL rowURL = renderResponse.createRenderURL();

							rowURL.setParameter("mvcRenderCommandName", "editCommerceOrderItem");
							rowURL.setParameter("redirect", currentURL);
							rowURL.setParameter("commerceOrderId", String.valueOf(commerceOrderItem.getCommerceOrderId()));
							rowURL.setParameter("commerceOrderItemId", String.valueOf(commerceOrderItem.getCommerceOrderItemId()));

							CommerceOrder commerceOrder = commerceOrderItem.getCommerceOrder();
							%>

							<liferay-ui:search-container-column-text
								cssClass="important table-cell-content"
								href="<%= rowURL %>"
								property="sku"
							/>

							<liferay-ui:search-container-column-text
								cssClass="table-cell-content"
								name="name"
								value="<%= commerceOrderItem.getName(locale) %>"
							/>

							<%
							CommerceProductPrice commerceProductPrice = commerceOrderEditDisplayContext.getCommerceProductPrice(commerceOrderItem);
							%>

							<liferay-ui:search-container-column-text
								cssClass="table-cell-content"
								name="price"
							>
								<c:if test="<%= commerceProductPrice != null %>">

									<%
									CommerceMoney unitPrice = commerceProductPrice.getUnitPrice();
									%>

									<div class="value-section">
										<span class="commerce-value">
											<%= HtmlUtil.escape(unitPrice.format(locale)) %>
										</span>
										<span class="commerce-subscription-info">
											<liferay-commerce:subscription-info
												commerceOrderItemId="<%= commerceOrder.isOpen() ? 0 : commerceOrderItem.getCommerceOrderItemId() %>"
												CPInstanceId="<%= commerceOrderItem.getCPInstanceId() %>"
												showDuration="<%= false %>"
											/>
										</span>
									</div>
								</c:if>
							</liferay-ui:search-container-column-text>

							<liferay-ui:search-container-column-text
								name="discount"
							>
								<c:if test="<%= commerceProductPrice != null %>">

									<%
									CommerceDiscountValue commerceDiscountValue = commerceProductPrice.getDiscountValue();
									%>

									<c:if test="<%= commerceDiscountValue != null %>">

										<%
										CommerceMoney discountAmount = commerceDiscountValue.getDiscountAmount();
										%>

										<div class="value-section">
											<span class="commerce-value">
												<%= HtmlUtil.escape(discountAmount.format(locale)) %>
											</span>
										</div>
									</c:if>
								</c:if>
							</liferay-ui:search-container-column-text>

							<liferay-ui:search-container-column-text
								property="quantity"
							/>

							<liferay-ui:search-container-column-text
								name="total"
							>
								<c:if test="<%= commerceProductPrice != null %>">

									<%
									CommerceMoney finalPrice = commerceProductPrice.getFinalPrice();
									%>

									<div class="value-section">
										<span class="commerce-value">
											<%= HtmlUtil.escape(finalPrice.format(locale)) %>
										</span>
									</div>
								</c:if>
							</liferay-ui:search-container-column-text>

							<liferay-ui:search-container-column-jsp
								cssClass="entry-action-column"
								path="/order/item_action.jsp"
							/>
						</liferay-ui:search-container-row>

						<liferay-ui:search-iterator
							markupView="lexicon"
						/>
					</liferay-ui:search-container>
				</div>
			</commerce-ui:panel>
		</div>

		<div class="col-md-4">
			<commerce-ui:panel
				showMoreUrl="/test"
				title="recent-order-notes"
			>
				<div class="card-text">
					Vivamus magna justo, lacinia eget consectetur sed, convallis at tellus. Nulla porttitor accumsan tincidunt. Donec sollicitudin molestie malesuada. Lorem ipsum dolor sit amet, consectetur adipiscing elit. Lorem ipsum dolor sit amet, consectetur adipiscing elit. Quisque velit nisi, pretium ut lacinia in, elementum id enim.
				</div>
			</commerce-ui:panel>

			<commerce-ui:panel
				headerActionLabel="edit"
				title="order-summary"
			>
				<div id="order-timeline-root"></div>
			</commerce-ui:panel>
		</div>
	</div>
</div>

<aui:script>
	function <portlet:namespace />deleteCommerceOrderItems() {
		if (confirm('<liferay-ui:message key="are-you-sure-you-want-to-delete-the-selected-order-items" />')) {
			var form = AUI.$(document.<portlet:namespace />fm);

			form.attr('method', 'post');
			form.fm('<%= Constants.CMD %>').val('<%= Constants.DELETE %>');
			form.fm('deleteCommerceOrderItemIds').val(Liferay.Util.listCheckedExcept(form, '<portlet:namespace />allRowIds'));

			submitForm(form, '<portlet:actionURL name="editCommerceOrderItem" />');
		}
	}
</aui:script>

<aui:script use="liferay-item-selector-dialog">
	$('#<portlet:namespace />addCommerceOrderItem').on(
		'click',
		function(event) {
			event.preventDefault();

			var itemSelectorDialog = new A.LiferayItemSelectorDialog(
				{
					eventName: 'productInstancesSelectItem',
					on: {
						selectedItemChange: function(event) {
							var selectedItems = event.newVal;

							if (selectedItems) {
								$('#<portlet:namespace />cpInstanceIds').val(selectedItems);

								var addCommerceOrderItemFm = $('#<portlet:namespace />addCommerceOrderItemFm');

								submitForm(addCommerceOrderItemFm);
							}
						}
					},
					title: '<liferay-ui:message arguments="<%= commerceOrderEditDisplayContext.getCommerceOrderId() %>" key="add-new-product-to-order-x" />',
					url: '<%= commerceOrderEditDisplayContext.getItemSelectorUrl() %>'
				}
			);

			itemSelectorDialog.open();
		}
	);
</aui:script>