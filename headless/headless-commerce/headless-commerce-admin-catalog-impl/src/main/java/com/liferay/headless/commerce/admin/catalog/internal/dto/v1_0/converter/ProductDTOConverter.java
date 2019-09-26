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

package com.liferay.headless.commerce.admin.catalog.internal.dto.v1_0.converter;

import com.liferay.asset.kernel.model.AssetCategory;
import com.liferay.asset.kernel.model.AssetTag;
import com.liferay.asset.kernel.service.AssetCategoryService;
import com.liferay.asset.kernel.service.AssetTagService;
import com.liferay.commerce.model.CPDefinitionInventory;
import com.liferay.commerce.product.model.CPAttachmentFileEntry;
import com.liferay.commerce.product.model.CPAttachmentFileEntryConstants;
import com.liferay.commerce.product.model.CPDefinition;
import com.liferay.commerce.product.model.CPDefinitionLink;
import com.liferay.commerce.product.model.CPDefinitionOptionRel;
import com.liferay.commerce.product.model.CPDefinitionSpecificationOptionValue;
import com.liferay.commerce.product.model.CPInstance;
import com.liferay.commerce.product.model.CProduct;
import com.liferay.commerce.product.model.CommerceCatalog;
import com.liferay.commerce.product.service.CPDefinitionLinkService;
import com.liferay.commerce.product.service.CPDefinitionService;
import com.liferay.expando.kernel.model.ExpandoBridge;
import com.liferay.headless.commerce.admin.catalog.dto.v1_0.Attachment;
import com.liferay.headless.commerce.admin.catalog.dto.v1_0.Category;
import com.liferay.headless.commerce.admin.catalog.dto.v1_0.Product;
import com.liferay.headless.commerce.admin.catalog.dto.v1_0.ProductConfiguration;
import com.liferay.headless.commerce.admin.catalog.dto.v1_0.ProductOption;
import com.liferay.headless.commerce.admin.catalog.dto.v1_0.ProductShippingConfiguration;
import com.liferay.headless.commerce.admin.catalog.dto.v1_0.ProductSpecification;
import com.liferay.headless.commerce.admin.catalog.dto.v1_0.ProductSubscriptionConfiguration;
import com.liferay.headless.commerce.admin.catalog.dto.v1_0.ProductTaxConfiguration;
import com.liferay.headless.commerce.admin.catalog.dto.v1_0.RelatedProduct;
import com.liferay.headless.commerce.admin.catalog.dto.v1_0.Sku;
import com.liferay.headless.commerce.core.dto.v1_0.converter.DTOConverter;
import com.liferay.headless.commerce.core.dto.v1_0.converter.DTOConverterContext;
import com.liferay.headless.commerce.core.dto.v1_0.converter.DTOConverterRegistry;
import com.liferay.headless.commerce.core.dto.v1_0.converter.DefaultDTOConverterContext;
import com.liferay.headless.commerce.core.util.LanguageUtils;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.workflow.WorkflowConstants;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Alessio Antonio Rendina
 */
@Component(
	property = "model.class.name=com.liferay.commerce.product.model.CPDefinition",
	service = {DTOConverter.class, ProductDTOConverter.class}
)
public class ProductDTOConverter implements DTOConverter {

	@Override
	public String getContentType() {
		return Product.class.getSimpleName();
	}

