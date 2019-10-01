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

package com.liferay.commerce.price.list.service;

import com.liferay.portal.kernel.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link CommercePriceListService}.
 *
 * @author Alessio Antonio Rendina
 * @see CommercePriceListService
 * @generated
 */
public class CommercePriceListServiceWrapper
	implements CommercePriceListService,
			   ServiceWrapper<CommercePriceListService> {

	public CommercePriceListServiceWrapper(
		CommercePriceListService commercePriceListService) {

		_commercePriceListService = commercePriceListService;
	}

	/**
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link CommercePriceListServiceUtil} to access the commerce price list remote service. Add custom service methods to <code>com.liferay.commerce.price.list.service.impl.CommercePriceListServiceImpl</code> and rerun ServiceBuilder to automatically copy the method declarations to this interface.
	 */
	@Override
	public com.liferay.commerce.price.list.model.CommercePriceList
			addCommercePriceList(
				long groupId, long userId, String commerceCurrencyCode,
				long parentCommercePriceListId, String name, double priority,
				int displayDateMonth, int displayDateDay, int displayDateYear,
				int displayDateHour, int displayDateMinute,
				int expirationDateMonth, int expirationDateDay,
				int expirationDateYear, int expirationDateHour,
				int expirationDateMinute, boolean neverExpire,
				com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _commercePriceListService.addCommercePriceList(
			groupId, userId, commerceCurrencyCode, parentCommercePriceListId,
			name, priority, displayDateMonth, displayDateDay, displayDateYear,
			displayDateHour, displayDateMinute, expirationDateMonth,
			expirationDateDay, expirationDateYear, expirationDateHour,
			expirationDateMinute, neverExpire, serviceContext);
	}

	@Override
	public com.liferay.commerce.price.list.model.CommercePriceList
			addCommercePriceList(
				long groupId, long userId, String commerceCurrencyCode,
				long parentCommercePriceListId, String name, double priority,
				int displayDateMonth, int displayDateDay, int displayDateYear,
				int displayDateHour, int displayDateMinute,
				int expirationDateMonth, int expirationDateDay,
				int expirationDateYear, int expirationDateHour,
				int expirationDateMinute, String externalReferenceCode,
				boolean neverExpire,
				com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _commercePriceListService.addCommercePriceList(
			groupId, userId, commerceCurrencyCode, parentCommercePriceListId,
			name, priority, displayDateMonth, displayDateDay, displayDateYear,
			displayDateHour, displayDateMinute, expirationDateMonth,
			expirationDateDay, expirationDateYear, expirationDateHour,
			expirationDateMinute, externalReferenceCode, neverExpire,
			serviceContext);
	}

	@Override
	public com.liferay.commerce.price.list.model.CommercePriceList
			addCommercePriceList(
				long groupId, long userId, String commerceCurrencyCode,
				String name, double priority, int displayDateMonth,
				int displayDateDay, int displayDateYear, int displayDateHour,
				int displayDateMinute, int expirationDateMonth,
				int expirationDateDay, int expirationDateYear,
				int expirationDateHour, int expirationDateMinute,
				boolean neverExpire,
				com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _commercePriceListService.addCommercePriceList(
			groupId, userId, commerceCurrencyCode, name, priority,
			displayDateMonth, displayDateDay, displayDateYear, displayDateHour,
			displayDateMinute, expirationDateMonth, expirationDateDay,
			expirationDateYear, expirationDateHour, expirationDateMinute,
			neverExpire, serviceContext);
	}

	@Override
	public com.liferay.commerce.price.list.model.CommercePriceList
			addCommercePriceList(
				long groupId, long userId, String commerceCurrencyCode,
				String name, double priority, int displayDateMonth,
				int displayDateDay, int displayDateYear, int displayDateHour,
				int displayDateMinute, int expirationDateMonth,
				int expirationDateDay, int expirationDateYear,
				int expirationDateHour, int expirationDateMinute,
				String externalReferenceCode, boolean neverExpire,
				com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _commercePriceListService.addCommercePriceList(
			groupId, userId, commerceCurrencyCode, name, priority,
			displayDateMonth, displayDateDay, displayDateYear, displayDateHour,
			displayDateMinute, expirationDateMonth, expirationDateDay,
			expirationDateYear, expirationDateHour, expirationDateMinute,
			externalReferenceCode, neverExpire, serviceContext);
	}

	@Override
	public void deleteCommercePriceList(long commercePriceListId)
		throws com.liferay.portal.kernel.exception.PortalException {

		_commercePriceListService.deleteCommercePriceList(commercePriceListId);
	}

	@Override
	public com.liferay.commerce.price.list.model.CommercePriceList
			fetchByExternalReferenceCode(
				long companyId, String externalReferenceCode)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _commercePriceListService.fetchByExternalReferenceCode(
			companyId, externalReferenceCode);
	}

	@Override
	public com.liferay.commerce.price.list.model.CommercePriceList
			fetchCommercePriceList(long commercePriceListId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _commercePriceListService.fetchCommercePriceList(
			commercePriceListId);
	}

	@Override
	public com.liferay.commerce.price.list.model.CommercePriceList
			getCommercePriceList(long commercePriceListId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _commercePriceListService.getCommercePriceList(
			commercePriceListId);
	}

	@Override
	public java.util.List
		<com.liferay.commerce.price.list.model.CommercePriceList>
				getCommercePriceLists(
					long companyId, int status, int start, int end,
					com.liferay.portal.kernel.util.OrderByComparator
						<com.liferay.commerce.price.list.model.
							CommercePriceList> orderByComparator)
			throws com.liferay.portal.kernel.exception.PortalException {

		return _commercePriceListService.getCommercePriceLists(
			companyId, status, start, end, orderByComparator);
	}

	@Override
	public int getCommercePriceListsCount(long companyId, int status)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _commercePriceListService.getCommercePriceListsCount(
			companyId, status);
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	@Override
	public String getOSGiServiceIdentifier() {
		return _commercePriceListService.getOSGiServiceIdentifier();
	}

	@Override
	public com.liferay.portal.kernel.search.BaseModelSearchResult
		<com.liferay.commerce.price.list.model.CommercePriceList>
				searchCommercePriceLists(
					long companyId, String keywords, int status, int start,
					int end, com.liferay.portal.kernel.search.Sort sort)
			throws com.liferay.portal.kernel.exception.PortalException {

		return _commercePriceListService.searchCommercePriceLists(
			companyId, keywords, status, start, end, sort);
	}

	@Override
	public com.liferay.commerce.price.list.model.CommercePriceList
			updateCommercePriceList(
				long commercePriceListId, String commerceCurrencyCode,
				long parentCommercePriceListId, String name, double priority,
				int displayDateMonth, int displayDateDay, int displayDateYear,
				int displayDateHour, int displayDateMinute,
				int expirationDateMonth, int expirationDateDay,
				int expirationDateYear, int expirationDateHour,
				int expirationDateMinute, boolean neverExpire,
				com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _commercePriceListService.updateCommercePriceList(
			commercePriceListId, commerceCurrencyCode,
			parentCommercePriceListId, name, priority, displayDateMonth,
			displayDateDay, displayDateYear, displayDateHour, displayDateMinute,
			expirationDateMonth, expirationDateDay, expirationDateYear,
			expirationDateHour, expirationDateMinute, neverExpire,
			serviceContext);
	}

	@Override
	public com.liferay.commerce.price.list.model.CommercePriceList
			updateCommercePriceList(
				long commercePriceListId, String commerceCurrencyCode,
				String name, double priority, int displayDateMonth,
				int displayDateDay, int displayDateYear, int displayDateHour,
				int displayDateMinute, int expirationDateMonth,
				int expirationDateDay, int expirationDateYear,
				int expirationDateHour, int expirationDateMinute,
				boolean neverExpire,
				com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _commercePriceListService.updateCommercePriceList(
			commercePriceListId, commerceCurrencyCode, name, priority,
			displayDateMonth, displayDateDay, displayDateYear, displayDateHour,
			displayDateMinute, expirationDateMonth, expirationDateDay,
			expirationDateYear, expirationDateHour, expirationDateMinute,
			neverExpire, serviceContext);
	}

	@Override
	public com.liferay.commerce.price.list.model.CommercePriceList
			updateExternalReferenceCode(
				com.liferay.commerce.price.list.model.CommercePriceList
					commercePriceList,
				long companyId, String externalReferenceCode)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _commercePriceListService.updateExternalReferenceCode(
			commercePriceList, companyId, externalReferenceCode);
	}

	@Override
	public com.liferay.commerce.price.list.model.CommercePriceList
			upsertCommercePriceList(
				long groupId, long userId, long commercePriceListId,
				String commerceCurrencyCode, long parentCommercePriceListId,
				String name, double priority, int displayDateMonth,
				int displayDateDay, int displayDateYear, int displayDateHour,
				int displayDateMinute, int expirationDateMonth,
				int expirationDateDay, int expirationDateYear,
				int expirationDateHour, int expirationDateMinute,
				String externalReferenceCode, boolean neverExpire,
				com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _commercePriceListService.upsertCommercePriceList(
			groupId, userId, commercePriceListId, commerceCurrencyCode,
			parentCommercePriceListId, name, priority, displayDateMonth,
			displayDateDay, displayDateYear, displayDateHour, displayDateMinute,
			expirationDateMonth, expirationDateDay, expirationDateYear,
			expirationDateHour, expirationDateMinute, externalReferenceCode,
			neverExpire, serviceContext);
	}

	@Override
	public com.liferay.commerce.price.list.model.CommercePriceList
			upsertCommercePriceList(
				long groupId, long userId, long commercePriceListId,
				String commerceCurrencyCode, String name, double priority,
				int displayDateMonth, int displayDateDay, int displayDateYear,
				int displayDateHour, int displayDateMinute,
				int expirationDateMonth, int expirationDateDay,
				int expirationDateYear, int expirationDateHour,
				int expirationDateMinute, String externalReferenceCode,
				boolean neverExpire,
				com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _commercePriceListService.upsertCommercePriceList(
			groupId, userId, commercePriceListId, commerceCurrencyCode, name,
			priority, displayDateMonth, displayDateDay, displayDateYear,
			displayDateHour, displayDateMinute, expirationDateMonth,
			expirationDateDay, expirationDateYear, expirationDateHour,
			expirationDateMinute, externalReferenceCode, neverExpire,
			serviceContext);
	}

	@Override
	public CommercePriceListService getWrappedService() {
		return _commercePriceListService;
	}

	@Override
	public void setWrappedService(
		CommercePriceListService commercePriceListService) {

		_commercePriceListService = commercePriceListService;
	}

	private CommercePriceListService _commercePriceListService;

}