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

import com.liferay.commerce.openapi.admin.internal.resource.util.ProductOptionValueHelper;
import com.liferay.commerce.openapi.admin.model.ProductOptionValueDTO;
import com.liferay.commerce.openapi.admin.resource.ProductOptionValueResource;
import com.liferay.portal.kernel.model.Company;

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
		JaxrsWhiteboardConstants.JAX_RS_RESOURCE + "=true", "api.version=v1.0"
	},
	scope = ServiceScope.PROTOTYPE, service = ProductOptionValueResource.class
)
@Generated(value = "OSGiRESTModuleGenerator")
public class ProductOptionValueResourceImpl
	implements ProductOptionValueResource {

	@Override
	public Response deleteProductOptionValue(String id, Locale locale)
		throws Exception {

		_productOptionValueHelper.deleteProductOptionValue(id, _company);

		Response.ResponseBuilder responseBuilder = Response.noContent();

		return responseBuilder.build();
	}

	@Override
	public ProductOptionValueDTO getProductOptionValue(String id, Locale locale)
		throws Exception {

		return _productOptionValueHelper.getProductOptionValue(
			id, locale, _company);
	}

	@Override
	public Response updateProductOptionValue(
			String id, Long groupId,
			ProductOptionValueDTO productOptionValueDTO, Locale locale)
		throws Exception {

		_productOptionValueHelper.updateProductOptionValue(
			id, groupId, productOptionValueDTO, locale, _company);

		Response.ResponseBuilder responseBuilder = Response.accepted();

		return responseBuilder.build();
	}

	@Context
	private Company _company;

	@Reference
	private ProductOptionValueHelper _productOptionValueHelper;

}