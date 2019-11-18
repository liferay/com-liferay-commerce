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

package com.liferay.headless.commerce.admin.catalog.internal.graphql.query.v1_0;

import com.liferay.headless.commerce.admin.catalog.dto.v1_0.Attachment;
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
import com.liferay.portal.kernel.search.Sort;
import com.liferay.portal.kernel.search.filter.Filter;
import com.liferay.portal.kernel.security.auth.CompanyThreadLocal;
import com.liferay.portal.kernel.service.CompanyLocalServiceUtil;
import com.liferay.portal.vulcan.pagination.Page;
import com.liferay.portal.vulcan.pagination.Pagination;

import graphql.annotations.annotationTypes.GraphQLField;
import graphql.annotations.annotationTypes.GraphQLInvokeDetached;
import graphql.annotations.annotationTypes.GraphQLName;

import java.util.Collection;

import javax.annotation.Generated;

import org.osgi.service.component.ComponentServiceObjects;

/**
 * @author Zoltán Takács
 * @generated
 */
@Generated("")
public class Query {

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
	public Collection<Attachment> getProductIdAttachmentsPage(
			@GraphQLName("id") Long id, @GraphQLName("pageSize") int pageSize,
			@GraphQLName("page") int page)
		throws Exception {

		return _applyComponentServiceObjects(
			_attachmentResourceComponentServiceObjects,
			this::_populateResourceContext,
			attachmentResource -> {
				Page paginationPage =
					attachmentResource.getProductIdAttachmentsPage(
						id, Pagination.of(pageSize, page));

				return paginationPage.getItems();
			});
	}

	@GraphQLField
	@GraphQLInvokeDetached
	public Collection<Attachment>
			getProductByExternalReferenceCodeAttachmentsPage(
				@GraphQLName("externalReferenceCode") String
					externalReferenceCode,
				@GraphQLName("pageSize") int pageSize,
				@GraphQLName("page") int page)
		throws Exception {

		return _applyComponentServiceObjects(
			_attachmentResourceComponentServiceObjects,
			this::_populateResourceContext,
			attachmentResource -> {
				Page paginationPage =
					attachmentResource.
						getProductByExternalReferenceCodeAttachmentsPage(
							externalReferenceCode,
							Pagination.of(pageSize, page));

				return paginationPage.getItems();
			});
	}

	@GraphQLField
	@GraphQLInvokeDetached
	public Collection<Attachment> getProductIdImagesPage(
			@GraphQLName("id") Long id, @GraphQLName("pageSize") int pageSize,
			@GraphQLName("page") int page)
		throws Exception {

		return _applyComponentServiceObjects(
			_attachmentResourceComponentServiceObjects,
			this::_populateResourceContext,
			attachmentResource -> {
				Page paginationPage = attachmentResource.getProductIdImagesPage(
					id, Pagination.of(pageSize, page));

				return paginationPage.getItems();
			});
	}

	@GraphQLField
	@GraphQLInvokeDetached
	public Collection<Attachment> getProductByExternalReferenceCodeImagesPage(
			@GraphQLName("externalReferenceCode") String externalReferenceCode,
			@GraphQLName("pageSize") int pageSize,
			@GraphQLName("page") int page)
		throws Exception {

		return _applyComponentServiceObjects(
			_attachmentResourceComponentServiceObjects,
			this::_populateResourceContext,
			attachmentResource -> {
				Page paginationPage =
					attachmentResource.
						getProductByExternalReferenceCodeImagesPage(
							externalReferenceCode,
							Pagination.of(pageSize, page));

				return paginationPage.getItems();
			});
	}

	@GraphQLField
	@GraphQLInvokeDetached
	public Catalog getCatalog(@GraphQLName("id") Long id) throws Exception {
		return _applyComponentServiceObjects(
			_catalogResourceComponentServiceObjects,
			this::_populateResourceContext,
			catalogResource -> catalogResource.getCatalog(id));
	}

	@GraphQLField
	@GraphQLInvokeDetached
	public Catalog getCatalogByExternalReferenceCode(
			@GraphQLName("externalReferenceCode") String externalReferenceCode)
		throws Exception {

		return _applyComponentServiceObjects(
			_catalogResourceComponentServiceObjects,
			this::_populateResourceContext,
			catalogResource ->
				catalogResource.getCatalogByExternalReferenceCode(
					externalReferenceCode));
	}

