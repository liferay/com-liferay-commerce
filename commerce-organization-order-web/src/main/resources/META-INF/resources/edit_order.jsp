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
CommerceOrganizationOrderDisplayContext commerceOrganizationOrderDisplayContext = (CommerceOrganizationOrderDisplayContext)request.getAttribute(WebKeys.PORTLET_DISPLAY_CONTEXT);

CommerceOrder commerceOrder = commerceOrganizationOrderDisplayContext.getCommerceOrder();

CommerceOrderPrice commerceOrderPrice = commerceOrganizationOrderDisplayContext.getCommerceOrderPrice();

CommerceMoney shippingValue = commerceOrderPrice.getShippingValue();
CommerceDiscountValue shippingDiscountValue = commerceOrderPrice.getShippingDiscountValue();
CommerceMoney subtotal = commerceOrderPrice.getSubtotal();
CommerceDiscountValue subtotalDiscountValue = commerceOrderPrice.getSubtotalDiscountValue();
CommerceMoney taxValue = commerceOrderPrice.getTaxValue();
CommerceDiscountValue totalDiscountValue = commerceOrderPrice.getTotalDiscountValue();
CommerceMoney totalOrder = commerceOrderPrice.getTotal();

List<CommerceOrderValidatorResult> commerceOrderValidatorResults = new ArrayList<>();
%>

<portlet:actionURL name="editCommerceOrder" var="editCommerceOrderActionURL">
	<portlet:param name="mvcRenderCommandName" value="editCommerceOrder" />
</portlet:actionURL>

<liferay-ui:error exception="<%= CommerceOrderValidatorException.class %>">

	<%
	CommerceOrderValidatorException cove = (CommerceOrderValidatorException)errorException;

	if (cove != null) {
		commerceOrderValidatorResults = cove.getCommerceOrderValidatorResults();
	}

	for (CommerceOrderValidatorResult commerceOrderValidatorResult : commerceOrderValidatorResults) {
	%>

		<c:choose>
			<c:when test="<%= commerceOrderValidatorResult.hasArgument() %>">
				<liferay-ui:message arguments="<%= commerceOrderValidatorResult.getArgument() %>" key="<%= commerceOrderValidatorResult.getMessage() %>" />
			</c:when>
			<c:otherwise>
				<liferay-ui:message key="<%= commerceOrderValidatorResult.getMessage() %>" />
			</c:otherwise>
		</c:choose>

	<%
	}
	%>

</liferay-ui:error>

<div class="b2b-portlet-content-header">
	<liferay-ui:icon
		cssClass="header-back-to"
		icon="order-arrow-down"
		id="TabsBack"
		label="<%= false %>"
		markupView="lexicon"
		message='<%= LanguageUtil.get(resourceBundle, "back") %>'
		method="get"
		url="<%= layout.getRegularURL(request) %>"
	/>

	<div class="autofit-float autofit-row header-title-bar">
		<div class="autofit-col autofit-col-expand">
			<liferay-ui:header
				backURL="<%= redirect %>"
				localizeTitle="<%= false %>"
				showBackURL="<%= false %>"
				title='<%= LanguageUtil.format(request, "order-x", commerceOrder.getCommerceOrderId()) %>'
			/>
		</div>

		<div class="autofit-col">
			<liferay-ui:icon
				icon="print"
				iconCssClass="inline-item inline-item-after"
				label="<%= true %>"
				linkCssClass="lfr-icon-item-reverse link-outline link-outline-borderless link-outline-secondary"
				markupView="lexicon"
				message="print"
				method="get"
				url="javascript:window.print();"
			/>
		</div>

		<div class="autofit-col">

			<%
			request.setAttribute("order_notes.jsp-showLabel", Boolean.TRUE);
			request.setAttribute("order_notes.jsp-taglibLinkCssClass", "link-outline link-outline-borderless link-outline-secondary lfr-icon-item-reverse");
			%>

			<liferay-util:include page="/order_notes.jsp" servletContext="<%= application %>" />
		</div>

		<c:if test="<%= commerceOrder.isOpen() %>">
			<div class="autofit-col">
				<liferay-commerce:order-transitions
					commerceOrderId="<%= commerceOrder.getCommerceOrderId() %>"
					cssClass="btn btn-secondary btn-sm"
				/>
			</div>

			<c:if test="<%= commerceOrganizationOrderDisplayContext.hasPermission(commerceOrder, ActionKeys.UPDATE) %>">
				<div class="autofit-col">
					<liferay-ui:icon-menu
						direction="right"
						icon="<%= StringPool.BLANK %>"
						markupView="lexicon"
						message="<%= StringPool.BLANK %>"
						showWhenSingleIcon="<%= true %>"
						triggerCssClass="component-action"
					>
						<liferay-ui:icon
							message="edit"
							url='<%= "javascript:" + renderResponse.getNamespace() + "editCommerceOrder();" %>'
						/>

						<portlet:actionURL name="editCommerceOrderItem" var="deleteURL">
							<portlet:param name="<%= Constants.CMD %>" value="<%= Constants.RESET %>" />
							<portlet:param name="redirect" value="<%= currentURL %>" />
							<portlet:param name="commerceOrderId" value="<%= String.valueOf(commerceOrder.getCommerceOrderId()) %>" />
						</portlet:actionURL>

						<liferay-ui:icon-delete
							message="delete-all"
							url="<%= deleteURL %>"
						/>
					</liferay-ui:icon-menu>
				</div>
			</c:if>
		</c:if>
	</div>
