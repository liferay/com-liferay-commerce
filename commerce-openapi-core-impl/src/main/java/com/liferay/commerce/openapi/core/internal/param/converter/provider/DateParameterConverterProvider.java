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

package com.liferay.commerce.openapi.core.internal.param.converter.provider;

import com.liferay.commerce.openapi.core.constants.OpenApiPropsKeys;
import com.liferay.commerce.openapi.core.internal.param.converter.DateParameterConverter;

import java.lang.annotation.Annotation;
import java.lang.reflect.Type;

import java.util.Date;

import javax.ws.rs.ext.ParamConverter;
import javax.ws.rs.ext.ParamConverterProvider;
import javax.ws.rs.ext.Provider;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.jaxrs.whiteboard.JaxrsWhiteboardConstants;

/**
 * @author Ivica Cardic
 */
@Component(
	property = {
		JaxrsWhiteboardConstants.JAX_RS_APPLICATION_SELECT + "=(" + OpenApiPropsKeys.PARAMETER_CONVERTER_PROVIDERS_ENABLED + ")",
		JaxrsWhiteboardConstants.JAX_RS_EXTENSION + "=true"
	},
	service = ParamConverterProvider.class
)
@Provider
public class DateParameterConverterProvider implements ParamConverterProvider {

	@Override
	@SuppressWarnings("unchecked")
	public <T> ParamConverter<T> getConverter(
		Class<T> rawType, Type genericType, Annotation[] annotations) {

		if (Date.class.equals(rawType)) {
			return (ParamConverter<T>)new DateParameterConverter();
		}

		return null;
	}

}