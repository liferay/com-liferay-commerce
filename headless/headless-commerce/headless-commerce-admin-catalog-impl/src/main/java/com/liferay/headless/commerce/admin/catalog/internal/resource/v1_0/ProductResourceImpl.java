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

package com.liferay.headless.commerce.admin.catalog.internal.resource.v1_0;

import com.liferay.headless.commerce.admin.catalog.dto.v1_0.Attachment;
import com.liferay.headless.commerce.admin.catalog.dto.v1_0.Category;
import com.liferay.headless.commerce.admin.catalog.dto.v1_0.Product;
import com.liferay.headless.commerce.admin.catalog.dto.v1_0.ProductConfiguration;
import com.liferay.headless.commerce.admin.catalog.dto.v1_0.ProductOption;
import com.liferay.headless.commerce.admin.catalog.dto.v1_0.ProductShippingConfiguration;
import com.liferay.headless.commerce.admin.catalog.dto.v1_0.ProductSubscriptionConfiguration;
import com.liferay.headless.commerce.admin.catalog.dto.v1_0.ProductTaxConfiguration;
import com.liferay.headless.commerce.admin.catalog.dto.v1_0.Sku;
import com.liferay.headless.commerce.admin.catalog.internal.util.v1_0.AttachmentHelper;
import com.liferay.headless.commerce.admin.catalog.internal.util.v1_0.ProductHelper;
import com.liferay.headless.commerce.admin.catalog.internal.util.v1_0.ProductOptionHelper;
import com.liferay.headless.commerce.admin.catalog.internal.util.v1_0.SKUHelper;
import com.liferay.headless.commerce.admin.catalog.resource.v1_0.ProductResource;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.vulcan.pagination.Page;
import com.liferay.portal.vulcan.pagination.Pagination;

import javax.validation.constraints.NotNull;

import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.osgi.service.component.annotations.ServiceScope;

/**
 * @author Zoltán Takács
 */
@Component(
	properties = "OSGI-INF/liferay/rest/v1_0/product.properties",
	scope = ServiceScope.PROTOTYPE, service = ProductResource.class
)
public class ProductResourceImpl extends BaseProductResourceImpl {

	@Override
	public Response deleteProduct(@NotNull String id) throws Exception {
		_productHelper.deleteProduct(id, contextCompany);

		Response.ResponseBuilder responseBuilder = Response.noContent();

		return responseBuilder.build();
	}

	@Override
	public Product getProduct(@NotNull String id) throws Exception {
		return _productHelper.getProduct(
			id, contextCompany, contextAcceptLanguage);
	}

	@Override
	public Page<Attachment> getProductAttachments(
			@NotNull String id, Pagination pagination)
		throws Exception {

		return _attachmentHelper.getAttachments(id, contextCompany, pagination);
	}

	@Override
	public Page<Category> getProductCategories(
			@NotNull String id, Pagination pagination)
		throws Exception {

		return _productHelper.getProductCategories(
			id, contextCompany, pagination);
	}

	@Override
	public ProductConfiguration getProductConfiguration(@NotNull String id)
		throws Exception {

		return _productHelper.getProductConfiguration(id, contextCompany);
	}

	@Override
	public Page<Attachment> getProductImages(
			@NotNull String id, Pagination pagination)
		throws Exception {

		return _attachmentHelper.getImages(id, contextCompany, pagination);
	}

	@Override
	public Page<ProductOption> getProductOptions(
			@NotNull String id, Pagination pagination)
		throws Exception {

		return _productOptionHelper.getProductOptions(
			id, contextCompany, contextAcceptLanguage, pagination);
	}

	@Override
	public Page<Product> getProducts(
			@NotNull Long groupId, Pagination pagination)
		throws Exception {

		return _productHelper.getProducts(
			groupId, contextAcceptLanguage, pagination);
	}

	@Override
	public ProductShippingConfiguration getProductShippingConfiguration(
			@NotNull String id)
		throws Exception {

		return _productHelper.getProductShippingConfiguration(
			id, contextCompany);
	}

