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

import com.liferay.headless.commerce.admin.pricing.dto.v1_0.TierPrice;
import com.liferay.headless.commerce.admin.pricing.internal.util.v1_0.TierPriceHelper;
import com.liferay.headless.commerce.admin.pricing.resource.v1_0.TierPriceResource;

import javax.validation.constraints.NotNull;

import javax.ws.rs.core.Response;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.osgi.service.component.annotations.ServiceScope;

/**
 * @author Zoltán Takács
 */
@Component(
	properties = "OSGI-INF/liferay/rest/v1_0/tier-price.properties",
	scope = ServiceScope.PROTOTYPE, service = TierPriceResource.class
)
public class TierPriceResourceImpl extends BaseTierPriceResourceImpl {

	@Override
	public Response deleteTierPrice(@NotNull String id) throws Exception {
		_tierPriceHelper.deleteCommerceTierPriceEntry(
			id, contextCompany.getCompanyId());

		Response.ResponseBuilder responseBuilder = Response.noContent();

		return responseBuilder.build();
	}

	@Override
	public TierPrice getTierPrice(@NotNull String id) throws Exception {
		return _tierPriceHelper.getTierPrice(id, contextCompany.getCompanyId());
	}

	@Override
	public Response updateTierPrice(@NotNull String id, TierPrice tierPrice)
		throws Exception {

		_tierPriceHelper.updateCommerceTierPriceEntry(
			id, contextCompany.getCompanyId(), tierPrice);

		Response.ResponseBuilder responseBuilder = Response.noContent();

		return responseBuilder.build();
	}

	@Reference
	private TierPriceHelper _tierPriceHelper;

}