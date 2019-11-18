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

package com.liferay.headless.commerce.admin.catalog.internal.graphql.mutation.v1_0;

import com.liferay.headless.commerce.admin.catalog.dto.v1_0.Attachment;
import com.liferay.headless.commerce.admin.catalog.dto.v1_0.AttachmentBase64;
import com.liferay.headless.commerce.admin.catalog.dto.v1_0.AttachmentUrl;
import com.liferay.headless.commerce.admin.catalog.dto.v1_0.Catalog;
import com.liferay.headless.commerce.admin.catalog.dto.v1_0.Category;
import com.liferay.headless.commerce.admin.catalog.dto.v1_0.Option;
import com.liferay.headless.commerce.admin.catalog.dto.v1_0.OptionCategory;
import com.liferay.headless.commerce.admin.catalog.dto.v1_0.OptionValue;
import com.liferay.headless.commerce.admin.catalog.dto.v1_0.Product;
import com.liferay.headless.commerce.admin.catalog.dto.v1_0.ProductConfiguration;
import com.liferay.headless.commerce.admin.catalog.dto.v1_0.ProductOption;
import com.liferay.headless.commerce.admin.catalog.dto.v1_0.ProductOptionValue;
import com.liferay.headless.commerce.admin.catalog.dto.v1_0.ProductShippingConfiguration;
import com.liferay.headless.commerce.admin.catalog.dto.v1_0.ProductSpecification;
import com.liferay.headless.commerce.admin.catalog.dto.v1_0.ProductSubscriptionConfiguration;
import com.liferay.headless.commerce.admin.catalog.dto.v1_0.ProductTaxConfiguration;
import com.liferay.headless.commerce.admin.catalog.dto.v1_0.RelatedProduct;
import com.liferay.headless.commerce.admin.catalog.dto.v1_0.Sku;
import com.liferay.headless.commerce.admin.catalog.dto.v1_0.Specification;
import com.liferay.headless.commerce.admin.catalog.resource.v1_0.AttachmentResource;
import com.liferay.headless.commerce.admin.catalog.resource.v1_0.CatalogResource;
import com.liferay.headless.commerce.admin.catalog.resource.v1_0.CategoryResource;
import com.liferay.headless.commerce.admin.catalog.resource.v1_0.OptionCategoryResource;
import com.liferay.headless.commerce.admin.catalog.resource.v1_0.OptionResource;
import com.liferay.headless.commerce.admin.catalog.resource.v1_0.OptionValueResource;
import com.liferay.headless.commerce.admin.catalog.resource.v1_0.ProductConfigurationResource;
import com.liferay.headless.commerce.admin.catalog.resource.v1_0.ProductOptionResource;
import com.liferay.headless.commerce.admin.catalog.resource.v1_0.ProductOptionValueResource;
import com.liferay.headless.commerce.admin.catalog.resource.v1_0.ProductResource;
import com.liferay.headless.commerce.admin.catalog.resource.v1_0.ProductShippingConfigurationResource;
import com.liferay.headless.commerce.admin.catalog.resource.v1_0.ProductSpecificationResource;
import com.liferay.headless.commerce.admin.catalog.resource.v1_0.ProductSubscriptionConfigurationResource;
import com.liferay.headless.commerce.admin.catalog.resource.v1_0.ProductTaxConfigurationResource;
import com.liferay.headless.commerce.admin.catalog.resource.v1_0.RelatedProductResource;
import com.liferay.headless.commerce.admin.catalog.resource.v1_0.SkuResource;
import com.liferay.headless.commerce.admin.catalog.resource.v1_0.SpecificationResource;
import com.liferay.petra.function.UnsafeConsumer;
import com.liferay.petra.function.UnsafeFunction;
import com.liferay.portal.kernel.security.auth.CompanyThreadLocal;
import com.liferay.portal.kernel.service.CompanyLocalServiceUtil;
import com.liferay.portal.vulcan.pagination.Page;

import graphql.annotations.annotationTypes.GraphQLField;
import graphql.annotations.annotationTypes.GraphQLInvokeDetached;
import graphql.annotations.annotationTypes.GraphQLName;

import java.util.Collection;

import javax.annotation.Generated;

import javax.ws.rs.core.Response;

import org.osgi.service.component.ComponentServiceObjects;

/**
 * @author Zoltán Takács
 * @generated
 */
@Generated("")
public class Mutation {

	public static void setAttachmentResourceComponentServiceObjects(
		ComponentServiceObjects<AttachmentResource>
			attachmentResourceComponentServiceObjects) {

		_attachmentResourceComponentServiceObjects =
			attachmentResourceComponentServiceObjects;
	}

	public static void setCatalogResourceComponentServiceObjects(
		ComponentServiceObjects<CatalogResource>
			catalogResourceComponentServiceObjects) {

		_catalogResourceComponentServiceObjects =
			catalogResourceComponentServiceObjects;
	}

	public static void setCategoryResourceComponentServiceObjects(
		ComponentServiceObjects<CategoryResource>
			categoryResourceComponentServiceObjects) {

		_categoryResourceComponentServiceObjects =
			categoryResourceComponentServiceObjects;
	}

	public static void setOptionResourceComponentServiceObjects(
		ComponentServiceObjects<OptionResource>
			optionResourceComponentServiceObjects) {

		_optionResourceComponentServiceObjects =
			optionResourceComponentServiceObjects;
	}

