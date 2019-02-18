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

package com.liferay.commerce.headless.admin.site.setting.resource.v1_0;

import com.liferay.commerce.headless.admin.site.setting.model.v1_0.AvailabilityEstimateDTO;
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
 * @author Alessio Antonio Rendina
 */
@Generated(value = "OSGiRESTModuleGenerator")
@Path("/v1.0/availabilityEstimate")
public interface AvailabilityEstimateResource {

	@Consumes("application/*")
	@Path("/")
	@POST
	@Produces("application/*")
	public AvailabilityEstimateDTO addAvailabilityEstimate(
			@QueryParam("groupId") Long groupId,
			AvailabilityEstimateDTO availabilityEstimateDTO)
		throws Exception;

	@DELETE
	@Path("/{id}")
	public Response deleteAvailabilityEstimate(@PathParam("id") String id)
		throws Exception;

	@GET
	@Path("/{id}")
	@Produces("application/*")
	public AvailabilityEstimateDTO getAvailabilityEstimate(
			@PathParam("id") String id)
		throws Exception;

	@GET
	@Path("/")
	@Produces("application/*")
	public CollectionDTO<AvailabilityEstimateDTO> getAvailabilityEstimates(
			@QueryParam("groupId") Long groupId, @Context Pagination pagination)
		throws Exception;

	@Consumes("application/*")
	@Path("/{id}")
	@PUT
	public Response updateAvailabilityEstimate(
			@PathParam("id") String id,
			AvailabilityEstimateDTO availabilityEstimateDTO)
		throws Exception;

}