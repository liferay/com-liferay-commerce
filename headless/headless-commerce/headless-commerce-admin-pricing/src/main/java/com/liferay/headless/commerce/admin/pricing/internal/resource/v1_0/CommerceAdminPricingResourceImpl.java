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
import com.liferay.commerce.openapi.core.annotation.Status;
import com.liferay.commerce.openapi.core.context.Async;
import com.liferay.commerce.openapi.core.context.Language;
import com.liferay.commerce.openapi.core.context.Pagination;
import com.liferay.commerce.openapi.core.model.CollectionDTO;
import com.liferay.headless.commerce.admin.pricing.internal.resource.util.v1_0.DiscountHelper;
import com.liferay.headless.commerce.admin.pricing.internal.resource.util.v1_0.PriceListHelper;
import com.liferay.headless.commerce.admin.pricing.model.v1_0.DiscountDTO;
import com.liferay.headless.commerce.admin.pricing.model.v1_0.PriceListDTO;
import com.liferay.headless.commerce.admin.pricing.resource.v1_0.CommerceAdminPricingResource;
import com.liferay.oauth2.provider.scope.RequiresScope;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.User;

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
	scope = ServiceScope.PROTOTYPE, service = CommerceAdminPricingResource.class
)
@Generated(value = "OSGiRESTModuleGenerator")
public class CommerceAdminPricingResourceImpl
	implements CommerceAdminPricingResource {

	@Override
	@RequiresScope("HeadlessCommerceAdminPricing.read")
	public CollectionDTO<DiscountDTO> getDiscounts(
			Long groupId, Pagination pagination)
		throws Exception {

		return _discountHelper.getDiscountDTOs(groupId, pagination);
	}

	@Override
	@RequiresScope("HeadlessCommerceAdminPricing.read")
	public CollectionDTO<PriceListDTO> getPriceLists(
			Long groupId, Language language, Pagination pagination)
		throws Exception {

		return _priceListHelper.getPriceLists(groupId, language, pagination);
	}

	@AsyncSupported
	@Override
	@RequiresScope("HeadlessCommerceAdminPricing.write")
	@Status(Response.Status.CREATED)
	public DiscountDTO upsertDiscount(Long groupId, DiscountDTO discountDTO)
		throws Exception {

		if (_async.isEnabled()) {
			new Thread(
				() -> {
					try {
						_discountHelper.upsertDiscount(
							groupId, discountDTO, _user);
					}
					catch (PortalException pe) {
						_log.error(pe, pe);
					}
				}
			).start();

			return null;
		}

		return _discountHelper.upsertDiscount(groupId, discountDTO, _user);
	}

	@AsyncSupported
	@Override
	@RequiresScope("HeadlessCommerceAdminPricing.write")
	@Status(Response.Status.CREATED)
	public PriceListDTO upsertPriceList(
			Long groupId, PriceListDTO priceListDTO, Language language)
		throws Exception {

		if (_async.isEnabled()) {
			new Thread(
				() -> {
					try {
						_priceListHelper.upsertPriceList(
							groupId, priceListDTO, _user, language);
					}
					catch (PortalException pe) {
						_log.error(pe, pe);
					}
				}
			).start();

			return null;
		}

		return _priceListHelper.upsertPriceList(
			groupId, priceListDTO, _user, language);
	}

	private static final Log _log = LogFactoryUtil.getLog(
		CommerceAdminPricingResourceImpl.class);

	@Context
	private Async _async;

	@Reference
	private DiscountHelper _discountHelper;

	@Reference
	private PriceListHelper _priceListHelper;

	@Context
	private User _user;

}