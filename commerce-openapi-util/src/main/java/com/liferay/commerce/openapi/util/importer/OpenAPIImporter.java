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

package com.liferay.commerce.openapi.util.importer;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import com.liferay.commerce.openapi.util.Method;
import com.liferay.commerce.openapi.util.OpenApi;
import com.liferay.commerce.openapi.util.OpenApiComponent;
import com.liferay.commerce.openapi.util.config.ConfigurationFactory;
import com.liferay.commerce.openapi.util.importer.exception.ImporterException;

import java.io.IOException;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Igor Beslic
 * @author Alessio Antonio Rendina
 */
public class OpenAPIImporter {

	public static void main(String[] args) throws Exception {
		List<Properties> configurations =
			ConfigurationFactory.getConfigurations();

		try {
			for (Properties configuration : configurations) {
				OpenApiReader openApiReader =
					OpenApiReaderFactory.getOpenApiReader(configuration);

				OpenAPIImporter openAPIImporter = new OpenAPIImporter(
					openApiReader);

				OpenApi openApi = openAPIImporter.getOpenApi();

				_logger.debug("Resolved openApi {}", openApi);
			}

			System.exit(0);
		}
		catch (Exception e) {
			e.printStackTrace();

			System.exit(-1);
		}
	}

	public OpenAPIImporter(OpenApiReader openApiReader) {
		_openApiReader = openApiReader;
	}

	public OpenApi getOpenApi() {
		if (_openApi != null) {
			return _openApi;
		}

		try {
			_openAPIDefinitionJSON = _openApiReader.read();

			_openApi = _getOpenApi(_openAPIDefinitionJSON);

			return _openApi;
		}
		catch (IOException ioe) {
			throw new ImporterException(
				"Unable to import open API definition", ioe);
		}
	}

	private OpenApi _getOpenApi(String json) throws IOException {
		ObjectMapper mapper = new ObjectMapper();

		JsonNode rootJSONNode = mapper.readTree(json);

		JsonNode infoJSONNode = rootJSONNode.get("info");

		JsonNode descriptionJSONNode = infoJSONNode.get("description");
		JsonNode titleJSONNode = infoJSONNode.get("title");
		JsonNode versionJSONNode = infoJSONNode.get("version");

		OpenApi openApi = new OpenApi(
			versionJSONNode.asText(), descriptionJSONNode.asText(),
			titleJSONNode.asText());

		ComponentImporter componentImporter = new ComponentImporter();

		Set<OpenApiComponent> openApiComponents =
			componentImporter.getComponents(rootJSONNode.get("components"));

		openApi.setOpenApiComponents(openApiComponents);

		JsonNode pathsJSONNode = rootJSONNode.get("paths");

		Iterator<String> iterator = pathsJSONNode.fieldNames();

		MethodImporter methodImporter = new MethodImporter();

		Map<String, Integer> methodNamesCount = new HashMap<>();

		while (iterator.hasNext()) {
			String path = iterator.next();

			List<Method> methods = methodImporter.getMethods(
				path, pathsJSONNode.get(path), openApiComponents,
				methodNamesCount);

			for (Method method : methods) {
				openApi.addMethod(method);
			}
		}

		return openApi;
	}

	private static Logger _logger = LoggerFactory.getLogger(
		OpenAPIImporter.class);

	private OpenApi _openApi;
	private String _openAPIDefinitionJSON;
	private final OpenApiReader _openApiReader;

}