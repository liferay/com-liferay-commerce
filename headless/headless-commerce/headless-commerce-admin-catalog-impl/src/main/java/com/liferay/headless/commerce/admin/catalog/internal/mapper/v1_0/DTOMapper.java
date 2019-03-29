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

package com.liferay.headless.commerce.admin.catalog.internal.mapper.v1_0;

import com.liferay.asset.kernel.model.AssetCategory;
import com.liferay.asset.kernel.model.AssetVocabulary;
import com.liferay.asset.kernel.service.AssetVocabularyLocalService;
import com.liferay.commerce.media.CommerceMediaResolver;
import com.liferay.commerce.model.CPDefinitionInventory;
import com.liferay.commerce.product.model.CPAttachmentFileEntry;
import com.liferay.commerce.product.model.CPAttachmentFileEntryConstants;
import com.liferay.commerce.product.model.CPDefinition;
import com.liferay.commerce.product.model.CPDefinitionOptionRel;
import com.liferay.commerce.product.model.CPDefinitionSpecificationOptionValue;
import com.liferay.commerce.product.model.CPInstance;
import com.liferay.commerce.product.model.CPOption;
import com.liferay.commerce.product.model.CPOptionCategory;
import com.liferay.commerce.product.model.CPOptionValue;
import com.liferay.commerce.product.model.CPSpecificationOption;
import com.liferay.commerce.product.model.CPTaxCategory;
import com.liferay.expando.kernel.model.ExpandoBridge;
import com.liferay.headless.commerce.admin.catalog.dto.v1_0.Attachment;
import com.liferay.headless.commerce.admin.catalog.dto.v1_0.Category;
import com.liferay.headless.commerce.admin.catalog.dto.v1_0.OptionCategory;
import com.liferay.headless.commerce.admin.catalog.dto.v1_0.Product;
import com.liferay.headless.commerce.admin.catalog.dto.v1_0.ProductConfiguration;
import com.liferay.headless.commerce.admin.catalog.dto.v1_0.ProductOption;
import com.liferay.headless.commerce.admin.catalog.dto.v1_0.ProductOptionValue;
import com.liferay.headless.commerce.admin.catalog.dto.v1_0.ProductShippingConfiguration;
import com.liferay.headless.commerce.admin.catalog.dto.v1_0.ProductSubscriptionConfiguration;
import com.liferay.headless.commerce.admin.catalog.dto.v1_0.ProductTaxConfiguration;
import com.liferay.headless.commerce.admin.catalog.dto.v1_0.Sku;
import com.liferay.headless.commerce.admin.catalog.dto.v1_0.Specification;
import com.liferay.headless.commerce.admin.catalog.dto.v1_0.SpecificationValue;
import com.liferay.headless.commerce.core.util.LanguageUtils;
import com.liferay.portal.kernel.json.JSONException;
import com.liferay.portal.kernel.json.JSONFactory;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.repository.model.FileEntry;
import com.liferay.portal.kernel.util.ArrayUtil;
import com.liferay.portal.kernel.util.Base64;
import com.liferay.portal.kernel.util.File;
import com.liferay.portal.kernel.workflow.WorkflowConstants;

import java.io.Serializable;

import java.math.BigDecimal;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Alessio Antonio Rendina
 */
@Component(immediate = true, service = DTOMapper.class)
public class DTOMapper {

	public Attachment[] modelsToAttachmentDTOArray(
		List<CPAttachmentFileEntry> cpAttachmentFileEntries) {

		if (cpAttachmentFileEntries == null) {
			return null;
		}

		List<Attachment> attachments = new ArrayList<>();

		for (CPAttachmentFileEntry cpAttachmentFileEntry :
				cpAttachmentFileEntries) {

			attachments.add(modelToDTO(cpAttachmentFileEntry));
		}

		Stream<Attachment> stream = attachments.stream();

		return stream.toArray(Attachment[]::new);
	}

