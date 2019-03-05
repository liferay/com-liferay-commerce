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

import static org.hamcrest.CoreMatchers.containsString;

import com.liferay.commerce.openapi.util.OpenApi;
import com.liferay.commerce.openapi.util.OpenApiComponent;
import com.liferay.commerce.openapi.util.OpenApiFormat;
import com.liferay.commerce.openapi.util.OpenApiProperty;
import com.liferay.commerce.openapi.util.OpenApiTestUtil;
import com.liferay.commerce.openapi.util.importer.ComponentImporter;
import com.liferay.commerce.openapi.util.util.ArrayProvider;
import com.liferay.commerce.openapi.util.util.MapStringStringProvider;
import com.liferay.commerce.openapi.util.util.MapStringWildcardProvider;
import com.liferay.commerce.openapi.util.util.Provider;
import com.liferay.commerce.openapi.util.util.StringUtils;

import java.io.IOException;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

/**
 * @author Igor Beslic
 */
public class DTOGeneratorTest extends BaseGeneratorTest {

	@Test
	public void testGetDTOSource() throws IOException {
		List<OpenApiProperty> openApiProperties = _getPropertyDefinitions(
			"string", "integer", "boolean");

		OpenApi openApi = new OpenApi("1.0", "Test Open Api", "Test Open Api");

		DTOGenerator dtoGenerator = new DTOGenerator(
			"test", "test", "com.liferay.test", openApi);

		OpenApiComponent.OpenApiComponentBuilder openApiComponentBuilder =
			new OpenApiComponent.OpenApiComponentBuilder();

		openApiComponentBuilder.name("Component");
		openApiComponentBuilder.type("object");
		openApiComponentBuilder.openApiProperties(openApiProperties);

		String classSource = dtoGenerator.getClassSource(
			openApiComponentBuilder.build());

		Assert.assertTrue(
			"package statement is present",
			containsOnlyOne(classSource, "package com.liferay.test.v1_0;"));

		Assert.assertTrue(
			"DTO class name has DTO in name",
			containsOnlyOne(classSource, "public class ComponentDTO {"));

		for (OpenApiProperty openApiProperty : openApiProperties) {
			Provider javaTypeProvider = openApiProperty.getJavaTypeProvider();

			Assert.assertTrue(
				"DTO class has private variable _" +
					openApiProperty.getName(),
				containsOnlyOne(
					classSource,
					String.format(
						"private %s _%s", javaTypeProvider.getModelName(),
						openApiProperty.getName())));

			if (!"Boolean".equals(javaTypeProvider.getModelName())) {
				continue;
			}

			Assert.assertTrue(
				"DTO class boolean getter has \"is\" syntax",
				containsOnlyOne(
					classSource,
					String.format(
						"public %s is%s", javaTypeProvider.getModelName(),
						StringUtils.upperCaseFirstChar(
							openApiProperty.getName()))));
		}
	}

	@Test
	public void testGetDTOSourceIfArrayWithReferencePresent()
		throws IOException {

		ComponentImporter componentImporter = new ComponentImporter();

		List<OpenApiComponent> components = componentImporter.getComponents(
			OpenApiTestUtil.getOpenApiComponentsWithArrayPattern());

		OpenApiComponent hostOpenApiComponent = _getHostOpenApiComponent(
			components);

		OpenApi openApi = new OpenApi("1.0", "Test Open Api", "Test Open Api");

		openApi.setOpenApiComponents(components);

		DTOGenerator dtoGenerator = new DTOGenerator(
			"test", "test", "com.liferay.test", openApi);

		String classSource = dtoGenerator.getClassSource(hostOpenApiComponent);

		Assert.assertTrue(
			"package statement is present",
			containsOnlyOne(classSource, "package com.liferay.test.v1_0;"));

		for (OpenApiProperty openApiProperty :
				hostOpenApiComponent.getOpenApiProperties()) {

			if (!openApiProperty.isArray()) {
				continue;
			}

			Provider javaTypeProvider = OpenApiFormat.getJavaTypeProvider(
				openApiProperty, new HashSet<>(components));

			String expectedVariableName = openApiProperty.getName();

			Assert.assertTrue(
				"array should be handled by array provider",
				javaTypeProvider instanceof ArrayProvider);

			Assert.assertTrue(
				"DTO class has private variable _" + expectedVariableName,
				containsOnlyOne(
					classSource,
					String.format(
						"private %s _%s", javaTypeProvider.getModelName(),
						expectedVariableName)));
		}
	}

