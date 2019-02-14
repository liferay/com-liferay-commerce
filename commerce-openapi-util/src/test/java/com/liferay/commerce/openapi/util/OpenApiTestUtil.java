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

package com.liferay.commerce.openapi.util;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import java.nio.charset.StandardCharsets;

/**
 * @author Igor Beslic
 */
public class OpenApiTestUtil {

	public static JsonNode getOpenApiComponentsPattern() throws IOException {
		StringBuilder sb = new StringBuilder();

		sb.append("{ \"components\": {\"schemas\": ");
		sb.append("{\"DictionaryDefinition\": {\"type\": \"object\", ");
		sb.append("\"additionalProperties\": {\"$ref\": ");
		sb.append("\"#/components/schemas/DictionaryValue\"}}, ");
		sb.append("\"DictionaryValue\": {\"type\": \"object\", ");
		sb.append("\"properties\": {\"code\": {\"type\": \"integer\", ");
		sb.append("\"format\": \"int64\"}, \"text\": {\"type\": ");
		sb.append("\"string\"}}}, \"DictionaryConsumer\": {\"type\": ");
		sb.append("\"object\", \"properties\": {\"id\": {\"type\": ");
		sb.append("\"integer\", \"format\": \"int64\"}, \"headerTitle\": ");
		sb.append("{\"$ref\": ");
		sb.append("\"#/components/schemas/DictionaryDefinition\"}, ");
		sb.append("\"sectionTitle\": {\"$ref\": ");
		sb.append("\"#/components/schemas/DictionaryDefinition\"}}}}}}}");

		ObjectMapper mapper = new ObjectMapper();

		JsonNode jsonNode = mapper.readTree(sb.toString());

		return jsonNode.get("components");
	}

	public static JsonNode getOpenApiComponentsWithArrayPattern()
		throws IOException {

		StringBuilder sb = new StringBuilder();

		sb.append("{ \"components\": {\"schemas\": ");
		sb.append("{\"ArrayItemComponent\": {\"type\": \"object\", ");
		sb.append("\"properties\": {\"code\": {\"type\": \"integer\", ");
		sb.append("\"format\": \"int64\"}, \"text\": {\"type\": ");
		sb.append("\"string\"}}}, \"HostComponent\": {\"type\": ");
		sb.append("\"object\", \"properties\": {\"id\": {\"type\": ");
		sb.append("\"integer\"}, \"arrayOfComponents\": ");
		sb.append("{\"type\": \"array\", \"items\": {\"$ref\": ");
		sb.append("\"#/components/schemas/ArrayItemComponent\"}}}}}}}}");

		ObjectMapper mapper = new ObjectMapper();

		JsonNode jsonNode = mapper.readTree(sb.toString());

		return jsonNode.get("components");
	}

	public static JsonNode getOpenApiComponentsWithFormatDatePattern()
		throws IOException {

		String resource = _getResource(
			"components/ComponentsWithFormatDatePattern.yaml");

		String componentJSON = _convertYamlToJsonString(resource);

		ObjectMapper mapper = new ObjectMapper();

		JsonNode jsonNode = mapper.readTree(componentJSON);

		return jsonNode.get("components");
	}

	public static JsonNode getOpenApiComponentsWithFormatDateTimePattern()
		throws IOException {

		String resource = _getResource(
			"components/ComponentsWithFormatDateTimePattern.yaml");

		String componentJSON = _convertYamlToJsonString(resource);

		ObjectMapper mapper = new ObjectMapper();

		JsonNode jsonNode = mapper.readTree(componentJSON);

		return jsonNode.get("components");
	}

	public static JsonNode getOpenApiComponentsWithFreeFormObjectPattern()
		throws IOException {

		StringBuilder sb = new StringBuilder();

		sb.append("{ \"components\": {\"schemas\": ");
		sb.append("{\"FreeFormObjectA\": {\"type\": \"object\", ");
		sb.append("\"additionalProperties\": {}}, ");
		sb.append("\"FreeFormObjectB\": {\"type\": \"object\", ");
		sb.append("\"additionalProperties\": \"true\"}, ");
		sb.append("\"SimpleComponent\": {\"type\": \"object\", ");
		sb.append("\"properties\": {\"code\": {\"type\": \"integer\", ");
		sb.append("\"format\": \"int64\"}, \"text\": {\"type\": ");
		sb.append("\"string\"}}}, \"FreeFormObjectConsumer\": {\"type\": ");
		sb.append("\"object\", \"properties\": {\"id\": {\"type\": ");
		sb.append("\"integer\", \"format\": \"int64\"}, \"noodles\": ");
		sb.append("{\"$ref\": ");
		sb.append("\"#/components/schemas/FreeFormObjectA\"}, ");
		sb.append("\"burgers\": {\"$ref\": ");
		sb.append("\"#/components/schemas/FreeFormObjectB\"}, \"cakes\": {");
		sb.append("\"type\": \"object\", \"additionalProperties\": \"true\"}");
		sb.append("}}}}}}");

		ObjectMapper mapper = new ObjectMapper();

		JsonNode jsonNode = mapper.readTree(sb.toString());

		return jsonNode.get("components");
	}

