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

${IMPORT_STATEMENTS}

import java.util.Collections;

import javax.annotation.Generated;

${MODEL_IMPORT_STATEMENTS_JAVAX}

import javax.ws.rs.core.Response;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.jaxrs.whiteboard.JaxrsWhiteboardConstants;

/**
 * @author ${AUTHOR}
 */
@Component(
	immediate = true,
	property = {
		JaxrsWhiteboardConstants.JAX_RS_APPLICATION_SELECT + "=(osgi.jaxrs.name=${APPLICATION_NAME}.Rest)",
		JaxrsWhiteboardConstants.JAX_RS_RESOURCE + "=true", "api.version=${API_VERSION}"
	},
	service = ${MODEL_RESOURCE_INTERFACE_CLASS}.class)
@Generated(value = "OSGiRESTModuleGenerator")
public class ${MODEL_RESOURCE_IMPLEMENTATION_CLASS} implements ${MODEL_RESOURCE_INTERFACE_CLASS} {

${METHODS}
}