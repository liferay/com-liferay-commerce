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
 * Provides a wrapper for {@link CommerceDiscountRelService}.
 *
 * @author Marco Leo
 * @see CommerceDiscountRelService
 * @generated
 */
@ProviderType
public class CommerceDiscountRelServiceWrapper
	implements CommerceDiscountRelService,
			   ServiceWrapper<CommerceDiscountRelService> {

	public CommerceDiscountRelServiceWrapper(
		CommerceDiscountRelService commerceDiscountRelService) {

		_commerceDiscountRelService = commerceDiscountRelService;
	}

	@Override
	public com.liferay.commerce.discount.model.CommerceDiscountRel
			addCommerceDiscountRel(
				long commerceDiscountId, String className, long classPK,
				com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _commerceDiscountRelService.addCommerceDiscountRel(
			commerceDiscountId, className, classPK, serviceContext);
	}

	@Override
	public void deleteCommerceDiscountRel(long commerceDiscountRelId)
		throws com.liferay.portal.kernel.exception.PortalException {

		_commerceDiscountRelService.deleteCommerceDiscountRel(
			commerceDiscountRelId);
	}

	@Override
	public com.liferay.commerce.discount.model.CommerceDiscountRel
			fetchCommerceDiscountRel(String className, long classPK)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _commerceDiscountRelService.fetchCommerceDiscountRel(
			className, classPK);
	}

	@Override
	public long[] getClassPKs(long commerceDiscountId, String className)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _commerceDiscountRelService.getClassPKs(
			commerceDiscountId, className);
	}

	@Override
	public com.liferay.commerce.discount.model.CommerceDiscountRel
			getCommerceDiscountRel(long commerceDiscountRelId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _commerceDiscountRelService.getCommerceDiscountRel(
			commerceDiscountRelId);
	}

	@Override
	public java.util.List
		<com.liferay.commerce.discount.model.CommerceDiscountRel>
				getCommerceDiscountRels(
					long commerceDiscountId, String className)
			throws com.liferay.portal.kernel.exception.PortalException {

		return _commerceDiscountRelService.getCommerceDiscountRels(
			commerceDiscountId, className);
	}

	@Override
	public java.util.List
		<com.liferay.commerce.discount.model.CommerceDiscountRel>
				getCommerceDiscountRels(
					long commerceDiscountId, String className, int start,
					int end,
					com.liferay.portal.kernel.util.OrderByComparator
						<com.liferay.commerce.discount.model.
							CommerceDiscountRel> orderByComparator)
			throws com.liferay.portal.kernel.exception.PortalException {

		return _commerceDiscountRelService.getCommerceDiscountRels(
			commerceDiscountId, className, start, end, orderByComparator);
	}

	@Override
	public int getCommerceDiscountRelsCount(
			long commerceDiscountId, String className)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _commerceDiscountRelService.getCommerceDiscountRelsCount(
			commerceDiscountId, className);
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	@Override
	public String getOSGiServiceIdentifier() {
		return _commerceDiscountRelService.getOSGiServiceIdentifier();
	}

	@Override
	public CommerceDiscountRelService getWrappedService() {
		return _commerceDiscountRelService;
	}

	@Override
	public void setWrappedService(
		CommerceDiscountRelService commerceDiscountRelService) {

		_commerceDiscountRelService = commerceDiscountRelService;
	}

	private CommerceDiscountRelService _commerceDiscountRelService;

}