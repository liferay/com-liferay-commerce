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

package com.liferay.headless.commerce.admin.pricing.internal.resource.util.v1_0;

import com.liferay.commerce.discount.exception.NoSuchDiscountException;
import com.liferay.commerce.discount.model.CommerceDiscount;
import com.liferay.commerce.discount.service.CommerceDiscountService;
import com.liferay.commerce.openapi.core.context.Pagination;
import com.liferay.commerce.openapi.core.model.CollectionDTO;
import com.liferay.commerce.openapi.core.util.ServiceContextHelper;
import com.liferay.headless.commerce.admin.pricing.internal.util.v1_0.DTOUtils;
import com.liferay.headless.commerce.admin.pricing.model.v1_0.DiscountDTO;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.CalendarFactoryUtil;
import com.liferay.portal.kernel.util.GetterUtil;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Alessio Antonio Rendina
 */
@Component(immediate = true, service = DiscountHelper.class)
public class DiscountHelper {

	public void deleteDiscount(String id) throws PortalException {
		_commerceDiscountService.deleteCommerceDiscount(GetterUtil.getLong(id));
	}

	public DiscountDTO getDiscountDTO(String id) throws PortalException {
		CommerceDiscount commerceDiscount =
			_commerceDiscountService.getCommerceDiscount(
				GetterUtil.getLong(id));

		return DTOUtils.modelToDTO(commerceDiscount);
	}

	public CollectionDTO<DiscountDTO> getDiscountDTOs(
			Long groupId, Pagination pagination)
		throws PortalException {

		List<CommerceDiscount> commerceDiscounts =
			_commerceDiscountService.getCommerceDiscounts(
				groupId, pagination.getStartPosition(),
				pagination.getEndPosition(), null);

		int count = _commerceDiscountService.getCommerceDiscountsCount(groupId);

		List<DiscountDTO> discountDTOs = new ArrayList<>();

		for (CommerceDiscount commerceDiscount : commerceDiscounts) {
			discountDTOs.add(DTOUtils.modelToDTO(commerceDiscount));
		}

		return new CollectionDTO<>(discountDTOs, count);
	}

	public CommerceDiscount updateDiscount(
			String id, DiscountDTO discountDTO, User user)
		throws PortalException {

		CommerceDiscount commerceDiscount =
			_commerceDiscountService.getCommerceDiscount(
				GetterUtil.getLong(id));

		Boolean neverExpire = discountDTO.isNeverExpire();

		if (neverExpire == null) {
			neverExpire = Boolean.TRUE;
		}

		ServiceContext serviceContext = _serviceContextHelper.getServiceContext(
			commerceDiscount.getGroupId(), new long[0], user, true);

		Calendar displayCalendar = CalendarFactoryUtil.getCalendar(
			serviceContext.getTimeZone());

		if (discountDTO.getDisplayDate() != null) {
			displayCalendar = _convertDateToCalendar(
				discountDTO.getDisplayDate());
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

		if (discountDTO.getExpirationDate() != null) {
			expirationCalendar = _convertDateToCalendar(
				discountDTO.getExpirationDate());
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

		return _commerceDiscountService.updateCommerceDiscount(
			GetterUtil.getLong(id), discountDTO.getTitle(),
			discountDTO.getTarget(),
			GetterUtil.get(
				discountDTO.isUseCouponCode(),
				commerceDiscount.isUseCouponCode()),
			discountDTO.getCouponCode(),
			GetterUtil.get(
				discountDTO.isUsePercentage(),
				commerceDiscount.isUsePercentage()),
			discountDTO.getMaximumDiscountAmount(),
			discountDTO.getPercentageLevel1(),
			discountDTO.getPercentageLevel2(),
			discountDTO.getPercentageLevel3(),
			discountDTO.getPercentageLevel4(), discountDTO.getLimitationType(),
			GetterUtil.get(
				discountDTO.getLimitationTimes(),
				commerceDiscount.getLimitationTimes()),
			GetterUtil.get(discountDTO.isActive(), commerceDiscount.isActive()),
			displayDateMonth, displayDateDay, displayDateYear, displayDateHour,
			displayDateMinute, expirationDateMonth, expirationDateDay,
			expirationDateYear, expirationDateHour, expirationDateMinute,
			neverExpire, serviceContext);
	}

	public DiscountDTO upsertDiscount(
			Long groupId, DiscountDTO discountDTO, User user)
		throws PortalException {

		try {
			CommerceDiscount commerceDiscount = updateDiscount(
				String.valueOf(discountDTO.getId()), discountDTO, user);

			return DTOUtils.modelToDTO(commerceDiscount);
		}
		catch (NoSuchDiscountException nsde) {
			if (_log.isDebugEnabled()) {
				_log.debug(
					"Unable to find discount with ID: " + discountDTO.getId());
			}
		}

		Boolean neverExpire = discountDTO.isNeverExpire();

		if (neverExpire == null) {
			neverExpire = Boolean.TRUE;
		}

		ServiceContext serviceContext = _serviceContextHelper.getServiceContext(
			groupId, new long[0], user, true);

		Calendar displayCalendar = CalendarFactoryUtil.getCalendar(
			serviceContext.getTimeZone());

		if (discountDTO.getDisplayDate() != null) {
			displayCalendar = _convertDateToCalendar(
				discountDTO.getDisplayDate());
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

		if (discountDTO.getExpirationDate() != null) {
			expirationCalendar = _convertDateToCalendar(
				discountDTO.getExpirationDate());
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
				discountDTO.getTitle(), discountDTO.getTarget(),
				GetterUtil.get(discountDTO.isUseCouponCode(), false),
				discountDTO.getCouponCode(),
				GetterUtil.get(discountDTO.isUsePercentage(), false),
				discountDTO.getMaximumDiscountAmount(),
				discountDTO.getPercentageLevel1(),
				discountDTO.getPercentageLevel2(),
				discountDTO.getPercentageLevel3(),
				discountDTO.getPercentageLevel4(),
				discountDTO.getLimitationType(),
				GetterUtil.get(discountDTO.getLimitationTimes(), 0),
				GetterUtil.get(discountDTO.isActive(), false), displayDateMonth,
				displayDateDay, displayDateYear, displayDateHour,
				displayDateMinute, expirationDateMonth, expirationDateDay,
				expirationDateYear, expirationDateHour, expirationDateMinute,
				neverExpire, serviceContext);

		return DTOUtils.modelToDTO(commerceDiscount);
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
	private ServiceContextHelper _serviceContextHelper;

}