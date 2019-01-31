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
import com.liferay.commerce.openapi.util.util.ComponentDefinitionUtil;

import java.util.Set;

/**
 * @author Igor Beslic
 */
public enum OpenApiFormat {

	BIGDECIMAL("bigdecimal", OpenApiType.NUMBER, "java.math.BigDecimal"),
	BINARY("binary", OpenApiType.STRING, "byte[]"),
	BOOLEAN("boolean", OpenApiType.BOOLEAN, "boolean", true),
	BYTE("byte", OpenApiType.STRING, "byte"),
	DATE("date", OpenApiType.STRING, "java.util.Date"),
	DATE_TIME("date-time", OpenApiType.STRING, "java.util.Date"),
	DOUBLE("double", OpenApiType.NUMBER, "double"),
	FLOAT("float", OpenApiType.NUMBER, "float", true),
	INT32("int32", OpenApiType.INTEGER, "int", true),
	INT64("int64", OpenApiType.INTEGER, "long"),
	STRING(null, OpenApiType.STRING, "String", true);

	public static OpenApiFormat fromOpenApiTypeAndFormat(
		OpenApiType openApiType, String openApiFormatDefinition) {

		OpenApiFormat defaultOpenApiFormat = null;

		for (OpenApiFormat openApiFormat : values()) {
			if (openApiType != openApiFormat._openApiType) {
				continue;
			}

			if ((openApiFormatDefinition == null) && openApiFormat._default) {
				return openApiFormat;
			}

			if ((openApiFormatDefinition != null) &&
				openApiFormatDefinition.equals(
					openApiFormat._openApiFormatExpression)) {

				return openApiFormat;
			}

			if (openApiFormat._default) {
				defaultOpenApiFormat = openApiFormat;
			}
		}

		return defaultOpenApiFormat;
	}

	public static String getJavaType(
		OpenApiProperty openApiProperty,
		Set<ComponentDefinition> componentDefinitions) {

		if (openApiProperty.isObject()) {
			ComponentDefinition componentDefinition =
				ComponentDefinitionUtil.getSchemaComponentDefinition(
					Schema.getReferencedModel(
						openApiProperty.getComponentReference()),
					componentDefinitions);

			if (componentDefinition.isDictionary()) {
				return "Map<String, String>";
			}
			else if (componentDefinition.isObject()) {
				return componentDefinition.getName() + "DTO";
			}

			throw new OpenApiException(
				"Unable to resolve java type for " + componentDefinition);
		}

		return openApiProperty.getJavaType();
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

	private OpenApiFormat(
		String openApiFormatExpression, OpenApiType openApiType,
		String javaType) {

		_openApiFormatExpression = openApiFormatExpression;
		_openApiType = openApiType;
		_javaType = javaType;
	}

	private OpenApiFormat(
		String openApiFormatExpression, OpenApiType openApiType,
		String javaType, boolean defaultFormat) {

		this(openApiFormatExpression, openApiType, javaType);

		_default = defaultFormat;
	}

	private boolean _default;
	private String _javaType;
	private String _openApiFormatExpression;
	private OpenApiType _openApiType;

}