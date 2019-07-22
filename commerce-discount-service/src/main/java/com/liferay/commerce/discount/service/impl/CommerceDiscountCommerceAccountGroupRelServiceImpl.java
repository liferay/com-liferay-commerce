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

package com.liferay.commerce.discount.service.impl;

import com.liferay.commerce.discount.model.CommerceDiscount;
import com.liferay.commerce.discount.model.CommerceDiscountCommerceAccountGroupRel;
import com.liferay.commerce.discount.service.base.CommerceDiscountCommerceAccountGroupRelServiceBaseImpl;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.security.permission.ActionKeys;
import com.liferay.portal.kernel.security.permission.resource.ModelResourcePermission;
import com.liferay.portal.kernel.security.permission.resource.ModelResourcePermissionFactory;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.OrderByComparator;

import java.util.List;

/**
 * @author Marco Leo
 * @author Alessio Antonio Rendina
 */
public class CommerceDiscountCommerceAccountGroupRelServiceImpl
	extends CommerceDiscountCommerceAccountGroupRelServiceBaseImpl {

	@Override
	public CommerceDiscountCommerceAccountGroupRel
			addCommerceDiscountCommerceAccountGroupRel(
				long commerceDiscountId, long commerceAccountGroupId,
				ServiceContext serviceContext)
		throws PortalException {

		_commerceDiscountResourcePermission.check(
			getPermissionChecker(), commerceDiscountId, ActionKeys.UPDATE);

		return commerceDiscountCommerceAccountGroupRelLocalService.
			addCommerceDiscountCommerceAccountGroupRel(
				commerceDiscountId, commerceAccountGroupId, serviceContext);
	}

	@Override
	public void deleteCommerceDiscountCommerceAccountGroupRel(
			long commerceDiscountCommerceAccountGroupRelId)
		throws PortalException {

		CommerceDiscountCommerceAccountGroupRel
			commerceDiscountCommerceAccountGroupRel =
				commerceDiscountCommerceAccountGroupRelLocalService.
					getCommerceDiscountCommerceAccountGroupRel(
						commerceDiscountCommerceAccountGroupRelId);

		_commerceDiscountResourcePermission.check(
			getPermissionChecker(),
			commerceDiscountCommerceAccountGroupRel.getCommerceDiscountId(),
			ActionKeys.UPDATE);

		commerceDiscountCommerceAccountGroupRelLocalService.
			deleteCommerceDiscountCommerceAccountGroupRel(
				commerceDiscountCommerceAccountGroupRel);
	}

	@Override
	public CommerceDiscountCommerceAccountGroupRel
			fetchCommerceDiscountCommerceAccountGroupRel(
				long commerceDiscountId, long commerceAccountGroupId)
		throws PortalException {

		CommerceDiscountCommerceAccountGroupRel
			commerceDiscountCommerceAccountGroupRel =
				commerceDiscountCommerceAccountGroupRelLocalService.
					fetchCommerceDiscountCommerceAccountGroupRel(
						commerceDiscountId, commerceAccountGroupId);

		if (commerceDiscountCommerceAccountGroupRel != null) {
			_commerceDiscountResourcePermission.check(
				getPermissionChecker(),
				commerceDiscountCommerceAccountGroupRel.getCommerceDiscountId(),
				ActionKeys.UPDATE);
		}

		return commerceDiscountCommerceAccountGroupRel;
	}

	@Override
	public CommerceDiscountCommerceAccountGroupRel
			getCommerceDiscountCommerceAccountGroupRel(
				long commerceDiscountCommerceAccountGroupRelId)
		throws PortalException {

		CommerceDiscountCommerceAccountGroupRel
			commerceDiscountCommerceAccountGroupRel =
				commerceDiscountCommerceAccountGroupRelLocalService.
					getCommerceDiscountCommerceAccountGroupRel(
						commerceDiscountCommerceAccountGroupRelId);

		_commerceDiscountResourcePermission.check(
			getPermissionChecker(),
			commerceDiscountCommerceAccountGroupRel.getCommerceDiscountId(),
			ActionKeys.UPDATE);

		return commerceDiscountCommerceAccountGroupRel;
	}

	@Override
	public List<CommerceDiscountCommerceAccountGroupRel>
			getCommerceDiscountCommerceAccountGroupRels(
				long commerceDiscountId, int start, int end,
				OrderByComparator<CommerceDiscountCommerceAccountGroupRel>
					orderByComparator)
		throws PortalException {

		_commerceDiscountResourcePermission.check(
			getPermissionChecker(), commerceDiscountId, ActionKeys.UPDATE);

		return commerceDiscountCommerceAccountGroupRelLocalService.
			getCommerceDiscountCommerceAccountGroupRels(
				commerceDiscountId, start, end, orderByComparator);
	}

	@Override
	public int getCommerceDiscountCommerceAccountGroupRelsCount(
			long commerceDiscountId)
		throws PortalException {

		_commerceDiscountResourcePermission.check(
			getPermissionChecker(), commerceDiscountId, ActionKeys.UPDATE);

		return commerceDiscountCommerceAccountGroupRelLocalService.
			getCommerceDiscountCommerceAccountGroupRelsCount(
				commerceDiscountId);
	}

	private static volatile ModelResourcePermission<CommerceDiscount>
		_commerceDiscountResourcePermission =
			ModelResourcePermissionFactory.getInstance(
				CommerceDiscountCommerceAccountGroupRelServiceImpl.class,
				"_commerceDiscountResourcePermission", CommerceDiscount.class);

}