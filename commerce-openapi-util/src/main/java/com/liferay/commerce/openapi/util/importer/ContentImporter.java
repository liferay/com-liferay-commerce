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

import com.liferay.commerce.openapi.util.Content;
import com.liferay.commerce.openapi.util.Schema;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Igor Beslic
 */
public class ContentImporter {

	public static List<Content> getContents(JsonNode contentParentNode) {
		if (!contentParentNode.has("content")) {
			return Collections.emptyList();
		}

		JsonNode contentJSONNode = contentParentNode.get("content");

		List<Content> contents = new ArrayList<>();

		Iterator<String> fieldNames = contentJSONNode.fieldNames();

		fieldNames.forEachRemaining(
			mimeType -> {
				JsonNode mimeTypeJSONNode = contentJSONNode.get(mimeType);

				Schema schema = null;

				if (mimeTypeJSONNode.has("schema")) {
					schema = ParameterImporter.getSchema(
						mimeTypeJSONNode.get("schema"));
				}

				_logger.trace(
					"Content detected: type {} and schema {}", mimeType,
					schema);

				contents.add(new Content(mimeType, schema));
			});

		return contents;
	}

	private static final Logger _logger = LoggerFactory.getLogger(
		ContentImporter.class);

}