	@Override
	public Page<Sku> getProductSkus(@NotNull String id, Pagination pagination)
		throws Exception {

		return _skuHelper.getSKUs(id, contextCompany, pagination);
	}

	@Override
	public ProductSubscriptionConfiguration getProductSubscriptionConfiguration(
			@NotNull String id)
		throws Exception {

		return _productHelper.getProductSubscriptionConfiguration(
			id, contextCompany);
	}

	@Override
	public ProductTaxConfiguration getProductTaxConfiguration(
			@NotNull String id)
		throws Exception {

		return _productHelper.getProductTaxConfiguration(
			id, contextCompany, contextAcceptLanguage);
	}

	@Override
	public Response updateProduct(@NotNull String id, Product product)
		throws Exception {

		_productHelper.updateProduct(
			id, product, contextCompany, contextAcceptLanguage);

		Response.ResponseBuilder responseBuilder = Response.accepted();

		return responseBuilder.build();
	}

	@Override
	public Response updateProductCategory(@NotNull String id, Category category)
		throws Exception {

		// TODO Category should be array in this method signature

		return super.updateProductCategory(id, category);
	}

	@Override
	public Response updateProductConfiguraton(
			@NotNull String id, ProductConfiguration productConfiguration)
		throws Exception {

		_productHelper.updateProductConfiguration(
			id, contextCompany, productConfiguration);

		Response.ResponseBuilder responseBuilder = Response.accepted();

		return responseBuilder.build();
	}

	@Override
	public Response updateProductOptions(
			@NotNull String id, ProductOption productOption)
		throws Exception {

		// TODO Category should be array in this method signature

		return super.updateProductOptions(id, productOption);
	}

	@Override
	public Response updateProductShippingConfiguration(
			@NotNull String id,
			ProductShippingConfiguration productShippingConfiguration)
		throws Exception {

		_productHelper.updateProductShippingConfiguration(
			id, contextCompany, productShippingConfiguration);

		Response.ResponseBuilder responseBuilder = Response.accepted();

		return responseBuilder.build();
	}

	@Override
	public Response updateProductSubscriptionConfiguration(
			@NotNull String id,
			ProductSubscriptionConfiguration productSubscriptionConfiguration)
		throws Exception {

		_productHelper.updateProductSubscriptionConfiguration(
			id, contextCompany, productSubscriptionConfiguration);

		Response.ResponseBuilder responseBuilder = Response.accepted();

		return responseBuilder.build();
	}

	@Override
	public Response updateProductTaxConfiguration(
			@NotNull String id, ProductTaxConfiguration productTaxConfiguration)
		throws Exception {

		_productHelper.updateProductTaxConfiguration(
			id, contextCompany, productTaxConfiguration, contextAcceptLanguage);

		Response.ResponseBuilder responseBuilder = Response.accepted();

		return responseBuilder.build();
	}

	@Override
	public Product upsertProduct(@NotNull Long groupId, Product product)
		throws Exception {

		return _productHelper.upsertProduct(
			groupId, product, contextAcceptLanguage, _user);
	}

	@Override
	public Attachment upsertProductAttachment(
			@NotNull String id, Attachment attachment)
		throws Exception {

		return _attachmentHelper.upsertAttachment(
			id, attachment, contextCompany);
	}

	@Override
	public Attachment upsertProductImage(
			@NotNull String id, Attachment attachment)
		throws Exception {

		return _attachmentHelper.upsertImage(id, attachment, contextCompany);
	}

	@Override
	public Sku upsertProductSku(@NotNull String id, Sku sku) throws Exception {
		return _skuHelper.upsertSKU(id, sku, contextCompany);
	}

	@Reference
	private AttachmentHelper _attachmentHelper;

	@Reference
	private ProductHelper _productHelper;

	@Reference
	private ProductOptionHelper _productOptionHelper;

	@Reference
	private SKUHelper _skuHelper;

	@Context
	private User _user;

}