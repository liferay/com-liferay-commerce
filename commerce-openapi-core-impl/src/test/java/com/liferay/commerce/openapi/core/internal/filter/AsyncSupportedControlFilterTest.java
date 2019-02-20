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

package com.liferay.commerce.openapi.core.internal.filter;

import java.io.IOException;

import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerResponseContext;
import javax.ws.rs.core.Response;

import org.junit.Assert;
import org.junit.Test;

import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;

/**
 * @author Ivica Cardic
 */
public class AsyncSupportedControlFilterTest {

	@Test
	public void testFilter() throws IOException {
		Mockito.when(
			_containerResponseContext.getStatus()
		).thenReturn(
			Response.Status.NO_CONTENT.getStatusCode()
		);

		_asyncSupportedControlFilter.filter(
			_containerRequestContext, _containerResponseContext);

		ArgumentCaptor<Integer> statusArgumentCaptor = ArgumentCaptor.forClass(
			Integer.class);

		Mockito.verify(
			_containerResponseContext
		).setStatus(
			statusArgumentCaptor.capture()
		);

		Assert.assertEquals(
			Response.Status.ACCEPTED.getStatusCode(),
			(int)statusArgumentCaptor.getValue());

		Mockito.reset(_containerResponseContext);

		Mockito.when(
			_containerResponseContext.getStatus()
		).thenReturn(
			Response.Status.CREATED.getStatusCode()
		);

		_asyncSupportedControlFilter.filter(
			_containerRequestContext, _containerResponseContext);

		statusArgumentCaptor = ArgumentCaptor.forClass(Integer.class);

		Mockito.verify(
			_containerResponseContext
		).setStatus(
			statusArgumentCaptor.capture()
		);

		Assert.assertEquals(
			Response.Status.ACCEPTED.getStatusCode(),
			(int)statusArgumentCaptor.getValue());

		Mockito.reset(_containerResponseContext);

		Mockito.when(
			_containerResponseContext.getStatus()
		).thenReturn(
			Response.Status.OK.getStatusCode()
		);

		_asyncSupportedControlFilter.filter(
			_containerRequestContext, _containerResponseContext);

		Mockito.verify(
			_containerResponseContext, Mockito.never()
		).setStatus(
			Mockito.anyInt()
		);
	}

	private final AsyncSupportedControlFilter _asyncSupportedControlFilter =
		new AsyncSupportedControlFilter();
	private final ContainerRequestContext _containerRequestContext =
		Mockito.mock(ContainerRequestContext.class);
	private final ContainerResponseContext _containerResponseContext =
		Mockito.mock(ContainerResponseContext.class);

}