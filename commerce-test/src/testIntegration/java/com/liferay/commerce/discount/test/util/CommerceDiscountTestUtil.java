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

package com.liferay.commerce.discount.test.util;

import com.liferay.asset.kernel.model.AssetCategory;
import com.liferay.commerce.account.model.CommerceAccountGroup;
import com.liferay.commerce.discount.constants.CommerceDiscountConstants;
import com.liferay.commerce.discount.model.CommerceDiscount;
import com.liferay.commerce.discount.model.CommerceDiscountCommerceAccountGroupRel;
import com.liferay.commerce.discount.service.CommerceDiscountCommerceAccountGroupRelLocalServiceUtil;
import com.liferay.commerce.discount.service.CommerceDiscountLocalServiceUtil;
import com.liferay.commerce.discount.service.CommerceDiscountRelLocalServiceUtil;
import com.liferay.commerce.product.model.CPDefinition;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.service.UserLocalServiceUtil;
import com.liferay.portal.kernel.test.util.RandomTestUtil;
import com.liferay.portal.kernel.test.util.ServiceContextTestUtil;
import com.liferay.portal.kernel.util.CalendarFactoryUtil;

import java.math.BigDecimal;

import java.util.Calendar;

/**
 * @author Luca Pellizzon
 */
public class CommerceDiscountTestUtil {

	public static CommerceDiscountCommerceAccountGroupRel
			addAccountGroupToDiscount(
				CommerceDiscount commerceDiscount, long userId)
		throws Exception {

		CommerceAccountGroup commerceAccountGroup = null;

		/*CommerceAccountGroupTestUtil.addUserCommerceAccountGroup(
			userId);*/

		return CommerceDiscountCommerceAccountGroupRelLocalServiceUtil.
			addCommerceDiscountCommerceAccountGroupRel(
				commerceDiscount.getCommerceDiscountId(),
				commerceAccountGroup.getCommerceAccountGroupId(),
				ServiceContextTestUtil.getServiceContext());
	}

	public static CommerceDiscount addCouponDiscount(
			long groupId, double amount, String couponCode, String target,
			long... targetIds)
		throws Exception {

		CommerceDiscount commerceDiscount = addFixedCommerceDiscount(
			groupId, amount, target, targetIds);

		commerceDiscount.setUseCouponCode(true);
		commerceDiscount.setCouponCode(couponCode);
		commerceDiscount.setLimitationType(
			CommerceDiscountConstants.LIMITATION_TYPE_LIMITED);
		commerceDiscount.setLimitationTimes(1);

		commerceDiscount =
			CommerceDiscountLocalServiceUtil.updateCommerceDiscount(
				commerceDiscount);

		return commerceDiscount;
	}

	public static CommerceDiscountCommerceAccountGroupRel
			addDiscountCommerceAccountGroupRel(
				CommerceDiscount commerceDiscount, long userId)
		throws Exception {

		CommerceAccountGroup commerceAccountGroup = null;

		/*CommerceAccountGroupTestUtil.addUserCommerceAccountGroup(
			commerceDiscount.getGroupId(), userId);*/

		return CommerceDiscountCommerceAccountGroupRelLocalServiceUtil.
			addCommerceDiscountCommerceAccountGroupRel(
				commerceDiscount.getCommerceDiscountId(),
				commerceAccountGroup.getCommerceAccountGroupId(),
				ServiceContextTestUtil.getServiceContext());
	}