	public ProductOption[] modelsToProductOptionDTOArray(
		List<CPDefinitionOptionRel> cpDefinitionOptionRels, String languageId) {

		if (cpDefinitionOptionRels == null) {
			return null;
		}

		List<ProductOption> productOptions = new ArrayList<>();

		for (CPDefinitionOptionRel cpDefinitionOptionRel :
				cpDefinitionOptionRels) {

			productOptions.add(modelToDTO(cpDefinitionOptionRel, languageId));
		}

		Stream<ProductOption> stream = productOptions.stream();

		return stream.toArray(ProductOption[]::new);
	}

	public Category modelToDTO(AssetCategory assetCategory) {
		Category category = new Category();

		if (assetCategory == null) {
			return category;
		}

		try {
			AssetVocabulary assetVocabulary =
				_assetVocabularyLocalService.getAssetVocabulary(
					assetCategory.getVocabularyId());

			category.setVocabulary(assetVocabulary.getName());
		}
		catch (Exception e) {
			_log.error("Cannot instantiate CategoryDTO ", e);

			throw new RuntimeException(e);
		}

		category.setExternalReferenceCode(
			assetCategory.getExternalReferenceCode());
		category.setGroupId(assetCategory.getGroupId());
		category.setId(assetCategory.getCategoryId());
		category.setName(assetCategory.getName());

		return category;
	}

	public Attachment modelToDTO(CPAttachmentFileEntry cpAttachmentFileEntry) {
		Attachment attachment = new Attachment();

		if (cpAttachmentFileEntry == null) {
			return attachment;
		}

		try {
			FileEntry fileEntry = cpAttachmentFileEntry.getFileEntry();

			byte[] bytes = _file.getBytes(fileEntry.getContentStream());

			attachment.setAttachment(Base64.encode(bytes));

			attachment.setOptions(_getAttachmentOptions(cpAttachmentFileEntry));
			attachment.setSrc(
				_commerceMediaResolver.getDownloadUrl(
					cpAttachmentFileEntry.getCPAttachmentFileEntryId()));
		}
		catch (Exception e) {
			_log.error("Cannot instantiate Attachment ", e);

			throw new RuntimeException(e);
		}

		attachment.setDisplayDate(cpAttachmentFileEntry.getDisplayDate());
		attachment.setExpirationDate(cpAttachmentFileEntry.getExpirationDate());
		attachment.setExternalReferenceCode(
			cpAttachmentFileEntry.getExternalReferenceCode());
		attachment.setId(cpAttachmentFileEntry.getCPAttachmentFileEntryId());
		attachment.setPriority(cpAttachmentFileEntry.getPriority());
		attachment.setTitle(
			LanguageUtils.getLanguageIdMap(
				cpAttachmentFileEntry.getTitleMap()));
		attachment.setType(cpAttachmentFileEntry.getType());

		return attachment;
	}

	public Product modelToDTO(CPDefinition cpDefinition, String languageId) {
		Product product = new Product();

		if (cpDefinition == null) {
			return product;
		}

		try {
			product.setAttachments(
				modelsToAttachmentDTOArray(
					cpDefinition.getCPAttachmentFileEntries(
						CPAttachmentFileEntryConstants.TYPE_OTHER,
						WorkflowConstants.STATUS_APPROVED)));
			product.setImages(
				modelsToAttachmentDTOArray(
					cpDefinition.getCPAttachmentFileEntries(
						CPAttachmentFileEntryConstants.TYPE_IMAGE,
						WorkflowConstants.STATUS_APPROVED)));
		}
		catch (Exception e) {
			_log.error("Cannot instantiate Product ", e);

			throw new RuntimeException(e);
		}

		product.setActive(!cpDefinition.isInactive());
		product.setDescription(
			LanguageUtils.getLanguageIdMap(cpDefinition.getDescriptionMap()));
		product.setExternalReferenceCode(
			cpDefinition.getExternalReferenceCode());
		product.setId(cpDefinition.getCPDefinitionId());
		product.setProductType(cpDefinition.getProductTypeName());
		product.setShortDescription(
			LanguageUtils.getLanguageIdMap(
				cpDefinition.getShortDescriptionMap()));
		product.setName(
			LanguageUtils.getLanguageIdMap(cpDefinition.getNameMap()));

		ExpandoBridge expandoBridge = cpDefinition.getExpandoBridge();

		Map<String, Serializable> attributes = expandoBridge.getAttributes();

		product.setExpando(attributes);

		product.setShippingConfiguration(
			modelToProductShippingConfigurationDTO(cpDefinition));
		product.setSubscriptionConfiguration(
			modelToProductSubscriptionConfigurationDTO(cpDefinition));
		product.setTaxConfiguration(
			modelToProductTaxConfigurationDTO(cpDefinition, languageId));

		return product;
	}

