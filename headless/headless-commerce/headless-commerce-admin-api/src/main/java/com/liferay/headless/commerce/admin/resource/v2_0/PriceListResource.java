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

package com.liferay.headless.commerce.admin.resource.v2_0;

import com.liferay.headless.commerce.admin.dto.v2_0.PriceList;
import com.liferay.portal.kernel.model.Company;

import javax.annotation.Generated;

import javax.ws.rs.core.Response;

/**
 * To access this resource, run:
 *
 *     curl -u your@email.com:yourpassword -D - http://localhost:8080/o/headless-commerce-admin/v2.0
 *
 * @author Igor Beslic
 * @generated
 */
@Generated("")
public interface PriceListResource {

	public Response deletePriceList(String id) throws Exception;

	public Response getPriceList(String id, Long groupId) throws Exception;

	public Response updateMediaType1PriceList(
			String id, Long groupId, PriceList priceList)
		throws Exception;

	public Response updateMediaType2PriceList(
			String id, Long groupId, PriceList priceList)
		throws Exception;

	public Response getPriceLists(Long groupId) throws Exception;

	public Response upsertMediaType1PriceList(Long groupId, PriceList priceList)
		throws Exception;

	public Response upsertMediaType2PriceList(Long groupId, PriceList priceList)
		throws Exception;

	public void setContextCompany(Company contextCompany);

}