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

package com.liferay.commerce.headless.admin.site.setting.internal.resource.v1_0;

import com.liferay.commerce.headless.admin.site.setting.internal.resource.util.v1_0.MeasurementUnitHelper;
import com.liferay.commerce.headless.admin.site.setting.model.v1_0.MeasurementUnitDTO;
import com.liferay.commerce.headless.admin.site.setting.resource.v1_0.MeasurementUnitResource;
import com.liferay.commerce.openapi.core.annotation.Nullable;
import com.liferay.commerce.openapi.core.context.Pagination;
import com.liferay.commerce.openapi.core.model.CollectionDTO;
import com.liferay.oauth2.provider.scope.RequiresScope;
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
		JaxrsWhiteboardConstants.JAX_RS_APPLICATION_SELECT + "=(osgi.jaxrs.name=CommerceHeadlessAdminSiteSetting.Rest)",
		JaxrsWhiteboardConstants.JAX_RS_RESOURCE + "=true", "api.version=v1.0"
	},
	scope = ServiceScope.PROTOTYPE, service = MeasurementUnitResource.class
)
@Generated(value = "OSGiRESTModuleGenerator")
public class MeasurementUnitResourceImpl implements MeasurementUnitResource {

	@Override
	@RequiresScope("CommerceOpenApiAdmin.write")
	public Response deleteMeasurementUnit(String id) throws Exception {
		_measurementUnitHelper.deleteMeasurementUnit(id);

		Response.ResponseBuilder responseBuilder = Response.noContent();

		return responseBuilder.build();
	}

	@Override
	@RequiresScope("CommerceOpenApiAdmin.read")
	public MeasurementUnitDTO getMeasurementUnit(String id) throws Exception {
		return _measurementUnitHelper.getMeasurementUnitDTO(id);
	}

	@Override
	@RequiresScope("CommerceOpenApiAdmin.read")
	public CollectionDTO<MeasurementUnitDTO> getMeasurementUnits(
			Long groupId, @Nullable Integer type, Pagination pagination)
		throws Exception {

		return _measurementUnitHelper.getMeasurementUnitDTOs(
			groupId, type, pagination);
	}

	@Override
	@RequiresScope("CommerceOpenApiAdmin.write")
	public Response updateMeasurementUnit(
			String id, MeasurementUnitDTO measurementUnitDTO)
		throws Exception {

		_measurementUnitHelper.updateMeasurementUnit(
			id, measurementUnitDTO, _user);

		Response.ResponseBuilder responseBuilder = Response.accepted();

		return responseBuilder.build();
	}

	@Override
	@RequiresScope("CommerceOpenApiAdmin.write")
	public MeasurementUnitDTO upsertMeasurementUnit(
			Long groupId, MeasurementUnitDTO measurementUnitDTO)
		throws Exception {

		return _measurementUnitHelper.upsertMeasurementUnit(
			groupId, measurementUnitDTO, _user);
	}

	@Reference
	private MeasurementUnitHelper _measurementUnitHelper;

	@Context
	private User _user;

}