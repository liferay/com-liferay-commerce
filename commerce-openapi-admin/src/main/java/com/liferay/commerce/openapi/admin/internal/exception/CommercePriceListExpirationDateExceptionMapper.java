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

package com.liferay.commerce.openapi.admin.internal.exception;

import com.liferay.commerce.openapi.core.exception.BaseExceptionMapper;
import com.liferay.commerce.price.list.exception.CommercePriceListExpirationDateException;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.jaxrs.whiteboard.JaxrsWhiteboardConstants;

/**
 * @author Ivica Cardic
 */
@Component(
	property = JaxrsWhiteboardConstants.JAX_RS_EXTENSION + "=true",
	service = ExceptionMapper.class
)
@Provider
public class CommercePriceListExpirationDateExceptionMapper
	extends BaseExceptionMapper<CommercePriceListExpirationDateException> {

	@Override
	public int getErrorCode() {
		return 994;
	}

	@Override
	public String getErrorDescription() {
		return "Invalid expiration date.";
	}

	@Override
	public Response.Status getStatus() {
		return Response.Status.CONFLICT;
	}

}