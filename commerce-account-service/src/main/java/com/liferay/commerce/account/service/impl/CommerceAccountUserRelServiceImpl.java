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

import com.liferay.commerce.account.model.CommerceAccount;
import com.liferay.commerce.account.model.CommerceAccountUserRel;
import com.liferay.commerce.account.service.base.CommerceAccountUserRelServiceBaseImpl;
import com.liferay.commerce.account.service.persistence.CommerceAccountUserRelPK;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.security.permission.ActionKeys;
import com.liferay.portal.kernel.security.permission.resource.ModelResourcePermission;
import com.liferay.portal.kernel.security.permission.resource.ModelResourcePermissionFactory;
import com.liferay.portal.kernel.service.ServiceContext;

import java.util.List;

/**
 * @author Marco Leo
 * @author Alessio Antonio Rendina
 */
public class CommerceAccountUserRelServiceImpl
	extends CommerceAccountUserRelServiceBaseImpl {

	@Override
	public CommerceAccountUserRel addCommerceAccountUserRel(
			long commerceAccountId, long userId, ServiceContext serviceContext)
		throws PortalException {

		_commerceAccountModelResourcePermission.check(
			getPermissionChecker(), commerceAccountId, ActionKeys.UPDATE);

		return commerceAccountUserRelLocalService.addCommerceAccountUserRel(
			commerceAccountId, userId, serviceContext);
	}

	@Override
	public void addCommerceAccountUserRels(
			long commerceAccountId, String[] emailAddresses, long[] roleIds,
			ServiceContext serviceContext)
		throws PortalException {

		_commerceAccountModelResourcePermission.check(
			getPermissionChecker(), commerceAccountId, ActionKeys.UPDATE);

		commerceAccountUserRelLocalService.addCommerceAccountUserRels(
			commerceAccountId, emailAddresses, roleIds, serviceContext);
	}

	@Override
	public void deleteCommerceAccountUserRel(
			long commerceAccountId, long userId)
		throws PortalException {

		_commerceAccountModelResourcePermission.check(
			getPermissionChecker(), commerceAccountId, ActionKeys.UPDATE);

		CommerceAccountUserRelPK commerceAccountUserRelPK =
			new CommerceAccountUserRelPK(commerceAccountId, userId);

		commerceAccountUserRelLocalService.deleteCommerceAccountUserRel(
			commerceAccountUserRelPK);
	}

	@Override
	public void deleteCommerceAccountUserRels(
			long commerceAccountId, long[] userIds)
		throws PortalException {

		_commerceAccountModelResourcePermission.check(
			getPermissionChecker(), commerceAccountId, ActionKeys.UPDATE);

		commerceAccountUserRelLocalService.deleteCommerceAccountUserRels(
			commerceAccountId, userIds);
	}

	@Override
	public List<CommerceAccountUserRel> getCommerceAccountUserRels(
			long commerceAccountId)
		throws PortalException {

		_commerceAccountModelResourcePermission.check(
			getPermissionChecker(), commerceAccountId, ActionKeys.UPDATE);

		return commerceAccountUserRelLocalService.getCommerceAccountUserRels(
			commerceAccountId);
	}

	private static volatile ModelResourcePermission<CommerceAccount>
		_commerceAccountModelResourcePermission =
			ModelResourcePermissionFactory.getInstance(
				CommerceAccountUserRelServiceImpl.class,
				"_commerceAccountModelResourcePermission",
				CommerceAccount.class);

}