	public static void setOptionCategoryResourceComponentServiceObjects(
		ComponentServiceObjects<OptionCategoryResource>
			optionCategoryResourceComponentServiceObjects) {

		_optionCategoryResourceComponentServiceObjects =
			optionCategoryResourceComponentServiceObjects;
	}

	public static void setOptionValueResourceComponentServiceObjects(
		ComponentServiceObjects<OptionValueResource>
			optionValueResourceComponentServiceObjects) {

		_optionValueResourceComponentServiceObjects =
			optionValueResourceComponentServiceObjects;
	}

	public static void setProductResourceComponentServiceObjects(
		ComponentServiceObjects<ProductResource>
			productResourceComponentServiceObjects) {

		_productResourceComponentServiceObjects =
			productResourceComponentServiceObjects;
	}

	public static void setProductConfigurationResourceComponentServiceObjects(
		ComponentServiceObjects<ProductConfigurationResource>
			productConfigurationResourceComponentServiceObjects) {

		_productConfigurationResourceComponentServiceObjects =
			productConfigurationResourceComponentServiceObjects;
	}

	public static void setProductOptionResourceComponentServiceObjects(
		ComponentServiceObjects<ProductOptionResource>
			productOptionResourceComponentServiceObjects) {

		_productOptionResourceComponentServiceObjects =
			productOptionResourceComponentServiceObjects;
	}

	public static void setProductOptionValueResourceComponentServiceObjects(
		ComponentServiceObjects<ProductOptionValueResource>
			productOptionValueResourceComponentServiceObjects) {

		_productOptionValueResourceComponentServiceObjects =
			productOptionValueResourceComponentServiceObjects;
	}

	public static void
		setProductShippingConfigurationResourceComponentServiceObjects(
			ComponentServiceObjects<ProductShippingConfigurationResource>
				productShippingConfigurationResourceComponentServiceObjects) {

		_productShippingConfigurationResourceComponentServiceObjects =
			productShippingConfigurationResourceComponentServiceObjects;
	}

	public static void setProductSpecificationResourceComponentServiceObjects(
		ComponentServiceObjects<ProductSpecificationResource>
			productSpecificationResourceComponentServiceObjects) {

		_productSpecificationResourceComponentServiceObjects =
			productSpecificationResourceComponentServiceObjects;
	}

	public static void
		setProductSubscriptionConfigurationResourceComponentServiceObjects(
			ComponentServiceObjects<ProductSubscriptionConfigurationResource>
				productSubscriptionConfigurationResourceComponentServiceObjects) {

		_productSubscriptionConfigurationResourceComponentServiceObjects =
			productSubscriptionConfigurationResourceComponentServiceObjects;
	}

	public static void
		setProductTaxConfigurationResourceComponentServiceObjects(
			ComponentServiceObjects<ProductTaxConfigurationResource>
				productTaxConfigurationResourceComponentServiceObjects) {

		_productTaxConfigurationResourceComponentServiceObjects =
			productTaxConfigurationResourceComponentServiceObjects;
	}

	public static void setRelatedProductResourceComponentServiceObjects(
		ComponentServiceObjects<RelatedProductResource>
			relatedProductResourceComponentServiceObjects) {

		_relatedProductResourceComponentServiceObjects =
			relatedProductResourceComponentServiceObjects;
	}

	public static void setSkuResourceComponentServiceObjects(
		ComponentServiceObjects<SkuResource>
			skuResourceComponentServiceObjects) {

		_skuResourceComponentServiceObjects =
			skuResourceComponentServiceObjects;
	}

	public static void setSpecificationResourceComponentServiceObjects(
		ComponentServiceObjects<SpecificationResource>
			specificationResourceComponentServiceObjects) {

		_specificationResourceComponentServiceObjects =
			specificationResourceComponentServiceObjects;
	}

	@GraphQLField
	@GraphQLInvokeDetached
	public Attachment postProductIdAttachmentByBase64(
			@GraphQLName("id") Long id,
			@GraphQLName("attachmentBase64") AttachmentBase64 attachmentBase64)
		throws Exception {

		return _applyComponentServiceObjects(
			_attachmentResourceComponentServiceObjects,
			this::_populateResourceContext,
			attachmentResource ->
				attachmentResource.postProductIdAttachmentByBase64(
					id, attachmentBase64));
	}

	@GraphQLField
	@GraphQLInvokeDetached
	public Attachment postProductIdAttachmentByUrl(
			@GraphQLName("id") Long id,
			@GraphQLName("attachmentUrl") AttachmentUrl attachmentUrl)
		throws Exception {

		return _applyComponentServiceObjects(
			_attachmentResourceComponentServiceObjects,
			this::_populateResourceContext,
			attachmentResource ->
				attachmentResource.postProductIdAttachmentByUrl(
					id, attachmentUrl));
	}

	@GraphQLField
	@GraphQLInvokeDetached
	public Attachment postProductIdAttachment(
			@GraphQLName("id") Long id,
			@GraphQLName("attachment") Attachment attachment)
		throws Exception {

		return _applyComponentServiceObjects(
			_attachmentResourceComponentServiceObjects,
			this::_populateResourceContext,
			attachmentResource -> attachmentResource.postProductIdAttachment(
				id, attachment));
	}

	@GraphQLField
	@GraphQLInvokeDetached
	public Attachment postProductByExternalReferenceCodeAttachmentByBase64(
			@GraphQLName("externalReferenceCode") String externalReferenceCode,
			@GraphQLName("attachmentBase64") AttachmentBase64 attachmentBase64)
		throws Exception {

		return _applyComponentServiceObjects(
			_attachmentResourceComponentServiceObjects,
			this::_populateResourceContext,
			attachmentResource ->
				attachmentResource.
					postProductByExternalReferenceCodeAttachmentByBase64(
						externalReferenceCode, attachmentBase64));
	}