	public static JsonNode getOpenApiComponentsWithNestedObjectPattern()
		throws IOException {

		StringBuilder sb = new StringBuilder();

		sb.append("{ \"components\": {\"schemas\": ");
		sb.append("{\"NestedComponent\": {\"type\": \"object\", ");
		sb.append("\"properties\": {\"code\": {\"type\": \"integer\", ");
		sb.append("\"format\": \"int64\"}, \"modifyDate\": {\"type\": ");
		sb.append("\"string\", \"format\": \"date\"}}}, ");
		sb.append("\"HostComponent\": {\"type\": ");
		sb.append("\"object\", \"properties\": {\"id\": {\"type\": ");
		sb.append("\"integer\", \"format\": \"int64\"}, \"headerTitle\": ");
		sb.append("{\"type\": \"string\"}, \"nestedComponent\": {\"$ref\": ");
		sb.append("\"#/components/schemas/NestedComponent\"}}}}}}");

		ObjectMapper mapper = new ObjectMapper();

		JsonNode jsonNode = mapper.readTree(sb.toString());

		return jsonNode.get("components");
	}

	public static JsonNode getOpenApiComponentsWithSimpleDictionaryPattern()
		throws IOException {

		StringBuilder sb = new StringBuilder();

		sb.append("{ \"components\": {\"schemas\": ");
		sb.append("{\"ChildComponent\": {\"type\": \"object\", ");
		sb.append("\"properties\": {\"code\": {\"type\": \"integer\", ");
		sb.append("\"format\": \"int64\"}, \"localizedText\": {\"type\": ");
		sb.append("\"object\", \"additionalProperties\": {\"type\": ");
		sb.append("\"string\"}}}}, \"ParentComponent\": {\"type\": ");
		sb.append("\"object\", \"properties\": {\"id\": {\"type\": ");
		sb.append("\"integer\", \"format\": \"int64\"}, \"headerTitle\": ");
		sb.append("{\"type\": \"object\", \"additionalProperties\": ");
		sb.append("{\"type\": \"string\"}}, \"childComponent\": {\"$ref\": ");
		sb.append("\"#/components/schemas/ChildComponent\"}}}}}}}");

		ObjectMapper mapper = new ObjectMapper();

		JsonNode jsonNode = mapper.readTree(sb.toString());

		return jsonNode.get("components");
	}

	public static JsonNode getOpenApiContextExtensionPattern()
		throws IOException {

		StringBuilder sb = new StringBuilder();

		sb.append("{ \"x-liferay-context\": [");

		OpenApiContextExtension[] openApiContextExtensions =
			OpenApiContextExtension.values();

		for (int i = 0; i < openApiContextExtensions.length; i++) {
			sb.append("\"");

			OpenApiContextExtension openApiContextExtension =
				openApiContextExtensions[i];

			sb.append(openApiContextExtension.getOpenApiName());

			sb.append("\"");

			if ((i + 1) < openApiContextExtensions.length) {
				sb.append(",");
			}
		}

		sb.append("]}");

		ObjectMapper mapper = new ObjectMapper();

		return mapper.readTree(sb.toString());
	}

	private static String _convertYamlToJsonString(String yaml)
		throws IOException {

		ObjectMapper yamlReader = new ObjectMapper(new YAMLFactory());

		Object obj = yamlReader.readValue(yaml, Object.class);

		ObjectMapper jsonWriter = new ObjectMapper();

		return jsonWriter.writeValueAsString(obj);
	}

	private static String _getResource(String resourcePath) throws IOException {
		Class<?> clazz = OpenApiTestUtil.class;

		try (BufferedReader reader = new BufferedReader(
				new InputStreamReader(
					clazz.getResourceAsStream(resourcePath),
					StandardCharsets.UTF_8))) {

			String line = null;
			StringBuilder stringBuilder = new StringBuilder();
			String ls = System.getProperty("line.separator");

			while ((line = reader.readLine()) != null) {
				stringBuilder.append(line);
				stringBuilder.append(ls);
			}

			stringBuilder.setLength(stringBuilder.length() - ls.length());

			return stringBuilder.toString();
		}
	}

}