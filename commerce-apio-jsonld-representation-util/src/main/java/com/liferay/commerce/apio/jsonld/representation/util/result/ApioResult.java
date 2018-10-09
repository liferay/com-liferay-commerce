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

package com.liferay.commerce.apio.jsonld.representation.util.result;

/**
 * @author Zoltán Takács  Apio Architect response. It stores HTTP status code
 *         and body string
 */
public final class ApioResult {

	/**
	 * Constructor sets status code and response body
	 *
	 * @param statusCode status code
	 * @param body response body; in case of <code>null</code> "" will be set
	 */
	public ApioResult(int statusCode, String body) {
		_statusCode = statusCode;

		if (body == null) {
			body = "";
		}

		_body = body;
	}

	/**
	 * Returns response body
	 *
	 * @return response body
	 */
	public String getBody() {
		return _body;
	}

	/**
	 * Returns response status code
	 *
	 * @return response status code
	 */
	public int getStatusCode() {
		return _statusCode;
	}

	private final String _body;
	private final int _statusCode;

}