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

import com.liferay.commerce.openapi.util.ComponentDefinition;
import com.liferay.commerce.openapi.util.Content;
import com.liferay.commerce.openapi.util.Definition;
import com.liferay.commerce.openapi.util.Method;
import com.liferay.commerce.openapi.util.Parameter;
import com.liferay.commerce.openapi.util.PropertiesFactory;
import com.liferay.commerce.openapi.util.Response;
import com.liferay.commerce.openapi.util.Schema;
import com.liferay.commerce.openapi.util.importer.exception.ImporterException;
import com.liferay.petra.json.web.service.client.JSONWebServiceClient;
import com.liferay.petra.json.web.service.client.JSONWebServiceException;
import com.liferay.petra.json.web.service.client.internal.JSONWebServiceClientImpl;

import java.io.IOException;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Igor Beslic
 */
public class OpenAPIImporter {

	public static void main(String[] args) throws Exception {
		OpenAPIImporter openAPIImporter = new OpenAPIImporter();

		try {
			Definition definition = openAPIImporter.getDefinition();

			_logger.debug("Resolved definition {}", definition);

			System.exit(0);
		}
		catch (Exception e) {
			e.printStackTrace();

			System.exit(-1);
		}
	}

	public OpenAPIImporter() throws IOException {
		_properties = PropertiesFactory.getPropertiesFor(getClass());

		_apiDefinitionURL = _properties.getProperty("openapi.swagger.url");

		_headers.add(
			new BasicNameValuePair(
				"Authorization",
				_properties.getProperty("openapi.swagger.authorization.key")));

		_jsonWebServiceClient = new JSONWebServiceClientImpl();

		_jsonWebServiceClient.setHostName(
			_properties.getProperty("openapi.swagger.host.name"));

		_jsonWebServiceClient.setHostPort(
			_getInteger(_properties.getProperty("openapi.swagger.host.port")));

		_jsonWebServiceClient.setProtocol(
			_properties.getProperty("openapi.swagger.protocol"));
	}

