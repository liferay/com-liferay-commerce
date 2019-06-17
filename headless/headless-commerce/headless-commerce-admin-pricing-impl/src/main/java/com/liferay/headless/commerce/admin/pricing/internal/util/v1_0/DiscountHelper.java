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

package com.liferay.headless.commerce.admin.pricing.internal.util.v1_0;

import com.liferay.commerce.discount.exception.NoSuchDiscountException;
import com.liferay.commerce.discount.model.CommerceDiscount;
import com.liferay.commerce.discount.service.CommerceDiscountService;
import com.liferay.headless.commerce.admin.pricing.dto.v1_0.Discount;
import com.liferay.headless.commerce.admin.pricing.internal.mapper.v1_0.DTOMapper;
import com.liferay.headless.commerce.core.util.DateConfig;
import com.liferay.headless.commerce.core.util.ServiceContextHelper;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.search.BaseModelSearchResult;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.CalendarFactoryUtil;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.workflow.WorkflowConstants;
import com.liferay.portal.vulcan.pagination.Page;
import com.liferay.portal.vulcan.pagination.Pagination;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Alessio Antonio Rendina
 * @author Zoltán Takács
 */
@Component(immediate = true, service = DiscountHelper.class)
public class DiscountHelper {

	public void deleteDiscount(Long id) throws PortalException {
		_commerceDiscountService.deleteCommerceDiscount(id);
	}

	public Discount getDiscount(Long id) throws PortalException {
		CommerceDiscount commerceDiscount =
			_commerceDiscountService.getCommerceDiscount(id);

		return _dtoMapper.modelToDTO(commerceDiscount);
	}

	public Page<Discount> getDiscounts(Long companyId, Pagination pagination)
		throws PortalException {

		BaseModelSearchResult<CommerceDiscount> commerceDiscounts =
			_commerceDiscountService.searchCommerceDiscounts(
				companyId, StringPool.BLANK, WorkflowConstants.STATUS_ANY,
				pagination.getStartPosition(), pagination.getEndPosition(),
				null);

		int count = commerceDiscounts.getLength();

		List<Discount> discounts = new ArrayList<>();

		for (CommerceDiscount commerceDiscount :
				commerceDiscounts.getBaseModels()) {

			discounts.add(_dtoMapper.modelToDTO(commerceDiscount));
		}

		return Page.of(discounts, pagination, count);
	}

	public CommerceDiscount updateDiscount(
			Long id, Discount discount, User user)
		throws PortalException {

		CommerceDiscount commerceDiscount =
			_commerceDiscountService.getCommerceDiscount(id);

		Boolean neverExpire = discount.getNeverExpire();

		if (neverExpire == null) {
			neverExpire = Boolean.TRUE;
		}

		ServiceContext serviceContext = _serviceContextHelper.getServiceContext(
			0L, new long[0], user, true);

		Calendar displayCalendar = CalendarFactoryUtil.getCalendar(
			serviceContext.getTimeZone());

		DateConfig displayDateConfig = new DateConfig(displayCalendar);

		Calendar expirationCalendar = CalendarFactoryUtil.getCalendar(
			serviceContext.getTimeZone());

		expirationCalendar.add(Calendar.MONTH, 1);

		DateConfig expirationDateConfig = new DateConfig(expirationCalendar);

		return _commerceDiscountService.updateCommerceDiscount(
			id, discount.getTitle(), discount.getTarget(),
			GetterUtil.get(
				discount.getUseCouponCode(),
				commerceDiscount.isUseCouponCode()),
			discount.getCouponCode(),
			GetterUtil.get(
				discount.getUsePercentage(),
				commerceDiscount.isUsePercentage()),
			discount.getMaximumDiscountAmount(), discount.getPercentageLevel1(),
			discount.getPercentageLevel2(), discount.getPercentageLevel3(),
			discount.getPercentageLevel4(), discount.getLimitationType(),
			GetterUtil.get(
				discount.getLimitationTimes(),
				commerceDiscount.getLimitationTimes()),
			GetterUtil.get(discount.getActive(), commerceDiscount.isActive()),
			displayDateConfig.getMonth(), displayDateConfig.getDay(),
			displayDateConfig.getYear(), displayDateConfig.getHour(),
			displayDateConfig.getMinute(), expirationDateConfig.getMonth(),
			expirationDateConfig.getDay(), expirationDateConfig.getYear(),
			expirationDateConfig.getHour(), expirationDateConfig.getMinute(),
			neverExpire, serviceContext);
	}

