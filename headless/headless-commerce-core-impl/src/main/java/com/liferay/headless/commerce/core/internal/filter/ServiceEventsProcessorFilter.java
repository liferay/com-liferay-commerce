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

package com.liferay.headless.commerce.core.internal.filter;

import com.liferay.portal.events.EventsProcessorUtil;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.PropsKeys;
import com.liferay.portal.util.PropsValues;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.container.PreMatching;
import javax.ws.rs.core.Context;
import javax.ws.rs.ext.Provider;

import org.osgi.service.component.annotations.Component;

/**
 * @author Matija Petanjek
 * @author Zoltán Takács
 */
@Component(
	immediate = true,
	property = {
		"osgi.jaxrs.application.select=(osgi.jaxrs.name=Liferay.Headless.Commerce.Admin.*)",
		"osgi.jaxrs.extension=true",
		"osgi.jaxrs.name=Filter.ServiceEventsProcessorFilter"
	},
	service = ContainerRequestFilter.class
)
@PreMatching
@Provider
public class ServiceEventsProcessorFilter implements ContainerRequestFilter {

	@Override
	public void filter(ContainerRequestContext requestContext) {
		try {
			EventsProcessorUtil.process(
				PropsKeys.SERVLET_SERVICE_EVENTS_PRE,
				PropsValues.SERVLET_SERVICE_EVENTS_PRE, _httpServletRequest,
				_httpServletResponse);
		}
		catch (Exception e) {
			_log.error(e, e);
		}
	}

	private static final Log _log = LogFactoryUtil.getLog(
		ServiceEventsProcessorFilter.class);

	@Context
	private HttpServletRequest _httpServletRequest;

	@Context
	private HttpServletResponse _httpServletResponse;

}