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

package com.liferay.commerce.price.list.test.util;

import com.liferay.commerce.currency.model.CommerceCurrency;
import com.liferay.commerce.currency.service.CommerceCurrencyLocalServiceUtil;
import com.liferay.commerce.price.list.model.CommercePriceList;
import com.liferay.commerce.price.list.service.CommercePriceListLocalServiceUtil;
import com.liferay.commerce.product.model.CommerceCatalog;
import com.liferay.commerce.product.service.CommerceCatalogLocalServiceUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.service.UserLocalServiceUtil;
import com.liferay.portal.kernel.test.util.ServiceContextTestUtil;
import com.liferay.portal.kernel.util.CalendarFactoryUtil;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * @author Zoltán Takács
 * @author Ethan Bustad
 * @author Luca Pellizzon
 */
public class CommercePriceListTestUtil {

	public static CommercePriceList addCommercePriceList(
			long groupId, String currency, long parentCommercePriceListId,
			String name, Double priority, Boolean neverExpire, Date displayDate,
			Date expirationDate, String externalReferenceCode)
		throws PortalException {

		if (priority == null) {
			priority = 0D;
		}

		if (neverExpire == null) {
			neverExpire = Boolean.TRUE;
		}

		ServiceContext serviceContext =
			ServiceContextTestUtil.getServiceContext(groupId);

		User user = UserLocalServiceUtil.getUserById(
			serviceContext.getUserId());

		List<CommerceCatalog> commerceCatalogs =
			CommerceCatalogLocalServiceUtil.getCommerceCatalogs(
				user.getCompanyId(), true);

		CommerceCatalog commerceCatalog = commerceCatalogs.get(0);

		DateElements displayDateElements = new DateElements(
			displayDate, CalendarFactoryUtil.getCalendar(user.getTimeZone()));

		Calendar defaultExpirationCalendar = CalendarFactoryUtil.getCalendar(
			user.getTimeZone());

		defaultExpirationCalendar.add(Calendar.MONTH, 1);

		DateElements expirationDateElements = new DateElements(
			expirationDate, defaultExpirationCalendar);

		long commerceCurrencyId = _getCommerceCurrencyId(
			serviceContext.getCompanyId(), currency);

		return CommercePriceListLocalServiceUtil.addCommercePriceList(
			commerceCatalog.getGroupId(), user.getUserId(), commerceCurrencyId,
			parentCommercePriceListId, name, priority,
			displayDateElements.getMonth(), displayDateElements.getDay(),
			displayDateElements.getYear(), displayDateElements.getHour(),
			displayDateElements.getMinute(), expirationDateElements.getMonth(),
			expirationDateElements.getDay(), expirationDateElements.getYear(),
			expirationDateElements.getHour(),
			expirationDateElements.getMinute(), externalReferenceCode,
			neverExpire, serviceContext);
	}

	public static CommercePriceList addCommercePriceList(
			long groupId, String currency, String name, Double priority,
			Boolean neverExpire, Date displayDate, Date expirationDate,
			String externalReferenceCode)
		throws PortalException {

		return addCommercePriceList(
			groupId, currency, 0, name, priority, neverExpire, displayDate,
			expirationDate, externalReferenceCode);
	}

	public static CommercePriceList updateCommercePriceList(
			long groupId, long commercePriceListId, String currency,
			long parentCommercePriceListId, String name, Double priority,
			Boolean neverExpire, Date displayDate, Date expirationDate)
		throws PortalException {

		if (neverExpire == null) {
			neverExpire = Boolean.TRUE;
		}

		if (priority == null) {
			priority = 0D;
		}

		ServiceContext serviceContext =
			ServiceContextTestUtil.getServiceContext(groupId);

		User user = UserLocalServiceUtil.getUserById(
			serviceContext.getUserId());

		DateElements displayDateElements = new DateElements(
			displayDate, CalendarFactoryUtil.getCalendar(user.getTimeZone()));

		Calendar defaultExpirationCalendar = CalendarFactoryUtil.getCalendar(
			user.getTimeZone());

		defaultExpirationCalendar.add(Calendar.MONTH, 1);

		DateElements expirationDateElements = new DateElements(
			expirationDate, defaultExpirationCalendar);

		long commerceCurrencyId = _getCommerceCurrencyId(
			serviceContext.getCompanyId(), currency);

		return CommercePriceListLocalServiceUtil.updateCommercePriceList(
			commercePriceListId, commerceCurrencyId, parentCommercePriceListId,
			name, priority, displayDateElements.getMonth(),
			displayDateElements.getDay(), displayDateElements.getYear(),
			displayDateElements.getHour(), displayDateElements.getMinute(),
			expirationDateElements.getMonth(), expirationDateElements.getDay(),
			expirationDateElements.getYear(), expirationDateElements.getHour(),
			expirationDateElements.getMinute(), neverExpire, serviceContext);
	}