	public Definition getDefinition() {
		if (_definition != null) {
			return _definition;
		}

		try {
			_openAPIDefinitionJSON = _jsonWebServiceClient.doGet(
				_apiDefinitionURL, Collections.emptyList(), _headers);

			_definition = _getDefinition(_openAPIDefinitionJSON);

			return _definition;
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

	private List<Content> _getContents(JsonNode contentJsonNode) {
		List<Content> contents = new ArrayList<>();

		Iterator<String> fieldNames = contentJsonNode.fieldNames();

		fieldNames.forEachRemaining(
			mimeType -> {
				JsonNode mimeTypeJSONNode = contentJsonNode.get(mimeType);

				Schema schema = null;

				if (mimeTypeJSONNode.has("schema")) {
					schema = ParameterImporter.getSchema(
						mimeTypeJSONNode.get("schema"));
				}

				contents.add(new Content(mimeType, schema));
			});

		return contents;
	}

	private Definition _getDefinition(String json) throws IOException {
		ObjectMapper mapper = new ObjectMapper();

		JsonNode rootJSONNode = mapper.readTree(json);

		JsonNode infoJSONNode = rootJSONNode.get("info");

		JsonNode descriptionJSONNode = infoJSONNode.get("description");
		JsonNode titleJSONNode = infoJSONNode.get("title");
		JsonNode versionJSONNode = infoJSONNode.get("version");

		Definition definition = new Definition(
			versionJSONNode.asText(), descriptionJSONNode.asText(),
			titleJSONNode.asText());

		ComponentImporter componentImporter = new ComponentImporter();

		List<ComponentDefinition> componentDefinitions =
			componentImporter.getComponents(rootJSONNode.get("components"));

		definition.setComponentDefinitions(componentDefinitions);

		JsonNode pathsJSONNode = rootJSONNode.get("paths");

		Iterator<String> iterator = pathsJSONNode.fieldNames();

		while (iterator.hasNext()) {
			String path = iterator.next();

			List<Method> methods = _getMethods(
				path, pathsJSONNode.get(path), componentDefinitions);

			for (Method method : methods) {
				definition.addMethod(method);
			}
		}

		return definition;
	}

	private int _getInteger(String value) {
		if (value == null) {
			return 0;
		}

		try {
			return Integer.parseInt(value);
		}
		catch (NumberFormatException nfe) {
			_logger.error("Unable to parse value {}", value, nfe);
		}

		return 0;
	}

	private List<Method> _getMethods(
		String path, JsonNode pathJSONNode,
		List<ComponentDefinition> componentDefinitions) {

		List<Method> methods = new ArrayList<>();

		Iterator<String> iterator = pathJSONNode.fieldNames();

		while (iterator.hasNext()) {
			String httpMethodName = iterator.next();

			JsonNode httpMethodJSONNode = pathJSONNode.get(httpMethodName);

			String methodName = httpMethodName;

			if (httpMethodJSONNode.has("operationId")) {
				JsonNode operationIdJSONNode = httpMethodJSONNode.get(
					"operationId");

				methodName = operationIdJSONNode.asText();
			}

			List<Parameter> parameters = new ArrayList<>();

			if (httpMethodJSONNode.has("parameters")) {
				parameters.addAll(
					_getParameters(
						httpMethodJSONNode.get("parameters"),
						componentDefinitions));
			}

			List<Content> requestBodyContents = new ArrayList<>();

			if (httpMethodJSONNode.has("requestBody")) {
				JsonNode requestBody = httpMethodJSONNode.get("requestBody");

				List<Content> contents = _getContents(
					requestBody.get("content"));

				if (!contents.isEmpty()) {
					Content content = contents.get(0);

					Schema schema = content.getSchema();

					Parameter.ParameterBuilder parameterBuilder =
						new Parameter.ParameterBuilder();

					parameterBuilder.name(
						schema.getReferencedModel()
					).location(
						"body"
					).content(
						content
					).required(
						true
					);

					parameters.add(parameterBuilder.build());

					requestBodyContents.addAll(contents);
				}
			}

			JsonNode responsesJSONNode = httpMethodJSONNode.get("responses");

			List<Response> responses = new ArrayList<>();

			Iterator<Map.Entry<String, JsonNode>> fields =
				responsesJSONNode.fields();

			fields.forEachRemaining(
				entry -> {
					JsonNode jsonNode = entry.getValue();

					if (jsonNode.has("content")) {
						List<Content> contents = _getContents(
							jsonNode.get("content"));

						responses.add(
							new Response(
								_getInteger(entry.getKey()), contents));
					}
					else {
						responses.add(
							new Response(_getInteger(entry.getKey()), null));
					}
				});

			methods.add(
				new Method(
					methodName, requestBodyContents, httpMethodName, path,
					parameters, responses));
		}

		return methods;
	}

	private List<Parameter> _getParameters(
		JsonNode parametersJSONNode,
		List<ComponentDefinition> componentDefinitions) {

		if (parametersJSONNode.isNull()) {
			return Collections.emptyList();
		}

		List<Parameter> parameters = new ArrayList<>();

		parametersJSONNode.forEach(
			parameterJSONNode -> {
				if (parameterJSONNode.has("$ref")) {
					parameters.add(
						ParameterImporter.fromComponentDefinition(
							parameterJSONNode.get("$ref"),
							componentDefinitions));
				}
				else {
					parameters.add(
						ParameterImporter.fromJSONNode(parameterJSONNode));
				}
			});

		return parameters;
	}

	private static Logger _logger = LoggerFactory.getLogger(
		OpenAPIImporter.class);

	private final String _apiDefinitionURL;
	private Definition _definition;
	private final List<NameValuePair> _headers = new ArrayList<>();
	private final JSONWebServiceClient _jsonWebServiceClient;
	private String _openAPIDefinitionJSON;
	private final Properties _properties;

}