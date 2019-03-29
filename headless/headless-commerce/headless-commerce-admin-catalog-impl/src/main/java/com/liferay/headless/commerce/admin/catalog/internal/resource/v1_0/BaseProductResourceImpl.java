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
import com.liferay.headless.commerce.admin.catalog.dto.v1_0.Category;
import com.liferay.headless.commerce.admin.catalog.dto.v1_0.Product;
import com.liferay.headless.commerce.admin.catalog.dto.v1_0.ProductConfiguration;
import com.liferay.headless.commerce.admin.catalog.dto.v1_0.ProductOption;
import com.liferay.headless.commerce.admin.catalog.dto.v1_0.ProductShippingConfiguration;
import com.liferay.headless.commerce.admin.catalog.dto.v1_0.ProductSubscriptionConfiguration;
import com.liferay.headless.commerce.admin.catalog.dto.v1_0.ProductTaxConfiguration;
import com.liferay.headless.commerce.admin.catalog.dto.v1_0.Sku;
import com.liferay.headless.commerce.admin.catalog.resource.v1_0.ProductResource;
import com.liferay.petra.function.UnsafeFunction;
import com.liferay.petra.string.StringPool;
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

import java.net.URI;

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
import javax.ws.rs.core.UriBuilder;
import javax.ws.rs.core.UriInfo;

/**
 * @author Zoltán Takács
 * @generated
 */
@Generated("")
@Path("/v1.0")
public abstract class BaseProductResourceImpl implements ProductResource {

	@Override
	@DELETE
	@Path("/product/{id}")
	@Produces({"application/json", "application/xml"})
	@Tags(value = {@Tag(name = "Product")})
	public Response deleteProduct(@NotNull @PathParam("id") String id)
		throws Exception {

		Response.ResponseBuilder responseBuilder = Response.ok();

		return responseBuilder.build();
	}

	@Override
	@GET
	@Path("/product/{id}")
	@Produces({"application/json", "application/xml"})
	@Tags(value = {@Tag(name = "Product")})
	public Product getProduct(@NotNull @PathParam("id") String id)
		throws Exception {

		return new Product();
	}

	@Override
	@Consumes({"application/json", "application/xml"})
	@PUT
	@Path("/product/{id}")
	@Produces({"application/json", "application/xml"})
	@Tags(value = {@Tag(name = "Product")})
	public Response updateProduct(
			@NotNull @PathParam("id") String id, Product product)
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
	@Path("/product/{id}/productOption/")
	@Produces({"application/json", "application/xml"})
	@Tags(value = {@Tag(name = "Product")})
	public Page<ProductOption> getProductOptions(
			@NotNull @PathParam("id") String id, @Context Pagination pagination)
		throws Exception {

		return Page.of(Collections.emptyList());
	}

	@Override
	@Consumes("application/json")
	@PUT
	@Path("/product/{id}/productOption/")
	@Produces({"application/json", "application/xml"})
	@Tags(value = {@Tag(name = "Product")})
	public Response updateProductOptions(
			@NotNull @PathParam("id") String id, ProductOption[] productOptions)
		throws Exception {

		Response.ResponseBuilder responseBuilder = Response.ok();

		return responseBuilder.build();
	}

	@Override
	@GET
	@Path("/product/{id}/configuration/")
	@Produces({"application/json", "application/xml"})
	@Tags(value = {@Tag(name = "Product")})
	public ProductConfiguration getProductConfiguration(
			@NotNull @PathParam("id") String id)
		throws Exception {

		return new ProductConfiguration();
	}

	@Override
	@Consumes("application/json")
	@PUT
	@Path("/product/{id}/configuration/")
	@Produces({"application/json", "application/xml"})
	@Tags(value = {@Tag(name = "Product")})
	public Response updateProductConfiguraton(
			@NotNull @PathParam("id") String id,
			ProductConfiguration productConfiguration)
		throws Exception {

		Response.ResponseBuilder responseBuilder = Response.ok();

		return responseBuilder.build();
	}

	@Override
	@GET
	@Path("/product/{id}/shippingConfiguration/")
	@Produces({"application/json", "application/xml"})
	@Tags(value = {@Tag(name = "Product")})
	public ProductShippingConfiguration getProductShippingConfiguration(
			@NotNull @PathParam("id") String id)
		throws Exception {

		return new ProductShippingConfiguration();
	}

	@Override
	@Consumes("application/json")
	@PUT
	@Path("/product/{id}/shippingConfiguration/")
	@Produces({"application/json", "application/xml"})
	@Tags(value = {@Tag(name = "Product")})
	public Response updateProductShippingConfiguration(
			@NotNull @PathParam("id") String id,
			ProductShippingConfiguration productShippingConfiguration)
		throws Exception {

		Response.ResponseBuilder responseBuilder = Response.ok();

		return responseBuilder.build();
	}

	@Override
	@GET
	@Path("/product/{id}/taxConfiguration")
	@Produces({"application/json", "application/xml"})
	@Tags(value = {@Tag(name = "Product")})
	public ProductTaxConfiguration getProductTaxConfiguration(
			@NotNull @PathParam("id") String id)
		throws Exception {

		return new ProductTaxConfiguration();
	}

	@Override
	@Consumes("application/json")
	@PUT
	@Path("/product/{id}/taxConfiguration")
	@Produces({"application/json", "application/xml"})
	@Tags(value = {@Tag(name = "Product")})
	public Response updateProductTaxConfiguration(
			@NotNull @PathParam("id") String id,
			ProductTaxConfiguration productTaxConfiguration)
		throws Exception {

		Response.ResponseBuilder responseBuilder = Response.ok();

		return responseBuilder.build();
	}