	public static CommercePriceList upsertCommercePriceList(
			long groupId, long commercePriceListId, String currency,
			long parentCommercePriceListId, String name, Double priority,
			Boolean neverExpire, Date displayDate, Date expirationDate,
			String externalReferenceCode)
		throws PortalException {

		if (neverExpire == null) {
			neverExpire = Boolean.TRUE;
		}

		if (priority == null) {
			priority = 0D;
		}

		ServiceContext serviceContext =
			ServiceContextTestUtil.getServiceContext(groupId);

		User user = UserLocalServiceUtil.getUserById(
			serviceContext.getUserId());

		List<CommerceCatalog> commerceCatalogs =
			CommerceCatalogLocalServiceUtil.getCommerceCatalogs(
				user.getCompanyId(), true);

		CommerceCatalog commerceCatalog = commerceCatalogs.get(0);

		DateElements displayDateElements = new DateElements(
			displayDate, CalendarFactoryUtil.getCalendar(user.getTimeZone()));

		Calendar defaultExpirationCalendar = CalendarFactoryUtil.getCalendar(
			user.getTimeZone());

		defaultExpirationCalendar.add(Calendar.MONTH, 1);

		DateElements expirationDateElements = new DateElements(
			expirationDate, defaultExpirationCalendar);

		long commerceCurrencyId = _getCommerceCurrencyId(
			serviceContext.getCompanyId(), currency);

		return CommercePriceListLocalServiceUtil.upsertCommercePriceList(
			commerceCatalog.getGroupId(), user.getUserId(), commercePriceListId,
			commerceCurrencyId, parentCommercePriceListId, name, priority,
			displayDateElements.getMonth(), displayDateElements.getDay(),
			displayDateElements.getYear(), displayDateElements.getHour(),
			displayDateElements.getMinute(), expirationDateElements.getMonth(),
			expirationDateElements.getDay(), expirationDateElements.getYear(),
			expirationDateElements.getHour(),
			expirationDateElements.getMinute(), externalReferenceCode,
			neverExpire, serviceContext);
	}

	public static CommercePriceList upsertCommercePriceList(
			long groupId, long commercePriceListId, String currency,
			String name, Double priority, Boolean neverExpire, Date displayDate,
			Date expirationDate, String externalReferenceCode)
		throws PortalException {

		return upsertCommercePriceList(
			groupId, commercePriceListId, currency, 0, name, priority,
			neverExpire, displayDate, expirationDate, externalReferenceCode);
	}

	private static long _getCommerceCurrencyId(
			long groupId, String currencyCode)
		throws PortalException {

		CommerceCurrency commerceCurrency =
			CommerceCurrencyLocalServiceUtil.getCommerceCurrency(
				groupId, currencyCode);

		return commerceCurrency.getCommerceCurrencyId();
	}

	private static class DateElements {

		public DateElements(Date date, Calendar defaultCalendar) {
			if (date != null) {
				_calendar = _convertDateToCalendar(date);
			}
			else {
				_calendar = defaultCalendar;
			}
		}

		public int getDay() {
			return _calendar.get(Calendar.DAY_OF_MONTH);
		}

		public int getHour() {
			int hour = _calendar.get(Calendar.HOUR);

			if (_calendar.get(Calendar.AM_PM) == Calendar.PM) {
				hour += 12;
			}

			return hour;
		}

		public int getMinute() {
			return _calendar.get(Calendar.MINUTE);
		}

		public int getMonth() {
			return _calendar.get(Calendar.MONTH);
		}

		public int getYear() {
			return _calendar.get(Calendar.YEAR);
		}

		private static Calendar _convertDateToCalendar(Date date) {
			Calendar calendar = CalendarFactoryUtil.getCalendar();

			calendar.setTime(date);

			return calendar;
		}

		private final Calendar _calendar;

	}

}