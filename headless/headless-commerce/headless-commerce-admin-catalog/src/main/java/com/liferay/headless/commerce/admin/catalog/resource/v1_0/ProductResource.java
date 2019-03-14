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

import com.liferay.commerce.openapi.core.context.Language;
import com.liferay.commerce.openapi.core.context.Pagination;
import com.liferay.commerce.openapi.core.model.CollectionDTO;
import com.liferay.headless.commerce.admin.catalog.model.v1_0.AttachmentDTO;
import com.liferay.headless.commerce.admin.catalog.model.v1_0.CategoryDTO;
import com.liferay.headless.commerce.admin.catalog.model.v1_0.ProductConfigurationDTO;
import com.liferay.headless.commerce.admin.catalog.model.v1_0.ProductDTO;
import com.liferay.headless.commerce.admin.catalog.model.v1_0.ProductOptionDTO;
import com.liferay.headless.commerce.admin.catalog.model.v1_0.ProductShippingConfigurationDTO;
import com.liferay.headless.commerce.admin.catalog.model.v1_0.ProductSubscriptionConfigurationDTO;
import com.liferay.headless.commerce.admin.catalog.model.v1_0.ProductTaxConfigurationDTO;
import com.liferay.headless.commerce.admin.catalog.model.v1_0.SkuDTO;

import javax.annotation.Generated;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;

/**
 * @author Alessio Antonio Rendina
 */
@Generated(value = "OSGiRESTModuleGenerator")
@Path("/v1.0/product")
public interface ProductResource {

	@DELETE
	@Path("/{id}")
	public Response deleteProduct(@PathParam("id") String id) throws Exception;

	@GET
	@Path("/{id}")
	@Produces({"application/json", "application/xml"})
	public ProductDTO getProduct(
			@PathParam("id") String id, @Context Language language)
		throws Exception;

	@GET
	@Path("/{id}/attachment/")
	@Produces({"application/json", "application/xml"})
	public CollectionDTO<AttachmentDTO> getProductAttachments(
			@PathParam("id") String id, @Context Pagination pagination)
		throws Exception;

	@GET
	@Path("/{id}/category/")
	@Produces({"application/json", "application/xml"})
	public CollectionDTO<CategoryDTO> getProductCategories(
			@PathParam("id") String id, @Context Pagination pagination)
		throws Exception;

	@GET
	@Path("/{id}/configuration/")
	@Produces({"application/json", "application/xml"})
	public ProductConfigurationDTO getProductConfiguration(
			@PathParam("id") String id)
		throws Exception;

	@GET
	@Path("/{id}/image/")
	@Produces({"application/json", "application/xml"})
	public CollectionDTO<AttachmentDTO> getProductImages(
			@PathParam("id") String id, @Context Pagination pagination)
		throws Exception;

	@GET
	@Path("/{id}/productOption/")
	@Produces({"application/json", "application/xml"})
	public CollectionDTO<ProductOptionDTO> getProductOptions(
			@PathParam("id") String id, @Context Language language,
			@Context Pagination pagination)
		throws Exception;

	@GET
	@Path("/{id}/shippingConfiguration/")
	@Produces({"application/json", "application/xml"})
	public ProductShippingConfigurationDTO getProductShippingConfiguration(
			@PathParam("id") String id)
		throws Exception;

	@GET
	@Path("/{id}/sku/")
	@Produces({"application/json", "application/xml"})
	public CollectionDTO<SkuDTO> getProductSkus(
			@PathParam("id") String id, @Context Pagination pagination)
		throws Exception;

	@GET
	@Path("/{id}/subscriptionConfiguration")
	@Produces({"application/json", "application/xml"})
	public ProductSubscriptionConfigurationDTO
			getProductSubscriptionConfiguration(@PathParam("id") String id)
		throws Exception;

	@GET
	@Path("/{id}/taxConfiguration")
	@Produces({"application/json", "application/xml"})
	public ProductTaxConfigurationDTO getProductTaxConfiguration(
			@PathParam("id") String id, @Context Language language)
		throws Exception;

	@Consumes({"application/json", "application/xml"})
	@Path("/{id}")
	@PUT
	public Response updateProduct(
			@PathParam("id") String id, ProductDTO productDTO,
			@Context Language language)
		throws Exception;

	@Consumes("application/json")
	@Path("/{id}/category/")
	@PUT
	public Response updateProductCategory(
			@PathParam("id") String id, CategoryDTO[] categoryDTO,
			@Context Language language)
		throws Exception;

	@Consumes("application/json")
	@Path("/{id}/configuration/")
	@PUT
	public Response updateProductConfiguraton(
			@PathParam("id") String id,
			ProductConfigurationDTO productConfigurationDTO)
		throws Exception;

	@Consumes("application/json")
	@Path("/{id}/productOption/")
	@PUT
	public Response updateProductOptions(
			@PathParam("id") String id, ProductOptionDTO[] productOptionDTO,
			@Context Language language)
		throws Exception;

	@Consumes("application/json")
	@Path("/{id}/shippingConfiguration/")
	@PUT
	public Response updateProductShippingConfiguration(
			@PathParam("id") String id,
			ProductShippingConfigurationDTO productShippingConfigurationDTO)
		throws Exception;

	@Consumes("application/json")
	@Path("/{id}/subscriptionConfiguration")
	@PUT
	public Response updateProductSubscriptionConfiguration(
			@PathParam("id") String id,
			ProductSubscriptionConfigurationDTO
				productSubscriptionConfigurationDTO)
		throws Exception;

	@Consumes("application/json")
	@Path("/{id}/taxConfiguration")
	@PUT
	public Response updateProductTaxConfiguration(
			@PathParam("id") String id,
			ProductTaxConfigurationDTO productTaxConfigurationDTO,
			@Context Language language)
		throws Exception;

	@Consumes({"application/json", "application/xml"})
	@Path("/{id}/attachment/")
	@POST
	@Produces({"application/json", "application/xml"})
	public AttachmentDTO upsertProductAttachment(
			@PathParam("id") String id, AttachmentDTO attachmentDTO)
		throws Exception;

	@Consumes({"application/json", "application/xml"})
	@Path("/{id}/image/")
	@POST
	@Produces({"application/json", "application/xml"})
	public AttachmentDTO upsertProductImage(
			@PathParam("id") String id, AttachmentDTO attachmentDTO)
		throws Exception;

	@Consumes({"application/json", "application/xml"})
	@Path("/{id}/sku/")
	@POST
	@Produces({"application/json", "application/xml"})
	public SkuDTO upsertProductSku(@PathParam("id") String id, SkuDTO skuDTO)
		throws Exception;

}