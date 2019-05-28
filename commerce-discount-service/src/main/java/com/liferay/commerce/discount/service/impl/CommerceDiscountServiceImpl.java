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

import com.liferay.commerce.discount.constants.CommerceDiscountActionKeys;
import com.liferay.commerce.discount.model.CommerceDiscount;
import com.liferay.commerce.discount.service.base.CommerceDiscountServiceBaseImpl;
import com.liferay.commerce.product.model.CommerceCatalog;
import com.liferay.commerce.product.service.CommerceCatalogService;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.search.BaseModelSearchResult;
import com.liferay.portal.kernel.search.Sort;
import com.liferay.portal.kernel.security.permission.ActionKeys;
import com.liferay.portal.kernel.security.permission.resource.ModelResourcePermission;
import com.liferay.portal.kernel.security.permission.resource.ModelResourcePermissionFactory;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.service.permission.PortalPermissionUtil;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.spring.extender.service.ServiceReference;

import java.math.BigDecimal;

import java.util.List;
import java.util.function.ToLongFunction;
import java.util.stream.Stream;

/**
 * @author Marco Leo
 * @author Alessio Antonio Rendina
 */
public class CommerceDiscountServiceImpl
	extends CommerceDiscountServiceBaseImpl {

	@Override
	public CommerceDiscount addCommerceDiscount(
			long groupId, long userId, String title, String target,
			boolean useCouponCode, String couponCode, boolean usePercentage,
			BigDecimal maximumDiscountAmount, BigDecimal level1,
			BigDecimal level2, BigDecimal level3, BigDecimal level4,
			String limitationType, int limitationTimes, boolean active,
			int displayDateMonth, int displayDateDay, int displayDateYear,
			int displayDateHour, int displayDateMinute, int expirationDateMonth,
			int expirationDateDay, int expirationDateYear,
			int expirationDateHour, int expirationDateMinute,
			boolean neverExpire, ServiceContext serviceContext)
		throws PortalException {

		PortalPermissionUtil.check(
			getPermissionChecker(),
			CommerceDiscountActionKeys.ADD_COMMERCE_DISCOUNT);

		return commerceDiscountLocalService.addCommerceDiscount(
			groupId, userId, title, target, useCouponCode, couponCode,
			usePercentage, maximumDiscountAmount, level1, level2, level3,
			level4, limitationType, limitationTimes, active, displayDateMonth,
			displayDateDay, displayDateYear, displayDateHour, displayDateMinute,
			expirationDateMonth, expirationDateDay, expirationDateYear,
			expirationDateHour, expirationDateMinute, neverExpire,
			serviceContext);
	}

	@Override
	public void deleteCommerceDiscount(long commerceDiscountId)
		throws PortalException {

		_commerceDiscountResourcePermission.check(
			getPermissionChecker(), commerceDiscountId, ActionKeys.DELETE);

		commerceDiscountLocalService.deleteCommerceDiscount(commerceDiscountId);
	}

	@Override
	public CommerceDiscount fetchCommerceDiscount(long commerceDiscountId)
		throws PortalException {

		CommerceDiscount commerceDiscount =
			commerceDiscountLocalService.fetchCommerceDiscount(
				commerceDiscountId);

		if (commerceDiscount != null) {
			_commerceDiscountResourcePermission.check(
				getPermissionChecker(), commerceDiscountId, ActionKeys.VIEW);
		}

		return commerceDiscount;
	}

	@Override
	public CommerceDiscount getCommerceDiscount(long commerceDiscountId)
		throws PortalException {

		_commerceDiscountResourcePermission.check(
			getPermissionChecker(), commerceDiscountId, ActionKeys.VIEW);

		return commerceDiscountLocalService.getCommerceDiscount(
			commerceDiscountId);
	}

	@Override
	public List<CommerceDiscount> getCommerceDiscounts(
			long companyId, int start, int end,
			OrderByComparator<CommerceDiscount> orderByComparator)
		throws PortalException {

		PortalPermissionUtil.check(
			getPermissionChecker(),
			CommerceDiscountActionKeys.VIEW_COMMERCE_DISCOUNTS);

		List<CommerceCatalog> commerceCatalogs =
			_commerceCatalogService.searchCommerceCatalogs(companyId);

		Stream<CommerceCatalog> stream = commerceCatalogs.stream();

		long[] commerceCatalogGroupIds = stream.mapToLong(
			_getCommerceCatalogToLongFunction()
		).toArray();

		return commerceDiscountLocalService.getCommerceDiscounts(
			commerceCatalogGroupIds, companyId, start, end, orderByComparator);
	}

	@Override
	public List<CommerceDiscount> getCommerceDiscounts(
			long companyId, String couponCode)
		throws PortalException {

		PortalPermissionUtil.check(
			getPermissionChecker(),
			CommerceDiscountActionKeys.VIEW_COMMERCE_DISCOUNTS);

		return commerceDiscountLocalService.getCommerceDiscounts(
			companyId, couponCode);
	}

	@Override
	public int getCommerceDiscountsCount(long companyId)
		throws PortalException {

		PortalPermissionUtil.check(
			getPermissionChecker(),
			CommerceDiscountActionKeys.VIEW_COMMERCE_DISCOUNTS);

		List<CommerceCatalog> commerceCatalogs =
			_commerceCatalogService.searchCommerceCatalogs(companyId);

		Stream<CommerceCatalog> stream = commerceCatalogs.stream();

		long[] commerceCatalogGroupIds = stream.mapToLong(
			_getCommerceCatalogToLongFunction()
		).toArray();

		return commerceDiscountLocalService.getCommerceDiscountsCount(
			commerceCatalogGroupIds, companyId);
	}

	@Override
	public int getCommerceDiscountsCount(long companyId, String couponCode)
		throws PortalException {

		PortalPermissionUtil.check(
			getPermissionChecker(),
			CommerceDiscountActionKeys.VIEW_COMMERCE_DISCOUNTS);

		return commerceDiscountLocalService.getCommerceDiscountsCount(
			companyId, couponCode);
	}

	@Override
	public BaseModelSearchResult<CommerceDiscount> searchCommerceDiscounts(
			long companyId, String keywords, int status, int start, int end,
			Sort sort)
		throws PortalException {

		PortalPermissionUtil.check(
			getPermissionChecker(),
			CommerceDiscountActionKeys.VIEW_COMMERCE_DISCOUNTS);

		List<CommerceCatalog> commerceCatalogs =
			_commerceCatalogService.searchCommerceCatalogs(companyId);

		Stream<CommerceCatalog> stream = commerceCatalogs.stream();

		long[] commerceCatalogGroupIds = stream.mapToLong(
			_getCommerceCatalogToLongFunction()
		).toArray();

		return commerceDiscountLocalService.searchCommerceDiscounts(
			companyId, commerceCatalogGroupIds, keywords, status, start, end,
			sort);
	}

	@Override
	public CommerceDiscount updateCommerceDiscount(
			long commerceDiscountId, String title, String target,
			boolean useCouponCode, String couponCode, boolean usePercentage,
			BigDecimal maximumDiscountAmount, BigDecimal level1,
			BigDecimal level2, BigDecimal level3, BigDecimal level4,
			String limitationType, int limitationTimes, boolean active,
			int displayDateMonth, int displayDateDay, int displayDateYear,
			int displayDateHour, int displayDateMinute, int expirationDateMonth,
			int expirationDateDay, int expirationDateYear,
			int expirationDateHour, int expirationDateMinute,
			boolean neverExpire, ServiceContext serviceContext)
		throws PortalException {

		_commerceDiscountResourcePermission.check(
			getPermissionChecker(), commerceDiscountId, ActionKeys.UPDATE);

		return commerceDiscountLocalService.updateCommerceDiscount(
			commerceDiscountId, title, target, useCouponCode, couponCode,
			usePercentage, maximumDiscountAmount, level1, level2, level3,
			level4, limitationType, limitationTimes, active, displayDateMonth,
			displayDateDay, displayDateYear, displayDateHour, displayDateMinute,
			expirationDateMonth, expirationDateDay, expirationDateYear,
			expirationDateHour, expirationDateMinute, neverExpire,
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
		CommerceDiscountServiceImpl.class);

	private static volatile ModelResourcePermission<CommerceDiscount>
		_commerceDiscountResourcePermission =
			ModelResourcePermissionFactory.getInstance(
				CommerceDiscountServiceImpl.class,
				"_commerceDiscountResourcePermission", CommerceDiscount.class);

	@ServiceReference(type = CommerceCatalogService.class)
	private CommerceCatalogService _commerceCatalogService;

}