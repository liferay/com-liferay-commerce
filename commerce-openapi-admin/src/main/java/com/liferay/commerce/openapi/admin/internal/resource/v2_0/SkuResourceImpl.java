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

package com.liferay.commerce.openapi.admin.internal.resource.v2_0;

import com.liferay.commerce.openapi.admin.internal.resource.util.v2_0.InventoryHelper;
import com.liferay.commerce.openapi.admin.internal.resource.util.v2_0.SKUHelper;
import com.liferay.commerce.openapi.admin.model.v2_0.InventoryDTO;
import com.liferay.commerce.openapi.admin.model.v2_0.SkuDTO;
import com.liferay.commerce.openapi.admin.resource.v2_0.SkuResource;
import com.liferay.commerce.openapi.core.annotation.Status;
import com.liferay.commerce.openapi.core.context.Language;
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
		JaxrsWhiteboardConstants.JAX_RS_RESOURCE + "=true", "api.version=v2.0"
	},
	scope = ServiceScope.PROTOTYPE, service = SkuResource.class
)
public class SkuResourceImpl implements SkuResource {

	@Override
	@RequiresScope("CommerceOpenApiAdmin.write")
	public Response deleteSku(String id) throws Exception {
		_skuHelper.deleteSKU(id, _company);

		Response.ResponseBuilder responseBuilder = Response.noContent();

		return responseBuilder.build();
	}

	@Override
	@RequiresScope("CommerceOpenApiAdmin.read")
	public CollectionDTO<InventoryDTO> getInventorys(
			String id, Pagination pagination)
		throws Exception {

		return _inventoryHelper.getInventories(id, _company, pagination);
	}

	@Override
	@RequiresScope("CommerceOpenApiAdmin.read")
	public SkuDTO getSku(String id) throws Exception {
		return _skuHelper.getSku(id, _company);
	}

	@Override
	@RequiresScope("CommerceOpenApiAdmin.write")
	public Response updateSku(
			String id, Long groupId, SkuDTO skuDTO, Language language)
		throws Exception {

		_skuHelper.updateSKU(id, groupId, skuDTO, _company);

		Response.ResponseBuilder responseBuilder = Response.accepted();

		return responseBuilder.build();
	}

	@Override
	@RequiresScope("CommerceOpenApiAdmin.write")
	@Status(Response.Status.CREATED)
	public InventoryDTO upsertInventory(
			String id, Long groupId, InventoryDTO inventoryDTO)
		throws Exception {

		return _inventoryHelper.upsertInventory(
			id, groupId, inventoryDTO, _company);
	}

	@Context
	private Company _company;

	@Reference
	private InventoryHelper _inventoryHelper;

	@Reference
	private SKUHelper _skuHelper;

}