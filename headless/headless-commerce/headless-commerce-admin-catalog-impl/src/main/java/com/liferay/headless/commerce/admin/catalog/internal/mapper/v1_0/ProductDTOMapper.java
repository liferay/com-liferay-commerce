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

import com.liferay.commerce.model.CPDefinitionInventory;
import com.liferay.commerce.product.model.CPAttachmentFileEntryConstants;
import com.liferay.commerce.product.model.CPDefinition;
import com.liferay.commerce.product.model.CPTaxCategory;
import com.liferay.commerce.product.model.CProduct;
import com.liferay.commerce.service.CPDefinitionInventoryService;
import com.liferay.expando.kernel.model.ExpandoBridge;
import com.liferay.headless.commerce.admin.catalog.dto.v1_0.Product;
import com.liferay.headless.commerce.admin.catalog.dto.v1_0.ProductConfiguration;
import com.liferay.headless.commerce.admin.catalog.dto.v1_0.ProductShippingConfiguration;
import com.liferay.headless.commerce.admin.catalog.dto.v1_0.ProductSubscriptionConfiguration;
import com.liferay.headless.commerce.admin.catalog.dto.v1_0.ProductTaxConfiguration;
import com.liferay.headless.commerce.core.util.LanguageUtils;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.ArrayUtil;
import com.liferay.portal.kernel.workflow.WorkflowConstants;

import java.io.Serializable;

import java.math.BigDecimal;

import java.util.Map;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Alessio Antonio Rendina
 */
@Component(immediate = true, service = ProductDTOMapper.class)
public class ProductDTOMapper {

	public Product toProduct(CPDefinition cpDefinition, String languageId) {
		Product product = new Product();

		if (cpDefinition == null) {
			return product;
		}

		try {
			product.setAttachments(
				_attachmentDTOMapper.toAttachments(
					cpDefinition.getCPAttachmentFileEntries(
						CPAttachmentFileEntryConstants.TYPE_OTHER,
						WorkflowConstants.STATUS_APPROVED)));
			product.setImages(
				_attachmentDTOMapper.toAttachments(
					cpDefinition.getCPAttachmentFileEntries(
						CPAttachmentFileEntryConstants.TYPE_IMAGE,
						WorkflowConstants.STATUS_APPROVED)));

			CPDefinitionInventory cpDefinitionInventory =
				_cpDefinitionInventoryService.
					fetchCPDefinitionInventoryByCPDefinitionId(
						cpDefinition.getCPDefinitionId());

			product.setConfiguration(
				toProductConfiguration(cpDefinitionInventory));

			CProduct cProduct = cpDefinition.getCProduct();

			product.setExternalReferenceCode(
				cProduct.getExternalReferenceCode());
		}
		catch (Exception e) {
			_log.error("Cannot instantiate Product ", e);

			throw new RuntimeException(e);
		}

		product.setActive(!cpDefinition.isInactive());
		product.setDescription(
			LanguageUtils.getLanguageIdMap(cpDefinition.getDescriptionMap()));
		product.setId(cpDefinition.getCPDefinitionId());
		product.setProductType(cpDefinition.getProductTypeName());
		product.setShortDescription(
			LanguageUtils.getLanguageIdMap(
				cpDefinition.getShortDescriptionMap()));
		product.setName(
			LanguageUtils.getLanguageIdMap(cpDefinition.getNameMap()));
		product.setOptions(
			_productOptionDTOMapper.toProductOptions(cpDefinition));

		ExpandoBridge expandoBridge = cpDefinition.getExpandoBridge();

		Map<String, Serializable> attributes = expandoBridge.getAttributes();

		product.setExpando(attributes);

		product.setRelatedProducts(
			_relatedProductDTOMapper.toRelatedProducts(
				cpDefinition, languageId));

		product.setShippingConfiguration(
			toProductShippingConfiguration(cpDefinition));
		product.setSubscriptionConfiguration(
			toProductSubscriptionConfiguration(cpDefinition));
		product.setTaxConfiguration(
			toProductTaxConfiguration(cpDefinition, languageId));

		return product;
	}

	public ProductConfiguration toProductConfiguration(
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

	public ProductShippingConfiguration toProductShippingConfiguration(
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

	public ProductSubscriptionConfiguration toProductSubscriptionConfiguration(
		CPDefinition cpDefinition) {

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

	public ProductTaxConfiguration toProductTaxConfiguration(
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

	private static final Log _log = LogFactoryUtil.getLog(
		ProductDTOMapper.class);

	@Reference
	private AttachmentDTOMapper _attachmentDTOMapper;

	@Reference
	private CPDefinitionInventoryService _cpDefinitionInventoryService;

	@Reference
	private ProductOptionDTOMapper _productOptionDTOMapper;

	@Reference
	private RelatedProductDTOMapper _relatedProductDTOMapper;

}