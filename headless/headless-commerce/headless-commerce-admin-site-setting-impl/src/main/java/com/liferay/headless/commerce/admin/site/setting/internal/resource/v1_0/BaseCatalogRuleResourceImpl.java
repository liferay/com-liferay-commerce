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
import com.liferay.headless.commerce.admin.site.setting.resource.v1_0.CatalogRuleResource;
import com.liferay.petra.function.UnsafeFunction;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.model.Company;
import com.liferay.portal.vulcan.accept.language.AcceptLanguage;
import com.liferay.portal.vulcan.util.TransformUtil;

import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.tags.Tags;

import java.net.URI;

import java.util.Collection;
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
import javax.ws.rs.core.UriBuilder;
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
	public Response getCatalogRule(@NotNull @PathParam("id") Long id)
		throws Exception {

		Response.ResponseBuilder responseBuilder = Response.ok();

		return responseBuilder.build();
	}

	@Override
	@Consumes("application/json")
	@PUT
	@Path("/catalogRule/{id}")
	@Produces({"application/json", "application/xml"})
	@Tags(value = {@Tag(name = "CatalogRule")})
	public Response updateMediaType1CatalogRule(
			@NotNull @PathParam("id") Long id, CatalogRule catalogRule)
		throws Exception {

		Response.ResponseBuilder responseBuilder = Response.ok();

		return responseBuilder.build();
	}

	@Override
	@Consumes("application/xml")
	@PUT
	@Path("/catalogRule/{id}")
	@Produces({"application/json", "application/xml"})
	@Tags(value = {@Tag(name = "CatalogRule")})
	public Response updateMediaType2CatalogRule(
			@NotNull @PathParam("id") Long id, CatalogRule catalogRule)
		throws Exception {

		Response.ResponseBuilder responseBuilder = Response.ok();

		return responseBuilder.build();
	}

	@Override
	@GET
	@Path("/catalogRule/{id}/category")
	@Produces({"application/json", "application/xml"})
	@Tags(value = {@Tag(name = "CatalogRule")})
	public Response getCatalogRuleCategories(@NotNull @PathParam("id") Long id)
		throws Exception {

		Response.ResponseBuilder responseBuilder = Response.ok();

		return responseBuilder.build();
	}

	@Override
	@GET
	@Path("/catalogRule/{id}/userSegment")
	@Produces({"application/json", "application/xml"})
	@Tags(value = {@Tag(name = "CatalogRule")})
	public Response getCatalogRuleUserSegments(
			@NotNull @PathParam("id") Long id)
		throws Exception {

		Response.ResponseBuilder responseBuilder = Response.ok();

		return responseBuilder.build();
	}

	@Override
	@GET
	@Path("/commerceAdminSiteSetting/{groupId}/catalogRule/")
	@Produces({"application/json", "application/xml"})
	@Tags(value = {@Tag(name = "CatalogRule")})
	public Response getCatalogRules(@NotNull @PathParam("groupId") Long groupId)
		throws Exception {

		Response.ResponseBuilder responseBuilder = Response.ok();

		return responseBuilder.build();
	}

	@Override
	@Consumes("application/json")
	@POST
	@Path("/commerceAdminSiteSetting/{groupId}/catalogRule/")
	@Produces({"application/json", "application/xml"})
	@Tags(value = {@Tag(name = "CatalogRule")})
	public Response upsertMediaType1CatalogRule(
			@NotNull @PathParam("groupId") Long groupId,
			CatalogRule catalogRule)
		throws Exception {

		Response.ResponseBuilder responseBuilder = Response.ok();

		return responseBuilder.build();
	}

	@Override
	@Consumes("application/xml")
	@POST
	@Path("/commerceAdminSiteSetting/{groupId}/catalogRule/")
	@Produces({"application/json", "application/xml"})
	@Tags(value = {@Tag(name = "CatalogRule")})
	public Response upsertMediaType2CatalogRule(
			@NotNull @PathParam("groupId") Long groupId,
			CatalogRule catalogRule)
		throws Exception {

		Response.ResponseBuilder responseBuilder = Response.ok();

		return responseBuilder.build();
	}

	public void setContextCompany(Company contextCompany) {
		this.contextCompany = contextCompany;
	}

	protected String getJAXRSLink(String methodName, Object... values) {
		String baseURIString = String.valueOf(contextUriInfo.getBaseUri());

		if (baseURIString.endsWith(StringPool.FORWARD_SLASH)) {
			baseURIString = baseURIString.substring(
				0, baseURIString.length() - 1);
		}

		URI resourceURI = UriBuilder.fromResource(
			BaseCatalogRuleResourceImpl.class
		).build();

		URI methodURI = UriBuilder.fromMethod(
			BaseCatalogRuleResourceImpl.class, methodName
		).build(
			values
		);

		return baseURIString + resourceURI.toString() + methodURI.toString();
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