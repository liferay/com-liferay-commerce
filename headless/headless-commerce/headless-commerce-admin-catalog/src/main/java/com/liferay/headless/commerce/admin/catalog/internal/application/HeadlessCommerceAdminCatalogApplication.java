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

package com.liferay.headless.commerce.admin.catalog.internal.application;

import com.liferay.commerce.openapi.core.constants.OpenApiPropsKeys;

import javax.annotation.Generated;

import javax.ws.rs.core.Application;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.jaxrs.whiteboard.JaxrsWhiteboardConstants;

/**
 * @author Alessio Antonio Rendina
 */
@Component(
	property = {
		JaxrsWhiteboardConstants.JAX_RS_APPLICATION_BASE + "=/commerce-admin-catalog",
		JaxrsWhiteboardConstants.JAX_RS_EXTENSION_SELECT + "=(osgi.jaxrs.name=Liferay.OAuth2)",
		JaxrsWhiteboardConstants.JAX_RS_NAME + "=HeadlessCommerceAdminCatalog.Rest",
		OpenApiPropsKeys.ASYNC_SUPPORTED_FEATURE_ENABLED,
		OpenApiPropsKeys.CONTEXT_PROVIDERS_ENABLED,
		OpenApiPropsKeys.EXCEPTION_MAPPERS_ENABLED,
		OpenApiPropsKeys.MESSAGE_BODY_READERS_ENABLED,
		OpenApiPropsKeys.MESSAGE_BODY_WRITERS_ENABLED,
		OpenApiPropsKeys.NESTED_FILTER_ENABLED,
		OpenApiPropsKeys.PARAMETER_CONVERTER_PROVIDERS_ENABLED,
		OpenApiPropsKeys.SERVICE_EVENT_FILTER_ENABLED,
		OpenApiPropsKeys.STATUS_FEATURE_ENABLED,
		"auth.verifier.auth.verifier.BasicAuthHeaderAuthVerifier.urls.includes=/*",
		"auth.verifier.auth.verifier.OAuth2RestAuthVerifier.urls.includes=/*",
		"auth.verifier.auth.verifier.PortalSessionAuthVerifier.urls.includes=/*",
		"auth.verifier.guest.allowed=true",
		"oauth2.scopechecker.type=annotations"
	},
	service = Application.class
)
@Generated(value = "OSGiRESTModuleGenerator")
public class HeadlessCommerceAdminCatalogApplication extends Application {
}