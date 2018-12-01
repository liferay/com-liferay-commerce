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

package com.liferay.commerce.data.integration.apio.internal.security.permission;

import com.liferay.apio.architect.credentials.Credentials;
import com.liferay.apio.architect.functional.Try;
import com.liferay.commerce.data.integration.apio.identifier.ClassPKExternalReferenceCode;
import com.liferay.commerce.data.integration.apio.internal.util.CommerceUserHelper;
import com.liferay.portal.apio.permission.HasPermission;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.security.permission.ActionKeys;
import com.liferay.portal.kernel.security.permission.PermissionChecker;
import com.liferay.portal.kernel.service.permission.PortalPermissionUtil;
import com.liferay.portal.kernel.service.permission.UserPermission;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Zoltán Takács
 */
@Component(
	property = "name=com.liferay.commerce.user", service = HasPermission.class
)
public class CommerceUserPermissionImpl
	implements HasPermission<ClassPKExternalReferenceCode> {

	@Override
	public Boolean forAdding(Credentials credentials) {
		return PortalPermissionUtil.contains(
			(PermissionChecker)credentials.get(), ActionKeys.ADD_USER);
	}

	@Override
	public Boolean forDeleting(
		Credentials credentials,
		ClassPKExternalReferenceCode commerceUserCPKERC) {

		Try<User> userTry = Try.fromFallible(
			() -> _commerceUserHelper.getUser(commerceUserCPKERC)
		);

		if (userTry.isFailure()) {
			return false;
		}

		User user = userTry.getUnchecked();

		return _userPermission.contains(
			(PermissionChecker)credentials.get(), user.getUserId(),
			ActionKeys.DELETE);
	}

	@Override
	public Boolean forUpdating(
		Credentials credentials,
		ClassPKExternalReferenceCode commerceUserCPKERC) {

		Try<User> userTry = Try.fromFallible(
			() -> _commerceUserHelper.getUser(commerceUserCPKERC)
		);

		if (userTry.isFailure()) {
			return false;
		}

		User user = userTry.getUnchecked();

		return _userPermission.contains(
			(PermissionChecker)credentials.get(), user.getUserId(),
			ActionKeys.UPDATE);
	}

	@Reference
	private CommerceUserHelper _commerceUserHelper;

	@Reference
	private UserPermission _userPermission;

}