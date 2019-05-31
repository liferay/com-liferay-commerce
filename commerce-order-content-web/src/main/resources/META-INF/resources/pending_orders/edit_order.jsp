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

CommerceAddress billingCommerceAddress = commerceOrder.getBillingAddress();
CommerceAddress shippingCommerceAddress = commerceOrder.getShippingAddress();

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

	<div class="panel panel-secondary header-panel">
		<div class="panel-body">
			<div class="row">
				<div class="col">
					<div class="row row-with-separators">
						<div class="col-auto">
							<div class="row">
								<div class="col-auto">
									<span class="header-panel__main-title component-title">
										<%= HtmlUtil.escape(commerceAccount.getName()) %>
									</span>
								</div>
								<div class="col">
									<span class="header-panel__main-id">
										ID: <a href="#"><%= commerceAccount.getCommerceAccountId() %></a>
									</span>
								</div>
							</div>
							<div class="header-panel__main-detail">
								Aug 24, 2019 @ 9:51 AM CST
							</div>
						</div>
						<div class="col-auto">
							<div class="header-panel__secondary-id">
								# <%= commerceOrder.getCommerceOrderId() %>
							</div>
							<div class="header-panel__state">
								<span class="label label-warning">
									<span class="label-item label-item-expand">
										Pending
									</span>
								</span>
							</div>
						</div>
						<div class="col">
							<div class="header-panel__assignee">
								<aui:select name="assignedTo" showEmptyOption="<%= true %>" inlineField="<%= true %>" inlineLabel="assigned-to" />
							</div>
						</div>
					</div>
				</div>

				<div class="col-md-3">
					<dl class="commerce-list">
						<dt><liferay-ui:message key="total" /></dt>
						<dd><%= HtmlUtil.escape(totalOrder.format(locale)) %></dd>
					</dl>
				</div>

				<div class="col-md-3">
					<dl class="commerce-list">
						<dt><liferay-ui:message key="notes" /></dt>
						<dd>

							<%
							request.setAttribute("order_notes.jsp-showLabel", Boolean.TRUE);
							request.setAttribute("order_notes.jsp-taglibLinkCssClass", "link-outline link-outline-borderless link-outline-secondary lfr-icon-item-reverse");
							%>

							<liferay-util:include page="/pending_orders/order_notes.jsp" servletContext="<%= application %>" />
						</dd>
					</dl>
				</div>
			</div>
		</div>
	</div>

	<div class="panel panel-secondary data-box-container">
		<div class="panel-header">
			<div class="panel-title">
				<liferay-ui:message key="info" />
			</div>
		</div>
		<div class="panel-body">
			<div class="row row-with-separators">
				<div class="col-4">
					<div class="panel panel-secondary data-box">
						<div class="panel-body data-box__body">
							<div class="row">
								<div class="col">
									<h5 class="data-box__title">
										<liferay-ui:message key="billing-address" />
									</h5>
								</div>
								<div class="col-auto">
									<a class="data-box__action" href="#">
										Add
									</a>
								</div>
								<div class="col-12">
									<div class="data-box__value data-box__value--disabled">
										No billing address selected
									</div>
								</div>
							</div>
						</div>
					</div>
					<div class="panel panel-secondary data-box">
						<div class="panel-body data-box__body">
							<div class="row">
								<div class="col">
									<h5 class="data-box__title">
										<liferay-ui:message key="shipping-address" />
									</h5>
								</div>
								<div class="col-auto">
									<a class="data-box__action" href="#">
										Edit
									</a>
								</div>
								<div class="col-12">
									<div class="data-box__value">
										PO Box 467 New York NY 100002
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
				<div class="col-4">
					<div class="panel panel-secondary data-box">
						<div class="panel-body data-box__body">
							<div class="row">
								<div class="col">
									<h5 class="data-box__title">
										<liferay-ui:message key="purchase-order-number" />
									</h5>
								</div>
								<div class="col-auto">
									<a class="data-box__action" href="#">
										Edit
									</a>
								</div>
								<div class="col-12">
									<div class="data-box__value">
										#564897123
									</div>
								</div>
							</div>
						</div>
					</div>
					<div class="panel panel-secondary data-box">
						<div class="panel-body data-box__body">
							<div class="row">
								<div class="col">
									<h5 class="data-box__title">
										Request Delivery Date
									</h5>
								</div>
								<div class="col-auto">
									<a class="data-box__action" href="#">
										Edit
									</a>
								</div>
								<div class="col-12">
									<div class="data-box__value">
										Aug 24, 2019
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
				<div class="col-4">
					<div class="panel panel-secondary data-box">
						<div class="panel-body data-box__body">
							<div class="row">
								<div class="col">
									<h5 class="data-box__title">
										<liferay-ui:message key="notes" />
									</h5>
								</div>
								<div class="col-auto">
									<a class="data-box__action" href="#">
										Edit
									</a>
								</div>
								<div class="col-12">
									<div class="data-box__value">
										<%-- <liferay-util:include page="/order_notes.jsp" servletContext="<%= application %>" /> --%>
										Lorem ipsum dolor sit amet, consectetur adipiscing elit. Donec sollicitudin molestie malesuada.
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</aui:form>

<commerce-ui:table
	dataProviderKey="commercePendingOrderItems"
	itemPerPage="<%= 5 %>"
	namespace="<%= renderResponse.getNamespace() %>"
	pageNumber="1"
	portletURL="<%= commerceOrderContentDisplayContext.getPortletURL() %>"
	tableName="commercePendingOrderItems"
/>

