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
public enum ParameterFormat {

	BIGDECIMAL("bigdecimal", ParameterType.NUMBER, "java.math.BigDecimal"),
	BINARY("binary", ParameterType.STRING, "byte[]"),
	BOOLEAN("boolean", ParameterType.BOOLEAN, "boolean", true),
	BYTE("byte", ParameterType.STRING, "byte"),
	DATE("date", ParameterType.STRING, "java.util.Date"),
	DATE_TIME("date-time", ParameterType.STRING, "java.util.Date"),
	DOUBLE("double", ParameterType.NUMBER, "double"),
	FLOAT("float", ParameterType.NUMBER, "float", true),
	INT32("int32", ParameterType.INTEGER, "int", true),
	INT64("int64", ParameterType.INTEGER, "long"),
	STRING(null, ParameterType.STRING, "String", true);

	public static ParameterFormat fromHttpParameterTypeAndDefinition(
		ParameterType parameterType, String swaggerDefinitionFormat) {

		ParameterFormat defaultParameterFormat = null;

		for (ParameterFormat parameterFormat : values()) {
			if (parameterType != parameterFormat._parameterType) {
				continue;
			}

			if ((swaggerDefinitionFormat == null) && parameterFormat._default) {
				return parameterFormat;
			}

			if ((swaggerDefinitionFormat != null) &&
				swaggerDefinitionFormat.equals(
					parameterFormat._swaggerDefinitionFormat)) {

				return parameterFormat;
			}

			if (parameterFormat._default) {
				defaultParameterFormat = parameterFormat;
			}
		}

		return defaultParameterFormat;
	}

	public String getGetterSyntax() {
		if (this == BOOLEAN) {
			return "is";
		}

		return "get";
	}

	public String getJavaType() {
		return _javaType;
	}

	public String getSetterSyntax() {
		return "set";
	}

	private ParameterFormat(
		String swaggerDefinitionFormat, ParameterType parameterType,
		String javaType) {

		_swaggerDefinitionFormat = swaggerDefinitionFormat;
		_parameterType = parameterType;
		_javaType = javaType;
	}

	private ParameterFormat(
		String swaggerDefinitionFormat, ParameterType parameterType,
		String javaType, boolean defaultFormat) {

		this(swaggerDefinitionFormat, parameterType, javaType);

		_default = defaultFormat;
	}

	private boolean _default;
	private String _javaType;
	private ParameterType _parameterType;
	private String _swaggerDefinitionFormat;

}