	@GraphQLField
	@GraphQLInvokeDetached
	public Attachment postProductByExternalReferenceCodeAttachmentByUrl(
			@GraphQLName("externalReferenceCode") String externalReferenceCode,
			@GraphQLName("attachmentUrl") AttachmentUrl attachmentUrl)
		throws Exception {

		return _applyComponentServiceObjects(
			_attachmentResourceComponentServiceObjects,
			this::_populateResourceContext,
			attachmentResource ->
				attachmentResource.
					postProductByExternalReferenceCodeAttachmentByUrl(
						externalReferenceCode, attachmentUrl));
	}

	@GraphQLField
	@GraphQLInvokeDetached
	public Attachment postProductByExternalReferenceCodeAttachment(
			@GraphQLName("externalReferenceCode") String externalReferenceCode,
			@GraphQLName("attachment") Attachment attachment)
		throws Exception {

		return _applyComponentServiceObjects(
			_attachmentResourceComponentServiceObjects,
			this::_populateResourceContext,
			attachmentResource ->
				attachmentResource.postProductByExternalReferenceCodeAttachment(
					externalReferenceCode, attachment));
	}

	@GraphQLField
	@GraphQLInvokeDetached
	public Attachment postProductIdImageByBase64(
			@GraphQLName("id") Long id,
			@GraphQLName("attachmentBase64") AttachmentBase64 attachmentBase64)
		throws Exception {

		return _applyComponentServiceObjects(
			_attachmentResourceComponentServiceObjects,
			this::_populateResourceContext,
			attachmentResource -> attachmentResource.postProductIdImageByBase64(
				id, attachmentBase64));
	}

	@GraphQLField
	@GraphQLInvokeDetached
	public Attachment postProductIdImageByUrl(
			@GraphQLName("id") Long id,
			@GraphQLName("attachmentUrl") AttachmentUrl attachmentUrl)
		throws Exception {

		return _applyComponentServiceObjects(
			_attachmentResourceComponentServiceObjects,
			this::_populateResourceContext,
			attachmentResource -> attachmentResource.postProductIdImageByUrl(
				id, attachmentUrl));
	}

	@GraphQLField
	@GraphQLInvokeDetached
	public Attachment postProductIdImage(
			@GraphQLName("id") Long id,
			@GraphQLName("attachment") Attachment attachment)
		throws Exception {

		return _applyComponentServiceObjects(
			_attachmentResourceComponentServiceObjects,
			this::_populateResourceContext,
			attachmentResource -> attachmentResource.postProductIdImage(
				id, attachment));
	}

	@GraphQLField
	@GraphQLInvokeDetached
	public Attachment postProductByExternalReferenceCodeImage(
			@GraphQLName("externalReferenceCode") String externalReferenceCode,
			@GraphQLName("attachment") Attachment attachment)
		throws Exception {

		return _applyComponentServiceObjects(
			_attachmentResourceComponentServiceObjects,
			this::_populateResourceContext,
			attachmentResource ->
				attachmentResource.postProductByExternalReferenceCodeImage(
					externalReferenceCode, attachment));
	}

	@GraphQLField
	@GraphQLInvokeDetached
	public Attachment postProductByExternalReferenceCodeImageByBase64(
			@GraphQLName("externalReferenceCode") String externalReferenceCode,
			@GraphQLName("attachmentBase64") AttachmentBase64 attachmentBase64)
		throws Exception {

		return _applyComponentServiceObjects(
			_attachmentResourceComponentServiceObjects,
			this::_populateResourceContext,
			attachmentResource ->
				attachmentResource.
					postProductByExternalReferenceCodeImageByBase64(
						externalReferenceCode, attachmentBase64));
	}

	@GraphQLField
	@GraphQLInvokeDetached
	public Attachment postProductByExternalReferenceCodeImageByUrl(
			@GraphQLName("externalReferenceCode") String externalReferenceCode,
			@GraphQLName("attachmentUrl") AttachmentUrl attachmentUrl)
		throws Exception {

		return _applyComponentServiceObjects(
			_attachmentResourceComponentServiceObjects,
			this::_populateResourceContext,
			attachmentResource ->
				attachmentResource.postProductByExternalReferenceCodeImageByUrl(
					externalReferenceCode, attachmentUrl));
	}

	@GraphQLInvokeDetached
	public Response deleteCatalog(@GraphQLName("id") Long id) throws Exception {
		return _applyComponentServiceObjects(
			_catalogResourceComponentServiceObjects,
			this::_populateResourceContext,
			catalogResource -> catalogResource.deleteCatalog(id));
	}

	@GraphQLInvokeDetached
	public Response patchCatalog(
			@GraphQLName("id") Long id, @GraphQLName("catalog") Catalog catalog)
		throws Exception {

		return _applyComponentServiceObjects(
			_catalogResourceComponentServiceObjects,
			this::_populateResourceContext,
			catalogResource -> catalogResource.patchCatalog(id, catalog));
	}

	@GraphQLInvokeDetached
	public Response deleteCatalogByExternalReferenceCode(
			@GraphQLName("externalReferenceCode") String externalReferenceCode)
		throws Exception {

		return _applyComponentServiceObjects(
			_catalogResourceComponentServiceObjects,
			this::_populateResourceContext,
			catalogResource ->
				catalogResource.deleteCatalogByExternalReferenceCode(
					externalReferenceCode));
	}

