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

import com.liferay.headless.commerce.admin.dto.v2_0.ProductOption;
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
public interface ProductOptionResource {

	public Response deleteProductOption(String id) throws Exception;

	public Response getProductOption(String id) throws Exception;

	public Response updateMediaType1ProductOption(
			String id, Long groupId, ProductOption productOption)
		throws Exception;

	public Response updateMediaType2ProductOption(
			String id, Long groupId, ProductOption productOption)
		throws Exception;

	public Response getProductOptions(Long groupId) throws Exception;

	public Response upsertMediaType1ProductOption(
			Long groupId, ProductOption productOption)
		throws Exception;

	public Response upsertMediaType2ProductOption(
			Long groupId, ProductOption productOption)
		throws Exception;

	public void setContextCompany(Company contextCompany);

}