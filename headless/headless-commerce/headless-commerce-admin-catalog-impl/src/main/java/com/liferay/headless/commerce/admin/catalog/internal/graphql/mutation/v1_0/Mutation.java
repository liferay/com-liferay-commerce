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
import com.liferay.headless.commerce.admin.catalog.resource.v1_0.AttachmentResource;
import com.liferay.headless.commerce.admin.catalog.resource.v1_0.OptionCategoryResource;
import com.liferay.headless.commerce.admin.catalog.resource.v1_0.ProductOptionResource;
import com.liferay.headless.commerce.admin.catalog.resource.v1_0.ProductResource;
import com.liferay.headless.commerce.admin.catalog.resource.v1_0.SkuResource;
import com.liferay.headless.commerce.admin.catalog.resource.v1_0.SpecificationResource;
import com.liferay.petra.function.UnsafeConsumer;
import com.liferay.petra.function.UnsafeFunction;
import com.liferay.portal.kernel.security.auth.CompanyThreadLocal;
import com.liferay.portal.kernel.service.CompanyLocalServiceUtil;

import graphql.annotations.annotationTypes.GraphQLField;
import graphql.annotations.annotationTypes.GraphQLInvokeDetached;
import graphql.annotations.annotationTypes.GraphQLName;

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

	public static void setOptionCategoryResourceComponentServiceObjects(
		ComponentServiceObjects<OptionCategoryResource>
			optionCategoryResourceComponentServiceObjects) {

		_optionCategoryResourceComponentServiceObjects =
			optionCategoryResourceComponentServiceObjects;
	}

	public static void setProductResourceComponentServiceObjects(
		ComponentServiceObjects<ProductResource>
			productResourceComponentServiceObjects) {

		_productResourceComponentServiceObjects =
			productResourceComponentServiceObjects;
	}

	public static void setProductOptionResourceComponentServiceObjects(
		ComponentServiceObjects<ProductOptionResource>
			productOptionResourceComponentServiceObjects) {

		_productOptionResourceComponentServiceObjects =
			productOptionResourceComponentServiceObjects;
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

	@GraphQLInvokeDetached
	public Response deleteAttachment(@GraphQLName("id") String id)
		throws Exception {

		return _applyComponentServiceObjects(
			_attachmentResourceComponentServiceObjects,
			this::_populateResourceContext,
			attachmentResource -> attachmentResource.deleteAttachment(id));
	}

	@GraphQLInvokeDetached
	public Response updateAttachment(
			@GraphQLName("id") String id,
			@GraphQLName("Attachment") Attachment attachment)
		throws Exception {

		return _applyComponentServiceObjects(
			_attachmentResourceComponentServiceObjects,
			this::_populateResourceContext,
			attachmentResource -> attachmentResource.updateAttachment(
				id, attachment));
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
	public Response updateOptionCategory(
			@GraphQLName("id") Long id,
			@GraphQLName("OptionCategory") OptionCategory optionCategory)
		throws Exception {

		return _applyComponentServiceObjects(
			_optionCategoryResourceComponentServiceObjects,
			this::_populateResourceContext,
			optionCategoryResource ->
				optionCategoryResource.updateOptionCategory(
					id, optionCategory));
	}

	@GraphQLField
	@GraphQLInvokeDetached
	public OptionCategory upsertOptionCategory(
			@GraphQLName("groupId") Long groupId,
			@GraphQLName("OptionCategory") OptionCategory optionCategory)
		throws Exception {

		return _applyComponentServiceObjects(
			_optionCategoryResourceComponentServiceObjects,
			this::_populateResourceContext,
			optionCategoryResource ->
				optionCategoryResource.upsertOptionCategory(
					groupId, optionCategory));
	}

	@GraphQLInvokeDetached
	public Response deleteProduct(@GraphQLName("id") String id)
		throws Exception {

		return _applyComponentServiceObjects(
			_productResourceComponentServiceObjects,
			this::_populateResourceContext,
			productResource -> productResource.deleteProduct(id));
	}

	@GraphQLInvokeDetached
	public Response updateProduct(
			@GraphQLName("id") String id,
			@GraphQLName("Product") Product product)
		throws Exception {

		return _applyComponentServiceObjects(
			_productResourceComponentServiceObjects,
			this::_populateResourceContext,
			productResource -> productResource.updateProduct(id, product));
	}

	@GraphQLInvokeDetached
	public Response updateProductOptions(
			@GraphQLName("id") String id,
			@GraphQLName("ProductOption") ProductOption[] productOptions)
		throws Exception {

		return _applyComponentServiceObjects(
			_productResourceComponentServiceObjects,
			this::_populateResourceContext,
			productResource -> productResource.updateProductOptions(
				id, productOptions));
	}

	@GraphQLInvokeDetached
	public Response updateProductConfiguraton(
			@GraphQLName("id") String id,
			@GraphQLName("ProductConfiguration") ProductConfiguration
				productConfiguration)
		throws Exception {

		return _applyComponentServiceObjects(
			_productResourceComponentServiceObjects,
			this::_populateResourceContext,
			productResource -> productResource.updateProductConfiguraton(
				id, productConfiguration));
	}

	@GraphQLInvokeDetached
	public Response updateProductShippingConfiguration(
			@GraphQLName("id") String id,
			@GraphQLName("ProductShippingConfiguration")
				ProductShippingConfiguration productShippingConfiguration)
		throws Exception {

		return _applyComponentServiceObjects(
			_productResourceComponentServiceObjects,
			this::_populateResourceContext,
			productResource ->
				productResource.updateProductShippingConfiguration(
					id, productShippingConfiguration));
	}

	@GraphQLInvokeDetached
	public Response updateProductTaxConfiguration(
			@GraphQLName("id") String id,
			@GraphQLName("ProductTaxConfiguration") ProductTaxConfiguration
				productTaxConfiguration)
		throws Exception {

		return _applyComponentServiceObjects(
			_productResourceComponentServiceObjects,
			this::_populateResourceContext,
			productResource -> productResource.updateProductTaxConfiguration(
				id, productTaxConfiguration));
	}

	@GraphQLInvokeDetached
	public Response updateProductSubscriptionConfiguration(
			@GraphQLName("id") String id,
			@GraphQLName("ProductSubscriptionConfiguration")
				ProductSubscriptionConfiguration
					productSubscriptionConfiguration)
		throws Exception {

		return _applyComponentServiceObjects(
			_productResourceComponentServiceObjects,
			this::_populateResourceContext,
			productResource ->
				productResource.updateProductSubscriptionConfiguration(
					id, productSubscriptionConfiguration));
	}

	@GraphQLField
	@GraphQLInvokeDetached
	public Product upsertProduct(
			@GraphQLName("groupId") Long groupId,
			@GraphQLName("Product") Product product)
		throws Exception {

		return _applyComponentServiceObjects(
			_productResourceComponentServiceObjects,
			this::_populateResourceContext,
			productResource -> productResource.upsertProduct(groupId, product));
	}

	@GraphQLField
	@GraphQLInvokeDetached
	public Attachment upsertProductAttachment(
			@GraphQLName("id") String id,
			@GraphQLName("Attachment") Attachment attachment)
		throws Exception {

		return _applyComponentServiceObjects(
			_productResourceComponentServiceObjects,
			this::_populateResourceContext,
			productResource -> productResource.upsertProductAttachment(
				id, attachment));
	}

	@GraphQLField
	@GraphQLInvokeDetached
	public Attachment upsertProductImage(
			@GraphQLName("id") String id,
			@GraphQLName("Attachment") Attachment attachment)
		throws Exception {

		return _applyComponentServiceObjects(
			_productResourceComponentServiceObjects,
			this::_populateResourceContext,
			productResource -> productResource.upsertProductImage(
				id, attachment));
	}

	@GraphQLInvokeDetached
	public Response updateProductCategory(
			@GraphQLName("id") String id,
			@GraphQLName("Category") Category[] categories)
		throws Exception {

		return _applyComponentServiceObjects(
			_productResourceComponentServiceObjects,
			this::_populateResourceContext,
			productResource -> productResource.updateProductCategory(
				id, categories));
	}

	@GraphQLField
	@GraphQLInvokeDetached
	public Sku upsertProductSku(
			@GraphQLName("id") String id, @GraphQLName("Sku") Sku sku)
		throws Exception {

		return _applyComponentServiceObjects(
			_productResourceComponentServiceObjects,
			this::_populateResourceContext,
			productResource -> productResource.upsertProductSku(id, sku));
	}

	@GraphQLInvokeDetached
	public Response deleteProductOption(@GraphQLName("id") String id)
		throws Exception {

		return _applyComponentServiceObjects(
			_productOptionResourceComponentServiceObjects,
			this::_populateResourceContext,
			productOptionResource -> productOptionResource.deleteProductOption(
				id));
	}

	@GraphQLInvokeDetached
	public Response updateProductOption(
			@GraphQLName("id") String id,
			@GraphQLName("ProductOption") ProductOption productOption)
		throws Exception {

		return _applyComponentServiceObjects(
			_productOptionResourceComponentServiceObjects,
			this::_populateResourceContext,
			productOptionResource -> productOptionResource.updateProductOption(
				id, productOption));
	}

	@GraphQLField
	@GraphQLInvokeDetached
	public ProductOption upsertProductOption(
			@GraphQLName("groupId") Long groupId,
			@GraphQLName("ProductOption") ProductOption productOption)
		throws Exception {

		return _applyComponentServiceObjects(
			_productOptionResourceComponentServiceObjects,
			this::_populateResourceContext,
			productOptionResource -> productOptionResource.upsertProductOption(
				groupId, productOption));
	}

	@GraphQLField
	@GraphQLInvokeDetached
	public ProductOptionValue upsertProductOptionValue(
			@GraphQLName("id") String id,
			@GraphQLName("ProductOptionValue") ProductOptionValue
				productOptionValue)
		throws Exception {

		return _applyComponentServiceObjects(
			_productOptionResourceComponentServiceObjects,
			this::_populateResourceContext,
			productOptionResource ->
				productOptionResource.upsertProductOptionValue(
					id, productOptionValue));
	}

	@GraphQLInvokeDetached
	public Response deleteSku(@GraphQLName("id") String id) throws Exception {
		return _applyComponentServiceObjects(
			_skuResourceComponentServiceObjects, this::_populateResourceContext,
			skuResource -> skuResource.deleteSku(id));
	}

	@GraphQLInvokeDetached
	public Response updateSku(
			@GraphQLName("id") String id, @GraphQLName("Sku") Sku sku)
		throws Exception {

		return _applyComponentServiceObjects(
			_skuResourceComponentServiceObjects, this::_populateResourceContext,
			skuResource -> skuResource.updateSku(id, sku));
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
	public Response updateSpecification(
			@GraphQLName("id") Long id,
			@GraphQLName("Specification") Specification specification)
		throws Exception {

		return _applyComponentServiceObjects(
			_specificationResourceComponentServiceObjects,
			this::_populateResourceContext,
			specificationResource -> specificationResource.updateSpecification(
				id, specification));
	}

	@GraphQLField
	@GraphQLInvokeDetached
	public Specification upsertSpecification(
			@GraphQLName("groupId") Long groupId,
			@GraphQLName("Specification") Specification specification)
		throws Exception {

		return _applyComponentServiceObjects(
			_specificationResourceComponentServiceObjects,
			this::_populateResourceContext,
			specificationResource -> specificationResource.upsertSpecification(
				groupId, specification));
	}

	@GraphQLField
	@GraphQLInvokeDetached
	public SpecificationValue upsertSpecificationValue(
			@GraphQLName("id") Long id,
			@GraphQLName("SpecificationValue") SpecificationValue
				specificationValue)
		throws Exception {

		return _applyComponentServiceObjects(
			_specificationResourceComponentServiceObjects,
			this::_populateResourceContext,
			specificationResource ->
				specificationResource.upsertSpecificationValue(
					id, specificationValue));
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

	private void _populateResourceContext(
			OptionCategoryResource optionCategoryResource)
		throws Exception {

		optionCategoryResource.setContextCompany(
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
			ProductOptionResource productOptionResource)
		throws Exception {

		productOptionResource.setContextCompany(
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
	private static ComponentServiceObjects<OptionCategoryResource>
		_optionCategoryResourceComponentServiceObjects;
	private static ComponentServiceObjects<ProductResource>
		_productResourceComponentServiceObjects;
	private static ComponentServiceObjects<ProductOptionResource>
		_productOptionResourceComponentServiceObjects;
	private static ComponentServiceObjects<SkuResource>
		_skuResourceComponentServiceObjects;
	private static ComponentServiceObjects<SpecificationResource>
		_specificationResourceComponentServiceObjects;

}