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
CommerceOrderContentDisplayContext commerceOrderContentDisplayContext = (CommerceOrderContentDisplayContext)request.getAttribute(WebKeys.PORTLET_DISPLAY_CONTEXT);

CommerceOrder commerceOrder = commerceOrderContentDisplayContext.getCommerceOrder();

long billingCommerceAddressId = BeanParamUtil.getLong(commerceOrder, request, "billingAddressId");
long shippingCommerceAddressId = BeanParamUtil.getLong(commerceOrder, request, "shippingAddressId");

CommerceOrderPrice commerceOrderPrice = commerceOrderContentDisplayContext.getCommerceOrderPrice();

CommerceMoney shippingValue = commerceOrderPrice.getShippingValue();
CommerceDiscountValue shippingDiscountValue = commerceOrderPrice.getShippingDiscountValue();
CommerceMoney subtotal = commerceOrderPrice.getSubtotal();
CommerceDiscountValue subtotalDiscountValue = commerceOrderPrice.getSubtotalDiscountValue();
CommerceMoney taxValue = commerceOrderPrice.getTaxValue();
CommerceDiscountValue totalDiscountValue = commerceOrderPrice.getTotalDiscountValue();
CommerceMoney totalOrder = commerceOrderPrice.getTotal();

List<CommerceOrderValidatorResult> commerceOrderValidatorResults = new ArrayList<>();

CommerceAccount commerceAccount = commerceOrderContentDisplayContext.getCommerceAccount();

if (commerceOrder != null) {
	commerceAccount = commerceOrder.getCommerceAccount();
}

List<CommerceAddress> commerceAddresses = commerceOrderContentDisplayContext.getCommerceAddresses(commerceAccount.getCommerceAccountId());
%>

<portlet:actionURL name="editCommerceOrder" var="editCommerceOrderActionURL">
	<portlet:param name="mvcRenderCommandName" value="editCommerceOrder" />
</portlet:actionURL>

