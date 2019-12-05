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

SearchContainer<CommerceOrderPayment> commerceOrderPaymentsSearchContainer = commerceOrderEditDisplayContext.getCommerceOrderPaymentsSearchContainer();
CommerceOrder commerceOrder = commerceOrderEditDisplayContext.getCommerceOrder();
%>

<div class="row">
	<div class="col-md-6 d-flex">
		<liferay-portlet:renderURL var="editOrderPaymentMethodURL" windowState="<%= LiferayWindowState.POP_UP.toString() %>">
			<portlet:param name="mvcRenderCommandName" value="editCommerceOrderPaymentMethod" />
			<portlet:param name="commerceOrderId" value="<%= String.valueOf(commerceOrderEditDisplayContext.getCommerceOrderId()) %>" />
		</liferay-portlet:renderURL>

		<commerce-ui:modal
			closeOnSubmit="<%= true %>"
			id="order-payment-method-modal"
			showCancel="<%= true %>"
			showSubmit="<%= true %>"
			size="lg"
			title='<%= LanguageUtil.get(request, "payment-method") %>'
			url="<%= editOrderPaymentMethodURL %>"
		/>

		<commerce-ui:panel
			elementClasses="flex-fill"
			actionLabel='<%= LanguageUtil.get(request, Validator.isNull(commerceOrder.getCommercePaymentMethodKey()) ? "add" : "edit") %>'
			actionTargetId="order-payment-method-modal"
			actionUrl="<%= editOrderPaymentMethodURL %>"
			title='<%= LanguageUtil.get(request, "payment-method") %>'
		>

			<%
			String commerceOrderPaymentMethodName = commerceOrderEditDisplayContext.getCommerceOrderPaymentMethodName();
			%>

			<c:choose>
				<c:when test="<%= Validator.isNull(commerceOrderPaymentMethodName) %>">
					<div class="align-items-center d-flex payment-info">
						<span class="ml-3 payment-name text-muted">
							<%= LanguageUtil.get(request, "click-add-to-insert") %>
						</span>
					</div>
				</c:when>
				<c:otherwise>
					<div class="align-items-center d-flex payment-info">
						<clay:icon
							symbol="info-circle"
						/>

						<span class="ml-3 payment-name">
							<%= HtmlUtil.escape(commerceOrderPaymentMethodName) %>
						</span>
					</div>
				</c:otherwise>
			</c:choose>
		</commerce-ui:panel>
	</div>

	<div class="col-md-6 d-flex">
		<commerce-ui:panel
			elementClasses="flex-fill"
			title='<%= LanguageUtil.get(request, "payment-status") %>'
		>
			<div class="row">
				<div class="col d-flex">
					<clay:label
						elementClasses="align-self-center"
						label="<%= LanguageUtil.get(request, CommerceOrderConstants.getPaymentStatusLabel(commerceOrder.getPaymentStatus())) %>"
						size="lg"
						style="<%= CommerceOrderConstants.getPaymentLabelStyle(commerceOrder.getPaymentStatus()) %>"
					/>
				</div>
			</div>
		</commerce-ui:panel>
	</div>

	<div class="col-12">
		<commerce-ui:panel
			bodyClasses="p-0"
			elementClasses="flex-fill"
			title='<%= LanguageUtil.get(request, "payment-transactions") %>'
		>
			<liferay-ui:search-container
				id="commerceOrderPayments"
				searchContainer="<%= commerceOrderPaymentsSearchContainer %>"
			>
				<liferay-ui:search-container-row
					className="com.liferay.commerce.model.CommerceOrderPayment"
					cssClass="entry-display-style"
					escapedModel="<%= true %>"
					keyProperty="commerceOrderPaymentId"
					modelVar="commerceOrderPayment"
				>
					<liferay-ui:search-container-column-text
						name="type"
					>
						<clay:label
							label="<%= LanguageUtil.get(request, CommerceOrderPaymentConstants.getOrderPaymentStatusLabel(commerceOrderPayment.getStatus())) %>"
							style="<%= CommerceOrderPaymentConstants.getOrderPaymentLabelStyle(commerceOrderPayment.getStatus()) %>"
						/>
					</liferay-ui:search-container-column-text>

					<%
					String amount = StringPool.BLANK;

					CommerceMoney totalMoney = commerceOrder.getTotalMoney();

					if (totalMoney != null) {
						amount = totalMoney.format(locale);
					}
					%>

					<liferay-ui:search-container-column-text
						name="amount"
					>
						<%= HtmlUtil.escape(amount) %>
					</liferay-ui:search-container-column-text>

					<liferay-ui:search-container-column-date
						name="date"
						property="createDate"
					/>

					<liferay-ui:search-container-column-text
						property="content"
					/>
				</liferay-ui:search-container-row>

				<liferay-ui:search-iterator
					markupView="lexicon"
				/>
			</liferay-ui:search-container>
		</commerce-ui:panel>
	</div>
</div>