<div class="panel panel-secondary table-summary">
	<div class="panel-body">
		<div class="row">
			<div class="col text-right">
				<div class="component-title table-summary__label">
					<liferay-ui:message key="subtotal" />
				</div>
			</div>
			<div class="col-2 text-right table-summary__value-container">
				<div class="table-summary__value">
					<%= HtmlUtil.escape(subtotal.format(locale)) %>
				</div>
			</div>
		</div>

		<c:if test="<%= (subtotalDiscountValue != null) && (BigDecimal.ZERO.compareTo(subtotalDiscountValue.getDiscountPercentage()) < 0) %>">
			<% CommerceMoney subtotalDiscountAmount = subtotalDiscountValue.getDiscountAmount(); %>
			<div class="row">
				<div class="col text-right">
					<div class="component-title table-summary__label">
						<liferay-ui:message key="subtotal-discount" />
					</div>
				</div>
				<div class="col-2 text-right table-summary__value-container">
					<div class="table-summary__value">
						<%= HtmlUtil.escape(subtotalDiscountAmount.format(locale)) %>
					</div>
					<div class="table-summary__value">
						<%= HtmlUtil.escape(commerceOrderContentDisplayContext.getFormattedPercentage(subtotalDiscountValue.getDiscountPercentage())) %>
					</div>
				</div>
			</div>
		</c:if>

		<div class="row">
			<div class="col text-right">
				<div class="component-title table-summary__label">
					<liferay-ui:message key="delivery" />
				</div>
			</div>
			<div class="col-2 text-right table-summary__value-container">
				<div class="table-summary__value">
					<%= HtmlUtil.escape(shippingValue.format(locale)) %>
				</div>
			</div>
		</div>

		<c:if test="<%= (shippingDiscountValue != null) && (BigDecimal.ZERO.compareTo(shippingDiscountValue.getDiscountPercentage()) < 0) %>">
			<% CommerceMoney shippingDiscountAmount = shippingDiscountValue.getDiscountAmount(); %>
			<div class="row">
				<div class="col text-right">
					<div class="component-title table-summary__label">
						<liferay-ui:message key="delivery-discount" />
					</div>
				</div>
				<div class="col-2 text-right table-summary__value-container">
					<div class="table-summary__value">
						<%= HtmlUtil.escape(shippingValue.format(locale)) %>
					</div>
					<div class="table-summary__value">
						<%= HtmlUtil.escape(shippingDiscountAmount.format(locale)) %>
					</div>
					<div class="table-summary__value">
						<%= HtmlUtil.escape(commerceOrderContentDisplayContext.getFormattedPercentage(shippingDiscountValue.getDiscountPercentage())) %>
					</div>
				</div>
			</div>
		</c:if>

		<div class="row">
			<div class="col text-right">
				<div class="component-title table-summary__label">
					<liferay-ui:message key="tax" />
				</div>
			</div>
			<div class="col-2 text-right table-summary__value-container">
				<div class="table-summary__value">
					<%= HtmlUtil.escape(taxValue.format(locale)) %>
				</div>
			</div>
		</div>

		<c:if test="<%= (totalDiscountValue != null) && (BigDecimal.ZERO.compareTo(totalDiscountValue.getDiscountPercentage()) < 0) %>">
			<% CommerceMoney totalDiscountAmount = totalDiscountValue.getDiscountAmount(); %>
			<div class="row">
				<div class="col text-right">
					<div class="component-title table-summary__label">
						<liferay-ui:message key="delivery-discount" />
					</div>
				</div>
				<div class="col-2 text-right table-summary__value-container">
					<div class="table-summary__value">
						<%= HtmlUtil.escape(totalDiscountAmount.format(locale)) %>
					</div>
					<div class="table-summary__value">
						<%= HtmlUtil.escape(commerceOrderContentDisplayContext.getFormattedPercentage(totalDiscountValue.getDiscountPercentage())) %>
					</div>
				</div>
			</div>
		</c:if>

		<div class="row">
			<div class="col text-right">
				<div class="component-title table-summary__label">
					<liferay-ui:message key="total" />
				</div>
			</div>
			<div class="col-2 text-right table-summary__value-container">
				<div class="table-summary__value table-summary__value--lg">
					<%= HtmlUtil.escape(totalOrder.format(locale)) %>
				</div>
			</div>
		</div>
	</div>
</div>

<portlet:actionURL name="editCommerceOrder" var="editCommerceOrderURL" />

<%@ include file="/pending_orders/transition.jspf" %>

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


<%
    String dynamicPanelRootElementId = renderResponse.getNamespace() + "-dynamic-panel-root";
%>

<div class="dynamic-panel-wrapper" id="<%= dynamicPanelRootElementId %>">
    <div class="inline-item my-5 p-5 w-100">
        <span aria-hidden="true" class="loading-animation"></span>
    </div>
</div>

<aui:script require="commerce-order-content-web@1.0.13/dynamic_panel/js/index.es as DynamicPanel">

	var props = {
		elements : [
			{
				icon: 'favorites',
				url: 'http://localhost:4000/api/text/list',
				pageName: 'Comments'
			},
			{
				icon: 'print',
				url: 'http://localhost:4000/api/text/print',
				pageName: 'Edit'
			},
			{
				icon: 'search',
				url: 'http://localhost:4000/api/text/search',
				pageName: 'Changelog'
			}
		],
		spritemap: '<%= themeDisplay.getPathThemeImages() + "/commerce-icons.svg" %>'
	}

    DynamicPanel.default(
        '<%= dynamicPanelRootElementId %>',
        props
    );

</aui:script>
