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

package com.liferay.commerce.data.integration.headless.compat.apio.internal.provider;

import com.liferay.apio.architect.functional.Try;
import com.liferay.apio.architect.provider.Provider;
import com.liferay.portal.kernel.security.permission.PermissionChecker;
import com.liferay.portal.kernel.security.permission.PermissionCheckerFactoryUtil;
import com.liferay.portal.kernel.security.permission.PermissionThreadLocal;
import com.liferay.portal.kernel.util.Portal;

import javax.servlet.http.HttpServletRequest;

import javax.ws.rs.ForbiddenException;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * Lets resources provide {@code PermissionChecker} as a parameter to the
 * methods of the different routes builders.
 *
 * @author Alejandro Hernández
 */
@Component(
	immediate = true, property = "service.ranking:Integer=100",
	service = Provider.class
)
public class PermissionCheckerProvider implements Provider<PermissionChecker> {

	@Override
	public PermissionChecker createContext(
		HttpServletRequest httpServletRequest) {

		return Try.fromFallible(
			() -> _portal.getUser(httpServletRequest)
		).map(
			PermissionCheckerFactoryUtil::create
		).recoverWith(
			__ -> Try.fromFallible(PermissionThreadLocal::getPermissionChecker)
		).orElseThrow(
			ForbiddenException::new
		);
	}

	@Reference
	private Portal _portal;

}