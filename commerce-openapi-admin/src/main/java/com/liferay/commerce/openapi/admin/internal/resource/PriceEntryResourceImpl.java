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
import com.liferay.commerce.openapi.admin.model.PriceEntryDTO;
import com.liferay.commerce.openapi.admin.resource.PriceEntryResource;

import java.util.Arrays;

import javax.annotation.Generated;

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
	service = PriceEntryResource.class
)
@Generated(value = "OSGiRESTModuleGenerator")
public class PriceEntryResourceImpl implements PriceEntryResource {

	@Override
	public Response deletePriceEntry(String id, long groupId) {
		Response.ResponseBuilder responseBuilder = Response.noContent();

		return responseBuilder.build();
	}

	@Override
	public PriceEntryDTO getPriceEntry(String id, long groupId) {
		return new PriceEntryDTO();
	}

	@Override
	public CollectionDTO<PriceEntryDTO> getPriceEntrys(long groupId) {
		return new CollectionDTO(Arrays.asList(new PriceEntryDTO()), 0);
	}

	@Override
	public Response updatePriceEntry(
		String id, long groupId, PriceEntryDTO priceEntryDTO) {

		Response.ResponseBuilder responseBuilder = Response.ok();

		return responseBuilder.build();
	}

	@Override
	public PriceEntryDTO upsertPriceEntry(
		long groupId, PriceEntryDTO priceEntryDTO) {

		return new PriceEntryDTO();
	}

}