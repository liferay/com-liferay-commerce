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
import com.liferay.commerce.openapi.util.importer.exception.ImporterException;
import com.liferay.commerce.openapi.util.util.GetterUtil;
import com.liferay.petra.json.web.service.client.JSONWebServiceClient;
import com.liferay.petra.json.web.service.client.JSONWebServiceException;
import com.liferay.petra.json.web.service.client.internal.JSONWebServiceClientImpl;

import java.io.IOException;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Igor Beslic
 * @author Alessio Antonio Rendina
 */
public class OpenAPIImporter {

	public static void main(String[] args) throws Exception {
		OpenAPIImporter openAPIImporter = new OpenAPIImporter();

		try {
			OpenApi openApi = openAPIImporter.getOpenApi();

			_logger.debug("Resolved openApi {}", openApi);

			System.exit(0);
		}
		catch (Exception e) {
			e.printStackTrace();

			System.exit(-1);
		}
	}

	public OpenAPIImporter() {
		this(null);
	}

	public OpenAPIImporter(Properties configuration) {
		_properties = configuration;

		_apiDefinitionURL = _properties.getProperty("openapi.swagger.url");

		_headers.add(
			new BasicNameValuePair(
				"Authorization",
				_properties.getProperty("openapi.swagger.authorization.key")));

		_jsonWebServiceClient = new JSONWebServiceClientImpl();

		_jsonWebServiceClient.setHostName(
			_properties.getProperty("openapi.swagger.host.name"));

		_jsonWebServiceClient.setHostPort(
			GetterUtil.getInteger(
				_properties.getProperty("openapi.swagger.host.port")));

		_jsonWebServiceClient.setProtocol(
			_properties.getProperty("openapi.swagger.protocol"));
	}

	public OpenApi getOpenApi() {
		if (_openApi != null) {
			return _openApi;
		}

		try {
			_openAPIDefinitionJSON = _jsonWebServiceClient.doGet(
				_apiDefinitionURL, Collections.emptyList(), _headers);

			_openApi = _getDefinition(_openAPIDefinitionJSON);

			return _openApi;
		}
		catch (JSONWebServiceException jsonwse) {
			throw new ImporterException(
				"Unable to get open API definition", jsonwse);
		}
		catch (IOException ioe) {
			throw new ImporterException(
				"Unable to get open API definition", ioe);
		}
	}

	private OpenApi _getDefinition(String json) throws IOException {
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

		List<OpenApiComponent> openApiComponents =
			componentImporter.getComponents(rootJSONNode.get("components"));

		openApi.setOpenApiComponents(openApiComponents);

		JsonNode pathsJSONNode = rootJSONNode.get("paths");

		Iterator<String> iterator = pathsJSONNode.fieldNames();

		MethodImporter methodImporter = new MethodImporter();

		while (iterator.hasNext()) {
			String path = iterator.next();

			List<Method> methods = methodImporter.getMethods(
				path, pathsJSONNode.get(path), openApiComponents);

			for (Method method : methods) {
				openApi.addMethod(method);
			}
		}

		return openApi;
	}

	private static Logger _logger = LoggerFactory.getLogger(
		OpenAPIImporter.class);

	private final String _apiDefinitionURL;
	private final List<NameValuePair> _headers = new ArrayList<>();
	private final JSONWebServiceClient _jsonWebServiceClient;
	private OpenApi _openApi;
	private String _openAPIDefinitionJSON;
	private final Properties _properties;

}