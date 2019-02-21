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

import com.liferay.commerce.openapi.admin.model.v2_0.WebSiteDTO;
import com.liferay.commerce.openapi.core.context.Language;
import com.liferay.commerce.openapi.core.context.Pagination;
import com.liferay.commerce.openapi.core.model.CollectionDTO;

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
@Path("/v2.0/webSite")
public interface WebSiteResource {

	@GET
	@Path("/{id}")
	@Produces({"application/json", "application/xml"})
	public WebSiteDTO getWebSite(
			@PathParam("id") String id, @Context Language language)
		throws Exception;

	@GET
	@Path("/")
	@Produces({"application/json", "application/xml"})
	public CollectionDTO<WebSiteDTO> getWebSites(
			@Context Language language, @Context Pagination pagination)
		throws Exception;

}