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
import com.liferay.commerce.price.list.exception.NoSuchPriceListException;
import com.liferay.commerce.price.list.model.CommercePriceList;
import com.liferay.commerce.price.list.model.CommercePriceListCommerceAccountGroupRel;
import com.liferay.commerce.price.list.service.base.CommercePriceListCommerceAccountGroupRelServiceBaseImpl;
import com.liferay.commerce.product.model.CommerceCatalog;
import com.liferay.commerce.product.service.CommerceCatalogService;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.security.permission.resource.ModelResourcePermission;
import com.liferay.portal.kernel.security.permission.resource.ModelResourcePermissionFactory;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.spring.extender.service.ServiceReference;

import java.util.List;

/**
 * @author Alessio Antonio Rendina
 */
public class CommercePriceListCommerceAccountGroupRelServiceImpl
	extends CommercePriceListCommerceAccountGroupRelServiceBaseImpl {

	@Override
	public CommercePriceListCommerceAccountGroupRel
			addCommercePriceListCommerceAccountGroupRel(
				long commercePriceListId, long commerceAccountGroupId,
				int order, ServiceContext serviceContext)
		throws PortalException {

		_checkCommerceCatalogPermissionByCommercePriceListId(
			commercePriceListId,
			CommercePriceListActionKeys.MANAGE_COMMERCE_PRICE_LISTS);

		return commercePriceListCommerceAccountGroupRelLocalService.
			addCommercePriceListCommerceAccountGroupRel(
				commercePriceListId, commerceAccountGroupId, order,
				serviceContext);
	}

	@Override
	public void deleteCommercePriceListCommerceAccountGroupRel(
			long commercePriceListCommerceAccountGroupRelId)
		throws PortalException {

		CommercePriceListCommerceAccountGroupRel
			commercePriceListCommerceAccountGroupRel =
				commercePriceListCommerceAccountGroupRelLocalService.
					getCommercePriceListCommerceAccountGroupRel(
						commercePriceListCommerceAccountGroupRelId);

		_checkCommerceCatalogPermissionByCommercePriceListId(
			commercePriceListCommerceAccountGroupRel.getCommercePriceListId(),
			CommercePriceListActionKeys.MANAGE_COMMERCE_PRICE_LISTS);

		commercePriceListCommerceAccountGroupRelLocalService.
			deleteCommercePriceListCommerceAccountGroupRel(
				commercePriceListCommerceAccountGroupRel);
	}

	@Override
	public CommercePriceListCommerceAccountGroupRel
			fetchCommercePriceListCommerceAccountGroupRel(
				long commercePriceListId, long commerceAccountGroupId)
		throws PortalException {

		CommercePriceListCommerceAccountGroupRel
			commercePriceListCommerceAccountGroupRel =
				commercePriceListCommerceAccountGroupRelLocalService.
					fetchCommercePriceListCommerceAccountGroupRel(
						commercePriceListId, commerceAccountGroupId);

		if (commercePriceListCommerceAccountGroupRel != null) {
			_checkCommerceCatalogPermissionByCommercePriceListId(
				commercePriceListCommerceAccountGroupRel.
					getCommercePriceListId(),
				CommercePriceListActionKeys.MANAGE_COMMERCE_PRICE_LISTS);
		}

		return commercePriceListCommerceAccountGroupRel;
	}

	@Override
	public CommercePriceListCommerceAccountGroupRel
			getCommercePriceListCommerceAccountGroupRel(
				long commercePriceListCommerceAccountGroupRelId)
		throws PortalException {

		CommercePriceListCommerceAccountGroupRel
			commercePriceListCommerceAccountGroupRel =
				commercePriceListCommerceAccountGroupRelLocalService.
					getCommercePriceListCommerceAccountGroupRel(
						commercePriceListCommerceAccountGroupRelId);

		_checkCommerceCatalogPermissionByCommercePriceListId(
			commercePriceListCommerceAccountGroupRel.getCommercePriceListId(),
			CommercePriceListActionKeys.MANAGE_COMMERCE_PRICE_LISTS);

		return commercePriceListCommerceAccountGroupRel;
	}

	@Override
	public List<CommercePriceListCommerceAccountGroupRel>
			getCommercePriceListCommerceAccountGroupRels(
				long commercePriceListId)
		throws PortalException {

		_checkCommerceCatalogPermissionByCommercePriceListId(
			commercePriceListId,
			CommercePriceListActionKeys.MANAGE_COMMERCE_PRICE_LISTS);

		return commercePriceListCommerceAccountGroupRelLocalService.
			getCommercePriceListCommerceAccountGroupRels(commercePriceListId);
	}

	@Override
	public List<CommercePriceListCommerceAccountGroupRel>
			getCommercePriceListCommerceAccountGroupRels(
				long commercePriceListId, int start, int end,
				OrderByComparator<CommercePriceListCommerceAccountGroupRel>
					orderByComparator)
		throws PortalException {

		_checkCommerceCatalogPermissionByCommercePriceListId(
			commercePriceListId,
			CommercePriceListActionKeys.MANAGE_COMMERCE_PRICE_LISTS);

		return commercePriceListCommerceAccountGroupRelLocalService.
			getCommercePriceListCommerceAccountGroupRels(
				commercePriceListId, start, end, orderByComparator);
	}

	@Override
	public int getCommercePriceListCommerceAccountGroupRelsCount(
			long commercePriceListId)
		throws PortalException {

		_checkCommerceCatalogPermissionByCommercePriceListId(
			commercePriceListId,
			CommercePriceListActionKeys.MANAGE_COMMERCE_PRICE_LISTS);

		return commercePriceListCommerceAccountGroupRelLocalService.
			getCommercePriceListCommerceAccountGroupRelsCount(
				commercePriceListId);
	}

	@Override
	public CommercePriceListCommerceAccountGroupRel
			updateCommercePriceListCommerceAccountGroupRel(
				long commercePriceListCommerceAccountGroupRelId, int order,
				ServiceContext serviceContext)
		throws PortalException {

		CommercePriceListCommerceAccountGroupRel
			commercePriceListCommerceAccountGroupRel =
				commercePriceListCommerceAccountGroupRelLocalService.
					getCommercePriceListCommerceAccountGroupRel(
						commercePriceListCommerceAccountGroupRelId);

		_checkCommerceCatalogPermissionByCommercePriceListId(
			commercePriceListCommerceAccountGroupRel.getCommercePriceListId(),
			CommercePriceListActionKeys.MANAGE_COMMERCE_PRICE_LISTS);

		return commercePriceListCommerceAccountGroupRelLocalService.
			updateCommercePriceListCommerceAccountGroupRel(
				commercePriceListCommerceAccountGroupRelId, order,
				serviceContext);
	}

	private void _checkCommerceCatalogPermissionByCommercePriceListId(
			long commercePriceListId, String actionId)
		throws PortalException {

		CommercePriceList commercePriceList =
			commercePriceListLocalService.fetchCommercePriceList(
				commercePriceListId);

		if (commercePriceList == null) {
			throw new NoSuchPriceListException();
		}

		CommerceCatalog commerceCatalog =
			_commerceCatalogService.fetchCommerceCatalogByGroupId(
				commercePriceList.getGroupId());

		_commerceCatalogModelResourcePermission.check(
			getPermissionChecker(), commerceCatalog, actionId);
	}

	private static volatile ModelResourcePermission<CommerceCatalog>
		_commerceCatalogModelResourcePermission =
			ModelResourcePermissionFactory.getInstance(
				CommercePriceListCommerceAccountGroupRelServiceImpl.class,
				"_commerceCatalogModelResourcePermission",
				CommerceCatalog.class);

	@ServiceReference(type = CommerceCatalogService.class)
	private CommerceCatalogService _commerceCatalogService;

}