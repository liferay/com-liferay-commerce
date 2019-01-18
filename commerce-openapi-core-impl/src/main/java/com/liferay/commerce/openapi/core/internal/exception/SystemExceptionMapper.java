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

package com.liferay.commerce.openapi.core.internal.exception;

import com.liferay.commerce.openapi.core.exception.RESTError;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.jaxrs.whiteboard.JaxrsWhiteboardConstants;

/**
 * @author Igor Beslic
 */
@Component(
	property = JaxrsWhiteboardConstants.JAX_RS_EXTENSION + "=true",
	service = ExceptionMapper.class
)
@Provider
public class SystemExceptionMapper implements ExceptionMapper<SystemException> {

	@Override
	public Response toResponse(SystemException e) {
		_log.error("Internal server exception", e);

		Response.Status internalServerError =
			Response.Status.INTERNAL_SERVER_ERROR;

		Response.ResponseBuilder responseBuilder = Response.status(
			internalServerError);

		RESTError restError = RESTError.INTERNAL_ERROR;

		responseBuilder.entity(
			restError.toJSON(
				e.getMessage(), internalServerError.getStatusCode()));

		responseBuilder.type(MediaType.APPLICATION_JSON_TYPE);

		return responseBuilder.build();
	}

	private static final Log _log = LogFactoryUtil.getLog(
		SystemExceptionMapper.class);

}