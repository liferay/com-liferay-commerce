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

package com.liferay.commerce.openapi.admin.internal.application;

import javax.annotation.Generated;

import javax.ws.rs.core.Application;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.jaxrs.whiteboard.JaxrsWhiteboardConstants;

/**
 * @author Igor Beslic
 */
@Component(
	property = {
		JaxrsWhiteboardConstants.JAX_RS_APPLICATION_BASE + "=/commerce-admin",
		JaxrsWhiteboardConstants.JAX_RS_EXTENSION_SELECT + "=(osgi.jaxrs.name=Liferay.OAuth2)",
		JaxrsWhiteboardConstants.JAX_RS_NAME + "=CommerceOpenApiAdmin.Rest",
		"auth.verifier.auth.verifier.BasicAuthHeaderAuthVerifier.urls.includes=/*",
		"auth.verifier.auth.verifier.OAuth2RestAuthVerifier.urls.includes=/*",
		"auth.verifier.auth.verifier.PortalSessionAuthVerifier.urls.includes=/*",
		"auth.verifier.guest.allowed=true",
		"liferay.jaxrs.context.providers.enabled=true",
		"liferay.jaxrs.exception.mappers.enabled=true",
		"liferay.jaxrs.message.body.readers.enabled=true",
		"liferay.jaxrs.message.body.writers.enabled=true",
		"oauth2.scopechecker.type=annotations"
	},
	service = Application.class
)
@Generated(value = "OSGiRESTModuleGenerator")
public class CommerceOpenApiAdminApplication extends Application {
}