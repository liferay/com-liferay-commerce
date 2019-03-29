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

package com.liferay.headless.commerce.admin.catalog.internal.resource.v1_0;

import com.liferay.headless.commerce.admin.catalog.dto.v1_0.Sku;
import com.liferay.headless.commerce.admin.catalog.internal.util.v1_0.SKUHelper;
import com.liferay.headless.commerce.admin.catalog.resource.v1_0.SkuResource;

import javax.validation.constraints.NotNull;

import javax.ws.rs.core.Response;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.osgi.service.component.annotations.ServiceScope;

/**
 * @author Zoltán Takács
 */
@Component(
	properties = "OSGI-INF/liferay/rest/v1_0/sku.properties",
	scope = ServiceScope.PROTOTYPE, service = SkuResource.class
)
public class SkuResourceImpl extends BaseSkuResourceImpl {

	@Override
	public Response deleteSku(@NotNull String id) throws Exception {
		_skuHelper.deleteSKU(id, contextCompany);

		Response.ResponseBuilder responseBuilder = Response.noContent();

		return responseBuilder.build();
	}

	@Override
	public Sku getSku(@NotNull String id) throws Exception {
		return _skuHelper.getSku(id, contextCompany);
	}

	@Override
	public Response updateSku(@NotNull String id, Sku sku) throws Exception {
		_skuHelper.updateSKU(id, sku, contextCompany);

		Response.ResponseBuilder responseBuilder = Response.accepted();

		return responseBuilder.build();
	}

	@Reference
	private SKUHelper _skuHelper;

}