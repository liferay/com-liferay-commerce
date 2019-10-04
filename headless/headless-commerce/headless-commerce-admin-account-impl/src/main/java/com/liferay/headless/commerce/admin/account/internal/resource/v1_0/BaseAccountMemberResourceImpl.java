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

package com.liferay.headless.commerce.admin.account.internal.resource.v1_0;

import com.liferay.headless.commerce.admin.account.dto.v1_0.AccountMember;
import com.liferay.headless.commerce.admin.account.resource.v1_0.AccountMemberResource;
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
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

/**
 * @author Alessio Antonio Rendina
 * @generated
 */
@Generated("")
@Path("/v1.0")
public abstract class BaseAccountMemberResourceImpl
	implements AccountMemberResource {

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
		"/accounts/by-externalReferenceCode/{externalReferenceCode}/accountMembers/"
	)
	@Produces({"application/json", "application/xml"})
	@Tags(value = {@Tag(name = "AccountMember")})
	public Page<AccountMember>
			getAccountByExternalReferenceCodeAccountMembersPage(
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
		"/accounts/by-externalReferenceCode/{externalReferenceCode}/accountMembers/"
	)
	@Produces({"application/json", "application/xml"})
	@Tags(value = {@Tag(name = "AccountMember")})
	public AccountMember postAccountByExternalReferenceCodeAccountMember(
			@NotNull @Parameter(hidden = true)
			@PathParam("externalReferenceCode") String externalReferenceCode,
			AccountMember accountMember)
		throws Exception {

		return new AccountMember();
	}

	@Override
	@DELETE
	@Parameters(
		value = {
			@Parameter(in = ParameterIn.PATH, name = "externalReferenceCode"),
			@Parameter(in = ParameterIn.PATH, name = "userId")
		}
	)
	@Path(
		"/accounts/by-externalReferenceCode/{externalReferenceCode}/accountMembers/{userId}"
	)
	@Produces({"application/json", "application/xml"})
	@Tags(value = {@Tag(name = "AccountMember")})
	public Response deleteAccountByExternalReferenceCodeAccountMember(
			@NotNull @Parameter(hidden = true)
			@PathParam("externalReferenceCode") String externalReferenceCode,
			@NotNull @Parameter(hidden = true) @PathParam("userId") Long userId)
		throws Exception {

		Response.ResponseBuilder responseBuilder = Response.ok();

		return responseBuilder.build();
	}

	@Override
	@GET
	@Parameters(
		value = {
			@Parameter(in = ParameterIn.PATH, name = "externalReferenceCode"),
			@Parameter(in = ParameterIn.PATH, name = "userId")
		}
	)
	@Path(
		"/accounts/by-externalReferenceCode/{externalReferenceCode}/accountMembers/{userId}"
	)
	@Produces({"application/json", "application/xml"})
	@Tags(value = {@Tag(name = "AccountMember")})
	public AccountMember getAccountByExternalReferenceCodeAccountMember(
			@NotNull @Parameter(hidden = true)
			@PathParam("externalReferenceCode") String externalReferenceCode,
			@NotNull @Parameter(hidden = true) @PathParam("userId") Long userId)
		throws Exception {

		return new AccountMember();
	}

	@Override
	@Consumes({"application/json", "application/xml"})
	@PATCH
	@Parameters(
		value = {
			@Parameter(in = ParameterIn.PATH, name = "externalReferenceCode"),
			@Parameter(in = ParameterIn.PATH, name = "userId")
		}
	)
	@Path(
		"/accounts/by-externalReferenceCode/{externalReferenceCode}/accountMembers/{userId}"
	)
	@Produces({"application/json", "application/xml"})
	@Tags(value = {@Tag(name = "AccountMember")})
	public Response patchAccountByExternalReferenceCodeAccountMember(
			@NotNull @Parameter(hidden = true)
			@PathParam("externalReferenceCode") String externalReferenceCode,
			@NotNull @Parameter(hidden = true) @PathParam("userId") Long userId,
			AccountMember accountMember)
		throws Exception {

		Response.ResponseBuilder responseBuilder = Response.ok();

		return responseBuilder.build();
	}

	@Override
	@GET
	@Parameters(
		value = {
			@Parameter(in = ParameterIn.PATH, name = "id"),
			@Parameter(in = ParameterIn.QUERY, name = "page"),
			@Parameter(in = ParameterIn.QUERY, name = "pageSize")
		}
	)
	@Path("/accounts/{id}/accountMembers/")
	@Produces({"application/json", "application/xml"})
	@Tags(value = {@Tag(name = "AccountMember")})
	public Page<AccountMember> getAccountIdAccountMembersPage(
			@NotNull @Parameter(hidden = true) @PathParam("id") Long id,
			@Context Pagination pagination)
		throws Exception {

		return Page.of(Collections.emptyList());
	}

	@Override
	@Consumes({"application/json", "application/xml"})
	@POST
	@Parameters(value = {@Parameter(in = ParameterIn.PATH, name = "id")})
	@Path("/accounts/{id}/accountMembers/")
	@Produces({"application/json", "application/xml"})
	@Tags(value = {@Tag(name = "AccountMember")})
	public AccountMember postAccountIdAccountMember(
			@NotNull @Parameter(hidden = true) @PathParam("id") Long id,
			AccountMember accountMember)
		throws Exception {

		return new AccountMember();
	}

	@Override
	@DELETE
	@Parameters(
		value = {
			@Parameter(in = ParameterIn.PATH, name = "id"),
			@Parameter(in = ParameterIn.PATH, name = "userId")
		}
	)
	@Path("/accounts/{id}/accountMembers/{userId}")
	@Produces({"application/json", "application/xml"})
	@Tags(value = {@Tag(name = "AccountMember")})
	public Response deleteAccountIdAccountMember(
			@NotNull @Parameter(hidden = true) @PathParam("id") Long id,
			@NotNull @Parameter(hidden = true) @PathParam("userId") Long userId)
		throws Exception {

		Response.ResponseBuilder responseBuilder = Response.ok();

		return responseBuilder.build();
	}

	@Override
	@GET
	@Parameters(
		value = {
			@Parameter(in = ParameterIn.PATH, name = "id"),
			@Parameter(in = ParameterIn.PATH, name = "userId")
		}
	)
	@Path("/accounts/{id}/accountMembers/{userId}")
	@Produces({"application/json", "application/xml"})
	@Tags(value = {@Tag(name = "AccountMember")})
	public AccountMember getAccountIdAccountMember(
			@NotNull @Parameter(hidden = true) @PathParam("id") Long id,
			@NotNull @Parameter(hidden = true) @PathParam("userId") Long userId)
		throws Exception {

		return new AccountMember();
	}

	@Override
	@Consumes({"application/json", "application/xml"})
	@PATCH
	@Parameters(
		value = {
			@Parameter(in = ParameterIn.PATH, name = "id"),
			@Parameter(in = ParameterIn.PATH, name = "userId")
		}
	)
	@Path("/accounts/{id}/accountMembers/{userId}")
	@Produces({"application/json", "application/xml"})
	@Tags(value = {@Tag(name = "AccountMember")})
	public Response patchAccountIdAccountMember(
			@NotNull @Parameter(hidden = true) @PathParam("id") Long id,
			@NotNull @Parameter(hidden = true) @PathParam("userId") Long userId,
			AccountMember accountMember)
		throws Exception {

		Response.ResponseBuilder responseBuilder = Response.ok();

		return responseBuilder.build();
	}

	public void setContextCompany(Company contextCompany) {
		this.contextCompany = contextCompany;
	}

	protected void preparePatch(
		AccountMember accountMember, AccountMember existingAccountMember) {
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