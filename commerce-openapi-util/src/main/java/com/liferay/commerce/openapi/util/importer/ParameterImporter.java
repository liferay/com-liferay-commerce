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

import com.liferay.commerce.openapi.util.Parameter;
import com.liferay.commerce.openapi.util.Schema;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Igor Beslic
 */
public class ParameterImporter {

	public static Parameter fromJSONNode(JsonNode parameterJSONNode) {
		JsonNode in = parameterJSONNode.get("in");
		JsonNode nameJSONNode = parameterJSONNode.get("name");
		JsonNode requiredJSONNode = parameterJSONNode.get("required");

		JsonNode schemaJSONNode = parameterJSONNode.get("schema");

		Schema schema = getSchema(schemaJSONNode);

		_logger.debug(
			"Create parameter using {}, {}, {}, {}", nameJSONNode.asText(),
			in.asText(), schema, requiredJSONNode.asBoolean());

		return new Parameter(
			nameJSONNode.asText(), in.asText(), schema,
			requiredJSONNode.asBoolean());
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

	public List<Parameter> getParameters(JsonNode parametersParentJSONNode) {
		List<Parameter> parameters = new ArrayList<>();

		parametersParentJSONNode.forEach(
			parameterJSONNode -> {
				parameters.add(fromJSONNode(parameterJSONNode));
			});

		return parameters;
	}

	private static final Logger _logger = LoggerFactory.getLogger(
		ParameterImporter.class);

}