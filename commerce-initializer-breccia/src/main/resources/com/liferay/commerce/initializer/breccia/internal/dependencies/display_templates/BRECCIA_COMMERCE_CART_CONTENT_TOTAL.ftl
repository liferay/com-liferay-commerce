<#assign
	orderId = '${commerceCartContentTotalDisplayContext.getCommerceOrderId()}'
	orderTotalItems = 0
	commerceOrderPrice = commerceCartContentTotalDisplayContext.getCommerceOrderPrice()
	subtotalPrice = commerceOrderPrice.getSubtotal()
	shippingValuePrice = commerceOrderPrice.getShippingValue()
	taxValuePrice = commerceOrderPrice.getTaxValue()
	totalOrderPrice = commerceOrderPrice.getTotal()
	shippingPrice = '${shippingValuePrice.format(locale)}'
	subTotal = '${subtotalPrice.format(locale)}'
	tax = '${taxValuePrice.format(locale)}'
	total = '${totalOrderPrice.format(locale)}'
/>

<#if commerceCartContentTotalDisplayContext.getCommerceOrderId() == 0>
	<#assign
		orderId = '0 Items in your cart'
	/>
</#if>

<#list entries as curCommerceOrderItem>
	<#assign orderTotalItems = orderTotalItems + curCommerceOrderItem.getQuantity() />
</#list>

<div class="commerce-component-header">
	<h2 class="component-title">
		<span class="component-title-label">${languageUtil.get(request, "total")}</span>
		<span class="component-title-value">${total}</span>
	</h2>
</div>

<div class="commerce-panel-secondary panel">
	<div class="panel-header">
		<span class="panel-title">${languageUtil.format(request, "order-x", orderId, false)}</span>
	</div>

	<div class="panel-body">
		<ul class="commerce-cart-content-total-list list-unstyled">
			<li class="autofit-float autofit-row autofit-row-center commerce-cart-content-total-item">
				<div class="autofit-col autofit-col-expand">
					<div class="autofit-section">
						<span class="commerce-cart-content-total-label subtotal-label">${languageUtil.get(request, "subtotal")} (${orderTotalItems})</span>
					</div>
				</div>

				<div class="autofit-col">
					<div class="autofit-section">
						<span class="commerce-cart-content-total-value subtotal-value">${subTotal}</span>
					</div>
				</div>
			</li>
			<li class="autofit-float autofit-row autofit-row-center commerce-cart-content-total-item">
				<div class="autofit-col autofit-col-expand">
					<div class="autofit-section">
						<span class="commerce-cart-content-total-label shipping-label">${languageUtil.get(request, "delivery")}</span>
					</div>
				</div>

				<div class="autofit-col">
					<div class="autofit-section">
						<span class="commerce-cart-content-total-value shipping-value">${shippingPrice}</span>
					</div>
				</div>
			</li>
			<li class="autofit-float autofit-row autofit-row-center commerce-cart-content-total-item">
				<div class="autofit-col autofit-col-expand">
					<div class="autofit-section">
						<span class="commerce-cart-content-total-label tax-label">${languageUtil.get(request, "tax")}</span>
					</div>
				</div>

				<div class="autofit-col">
					<div class="autofit-section">
						<span class="commerce-cart-content-total-value tax-value">${tax}</span>
					</div>
				</div>
			</li>
			<li class="commerce-cart-content-total-divider"></li>
			<li class="autofit-float autofit-row autofit-row-center commerce-cart-content-total-item">
				<div class="autofit-col autofit-col-expand">
					<div class="autofit-section">
						<span class="commerce-cart-content-total-label total-label">${languageUtil.get(request, "total")}</span>
					</div>
				</div>

				<div class="autofit-col">
					<div class="autofit-section">
						<span class="commerce-cart-content-total-value total-value">${total}</span>
					</div>
				</div>
			</li>
		</ul>
	</div>
</div>

<div class="autofit-float autofit-row autofit-row-center commerce-cart-content-total-actions">
	<div class="autofit-col autofit-col-expand">
		<div class="autofit-section">
			<a class="btn btn-primary commerce-btn-cta" href="${commerceCartContentTotalDisplayContext.getCheckoutPortletURL()}" role="button">${languageUtil.get(request, "checkout")}</a>
		</div>
	</div>
</div>

<@liferay_aui["script"]>
	Liferay.after(
		'commerce:productAddedToCart',
		function(event) {
			Liferay.Portlet.refresh('#p_p_id<@portlet.namespace />');
		}
	);
</@>