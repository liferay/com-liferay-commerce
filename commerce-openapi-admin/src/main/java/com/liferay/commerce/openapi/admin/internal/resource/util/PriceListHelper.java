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

package com.liferay.commerce.openapi.admin.internal.resource.util;

import com.liferay.commerce.currency.model.CommerceCurrency;
import com.liferay.commerce.currency.service.CommerceCurrencyService;
import com.liferay.commerce.openapi.admin.internal.util.DTOUtils;
import com.liferay.commerce.openapi.admin.internal.util.IdUtils;
import com.liferay.commerce.openapi.admin.model.CollectionDTO;
import com.liferay.commerce.openapi.admin.model.PriceListDTO;
import com.liferay.commerce.openapi.core.context.Pagination;
import com.liferay.commerce.price.list.exception.NoSuchPriceListException;
import com.liferay.commerce.price.list.model.CommercePriceList;
import com.liferay.commerce.price.list.service.CommercePriceListLocalService;
import com.liferay.commerce.price.list.service.CommercePriceListService;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.Company;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.CalendarFactoryUtil;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.workflow.WorkflowConstants;

import java.io.Serializable;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Zoltán Takács
 */
@Component(immediate = true, service = PriceListHelper.class)
public class PriceListHelper {

	public static String getCurrencyCode(CommercePriceList commercePriceList)
		throws PortalException {

		return Optional.of(
			commercePriceList.getCommerceCurrency()
		).map(
			CommerceCurrency::getCode
		).orElse(
			null
		);
	}

	public void deletePriceList(
			String id, long groupId, User user, Locale locale, Company company)
		throws PortalException {

		CommercePriceList commercePriceList = null;

		try {
			commercePriceList = getPriceListById(id, company);
		}
		catch (NoSuchPriceListException nsple) {
			if (_log.isDebugEnabled()) {
				_log.debug("Price List not exist with ID: " + id, nsple);
			}

			return;
		}

		_commercePriceListService.deleteCommercePriceList(
			commercePriceList.getCommercePriceListId());
	}

	public PriceListDTO getPriceList(
			String id, long groupId, User user, Locale locale, Company company)
		throws PortalException {

		return DTOUtils.modelToDTO(getPriceListById(id, company), locale);
	}

	public CommercePriceList getPriceListById(String id, Company company)
		throws PortalException {

		CommercePriceList commercePriceList = null;

		if (IdUtils.isLocalPK(id)) {
			commercePriceList =
				_commercePriceListService.fetchCommercePriceList(
					GetterUtil.getLong(id));
		}
		else {

			// Get Price List by External Reference Code

			String erc = IdUtils.getExternalReferenceCodeFromId(id);

			commercePriceList =
				_commercePriceListService.fetchByExternalReferenceCode(
					company.getCompanyId(), erc);
		}

		if (commercePriceList == null) {
			throw new NoSuchPriceListException(
				"Unable to find Price List with ID: " + id);
		}

		return commercePriceList;
	}

	public CollectionDTO<PriceListDTO> getPriceLists(
			long groupId, User user, Locale locale, Company company,
			Pagination pagination)
		throws PortalException {

		List<CommercePriceList> commercePriceLists =
			_commercePriceListService.getCommercePriceLists(
				groupId, WorkflowConstants.STATUS_APPROVED,
				pagination.getStartPosition(), pagination.getEndPosition(),
				null);

		int totalItems = _commercePriceListService.getCommercePriceListsCount(
			groupId, WorkflowConstants.STATUS_APPROVED);

		Stream<CommercePriceList> stream = commercePriceLists.stream();

		return stream.map(
			commercePriceList -> DTOUtils.modelToDTO(commercePriceList, locale)
		).collect(
			Collectors.collectingAndThen(
				Collectors.toList(),
				priceListDTOs ->
					new CollectionDTO<>(priceListDTOs, totalItems))
		);
	}

	public PriceListDTO updatePriceList(
			String id, long groupId, PriceListDTO priceListDTO, User user,
			Locale locale, Company company)
		throws PortalException {

		return DTOUtils.modelToDTO(
			_updatePriceList(
				id, company, priceListDTO.getCurrency(), priceListDTO.getName(),
				priceListDTO.getPriority(), priceListDTO.isNeverExpire(),
				priceListDTO.getDisplayDate(),
				priceListDTO.getExpirationDate()),
			locale);
	}

	public PriceListDTO upsertPriceList(
			long groupId, PriceListDTO priceListDTO, User user, Locale locale,
			Company company)
		throws PortalException {

		return DTOUtils.modelToDTO(
			_upsertPriceList(
				groupId, priceListDTO.getCommercePriceListId(),
				priceListDTO.getCurrency(), priceListDTO.getName(),
				priceListDTO.getPriority(), priceListDTO.isNeverExpire(),
				priceListDTO.getDisplayDate(), priceListDTO.getExpirationDate(),
				priceListDTO.getExternalReferenceCode(),
				priceListDTO.isActive(), user),
			locale);
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

	private CommercePriceList _updatePriceList(
			String id, Company company, String currency, String name,
			Double priority, Boolean neverExpire, Date displayDate,
			Date expirationDate)
		throws PortalException {

		CommercePriceList commercePriceList = getPriceListById(id, company);

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

	private CommercePriceList _upsertPriceList(
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

	private static final Log _log = LogFactoryUtil.getLog(
		PriceListHelper.class);

	@Reference
	private CommerceCurrencyService _commerceCurrencyService;

	@Reference
	private CommercePriceListLocalService _commercePriceListLocalService;

	@Reference
	private CommercePriceListService _commercePriceListService;

	@Reference
	private ServiceContextHelper _serviceContextHelper;

}