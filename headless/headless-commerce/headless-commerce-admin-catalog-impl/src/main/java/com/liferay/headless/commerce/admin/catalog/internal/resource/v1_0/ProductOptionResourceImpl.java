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

package com.liferay.headless.commerce.admin.catalog.internal.resource.v1_0;

import com.liferay.headless.commerce.admin.catalog.dto.v1_0.ProductOption;
import com.liferay.headless.commerce.admin.catalog.dto.v1_0.ProductOptionValue;
import com.liferay.headless.commerce.admin.catalog.internal.util.v1_0.ProductOptionHelper;
import com.liferay.headless.commerce.admin.catalog.internal.util.v1_0.ProductOptionValueHelper;
import com.liferay.headless.commerce.admin.catalog.resource.v1_0.ProductOptionResource;
import com.liferay.portal.vulcan.pagination.Page;
import com.liferay.portal.vulcan.pagination.Pagination;

import javax.validation.constraints.NotNull;

import javax.ws.rs.core.Response;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.osgi.service.component.annotations.ServiceScope;

/**
 * @author Zoltán Takács
 */
@Component(
	properties = "OSGI-INF/liferay/rest/v1_0/product-option.properties",
	scope = ServiceScope.PROTOTYPE, service = ProductOptionResource.class
)
public class ProductOptionResourceImpl extends BaseProductOptionResourceImpl {

	@Override
	public Response deleteProductOption(@NotNull String id) throws Exception {
		_productOptionHelper.deleteProductOption(id, contextCompany);

		Response.ResponseBuilder responseBuilder = Response.noContent();

		return responseBuilder.build();
	}

	@Override
	public Page<ProductOption> getOptions(
			@NotNull Long groupId, Pagination pagination)
		throws Exception {

		return _productOptionHelper.getProductOptions(
			groupId, contextAcceptLanguage, pagination);
	}

	@Override
	public ProductOption getProductOption(@NotNull String id) throws Exception {
		return _productOptionHelper.getProductOption(
			id, contextAcceptLanguage, contextCompany);
	}

	@Override
	public Page<ProductOptionValue> getProductOptionValues(
			@NotNull String id, Pagination pagination)
		throws Exception {

		return _productOptionValueHelper.getProductOptionValues(
			id, contextAcceptLanguage, contextCompany, pagination);
	}

	@Override
	public Response updateProductOption(
			@NotNull String id, ProductOption productOption)
		throws Exception {

		_productOptionHelper.updateProductOption(
			id, productOption, contextAcceptLanguage, contextCompany);

		Response.ResponseBuilder responseBuilder = Response.accepted();

		return responseBuilder.build();
	}

	@Override
	public ProductOption upsertProductOption(
			@NotNull Long groupId, ProductOption productOption)
		throws Exception {

		return _productOptionHelper.upsertProductOption(
			groupId, productOption, contextAcceptLanguage);
	}

	@Override
	public ProductOptionValue upsertProductOptionValue(
			@NotNull String id, ProductOptionValue productOptionValue)
		throws Exception {

		ProductOption productOption = _productOptionHelper.getProductOption(
			id, contextAcceptLanguage, contextCompany);

		return _productOptionValueHelper.upsertProductOptionValue(
			id, productOption.getGroupId(), productOptionValue,
			contextAcceptLanguage, contextCompany);
	}

	@Reference
	private ProductOptionHelper _productOptionHelper;

	@Reference
	private ProductOptionValueHelper _productOptionValueHelper;

}