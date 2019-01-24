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

package com.liferay.commerce.openapi.util.generator;

import com.liferay.commerce.openapi.util.ComponentDefinition;
import com.liferay.commerce.openapi.util.Content;
import com.liferay.commerce.openapi.util.Extension;
import com.liferay.commerce.openapi.util.Method;
import com.liferay.commerce.openapi.util.Response;
import com.liferay.commerce.openapi.util.Schema;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.Assert;
import org.junit.Test;

/**
 * @author Igor Beslic
 */
public class ResourceGeneratorTest extends BaseGeneratorTest {

	@Test
	public void testGetReturnType() {
		ResourceGenerator resourceGenerator = new ResourceGenerator(
			"test", "test", "test", "test", "test", false, "test", "test");

		Method getMethod = _getMethod(true);

		Assert.assertEquals(
			"Return type is collection DTO", "CollectionDTO<TestModel1DTO> ",
			resourceGenerator.getReturnType(
				getMethod, _getRandomComponentDefinitions(4, "TestModel")));

		getMethod = _getMethod(false);

		Assert.assertEquals(
			"Return type is entity DTO", "TestModel1DTO ",
			resourceGenerator.getReturnType(
				getMethod, _getRandomComponentDefinitions(4, "TestModel")));
	}

	@Test
	public void testToJavaxImports() {
		ResourceGenerator resourceGenerator = new ResourceGenerator(
			"test", "test", "test", "test", "test", false, "test", "test");

		String imports = resourceGenerator.toJavaxImports(
			_getMethods(true, "testModel", Collections.emptyList()),
			_getRandomComponentDefinitions(4, "TestModel"));

		Assert.assertTrue(
			"Exactly one import statement expected",
			containsOnlyOne(imports, "import javax.ws.rs.GET;"));
		Assert.assertTrue(
			"Exactly one import statement expected",
			containsOnlyOne(imports, "import javax.ws.rs.core.Context;"));

		imports = resourceGenerator.toJavaxImports(
			_getMethods(true, "testModel", _getExtensions("locale", "company")),
			_getRandomComponentDefinitions(4, "TestModel"));

		Assert.assertTrue(
			"Exactly one import statement expected",
			containsOnlyOne(imports, "import java.util.Locale;"));
		Assert.assertTrue(
			"Exactly one import statement expected",
			containsOnlyOne(imports, "import javax.ws.rs.core.Context;"));
	}

	@Test
	public void testToResourceImplementationMethods() {
		ResourceGenerator resourceGenerator = new ResourceGenerator(
			"test", "test", "test", "test", "test", false, "test", "test");

		String interfaceMethods =
			resourceGenerator.toResourceImplementationMethods(
				_getMethods(true, "testModel", Collections.emptyList()),
				_getRandomComponentDefinitions(4, "TestModel"));

		Assert.assertTrue(
			"Pagination context expected",
			interfaceMethods.contains("Pagination pagination"));

		interfaceMethods = resourceGenerator.toResourceImplementationMethods(
			_getMethods(true, "testModel", _getExtensions("locale")),
			_getRandomComponentDefinitions(4, "TestModel"));

		Assert.assertTrue(
			"Context parameters with proper syntax expected",
			interfaceMethods.contains("Locale locale, Pagination pagination"));
	}

	@Test
	public void testToResourceInterfaceMethods() {
		ResourceGenerator resourceGenerator = new ResourceGenerator(
			"test", "test", "test", "test", "test", false, "test", "test");

		String interfaceMethods = resourceGenerator.toResourceInterfaceMethods(
			_getMethods(true, "testModel", Collections.emptyList()),
			_getRandomComponentDefinitions(4, "TestModel"));

		Assert.assertTrue(
			"Pagination context expected",
			interfaceMethods.contains("@Context Pagination pagination"));

		interfaceMethods = resourceGenerator.toResourceInterfaceMethods(
			_getMethods(true, "testModel", _getExtensions("locale")),
			_getRandomComponentDefinitions(4, "TestModel"));

		Assert.assertTrue(
			"Context parameters with proper syntax expected",
			interfaceMethods.contains(
				"Locale locale, @Context Pagination pagination"));
	}

	private List<Extension> _getExtensions(String... extensionOpenApiNames) {
		List<Extension> extensions = new ArrayList<>();

		if (extensionOpenApiNames == null) {
			return extensions;
		}

		for (String extensionOpenApiName : extensionOpenApiNames) {
			extensions.add(
				new Extension(extensionOpenApiName, Collections.emptyList()));
		}

		return extensions;
	}

	private Method _getMethod(boolean responseContentArray) {
		for (Method method :
				_getMethods(
					responseContentArray, "testModel",
					Collections.emptyList())) {

			if ("GET".equals(method.getHttpMethod())) {
				return method;
			}
		}

		return null;
	}

	private List<Method> _getMethods(
		boolean responseContentArray, String model,
		List<Extension> extensions) {

		List<Method> methods = new ArrayList<>();

		String absolutePath = String.format("/%s/{id}", model);

		methods.add(
			new Method(
				"update", _getRequestContents(), "PUT", absolutePath,
				Collections.emptyList(),
				_getResponses(
					responseContentArray, 201, 202, 400, 401, 404, 500),
				extensions));
		methods.add(
			new Method(
				"delete", Collections.emptyList(), "DELETE", absolutePath,
				Collections.emptyList(),
				_getResponses(responseContentArray, 204, 401), extensions));
		methods.add(
			new Method(
				"get", _getRequestContents(), "GET", absolutePath,
				Collections.emptyList(),
				_getResponses(responseContentArray, 200, 400, 404, 500),
				extensions));

		return methods;
	}

	private Set<ComponentDefinition> _getRandomComponentDefinitions(
		int size, String modelPattern) {

		Set<ComponentDefinition> componentDefinitions = new HashSet<>();

		String modelReferencePattern = String.format(
			"#/components/schemas/%s", modelPattern);

		for (int i = 0; i < size; i++) {
			componentDefinitions.add(
				new ComponentDefinition(
					modelPattern + i, Collections.emptyList(), "object",
					modelReferencePattern + i));
		}

		return componentDefinitions;
	}

	private List<Content> _getRequestContents() {
		List<Content> contents = new ArrayList<>();

		contents.add(
			new Content(
				"application/json",
				new Schema(null, null, "#/components/schemas/TestModel2")));

		return contents;
	}

	private List<Content> _getResponseContents(boolean responseContentArray) {
		List<Content> contents = new ArrayList<>();

		String responseContentSchemaType = "object";

		if (responseContentArray) {
			responseContentSchemaType = "array";
		}

		contents.add(
			new Content(
				"application/json",
				new Schema(
					responseContentSchemaType, null,
					"#/components/schemas/TestModel1")));
		contents.add(
			new Content(
				"application/json", new Schema("integer", "int32", null)));
		contents.add(
			new Content(
				"application/json",
				new Schema(null, null, "#/components/schemas/Error")));

		return contents;
	}

	private List<Response> _getResponses(
		boolean responseContentArray, Integer... statuses) {

		List<Response> responses = new ArrayList<>();

		for (int status : statuses) {
			if (status == 200) {
				responses.add(
					new Response(
						status, _getResponseContents(responseContentArray)));

				continue;
			}

			responses.add(new Response(status, null));
		}

		return responses;
	}

}