	public ProductOption modelToDTO(
		CPDefinitionOptionRel cpDefinitionOptionRel, String languageId) {

		ProductOption productOption = new ProductOption();

		if (cpDefinitionOptionRel == null) {
			return productOption;
		}

		productOption.setDescription(
			cpDefinitionOptionRel.getDescription(languageId));

		try {
			CPOption cpOption = cpDefinitionOptionRel.getCPOption();

			productOption.setExternalReferenceCode(
				cpOption.getExternalReferenceCode());
			productOption.setKey(cpOption.getKey());
		}
		catch (Exception e) {
			_log.error("Cannot instantiate ProductOption ", e);

			throw new RuntimeException(e);
		}

		productOption.setFacetable(cpDefinitionOptionRel.isFacetable());
		productOption.setFieldType(
			ProductOption.FieldType.create(
				cpDefinitionOptionRel.getDDMFormFieldTypeName()));
		productOption.setId(cpDefinitionOptionRel.getCPOptionId());
		productOption.setName(
			LanguageUtils.getLanguageIdMap(cpDefinitionOptionRel.getNameMap()));
		productOption.setRequired(cpDefinitionOptionRel.isRequired());
		productOption.setSkuContributor(
			cpDefinitionOptionRel.isSkuContributor());

		return productOption;
	}

	public SpecificationValue modelToDTO(
		CPDefinitionSpecificationOptionValue
			cpDefinitionSpecificationOptionValue,
		String languageId) {

		SpecificationValue specificationValue = new SpecificationValue();

		if (cpDefinitionSpecificationOptionValue == null) {
			return specificationValue;
		}

		try {
			specificationValue.setOptionCategory(
				modelToDTO(
					cpDefinitionSpecificationOptionValue.
						getCPOptionCategory()));
			specificationValue.setProduct(
				modelToDTO(
					cpDefinitionSpecificationOptionValue.getCPDefinition(),
					languageId));
			specificationValue.setSpecification(
				modelToDTO(
					cpDefinitionSpecificationOptionValue.
						getCPSpecificationOption()));
		}
		catch (Exception e) {
			_log.error("Cannot instantiate Specification ", e);

			throw new RuntimeException(e);
		}

		specificationValue.setId(
			cpDefinitionSpecificationOptionValue.
				getCPDefinitionSpecificationOptionValueId());
		specificationValue.setPriority(
			cpDefinitionSpecificationOptionValue.getPriority());
		specificationValue.setValue(
			LanguageUtils.getLanguageIdMap(
				cpDefinitionSpecificationOptionValue.getValueMap()));

		return specificationValue;
	}

	public Sku modelToDTO(CPInstance cpInstance) {
		Sku sku = new Sku();

		if (cpInstance == null) {
			return sku;
		}

		sku.setCost(cpInstance.getCost());
		sku.setDepth(cpInstance.getDepth());
		sku.setDisplayDate(cpInstance.getDisplayDate());
		sku.setExpirationDate(cpInstance.getExpirationDate());
		sku.setExternalReferenceCode(cpInstance.getExternalReferenceCode());
		sku.setGtin(cpInstance.getGtin());
		sku.setHeight(cpInstance.getHeight());
		sku.setId(cpInstance.getCPInstanceId());
		sku.setManufacturerPartNumber(cpInstance.getManufacturerPartNumber());
		sku.setNeverExpire(cpInstance.isExpired());
		sku.setPrice(cpInstance.getPrice());
		sku.setPromoPrice(cpInstance.getPromoPrice());
		sku.setPublished(cpInstance.isPublished());
		sku.setPurchasable(cpInstance.isPurchasable());
		sku.setSku(cpInstance.getSku());
		sku.setWeight(cpInstance.getWeight());
		sku.setWidth(cpInstance.getWidth());

		return sku;
	}