	public Product toDTO(DTOConverterContext dtoConverterContext)
		throws Exception {

		CPDefinition cpDefinition = _cpDefinitionService.getCPDefinition(
			dtoConverterContext.getResourcePrimKey());

		CProduct cProduct = cpDefinition.getCProduct();

		ExpandoBridge expandoBridge = cpDefinition.getExpandoBridge();

		DTOConverter productConfigurationDTOConverter =
			_dtoConverterRegistry.getDTOConverter(
				CPDefinitionInventory.class.getName());
		DTOConverter productShippingConfigurationDTOConverter =
			_dtoConverterRegistry.getDTOConverter(
				"ProductShippingConfiguration");
		DTOConverter productSubscriptionConfigurationDTOConverter =
			_dtoConverterRegistry.getDTOConverter(
				"ProductSubscriptionConfiguration");
		DTOConverter productTaxConfigurationDTOConverter =
			_dtoConverterRegistry.getDTOConverter("ProductTaxConfiguration");

		return new Product() {
			{
				active = !cpDefinition.isInactive();
				attachments = _getAttachments(
					cpDefinition, CPAttachmentFileEntryConstants.TYPE_OTHER,
					dtoConverterContext);
				catalogId = _getCommerceCatalogId(cpDefinition);
				categories = _getCategories(cpDefinition, dtoConverterContext);
				configuration =
					(ProductConfiguration)
						productConfigurationDTOConverter.toDTO(
							dtoConverterContext);
				createDate = cpDefinition.getCreateDate();
				description = LanguageUtils.getLanguageIdMap(
					cpDefinition.getDescriptionMap());
				displayDate = cpDefinition.getDisplayDate();
				expando = expandoBridge.getAttributes();
				expirationDate = cpDefinition.getExpirationDate();
				externalReferenceCode = cProduct.getExternalReferenceCode();
				id = cpDefinition.getCPDefinitionId();
				images = _getAttachments(
					cpDefinition, CPAttachmentFileEntryConstants.TYPE_IMAGE,
					dtoConverterContext);
				metaDescription = LanguageUtils.getLanguageIdMap(
					cpDefinition.getMetaDescriptionMap());
				metaKeyword = LanguageUtils.getLanguageIdMap(
					cpDefinition.getMetaKeywordsMap());
				metaTitle = LanguageUtils.getLanguageIdMap(
					cpDefinition.getMetaTitleMap());
				modifiedDate = cpDefinition.getModifiedDate();
				name = LanguageUtils.getLanguageIdMap(
					cpDefinition.getNameMap());
				options = _getProductOptions(cpDefinition, dtoConverterContext);
				productId = cProduct.getCProductId();
				productSpecifications = _getProductSpecifications(
					cpDefinition, dtoConverterContext);
				productType = cpDefinition.getProductTypeName();
				relatedProducts = _getRelatedProducts(
					cpDefinition, dtoConverterContext);
				shippingConfiguration =
					(ProductShippingConfiguration)
						productShippingConfigurationDTOConverter.toDTO(
							dtoConverterContext);
				shortDescription = LanguageUtils.getLanguageIdMap(
					cpDefinition.getShortDescriptionMap());
				skus = _getSkus(cpDefinition, dtoConverterContext);
				subscriptionConfiguration =
					(ProductSubscriptionConfiguration)
						productSubscriptionConfigurationDTOConverter.toDTO(
							dtoConverterContext);
				taxConfiguration =
					(ProductTaxConfiguration)
						productTaxConfigurationDTOConverter.toDTO(
							dtoConverterContext);
				tags = _getTags(cpDefinition);
			}
		};
	}

	private Attachment[] _getAttachments(
			CPDefinition cpDefinition, int type,
			DTOConverterContext dtoConverterContext)
		throws Exception {

		List<Attachment> attachments = new ArrayList<>();

		DTOConverter attachmentDTOConverter =
			_dtoConverterRegistry.getDTOConverter(
				CPAttachmentFileEntry.class.getName());

		for (CPAttachmentFileEntry cpAttachmentFileEntry :
				cpDefinition.getCPAttachmentFileEntries(
					type, WorkflowConstants.STATUS_APPROVED)) {

			attachments.add(
				(Attachment)attachmentDTOConverter.toDTO(
					new DefaultDTOConverterContext(
						dtoConverterContext.getLocale(),
						cpAttachmentFileEntry.getCPAttachmentFileEntryId())));
		}

		Stream<Attachment> stream = attachments.stream();

		return stream.toArray(Attachment[]::new);
	}

	private Category[] _getCategories(
			CPDefinition cpDefinition, DTOConverterContext dtoConverterContext)
		throws Exception {

		List<Category> categories = new ArrayList<>();

		List<AssetCategory> assetCategories =
			_assetCategoryService.getCategories(
				cpDefinition.getModelClassName(),
				cpDefinition.getCPDefinitionId());

		DTOConverter categoryDTOConverter =
			_dtoConverterRegistry.getDTOConverter(
				AssetCategory.class.getName());

		for (AssetCategory assetCategory : assetCategories) {
			categories.add(
				(Category)categoryDTOConverter.toDTO(
					new DefaultDTOConverterContext(
						dtoConverterContext.getLocale(),
						assetCategory.getCategoryId())));
		}

		Stream<Category> stream = categories.stream();

		return stream.toArray(Category[]::new);
	}

	private long _getCommerceCatalogId(CPDefinition cpDefinition) {
		CommerceCatalog commerceCatalog = cpDefinition.getCommerceCatalog();

		if (commerceCatalog == null) {
			return 0;
		}

		return commerceCatalog.getCommerceCatalogId();
	}