<aui:form action="<%= editCommerceOrderActionURL %>" cssClass="order-details-container" method="post" name="fm">
	<aui:input name="<%= Constants.CMD %>" type="hidden" value="<%= Constants.UPDATE %>" />
	<aui:input name="redirect" type="hidden" value="<%= currentURL %>" />
	<aui:input name="commerceOrderId" type="hidden" value="<%= String.valueOf(commerceOrder.getCommerceOrderId()) %>" />

	<liferay-ui:error exception="<%= CommerceOrderValidatorException.class %>">

		<%
		CommerceOrderValidatorException cove = (CommerceOrderValidatorException)errorException;

		if (cove != null) {
			commerceOrderValidatorResults = cove.getCommerceOrderValidatorResults();
		}

		for (CommerceOrderValidatorResult commerceOrderValidatorResult : commerceOrderValidatorResults) {
		%>

			<liferay-ui:message key="<%= commerceOrderValidatorResult.getLocalizedMessage() %>" />

		<%
		}
		%>

	</liferay-ui:error>

	<aui:model-context bean="<%= commerceOrder %>" model="<%= CommerceOrder.class %>" />

	<div class="minium-card">
		<div class="minium-card__content">
			<div class="align-items-center row">
				<div class="col-md-3">
					<div class="minium-order-title">
						<%= HtmlUtil.escape(commerceAccount.getName()) %>
					</div>
				</div>

				<div class="col-md-3">
					<dl class="minium-list">
						<dt><liferay-ui:message key="total" /></dt>
						<dd><%= HtmlUtil.escape(totalOrder.format(locale)) %></dd>
					</dl>
				</div>

				<div class="col-md-3">
					<dl class="minium-list">
						<dt><liferay-ui:message key="notes" /></dt>
						<dd>

							<%
							request.setAttribute("order_notes.jsp-showLabel", Boolean.TRUE);
							request.setAttribute("order_notes.jsp-taglibLinkCssClass", "link-outline link-outline-borderless link-outline-secondary lfr-icon-item-reverse");
							%>

							<liferay-util:include page="/order_notes.jsp" servletContext="<%= application %>" />
						</dd>
					</dl>
				</div>
			</div>
		</div>

		<div class="minium-card__content">
			<div class="align-items-center row">
				<div class="col-md-3">
					<dl class="minium-list">
						<dt><liferay-ui:message key="account-id" /></dt>
						<dd><%= commerceAccount.getCommerceAccountId() %></dd>
					</dl>
				</div>

				<div class="col-md-3">
					<dl class="minium-list">
						<dt><liferay-ui:message key="order-id" /></dt>
						<dd><%= commerceOrder.getCommerceOrderId() %></dd>
					</dl>
				</div>

				<div class="col-md-3">
					<dl class="minium-list">
						<dt><liferay-ui:message key="order-date" /></dt>
						<dd>
							<%= commerceOrderContentDisplayContext.getCommerceOrderDate(commerceOrder) %>
							<%= commerceOrderContentDisplayContext.getCommerceOrderTime(commerceOrder) %>
						</dd>
					</dl>
				</div>

			</div>
		</div>
	</div>

	<div class="row">
		<div class="col-md-12">
			<div class="minium-card">
				<div class="minium-card__title"><liferay-ui:message key="purchase-order-number" /></div>
				<div class="minium-card__content">
					<div class="row">
						<div class="col-md-6">
							<dl class="minium-list">
								<aui:input cssClass="minium-input" inlineField="<%= true %>" label="" name="purchaseOrderNumber" wrappedField="<%= false %>" />
							</dl>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>

	<div class="row">
		<div class="col-md-6">
			<div class="minium-card">
				<div class="minium-card__title"><liferay-ui:message key="billing-address" /></div>
				<div class="minium-card__content">
					<div class="row">
						<div class="col-md-8">
							<dl class="minium-list">
								<aui:select cssClass="minium-input" inlineField="<%= true %>" label="" name="billingAddressId" wrappedField="<%= false %>">

									<%
									for (CommerceAddress commerceAddress : commerceAddresses) {
									%>

										<aui:option label="<%= commerceAddress.getName() %>" selected="<%= billingCommerceAddressId == commerceAddress.getCommerceAddressId() %>" value="<%= commerceAddress.getCommerceAddressId() %>" />

									<%
									}
									%>

								</aui:select>
							</dl>
						</div>

						<div class="col-md-4">
							<div class="minium-button minium-button--outline minium-button--small"><liferay-ui:message key="add-address" /></div>
						</div>
					</div>
				</div>
			</div>
		</div>

		<div class="col-md-6">
			<div class="minium-card">
				<div class="minium-card__title"><liferay-ui:message key="shipping-address" /></div>
				<div class="minium-card__content">
					<div class="row">
						<div class="col-md-8">
							<dl class="minium-list">
								<aui:select cssClass="minium-input" inlineField="<%= true %>" label="" name="shippingAddressId" wrappedField="<%= false %>">

									<%
									for (CommerceAddress commerceAddress : commerceAddresses) {
									%>

										<aui:option label="<%= commerceAddress.getName() %>" selected="<%= shippingCommerceAddressId == commerceAddress.getCommerceAddressId() %>" value="<%= commerceAddress.getCommerceAddressId() %>" />

									<%
									}
									%>

								</aui:select>
							</dl>
						</div>

						<div class="col-md-4">
							<div class="minium-button minium-button--outline minium-button--small"><liferay-ui:message key="add-address" /></div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>

	<c:if test="<%= commerceOrder.isOpen() %>">
		<div class="minium-thumb-menu">
			<c:if test="<%= commerceOrderContentDisplayContext.hasModelPermission(commerceOrder, ActionKeys.UPDATE) %>">
				<div class="col-md-auto">
					<liferay-ui:icon-menu
						direction="right"
						icon="<%= StringPool.BLANK %>"
						markupView="lexicon"
						message="<%= StringPool.BLANK %>"
						showWhenSingleIcon="<%= true %>"
						triggerCssClass="component-action"
					>
						<liferay-ui:icon
							message="print"
							url='<%= "javascript:window.print();" %>'
						/>

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
		</div>

		<div class="minium-frame__cta is-visible">
			<aui:button cssClass="minium-button minium-button--big minium-button--outline" href="<%= backURL %>" value="cancel" />

			<aui:button cssClass="minium-button minium-button--big minium-button--outline" type="submit" />

			<liferay-commerce:order-transitions
				commerceOrderId="<%= commerceOrder.getCommerceOrderId() %>"
				cssClass="minium-button minium-button--big minium-button--spaced"
			/>
		</div>
	</c:if>
