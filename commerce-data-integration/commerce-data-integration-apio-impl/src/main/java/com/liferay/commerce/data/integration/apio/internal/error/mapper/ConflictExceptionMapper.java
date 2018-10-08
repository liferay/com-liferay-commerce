/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * The contents of this file are subject to the terms of the Liferay Enterprise
 * Subscription License ("License"). You may not use this file except in
 * compliance with the License. You can obtain a copy of the License by
 * contacting Liferay, Inc. See the License for the specific language governing
 * permissions and limitations under the License, including but not limited to
 * distribution rights of the Software.
 *
 *
 *
 */

package com.liferay.commerce.data.integration.apio.internal.error.mapper;

import com.liferay.apio.architect.error.APIError;
import com.liferay.apio.architect.exception.mapper.ExceptionMapper;
import com.liferay.commerce.data.integration.apio.internal.exceptions.ConflictException;

import javax.ws.rs.core.Response;

import org.osgi.service.component.annotations.Component;

/**
 * Converts a {@code ConflictException} to its {@link APIError} representation.
 *
 * @author Zoltán Takács
 */
@Component(immediate = true)
public class ConflictExceptionMapper
	implements ExceptionMapper<ConflictException> {

	@Override
	public APIError map(ConflictException ce) {
		return new APIError(
			ce, "Unable to process the instructions in the request",
			"Conflict: " + ce.getLocalizedMessage(),
			Response.Status.CONFLICT.getStatusCode());
	}

}