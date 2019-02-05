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

package com.liferay.commerce.openapi.admin.internal.resource;

import com.liferay.commerce.openapi.admin.internal.resource.util.ProductOptionHelper;
import com.liferay.commerce.openapi.admin.internal.resource.util.ProductOptionValueHelper;
import com.liferay.commerce.openapi.admin.model.ProductOptionDTO;
import com.liferay.commerce.openapi.admin.model.ProductOptionValueDTO;
import com.liferay.commerce.openapi.admin.resource.ProductOptionResource;
import com.liferay.commerce.openapi.core.context.Pagination;
import com.liferay.commerce.openapi.core.model.CollectionDTO;
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
		JaxrsWhiteboardConstants.JAX_RS_RESOURCE + "=true", "api.version=v1.0"
	},
	scope = ServiceScope.PROTOTYPE, service = ProductOptionResource.class
)
public class ProductOptionResourceImpl implements ProductOptionResource {

	@Override
	@RequiresScope("CommerceOpenApiAdmin.write")
	public Response deleteProductOption(String id, String languageId)
		throws Exception {

		_productOptionHelper.deleteProductOption(id, _company);

		Response.ResponseBuilder responseBuilder = Response.noContent();

		return responseBuilder.build();
	}

	@Override
	@RequiresScope("CommerceOpenApiAdmin.read")
	public ProductOptionDTO getProductOption(String id, String languageId)
		throws Exception {

		return _productOptionHelper.getProductOption(id, languageId, _company);
	}

	@Override
	@RequiresScope("CommerceOpenApiAdmin.read")
	public CollectionDTO<ProductOptionDTO> getProductOptions(
			Long groupId, String languageId, Pagination pagination)
		throws Exception {

		return _productOptionHelper.getProductOptions(
			groupId, languageId, pagination);
	}

	@Override
	@RequiresScope("CommerceOpenApiAdmin.read")
	public CollectionDTO<ProductOptionValueDTO> getProductOptionValues(
			String id, String languageId, Pagination pagination)
		throws Exception {

		return _productOptionValueHelper.getProductOptionValues(
			id, languageId, _company, pagination);
	}

	@Override
	@RequiresScope("CommerceOpenApiAdmin.write")
	public Response updateProductOption(
			String id, Long groupId, ProductOptionDTO productOptionDTO,
			String languageId)
		throws Exception {

		_productOptionHelper.updateProductOption(
			id, groupId, productOptionDTO, languageId, _company);

		Response.ResponseBuilder responseBuilder = Response.accepted();

		return responseBuilder.build();
	}

	@Override
	@RequiresScope("CommerceOpenApiAdmin.write")
	public ProductOptionDTO upsertProductOption(
			Long groupId, ProductOptionDTO productOptionDTO, String languageId)
		throws Exception {

		return _productOptionHelper.upsertProductOption(
			groupId, productOptionDTO, languageId);
	}

	@Override
	@RequiresScope("CommerceOpenApiAdmin.write")
	public ProductOptionValueDTO upsertProductOptionValue(
			String id, Long groupId,
			ProductOptionValueDTO productOptionValueDTO, String languageId)
		throws Exception {

		return _productOptionValueHelper.upsertProductOptionValue(
			id, groupId, productOptionValueDTO, languageId, _company);
	}

	@Context
	private Company _company;

	@Reference
	private ProductOptionHelper _productOptionHelper;

	@Reference
	private ProductOptionValueHelper _productOptionValueHelper;

}