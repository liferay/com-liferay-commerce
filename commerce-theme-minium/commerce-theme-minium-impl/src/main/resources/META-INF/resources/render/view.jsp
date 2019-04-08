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
CPContentHelper cpContentHelper = (CPContentHelper)request.getAttribute(CPContentWebKeys.CP_CONTENT_HELPER);

CPCatalogEntry cpCatalogEntry = cpContentHelper.getCPCatalogEntry(request);
CPSku cpSku = cpContentHelper.getDefaultCPSku(cpCatalogEntry);

long cpDefinitionId = cpCatalogEntry.getCPDefinitionId();

String addToCartId = PortalUtil.generateRandomKey(request, "add-to-cart");
String galleryId = PortalUtil.generateRandomKey(request, "gallery");
NPMResolver npmResolver = NPMResolverProvider.getNPMResolver();
%>

<div class="container-fluid product-detail" id="<portlet:namespace /><%= cpDefinitionId %>ProductContent">
	<div class="row">
		<div class="col-6" id="minium-product-gallery">

			<%
			Map<String, Object> context = new HashMap<>();
			context.put("images", cpContentHelper.getImages(cpDefinitionId, themeDisplay));
			context.put("selected", 0);
			%>

			<soy:template-renderer
				componentId="<%= galleryId %>"
				context="<%= context %>"
				module='<%= npmResolver.resolveModuleName("commerce-theme-minium-impl/product_gallery/MiniumProductGallery.es") %>'
				templateNamespace="MiniumProductGallery.render"
			/>
		</div>

		<div class="col-6">
			<header class="minium-product-header">
				<commerce-ui:compare-checkbox
					componentId="compareCheckbox"
					CPDefinitionId="<%= cpDefinitionId %>"
				/>

				<h3 class="minium-product-header__tagline" data-text-cp-instance-sku>
					<%= (cpSku == null) ? StringPool.BLANK : HtmlUtil.escape(cpSku.getSku()) %>
				</h3>

				<h2 class="minium-product-header__title"><%= HtmlUtil.escape(cpCatalogEntry.getName()) %></h2>

				<h4 class="minium-product-header__subtitle" data-text-cp-instance-manufacturer-part-number>
					<%= (cpSku == null) ? StringPool.BLANK : HtmlUtil.escape(cpSku.getManufacturerPartNumber()) %>
				</h4>

				<h4 class="minium-product-header__subtitle" data-text-cp-instance-gtin>
					<%= (cpSku == null) ? StringPool.BLANK : HtmlUtil.escape(cpSku.getGtin()) %>
				</h4>

				<c:choose>
					<c:when test="<%= cpSku != null %>">
						<div class="availability"><%= cpContentHelper.getAvailabilityLabel(request) %></div>

						<div class="availabilityEstimate"><%= cpContentHelper.getAvailabilityEstimateLabel(request) %></div>

						<div class="stockQuantity"><%= cpContentHelper.getStockQuantityLabel(request) %></div>
					</c:when>
					<c:otherwise>
						<div class="availability" data-text-cp-instance-availability></div>

						<div class="availabilityEstimate" data-text-cp-instance-availability-estimate></div>

						<div class="stockQuantity" data-text-cp-instance-stock-quantity></div>
					</c:otherwise>
				</c:choose>
			</header>

			<p><%= cpCatalogEntry.getDescription() %></p>

			<h4 class="commerce-subscription-info w-100" data-text-cp-instance-subscription-info>
				<c:if test="<%= cpSku != null %>">
					<liferay-commerce:subscription-info
						CPInstanceId="<%= cpSku.getCPInstanceId() %>"
					/>
				</c:if>
			</h4>

			<div class="product-detail-options">
				<%= cpContentHelper.renderOptions(renderRequest, renderResponse) %>

				<script>
					AUI().use(
						'liferay-portlet-url', function(A) {
						Liferay.on(
							'<%= cpDefinitionId %>DDMForm:render', function() {
								function ddmFormChange(valueChangeEvent) {
									checkCPInstance();
								}

								function checkCPInstance() {
									const portletURL = Liferay.PortletURL.createActionURL();
									var CP_CONTENT_WEB_PORTLET_KEY = 'com_liferay_commerce_product_content_web_internal_portlet_CPContentPortlet';
									var CP_INSTANCE_CHANGE_EVENT = 'CPInstance:change';

									portletURL.setPortletId(CP_CONTENT_WEB_PORTLET_KEY);
									portletURL.setName('checkCPInstance');
									portletURL.setParameter('cpDefinitionId', cpDefinitionId);
									portletURL.setParameter('groupId', themeDisplay.getScopeGroupId());
									portletURL.setParameter('p_auth', Liferay.authToken);

									const formData = new FormData();
									formData.append('_' + CP_CONTENT_WEB_PORTLET_KEY + '_ddmFormValues', JSON.stringify(getFormValues()));

									fetch(
										portletURL,
										{
											body: formData,
											credentials: 'include',
											method: 'post'
										}
									).then(
										function(response) {
											return response.json();
										}
									).then(
										function(response) {
											AddToCartButton.productId = response.cpInstanceId;
											AddToCartButton.options = JSON.stringify(getFormValues());
											AddToCartButton.quantity = 0;
											AddToCartButton.settings = {
												maxQuantity: 1000,
												minQuantity: 1,
												multipleQuantities: 1
											};
											AddToCartButton.disabled = false;

											document.querySelector('[data-text-cp-instance-sku]').innerHTML = response.sku || '';
											document.querySelector('[data-text-cp-instance-manufacturer-part-number]').innerHTML = response.manufacturerPartNumber || '';
											document.querySelector('[data-text-cp-instance-gtin]').innerHTML = response.gtin || '';
											document.querySelector('[data-text-cp-instance-availability]').innerHTML = response.availability || '';
											document.querySelector('[data-text-cp-instance-availability-estimate]').innerHTML = response.availabilityEstimate || '';
											document.querySelector('[data-text-cp-instance-stock-quantity]').innerHTML = response.stockQuantity || '';
											document.querySelector('[data-text-cp-instance-subscription-info]').innerHTML = response.subscriptionInfo || '';
											document.querySelector('[data-text-cp-instance-price]').innerHTML = response.price || '';

											const formData = new FormData();
											formData.append('<portlet:namespace />ddmFormValues', JSON.stringify(getFormValues()));
											formData.append('groupId', themeDisplay.getScopeGroupId());

											fetch(
												'<%= String.valueOf(cpContentHelper.getViewAttachmentURL(liferayPortletRequest, liferayPortletResponse)) %>',
												{
													body: formData,
													credentials: 'include',
													method: 'post'
												}
											).then(
												function(response) {
													return response.json();
												}
											).then(
												function(response) {
													ProductGallery.selected = 0
													ProductGallery.images = response.map(
														function(image) {
															return {
																thumbnailUrl: image.url,
																url: image.url,
																title: ''
															};
														}
													);
												}
											)
										}
									);
								}

								function getFormValues() {
									return !form ? [] : form.getImmediateFields().map(
										function(field) {
											var value = field.getValue();

											return {
												key: field.get('fieldName'),
												value: value instanceof Array ? value : [value]
											};
										}
									);
								}

								const cpDefinitionId = <%= cpDefinitionId %>;
								const form = Liferay.component('<%= cpDefinitionId %>DDMForm');
								const AddToCartButton = Liferay.component('<%= addToCartId %>');
								AddToCartButton.disabled = true;
								const ProductGallery = Liferay.component('<%= galleryId %>');

								if (form) {
									form.after('*:valueChange', ddmFormChange, {});
								}
							}
						);
					});
				</script>
			</div>

			<h2 class="commerce-price" data-text-cp-instance-price>
				<c:if test="<%= cpSku != null %>">
					<commerce-ui:price
						CPInstanceId="<%= cpSku.getCPInstanceId() %>"
					/>
				</c:if>
			</h2>

			<div>
				<c:if test="<%= cpSku != null %>">
					<liferay-commerce:tier-price
						CPInstanceId="<%= cpSku.getCPInstanceId() %>"
						taglibQuantityInputId='<%= renderResponse.getNamespace() + cpDefinitionId + "Quantity" %>'
					/>
				</c:if>
			</div>

			<div class="product-detail__actions">
				<div class="autofit-col">
					<commerce-ui:add-to-cart
						CPInstanceId="<%= (cpSku == null) ? 0 : cpSku.getCPInstanceId() %>"
						id="<%= addToCartId %>"
					/>
				</div>
			</div>
		</div>
	</div>
