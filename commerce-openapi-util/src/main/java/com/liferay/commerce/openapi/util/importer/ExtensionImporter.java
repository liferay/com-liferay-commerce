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

import com.liferay.commerce.openapi.util.LiferayContextOpenApiExtension;
import com.liferay.commerce.openapi.util.OpenApiComponent;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Igor Beslic
 */
public class ExtensionImporter {

	public List<LiferayContextOpenApiExtension> getExtensions(
		JsonNode parentJSONNode, List<OpenApiComponent> openApiComponents) {

		JsonNode extensionJSONNode = parentJSONNode.get("x-liferay-context");

		if (extensionJSONNode == null) {
			return Collections.emptyList();
		}

		List<LiferayContextOpenApiExtension> liferayContextOpenApiExtensions = new ArrayList<>();

		Iterator<String> iterator = extensionJSONNode.fieldNames();

		ParameterImporter parameterImporter = new ParameterImporter();

		while (iterator.hasNext()) {
			String name = iterator.next();

			JsonNode extensionDefinition = extensionJSONNode.get(name);

			_logger.info(
				"Importing extension {} with parameter size {}", name,
				parameters.size());

			try {
				liferayContextOpenApiExtensions.add(new LiferayContextOpenApiExtension(name, parameters));
			}
			catch (IllegalArgumentException iae) {
				_logger.warn("Unable to process extension: {}", name);
			}
		}

		return liferayContextOpenApiExtensions;
	}

	private static final Logger _logger = LoggerFactory.getLogger(
		ExtensionImporter.class);

}