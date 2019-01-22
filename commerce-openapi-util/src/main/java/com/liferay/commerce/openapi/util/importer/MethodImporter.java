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

import com.liferay.commerce.openapi.util.ComponentDefinition;
import com.liferay.commerce.openapi.util.Content;
import com.liferay.commerce.openapi.util.Extension;
import com.liferay.commerce.openapi.util.Method;
import com.liferay.commerce.openapi.util.Parameter;
import com.liferay.commerce.openapi.util.Response;
import com.liferay.commerce.openapi.util.Schema;
import com.liferay.commerce.openapi.util.util.GetterUtil;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Igor Beslic
 */
public class MethodImporter {

	public List<Method> getMethods(
		String path, JsonNode pathJSONNode,
		List<ComponentDefinition> componentDefinitions) {

		List<Method> methods = new ArrayList<>();

		Iterator<String> iterator = pathJSONNode.fieldNames();

		ParameterImporter parameterImporter = new ParameterImporter();

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
					parameterImporter.getParameters(
						httpMethodJSONNode.get("parameters"),
						componentDefinitions));
			}

			List<Content> requestBodyContents = new ArrayList<>();

			if (httpMethodJSONNode.has("requestBody")) {
				JsonNode requestBody = httpMethodJSONNode.get("requestBody");

				List<Content> contents = ContentImporter.getContents(
					requestBody);

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
					).schema(
						schema
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

					responses.add(
						new Response(
							GetterUtil.getInteger(entry.getKey()),
							ContentImporter.getContents(jsonNode)));
				});

			List<Extension> extensions = _getMethodExtensions(
				httpMethodJSONNode, componentDefinitions);

			methods.add(
				new Method(
					methodName, requestBodyContents, httpMethodName, path,
					parameters, responses, extensions));
		}

		_logger.trace("Imported {} methods for path {}", methods.size(), path);

		return methods;
	}

	private List<Extension> _getMethodExtensions(
		JsonNode methodDefinitionJSONNode,
		List<ComponentDefinition> componentDefinitions) {

		JsonNode liferayContextExtensionJSONNode = methodDefinitionJSONNode.get(
			"x-liferay-context");

		if (liferayContextExtensionJSONNode == null) {
			return Collections.emptyList();
		}

		ExtensionImporter extensionImporter = new ExtensionImporter();

		return extensionImporter.getExtensions(
			liferayContextExtensionJSONNode, componentDefinitions);
	}

	private static final Logger _logger = LoggerFactory.getLogger(
		MethodImporter.class);

}