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

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;

/**
 * @author Igor Beslic
 */
public enum RESTError {

	GENERAL_ERROR(999, "General error."),
	INTERNAL_ERROR(998, "Internal error. Please try again later."),
	UNDEFINED(0, "Undefined error.");

	public static RESTError getRESTError(String message) {
		try {
			int idx = message.indexOf("errorCode");

			if (idx < 0) {
				return UNDEFINED;
			}

			idx = idx + 12;

			int errorCode = Integer.parseInt(
				message.substring(idx, message.indexOf(",", idx)));

			return toRestError(errorCode);
		}
		catch (Exception e) {
			if (_log.isDebugEnabled()) {
				_log.debug(e.getMessage(), e);
			}

			return UNDEFINED;
		}
	}

	public static RESTError toRestError(int errorCode) {
		for (RESTError restError : values()) {
			if (restError.getErrorCode() == errorCode) {
				return restError;
			}
		}

		return UNDEFINED;
	}

	public int getErrorCode() {
		return _errorCode;
	}

	public String getErrorDescription() {
		return _errorDescription;
	}

	public String toJSON(String message, int status, Object... args) {
		StringBuilder sb = new StringBuilder(9 + (args.length * 8));

		sb.append("{\"errorCode\": ");
		sb.append(getErrorCode());
		sb.append(", \"errorDescription\": \"");
		sb.append(getErrorDescription());
		sb.append("\", \"message\": \"");
		sb.append(message);
		sb.append("\", \"status\": ");
		sb.append(status);

		if (args.length == 0) {
			sb.append("}");

			return sb.toString();
		}

		sb.append(", \"args\": {");

		for (int i = 0; i < (args.length - 1); i = i + 2) {
			sb.append("\"");
			sb.append(args[i]);
			sb.append("\"");
			sb.append(":");
			sb.append("\"");
			sb.append(String.valueOf(args[i + 1]));
			sb.append("\"");

			if ((i + 2) < args.length) {
				sb.append(", ");
			}
		}

		sb.append("}}");

		return sb.toString();
	}

	private RESTError(int errorCode, String errorDescription) {
		_errorCode = errorCode;
		_errorDescription = errorDescription;
	}

	private static final Log _log = LogFactoryUtil.getLog(RESTError.class);

	private final int _errorCode;
	private final String _errorDescription;

}