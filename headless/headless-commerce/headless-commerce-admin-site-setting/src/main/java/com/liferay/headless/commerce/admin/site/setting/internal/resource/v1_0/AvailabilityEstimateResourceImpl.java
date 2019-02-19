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

import com.liferay.commerce.openapi.core.context.Pagination;
import com.liferay.commerce.openapi.core.model.CollectionDTO;
import com.liferay.headless.commerce.admin.site.setting.model.v1_0.AvailabilityEstimateDTO;
import com.liferay.headless.commerce.admin.site.setting.resource.v1_0.AvailabilityEstimateResource;
import com.liferay.oauth2.provider.scope.RequiresScope;

import java.util.Collections;

import javax.annotation.Generated;

import javax.ws.rs.core.Response;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.ServiceScope;
import org.osgi.service.jaxrs.whiteboard.JaxrsWhiteboardConstants;

/**
 * @author Alessio Antonio Rendina
 */
@Component(
	property = {
		JaxrsWhiteboardConstants.JAX_RS_APPLICATION_SELECT + "=(osgi.jaxrs.name=HeadlessCommerceAdminSiteSetting.Rest)",
		JaxrsWhiteboardConstants.JAX_RS_RESOURCE + "=true", "api.version=v1.0"
	},
	scope = ServiceScope.PROTOTYPE, service = AvailabilityEstimateResource.class
)
@Generated(value = "OSGiRESTModuleGenerator")
public class AvailabilityEstimateResourceImpl
	implements AvailabilityEstimateResource {

	@Override
	@RequiresScope("CommerceOpenApiAdmin.write")
	public Response deleteAvailabilityEstimate(String id) throws Exception {
		Response.ResponseBuilder responseBuilder = Response.noContent();

		return responseBuilder.build();
	}

	@Override
	@RequiresScope("CommerceOpenApiAdmin.read")
	public AvailabilityEstimateDTO getAvailabilityEstimate(String id)
		throws Exception {

		return new AvailabilityEstimateDTO();
	}

	@Override
	@RequiresScope("CommerceOpenApiAdmin.read")
	public CollectionDTO<AvailabilityEstimateDTO> getAvailabilityEstimates(
			Long groupId, Pagination pagination)
		throws Exception {

		return new CollectionDTO(Collections.emptyList(), 0);
	}

	@Override
	@RequiresScope("CommerceOpenApiAdmin.write")
	public Response updateAvailabilityEstimate(
			String id, AvailabilityEstimateDTO availabilityEstimateDTO)
		throws Exception {

		Response.ResponseBuilder responseBuilder = Response.accepted();

		return responseBuilder.build();
	}

	@Override
	@RequiresScope("CommerceOpenApiAdmin.write")
	public AvailabilityEstimateDTO upsertAvailabilityEstimate(
			Long groupId, AvailabilityEstimateDTO availabilityEstimateDTO)
		throws Exception {

		return new AvailabilityEstimateDTO();
	}

}