</aui:form>

<div class="row">
	<div class="col-md-9">
		<commerce-ui:table
			dataProviderKey="commerceOrderItems"
			itemPerPage="<%= 5 %>"
			namespace="<%= renderResponse.getNamespace() %>"
			pageNumber="1"
			portletURL="<%= commerceOrderContentDisplayContext.getPortletURL() %>"
			tableName="commerceOrderItems"
		/>
	</div>
	<div class="col-md-3">
		<div class="minium-card">
			<div class="minium-card__content">

				<dl class="minium-list">
					<dt><liferay-ui:message key="subtotal" /></dt>
					<dd class="text-right"><%= HtmlUtil.escape(subtotal.format(locale)) %></dd>

					<c:if test="<%= (subtotalDiscountValue != null) && (BigDecimal.ZERO.compareTo(subtotalDiscountValue.getDiscountPercentage()) < 0) %>">

						<%
						CommerceMoney subtotalDiscountAmount = subtotalDiscountValue.getDiscountAmount();
						%>

						<dt><liferay-ui:message key="subtotal-discount" /></dt>
						<dd class="text-right"><%= HtmlUtil.escape(subtotalDiscountAmount.format(locale)) %></dd>
						<dt></dt>
						<dd class="text-right"><%= HtmlUtil.escape(commerceOrderContentDisplayContext.getFormattedPercentage(subtotalDiscountValue.getDiscountPercentage())) %></dd>
					</c:if>

					<dt><liferay-ui:message key="delivery" /></dt>
					<dd class="text-right"><%= HtmlUtil.escape(shippingValue.format(locale)) %></dd>

					<c:if test="<%= (shippingDiscountValue != null) && (BigDecimal.ZERO.compareTo(shippingDiscountValue.getDiscountPercentage()) < 0) %>">

						<%
						CommerceMoney shippingDiscountAmount = shippingDiscountValue.getDiscountAmount();
						%>

						<dt><liferay-ui:message key="delivery-discount" /></dt>
						<dd class="text-right"><%= HtmlUtil.escape(shippingDiscountAmount.format(locale)) %></dd>
						<dt></dt>
						<dd class="text-right"><%= HtmlUtil.escape(commerceOrderContentDisplayContext.getFormattedPercentage(shippingDiscountValue.getDiscountPercentage())) %></dd>
					</c:if>

					<dt><liferay-ui:message key="tax" /></dt>
					<dd class="text-right"><%= HtmlUtil.escape(taxValue.format(locale)) %></dd>

					<c:if test="<%= (totalDiscountValue != null) && (BigDecimal.ZERO.compareTo(totalDiscountValue.getDiscountPercentage()) < 0) %>">

						<%
						CommerceMoney totalDiscountAmount = totalDiscountValue.getDiscountAmount();
						%>

						<dt><liferay-ui:message key="delivery-discount" /></dt>
						<dd class="text-right"><%= HtmlUtil.escape(totalDiscountAmount.format(locale)) %></dd>
						<dt></dt>
						<dd class="text-right"><%= HtmlUtil.escape(commerceOrderContentDisplayContext.getFormattedPercentage(totalDiscountValue.getDiscountPercentage())) %></dd>
					</c:if>
				</dl>
			</div>
			<div class="minium-card__content">
				<dl class="minium-list">
					<dt><liferay-ui:message key="total" /></dt>
					<dd class="text-right"><%= HtmlUtil.escape(totalOrder.format(locale)) %></dd>
				</dl>
			</div>
		</div>
	</div>
</div>


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