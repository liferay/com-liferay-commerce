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
		<commerce-ui:panel
			elementClasses="flex-fill"
			title='<%= LanguageUtil.get(request, "payment-method") %>'
		>
			<div class="align-items-center d-flex payment-info">
				<clay:icon
					symbol="info-circle"
				/>

				<span class="ml-3 payment-name">
					<%= HtmlUtil.escape(commerceOrderEditDisplayContext.getCommerceOrderPaymentMethodName()) %>
				</span>

				<clay:button
					componentId="editPaymentMethodButton"
					elementClasses="ml-3"
					label='<%= LanguageUtil.get(request, "edit") %>'
					style="link"
				/>
			</div>
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
						style="<%= CommerceOrderConstants.getPaymentLabelStyle(commerceOrder.getPaymentStatus()) %>"
					/>
				</div>

				<div class="col-auto">
					<clay:button
						elementClasses="ml-3"
						label='<%= LanguageUtil.get(request, "refund") %>'
						style="secondary"
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

			<liferay-ui:empty-result-message
				message="there-are-no-payments"
			/>
		</commerce-ui:panel>
	</div>
</div>