	@GraphQLInvokeDetached
	public Response patchCatalogByExternalReferenceCode(
			@GraphQLName("externalReferenceCode") String externalReferenceCode,
			@GraphQLName("catalog") Catalog catalog)
		throws Exception {

		return _applyComponentServiceObjects(
			_catalogResourceComponentServiceObjects,
			this::_populateResourceContext,
			catalogResource ->
				catalogResource.patchCatalogByExternalReferenceCode(
					externalReferenceCode, catalog));
	}

	@GraphQLField
	@GraphQLInvokeDetached
	public Catalog postCatalog(@GraphQLName("catalog") Catalog catalog)
		throws Exception {

		return _applyComponentServiceObjects(
			_catalogResourceComponentServiceObjects,
			this::_populateResourceContext,
			catalogResource -> catalogResource.postCatalog(catalog));
	}

	@GraphQLInvokeDetached
	public Response patchProductIdCategory(
			@GraphQLName("id") Long id,
			@GraphQLName("categories") Category[] categories)
		throws Exception {

		return _applyComponentServiceObjects(
			_categoryResourceComponentServiceObjects,
			this::_populateResourceContext,
			categoryResource -> categoryResource.patchProductIdCategory(
				id, categories));
	}

	@GraphQLInvokeDetached
	public Response patchProductByExternalReferenceCodeCategory(
			@GraphQLName("externalReferenceCode") String externalReferenceCode,
			@GraphQLName("categories") Category[] categories)
		throws Exception {

		return _applyComponentServiceObjects(
			_categoryResourceComponentServiceObjects,
			this::_populateResourceContext,
			categoryResource ->
				categoryResource.patchProductByExternalReferenceCodeCategory(
					externalReferenceCode, categories));
	}

	@GraphQLInvokeDetached
	public Response deleteOption(@GraphQLName("id") Long id) throws Exception {
		return _applyComponentServiceObjects(
			_optionResourceComponentServiceObjects,
			this::_populateResourceContext,
			optionResource -> optionResource.deleteOption(id));
	}

	@GraphQLInvokeDetached
	public Response patchOption(
			@GraphQLName("id") Long id, @GraphQLName("option") Option option)
		throws Exception {

		return _applyComponentServiceObjects(
			_optionResourceComponentServiceObjects,
			this::_populateResourceContext,
			optionResource -> optionResource.patchOption(id, option));
	}

	@GraphQLInvokeDetached
	public Response deleteOptionByExternalReferenceCode(
			@GraphQLName("externalReferenceCode") String externalReferenceCode)
		throws Exception {

		return _applyComponentServiceObjects(
			_optionResourceComponentServiceObjects,
			this::_populateResourceContext,
			optionResource ->
				optionResource.deleteOptionByExternalReferenceCode(
					externalReferenceCode));
	}

	@GraphQLInvokeDetached
	public Response patchOptionByExternalReferenceCode(
			@GraphQLName("externalReferenceCode") String externalReferenceCode,
			@GraphQLName("option") Option option)
		throws Exception {

		return _applyComponentServiceObjects(
			_optionResourceComponentServiceObjects,
			this::_populateResourceContext,
			optionResource -> optionResource.patchOptionByExternalReferenceCode(
				externalReferenceCode, option));
	}

	@GraphQLField
	@GraphQLInvokeDetached
	public Option postOption(@GraphQLName("option") Option option)
		throws Exception {

		return _applyComponentServiceObjects(
			_optionResourceComponentServiceObjects,
			this::_populateResourceContext,
			optionResource -> optionResource.postOption(option));
	}

	@GraphQLInvokeDetached
	public Response deleteOptionCategory(@GraphQLName("id") Long id)
		throws Exception {

		return _applyComponentServiceObjects(
			_optionCategoryResourceComponentServiceObjects,
			this::_populateResourceContext,
			optionCategoryResource ->
				optionCategoryResource.deleteOptionCategory(id));
	}

	@GraphQLInvokeDetached
	public Response patchOptionCategory(
			@GraphQLName("id") Long id,
			@GraphQLName("optionCategory") OptionCategory optionCategory)
		throws Exception {

		return _applyComponentServiceObjects(
			_optionCategoryResourceComponentServiceObjects,
			this::_populateResourceContext,
			optionCategoryResource ->
				optionCategoryResource.patchOptionCategory(id, optionCategory));
	}

	@GraphQLField
	@GraphQLInvokeDetached
	public OptionCategory postOptionCategory(
			@GraphQLName("optionCategory") OptionCategory optionCategory)
		throws Exception {

		return _applyComponentServiceObjects(
			_optionCategoryResourceComponentServiceObjects,
			this::_populateResourceContext,
			optionCategoryResource -> optionCategoryResource.postOptionCategory(
				optionCategory));
	}

	@GraphQLField
	@GraphQLInvokeDetached
	public OptionValue postOptionIdOptionValue(
			@GraphQLName("id") Long id,
			@GraphQLName("optionValue") OptionValue optionValue)
		throws Exception {

		return _applyComponentServiceObjects(
			_optionValueResourceComponentServiceObjects,
			this::_populateResourceContext,
			optionValueResource -> optionValueResource.postOptionIdOptionValue(
				id, optionValue));
	}

