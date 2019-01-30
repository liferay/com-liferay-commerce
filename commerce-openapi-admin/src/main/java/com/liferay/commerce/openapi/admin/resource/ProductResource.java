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

package com.liferay.commerce.openapi.admin.resource;

import com.liferay.commerce.openapi.admin.model.ProductDTO;
import com.liferay.commerce.openapi.admin.model.SkuDTO;
import com.liferay.commerce.openapi.core.context.Pagination;
import com.liferay.commerce.openapi.core.model.CollectionDTO;

import java.util.Locale;

import javax.annotation.Generated;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;

/**
 * @author Igor Beslic
 */
@Generated(value = "OSGiRESTModuleGenerator")
@Path("/v1.0/product")
public interface ProductResource {

	@DELETE
	@Path("/{id}")
	public Response deleteProduct(@PathParam("id") String id) throws Exception;

	@GET
	@Path("/{id}")
	@Produces("application/*")
	public ProductDTO getProduct(@PathParam("id") String id) throws Exception;

	@GET
	@Path("/")
	@Produces("application/*")
	public CollectionDTO<ProductDTO> getProducts(
			@QueryParam("groupId") Long groupId, @Context Pagination pagination)
		throws Exception;

	@GET
	@Path("/{id}/sku")
	@Produces("application/*")
	public CollectionDTO<SkuDTO> getSkus(
			@PathParam("id") String id, @Context Pagination pagination)
		throws Exception;

	@Consumes("application/*")
	@Path("/{id}")
	@PUT
	public Response updateProduct(
			@PathParam("id") String id, @QueryParam("groupId") Long groupId,
			ProductDTO productDTO, @Context Locale locale)
		throws Exception;

	@Consumes("application/*")
	@Path("/")
	@POST
	@Produces("application/*")
	public ProductDTO upsertProduct(
			@QueryParam("groupId") Long groupId, ProductDTO productDTO,
			@Context Locale locale)
		throws Exception;

	@Consumes("application/*")
	@Path("/{id}/sku")
	@POST
	@Produces("application/*")
	public SkuDTO upsertSku(
			@PathParam("id") String id, @QueryParam("groupId") Long groupId,
			SkuDTO skuDTO, @Context Locale locale)
		throws Exception;

}