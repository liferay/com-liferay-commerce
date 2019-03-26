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

package com.liferay.headless.commerce.admin.site.setting.internal.resource.v1_0;

import com.liferay.headless.commerce.admin.site.setting.dto.v1_0.AvailabilityEstimate;
import com.liferay.headless.commerce.admin.site.setting.internal.util.v1_0.AvailabilityEstimateHelper;
import com.liferay.headless.commerce.admin.site.setting.resource.v1_0.AvailabilityEstimateResource;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.vulcan.pagination.Page;
import com.liferay.portal.vulcan.pagination.Pagination;

import javax.validation.constraints.NotNull;

import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.osgi.service.component.annotations.ServiceScope;

/**
 * @author Zoltán Takács
 */
@Component(
	properties = "OSGI-INF/liferay/rest/v1_0/availability-estimate.properties",
	scope = ServiceScope.PROTOTYPE, service = AvailabilityEstimateResource.class
)
public class AvailabilityEstimateResourceImpl
	extends BaseAvailabilityEstimateResourceImpl {

	@Override
	public Response deleteAvailabilityEstimate(@NotNull Long id)
		throws Exception {

		_availabilityEstimateHelper.deleteAvailabilityEstimate(id);

		Response.ResponseBuilder responseBuilder = Response.noContent();

		return responseBuilder.build();
	}

	@Override
	public AvailabilityEstimate getAvailabilityEstimate(@NotNull Long id)
		throws Exception {

		return _availabilityEstimateHelper.getAvailabilityEstimate(id);
	}

	@Override
	public Page<AvailabilityEstimate> getAvailabilityEstimates(
			@NotNull Long groupId, Pagination pagination)
		throws Exception {

		return _availabilityEstimateHelper.getAvailabilityEstimates(
			groupId, pagination);
	}

	@Override
	public Response updateAvailabilityEstimate(
			@NotNull Long id, AvailabilityEstimate availabilityEstimate)
		throws Exception {

		_availabilityEstimateHelper.updateAvailabilityEstimate(
			id, availabilityEstimate, _user);

		Response.ResponseBuilder responseBuilder = Response.accepted();

		return responseBuilder.build();
	}

	@Override
	public AvailabilityEstimate upsertAvailabilityEstimate(
			@NotNull Long groupId, AvailabilityEstimate availabilityEstimate)
		throws Exception {

		return _availabilityEstimateHelper.upsertAvailabilityEstimate(
			groupId, availabilityEstimate, _user);
	}

	@Reference
	private AvailabilityEstimateHelper _availabilityEstimateHelper;

	@Context
	private User _user;

}