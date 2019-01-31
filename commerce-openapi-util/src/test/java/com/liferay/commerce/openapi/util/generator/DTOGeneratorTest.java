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

import com.liferay.commerce.openapi.util.OpenApiComponent;
import com.liferay.commerce.openapi.util.OpenApiFormat;
import com.liferay.commerce.openapi.util.OpenApiProperty;
import com.liferay.commerce.openapi.util.OpenApiTestUtil;
import com.liferay.commerce.openapi.util.importer.ComponentImporter;
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

		DTOGenerator dtoGenerator = new DTOGenerator(
			"test", "test", "com.liferay.test",
			new OpenApiComponent(
				"Component", openApiProperties, "object", null),
			Collections.emptySet());

		String classSource = dtoGenerator.getClassSource();

		Assert.assertTrue(
			"package statement is present",
			containsOnlyOne(classSource, "package com.liferay.test;"));

		Assert.assertTrue(
			"DTO class name has DTO in name",
			containsOnlyOne(classSource, "public class ComponentDTO {"));

		for (OpenApiProperty openApiProperty : openApiProperties) {
			Assert.assertTrue(
				"DTO class name has private variable _" +
					openApiProperty.getName(),
				containsOnlyOne(
					classSource,
					String.format(
						"private %s _%s", openApiProperty.getJavaType(),
						openApiProperty.getName())));

			if (!"boolean".equals(openApiProperty.getJavaType())) {
				continue;
			}

			Assert.assertTrue(
				"DTO class boolean getter has \"is\" syntax",
				containsOnlyOne(
					classSource,
					String.format(
						"public %s is%s", openApiProperty.getJavaType(),
						StringUtils.upperCaseFirstChar(
							openApiProperty.getName()))));
		}
	}

	@Test
	public void testGetDTOSourceIfDictionaryPresent() throws IOException {
		ComponentImporter componentImporter = new ComponentImporter();

		List<OpenApiComponent> components = componentImporter.getComponents(
			OpenApiTestUtil.getComponentDefinitions());

		OpenApiComponent dictionaryConsumerOpenApiComponent = null;

		for (OpenApiComponent openApiComponent : components) {
			if ("DictionaryConsumer".equals(openApiComponent.getName())) {
				dictionaryConsumerOpenApiComponent = openApiComponent;

				break;
			}
		}

		DTOGenerator dtoGenerator = new DTOGenerator(
			"test", "test", "com.liferay.test",
			dictionaryConsumerOpenApiComponent, new HashSet<>(components));

		String classSource = dtoGenerator.getClassSource();

		Assert.assertTrue(
			"package statement is present",
			containsOnlyOne(classSource, "package com.liferay.test;"));

		for (OpenApiProperty openApiProperty :
				dictionaryConsumerOpenApiComponent.getOpenApiProperties()) {

			Assert.assertTrue(
				"DTO class name has private variable _" +
					openApiProperty.getName(),
				containsOnlyOne(
					classSource,
					String.format(
						"private %s _%s",
						OpenApiFormat.getJavaType(
							openApiProperty, new HashSet<>(components)),
						openApiProperty.getName())));
		}
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