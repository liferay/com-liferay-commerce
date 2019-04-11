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
OrderSummaryCheckoutStepDisplayContext orderSummaryCheckoutStepDisplayContext = (OrderSummaryCheckoutStepDisplayContext)request.getAttribute(CommerceCheckoutWebKeys.COMMERCE_CHECKOUT_STEP_DISPLAY_CONTEXT);

String goToConfirmation = (String)request.getAttribute("goToConfirmation");

CommerceOrder commerceOrder = orderSummaryCheckoutStepDisplayContext.getCommerceOrder();
CommerceOrderPrice commerceOrderPrice = orderSummaryCheckoutStepDisplayContext.getCommerceOrderPrice();

CommerceDiscountValue shippingDiscountValue = commerceOrderPrice.getShippingDiscountValue();
CommerceMoney shippingValue = commerceOrderPrice.getShippingValue();
CommerceMoney subtotal = commerceOrderPrice.getSubtotal();
CommerceDiscountValue subtotalDiscountValue = commerceOrderPrice.getSubtotalDiscountValue();
CommerceMoney taxValue = commerceOrderPrice.getTaxValue();
CommerceDiscountValue totalDiscountValue = commerceOrderPrice.getTotalDiscountValue();
CommerceMoney totalOrder = commerceOrderPrice.getTotal();

String commercePaymentMethodName = StringPool.BLANK;

String commercePaymentMethodKey = commerceOrder.getCommercePaymentMethodKey();

if (commercePaymentMethodKey != null) {
	commercePaymentMethodName = orderSummaryCheckoutStepDisplayContext.getPaymentMethodName(commercePaymentMethodKey, locale);
}

String commerceShippingOptionName = commerceOrder.getShippingOptionName();

Map<Long, List<CommerceOrderValidatorResult>> commerceOrderValidatorResultMap = orderSummaryCheckoutStepDisplayContext.getCommerceOrderValidatorResults();
%>

<c:if test="${not empty goToConfirmation}">
	<c:redirect url="${goToConfirmation}" />
</c:if>

