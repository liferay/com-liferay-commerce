<div class="categories-carousel--container">
	<header>
<div class="title">
			<h3>${SectionTitle.getData()}</h3>
		</div>

		<div class="pagination"></div>
	</header>
<div class="wrapper">
		<#if CategoryImage.getSiblings()?has_content>
<div class="list-page">
				<#list CategoryImage.getSiblings()?chunk(4) as cur_CategoryImagePage>
					<#list cur_CategoryImagePage as cur_CategoryImage>
						<#if cur_CategoryImage.getData()?? && cur_CategoryImage.getData() != "">
<div class="card-type-asset form-check form-check-card form-check-top-left image-card">
<div class="card">
<div class="aspect-ratio card-item-first">
<div class="custom-control">
											<label>
<img alt="thumbnail" class="aspect-ratio-item-center-middle aspect-ratio-item-fluid" src="${cur_CategoryImage.getData()}" />
											</label>
										</div>
									</div>
<div class="card-body">
<div class="card-row">
<div class="autofit-col autofit-col-expand">
												<div class="card-title text-truncate" title="thumbnail_coffee.jpg">${cur_CategoryImage.CategoryTitle.getData()}</div>
												<div class="card-subtitle text-truncate" title="Author Action">${cur_CategoryImage.CategoryDescription.getData()}</div>
											</div>
<div class="autofit-col">
											</div>
										</div>
									</div>
								</div>
							</div>
						</#if>
					</#list>
				</#list>
			</div>
		</#if>
	</div>
</div>