	@GraphQLField
	@GraphQLInvokeDetached
	public Collection<Catalog> getCatalogsPage(
			@GraphQLName("filter") Filter filter,
			@GraphQLName("pageSize") int pageSize,
			@GraphQLName("page") int page, @GraphQLName("sorts") Sort[] sorts)
		throws Exception {

		return _applyComponentServiceObjects(
			_catalogResourceComponentServiceObjects,
			this::_populateResourceContext,
			catalogResource -> {
				Page paginationPage = catalogResource.getCatalogsPage(
					filter, Pagination.of(pageSize, page), sorts);

				return paginationPage.getItems();
			});
	}

	@GraphQLField
	@GraphQLInvokeDetached
	public Collection<Category> getProductIdCategoriesPage(
			@GraphQLName("id") Long id, @GraphQLName("pageSize") int pageSize,
			@GraphQLName("page") int page)
		throws Exception {

		return _applyComponentServiceObjects(
			_categoryResourceComponentServiceObjects,
			this::_populateResourceContext,
			categoryResource -> {
				Page paginationPage =
					categoryResource.getProductIdCategoriesPage(
						id, Pagination.of(pageSize, page));

				return paginationPage.getItems();
			});
	}

	@GraphQLField
	@GraphQLInvokeDetached
	public Collection<Category> getProductByExternalReferenceCodeCategoriesPage(
			@GraphQLName("externalReferenceCode") String externalReferenceCode,
			@GraphQLName("pageSize") int pageSize,
			@GraphQLName("page") int page)
		throws Exception {

		return _applyComponentServiceObjects(
			_categoryResourceComponentServiceObjects,
			this::_populateResourceContext,
			categoryResource -> {
				Page paginationPage =
					categoryResource.
						getProductByExternalReferenceCodeCategoriesPage(
							externalReferenceCode,
							Pagination.of(pageSize, page));

				return paginationPage.getItems();
			});
	}

	@GraphQLField
	@GraphQLInvokeDetached
	public Option getOption(@GraphQLName("id") Long id) throws Exception {
		return _applyComponentServiceObjects(
			_optionResourceComponentServiceObjects,
			this::_populateResourceContext,
			optionResource -> optionResource.getOption(id));
	}

	@GraphQLField
	@GraphQLInvokeDetached
	public Option getOptionByExternalReferenceCode(
			@GraphQLName("externalReferenceCode") String externalReferenceCode)
		throws Exception {

		return _applyComponentServiceObjects(
			_optionResourceComponentServiceObjects,
			this::_populateResourceContext,
			optionResource -> optionResource.getOptionByExternalReferenceCode(
				externalReferenceCode));
	}

	@GraphQLField
	@GraphQLInvokeDetached
	public Collection<Option> getOptionsPage(
			@GraphQLName("filter") Filter filter,
			@GraphQLName("pageSize") int pageSize,
			@GraphQLName("page") int page, @GraphQLName("sorts") Sort[] sorts)
		throws Exception {

		return _applyComponentServiceObjects(
			_optionResourceComponentServiceObjects,
			this::_populateResourceContext,
			optionResource -> {
				Page paginationPage = optionResource.getOptionsPage(
					filter, Pagination.of(pageSize, page), sorts);

				return paginationPage.getItems();
			});
	}

	@GraphQLField
	@GraphQLInvokeDetached
	public OptionCategory getOptionCategory(@GraphQLName("id") Long id)
		throws Exception {

		return _applyComponentServiceObjects(
			_optionCategoryResourceComponentServiceObjects,
			this::_populateResourceContext,
			optionCategoryResource -> optionCategoryResource.getOptionCategory(
				id));
	}

	@GraphQLField
	@GraphQLInvokeDetached
	public Collection<OptionCategory> getOptionCategoriesPage(
			@GraphQLName("filter") Filter filter,
			@GraphQLName("pageSize") int pageSize,
			@GraphQLName("page") int page, @GraphQLName("sorts") Sort[] sorts)
		throws Exception {

		return _applyComponentServiceObjects(
			_optionCategoryResourceComponentServiceObjects,
			this::_populateResourceContext,
			optionCategoryResource -> {
				Page paginationPage =
					optionCategoryResource.getOptionCategoriesPage(
						filter, Pagination.of(pageSize, page), sorts);

				return paginationPage.getItems();
			});
	}

	@GraphQLField
	@GraphQLInvokeDetached
	public Collection<OptionValue> getOptionIdOptionValuesPage(
			@GraphQLName("id") Long id, @GraphQLName("pageSize") int pageSize,
			@GraphQLName("page") int page)
		throws Exception {

		return _applyComponentServiceObjects(
			_optionValueResourceComponentServiceObjects,
			this::_populateResourceContext,
			optionValueResource -> {
				Page paginationPage =
					optionValueResource.getOptionIdOptionValuesPage(
						id, Pagination.of(pageSize, page));

				return paginationPage.getItems();
			});
	}

