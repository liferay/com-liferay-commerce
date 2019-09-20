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
 * Provides a wrapper for {@link CommercePriceEntryService}.
 *
 * @author Alessio Antonio Rendina
 * @see CommercePriceEntryService
 * @generated
 */
public class CommercePriceEntryServiceWrapper
	implements CommercePriceEntryService,
			   ServiceWrapper<CommercePriceEntryService> {

	public CommercePriceEntryServiceWrapper(
		CommercePriceEntryService commercePriceEntryService) {

		_commercePriceEntryService = commercePriceEntryService;
	}

	/**
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link CommercePriceEntryServiceUtil} to access the commerce price entry remote service. Add custom service methods to <code>com.liferay.commerce.price.list.service.impl.CommercePriceEntryServiceImpl</code> and rerun ServiceBuilder to automatically copy the method declarations to this interface.
	 */
	@Override
	public com.liferay.commerce.price.list.model.CommercePriceEntry
			addCommercePriceEntry(
				long cpInstanceId, long commercePriceListId,
				java.math.BigDecimal price, java.math.BigDecimal promoPrice,
				com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _commercePriceEntryService.addCommercePriceEntry(
			cpInstanceId, commercePriceListId, price, promoPrice,
			serviceContext);
	}

	@Override
	public com.liferay.commerce.price.list.model.CommercePriceEntry
			addCommercePriceEntry(
				long cpInstanceId, long commercePriceListId,
				String externalReferenceCode, java.math.BigDecimal price,
				java.math.BigDecimal promoPrice,
				com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _commercePriceEntryService.addCommercePriceEntry(
			cpInstanceId, commercePriceListId, externalReferenceCode, price,
			promoPrice, serviceContext);
	}

	@Override
	public void deleteCommercePriceEntry(long commercePriceEntryId)
		throws com.liferay.portal.kernel.exception.PortalException {

		_commercePriceEntryService.deleteCommercePriceEntry(
			commercePriceEntryId);
	}

	@Override
	public com.liferay.commerce.price.list.model.CommercePriceEntry
			fetchByExternalReferenceCode(
				long companyId, String externalReferenceCode)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _commercePriceEntryService.fetchByExternalReferenceCode(
			companyId, externalReferenceCode);
	}

	@Override
	public com.liferay.commerce.price.list.model.CommercePriceEntry
			fetchCommercePriceEntry(long commercePriceEntryId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _commercePriceEntryService.fetchCommercePriceEntry(
			commercePriceEntryId);
	}

	@Override
	public java.util.List
		<com.liferay.commerce.price.list.model.CommercePriceEntry>
				getCommercePriceEntries(
					long commercePriceListId, int start, int end)
			throws com.liferay.portal.kernel.exception.PortalException {

		return _commercePriceEntryService.getCommercePriceEntries(
			commercePriceListId, start, end);
	}

	@Override
	public java.util.List
		<com.liferay.commerce.price.list.model.CommercePriceEntry>
				getCommercePriceEntries(
					long commercePriceListId, int start, int end,
					com.liferay.portal.kernel.util.OrderByComparator
						<com.liferay.commerce.price.list.model.
							CommercePriceEntry> orderByComparator)
			throws com.liferay.portal.kernel.exception.PortalException {

		return _commercePriceEntryService.getCommercePriceEntries(
			commercePriceListId, start, end, orderByComparator);
	}

	@Override
	public java.util.List
		<com.liferay.commerce.price.list.model.CommercePriceEntry>
				getCommercePriceEntriesByCompanyId(
					long companyId, int start, int end)
			throws com.liferay.portal.kernel.exception.PortalException {

		return _commercePriceEntryService.getCommercePriceEntriesByCompanyId(
			companyId, start, end);
	}

	@Override
	public int getCommercePriceEntriesCount(long commercePriceListId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _commercePriceEntryService.getCommercePriceEntriesCount(
			commercePriceListId);
	}

	@Override
	public int getCommercePriceEntriesCountByCompanyId(long companyId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _commercePriceEntryService.
			getCommercePriceEntriesCountByCompanyId(companyId);
	}

	@Override
	public com.liferay.commerce.price.list.model.CommercePriceEntry
			getCommercePriceEntry(long commercePriceEntryId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _commercePriceEntryService.getCommercePriceEntry(
			commercePriceEntryId);
	}

	@Override
	public java.util.List
		<com.liferay.commerce.price.list.model.CommercePriceEntry>
				getInstanceCommercePriceEntries(
					long cpInstanceId, int start, int end)
			throws com.liferay.portal.kernel.exception.PortalException {

		return _commercePriceEntryService.getInstanceCommercePriceEntries(
			cpInstanceId, start, end);
	}

	@Override
	public java.util.List
		<com.liferay.commerce.price.list.model.CommercePriceEntry>
				getInstanceCommercePriceEntries(
					long cpInstanceId, int start, int end,
					com.liferay.portal.kernel.util.OrderByComparator
						<com.liferay.commerce.price.list.model.
							CommercePriceEntry> orderByComparator)
			throws com.liferay.portal.kernel.exception.PortalException {

		return _commercePriceEntryService.getInstanceCommercePriceEntries(
			cpInstanceId, start, end, orderByComparator);
	}

	@Override
	public int getInstanceCommercePriceEntriesCount(long cpInstanceId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _commercePriceEntryService.getInstanceCommercePriceEntriesCount(
			cpInstanceId);
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	@Override
	public String getOSGiServiceIdentifier() {
		return _commercePriceEntryService.getOSGiServiceIdentifier();
	}

	@Override
	public com.liferay.portal.kernel.search.BaseModelSearchResult
		<com.liferay.commerce.price.list.model.CommercePriceEntry>
				searchCommercePriceEntries(
					long companyId, long commercePriceListId, String keywords,
					int start, int end,
					com.liferay.portal.kernel.search.Sort sort)
			throws com.liferay.portal.kernel.exception.PortalException {

		return _commercePriceEntryService.searchCommercePriceEntries(
			companyId, commercePriceListId, keywords, start, end, sort);
	}

	@Override
	public com.liferay.commerce.price.list.model.CommercePriceEntry
			updateCommercePriceEntry(
				long commercePriceEntryId, java.math.BigDecimal price,
				java.math.BigDecimal promoPrice,
				com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _commercePriceEntryService.updateCommercePriceEntry(
			commercePriceEntryId, price, promoPrice, serviceContext);
	}

	@Override
	public com.liferay.commerce.price.list.model.CommercePriceEntry
			updateExternalReferenceCode(
				com.liferay.commerce.price.list.model.CommercePriceEntry
					commercePriceEntry,
				String externalReferenceCode)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _commercePriceEntryService.updateExternalReferenceCode(
			commercePriceEntry, externalReferenceCode);
	}

	/**
	 * @deprecated As of Mueller (7.2.x)
	 */
	@Deprecated
	@Override
	public com.liferay.commerce.price.list.model.CommercePriceEntry
			upsertCommercePriceEntry(
				long commercePriceEntryId, long cpInstanceId,
				long commercePriceListId, String externalReferenceCode,
				java.math.BigDecimal price, java.math.BigDecimal promoPrice,
				String skuExternalReferenceCode,
				com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _commercePriceEntryService.upsertCommercePriceEntry(
			commercePriceEntryId, cpInstanceId, commercePriceListId,
			externalReferenceCode, price, promoPrice, skuExternalReferenceCode,
			serviceContext);
	}

	@Override
	public com.liferay.commerce.price.list.model.CommercePriceEntry
			upsertCommercePriceEntry(
				long commercePriceEntryId, long cProductId,
				String cpInstanceUuid, long commercePriceListId,
				String externalReferenceCode, java.math.BigDecimal price,
				java.math.BigDecimal promoPrice,
				String skuExternalReferenceCode,
				com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _commercePriceEntryService.upsertCommercePriceEntry(
			commercePriceEntryId, cProductId, cpInstanceUuid,
			commercePriceListId, externalReferenceCode, price, promoPrice,
			skuExternalReferenceCode, serviceContext);
	}

	@Override
	public CommercePriceEntryService getWrappedService() {
		return _commercePriceEntryService;
	}

	@Override
	public void setWrappedService(
		CommercePriceEntryService commercePriceEntryService) {

		_commercePriceEntryService = commercePriceEntryService;
	}

	private CommercePriceEntryService _commercePriceEntryService;

}