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
import com.liferay.commerce.openapi.admin.model.UserDTO;
import com.liferay.commerce.openapi.core.context.Pagination;
import com.liferay.oauth2.provider.scope.RequiresScope;
import com.liferay.portal.kernel.theme.ThemeDisplay;

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
 * @author Igor Beslic
 */
@Generated(value = "OSGiRESTModuleGenerator")
@Path("/1.0/user")
public interface UserResource {

	@DELETE
	@Path("/{id}")
	@RequiresScope("CommerceOpenApiAdmin.write")
	public Response deleteUser(@PathParam("id") String id) throws Exception;

	@GET
	@Path("/{id}")
	@Produces("application/*")
	@RequiresScope("CommerceOpenApiAdmin.read")
	public UserDTO getUser(
			@PathParam("id") String id, @Context ThemeDisplay themeDisplay)
		throws Exception;

	@GET
	@Path("/")
	@Produces("application/*")
	@RequiresScope("CommerceOpenApiAdmin.read")
	public CollectionDTO<UserDTO> getUsers(
			@Context ThemeDisplay themeDisplay, @Context Pagination pagination)
		throws Exception;

	@Consumes("application/*")
	@Path("/{id}")
	@PUT
	@RequiresScope("CommerceOpenApiAdmin.write")
	public Response updateUser(@PathParam("id") String id, UserDTO userDTO)
		throws Exception;

	@Consumes("application/*")
	@Path("/")
	@POST
	@Produces("application/*")
	@RequiresScope("CommerceOpenApiAdmin.write")
	public UserDTO upsertUser(
			UserDTO userDTO, @Context ThemeDisplay themeDisplay)
		throws Exception;

}