	@GraphQLField
	@GraphQLInvokeDetached
	public Collection<OptionValue>
			getOptionByExternalReferenceCodeOptionValuesPage(
				@GraphQLName("externalReferenceCode") String
					externalReferenceCode,
				@GraphQLName("pageSize") int pageSize,
				@GraphQLName("page") int page)
		throws Exception {

		return _applyComponentServiceObjects(
			_optionValueResourceComponentServiceObjects,
			this::_populateResourceContext,
			optionValueResource -> {
				Page paginationPage =
					optionValueResource.
						getOptionByExternalReferenceCodeOptionValuesPage(
							externalReferenceCode,
							Pagination.of(pageSize, page));

				return paginationPage.getItems();
			});
	}

	@GraphQLField
	@GraphQLInvokeDetached
	public Product getProduct(@GraphQLName("id") Long id) throws Exception {
		return _applyComponentServiceObjects(
			_productResourceComponentServiceObjects,
			this::_populateResourceContext,
			productResource -> productResource.getProduct(id));
	}

	@GraphQLField
	@GraphQLInvokeDetached
	public Product getProductByExternalReferenceCode(
			@GraphQLName("externalReferenceCode") String externalReferenceCode)
		throws Exception {

		return _applyComponentServiceObjects(
			_productResourceComponentServiceObjects,
			this::_populateResourceContext,
			productResource ->
				productResource.getProductByExternalReferenceCode(
					externalReferenceCode));
	}

	@GraphQLField
	@GraphQLInvokeDetached
	public Collection<Product> getProductsPage(
			@GraphQLName("filter") Filter filter,
			@GraphQLName("pageSize") int pageSize,
			@GraphQLName("page") int page, @GraphQLName("sorts") Sort[] sorts)
		throws Exception {

		return _applyComponentServiceObjects(
			_productResourceComponentServiceObjects,
			this::_populateResourceContext,
			productResource -> {
				Page paginationPage = productResource.getProductsPage(
					filter, Pagination.of(pageSize, page), sorts);

				return paginationPage.getItems();
			});
	}

	@GraphQLField
	@GraphQLInvokeDetached
	public ProductConfiguration getProductIdConfiguration(
			@GraphQLName("id") Long id)
		throws Exception {

		return _applyComponentServiceObjects(
			_productConfigurationResourceComponentServiceObjects,
			this::_populateResourceContext,
			productConfigurationResource ->
				productConfigurationResource.getProductIdConfiguration(id));
	}

	@GraphQLField
	@GraphQLInvokeDetached
	public ProductConfiguration getProductByExternalReferenceCodeConfiguration(
			@GraphQLName("externalReferenceCode") String externalReferenceCode)
		throws Exception {

		return _applyComponentServiceObjects(
			_productConfigurationResourceComponentServiceObjects,
			this::_populateResourceContext,
			productConfigurationResource ->
				productConfigurationResource.
					getProductByExternalReferenceCodeConfiguration(
						externalReferenceCode));
	}

	@GraphQLField
	@GraphQLInvokeDetached
	public Collection<ProductOption> getProductIdProductOptionsPage(
			@GraphQLName("id") Long id, @GraphQLName("pageSize") int pageSize,
			@GraphQLName("page") int page)
		throws Exception {

		return _applyComponentServiceObjects(
			_productOptionResourceComponentServiceObjects,
			this::_populateResourceContext,
			productOptionResource -> {
				Page paginationPage =
					productOptionResource.getProductIdProductOptionsPage(
						id, Pagination.of(pageSize, page));

				return paginationPage.getItems();
			});
	}

	@GraphQLField
	@GraphQLInvokeDetached
	public Collection<ProductOption>
			getProductByExternalReferenceCodeProductOptionsPage(
				@GraphQLName("externalReferenceCode") String
					externalReferenceCode,
				@GraphQLName("pageSize") int pageSize,
				@GraphQLName("page") int page)
		throws Exception {

		return _applyComponentServiceObjects(
			_productOptionResourceComponentServiceObjects,
			this::_populateResourceContext,
			productOptionResource -> {
				Page paginationPage =
					productOptionResource.
						getProductByExternalReferenceCodeProductOptionsPage(
							externalReferenceCode,
							Pagination.of(pageSize, page));

				return paginationPage.getItems();
			});
	}

	@GraphQLField
	@GraphQLInvokeDetached
	public ProductOption getProductOption(@GraphQLName("id") Long id)
		throws Exception {

		return _applyComponentServiceObjects(
			_productOptionResourceComponentServiceObjects,
			this::_populateResourceContext,
			productOptionResource -> productOptionResource.getProductOption(
				id));
	}