	@GraphQLField
	@GraphQLInvokeDetached
	public OptionValue postOptionByExternalReferenceCodeOptionValue(
			@GraphQLName("externalReferenceCode") String externalReferenceCode,
			@GraphQLName("optionValue") OptionValue optionValue)
		throws Exception {

		return _applyComponentServiceObjects(
			_optionValueResourceComponentServiceObjects,
			this::_populateResourceContext,
			optionValueResource ->
				optionValueResource.
					postOptionByExternalReferenceCodeOptionValue(
						externalReferenceCode, optionValue));
	}

	@GraphQLInvokeDetached
	public Response deleteProduct(@GraphQLName("id") Long id) throws Exception {
		return _applyComponentServiceObjects(
			_productResourceComponentServiceObjects,
			this::_populateResourceContext,
			productResource -> productResource.deleteProduct(id));
	}

	@GraphQLInvokeDetached
	public Response patchProduct(
			@GraphQLName("id") Long id, @GraphQLName("product") Product product)
		throws Exception {

		return _applyComponentServiceObjects(
			_productResourceComponentServiceObjects,
			this::_populateResourceContext,
			productResource -> productResource.patchProduct(id, product));
	}

	@GraphQLInvokeDetached
	public Response deleteProductByExternalReferenceCode(
			@GraphQLName("externalReferenceCode") String externalReferenceCode)
		throws Exception {

		return _applyComponentServiceObjects(
			_productResourceComponentServiceObjects,
			this::_populateResourceContext,
			productResource ->
				productResource.deleteProductByExternalReferenceCode(
					externalReferenceCode));
	}

	@GraphQLInvokeDetached
	public Response patchProductByExternalReferenceCode(
			@GraphQLName("externalReferenceCode") String externalReferenceCode,
			@GraphQLName("product") Product product)
		throws Exception {

		return _applyComponentServiceObjects(
			_productResourceComponentServiceObjects,
			this::_populateResourceContext,
			productResource ->
				productResource.patchProductByExternalReferenceCode(
					externalReferenceCode, product));
	}

	@GraphQLField
	@GraphQLInvokeDetached
	public Product postProduct(@GraphQLName("product") Product product)
		throws Exception {

		return _applyComponentServiceObjects(
			_productResourceComponentServiceObjects,
			this::_populateResourceContext,
			productResource -> productResource.postProduct(product));
	}

	@GraphQLInvokeDetached
	public Response patchProductIdConfiguration(
			@GraphQLName("id") Long id,
			@GraphQLName("productConfiguration") ProductConfiguration
				productConfiguration)
		throws Exception {

		return _applyComponentServiceObjects(
			_productConfigurationResourceComponentServiceObjects,
			this::_populateResourceContext,
			productConfigurationResource ->
				productConfigurationResource.patchProductIdConfiguration(
					id, productConfiguration));
	}

	@GraphQLInvokeDetached
	public Response patchProductByExternalReferenceCodeConfiguration(
			@GraphQLName("externalReferenceCode") String externalReferenceCode,
			@GraphQLName("productConfiguration") ProductConfiguration
				productConfiguration)
		throws Exception {

		return _applyComponentServiceObjects(
			_productConfigurationResourceComponentServiceObjects,
			this::_populateResourceContext,
			productConfigurationResource ->
				productConfigurationResource.
					patchProductByExternalReferenceCodeConfiguration(
						externalReferenceCode, productConfiguration));
	}

	@GraphQLField
	@GraphQLInvokeDetached
	public Collection<ProductOption> postProductIdProductOptionsPage(
			@GraphQLName("id") Long id,
			@GraphQLName("productOptions") ProductOption[] productOptions)
		throws Exception {

		return _applyComponentServiceObjects(
			_productOptionResourceComponentServiceObjects,
			this::_populateResourceContext,
			productOptionResource -> {
				Page paginationPage =
					productOptionResource.postProductIdProductOptionsPage(
						id, productOptions);

				return paginationPage.getItems();
			});
	}

	@GraphQLField
	@GraphQLInvokeDetached
	public Collection<ProductOption>
			postProductByExternalReferenceCodeProductOptionsPage(
				@GraphQLName("externalReferenceCode") String
					externalReferenceCode,
				@GraphQLName("productOptions") ProductOption[] productOptions)
		throws Exception {

		return _applyComponentServiceObjects(
			_productOptionResourceComponentServiceObjects,
			this::_populateResourceContext,
			productOptionResource -> {
				Page paginationPage =
					productOptionResource.
						postProductByExternalReferenceCodeProductOptionsPage(
							externalReferenceCode, productOptions);

				return paginationPage.getItems();
			});
	}

	@GraphQLInvokeDetached
	public Response deleteProductOption(@GraphQLName("id") Long id)
		throws Exception {

		return _applyComponentServiceObjects(
			_productOptionResourceComponentServiceObjects,
			this::_populateResourceContext,
			productOptionResource -> productOptionResource.deleteProductOption(
				id));
	}

	@GraphQLInvokeDetached
	public Response patchProductOption(
			@GraphQLName("id") Long id,
			@GraphQLName("productOption") ProductOption productOption)
		throws Exception {

		return _applyComponentServiceObjects(
			_productOptionResourceComponentServiceObjects,
			this::_populateResourceContext,
			productOptionResource -> productOptionResource.patchProductOption(
				id, productOption));
	}

	@GraphQLField
	@GraphQLInvokeDetached
	public ProductOptionValue postProductOptionIdProductOptionValue(
			@GraphQLName("id") Long id,
			@GraphQLName("productOptionValue") ProductOptionValue
				productOptionValue)
		throws Exception {

		return _applyComponentServiceObjects(
			_productOptionValueResourceComponentServiceObjects,
			this::_populateResourceContext,
			productOptionValueResource ->
				productOptionValueResource.
					postProductOptionIdProductOptionValue(
						id, productOptionValue));
	}

