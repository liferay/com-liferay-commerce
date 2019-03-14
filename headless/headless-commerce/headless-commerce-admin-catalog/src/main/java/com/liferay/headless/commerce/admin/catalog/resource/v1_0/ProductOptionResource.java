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
import com.liferay.headless.commerce.admin.catalog.model.v1_0.ProductOptionDTO;
import com.liferay.headless.commerce.admin.catalog.model.v1_0.ProductOptionValueDTO;

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
@Path("/v1.0/productOption")
public interface ProductOptionResource {

	@DELETE
	@Path("/{id}")
	public Response deleteProductOption(@PathParam("id") String id)
		throws Exception;

	@GET
	@Path("/{id}")
	@Produces({"application/json", "application/xml"})
	public ProductOptionDTO getProductOption(
			@PathParam("id") String id, @Context Language language)
		throws Exception;

	@GET
	@Path("/{id}/productOptionValue/")
	@Produces({"application/json", "application/xml"})
	public CollectionDTO<ProductOptionValueDTO> getProductOptionValues(
			@PathParam("id") String id, @Context Language language,
			@Context Pagination pagination)
		throws Exception;

	@Consumes({"application/json", "application/xml"})
	@Path("/{id}")
	@PUT
	public Response updateProductOption(
			@PathParam("id") String id, ProductOptionDTO productOptionDTO,
			@Context Language language)
		throws Exception;

	@Consumes({"application/json", "application/xml"})
	@Path("/{id}/productOptionValue/")
	@POST
	@Produces({"application/json", "application/xml"})
	public ProductOptionValueDTO upsertProductOptionValue(
			@PathParam("id") String id,
			ProductOptionValueDTO productOptionValueDTO,
			@Context Language language)
		throws Exception;

}