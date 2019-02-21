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

package com.liferay.commerce.openapi.core.internal.dynamic.feature;

import com.liferay.commerce.openapi.core.annotation.AsyncSupported;
import com.liferay.commerce.openapi.core.constants.OpenApiPropsKeys;
import com.liferay.commerce.openapi.core.internal.filter.AsyncSupportedControlFilter;

import java.lang.reflect.Method;

import javax.ws.rs.Priorities;
import javax.ws.rs.container.DynamicFeature;
import javax.ws.rs.container.ResourceInfo;
import javax.ws.rs.core.FeatureContext;
import javax.ws.rs.ext.Provider;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.jaxrs.whiteboard.JaxrsWhiteboardConstants;

/**
 * @author Ivica Cardic
 */
@Component(
	property = {
		JaxrsWhiteboardConstants.JAX_RS_APPLICATION_SELECT + "=(" + OpenApiPropsKeys.ASYNC_SUPPORTED_FEATURE_ENABLED + ")",
		JaxrsWhiteboardConstants.JAX_RS_EXTENSION + "=true"
	},
	service = DynamicFeature.class
)
@Provider
public class AsyncSupportedFeature implements DynamicFeature {

	@Override
	public void configure(ResourceInfo resourceInfo, FeatureContext context) {
		Method resourceMethod = resourceInfo.getResourceMethod();

		AsyncSupported asyncSupported = resourceMethod.getAnnotation(
			AsyncSupported.class);

		if (asyncSupported == null) {
			return;
		}

		AsyncSupportedControlFilter asyncSupportedControlFilter =
			new AsyncSupportedControlFilter();

		context.register(asyncSupportedControlFilter, Priorities.USER);
	}

}