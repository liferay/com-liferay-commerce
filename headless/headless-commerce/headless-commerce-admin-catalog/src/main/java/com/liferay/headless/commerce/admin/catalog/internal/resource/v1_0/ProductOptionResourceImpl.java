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

import com.liferay.commerce.openapi.core.annotation.AsyncSupported;
import com.liferay.commerce.openapi.core.annotation.Status;
import com.liferay.commerce.openapi.core.context.Async;
import com.liferay.commerce.openapi.core.context.Language;
import com.liferay.commerce.openapi.core.context.Pagination;
import com.liferay.commerce.openapi.core.model.CollectionDTO;
import com.liferay.headless.commerce.admin.catalog.internal.resource.util.v1_0.ProductOptionHelper;
import com.liferay.headless.commerce.admin.catalog.internal.resource.util.v1_0.ProductOptionValueHelper;
import com.liferay.headless.commerce.admin.catalog.model.v1_0.ProductOptionDTO;
import com.liferay.headless.commerce.admin.catalog.model.v1_0.ProductOptionValueDTO;
import com.liferay.headless.commerce.admin.catalog.resource.v1_0.ProductOptionResource;
import com.liferay.oauth2.provider.scope.RequiresScope;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.Company;

import javax.annotation.Generated;

import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.osgi.service.component.annotations.ServiceScope;
import org.osgi.service.jaxrs.whiteboard.JaxrsWhiteboardConstants;

/**
 * @author Alessio Antonio Rendina
 */
@Component(
	property = {
		JaxrsWhiteboardConstants.JAX_RS_APPLICATION_SELECT + "=(osgi.jaxrs.name=HeadlessCommerceAdminCatalog.Rest)",
		JaxrsWhiteboardConstants.JAX_RS_RESOURCE + "=true", "api.version=v1.0"
	},
	scope = ServiceScope.PROTOTYPE, service = ProductOptionResource.class
)
@Generated(value = "OSGiRESTModuleGenerator")
public class ProductOptionResourceImpl implements ProductOptionResource {

	@Override
	@RequiresScope("HeadlessCommerceAdminCatalog.write")
	public Response deleteProductOption(String id) throws Exception {
		_productOptionHelper.deleteProductOption(id, _company);

		Response.ResponseBuilder responseBuilder = Response.noContent();

		return responseBuilder.build();
	}

	@Override
	@RequiresScope("HeadlessCommerceAdminCatalog.read")
	public ProductOptionDTO getProductOption(String id, Language language)
		throws Exception {

		return _productOptionHelper.getProductOption(id, language, _company);
	}

	@Override
	@RequiresScope("HeadlessCommerceAdminCatalog.read")
	public CollectionDTO<ProductOptionValueDTO> getProductOptionValues(
			String id, Language language, Pagination pagination)
		throws Exception {

		return _productOptionValueHelper.getProductOptionValues(
			id, language, _company, pagination);
	}

	@AsyncSupported
	@Override
	@RequiresScope("HeadlessCommerceAdminCatalog.write")
	public Response updateProductOption(
			String id, ProductOptionDTO productOptionDTO, Language language)
		throws Exception {

		if (_async.isEnabled()) {
			new Thread(
				() -> {
					try {
						_productOptionHelper.updateProductOption(
							id, productOptionDTO, language, _company);
					}
					catch (PortalException pe) {
						_log.error(pe, pe);
					}
				}
			).start();

			return null;
		}

		_productOptionHelper.updateProductOption(
			id, productOptionDTO, language, _company);

		Response.ResponseBuilder responseBuilder = Response.accepted();

		return responseBuilder.build();
	}

	@AsyncSupported
	@Override
	@RequiresScope("HeadlessCommerceAdminCatalog.write")
	@Status(Response.Status.CREATED)
	public ProductOptionValueDTO upsertProductOptionValue(
			String id, ProductOptionValueDTO productOptionValueDTO,
			Language language)
		throws Exception {

		ProductOptionDTO productOption = _productOptionHelper.getProductOption(
			id, language, _company);

		if (_async.isEnabled()) {
			new Thread(
				() -> {
					try {
						_productOptionValueHelper.upsertProductOptionValue(
							id, productOption.getGroupId(),
							productOptionValueDTO, language, _company);
					}
					catch (PortalException pe) {
						_log.error(pe, pe);
					}
				}
			).start();

			return null;
		}

		return _productOptionValueHelper.upsertProductOptionValue(
			id, productOption.getGroupId(), productOptionValueDTO, language,
			_company);
	}

	private static final Log _log = LogFactoryUtil.getLog(
		ProductOptionResourceImpl.class);

	@Context
	private Async _async;

	@Context
	private Company _company;

	@Reference
	private ProductOptionHelper _productOptionHelper;

	@Reference
	private ProductOptionValueHelper _productOptionValueHelper;

}