</div>

<aui:form action="<%= editCommerceOrderActionURL %>" cssClass="order-details-container" method="post" name="fm">
	<aui:input name="<%= Constants.CMD %>" type="hidden" />
	<aui:input name="commerceOrderId" type="hidden" value="<%= String.valueOf(commerceOrder.getCommerceOrderId()) %>" />

	<div class="autofit-float autofit-row order-details-header">
		<div class="autofit-col autofit-col-expand">
			<div class="autofit-section">
				<h3 class="order-details-title"><liferay-ui:message key="order-date" /></h3>

				<div class="order-date order-details-subtitle">
					<%= commerceOrganizationOrderDisplayContext.getCommerceOrderDate(commerceOrder) %>
				</div>

				<div class="order-time">
					<%= commerceOrganizationOrderDisplayContext.getCommerceOrderTime(commerceOrder) %>
				</div>
			</div>
		</div>

		<div class="autofit-col autofit-col-expand">
			<div class="autofit-section">
				<h3 class="order-details-title"><liferay-ui:message key="customer" /></h3>

				<div class="customer-name order-details-subtitle">
					<%= HtmlUtil.escape(commerceOrder.getCustomerName()) %>
				</div>

				<div class="customer-id">
					<%= commerceOrder.getCustomerId() %>
				</div>
			</div>
		</div>

		<div class="autofit-col autofit-col-expand">
			<div class="autofit-section">
				<h3 class="order-details-title"><liferay-ui:message key="payment" /></h3>

				<%
				String paymentMethodName = commerceOrganizationOrderDisplayContext.getCommerceOrderPaymentMethodName(commerceOrder);
				%>

				<c:if test="<%= Validator.isNotNull(paymentMethodName) %>">
					<div class="order-details-subtitle payment-method-name">
						<%= HtmlUtil.escape(paymentMethodName) %>
					</div>
				</c:if>

				<div class="order-details-subtitle purchase-order-number">
					<liferay-ui:message arguments="<%= HtmlUtil.escape(commerceOrder.getPurchaseOrderNumber()) %>" key="purchase-order-number-x" translateArguments="<%= false %>" />
				</div>
			</div>
		</div>

		<div class="autofit-col autofit-col-expand">
			<div class="autofit-section">
				<h3 class="order-details-title"><liferay-ui:message key="status" /></h3>

				<div class="order-details-subtitle order-status">
					<%= commerceOrganizationOrderDisplayContext.getCommerceOrderStatus(commerceOrder) %>
				</div>
			</div>
		</div>

		<c:if test="<%= !commerceOrder.isOpen() %>">
			<div class="autofit-col autofit-col-expand order-details-reorder">
				<div class="autofit-section">
					<aui:button icon="icon-refresh" iconAlign="right" onClick='<%= renderResponse.getNamespace() + "reorderCommerceOrder();" %>' primary="<%= true %>" value="reorder" />
				</div>
			</div>
		</c:if>
	</div>
</aui:form>

<liferay-portlet:actionURL name="editCommerceOrderItem" var="editCommerceOrderItemURL" />

<liferay-ui:search-container
	cssClass="order-details-table"
	searchContainer="<%= commerceOrganizationOrderDisplayContext.getCommerceOrderItemsSearchContainer() %>"
