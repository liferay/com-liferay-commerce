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

import com.liferay.oauth2.provider.scope.RequiresScope;

${MODEL_IMPORT_STATEMENTS}

import javax.annotation.Generated;

${MODEL_IMPORT_STATEMENTS_JAVAX}

import javax.annotation.Generated;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

/**
 * @author ${AUTHOR}
 */
@Generated(value = "OSGiRESTModuleGenerator")
@Path("/${API_VERSION}/${PATH}")
public interface ${MODEL_RESOURCE_INTERFACE_CLASS} {

${METHODS}
}