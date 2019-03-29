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
import com.liferay.headless.commerce.admin.pricing.dto.v1_0.TierPrice;
import com.liferay.headless.commerce.admin.pricing.internal.util.v1_0.PriceEntryHelper;
import com.liferay.headless.commerce.admin.pricing.internal.util.v1_0.TierPriceHelper;
import com.liferay.headless.commerce.admin.pricing.resource.v1_0.PriceEntryResource;
import com.liferay.portal.vulcan.pagination.Page;
import com.liferay.portal.vulcan.pagination.Pagination;

import javax.validation.constraints.NotNull;

import javax.ws.rs.core.Response;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.osgi.service.component.annotations.ServiceScope;

/**
 * @author Zoltán Takács
 */
@Component(
	properties = "OSGI-INF/liferay/rest/v1_0/price-entry.properties",
	scope = ServiceScope.PROTOTYPE, service = PriceEntryResource.class
)
public class PriceEntryResourceImpl extends BasePriceEntryResourceImpl {

	@Override
	public Response deletePriceEntry(@NotNull String id) throws Exception {
		_priceEntryHelper.deleteCommercePriceEntry(
			id, contextCompany.getCompanyId());

		Response.ResponseBuilder responseBuilder = Response.noContent();

		return responseBuilder.build();
	}

	@Override
	public PriceEntry getPriceEntry(@NotNull String id) throws Exception {
		return _priceEntryHelper.getPriceEntry(
			id, contextCompany.getCompanyId());
	}

	@Override
	public Page<TierPrice> getTierPrices(
			@NotNull String id, Pagination pagination)
		throws Exception {

		return _tierPriceHelper.getTierPrices(id, contextCompany, pagination);
	}

	@Override
	public Response updatePriceEntry(@NotNull String id, PriceEntry priceEntry)
		throws Exception {

		_priceEntryHelper.updateCommercePriceEntry(
			id, contextCompany.getCompanyId(), priceEntry);

		Response.ResponseBuilder responseBuilder = Response.noContent();

		return responseBuilder.build();
	}

	@Override
	public TierPrice upsertTierPrice(@NotNull String id, TierPrice tierPrice)
		throws Exception {

		return _tierPriceHelper.upsertCommerceTierPriceEntry(
			id, tierPrice, contextCompany);
	}

	@Reference
	private PriceEntryHelper _priceEntryHelper;

	@Reference
	private TierPriceHelper _tierPriceHelper;

}