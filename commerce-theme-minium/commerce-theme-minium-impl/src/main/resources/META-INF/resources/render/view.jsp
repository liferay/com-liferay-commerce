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

String addToCartId = PortalUtil.generateRandomKey(request, "addToCart");
String ddmFormContainerId = PortalUtil.generateRandomKey(request, "formContainer");
String galleryId = PortalUtil.generateRandomKey(request, "gallery");
%>

<script>
	window.productDetailPage_addToCartId = '<%= addToCartId %>';
	window.productDetailPage_galleryId = '<%= galleryId %>';
	window.productDetailPage_cpDefinitionId = '<%= cpDefinitionId %>';
	window.productDetailPage_ddmFormContainerId = '<%= ddmFormContainerId %>DDMForm';
	window.productDetailPage_galleryUrl = '<%= String.valueOf(cpContentHelper.getViewAttachmentURL(liferayPortletRequest, liferayPortletResponse)) %>'
</script>

<div class="product-detail" id="<portlet:namespace /><%= cpDefinitionId %>ProductContent">
	<div class="row">
		<div class="col-md-6 col-xs-12" id="minium-product-gallery">
			<commerce-ui:gallery id="<%= galleryId %>" CPDefinitionId="<%= cpDefinitionId %>" />
		</div>

		<div class="col-md-6 col-xs-12">
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
				<%= cpContentHelper.renderOptions(ddmFormContainerId, renderRequest, renderResponse) %>

				<aui:script use="liferay-portlet-url">

					function waitFor(componentId) {
						function waitForComponent(resolve, reject) {
							const component = Liferay.component(componentId);
							if (component) {
								resolve(component);
							}
							else {
								setTimeout(function() { waitForComponent(resolve, reject) }, 0);
							}
						}
						return new Promise(waitForComponent);
					}

					function responseToJson(response) {
						return response.json();
					}

					function getCpInstanceNode(name) {
						return document.querySelector('[data-text-cp-instance-' + name + ']');
					}

					function updateTextInDom(name, text) {
						getCpInstanceNode(name).innerHTML = Liferay.Util.escape(text) || '';
					}

					function updateAddToCartButton(AddToCartButton, data, formValues) {
						AddToCartButton.disabled = !data.cpInstanceExist;
						if (data.cpInstanceExist) {
							AddToCartButton.productId = data.cpInstanceId;
							AddToCartButton.options = formValues;
							AddToCartButton.quantity = 0;
							AddToCartButton.settings = {
								maxQuantity: 1000,
								minQuantity: 1,
								multipleQuantities: 1
							};
						}
					}

					function formatImagesForGallery(image) {
						return {
							thumbnailUrl: image.url,
							url: image.url,
							title: ''
						};
					}

					function setImages(gallery, images) {
						if (images.length) {
							gallery.selected = 0;
							gallery.images = images.map(formatImagesForGallery);
						}
					}

					function updateGallery(ProductGallery) {
						const formData = new FormData();
						formData.append('<portlet:namespace />ddmFormValues', getSerializedFormValues());
						formData.append('groupId', themeDisplay.getScopeGroupId());

						fetch(window.productDetailPage_galleryUrl, {
							body: formData,
							credentials: 'include',
							method: 'post'
						})
						.then(responseToJson)
						.then(setImages.bind(this, ProductGallery));
					}

					function updateTextInPage(data) {
						updateTextInDom('sku', data.sku);
						updateTextInDom('manufacturer-part-number', data.manufacturerPartNumber);
						updateTextInDom('gtin', data.gtin);
						updateTextInDom('subscription-info', data.subscriptionInfo);

						if (data.promoPrice != null) {
							updateTextInDom('price', '<span class="price__value price__value--promo-price">' + data.promoPrice + '</span>' + '<span class="price__value price__value--inactive">' + data.price + '</span>');
						}
						else if (data.price != null) {
							updateTextInDom('price', '<span class="price__value">' + data.price + '</span>');
						}
						else {
							updateTextInDom('price', data.price);
						}

						const availabilityEstimateContainer = getCpInstanceNode('availability-estimate');
						const availabilityContainer = getCpInstanceNode('availability');
						const stockQuantityContainer = getCpInstanceNode('stock-quantity');

						if (availabilityEstimateContainer && availabilityContainer && stockQuantityContainer) {
							availabilityContainer.innerHTML = data.availability || '';
							availabilityEstimateContainer.innerHTML = data.availabilityEstimate || '';
							stockQuantityContainer.innerHTML = data.stockQuantity || '';
						}
					}

					function updateCpInstance(response) {
						updateTextInPage(response);
						waitFor(window.productDetailPage_galleryId).then(updateGallery);
						waitFor(window.productDetailPage_addToCartId)
						.then(
							function(AddToCartButton) {
								updateAddToCartButton(AddToCartButton, response, getSerializedFormValues());
						});
					}

					function getCpContentUrl(portletId) {
						const portletURL = Liferay.PortletURL.createActionURL();

						portletURL.setPortletId(portletId);
						portletURL.setName('checkCPInstance');
						portletURL.setParameter('cpDefinitionId', window.productDetailPage_cpDefinitionId);
						portletURL.setParameter('groupId', themeDisplay.getScopeGroupId());
						portletURL.setParameter('p_auth', Liferay.authToken);

						return portletURL;
					}

					function checkCPInstance() {
						const portletId = 'com_liferay_commerce_product_content_web_internal_portlet_CPContentPortlet';
						const formData = new FormData();
						formData.append('_' + portletId + '_ddmFormValues', getSerializedFormValues());

						fetch(getCpContentUrl(portletId), {
							body: formData,
							credentials: 'include',
							method: 'post'
						})
						.then(responseToJson)
						.then(updateCpInstance);
					}

					function getSerializedFormValues() {
						const form = Liferay.component(window.productDetailPage_ddmFormContainerId);
						return JSON.stringify(!form ? [] : form.getImmediateFields().map(
						function(field) {
							const value = field.getValue();

							return {
								key: field.get('fieldName'),
								value: value instanceof Array ? value : [value]
							};
						}
						));
					}

					function onFormRender(event) {
						event.form.after('*:valueChange', checkCPInstance, {});
						checkCPInstance();
					}

					if (!Liferay.getEvent(window.productDetailPage_ddmFormContainerId + ':render')) {
						Liferay.on(window.productDetailPage_ddmFormContainerId + ':render', onFormRender);
					}

					if (Liferay.SPA) {
						Liferay.SPA.app.on(
							'beforeNavigate', function() {
								Liferay.destroyComponent(window.productDetailPage_ddmFormContainerId);
						});
					}

				</aui:script>
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
List<CPOptionCategory> cpOptionCategories = cpContentHelper.getCPOptionCategories(company.getCompanyId());
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

						<%
						for (CPOptionCategory cpOptionCategory : cpOptionCategories) {
							List<CPDefinitionSpecificationOptionValue> categorizedCPDefinitionSpecificationOptionValues = cpContentHelper.getCategorizedCPDefinitionSpecificationOptionValues(cpDefinitionId, cpOptionCategory.getCPOptionCategoryId());
						%>

							<c:if test="<%= !categorizedCPDefinitionSpecificationOptionValues.isEmpty() %>">

								<%
								for (CPDefinitionSpecificationOptionValue cpDefinitionSpecificationOptionValue : categorizedCPDefinitionSpecificationOptionValues) {
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

							</c:if>

						<%
						}
						%>

					</dl>
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