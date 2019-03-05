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

package com.liferay.commerce.price.list.service.impl;

import com.liferay.commerce.price.list.constants.CommercePriceListActionKeys;
import com.liferay.commerce.price.list.constants.CommercePriceListConstants;
import com.liferay.commerce.price.list.model.CommercePriceEntry;
import com.liferay.commerce.price.list.model.CommerceTierPriceEntry;
import com.liferay.commerce.price.list.service.base.CommerceTierPriceEntryServiceBaseImpl;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.search.BaseModelSearchResult;
import com.liferay.portal.kernel.search.Sort;
import com.liferay.portal.kernel.security.permission.resource.PortletResourcePermission;
import com.liferay.portal.kernel.security.permission.resource.PortletResourcePermissionFactory;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.OrderByComparator;

import java.math.BigDecimal;

import java.util.List;

/**
 * @author Alessio Antonio Rendina
 * @author Zoltán Takács
 */
public class CommerceTierPriceEntryServiceImpl
	extends CommerceTierPriceEntryServiceBaseImpl {

	@Override
	public CommerceTierPriceEntry addCommerceTierPriceEntry(
			long commercePriceEntryId, BigDecimal price, BigDecimal promoPrice,
			int minQuantity, ServiceContext serviceContext)
		throws PortalException {

		return addCommerceTierPriceEntry(
			commercePriceEntryId, null, price, promoPrice, minQuantity,
			serviceContext);
	}

	@Override
	public CommerceTierPriceEntry addCommerceTierPriceEntry(
			long commercePriceEntryId, String externalReferenceCode,
			BigDecimal price, BigDecimal promoPrice, int minQuantity,
			ServiceContext serviceContext)
		throws PortalException {

		_portletResourcePermission.check(
			getPermissionChecker(), serviceContext.getScopeGroupId(),
			CommercePriceListActionKeys.MANAGE_COMMERCE_PRICE_LISTS);

		return commerceTierPriceEntryLocalService.addCommerceTierPriceEntry(
			commercePriceEntryId, externalReferenceCode, price, promoPrice,
			minQuantity, serviceContext);
	}

	@Override
	public void deleteCommerceTierPriceEntry(long commerceTierPriceEntryId)
		throws PortalException {

		CommerceTierPriceEntry commerceTierPriceEntry =
			commerceTierPriceEntryLocalService.fetchCommerceTierPriceEntry(
				commerceTierPriceEntryId);

		if (commerceTierPriceEntry != null) {
			_portletResourcePermission.check(
				getPermissionChecker(), commerceTierPriceEntry.getGroupId(),
				CommercePriceListActionKeys.MANAGE_COMMERCE_PRICE_LISTS);
		}

		commerceTierPriceEntryLocalService.deleteCommerceTierPriceEntry(
			commerceTierPriceEntryId);
	}

	@Override
	public CommerceTierPriceEntry fetchByExternalReferenceCode(
			long companyId, String externalReferenceCode)
		throws PortalException {

		CommerceTierPriceEntry commerceTierPriceEntry =
			commerceTierPriceEntryLocalService.fetchByExternalReferenceCode(
				companyId, externalReferenceCode);

		if (commerceTierPriceEntry != null) {
			_portletResourcePermission.check(
				getPermissionChecker(), commerceTierPriceEntry.getGroupId(),
				CommercePriceListActionKeys.MANAGE_COMMERCE_PRICE_LISTS);
		}

		return commerceTierPriceEntry;
	}

	@Override
	public List<CommerceTierPriceEntry> fetchCommerceTierPriceEntries(
			long groupId, int start, int end)
		throws PortalException {

		_portletResourcePermission.check(
			getPermissionChecker(), groupId,
			CommercePriceListActionKeys.MANAGE_COMMERCE_PRICE_LISTS);

		return commerceTierPriceEntryLocalService.fetchCommerceTierPriceEntries(
			groupId, start, end);
	}

	@Override
	public CommerceTierPriceEntry fetchCommerceTierPriceEntry(
			long commerceTierPriceEntryId)
		throws PortalException {

		CommerceTierPriceEntry commerceTierPriceEntry =
			commerceTierPriceEntryLocalService.fetchCommerceTierPriceEntry(
				commerceTierPriceEntryId);

		if (commerceTierPriceEntry != null) {
			_portletResourcePermission.check(
				getPermissionChecker(), commerceTierPriceEntry.getGroupId(),
				CommercePriceListActionKeys.MANAGE_COMMERCE_PRICE_LISTS);
		}

		return commerceTierPriceEntry;
	}

	@Override
	public List<CommerceTierPriceEntry> getCommerceTierPriceEntries(
			long commercePriceEntryId, int start, int end)
		throws PortalException {

		CommercePriceEntry commercePriceEntry =
			commercePriceEntryLocalService.getCommercePriceEntry(
				commercePriceEntryId);

		_portletResourcePermission.check(
			getPermissionChecker(), commercePriceEntry.getGroupId(),
			CommercePriceListActionKeys.MANAGE_COMMERCE_PRICE_LISTS);

		return commerceTierPriceEntryLocalService.getCommerceTierPriceEntries(
			commercePriceEntryId, start, end);
	}

	@Override
	public List<CommerceTierPriceEntry> getCommerceTierPriceEntries(
			long commercePriceEntryId, int start, int end,
			OrderByComparator<CommerceTierPriceEntry> orderByComparator)
		throws PortalException {

		CommercePriceEntry commercePriceEntry =
			commercePriceEntryLocalService.getCommercePriceEntry(
				commercePriceEntryId);

		_portletResourcePermission.check(
			getPermissionChecker(), commercePriceEntry.getGroupId(),
			CommercePriceListActionKeys.MANAGE_COMMERCE_PRICE_LISTS);

		return commerceTierPriceEntryLocalService.getCommerceTierPriceEntries(
			commercePriceEntryId, start, end, orderByComparator);
	}

	@Override
	public int getCommerceTierPriceEntriesCount(long commercePriceEntryId)
		throws PortalException {

		CommercePriceEntry commercePriceEntry =
			commercePriceEntryLocalService.getCommercePriceEntry(
				commercePriceEntryId);

		_portletResourcePermission.check(
			getPermissionChecker(), commercePriceEntry.getGroupId(),
			CommercePriceListActionKeys.MANAGE_COMMERCE_PRICE_LISTS);

		return commerceTierPriceEntryLocalService.
			getCommerceTierPriceEntriesCount(commercePriceEntryId);
	}

	@Override
	public int getCommerceTierPriceEntriesCountByGroupId(long groupId)
		throws PortalException {

		_portletResourcePermission.check(
			getPermissionChecker(), groupId,
			CommercePriceListActionKeys.MANAGE_COMMERCE_PRICE_LISTS);

		return commerceTierPriceEntryLocalService.
			getCommerceTierPriceEntriesCountByGroupId(groupId);
	}

	@Override
	public BaseModelSearchResult<CommerceTierPriceEntry>
			searchCommerceTierPriceEntries(
				long companyId, long groupId, long commercePriceEntryId,
				String keywords, int start, int end, Sort sort)
		throws PortalException {

		_portletResourcePermission.check(
			getPermissionChecker(), groupId,
			CommercePriceListActionKeys.MANAGE_COMMERCE_PRICE_LISTS);

		return commerceTierPriceEntryLocalService.
			searchCommerceTierPriceEntries(
				companyId, groupId, commercePriceEntryId, keywords, start, end,
				sort);
	}

	@Override
	public CommerceTierPriceEntry updateCommerceTierPriceEntry(
			long commerceTierPriceEntryId, BigDecimal price,
			BigDecimal promoPrice, int minQuantity,
			ServiceContext serviceContext)
		throws PortalException {

		_portletResourcePermission.check(
			getPermissionChecker(), serviceContext.getScopeGroupId(),
			CommercePriceListActionKeys.MANAGE_COMMERCE_PRICE_LISTS);

		return commerceTierPriceEntryLocalService.updateCommerceTierPriceEntry(
			commerceTierPriceEntryId, price, promoPrice, minQuantity,
			serviceContext);
	}

	@Override
	public CommerceTierPriceEntry updateExternalReferenceCode(
			CommerceTierPriceEntry commerceTierPriceEntry, long groupId,
			String externalReferenceCode)
		throws PortalException {

		_portletResourcePermission.check(
			getPermissionChecker(), groupId,
			CommercePriceListActionKeys.MANAGE_COMMERCE_PRICE_LISTS);

		return commerceTierPriceEntryLocalService.updateExternalReferenceCode(
			commerceTierPriceEntry, externalReferenceCode);
	}

	@Override
	public CommerceTierPriceEntry upsertCommerceTierPriceEntry(
			long commerceTierPriceEntryId, long commercePriceEntryId,
			String externalReferenceCode, BigDecimal price,
			BigDecimal promoPrice, int minQuantity,
			String priceEntryExternalReferenceCode,
			ServiceContext serviceContext)
		throws PortalException {

		_portletResourcePermission.check(
			getPermissionChecker(), serviceContext.getScopeGroupId(),
			CommercePriceListActionKeys.MANAGE_COMMERCE_PRICE_LISTS);

		return commerceTierPriceEntryLocalService.upsertCommerceTierPriceEntry(
			commerceTierPriceEntryId, commercePriceEntryId,
			externalReferenceCode, price, promoPrice, minQuantity,
			priceEntryExternalReferenceCode, serviceContext);
	}

	private static volatile PortletResourcePermission
		_portletResourcePermission =
			PortletResourcePermissionFactory.getInstance(
				CommerceTierPriceEntryServiceImpl.class,
				"_portletResourcePermission",
				CommercePriceListConstants.RESOURCE_NAME);

}