	public ProductOption modelToDTO(CPOption cpOption, String languageId) {
		ProductOption productOption = new ProductOption();

		if (cpOption == null) {
			return productOption;
		}

		productOption.setDescription(cpOption.getDescription(languageId));
		productOption.setExternalReferenceCode(
			cpOption.getExternalReferenceCode());
		productOption.setFacetable(cpOption.isFacetable());
		productOption.setFieldType(
			ProductOption.FieldType.create(cpOption.getDDMFormFieldTypeName()));
		productOption.setId(cpOption.getCPOptionId());
		productOption.setKey(cpOption.getKey());
		productOption.setName(
			LanguageUtils.getLanguageIdMap(cpOption.getNameMap()));
		productOption.setRequired(cpOption.isRequired());
		productOption.setSkuContributor(cpOption.isSkuContributor());

		return productOption;
	}

	public OptionCategory modelToDTO(CPOptionCategory cpOptionCategory) {
		OptionCategory optionCategory = new OptionCategory();

		if (cpOptionCategory == null) {
			return optionCategory;
		}

		optionCategory.setDescription(
			LanguageUtils.getLanguageIdMap(
				cpOptionCategory.getDescriptionMap()));
		optionCategory.setId(cpOptionCategory.getCPOptionCategoryId());
		optionCategory.setKey(cpOptionCategory.getKey());
		optionCategory.setPriority(cpOptionCategory.getPriority());
		optionCategory.setTitle(
			LanguageUtils.getLanguageIdMap(cpOptionCategory.getTitleMap()));

		return optionCategory;
	}

	public ProductOptionValue modelToDTO(
		CPOptionValue cpOptionValue, String languageId) {

		ProductOptionValue productOptionValue = new ProductOptionValue();

		if (cpOptionValue == null) {
			return productOptionValue;
		}

		productOptionValue.setExternalReferenceCode(
			cpOptionValue.getExternalReferenceCode());
		productOptionValue.setId(cpOptionValue.getCPOptionValueId());
		productOptionValue.setKey(cpOptionValue.getKey());
		productOptionValue.setName(cpOptionValue.getName(languageId));
		productOptionValue.setPriority(cpOptionValue.getPriority());

		return productOptionValue;
	}

	public Specification modelToDTO(
		CPSpecificationOption cpSpecificationOption) {

		Specification specification = new Specification();

		if (cpSpecificationOption == null) {
			return specification;
		}

		try {
			specification.setOptionCategory(
				modelToDTO(cpSpecificationOption.getCPOptionCategory()));
		}
		catch (Exception e) {
			_log.error("Cannot instantiate Specification ", e);

			throw new RuntimeException(e);
		}

		specification.setDescription(
			LanguageUtils.getLanguageIdMap(
				cpSpecificationOption.getDescriptionMap()));
		specification.setFacetable(cpSpecificationOption.isFacetable());
		specification.setId(cpSpecificationOption.getCPOptionCategoryId());
		specification.setKey(cpSpecificationOption.getKey());
		specification.setTitle(
			LanguageUtils.getLanguageIdMap(
				cpSpecificationOption.getTitleMap()));

		return specification;
	}

	public ProductConfiguration modelToProductConfigurationDTO(
		CPDefinitionInventory cpDefinitionInventory) {

		ProductConfiguration productConfiguration = new ProductConfiguration();

		if (cpDefinitionInventory == null) {
			return productConfiguration;
		}

		productConfiguration.setAllowBackOrder(
			cpDefinitionInventory.isBackOrders());
		productConfiguration.setAllowedOrderQuantities(
			ArrayUtil.toArray(
				cpDefinitionInventory.getAllowedOrderQuantitiesArray()));
		productConfiguration.setInventoryEngine(
			cpDefinitionInventory.getCPDefinitionInventoryEngine());
		productConfiguration.setMaxOrderQuantity(
			cpDefinitionInventory.getMaxOrderQuantity());
		productConfiguration.setMinOrderQuantity(
			cpDefinitionInventory.getMinOrderQuantity());
		productConfiguration.setMultipleOrderQuantity(
			cpDefinitionInventory.getMultipleOrderQuantity());

		return productConfiguration;
	}

