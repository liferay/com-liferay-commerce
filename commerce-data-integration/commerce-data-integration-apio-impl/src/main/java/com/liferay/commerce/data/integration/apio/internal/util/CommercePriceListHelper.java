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

package com.liferay.commerce.data.integration.apio.internal.util;

import com.liferay.apio.architect.functional.Try;
import com.liferay.commerce.currency.model.CommerceCurrency;
import com.liferay.commerce.currency.service.CommerceCurrencyService;
import com.liferay.commerce.data.integration.apio.identifier.ClassPKExternalReferenceCode;
import com.liferay.commerce.price.list.model.CommercePriceList;
import com.liferay.commerce.price.list.service.CommercePriceListLocalService;
import com.liferay.commerce.price.list.service.CommercePriceListService;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.Company;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.security.auth.CompanyThreadLocal;
import com.liferay.portal.kernel.service.CompanyLocalService;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.CalendarFactoryUtil;
import com.liferay.portal.kernel.workflow.WorkflowConstants;

import java.io.Serializable;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Zoltán Takács
 */
@Component(immediate = true, service = CommercePriceListHelper.class)
public class CommercePriceListHelper {

	public static String getCurrencyCode(CommercePriceList commercePriceList) {
		return Try.fromFallible(
			commercePriceList::getCommerceCurrency
		).map(
			CommerceCurrency::getCode
		).orElse(
			null
		);
	}

	public ClassPKExternalReferenceCode
		commercePriceListIdToClassPKExternalReferenceCode(
			long commercePriceListId) {

		try {
			CommercePriceList commercePriceList =
				_commercePriceListService.fetchCommercePriceList(
					commercePriceListId);

			return commercePriceListToClassPKExternalReferenceCode(
				commercePriceList);
		}
		catch (PortalException pe) {
			_log.error(
				"Unable to find Price List with ID " + commercePriceListId, pe);
		}

		return null;
	}

	public ClassPKExternalReferenceCode
		commercePriceListToClassPKExternalReferenceCode(
			CommercePriceList commercePriceList) {

		if (commercePriceList != null) {
			return ClassPKExternalReferenceCode.create(
				commercePriceList.getCommercePriceListId(),
				commercePriceList.getExternalReferenceCode());
		}

		return null;
	}

	public void deletePriceList(
			ClassPKExternalReferenceCode classPKExternalReferenceCode)
		throws PortalException {

		CommercePriceList commercePriceList =
			getCommercePriceListByClassPKExternalReferenceCode(
				classPKExternalReferenceCode);

		if (commercePriceList != null) {
			_commercePriceListService.deleteCommercePriceList(
				commercePriceList.getCommercePriceListId());
		}
	}

	public CommercePriceList getCommercePriceList(Long commercePriceListId)
		throws PortalException {

		return _commercePriceListService.fetchCommercePriceList(
			commercePriceListId);
	}

	public CommercePriceList getCommercePriceListByClassPKExternalReferenceCode(
			ClassPKExternalReferenceCode classPKExternalReferenceCode)
		throws PortalException {

		long companyId = CompanyThreadLocal.getCompanyId();

		Company company = _companyLocalService.getCompany(companyId);

		return getCommercePriceListByClassPKExternalReferenceCode(
			classPKExternalReferenceCode, company);
	}

	public CommercePriceList getCommercePriceListByClassPKExternalReferenceCode(
			ClassPKExternalReferenceCode classPKExternalReferenceCode,
			Company company)
		throws PortalException {

		long commercePriceListId = classPKExternalReferenceCode.getClassPK();

		if (commercePriceListId > 0) {
			return _commercePriceListService.fetchCommercePriceList(
				commercePriceListId);
		}

		return _commercePriceListService.fetchByExternalReferenceCode(
			company.getCompanyId(),
			classPKExternalReferenceCode.getExternalReferenceCode());
	}

