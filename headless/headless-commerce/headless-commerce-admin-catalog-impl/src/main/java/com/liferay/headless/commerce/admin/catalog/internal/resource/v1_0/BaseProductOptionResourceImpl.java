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

package com.liferay.headless.commerce.admin.catalog.internal.resource.v1_0;

import com.liferay.headless.commerce.admin.catalog.dto.v1_0.ProductOption;
import com.liferay.headless.commerce.admin.catalog.dto.v1_0.ProductOptionValue;
import com.liferay.headless.commerce.admin.catalog.resource.v1_0.ProductOptionResource;
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
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

/**
 * @author Zoltán Takács
 * @generated
 */
@Generated("")
@Path("/v1.0")
public abstract class BaseProductOptionResourceImpl
	implements ProductOptionResource {

	@Override
	@GET
	@Parameters(
		value = {
			@Parameter(in = ParameterIn.QUERY, name = "page"),
			@Parameter(in = ParameterIn.QUERY, name = "pageSize")
		}
	)
	@Path("/commerceAdminCatalog/{groupId}/productOption/")
	@Produces({"application/json", "application/xml"})
	@Tags(value = {@Tag(name = "ProductOption")})
	public Page<ProductOption> getOptions(
			@NotNull @PathParam("groupId") Long groupId,
			@Context Pagination pagination)
		throws Exception {

		return Page.of(Collections.emptyList());
	}

	@Override
	@Consumes({"application/json", "application/xml"})
	@POST
	@Path("/commerceAdminCatalog/{groupId}/productOption/")
	@Produces({"application/json", "application/xml"})
	@Tags(value = {@Tag(name = "ProductOption")})
	public ProductOption upsertProductOption(
			@NotNull @PathParam("groupId") Long groupId,
			ProductOption productOption)
		throws Exception {

		return new ProductOption();
	}

	@Override
	@GET
	@Parameters(
		value = {
			@Parameter(in = ParameterIn.QUERY, name = "page"),
			@Parameter(in = ParameterIn.QUERY, name = "pageSize")
		}
	)
	@Path("/productOption/{id}/productOptionValue/")
	@Produces({"application/json", "application/xml"})
	@Tags(value = {@Tag(name = "ProductOption")})
	public Page<ProductOptionValue> getProductOptionValues(
			@NotNull @PathParam("id") String id, @Context Pagination pagination)
		throws Exception {

		return Page.of(Collections.emptyList());
	}

	@Override
	@Consumes({"application/json", "application/xml"})
	@POST
	@Path("/productOption/{id}/productOptionValue/")
	@Produces({"application/json", "application/xml"})
	@Tags(value = {@Tag(name = "ProductOption")})
	public ProductOptionValue upsertProductOptionValue(
			@NotNull @PathParam("id") String id,
			ProductOptionValue productOptionValue)
		throws Exception {

		return new ProductOptionValue();
	}

	@Override
	@DELETE
	@Path("/productOption/{id}")
	@Produces({"application/json", "application/xml"})
	@Tags(value = {@Tag(name = "ProductOption")})
	public Response deleteProductOption(@NotNull @PathParam("id") String id)
		throws Exception {

		Response.ResponseBuilder responseBuilder = Response.ok();

		return responseBuilder.build();
	}

	@Override
	@GET
	@Path("/productOption/{id}")
	@Produces({"application/json", "application/xml"})
	@Tags(value = {@Tag(name = "ProductOption")})
	public ProductOption getProductOption(@NotNull @PathParam("id") String id)
		throws Exception {

		return new ProductOption();
	}

	@Override
	@Consumes({"application/json", "application/xml"})
	@PUT
	@Path("/productOption/{id}")
	@Produces({"application/json", "application/xml"})
	@Tags(value = {@Tag(name = "ProductOption")})
	public Response updateProductOption(
			@NotNull @PathParam("id") String id, ProductOption productOption)
		throws Exception {

		Response.ResponseBuilder responseBuilder = Response.ok();

		return responseBuilder.build();
	}

	public void setContextCompany(Company contextCompany) {
		this.contextCompany = contextCompany;
	}

	protected void preparePatch(ProductOption productOption) {
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