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

import org.osgi.framework.Bundle;
import org.osgi.framework.FrameworkUtil;

import org.osgi.util.tracker.ServiceTracker;

/**
 * Provides the remote service utility for CommerceTierPriceEntry. This utility wraps
 * {@link com.liferay.commerce.price.list.service.impl.CommerceTierPriceEntryServiceImpl} and is the
 * primary access point for service operations in application layer code running
 * on a remote server. Methods of this service are expected to have security
 * checks based on the propagated JAAS credentials because this service can be
 * accessed remotely.
 *
 * @author Alessio Antonio Rendina
 * @see CommerceTierPriceEntryService
 * @see com.liferay.commerce.price.list.service.base.CommerceTierPriceEntryServiceBaseImpl
 * @see com.liferay.commerce.price.list.service.impl.CommerceTierPriceEntryServiceImpl
 * @generated
 */
@ProviderType
public class CommerceTierPriceEntryServiceUtil {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to {@link com.liferay.commerce.price.list.service.impl.CommerceTierPriceEntryServiceImpl} and rerun ServiceBuilder to regenerate this class.
	 */
	public static com.liferay.commerce.price.list.model.CommerceTierPriceEntry addCommerceTierPriceEntry(
		long commercePriceEntryId, java.math.BigDecimal price,
		java.math.BigDecimal promoPrice, int minQuantity,
		com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService()
				   .addCommerceTierPriceEntry(commercePriceEntryId, price,
			promoPrice, minQuantity, serviceContext);
	}

	public static com.liferay.commerce.price.list.model.CommerceTierPriceEntry addCommerceTierPriceEntry(
		long commercePriceEntryId, String externalReferenceCode,
		java.math.BigDecimal price, java.math.BigDecimal promoPrice,
		int minQuantity,
		com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService()
				   .addCommerceTierPriceEntry(commercePriceEntryId,
			externalReferenceCode, price, promoPrice, minQuantity,
			serviceContext);
	}

	public static void deleteCommerceTierPriceEntry(
		long commerceTierPriceEntryId)
		throws com.liferay.portal.kernel.exception.PortalException {
		getService().deleteCommerceTierPriceEntry(commerceTierPriceEntryId);
	}

	public static com.liferay.commerce.price.list.model.CommerceTierPriceEntry fetchByExternalReferenceCode(
		long companyId, String externalReferenceCode)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService()
				   .fetchByExternalReferenceCode(companyId,
			externalReferenceCode);
	}

	public static java.util.List<com.liferay.commerce.price.list.model.CommerceTierPriceEntry> fetchCommerceTierPriceEntries(
		long groupId, int start, int end) {
		return getService().fetchCommerceTierPriceEntries(groupId, start, end);
	}

	public static com.liferay.commerce.price.list.model.CommerceTierPriceEntry fetchCommerceTierPriceEntry(
		long commerceTierPriceEntryId) {
		return getService().fetchCommerceTierPriceEntry(commerceTierPriceEntryId);
	}

	public static java.util.List<com.liferay.commerce.price.list.model.CommerceTierPriceEntry> getCommerceTierPriceEntries(
		long commercePriceEntryId, int start, int end) {
		return getService()
				   .getCommerceTierPriceEntries(commercePriceEntryId, start, end);
	}

	public static java.util.List<com.liferay.commerce.price.list.model.CommerceTierPriceEntry> getCommerceTierPriceEntries(
		long commercePriceEntryId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<com.liferay.commerce.price.list.model.CommerceTierPriceEntry> orderByComparator) {
		return getService()
				   .getCommerceTierPriceEntries(commercePriceEntryId, start,
			end, orderByComparator);
	}

	public static int getCommerceTierPriceEntriesCount(
		long commercePriceEntryId) {
		return getService()
				   .getCommerceTierPriceEntriesCount(commercePriceEntryId);
	}

	public static int getCommerceTierPriceEntriesCountByGroupId(long groupId) {
		return getService().getCommerceTierPriceEntriesCountByGroupId(groupId);
	}

	/**
	* Returns the OSGi service identifier.
	*
	* @return the OSGi service identifier
	*/
	public static String getOSGiServiceIdentifier() {
		return getService().getOSGiServiceIdentifier();
	}

	public static com.liferay.portal.kernel.search.Hits search(
		com.liferay.portal.kernel.search.SearchContext searchContext) {
		return getService().search(searchContext);
	}

	public static com.liferay.portal.kernel.search.BaseModelSearchResult<com.liferay.commerce.price.list.model.CommerceTierPriceEntry> searchCommerceTierPriceEntries(
		long companyId, long groupId, long commercePriceEntryId,
		String keywords, int start, int end,
		com.liferay.portal.kernel.search.Sort sort)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService()
				   .searchCommerceTierPriceEntries(companyId, groupId,
			commercePriceEntryId, keywords, start, end, sort);
	}

	public static com.liferay.commerce.price.list.model.CommerceTierPriceEntry updateCommerceTierPriceEntry(
		long commerceTierPriceEntryId, java.math.BigDecimal price,
		java.math.BigDecimal promoPrice, int minQuantity,
		com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService()
				   .updateCommerceTierPriceEntry(commerceTierPriceEntryId,
			price, promoPrice, minQuantity, serviceContext);
	}

	public static com.liferay.commerce.price.list.model.CommerceTierPriceEntry updateExternalReferenceCode(
		com.liferay.commerce.price.list.model.CommerceTierPriceEntry commerceTierPriceEntry,
		long groupId, String externalReferenceCode)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService()
				   .updateExternalReferenceCode(commerceTierPriceEntry,
			groupId, externalReferenceCode);
	}

	public static com.liferay.commerce.price.list.model.CommerceTierPriceEntry upsertCommerceTierPriceEntry(
		long commerceTierPriceEntryId, long commercePriceEntryId,
		String externalReferenceCode, java.math.BigDecimal price,
		java.math.BigDecimal promoPrice, int minQuantity,
		String priceEntryExternalReferenceCode,
		com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService()
				   .upsertCommerceTierPriceEntry(commerceTierPriceEntryId,
			commercePriceEntryId, externalReferenceCode, price, promoPrice,
			minQuantity, priceEntryExternalReferenceCode, serviceContext);
	}

	public static CommerceTierPriceEntryService getService() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker<CommerceTierPriceEntryService, CommerceTierPriceEntryService> _serviceTracker;

	static {
		Bundle bundle = FrameworkUtil.getBundle(CommerceTierPriceEntryService.class);

		ServiceTracker<CommerceTierPriceEntryService, CommerceTierPriceEntryService> serviceTracker =
			new ServiceTracker<CommerceTierPriceEntryService, CommerceTierPriceEntryService>(bundle.getBundleContext(),
				CommerceTierPriceEntryService.class, null);

		serviceTracker.open();

		_serviceTracker = serviceTracker;
	}
}