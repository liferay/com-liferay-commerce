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
import com.liferay.commerce.openapi.util.OpenApiTestUtil;
import com.liferay.commerce.openapi.util.ParameterFormat;
import com.liferay.commerce.openapi.util.PropertyDefinition;
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
		List<PropertyDefinition> propertyDefinitions = _getPropertyDefinitions(
			"string", "integer", "boolean");

		DTOGenerator dtoGenerator = new DTOGenerator(
			"test", "test", "com.liferay.test",
			new ComponentDefinition(
				"Component", propertyDefinitions, "object", null),
			Collections.emptySet());

		String classSource = dtoGenerator.getClassSource();

		Assert.assertTrue(
			"package statement is present",
			containsOnlyOne(classSource, "package com.liferay.test;"));

		Assert.assertTrue(
			"DTO class name has DTO in name",
			containsOnlyOne(classSource, "public class ComponentDTO {"));

		for (PropertyDefinition propertyDefinition : propertyDefinitions) {
			Assert.assertTrue(
				"DTO class name has private variable _" +
					propertyDefinition.getName(),
				containsOnlyOne(
					classSource,
					String.format(
						"private %s _%s", propertyDefinition.getJavaType(),
						propertyDefinition.getName())));

			if (!"boolean".equals(propertyDefinition.getJavaType())) {
				continue;
			}

			Assert.assertTrue(
				"DTO class boolean getter has \"is\" syntax",
				containsOnlyOne(
					classSource,
					String.format(
						"public %s is%s", propertyDefinition.getJavaType(),
						StringUtils.upperCaseFirstChar(
							propertyDefinition.getName()))));
		}
	}

	@Test
	public void testGetDTOSourceIfDictionaryPresent() throws IOException {
		ComponentImporter componentImporter = new ComponentImporter();

		List<ComponentDefinition> components = componentImporter.getComponents(
			OpenApiTestUtil.getComponentDefinitions());

		ComponentDefinition dictionaryConsumerComponentDefinition = null;

		for (ComponentDefinition componentDefinition : components) {
			if ("DictionaryConsumer".equals(componentDefinition.getName())) {
				dictionaryConsumerComponentDefinition = componentDefinition;

				break;
			}
		}

		DTOGenerator dtoGenerator = new DTOGenerator(
			"test", "test", "com.liferay.test",
			dictionaryConsumerComponentDefinition, new HashSet<>(components));

		String classSource = dtoGenerator.getClassSource();

		Assert.assertTrue(
			"package statement is present",
			containsOnlyOne(classSource, "package com.liferay.test;"));

		for (PropertyDefinition propertyDefinition :
				dictionaryConsumerComponentDefinition.
					getPropertyDefinitions()) {

			Assert.assertTrue(
				"DTO class name has private variable _" +
					propertyDefinition.getName(),
				containsOnlyOne(
					classSource,
					String.format(
						"private %s _%s",
						ParameterFormat.getJavaType(
							propertyDefinition, new HashSet<>(components)),
						propertyDefinition.getName())));
		}
	}

	private List<PropertyDefinition> _getPropertyDefinitions(
		String... propertyTypes) {

		if (propertyTypes == null) {
			return Collections.emptyList();
		}

		List<PropertyDefinition> propertyDefinitions = new ArrayList<>();

		for (String propertyType : propertyTypes) {
			propertyDefinitions.add(
				new PropertyDefinition(propertyType, propertyType, null));
		}

		return propertyDefinitions;
	}

}