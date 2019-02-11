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

import java.io.IOException;

import java.util.List;

import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.core.MultivaluedHashMap;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.UriInfo;

import org.junit.Assert;
import org.junit.Test;

import org.mockito.Mockito;

/**
 * @author Ivica Cardic
 */
public class NestedFilterTest {

	@Test
	public void testFilter() throws IOException {
		NestedFilter nestedFilter = new NestedFilter();

		ContainerRequestContext context = Mockito.mock(
			ContainerRequestContext.class);

		UriInfo uriInfo = Mockito.mock(UriInfo.class);

		Mockito.when(
			context.getUriInfo()
		).thenReturn(
			uriInfo
		);

		MultivaluedMap<String, String> queryParameters =
			new MultivaluedHashMap<>();

		Mockito.when(
			uriInfo.getQueryParameters()
		).thenReturn(
			queryParameters
		);

		nestedFilter.filter(context);

		NestedContext nestedContext =
			NestedContextThreadLocal.getNestedContext();

		Assert.assertNull(nestedContext);

		queryParameters.putSingle("nested", "skus,productOptions");

		nestedFilter.filter(context);

		nestedContext = NestedContextThreadLocal.getNestedContext();

		List<String> fieldNames = nestedContext.getFieldNames();

		Assert.assertEquals(fieldNames.toString(), 2, fieldNames.size());
	}

}