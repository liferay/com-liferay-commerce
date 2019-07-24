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

package com.liferay.headless.commerce.admin.inventory.resource.v1_0;

import com.liferay.headless.commerce.admin.inventory.dto.v1_0.WarehouseItem;
import com.liferay.portal.kernel.model.Company;
import com.liferay.portal.vulcan.pagination.Page;
import com.liferay.portal.vulcan.pagination.Pagination;

import javax.annotation.Generated;

import javax.ws.rs.core.Response;

/**
 * To access this resource, run:
 *
 *     curl -u your@email.com:yourpassword -D - http://localhost:8080/o/headless-commerce-admin-inventory/v1.0
 *
 * @author Alessio Antonio Rendina
 * @generated
 */
@Generated("")
public interface WarehouseItemResource {

	public Page<WarehouseItem> getWarehousIdWarehouseItemsPage(
			Long id, Pagination pagination)
		throws Exception;

	public WarehouseItem postWarehousIdWarehouseItem(
			Long id, WarehouseItem warehouseItem)
		throws Exception;

	public Page<WarehouseItem>
			getWarehousByExternalReferenceCodeWarehouseItemsPage(
				String externalReferenceCode, Pagination pagination)
		throws Exception;

	public WarehouseItem postWarehousByExternalReferenceCodeWarehouseItem(
			String externalReferenceCode, WarehouseItem warehouseItem)
		throws Exception;

	public Response deleteWarehouseItem(Long id) throws Exception;

	public WarehouseItem getWarehouseItem(Long id) throws Exception;

	public Response patchWarehouseItem(Long id, WarehouseItem warehouseItem)
		throws Exception;

	public void setContextCompany(Company contextCompany);

}