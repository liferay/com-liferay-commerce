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
import com.liferay.commerce.price.list.model.CommercePriceList;
import com.liferay.commerce.price.list.service.base.CommercePriceEntryServiceBaseImpl;
import com.liferay.commerce.product.model.CPInstance;
import com.liferay.commerce.product.service.CPInstanceLocalService;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.search.BaseModelSearchResult;
import com.liferay.portal.kernel.search.Sort;
import com.liferay.portal.kernel.security.permission.resource.PortletResourcePermission;
import com.liferay.portal.kernel.security.permission.resource.PortletResourcePermissionFactory;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.spring.extender.service.ServiceReference;

import java.math.BigDecimal;

import java.util.List;

/**
 * @author Marco Leo
 * @author Alessio Antonio Rendina
 * @author Zoltán Takács
 */
public class CommercePriceEntryServiceImpl
	extends CommercePriceEntryServiceBaseImpl {

	@Override
	public CommercePriceEntry addCommercePriceEntry(
			long cpInstanceId, long commercePriceListId, BigDecimal price,
			BigDecimal promoPrice, ServiceContext serviceContext)
		throws PortalException {

		return addCommercePriceEntry(
			cpInstanceId, commercePriceListId, null, price, promoPrice,
			serviceContext);
	}

	@Override
	public CommercePriceEntry addCommercePriceEntry(
			long cpInstanceId, long commercePriceListId,
			String externalReferenceCode, BigDecimal price,
			BigDecimal promoPrice, ServiceContext serviceContext)
		throws PortalException {

		_portletResourcePermission.check(
			getPermissionChecker(), serviceContext.getScopeGroupId(),
			CommercePriceListActionKeys.MANAGE_COMMERCE_PRICE_LISTS);

		return commercePriceEntryLocalService.addCommercePriceEntry(
			cpInstanceId, commercePriceListId, externalReferenceCode, price,
			promoPrice, serviceContext);
	}

	@Override
	public void deleteCommercePriceEntry(long commercePriceEntryId)
		throws PortalException {

		CommercePriceEntry commercePriceEntry =
			commercePriceEntryLocalService.fetchCommercePriceEntry(
				commercePriceEntryId);

		if (commercePriceEntry != null) {
			_portletResourcePermission.check(
				getPermissionChecker(), commercePriceEntry.getGroupId(),
				CommercePriceListActionKeys.MANAGE_COMMERCE_PRICE_LISTS);
		}

		commercePriceEntryLocalService.deleteCommercePriceEntry(
			commercePriceEntryId);
	}

	@Override
	public CommercePriceEntry fetchByExternalReferenceCode(
			long companyId, String externalReferenceCode)
		throws PortalException {

		CommercePriceEntry commercePriceList =
			commercePriceEntryLocalService.fetchByExternalReferenceCode(
				companyId, externalReferenceCode);

		if (commercePriceList != null) {
			_portletResourcePermission.check(
				getPermissionChecker(), commercePriceList.getGroupId(),
				CommercePriceListActionKeys.MANAGE_COMMERCE_PRICE_LISTS);
		}

		return commercePriceList;
	}

	@Override
	public CommercePriceEntry fetchCommercePriceEntry(long commercePriceEntryId)
		throws PortalException {

		CommercePriceEntry commercePriceEntry =
			commercePriceEntryLocalService.fetchCommercePriceEntry(
				commercePriceEntryId);

		if (commercePriceEntry != null) {
			_portletResourcePermission.check(
				getPermissionChecker(), commercePriceEntry.getGroupId(),
				CommercePriceListActionKeys.MANAGE_COMMERCE_PRICE_LISTS);
		}

		return commercePriceEntry;
	}

	@Override
	public List<CommercePriceEntry> getCommercePriceEntries(
			long commercePriceListId, int start, int end)
		throws PortalException {

		CommercePriceList commercePriceList =
			commercePriceListLocalService.getCommercePriceList(
				commercePriceListId);

		_portletResourcePermission.check(
			getPermissionChecker(), commercePriceList.getGroupId(),
			CommercePriceListActionKeys.MANAGE_COMMERCE_PRICE_LISTS);

		return commercePriceEntryLocalService.getCommercePriceEntries(
			commercePriceListId, start, end);
	}

	@Override
	public List<CommercePriceEntry> getCommercePriceEntries(
			long commercePriceListId, int start, int end,
			OrderByComparator<CommercePriceEntry> orderByComparator)
		throws PortalException {

		CommercePriceList commercePriceList =
			commercePriceListLocalService.getCommercePriceList(
				commercePriceListId);

		_portletResourcePermission.check(
			getPermissionChecker(), commercePriceList.getGroupId(),
			CommercePriceListActionKeys.MANAGE_COMMERCE_PRICE_LISTS);

		return commercePriceEntryLocalService.getCommercePriceEntries(
			commercePriceListId, start, end, orderByComparator);
	}

	@Override
	public List<CommercePriceEntry> getCommercePriceEntriesByGroupId(
			long groupId, int start, int end)
		throws PortalException {

		_portletResourcePermission.check(
			getPermissionChecker(), groupId,
			CommercePriceListActionKeys.MANAGE_COMMERCE_PRICE_LISTS);

		return commercePriceEntryLocalService.getCommercePriceEntriesByGroupId(
			groupId, start, end);
	}

	@Override
	public int getCommercePriceEntriesCount(long commercePriceListId)
		throws PortalException {

		CommercePriceList commercePriceList =
			commercePriceListLocalService.getCommercePriceList(
				commercePriceListId);

		_portletResourcePermission.check(
			getPermissionChecker(), commercePriceList.getGroupId(),
			CommercePriceListActionKeys.MANAGE_COMMERCE_PRICE_LISTS);

		return commercePriceEntryLocalService.getCommercePriceEntriesCount(
			commercePriceListId);
	}

	@Override
	public int getCommercePriceEntriesCountByGroupId(long groupId)
		throws PortalException {

		_portletResourcePermission.check(
			getPermissionChecker(), groupId,
			CommercePriceListActionKeys.MANAGE_COMMERCE_PRICE_LISTS);

		return commercePriceEntryLocalService.
			getCommercePriceEntriesCountByGroupId(groupId);
	}

	@Override
	public List<CommercePriceEntry> getInstanceCommercePriceEntries(
			long cpInstanceId, int start, int end)
		throws PortalException {

		CPInstance cpInstance = _cpInstanceLocalService.getCPInstance(
			cpInstanceId);

		_portletResourcePermission.check(
			getPermissionChecker(), cpInstance.getGroupId(),
			CommercePriceListActionKeys.MANAGE_COMMERCE_PRICE_LISTS);

		return commercePriceEntryLocalService.getInstanceCommercePriceEntries(
			cpInstanceId, start, end);
	}

	@Override
	public List<CommercePriceEntry> getInstanceCommercePriceEntries(
			long cpInstanceId, int start, int end,
			OrderByComparator<CommercePriceEntry> orderByComparator)
		throws PortalException {

		CPInstance cpInstance = _cpInstanceLocalService.getCPInstance(
			cpInstanceId);

		_portletResourcePermission.check(
			getPermissionChecker(), cpInstance.getGroupId(),
			CommercePriceListActionKeys.MANAGE_COMMERCE_PRICE_LISTS);

		return commercePriceEntryLocalService.getInstanceCommercePriceEntries(
			cpInstanceId, start, end, orderByComparator);
	}

	@Override
	public int getInstanceCommercePriceEntriesCount(long cpInstanceId)
		throws PortalException {

		CPInstance cpInstance = _cpInstanceLocalService.getCPInstance(
			cpInstanceId);

		_portletResourcePermission.check(
			getPermissionChecker(), cpInstance.getGroupId(),
			CommercePriceListActionKeys.MANAGE_COMMERCE_PRICE_LISTS);

		return commercePriceEntryLocalService.
			getInstanceCommercePriceEntriesCount(cpInstanceId);
	}

	@Override
	public BaseModelSearchResult<CommercePriceEntry> searchCommercePriceEntries(
			long companyId, long groupId, long commercePriceListId,
			String keywords, int start, int end, Sort sort)
		throws PortalException {

		_portletResourcePermission.check(
			getPermissionChecker(), groupId,
			CommercePriceListActionKeys.MANAGE_COMMERCE_PRICE_LISTS);

		return commercePriceEntryLocalService.searchCommercePriceEntries(
			companyId, groupId, commercePriceListId, keywords, start, end,
			sort);
	}

	@Override
	public CommercePriceEntry updateCommercePriceEntry(
			long commercePriceEntryId, BigDecimal price, BigDecimal promoPrice,
			ServiceContext serviceContext)
		throws PortalException {

		_portletResourcePermission.check(
			getPermissionChecker(), serviceContext.getScopeGroupId(),
			CommercePriceListActionKeys.MANAGE_COMMERCE_PRICE_LISTS);

		return commercePriceEntryLocalService.updateCommercePriceEntry(
			commercePriceEntryId, price, promoPrice, serviceContext);
	}

	@Override
	public CommercePriceEntry updateExternalReferenceCode(
			CommercePriceEntry commercePriceEntry, long groupId,
			String externalReferenceCode)
		throws PortalException {

		_portletResourcePermission.check(
			getPermissionChecker(), groupId,
			CommercePriceListActionKeys.MANAGE_COMMERCE_PRICE_LISTS);

		return commercePriceEntryLocalService.updateExternalReferenceCode(
			commercePriceEntry, externalReferenceCode);
	}

	@Override
	public CommercePriceEntry upsertCommercePriceEntry(
			long commercePriceEntryId, long cpInstanceId,
			long commercePriceListId, String externalReferenceCode,
			BigDecimal price, BigDecimal promoPrice,
			String skuExternalReferenceCode, ServiceContext serviceContext)
		throws PortalException {

		_portletResourcePermission.check(
			getPermissionChecker(), serviceContext.getScopeGroupId(),
			CommercePriceListActionKeys.MANAGE_COMMERCE_PRICE_LISTS);

		return commercePriceEntryLocalService.upsertCommercePriceEntry(
			commercePriceEntryId, cpInstanceId, commercePriceListId,
			externalReferenceCode, price, promoPrice, skuExternalReferenceCode,
			serviceContext);
	}

	private static volatile PortletResourcePermission
		_portletResourcePermission =
			PortletResourcePermissionFactory.getInstance(
				CommercePriceEntryServiceImpl.class,
				"_portletResourcePermission",
				CommercePriceListConstants.RESOURCE_NAME);

	@ServiceReference(type = CPInstanceLocalService.class)
	private CPInstanceLocalService _cpInstanceLocalService;

}