>
	<liferay-ui:search-container-row
		className="com.liferay.commerce.model.CommerceOrderItem"
		keyProperty="commerceOrderItemId"
		modelVar="commerceOrderItem"
	>
		<liferay-ui:search-container-column-text
			property="sku"
		/>

		<liferay-ui:search-container-column-text
			name="name"
			value="<%= HtmlUtil.escape(commerceOrderItem.getName(locale)) %>"
		/>

		<%
		List<KeyValuePair> keyValuePairs = commerceOrganizationOrderDisplayContext.getKeyValuePairs(commerceOrderItem.getJson(), locale);

		StringJoiner stringJoiner = new StringJoiner(StringPool.COMMA);

		for (KeyValuePair keyValuePair : keyValuePairs) {
			stringJoiner.add(keyValuePair.getValue());
		}
		%>

		<liferay-ui:search-container-column-text
			name="description"
			value="<%= HtmlUtil.escape(stringJoiner.toString()) %>"
		/>

		<c:choose>
			<c:when test="<%= commerceOrder.isOpen() && CommerceOrderPermission.contains(permissionChecker, commerceOrder, ActionKeys.UPDATE) %>">
				<liferay-ui:search-container-column-text
					cssClass="order-item-quantity"
					name="quantity"
				>
					<aui:form action="<%= editCommerceOrderItemURL %>" method="post" name='<%= commerceOrderItem.getCommerceOrderItemId() + "fm" %>'>
						<aui:input name="<%= Constants.CMD %>" type="hidden" value="<%= Constants.UPDATE %>" />
						<aui:input name="redirect" type="hidden" value="<%= currentURL %>" />
						<aui:input name="commerceOrderItemId" type="hidden" value="<%= commerceOrderItem.getCommerceOrderItemId() %>" />

						<aui:model-context bean="<%= commerceOrderItem %>" model="<%= CommerceOrderItem.class %>" />

						<div class="form-group">
							<div class="input-group">
								<div class="input-group-item input-group-prepend">
									<liferay-commerce:quantity-input name="quantity" CPDefinitionId="<%= commerceOrderItem.getCPDefinitionId() %>" value="<%= commerceOrderItem.getQuantity() %>" useSelect="<%= false %>" />
								</div>

								<div class="input-group-append input-group-item input-group-item-shrink">
									<clay:button
										label='<%= LanguageUtil.get(resourceBundle, "update") %>'
										style="secondary"
										type="submit"
									/>
								</div>
							</div>
						</div>
					</aui:form>
				</liferay-ui:search-container-column-text>
			</c:when>
			<c:otherwise>
				<liferay-ui:search-container-column-text
					property="quantity"
				/>
			</c:otherwise>
		</c:choose>

		<%
		CommerceProductPrice commerceProductPrice = commerceOrganizationOrderDisplayContext.getCommerceProductPrice(commerceOrderItem);

		CommerceDiscountValue discountValue = commerceProductPrice.getDiscountValue();
		CommerceMoney finalPrice = commerceProductPrice.getFinalPrice();
		CommerceMoney unitPrice = commerceProductPrice.getUnitPrice();

		CPInstance cpInstance = commerceOrderItem.getCPInstance();
		%>

		<liferay-ui:search-container-column-text
			name="price"
		>
			<c:if test="<%= commerceProductPrice != null %>">
				<div class="value-section">
					<span class="commerce-value">
						<%= unitPrice.format(locale) %>
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

		<%
		CommerceMoney discountAmount = null;

		if (discountValue != null) {
			discountAmount = discountValue.getDiscountAmount();
		}
		%>

		<liferay-ui:search-container-column-text
			name="discount"
		>
			<div class="value-section">
				<span class="commerce-value">
					<%= (discountAmount == null) ? StringPool.BLANK : discountAmount.format(locale) %>
				</span>
			</div>
		</liferay-ui:search-container-column-text>

		<liferay-ui:search-container-column-text
			name="final-price"
		>
			<div class="value-section">
				<span class="commerce-value">
					<%= finalPrice.format(locale) %>
				</span>
			</div>
		</liferay-ui:search-container-column-text>

		<liferay-ui:search-container-column-text>

			<%
			String viewCommerceShipmentsURL = commerceOrganizationOrderDisplayContext.getViewCommerceShipmentsURL(commerceOrderItem.getCommerceOrderItemId());
			%>

			<c:if test="<%= Validator.isNotNull(viewCommerceShipmentsURL) %>">
				<liferay-ui:icon
					iconCssClass="glyphicon glyphicon-plane"
					url='<%= "javascript:" + renderResponse.getNamespace() + "viewCommerceOrderShipments('" + HtmlUtil.escapeJS(viewCommerceShipmentsURL) + "');" %>'
				/>
			</c:if>
		</liferay-ui:search-container-column-text>

		<c:if test="<%= commerceOrganizationOrderDisplayContext.hasPermission(commerceOrder, ActionKeys.UPDATE) && commerceOrder.isOpen() %>">
			<liferay-ui:search-container-column-text>
				<portlet:actionURL name="editCommerceOrderItem" var="deleteURL">
					<portlet:param name="<%= Constants.CMD %>" value="<%= Constants.DELETE %>" />
					<portlet:param name="redirect" value="<%= currentURL %>" />
					<portlet:param name="commerceOrderItemId" value="<%= String.valueOf(commerceOrderItem.getCommerceOrderItemId()) %>" />
				</portlet:actionURL>

				<liferay-ui:icon
					icon="times"
					markupView="lexicon"
					url="<%= deleteURL %>"
				/>
			</liferay-ui:search-container-column-text>
		</c:if>
	</liferay-ui:search-container-row>

	<liferay-ui:search-iterator
		markupView="lexicon"
	/>
