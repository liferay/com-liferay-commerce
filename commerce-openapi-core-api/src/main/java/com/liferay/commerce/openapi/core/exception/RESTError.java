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

package com.liferay.commerce.openapi.core.exception;

import javax.ws.rs.core.Response;

/**
 * @author Igor Beslic
 * @author Matija Petanjek
 */
public enum RESTError {

	DUPLICATE_PRICE_ENTRY(
		988, "Duplicated price entry", Response.Status.CONFLICT),
	GENERAL_ERROR(999, "General error.", Response.Status.BAD_REQUEST),
	INTERNAL_ERROR(
		998, "Internal error. Please try again later.",
		Response.Status.INTERNAL_SERVER_ERROR),
	NO_SUCH_CP_INSTANCE(
		987, "Unable to find product instance", Response.Status.NOT_FOUND),
	NO_SUCH_PRICE_ENTRY(
		989, "Unable to find price entry", Response.Status.NOT_FOUND),
	SEARCH_ERROR(
		986, "Unable to fix the search index after 10 attempts",
		Response.Status.INTERNAL_SERVER_ERROR);

	public int getErrorCode() {
		return _errorCode;
	}

	public String getErrorDescription() {
		return _errorDescription;
	}

	public Response.Status getStatus() {
		return _status;
	}

	private RESTError(
		int errorCode, String errorDescription, Response.Status status) {

		_errorCode = errorCode;
		_errorDescription = errorDescription;
		_status = status;
	}

	private final int _errorCode;
	private final String _errorDescription;
	private final Response.Status _status;

}