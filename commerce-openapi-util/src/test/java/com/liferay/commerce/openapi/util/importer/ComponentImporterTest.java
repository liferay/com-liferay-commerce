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

import com.liferay.commerce.openapi.util.OpenApiComponent;
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

		List<OpenApiComponent> components = componentImporter.getComponents(
			OpenApiTestUtil.getComponentDefinitions());

		Assert.assertEquals("Two components expected", 3, components.size());

		OpenApiComponent openApiComponent =
			_getDictionaryComponentDefinition(components);

		Assert.assertNotNull(
			"Dictionary component definition", openApiComponent);
		Assert.assertEquals(
			"Dictionary has reference", "#/components/schemas/DictionaryValue",
			openApiComponent.getItemsReference());
		Assert.assertEquals(
			"Dictionary has referenced model", "DictionaryValue",
			openApiComponent.getItemsReferencedModel());
	}

	private OpenApiComponent _getDictionaryComponentDefinition(
		List<OpenApiComponent> openApiComponents) {

		for (OpenApiComponent openApiComponent : openApiComponents) {
			if (openApiComponent.isDictionary()) {
				return openApiComponent;
			}
		}

		return null;
	}

}