	@Test
	public void testGetDTOSourceIfDictionaryPresent() throws IOException {
		ComponentImporter componentImporter = new ComponentImporter();

		List<OpenApiComponent> components = componentImporter.getComponents(
			OpenApiTestUtil.getOpenApiComponentsPattern());

		OpenApiComponent dictionaryConsumerOpenApiComponent = null;

		for (OpenApiComponent openApiComponent : components) {
			if ("DictionaryConsumer".equals(openApiComponent.getName())) {
				dictionaryConsumerOpenApiComponent = openApiComponent;

				break;
			}
		}

		OpenApi openApi = new OpenApi("1.0", "Test Open Api", "Test Open Api");

		openApi.setOpenApiComponents(components);

		DTOGenerator dtoGenerator = new DTOGenerator(
			"test", "test", "com.liferay.test", openApi);

		String classSource = dtoGenerator.getClassSource(
			dictionaryConsumerOpenApiComponent);

		Assert.assertTrue(
			"package statement is present",
			containsOnlyOne(classSource, "package com.liferay.test.v1_0;"));

		for (OpenApiProperty openApiProperty :
				dictionaryConsumerOpenApiComponent.getOpenApiProperties()) {

			Provider javaTypeProvider = OpenApiFormat.getJavaTypeProvider(
				openApiProperty, new HashSet<>(components));

			Assert.assertTrue(
				"DTO class has private variable _" +
					openApiProperty.getName(),
				containsOnlyOne(
					classSource,
					String.format(
						"private %s _%s", javaTypeProvider.getModelName(),
						openApiProperty.getName())));
		}
	}

	@Test
	public void testGetDTOSourceIfFormatDatePresent() throws IOException {
		ComponentImporter componentImporter = new ComponentImporter();

		List<OpenApiComponent> components = componentImporter.getComponents(
			OpenApiTestUtil.getOpenApiComponentsWithFormatDatePattern());

		OpenApiComponent hostOpenApiComponent = _getHostOpenApiComponent(
			components);

		OpenApi openApi = new OpenApi("1.0", "Test Open Api", "Test Open Api");

		openApi.setOpenApiComponents(components);

		DTOGenerator dtoGenerator = new DTOGenerator(
			"test", "test", "com.liferay.test", openApi);

		String classSource = dtoGenerator.getClassSource(hostOpenApiComponent);

		Assert.assertTrue(
			"import statement is present",
			containsOnlyOne(
				classSource,
				"import com.fasterxml.jackson.annotation.JsonFormat;"));

		Assert.assertThat(
			classSource,
			containsString(
				"@JsonFormat(pattern = \"yyyy-MM-dd\", shape = " +
					"JsonFormat.Shape.STRING)\n\tpublic Date getModifyDate"));

		Assert.assertThat(
			classSource,
			containsString(
				"@JsonFormat(pattern = \"yyyy-MM-dd\", shape = " +
					"JsonFormat.Shape.STRING)\n\tpublic void setModifyDate"));
	}

	@Test
	public void testGetDTOSourceIfFormatDateTimePresent() throws IOException {
		ComponentImporter componentImporter = new ComponentImporter();

		List<OpenApiComponent> components = componentImporter.getComponents(
			OpenApiTestUtil.getOpenApiComponentsWithFormatDateTimePattern());

		OpenApiComponent hostOpenApiComponent = _getHostOpenApiComponent(
			components);

		OpenApi openApi = new OpenApi("1.0", "Test Open Api", "Test Open Api");

		openApi.setOpenApiComponents(components);

		DTOGenerator dtoGenerator = new DTOGenerator(
			"test", "test", "com.liferay.test", openApi);

		String classSource = dtoGenerator.getClassSource(hostOpenApiComponent);

		Assert.assertTrue(
			"import statement is present",
			containsOnlyOne(
				classSource,
				"import com.fasterxml.jackson.annotation.JsonFormat;"));

		Assert.assertThat(
			classSource,
			containsString(
				"@JsonFormat(pattern = \"yyyy-MM-dd'T'HH:mm:ss.SSS'Z'\", " +
					"shape = JsonFormat.Shape.STRING)\n\t@Nullable\n\t" +
						"public Date getModifyDateTime"));

		Assert.assertThat(
			classSource,
			containsString(
				"@JsonFormat(pattern = \"yyyy-MM-dd'T'HH:mm:ss.SSS'Z'\", " +
					"shape = JsonFormat.Shape.STRING)\n\tpublic void " +
						"setModifyDateTime"));
	}

	@Test
	public void testGetDTOSourceIfFreeFormObjectPresent() throws IOException {
		ComponentImporter componentImporter = new ComponentImporter();

		List<OpenApiComponent> components = componentImporter.getComponents(
			OpenApiTestUtil.getOpenApiComponentsWithFreeFormObjectPattern());

		OpenApiComponent hostOpenApiComponent = null;

		for (OpenApiComponent openApiComponent : components) {
			if ("FreeFormObjectConsumer".equals(openApiComponent.getName())) {
				hostOpenApiComponent = openApiComponent;

				break;
			}
		}

		List<OpenApiProperty> openApiProperties =
			hostOpenApiComponent.getOpenApiProperties();

		Assert.assertEquals(
			"open api properties count", 4, openApiProperties.size());

		OpenApi openApi = new OpenApi("1.0", "Test Open Api", "Test Open Api");

		openApi.setOpenApiComponents(components);

		DTOGenerator dtoGenerator = new DTOGenerator(
			"test", "test", "com.liferay.test", openApi);

		String classSource = dtoGenerator.getClassSource(hostOpenApiComponent);

		Assert.assertTrue(
			"package statement is present",
			containsOnlyOne(classSource, "package com.liferay.test.v1_0;"));

		for (OpenApiProperty openApiProperty : openApiProperties) {
			if (!openApiProperty.isDictionary()) {
				continue;
			}

			Provider javaTypeProvider = OpenApiFormat.getJavaTypeProvider(
				openApiProperty, new HashSet<>(components));

			String expectedVariableName = openApiProperty.getName();

			Assert.assertEquals(
				"free form object should be handled by map<String, ?> provider",
				MapStringWildcardProvider.class, javaTypeProvider.getClass());

			Assert.assertTrue(
				"DTO class has private variable _" + expectedVariableName,
				containsOnlyOne(
					classSource,
					String.format(
						"private %s _%s", javaTypeProvider.getModelName(),
						expectedVariableName)));
		}
	}