	@GraphQLField
	@GraphQLInvokeDetached
	public Collection<ProductOptionValue>
			getProductOptionIdProductOptionValuesPage(
				@GraphQLName("id") Long id,
				@GraphQLName("pageSize") int pageSize,
				@GraphQLName("page") int page)
		throws Exception {

		return _applyComponentServiceObjects(
			_productOptionValueResourceComponentServiceObjects,
			this::_populateResourceContext,
			productOptionValueResource -> {
				Page paginationPage =
					productOptionValueResource.
						getProductOptionIdProductOptionValuesPage(
							id, Pagination.of(pageSize, page));

				return paginationPage.getItems();
			});
	}

	@GraphQLField
	@GraphQLInvokeDetached
	public ProductShippingConfiguration getProductIdShippingConfiguration(
			@GraphQLName("id") Long id)
		throws Exception {

		return _applyComponentServiceObjects(
			_productShippingConfigurationResourceComponentServiceObjects,
			this::_populateResourceContext,
			productShippingConfigurationResource ->
				productShippingConfigurationResource.
					getProductIdShippingConfiguration(id));
	}

	@GraphQLField
	@GraphQLInvokeDetached
	public ProductShippingConfiguration
			getProductByExternalReferenceCodeShippingConfiguration(
				@GraphQLName("externalReferenceCode") String
					externalReferenceCode)
		throws Exception {

		return _applyComponentServiceObjects(
			_productShippingConfigurationResourceComponentServiceObjects,
			this::_populateResourceContext,
			productShippingConfigurationResource ->
				productShippingConfigurationResource.
					getProductByExternalReferenceCodeShippingConfiguration(
						externalReferenceCode));
	}

	@GraphQLField
	@GraphQLInvokeDetached
	public Collection<ProductSpecification>
			getProductIdProductSpecificationsPage(
				@GraphQLName("id") Long id,
				@GraphQLName("pageSize") int pageSize,
				@GraphQLName("page") int page)
		throws Exception {

		return _applyComponentServiceObjects(
			_productSpecificationResourceComponentServiceObjects,
			this::_populateResourceContext,
			productSpecificationResource -> {
				Page paginationPage =
					productSpecificationResource.
						getProductIdProductSpecificationsPage(
							id, Pagination.of(pageSize, page));

				return paginationPage.getItems();
			});
	}

	@GraphQLField
	@GraphQLInvokeDetached
	public ProductSubscriptionConfiguration
			getProductIdSubscriptionConfiguration(@GraphQLName("id") Long id)
		throws Exception {

		return _applyComponentServiceObjects(
			_productSubscriptionConfigurationResourceComponentServiceObjects,
			this::_populateResourceContext,
			productSubscriptionConfigurationResource ->
				productSubscriptionConfigurationResource.
					getProductIdSubscriptionConfiguration(id));
	}

	@GraphQLField
	@GraphQLInvokeDetached
	public ProductSubscriptionConfiguration
			getProductByExternalReferenceCodeSubscriptionConfiguration(
				@GraphQLName("externalReferenceCode") String
					externalReferenceCode)
		throws Exception {

		return _applyComponentServiceObjects(
			_productSubscriptionConfigurationResourceComponentServiceObjects,
			this::_populateResourceContext,
			productSubscriptionConfigurationResource ->
				productSubscriptionConfigurationResource.
					getProductByExternalReferenceCodeSubscriptionConfiguration(
						externalReferenceCode));
	}

	@GraphQLField
	@GraphQLInvokeDetached
	public ProductTaxConfiguration getProductIdTaxConfiguration(
			@GraphQLName("id") Long id)
		throws Exception {

		return _applyComponentServiceObjects(
			_productTaxConfigurationResourceComponentServiceObjects,
			this::_populateResourceContext,
			productTaxConfigurationResource ->
				productTaxConfigurationResource.getProductIdTaxConfiguration(
					id));
	}

	@GraphQLField
	@GraphQLInvokeDetached
	public ProductTaxConfiguration
			getProductByExternalReferenceCodeTaxConfiguration(
				@GraphQLName("externalReferenceCode") String
					externalReferenceCode)
		throws Exception {

		return _applyComponentServiceObjects(
			_productTaxConfigurationResourceComponentServiceObjects,
			this::_populateResourceContext,
			productTaxConfigurationResource ->
				productTaxConfigurationResource.
					getProductByExternalReferenceCodeTaxConfiguration(
						externalReferenceCode));
	}

