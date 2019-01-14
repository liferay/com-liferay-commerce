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

package com.liferay.commerce.openapi.admin.internal.resource;

import com.liferay.commerce.openapi.admin.model.ProductDTO;
import com.liferay.commerce.openapi.admin.resource.ProductResource;

import javax.annotation.Generated;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.jaxrs.whiteboard.JaxrsWhiteboardConstants;

/**
 * @author Igor Beslic
 */
@Component(
	immediate = true,
	property = {
		JaxrsWhiteboardConstants.JAX_RS_APPLICATION_SELECT + "=(osgi.jaxrs.name=CommerceOpenApiAdmin.Rest)",
		JaxrsWhiteboardConstants.JAX_RS_RESOURCE + "=true", "api.version=1.0"
	},
	service = ProductResource.class
)
@Generated(value = "OSGiRESTModuleGenerator")
public class ProductResourceImpl implements ProductResource {

	@Override
	public Response deleteProduct(String id) {
		Response.ResponseBuilder responseBuilder = Response.noContent();

		return responseBuilder.build();
	}

	@Override
	public Response getProduct(String id, long groupId) {
		return Response.ok(
			"Here goes output", MediaType.APPLICATION_JSON
		).build();
	}

	@Override
	public Response getProducts(long groupId, int page, int pageSize) {
		return Response.ok(
			"Here goes output", MediaType.APPLICATION_JSON
		).build();
	}

	@Override
	public Response updateProduct(String id, ProductDTO productDTO) {
		return Response.ok(
			"Here goes output", MediaType.APPLICATION_JSON
		).build();
	}

	@Override
	public Response upsertProduct(ProductDTO productDTO) {
		return Response.ok(
			"Here goes output", MediaType.APPLICATION_JSON
		).build();
	}

}