	public Discount upsertDiscount(Discount discount, User user)
		throws PortalException {

		try {
			CommerceDiscount commerceDiscount = updateDiscount(
				discount.getId(), discount, user);

			return _dtoMapper.modelToDTO(commerceDiscount);
		}
		catch (NoSuchDiscountException nsde) {
			if (_log.isDebugEnabled()) {
				_log.debug(
					"Unable to find discount with ID: " + discount.getId());
			}
		}

		Boolean neverExpire = discount.getNeverExpire();

		if (neverExpire == null) {
			neverExpire = Boolean.TRUE;
		}

		ServiceContext serviceContext = _serviceContextHelper.getServiceContext(
			user);

		Calendar displayCalendar = CalendarFactoryUtil.getCalendar(
			serviceContext.getTimeZone());

		if (discount.getDisplayDate() != null) {
			displayCalendar = _convertDateToCalendar(discount.getDisplayDate());
		}

		int displayDateMonth = displayCalendar.get(Calendar.MONTH);
		int displayDateDay = displayCalendar.get(Calendar.DAY_OF_MONTH);
		int displayDateYear = displayCalendar.get(Calendar.YEAR);
		int displayDateHour = displayCalendar.get(Calendar.HOUR);
		int displayDateMinute = displayCalendar.get(Calendar.MINUTE);
		int displayDateAmPm = displayCalendar.get(Calendar.AM_PM);

		if (displayDateAmPm == Calendar.PM) {
			displayDateHour += 12;
		}

		Calendar expirationCalendar = CalendarFactoryUtil.getCalendar(
			serviceContext.getTimeZone());

		expirationCalendar.add(Calendar.MONTH, 1);

		if (discount.getExpirationDate() != null) {
			expirationCalendar = _convertDateToCalendar(
				discount.getExpirationDate());
		}

		int expirationDateMonth = expirationCalendar.get(Calendar.MONTH);
		int expirationDateDay = expirationCalendar.get(Calendar.DAY_OF_MONTH);
		int expirationDateYear = expirationCalendar.get(Calendar.YEAR);
		int expirationDateHour = expirationCalendar.get(Calendar.HOUR);
		int expirationDateMinute = expirationCalendar.get(Calendar.MINUTE);
		int expirationDateAmPm = expirationCalendar.get(Calendar.AM_PM);

		if (expirationDateAmPm == Calendar.PM) {
			expirationDateHour += 12;
		}

		CommerceDiscount commerceDiscount =
			_commerceDiscountService.addCommerceDiscount(
				user.getUserId(), discount.getTitle(), discount.getTarget(),
				GetterUtil.get(discount.getUseCouponCode(), false),
				discount.getCouponCode(),
				GetterUtil.get(discount.getUsePercentage(), false),
				discount.getMaximumDiscountAmount(),
				discount.getPercentageLevel1(), discount.getPercentageLevel2(),
				discount.getPercentageLevel3(), discount.getPercentageLevel4(),
				discount.getLimitationType(),
				GetterUtil.get(discount.getLimitationTimes(), 0),
				GetterUtil.get(discount.getActive(), false), displayDateMonth,
				displayDateDay, displayDateYear, displayDateHour,
				displayDateMinute, expirationDateMonth, expirationDateDay,
				expirationDateYear, expirationDateHour, expirationDateMinute,
				neverExpire, serviceContext);

		return _dtoMapper.modelToDTO(commerceDiscount);
	}

	private Calendar _convertDateToCalendar(Date date) {
		Calendar calendar = CalendarFactoryUtil.getCalendar();

		calendar.setTime(date);

		return calendar;
	}

	private static final Log _log = LogFactoryUtil.getLog(DiscountHelper.class);

	@Reference
	private CommerceDiscountService _commerceDiscountService;

	@Reference
	private DTOMapper _dtoMapper;

	@Reference
	private ServiceContextHelper _serviceContextHelper;

}