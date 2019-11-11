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

package com.liferay.commerce.data.integration.web.internal.security.permisison.resource;

import com.liferay.commerce.data.integration.model.CommerceDataIntegrationProcess;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.security.permission.PermissionChecker;
import com.liferay.portal.kernel.security.permission.resource.ModelResourcePermission;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Marco Leo
 */
@Component(immediate = true, service = {})
public class CommerceDataintegrationProcessPermission {

	public static boolean contains(
			PermissionChecker permissionChecker,
			CommerceDataIntegrationProcess commerceDataIntegrationProcess,
			String actionId)
		throws PortalException {

		return _commerceDataIntegrationProcessModelResourcePermission.contains(
			permissionChecker,
			commerceDataIntegrationProcess.
				getCommerceDataIntegrationProcessId(),
			actionId);
	}

	public static boolean contains(
			PermissionChecker permissionChecker,
			long commerceDataIntegrationProcessId, String actionId)
		throws PortalException {

		return _commerceDataIntegrationProcessModelResourcePermission.contains(
			permissionChecker, commerceDataIntegrationProcessId, actionId);
	}

	@Reference(
		target = "(model.class.name=com.liferay.commerce.data.integration.model.CommerceDataIntegrationProcess)",
		unbind = "-"
	)
	protected void setModelResourcePermission(
		ModelResourcePermission<CommerceDataIntegrationProcess>
			modelResourcePermission) {

		_commerceDataIntegrationProcessModelResourcePermission =
			modelResourcePermission;
	}

	private static ModelResourcePermission<CommerceDataIntegrationProcess>
		_commerceDataIntegrationProcessModelResourcePermission;

}