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

import aQute.bnd.annotation.ProviderType;

import com.liferay.portal.kernel.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link CommerceTierPriceEntryService}.
 *
 * @author Alessio Antonio Rendina
 * @see CommerceTierPriceEntryService
 * @generated
 */
@ProviderType
public class CommerceTierPriceEntryServiceWrapper
	implements CommerceTierPriceEntryService,
			   ServiceWrapper<CommerceTierPriceEntryService> {

	public CommerceTierPriceEntryServiceWrapper(
		CommerceTierPriceEntryService commerceTierPriceEntryService) {

		_commerceTierPriceEntryService = commerceTierPriceEntryService;
	}

	@Override
	public com.liferay.commerce.price.list.model.CommerceTierPriceEntry
			addCommerceTierPriceEntry(
				long commercePriceEntryId, java.math.BigDecimal price,
				java.math.BigDecimal promoPrice, int minQuantity,
				com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _commerceTierPriceEntryService.addCommerceTierPriceEntry(
			commercePriceEntryId, price, promoPrice, minQuantity,
			serviceContext);
	}

	@Override
	public com.liferay.commerce.price.list.model.CommerceTierPriceEntry
			addCommerceTierPriceEntry(
				long commercePriceEntryId, String externalReferenceCode,
				java.math.BigDecimal price, java.math.BigDecimal promoPrice,
				int minQuantity,
				com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _commerceTierPriceEntryService.addCommerceTierPriceEntry(
			commercePriceEntryId, externalReferenceCode, price, promoPrice,
			minQuantity, serviceContext);
	}

	@Override
	public void deleteCommerceTierPriceEntry(long commerceTierPriceEntryId)
		throws com.liferay.portal.kernel.exception.PortalException {

		_commerceTierPriceEntryService.deleteCommerceTierPriceEntry(
			commerceTierPriceEntryId);
	}

	@Override
	public com.liferay.commerce.price.list.model.CommerceTierPriceEntry
			fetchByExternalReferenceCode(
				long companyId, String externalReferenceCode)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _commerceTierPriceEntryService.fetchByExternalReferenceCode(
			companyId, externalReferenceCode);
	}

	@Override
	public java.util.List
		<com.liferay.commerce.price.list.model.CommerceTierPriceEntry>
				fetchCommerceTierPriceEntries(
					long companyId, int start, int end)
			throws com.liferay.portal.kernel.exception.PortalException {

		return _commerceTierPriceEntryService.fetchCommerceTierPriceEntries(
			companyId, start, end);
	}

	@Override
	public com.liferay.commerce.price.list.model.CommerceTierPriceEntry
			fetchCommerceTierPriceEntry(long commerceTierPriceEntryId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _commerceTierPriceEntryService.fetchCommerceTierPriceEntry(
			commerceTierPriceEntryId);
	}

	@Override
	public java.util.List
		<com.liferay.commerce.price.list.model.CommerceTierPriceEntry>
				getCommerceTierPriceEntries(
					long commercePriceEntryId, int start, int end)
			throws com.liferay.portal.kernel.exception.PortalException {

		return _commerceTierPriceEntryService.getCommerceTierPriceEntries(
			commercePriceEntryId, start, end);
	}

	@Override
	public java.util.List
		<com.liferay.commerce.price.list.model.CommerceTierPriceEntry>
				getCommerceTierPriceEntries(
					long commercePriceEntryId, int start, int end,
					com.liferay.portal.kernel.util.OrderByComparator
						<com.liferay.commerce.price.list.model.
							CommerceTierPriceEntry> orderByComparator)
			throws com.liferay.portal.kernel.exception.PortalException {

		return _commerceTierPriceEntryService.getCommerceTierPriceEntries(
			commercePriceEntryId, start, end, orderByComparator);
	}

	@Override
	public int getCommerceTierPriceEntriesCount(long commercePriceEntryId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _commerceTierPriceEntryService.getCommerceTierPriceEntriesCount(
			commercePriceEntryId);
	}

	@Override
	public int getCommerceTierPriceEntriesCountByCompanyId(long companyId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _commerceTierPriceEntryService.
			getCommerceTierPriceEntriesCountByCompanyId(companyId);
	}

	@Override
	public com.liferay.commerce.price.list.model.CommerceTierPriceEntry
			getCommerceTierPriceEntry(long commerceTierPriceEntryId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _commerceTierPriceEntryService.getCommerceTierPriceEntry(
			commerceTierPriceEntryId);
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	@Override
	public String getOSGiServiceIdentifier() {
		return _commerceTierPriceEntryService.getOSGiServiceIdentifier();
	}

	@Override
	public com.liferay.portal.kernel.search.BaseModelSearchResult
		<com.liferay.commerce.price.list.model.CommerceTierPriceEntry>
				searchCommerceTierPriceEntries(
					long companyId, long commercePriceEntryId, String keywords,
					int start, int end,
					com.liferay.portal.kernel.search.Sort sort)
			throws com.liferay.portal.kernel.exception.PortalException {

		return _commerceTierPriceEntryService.searchCommerceTierPriceEntries(
			companyId, commercePriceEntryId, keywords, start, end, sort);
	}

	@Override
	public com.liferay.commerce.price.list.model.CommerceTierPriceEntry
			updateCommerceTierPriceEntry(
				long commerceTierPriceEntryId, java.math.BigDecimal price,
				java.math.BigDecimal promoPrice, int minQuantity,
				com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _commerceTierPriceEntryService.updateCommerceTierPriceEntry(
			commerceTierPriceEntryId, price, promoPrice, minQuantity,
			serviceContext);
	}

	@Override
	public com.liferay.commerce.price.list.model.CommerceTierPriceEntry
			updateExternalReferenceCode(
				com.liferay.commerce.price.list.model.CommerceTierPriceEntry
					commerceTierPriceEntry,
				String externalReferenceCode)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _commerceTierPriceEntryService.updateExternalReferenceCode(
			commerceTierPriceEntry, externalReferenceCode);
	}

	@Override
	public com.liferay.commerce.price.list.model.CommerceTierPriceEntry
			upsertCommerceTierPriceEntry(
				long commerceTierPriceEntryId, long commercePriceEntryId,
				String externalReferenceCode, java.math.BigDecimal price,
				java.math.BigDecimal promoPrice, int minQuantity,
				String priceEntryExternalReferenceCode,
				com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _commerceTierPriceEntryService.upsertCommerceTierPriceEntry(
			commerceTierPriceEntryId, commercePriceEntryId,
			externalReferenceCode, price, promoPrice, minQuantity,
			priceEntryExternalReferenceCode, serviceContext);
	}

	@Override
	public CommerceTierPriceEntryService getWrappedService() {
		return _commerceTierPriceEntryService;
	}

	@Override
	public void setWrappedService(
		CommerceTierPriceEntryService commerceTierPriceEntryService) {

		_commerceTierPriceEntryService = commerceTierPriceEntryService;
	}

	private CommerceTierPriceEntryService _commerceTierPriceEntryService;

}