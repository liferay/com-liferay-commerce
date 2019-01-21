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
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.model.Company;
import com.liferay.portal.kernel.model.User;

import java.util.Locale;

import javax.ws.rs.core.Response;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.osgi.service.jaxrs.whiteboard.JaxrsWhiteboardConstants;

/**
 * @author Zoltán Takács
 */
@Component(
	immediate = true,
	property = {
		JaxrsWhiteboardConstants.JAX_RS_APPLICATION_SELECT + "=(osgi.jaxrs.name=CommerceOpenApiAdmin.Rest)",
		JaxrsWhiteboardConstants.JAX_RS_RESOURCE + "=true", "api.version=1.0"
	},
	service = PriceListResource.class
)
public class PriceListResourceImpl implements PriceListResource {

	@Override
	public Response deletePriceList(
		String id, long groupId, User user, Locale locale, Company company) {

		try {
			_priceListHelper.deletePriceList(
				id, groupId, user, locale, company);
		}
		catch (PortalException pe) {
			throw new RuntimeException(pe);
		}

		Response.ResponseBuilder responseBuilder = Response.noContent();

		return responseBuilder.build();
	}

	@Override
	public PriceListDTO getPriceList(
		String id, long groupId, User user, Locale locale, Company company) {

		return _priceListHelper.getPriceList(
			id, groupId, user, locale, company);
	}

	@Override
	public CollectionDTO<PriceListDTO> getPriceLists(
		long groupId, User user, Locale locale, Company company,
		Pagination pagination) {

		try {
			return _priceListHelper.getPriceLists(
				groupId, user, locale, company, pagination);
		}
		catch (PortalException pe) {
			throw new RuntimeException(pe);
		}
	}

	@Override
	public Response updatePriceList(
		String id, long groupId, PriceListDTO priceListDTO, User user,
		Locale locale, Company company) {

		try {
			_priceListHelper.updatePriceList(
				id, groupId, priceListDTO, user, locale, company);
		}
		catch (PortalException pe) {
			throw new RuntimeException(pe);
		}

		Response.ResponseBuilder responseBuilder = Response.noContent();

		return responseBuilder.build();
	}

	@Override
	public PriceListDTO upsertPriceList(
		long groupId, PriceListDTO priceListDTO, User user, Locale locale,
		Company company) {

		try {
			return _priceListHelper.upsertPriceList(
				groupId, priceListDTO, user, locale, company);
		}
		catch (PortalException pe) {
			throw new RuntimeException(pe);
		}
	}

	@Reference
	private PriceListHelper _priceListHelper;

}