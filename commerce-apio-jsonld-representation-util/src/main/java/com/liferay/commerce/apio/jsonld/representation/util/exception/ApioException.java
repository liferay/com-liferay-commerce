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

package com.liferay.commerce.apio.jsonld.representation.util.exception;

import java.io.IOException;

/**
 * @author Zoltán Takács
 */
public class ApioException extends IOException {

	public ApioException(int code, String message) {
		super(message);

		_code = code;
	}

	public ApioException(int code, String message, Throwable cause) {
		super(message, cause);

		_code = code;
	}

	public ApioException(Throwable cause) {
		super(cause);

		_code = _DEFAULT_ERROR_CODE;
	}

	public int getCode() {
		return _code;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder("{");

		if (getCause() != null) {
			sb.append("cause=");
			sb.append(getCause());
			sb.append(", ");
		}

		sb.append("code=");
		sb.append(_code);
		sb.append(", message=");
		sb.append(getMessage());
		sb.append("}");

		return sb.toString();
	}

	private static final int _DEFAULT_ERROR_CODE = 400;

	private final int _code;

}