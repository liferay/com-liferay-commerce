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

package com.liferay.commerce.openapi.util;

import org.junit.Assert;
import org.junit.Test;

/**
 * @author Igor Beslic
 */
public class OpenApiFormatTest {

	@Test
	public void testGetterSyntax() {
		for (OpenApiFormat openApiFormat : OpenApiFormat.values()) {
			if (openApiFormat == OpenApiFormat.BOOLEAN) {
				Assert.assertEquals(
					"Boolean syntax for getter", "is",
					openApiFormat.getGetterSyntax());

				continue;
			}

			Assert.assertEquals(
				openApiFormat.getJavaType() + " syntax for getter", "get",
				openApiFormat.getGetterSyntax());
		}
	}

}