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

package com.liferay.commerce.openapi.admin.internal.resource.v2_0;

import com.liferay.commerce.openapi.admin.model.v2_0.ProductOptionDTO;
import com.liferay.commerce.openapi.admin.model.v2_0.ProductOptionValueDTO;
import com.liferay.commerce.openapi.admin.resource.v2_0.ProductOptionResource;
import com.liferay.commerce.openapi.core.context.Language;
import com.liferay.commerce.openapi.core.context.Pagination;
import com.liferay.commerce.openapi.core.model.CollectionDTO;
import com.liferay.oauth2.provider.scope.RequiresScope;

import java.util.Collections;

import javax.annotation.Generated;

import javax.ws.rs.core.Response;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.ServiceScope;
import org.osgi.service.jaxrs.whiteboard.JaxrsWhiteboardConstants;

/**
 * @author Igor Beslic
 */
@Component(
	property = {
		JaxrsWhiteboardConstants.JAX_RS_APPLICATION_SELECT + "=(osgi.jaxrs.name=CommerceOpenApiAdmin.Rest)",
		JaxrsWhiteboardConstants.JAX_RS_RESOURCE + "=true", "api.version=v2.0"
	},
	scope = ServiceScope.PROTOTYPE, service = ProductOptionResource.class
)
@Generated(value = "OSGiRESTModuleGenerator")
public class ProductOptionResourceImpl implements ProductOptionResource {

	@Override
	@RequiresScope("CommerceOpenApiAdmin.write")
	public Response deleteProductOption(String id, Language language)
		throws Exception {

		Response.ResponseBuilder responseBuilder = Response.noContent();

		return responseBuilder.build();
	}

	@Override
	@RequiresScope("CommerceOpenApiAdmin.read")
	public ProductOptionDTO getProductOption(String id, Language language)
		throws Exception {

		return new ProductOptionDTO();
	}

	@Override
	@RequiresScope("CommerceOpenApiAdmin.read")
	public CollectionDTO<ProductOptionDTO> getProductOptions(
			Long groupId, Language language, Pagination pagination)
		throws Exception {

		return new CollectionDTO(Collections.emptyList(), 0);
	}

	@Override
	@RequiresScope("CommerceOpenApiAdmin.read")
	public CollectionDTO<ProductOptionValueDTO> getProductOptionValues(
			String id, Language language, Pagination pagination)
		throws Exception {

		return new CollectionDTO(Collections.emptyList(), 0);
	}

	@Override
	@RequiresScope("CommerceOpenApiAdmin.write")
	public Response updateProductOption(
			String id, Long groupId, ProductOptionDTO productOptionDTO,
			Language language)
		throws Exception {

		Response.ResponseBuilder responseBuilder = Response.accepted();

		return responseBuilder.build();
	}

	@Override
	@RequiresScope("CommerceOpenApiAdmin.write")
	public ProductOptionDTO upsertProductOption(
			Long groupId, ProductOptionDTO productOptionDTO, Language language)
		throws Exception {

		return new ProductOptionDTO();
	}

	@Override
	@RequiresScope("CommerceOpenApiAdmin.write")
	public ProductOptionValueDTO upsertProductOptionValue(
			String id, Long groupId,
			ProductOptionValueDTO productOptionValueDTO, Language language)
		throws Exception {

		return new ProductOptionValueDTO();
	}

}