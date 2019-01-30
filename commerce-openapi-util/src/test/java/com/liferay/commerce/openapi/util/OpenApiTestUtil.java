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

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;

/**
 * @author Igor Beslic
 */
public class OpenApiTestUtil {

	public static JsonNode getComponentDefinitions()
		throws IOException, JsonProcessingException {

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

}