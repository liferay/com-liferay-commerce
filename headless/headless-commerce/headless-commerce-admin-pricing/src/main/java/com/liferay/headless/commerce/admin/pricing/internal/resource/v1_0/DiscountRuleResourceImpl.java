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
import com.liferay.headless.commerce.admin.pricing.internal.resource.util.v1_0.DiscountRuleHelper;
import com.liferay.headless.commerce.admin.pricing.model.v1_0.DiscountRuleDTO;
import com.liferay.headless.commerce.admin.pricing.resource.v1_0.DiscountRuleResource;
import com.liferay.oauth2.provider.scope.RequiresScope;

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
	scope = ServiceScope.PROTOTYPE, service = DiscountRuleResource.class
)
@Generated(value = "OSGiRESTModuleGenerator")
public class DiscountRuleResourceImpl implements DiscountRuleResource {

	@Override
	@RequiresScope("HeadlessCommerceAdminPricing.write")
	public Response deleteDiscountRule(String id) throws Exception {
		_discountRuleHelper.deleteDiscountRule(id);

		Response.ResponseBuilder responseBuilder = Response.noContent();

		return responseBuilder.build();
	}

	@Override
	@RequiresScope("HeadlessCommerceAdminPricing.read")
	public DiscountRuleDTO getDiscountRule(String id) throws Exception {
		return _discountRuleHelper.getDiscountRuleDTO(id);
	}

	@AsyncSupported
	@Override
	@RequiresScope("HeadlessCommerceAdminPricing.write")
	public DiscountRuleDTO updateDiscountRule(
			String id, DiscountRuleDTO discountRuleDTO)
		throws Exception {

		if (_async.isEnabled()) {
			new Thread() {

				public void run() {

					// TODO

				}

			}.start();

			return null;
		}

		return _discountRuleHelper.updateDiscountRule(id, discountRuleDTO);
	}

	@Context
	private Async _async;

	@Reference
	private DiscountRuleHelper _discountRuleHelper;

}