</div>

<%
List<CPDefinitionSpecificationOptionValue> cpDefinitionSpecificationOptionValues = cpContentHelper.getCPDefinitionSpecificationOptionValues(cpDefinitionId);
List<CPOptionCategory> cpOptionCategories = cpContentHelper.getCPOptionCategories(scopeGroupId);
List<CPMedia> cpAttachmentFileEntries = cpContentHelper.getCPAttachmentFileEntries(cpDefinitionId, themeDisplay);
%>

<c:if test="<%= cpContentHelper.hasCPDefinitionSpecificationOptionValues(cpDefinitionId) %>">
	<div class="row">
		<div class="col">
			<div class="commerce-panel">
				<div class="commerce-panel__title"><%= LanguageUtil.get(resourceBundle, "specifications") %></div>
				<div class="commerce-panel__content">
					<dl class="specification-list">

						<%
						for (CPDefinitionSpecificationOptionValue cpDefinitionSpecificationOptionValue : cpDefinitionSpecificationOptionValues) {
							CPSpecificationOption cpSpecificationOption = cpDefinitionSpecificationOptionValue.getCPSpecificationOption();
						%>

							<dt class="specification-term">
								<%= HtmlUtil.escape(cpSpecificationOption.getTitle(languageId)) %>
							</dt>
							<dd class="specification-desc">
								<%= HtmlUtil.escape(cpDefinitionSpecificationOptionValue.getValue(languageId)) %>
							</dd>

						<%
						}
						%>

					</dl>

					<%
					for (CPOptionCategory cpOptionCategory : cpOptionCategories) {
						List<CPDefinitionSpecificationOptionValue> categorizedCPDefinitionSpecificationOptionValues = cpContentHelper.getCategorizedCPDefinitionSpecificationOptionValues(cpDefinitionId, cpOptionCategory.getCPOptionCategoryId());
					%>

						<c:if test="<%= !categorizedCPDefinitionSpecificationOptionValues.isEmpty() %>">
							<dl class="autofit-float autofit-row autofit-row-center specification-list">

								<%
								for (CPDefinitionSpecificationOptionValue cpDefinitionSpecificationOptionValue : categorizedCPDefinitionSpecificationOptionValues) {
									CPSpecificationOption cpSpecificationOption = cpDefinitionSpecificationOptionValue.getCPSpecificationOption();
								%>

									<dt class="specification-term">
										<%= cpSpecificationOption.getTitle(languageId) %>
									</dt>
									<dd class="specification-desc">
										<%= cpDefinitionSpecificationOptionValue.getValue(languageId) %>
									</dd>

								<%
								}
								%>

							</dl>
						</c:if>

					<%
					}
					%>

				</div>
			</div>
		</div>
	</div>
</c:if>

<c:if test="<%= !cpAttachmentFileEntries.isEmpty() %>">
	<div class="row">
		<div class="col">
			<div class="commerce-panel">
				<div class="commerce-panel__title"><%= LanguageUtil.get(resourceBundle, "attachments") %></div>
				<div class="commerce-panel__content">
					<dl class="specification-list">

						<%
						int attachmentsCount = 0;

						for (CPMedia curCPAttachmentFileEntry : cpAttachmentFileEntries) {
						%>

							<dt class="specification-term">
								<%= HtmlUtil.escape(curCPAttachmentFileEntry.getTitle()) %>
							</dt>
							<dd class="specification-desc">
								<aui:icon cssClass="icon-monospaced" image="download" markupView="lexicon" target="_blank" url="<%= curCPAttachmentFileEntry.getDownloadUrl() %>" />
							</dd>

							<%
							attachmentsCount = attachmentsCount + 1;

							if (attachmentsCount >= 2) {
							%>

								<dt class="specification-empty specification-term"></dt>
								<dd class="specification-desc specification-empty"></dd>

						<%
								attachmentsCount = 0;
							}
						}
						%>

					</dl>
				</div>
			</div>
		</div>
	</div>
</c:if>