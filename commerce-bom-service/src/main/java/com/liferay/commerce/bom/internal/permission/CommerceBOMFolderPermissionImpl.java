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

package com.liferay.commerce.bom.internal.permission;

import com.liferay.commerce.bom.model.CommerceBOMFolder;
import com.liferay.commerce.bom.permission.CommerceBOMFolderPermission;
import com.liferay.commerce.bom.service.CommerceBOMFolderLocalService;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.security.auth.PrincipalException;
import com.liferay.portal.kernel.security.permission.PermissionChecker;
import com.liferay.portal.kernel.util.ArrayUtil;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Alessio Antonio Rendina
 */
@Component(immediate = true, service = CommerceBOMFolderPermission.class)
public class CommerceBOMFolderPermissionImpl
	implements CommerceBOMFolderPermission {

	@Override
	public void check(
			PermissionChecker permissionChecker,
			CommerceBOMFolder commerceBOMFolder, String actionId)
		throws PortalException {

		if (!contains(permissionChecker, commerceBOMFolder, actionId)) {
			throw new PrincipalException.MustHavePermission(
				permissionChecker, CommerceBOMFolder.class.getName(),
				commerceBOMFolder.getCommerceBOMFolderId(), actionId);
		}
	}

	@Override
	public void check(
			PermissionChecker permissionChecker, long commerceBOMFolderId,
			String actionId)
		throws PortalException {

		if (!contains(permissionChecker, commerceBOMFolderId, actionId)) {
			throw new PrincipalException.MustHavePermission(
				permissionChecker, CommerceBOMFolder.class.getName(),
				commerceBOMFolderId, actionId);
		}
	}

	@Override
	public boolean contains(
		PermissionChecker permissionChecker,
		CommerceBOMFolder commerceBOMFolder, String actionId) {

		if (contains(
				permissionChecker, commerceBOMFolder.getCommerceBOMFolderId(),
				actionId)) {

			return true;
		}

		return false;
	}

	@Override
	public boolean contains(
		PermissionChecker permissionChecker, long commerceBOMFolderId,
		String actionId) {

		CommerceBOMFolder commerceBOMFolder =
			_commerceBOMFolderLocalService.fetchCommerceBOMFolder(
				commerceBOMFolderId);

		if (commerceBOMFolder == null) {
			return false;
		}

		return _contains(permissionChecker, commerceBOMFolder, actionId);
	}

	@Override
	public boolean contains(
			PermissionChecker permissionChecker, long[] commerceBOMFolderIds,
			String actionId)
		throws PortalException {

		if (ArrayUtil.isEmpty(commerceBOMFolderIds)) {
			return false;
		}

		for (long commerceBOMFolderId : commerceBOMFolderIds) {
			if (!contains(permissionChecker, commerceBOMFolderId, actionId)) {
				return false;
			}
		}

		return true;
	}

	private boolean _contains(
		PermissionChecker permissionChecker,
		CommerceBOMFolder commerceBOMFolder, String actionId) {

		if (permissionChecker.isCompanyAdmin(
				commerceBOMFolder.getCompanyId()) ||
			permissionChecker.isOmniadmin()) {

			return true;
		}

		if (permissionChecker.hasOwnerPermission(
				permissionChecker.getCompanyId(),
				CommerceBOMFolder.class.getName(),
				commerceBOMFolder.getCommerceBOMFolderId(),
				permissionChecker.getUserId(), actionId) &&
			(commerceBOMFolder.getUserId() == permissionChecker.getUserId())) {

			return true;
		}

		return permissionChecker.hasPermission(
			null, CommerceBOMFolder.class.getName(),
			commerceBOMFolder.getCommerceBOMFolderId(), actionId);
	}

	@Reference
	private CommerceBOMFolderLocalService _commerceBOMFolderLocalService;

}