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

import com.liferay.commerce.openapi.util.ComponentDefinition;
import com.liferay.commerce.openapi.util.OpenApiTestUtil;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;

/**
 * @author Igor Beslic
 */
public class ComponentImporterTest {

	@Test
	public void testGetComponents() throws Exception {
		ComponentImporter componentImporter = new ComponentImporter();

		List<ComponentDefinition> components = componentImporter.getComponents(
			OpenApiTestUtil.getComponentDefinitions());

		Assert.assertEquals("Two components expected", 3, components.size());

		ComponentDefinition componentDefinition =
			_getDictionaryComponentDefinition(components);

		Assert.assertNotNull(
			"Dictionary component definition", componentDefinition);
		Assert.assertEquals(
			"Dictionary has reference", "#/components/schemas/DictionaryValue",
			componentDefinition.getItemsReference());
		Assert.assertEquals(
			"Dictionary has referenced model", "DictionaryValue",
			componentDefinition.getItemsReferencedModel());
	}

	private ComponentDefinition _getDictionaryComponentDefinition(
		List<ComponentDefinition> componentDefinitions) {

		for (ComponentDefinition componentDefinition : componentDefinitions) {
			if (componentDefinition.isDictionary()) {
				return componentDefinition;
			}
		}

		return null;
	}

}