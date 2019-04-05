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

import com.liferay.headless.commerce.admin.catalog.dto.v1_0.ProductTaxConfiguration;
import com.liferay.portal.kernel.model.Company;

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
public interface ProductTaxConfigurationResource {

	public ProductTaxConfiguration getProductIdTaxConfiguration(Long id)
		throws Exception;

	public Response patchProductIdTaxConfiguration(
			Long id, ProductTaxConfiguration productTaxConfiguration)
		throws Exception;

	public ProductTaxConfiguration
			getProductByExternalReferenceCodeTaxConfiguration(
				String externalReferenceCode)
		throws Exception;

	public Response patchProductByExternalReferenceCodeTaxConfiguration(
			String externalReferenceCode,
			ProductTaxConfiguration productTaxConfiguration)
		throws Exception;

	public void setContextCompany(Company contextCompany);

}