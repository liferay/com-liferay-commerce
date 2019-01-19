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

import javax.ws.rs.core.Response;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.jaxrs.whiteboard.JaxrsWhiteboardConstants;

/**
 * @author Igor Beslic
 */
@Component(
	immediate = true,
	property = {
		JaxrsWhiteboardConstants.JAX_RS_APPLICATION_SELECT + "=(osgi.jaxrs.name=CommerceOpenApiAdmin.Rest)",
		JaxrsWhiteboardConstants.JAX_RS_RESOURCE + "=true", "api.version=1.0"
	},
	service = ProductResource.class
)
@Generated(value = "OSGiRESTModuleGenerator")
public class ProductResourceImpl implements ProductResource {

	@Override
	public Response deleteProduct(
		String id, long groupId, User user, Locale locale, Company company) {

		Response.ResponseBuilder responseBuilder = Response.noContent();

		return responseBuilder.build();
	}

	@Override
	public ProductDTO getProduct(
		String id, long groupId, User user, Locale locale, Company company) {

		return new ProductDTO();
	}

	@Override
	public CollectionDTO<ProductDTO> getProducts(
		long groupId, User user, Locale locale, Company company,
		Pagination pagination) {

		return new CollectionDTO(Collections.emptyList(), 0);
	}

	@Override
	public CollectionDTO<SkuDTO> getSkus(
		String id, long groupId, User user, Locale locale, Company company,
		Pagination pagination) {

		return new CollectionDTO(Collections.emptyList(), 0);
	}

	@Override
	public Response updateProduct(
		String id, long groupId, ProductDTO productDTO, User user,
		Locale locale, Company company) {

		Response.ResponseBuilder responseBuilder = Response.ok();

		return responseBuilder.build();
	}

	@Override
	public InventoryDTO upsertInventory(
		String id, long groupId, InventoryDTO inventoryDTO, User user,
		Locale locale, Company company) {

		return new InventoryDTO();
	}

	@Override
	public ProductDTO upsertProduct(
		long groupId, ProductDTO productDTO, User user, Locale locale,
		Company company) {

		return new ProductDTO();
	}

	@Override
	public SkuDTO upsertSku(
		String id, long groupId, SkuDTO skuDTO, User user, Locale locale,
		Company company) {

		return new SkuDTO();
	}

}