	public ProductShippingConfiguration modelToProductShippingConfigurationDTO(
		CPDefinition cpDefinition) {

		ProductShippingConfiguration productShippingConfiguration =
			new ProductShippingConfiguration();

		if (cpDefinition == null) {
			return productShippingConfiguration;
		}

		productShippingConfiguration.setDepth(
			BigDecimal.valueOf(cpDefinition.getDepth()));
		productShippingConfiguration.setFreeShipping(
			cpDefinition.isFreeShipping());
		productShippingConfiguration.setHeight(
			BigDecimal.valueOf(cpDefinition.getHeight()));
		productShippingConfiguration.setShippable(cpDefinition.isShippable());
		productShippingConfiguration.setShippingExtraPrice(
			BigDecimal.valueOf(cpDefinition.getShippingExtraPrice()));
		productShippingConfiguration.setShippingSeparately(
			cpDefinition.isShipSeparately());
		productShippingConfiguration.setWeight(
			BigDecimal.valueOf(cpDefinition.getWeight()));
		productShippingConfiguration.setWidth(
			BigDecimal.valueOf(cpDefinition.getWidth()));

		return productShippingConfiguration;
	}

	public ProductSubscriptionConfiguration
		modelToProductSubscriptionConfigurationDTO(CPDefinition cpDefinition) {

		ProductSubscriptionConfiguration productSubscriptionConfiguration =
			new ProductSubscriptionConfiguration();

		if (cpDefinition == null) {
			return productSubscriptionConfiguration;
		}

		productSubscriptionConfiguration.setEnable(
			cpDefinition.isSubscriptionEnabled());
		productSubscriptionConfiguration.setLength(
			cpDefinition.getSubscriptionLength());
		productSubscriptionConfiguration.setNumberOfLength(
			cpDefinition.getMaxSubscriptionCycles());
		productSubscriptionConfiguration.setSubscriptionType(
			ProductSubscriptionConfiguration.SubscriptionType.create(
				cpDefinition.getSubscriptionType()));
		productSubscriptionConfiguration.setSubscriptionTypeSettings(
			cpDefinition.getSubscriptionTypeSettingsProperties());

		return productSubscriptionConfiguration;
	}

	public ProductTaxConfiguration modelToProductTaxConfigurationDTO(
		CPDefinition cpDefinition, String languageId) {

		ProductTaxConfiguration productTaxConfiguration =
			new ProductTaxConfiguration();

		if (cpDefinition == null) {
			return productTaxConfiguration;
		}

		productTaxConfiguration.setId(cpDefinition.getCPTaxCategoryId());
		productTaxConfiguration.setTaxable(!cpDefinition.isTaxExempt());

		try {
			CPTaxCategory cpTaxCategory = cpDefinition.getCPTaxCategory();

			productTaxConfiguration.setTaxCategory(
				cpTaxCategory.getName(languageId));
		}
		catch (Exception e) {
			_log.error("Cannot instantiate ProductTaxConfiguration ", e);

			throw new RuntimeException(e);
		}

		return productTaxConfiguration;
	}

	private Map<String, String> _getAttachmentOptions(
			CPAttachmentFileEntry cpAttachmentFileEntry)
		throws JSONException {

		Map<String, String> options = new HashMap<>();

		JSONObject jsonObject = _jsonFactory.createJSONObject(
			cpAttachmentFileEntry.getJson());

		Iterator<String> keys = jsonObject.keys();

		while (keys.hasNext()) {
			String key = keys.next();

			options.put(key, jsonObject.getString(key));
		}

		return options;
	}

	private static final Log _log = LogFactoryUtil.getLog(DTOMapper.class);

	@Reference
	private AssetVocabularyLocalService _assetVocabularyLocalService;

	@Reference
	private CommerceMediaResolver _commerceMediaResolver;

	@Reference
	private File _file;

	@Reference
	private JSONFactory _jsonFactory;

}