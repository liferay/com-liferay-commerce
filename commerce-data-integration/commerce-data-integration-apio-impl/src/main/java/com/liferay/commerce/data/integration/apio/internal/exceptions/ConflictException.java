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

package com.liferay.commerce.data.integration.apio.internal.exceptions;

import javax.ws.rs.ClientErrorException;
import javax.ws.rs.core.Response;

/**
 * @author Zoltán Takács
 */
public class ConflictException extends ClientErrorException {

	public ConflictException(int status) {
		super(status);
	}

	public ConflictException(Response.Status status) {
		super(status);
	}

	public ConflictException(Response.Status status, Throwable cause) {
		super(status, cause);
	}

	public ConflictException(String message, int status, Throwable cause) {
		super(message, status, cause);
	}

	public ConflictException(String message, Response.Status status) {
		super(message, status);
	}

	public ConflictException(
		String message, Response.Status status, Throwable cause) {

		super(message, status, cause);
	}

}