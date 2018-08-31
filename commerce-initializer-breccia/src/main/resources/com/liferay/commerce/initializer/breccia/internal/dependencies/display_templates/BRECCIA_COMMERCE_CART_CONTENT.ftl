<div class="breccia-commerce-cart-content">
	<#if entries?has_content>
		<div class="table-responsive">
			<table class="table table-autofit table-list">
				<thead>
					<tr>
						<th class="image-column"></th>
						<th class="product-column table-cell-expand">${languageUtil.get(request, "product")}</th>
						<th class="quantity-column table-cell-expand">${languageUtil.get(request, "quantity")}</th>
						<th class="subtotal-column table-cell-expand">${languageUtil.get(request, "price")}</th>
						<th class="subtotal-column table-cell-expand">${languageUtil.get(request, "discount")}</th>
						<th class="subtotal-column table-cell-expand">${languageUtil.get(request, "final-price")}</th>
						<th class="subtotal-column table-cell-expand"></th>
					</tr>
				</thead>

				<tbody>
					<#list entries as curCommerceOrderItem>
						<#assign
							commerceProductPrice = commerceCartContentDisplayContext.getCommerceProductPrice(curCommerceOrderItem)

							cpDefinition = curCommerceOrderItem.getCPDefinition()

							deleteURL = commerceCartContentDisplayContext.getDeleteURL(curCommerceOrderItem)

							finalPriceMoney = commerceProductPrice.getFinalPrice()

							image = ''

							productURL = commerceCartContentDisplayContext.getCPDefinitionURL(cpDefinition.getCPDefinitionId(), themeDisplay)

							title = cpDefinition.getName()

							unitPriceMoney = commerceProductPrice.getUnitPrice()
						/>

						<#if commerceCartContentDisplayContext.getCommerceOrderItemThumbnailSrc(curCommerceOrderItem, themeDisplay)??>
							<#assign
								image = commerceCartContentDisplayContext.getCommerceOrderItemThumbnailSrc(curCommerceOrderItem, themeDisplay)
							/>
						</#if>

						<tr>
							<td class="image-column">
								<#if image?has_content>
									<img class="commerce-cart-content-image" src="${image}">
								</#if>
							</td>
							<td class="product-column table-cell-expand">
								<div class="table-title">
									<a href="${productURL}">${title}</a>
								</div>

								<div class="table-text">${languageUtil.get(request, "sku")} ${curCommerceOrderItem.getSku()}</div>
							</td>
							<td class="quantity-column table-cell-expand">
								<@liferay_commerce_cart["quantity-control"] commerceOrderItemId=curCommerceOrderItem.getCommerceOrderItemId() />
							</td>
							<td class="subtotal-column table-cell-expand">
								<div class="commerce-cart-content-value unit-price-value">
									${unitPriceMoney.format(locale)}
								</div>
							</td>
							<td class="subtotal-column table-cell-expand">
								<div class="commerce-cart-content-value discount-value">
									<#if commerceProductPrice.getDiscountValue()??>
										<#assign
											discountValue = commerceProductPrice.getDiscountValue()

											discountAmount = discountValue.getDiscountAmount()
										/>

										${discountAmount.format(locale)}
									</#if>
								</div>
							</td>
							<td class="subtotal-column table-cell-expand">
								<div class="commerce-cart-content-value final-price-value">
									${finalPriceMoney.format(locale)}
								</div>
							</td>
							<td class="subtotal-column table-cell-expand">
								<@liferay_ui["icon-delete"]
									label=true
									url=deleteURL
								/>
							</td>
						</tr>
					</#list>
				</tbody>
			</table>
		</div>
	<#else>
		<h3>${languageUtil.get(resourceBundle, "there-are-no-items-in-your-cart")}</h3>
	</#if>
</div>

<div class="autofit-float autofit-row autofit-row-center commerce-cart-content-actions">
	<div class="autofit-col autofit-col-expand">
		<div class="autofit-section">
			<a class="btn btn-secondary" href="${themeDisplay.getLayout().getGroup().getDisplayURL(themeDisplay, false)}">${languageUtil.get(request, "continue-shopping")}</a>
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