	@Test
	public void testGetDTOSourceIfNestedObjectPresent() throws IOException {
		ComponentImporter componentImporter = new ComponentImporter();

		List<OpenApiComponent> components = componentImporter.getComponents(
			OpenApiTestUtil.getOpenApiComponentsWithNestedObjectPattern());

		OpenApiComponent hostOpenApiComponent = _getHostOpenApiComponent(
			components);

		OpenApi openApi = new OpenApi("1.0", "Test Open Api", "Test Open Api");

		openApi.setOpenApiComponents(components);

		DTOGenerator dtoGenerator = new DTOGenerator(
			"test", "test", "com.liferay.test", openApi);

		String classSource = dtoGenerator.getClassSource(hostOpenApiComponent);

		Assert.assertTrue(
			"package statement is present",
			containsOnlyOne(classSource, "package com.liferay.test.v1_0;"));

		for (OpenApiProperty openApiProperty :
				hostOpenApiComponent.getOpenApiProperties()) {

			if (!openApiProperty.isObject()) {
				continue;
			}

			Provider javaTypeProvider = OpenApiFormat.getJavaTypeProvider(
				openApiProperty, new HashSet<>(components));

			String expectedVariableName = openApiProperty.getName() + "DTO";

			Assert.assertTrue(
				"DTO class has private variable _" + expectedVariableName,
				containsOnlyOne(
					classSource,
					String.format(
						"private %s _%s", javaTypeProvider.getModelName(),
						expectedVariableName)));
		}
	}

	@Test
	public void testGetDTOSourceIfSimpleDictionaryPresent() throws IOException {
		ComponentImporter componentImporter = new ComponentImporter();

		List<OpenApiComponent> components = componentImporter.getComponents(
			OpenApiTestUtil.getOpenApiComponentsWithSimpleDictionaryPattern());

		OpenApiComponent childOpenApiComponent = null;

		for (OpenApiComponent openApiComponent : components) {
			if ("ChildComponent".equals(openApiComponent.getName())) {
				childOpenApiComponent = openApiComponent;

				break;
			}
		}

		OpenApi openApi = new OpenApi("1.0", "Test Open Api", "Test Open Api");

		openApi.setOpenApiComponents(components);

		DTOGenerator dtoGenerator = new DTOGenerator(
			"test", "test", "com.liferay.test", openApi);

		String classSource = dtoGenerator.getClassSource(childOpenApiComponent);

		Assert.assertTrue(
			"package statement is present",
			containsOnlyOne(classSource, "package com.liferay.test.v1_0;"));

		for (OpenApiProperty openApiProperty :
				childOpenApiComponent.getOpenApiProperties()) {

			Provider javaTypeProvider = OpenApiFormat.getJavaTypeProvider(
				openApiProperty, new HashSet<>(components));

			Assert.assertTrue(
				"DTO class has private variable _" +
					openApiProperty.getName(),
				containsOnlyOne(
					classSource,
					String.format(
						"private %s _%s", javaTypeProvider.getModelName(),
						openApiProperty.getName())));

			if (openApiProperty.isDictionary()) {
				Assert.assertEquals(
					"free form object should be handled by map<String, " +
						"String> provider",
					MapStringStringProvider.class, javaTypeProvider.getClass());
			}
		}
	}

	private OpenApiComponent _getHostOpenApiComponent(
		List<OpenApiComponent> components) {

		OpenApiComponent hostOpenApiComponent = null;

		for (OpenApiComponent openApiComponent : components) {
			if ("HostComponent".equals(openApiComponent.getName())) {
				hostOpenApiComponent = openApiComponent;

				break;
			}
		}

		return hostOpenApiComponent;
	}

	private List<OpenApiProperty> _getPropertyDefinitions(
		String... openApiTypeDefinitions) {

		if (openApiTypeDefinitions == null) {
			return Collections.emptyList();
		}

		List<OpenApiProperty> openApiProperties = new ArrayList<>();

		OpenApiProperty.OpenApiPropertyBuilder openApiPropertyBuilder =
			new OpenApiProperty.OpenApiPropertyBuilder();

		for (String openApiTypeDefinition : openApiTypeDefinitions) {
			openApiPropertyBuilder.name(openApiTypeDefinition);
			openApiPropertyBuilder.openApiTypeDefinition(openApiTypeDefinition);

			openApiProperties.add(openApiPropertyBuilder.build());
		}

		return openApiProperties;
	}

}