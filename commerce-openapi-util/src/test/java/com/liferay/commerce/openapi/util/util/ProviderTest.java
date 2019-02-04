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

package com.liferay.commerce.openapi.util.util;

import org.junit.Assert;
import org.junit.Test;

/**
 * @author Igor Beslic
 */
public class ProviderTest {

	@Test
	public void testThreeParametersConstructor() {
		Provider provider = new Provider(
			"String", "java.lang.String", "localeId");

		Assert.assertEquals("Model name", "String", provider.getModelName());
		Assert.assertEquals(
			"Fully qualified class name", "java.lang.String",
			provider.getModelFQCN());
		Assert.assertEquals(
			"Variable name", "localeId", provider.getVariableName());
	}

	@Test
	public void testTwoParametersConstructor() {
		Provider provider = new Provider(
			"TestDTO", "com.liferay.test.dto.TestDTO");

		Assert.assertEquals("Model name", "TestDTO", provider.getModelName());
		Assert.assertEquals(
			"Fully qualified class name", "com.liferay.test.dto.TestDTO",
			provider.getModelFQCN());
		Assert.assertEquals(
			"Variable name", "TestDTO", provider.getVariableName());
	}

}