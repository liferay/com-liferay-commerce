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
import com.liferay.commerce.openapi.admin.model.InventoryDTO;
import com.liferay.commerce.openapi.admin.resource.InventoryResource;
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
		JaxrsWhiteboardConstants.JAX_RS_RESOURCE + "=true", "api.version=v1.0"
	},
	scope = ServiceScope.PROTOTYPE, service = InventoryResource.class
)
@Generated(value = "OSGiRESTModuleGenerator")
public class InventoryResourceImpl implements InventoryResource {

	@Override
	public Response deleteInventory(String id) throws Exception {
		_inventoryHelper.deleteInventory(id);

		Response.ResponseBuilder responseBuilder = Response.noContent();

		return responseBuilder.build();
	}

	@Override
	public InventoryDTO getInventory(String id) throws Exception {
		return _inventoryHelper.getInventory(id);
	}

	@Override
	public Response updateInventory(
			String id, Long groupId, InventoryDTO inventoryDTO)
		throws Exception {

		_inventoryHelper.updateInventory(id, groupId, inventoryDTO);

		Response.ResponseBuilder responseBuilder = Response.accepted();

		return responseBuilder.build();
	}

	@Context
	private Company _company;

	@Reference
	private InventoryHelper _inventoryHelper;

}