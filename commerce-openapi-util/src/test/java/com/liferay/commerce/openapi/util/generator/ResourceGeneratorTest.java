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
public class ResourceGeneratorTest {

	@Test
	public void testToResourceImplementationMethods() {
		ResourceGenerator resourceGenerator = new ResourceGenerator(
			"test", "test", "test", "test", "test", false, "test", "test");

		String interfaceMethods =
			resourceGenerator.toResourceImplementationMethods(
				_getMethods("testModel"),
				_getRandomComponentDefinitions(4, "TestModel"));

		Assert.assertTrue(
			"Pagination context expected",
			interfaceMethods.contains("Pagination pagination"));
	}

	@Test
	public void testToResourceInterfaceMethods() {
		ResourceGenerator resourceGenerator = new ResourceGenerator(
			"test", "test", "test", "test", "test", false, "test", "test");

		String interfaceMethods = resourceGenerator.toResourceInterfaceMethods(
			_getMethods("testModel"),
			_getRandomComponentDefinitions(4, "TestModel"));

		Assert.assertTrue(
			"Pagination context expected",
			interfaceMethods.contains("@Context Pagination pagination"));
	}

	private List<Method> _getMethods(String model) {
		List<Method> methods = new ArrayList<>();

		String absolutePath = String.format("/%s/{id}", model);

		methods.add(
			new Method(
				"update", _getRequestContents(), "PUT", absolutePath,
				Collections.emptyList(),
				_getResponses(201, 202, 400, 401, 404, 500),
				Collections.emptyList()));
		methods.add(
			new Method(
				"delete", Collections.emptyList(), "DELETE", absolutePath,
				Collections.emptyList(), _getResponses(204, 401),
				Collections.emptyList()));
		methods.add(
			new Method(
				"get", _getRequestContents(), "GET", absolutePath,
				Collections.emptyList(), _getResponses(200, 400, 404, 500),
				Collections.emptyList()));

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

	private List<Content> _getResponseContents() {
		List<Content> contents = new ArrayList<>();

		contents.add(
			new Content(
				"application/json",
				new Schema("array", null, "#/components/schemas/TestModel1")));
		contents.add(
			new Content(
				"application/json", new Schema("integer", "int32", null)));
		contents.add(
			new Content(
				"application/json",
				new Schema(null, null, "#/components/schemas/Error")));

		return contents;
	}

	private List<Response> _getResponses(Integer... statuses) {
		List<Response> responses = new ArrayList<>();

		for (int status : statuses) {
			if (status == 200) {
				responses.add(new Response(status, _getResponseContents()));

				continue;
			}

			responses.add(new Response(status, null));
		}

		return responses;
	}

}