</liferay-ui:search-container>

<aui:row>
	<aui:col width="<%= 70 %>" />

	<aui:col width="<%= 30 %>">
		<ul class="order-details-footer">
			<li class="autofit-row commerce-subtotal">
				<div class="autofit-col autofit-col-expand">
					<div class="commerce-description"><liferay-ui:message key="subtotal" /></div>
				</div>

				<div class="autofit-col">
					<div class="commerce-value"><%= HtmlUtil.escape(subtotal.format(locale)) %></div>
				</div>
			</li>

			<c:if test="<%= (subtotalDiscountValue != null) && (BigDecimal.ZERO.compareTo(subtotalDiscountValue.getDiscountPercentage()) < 0) %>">

				<%
				CommerceMoney subtotalDiscountAmount = subtotalDiscountValue.getDiscountAmount();
				%>

				<li class="autofit-row commerce-subtotal-discount">
					<div class="autofit-col autofit-col-expand">
						<div class="commerce-description"><liferay-ui:message key="subtotal-discount" /></div>
					</div>

					<div class="order-details-subtitle order-value">
						<%= HtmlUtil.escape(subtotalDiscountAmount.format(locale)) %>
					</div>
				</li>
				<li class="autofit-row commerce-subtotal-discount">
					<div class="autofit-col autofit-col-expand"></div>

					<div class="order-details-subtitle order-value">
						<%= HtmlUtil.escape(commerceOrganizationOrderDisplayContext.getFormattedPercentage(subtotalDiscountValue.getDiscountPercentage())) %>
					</div>
				</li>
			</c:if>

			<li class="autofit-row commerce-delivery">
				<div class="autofit-col autofit-col-expand">
					<div class="commerce-description"><liferay-ui:message key="delivery" /></div>
				</div>

				<div class="autofit-col">
					<div class="commerce-value"><%= HtmlUtil.escape(shippingValue.format(locale)) %></div>
				</div>
			</li>

			<c:if test="<%= (shippingDiscountValue != null) && (BigDecimal.ZERO.compareTo(shippingDiscountValue.getDiscountPercentage()) < 0) %>">

				<%
				CommerceMoney shippingDiscountAmount = shippingDiscountValue.getDiscountAmount();
				%>

				<li class="autofit-row commerce-delivery-discount">
					<div class="autofit-col autofit-col-expand">
						<div class="commerce-description"><liferay-ui:message key="delivery-discount" /></div>
					</div>

					<div class="order-details-subtitle order-value">
						<%= HtmlUtil.escape(shippingDiscountAmount.format(locale)) %>
					</div>
				</li>
				<li class="autofit-row commerce-delivery-discount">
					<div class="autofit-col autofit-col-expand"></div>

					<div class="order-details-subtitle order-value">
						<%= HtmlUtil.escape(commerceOrganizationOrderDisplayContext.getFormattedPercentage(shippingDiscountValue.getDiscountPercentage())) %>
					</div>
				</li>
			</c:if>

			<li class="autofit-row commerce-tax">
				<div class="autofit-col autofit-col-expand">
					<div class="commerce-description"><liferay-ui:message key="tax" /></div>
				</div>

				<div class="autofit-col">
					<div class="commerce-value"><%= HtmlUtil.escape(taxValue.format(locale)) %></div>
				</div>
			</li>

			<c:if test="<%= (totalDiscountValue != null) && (BigDecimal.ZERO.compareTo(totalDiscountValue.getDiscountPercentage()) < 0) %>">

				<%
				CommerceMoney totalDiscountAmount = totalDiscountValue.getDiscountAmount();
				%>

				<li class="autofit-row commerce-total-discount">
					<div class="autofit-col autofit-col-expand">
						<div class="commerce-description"><liferay-ui:message key="total-discount" /></div>
					</div>

					<div class="order-details-subtitle order-value">
						<%= HtmlUtil.escape(totalDiscountAmount.format(locale)) %>
					</div>
				</li>
				<li class="autofit-row commerce-total-discount">
					<div class="autofit-col autofit-col-expand"></div>

					<div class="order-details-subtitle order-value">
						<%= HtmlUtil.escape(commerceOrganizationOrderDisplayContext.getFormattedPercentage(totalDiscountValue.getDiscountPercentage())) %>
					</div>
				</li>
			</c:if>

			<li class="autofit-row commerce-total">
				<div class="autofit-col autofit-col-expand">
					<div class="commerce-description order-total"><liferay-ui:message key="total" /></div>
				</div>

				<div class="autofit-col">
					<div class="commerce-value order-total"><%= HtmlUtil.escape(totalOrder.format(locale)) %></div>
				</div>
			</li>
		</ul>
	</aui:col>
