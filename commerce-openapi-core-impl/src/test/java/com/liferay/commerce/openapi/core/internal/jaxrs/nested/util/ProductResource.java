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

package com.liferay.commerce.openapi.core.internal.jaxrs.nested.util;

import com.liferay.commerce.openapi.core.context.Pagination;
import com.liferay.commerce.openapi.core.model.CollectionDTO;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;

/**
 * @author Ivica Cardic
 */
@Path("/v1.0/product")
public interface ProductResource {

	@GET
	@Path("/{id}/productOption")
	@Produces("application/*")
	public List<ProductOptionDTO> getProductOptions(
		@PathParam("id") Long id, @QueryParam("name") String name,
		@Context Pagination pagination);

	@GET
	@Path("/{id}/sku")
	@Produces("application/*")
	public CollectionDTO<SkuDTO> getSkus(
		@PathParam("id") String id, @Context Pagination pagination);

}