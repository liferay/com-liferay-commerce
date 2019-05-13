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

package com.liferay.commerce.openapi.util.generator;

import com.liferay.commerce.openapi.util.Parameter;
import com.liferay.commerce.openapi.util.Schema;
import com.liferay.commerce.openapi.util.generator.constants.GeneratorConstants;
import com.liferay.commerce.openapi.util.util.OpenApiComponentUtil;
import com.liferay.commerce.openapi.util.util.Provider;
import com.liferay.commerce.openapi.util.util.StringUtils;

import java.util.Objects;

/**
 * @author Igor Beslic
 */
public class ParameterGenerator {

	public String toAnnotatedMethodContextParameter(Provider provider) {
		return String.format(
			"@Context %s %s", provider.getClassName(),
			StringUtils.lowerCaseFirstChar(provider.getVariableName()));
	}

	public String toAnnotatedMethodParameter(Parameter parameter) {
		String parameterAnnotation = "";

		String location = parameter.getLocation();

		if (location.equals("body")) {
			return _toBodyContentParameter(parameter);
		}
		else if (location.equals("cookie")) {
			parameterAnnotation = "@CookieParam";
		}
		else if (location.equals("form")) {
			parameterAnnotation = "@FormParam";
		}
		else if (location.equals("header")) {
			parameterAnnotation = "@HeaderParam";
		}
		else if (location.equals("query")) {
			parameterAnnotation = "@QueryParam";
		}
		else if (location.equals("path")) {
			parameterAnnotation = "@PathParam";
		}

		if (!parameter.isRequired()) {
			parameterAnnotation =
				GeneratorConstants.NULLABLE_ANNOTATION + parameterAnnotation;
		}

		Provider javaTypeProvider = parameter.getJavaTypeProvider();

		return String.format(
			"%s(\"%s\") %s %s", parameterAnnotation, parameter.getName(),
			javaTypeProvider.getClassName(),
			StringUtils.toCamelCase(parameter.getName()));
	}

	public String toMethodParameter(Parameter parameter) {
		String location = parameter.getLocation();

		if (location.equals("body")) {
			return _toBodyContentParameter(parameter);
		}

		Provider javaTypeProvider = parameter.getJavaTypeProvider();

		String nullableAnnotation = "";

		if (!parameter.isRequired()) {
			nullableAnnotation = GeneratorConstants.NULLABLE_ANNOTATION;
		}

		return String.format(
			"%s %s %s", nullableAnnotation, javaTypeProvider.getClassName(),
			StringUtils.toCamelCase(parameter.getName()));
	}

	public String toMethodParameter(Provider provider) {
		return String.format(
			"%s %s", provider.getClassName(),
			StringUtils.lowerCaseFirstChar(provider.getVariableName()));
	}

	private boolean _isMultipartFormData(Parameter parameter) {
		String mimeType = parameter.getContentMimeType();

		return mimeType.equals("multipart/form-data");
	}

	private String _toBodyContentParameter(Parameter parameter) {
		if (_isMultipartFormData(parameter)) {
			return "MultipartBody multipartBody";
		}

		Schema schema = parameter.getSchema();

		String arrayExpression = "";

		if (Objects.equals(schema.getType(), "array")) {
			arrayExpression = "[]";
		}

		return String.format(
			"%sDTO%s %sDTO",
			OpenApiComponentUtil.getComponentName(schema.getReference()),
			arrayExpression,
			StringUtils.lowerCaseFirstChar(parameter.getName()));
	}

}