<div class="commerce-order-summary">
	<liferay-ui:error exception="<%= CommerceOrderBillingAddressException.class %>" message="please-select-a-valid-billing-address" />
	<liferay-ui:error exception="<%= CommerceOrderPaymentMethodException.class %>" message="please-select-a-valid-payment-method" />
	<liferay-ui:error exception="<%= CommerceOrderShippingAddressException.class %>" message="please-select-a-valid-shipping-address" />
	<liferay-ui:error exception="<%= CommerceOrderShippingMethodException.class %>" message="please-select-a-valid-shipping-method" />

	<aui:row>
		<aui:col cssClass="commerce-checkout-summary" width="<%= 70 %>">
			<ul class="commerce-checkout-summary-header">
				<li class="autofit-row">
					<div class="autofit-col autofit-col-expand">
						<h5 class="commerce-title">
							<liferay-ui:message arguments="<%= orderSummaryCheckoutStepDisplayContext.getCommerceOrderItemsQuantity() %>" key="items-x" translateArguments="<%= false %>" />
						</h5>
					</div>
				</li>
			</ul>

			<div class="commerce-checkout-summary-body" id="<portlet:namespace />entriesContainer">
				<liferay-ui:search-container
					cssClass="list-group-flush"
					id="commerceOrderItems"
				>
					<liferay-ui:search-container-results
						results="<%= commerceOrder.getCommerceOrderItems() %>"
					/>

					<liferay-ui:search-container-row
						className="com.liferay.commerce.model.CommerceOrderItem"
						cssClass="entry-display-style"
						keyProperty="CommerceOrderItemId"
						modelVar="commerceOrderItem"
					>

						<%
						CPDefinition cpDefinition = commerceOrderItem.getCPDefinition();

						String thumbnailSrc = orderSummaryCheckoutStepDisplayContext.getCommerceOrderItemThumbnailSrc(commerceOrderItem);
						%>

						<liferay-ui:search-container-column-image
							cssClass="thumbnail-section"
							name="image"
							src="<%= thumbnailSrc %>"
						/>

						<liferay-ui:search-container-column-text
							cssClass="autofit-col-expand"
							name="product"
						>
							<div class="description-section">
								<div class="list-group-title">
									<%= HtmlUtil.escape(cpDefinition.getName(themeDisplay.getLanguageId())) %>
								</div>

								<%
								List<KeyValuePair> keyValuePairs = orderSummaryCheckoutStepDisplayContext.getKeyValuePairs(commerceOrderItem.getJson(), locale);

								StringJoiner stringJoiner = new StringJoiner(StringPool.COMMA);

								for (KeyValuePair keyValuePair : keyValuePairs) {
									stringJoiner.add(keyValuePair.getValue());
								}
								%>

								<div class="list-group-subtitle"><%= HtmlUtil.escape(stringJoiner.toString()) %></div>

								<c:if test="<%= !commerceOrderValidatorResultMap.isEmpty() %>">

									<%
									List<CommerceOrderValidatorResult> commerceOrderValidatorResults = commerceOrderValidatorResultMap.get(commerceOrderItem.getCommerceOrderItemId());

									for (CommerceOrderValidatorResult commerceOrderValidatorResult : commerceOrderValidatorResults) {
									%>

										<div class="alert-danger commerce-alert-danger">
											<liferay-ui:message key="<%= commerceOrderValidatorResult.getLocalizedMessage() %>" />
										</div>

									<%
									}
									%>

								</c:if>
							</div>
						</liferay-ui:search-container-column-text>

						<liferay-ui:search-container-column-text
							name="quantity"
						>
							<div class="quantity-section">
								<span class="commerce-quantity"><%= commerceOrderItem.getQuantity() %></span><span class="inline-item-after">x</span>
							</div>
						</liferay-ui:search-container-column-text>

						<%
						CommerceProductPrice commerceProductPrice = orderSummaryCheckoutStepDisplayContext.getCommerceProductPrice(commerceOrderItem);
						CPInstance cpInstance = commerceOrderItem.getCPInstance();
						%>

						<liferay-ui:search-container-column-text
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

									<c:if test="<%= Validator.isNotNull(cpInstance.getCPSubscriptionInfo()) %>">
										<span class="commerce-subscription-info">
											<liferay-commerce:subscription-info
												CPInstanceId="<%= commerceOrderItem.getCPInstanceId() %>"
												showDuration="<%= false %>"
											/>
										</span>
									</c:if>
								</div>
							</c:if>
						</liferay-ui:search-container-column-text>

						<liferay-ui:search-container-column-text
							name="discount"
						>
							<c:if test="<%= commerceProductPrice != null %>">

								<%
								CommerceDiscountValue discountValue = commerceProductPrice.getDiscountValue();

								CommerceMoney discountAmount = null;

								if (discountValue != null) {
									discountAmount = discountValue.getDiscountAmount();
								}
								%>

								<div class="value-section">
									<span class="commerce-value">
										<%= (discountAmount == null) ? StringPool.BLANK : HtmlUtil.escape(discountAmount.format(locale)) %>
									</span>
								</div>
							</c:if>
						</liferay-ui:search-container-column-text>

						<liferay-ui:search-container-column-text
							name="final-price"
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
					</liferay-ui:search-container-row>

					<liferay-ui:search-iterator
						displayStyle="list"
						markupView="lexicon"
						paginate="<%= false %>"
					/>
				</liferay-ui:search-container>
			</div>

			<ul class="commerce-checkout-summary-footer">
				<li class="autofit-row commerce-subtotal">
					<div class="autofit-col autofit-col-expand">
						<div class="commerce-description"><liferay-ui:message key="subtotal" /></div>
					</div>

					<div class="autofit-col">
						<div class="commerce-value"><%= HtmlUtil.escape(subtotal.format(locale)) %></div>
					</div>
				</li>

				<c:if test="<%= subtotalDiscountValue != null %>">

					<%
					CommerceMoney subtotalDiscountAmount = subtotalDiscountValue.getDiscountAmount();
					%>

					<li class="autofit-row commerce-subtotal-discount">
						<div class="autofit-col autofit-col-expand">
							<div class="commerce-description"><liferay-ui:message key="subtotal-discount" /></div>
						</div>

						<div class="commerce-value">
							<%= HtmlUtil.escape(subtotalDiscountAmount.format(locale)) %>
						</div>
					</li>
					<li class="autofit-row commerce-subtotal-discount">
						<div class="autofit-col autofit-col-expand"></div>

						<div class="commerce-value">
							<%= HtmlUtil.escape(orderSummaryCheckoutStepDisplayContext.getFormattedPercentage(subtotalDiscountValue.getDiscountPercentage())) %>
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

				<c:if test="<%= shippingDiscountValue != null %>">

					<%
					CommerceMoney shippingDiscountAmount = shippingDiscountValue.getDiscountAmount();
					%>

					<li class="autofit-row commerce-delivery-discount">
						<div class="autofit-col autofit-col-expand">
							<div class="commerce-description"><liferay-ui:message key="delivery-discount" /></div>
						</div>

						<div class="commerce-value">
							<%= HtmlUtil.escape(shippingDiscountAmount.format(locale)) %>
						</div>
					</li>
					<li class="autofit-row commerce-delivery-discount">
						<div class="autofit-col autofit-col-expand"></div>

						<div class="commerce-value">
							<%= HtmlUtil.escape(orderSummaryCheckoutStepDisplayContext.getFormattedPercentage(shippingDiscountValue.getDiscountPercentage())) %>
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

				<c:if test="<%= totalDiscountValue != null %>">

					<%
					CommerceMoney totalDiscountAmount = totalDiscountValue.getDiscountAmount();
					%>

					<li class="autofit-row commerce-total-discount">
						<div class="autofit-col autofit-col-expand">
							<div class="commerce-description"><liferay-ui:message key="total-discount" /></div>
						</div>

						<div class="autofit-col commerce-value">
							<%= HtmlUtil.escape(totalDiscountAmount.format(locale)) %>
						</div>
					</li>
					<li class="autofit-row commerce-total-discount">
						<div class="autofit-col autofit-col-expand"></div>

						<div class="autofit-col commerce-value">
							<%= HtmlUtil.escape(orderSummaryCheckoutStepDisplayContext.getFormattedPercentage(totalDiscountValue.getDiscountPercentage())) %>
						</div>
					</li>
				</c:if>

				<li class="autofit-row commerce-total">
					<div class="autofit-col autofit-col-expand">
						<div class="commerce-description"><liferay-ui:message key="total" /></div>
					</div>

					<div class="autofit-col">
						<div class="commerce-value"><%= HtmlUtil.escape(totalOrder.format(locale)) %></div>
					</div>
				</li>
			</ul>
		</aui:col>

		<aui:col cssClass="commerce-checkout-info" width="<%= 30 %>">

			<%
			CommerceAddress shippingAddress = commerceOrder.getShippingAddress();
			%>

			<c:if test="<%= shippingAddress != null %>">
				<address class="shipping-address">
					<h5>
						<liferay-ui:message key="shipping-address" />
					</h5>

					<%
					request.setAttribute("address.jsp-commerceAddress", shippingAddress);
					%>

					<%= HtmlUtil.escape(shippingAddress.getName()) %> <br />
					<%= HtmlUtil.escape(shippingAddress.getStreet1()) %> <br />

					<c:if test="<%= Validator.isNotNull(shippingAddress.getStreet2()) %>">
						<%= HtmlUtil.escape(shippingAddress.getStreet2()) %> <br />
					</c:if>

					<c:if test="<%= Validator.isNotNull(shippingAddress.getStreet3()) %>">
						<%= HtmlUtil.escape(shippingAddress.getStreet3()) %> <br />
					</c:if>

					<%= HtmlUtil.escape(shippingAddress.getCity()) %> <br />

					<%
					CommerceCountry commerceCountry = shippingAddress.getCommerceCountry();
					%>

					<c:if test="<%= commerceCountry != null %>">
						<%= HtmlUtil.escape(commerceCountry.getName(locale)) %><br />
					</c:if>
				</address>
			</c:if>

			<c:if test="<%= Validator.isNotNull(commerceShippingOptionName) %>">
				<div class="shipping-method">
					<h5>
						<liferay-ui:message key="method" />
					</h5>

					<div class="shipping-description">
						<%= HtmlUtil.escape(commerceShippingOptionName) %>
					</div>

					<div class="shipping-cost">
						<%= HtmlUtil.escape(shippingValue.format(locale)) %>
					</div>
				</div>
			</c:if>

			<c:if test="<%= Validator.isNotNull(commercePaymentMethodName) %>">
				<div class="payment-method">
					<h5>
						<liferay-ui:message key="payment" />
					</h5>

					<div class="shipping-description">
						<%= HtmlUtil.escape(commercePaymentMethodName) %>
					</div>
				</div>
			</c:if>
		</aui:col>
	</aui:row>
</div>