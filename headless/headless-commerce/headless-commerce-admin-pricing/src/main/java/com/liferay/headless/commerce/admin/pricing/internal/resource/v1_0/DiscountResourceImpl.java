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
import com.liferay.commerce.openapi.core.context.Pagination;
import com.liferay.commerce.openapi.core.model.CollectionDTO;
import com.liferay.headless.commerce.admin.pricing.internal.resource.util.v1_0.DiscountHelper;
import com.liferay.headless.commerce.admin.pricing.internal.resource.util.v1_0.DiscountRuleHelper;
import com.liferay.headless.commerce.admin.pricing.model.v1_0.DiscountDTO;
import com.liferay.headless.commerce.admin.pricing.model.v1_0.DiscountRuleDTO;
import com.liferay.headless.commerce.admin.pricing.resource.v1_0.DiscountResource;
import com.liferay.oauth2.provider.scope.RequiresScope;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.util.GetterUtil;

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
	scope = ServiceScope.PROTOTYPE, service = DiscountResource.class
)
@Generated(value = "OSGiRESTModuleGenerator")
public class DiscountResourceImpl implements DiscountResource {

	@Override
	@RequiresScope("HeadlessCommerceAdminPricing.write")
	public Response deleteDiscount(String id) throws Exception {
		_discountHelper.deleteDiscount(id);

		Response.ResponseBuilder responseBuilder = Response.noContent();

		return responseBuilder.build();
	}

	@Override
	@RequiresScope("HeadlessCommerceAdminPricing.read")
	public DiscountDTO getDiscount(String id) throws Exception {
		return _discountHelper.getDiscountDTO(id);
	}

	@Override
	public CollectionDTO<DiscountRuleDTO> getDiscountRules(
			String id, Pagination pagination)
		throws Exception {

		return _discountRuleHelper.getDiscountRuleDTOs(
			GetterUtil.getLong(id), pagination);
	}

	@Override
	@RequiresScope("HeadlessCommerceAdminPricing.read")
	public CollectionDTO<DiscountDTO> getDiscounts(
			Long groupId, Pagination pagination)
		throws Exception {

		return _discountHelper.getDiscountDTOs(groupId, pagination);
	}

	@AsyncSupported
	@Override
	@RequiresScope("HeadlessCommerceAdminPricing.write")
	public Response updateDiscount(String id, DiscountDTO discountDTO)
		throws Exception {

		if (_async.isEnabled()) {
			new Thread() {

				public void run() {

					// TODO

				}

			}.start();

			return null;
		}

		_discountHelper.updateDiscount(id, discountDTO, _user);

		Response.ResponseBuilder responseBuilder = Response.accepted();

		return responseBuilder.build();
	}

	@AsyncSupported
	@Override
	@RequiresScope("HeadlessCommerceAdminPricing.write")
	public DiscountDTO upsertDiscount(Long groupId, DiscountDTO discountDTO)
		throws Exception {

		if (_async.isEnabled()) {
			new Thread() {

				public void run() {

					// TODO

				}

			}.start();

			return null;
		}

		return _discountHelper.upsertDiscount(groupId, discountDTO, _user);
	}

	@Override
	public DiscountRuleDTO upsertDiscountRule(
			String id, DiscountRuleDTO discountRuleDTO)
		throws Exception {

		return _discountRuleHelper.upsertDiscountRule(
			GetterUtil.getLong(id), discountRuleDTO, _user);
	}

	@Context
	private Async _async;

	@Reference
	private DiscountHelper _discountHelper;

	@Reference
	private DiscountRuleHelper _discountRuleHelper;

	@Context
	private User _user;

}