	@Override
	@GET
	@Path("/product/{id}/subscriptionConfiguration")
	@Produces({"application/json", "application/xml"})
	@Tags(value = {@Tag(name = "Product")})
	public ProductSubscriptionConfiguration getProductSubscriptionConfiguration(
			@NotNull @PathParam("id") String id)
		throws Exception {

		return new ProductSubscriptionConfiguration();
	}

	@Override
	@Consumes("application/json")
	@PUT
	@Path("/product/{id}/subscriptionConfiguration")
	@Produces({"application/json", "application/xml"})
	@Tags(value = {@Tag(name = "Product")})
	public Response updateProductSubscriptionConfiguration(
			@NotNull @PathParam("id") String id,
			ProductSubscriptionConfiguration productSubscriptionConfiguration)
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
	@Path("/commerceAdminCatalog/{groupId}/product/")
	@Produces({"application/json", "application/xml"})
	@Tags(value = {@Tag(name = "Product")})
	public Page<Product> getProducts(
			@NotNull @PathParam("groupId") Long groupId,
			@Context Pagination pagination)
		throws Exception {

		return Page.of(Collections.emptyList());
	}

	@Override
	@Consumes({"application/json", "application/xml"})
	@POST
	@Path("/commerceAdminCatalog/{groupId}/product/")
	@Produces({"application/json", "application/xml"})
	@Tags(value = {@Tag(name = "Product")})
	public Product upsertProduct(
			@NotNull @PathParam("groupId") Long groupId, Product product)
		throws Exception {

		return new Product();
	}

	@Override
	@GET
	@Parameters(
		value = {
			@Parameter(in = ParameterIn.QUERY, name = "page"),
			@Parameter(in = ParameterIn.QUERY, name = "pageSize")
		}
	)
	@Path("/product/{id}/attachment/")
	@Produces({"application/json", "application/xml"})
	@Tags(value = {@Tag(name = "Product")})
	public Page<Attachment> getProductAttachments(
			@NotNull @PathParam("id") String id, @Context Pagination pagination)
		throws Exception {

		return Page.of(Collections.emptyList());
	}

	@Override
	@Consumes({"application/json", "application/xml"})
	@POST
	@Path("/product/{id}/attachment/")
	@Produces({"application/json", "application/xml"})
	@Tags(value = {@Tag(name = "Product")})
	public Attachment upsertProductAttachment(
			@NotNull @PathParam("id") String id, Attachment attachment)
		throws Exception {

		return new Attachment();
	}

	@Override
	@GET
	@Parameters(
		value = {
			@Parameter(in = ParameterIn.QUERY, name = "page"),
			@Parameter(in = ParameterIn.QUERY, name = "pageSize")
		}
	)
	@Path("/product/{id}/image/")
	@Produces({"application/json", "application/xml"})
	@Tags(value = {@Tag(name = "Product")})
	public Page<Attachment> getProductImages(
			@NotNull @PathParam("id") String id, @Context Pagination pagination)
		throws Exception {

		return Page.of(Collections.emptyList());
	}

	@Override
	@Consumes({"application/json", "application/xml"})
	@POST
	@Path("/product/{id}/image/")
	@Produces({"application/json", "application/xml"})
	@Tags(value = {@Tag(name = "Product")})
	public Attachment upsertProductImage(
			@NotNull @PathParam("id") String id, Attachment attachment)
		throws Exception {

		return new Attachment();
	}

	@Override
	@GET
	@Parameters(
		value = {
			@Parameter(in = ParameterIn.QUERY, name = "page"),
			@Parameter(in = ParameterIn.QUERY, name = "pageSize")
		}
	)
	@Path("/product/{id}/category/")
	@Produces({"application/json", "application/xml"})
	@Tags(value = {@Tag(name = "Product")})
	public Page<Category> getProductCategories(
			@NotNull @PathParam("id") String id, @Context Pagination pagination)
		throws Exception {

		return Page.of(Collections.emptyList());
	}

	@Override
	@Consumes("application/json")
	@PUT
	@Path("/product/{id}/category/")
	@Produces({"application/json", "application/xml"})
	@Tags(value = {@Tag(name = "Product")})
	public Response updateProductCategory(
			@NotNull @PathParam("id") String id, Category[] categories)
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
	@Path("/product/{id}/sku/")
	@Produces({"application/json", "application/xml"})
	@Tags(value = {@Tag(name = "Product")})
	public Page<Sku> getProductSkus(
			@NotNull @PathParam("id") String id, @Context Pagination pagination)
		throws Exception {

		return Page.of(Collections.emptyList());
	}

	@Override
	@Consumes({"application/json", "application/xml"})
	@POST
	@Path("/product/{id}/sku/")
	@Produces({"application/json", "application/xml"})
	@Tags(value = {@Tag(name = "Product")})
	public Sku upsertProductSku(@NotNull @PathParam("id") String id, Sku sku)
		throws Exception {

		return new Sku();
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
			BaseProductResourceImpl.class
		).build();

		URI methodURI = UriBuilder.fromMethod(
			BaseProductResourceImpl.class, methodName
		).build(
			values
		);

		return baseURIString + resourceURI.toString() + methodURI.toString();
	}

	protected void preparePatch(Product product) {
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