	public CommercePriceList updateCommercePriceList(
			ClassPKExternalReferenceCode classPKExternalReferenceCode,
			String currency, String name, Double priority, Boolean neverExpire,
			Date displayDate, Date expirationDate)
		throws PortalException {

		CommercePriceList commercePriceList =
			getCommercePriceListByClassPKExternalReferenceCode(
				classPKExternalReferenceCode);

		long groupId = commercePriceList.getGroupId();

		long commerceCurrencyId = _getCommerceCurrencyId(groupId, currency);

		if (neverExpire == null) {
			neverExpire = Boolean.TRUE;
		}

		if (priority == null) {
			priority = 0D;
		}

		ServiceContext serviceContext = _serviceContextHelper.getServiceContext(
			groupId);

		Calendar displayCalendar = CalendarFactoryUtil.getCalendar(
			serviceContext.getTimeZone());

		if (displayDate != null) {
			displayCalendar = _convertDateToCalendar(displayDate);
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

		if (expirationDate != null) {
			expirationCalendar = _convertDateToCalendar(expirationDate);
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

		return _commercePriceListService.updateCommercePriceList(
			commercePriceList.getCommercePriceListId(), commerceCurrencyId,
			name, priority, displayDateMonth, displayDateDay, displayDateYear,
			displayDateHour, displayDateMinute, expirationDateMonth,
			expirationDateDay, expirationDateYear, expirationDateHour,
			expirationDateMinute, neverExpire, serviceContext);
	}

	public CommercePriceList upsertCommercePriceList(
			Long groupId, Long commercePriceListId, String currency,
			String name, Double priority, Boolean neverExpire, Date displayDate,
			Date expirationDate, String externalReferenceCode, Boolean active,
			User currentUser)
		throws PortalException {

		long commerceCurrencyId = _getCommerceCurrencyId(groupId, currency);

		if (neverExpire == null) {
			neverExpire = Boolean.TRUE;
		}

		if (priority == null) {
			priority = 0D;
		}

		ServiceContext serviceContext = _serviceContextHelper.getServiceContext(
			groupId);

		Calendar displayCalendar = CalendarFactoryUtil.getCalendar(
			serviceContext.getTimeZone());

		if (displayDate != null) {
			displayCalendar = _convertDateToCalendar(displayDate);
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

		if (expirationDate != null) {
			expirationCalendar = _convertDateToCalendar(expirationDate);
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

		CommercePriceList commercePriceList =
			_commercePriceListService.upsertCommercePriceList(
				commercePriceListId, commerceCurrencyId, name, priority,
				displayDateMonth, displayDateDay, displayDateYear,
				displayDateHour, displayDateMinute, expirationDateMonth,
				expirationDateDay, expirationDateYear, expirationDateHour,
				expirationDateMinute, externalReferenceCode, neverExpire,
				serviceContext);

		if (!active) {
			Map<String, Serializable> workflowContext = new HashMap<>();

			_commercePriceListLocalService.updateStatus(
				currentUser.getUserId(),
				commercePriceList.getCommercePriceListId(),
				WorkflowConstants.STATUS_INACTIVE, serviceContext,
				workflowContext);
		}

		return commercePriceList;
	}

	private Calendar _convertDateToCalendar(Date date) {
		Calendar calendar = CalendarFactoryUtil.getCalendar();

		calendar.setTime(date);

		return calendar;
	}

	private long _getCommerceCurrencyId(Long groupId, String currencyCode)
		throws PortalException {

		CommerceCurrency commerceCurrency =
			_commerceCurrencyService.getCommerceCurrency(groupId, currencyCode);

		return commerceCurrency.getCommerceCurrencyId();
	}

	private static final Log _log = LogFactoryUtil.getLog(
		CommercePriceListHelper.class);

	@Reference
	private CommerceCurrencyService _commerceCurrencyService;

	@Reference
	private CommercePriceListLocalService _commercePriceListLocalService;

	@Reference
	private CommercePriceListService _commercePriceListService;

	@Reference
	private CompanyLocalService _companyLocalService;

	@Reference
	private ServiceContextHelper _serviceContextHelper;

}