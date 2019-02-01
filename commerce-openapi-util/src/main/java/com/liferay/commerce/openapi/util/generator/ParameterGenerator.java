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
import com.liferay.commerce.openapi.util.util.Provider;
import com.liferay.commerce.openapi.util.util.StringUtils;

/**
 * @author Igor Beslic
 */
public class ParameterGenerator {

	public String toAnnotatedMethodContextParameter(String parameterType) {
		return String.format(
			"@Context %s %s", parameterType,
			StringUtils.lowerCaseFirstChar(parameterType));
	}

	public String toAnnotatedMethodParameter(Parameter parameter) {
		String parameterAnnotation = "";

		String location = parameter.getLocation();

		if (location.equals("body")) {
			Schema schema = parameter.getSchema();

			return String.format(
				"%sDTO %sDTO", schema.getReferencedModel(),
				StringUtils.lowerCaseFirstChar(parameter.getName()));
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

		Provider javaTypeProvider = parameter.getJavaTypeProvider();

		return String.format(
			"%s(\"%s\") %s %s", parameterAnnotation, parameter.getName(),
			javaTypeProvider.getModelName(),
			StringUtils.toCamelCase(parameter.getName()));
	}

	public String toMethodParameter(Parameter parameter) {
		String location = parameter.getLocation();

		if (location.equals("body")) {
			Schema schema = parameter.getSchema();

			return String.format(
				"%sDTO %sDTO", schema.getReferencedModel(),
				StringUtils.lowerCaseFirstChar(parameter.getName()));
		}

		Provider javaTypeProvider = parameter.getJavaTypeProvider();

		return String.format(
			"%s %s", javaTypeProvider.getModelName(),
			StringUtils.toCamelCase(parameter.getName()));
	}

	public String toMethodParameter(String parameterType) {
		return String.format(
			"%s %s", parameterType,
			StringUtils.lowerCaseFirstChar(parameterType));
	}

}