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

package com.liferay.commerce.openapi.core.internal.context.provider;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.security.permission.PermissionChecker;
import com.liferay.portal.kernel.security.permission.PermissionCheckerFactoryUtil;
import com.liferay.portal.kernel.security.permission.PermissionThreadLocal;
import com.liferay.portal.kernel.util.Portal;

import javax.servlet.http.HttpServletRequest;

import javax.ws.rs.ext.Provider;

import org.apache.cxf.jaxrs.ext.ContextProvider;
import org.apache.cxf.message.Message;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.osgi.service.jaxrs.whiteboard.JaxrsWhiteboardConstants;

/**
 * @author Zoltán Takács
 */
@Component(
	property = JaxrsWhiteboardConstants.JAX_RS_EXTENSION + "=true",
	service = ContextProvider.class
)
@Provider
public class PermissionCheckerContextProvider
	implements ContextProvider<PermissionChecker> {

	@Override
	public PermissionChecker createContext(Message message) {
		HttpServletRequest httpServletRequest =
			(HttpServletRequest)message.getContextualProperty("HTTP.REQUEST");

		User user = null;

		try {
			user = _portal.getUser(httpServletRequest);
		}
		catch (PortalException pe) {
			if (_log.isWarnEnabled()) {
				_log.warn("Unable to get user", pe);
			}
		}

		try {
			return PermissionCheckerFactoryUtil.create(user);
		}
		catch (Exception e) {
			return PermissionThreadLocal.getPermissionChecker();
		}
	}

	private static final Log _log = LogFactoryUtil.getLog(
		PermissionCheckerContextProvider.class);

	@Reference
	private Portal _portal;

}