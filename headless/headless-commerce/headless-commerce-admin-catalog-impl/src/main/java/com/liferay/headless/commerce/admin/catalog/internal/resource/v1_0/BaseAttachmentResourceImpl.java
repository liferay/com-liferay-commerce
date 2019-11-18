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

import com.liferay.headless.commerce.admin.catalog.dto.v1_0.Attachment;
import com.liferay.headless.commerce.admin.catalog.dto.v1_0.AttachmentBase64;
import com.liferay.headless.commerce.admin.catalog.dto.v1_0.AttachmentUrl;
import com.liferay.headless.commerce.admin.catalog.resource.v1_0.AttachmentResource;
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
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;

/**
 * @author Zoltán Takács
 * @generated
 */
@Generated("")
@Path("/v1.0")
public abstract class BaseAttachmentResourceImpl implements AttachmentResource {

	@Override
	@Consumes({"application/json", "application/xml"})
	@POST
	@Parameters(value = {@Parameter(in = ParameterIn.PATH, name = "id")})
	@Path("/products/{id}/attachments/by-base64/")
	@Produces({"application/json", "application/xml"})
	@Tags(value = {@Tag(name = "Attachment")})
	public Attachment postProductIdAttachmentByBase64(
			@NotNull @Parameter(hidden = true) @PathParam("id") Long id,
			AttachmentBase64 attachmentBase64)
		throws Exception {

		return new Attachment();
	}

