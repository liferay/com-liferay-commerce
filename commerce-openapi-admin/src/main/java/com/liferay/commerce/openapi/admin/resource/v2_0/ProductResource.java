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

package com.liferay.commerce.openapi.admin.resource.v2_0;

import com.liferay.commerce.openapi.admin.model.v2_0.ProductDTO;
import com.liferay.commerce.openapi.admin.model.v2_0.SkuDTO;
import com.liferay.commerce.openapi.core.context.Language;
import com.liferay.commerce.openapi.core.context.Pagination;
import com.liferay.commerce.openapi.core.model.CollectionDTO;

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
@Path("/v2.0/product")
public interface ProductResource {

	@DELETE
	@Path("/{id}")
	public Response deleteProduct(@PathParam("id") String id) throws Exception;

	@GET
	@Path("/{id}")
	@Produces({"application/json", "application/xml"})
	public ProductDTO getProduct(@PathParam("id") String id) throws Exception;

	@GET
	@Path("/")
	@Produces({"application/json", "application/xml"})
	public CollectionDTO<ProductDTO> getProducts(
			@QueryParam("groupId") Long groupId, @Context Pagination pagination)
		throws Exception;

	@GET
	@Path("/{id}/sku")
	@Produces({"application/json", "application/xml"})
	public CollectionDTO<SkuDTO> getSkus(
			@PathParam("id") String id, @Context Pagination pagination)
		throws Exception;

	@Consumes({"application/json", "application/xml"})
	@Path("/{id}")
	@PUT
	public Response updateProduct(
			@PathParam("id") String id, @QueryParam("groupId") Long groupId,
			ProductDTO productDTO, @Context Language language)
		throws Exception;

	@Consumes({"application/json", "application/xml"})
	@Path("/")
	@POST
	@Produces({"application/json", "application/xml"})
	public ProductDTO upsertProduct(
			@QueryParam("groupId") Long groupId, ProductDTO productDTO,
			@Context Language language)
		throws Exception;

	@Consumes({"application/json", "application/xml"})
	@Path("/{id}/sku")
	@POST
	@Produces({"application/json", "application/xml"})
	public SkuDTO upsertSku(
			@PathParam("id") String id, @QueryParam("groupId") Long groupId,
			SkuDTO skuDTO, @Context Language language)
		throws Exception;

}