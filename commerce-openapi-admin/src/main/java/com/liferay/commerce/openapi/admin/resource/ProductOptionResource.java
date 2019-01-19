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

import com.liferay.commerce.openapi.admin.model.CollectionDTO;
import com.liferay.commerce.openapi.admin.model.ProductOptionDTO;
import com.liferay.commerce.openapi.admin.model.ProductOptionValueDTO;
import com.liferay.commerce.openapi.core.context.Pagination;
import com.liferay.oauth2.provider.scope.RequiresScope;
import com.liferay.portal.kernel.model.Company;
import com.liferay.portal.kernel.model.User;

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
@Path("/1.0/productOption")
public interface ProductOptionResource {

	@DELETE
	@Path("/{id}")
	@RequiresScope("CommerceOpenApiAdmin.write")
	public Response deleteProductOption(
		@PathParam("id") String id, @QueryParam("groupId") long groupId,
		@Context User user, @Context Locale locale, @Context Company company);

	@GET
	@Path("/{id}")
	@Produces("application/*")
	@RequiresScope("CommerceOpenApiAdmin.read")
	public ProductOptionDTO getProductOption(
		@PathParam("id") String id, @QueryParam("groupId") long groupId,
		@Context User user, @Context Locale locale, @Context Company company);

	@GET
	@Path("/")
	@Produces("application/*")
	@RequiresScope("CommerceOpenApiAdmin.read")
	public CollectionDTO<ProductOptionDTO> getProductOptions(
		@QueryParam("groupId") long groupId, @Context User user,
		@Context Locale locale, @Context Company company,
		@Context Pagination pagination);

	@GET
	@Path("/{id}/productOptionValue")
	@Produces("application/*")
	@RequiresScope("CommerceOpenApiAdmin.read")
	public CollectionDTO<ProductOptionValueDTO> getProductOptionValues(
		@PathParam("id") String id, @QueryParam("groupId") long groupId,
		@Context User user, @Context Locale locale, @Context Company company,
		@Context Pagination pagination);

	@Consumes("application/*")
	@Path("/{id}")
	@PUT
	@RequiresScope("CommerceOpenApiAdmin.write")
	public Response updateProductOption(
		@PathParam("id") String id, @QueryParam("groupId") long groupId,
		ProductOptionDTO productOptionDTO, @Context User user,
		@Context Locale locale, @Context Company company);

	@Consumes("application/*")
	@Path("/")
	@POST
	@Produces("application/*")
	@RequiresScope("CommerceOpenApiAdmin.write")
	public ProductOptionDTO upsertProductOption(
		@QueryParam("groupId") long groupId, ProductOptionDTO productOptionDTO,
		@Context User user, @Context Locale locale, @Context Company company);

	@Consumes("application/*")
	@Path("/{id}/productOptionValue")
	@POST
	@Produces("application/*")
	@RequiresScope("CommerceOpenApiAdmin.write")
	public ProductOptionValueDTO upsertProductOptionValue(
		@PathParam("id") String id, @QueryParam("groupId") long groupId,
		ProductOptionValueDTO productOptionValueDTO, @Context User user,
		@Context Locale locale, @Context Company company);

}