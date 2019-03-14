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

import com.liferay.headless.commerce.admin.catalog.model.v1_0.SkuDTO;

import javax.annotation.Generated;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

/**
 * @author Alessio Antonio Rendina
 */
@Generated(value = "OSGiRESTModuleGenerator")
@Path("/v1.0/sku")
public interface SkuResource {

	@DELETE
	@Path("/{id}")
	public Response deleteSku(@PathParam("id") String id) throws Exception;

	@GET
	@Path("/{id}")
	@Produces({"application/json", "application/xml"})
	public SkuDTO getSku(@PathParam("id") String id) throws Exception;

	@Consumes({"application/json", "application/xml"})
	@Path("/{id}")
	@PUT
	public Response updateSku(@PathParam("id") String id, SkuDTO skuDTO)
		throws Exception;

}