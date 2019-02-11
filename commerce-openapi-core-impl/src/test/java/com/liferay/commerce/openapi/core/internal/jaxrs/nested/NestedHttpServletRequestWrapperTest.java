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

package com.liferay.commerce.openapi.core.internal.jaxrs.nested;

import com.liferay.commerce.openapi.core.internal.jaxrs.nested.util.MockHttpServletRequest;

import org.junit.Assert;
import org.junit.Test;

/**
 * @author Ivica Cardic
 */
public class NestedHttpServletRequestWrapperTest {

	@Test
	public void testGetParameter() {
		NestedHttpServletRequestWrapper nestedHttpServletRequestWrapper =
			new NestedHttpServletRequestWrapper(
				"skus",
				new MockHttpServletRequest(
					"skus", "externalReferenceCode", "12345", "width", "11"));

		Assert.assertEquals(
			"12345",
			nestedHttpServletRequestWrapper.getParameter(
				"externalReferenceCode"));
		Assert.assertEquals(
			"11", nestedHttpServletRequestWrapper.getParameter("width"));
	}

}