	@GraphQLInvokeDetached
	public Response patchProductIdShippingConfiguration(
			@GraphQLName("id") Long id,
			@GraphQLName("productShippingConfiguration")
				ProductShippingConfiguration productShippingConfiguration)
		throws Exception {

		return _applyComponentServiceObjects(
			_productShippingConfigurationResourceComponentServiceObjects,
			this::_populateResourceContext,
			productShippingConfigurationResource ->
				productShippingConfigurationResource.
					patchProductIdShippingConfiguration(
						id, productShippingConfiguration));
	}

	@GraphQLInvokeDetached
	public Response patchProductByExternalReferenceCodeShippingConfiguration(
			@GraphQLName("externalReferenceCode") String externalReferenceCode,
			@GraphQLName("productShippingConfiguration")
				ProductShippingConfiguration productShippingConfiguration)
		throws Exception {

		return _applyComponentServiceObjects(
			_productShippingConfigurationResourceComponentServiceObjects,
			this::_populateResourceContext,
			productShippingConfigurationResource ->
				productShippingConfigurationResource.
					patchProductByExternalReferenceCodeShippingConfiguration(
						externalReferenceCode, productShippingConfiguration));
	}

	@GraphQLField
	@GraphQLInvokeDetached
	public ProductSpecification postProductIdProductSpecification(
			@GraphQLName("id") Long id,
			@GraphQLName("productSpecification") ProductSpecification
				productSpecification)
		throws Exception {

		return _applyComponentServiceObjects(
			_productSpecificationResourceComponentServiceObjects,
			this::_populateResourceContext,
			productSpecificationResource ->
				productSpecificationResource.postProductIdProductSpecification(
					id, productSpecification));
	}

	@GraphQLInvokeDetached
	public Response patchProductIdSubscriptionConfiguration(
			@GraphQLName("id") Long id,
			@GraphQLName("productSubscriptionConfiguration")
				ProductSubscriptionConfiguration
					productSubscriptionConfiguration)
		throws Exception {

		return _applyComponentServiceObjects(
			_productSubscriptionConfigurationResourceComponentServiceObjects,
			this::_populateResourceContext,
			productSubscriptionConfigurationResource ->
				productSubscriptionConfigurationResource.
					patchProductIdSubscriptionConfiguration(
						id, productSubscriptionConfiguration));
	}

	@GraphQLInvokeDetached
	public Response
			patchProductByExternalReferenceCodeSubscriptionConfiguration(
				@GraphQLName("externalReferenceCode") String
					externalReferenceCode,
				@GraphQLName("productSubscriptionConfiguration")
					ProductSubscriptionConfiguration
						productSubscriptionConfiguration)
		throws Exception {

		return _applyComponentServiceObjects(
			_productSubscriptionConfigurationResourceComponentServiceObjects,
			this::_populateResourceContext,
			productSubscriptionConfigurationResource ->
				productSubscriptionConfigurationResource.
					patchProductByExternalReferenceCodeSubscriptionConfiguration(
						externalReferenceCode,
						productSubscriptionConfiguration));
	}

	@GraphQLInvokeDetached
	public Response patchProductIdTaxConfiguration(
			@GraphQLName("id") Long id,
			@GraphQLName("productTaxConfiguration") ProductTaxConfiguration
				productTaxConfiguration)
		throws Exception {

		return _applyComponentServiceObjects(
			_productTaxConfigurationResourceComponentServiceObjects,
			this::_populateResourceContext,
			productTaxConfigurationResource ->
				productTaxConfigurationResource.patchProductIdTaxConfiguration(
					id, productTaxConfiguration));
	}

	@GraphQLInvokeDetached
	public Response patchProductByExternalReferenceCodeTaxConfiguration(
			@GraphQLName("externalReferenceCode") String externalReferenceCode,
			@GraphQLName("productTaxConfiguration") ProductTaxConfiguration
				productTaxConfiguration)
		throws Exception {

		return _applyComponentServiceObjects(
			_productTaxConfigurationResourceComponentServiceObjects,
			this::_populateResourceContext,
			productTaxConfigurationResource ->
				productTaxConfigurationResource.
					patchProductByExternalReferenceCodeTaxConfiguration(
						externalReferenceCode, productTaxConfiguration));
	}

	@GraphQLInvokeDetached
	public Response deleteRelatedProduct(@GraphQLName("id") Long id)
		throws Exception {

		return _applyComponentServiceObjects(
			_relatedProductResourceComponentServiceObjects,
			this::_populateResourceContext,
			relatedProductResource ->
				relatedProductResource.deleteRelatedProduct(id));
	}

	@GraphQLField
	@GraphQLInvokeDetached
	public RelatedProduct postProductIdRelatedProduct(
			@GraphQLName("id") Long id,
			@GraphQLName("relatedProduct") RelatedProduct relatedProduct)
		throws Exception {

		return _applyComponentServiceObjects(
			_relatedProductResourceComponentServiceObjects,
			this::_populateResourceContext,
			relatedProductResource ->
				relatedProductResource.postProductIdRelatedProduct(
					id, relatedProduct));
	}

	@GraphQLField
	@GraphQLInvokeDetached
	public RelatedProduct postProductByExternalReferenceCodeRelatedProduct(
			@GraphQLName("externalReferenceCode") String externalReferenceCode,
			@GraphQLName("relatedProduct") RelatedProduct relatedProduct)
		throws Exception {

		return _applyComponentServiceObjects(
			_relatedProductResourceComponentServiceObjects,
			this::_populateResourceContext,
			relatedProductResource ->
				relatedProductResource.
					postProductByExternalReferenceCodeRelatedProduct(
						externalReferenceCode, relatedProduct));
	}

