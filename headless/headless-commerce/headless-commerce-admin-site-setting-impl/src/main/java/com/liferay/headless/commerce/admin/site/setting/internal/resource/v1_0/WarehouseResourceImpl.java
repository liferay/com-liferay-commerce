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

package com.liferay.headless.commerce.admin.site.setting.internal.resource.v1_0;

import com.liferay.headless.commerce.admin.site.setting.dto.v1_0.Warehouse;
import com.liferay.headless.commerce.admin.site.setting.internal.util.v1_0.WarehouseHelper;
import com.liferay.headless.commerce.admin.site.setting.resource.v1_0.WarehouseResource;
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
	properties = "OSGI-INF/liferay/rest/v1_0/warehouse.properties",
	scope = ServiceScope.PROTOTYPE, service = WarehouseResource.class
)
public class WarehouseResourceImpl extends BaseWarehouseResourceImpl {

	@Override
	public Response deleteWarehouse(@NotNull Long id) throws Exception {
		_warehouseHelper.deleteWarehouse(id);

		Response.ResponseBuilder responseBuilder = Response.noContent();

		return responseBuilder.build();
	}

	@Override
	public Warehouse getWarehouse(@NotNull Long id) throws Exception {
		return _warehouseHelper.getWarehouse(id);
	}

	@Override
	public Page<Warehouse> getWarehouses(
			@NotNull Long groupId, Boolean active, Pagination pagination)
		throws Exception {

		return _warehouseHelper.getWarehouses(groupId, active, pagination);
	}

	@Override
	public Response updateWarehouse(@NotNull Long id, Warehouse warehouse)
		throws Exception {

		_warehouseHelper.updateWarehouse(id, warehouse, _user);

		Response.ResponseBuilder responseBuilder = Response.noContent();

		return responseBuilder.build();
	}

	@Override
	public Warehouse upsertWarehouse(@NotNull Long groupId, Warehouse warehouse)
		throws Exception {

		return _warehouseHelper.upsertWarehouse(groupId, warehouse, _user);
	}

	@Context
	private User _user;

	@Reference
	private WarehouseHelper _warehouseHelper;

}