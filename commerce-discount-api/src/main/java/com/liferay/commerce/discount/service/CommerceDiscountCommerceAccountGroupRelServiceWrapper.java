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

package com.liferay.commerce.discount.service;

import aQute.bnd.annotation.ProviderType;

import com.liferay.portal.kernel.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link CommerceDiscountCommerceAccountGroupRelService}.
 *
 * @author Marco Leo
 * @see CommerceDiscountCommerceAccountGroupRelService
 * @generated
 */
@ProviderType
public class CommerceDiscountCommerceAccountGroupRelServiceWrapper
	implements CommerceDiscountCommerceAccountGroupRelService,
			   ServiceWrapper<CommerceDiscountCommerceAccountGroupRelService> {

	public CommerceDiscountCommerceAccountGroupRelServiceWrapper(
		CommerceDiscountCommerceAccountGroupRelService
			commerceDiscountCommerceAccountGroupRelService) {

		_commerceDiscountCommerceAccountGroupRelService =
			commerceDiscountCommerceAccountGroupRelService;
	}

	@Override
	public
		com.liferay.commerce.discount.model.
			CommerceDiscountCommerceAccountGroupRel
					addCommerceDiscountCommerceAccountGroupRel(
						long commerceDiscountId, long commerceAccountGroupId,
						com.liferay.portal.kernel.service.ServiceContext
							serviceContext)
				throws com.liferay.portal.kernel.exception.PortalException {

		return _commerceDiscountCommerceAccountGroupRelService.
			addCommerceDiscountCommerceAccountGroupRel(
				commerceDiscountId, commerceAccountGroupId, serviceContext);
	}

	@Override
	public void deleteCommerceDiscountCommerceAccountGroupRel(
			long commerceDiscountCommerceAccountGroupRelId)
		throws com.liferay.portal.kernel.exception.PortalException {

		_commerceDiscountCommerceAccountGroupRelService.
			deleteCommerceDiscountCommerceAccountGroupRel(
				commerceDiscountCommerceAccountGroupRelId);
	}

	@Override
	public
		com.liferay.commerce.discount.model.
			CommerceDiscountCommerceAccountGroupRel
					fetchCommerceDiscountCommerceAccountGroupRel(
						long commerceDiscountId, long commerceAccountGroupId)
				throws com.liferay.portal.kernel.exception.PortalException {

		return _commerceDiscountCommerceAccountGroupRelService.
			fetchCommerceDiscountCommerceAccountGroupRel(
				commerceDiscountId, commerceAccountGroupId);
	}

	@Override
	public
		com.liferay.commerce.discount.model.
			CommerceDiscountCommerceAccountGroupRel
					getCommerceDiscountCommerceAccountGroupRel(
						long commerceDiscountCommerceAccountGroupRelId)
				throws com.liferay.portal.kernel.exception.PortalException {

		return _commerceDiscountCommerceAccountGroupRelService.
			getCommerceDiscountCommerceAccountGroupRel(
				commerceDiscountCommerceAccountGroupRelId);
	}

	@Override
	public java.util.List
		<com.liferay.commerce.discount.model.
			CommerceDiscountCommerceAccountGroupRel>
					getCommerceDiscountCommerceAccountGroupRels(
						long commerceDiscountId, int start, int end,
						com.liferay.portal.kernel.util.OrderByComparator
							<com.liferay.commerce.discount.model.
								CommerceDiscountCommerceAccountGroupRel>
									orderByComparator)
				throws com.liferay.portal.kernel.exception.PortalException {

		return _commerceDiscountCommerceAccountGroupRelService.
			getCommerceDiscountCommerceAccountGroupRels(
				commerceDiscountId, start, end, orderByComparator);
	}

	@Override
	public int getCommerceDiscountCommerceAccountGroupRelsCount(
			long commerceDiscountId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _commerceDiscountCommerceAccountGroupRelService.
			getCommerceDiscountCommerceAccountGroupRelsCount(
				commerceDiscountId);
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	@Override
	public String getOSGiServiceIdentifier() {
		return _commerceDiscountCommerceAccountGroupRelService.
			getOSGiServiceIdentifier();
	}

	@Override
	public CommerceDiscountCommerceAccountGroupRelService getWrappedService() {
		return _commerceDiscountCommerceAccountGroupRelService;
	}

	@Override
	public void setWrappedService(
		CommerceDiscountCommerceAccountGroupRelService
			commerceDiscountCommerceAccountGroupRelService) {

		_commerceDiscountCommerceAccountGroupRelService =
			commerceDiscountCommerceAccountGroupRelService;
	}

	private CommerceDiscountCommerceAccountGroupRelService
		_commerceDiscountCommerceAccountGroupRelService;

}