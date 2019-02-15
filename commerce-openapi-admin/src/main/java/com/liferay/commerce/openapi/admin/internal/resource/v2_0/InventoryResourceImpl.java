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
import com.liferay.commerce.openapi.admin.model.v2_0.InventoryDTO;
import com.liferay.commerce.openapi.admin.resource.v2_0.InventoryResource;
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
	scope = ServiceScope.PROTOTYPE, service = InventoryResource.class
)
public class InventoryResourceImpl implements InventoryResource {

	@Override
	@RequiresScope("CommerceOpenApiAdmin.write")
	public Response deleteInventory(String id) throws Exception {
		_inventoryHelper.deleteInventory(id);

		Response.ResponseBuilder responseBuilder = Response.noContent();

		return responseBuilder.build();
	}

	@Override
	@RequiresScope("CommerceOpenApiAdmin.read")
	public InventoryDTO getInventory(String id) throws Exception {
		return _inventoryHelper.getInventory(id);
	}

	@Override
	@RequiresScope("CommerceOpenApiAdmin.write")
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