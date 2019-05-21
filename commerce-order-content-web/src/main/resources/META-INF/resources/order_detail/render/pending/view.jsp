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
CommerceOrderDetailHelper commerceOrderDetailHelper = (CommerceOrderDetailHelper)request.getAttribute(CommerceOrderDetailWebKeys.COMMERCE_ORDER_DETAIL_HELPER);

CommerceOrder commerceOrder = commerceOrderDetailHelper.getCommerceOrder(request);

long billingCommerceAddressId = BeanParamUtil.getLong(commerceOrder, request, "billingAddressId");
long shippingCommerceAddressId = BeanParamUtil.getLong(commerceOrder, request, "shippingAddressId");

CommerceAddress billingCommerceAddress = commerceOrder.getBillingAddress();
CommerceAddress shippingCommerceAddress = commerceOrder.getShippingAddress();

CommerceOrderPrice commerceOrderPrice = commerceOrderDetailHelper.getCommerceOrderPrice(request);

CommerceMoney shippingValue = commerceOrderPrice.getShippingValue();
CommerceDiscountValue shippingDiscountValue = commerceOrderPrice.getShippingDiscountValue();
CommerceMoney subtotal = commerceOrderPrice.getSubtotal();
CommerceDiscountValue subtotalDiscountValue = commerceOrderPrice.getSubtotalDiscountValue();
CommerceMoney taxValue = commerceOrderPrice.getTaxValue();
CommerceDiscountValue totalDiscountValue = commerceOrderPrice.getTotalDiscountValue();
CommerceMoney totalOrder = commerceOrderPrice.getTotal();

List<CommerceOrderValidatorResult> commerceOrderValidatorResults = new ArrayList<>();

CommerceAccount commerceAccount = commerceOrder.getCommerceAccount();

List<CommerceAddress> commerceAddresses = commerceOrderDetailHelper.getCommerceAddresses(commerceAccount.getCommerceAccountId(), request);
%>

<portlet:actionURL name="editCommerceOrder" var="editCommerceOrderActionURL" />

