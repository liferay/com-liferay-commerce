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

package com.liferay.headless.commerce.admin.catalog.resource.v1_0;

import com.liferay.headless.commerce.admin.catalog.dto.v1_0.Attachment;
import com.liferay.headless.commerce.admin.catalog.dto.v1_0.Category;
import com.liferay.headless.commerce.admin.catalog.dto.v1_0.Product;
import com.liferay.headless.commerce.admin.catalog.dto.v1_0.ProductConfiguration;
import com.liferay.headless.commerce.admin.catalog.dto.v1_0.ProductOption;
import com.liferay.headless.commerce.admin.catalog.dto.v1_0.ProductShippingConfiguration;
import com.liferay.headless.commerce.admin.catalog.dto.v1_0.ProductSubscriptionConfiguration;
import com.liferay.headless.commerce.admin.catalog.dto.v1_0.ProductTaxConfiguration;
import com.liferay.headless.commerce.admin.catalog.dto.v1_0.Sku;
import com.liferay.portal.kernel.model.Company;
import com.liferay.portal.vulcan.pagination.Page;
import com.liferay.portal.vulcan.pagination.Pagination;

import javax.annotation.Generated;

import javax.ws.rs.core.Response;

/**
 * To access this resource, run:
 *
 *     curl -u your@email.com:yourpassword -D - http://localhost:8080/o/headless-commerce-admin-catalog/v1.0
 *
 * @author Zoltán Takács
 * @generated
 */
@Generated("")
public interface ProductResource {

	public Page<Product> getProducts(Long groupId, Pagination pagination)
		throws Exception;

	public Product upsertProduct(Long groupId, Product product)
		throws Exception;

	public Page<Attachment> getProductAttachments(
			String id, Pagination pagination)
		throws Exception;

	public Attachment upsertProductAttachment(String id, Attachment attachment)
		throws Exception;

	public Page<Category> getProductCategories(String id, Pagination pagination)
		throws Exception;

	public Response updateProductCategory(String id, Category[] categories)
		throws Exception;

	public ProductConfiguration getProductConfiguration(String id)
		throws Exception;

	public Response updateProductConfiguraton(
			String id, ProductConfiguration productConfiguration)
		throws Exception;

	public Page<Attachment> getProductImages(String id, Pagination pagination)
		throws Exception;

	public Attachment upsertProductImage(String id, Attachment attachment)
		throws Exception;

	public Page<ProductOption> getProductOptions(
			String id, Pagination pagination)
		throws Exception;

	public Response updateProductOptions(
			String id, ProductOption[] productOptions)
		throws Exception;

	public ProductShippingConfiguration getProductShippingConfiguration(
			String id)
		throws Exception;

	public Response updateProductShippingConfiguration(
			String id,
			ProductShippingConfiguration productShippingConfiguration)
		throws Exception;

	public Page<Sku> getProductSkus(String id, Pagination pagination)
		throws Exception;

	public Sku upsertProductSku(String id, Sku sku) throws Exception;

	public ProductSubscriptionConfiguration getProductSubscriptionConfiguration(
			String id)
		throws Exception;

	public Response updateProductSubscriptionConfiguration(
			String id,
			ProductSubscriptionConfiguration productSubscriptionConfiguration)
		throws Exception;

	public ProductTaxConfiguration getProductTaxConfiguration(String id)
		throws Exception;

	public Response updateProductTaxConfiguration(
			String id, ProductTaxConfiguration productTaxConfiguration)
		throws Exception;

	public Response deleteProduct(String id) throws Exception;

	public Product getProduct(String id) throws Exception;

	public Response updateProduct(String id, Product product) throws Exception;

	public void setContextCompany(Company contextCompany);

}