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

package com.liferay.headless.commerce.admin.pricing.resource.v1_0;

import com.liferay.headless.commerce.admin.pricing.dto.v1_0.PriceEntry;
import com.liferay.headless.commerce.admin.pricing.dto.v1_0.PriceList;
import com.liferay.portal.kernel.model.Company;
import com.liferay.portal.vulcan.pagination.Page;
import com.liferay.portal.vulcan.pagination.Pagination;

import javax.annotation.Generated;

import javax.ws.rs.core.Response;

/**
 * To access this resource, run:
 *
 *     curl -u your@email.com:yourpassword -D - http://localhost:8080/o/headless-commerce-admin-pricing/v1.0
 *
 * @author Zoltán Takács
 * @generated
 */
@Generated("")
public interface PriceListResource {

	public Page<PriceList> getPriceLists(Long groupId, Pagination pagination)
		throws Exception;

	public PriceList upsertPriceList(Long groupId, PriceList priceList)
		throws Exception;

	public Page<PriceEntry> getPriceEntries(String id, Pagination pagination)
		throws Exception;

	public PriceEntry upsertPriceEntry(String id, PriceEntry priceEntry)
		throws Exception;

	public Response deletePriceList(String id) throws Exception;

	public PriceList getPriceList(String id) throws Exception;

	public Response updatePriceList(String id, PriceList priceList)
		throws Exception;

	public void setContextCompany(Company contextCompany);

}