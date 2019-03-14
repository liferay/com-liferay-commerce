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

package com.liferay.headless.commerce.admin.catalog.model.v1_0;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;

import com.liferay.commerce.openapi.core.annotation.Nullable;

import java.util.Map;

import javax.annotation.Generated;

/**
 * @author Alessio Antonio Rendina
 */
@Generated(value = "OSGiRESTModuleGenerator")
@JacksonXmlRootElement(localName = "Product")
public class ProductDTO {

	@Nullable
	public AttachmentDTO[] getAttachments() {
		return _attachments;
	}

	@Nullable
	public CategoryDTO[] getCategories() {
		return _categories;
	}

	@Nullable
	public ProductConfigurationDTO getConfigurationDTO() {
		return _configurationDTO;
	}

	@Nullable
	public String getDefaultSku() {
		return _defaultSku;
	}

	@Nullable
	public Map<String, String> getDescription() {
		return _description;
	}

	@Nullable
	public Map<String, ?> getExpando() {
		return _expando;
	}

	public String getExternalReferenceCode() {
		return _externalReferenceCode;
	}

	@Nullable
	public Long getGroupId() {
		return _groupId;
	}

	@Nullable
	public Long getId() {
		return _id;
	}

	@Nullable
	public AttachmentDTO[] getImages() {
		return _images;
	}

	@Nullable
	public Map<String, String> getMetaDescription() {
		return _metaDescription;
	}

	@Nullable
	public Map<String, String> getMetaKeyword() {
		return _metaKeyword;
	}

	@Nullable
	public Map<String, String> getMetaTitle() {
		return _metaTitle;
	}

	public Map<String, String> getName() {
		return _name;
	}

	@Nullable
	public ProductOptionDTO[] getOptions() {
		return _options;
	}

	@Nullable
	public String getProductType() {
		return _productType;
	}

	@Nullable
	public Map<String, String> getRelatedProducts() {
		return _relatedProducts;
	}

	@Nullable
	public ProductShippingConfigurationDTO getShippingConfigurationDTO() {
		return _shippingConfigurationDTO;
	}

	@Nullable
	public Map<String, String> getShortDescription() {
		return _shortDescription;
	}

	@Nullable
	public SkuDTO[] getSkus() {
		return _skus;
	}

	@Nullable
	public ProductSubscriptionConfigurationDTO
		getSubscriptionConfigurationDTO() {

		return _subscriptionConfigurationDTO;
	}

	@Nullable
	public String[] getTags() {
		return _tags;
	}

	@Nullable
	public ProductTaxConfigurationDTO getTaxConfigurationDTO() {
		return _taxConfigurationDTO;
	}

	@Nullable
	public Map<String, String> getUrls() {
		return _urls;
	}

	public Boolean isActive() {
		return _active;
	}

	public void setActive(Boolean active) {
		_active = active;
	}

	public void setAttachments(AttachmentDTO[] attachments) {
		_attachments = attachments;
	}

	public void setCategories(CategoryDTO[] categories) {
		_categories = categories;
	}

	public void setConfigurationDTO(ProductConfigurationDTO configurationDTO) {
		_configurationDTO = configurationDTO;
	}

	public void setDefaultSku(String defaultSku) {
		_defaultSku = defaultSku;
	}

	public void setDescription(Map<String, String> description) {
		_description = description;
	}

	public void setExpando(Map<String, ?> expando) {
		_expando = expando;
	}

	public void setExternalReferenceCode(String externalReferenceCode) {
		_externalReferenceCode = externalReferenceCode;
	}

	public void setGroupId(Long groupId) {
		_groupId = groupId;
	}

	public void setId(Long id) {
		_id = id;
	}

	public void setImages(AttachmentDTO[] images) {
		_images = images;
	}

	public void setMetaDescription(Map<String, String> metaDescription) {
		_metaDescription = metaDescription;
	}

	public void setMetaKeyword(Map<String, String> metaKeyword) {
		_metaKeyword = metaKeyword;
	}

	public void setMetaTitle(Map<String, String> metaTitle) {
		_metaTitle = metaTitle;
	}

	public void setName(Map<String, String> name) {
		_name = name;
	}

	public void setOptions(ProductOptionDTO[] options) {
		_options = options;
	}

	public void setProductType(String productType) {
		_productType = productType;
	}

	public void setRelatedProducts(Map<String, String> relatedProducts) {
		_relatedProducts = relatedProducts;
	}

	public void setShippingConfigurationDTO(
		ProductShippingConfigurationDTO shippingConfigurationDTO) {

		_shippingConfigurationDTO = shippingConfigurationDTO;
	}

	public void setShortDescription(Map<String, String> shortDescription) {
		_shortDescription = shortDescription;
	}

	public void setSkus(SkuDTO[] skus) {
		_skus = skus;
	}

	public void setSubscriptionConfigurationDTO(
		ProductSubscriptionConfigurationDTO subscriptionConfigurationDTO) {

		_subscriptionConfigurationDTO = subscriptionConfigurationDTO;
	}

	public void setTags(String[] tags) {
		_tags = tags;
	}

	public void setTaxConfigurationDTO(
		ProductTaxConfigurationDTO taxConfigurationDTO) {

		_taxConfigurationDTO = taxConfigurationDTO;
	}

	public void setUrls(Map<String, String> urls) {
		_urls = urls;
	}

	private Boolean _active;

	@Nullable
	private AttachmentDTO[] _attachments;

	@Nullable
	private CategoryDTO[] _categories;

	@Nullable
	private ProductConfigurationDTO _configurationDTO;

	@Nullable
	private String _defaultSku;

	@Nullable
	private Map<String, String> _description;

	@Nullable
	private Map<String, ?> _expando;

	private String _externalReferenceCode;

	@Nullable
	private Long _groupId;

	@Nullable
	private Long _id;

	@Nullable
	private AttachmentDTO[] _images;

	@Nullable
	private Map<String, String> _metaDescription;

	@Nullable
	private Map<String, String> _metaKeyword;

	@Nullable
	private Map<String, String> _metaTitle;

	private Map<String, String> _name;

	@Nullable
	private ProductOptionDTO[] _options;

	@Nullable
	private String _productType;

	@Nullable
	private Map<String, String> _relatedProducts;

	@Nullable
	private ProductShippingConfigurationDTO _shippingConfigurationDTO;

	@Nullable
	private Map<String, String> _shortDescription;

	@Nullable
	private SkuDTO[] _skus;

	@Nullable
	private ProductSubscriptionConfigurationDTO _subscriptionConfigurationDTO;

	@Nullable
	private String[] _tags;

	@Nullable
	private ProductTaxConfigurationDTO _taxConfigurationDTO;

	@Nullable
	private Map<String, String> _urls;

}