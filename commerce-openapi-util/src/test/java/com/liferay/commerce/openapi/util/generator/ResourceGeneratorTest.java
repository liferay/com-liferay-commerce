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

import com.liferay.commerce.openapi.util.Content;
import com.liferay.commerce.openapi.util.Method;
import com.liferay.commerce.openapi.util.OpenApi;
import com.liferay.commerce.openapi.util.OpenApiComponent;
import com.liferay.commerce.openapi.util.OpenApiContextExtension;
import com.liferay.commerce.openapi.util.Response;
import com.liferay.commerce.openapi.util.Schema;
import com.liferay.commerce.openapi.util.Security;

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
			"test", "test", "test", "test", "test", false, "test", "test",
			new OpenApi("1.0", "Test Open Api", "Test Open Api"));

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
			"test", "test", "test", "test", "test", false, "test", "test",
			new OpenApi("1.0", "Test Open Api", "Test Open Api"));

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
			_getMethods(
				true, "testModel",
				_getExtensions(
					"company", "language", "pagination", "permissionchecker")),
			_getRandomComponentDefinitions(4, "TestModel"));

		Assert.assertTrue(
			"Exactly one import statement expected",
			containsOnlyOne(
				imports, "com.liferay.portal.kernel.model.Company;"));
		Assert.assertTrue(
			"Exactly one import statement expected",
			containsOnlyOne(
				imports,
				"com.liferay.commerce.openapi.core.context.Pagination;"));
		Assert.assertTrue(
			"Exactly one import statement expected",
			containsOnlyOne(
				imports,
				"com.liferay.portal.kernel.security.permission." +
					"PermissionChecker;"));
		Assert.assertTrue(
			"Exactly one import statement expected",
			containsOnlyOne(imports, "import javax.ws.rs.core.Context;"));
	}

	@Test
	public void testToResourceImplementationMethods() {
		ResourceGenerator resourceGenerator = new ResourceGenerator(
			"test", "test", "test", "test", "test", false, "test", "test",
			new OpenApi("1.0", "Test Open Api", "Test Open Api"));

		String implementationMethods =
			resourceGenerator.toResourceImplementationMethods(
				_getMethods(true, "testModel", Collections.emptyList()),
				_getRandomComponentDefinitions(4, "TestModel"));

		Assert.assertTrue(
			"RequiresScope read expected",
			implementationMethods.contains(
				"@RequiresScope(\"CommerceOpenApiAdmin.read\")\n\tpublic " +
					"CollectionDTO<TestModel1DTO> get(Pagination pagination)"));

		Assert.assertTrue(
			"RequiresScope write expected",
			implementationMethods.contains(
				"@RequiresScope(\"CommerceOpenApiAdmin.write\")\n\tpublic " +
					"Response update()"));

		Assert.assertTrue(
			"no RequiresScope expected",
			implementationMethods.contains(
				"@Override\n\tpublic Response delete()"));

		Assert.assertTrue(
			"Pagination context expected",
			implementationMethods.contains("Pagination pagination"));

		implementationMethods =
			resourceGenerator.toResourceImplementationMethods(
				_getMethods(true, "testModel", _getExtensions("language")),
				_getRandomComponentDefinitions(4, "TestModel"));

		Assert.assertTrue(
			"Context parameters with proper syntax expected",
			implementationMethods.contains(
				"Language language, Pagination pagination"));
	}

	@Test
	public void testToResourceInterfaceMethods() {
		ResourceGenerator resourceGenerator = new ResourceGenerator(
			"test", "test", "test", "test", "test", false, "test", "test",
			new OpenApi("1.0", "Test Open Api", "Test Open Api"));

		String interfaceMethods = resourceGenerator.toResourceInterfaceMethods(
			_getMethods(true, "testModel", Collections.emptyList()),
			_getRandomComponentDefinitions(4, "TestModel"));

		Assert.assertTrue(
			"Pagination context expected",
			interfaceMethods.contains("@Context Pagination pagination"));

		interfaceMethods = resourceGenerator.toResourceInterfaceMethods(
			_getMethods(true, "testModel", _getExtensions("language")),
			_getRandomComponentDefinitions(4, "TestModel"));

		Assert.assertTrue(
			"Context parameters with proper syntax expected",
			interfaceMethods.contains(
				"@Context Language language, @Context Pagination pagination"));
	}

	private List<OpenApiContextExtension> _getExtensions(
		String... extensionOpenApiNames) {

		List<OpenApiContextExtension> openApiContextExtensions =
			new ArrayList<>();

		if (extensionOpenApiNames == null) {
			return openApiContextExtensions;
		}

		for (String extensionOpenApiName : extensionOpenApiNames) {
			openApiContextExtensions.add(
				OpenApiContextExtension.fromOpenApiName(extensionOpenApiName));
		}

		return openApiContextExtensions;
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
		List<OpenApiContextExtension> openApiContextExtensions) {

		List<Method> methods = new ArrayList<>();

		String absolutePath = String.format("/%s/{id}", model);

		methods.add(
			new Method(
				"update", _getSecurity("CommerceOpenApiAdmin.write"),
				_getRequestContents(), "PUT", absolutePath,
				Collections.emptyList(),
				_getResponses(
					responseContentArray, 201, 202, 400, 401, 404, 500),
				openApiContextExtensions));
		methods.add(
			new Method(
				"delete", null, Collections.emptyList(), "DELETE", absolutePath,
				Collections.emptyList(),
				_getResponses(responseContentArray, 204, 401),
				openApiContextExtensions));
		methods.add(
			new Method(
				"get", _getSecurity("CommerceOpenApiAdmin.read"),
				_getRequestContents(), "GET", absolutePath,
				Collections.emptyList(),
				_getResponses(responseContentArray, 200, 400, 404, 500),
				openApiContextExtensions));

		return methods;
	}

	private Set<OpenApiComponent> _getRandomComponentDefinitions(
		int size, String modelPattern) {

		Set<OpenApiComponent> openApiComponents = new HashSet<>();

		String modelReferencePattern = String.format(
			"#/components/schemas/%s", modelPattern);

		for (int i = 0; i < size; i++) {
			OpenApiComponent.OpenApiComponentBuilder openApiComponentBuilder =
				new OpenApiComponent.OpenApiComponentBuilder();

			openApiComponentBuilder.name(modelPattern + i);
			openApiComponentBuilder.type("object");
			openApiComponentBuilder.itemsReference(modelReferencePattern + i);

			openApiComponents.add(openApiComponentBuilder.build());
		}

		return openApiComponents;
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

	private Security _getSecurity(String oAuth2Scope) {
		Security security = new Security();

		security.setOAuth2Scopes(Collections.singletonList(oAuth2Scope));

		return security;
	}

}