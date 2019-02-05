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
public class PackageUtilsTest {

	@Test
	public void testToPackageName() {
		Assert.assertEquals(
			"Version package", "v1", PackageUtils.toPackageName("v1"));
		Assert.assertEquals(
			"Version package", "v1_2", PackageUtils.toPackageName("1.2"));
		Assert.assertEquals(
			"Version package", "v1_20_3",
			PackageUtils.toPackageName("v1.20.3"));
		Assert.assertEquals(
			"Version package", "v1_2_3_4",
			PackageUtils.toPackageName("v1.2.3.4"));
	}

	@Test(expected = IllegalArgumentException.class)
	public void testToPackageNameWithIllegalVersionExpression() {
		PackageUtils.toPackageName("x4i111.34.rt");
	}

	@Test(expected = IllegalArgumentException.class)
	public void testToPackageNameWithNullAsIllegalArgument() {
		PackageUtils.toPackageName(null);
	}

}