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

package com.liferay.commerce.openapi.admin.internal.resource.v1_0;

import com.liferay.commerce.openapi.admin.internal.resource.util.v1_0.PriceEntryHelper;
import com.liferay.commerce.openapi.admin.model.v1_0.PriceEntryDTO;
import com.liferay.commerce.openapi.admin.resource.v1_0.PriceEntryResource;
import com.liferay.commerce.openapi.core.annotation.Status;
import com.liferay.commerce.openapi.core.context.Pagination;
import com.liferay.commerce.openapi.core.model.CollectionDTO;
import com.liferay.oauth2.provider.scope.RequiresScope;
import com.liferay.portal.kernel.model.Company;

import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.osgi.service.component.annotations.ServiceScope;
import org.osgi.service.jaxrs.whiteboard.JaxrsWhiteboardConstants;

/**
 * @author Igor Beslic
 */
@Component(
	property = {
		JaxrsWhiteboardConstants.JAX_RS_APPLICATION_SELECT + "=(osgi.jaxrs.name=CommerceOpenApiAdmin.Rest)",
		JaxrsWhiteboardConstants.JAX_RS_RESOURCE + "=true", "api.version=v1.0"
	},
	scope = ServiceScope.PROTOTYPE, service = PriceEntryResource.class
)
public class PriceEntryResourceImpl implements PriceEntryResource {

	@Override
	@RequiresScope("CommerceOpenApiAdmin.write")
	public Response deletePriceEntry(String id) throws Exception {
		_priceEntryHelper.deleteCommercePriceEntry(id, _company.getCompanyId());

		Response.ResponseBuilder responseBuilder = Response.noContent();

		return responseBuilder.build();
	}

	@Override
	@RequiresScope("CommerceOpenApiAdmin.read")
	public CollectionDTO<PriceEntryDTO> getPriceEntries(
			Long groupId, Pagination pagination)
		throws Exception {

		return _priceEntryHelper.getPriceEntryDTOs(groupId, pagination);
	}

	@Override
	@RequiresScope("CommerceOpenApiAdmin.read")
	public PriceEntryDTO getPriceEntry(String id) throws Exception {
		return _priceEntryHelper.getPriceEntryDTO(id, _company.getCompanyId());
	}

	@Override
	@RequiresScope("CommerceOpenApiAdmin.write")
	public Response updatePriceEntry(String id, PriceEntryDTO priceEntryDTO)
		throws Exception {

		_priceEntryHelper.updateCommercePriceEntry(
			id, _company.getCompanyId(), priceEntryDTO);

		Response.ResponseBuilder responseBuilder = Response.noContent();

		return responseBuilder.build();
	}

	@Override
	@RequiresScope("CommerceOpenApiAdmin.write")
	@Status(Response.Status.CREATED)
	public PriceEntryDTO upsertPriceEntry(
			Long groupId, PriceEntryDTO priceEntryDTO)
		throws Exception {

		return _priceEntryHelper.upsertCommercePriceEntry(
			groupId, priceEntryDTO);
	}

	@Context
	private Company _company;

	@Reference
	private PriceEntryHelper _priceEntryHelper;

}