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

import com.liferay.commerce.openapi.util.Method;
import com.liferay.commerce.openapi.util.OpenApi;
import com.liferay.commerce.openapi.util.Parameter;
import com.liferay.commerce.openapi.util.Path;
import com.liferay.commerce.openapi.util.config.ConfigurationFactory;
import com.liferay.commerce.openapi.util.config.ConfigurationFactoryTest;
import com.liferay.commerce.openapi.util.util.Provider;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;

/**
 * @author Igor Beslic
 */
public class OpenApiImporterTest {

	@Test
	public void testReferenceToExternallyDescribedComponents() {
		ConfigurationFactory.initialize(ConfigurationFactoryTest.class);

		OpenApiReader openApiReader = OpenApiReaderFactory.getOpenApiReader(
			"./rest-test-openapi-c.json", ConfigurationFactory.getPath());

		OpenAPIImporter openAPIImporter = new OpenAPIImporter(openApiReader);

		OpenApi openApi = openAPIImporter.getOpenApi();

		Assert.assertEquals(
			"Proper test openapi ", "Open API C", openApi.getTitle());

		List<Path> paths = openApi.getPaths();

		Assert.assertEquals("Only one path expected", 1, paths.size());

		Path path = paths.get(0);

		List<Method> methods = path.getMethods();

		Method method = methods.get(0);

		List<Parameter> parameters = method.getParameters();

		Assert.assertEquals(
			"Only one parameter expected", 1, parameters.size());

		Parameter parameter = parameters.get(0);

		Assert.assertEquals("parameter name", "groupId", parameter.getName());
		Assert.assertEquals(
			"parameter location", "query", parameter.getLocation());

		Provider javaTypeProvider = parameter.getJavaTypeProvider();

		Assert.assertEquals(
			"java type provider instance", Provider.class,
			javaTypeProvider.getClass());

		Assert.assertEquals(
			"parameter java type", "Long", javaTypeProvider.getClassName());
	}

}