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
import com.liferay.commerce.openapi.util.Extension;
import com.liferay.commerce.openapi.util.Parameter;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Igor Beslic
 */
public class ExtensionImporter {

	public List<Extension> getExtensions(
		JsonNode extensionJSONNode,
		List<ComponentDefinition> componentDefinitions) {

		List<Extension> extensions = new ArrayList<>();

		Iterator<String> iterator = extensionJSONNode.fieldNames();

		ParameterImporter parameterImporter = new ParameterImporter();

		while (iterator.hasNext()) {
			String name = iterator.next();

			JsonNode extensionDefinition = extensionJSONNode.get(name);

			List<Parameter> parameters = parameterImporter.getParameters(
				extensionDefinition.get("parameters"), componentDefinitions);

			_logger.info(
				"Importing extension {} with parameter size {}", name,
				parameters.size());

			try {
				extensions.add(new Extension(name, parameters));
			}
			catch (IllegalArgumentException iae) {
				_logger.warn("Unable to process extension: {}", name);
			}
		}

		return extensions;
	}

	private static final Logger _logger = LoggerFactory.getLogger(
		ExtensionImporter.class);

}