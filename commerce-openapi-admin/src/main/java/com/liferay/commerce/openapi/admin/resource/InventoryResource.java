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
import com.liferay.commerce.openapi.admin.model.InventoryDTO;
import com.liferay.commerce.openapi.core.context.Pagination;
import com.liferay.oauth2.provider.scope.RequiresScope;
import com.liferay.portal.kernel.model.Company;
import com.liferay.portal.kernel.model.User;

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
@Path("/1.0/inventory")
public interface InventoryResource {

	@DELETE
	@Path("/{id}")
	@RequiresScope("CommerceOpenApiAdmin.write")
	public Response deleteInventory(
		@PathParam("id") String id, @QueryParam("groupId") long groupId,
		@Context User user, @Context Locale locale, @Context Company company);

	@GET
	@Path("/{id}")
	@Produces("application/*")
	@RequiresScope("CommerceOpenApiAdmin.read")
	public InventoryDTO getInventory(
		@PathParam("id") String id, @QueryParam("groupId") long groupId,
		@Context User user, @Context Locale locale, @Context Company company);

	@GET
	@Path("/")
	@Produces("application/*")
	@RequiresScope("CommerceOpenApiAdmin.read")
	public CollectionDTO<InventoryDTO> getInventorys(
		@Context User user, @Context Locale locale, @Context Company company,
		@Context Pagination pagination);

	@Consumes("application/*")
	@Path("/{id}")
	@PUT
	@RequiresScope("CommerceOpenApiAdmin.write")
	public Response updateInventory(
		@PathParam("id") String id, @QueryParam("groupId") long groupId,
		InventoryDTO inventoryDTO, @Context User user, @Context Locale locale,
		@Context Company company);

}