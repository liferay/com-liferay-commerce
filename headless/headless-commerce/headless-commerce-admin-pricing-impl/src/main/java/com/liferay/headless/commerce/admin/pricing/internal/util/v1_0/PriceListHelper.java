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

import com.liferay.commerce.currency.model.CommerceCurrency;
import com.liferay.commerce.currency.service.CommerceCurrencyService;
import com.liferay.commerce.price.list.exception.NoSuchPriceListException;
import com.liferay.commerce.price.list.model.CommercePriceList;
import com.liferay.commerce.price.list.service.CommercePriceListLocalService;
import com.liferay.commerce.price.list.service.CommercePriceListService;
import com.liferay.commerce.product.model.CommerceCatalog;
import com.liferay.commerce.product.service.CommerceCatalogService;
import com.liferay.headless.commerce.admin.pricing.dto.v1_0.PriceList;
import com.liferay.headless.commerce.admin.pricing.internal.mapper.v1_0.DTOMapper;
import com.liferay.headless.commerce.core.util.DateConfig;
import com.liferay.headless.commerce.core.util.IdUtils;
import com.liferay.headless.commerce.core.util.ServiceContextHelper;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.Company;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.CalendarFactoryUtil;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.workflow.WorkflowConstants;
import com.liferay.portal.vulcan.accept.language.AcceptLanguage;
import com.liferay.portal.vulcan.pagination.Page;
import com.liferay.portal.vulcan.pagination.Pagination;

import java.io.Serializable;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Zoltán Takács
 * @author Alessio Antonio Rendina
 */
@Component(immediate = true, service = PriceListHelper.class)
public class PriceListHelper {

	public void deletePriceList(String id, User user, Company company)
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

	public PriceList getPriceList(
			String id, AcceptLanguage acceptLanguage, Company company)
		throws PortalException {

		return _dtoMapper.modelToDTO(
			getPriceListById(id, company),
			acceptLanguage.getPreferredLanguageId());
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

	public Page<PriceList> getPriceLists(
			long companyId, AcceptLanguage acceptLanguage,
			Pagination pagination)
		throws PortalException {

		List<CommercePriceList> commercePriceLists =
			_commercePriceListService.getCommercePriceLists(
				companyId, WorkflowConstants.STATUS_APPROVED,
				pagination.getStartPosition(), pagination.getEndPosition(),
				null);

		int totalItems = _commercePriceListService.getCommercePriceListsCount(
			companyId, WorkflowConstants.STATUS_APPROVED);

		Stream<CommercePriceList> stream = commercePriceLists.stream();

		return stream.map(
			commercePriceList -> _dtoMapper.modelToDTO(
				commercePriceList, acceptLanguage.getPreferredLanguageId())
		).collect(
			Collectors.collectingAndThen(
				Collectors.toList(),
				priceLists -> Page.of(priceLists, pagination, totalItems))
		);
	}

	public PriceList updatePriceList(
			String id, PriceList priceList, AcceptLanguage acceptLanguage,
			Company company)
		throws PortalException {

		return _dtoMapper.modelToDTO(
			_updatePriceList(
				id, company, priceList.getCurrency(), priceList.getName(),
				priceList.getPriority(), priceList.getNeverExpire(),
				priceList.getDisplayDate(), priceList.getExpirationDate()),
			acceptLanguage.getPreferredLanguageId());
	}

	public PriceList upsertPriceList(
			long companyId, PriceList priceList, User user,
			AcceptLanguage acceptLanguage)
		throws PortalException {

		return _dtoMapper.modelToDTO(
			_upsertPriceList(
				companyId, 0L, priceList.getCommercePriceListId(),
				priceList.getCurrency(), priceList.getName(),
				priceList.getPriority(), priceList.getNeverExpire(),
				priceList.getDisplayDate(), priceList.getExpirationDate(),
				priceList.getExternalReferenceCode(), priceList.getActive(),
				user),
			acceptLanguage.getPreferredLanguageId());
	}

	private Calendar _convertDateToCalendar(Date date) {
		Calendar calendar = CalendarFactoryUtil.getCalendar();

		calendar.setTime(date);

		return calendar;
	}

	private long _getCommerceCurrencyId(Long companyId, String currencyCode)
		throws PortalException {

		CommerceCurrency commerceCurrency =
			_commerceCurrencyService.getCommerceCurrency(
				companyId, currencyCode);

		return commerceCurrency.getCommerceCurrencyId();
	}

	private CommercePriceList _updatePriceList(
			String id, Company company, String currency, String name,
			Double priority, Boolean neverExpire, Date displayDate,
			Date expirationDate)
		throws PortalException {

		CommercePriceList commercePriceList = getPriceListById(id, company);

		long commerceCurrencyId = _getCommerceCurrencyId(
			commercePriceList.getCompanyId(), currency);

		if (neverExpire == null) {
			neverExpire = Boolean.TRUE;
		}

		if (priority == null) {
			priority = 0D;
		}

		ServiceContext serviceContext = _serviceContextHelper.getServiceContext(
			commercePriceList.getGroupId());

		Calendar displayCalendar = CalendarFactoryUtil.getCalendar(
			serviceContext.getTimeZone());

		if (displayDate != null) {
			displayCalendar = _convertDateToCalendar(displayDate);
		}

		DateConfig displayDateConfig = new DateConfig(displayCalendar);

		Calendar expirationCalendar = CalendarFactoryUtil.getCalendar(
			serviceContext.getTimeZone());

		expirationCalendar.add(Calendar.MONTH, 1);

		if (expirationDate != null) {
			expirationCalendar = _convertDateToCalendar(expirationDate);
		}

		DateConfig expirationDateConfig = new DateConfig(expirationCalendar);

		return _commercePriceListService.updateCommercePriceList(
			commercePriceList.getCommercePriceListId(), commerceCurrencyId,
			name, priority, displayDateConfig.getMonth(),
			displayDateConfig.getDay(), displayDateConfig.getYear(),
			displayDateConfig.getHour(), displayDateConfig.getMinute(),
			expirationDateConfig.getMonth(), expirationDateConfig.getDay(),
			expirationDateConfig.getYear(), expirationDateConfig.getHour(),
			expirationDateConfig.getMinute(), neverExpire, serviceContext);
	}

	private CommercePriceList _upsertPriceList(
			Long companyId, Long commerceCatalogId, Long commercePriceListId,
			String currency, String name, Double priority, Boolean neverExpire,
			Date displayDate, Date expirationDate, String externalReferenceCode,
			Boolean active, User currentUser)
		throws PortalException {

		long commerceCurrencyId = _getCommerceCurrencyId(companyId, currency);

		if (neverExpire == null) {
			neverExpire = Boolean.TRUE;
		}

		if (priority == null) {
			priority = 0D;
		}

		ServiceContext serviceContext = _serviceContextHelper.getServiceContext(
			companyId);

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

		if (commercePriceListId == null) {
			commercePriceListId = 0L;
		}

		CommerceCatalog commerceCatalog =
			_commerceCatalogService.fetchCommerceCatalog(commerceCatalogId);

		CommercePriceList commercePriceList =
			_commercePriceListService.upsertCommercePriceList(
				commerceCatalog.getGroupId(), currentUser.getUserId(),
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
	private CommerceCatalogService _commerceCatalogService;

	@Reference
	private CommerceCurrencyService _commerceCurrencyService;

	@Reference
	private CommercePriceListLocalService _commercePriceListLocalService;

	@Reference
	private CommercePriceListService _commercePriceListService;

	@Reference
	private DTOMapper _dtoMapper;

	@Reference
	private ServiceContextHelper _serviceContextHelper;

}