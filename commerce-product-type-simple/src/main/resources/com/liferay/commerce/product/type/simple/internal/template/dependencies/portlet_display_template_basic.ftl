<#assign
	cpCatalogEntry = simpleCPTypeDisplayContext.getCPCatalogEntry()

	cpInstance = simpleCPTypeDisplayContext.getDefaultCPInstance()
/>

<div class="container-fluid product-detail" id="<@portlet.namespace />${cpCatalogEntry.getCPDefinitionId()}ProductContent">
	<div class="row">
		<div class="product-detail-header">
			<div class="col-lg-6 col-md-7">
				<div class="row">
					<div class="col-lg-2 col-md-3 col-xs-2">
						<div id="<@portlet.namespace />thumbs-container">
							<#assign images = simpleCPTypeDisplayContext.getImages() />

							<#if images?has_content>
								<#list images as curImage>
									<#assign url = simpleCPTypeDisplayContext.getImageURL(curImage.getFileEntry(), themeDisplay) />

									<div class="card thumb" data-url="${url}">
										<img class="center-block img-responsive" src="${url}">
									</div>
								</#list>
							</#if>
						</div>
					</div>

					<div class="col-lg-10 col-md-9 col-xs-10 full-image">
						<#assign cpAttachmentFileEntry = simpleCPTypeDisplayContext.getDefaultImage() />

						<#if cpAttachmentFileEntry??>
							<img class="center-block img-responsive" id="<@portlet.namespace />full-image" src="${simpleCPTypeDisplayContext.getImageURL(cpAttachmentFileEntry.getFileEntry(), themeDisplay)}">
						</#if>
					</div>
				</div>
			</div>

			<div class="col-lg-6 col-md-5">
				<h1>${HtmlUtil.escape(cpCatalogEntry.getName())}</h1>

				<#if cpInstance??>
					<h4 class="sku">${HtmlUtil.escape(cpInstance.getSku())}</h4>

					<div class="price">
						<@liferay_commerce["price"]
							CPDefinitionId=cpCatalogEntry.getCPDefinitionId()
							CPInstanceId=cpInstance.getCPInstanceId()
						/>
					</div>

					<div class="availability">${simpleCPTypeDisplayContext.getAvailabilityLabel()}</div>

					<div class="availabilityEstimate">${simpleCPTypeDisplayContext.getAvailabilityEstimateLabel()}</div>

					<div class="stockQuantity">${simpleCPTypeDisplayContext.getStockQuantityLabel()}</div>
				<#else>
					<h4 class="sku" data-text-cp-instance-sku=""></h4>

					<div class="price" data-text-cp-instance-price="" ></div>

					<div class="availability" data-text-cp-instance-availability="" ></div>

					<div class="availabilityEstimate" data-text-cp-instance-availability-estimate="" ></div>

					<div class="stockQuantity" data-text-cp-instance-stock-quantity="" ></div>
				</#if>

				<div class="row">
					<div class="col-md-12">
						<div class="options">
							${simpleCPTypeDisplayContext.renderOptions(renderRequest, renderResponse)}
						</div>
					</div>
				</div>

				<div class="row">
					<div class="col-md-12">
						<@liferay_util["dynamic-include"] key="com.liferay.commerce.product.content.web#/add_to_cart#" />
					</div>
				</div>
			</div>
		</div>
	</div>

	<#assign
		cpDefinitionSpecificationOptionValues = simpleCPTypeDisplayContext.getCPDefinitionSpecificationOptionValues()

		cpOptionCategories = simpleCPTypeDisplayContext.getCPOptionCategories()

		cpAttachmentFileEntries = simpleCPTypeDisplayContext.getCPAttachmentFileEntries()
	/>

	<div class="row">
		<div class="product-detail-body">
			<div class="nav-tabs-centered">
				<ul class="nav nav-tabs" role="tablist">
					<li class="active" role="presentation">
						<a aria-controls="<@portlet.namespace />description" aria-expanded="true" data-toggle="tab" href="#<@portlet.namespace />description" role="tab">
							${languageUtil.get(resourceBundle, "description")}
						</a>
					</li>

					<#if simpleCPTypeDisplayContext.hasCPDefinitionSpecificationOptionValues()>
						<li role="presentation">
							<a aria-controls="<@portlet.namespace />specification" aria-expanded="false" data-toggle="tab" href="#<@portlet.namespace />specification" role="tab">
								${languageUtil.get(resourceBundle, "specification-options")}
							</a>
						</li>
					</#if>

					<#if cpAttachmentFileEntries?has_content>
						<li role="presentation">
							<a aria-controls="<@portlet.namespace />attachments" aria-expanded="false" data-toggle="tab" href="#<@portlet.namespace />attachments" role="tab">
								${languageUtil.get(resourceBundle, "attachments")}
							</a>
						</li>
					</#if>
				</ul>

				<div class="tab-content">
					<div class="active tab-pane" id="<@portlet.namespace />description">
						<p>${cpCatalogEntry.getDescription()}</p>
					</div>

					<#if simpleCPTypeDisplayContext.hasCPDefinitionSpecificationOptionValues()>
						<div class="tab-pane" id="<@portlet.namespace />specification">
							<div class="table-responsive">
								<table class="table table-bordered table-striped">
									<#list cpDefinitionSpecificationOptionValues as cpDefinitionSpecificationOptionValue>
										<#assign cpSpecificationOption = cpDefinitionSpecificationOptionValue.getCPSpecificationOption() />

										<tr>
											<td>${cpSpecificationOption.getTitle(themeDisplay.getLanguageId())}</td>
											<td>${cpDefinitionSpecificationOptionValue.getValue(themeDisplay.getLanguageId())}</td>
										</tr>
									</#list>
								</table>
							</div>

							<#list cpOptionCategories as cpOptionCategory>
								<#assign categorizedCPDefinitionSpecificationOptionValues = simpleCPTypeDisplayContext.getCategorizedCPDefinitionSpecificationOptionValues(cpOptionCategory.getCPOptionCategoryId()) />

								<#if categorizedCPDefinitionSpecificationOptionValues?has_content>
									<div class="table-responsive">
										<table class="table table-bordered table-striped">
											<tr>
												<th>${cpOptionCategory.getTitle(themeDisplay.getLanguageId())}</th>
												<th></th>
											</tr>

											<#list categorizedCPDefinitionSpecificationOptionValues as cpDefinitionSpecificationOptionValue>
												<#assign cpSpecificationOption = cpDefinitionSpecificationOptionValue.getCPSpecificationOption() />

												<tr>
													<td>${cpSpecificationOption.getTitle(themeDisplay.getLanguageId())}</td>
													<td>${cpDefinitionSpecificationOptionValue.getValue(themeDisplay.getLanguageId())}</td>
												</tr>
											</#list>
										</table>
									</div>
								</#if>
							</#list>
						</div>
					</#if>

					<#if cpAttachmentFileEntries?has_content>
						<div class="tab-pane" id="<@portlet.namespace />attachments">
							<div class="table-responsive">
								<table class="table table-bordered table-striped">
									<tr>
										<#assign count = 0 />

										<#list cpAttachmentFileEntries as curCPAttachmentFileEntry>
											<#assign fileEntry = curCPAttachmentFileEntry.getFileEntry() />

											<td>
												<span>${curCPAttachmentFileEntry.getTitle(themeDisplay.getLanguageId())}</span>

												<span>
													<@liferay_aui.icon
														cssClass="icon-monospaced"
														image="download"
														markupView="lexicon"
														url="${simpleCPTypeDisplayContext.getDownloadFileEntryURL(fileEntry)}"
													/>
												</span>
											</td>

											<#assign count = count + 1 />

											<#if count gte 2>
												</tr>
												<tr>

												<#assign count = 0 />
											</#if>
										</#list>
									<tr>
								</table>
							</div>
						</div>
					</#if>
				</div>
			</div>
		</div>
	</div>
</div>

<script>
	$(document).ready(
		function() {
			$(".thumb").click(
				function() {
					$("#<@portlet.namespace />full-image").attr("src",$(this).attr("data-url"));
				}
			);
		}
	);
</script>

<@liferay_aui.script use="liferay-commerce-product-content">
	var productContent = new Liferay.Portlet.ProductContent(
		{
			cpDefinitionId: ${simpleCPTypeDisplayContext.getCPDefinitionId()},
			fullImageSelector : '#<@portlet.namespace />full-image',
			namespace: '<@portlet.namespace />',
			productContentSelector: '#<@portlet.namespace />${cpCatalogEntry.getCPDefinitionId()}ProductContent',
			thumbsContainerSelector : '#<@portlet.namespace />thumbs-container',
			viewAttachmentURL: '${simpleCPTypeDisplayContext.getViewAttachmentURL().toString()}'
		}
	);

	Liferay.component('<@portlet.namespace />${cpCatalogEntry.getCPDefinitionId()}ProductContent', productContent);
</@>