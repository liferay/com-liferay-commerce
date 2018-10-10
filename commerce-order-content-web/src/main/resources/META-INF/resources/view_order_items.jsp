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
CommerceOrderItemContentDisplayContext commerceOrderItemContentDisplayContext = (CommerceOrderItemContentDisplayContext)request.getAttribute(WebKeys.PORTLET_DISPLAY_CONTEXT);

CommerceOrder commerceOrder = commerceOrderItemContentDisplayContext.getCommerceOrder();
CommerceOrderPrice commerceOrderPrice = commerceOrderItemContentDisplayContext.getCommerceOrderPrice();

CommerceDiscountValue shippingDiscountValue = commerceOrderPrice.getShippingDiscountValue();
CommerceMoney shippingValue = commerceOrderPrice.getShippingValue();
CommerceMoney subtotal = commerceOrderPrice.getSubtotal();
CommerceDiscountValue subtotalDiscountValue = commerceOrderPrice.getSubtotalDiscountValue();
CommerceMoney taxValue = commerceOrderPrice.getTaxValue();
CommerceDiscountValue totalDiscountValue = commerceOrderPrice.getTotalDiscountValue();
CommerceMoney totalOrder = commerceOrderPrice.getTotal();

String commercePaymentMethodName = StringPool.BLANK;
String commerceShippingOptionName = StringPool.BLANK;

if (commerceOrder != null) {
	CommercePaymentMethod commercePaymentMethod = commerceOrder.getCommercePaymentMethod();

	if (commercePaymentMethod != null) {
		commercePaymentMethodName = commercePaymentMethod.getName(locale);
	}

	commerceShippingOptionName = commerceOrder.getShippingOptionName();
}

Map<Long, List<CommerceOrderValidatorResult>> commerceOrderValidatorResultMap = commerceOrderItemContentDisplayContext.getCommerceOrderValidatorResults();
%>

<div class="commerce-order-content">
	<portlet:renderURL var="backURL" />

	<liferay-ui:header
		backURL="<%= backURL %>"
		title="back"
	/>

	<c:choose>
		<c:when test="<%= commerceOrder == null %>">
			<div class="alert alert-info mx-auto">
				<liferay-ui:message key="order-is-no-longer-available" />
			</div>
		</c:when>
		<c:otherwise>
			<aui:row>
				<aui:col width="<%= 70 %>">
					<div id="<portlet:namespace />entriesContainer">
						<liferay-ui:search-container
							cssClass="list-group-flush"
							id="commerceOrderItems"
							searchContainer="<%= commerceOrderItemContentDisplayContext.getSearchContainer() %>"
						>
							<liferay-ui:search-container-row
								className="com.liferay.commerce.model.CommerceOrderItem"
								cssClass="entry-display-style"
								keyProperty="CommerceOrderItemId"
								modelVar="commerceOrderItem"
							>

								<%
								CPDefinition cpDefinition = commerceOrderItem.getCPDefinition();

								String thumbnailSrc = commerceOrderItemContentDisplayContext.getCommerceOrderItemThumbnailSrc(commerceOrderItem, themeDisplay);
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
										<div class="list-group-text">Brand</div>

										<div class="list-group-title">
											<%= HtmlUtil.escape(cpDefinition.getName(themeDisplay.getLanguageId())) %>
										</div>

										<%
										List<KeyValuePair> keyValuePairs = commerceOrderItemContentDisplayContext.getKeyValuePairs(commerceOrderItem.getJson(), locale);

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
													<c:choose>
														<c:when test="<%= commerceOrderValidatorResult.hasArgument() %>">
															<liferay-ui:message arguments="<%= commerceOrderValidatorResult.getArgument() %>" key="<%= commerceOrderValidatorResult.getMessage() %>" />
														</c:when>
														<c:otherwise>
															<liferay-ui:message key="<%= commerceOrderValidatorResult.getMessage() %>" />
														</c:otherwise>
													</c:choose>
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
								CommerceProductPrice commerceProductPrice = commerceOrderItemContentDisplayContext.getCommerceProductPrice(commerceOrderItem);
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
												<%= unitPrice.format(locale) %>
											</span>
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
												<%= (discountAmount == null) ? StringPool.BLANK : discountAmount.format(locale) %>
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
												<%= finalPrice.format(locale) %>
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

						<c:if test="<%= (subtotalDiscountValue != null) && (BigDecimal.ZERO.compareTo(subtotalDiscountValue.getDiscountPercentage()) < 0) %>">

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
									<%= HtmlUtil.escape(commerceOrderItemContentDisplayContext.getFormattedPercentage(subtotalDiscountValue.getDiscountPercentage())) %>
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

								<div class="commerce-value">
									<%= HtmlUtil.escape(shippingDiscountAmount.format(locale)) %>
								</div>
							</li>
							<li class="autofit-row commerce-delivery-discount">
								<div class="autofit-col autofit-col-expand"></div>

								<div class="commerce-value">
									<%= HtmlUtil.escape(commerceOrderItemContentDisplayContext.getFormattedPercentage(shippingDiscountValue.getDiscountPercentage())) %>
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

								<div class="autofit-col commerce-value">
									<%= HtmlUtil.escape(totalDiscountAmount.format(locale)) %>
								</div>
							</li>
							<li class="autofit-row commerce-total-discount">
								<div class="autofit-col autofit-col-expand"></div>

								<div class="autofit-col commerce-value">
									<%= HtmlUtil.escape(commerceOrderItemContentDisplayContext.getFormattedPercentage(totalDiscountValue.getDiscountPercentage())) %>
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

							<%= shippingAddress.getName() %> <br />
							<%= shippingAddress.getStreet1() %> <br />

							<c:if test="<%= Validator.isNotNull(shippingAddress.getStreet2()) %>">
								<%= shippingAddress.getStreet2() %> <br />
							</c:if>

							<c:if test="<%= Validator.isNotNull(shippingAddress.getStreet3()) %>">
								<%= shippingAddress.getStreet3() %> <br />
							</c:if>

							<%= shippingAddress.getCity() %> <br />

							<%
							CommerceCountry commerceCountry = shippingAddress.getCommerceCountry();
							%>

							<c:if test="<%= commerceCountry != null %>">
								<%= HtmlUtil.escape(commerceCountry.getName(locale)) %><br />
							</c:if>
						</address>
					</c:if>

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

					<div class="payment-method">
						<h5>
							<liferay-ui:message key="payment" />
						</h5>

						<div class="shipping-description">
							<%= HtmlUtil.escape(commercePaymentMethodName) %>
						</div>
					</div>
				</aui:col>
			</aui:row>
		</c:otherwise>
	</c:choose>
</div>