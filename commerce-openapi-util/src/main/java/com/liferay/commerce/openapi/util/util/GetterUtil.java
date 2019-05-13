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

package com.liferay.commerce.openapi.util.util;

import com.fasterxml.jackson.databind.JsonNode;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Igor Beslic
 */
public class GetterUtil {

	public static String getAsText(
		String attribute, JsonNode parentJsonNode, String defaultValue) {

		String text = getAsTextOrNullIfMisses(attribute, parentJsonNode);

		if (text == null) {
			return defaultValue;
		}

		return text;
	}

	public static List<String> getAsTextList(JsonNode arrayJSONNode) {
		if (!arrayJSONNode.isArray() || (arrayJSONNode.size() == 0)) {
			return Collections.emptyList();
		}

		List<String> requiredProperties = new ArrayList<>();

		if (arrayJSONNode.isArray() && (arrayJSONNode.size() > 0)) {
			for (int i = 0; i < arrayJSONNode.size(); i++) {
				JsonNode jsonNode = arrayJSONNode.get(i);

				requiredProperties.add(jsonNode.asText());
			}
		}

		return requiredProperties;
	}

	public static String getAsTextOrNullIfMisses(
		String attribute, JsonNode parentJsonNode) {

		if (!parentJsonNode.has(attribute)) {
			return null;
		}

		JsonNode jsonNode = parentJsonNode.get(attribute);

		return jsonNode.asText();
	}

	public static boolean getBoolean(String value) {
		if (Objects.equals(value, "true") || Objects.equals(value, "TRUE")) {
			return true;
		}

		return false;
	}

	public static int getInteger(String value) {
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

	private static final Logger _logger = LoggerFactory.getLogger(
		GetterUtil.class);

}