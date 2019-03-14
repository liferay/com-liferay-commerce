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
import com.liferay.commerce.openapi.core.util.LanguageUtils;
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
import com.liferay.headless.commerce.admin.catalog.model.v1_0.AttachmentDTO;
import com.liferay.headless.commerce.admin.catalog.model.v1_0.CategoryDTO;
import com.liferay.headless.commerce.admin.catalog.model.v1_0.OptionCategoryDTO;
import com.liferay.headless.commerce.admin.catalog.model.v1_0.ProductConfigurationDTO;
import com.liferay.headless.commerce.admin.catalog.model.v1_0.ProductDTO;
import com.liferay.headless.commerce.admin.catalog.model.v1_0.ProductOptionDTO;
import com.liferay.headless.commerce.admin.catalog.model.v1_0.ProductOptionValueDTO;
import com.liferay.headless.commerce.admin.catalog.model.v1_0.ProductShippingConfigurationDTO;
import com.liferay.headless.commerce.admin.catalog.model.v1_0.ProductSubscriptionConfigurationDTO;
import com.liferay.headless.commerce.admin.catalog.model.v1_0.ProductTaxConfigurationDTO;
import com.liferay.headless.commerce.admin.catalog.model.v1_0.SkuDTO;
import com.liferay.headless.commerce.admin.catalog.model.v1_0.SpecificationDTO;
import com.liferay.headless.commerce.admin.catalog.model.v1_0.SpecificationValueDTO;
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

	public AttachmentDTO[] modelsToAttachmentDTOArray(
		List<CPAttachmentFileEntry> cpAttachmentFileEntries) {

		if (cpAttachmentFileEntries == null) {
			return null;
		}

		List<AttachmentDTO> attachments = new ArrayList<>();

		for (CPAttachmentFileEntry cpAttachmentFileEntry :
				cpAttachmentFileEntries) {

			attachments.add(modelToDTO(cpAttachmentFileEntry));
		}

		Stream<AttachmentDTO> stream = attachments.stream();

		return stream.toArray(AttachmentDTO[]::new);
	}

	public ProductOptionDTO[] modelsToProductOptionDTOArray(
		List<CPDefinitionOptionRel> cpDefinitionOptionRels, String languageId) {

		if (cpDefinitionOptionRels == null) {
			return null;
		}

		List<ProductOptionDTO> productOptions = new ArrayList<>();

		for (CPDefinitionOptionRel cpDefinitionOptionRel :
				cpDefinitionOptionRels) {

			productOptions.add(modelToDTO(cpDefinitionOptionRel, languageId));
		}

		Stream<ProductOptionDTO> stream = productOptions.stream();

		return stream.toArray(ProductOptionDTO[]::new);
	}

	public CategoryDTO modelToDTO(AssetCategory category) {
		CategoryDTO categoryDTO = new CategoryDTO();

		if (category == null) {
			return categoryDTO;
		}

		try {
			AssetVocabulary assetVocabulary =
				_assetVocabularyLocalService.getAssetVocabulary(
					category.getVocabularyId());

			categoryDTO.setVocabulary(assetVocabulary.getName());
		}
		catch (Exception e) {
			_log.error("Cannot instantiate CategoryDTO ", e);

			throw new RuntimeException(e);
		}

		categoryDTO.setExternalReferenceCode(
			category.getExternalReferenceCode());
		categoryDTO.setGroupId(category.getGroupId());
		categoryDTO.setId(category.getCategoryId());
		categoryDTO.setName(category.getName());

		return categoryDTO;
	}

	public AttachmentDTO modelToDTO(
		CPAttachmentFileEntry cpAttachmentFileEntry) {

		AttachmentDTO attachmentDTO = new AttachmentDTO();

		if (cpAttachmentFileEntry == null) {
			return attachmentDTO;
		}

		try {
			FileEntry fileEntry = cpAttachmentFileEntry.getFileEntry();

			byte[] bytes = _file.getBytes(fileEntry.getContentStream());

			attachmentDTO.setAttachment(Base64.encode(bytes));

			attachmentDTO.setOptions(
				_getAttachmentOptions(cpAttachmentFileEntry));
			attachmentDTO.setSrc(
				_commerceMediaResolver.getDownloadUrl(
					cpAttachmentFileEntry.getCPAttachmentFileEntryId()));
		}
		catch (Exception e) {
			_log.error("Cannot instantiate AttachmentDTO ", e);

			throw new RuntimeException(e);
		}

		attachmentDTO.setDisplayDate(cpAttachmentFileEntry.getDisplayDate());
		attachmentDTO.setExpirationDate(
			cpAttachmentFileEntry.getExpirationDate());
		attachmentDTO.setExternalReferenceCode(
			cpAttachmentFileEntry.getExternalReferenceCode());
		attachmentDTO.setId(cpAttachmentFileEntry.getCPAttachmentFileEntryId());
		attachmentDTO.setPriority(cpAttachmentFileEntry.getPriority());
		attachmentDTO.setTitle(
			LanguageUtils.getLanguageIdMap(
				cpAttachmentFileEntry.getTitleMap()));
		attachmentDTO.setType(cpAttachmentFileEntry.getType());

		return attachmentDTO;
	}

	public ProductDTO modelToDTO(CPDefinition cpDefinition, String languageId) {
		ProductDTO productDTO = new ProductDTO();

		if (cpDefinition == null) {
			return productDTO;
		}

		try {
			productDTO.setAttachments(
				modelsToAttachmentDTOArray(
					cpDefinition.getCPAttachmentFileEntries(
						CPAttachmentFileEntryConstants.TYPE_OTHER,
						WorkflowConstants.STATUS_APPROVED)));
			productDTO.setImages(
				modelsToAttachmentDTOArray(
					cpDefinition.getCPAttachmentFileEntries(
						CPAttachmentFileEntryConstants.TYPE_IMAGE,
						WorkflowConstants.STATUS_APPROVED)));
		}
		catch (Exception e) {
			_log.error("Cannot instantiate ProductDTO ", e);

			throw new RuntimeException(e);
		}

		productDTO.setActive(!cpDefinition.isInactive());
		productDTO.setDescription(
			LanguageUtils.getLanguageIdMap(cpDefinition.getDescriptionMap()));
		productDTO.setExternalReferenceCode(
			cpDefinition.getExternalReferenceCode());
		productDTO.setId(cpDefinition.getCPDefinitionId());
		productDTO.setProductType(cpDefinition.getProductTypeName());
		productDTO.setShortDescription(
			LanguageUtils.getLanguageIdMap(
				cpDefinition.getShortDescriptionMap()));
		productDTO.setName(
			LanguageUtils.getLanguageIdMap(cpDefinition.getNameMap()));

		ExpandoBridge expandoBridge = cpDefinition.getExpandoBridge();

		Map<String, Serializable> attributes = expandoBridge.getAttributes();

		productDTO.setExpando(attributes);

		productDTO.setShippingConfigurationDTO(
			modelToProductShippingConfigurationDTO(cpDefinition));
		productDTO.setSubscriptionConfigurationDTO(
			modelToProductSubscriptionConfigurationDTO(cpDefinition));
		productDTO.setTaxConfigurationDTO(
			modelToProductTaxConfigurationDTO(cpDefinition, languageId));

		return productDTO;
	}

	public ProductOptionDTO modelToDTO(
		CPDefinitionOptionRel cpDefinitionOptionRel, String languageId) {

		ProductOptionDTO productOptionDTO = new ProductOptionDTO();

		if (cpDefinitionOptionRel == null) {
			return productOptionDTO;
		}

		productOptionDTO.setDescription(
			cpDefinitionOptionRel.getDescription(languageId));

		try {
			CPOption cpOption = cpDefinitionOptionRel.getCPOption();

			productOptionDTO.setExternalReferenceCode(
				cpOption.getExternalReferenceCode());
			productOptionDTO.setKey(cpOption.getKey());
		}
		catch (Exception e) {
			_log.error("Cannot instantiate ProductOptionDTO ", e);

			throw new RuntimeException(e);
		}

		productOptionDTO.setFacetable(cpDefinitionOptionRel.isFacetable());
		productOptionDTO.setFieldType(
			cpDefinitionOptionRel.getDDMFormFieldTypeName());
		productOptionDTO.setId(cpDefinitionOptionRel.getCPOptionId());
		productOptionDTO.setName(
			LanguageUtils.getLanguageIdMap(cpDefinitionOptionRel.getNameMap()));
		productOptionDTO.setRequired(cpDefinitionOptionRel.isRequired());
		productOptionDTO.setSkuContributor(
			cpDefinitionOptionRel.isSkuContributor());

		return productOptionDTO;
	}

	public SpecificationValueDTO modelToDTO(
		CPDefinitionSpecificationOptionValue
			cpDefinitionSpecificationOptionValue,
		String languageId) {

		SpecificationValueDTO specificationValueDTO =
			new SpecificationValueDTO();

		if (cpDefinitionSpecificationOptionValue == null) {
			return specificationValueDTO;
		}

		try {
			specificationValueDTO.setOptionCategoryDTO(
				modelToDTO(
					cpDefinitionSpecificationOptionValue.
						getCPOptionCategory()));
			specificationValueDTO.setProductDTO(
				modelToDTO(
					cpDefinitionSpecificationOptionValue.getCPDefinition(),
					languageId));
			specificationValueDTO.setSpecificationDTO(
				modelToDTO(
					cpDefinitionSpecificationOptionValue.
						getCPSpecificationOption()));
		}
		catch (Exception e) {
			_log.error("Cannot instantiate SpecificationDTO ", e);

			throw new RuntimeException(e);
		}

		specificationValueDTO.setId(
			cpDefinitionSpecificationOptionValue.
				getCPDefinitionSpecificationOptionValueId());
		specificationValueDTO.setPriority(
			cpDefinitionSpecificationOptionValue.getPriority());
		specificationValueDTO.setValue(
			LanguageUtils.getLanguageIdMap(
				cpDefinitionSpecificationOptionValue.getValueMap()));

		return specificationValueDTO;
	}

	public SkuDTO modelToDTO(CPInstance cpInstance) {
		SkuDTO skuDTO = new SkuDTO();

		if (cpInstance == null) {
			return skuDTO;
		}

		skuDTO.setCost(cpInstance.getCost());
		skuDTO.setDepth(cpInstance.getDepth());
		skuDTO.setDisplayDate(cpInstance.getDisplayDate());
		skuDTO.setExpirationDate(cpInstance.getExpirationDate());
		skuDTO.setExternalReferenceCode(cpInstance.getExternalReferenceCode());
		skuDTO.setGtin(cpInstance.getGtin());
		skuDTO.setHeight(cpInstance.getHeight());
		skuDTO.setId(cpInstance.getCPInstanceId());
		skuDTO.setManufacturerPartNumber(
			cpInstance.getManufacturerPartNumber());
		skuDTO.setNeverExpire(cpInstance.isExpired());
		skuDTO.setPrice(cpInstance.getPrice());
		skuDTO.setPromoPrice(cpInstance.getPromoPrice());
		skuDTO.setPublished(cpInstance.isPublished());
		skuDTO.setPurchasable(cpInstance.isPurchasable());
		skuDTO.setSku(cpInstance.getSku());
		skuDTO.setWeight(cpInstance.getWeight());
		skuDTO.setWidth(cpInstance.getWidth());

		return skuDTO;
	}

	public ProductOptionDTO modelToDTO(CPOption cpOption, String languageId) {
		ProductOptionDTO productOptionDTO = new ProductOptionDTO();

		if (cpOption == null) {
			return productOptionDTO;
		}

		productOptionDTO.setDescription(cpOption.getDescription(languageId));
		productOptionDTO.setExternalReferenceCode(
			cpOption.getExternalReferenceCode());
		productOptionDTO.setFacetable(cpOption.isFacetable());
		productOptionDTO.setFieldType(cpOption.getDDMFormFieldTypeName());
		productOptionDTO.setId(cpOption.getCPOptionId());
		productOptionDTO.setKey(cpOption.getKey());
		productOptionDTO.setName(
			LanguageUtils.getLanguageIdMap(cpOption.getNameMap()));
		productOptionDTO.setRequired(cpOption.isRequired());
		productOptionDTO.setSkuContributor(cpOption.isSkuContributor());

		return productOptionDTO;
	}

	public OptionCategoryDTO modelToDTO(CPOptionCategory cpOptionCategory) {
		OptionCategoryDTO optionCategoryDTO = new OptionCategoryDTO();

		if (cpOptionCategory == null) {
			return optionCategoryDTO;
		}

		optionCategoryDTO.setDescription(
			LanguageUtils.getLanguageIdMap(
				cpOptionCategory.getDescriptionMap()));
		optionCategoryDTO.setId(cpOptionCategory.getCPOptionCategoryId());
		optionCategoryDTO.setKey(cpOptionCategory.getKey());
		optionCategoryDTO.setPriority(cpOptionCategory.getPriority());
		optionCategoryDTO.setTitle(
			LanguageUtils.getLanguageIdMap(cpOptionCategory.getTitleMap()));

		return optionCategoryDTO;
	}

	public ProductOptionValueDTO modelToDTO(
		CPOptionValue cpOptionValue, String languageId) {

		ProductOptionValueDTO productOptionValueDTO =
			new ProductOptionValueDTO();

		if (cpOptionValue == null) {
			return productOptionValueDTO;
		}

		productOptionValueDTO.setExternalReferenceCode(
			cpOptionValue.getExternalReferenceCode());
		productOptionValueDTO.setId(cpOptionValue.getCPOptionValueId());
		productOptionValueDTO.setKey(cpOptionValue.getKey());
		productOptionValueDTO.setName(cpOptionValue.getName(languageId));
		productOptionValueDTO.setPriority(cpOptionValue.getPriority());

		return productOptionValueDTO;
	}

	public SpecificationDTO modelToDTO(
		CPSpecificationOption cpSpecificationOption) {

		SpecificationDTO specificationDTO = new SpecificationDTO();

		if (cpSpecificationOption == null) {
			return specificationDTO;
		}

		try {
			specificationDTO.setOptionCategoryDTO(
				modelToDTO(cpSpecificationOption.getCPOptionCategory()));
		}
		catch (Exception e) {
			_log.error("Cannot instantiate SpecificationDTO ", e);

			throw new RuntimeException(e);
		}

		specificationDTO.setDescription(
			LanguageUtils.getLanguageIdMap(
				cpSpecificationOption.getDescriptionMap()));
		specificationDTO.setFacetable(cpSpecificationOption.isFacetable());
		specificationDTO.setId(cpSpecificationOption.getCPOptionCategoryId());
		specificationDTO.setKey(cpSpecificationOption.getKey());
		specificationDTO.setTitle(
			LanguageUtils.getLanguageIdMap(
				cpSpecificationOption.getTitleMap()));

		return specificationDTO;
	}

	public ProductConfigurationDTO modelToProductConfigurationDTO(
		CPDefinitionInventory cpDefinitionInventory) {

		ProductConfigurationDTO productConfigurationDTO =
			new ProductConfigurationDTO();

		if (cpDefinitionInventory == null) {
			return productConfigurationDTO;
		}

		productConfigurationDTO.setAllowBackOrder(
			cpDefinitionInventory.isBackOrders());
		productConfigurationDTO.setAllowedOrderQuantities(
			ArrayUtil.toArray(
				cpDefinitionInventory.getAllowedOrderQuantitiesArray()));
		productConfigurationDTO.setInventoryEngine(
			cpDefinitionInventory.getCPDefinitionInventoryEngine());
		productConfigurationDTO.setMaxOrderQuantity(
			cpDefinitionInventory.getMaxOrderQuantity());
		productConfigurationDTO.setMinOrderQuantity(
			cpDefinitionInventory.getMinOrderQuantity());
		productConfigurationDTO.setMultipleOrderQuantity(
			cpDefinitionInventory.getMultipleOrderQuantity());

		return productConfigurationDTO;
	}

	public ProductShippingConfigurationDTO
		modelToProductShippingConfigurationDTO(CPDefinition cpDefinition) {

		ProductShippingConfigurationDTO productShippingConfigurationDTO =
			new ProductShippingConfigurationDTO();

		if (cpDefinition == null) {
			return productShippingConfigurationDTO;
		}

		productShippingConfigurationDTO.setDepth(
			BigDecimal.valueOf(cpDefinition.getDepth()));
		productShippingConfigurationDTO.setFreeShipping(
			cpDefinition.isFreeShipping());
		productShippingConfigurationDTO.setHeight(
			BigDecimal.valueOf(cpDefinition.getHeight()));
		productShippingConfigurationDTO.setShippable(
			cpDefinition.isShippable());
		productShippingConfigurationDTO.setShippingExtraPrice(
			BigDecimal.valueOf(cpDefinition.getShippingExtraPrice()));
		productShippingConfigurationDTO.setShippingSeparately(
			cpDefinition.isShipSeparately());
		productShippingConfigurationDTO.setWeight(
			BigDecimal.valueOf(cpDefinition.getWeight()));
		productShippingConfigurationDTO.setWidth(
			BigDecimal.valueOf(cpDefinition.getWidth()));

		return productShippingConfigurationDTO;
	}

	public ProductSubscriptionConfigurationDTO
		modelToProductSubscriptionConfigurationDTO(CPDefinition cpDefinition) {

		ProductSubscriptionConfigurationDTO
			productSubscriptionConfigurationDTO =
				new ProductSubscriptionConfigurationDTO();

		if (cpDefinition == null) {
			return productSubscriptionConfigurationDTO;
		}

		productSubscriptionConfigurationDTO.setEnable(
			cpDefinition.isSubscriptionEnabled());
		productSubscriptionConfigurationDTO.setLength(
			cpDefinition.getSubscriptionLength());
		productSubscriptionConfigurationDTO.setNumberOfLength(
			cpDefinition.getMaxSubscriptionCycles());
		productSubscriptionConfigurationDTO.setSubscriptionType(
			cpDefinition.getSubscriptionType());
		productSubscriptionConfigurationDTO.setSubscriptionTypeSettings(
			cpDefinition.getSubscriptionTypeSettingsProperties());

		return productSubscriptionConfigurationDTO;
	}

	public ProductTaxConfigurationDTO modelToProductTaxConfigurationDTO(
		CPDefinition cpDefinition, String languageId) {

		ProductTaxConfigurationDTO productTaxConfigurationDTO =
			new ProductTaxConfigurationDTO();

		if (cpDefinition == null) {
			return productTaxConfigurationDTO;
		}

		productTaxConfigurationDTO.setId(cpDefinition.getCPTaxCategoryId());
		productTaxConfigurationDTO.setTaxable(!cpDefinition.isTaxExempt());

		try {
			CPTaxCategory cpTaxCategory = cpDefinition.getCPTaxCategory();

			productTaxConfigurationDTO.setTaxCategory(
				cpTaxCategory.getName(languageId));
		}
		catch (Exception e) {
			_log.error("Cannot instantiate ProductTaxConfigurationDTO ", e);

			throw new RuntimeException(e);
		}

		return productTaxConfigurationDTO;
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