	@GraphQLInvokeDetached
	public Response deleteSku(@GraphQLName("id") Long id) throws Exception {
		return _applyComponentServiceObjects(
			_skuResourceComponentServiceObjects, this::_populateResourceContext,
			skuResource -> skuResource.deleteSku(id));
	}

	@GraphQLInvokeDetached
	public Response patchSku(
			@GraphQLName("id") Long id, @GraphQLName("sku") Sku sku)
		throws Exception {

		return _applyComponentServiceObjects(
			_skuResourceComponentServiceObjects, this::_populateResourceContext,
			skuResource -> skuResource.patchSku(id, sku));
	}

	@GraphQLInvokeDetached
	public Response deleteSkuByExternalReferenceCode(
			@GraphQLName("externalReferenceCode") String externalReferenceCode)
		throws Exception {

		return _applyComponentServiceObjects(
			_skuResourceComponentServiceObjects, this::_populateResourceContext,
			skuResource -> skuResource.deleteSkuByExternalReferenceCode(
				externalReferenceCode));
	}

	@GraphQLInvokeDetached
	public Response patchSkuByExternalReferenceCode(
			@GraphQLName("externalReferenceCode") String externalReferenceCode,
			@GraphQLName("sku") Sku sku)
		throws Exception {

		return _applyComponentServiceObjects(
			_skuResourceComponentServiceObjects, this::_populateResourceContext,
			skuResource -> skuResource.patchSkuByExternalReferenceCode(
				externalReferenceCode, sku));
	}

	@GraphQLField
	@GraphQLInvokeDetached
	public Sku postProductIdSku(
			@GraphQLName("id") Long id, @GraphQLName("sku") Sku sku)
		throws Exception {

		return _applyComponentServiceObjects(
			_skuResourceComponentServiceObjects, this::_populateResourceContext,
			skuResource -> skuResource.postProductIdSku(id, sku));
	}

	@GraphQLField
	@GraphQLInvokeDetached
	public Sku postProductByExternalReferenceCodeSku(
			@GraphQLName("externalReferenceCode") String externalReferenceCode,
			@GraphQLName("sku") Sku sku)
		throws Exception {

		return _applyComponentServiceObjects(
			_skuResourceComponentServiceObjects, this::_populateResourceContext,
			skuResource -> skuResource.postProductByExternalReferenceCodeSku(
				externalReferenceCode, sku));
	}

	@GraphQLInvokeDetached
	public Response deleteSpecification(@GraphQLName("id") Long id)
		throws Exception {

		return _applyComponentServiceObjects(
			_specificationResourceComponentServiceObjects,
			this::_populateResourceContext,
			specificationResource -> specificationResource.deleteSpecification(
				id));
	}

	@GraphQLInvokeDetached
	public Response patchSpecification(
			@GraphQLName("id") Long id,
			@GraphQLName("specification") Specification specification)
		throws Exception {

		return _applyComponentServiceObjects(
			_specificationResourceComponentServiceObjects,
			this::_populateResourceContext,
			specificationResource -> specificationResource.patchSpecification(
				id, specification));
	}

	@GraphQLField
	@GraphQLInvokeDetached
	public Specification postSpecification(
			@GraphQLName("specification") Specification specification)
		throws Exception {

		return _applyComponentServiceObjects(
			_specificationResourceComponentServiceObjects,
			this::_populateResourceContext,
			specificationResource -> specificationResource.postSpecification(
				specification));
	}

	private <T, R, E1 extends Throwable, E2 extends Throwable> R
			_applyComponentServiceObjects(
				ComponentServiceObjects<T> componentServiceObjects,
				UnsafeConsumer<T, E1> unsafeConsumer,
				UnsafeFunction<T, R, E2> unsafeFunction)
		throws E1, E2 {

		T resource = componentServiceObjects.getService();

		try {
			unsafeConsumer.accept(resource);

			return unsafeFunction.apply(resource);
		}
		finally {
			componentServiceObjects.ungetService(resource);
		}
	}

	private <T, E1 extends Throwable, E2 extends Throwable> void
			_applyVoidComponentServiceObjects(
				ComponentServiceObjects<T> componentServiceObjects,
				UnsafeConsumer<T, E1> unsafeConsumer,
				UnsafeConsumer<T, E2> unsafeFunction)
		throws E1, E2 {

		T resource = componentServiceObjects.getService();

		try {
			unsafeConsumer.accept(resource);

			unsafeFunction.accept(resource);
		}
		finally {
			componentServiceObjects.ungetService(resource);
		}
	}

	private void _populateResourceContext(AttachmentResource attachmentResource)
		throws Exception {

		attachmentResource.setContextCompany(
			CompanyLocalServiceUtil.getCompany(
				CompanyThreadLocal.getCompanyId()));
	}

	private void _populateResourceContext(CatalogResource catalogResource)
		throws Exception {

		catalogResource.setContextCompany(
			CompanyLocalServiceUtil.getCompany(
				CompanyThreadLocal.getCompanyId()));
	}

	private void _populateResourceContext(CategoryResource categoryResource)
		throws Exception {

		categoryResource.setContextCompany(
			CompanyLocalServiceUtil.getCompany(
				CompanyThreadLocal.getCompanyId()));
	}

