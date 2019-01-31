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

import com.liferay.commerce.openapi.util.exception.OpenApiException;

/**
 * @author Igor Beslic
 */
public enum OpenApiType {

	ARRAY("array"), BOOLEAN("boolean"), DICTIONARY("dictionary"),
	INTEGER("integer"), NUMBER("number"), OBJECT("object"), STRING("string");

	public static OpenApiType fromDefinition(String openApiDefinition) {
		for (OpenApiType openApiType : values()) {
			if (openApiDefinition.equals(openApiType._openApiDefinition)) {
				return openApiType;
			}
		}

		throw new OpenApiException(
			"Unknown Open API type " + openApiDefinition);
	}

	private OpenApiType(String openApiDefinition) {
		_openApiDefinition = openApiDefinition;
	}

	private final String _openApiDefinition;

}