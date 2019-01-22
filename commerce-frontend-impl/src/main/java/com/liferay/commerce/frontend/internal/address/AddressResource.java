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

package com.liferay.commerce.frontend.internal.address;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import com.liferay.commerce.context.CommerceContextFactory;
import com.liferay.commerce.frontend.internal.address.model.CountryModel;
import com.liferay.commerce.frontend.internal.address.model.RegionModel;
import com.liferay.commerce.model.CommerceCountry;
import com.liferay.commerce.model.CommerceRegion;
import com.liferay.commerce.service.CommerceCountryService;
import com.liferay.commerce.service.CommerceRegionService;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.theme.ThemeDisplay;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Alessio Antonio Rendina
 */
@Component(service = AddressResource.class)
public class AddressResource {

	@GET
	@Path("/address/billing-countries/{groupId}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getBillingCommerceCountries(
		@PathParam("groupId") long groupId,
		@Context ThemeDisplay themeDisplay) {

		List<CommerceCountry> commerceCountries =
			_commerceCountryService.getBillingCommerceCountries(
				groupId, true, true);

		return _getCommerceCountries(
			commerceCountries, themeDisplay.getLanguageId());
	}

	@GET
	@Path("/address/regions/{countryId}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getCommerceRegions(
		@PathParam("countryId") long commerceCountryId,
		@Context ThemeDisplay themeDisplay) {

		List<RegionModel> regionModels = new ArrayList<>();

		List<CommerceRegion> commerceRegions =
			_commerceRegionService.getCommerceRegions(commerceCountryId, true);

		for (CommerceRegion commerceRegion : commerceRegions) {
			regionModels.add(
				new RegionModel(
					commerceRegion.getCommerceRegionId(),
					commerceRegion.getName()));
		}

		try {
			String json = _OBJECT_MAPPER.writeValueAsString(regionModels);

			return Response.ok(
				json, MediaType.APPLICATION_JSON
			).build();
		}
		catch (JsonProcessingException jpe) {
			_log.error(jpe, jpe);
		}

		return Response.status(
			Response.Status.INTERNAL_SERVER_ERROR
		).build();
	}

	@GET
	@Path("/address/shipping-countries/{groupId}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getShippingCommerceCountries(
		@PathParam("groupId") long groupId,
		@Context ThemeDisplay themeDisplay) {

		List<CommerceCountry> commerceCountries =
			_commerceCountryService.getShippingCommerceCountries(
				groupId, true, true);

		return _getCommerceCountries(
			commerceCountries, themeDisplay.getLanguageId());
	}

	private Response _getCommerceCountries(
		List<CommerceCountry> commerceCountries, String languageId) {

		List<CountryModel> countryModels = new ArrayList<>();

		for (CommerceCountry commerceCountry : commerceCountries) {
			countryModels.add(
				new CountryModel(
					commerceCountry.getCommerceCountryId(),
					commerceCountry.getName(languageId)));
		}

		try {
			String json = _OBJECT_MAPPER.writeValueAsString(commerceCountries);

			return Response.ok(
				json, MediaType.APPLICATION_JSON
			).build();
		}
		catch (JsonProcessingException jpe) {
			_log.error(jpe, jpe);
		}

		return Response.status(
			Response.Status.INTERNAL_SERVER_ERROR
		).build();
	}

	private static final ObjectMapper _OBJECT_MAPPER = new ObjectMapper() {
		{
			configure(MapperFeature.SORT_PROPERTIES_ALPHABETICALLY, true);
			enable(SerializationFeature.INDENT_OUTPUT);
		}
	};

	private static final Log _log = LogFactoryUtil.getLog(
		AddressResource.class);

	@Reference
	private CommerceContextFactory _commerceContextFactory;

	@Reference
	private CommerceCountryService _commerceCountryService;

	@Reference
	private CommerceRegionService _commerceRegionService;

}