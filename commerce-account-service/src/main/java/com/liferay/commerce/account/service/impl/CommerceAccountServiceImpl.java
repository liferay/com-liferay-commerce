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

package com.liferay.commerce.account.service.impl;

import com.liferay.commerce.account.constants.CommerceAccountActionKeys;
import com.liferay.commerce.account.constants.CommerceAccountConstants;
import com.liferay.commerce.account.model.CommerceAccount;
import com.liferay.commerce.account.service.base.CommerceAccountServiceBaseImpl;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.model.GroupConstants;
import com.liferay.portal.kernel.search.BaseModelSearchResult;
import com.liferay.portal.kernel.search.Sort;
import com.liferay.portal.kernel.security.permission.ActionKeys;
import com.liferay.portal.kernel.security.permission.PermissionChecker;
import com.liferay.portal.kernel.security.permission.resource.ModelResourcePermission;
import com.liferay.portal.kernel.security.permission.resource.ModelResourcePermissionFactory;
import com.liferay.portal.kernel.security.permission.resource.PortletResourcePermission;
import com.liferay.portal.kernel.security.permission.resource.PortletResourcePermissionFactory;
import com.liferay.portal.kernel.service.ServiceContext;

import java.util.List;

/**
 * @author Marco Leo
 * @author Alessio Antonio Rendina
 */
public class CommerceAccountServiceImpl extends CommerceAccountServiceBaseImpl {

	@Override
	public CommerceAccount addCommerceAccount(
			String name, long parentCommerceAccountId, String email,
			String taxId, boolean active, String externalReferenceCode,
			ServiceContext serviceContext)
		throws PortalException {

		_portletResourcePermission.check(
			getPermissionChecker(), GroupConstants.DEFAULT_LIVE_GROUP_ID,
			CommerceAccountActionKeys.MANAGE_ACCOUNTS);

		return commerceAccountLocalService.addCommerceAccount(
			name, parentCommerceAccountId, email, taxId, active,
			externalReferenceCode, serviceContext);
	}

	@Override
	public void deleteCommerceAccount(long commerceAccountId)
		throws PortalException {

		_commerceAccountModelResourcePermission.check(
			getPermissionChecker(), commerceAccountId, ActionKeys.DELETE);

		commerceAccountLocalService.deleteCommerceAccount(commerceAccountId);
	}

	@Override
	public CommerceAccount fetchCommerceAccount(long commerceAccountId)
		throws PortalException {

		CommerceAccount commerceAccount =
			commerceAccountLocalService.fetchCommerceAccount(commerceAccountId);

		if (commerceAccount != null) {
			_commerceAccountModelResourcePermission.check(
				getPermissionChecker(), commerceAccountId, ActionKeys.VIEW);
		}

		return commerceAccount;
	}

	@Override
	public CommerceAccount getCommerceAccount(long commerceAccountId)
		throws PortalException {

		_commerceAccountModelResourcePermission.check(
			getPermissionChecker(), commerceAccountId, ActionKeys.VIEW);

		return commerceAccountLocalService.getCommerceAccount(
			commerceAccountId);
	}

	@Override
	public List<CommerceAccount> getUserCommerceAccounts(int start, int end)
		throws PortalException {

		return commerceAccountService.getUserCommerceAccounts(null, start, end);
	}

	@Override
	public List<CommerceAccount> getUserCommerceAccounts(
			Long parentCommerceAccountId, int start, int end)
		throws PortalException {

		return commerceAccountLocalService.getUserCommerceAccounts(
			getUserId(), parentCommerceAccountId, start, end);
	}

	@Override
	public BaseModelSearchResult<CommerceAccount> searchCommerceAccounts(
			long groupId, long parentCommerceAccountId, String keywords,
			Boolean active, int start, int end, Sort sort)
		throws PortalException {

		PermissionChecker permissionChecker = getPermissionChecker();

		_portletResourcePermission.check(
			permissionChecker, GroupConstants.DEFAULT_LIVE_GROUP_ID,
			CommerceAccountActionKeys.MANAGE_ACCOUNTS);

		return commerceAccountLocalService.searchCommerceAccounts(
			permissionChecker.getCompanyId(), parentCommerceAccountId, keywords,
			active, start, end, sort);
	}

	@Override
	public CommerceAccount updateCommerceAccount(
			long commerceAccountId, String name, boolean logo, byte[] logoBytes,
			String email, String taxId, boolean active,
			ServiceContext serviceContext)
		throws PortalException {

		_commerceAccountModelResourcePermission.check(
			getPermissionChecker(), commerceAccountId, ActionKeys.UPDATE);

		return commerceAccountLocalService.updateCommerceAccount(
			commerceAccountId, name, logo, logoBytes, email, taxId, active,
			serviceContext);
	}

	@Override
	public CommerceAccount upsertCommerceAccount(
			String name, long parentCommerceAccountId, boolean logo,
			byte[] logoBytes, String email, String taxId, boolean active,
			String externalReferenceCode, ServiceContext serviceContext)
		throws PortalException {

		PermissionChecker permissionChecker = getPermissionChecker();

		CommerceAccount commerceAccount =
			commerceAccountLocalService.fetchCommerceAccount(
				permissionChecker.getCompanyId(), name);

		if (commerceAccount == null) {
			_portletResourcePermission.check(
				permissionChecker, GroupConstants.DEFAULT_LIVE_GROUP_ID,
				CommerceAccountActionKeys.MANAGE_ACCOUNTS);
		}
		else {
			_commerceAccountModelResourcePermission.check(
				permissionChecker, commerceAccount, ActionKeys.UPDATE);
		}

		return commerceAccountLocalService.upsertCommerceAccount(
			name, parentCommerceAccountId, logo, logoBytes, email, taxId,
			active, externalReferenceCode, serviceContext);
	}

	private static volatile ModelResourcePermission<CommerceAccount>
		_commerceAccountModelResourcePermission =
			ModelResourcePermissionFactory.getInstance(
				CommerceAccountServiceImpl.class,
				"_commerceAccountModelResourcePermission",
				CommerceAccount.class);
	private static volatile PortletResourcePermission
		_portletResourcePermission =
			PortletResourcePermissionFactory.getInstance(
				CommerceAccountServiceImpl.class, "_portletResourcePermission",
				CommerceAccountConstants.RESOURCE_NAME);

}