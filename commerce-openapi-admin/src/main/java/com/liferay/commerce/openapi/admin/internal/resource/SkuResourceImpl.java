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

import com.liferay.commerce.openapi.admin.internal.resource.util.InventoryHelper;
import com.liferay.commerce.openapi.admin.model.CollectionDTO;
import com.liferay.commerce.openapi.admin.model.InventoryDTO;
import com.liferay.commerce.openapi.admin.model.SkuDTO;
import com.liferay.commerce.openapi.admin.resource.SkuResource;
import com.liferay.commerce.openapi.core.context.Pagination;
import com.liferay.portal.kernel.model.Company;

import java.util.Locale;

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
	scope = ServiceScope.PROTOTYPE, service = SkuResource.class
)
@Generated(value = "OSGiRESTModuleGenerator")
public class SkuResourceImpl implements SkuResource {

	@Override
	public Response deleteSku(String id, long groupId, Locale locale)
		throws Exception {

		Response.ResponseBuilder responseBuilder = Response.noContent();

		return responseBuilder.build();
	}

	@Override
	public CollectionDTO<InventoryDTO> getInventories(
			String id, Pagination pagination)
		throws Exception {

		return _inventoryHelper.getInventories(id, _company, pagination);
	}

	@Override
	public SkuDTO getSku(String id, long groupId, Locale locale)
		throws Exception {

		return new SkuDTO();
	}

	@Override
	public Response updateSku(
			String id, long groupId, SkuDTO skuDTO, Locale locale)
		throws Exception {

		Response.ResponseBuilder responseBuilder = Response.accepted();

		return responseBuilder.build();
	}

	@Override
	public InventoryDTO upsertInventory(
			String id, long groupId, InventoryDTO inventoryDTO)
		throws Exception {

		return _inventoryHelper.upsertInventory(
			id, groupId, inventoryDTO, _company);
	}

	@Context
	private Company _company;

	@Reference
	private InventoryHelper _inventoryHelper;

}