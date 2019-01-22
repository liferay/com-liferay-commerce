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

import com.liferay.commerce.openapi.admin.internal.resource.util.PriceEntryHelper;
import com.liferay.commerce.openapi.admin.model.CollectionDTO;
import com.liferay.commerce.openapi.admin.model.PriceEntryDTO;
import com.liferay.commerce.openapi.admin.resource.PriceEntryResource;
import com.liferay.commerce.openapi.core.context.Pagination;
import com.liferay.portal.kernel.model.Company;

import javax.annotation.Generated;

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
		JaxrsWhiteboardConstants.JAX_RS_RESOURCE + "=true", "api.version=1.0"
	},
	scope = ServiceScope.PROTOTYPE, service = PriceEntryResource.class
)
@Generated(value = "OSGiRESTModuleGenerator")
public class PriceEntryResourceImpl implements PriceEntryResource {

	@Override
	public Response deletePriceEntry(String id) throws Exception {
		_priceEntryHelper.deleteCommercePriceEntry(id, _company.getCompanyId());

		Response.ResponseBuilder responseBuilder = Response.noContent();

		return responseBuilder.build();
	}

	@Override
	public CollectionDTO<PriceEntryDTO> getPriceEntries(
			long groupId, Pagination pagination)
		throws Exception {

		return _priceEntryHelper.getCommercePriceEntryDTOs(groupId, pagination);
	}

	@Override
	public PriceEntryDTO getPriceEntry(String id) throws Exception {
		return _priceEntryHelper.getPriceEntryDTO(id, _company.getCompanyId());
	}

	@Override
	public Response updatePriceEntry(String id, PriceEntryDTO priceEntryDTO)
		throws Exception {

		_priceEntryHelper.updateCommercePriceEntry(
			id, _company.getCompanyId(), priceEntryDTO);

		Response.ResponseBuilder responseBuilder = Response.noContent();

		return responseBuilder.build();
	}

	@Override
	public PriceEntryDTO upsertPriceEntry(
			long groupId, PriceEntryDTO priceEntryDTO)
		throws Exception {

		return _priceEntryHelper.upsertCommercePriceEntry(
			groupId, priceEntryDTO);
	}

	@Context
	private Company _company;

	@Reference
	private PriceEntryHelper _priceEntryHelper;

}