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

package com.liferay.headless.commerce.admin.site.setting.internal.resource.v1_0;

import com.liferay.headless.commerce.admin.site.setting.dto.v1_0.CatalogRule;
import com.liferay.headless.commerce.admin.site.setting.dto.v1_0.Category;
import com.liferay.headless.commerce.admin.site.setting.dto.v1_0.UserSegment;
import com.liferay.headless.commerce.admin.site.setting.resource.v1_0.CatalogRuleResource;
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
public abstract class BaseCatalogRuleResourceImpl
	implements CatalogRuleResource {

	@Override
	@GET
	@Parameters(
		value = {
			@Parameter(in = ParameterIn.QUERY, name = "page"),
			@Parameter(in = ParameterIn.QUERY, name = "pageSize")
		}
	)
	@Path("/catalogRule/{id}/category")
	@Produces({"application/json", "application/xml"})
	@Tags(value = {@Tag(name = "CatalogRule")})
	public Page<Category> getCatalogRuleCategories(
			@NotNull @PathParam("id") Long id, @Context Pagination pagination)
		throws Exception {

		return Page.of(Collections.emptyList());
	}

	@Override
	@GET
	@Parameters(
		value = {
			@Parameter(in = ParameterIn.QUERY, name = "page"),
			@Parameter(in = ParameterIn.QUERY, name = "pageSize")
		}
	)
	@Path("/catalogRule/{id}/userSegment")
	@Produces({"application/json", "application/xml"})
	@Tags(value = {@Tag(name = "CatalogRule")})
	public Page<UserSegment> getCatalogRuleUserSegments(
			@NotNull @PathParam("id") Long id, @Context Pagination pagination)
		throws Exception {

		return Page.of(Collections.emptyList());
	}

	@Override
	@DELETE
	@Path("/catalogRule/{id}")
	@Produces({"application/json", "application/xml"})
	@Tags(value = {@Tag(name = "CatalogRule")})
	public Response deleteCatalogRule(@NotNull @PathParam("id") Long id)
		throws Exception {

		Response.ResponseBuilder responseBuilder = Response.ok();

		return responseBuilder.build();
	}

	@Override
	@GET
	@Path("/catalogRule/{id}")
	@Produces({"application/json", "application/xml"})
	@Tags(value = {@Tag(name = "CatalogRule")})
	public CatalogRule getCatalogRule(@NotNull @PathParam("id") Long id)
		throws Exception {

		return new CatalogRule();
	}

	@Override
	@Consumes({"application/json", "application/xml"})
	@PUT
	@Path("/catalogRule/{id}")
	@Produces({"application/json", "application/xml"})
	@Tags(value = {@Tag(name = "CatalogRule")})
	public Response updateCatalogRule(
			@NotNull @PathParam("id") Long id, CatalogRule catalogRule)
		throws Exception {

		Response.ResponseBuilder responseBuilder = Response.ok();

		return responseBuilder.build();
	}

	@Override
	@GET
	@Parameters(
		value = {
			@Parameter(in = ParameterIn.QUERY, name = "page"),
			@Parameter(in = ParameterIn.QUERY, name = "pageSize")
		}
	)
	@Path("/commerceAdminSiteSetting/{groupId}/catalogRule/")
	@Produces({"application/json", "application/xml"})
	@Tags(value = {@Tag(name = "CatalogRule")})
	public Page<CatalogRule> getCatalogRules(
			@NotNull @PathParam("groupId") Long groupId,
			@Context Pagination pagination)
		throws Exception {

		return Page.of(Collections.emptyList());
	}

	@Override
	@Consumes({"application/json", "application/xml"})
	@POST
	@Path("/commerceAdminSiteSetting/{groupId}/catalogRule/")
	@Produces({"application/json", "application/xml"})
	@Tags(value = {@Tag(name = "CatalogRule")})
	public CatalogRule upsertCatalogRule(
			@NotNull @PathParam("groupId") Long groupId,
			CatalogRule catalogRule)
		throws Exception {

		return new CatalogRule();
	}

	public void setContextCompany(Company contextCompany) {
		this.contextCompany = contextCompany;
	}

	protected void preparePatch(CatalogRule catalogRule) {
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