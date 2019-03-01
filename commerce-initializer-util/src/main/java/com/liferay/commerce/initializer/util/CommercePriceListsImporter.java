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

package com.liferay.commerce.initializer.util;

import com.liferay.commerce.currency.model.CommerceCurrency;
import com.liferay.commerce.currency.service.CommerceCurrencyLocalService;
import com.liferay.commerce.price.list.model.CommercePriceList;
import com.liferay.commerce.price.list.service.CommercePriceListLocalService;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.service.UserLocalService;
import com.liferay.portal.kernel.util.CalendarFactoryUtil;
import com.liferay.portal.kernel.util.FriendlyURLNormalizerUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.Validator;

import java.util.Calendar;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Alec Sloan
 */
@Component(service = CommercePriceListsImporter.class)
public class CommercePriceListsImporter {

	public void importCommercePriceLists(
			JSONArray jsonArray, long scopeGroupId, long userId)
		throws PortalException {

		User user = _userLocalService.getUser(userId);

		ServiceContext serviceContext = new ServiceContext();

		serviceContext.setScopeGroupId(scopeGroupId);
		serviceContext.setUserId(userId);
		serviceContext.setCompanyId(user.getCompanyId());

		for (int i = 0; i < jsonArray.length(); i++) {
			JSONObject jsonObject = jsonArray.getJSONObject(i);

			_importCommercePriceList(jsonObject, serviceContext);
		}
	}

	private void _importCommercePriceList(
			JSONObject jsonObject, ServiceContext serviceContext)
		throws PortalException {

		String currencyCode = jsonObject.getString("CurrencyCode");

		if (Validator.isNull(currencyCode)) {
			//We should throw that they must have currencyCode

			return;
		}

		CommerceCurrency commerceCurrency =
			_commerceCurrencyLocalService.getCommerceCurrency(
				serviceContext.getScopeGroupId(), currencyCode);

		long parentPriceListId = 0;

		String parentPriceListName = jsonObject.getString("ParentPriceList");

		if (!Validator.isBlank(parentPriceListName)) {
			String externalReferenceCode = StringBundler.concat(
				String.valueOf(serviceContext.getScopeGroupId()), "_",
				FriendlyURLNormalizerUtil.normalize(parentPriceListName));

			CommercePriceList parentPriceList =
				_commercePriceListLocalService.
					fetchCommercePriceListByReferenceCode(
						serviceContext.getCompanyId(), externalReferenceCode);

			parentPriceListId = parentPriceList.getParentCommercePriceListId();
		}

		String name = jsonObject.getString("Name");

		if (Validator.isBlank(name)) {
			//We should throw that the must have name

			return;
		}

		User user = _userLocalService.getUser(serviceContext.getUserId());

		int priority = jsonObject.getInt("Priority");

		Calendar displayCalendar = CalendarFactoryUtil.getCalendar(
			user.getTimeZone());

		int displayDateMonth = displayCalendar.get(
			jsonObject.getInt("DisplayDateMonth", Calendar.MONTH));
		int displayDateDay = displayCalendar.get(
			jsonObject.getInt("DisplayDateDayOfMonth", Calendar.DAY_OF_MONTH));
		int displayDateYear = displayCalendar.get(
			jsonObject.getInt("DisplayDateYear", Calendar.YEAR));
		int displayDateHour = displayCalendar.get(
			jsonObject.getInt("DisplayDateHour", Calendar.HOUR));
		int displayDateMinute = displayCalendar.get(
			jsonObject.getInt("DisplayDateMinute", Calendar.MINUTE));
		int displayDateAmPm = displayCalendar.get(
			jsonObject.getInt("DisplayDateAmPm", Calendar.AM_PM));

		if (displayDateAmPm == Calendar.PM) {
			displayDateHour += 12;
		}

		Calendar expirationCalendar = CalendarFactoryUtil.getCalendar(
			user.getTimeZone());

		expirationCalendar.add(Calendar.MONTH, 1);

		int expirationDateMonth = expirationCalendar.get(
			jsonObject.getInt("ExpirationDateMonth", Calendar.MONTH));
		int expirationDateDay = expirationCalendar.get(
			jsonObject.getInt(
				"ExpirationDateDayOfMonth", Calendar.DAY_OF_MONTH));
		int expirationDateYear = expirationCalendar.get(
			jsonObject.getInt("ExpirationDateYear", Calendar.YEAR));
		int expirationDateHour = expirationCalendar.get(
			jsonObject.getInt("ExpirationDateHour", Calendar.HOUR));
		int expirationDateMinute = expirationCalendar.get(
			jsonObject.getInt("ExpirationDateMinute", Calendar.MINUTE));
		int expirationDateAmPm = expirationCalendar.get(
			jsonObject.getInt("ExpirationDateAmPm", Calendar.AM_PM));

		if (expirationDateAmPm == Calendar.PM) {
			expirationDateHour += 12;
		}

		boolean neverExpire = jsonObject.getBoolean("NeverExpire", true);

		// Add Commerce Price List

		String externalReferenceCode = StringBundler.concat(
			String.valueOf(serviceContext.getScopeGroupId()), "_",
			FriendlyURLNormalizerUtil.normalize(name));

		_commercePriceListLocalService.upsertCommercePriceList(
			0, commerceCurrency.getCommerceCurrencyId(), parentPriceListId,
			name, priority, displayDateMonth, displayDateDay, displayDateYear,
			displayDateHour, displayDateMinute, expirationDateMonth,
			expirationDateDay, expirationDateYear, expirationDateHour,
			expirationDateMinute, externalReferenceCode, neverExpire,
			serviceContext);

	}

	@Reference
	private CommerceCurrencyLocalService _commerceCurrencyLocalService;

	@Reference
	private CommercePriceListLocalService _commercePriceListLocalService;

	@Reference
	private UserLocalService _userLocalService;

}