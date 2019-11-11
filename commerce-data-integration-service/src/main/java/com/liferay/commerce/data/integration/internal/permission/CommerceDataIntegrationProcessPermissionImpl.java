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

package com.liferay.commerce.data.integration.internal.permission;

import com.liferay.commerce.data.integration.model.CommerceDataIntegrationProcess;
import com.liferay.commerce.data.integration.permission.CommerceDataIntegrationProcessPermission;
import com.liferay.commerce.data.integration.service.CommerceDataIntegrationProcessLocalService;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.security.auth.PrincipalException;
import com.liferay.portal.kernel.security.permission.PermissionChecker;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Alessio Antonio Rendina
 */
@Component(
	immediate = true, service = CommerceDataIntegrationProcessPermission.class
)
public class CommerceDataIntegrationProcessPermissionImpl
	implements CommerceDataIntegrationProcessPermission {

	@Override
	public void check(
			PermissionChecker permissionChecker,
			CommerceDataIntegrationProcess commerceDataIntegrationProcess,
			String actionId)
		throws PortalException {

		if (!contains(
				permissionChecker, commerceDataIntegrationProcess, actionId)) {

			throw new PrincipalException.MustHavePermission(
				permissionChecker,
				CommerceDataIntegrationProcess.class.getName(),
				commerceDataIntegrationProcess.
					getCommerceDataIntegrationProcessId(),
				actionId);
		}
	}

	@Override
	public void check(
			PermissionChecker permissionChecker,
			long commerceDataIntegrationProcessId, String actionId)
		throws PortalException {

		if (!contains(
				permissionChecker, commerceDataIntegrationProcessId,
				actionId)) {

			throw new PrincipalException.MustHavePermission(
				permissionChecker,
				CommerceDataIntegrationProcess.class.getName(),
				commerceDataIntegrationProcessId, actionId);
		}
	}

	@Override
	public boolean contains(
			PermissionChecker permissionChecker,
			CommerceDataIntegrationProcess commerceDataIntegrationProcess,
			String actionId)
		throws PortalException {

		if (contains(
				permissionChecker,
				commerceDataIntegrationProcess.
					getCommerceDataIntegrationProcessId(),
				actionId)) {

			return true;
		}

		return false;
	}

	@Override
	public boolean contains(
			PermissionChecker permissionChecker,
			long commerceDataIntegrationProcessId, String actionId)
		throws PortalException {

		CommerceDataIntegrationProcess commerceDataIntegrationProcess =
			_commerceDataIntegrationProcessLocalService.
				fetchCommerceDataIntegrationProcess(
					commerceDataIntegrationProcessId);

		if (commerceDataIntegrationProcess == null) {
			return false;
		}

		return _contains(
			permissionChecker, commerceDataIntegrationProcess, actionId);
	}

	private boolean _contains(
			PermissionChecker permissionChecker,
			CommerceDataIntegrationProcess commerceDataIntegrationProcess,
			String actionId)
		throws PortalException {

		if (permissionChecker.hasOwnerPermission(
				commerceDataIntegrationProcess.getCompanyId(),
				CommerceDataIntegrationProcess.class.getName(),
				commerceDataIntegrationProcess.
					getCommerceDataIntegrationProcessId(),
				commerceDataIntegrationProcess.getUserId(), actionId)) {

			return true;
		}

		return permissionChecker.hasPermission(
			0, CommerceDataIntegrationProcess.class.getName(),
			commerceDataIntegrationProcess.
				getCommerceDataIntegrationProcessId(),
			actionId);
	}

	@Reference
	private CommerceDataIntegrationProcessLocalService
		_commerceDataIntegrationProcessLocalService;

}