</aui:row>

<liferay-portlet:renderURL var="editCommerceOrderDetailsURL" windowState="<%= LiferayWindowState.EXCLUSIVE.toString() %>">
	<liferay-portlet:param name="mvcRenderCommandName" value="editCommerceOrderDetails" />
	<liferay-portlet:param name="redirect" value="<%= currentURL %>" />
	<liferay-portlet:param name="commerceOrderId" value="<%= String.valueOf(commerceOrder.getCommerceOrderId()) %>" />
</liferay-portlet:renderURL>

<portlet:actionURL name="editCommerceOrder" var="editCommerceOrderURL" />

<%@ include file="/transition.jspf" %>

<aui:script>
	Liferay.provide(
		window,
		'<portlet:namespace />editCommerceOrder',
		function(A) {
			var A = AUI();

			var dialog = Liferay.Util.Window.getWindow(
				{
					dialog: {
						destroyOnClose: true,
						toolbars: {
							footer: [
								{
									cssClass: 'btn-cancel mr-2',
									label: '<liferay-ui:message key="cancel" />',
									on: {
										click: function() {
											dialog.hide();
										}
									}
								},
								{
								cssClass: 'btn-primary',
								label: '<liferay-ui:message key="edit-order" />',
								on: {
									click: function() {
										submitForm(document.<portlet:namespace />editFm);
									}
								}
							}
						],
						header: [
							{
								cssClass: 'close',
								discardDefaultButtonCssClasses: true,
								labelHTML: '<clay:icon symbol="times" />',
								on: {
									click: function(event) {
										dialog.hide();
									}
								}
							}
						]
					},
					width: 600
				},
				title: '<liferay-ui:message key="edit-order" />'
			}
		).plug(
			A.Plugin.IO,
				{
					uri: '<%= editCommerceOrderDetailsURL %>'
				}
			).render();
		},
		['aui-io-deprecated', 'liferay-util-window']
	);

	function <portlet:namespace/>viewCommerceOrderShipments(uri) {
		Liferay.Util.openWindow(
			{
				dialog: {
					centered: true,
					destroyOnClose: true,
					modal: true
				},
				dialogIframe: {
					bodyCssClass: 'dialog'
				},
				id: 'viewCommerceOrderShipmentsDialog',
				title: '',
				uri: uri
			}
		);
	}

	function <portlet:namespace />reorderCommerceOrder() {
		document.<portlet:namespace />fm.<portlet:namespace /><%= Constants.CMD %>.value = 'reorder';

		submitForm(document.<portlet:namespace />fm);
	}
</aui:script>

<aui:script use="aui-base">
	var orderTransition = A.one('#<portlet:namespace />orderTransition');

	if (orderTransition) {
		orderTransition.delegate(
			'click',
			function(event) {
				<portlet:namespace />transition(event);
			},
			'.transition-link'
		);
	}
</aui:script>