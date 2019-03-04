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

package com.liferay.headless.commerce.admin.pricing.internal.resource.v1_0;

import com.liferay.commerce.openapi.core.annotation.AsyncSupported;
import com.liferay.commerce.openapi.core.context.Async;
import com.liferay.headless.commerce.admin.pricing.internal.resource.util.v1_0.TierPriceHelper;
import com.liferay.headless.commerce.admin.pricing.model.v1_0.TierPriceDTO;
import com.liferay.headless.commerce.admin.pricing.resource.v1_0.TierPriceResource;
import com.liferay.oauth2.provider.scope.RequiresScope;
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
		JaxrsWhiteboardConstants.JAX_RS_APPLICATION_SELECT + "=(osgi.jaxrs.name=HeadlessCommerceAdminPricing.Rest)",
		JaxrsWhiteboardConstants.JAX_RS_RESOURCE + "=true", "api.version=v1.0"
	},
	scope = ServiceScope.PROTOTYPE, service = TierPriceResource.class
)
@Generated(value = "OSGiRESTModuleGenerator")
public class TierPriceResourceImpl implements TierPriceResource {

	@Override
	@RequiresScope("HeadlessCommerceAdminPricing.write")
	public Response deleteTierPrice(String id) throws Exception {
		_tierPriceHelper.deleteCommerceTierPriceEntry(
			id, _company.getCompanyId());

		Response.ResponseBuilder responseBuilder = Response.noContent();

		return responseBuilder.build();
	}

	@Override
	@RequiresScope("HeadlessCommerceAdminPricing.read")
	public TierPriceDTO getTierPrice(String id) throws Exception {
		return _tierPriceHelper.getTierPriceDTO(id, _company.getCompanyId());
	}

	@AsyncSupported
	@Override
	@RequiresScope("HeadlessCommerceAdminPricing.write")
	public Response updateTierPrice(String id, TierPriceDTO tierPriceDTO)
		throws Exception {

		if (_async.isEnabled()) {
			new Thread() {

				public void run() {

					// TODO

				}

			}.start();

			return null;
		}

		_tierPriceHelper.updateCommerceTierPriceEntry(
			id, _company.getCompanyId(), tierPriceDTO);

		Response.ResponseBuilder responseBuilder = Response.accepted();

		return responseBuilder.build();
	}

	@Context
	private Async _async;

	@Context
	private Company _company;

	@Reference
	private TierPriceHelper _tierPriceHelper;

}