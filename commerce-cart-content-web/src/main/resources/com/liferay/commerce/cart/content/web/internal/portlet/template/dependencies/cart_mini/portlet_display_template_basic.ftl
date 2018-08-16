<#assign count = 0 />

<#if entries?has_content>
	<div class="row">
		<#assign
		orderPrice = commerceCartContentMiniDisplayContext.getCommerceOrderPrice()

		subTotal = orderPrice.getSubtotal()
		/>

		<div class="col-md-12">
			<strong>Total: ${subTotal.format(themeDisplay.getLocale())}</strong>
		</div>

		<#list entries as curCommerceOrderItem>
			<#assign
				cpDefinition = curCommerceOrderItem.getCPDefinition()

				image = cpDefinition.getDefaultImageThumbnailSrc(themeDisplay)

				productURL = commerceCartContentMiniDisplayContext.getCPDefinitionURL(cpDefinition.getCPDefinitionId(), themeDisplay)

				name = cpDefinition.getName(themeDisplay.getLanguageId())
			/>

			<div class="col-md-6">
				<div class="row">
					<img src="${image}">
				</div>

				<div class="row">
					<a href="${productURL}">
						<strong>${name}</strong>
					</a>
				</div>

				<div class="row">
					<@liferay_commerce["format-price"]
						price=curCommerceOrderItem.getFinalPrice()
						quantity=1
					/>
				</div>
			</div>

			<#assign count = count + 1 />

			<#if count gte 2>
				</div>

				<div class="row">

				<#assign count = 0 />
			</#if>
		</#list>
	</div>
</#if>