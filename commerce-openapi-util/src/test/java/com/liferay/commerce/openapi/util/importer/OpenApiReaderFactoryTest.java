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

import com.liferay.commerce.openapi.util.config.ConfigurationFactory;
import com.liferay.commerce.openapi.util.config.ConfigurationFactoryTest;
import com.liferay.commerce.openapi.util.importer.exception.ReaderException;

import org.junit.Assert;
import org.junit.Test;

/**
 * @author Igor Beslic
 */
public class OpenApiReaderFactoryTest {

	@Test
	public void testGetOpenApiReader() {
		ConfigurationFactory.initialize(ConfigurationFactoryTest.class);

		OpenApiReader openApiReader = OpenApiReaderFactory.getOpenApiReader(
			"https://host.name.testb:8080/./rest-test-openapi-b.json#" +
				"/components/schema/Item",
			ConfigurationFactory.getPath());

		Assert.assertEquals(
			"Correct open API reader implementation", URLOpenApiReader.class,
			openApiReader.getClass());

		openApiReader = OpenApiReaderFactory.getOpenApiReader(
			"/Volumes/dev/openapi.yaml#/components/schema/Item");

		Assert.assertEquals(
			"Correct open API reader implementation", FileOpenApiReader.class,
			openApiReader.getClass());
	}

	@Test(expected = ReaderException.class)
	public void testGetOpenApiReaderIfInternalReferenceProvided() {
		OpenApiReaderFactory.getOpenApiReader("#/components/schema/Item");
	}

}