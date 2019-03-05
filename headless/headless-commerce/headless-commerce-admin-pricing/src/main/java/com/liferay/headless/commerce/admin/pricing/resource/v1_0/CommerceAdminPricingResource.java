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

package com.liferay.headless.commerce.admin.pricing.resource.v1_0;

import com.liferay.commerce.openapi.core.context.Language;
import com.liferay.commerce.openapi.core.context.Pagination;
import com.liferay.commerce.openapi.core.model.CollectionDTO;
import com.liferay.headless.commerce.admin.pricing.model.v1_0.DiscountDTO;
import com.liferay.headless.commerce.admin.pricing.model.v1_0.PriceListDTO;

import javax.annotation.Generated;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;

/**
 * @author Alessio Antonio Rendina
 */
@Generated(value = "OSGiRESTModuleGenerator")
@Path("/v1.0/commerceAdminPricing")
public interface CommerceAdminPricingResource {

	@GET
	@Path("/{groupId}/discount/")
	@Produces({"application/json", "application/xml"})
	public CollectionDTO<DiscountDTO> getDiscounts(
			@PathParam("groupId") Long groupId, @Context Pagination pagination)
		throws Exception;

	@GET
	@Path("/{groupId}/priceList/")
	@Produces({"application/json", "application/xml"})
	public CollectionDTO<PriceListDTO> getPriceLists(
			@PathParam("groupId") Long groupId,
			@Context Language language, @Context Pagination pagination)
		throws Exception;

	@Consumes({"application/json", "application/xml"})
	@Path("/{groupId}/discount/")
	@POST
	@Produces({"application/json", "application/xml"})
	public DiscountDTO upsertDiscount(
			@PathParam("groupId") Long groupId, DiscountDTO discountDTO)
		throws Exception;

	@Consumes({"application/json", "application/xml"})
	@Path("/{groupId}/priceList/")
	@POST
	@Produces({"application/json", "application/xml"})
	public PriceListDTO upsertPriceList(
			@PathParam("groupId") Long groupId,
			PriceListDTO priceListDTO, @Context Language language)
		throws Exception;

}