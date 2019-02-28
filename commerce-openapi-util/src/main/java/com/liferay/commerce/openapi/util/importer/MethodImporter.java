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
import com.fasterxml.jackson.databind.node.ArrayNode;

import com.liferay.commerce.openapi.util.Content;
import com.liferay.commerce.openapi.util.Method;
import com.liferay.commerce.openapi.util.OpenApiComponent;
import com.liferay.commerce.openapi.util.OpenApiContextExtension;
import com.liferay.commerce.openapi.util.Parameter;
import com.liferay.commerce.openapi.util.Response;
import com.liferay.commerce.openapi.util.Schema;
import com.liferay.commerce.openapi.util.Security;
import com.liferay.commerce.openapi.util.util.GetterUtil;
import com.liferay.commerce.openapi.util.util.OpenApiComponentUtil;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Igor Beslic
 */
public class MethodImporter {

	public List<Method> getMethods(
		String path, JsonNode pathJSONNode,
		Set<OpenApiComponent> openApiComponents,
		Map<String, Integer> methodNamesCount) {

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

				methodName = _getMethodName(
					operationIdJSONNode.asText(), methodNamesCount);
			}

			Security security = null;

			if (httpMethodJSONNode.has("security")) {
				SecurityImporter securityImporter = new SecurityImporter();

				security = securityImporter.getSecurity(
					(ArrayNode)httpMethodJSONNode.get("security"));
			}

			List<Parameter> parameters = new ArrayList<>();

			if (httpMethodJSONNode.has("parameters")) {
				parameters.addAll(
					parameterImporter.getParameters(
						httpMethodJSONNode.get("parameters"),
						openApiComponents));
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
						OpenApiComponentUtil.getComponentName(
							schema.getReference())
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

			List<OpenApiContextExtension> openApiContextExtensions =
				_getMethodExtensions(httpMethodJSONNode);

			methods.add(
				new Method(
					methodName, security, requestBodyContents, httpMethodName,
					path, parameters, responses, openApiContextExtensions));
		}

		_logger.trace("Imported {} methods for path {}", methods.size(), path);

		return methods;
	}

	private List<OpenApiContextExtension> _getMethodExtensions(
		JsonNode methodDefinitionJSONNode) {

		ExtensionImporter extensionImporter = new ExtensionImporter();

		return extensionImporter.getExtensions(methodDefinitionJSONNode);
	}

	private String _getMethodName(
		String methodName, Map<String, Integer> methodNamesCount) {

		Integer count = methodNamesCount.get(methodName);

		if (count == null) {
			methodNamesCount.put(methodName, 1);

			return methodName;
		}

		methodNamesCount.put(methodName, ++count);

		return String.format("%s%d", methodName, count);
	}

	private static final Logger _logger = LoggerFactory.getLogger(
		MethodImporter.class);

}