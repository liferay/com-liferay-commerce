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
import javax.ws.rs.container.ContainerResponseFilter;
import javax.ws.rs.core.Response;

/**
 * @author Ivica Cardic
 */
public class AsyncSupportedControlFilter implements ContainerResponseFilter {

	@Override
	public void filter(
			ContainerRequestContext containerRequestContext,
			ContainerResponseContext containerResponseContext)
		throws IOException {

		int responseStatus = containerResponseContext.getStatus();

		if (_isStatus(responseStatus, Response.Status.NO_CONTENT) ||
			(_isStatus(responseStatus, Response.Status.CREATED) &&
			 (containerResponseContext.getEntity() == null))) {

			containerResponseContext.setStatus(
				Response.Status.ACCEPTED.getStatusCode());
		}
	}

	private boolean _isStatus(int responseStatus, Response.Status status) {
		if (responseStatus == status.getStatusCode()) {
			return true;
		}

		return false;
	}

}