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

import com.liferay.commerce.openapi.core.annotation.Nullable;
import com.liferay.commerce.openapi.core.context.Pagination;
import com.liferay.commerce.openapi.core.model.CollectionDTO;
import com.liferay.headless.commerce.admin.site.setting.model.v1_0.AvailabilityEstimateDTO;
import com.liferay.headless.commerce.admin.site.setting.model.v1_0.CatalogRuleDTO;
import com.liferay.headless.commerce.admin.site.setting.model.v1_0.MeasurementUnitDTO;
import com.liferay.headless.commerce.admin.site.setting.model.v1_0.TaxCategoryDTO;
import com.liferay.headless.commerce.admin.site.setting.model.v1_0.UserSegmentDTO;
import com.liferay.headless.commerce.admin.site.setting.model.v1_0.WarehouseDTO;

import javax.annotation.Generated;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;

/**
 * @author Alessio Antonio Rendina
 */
@Generated(value = "OSGiRESTModuleGenerator")
@Path("/v1.0/commerceAdminSiteSetting")
public interface CommerceAdminSiteSettingResource {

	@GET
	@Path("/{groupId}/availabilityEstimate/")
	@Produces({"application/json", "application/xml"})
	public CollectionDTO<AvailabilityEstimateDTO> getAvailabilityEstimates(
			@PathParam("groupId") Long groupId, @Context Pagination pagination)
		throws Exception;

	@GET
	@Path("/{groupId}/catalogRule/")
	@Produces({"application/json", "application/xml"})
	public CollectionDTO<CatalogRuleDTO> getCatalogRules(
			@PathParam("groupId") Long groupId, @Context Pagination pagination)
		throws Exception;

	@GET
	@Path("/{groupId}/measurementUnit/")
	@Produces({"application/json", "application/xml"})
	public CollectionDTO<MeasurementUnitDTO> getMeasurementUnits(
			@PathParam("groupId") Long groupId,
			@Nullable @QueryParam("type") Integer type,
			@Context Pagination pagination)
		throws Exception;

	@GET
	@Path("/{groupId}/taxCategory/")
	@Produces({"application/json", "application/xml"})
	public CollectionDTO<TaxCategoryDTO> getTaxCategories(
			@PathParam("groupId") Long groupId, @Context Pagination pagination)
		throws Exception;

	@GET
	@Path("/{groupId}/userSegment/")
	@Produces({"application/json", "application/xml"})
	public CollectionDTO<UserSegmentDTO> getUserSegments(
			@PathParam("groupId") Long groupId, @Context Pagination pagination)
		throws Exception;

	@GET
	@Path("/{groupId}/warehouse/")
	@Produces({"application/json", "application/xml"})
	public CollectionDTO<WarehouseDTO> getWarehouses(
			@PathParam("groupId") Long groupId,
			@Nullable @QueryParam("active") Boolean active,
			@Context Pagination pagination)
		throws Exception;

	@Consumes({"application/json", "application/xml"})
	@Path("/{groupId}/availabilityEstimate/")
	@POST
	@Produces({"application/json", "application/xml"})
	public AvailabilityEstimateDTO upsertAvailabilityEstimate(
			@PathParam("groupId") Long groupId,
			AvailabilityEstimateDTO availabilityEstimateDTO)
		throws Exception;

	@Consumes({"application/json", "application/xml"})
	@Path("/{groupId}/catalogRule/")
	@POST
	@Produces({"application/json", "application/xml"})
	public CatalogRuleDTO upsertCatalogRule(
			@PathParam("groupId") Long groupId, CatalogRuleDTO catalogRuleDTO)
		throws Exception;

	@Consumes({"application/json", "application/xml"})
	@Path("/{groupId}/measurementUnit/")
	@POST
	@Produces({"application/json", "application/xml"})
	public MeasurementUnitDTO upsertMeasurementUnit(
			@PathParam("groupId") Long groupId,
			MeasurementUnitDTO measurementUnitDTO)
		throws Exception;

	@Consumes({"application/json", "application/xml"})
	@Path("/{groupId}/taxCategory/")
	@POST
	@Produces({"application/json", "application/xml"})
	public TaxCategoryDTO upsertTaxCategory(
			@PathParam("groupId") Long groupId, TaxCategoryDTO taxCategoryDTO)
		throws Exception;

	@Consumes({"application/json", "application/xml"})
	@Path("/{groupId}/userSegment/")
	@POST
	@Produces({"application/json", "application/xml"})
	public UserSegmentDTO upsertUserSegment(
			@PathParam("groupId") Long groupId, UserSegmentDTO userSegmentDTO)
		throws Exception;

	@Consumes({"application/json", "application/xml"})
	@Path("/{groupId}/warehouse/")
	@POST
	@Produces({"application/json", "application/xml"})
	public WarehouseDTO upsertWarehouse(
			@PathParam("groupId") Long groupId, WarehouseDTO warehouseDTO)
		throws Exception;

}