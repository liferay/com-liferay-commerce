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

package com.liferay.commerce.apio.jsonld.representation.util.operation;

import java.net.MalformedURLException;
import java.net.URL;

import java.util.Arrays;
import java.util.stream.Stream;

/**
 * Represents a resource's operation.
 *
 * @author Zoltán Takács
 */
public class Operation {

	public Operation(
			String method, String id, String expects, boolean singleModel)
		throws UnsupportedOperationException {

		_validateParameters(method, id, expects);

		_method = method;
		_id = id;
		_expects = expects;
		_singleModel = singleModel;

		_validateOperation();
	}

	public String getExpects() {
		return _expects;
	}

	public String getId() {
		return _id;
	}

	public String getMethod() {
		return _method;
	}

	public boolean isSingleModel() {
		return _singleModel;
	}

	private void _validateOperation() throws UnsupportedOperationException {
		if (_expects.startsWith("http")) {
			try {
				new URL(_expects);
			}
			catch (MalformedURLException murle) {
				throw new UnsupportedOperationException(
					String.format("Malformed URL: %s.", _expects), murle);
			}
		}

		Stream<Method> stream = Arrays.stream(Method.values());

		stream.filter(
			method -> _method.equals(method.name())
		).findFirst(
		).orElseThrow(
			() -> new UnsupportedOperationException(
				String.format("Unsupported operation: %s.", _method))
		);
	}

	private void _validateParameters(String method, String id, String expects) {
		final String message = " is NULL";

		if (method == null) {
			throw new IllegalArgumentException("Method".concat(message));
		}

		if (id == null) {
			throw new IllegalArgumentException("ID".concat(message));
		}

		if (expects == null) {
			throw new IllegalArgumentException("Expects".concat(message));
		}
	}

	private final String _expects;
	private final String _id;
	private final String _method;
	private final boolean _singleModel;

}