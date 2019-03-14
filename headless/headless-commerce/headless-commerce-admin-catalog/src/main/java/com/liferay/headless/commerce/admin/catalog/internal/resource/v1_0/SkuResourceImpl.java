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
import com.liferay.commerce.openapi.core.context.Async;
import com.liferay.headless.commerce.admin.catalog.internal.resource.util.v1_0.SKUHelper;
import com.liferay.headless.commerce.admin.catalog.model.v1_0.SkuDTO;
import com.liferay.headless.commerce.admin.catalog.resource.v1_0.SkuResource;
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
	scope = ServiceScope.PROTOTYPE, service = SkuResource.class
)
@Generated(value = "OSGiRESTModuleGenerator")
public class SkuResourceImpl implements SkuResource {

	@Override
	@RequiresScope("HeadlessCommerceAdminCatalog.write")
	public Response deleteSku(String id) throws Exception {
		_skuHelper.deleteSKU(id, _company);

		Response.ResponseBuilder responseBuilder = Response.noContent();

		return responseBuilder.build();
	}

	@Override
	@RequiresScope("HeadlessCommerceAdminCatalog.read")
	public SkuDTO getSku(String id) throws Exception {
		return _skuHelper.getSku(id, _company);
	}

	@AsyncSupported
	@Override
	@RequiresScope("HeadlessCommerceAdminCatalog.write")
	public Response updateSku(String id, SkuDTO skuDTO) throws Exception {
		if (_async.isEnabled()) {
			new Thread(
				() -> {
					try {
						_skuHelper.updateSKU(id, skuDTO, _company);
					}
					catch (PortalException pe) {
						_log.error(pe, pe);
					}
				}
			).start();

			return null;
		}

		_skuHelper.updateSKU(id, skuDTO, _company);

		Response.ResponseBuilder responseBuilder = Response.accepted();

		return responseBuilder.build();
	}

	private static final Log _log = LogFactoryUtil.getLog(
		SkuResourceImpl.class);

	@Context
	private Async _async;

	@Context
	private Company _company;

	@Reference
	private SKUHelper _skuHelper;

}