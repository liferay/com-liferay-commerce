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

package com.liferay.headless.commerce.admin.site.setting.resource.v1_0;

import com.liferay.commerce.openapi.core.context.Pagination;
import com.liferay.commerce.openapi.core.model.CollectionDTO;
import com.liferay.headless.commerce.admin.site.setting.model.v1_0.UserSegmentCriterionDTO;
import com.liferay.headless.commerce.admin.site.setting.model.v1_0.UserSegmentDTO;

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
@Path("/v1.0/userSegment")
public interface UserSegmentResource {

	@DELETE
	@Path("/{id}")
	public Response deleteUserSegment(@PathParam("id") Long id)
		throws Exception;

	@DELETE
	@Path("/{id}/userSegmentCriterion/{criterionId}")
	public Response deleteUserSegmentCriterion(
			@PathParam("id") Long id,
			@PathParam("criterionId") Long criterionId)
		throws Exception;

	@GET
	@Path("/{id}")
	@Produces({"application/json", "application/xml"})
	public UserSegmentDTO getUserSegment(@PathParam("id") Long id)
		throws Exception;

	@GET
	@Path("/{id}/userSegmentCriterion")
	@Produces({"application/json", "application/xml"})
	public CollectionDTO<UserSegmentCriterionDTO> getUserSegmentCriteria(
			@PathParam("id") Long id, @Context Pagination pagination)
		throws Exception;

	@GET
	@Path("/{id}/userSegmentCriterion/{criterionId}")
	@Produces({"application/json", "application/xml"})
	public UserSegmentCriterionDTO getUserSegmentCriterion(
			@PathParam("id") Long id,
			@PathParam("criterionId") Long criterionId)
		throws Exception;

	@Consumes({"application/json", "application/xml"})
	@Path("/{id}")
	@PUT
	public Response updateUserSegment(
			@PathParam("id") Long id, UserSegmentDTO userSegmentDTO)
		throws Exception;

	@Consumes({"application/json", "application/xml"})
	@Path("/{id}/userSegmentCriterion/{criterionId}")
	@POST
	@Produces({"application/json", "application/xml"})
	public UserSegmentCriterionDTO updateUserSegmentCriterion(
			@PathParam("id") Long id,
			@PathParam("criterionId") Long criterionId,
			UserSegmentCriterionDTO userSegmentCriterionDTO)
		throws Exception;

	@Consumes({"application/json", "application/xml"})
	@Path("/{id}/userSegmentCriterion")
	@POST
	@Produces({"application/json", "application/xml"})
	public UserSegmentCriterionDTO upsertUserSegmentCriterion(
			@PathParam("id") Long id,
			UserSegmentCriterionDTO userSegmentCriterionDTO)
		throws Exception;

}