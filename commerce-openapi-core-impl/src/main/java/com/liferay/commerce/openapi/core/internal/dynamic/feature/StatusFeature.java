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

import com.liferay.commerce.openapi.core.annotation.Status;
import com.liferay.commerce.openapi.core.internal.filter.StatusControlFilter;

import java.lang.reflect.Method;

import javax.ws.rs.container.DynamicFeature;
import javax.ws.rs.container.ResourceInfo;
import javax.ws.rs.core.FeatureContext;
import javax.ws.rs.ext.Provider;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.jaxrs.whiteboard.JaxrsWhiteboardConstants;

/**
 * @author Zoltán Takács
 */
@Component(
	property = JaxrsWhiteboardConstants.JAX_RS_EXTENSION + "=true",
	service = DynamicFeature.class
)
@Provider
public class StatusFeature implements DynamicFeature {

	public void configure(ResourceInfo resourceInfo, FeatureContext context) {
		Method resourceMethod = resourceInfo.getResourceMethod();

		Status status = resourceMethod.getAnnotation(Status.class);

		if (status == null) {
			return;
		}

		StatusControlFilter statusControlFilter = new StatusControlFilter(
			status.value());

		context.register(statusControlFilter);
	}

}