	private ProductOption[] _getProductOptions(
			CPDefinition cpDefinition, DTOConverterContext dtoConverterContext)
		throws Exception {

		List<ProductOption> productOptions = new ArrayList<>();

		DTOConverter productOptionDTOConverter =
			_dtoConverterRegistry.getDTOConverter(
				CPDefinitionOptionRel.class.getName());

		for (CPDefinitionOptionRel cpDefinitionOptionRel :
				cpDefinition.getCPDefinitionOptionRels()) {

			productOptions.add(
				(ProductOption)productOptionDTOConverter.toDTO(
					new DefaultDTOConverterContext(
						dtoConverterContext.getLocale(),
						cpDefinitionOptionRel.getCPDefinitionOptionRelId())));
		}

		Stream<ProductOption> stream = productOptions.stream();

		return stream.toArray(ProductOption[]::new);
	}

	private ProductSpecification[] _getProductSpecifications(
			CPDefinition cpDefinition, DTOConverterContext dtoConverterContext)
		throws Exception {

		List<ProductSpecification> productSpecifications = new ArrayList<>();

		DTOConverter productSpecificationDTOConverter =
			_dtoConverterRegistry.getDTOConverter(
				CPDefinitionSpecificationOptionValue.class.getName());

		for (CPDefinitionSpecificationOptionValue
				cpDefinitionSpecificationOptionValue :
					cpDefinition.getCPDefinitionSpecificationOptionValues()) {

			productSpecifications.add(
				(ProductSpecification)productSpecificationDTOConverter.toDTO(
					new DefaultDTOConverterContext(
						dtoConverterContext.getLocale(),
						cpDefinitionSpecificationOptionValue.
							getCPDefinitionSpecificationOptionValueId())));
		}

		Stream<ProductSpecification> stream = productSpecifications.stream();

		return stream.toArray(ProductSpecification[]::new);
	}

	private RelatedProduct[] _getRelatedProducts(
			CPDefinition cpDefinition, DTOConverterContext dtoConverterContext)
		throws Exception {

		List<RelatedProduct> relatedProducts = new ArrayList<>();

		DTOConverter relatedProductDTOConverter =
			_dtoConverterRegistry.getDTOConverter(
				CPDefinitionLink.class.getName());

		List<CPDefinitionLink> cpDefinitionLinks =
			_cpDefinitionLinkService.getCPDefinitionLinks(
				cpDefinition.getCPDefinitionId(), QueryUtil.ALL_POS,
				QueryUtil.ALL_POS);

		for (CPDefinitionLink cpDefinitionLink : cpDefinitionLinks) {
			relatedProducts.add(
				(RelatedProduct)relatedProductDTOConverter.toDTO(
					new DefaultDTOConverterContext(
						dtoConverterContext.getLocale(),
						cpDefinitionLink.getCPDefinitionLinkId())));
		}

		Stream<RelatedProduct> stream = relatedProducts.stream();

		return stream.toArray(RelatedProduct[]::new);
	}

	private Sku[] _getSkus(
			CPDefinition cpDefinition, DTOConverterContext dtoConverterContext)
		throws Exception {

		DTOConverter skuDTOConverter = _dtoConverterRegistry.getDTOConverter(
			CPInstance.class.getName());
		List<Sku> skus = new ArrayList<>();

		for (CPInstance cpInstance : cpDefinition.getCPInstances()) {
			skus.add(
				(Sku)skuDTOConverter.toDTO(
					new DefaultDTOConverterContext(
						dtoConverterContext.getLocale(),
						cpInstance.getCPInstanceId())));
		}

		Stream<Sku> stream = skus.stream();

		return stream.toArray(Sku[]::new);
	}

	private String[] _getTags(CPDefinition cpDefinition) {
		List<AssetTag> assetEntryAssetTags = _assetTagService.getTags(
			cpDefinition.getModelClassName(), cpDefinition.getCPDefinitionId());

		Stream<AssetTag> stream = assetEntryAssetTags.stream();

		return stream.map(
			AssetTag::getName
		).toArray(
			String[]::new
		);
	}

	@Reference
	private AssetCategoryService _assetCategoryService;

	@Reference
	private AssetTagService _assetTagService;

	@Reference
	private CPDefinitionLinkService _cpDefinitionLinkService;

	@Reference
	private CPDefinitionService _cpDefinitionService;

	@Reference
	private DTOConverterRegistry _dtoConverterRegistry;

}