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

package ${PACKAGE};

import com.liferay.commerce.openapi.core.constants.OpenApiPropsKeys;

import javax.annotation.Generated;

import javax.ws.rs.core.Application;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.jaxrs.whiteboard.JaxrsWhiteboardConstants;

/**
 * @author ${AUTHOR}
 */
@Component(
	property = {
		JaxrsWhiteboardConstants.JAX_RS_APPLICATION_BASE + "=${APPLICATION_BASE}",
		JaxrsWhiteboardConstants.JAX_RS_NAME + "=${APPLICATION_NAME}.Rest",
		OpenApiPropsKeys.CONTEXT_PROVIDERS_ENABLED,
		OpenApiPropsKeys.EXCEPTION_MAPPERS_ENABLED,
		OpenApiPropsKeys.MESSAGE_BODY_READERS_ENABLED,
		OpenApiPropsKeys.MESSAGE_BODY_WRITERS_ENABLED,
		OpenApiPropsKeys.NESTED_FILTER_ENABLED,
		OpenApiPropsKeys.PARAMETER_CONVERTER_PROVIDERS_ENABLED,
		OpenApiPropsKeys.SERVICE_EVENT_FILTER_ENABLED,
		OpenApiPropsKeys.STATUS_FEATURE_ENABLED,
		${PORTAL_SESSION_AUTH_VERIFIER},
		${BASIC_AUTH_VERIFIER},
		${OAUTH2_AUTH_VERIFIER},
		${GUEST_ALLOWED}
	},
	service = Application.class
)
@Generated(value = "OSGiRESTModuleGenerator")
public class ${APPLICATION_CLASS} extends Application {
}