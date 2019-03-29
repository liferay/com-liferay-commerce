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

package com.liferay.headless.commerce.admin.pricing.internal.resource.v1_0;

import com.liferay.headless.commerce.admin.pricing.dto.v1_0.PriceEntry;
import com.liferay.headless.commerce.admin.pricing.dto.v1_0.PriceList;
import com.liferay.headless.commerce.admin.pricing.internal.util.v1_0.PriceEntryHelper;
import com.liferay.headless.commerce.admin.pricing.internal.util.v1_0.PriceListHelper;
import com.liferay.headless.commerce.admin.pricing.resource.v1_0.PriceListResource;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.vulcan.pagination.Page;
import com.liferay.portal.vulcan.pagination.Pagination;

import javax.validation.constraints.NotNull;

import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.osgi.service.component.annotations.ServiceScope;

/**
 * @author Zoltán Takács
 */
@Component(
	properties = "OSGI-INF/liferay/rest/v1_0/price-list.properties",
	scope = ServiceScope.PROTOTYPE, service = PriceListResource.class
)
public class PriceListResourceImpl extends BasePriceListResourceImpl {

	@Override
	public Response deletePriceList(@NotNull String id) throws Exception {
		_priceListHelper.deletePriceList(id, _user, contextCompany);

		Response.ResponseBuilder responseBuilder = Response.noContent();

		return responseBuilder.build();
	}

	@Override
	public Page<PriceEntry> getPriceEntries(
			@NotNull String id, Pagination pagination)
		throws Exception {

		return _priceEntryHelper.getPriceEntries(
			id, contextCompany, pagination);
	}

	@Override
	public PriceList getPriceList(@NotNull String id) throws Exception {
		return _priceListHelper.getPriceList(
			id, contextAcceptLanguage, contextCompany);
	}

	@Override
	public Page<PriceList> getPriceLists(
			@NotNull Long groupId, Pagination pagination)
		throws Exception {

		return _priceListHelper.getPriceLists(
			groupId, contextAcceptLanguage, pagination);
	}

	@Override
	public Response updatePriceList(@NotNull String id, PriceList priceList)
		throws Exception {

		_priceListHelper.updatePriceList(
			id, priceList, contextAcceptLanguage, contextCompany);

		Response.ResponseBuilder responseBuilder = Response.noContent();

		return responseBuilder.build();
	}

	@Override
	public PriceEntry upsertPriceEntry(
			@NotNull String id, PriceEntry priceEntry)
		throws Exception {

		return _priceEntryHelper.upsertCommercePriceEntry(
			id, priceEntry, contextCompany);
	}

	@Override
	public PriceList upsertPriceList(@NotNull Long groupId, PriceList priceList)
		throws Exception {

		return _priceListHelper.upsertPriceList(
			groupId, priceList, _user, contextAcceptLanguage);
	}

	@Reference
	private PriceEntryHelper _priceEntryHelper;

	@Reference
	private PriceListHelper _priceListHelper;

	@Context
	private User _user;

}