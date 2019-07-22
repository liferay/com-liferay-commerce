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
 * Provides a wrapper for {@link CommercePriceListCommerceAccountGroupRelService}.
 *
 * @author Alessio Antonio Rendina
 * @see CommercePriceListCommerceAccountGroupRelService
 * @generated
 */
@ProviderType
public class CommercePriceListCommerceAccountGroupRelServiceWrapper
	implements CommercePriceListCommerceAccountGroupRelService,
			   ServiceWrapper<CommercePriceListCommerceAccountGroupRelService> {

	public CommercePriceListCommerceAccountGroupRelServiceWrapper(
		CommercePriceListCommerceAccountGroupRelService
			commercePriceListCommerceAccountGroupRelService) {

		_commercePriceListCommerceAccountGroupRelService =
			commercePriceListCommerceAccountGroupRelService;
	}

	@Override
	public com.liferay.commerce.price.list.model.
		CommercePriceListCommerceAccountGroupRel
				addCommercePriceListCommerceAccountGroupRel(
					long commercePriceListId, long commerceAccountGroupId,
					int order,
					com.liferay.portal.kernel.service.ServiceContext
						serviceContext)
			throws com.liferay.portal.kernel.exception.PortalException {

		return _commercePriceListCommerceAccountGroupRelService.
			addCommercePriceListCommerceAccountGroupRel(
				commercePriceListId, commerceAccountGroupId, order,
				serviceContext);
	}

	@Override
	public void deleteCommercePriceListCommerceAccountGroupRel(
			long commercePriceListCommerceAccountGroupRelId)
		throws com.liferay.portal.kernel.exception.PortalException {

		_commercePriceListCommerceAccountGroupRelService.
			deleteCommercePriceListCommerceAccountGroupRel(
				commercePriceListCommerceAccountGroupRelId);
	}

	@Override
	public com.liferay.commerce.price.list.model.
		CommercePriceListCommerceAccountGroupRel
				fetchCommercePriceListCommerceAccountGroupRel(
					long commercePriceListId, long commerceAccountGroupId)
			throws com.liferay.portal.kernel.exception.PortalException {

		return _commercePriceListCommerceAccountGroupRelService.
			fetchCommercePriceListCommerceAccountGroupRel(
				commercePriceListId, commerceAccountGroupId);
	}

	@Override
	public com.liferay.commerce.price.list.model.
		CommercePriceListCommerceAccountGroupRel
				getCommercePriceListCommerceAccountGroupRel(
					long commercePriceListCommerceAccoungGroupRelId)
			throws com.liferay.portal.kernel.exception.PortalException {

		return _commercePriceListCommerceAccountGroupRelService.
			getCommercePriceListCommerceAccountGroupRel(
				commercePriceListCommerceAccoungGroupRelId);
	}

	@Override
	public java.util.List
		<com.liferay.commerce.price.list.model.
			CommercePriceListCommerceAccountGroupRel>
					getCommercePriceListCommerceAccountGroupRels(
						long commercePriceListId)
				throws com.liferay.portal.kernel.exception.PortalException {

		return _commercePriceListCommerceAccountGroupRelService.
			getCommercePriceListCommerceAccountGroupRels(commercePriceListId);
	}

	@Override
	public java.util.List
		<com.liferay.commerce.price.list.model.
			CommercePriceListCommerceAccountGroupRel>
					getCommercePriceListCommerceAccountGroupRels(
						long commercePriceListId, int start, int end,
						com.liferay.portal.kernel.util.OrderByComparator
							<com.liferay.commerce.price.list.model.
								CommercePriceListCommerceAccountGroupRel>
									orderByComparator)
				throws com.liferay.portal.kernel.exception.PortalException {

		return _commercePriceListCommerceAccountGroupRelService.
			getCommercePriceListCommerceAccountGroupRels(
				commercePriceListId, start, end, orderByComparator);
	}

	@Override
	public int getCommercePriceListCommerceAccountGroupRelsCount(
			long commercePriceListId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _commercePriceListCommerceAccountGroupRelService.
			getCommercePriceListCommerceAccountGroupRelsCount(
				commercePriceListId);
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	@Override
	public String getOSGiServiceIdentifier() {
		return _commercePriceListCommerceAccountGroupRelService.
			getOSGiServiceIdentifier();
	}

	@Override
	public com.liferay.commerce.price.list.model.
		CommercePriceListCommerceAccountGroupRel
				updateCommercePriceListCommerceAccountGroupRel(
					long commercePriceListCommerceAccountGroupRelId, int order,
					com.liferay.portal.kernel.service.ServiceContext
						serviceContext)
			throws com.liferay.portal.kernel.exception.PortalException {

		return _commercePriceListCommerceAccountGroupRelService.
			updateCommercePriceListCommerceAccountGroupRel(
				commercePriceListCommerceAccountGroupRelId, order,
				serviceContext);
	}

	@Override
	public CommercePriceListCommerceAccountGroupRelService getWrappedService() {
		return _commercePriceListCommerceAccountGroupRelService;
	}

	@Override
	public void setWrappedService(
		CommercePriceListCommerceAccountGroupRelService
			commercePriceListCommerceAccountGroupRelService) {

		_commercePriceListCommerceAccountGroupRelService =
			commercePriceListCommerceAccountGroupRelService;
	}

	private CommercePriceListCommerceAccountGroupRelService
		_commercePriceListCommerceAccountGroupRelService;

}