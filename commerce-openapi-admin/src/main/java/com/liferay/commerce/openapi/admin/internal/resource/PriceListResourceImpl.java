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

import com.liferay.commerce.openapi.admin.model.CollectionDTO;
import com.liferay.commerce.openapi.admin.model.PriceListDTO;
import com.liferay.commerce.openapi.admin.resource.PriceListResource;

import java.util.Arrays;

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
	service = PriceListResource.class
)
@Generated(value = "OSGiRESTModuleGenerator")
public class PriceListResourceImpl implements PriceListResource {

	@Override
	public Response deletePriceList(String id, long groupId) {
		Response.ResponseBuilder responseBuilder = Response.noContent();

		return responseBuilder.build();
	}

	@Override
	public PriceListDTO getPriceList(String id, long groupId) {
		return new PriceListDTO();
	}

	@Override
	public CollectionDTO<PriceListDTO> getPriceLists(long groupId) {
		return new CollectionDTO(Arrays.asList(new PriceListDTO()), 0);
	}

	@Override
	public Response updatePriceList(
		String id, long groupId, PriceListDTO priceListDTO) {

		return Response.ok(
			"Here goes output", MediaType.APPLICATION_JSON
		).build();
	}

	@Override
	public PriceListDTO upsertPriceList(
		long groupId, PriceListDTO priceListDTO) {

		return new PriceListDTO();
	}

}