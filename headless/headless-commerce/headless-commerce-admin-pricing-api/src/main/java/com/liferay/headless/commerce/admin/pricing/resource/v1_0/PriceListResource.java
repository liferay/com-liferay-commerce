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

import com.liferay.headless.commerce.admin.pricing.dto.v1_0.PriceList;
import com.liferay.portal.kernel.model.Company;
import com.liferay.portal.kernel.search.Sort;
import com.liferay.portal.kernel.search.filter.Filter;
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

	public Page<PriceList> getPriceListsPage(
			Filter filter, Pagination pagination, Sort[] sorts)
		throws Exception;

	public PriceList postPriceList(PriceList priceList) throws Exception;

	public Response deletePriceListByExternalReferenceCode(
			String externalReferenceCode)
		throws Exception;

	public PriceList getPriceListByExternalReferenceCode(
			String externalReferenceCode)
		throws Exception;

	public Response patchPriceListByExternalReferenceCode(
			String externalReferenceCode, PriceList priceList)
		throws Exception;

	public Response deletePriceList(Long id) throws Exception;

	public PriceList getPriceList(Long id) throws Exception;

	public Response patchPriceList(Long id, PriceList priceList)
		throws Exception;

	public void setContextCompany(Company contextCompany);

}