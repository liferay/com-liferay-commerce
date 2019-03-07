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
import com.liferay.commerce.openapi.core.context.Pagination;
import com.liferay.commerce.openapi.core.model.CollectionDTO;
import com.liferay.headless.commerce.admin.pricing.internal.resource.util.v1_0.PriceEntryHelper;
import com.liferay.headless.commerce.admin.pricing.internal.resource.util.v1_0.TierPriceHelper;
import com.liferay.headless.commerce.admin.pricing.model.v1_0.PriceEntryDTO;
import com.liferay.headless.commerce.admin.pricing.model.v1_0.TierPriceDTO;
import com.liferay.headless.commerce.admin.pricing.resource.v1_0.PriceEntryResource;
import com.liferay.oauth2.provider.scope.RequiresScope;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.Company;
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
	scope = ServiceScope.PROTOTYPE, service = PriceEntryResource.class
)
@Generated(value = "OSGiRESTModuleGenerator")
public class PriceEntryResourceImpl implements PriceEntryResource {

	@Override
	@RequiresScope("HeadlessCommerceAdminPricing.write")
	public Response deletePriceEntry(String id) throws Exception {
		_priceEntryHelper.deleteCommercePriceEntry(id, _company.getCompanyId());

		Response.ResponseBuilder responseBuilder = Response.noContent();

		return responseBuilder.build();
	}

	@Override
	@RequiresScope("HeadlessCommerceAdminPricing.read")
	public PriceEntryDTO getPriceEntry(String id) throws Exception {
		return _priceEntryHelper.getPriceEntryDTO(id, _company.getCompanyId());
	}

	@Override
	@RequiresScope("HeadlessCommerceAdminPricing.read")
	public CollectionDTO<TierPriceDTO> getTierPrices(
			String id, Pagination pagination)
		throws Exception {

		return _tierPriceHelper.getTierPriceDTOs(
			GetterUtil.getLong(id), pagination);
	}

	@AsyncSupported
	@Override
	@RequiresScope("HeadlessCommerceAdminPricing.write")
	public Response updatePriceEntry(String id, PriceEntryDTO priceEntryDTO)
		throws Exception {

		if (_async.isEnabled()) {
			new Thread(
				() -> {
					try {
						_priceEntryHelper.updateCommercePriceEntry(
							id, _company.getCompanyId(), priceEntryDTO);
					}
					catch (PortalException pe) {
						_log.error(pe, pe);
					}
				}
			).start();

			return null;
		}

		_priceEntryHelper.updateCommercePriceEntry(
			id, _company.getCompanyId(), priceEntryDTO);

		Response.ResponseBuilder responseBuilder = Response.accepted();

		return responseBuilder.build();
	}

	@AsyncSupported
	@Override
	@RequiresScope("HeadlessCommerceAdminPricing.write")
	@Status(Response.Status.CREATED)
	public TierPriceDTO upsertTierPrice(String id, TierPriceDTO tierPriceDTO)
		throws Exception {

		if (_async.isEnabled()) {
			new Thread(
				() -> {
					try {
						_tierPriceHelper.upsertCommerceTierPriceEntry(
							id, tierPriceDTO, _company);
					}
					catch (PortalException pe) {
						_log.error(pe, pe);
					}
				}
			).start();

			return null;
		}

		return _tierPriceHelper.upsertCommerceTierPriceEntry(
			id, tierPriceDTO, _company);
	}

	private static final Log _log = LogFactoryUtil.getLog(
		PriceEntryResourceImpl.class);

	@Context
	private Async _async;

	@Context
	private Company _company;

	@Reference
	private PriceEntryHelper _priceEntryHelper;

	@Reference
	private TierPriceHelper _tierPriceHelper;

}