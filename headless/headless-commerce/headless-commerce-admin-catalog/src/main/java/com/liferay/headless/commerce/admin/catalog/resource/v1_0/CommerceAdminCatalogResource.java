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

import com.liferay.commerce.openapi.core.context.Language;
import com.liferay.commerce.openapi.core.context.Pagination;
import com.liferay.commerce.openapi.core.model.CollectionDTO;
import com.liferay.headless.commerce.admin.catalog.model.v1_0.OptionCategoryDTO;
import com.liferay.headless.commerce.admin.catalog.model.v1_0.ProductDTO;
import com.liferay.headless.commerce.admin.catalog.model.v1_0.ProductOptionDTO;
import com.liferay.headless.commerce.admin.catalog.model.v1_0.SpecificationDTO;

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
@Path("/v1.0/commerceAdminCatalog")
public interface CommerceAdminCatalogResource {

	@GET
	@Path("/{groupId}/optionCategory/")
	@Produces({"application/json", "application/xml"})
	public CollectionDTO<OptionCategoryDTO> getOptionCategories(
			@PathParam("groupId") Long groupId, @Context Pagination pagination)
		throws Exception;

	@GET
	@Path("/{groupId}/productOption/")
	@Produces({"application/json", "application/xml"})
	public CollectionDTO<ProductOptionDTO> getOptions(
			@PathParam("groupId") Long groupId, @Context Language language,
			@Context Pagination pagination)
		throws Exception;

	@GET
	@Path("/{groupId}/product/")
	@Produces({"application/json", "application/xml"})
	public CollectionDTO<ProductDTO> getProducts(
			@PathParam("groupId") Long groupId, @Context Language language,
			@Context Pagination pagination)
		throws Exception;

	@GET
	@Path("/{groupId}/specification/")
	@Produces({"application/json", "application/xml"})
	public CollectionDTO<SpecificationDTO> getSpecifications(
			@PathParam("groupId") Long groupId, @Context Pagination pagination)
		throws Exception;

	@Consumes({"application/json", "application/xml"})
	@Path("/{groupId}/optionCategory/")
	@POST
	@Produces({"application/json", "application/xml"})
	public OptionCategoryDTO upsertOptionCategory(
			@PathParam("groupId") Long groupId,
			OptionCategoryDTO optionCategoryDTO)
		throws Exception;

	@Consumes({"application/json", "application/xml"})
	@Path("/{groupId}/product/")
	@POST
	@Produces({"application/json", "application/xml"})
	public ProductDTO upsertProduct(
			@PathParam("groupId") Long groupId, ProductDTO productDTO,
			@Context Language language)
		throws Exception;

	@Consumes({"application/json", "application/xml"})
	@Path("/{groupId}/productOption/")
	@POST
	@Produces({"application/json", "application/xml"})
	public ProductOptionDTO upsertProductOption(
			@PathParam("groupId") Long groupId,
			ProductOptionDTO productOptionDTO, @Context Language language)
		throws Exception;

	@Consumes({"application/json", "application/xml"})
	@Path("/{groupId}/specification/")
	@POST
	@Produces({"application/json", "application/xml"})
	public SpecificationDTO upsertSpecification(
			@PathParam("groupId") Long groupId,
			SpecificationDTO specificationDTO)
		throws Exception;

}