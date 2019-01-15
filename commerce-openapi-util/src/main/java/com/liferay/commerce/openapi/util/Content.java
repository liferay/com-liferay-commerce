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

package com.liferay.commerce.openapi.util;

/**
 * @author Igor Beslic
 */
public class Content {

	public Content(String mimeType, Schema schema) {
		_mimeType = mimeType;
		_schema = schema;
	}

	public String getMimeType() {
		return _mimeType;
	}

	public Schema getSchema() {
		return _schema;
	}

	@Override
	public String toString() {
		return String.format("{mimeType=%s, schema=%s}", _mimeType, _schema);
	}

	private final String _mimeType;
	private final Schema _schema;

}