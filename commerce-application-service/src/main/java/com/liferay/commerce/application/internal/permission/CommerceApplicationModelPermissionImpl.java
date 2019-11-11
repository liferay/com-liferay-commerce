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

package com.liferay.commerce.application.internal.permission;

import com.liferay.commerce.application.model.CommerceApplicationModel;
import com.liferay.commerce.application.permission.CommerceApplicationModelPermission;
import com.liferay.commerce.application.service.CommerceApplicationModelLocalService;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.security.auth.PrincipalException;
import com.liferay.portal.kernel.security.permission.PermissionChecker;
import com.liferay.portal.kernel.util.ArrayUtil;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Alessio Antonio Rendina
 */
@Component(immediate = true, service = CommerceApplicationModelPermission.class)
public class CommerceApplicationModelPermissionImpl
	implements CommerceApplicationModelPermission {

	@Override
	public void check(
			PermissionChecker permissionChecker,
			CommerceApplicationModel commerceApplicationModel, String actionId)
		throws PortalException {

		if (!contains(permissionChecker, commerceApplicationModel, actionId)) {
			throw new PrincipalException.MustHavePermission(
				permissionChecker, CommerceApplicationModel.class.getName(),
				commerceApplicationModel.getCommerceApplicationModelId(),
				actionId);
		}
	}

	@Override
	public void check(
			PermissionChecker permissionChecker,
			long commerceApplicationModelId, String actionId)
		throws PortalException {

		if (!contains(
				permissionChecker, commerceApplicationModelId, actionId)) {

			throw new PrincipalException.MustHavePermission(
				permissionChecker, CommerceApplicationModel.class.getName(),
				commerceApplicationModelId, actionId);
		}
	}

	@Override
	public boolean contains(
		PermissionChecker permissionChecker,
		CommerceApplicationModel commerceApplicationModel, String actionId) {

		if (contains(
				permissionChecker,
				commerceApplicationModel.getCommerceApplicationModelId(),
				actionId)) {

			return true;
		}

		return false;
	}

	@Override
	public boolean contains(
		PermissionChecker permissionChecker, long commerceApplicationModelId,
		String actionId) {

		CommerceApplicationModel commerceApplicationModel =
			_commerceApplicationModelLocalService.fetchCommerceApplicationModel(
				commerceApplicationModelId);

		if (commerceApplicationModel == null) {
			return false;
		}

		return _contains(permissionChecker, commerceApplicationModel, actionId);
	}

	@Override
	public boolean contains(
			PermissionChecker permissionChecker,
			long[] commerceApplicationModelIds, String actionId)
		throws PortalException {

		if (ArrayUtil.isEmpty(commerceApplicationModelIds)) {
			return false;
		}

		for (long commerceApplicationModelId : commerceApplicationModelIds) {
			if (!contains(
					permissionChecker, commerceApplicationModelId, actionId)) {

				return false;
			}
		}

		return true;
	}

	private boolean _contains(
		PermissionChecker permissionChecker,
		CommerceApplicationModel commerceApplicationModel, String actionId) {

		if (permissionChecker.isCompanyAdmin(
				commerceApplicationModel.getCompanyId()) ||
			permissionChecker.isOmniadmin()) {

			return true;
		}

		if (permissionChecker.hasOwnerPermission(
				permissionChecker.getCompanyId(),
				CommerceApplicationModel.class.getName(),
				commerceApplicationModel.getCommerceApplicationModelId(),
				permissionChecker.getUserId(), actionId) &&
			(commerceApplicationModel.getUserId() ==
				permissionChecker.getUserId())) {

			return true;
		}

		return permissionChecker.hasPermission(
			null, CommerceApplicationModel.class.getName(),
			commerceApplicationModel.getCommerceApplicationModelId(), actionId);
	}

	@Reference
	private CommerceApplicationModelLocalService
		_commerceApplicationModelLocalService;

}