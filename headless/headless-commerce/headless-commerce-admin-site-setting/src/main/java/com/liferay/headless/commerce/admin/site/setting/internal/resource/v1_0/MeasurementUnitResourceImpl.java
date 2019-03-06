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

import com.liferay.commerce.openapi.core.annotation.AsyncSupported;
import com.liferay.commerce.openapi.core.context.Async;
import com.liferay.headless.commerce.admin.site.setting.internal.resource.util.v1_0.MeasurementUnitHelper;
import com.liferay.headless.commerce.admin.site.setting.model.v1_0.MeasurementUnitDTO;
import com.liferay.headless.commerce.admin.site.setting.resource.v1_0.MeasurementUnitResource;
import com.liferay.oauth2.provider.scope.RequiresScope;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.User;

import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.osgi.service.component.annotations.ServiceScope;
import org.osgi.service.jaxrs.whiteboard.JaxrsWhiteboardConstants;

/**
 * @author Alessio Antonio Rendina
 * @author Zoltán Takács
 */
@Component(
	property = {
		JaxrsWhiteboardConstants.JAX_RS_APPLICATION_SELECT + "=(osgi.jaxrs.name=HeadlessCommerceAdminSiteSetting.Rest)",
		JaxrsWhiteboardConstants.JAX_RS_RESOURCE + "=true", "api.version=v1.0"
	},
	scope = ServiceScope.PROTOTYPE, service = MeasurementUnitResource.class
)
public class MeasurementUnitResourceImpl implements MeasurementUnitResource {

	@Override
	@RequiresScope("HeadlessCommerceAdminSiteSetting.write")
	public Response deleteMeasurementUnit(String id) throws Exception {
		_measurementUnitHelper.deleteMeasurementUnit(id);

		Response.ResponseBuilder responseBuilder = Response.noContent();

		return responseBuilder.build();
	}

	@Override
	@RequiresScope("HeadlessCommerceAdminSiteSetting.read")
	public MeasurementUnitDTO getMeasurementUnit(String id) throws Exception {
		return _measurementUnitHelper.getMeasurementUnitDTO(id);
	}

	@AsyncSupported
	@Override
	@RequiresScope("HeadlessCommerceAdminSiteSetting.write")
	public Response updateMeasurementUnit(
			String id, MeasurementUnitDTO measurementUnitDTO)
		throws Exception {

		if (_async.isEnabled()) {
			new Thread(
				() -> {
					try {
						_measurementUnitHelper.updateMeasurementUnit(
							id, measurementUnitDTO, _user);
					}
					catch (PortalException pe) {
						_log.error(pe, pe);
					}
				}
			).start();

			return null;
		}

		_measurementUnitHelper.updateMeasurementUnit(
			id, measurementUnitDTO, _user);

		Response.ResponseBuilder responseBuilder = Response.accepted();

		return responseBuilder.build();
	}

	private static final Log _log = LogFactoryUtil.getLog(
		MeasurementUnitResourceImpl.class);

	@Context
	private Async _async;

	@Reference
	private MeasurementUnitHelper _measurementUnitHelper;

	@Context
	private User _user;

}