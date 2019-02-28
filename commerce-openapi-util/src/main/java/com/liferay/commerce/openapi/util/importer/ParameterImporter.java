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

import com.liferay.commerce.openapi.util.OpenApiComponent;
import com.liferay.commerce.openapi.util.Parameter;
import com.liferay.commerce.openapi.util.Schema;
import com.liferay.commerce.openapi.util.importer.exception.ImporterException;
import com.liferay.commerce.openapi.util.util.GetterUtil;
import com.liferay.commerce.openapi.util.util.OpenApiComponentUtil;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Igor Beslic
 */
public class ParameterImporter {

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

		String format = GetterUtil.getAsTextOrNullIfMisses(
			"format", schemaJSONNode);

		String reference = GetterUtil.getAsTextOrNullIfMisses(
			"$ref", schemaJSONNode);

		String type = GetterUtil.getAsTextOrNullIfMisses(
			"type", schemaJSONNode);

		if ("array".equals(type)) {
			Schema itemSchema = getSchema(schemaJSONNode.get("items"));

			if (itemSchema.getReference() == null) {
				throw new ImporterException(
					"Importer implementation supports only array items " +
						"described with reference object");
			}

			reference = itemSchema.getReference();
		}

		return new Schema(type, format, reference);
	}

	public List<Parameter> getParameters(
		JsonNode parametersParentJSONNode,
		List<OpenApiComponent> openApiComponents) {

		if (parametersParentJSONNode == null) {
			return Collections.emptyList();
		}

		List<Parameter> parameters = new ArrayList<>();

		parametersParentJSONNode.forEach(
			parameterJSONNode -> {
				String reference = GetterUtil.getAsTextOrNullIfMisses(
					"$ref", parameterJSONNode);

				if (reference != null) {
					parameters.add(
						_findOpenApiComponent(reference, openApiComponents));
				}
				else {
					parameters.add(fromJSONNode(parameterJSONNode));
				}
			});

		return parameters;
	}

	private static Parameter _findOpenApiComponent(
			String reference, List<OpenApiComponent> openApiComponents)
		throws ImporterException {

		OpenApiComponent openApiComponent =
			OpenApiComponentUtil.getOpenApiComponent(
				reference, openApiComponents);

		if (openApiComponent == null) {
			throw new ImporterException(
				"Unable to resolve parameter reference " + reference);
		}

		return openApiComponent.getParameter();
	}

	private static final Logger _logger = LoggerFactory.getLogger(
		ParameterImporter.class);

}