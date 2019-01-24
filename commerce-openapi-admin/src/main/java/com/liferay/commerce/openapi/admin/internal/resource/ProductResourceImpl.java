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

import com.liferay.commerce.openapi.admin.internal.resource.util.ProductHelper;
import com.liferay.commerce.openapi.admin.model.CollectionDTO;
import com.liferay.commerce.openapi.admin.model.InventoryDTO;
import com.liferay.commerce.openapi.admin.model.ProductDTO;
import com.liferay.commerce.openapi.admin.model.SkuDTO;
import com.liferay.commerce.openapi.admin.resource.ProductResource;
import com.liferay.commerce.openapi.core.context.Pagination;
import com.liferay.portal.kernel.model.Company;
import com.liferay.portal.kernel.model.User;

import java.util.Collections;
import java.util.Locale;

import javax.annotation.Generated;

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
		JaxrsWhiteboardConstants.JAX_RS_RESOURCE + "=true", "api.version=1.0"
	},
	scope = ServiceScope.PROTOTYPE, service = ProductResource.class
)
@Generated(value = "OSGiRESTModuleGenerator")
public class ProductResourceImpl implements ProductResource {

	@Override
	public Response deleteProduct(String id, long groupId, Locale locale)
		throws Exception {

		_productHelper.deleteProduct(id, _company);

		Response.ResponseBuilder responseBuilder = Response.noContent();

		return responseBuilder.build();
	}

	@Override
	public ProductDTO getProduct(String id, long groupId, Locale locale)
		throws Exception {

		return _productHelper.getProduct(id, locale, _company);
	}

	@Override
	public CollectionDTO<ProductDTO> getProducts(
			long groupId, Locale locale, Pagination pagination)
		throws Exception {

		return _productHelper.getProducts(groupId, locale, pagination);
	}

	@Override
	public CollectionDTO<SkuDTO> getSkus(
			String id, long groupId, Locale locale, Pagination pagination)
		throws Exception {

		return new CollectionDTO(Collections.emptyList(), 0);
	}

	@Override
	public Response updateProduct(
			String id, long groupId, ProductDTO productDTO, Locale locale)
		throws Exception {

		_productHelper.updateProductDTO(
			id, productDTO, _user, locale, _company);

		Response.ResponseBuilder responseBuilder = Response.noContent();

		return responseBuilder.build();
	}

	@Override
	public InventoryDTO upsertInventory(
			String id, long groupId, InventoryDTO inventoryDTO, Locale locale)
		throws Exception {

		return new InventoryDTO();
	}

	@Override
	public ProductDTO upsertProduct(
			long groupId, ProductDTO productDTO, Locale locale)
		throws Exception {

		return _productHelper.upsertProduct(groupId, productDTO, _user, locale);
	}

	@Override
	public SkuDTO upsertSku(
			String id, long groupId, SkuDTO skuDTO, Locale locale)
		throws Exception {

		return new SkuDTO();
	}

	@Context
	private Company _company;

	@Reference
	private ProductHelper _productHelper;

	@Context
	private User _user;

}