	public static CommerceDiscount addFixedCommerceDiscount(
			long groupId, double amount, String target, long... targetIds)
		throws Exception {

		BigDecimal discount = BigDecimal.valueOf(amount);

		ServiceContext serviceContext =
			ServiceContextTestUtil.getServiceContext(groupId);

		User user = UserLocalServiceUtil.getDefaultUser(
			serviceContext.getCompanyId());

		Calendar calendar = CalendarFactoryUtil.getCalendar(user.getTimeZone());

		CommerceDiscount commerceDiscount =
			CommerceDiscountLocalServiceUtil.addCommerceDiscount(
				user.getUserId(), RandomTestUtil.randomString(), target, false,
				null, false, BigDecimal.ZERO, discount, BigDecimal.ZERO,
				BigDecimal.ZERO, BigDecimal.ZERO,
				CommerceDiscountConstants.LIMITATION_TYPE_UNLIMITED, 0, true,
				calendar.get(Calendar.MONTH),
				calendar.get(Calendar.DAY_OF_MONTH),
				calendar.get(Calendar.YEAR), calendar.get(Calendar.HOUR_OF_DAY),
				calendar.get(Calendar.MINUTE), calendar.get(Calendar.MONTH),
				calendar.get(Calendar.DAY_OF_MONTH),
				calendar.get(Calendar.YEAR), calendar.get(Calendar.HOUR_OF_DAY),
				calendar.get(Calendar.MINUTE), true, serviceContext);

		_addTargetDetails(commerceDiscount, target, targetIds);

		return commerceDiscount;
	}

	public static CommerceDiscount addPercentageCommerceDiscount(
			long groupId, double percentage1, double percentage2,
			double percentage3, double percentage4, String target,
			long... targetIds)
		throws Exception {

		BigDecimal level1 = BigDecimal.valueOf(percentage1);
		BigDecimal level2 = BigDecimal.valueOf(percentage2);
		BigDecimal level3 = BigDecimal.valueOf(percentage3);
		BigDecimal level4 = BigDecimal.valueOf(percentage4);

		ServiceContext serviceContext =
			ServiceContextTestUtil.getServiceContext(groupId);

		User user = UserLocalServiceUtil.getDefaultUser(
			serviceContext.getCompanyId());

		Calendar calendar = CalendarFactoryUtil.getCalendar(user.getTimeZone());

		CommerceDiscount commerceDiscount =
			CommerceDiscountLocalServiceUtil.addCommerceDiscount(
				user.getUserId(), RandomTestUtil.randomString(), target, false,
				null, true, BigDecimal.valueOf(10000), level1, level2, level3,
				level4, CommerceDiscountConstants.LIMITATION_TYPE_UNLIMITED, 0,
				true, calendar.get(Calendar.MONTH),
				calendar.get(Calendar.DAY_OF_MONTH),
				calendar.get(Calendar.YEAR), calendar.get(Calendar.HOUR_OF_DAY),
				calendar.get(Calendar.MINUTE), calendar.get(Calendar.MONTH),
				calendar.get(Calendar.DAY_OF_MONTH),
				calendar.get(Calendar.YEAR), calendar.get(Calendar.HOUR_OF_DAY),
				calendar.get(Calendar.MINUTE), true, serviceContext);

		_addTargetDetails(commerceDiscount, target, targetIds);

		return commerceDiscount;
	}

	private static void _addDiscountCategoryRel(
			CommerceDiscount commerceDiscount, long... targetIds)
		throws Exception {

		ServiceContext serviceContext =
			ServiceContextTestUtil.getServiceContext();

		for (long id : targetIds) {
			CommerceDiscountRelLocalServiceUtil.addCommerceDiscountRel(
				commerceDiscount.getCommerceDiscountId(),
				AssetCategory.class.getName(), id, serviceContext);
		}
	}

	private static void _addDiscountProductRel(
			CommerceDiscount commerceDiscount, long... targetIds)
		throws Exception {

		ServiceContext serviceContext =
			ServiceContextTestUtil.getServiceContext();

		for (long id : targetIds) {
			CommerceDiscountRelLocalServiceUtil.addCommerceDiscountRel(
				commerceDiscount.getCommerceDiscountId(),
				CPDefinition.class.getName(), id, serviceContext);
		}
	}

	private static void _addTargetDetails(
			CommerceDiscount commerceDiscount, String target, long... targetIds)
		throws Exception {

		if (CommerceDiscountConstants.TARGET_CATEGORIES.equals(target)) {
			_addDiscountCategoryRel(commerceDiscount, targetIds);
		}

		if (CommerceDiscountConstants.TARGET_PRODUCT.equals(target)) {
			_addDiscountProductRel(commerceDiscount, targetIds);
		}
	}

}