	@Override
	@Consumes({"application/json", "application/xml"})
	@POST
	@Parameters(value = {@Parameter(in = ParameterIn.PATH, name = "id")})
	@Path("/products/{id}/attachments/by-url/")
	@Produces({"application/json", "application/xml"})
	@Tags(value = {@Tag(name = "Attachment")})
	public Attachment postProductIdAttachmentByUrl(
			@NotNull @Parameter(hidden = true) @PathParam("id") Long id,
			AttachmentUrl attachmentUrl)
		throws Exception {

		return new Attachment();
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
	@Path("/products/{id}/attachments/")
	@Produces({"application/json", "application/xml"})
	@Tags(value = {@Tag(name = "Attachment")})
	public Page<Attachment> getProductIdAttachmentsPage(
			@NotNull @Parameter(hidden = true) @PathParam("id") Long id,
			@Context Pagination pagination)
		throws Exception {

		return Page.of(Collections.emptyList());
	}

	@Override
	@Consumes({"application/json", "application/xml"})
	@POST
	@Parameters(value = {@Parameter(in = ParameterIn.PATH, name = "id")})
	@Path("/products/{id}/attachments/")
	@Produces({"application/json", "application/xml"})
	@Tags(value = {@Tag(name = "Attachment")})
	public Attachment postProductIdAttachment(
			@NotNull @Parameter(hidden = true) @PathParam("id") Long id,
			Attachment attachment)
		throws Exception {

		return new Attachment();
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
		"/products/by-externalReferenceCode/{externalReferenceCode}/attachments/by-base64/"
	)
	@Produces({"application/json", "application/xml"})
	@Tags(value = {@Tag(name = "Attachment")})
	public Attachment postProductByExternalReferenceCodeAttachmentByBase64(
			@NotNull @Parameter(hidden = true)
			@PathParam("externalReferenceCode") String externalReferenceCode,
			AttachmentBase64 attachmentBase64)
		throws Exception {

		return new Attachment();
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
		"/products/by-externalReferenceCode/{externalReferenceCode}/attachments/by-url/"
	)
	@Produces({"application/json", "application/xml"})
	@Tags(value = {@Tag(name = "Attachment")})
	public Attachment postProductByExternalReferenceCodeAttachmentByUrl(
			@NotNull @Parameter(hidden = true)
			@PathParam("externalReferenceCode") String externalReferenceCode,
			AttachmentUrl attachmentUrl)
		throws Exception {

		return new Attachment();
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
		"/products/by-externalReferenceCode/{externalReferenceCode}/attachments/"
	)
	@Produces({"application/json", "application/xml"})
	@Tags(value = {@Tag(name = "Attachment")})
	public Page<Attachment> getProductByExternalReferenceCodeAttachmentsPage(
			@NotNull @Parameter(hidden = true)
			@PathParam("externalReferenceCode") String externalReferenceCode,
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
		"/products/by-externalReferenceCode/{externalReferenceCode}/attachments/"
	)
	@Produces({"application/json", "application/xml"})
	@Tags(value = {@Tag(name = "Attachment")})
	public Attachment postProductByExternalReferenceCodeAttachment(
			@NotNull @Parameter(hidden = true)
			@PathParam("externalReferenceCode") String externalReferenceCode,
			Attachment attachment)
		throws Exception {

		return new Attachment();
	}

	@Override
	@Consumes({"application/json", "application/xml"})
	@POST
	@Parameters(value = {@Parameter(in = ParameterIn.PATH, name = "id")})
	@Path("/products/{id}/images/by-base64/")
	@Produces({"application/json", "application/xml"})
	@Tags(value = {@Tag(name = "Attachment")})
	public Attachment postProductIdImageByBase64(
			@NotNull @Parameter(hidden = true) @PathParam("id") Long id,
			AttachmentBase64 attachmentBase64)
		throws Exception {

		return new Attachment();
	}

	@Override
	@Consumes({"application/json", "application/xml"})
	@POST
	@Parameters(value = {@Parameter(in = ParameterIn.PATH, name = "id")})
	@Path("/products/{id}/images/by-url/")
	@Produces({"application/json", "application/xml"})
	@Tags(value = {@Tag(name = "Attachment")})
	public Attachment postProductIdImageByUrl(
			@NotNull @Parameter(hidden = true) @PathParam("id") Long id,
			AttachmentUrl attachmentUrl)
		throws Exception {

		return new Attachment();
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
	@Path("/products/{id}/images/")
	@Produces({"application/json", "application/xml"})
	@Tags(value = {@Tag(name = "Attachment")})
	public Page<Attachment> getProductIdImagesPage(
			@NotNull @Parameter(hidden = true) @PathParam("id") Long id,
			@Context Pagination pagination)
		throws Exception {

		return Page.of(Collections.emptyList());
	}

	@Override
	@Consumes({"application/json", "application/xml"})
	@POST
	@Parameters(value = {@Parameter(in = ParameterIn.PATH, name = "id")})
	@Path("/products/{id}/images/")
	@Produces({"application/json", "application/xml"})
	@Tags(value = {@Tag(name = "Attachment")})
	public Attachment postProductIdImage(
			@NotNull @Parameter(hidden = true) @PathParam("id") Long id,
			Attachment attachment)
		throws Exception {

		return new Attachment();
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
	@Path("/products/by-externalReferenceCode/{externalReferenceCode}/images/")
	@Produces({"application/json", "application/xml"})
	@Tags(value = {@Tag(name = "Attachment")})
	public Page<Attachment> getProductByExternalReferenceCodeImagesPage(
			@NotNull @Parameter(hidden = true)
			@PathParam("externalReferenceCode") String externalReferenceCode,
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
	@Path("/products/by-externalReferenceCode/{externalReferenceCode}/images/")
	@Produces({"application/json", "application/xml"})
	@Tags(value = {@Tag(name = "Attachment")})
	public Attachment postProductByExternalReferenceCodeImage(
			@NotNull @Parameter(hidden = true)
			@PathParam("externalReferenceCode") String externalReferenceCode,
			Attachment attachment)
		throws Exception {

		return new Attachment();
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
		"/products/by-externalReferenceCode/{externalReferenceCode}/images/by-base64/"
	)
	@Produces({"application/json", "application/xml"})
	@Tags(value = {@Tag(name = "Attachment")})
	public Attachment postProductByExternalReferenceCodeImageByBase64(
			@NotNull @Parameter(hidden = true)
			@PathParam("externalReferenceCode") String externalReferenceCode,
			AttachmentBase64 attachmentBase64)
		throws Exception {

		return new Attachment();
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
		"/products/by-externalReferenceCode/{externalReferenceCode}/images/by-url/"
	)
	@Produces({"application/json", "application/xml"})
	@Tags(value = {@Tag(name = "Attachment")})
	public Attachment postProductByExternalReferenceCodeImageByUrl(
			@NotNull @Parameter(hidden = true)
			@PathParam("externalReferenceCode") String externalReferenceCode,
			AttachmentUrl attachmentUrl)
		throws Exception {

		return new Attachment();
	}

	public void setContextCompany(Company contextCompany) {
		this.contextCompany = contextCompany;
	}

	protected void preparePatch(
		Attachment attachment, Attachment existingAttachment) {
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