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

package com.liferay.commerce.account.internal.permission;

import com.liferay.commerce.account.constants.CommerceAccountActionKeys;
import com.liferay.commerce.account.constants.CommerceAccountConstants;
import com.liferay.commerce.account.model.CommerceAccount;
import com.liferay.commerce.account.permission.CommerceAccountPermission;
import com.liferay.commerce.account.service.CommerceAccountLocalService;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.model.GroupConstants;
import com.liferay.portal.kernel.security.auth.PrincipalException;
import com.liferay.portal.kernel.security.permission.ActionKeys;
import com.liferay.portal.kernel.security.permission.PermissionChecker;
import com.liferay.portal.kernel.security.permission.resource.PortletResourcePermission;
import com.liferay.portal.kernel.util.ArrayUtil;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Alessio Antonio Rendina
 */
@Component(immediate = true, service = CommerceAccountPermission.class)
public class CommerceAccountPermissionImpl
	implements CommerceAccountPermission {

	@Override
	public void check(
			PermissionChecker permissionChecker,
			CommerceAccount commerceAccount, String actionId)
		throws PortalException {

		if (!contains(permissionChecker, commerceAccount, actionId)) {
			throw new PrincipalException.MustHavePermission(
				permissionChecker, CommerceAccount.class.getName(),
				commerceAccount.getCommerceAccountId(), actionId);
		}
	}

	@Override
	public void check(
			PermissionChecker permissionChecker, long commerceAccountId,
			String actionId)
		throws PortalException {

		if (!contains(permissionChecker, commerceAccountId, actionId)) {
			throw new PrincipalException.MustHavePermission(
				permissionChecker, CommerceAccount.class.getName(),
				commerceAccountId, actionId);
		}
	}

	@Override
	public boolean contains(
			PermissionChecker permissionChecker,
			CommerceAccount commerceAccount, String actionId)
		throws PortalException {

		if (_contains(permissionChecker, commerceAccount, actionId)) {
			return true;
		}

		while (!commerceAccount.isRoot()) {
			CommerceAccount parentCommerceAccount =
				commerceAccount.getParentCommerceAccount();

			if (_contains(
					permissionChecker, parentCommerceAccount,
					CommerceAccountActionKeys.MANAGE_ACCOUNTS)) {

				return true;
			}

			commerceAccount = parentCommerceAccount;
		}

		return false;
	}

	@Override
	public boolean contains(
			PermissionChecker permissionChecker, long commerceAccountId,
			String actionId)
		throws PortalException {

		if (commerceAccountId > 0) {
			CommerceAccount commerceAccount =
				_commerceAccountLocalService.getCommerceAccount(
					commerceAccountId);

			return contains(permissionChecker, commerceAccount, actionId);
		}

		return _portletResourcePermission.contains(
			permissionChecker, GroupConstants.DEFAULT_LIVE_GROUP_ID,
			CommerceAccountActionKeys.MANAGE_ACCOUNTS);
	}

	@Override
	public boolean contains(
			PermissionChecker permissionChecker, long[] commerceAccountIds,
			String actionId)
		throws PortalException {

		if (ArrayUtil.isEmpty(commerceAccountIds)) {
			return false;
		}

		for (long commerceAccountId : commerceAccountIds) {
			if (!contains(permissionChecker, commerceAccountId, actionId)) {
				return false;
			}
		}

		return true;
	}

	private boolean _contains(
			PermissionChecker permissionChecker,
			CommerceAccount commerceAccount, String actionId)
		throws PortalException {

		while ((commerceAccount != null) &&
			   (commerceAccount.getCommerceAccountId() !=
				   CommerceAccountConstants.DEFAULT_PARENT_ACCOUNT_ID)) {

			if (actionId.equals(ActionKeys.UPDATE) &&
				permissionChecker.hasPermission(
					0, CommerceAccount.class.getName(),
					commerceAccount.getCommerceAccountId(),
					CommerceAccountActionKeys.MANAGE_ACCOUNTS)) {

				return true;
			}
			else if (permissionChecker.hasPermission(
						0, CommerceAccount.class.getName(),
						commerceAccount.getCommerceAccountId(), actionId)) {

				return true;
			}

			commerceAccount = commerceAccount.getParentCommerceAccount();
		}

		return false;
	}

	@Reference
	private CommerceAccountLocalService _commerceAccountLocalService;

	@Reference(
		target = "(resource.name=" + CommerceAccountConstants.RESOURCE_NAME + ")"
	)
	private PortletResourcePermission _portletResourcePermission;

}