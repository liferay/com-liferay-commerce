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
import com.liferay.commerce.price.list.service.base.CommercePriceListServiceBaseImpl;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.search.BaseModelSearchResult;
import com.liferay.portal.kernel.search.Sort;
import com.liferay.portal.kernel.security.permission.resource.PortletResourcePermission;
import com.liferay.portal.kernel.security.permission.resource.PortletResourcePermissionFactory;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.OrderByComparator;

import java.util.List;

/**
 * @author Alessio Antonio Rendina
 * @author Zoltán Takács
 */
public class CommercePriceListServiceImpl
	extends CommercePriceListServiceBaseImpl {

	@Override
	public CommercePriceList addCommercePriceList(
			long commerceCurrencyId, String name, double priority,
			int displayDateMonth, int displayDateDay, int displayDateYear,
			int displayDateHour, int displayDateMinute, int expirationDateMonth,
			int expirationDateDay, int expirationDateYear,
			int expirationDateHour, int expirationDateMinute,
			boolean neverExpire, ServiceContext serviceContext)
		throws PortalException {

		return addCommercePriceList(
			commerceCurrencyId, 0, name, priority, displayDateMonth,
			displayDateDay, displayDateYear, displayDateHour, displayDateMinute,
			expirationDateMonth, expirationDateDay, expirationDateYear,
			expirationDateHour, expirationDateMinute, null, neverExpire,
			serviceContext);
	}

	@Override
	public CommercePriceList addCommercePriceList(
			long commerceCurrencyId, long parentCommercePriceListId,
			String name, double priority, int displayDateMonth,
			int displayDateDay, int displayDateYear, int displayDateHour,
			int displayDateMinute, int expirationDateMonth,
			int expirationDateDay, int expirationDateYear,
			int expirationDateHour, int expirationDateMinute,
			boolean neverExpire, ServiceContext serviceContext)
		throws PortalException {

		return addCommercePriceList(
			commerceCurrencyId, parentCommercePriceListId, name, priority,
			displayDateMonth, displayDateDay, displayDateYear, displayDateHour,
			displayDateMinute, expirationDateMonth, expirationDateDay,
			expirationDateYear, expirationDateHour, expirationDateMinute, null,
			neverExpire, serviceContext);
	}

	@Override
	public CommercePriceList addCommercePriceList(
			long commerceCurrencyId, String name, double priority,
			int displayDateMonth, int displayDateDay, int displayDateYear,
			int displayDateHour, int displayDateMinute, int expirationDateMonth,
			int expirationDateDay, int expirationDateYear,
			int expirationDateHour, int expirationDateMinute,
			String externalReferenceCode, boolean neverExpire,
			ServiceContext serviceContext)
		throws PortalException {

		return addCommercePriceList(
			commerceCurrencyId, 0, name, priority,
			displayDateMonth, displayDateDay, displayDateYear, displayDateHour,
			displayDateMinute, expirationDateMonth, expirationDateDay,
			expirationDateYear, expirationDateHour, expirationDateMinute,
			externalReferenceCode, neverExpire, serviceContext);
	}

	@Override
	public CommercePriceList addCommercePriceList(
			long commerceCurrencyId, long parentCommercePriceListId, String name, double priority,
			int displayDateMonth, int displayDateDay, int displayDateYear,
			int displayDateHour, int displayDateMinute, int expirationDateMonth,
			int expirationDateDay, int expirationDateYear,
			int expirationDateHour, int expirationDateMinute,
			String externalReferenceCode, boolean neverExpire,
			ServiceContext serviceContext)
		throws PortalException {

		_portletResourcePermission.check(
			getPermissionChecker(), serviceContext.getScopeGroupId(),
			CommercePriceListActionKeys.MANAGE_COMMERCE_PRICE_LISTS);

		return commercePriceListLocalService.addCommercePriceList(
			commerceCurrencyId, parentCommercePriceListId, name, priority, displayDateMonth,
			displayDateDay, displayDateYear, displayDateHour, displayDateMinute,
			expirationDateMonth, expirationDateDay, expirationDateYear,
			expirationDateHour, expirationDateMinute, externalReferenceCode,
			neverExpire, serviceContext);
	}

	@Override
	public void deleteCommercePriceList(long commercePriceListId)
		throws PortalException {

		CommercePriceList commercePriceList =
			commercePriceListLocalService.fetchCommercePriceList(
				commercePriceListId);

		if (commercePriceList != null) {
			_portletResourcePermission.check(
				getPermissionChecker(), commercePriceList.getGroupId(),
				CommercePriceListActionKeys.MANAGE_COMMERCE_PRICE_LISTS);
		}

		commercePriceListLocalService.deleteCommercePriceList(
			commercePriceListId);
	}

	@Override
	public CommercePriceList fetchByExternalReferenceCode(
			long companyId, String externalReferenceCode)
		throws PortalException {

		CommercePriceList commercePriceList =
			commercePriceListLocalService.fetchByExternalReferenceCode(
				companyId, externalReferenceCode);

		if (commercePriceList != null) {
			_portletResourcePermission.check(
				getPermissionChecker(), commercePriceList.getGroupId(),
				CommercePriceListActionKeys.MANAGE_COMMERCE_PRICE_LISTS);
		}

		return commercePriceList;
	}

	@Override
	public CommercePriceList fetchCommercePriceList(long commercePriceListId)
		throws PortalException {

		CommercePriceList commercePriceList =
			commercePriceListLocalService.fetchCommercePriceList(
				commercePriceListId);

		if (commercePriceList != null) {
			_portletResourcePermission.check(
				getPermissionChecker(), commercePriceList.getGroupId(),
				CommercePriceListActionKeys.MANAGE_COMMERCE_PRICE_LISTS);
		}

		return commercePriceList;
	}

	@Override
	public List<CommercePriceList> getCommercePriceLists(
			long groupId, int start, int end)
		throws PortalException {

		_portletResourcePermission.check(
			getPermissionChecker(), groupId,
			CommercePriceListActionKeys.MANAGE_COMMERCE_PRICE_LISTS);

		return commercePriceListLocalService.getCommercePriceLists(
			groupId, start, end);
	}

	@Override
	public List<CommercePriceList> getCommercePriceLists(
			long groupId, int status, int start, int end,
			OrderByComparator<CommercePriceList> orderByComparator)
		throws PortalException {

		_portletResourcePermission.check(
			getPermissionChecker(), groupId,
			CommercePriceListActionKeys.MANAGE_COMMERCE_PRICE_LISTS);

		return commercePriceListLocalService.getCommercePriceLists(
			groupId, status, start, end, orderByComparator);
	}

	@Override
	public int getCommercePriceListsCount(long groupId, int status)
		throws PortalException {

		_portletResourcePermission.check(
			getPermissionChecker(), groupId,
			CommercePriceListActionKeys.MANAGE_COMMERCE_PRICE_LISTS);

		return commercePriceListLocalService.getCommercePriceListsCount(
			groupId, status);
	}

	@Override
	public BaseModelSearchResult<CommercePriceList> searchCommercePriceLists(
			long companyId, long groupId, String keywords, int status,
			int start, int end, Sort sort)
		throws PortalException {

		_portletResourcePermission.check(
			getPermissionChecker(), groupId,
			CommercePriceListActionKeys.MANAGE_COMMERCE_PRICE_LISTS);

		return commercePriceListLocalService.searchCommercePriceLists(
			companyId, groupId, keywords, status, start, end, sort);
	}

	@Override
	public CommercePriceList updateCommercePriceList(
			long commercePriceListId, long commerceCurrencyId, String name,
			double priority, int displayDateMonth, int displayDateDay,
			int displayDateYear, int displayDateHour, int displayDateMinute,
			int expirationDateMonth, int expirationDateDay,
			int expirationDateYear, int expirationDateHour,
			int expirationDateMinute, boolean neverExpire,
			ServiceContext serviceContext)
		throws PortalException {

		return updateCommercePriceList(
			commercePriceListId, commerceCurrencyId, 0, name, priority,
			displayDateMonth, displayDateDay, displayDateYear, displayDateHour,
			displayDateMinute, expirationDateMonth, expirationDateDay,
			expirationDateYear, expirationDateHour, expirationDateMinute,
			neverExpire, serviceContext);
	}

	@Override
	public CommercePriceList updateCommercePriceList(
			long commercePriceListId, long commerceCurrencyId, long parentCommercePriceListId, String name,
			double priority, int displayDateMonth, int displayDateDay,
			int displayDateYear, int displayDateHour, int displayDateMinute,
			int expirationDateMonth, int expirationDateDay,
			int expirationDateYear, int expirationDateHour,
			int expirationDateMinute, boolean neverExpire,
			ServiceContext serviceContext)
		throws PortalException {

		_portletResourcePermission.check(
			getPermissionChecker(), serviceContext.getScopeGroupId(),
			CommercePriceListActionKeys.MANAGE_COMMERCE_PRICE_LISTS);

		return commercePriceListLocalService.updateCommercePriceList(
			commercePriceListId, commerceCurrencyId, parentCommercePriceListId, name, priority,
			displayDateMonth, displayDateDay, displayDateYear, displayDateHour,
			displayDateMinute, expirationDateMonth, expirationDateDay,
			expirationDateYear, expirationDateHour, expirationDateMinute,
			neverExpire, serviceContext);
	}

	@Override
	public CommercePriceList updateExternalReferenceCode(
			CommercePriceList commercePriceList, long groupId,
			String externalReferenceCode)
		throws PortalException {

		_portletResourcePermission.check(
			getPermissionChecker(), groupId,
			CommercePriceListActionKeys.MANAGE_COMMERCE_PRICE_LISTS);

		return commercePriceListLocalService.updateExternalReferenceCode(
			commercePriceList, externalReferenceCode);
	}

	@Override
	public CommercePriceList upsertCommercePriceList(
			long commercePriceListId, long commerceCurrencyId, String name,
			double priority, int displayDateMonth, int displayDateDay,
			int displayDateYear, int displayDateHour, int displayDateMinute,
			int expirationDateMonth, int expirationDateDay,
			int expirationDateYear, int expirationDateHour,
			int expirationDateMinute, String externalReferenceCode,
			boolean neverExpire, ServiceContext serviceContext)
		throws PortalException {

		return upsertCommercePriceList(
			commercePriceListId, commerceCurrencyId, 0, name, priority,
			displayDateMonth, displayDateDay, displayDateYear, displayDateHour,
			displayDateMinute, expirationDateMonth, expirationDateDay,
			expirationDateYear, expirationDateHour, expirationDateMinute,
			externalReferenceCode, neverExpire, serviceContext);
	}

	@Override
	public CommercePriceList upsertCommercePriceList(
			long commercePriceListId, long commerceCurrencyId, long parentCommercePriceListId, String name,
			double priority, int displayDateMonth, int displayDateDay,
			int displayDateYear, int displayDateHour, int displayDateMinute,
			int expirationDateMonth, int expirationDateDay,
			int expirationDateYear, int expirationDateHour,
			int expirationDateMinute, String externalReferenceCode,
			boolean neverExpire, ServiceContext serviceContext)
		throws PortalException {

		_portletResourcePermission.check(
			getPermissionChecker(), serviceContext.getScopeGroupId(),
			CommercePriceListActionKeys.MANAGE_COMMERCE_PRICE_LISTS);

		return commercePriceListLocalService.upsertCommercePriceList(
			commercePriceListId, commerceCurrencyId, parentCommercePriceListId, name, priority,
			displayDateMonth, displayDateDay, displayDateYear, displayDateHour,
			displayDateMinute, expirationDateMonth, expirationDateDay,
			expirationDateYear, expirationDateHour, expirationDateMinute,
			externalReferenceCode, neverExpire, serviceContext);
	}

	private static volatile PortletResourcePermission
		_portletResourcePermission =
			PortletResourcePermissionFactory.getInstance(
				CommercePriceListServiceImpl.class,
				"_portletResourcePermission",
				CommercePriceListConstants.RESOURCE_NAME);

}