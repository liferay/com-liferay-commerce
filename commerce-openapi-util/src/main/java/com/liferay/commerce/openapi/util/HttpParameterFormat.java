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
public enum HttpParameterFormat {

	BIGDECIMAL("bigdecimal", HttpParameterType.NUMBER, "java.math.BigDecimal"),
	BINARY("binary", HttpParameterType.STRING, "byte[]"),
	BOOLEAN("boolean", HttpParameterType.BOOLEAN, "boolean", true),
	BYTE("byte", HttpParameterType.STRING, "byte"),
	DATE("date", HttpParameterType.STRING, "java.util.Date"),
	DATE_TIME("date-time", HttpParameterType.STRING, "java.util.Date"),
	DOUBLE("double", HttpParameterType.NUMBER, "double"),
	FLOAT("float", HttpParameterType.NUMBER, "float", true),
	INT32("int32", HttpParameterType.INTEGER, "int", true),
	INT64("int64", HttpParameterType.INTEGER, "long"),
	STRING(null, HttpParameterType.STRING, "String", true);

	public static HttpParameterFormat fromHttpParameterTypeAndDefinition(
		HttpParameterType httpParameterType, String swaggerDefinitionFormat) {

		HttpParameterFormat defaultHTTPParameterFormat = null;

		for (HttpParameterFormat httpParameterFormat : values()) {
			if (httpParameterType != httpParameterFormat._httpParameterType) {
				continue;
			}

			if ((swaggerDefinitionFormat == null) &&
				httpParameterFormat._default) {

				return httpParameterFormat;
			}

			if ((swaggerDefinitionFormat != null) &&
				swaggerDefinitionFormat.equals(
					httpParameterFormat._swaggerDefinitionFormat)) {

				return httpParameterFormat;
			}

			if (httpParameterFormat._default) {
				defaultHTTPParameterFormat = httpParameterFormat;
			}
		}

		return defaultHTTPParameterFormat;
	}

	public String getJavaType() {
		return _javaType;
	}

	private HttpParameterFormat(
		String swaggerDefinitionFormat, HttpParameterType httpParameterType,
		String javaType) {

		_swaggerDefinitionFormat = swaggerDefinitionFormat;
		_httpParameterType = httpParameterType;
		_javaType = javaType;
	}

	private HttpParameterFormat(
		String swaggerDefinitionFormat, HttpParameterType httpParameterType,
		String javaType, boolean defaultFormat) {

		this(swaggerDefinitionFormat, httpParameterType, javaType);

		_default = defaultFormat;
	}

	private boolean _default;
	private HttpParameterType _httpParameterType;
	private String _javaType;
	private String _swaggerDefinitionFormat;

}