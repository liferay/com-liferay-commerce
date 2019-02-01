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

import com.liferay.commerce.openapi.admin.model.ProductOptionValueDTO;
import com.liferay.oauth2.provider.scope.RequiresScope;

import java.util.Locale;

import javax.annotation.Generated;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
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
@Path("/v1.0/productOptionValue")
public interface ProductOptionValueResource {

	@DELETE
	@Path("/{id}")
	@RequiresScope("CommerceOpenApiAdmin.write")
	public Response deleteProductOptionValue(
			@PathParam("id") String id, @Context Locale locale)
		throws Exception;

	@GET
	@Path("/{id}")
	@Produces("application/*")
	@RequiresScope("CommerceOpenApiAdmin.read")
	public ProductOptionValueDTO getProductOptionValue(
			@PathParam("id") String id, @Context Locale locale)
		throws Exception;

	@Consumes("application/*")
	@Path("/{id}")
	@PUT
	@RequiresScope("CommerceOpenApiAdmin.write")
	public Response updateProductOptionValue(
			@PathParam("id") String id, @QueryParam("groupId") Long groupId,
			ProductOptionValueDTO productOptionValueDTO, @Context Locale locale)
		throws Exception;

}