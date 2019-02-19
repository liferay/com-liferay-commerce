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

import com.liferay.commerce.openapi.admin.internal.resource.util.v2_0.ProductHelper;
import com.liferay.commerce.openapi.admin.internal.resource.util.v2_0.SKUHelper;
import com.liferay.commerce.openapi.admin.model.v2_0.ProductDTO;
import com.liferay.commerce.openapi.admin.model.v2_0.SkuDTO;
import com.liferay.commerce.openapi.admin.resource.v2_0.ProductResource;
import com.liferay.commerce.openapi.core.annotation.Nested;
import com.liferay.commerce.openapi.core.annotation.Status;
import com.liferay.commerce.openapi.core.context.Language;
import com.liferay.commerce.openapi.core.context.Pagination;
import com.liferay.commerce.openapi.core.model.CollectionDTO;
import com.liferay.oauth2.provider.scope.RequiresScope;
import com.liferay.portal.kernel.model.Company;
import com.liferay.portal.kernel.model.User;

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
		JaxrsWhiteboardConstants.JAX_RS_RESOURCE + "=true", "api.version=v2.0"
	},
	scope = ServiceScope.PROTOTYPE, service = ProductResource.class
)
public class ProductResourceImpl implements ProductResource {

	@Override
	@RequiresScope("CommerceOpenApiAdmin.write")
	public Response deleteProduct(String id) throws Exception {
		_productHelper.deleteProduct(id, _company);

		Response.ResponseBuilder responseBuilder = Response.noContent();

		return responseBuilder.build();
	}

	@Override
	@RequiresScope("CommerceOpenApiAdmin.read")
	public ProductDTO getProduct(String id) throws Exception {
		return _productHelper.getProduct(id, _company);
	}

	@Override
	@RequiresScope("CommerceOpenApiAdmin.read")
	public CollectionDTO<ProductDTO> getProducts(
			Long groupId, Pagination pagination)
		throws Exception {

		return _productHelper.getProducts(groupId, pagination);
	}

	@Nested("skus")
	@Override
	@RequiresScope("CommerceOpenApiAdmin.read")
	public CollectionDTO<SkuDTO> getSkus(String id, Pagination pagination)
		throws Exception {

		return _skuHelper.getSKUs(id, _company, pagination);
	}

	@Override
	@RequiresScope("CommerceOpenApiAdmin.write")
	public Response updateProduct(
			String id, Long groupId, ProductDTO productDTO, Language language)
		throws Exception {

		_productHelper.updateProductDTO(id, productDTO, _company);

		Response.ResponseBuilder responseBuilder = Response.noContent();

		return responseBuilder.build();
	}

	@Override
	@RequiresScope("CommerceOpenApiAdmin.write")
	@Status(Response.Status.CREATED)
	public ProductDTO upsertProduct(
			Long groupId, ProductDTO productDTO, Language language)
		throws Exception {

		return _productHelper.upsertProduct(groupId, productDTO, _user);
	}

	@Override
	@RequiresScope("CommerceOpenApiAdmin.write")
	@Status(Response.Status.CREATED)
	public SkuDTO upsertSku(
			String id, Long groupId, SkuDTO skuDTO, Language language)
		throws Exception {

		return _skuHelper.upsertSKU(id, groupId, skuDTO, _company);
	}

	@Context
	private Company _company;

	@Reference
	private ProductHelper _productHelper;

	@Reference
	private SKUHelper _skuHelper;

	@Context
	private User _user;

}