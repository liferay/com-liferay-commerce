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
import com.liferay.commerce.openapi.util.Parameter;
import com.liferay.commerce.openapi.util.Schema;
import com.liferay.commerce.openapi.util.importer.exception.ImporterException;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Igor Beslic
 */
public class ParameterImporter {

	public static Parameter fromComponentDefinition(
			JsonNode parameterReferenceJSONNode,
			List<ComponentDefinition> componentDefinitions)
		throws ImporterException {

		String parameterReference = Parameter.getParameterReference(
			parameterReferenceJSONNode.asText());

		_logger.debug("Parameter is reference to {}", parameterReference);

		for (ComponentDefinition componentDefinition : componentDefinitions) {
			if (!componentDefinition.isParameter()) {
				continue;
			}

			if (parameterReference.equals(componentDefinition.getName())) {
				_logger.debug(
					"Reference resolved to {}",
					componentDefinition.getParameter());

				return componentDefinition.getParameter();
			}
		}

		throw new ImporterException(
			"Unable to resolve parameter reference " + parameterReference);
	}

	public static Parameter fromJSONNode(JsonNode parameterJSONNode) {
		Parameter.ParameterBuilder parameterBuilder =
			new Parameter.ParameterBuilder();

		JsonNode inJSONNode = parameterJSONNode.get("in");

		parameterBuilder.location(inJSONNode.asText());

		JsonNode nameJSONNode = parameterJSONNode.get("name");

		parameterBuilder.name(nameJSONNode.asText());

		if (parameterJSONNode.has("required")) {
			JsonNode requiredJSONNode = parameterJSONNode.get("required");

			parameterBuilder.required(requiredJSONNode.asBoolean());
		}

		parameterBuilder.schema(getSchema(parameterJSONNode.get("schema")));

		_logger.debug(
			"Create {} parameter {}", inJSONNode.asText(),
			nameJSONNode.asText());

		return parameterBuilder.build();
	}

	public static Schema getSchema(JsonNode schemaJSONNode) {
		if (schemaJSONNode == null) {
			return null;
		}

		String format = null;

		if (schemaJSONNode.has("format")) {
			JsonNode formatJSONNode = schemaJSONNode.get("format");

			format = formatJSONNode.asText();
		}

		String reference = null;

		if (schemaJSONNode.has("$ref")) {
			JsonNode referenceJSONNode = schemaJSONNode.get("$ref");

			reference = referenceJSONNode.asText();
		}

		String type = null;

		if (schemaJSONNode.has("type")) {
			JsonNode typeJSONNode = schemaJSONNode.get("type");

			type = typeJSONNode.asText();
		}

		return new Schema(type, format, reference);
	}

	public List<Parameter> getParameters(
		JsonNode parametersParentJSONNode,
		List<ComponentDefinition> componentDefinitions) {

		if (parametersParentJSONNode == null) {
			return Collections.emptyList();
		}

		List<Parameter> parameters = new ArrayList<>();

		parametersParentJSONNode.forEach(
			parameterJSONNode -> {
				if (parameterJSONNode.has("$ref")) {
					parameters.add(
						fromComponentDefinition(
							parameterJSONNode.get("$ref"),
							componentDefinitions));
				}
				else {
					parameters.add(fromJSONNode(parameterJSONNode));
				}
			});

		return parameters;
	}

	private static final Logger _logger = LoggerFactory.getLogger(
		ParameterImporter.class);

}