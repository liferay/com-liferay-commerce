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

import com.liferay.commerce.openapi.util.OpenApi;
import com.liferay.commerce.openapi.util.config.ConfigurationFactory;
import com.liferay.commerce.openapi.util.config.ConfigurationFactoryTest;
import com.liferay.commerce.openapi.util.config.exception.ConfigurationException;

import java.io.IOException;

import java.util.List;
import java.util.Properties;

import org.junit.Assert;
import org.junit.Test;

/**
 * @author Igor Beslic
 */
public class FileOpenApiReaderTest {

	@Test
	public void testGetOpenApiReaderFromConfiguration() throws Exception {
		Properties configuration = _getTestAConfiguration();

		OpenApiReader openApiReader = OpenApiReaderFactory.getOpenApiReader(
			configuration.getProperty("openapi.url"),
			ConfigurationFactory.getPath(ConfigurationFactoryTest.class));

		Assert.assertEquals(
			"Correct open API locator implementation", FileOpenApiReader.class,
			openApiReader.getClass());

		OpenAPIImporter openAPIImporter = new OpenAPIImporter(openApiReader);

		OpenApi openApi = openAPIImporter.getOpenApi();

		Assert.assertEquals(
			"Load correct open API file", "Open API A", openApi.getTitle());
	}

	@Test
	public void testGetOpenApiReaderFromExternalReference() throws Exception {
		String externalReference =
			"./rest-test-openapi-b.json#/components/responses/NotFound";

		OpenApiReader openApiReader = OpenApiReaderFactory.getOpenApiReader(
			externalReference,
			ConfigurationFactory.getPath(ConfigurationFactoryTest.class));

		Assert.assertEquals(
			"Correct open API locator implementation", FileOpenApiReader.class,
			openApiReader.getClass());

		OpenAPIImporter openAPIImporter = new OpenAPIImporter(openApiReader);

		OpenApi openApi = openAPIImporter.getOpenApi();

		Assert.assertEquals(
			"Load correct open API file", "Open API B", openApi.getTitle());
	}

	private Properties _getTestAConfiguration() throws IOException {
		String configurationPath = ConfigurationFactory.getPath(
			ConfigurationFactoryTest.class);

		List<Properties> configurations =
			ConfigurationFactory.getConfigurations(configurationPath);

		for (Properties candidate : configurations) {
			if ("a".equals(candidate.getProperty("osgi.module.name"))) {
				return candidate;
			}
		}

		throw new ConfigurationException(
			"Unable to locate test A configuration file");
	}

}