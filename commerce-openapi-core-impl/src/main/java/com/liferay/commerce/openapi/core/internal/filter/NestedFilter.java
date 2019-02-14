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

import com.liferay.commerce.openapi.core.constants.OpenApiPropsKeys;
import com.liferay.commerce.openapi.core.internal.jaxrs.nested.NestedContext;
import com.liferay.commerce.openapi.core.internal.jaxrs.nested.NestedContextThreadLocal;
import com.liferay.portal.kernel.util.Validator;

import java.io.IOException;

import java.util.Arrays;
import java.util.List;

import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.ext.Provider;

import org.apache.cxf.jaxrs.utils.JAXRSUtils;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.jaxrs.whiteboard.JaxrsWhiteboardConstants;

/**
 * @author Ivica Cardic
 */
@Component(
	property = {
		JaxrsWhiteboardConstants.JAX_RS_APPLICATION_SELECT + "=(" + OpenApiPropsKeys.NESTED_FILTER_ENABLED + ")",
		JaxrsWhiteboardConstants.JAX_RS_EXTENSION + "=true"
	},
	service = ContainerRequestFilter.class
)
@Provider
public class NestedFilter implements ContainerRequestFilter {

	@Override
	public void filter(ContainerRequestContext containerRequestContext)
		throws IOException {

		UriInfo uriInfo = containerRequestContext.getUriInfo();

		MultivaluedMap<String, String> queryParameters =
			uriInfo.getQueryParameters();

		String nested = queryParameters.getFirst("nested");

		if (Validator.isNotNull(nested)) {
			List<String> fieldNames = Arrays.asList(nested.split("\\s*,\\s*"));

			MultivaluedMap<String, String> pathParameters =
				uriInfo.getPathParameters();

			NestedContextThreadLocal.setNestedContext(
				new NestedContext(
					fieldNames, JAXRSUtils.getCurrentMessage(), pathParameters,
					queryParameters));
		}
	}

}