<aui:form action="<%= editCommerceOrderActionURL %>" cssClass="order-details-container" method="post" name="fm">
	<aui:input name="<%= Constants.CMD %>" type="hidden" value="<%= Constants.UPDATE %>" />
	<aui:input name="redirect" type="hidden" value="<%= commerceOrderDetailHelper.getOrderDetailURL(commerceOrder.getCommerceOrderId(), request) %>" />
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

	<div class="commerce-panel">
		<div class="commerce-panel__content">
			<div class="align-items-center row">
				<div class="col-md-3">
					<div class="commerce-order-title">
						<%= HtmlUtil.escape(commerceAccount.getName()) %>
					</div>
				</div>

				<div class="col-md-1">
					<div class="commerce-list">
						# <%= commerceOrder.getCommerceOrderId() %>
					</div>
				</div>

				<div class="col-md-3">
					<dl class="commerce-list">
						<dt><liferay-ui:message key="total" /></dt>
						<dd><%= HtmlUtil.escape(totalOrder.format(locale)) %></dd>
					</dl>
				</div>

				<div class="col-md-1">
					<dl class="commerce-list">
						<dt><liferay-ui:message key="notes" /></dt>
						<dd>

							<%
							request.setAttribute("order_notes.jsp-showLabel", Boolean.TRUE);
							request.setAttribute("order_notes.jsp-taglibLinkCssClass", "link-outline link-outline-borderless link-outline-secondary lfr-icon-item-reverse");
							%>

							<liferay-util:include page="/order_detail/render/pending/order_notes.jsp" servletContext="<%= application %>" />
						</dd>
					</dl>
				</div>
			</div>
		</div>

		<div class="commerce-panel__content">
			<div class="align-items-center row">
				<div class="col-md-3">
					<dl class="commerce-list">
						<dt><liferay-ui:message key="account-id" /></dt>
						<dd><%= commerceAccount.getCommerceAccountId() %></dd>
					</dl>
				</div>

				<div class="col-md-3">
					<dl class="commerce-list">
						<dt><liferay-ui:message key="order-id" /></dt>
						<dd><%= commerceOrder.getCommerceOrderId() %></dd>
					</dl>
				</div>

				<div class="col-md-3">
					<dl class="commerce-list">
						<dt><liferay-ui:message key="order-date" /></dt>
						<dd>
							<%= commerceOrderDetailHelper.getCommerceOrderDate(commerceOrder, request) %>
							<%= commerceOrderDetailHelper.getCommerceOrderTime(commerceOrder, request) %>
						</dd>
					</dl>
				</div>
			</div>
		</div>
	</div>

	<div class="row">
		<div class="col-md-12">
			<div class="commerce-panel">
				<div class="commerce-panel__title"><liferay-ui:message key="purchase-order-number" /></div>
				<div class="commerce-panel__content">
					<div class="row">
						<div class="col-md-6">
							<dl class="commerce-list">
								<c:choose>
									<c:when test="<%= commerceOrderDetailHelper.hasPermission(commerceOrder.getCommerceOrderId(), ActionKeys.UPDATE, request) %>">
										<aui:input cssClass="commerce-input" inlineField="<%= true %>" label="" name="purchaseOrderNumber" wrappedField="<%= false %>" />
									</c:when>
									<c:otherwise>
										<%= commerceOrder.getPurchaseOrderNumber() %>
									</c:otherwise>
								</c:choose>
							</dl>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>

	<div class="row">
		<div class="col-md-6">
			<div class="commerce-panel">
				<div class="commerce-panel__title"><liferay-ui:message key="billing-address" /></div>
				<div class="commerce-panel__content">
					<div class="row">
						<div class="col-md-12">
							<c:choose>
								<c:when test="<%= commerceOrderDetailHelper.hasPermission(commerceOrder.getCommerceOrderId(), ActionKeys.UPDATE, request) %>">
									<dl class="commerce-list">
										<aui:select cssClass="commerce-input" inlineField="<%= true %>" label="" name="billingAddressId" wrappedField="<%= false %>">

											<%
											for (CommerceAddress commerceAddress : commerceAddresses) {
											%>

												<aui:option label="<%= commerceAddress.getName() %>" selected="<%= billingCommerceAddressId == commerceAddress.getCommerceAddressId() %>" value="<%= commerceAddress.getCommerceAddressId() %>" />

											<%
											}
											%>

										</aui:select>
									</dl>
								</c:when>
								<c:otherwise>
									<c:if test="<%= billingCommerceAddress != null %>">
										<%= billingCommerceAddress.getStreet1() %><br />
										<%= billingCommerceAddress.getCity() + StringPool.SPACE + billingCommerceAddress.getZip() %>
									</c:if>
								</c:otherwise>
							</c:choose>
						</div>
					</div>
				</div>
			</div>
		</div>

		<div class="col-md-6">
			<div class="commerce-panel">
				<div class="commerce-panel__title"><liferay-ui:message key="shipping-address" /></div>
				<div class="commerce-panel__content">
					<div class="row">
						<div class="col-md-12">
							<c:choose>
								<c:when test="<%= commerceOrderDetailHelper.hasPermission(commerceOrder.getCommerceOrderId(), ActionKeys.UPDATE, request) %>">
									<dl class="commerce-list">
										<aui:select cssClass="commerce-input" inlineField="<%= true %>" label="" name="shippingAddressId" wrappedField="<%= false %>">

											<%
											for (CommerceAddress commerceAddress : commerceAddresses) {
											%>

												<aui:option label="<%= commerceAddress.getName() %>" selected="<%= shippingCommerceAddressId == commerceAddress.getCommerceAddressId() %>" value="<%= commerceAddress.getCommerceAddressId() %>" />

											<%
											}
											%>

										</aui:select>
									</dl>
								</c:when>
								<c:otherwise>
									<c:if test="<%= shippingCommerceAddress != null %>">
										<%= shippingCommerceAddress.getStreet1() %><br />
										<%= shippingCommerceAddress.getCity() + StringPool.SPACE + shippingCommerceAddress.getZip() %>
									</c:if>
								</c:otherwise>
							</c:choose>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>

	<div class="commerce-thumb-menu">
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

				<c:if test="<%= commerceOrderDetailHelper.hasPermission(commerceOrder.getCommerceOrderId(), ActionKeys.DELETE, request) %>">
					<portlet:actionURL name="editCommerceOrder" var="deleteURL">
						<portlet:param name="<%= Constants.CMD %>" value="<%= Constants.DELETE %>" />
						<portlet:param name="redirect" value="<%= currentURL %>" />
						<portlet:param name="commerceOrderId" value="<%= String.valueOf(commerceOrder.getCommerceOrderId()) %>" />
					</portlet:actionURL>

					<liferay-ui:icon-delete
						message="delete"
						url="<%= deleteURL %>"
					/>
				</c:if>
			</liferay-ui:icon-menu>
		</div>
	</div>

	<div class="commerce-cta is-visible">
		<%-- <aui:button cssClass="commerce-button commerce-button--big commerce-button--outline" href="<%= backURL %>" value="cancel" /> --%>

		<aui:button cssClass="commerce-button commerce-button--big commerce-button--outline" type="submit" />

		<liferay-commerce:order-transitions
			commerceOrderId="<%= commerceOrder.getCommerceOrderId() %>"
			cssClass="commerce-button commerce-button--big commerce-button--spaced"
		/>
	</div>
