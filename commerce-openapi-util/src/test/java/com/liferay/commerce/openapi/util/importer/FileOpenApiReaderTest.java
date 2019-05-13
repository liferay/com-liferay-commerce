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
import java.util.Objects;
import java.util.Properties;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 * @author Igor Beslic
 */
public class FileOpenApiReaderTest {

	@BeforeClass
	public static void setUpClass() {
		ConfigurationFactory.initialize(ConfigurationFactoryTest.class);
	}

	@Test
	public void testGetOpenApiReaderFromConfiguration() throws Exception {
		Properties configuration = _getTestAConfiguration();

		OpenApiReader openApiReader = OpenApiReaderFactory.getOpenApiReader(
			configuration.getProperty("openapi.url"),
			ConfigurationFactory.getPath());

		Assert.assertEquals(
			"Correct open API locator implementation", FileOpenApiReader.class,
			openApiReader.getClass());

		OpenAPIImporter openAPIImporter = new OpenAPIImporter(openApiReader);

		OpenApi openApi = openAPIImporter.getOpenApi();

		Assert.assertEquals(
			"Load correct open API file", "Open API A", openApi.getTitle());
	}

	@Test
	public void testGetOpenApiReaderFromExternalReference() {
		String externalReference =
			"./rest-test-openapi-b.json#/components/responses/NotFound";

		OpenApiReader openApiReader = OpenApiReaderFactory.getOpenApiReader(
			externalReference, ConfigurationFactory.getPath());

		Assert.assertEquals(
			"Correct open API locator implementation", FileOpenApiReader.class,
			openApiReader.getClass());

		OpenAPIImporter openAPIImporter = new OpenAPIImporter(openApiReader);

		OpenApi openApi = openAPIImporter.getOpenApi();

		Assert.assertEquals(
			"Load correct open API file", "Open API B", openApi.getTitle());
	}

	private Properties _getTestAConfiguration() throws IOException {
		List<Properties> configurations =
			ConfigurationFactory.getConfigurations();

		for (Properties candidate : configurations) {
			if (Objects.equals(
					candidate.getProperty("osgi.module.name"), "a")) {

				return candidate;
			}
		}

		throw new ConfigurationException(
			"Unable to locate test A configuration file");
	}

}