	private void _populateResourceContext(OptionResource optionResource)
		throws Exception {

		optionResource.setContextCompany(
			CompanyLocalServiceUtil.getCompany(
				CompanyThreadLocal.getCompanyId()));
	}

	private void _populateResourceContext(
			OptionCategoryResource optionCategoryResource)
		throws Exception {

		optionCategoryResource.setContextCompany(
			CompanyLocalServiceUtil.getCompany(
				CompanyThreadLocal.getCompanyId()));
	}

	private void _populateResourceContext(
			OptionValueResource optionValueResource)
		throws Exception {

		optionValueResource.setContextCompany(
			CompanyLocalServiceUtil.getCompany(
				CompanyThreadLocal.getCompanyId()));
	}

	private void _populateResourceContext(ProductResource productResource)
		throws Exception {

		productResource.setContextCompany(
			CompanyLocalServiceUtil.getCompany(
				CompanyThreadLocal.getCompanyId()));
	}

	private void _populateResourceContext(
			ProductConfigurationResource productConfigurationResource)
		throws Exception {

		productConfigurationResource.setContextCompany(
			CompanyLocalServiceUtil.getCompany(
				CompanyThreadLocal.getCompanyId()));
	}

	private void _populateResourceContext(
			ProductOptionResource productOptionResource)
		throws Exception {

		productOptionResource.setContextCompany(
			CompanyLocalServiceUtil.getCompany(
				CompanyThreadLocal.getCompanyId()));
	}

	private void _populateResourceContext(
			ProductOptionValueResource productOptionValueResource)
		throws Exception {

		productOptionValueResource.setContextCompany(
			CompanyLocalServiceUtil.getCompany(
				CompanyThreadLocal.getCompanyId()));
	}

	private void _populateResourceContext(
			ProductShippingConfigurationResource
				productShippingConfigurationResource)
		throws Exception {

		productShippingConfigurationResource.setContextCompany(
			CompanyLocalServiceUtil.getCompany(
				CompanyThreadLocal.getCompanyId()));
	}

	private void _populateResourceContext(
			ProductSpecificationResource productSpecificationResource)
		throws Exception {

		productSpecificationResource.setContextCompany(
			CompanyLocalServiceUtil.getCompany(
				CompanyThreadLocal.getCompanyId()));
	}

	private void _populateResourceContext(
			ProductSubscriptionConfigurationResource
				productSubscriptionConfigurationResource)
		throws Exception {

		productSubscriptionConfigurationResource.setContextCompany(
			CompanyLocalServiceUtil.getCompany(
				CompanyThreadLocal.getCompanyId()));
	}

	private void _populateResourceContext(
			ProductTaxConfigurationResource productTaxConfigurationResource)
		throws Exception {

		productTaxConfigurationResource.setContextCompany(
			CompanyLocalServiceUtil.getCompany(
				CompanyThreadLocal.getCompanyId()));
	}

	private void _populateResourceContext(
			RelatedProductResource relatedProductResource)
		throws Exception {

		relatedProductResource.setContextCompany(
			CompanyLocalServiceUtil.getCompany(
				CompanyThreadLocal.getCompanyId()));
	}

	private void _populateResourceContext(SkuResource skuResource)
		throws Exception {

		skuResource.setContextCompany(
			CompanyLocalServiceUtil.getCompany(
				CompanyThreadLocal.getCompanyId()));
	}

	private void _populateResourceContext(
			SpecificationResource specificationResource)
		throws Exception {

		specificationResource.setContextCompany(
			CompanyLocalServiceUtil.getCompany(
				CompanyThreadLocal.getCompanyId()));
	}

	private static ComponentServiceObjects<AttachmentResource>
		_attachmentResourceComponentServiceObjects;
	private static ComponentServiceObjects<CatalogResource>
		_catalogResourceComponentServiceObjects;
	private static ComponentServiceObjects<CategoryResource>
		_categoryResourceComponentServiceObjects;
	private static ComponentServiceObjects<OptionResource>
		_optionResourceComponentServiceObjects;
	private static ComponentServiceObjects<OptionCategoryResource>
		_optionCategoryResourceComponentServiceObjects;
	private static ComponentServiceObjects<OptionValueResource>
		_optionValueResourceComponentServiceObjects;
	private static ComponentServiceObjects<ProductResource>
		_productResourceComponentServiceObjects;
	private static ComponentServiceObjects<ProductConfigurationResource>
		_productConfigurationResourceComponentServiceObjects;
	private static ComponentServiceObjects<ProductOptionResource>
		_productOptionResourceComponentServiceObjects;
	private static ComponentServiceObjects<ProductOptionValueResource>
		_productOptionValueResourceComponentServiceObjects;
	private static ComponentServiceObjects<ProductShippingConfigurationResource>
		_productShippingConfigurationResourceComponentServiceObjects;
	private static ComponentServiceObjects<ProductSpecificationResource>
		_productSpecificationResourceComponentServiceObjects;
	private static ComponentServiceObjects
		<ProductSubscriptionConfigurationResource>
			_productSubscriptionConfigurationResourceComponentServiceObjects;
	private static ComponentServiceObjects<ProductTaxConfigurationResource>
		_productTaxConfigurationResourceComponentServiceObjects;
	private static ComponentServiceObjects<RelatedProductResource>
		_relatedProductResourceComponentServiceObjects;
	private static ComponentServiceObjects<SkuResource>
		_skuResourceComponentServiceObjects;
	private static ComponentServiceObjects<SpecificationResource>
		_specificationResourceComponentServiceObjects;

}