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

package com.liferay.commerce.openapi.util.util;

import com.liferay.commerce.openapi.util.OpenApi;
import com.liferay.commerce.openapi.util.OpenApiComponent;
import com.liferay.commerce.openapi.util.exception.OpenApiReferenceException;
import com.liferay.commerce.openapi.util.importer.OpenAPIImporter;
import com.liferay.commerce.openapi.util.importer.OpenApiReader;
import com.liferay.commerce.openapi.util.importer.OpenApiReaderFactory;

import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author Igor Beslic
 */
public class OpenApiComponentUtil {

	public static OpenApiComponent getByName(
		String name, Set<OpenApiComponent> openApiComponents) {

		for (OpenApiComponent openApiComponent : openApiComponents) {
			if (Objects.equals(name, openApiComponent.getName())) {
				return openApiComponent;
			}
		}

		return null;
	}

	public static String getComponentName(String reference) {
		if (reference == null) {
			return null;
		}

		Matcher matcher = _referenceMatcher(reference);

		return matcher.group(4);
	}

	public static String getExternalUrl(String reference) {
		Matcher matcher = _referenceMatcher(reference);

		return matcher.group(1);
	}

	public static OpenApiComponent getOpenApiComponent(
		String reference, Set<OpenApiComponent> components) {

		if (reference == null) {
			return null;
		}

		if (!OpenApiReaderFactory.isExternalReference(reference)) {
			return getByName(getComponentName(reference), components);
		}

		OpenApi openApi = _getExternalOpenApi(reference);

		return getByName(
			getComponentName(reference), openApi.getOpenApiComponents());
	}

	private static OpenApi _getExternalOpenApi(String reference) {
		String externalUrl = getExternalUrl(reference);

		OpenApi openApi = _openApiCache.get(externalUrl);

		if (openApi != null) {
			return openApi;
		}

		OpenApiReader openApiReader = OpenApiReaderFactory.getOpenApiReader(
			reference);

		OpenAPIImporter openAPIImporter = new OpenAPIImporter(openApiReader);

		openApi = openAPIImporter.getOpenApi();

		_openApiCache.put(externalUrl, openApi);

		return openApi;
	}

	private static Matcher _referenceMatcher(String reference) {
		Matcher matcher = _referencePattern.matcher(reference);

		if (!matcher.find()) {
			throw new OpenApiReferenceException(
				"Unrecognized open API reference pattern for reference " +
					reference);
		}

		return matcher;
	}

	private static final Map<String, OpenApi> _openApiCache =
		new ConcurrentHashMap<>();
	private static final Pattern _referencePattern = Pattern.compile(
		"^(.+)?#/?(\\w+)/(\\w+)/(\\w+)$");

}