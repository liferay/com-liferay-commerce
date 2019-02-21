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

import com.liferay.commerce.exception.CommerceOrderValidatorException;
import com.liferay.commerce.openapi.core.exception.BaseExceptionMapper;
import com.liferay.commerce.openapi.core.exception.RESTError;
import com.liferay.commerce.order.CommerceOrderValidatorResult;
import com.liferay.petra.string.StringBundler;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;

import java.util.List;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.jaxrs.whiteboard.JaxrsWhiteboardConstants;

/**
 * @author Alessio Antonio Rendina
 */
@Component(
	property = {
		JaxrsWhiteboardConstants.JAX_RS_APPLICATION_SELECT + "=(osgi.jaxrs.name=CommerceOpenApiAdmin.Rest)",
		JaxrsWhiteboardConstants.JAX_RS_EXTENSION + "=true"
	},
	service = ExceptionMapper.class
)
@Provider
public class CommerceOrderValidatorExceptionMapper
	extends BaseExceptionMapper<CommerceOrderValidatorException> {

	@Override
	public int getErrorCode() {
		return RESTError.ORDER_VALIDATOR_ERROR.getErrorCode();
	}

	@Override
	public String getErrorDescription() {
		return RESTError.ORDER_VALIDATOR_ERROR.getErrorDescription();
	}

	@Override
	public Response.Status getStatus() {
		return RESTError.ORDER_VALIDATOR_ERROR.getStatus();
	}

	@Override
	public Response toResponse(CommerceOrderValidatorException e) {
		_log.error("General REST exception", e);

		Response.ResponseBuilder responseBuilder = Response.status(getStatus());

		Response.Status status = getStatus();

		responseBuilder.entity(
			toJSON(_getErrorMessage(e), status.getStatusCode()));

		responseBuilder.type(MediaType.APPLICATION_JSON_TYPE);

		return responseBuilder.build();
	}

	private String _getErrorMessage(
		CommerceOrderValidatorException commerceOrderValidatorException) {

		List<CommerceOrderValidatorResult> commerceOrderValidatorResults =
			commerceOrderValidatorException.getCommerceOrderValidatorResults();

		if (commerceOrderValidatorResults.isEmpty()) {
			return StringPool.BLANK;
		}

		StringBundler sb = new StringBundler();

		sb.append(StringPool.OPEN_CURLY_BRACE);

		for (CommerceOrderValidatorResult commerceOrderValidatorResult :
				commerceOrderValidatorResults) {

			if (commerceOrderValidatorResults.indexOf(
					commerceOrderValidatorResult) > 0) {

				sb.append(StringPool.COMMA);
			}

			sb.append(StringPool.QUOTE);
			sb.append(commerceOrderValidatorResult.getLocalizedMessage());
			sb.append(StringPool.QUOTE);
		}

		sb.append(StringPool.CLOSE_CURLY_BRACE);

		return sb.toString();
	}

	private static final Log _log = LogFactoryUtil.getLog(
		CommerceOrderValidatorExceptionMapper.class);

}