<#if cpAssetCategoriesNavigationDisplayContext.getAssetCategories()?has_content>
	<div class="card-page-nowrap commerce-category-navigation-inline">
		<div class="commerce-component-header product-detail-header">
			<h2 class="component-title">Explore Categories</h2>
		</div>

		<ul class="card-page commerce-category-navigation-card-page commerce-simple-card">
			<#list cpAssetCategoriesNavigationDisplayContext.getAssetCategories() as curAssetCategory>
				<li class="card-page-item">
					<a class="card" href="${cpAssetCategoriesNavigationDisplayContext.getFriendlyURL(curAssetCategory.getCategoryId(), themeDisplay)}">
						<#if cpAssetCategoriesNavigationDisplayContext.getDefaultImageSrc(curAssetCategory.getCategoryId(), themeDisplay)??>
							<img class="card-image" src="${cpAssetCategoriesNavigationDisplayContext.getDefaultImageSrc(curAssetCategory.getCategoryId(), themeDisplay)}">
						</#if>

						<span class="card-title">${curAssetCategory.name}</span>
					</a>
				</li>
			</#list>
		</ul>
	</div>
</#if>