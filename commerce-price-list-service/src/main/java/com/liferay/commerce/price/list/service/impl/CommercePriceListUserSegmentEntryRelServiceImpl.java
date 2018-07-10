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

package com.liferay.commerce.price.list.service.impl;

import com.liferay.commerce.price.list.constants.CommercePriceListActionKeys;
import com.liferay.commerce.price.list.constants.CommercePriceListConstants;
import com.liferay.commerce.price.list.model.CommercePriceList;
import com.liferay.commerce.price.list.model.CommercePriceListUserSegmentEntryRel;
import com.liferay.commerce.price.list.service.base.CommercePriceListUserSegmentEntryRelServiceBaseImpl;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.security.permission.resource.PortletResourcePermission;
import com.liferay.portal.kernel.security.permission.resource.PortletResourcePermissionFactory;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.OrderByComparator;

import java.util.List;

/**
 * @author Marco Leo
 */
public class CommercePriceListUserSegmentEntryRelServiceImpl
	extends CommercePriceListUserSegmentEntryRelServiceBaseImpl {

	@Override
	public CommercePriceListUserSegmentEntryRel
			addCommercePriceListUserSegmentEntryRel(
				long commercePriceListId, long commerceUserSegmentEntryId,
				int order, ServiceContext serviceContext)
		throws PortalException {

		CommercePriceList commercePriceList =
			commercePriceListLocalService.getCommercePriceList(
				commercePriceListId);

		_portletResourcePermission.check(
			getPermissionChecker(), commercePriceList.getGroupId(),
			CommercePriceListActionKeys.MANAGE_COMMERCE_PRICE_LISTS);

		return commercePriceListUserSegmentEntryRelLocalService.
			addCommercePriceListUserSegmentEntryRel(
				commercePriceListId, commerceUserSegmentEntryId, order,
				serviceContext);
	}

	@Override
	public void deleteCommercePriceListUserSegmentEntryRel(
			long commercePriceListUserSegmentEntryRelId)
		throws PortalException {

		CommercePriceListUserSegmentEntryRel
			commercePriceListUserSegmentEntryRel =
			commercePriceListUserSegmentEntryRelLocalService.
				fetchCommercePriceListUserSegmentEntryRel(
					commercePriceListUserSegmentEntryRelId);

		if (commercePriceListUserSegmentEntryRel != null) {
			_portletResourcePermission.check(
				getPermissionChecker(),
				commercePriceListUserSegmentEntryRel.getGroupId(),
				CommercePriceListActionKeys.MANAGE_COMMERCE_PRICE_LISTS);
		}

		commercePriceListUserSegmentEntryRelLocalService.
			deleteCommercePriceListUserSegmentEntryRel(
				commercePriceListUserSegmentEntryRelId);
	}

	@Override
	public CommercePriceListUserSegmentEntryRel
			fetchCommercePriceListUserSegmentEntryRel(
				long commercePriceListId, long commerceUserSegmentEntryId)
		throws PortalException {

		CommercePriceListUserSegmentEntryRel
			commercePriceListUserSegmentEntryRel =
				commercePriceListUserSegmentEntryRelLocalService.
					fetchCommercePriceListUserSegmentEntryRel(
						commercePriceListId, commerceUserSegmentEntryId);

		if (commercePriceListUserSegmentEntryRel != null) {
			_portletResourcePermission.check(
				getPermissionChecker(),
				commercePriceListUserSegmentEntryRel.getGroupId(),
				CommercePriceListActionKeys.MANAGE_COMMERCE_PRICE_LISTS);
		}

		return commercePriceListUserSegmentEntryRel;
	}

	@Override
	public List<CommercePriceListUserSegmentEntryRel>
			getCommercePriceListUserSegmentEntryRels(long commercePriceListId)
		throws PortalException {

		CommercePriceList commercePriceList =
			commercePriceListLocalService.getCommercePriceList(
				commercePriceListId);

		_portletResourcePermission.check(
			getPermissionChecker(), commercePriceList.getGroupId(),
			CommercePriceListActionKeys.MANAGE_COMMERCE_PRICE_LISTS);

		return commercePriceListUserSegmentEntryRelLocalService.
			getCommercePriceListUserSegmentEntryRels(commercePriceListId);
	}

	@Override
	public List<CommercePriceListUserSegmentEntryRel>
			getCommercePriceListUserSegmentEntryRels(
				long commercePriceListId, int start, int end,
				OrderByComparator<CommercePriceListUserSegmentEntryRel>
					orderByComparator)
		throws PortalException {

		CommercePriceList commercePriceList =
			commercePriceListLocalService.getCommercePriceList(
				commercePriceListId);

		_portletResourcePermission.check(
			getPermissionChecker(), commercePriceList.getGroupId(),
			CommercePriceListActionKeys.MANAGE_COMMERCE_PRICE_LISTS);

		return commercePriceListUserSegmentEntryRelLocalService.
			getCommercePriceListUserSegmentEntryRels(
				commercePriceListId, start, end, orderByComparator);
	}

	@Override
	public int getCommercePriceListUserSegmentEntryRelsCount(
			long commercePriceListId)
		throws PortalException {

		CommercePriceList commercePriceList =
			commercePriceListLocalService.getCommercePriceList(
				commercePriceListId);

		_portletResourcePermission.check(
			getPermissionChecker(), commercePriceList.getGroupId(),
			CommercePriceListActionKeys.MANAGE_COMMERCE_PRICE_LISTS);

		return commercePriceListUserSegmentEntryRelLocalService.
			getCommercePriceListUserSegmentEntryRelsCount(commercePriceListId);
	}

	@Override
	public CommercePriceListUserSegmentEntryRel
			updateCommercePriceListUserSegmentEntryRel(
				long commercePriceListUserSegmentEntryRelId, int order,
				ServiceContext serviceContext)
		throws PortalException {

		CommercePriceListUserSegmentEntryRel
			commercePriceListUserSegmentEntryRel =
				commercePriceListUserSegmentEntryRelLocalService.
					getCommercePriceListUserSegmentEntryRel(
						commercePriceListUserSegmentEntryRelId);

		_portletResourcePermission.check(
			getPermissionChecker(),
			commercePriceListUserSegmentEntryRel.getGroupId(),
			CommercePriceListActionKeys.MANAGE_COMMERCE_PRICE_LISTS);

		return commercePriceListUserSegmentEntryRelLocalService.
			updateCommercePriceListUserSegmentEntryRel(
				commercePriceListUserSegmentEntryRelId, order, serviceContext);
	}

	private static volatile PortletResourcePermission
		_portletResourcePermission =
			PortletResourcePermissionFactory.getInstance(
				CommercePriceListUserSegmentEntryRelServiceImpl.class,
				"_portletResourcePermission",
				CommercePriceListConstants.RESOURCE_NAME);

}