</aui:form>

<div class="row">
	<div class="col-md-9">
		<commerce-ui:table
			dataProviderKey="commercePendingOrderItems"
			itemPerPage="<%= 5 %>"
			namespace="<%= renderResponse.getNamespace() %>"
			pageNumber="1"
			portletURL="<%= currentURLObj %>"
			tableName="commercePendingOrderItems"
		/>
	</div>

	<div class="col-md-3">
		<div class="commerce-panel">
			<div class="commerce-panel__content">
				<dl class="commerce-list">
					<dt><liferay-ui:message key="subtotal" /></dt>
					<dd class="text-right"><%= HtmlUtil.escape(subtotal.format(locale)) %></dd>

					<c:if test="<%= (subtotalDiscountValue != null) && (BigDecimal.ZERO.compareTo(subtotalDiscountValue.getDiscountPercentage()) < 0) %>">

						<%
						CommerceMoney subtotalDiscountAmount = subtotalDiscountValue.getDiscountAmount();
						%>

						<dt><liferay-ui:message key="subtotal-discount" /></dt>
						<dd class="text-right"><%= HtmlUtil.escape(subtotalDiscountAmount.format(locale)) %></dd>
						<dt></dt>
						<dd class="text-right"><%= HtmlUtil.escape(commerceOrderDetailHelper.getFormattedPercentage(subtotalDiscountValue.getDiscountPercentage(), request)) %></dd>
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
						<dd class="text-right"><%= HtmlUtil.escape(commerceOrderDetailHelper.getFormattedPercentage(shippingDiscountValue.getDiscountPercentage(), request)) %></dd>
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
						<dd class="text-right"><%= HtmlUtil.escape(commerceOrderDetailHelper.getFormattedPercentage(totalDiscountValue.getDiscountPercentage(), request)) %></dd>
					</c:if>
				</dl>
			</div>

			<div class="commerce-panel__content">
				<dl class="commerce-list">
					<dt><liferay-ui:message key="total" /></dt>
					<dd class="text-right"><%= HtmlUtil.escape(totalOrder.format(locale)) %></dd>
				</dl>
			</div>
		</div>
	</div>
</div>

<portlet:actionURL name="editCommerceOrder" var="editCommerceOrderURL" />

<%@ include file="/order_detail/render/pending/transition.jspf" %>

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