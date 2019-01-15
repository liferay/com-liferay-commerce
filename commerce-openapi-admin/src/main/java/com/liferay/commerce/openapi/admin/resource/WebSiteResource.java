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

import com.liferay.commerce.openapi.admin.context.ClientHelper;
import com.liferay.commerce.openapi.admin.context.PortalHelper;
import com.liferay.commerce.openapi.admin.model.WebSiteDTO;

import java.util.List;

import javax.annotation.Generated;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;

/**
 * @author Igor Beslic
 */
@Generated(value = "OSGiRESTModuleGenerator")
@Path("/1.0/webSite")
public interface WebSiteResource {

	@GET
	@Path("/{id}")
	@Produces({"application/*", "application/json"})
	public WebSiteDTO getWebSite(
		@PathParam("id") String id, @Context PortalHelper portalHelper);

	@GET
	@Path("/")
	@Produces({"application/*", "application/json"})
	public List<WebSiteDTO> getWebSites(
		@Context PortalHelper portalHelper, @Context ClientHelper clientHelper);

}