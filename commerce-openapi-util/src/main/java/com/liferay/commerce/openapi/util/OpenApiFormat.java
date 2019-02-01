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
import com.liferay.commerce.openapi.util.util.DTOProvider;
import com.liferay.commerce.openapi.util.util.OpenApiComponentUtil;
import com.liferay.commerce.openapi.util.util.Provider;

import java.util.Set;

/**
 * @author Igor Beslic
 */
public enum OpenApiFormat {

	BIGDECIMAL(
		"bigdecimal", OpenApiType.NUMBER,
		new Provider("BigDecimal", "java.math.BigDecimal"), true),
	BINARY("binary", OpenApiType.STRING, new Provider("Byte[]", null), false),
	BOOLEAN(
		"boolean", OpenApiType.BOOLEAN, new Provider("Boolean", null), true),
	BYTE("byte", OpenApiType.STRING, new Provider("Byte", null), false),
	DATE(
		"date", OpenApiType.STRING, new Provider("Date", "java.util.Date"),
		false),
	DATE_TIME(
		"date-time", OpenApiType.STRING, new Provider("Date", "java.util.Date"),
		false),
	DICTIONARY(
		null, OpenApiType.DICTIONARY, new Provider("Map", "java.util.Map"),
		true),
	DOUBLE("double", OpenApiType.NUMBER, new Provider("Double", null), false),
	FLOAT("float", OpenApiType.NUMBER, new Provider("Float", null), true),
	INT32("int32", OpenApiType.INTEGER, new Provider("Integer", null), true),
	INT64("int64", OpenApiType.INTEGER, new Provider("Long", null), false),
	STRING(null, OpenApiType.STRING, new Provider("String", null), true);

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

	public static Provider getJavaTypeProvider(
		OpenApiProperty openApiProperty,
		Set<OpenApiComponent> openApiComponents) {

		if (openApiProperty.isObject()) {
			OpenApiComponent openApiComponent =
				OpenApiComponentUtil.getSchemaOpenApiComponent(
					Schema.getReferencedModel(
						openApiProperty.getComponentReference()),
					openApiComponents);

			if (openApiComponent.isDictionary()) {
				return DICTIONARY.getProvider();
			}
			else if (openApiComponent.isObject()) {
				return new DTOProvider(openApiComponent.getName());
			}

			throw new OpenApiException(
				"Unable to resolve java type for " + openApiComponent);
		}

		return openApiProperty.getJavaTypeProvider();
	}

	public String getGetterSyntax() {
		if (this == BOOLEAN) {
			return "is";
		}

		return "get";
	}

	public Provider getProvider() {
		return _provider;
	}

	public String getSetterSyntax() {
		return "set";
	}

	private OpenApiFormat(
		String openApiFormatExpression, OpenApiType openApiType,
		Provider javaTypeProvider, boolean defaultFormat) {

		_default = defaultFormat;
		_openApiFormatExpression = openApiFormatExpression;
		_openApiType = openApiType;
		_provider = javaTypeProvider;
	}

	private final boolean _default;
	private final String _openApiFormatExpression;
	private final OpenApiType _openApiType;
	private final Provider _provider;

}