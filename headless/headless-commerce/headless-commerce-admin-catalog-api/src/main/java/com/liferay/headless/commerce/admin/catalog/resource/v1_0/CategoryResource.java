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

package com.liferay.headless.commerce.admin.catalog.resource.v1_0;

import com.liferay.headless.commerce.admin.catalog.dto.v1_0.Category;
import com.liferay.portal.kernel.model.Company;
import com.liferay.portal.vulcan.pagination.Page;
import com.liferay.portal.vulcan.pagination.Pagination;

import javax.annotation.Generated;

import javax.ws.rs.core.Response;

/**
 * To access this resource, run:
 *
 *     curl -u your@email.com:yourpassword -D - http://localhost:8080/o/headless-commerce-admin-catalog/v1.0
 *
 * @author Zoltán Takács
 * @generated
 */
@Generated("")
public interface CategoryResource {

	public Page<Category> getProductByExternalReferenceCodeCategoriesPage(
			String externalReferenceCode, Pagination pagination)
		throws Exception;

	public Response patchProductByExternalReferenceCodeCategory(
			String externalReferenceCode, Category[] categories)
		throws Exception;

	public Page<Category> getProductIdCategoriesPage(
			Long id, Pagination pagination)
		throws Exception;

	public Response patchProductIdCategory(Long id, Category[] categories)
		throws Exception;

	public void setContextCompany(Company contextCompany);

}