	@GraphQLField
	@GraphQLInvokeDetached
	public RelatedProduct getRelatedProduct(@GraphQLName("id") Long id)
		throws Exception {

		return _applyComponentServiceObjects(
			_relatedProductResourceComponentServiceObjects,
			this::_populateResourceContext,
			relatedProductResource -> relatedProductResource.getRelatedProduct(
				id));
	}

	@GraphQLField
	@GraphQLInvokeDetached
	public Collection<RelatedProduct> getProductIdRelatedProductsPage(
			@GraphQLName("id") Long id, @GraphQLName("type") String type,
			@GraphQLName("pageSize") int pageSize,
			@GraphQLName("page") int page)
		throws Exception {

		return _applyComponentServiceObjects(
			_relatedProductResourceComponentServiceObjects,
			this::_populateResourceContext,
			relatedProductResource -> {
				Page paginationPage =
					relatedProductResource.getProductIdRelatedProductsPage(
						id, type, Pagination.of(pageSize, page));

				return paginationPage.getItems();
			});
	}

	@GraphQLField
	@GraphQLInvokeDetached
	public Collection<RelatedProduct>
			getProductByExternalReferenceCodeRelatedProductsPage(
				@GraphQLName("externalReferenceCode") String
					externalReferenceCode,
				@GraphQLName("type") String type,
				@GraphQLName("pageSize") int pageSize,
				@GraphQLName("page") int page)
		throws Exception {

		return _applyComponentServiceObjects(
			_relatedProductResourceComponentServiceObjects,
			this::_populateResourceContext,
			relatedProductResource -> {
				Page paginationPage =
					relatedProductResource.
						getProductByExternalReferenceCodeRelatedProductsPage(
							externalReferenceCode, type,
							Pagination.of(pageSize, page));

				return paginationPage.getItems();
			});
	}

	@GraphQLField
	@GraphQLInvokeDetached
	public Sku getSku(@GraphQLName("id") Long id) throws Exception {
		return _applyComponentServiceObjects(
			_skuResourceComponentServiceObjects, this::_populateResourceContext,
			skuResource -> skuResource.getSku(id));
	}

	@GraphQLField
	@GraphQLInvokeDetached
	public Sku getSkuByExternalReferenceCode(
			@GraphQLName("externalReferenceCode") String externalReferenceCode)
		throws Exception {

		return _applyComponentServiceObjects(
			_skuResourceComponentServiceObjects, this::_populateResourceContext,
			skuResource -> skuResource.getSkuByExternalReferenceCode(
				externalReferenceCode));
	}

	@GraphQLField
	@GraphQLInvokeDetached
	public Collection<Sku> getProductIdSkusPage(
			@GraphQLName("id") Long id, @GraphQLName("pageSize") int pageSize,
			@GraphQLName("page") int page)
		throws Exception {

		return _applyComponentServiceObjects(
			_skuResourceComponentServiceObjects, this::_populateResourceContext,
			skuResource -> {
				Page paginationPage = skuResource.getProductIdSkusPage(
					id, Pagination.of(pageSize, page));

				return paginationPage.getItems();
			});
	}

	@GraphQLField
	@GraphQLInvokeDetached
	public Collection<Sku> getProductByExternalReferenceCodeSkusPage(
			@GraphQLName("externalReferenceCode") String externalReferenceCode,
			@GraphQLName("pageSize") int pageSize,
			@GraphQLName("page") int page)
		throws Exception {

		return _applyComponentServiceObjects(
			_skuResourceComponentServiceObjects, this::_populateResourceContext,
			skuResource -> {
				Page paginationPage =
					skuResource.getProductByExternalReferenceCodeSkusPage(
						externalReferenceCode, Pagination.of(pageSize, page));

				return paginationPage.getItems();
			});
	}

	@GraphQLField
	@GraphQLInvokeDetached
	public Specification getSpecification(@GraphQLName("id") Long id)
		throws Exception {

		return _applyComponentServiceObjects(
			_specificationResourceComponentServiceObjects,
			this::_populateResourceContext,
			specificationResource -> specificationResource.getSpecification(
				id));
	}

	@GraphQLField
	@GraphQLInvokeDetached
	public Collection<Specification> getSpecificationsPage(
			@GraphQLName("filter") Filter filter,
			@GraphQLName("pageSize") int pageSize,
			@GraphQLName("page") int page, @GraphQLName("sorts") Sort[] sorts)
		throws Exception {

		return _applyComponentServiceObjects(
			_specificationResourceComponentServiceObjects,
			this::_populateResourceContext,
			specificationResource -> {
				Page paginationPage =
					specificationResource.getSpecificationsPage(
						filter, Pagination.of(pageSize, page), sorts);

				return paginationPage.getItems();
			});
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