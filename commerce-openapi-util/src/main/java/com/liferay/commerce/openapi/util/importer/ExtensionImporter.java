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

import com.liferay.commerce.openapi.util.OpenApiContextExtension;
import com.liferay.commerce.openapi.util.util.GetterUtil;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Igor Beslic
 */
public class ExtensionImporter {

	public List<OpenApiContextExtension> getExtensions(
		JsonNode parentJSONNode) {

		JsonNode liferayContextOpenApiExtensionJSONNode = parentJSONNode.get(
			"x-liferay-context");

		if (liferayContextOpenApiExtensionJSONNode == null) {
			_logger.trace("No Liferay context extensions found");

			return Collections.emptyList();
		}

		List<String> liferayContextOpenApiNames = GetterUtil.getAsTextList(
			liferayContextOpenApiExtensionJSONNode);

		List<OpenApiContextExtension> openApiContextExtensions =
			new ArrayList<>();

		for (String liferayContextOpenApiName : liferayContextOpenApiNames) {
			openApiContextExtensions.add(
				OpenApiContextExtension.fromOpenApiName(
					liferayContextOpenApiName));
		}

		return openApiContextExtensions;
	}

	private static final Logger _logger = LoggerFactory.getLogger(
		ExtensionImporter.class);

}