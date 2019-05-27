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
import com.liferay.commerce.price.list.model.CommercePriceList;
import com.liferay.commerce.price.list.service.base.CommercePriceListServiceBaseImpl;
import com.liferay.commerce.product.model.CommerceCatalog;
import com.liferay.commerce.product.service.CommerceCatalogService;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.search.BaseModelSearchResult;
import com.liferay.portal.kernel.search.Sort;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.service.permission.PortalPermissionUtil;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.spring.extender.service.ServiceReference;

import java.util.List;
import java.util.function.ToLongFunction;
import java.util.stream.Stream;

/**
 * @author Alessio Antonio Rendina
 * @author Zoltán Takács
 */
public class CommercePriceListServiceImpl
	extends CommercePriceListServiceBaseImpl {

	@Override
	public CommercePriceList addCommercePriceList(
			long groupId, long userId, long commerceCurrencyId,
			long parentCommercePriceListId, String name, double priority,
			int displayDateMonth, int displayDateDay, int displayDateYear,
			int displayDateHour, int displayDateMinute, int expirationDateMonth,
			int expirationDateDay, int expirationDateYear,
			int expirationDateHour, int expirationDateMinute,
			boolean neverExpire, ServiceContext serviceContext)
		throws PortalException {

		return commercePriceListService.addCommercePriceList(
			groupId, userId, commerceCurrencyId, parentCommercePriceListId,
			name, priority, displayDateMonth, displayDateDay, displayDateYear,
			displayDateHour, displayDateMinute, expirationDateMonth,
			expirationDateDay, expirationDateYear, expirationDateHour,
			expirationDateMinute, null, neverExpire, serviceContext);
	}

	@Override
	public CommercePriceList addCommercePriceList(
			long groupId, long userId, long commerceCurrencyId,
			long parentCommercePriceListId, String name, double priority,
			int displayDateMonth, int displayDateDay, int displayDateYear,
			int displayDateHour, int displayDateMinute, int expirationDateMonth,
			int expirationDateDay, int expirationDateYear,
			int expirationDateHour, int expirationDateMinute,
			String externalReferenceCode, boolean neverExpire,
			ServiceContext serviceContext)
		throws PortalException {

		PortalPermissionUtil.check(
			getPermissionChecker(),
			CommercePriceListActionKeys.MANAGE_COMMERCE_PRICE_LISTS);

		return commercePriceListLocalService.addCommercePriceList(
			groupId, userId, commerceCurrencyId, parentCommercePriceListId,
			name, priority, displayDateMonth, displayDateDay, displayDateYear,
			displayDateHour, displayDateMinute, expirationDateMonth,
			expirationDateDay, expirationDateYear, expirationDateHour,
			expirationDateMinute, externalReferenceCode, neverExpire,
			serviceContext);
	}

	@Override
	public CommercePriceList addCommercePriceList(
			long groupId, long userId, long commerceCurrencyId, String name,
			double priority, int displayDateMonth, int displayDateDay,
			int displayDateYear, int displayDateHour, int displayDateMinute,
			int expirationDateMonth, int expirationDateDay,
			int expirationDateYear, int expirationDateHour,
			int expirationDateMinute, boolean neverExpire,
			ServiceContext serviceContext)
		throws PortalException {

		return commercePriceListService.addCommercePriceList(
			groupId, userId, commerceCurrencyId, 0, name, priority,
			displayDateMonth, displayDateDay, displayDateYear, displayDateHour,
			displayDateMinute, expirationDateMonth, expirationDateDay,
			expirationDateYear, expirationDateHour, expirationDateMinute, null,
			neverExpire, serviceContext);
	}

	@Override
	public CommercePriceList addCommercePriceList(
			long groupId, long userId, long commerceCurrencyId, String name,
			double priority, int displayDateMonth, int displayDateDay,
			int displayDateYear, int displayDateHour, int displayDateMinute,
			int expirationDateMonth, int expirationDateDay,
			int expirationDateYear, int expirationDateHour,
			int expirationDateMinute, String externalReferenceCode,
			boolean neverExpire, ServiceContext serviceContext)
		throws PortalException {

		return commercePriceListService.addCommercePriceList(
			groupId, userId, commerceCurrencyId, 0, name, priority,
			displayDateMonth, displayDateDay, displayDateYear, displayDateHour,
			displayDateMinute, expirationDateMonth, expirationDateDay,
			expirationDateYear, expirationDateHour, expirationDateMinute,
			externalReferenceCode, neverExpire, serviceContext);
	}

	@Override
	public void deleteCommercePriceList(long commercePriceListId)
		throws PortalException {

		PortalPermissionUtil.check(
			getPermissionChecker(),
			CommercePriceListActionKeys.MANAGE_COMMERCE_PRICE_LISTS);

		commercePriceListLocalService.deleteCommercePriceList(
			commercePriceListId);
	}

	@Override
	public CommercePriceList fetchByExternalReferenceCode(
			long companyId, String externalReferenceCode)
		throws PortalException {

		PortalPermissionUtil.check(
			getPermissionChecker(),
			CommercePriceListActionKeys.MANAGE_COMMERCE_PRICE_LISTS);

		return commercePriceListLocalService.fetchByExternalReferenceCode(
			companyId, externalReferenceCode);
	}

	@Override
	public CommercePriceList fetchCommercePriceList(long commercePriceListId)
		throws PortalException {

		PortalPermissionUtil.check(
			getPermissionChecker(),
			CommercePriceListActionKeys.MANAGE_COMMERCE_PRICE_LISTS);

		return commercePriceListLocalService.fetchCommercePriceList(
			commercePriceListId);
	}

	@Override
	public List<CommercePriceList> getCommercePriceLists(
			long companyId, int status, int start, int end,
			OrderByComparator<CommercePriceList> orderByComparator)
		throws PortalException {

		PortalPermissionUtil.check(
			getPermissionChecker(),
			CommercePriceListActionKeys.MANAGE_COMMERCE_PRICE_LISTS);

		List<CommerceCatalog> commerceCatalogs =
			_commerceCatalogService.searchCommerceCatalogs(companyId);

		Stream<CommerceCatalog> stream = commerceCatalogs.stream();

		long[] commerceCatalogGroupIds = stream.mapToLong(
			_getCommerceCatalogToLongFunction()
		).toArray();

		return commercePriceListLocalService.getCommercePriceLists(
			commerceCatalogGroupIds, companyId, status, start, end,
			orderByComparator);
	}

	@Override
	public int getCommercePriceListsCount(long companyId, int status)
		throws PortalException {

		PortalPermissionUtil.check(
			getPermissionChecker(),
			CommercePriceListActionKeys.MANAGE_COMMERCE_PRICE_LISTS);

		List<CommerceCatalog> commerceCatalogs =
			_commerceCatalogService.searchCommerceCatalogs(companyId);

		Stream<CommerceCatalog> stream = commerceCatalogs.stream();

		long[] commerceCatalogGroupIds = stream.mapToLong(
			_getCommerceCatalogToLongFunction()
		).toArray();

		return commercePriceListLocalService.getCommercePriceListsCount(
			commerceCatalogGroupIds, companyId, status);
	}

	@Override
	public BaseModelSearchResult<CommercePriceList> searchCommercePriceLists(
			long companyId, String keywords, int status, int start, int end,
			Sort sort)
		throws PortalException {

		PortalPermissionUtil.check(
			getPermissionChecker(),
			CommercePriceListActionKeys.MANAGE_COMMERCE_PRICE_LISTS);

		List<CommerceCatalog> commerceCatalogs =
			_commerceCatalogService.searchCommerceCatalogs(companyId);

		Stream<CommerceCatalog> stream = commerceCatalogs.stream();

		long[] commerceCatalogGroupIds = stream.mapToLong(
			_getCommerceCatalogToLongFunction()
		).toArray();

		return commercePriceListLocalService.searchCommercePriceLists(
			companyId, commerceCatalogGroupIds, keywords, status, start, end,
			sort);
	}

	@Override
	public CommercePriceList updateCommercePriceList(
			long commercePriceListId, long commerceCurrencyId,
			long parentCommercePriceListId, String name, double priority,
			int displayDateMonth, int displayDateDay, int displayDateYear,
			int displayDateHour, int displayDateMinute, int expirationDateMonth,
			int expirationDateDay, int expirationDateYear,
			int expirationDateHour, int expirationDateMinute,
			boolean neverExpire, ServiceContext serviceContext)
		throws PortalException {

		PortalPermissionUtil.check(
			getPermissionChecker(),
			CommercePriceListActionKeys.MANAGE_COMMERCE_PRICE_LISTS);

		return commercePriceListLocalService.updateCommercePriceList(
			commercePriceListId, commerceCurrencyId, parentCommercePriceListId,
			name, priority, displayDateMonth, displayDateDay, displayDateYear,
			displayDateHour, displayDateMinute, expirationDateMonth,
			expirationDateDay, expirationDateYear, expirationDateHour,
			expirationDateMinute, neverExpire, serviceContext);
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

		return commercePriceListService.updateCommercePriceList(
			commercePriceListId, commerceCurrencyId, 0, name, priority,
			displayDateMonth, displayDateDay, displayDateYear, displayDateHour,
			displayDateMinute, expirationDateMonth, expirationDateDay,
			expirationDateYear, expirationDateHour, expirationDateMinute,
			neverExpire, serviceContext);
	}

	@Override
	public CommercePriceList updateExternalReferenceCode(
			CommercePriceList commercePriceList, long companyId,
			String externalReferenceCode)
		throws PortalException {

		PortalPermissionUtil.check(
			getPermissionChecker(),
			CommercePriceListActionKeys.MANAGE_COMMERCE_PRICE_LISTS);

		return commercePriceListLocalService.updateExternalReferenceCode(
			commercePriceList, externalReferenceCode);
	}

	@Override
	public CommercePriceList upsertCommercePriceList(
			long groupId, long userId, long commercePriceListId,
			long commerceCurrencyId, long parentCommercePriceListId,
			String name, double priority, int displayDateMonth,
			int displayDateDay, int displayDateYear, int displayDateHour,
			int displayDateMinute, int expirationDateMonth,
			int expirationDateDay, int expirationDateYear,
			int expirationDateHour, int expirationDateMinute,
			String externalReferenceCode, boolean neverExpire,
			ServiceContext serviceContext)
		throws PortalException {

		PortalPermissionUtil.check(
			getPermissionChecker(),
			CommercePriceListActionKeys.MANAGE_COMMERCE_PRICE_LISTS);

		return commercePriceListLocalService.upsertCommercePriceList(
			groupId, userId, commercePriceListId, commerceCurrencyId,
			parentCommercePriceListId, name, priority, displayDateMonth,
			displayDateDay, displayDateYear, displayDateHour, displayDateMinute,
			expirationDateMonth, expirationDateDay, expirationDateYear,
			expirationDateHour, expirationDateMinute, externalReferenceCode,
			neverExpire, serviceContext);
	}

	@Override
	public CommercePriceList upsertCommercePriceList(
			long groupId, long userId, long commercePriceListId,
			long commerceCurrencyId, String name, double priority,
			int displayDateMonth, int displayDateDay, int displayDateYear,
			int displayDateHour, int displayDateMinute, int expirationDateMonth,
			int expirationDateDay, int expirationDateYear,
			int expirationDateHour, int expirationDateMinute,
			String externalReferenceCode, boolean neverExpire,
			ServiceContext serviceContext)
		throws PortalException {

		return commercePriceListService.upsertCommercePriceList(
			groupId, userId, commercePriceListId, commerceCurrencyId, 0, name,
			priority, displayDateMonth, displayDateDay, displayDateYear,
			displayDateHour, displayDateMinute, expirationDateMonth,
			expirationDateDay, expirationDateYear, expirationDateHour,
			expirationDateMinute, externalReferenceCode, neverExpire,
			serviceContext);
	}

	private ToLongFunction<CommerceCatalog>
		_getCommerceCatalogToLongFunction() {

		return new ToLongFunction<CommerceCatalog>() {

			@Override
			public long applyAsLong(CommerceCatalog commerceCatalog) {
				try {
					return commerceCatalog.getCommerceCatalogGroupId();
				}
				catch (PortalException pe) {
					_log.error(pe, pe);

					return 0;
				}
			}

		};
	}

	private static final Log _log = LogFactoryUtil.getLog(
		CommercePriceListServiceImpl.class);

	@ServiceReference(type = CommerceCatalogService.class)
	private CommerceCatalogService _commerceCatalogService;

}