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

import com.liferay.commerce.openapi.admin.internal.resource.util.PriceListHelper;
import com.liferay.commerce.openapi.admin.model.CollectionDTO;
import com.liferay.commerce.openapi.admin.model.PriceListDTO;
import com.liferay.commerce.openapi.admin.resource.PriceListResource;
import com.liferay.commerce.openapi.core.context.Pagination;
import com.liferay.portal.kernel.model.Company;
import com.liferay.portal.kernel.model.User;

import java.util.Locale;

import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.osgi.service.component.annotations.ServiceScope;
import org.osgi.service.jaxrs.whiteboard.JaxrsWhiteboardConstants;

/**
 * @author Zoltán Takács
 */
@Component(
	property = {
		JaxrsWhiteboardConstants.JAX_RS_APPLICATION_SELECT + "=(osgi.jaxrs.name=CommerceOpenApiAdmin.Rest)",
		JaxrsWhiteboardConstants.JAX_RS_RESOURCE + "=true", "api.version=v1.0"
	},
	scope = ServiceScope.PROTOTYPE, service = PriceListResource.class
)
public class PriceListResourceImpl implements PriceListResource {

	@Override
	public Response deletePriceList(String id, Long groupId, Locale locale)
		throws Exception {

		_priceListHelper.deletePriceList(id, groupId, _user, locale, _company);

		Response.ResponseBuilder responseBuilder = Response.noContent();

		return responseBuilder.build();
	}

	@Override
	public PriceListDTO getPriceList(String id, Long groupId, Locale locale)
		throws Exception {

		return _priceListHelper.getPriceList(
			id, groupId, _user, locale, _company);
	}

	@Override
	public CollectionDTO<PriceListDTO> getPriceLists(
			Long groupId, Locale locale, Pagination pagination)
		throws Exception {

		return _priceListHelper.getPriceLists(
			groupId, _user, locale, _company, pagination);
	}

	@Override
	public Response updatePriceList(
			String id, Long groupId, PriceListDTO priceListDTO, Locale locale)
		throws Exception {

		_priceListHelper.updatePriceList(
			id, groupId, priceListDTO, _user, locale, _company);

		Response.ResponseBuilder responseBuilder = Response.noContent();

		return responseBuilder.build();
	}

	@Override
	public PriceListDTO upsertPriceList(
			Long groupId, PriceListDTO priceListDTO, Locale locale)
		throws Exception {

		return _priceListHelper.upsertPriceList(
			groupId, priceListDTO, _user, locale, _company);
	}

	@Context
	private Company _company;

	@Reference
	private PriceListHelper _priceListHelper;

	@Context
	private User _user;

}