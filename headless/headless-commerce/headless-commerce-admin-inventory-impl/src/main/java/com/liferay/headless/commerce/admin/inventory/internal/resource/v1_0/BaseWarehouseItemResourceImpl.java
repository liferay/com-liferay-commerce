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

package com.liferay.headless.commerce.admin.inventory.internal.resource.v1_0;

import com.liferay.headless.commerce.admin.inventory.dto.v1_0.WarehouseItem;
import com.liferay.headless.commerce.admin.inventory.resource.v1_0.WarehouseItemResource;
import com.liferay.petra.function.UnsafeFunction;
import com.liferay.portal.kernel.model.Company;
import com.liferay.portal.vulcan.accept.language.AcceptLanguage;
import com.liferay.portal.vulcan.pagination.Page;
import com.liferay.portal.vulcan.pagination.Pagination;
import com.liferay.portal.vulcan.util.TransformUtil;

import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.tags.Tags;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

import javax.annotation.Generated;

import javax.validation.constraints.NotNull;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.PATCH;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

/**
 * @author Alessio Antonio Rendina
 * @generated
 */
@Generated("")
@Path("/v1.0")
public abstract class BaseWarehouseItemResourceImpl
	implements WarehouseItemResource {

	@Override
	@GET
	@Parameters(
		value = {
			@Parameter(in = ParameterIn.PATH, name = "id"),
			@Parameter(in = ParameterIn.QUERY, name = "page"),
			@Parameter(in = ParameterIn.QUERY, name = "pageSize")
		}
	)
	@Path("/warehouses/{id}/warehouseItems/")
	@Produces({"application/json", "application/xml"})
	@Tags(value = {@Tag(name = "WarehouseItem")})
	public Page<WarehouseItem> getWarehousIdWarehouseItemsPage(
			@NotNull @Parameter(hidden = true) @PathParam("id") Long id,
			@Context Pagination pagination)
		throws Exception {

		return Page.of(Collections.emptyList());
	}

	@Override
	@Consumes({"application/json", "application/xml"})
	@POST
	@Parameters(value = {@Parameter(in = ParameterIn.PATH, name = "id")})
	@Path("/warehouses/{id}/warehouseItems/")
	@Produces({"application/json", "application/xml"})
	@Tags(value = {@Tag(name = "WarehouseItem")})
	public WarehouseItem postWarehousIdWarehouseItem(
			@NotNull @Parameter(hidden = true) @PathParam("id") Long id,
			WarehouseItem warehouseItem)
		throws Exception {

		return new WarehouseItem();
	}

	@Override
	@GET
	@Parameters(
		value = {
			@Parameter(in = ParameterIn.PATH, name = "externalReferenceCode"),
			@Parameter(in = ParameterIn.QUERY, name = "page"),
			@Parameter(in = ParameterIn.QUERY, name = "pageSize")
		}
	)
	@Path(
		"/warehouses/by-externalReferenceCode/{externalReferenceCode}/warehouseItems/"
	)
	@Produces({"application/json", "application/xml"})
	@Tags(value = {@Tag(name = "WarehouseItem")})
	public Page<WarehouseItem>
			getWarehousByExternalReferenceCodeWarehouseItemsPage(
				@NotNull @Parameter(hidden = true)
				@PathParam("externalReferenceCode") String
					externalReferenceCode,
				@Context Pagination pagination)
		throws Exception {

		return Page.of(Collections.emptyList());
	}

	@Override
	@Consumes({"application/json", "application/xml"})
	@POST
	@Parameters(
		value = {
			@Parameter(in = ParameterIn.PATH, name = "externalReferenceCode")
		}
	)
	@Path(
		"/warehouses/by-externalReferenceCode/{externalReferenceCode}/warehouseItems/"
	)
	@Produces({"application/json", "application/xml"})
	@Tags(value = {@Tag(name = "WarehouseItem")})
	public WarehouseItem postWarehousByExternalReferenceCodeWarehouseItem(
			@NotNull @Parameter(hidden = true)
			@PathParam("externalReferenceCode") String externalReferenceCode,
			WarehouseItem warehouseItem)
		throws Exception {

		return new WarehouseItem();
	}

	@Override
	@DELETE
	@Parameters(value = {@Parameter(in = ParameterIn.PATH, name = "id")})
	@Path("/warehouseItems/{id}")
	@Produces({"application/json", "application/xml"})
	@Tags(value = {@Tag(name = "WarehouseItem")})
	public Response deleteWarehouseItem(
			@NotNull @Parameter(hidden = true) @PathParam("id") Long id)
		throws Exception {

		Response.ResponseBuilder responseBuilder = Response.ok();

		return responseBuilder.build();
	}

	@Override
	@GET
	@Parameters(value = {@Parameter(in = ParameterIn.PATH, name = "id")})
	@Path("/warehouseItems/{id}")
	@Produces({"application/json", "application/xml"})
	@Tags(value = {@Tag(name = "WarehouseItem")})
	public WarehouseItem getWarehouseItem(
			@NotNull @Parameter(hidden = true) @PathParam("id") Long id)
		throws Exception {

		return new WarehouseItem();
	}

	@Override
	@Consumes({"application/json", "application/xml"})
	@PATCH
	@Parameters(value = {@Parameter(in = ParameterIn.PATH, name = "id")})
	@Path("/warehouseItems/{id}")
	@Produces({"application/json", "application/xml"})
	@Tags(value = {@Tag(name = "WarehouseItem")})
	public Response patchWarehouseItem(
			@NotNull @Parameter(hidden = true) @PathParam("id") Long id,
			WarehouseItem warehouseItem)
		throws Exception {

		Response.ResponseBuilder responseBuilder = Response.ok();

		return responseBuilder.build();
	}

	@Override
	@DELETE
	@Parameters(
		value = {
			@Parameter(in = ParameterIn.PATH, name = "externalReferenceCode")
		}
	)
	@Path("/warehouseItems/by-externalReferenceCode/{externalReferenceCode}/")
	@Produces({"application/json", "application/xml"})
	@Tags(value = {@Tag(name = "WarehouseItem")})
	public Response deleteWarehouseItemByExternalReferenceCode(
			@NotNull @Parameter(hidden = true)
			@PathParam("externalReferenceCode") String externalReferenceCode)
		throws Exception {

		Response.ResponseBuilder responseBuilder = Response.ok();

		return responseBuilder.build();
	}

	@Override
	@GET
	@Parameters(
		value = {
			@Parameter(in = ParameterIn.PATH, name = "externalReferenceCode")
		}
	)
	@Path("/warehouseItems/by-externalReferenceCode/{externalReferenceCode}/")
	@Produces({"application/json", "application/xml"})
	@Tags(value = {@Tag(name = "WarehouseItem")})
	public WarehouseItem getWarehouseItemByExternalReferenceCode(
			@NotNull @Parameter(hidden = true)
			@PathParam("externalReferenceCode") String externalReferenceCode)
		throws Exception {

		return new WarehouseItem();
	}

	@Override
	@Consumes({"application/json", "application/xml"})
	@PATCH
	@Parameters(
		value = {
			@Parameter(in = ParameterIn.PATH, name = "externalReferenceCode")
		}
	)
	@Path("/warehouseItems/by-externalReferenceCode/{externalReferenceCode}/")
	@Produces({"application/json", "application/xml"})
	@Tags(value = {@Tag(name = "WarehouseItem")})
	public Response patchWarehouseItemByExternalReferenceCode(
			@NotNull @Parameter(hidden = true)
			@PathParam("externalReferenceCode") String externalReferenceCode,
			WarehouseItem warehouseItem)
		throws Exception {

		Response.ResponseBuilder responseBuilder = Response.ok();

		return responseBuilder.build();
	}

	@Override
	@Consumes({"application/json", "application/xml"})
	@POST
	@Parameters(
		value = {
			@Parameter(in = ParameterIn.PATH, name = "externalReferenceCode")
		}
	)
	@Path("/warehouseItems/by-externalReferenceCode/{externalReferenceCode}/")
	@Produces({"application/json", "application/xml"})
	@Tags(value = {@Tag(name = "WarehouseItem")})
	public WarehouseItem postWarehouseItemByExternalReferenceCode(
			@NotNull @Parameter(hidden = true)
			@PathParam("externalReferenceCode") String externalReferenceCode,
			WarehouseItem warehouseItem)
		throws Exception {

		return new WarehouseItem();
	}

	@Override
	@GET
	@Parameters(
		value = {
			@Parameter(in = ParameterIn.QUERY, name = "end"),
			@Parameter(in = ParameterIn.QUERY, name = "start"),
			@Parameter(in = ParameterIn.QUERY, name = "page"),
			@Parameter(in = ParameterIn.QUERY, name = "pageSize")
		}
	)
	@Path("/warehouseItems/updated")
	@Produces({"application/json", "application/xml"})
	@Tags(value = {@Tag(name = "WarehouseItem")})
	public Page<WarehouseItem> getWarehouseItemsUpdatedPage(
			@Parameter(hidden = true) @QueryParam("end") java.util.Date end,
			@Parameter(hidden = true) @QueryParam("start") java.util.Date start,
			@Context Pagination pagination)
		throws Exception {

		return Page.of(Collections.emptyList());
	}

	public void setContextCompany(Company contextCompany) {
		this.contextCompany = contextCompany;
	}

	protected void preparePatch(
		WarehouseItem warehouseItem, WarehouseItem existingWarehouseItem) {
	}

	protected <T, R> List<R> transform(
		Collection<T> collection,
		UnsafeFunction<T, R, Exception> unsafeFunction) {

		return TransformUtil.transform(collection, unsafeFunction);
	}

	protected <T, R> R[] transform(
		T[] array, UnsafeFunction<T, R, Exception> unsafeFunction,
		Class<?> clazz) {

		return TransformUtil.transform(array, unsafeFunction, clazz);
	}

	protected <T, R> R[] transformToArray(
		Collection<T> collection,
		UnsafeFunction<T, R, Exception> unsafeFunction, Class<?> clazz) {

		return TransformUtil.transformToArray(
			collection, unsafeFunction, clazz);
	}

	protected <T, R> List<R> transformToList(
		T[] array, UnsafeFunction<T, R, Exception> unsafeFunction) {

		return TransformUtil.transformToList(array, unsafeFunction);
	}

	@Context
	protected AcceptLanguage contextAcceptLanguage;

	@Context
	protected Company contextCompany;

	@Context
	protected UriInfo contextUriInfo;

}