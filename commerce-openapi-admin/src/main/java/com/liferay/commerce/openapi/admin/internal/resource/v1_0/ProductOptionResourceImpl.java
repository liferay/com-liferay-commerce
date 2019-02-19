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

package com.liferay.commerce.openapi.admin.internal.resource.v1_0;

import com.liferay.commerce.openapi.admin.internal.resource.util.v1_0.ProductOptionHelper;
import com.liferay.commerce.openapi.admin.internal.resource.util.v1_0.ProductOptionValueHelper;
import com.liferay.commerce.openapi.admin.model.v1_0.ProductOptionDTO;
import com.liferay.commerce.openapi.admin.model.v1_0.ProductOptionValueDTO;
import com.liferay.commerce.openapi.admin.resource.v1_0.ProductOptionResource;
import com.liferay.commerce.openapi.core.annotation.Status;
import com.liferay.commerce.openapi.core.context.Language;
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
	public Response deleteProductOption(String id, Language language)
		throws Exception {

		_productOptionHelper.deleteProductOption(id, _company);

		Response.ResponseBuilder responseBuilder = Response.noContent();

		return responseBuilder.build();
	}

	@Override
	@RequiresScope("CommerceOpenApiAdmin.read")
	public ProductOptionDTO getProductOption(String id, Language language)
		throws Exception {

		return _productOptionHelper.getProductOption(id, language, _company);
	}

	@Override
	@RequiresScope("CommerceOpenApiAdmin.read")
	public CollectionDTO<ProductOptionDTO> getProductOptions(
			Long groupId, Language language, Pagination pagination)
		throws Exception {

		return _productOptionHelper.getProductOptions(
			groupId, language, pagination);
	}

	@Override
	@RequiresScope("CommerceOpenApiAdmin.read")
	public CollectionDTO<ProductOptionValueDTO> getProductOptionValues(
			String id, Language language, Pagination pagination)
		throws Exception {

		return _productOptionValueHelper.getProductOptionValues(
			id, language, _company, pagination);
	}

	@Override
	@RequiresScope("CommerceOpenApiAdmin.write")
	public Response updateProductOption(
			String id, Long groupId, ProductOptionDTO productOptionDTO,
			Language language)
		throws Exception {

		_productOptionHelper.updateProductOption(
			id, groupId, productOptionDTO, language, _company);

		Response.ResponseBuilder responseBuilder = Response.accepted();

		return responseBuilder.build();
	}

	@Override
	@RequiresScope("CommerceOpenApiAdmin.write")
	@Status(Response.Status.CREATED)
	public ProductOptionDTO upsertProductOption(
			Long groupId, ProductOptionDTO productOptionDTO, Language language)
		throws Exception {

		return _productOptionHelper.upsertProductOption(
			groupId, productOptionDTO, language);
	}

	@Override
	@RequiresScope("CommerceOpenApiAdmin.write")
	@Status(Response.Status.CREATED)
	public ProductOptionValueDTO upsertProductOptionValue(
			String id, Long groupId,
			ProductOptionValueDTO productOptionValueDTO, Language language)
		throws Exception {

		return _productOptionValueHelper.upsertProductOptionValue(
			id, groupId, productOptionValueDTO, language, _company);
	}

	@Context
	private Company _company;

	@Reference
	private ProductOptionHelper _productOptionHelper;

	@Reference
	private ProductOptionValueHelper _productOptionValueHelper;

}