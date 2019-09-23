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
 * Provides a wrapper for {@link CommercePriceListAccountRelService}.
 *
 * @author Alessio Antonio Rendina
 * @see CommercePriceListAccountRelService
 * @generated
 */
public class CommercePriceListAccountRelServiceWrapper
	implements CommercePriceListAccountRelService,
			   ServiceWrapper<CommercePriceListAccountRelService> {

	public CommercePriceListAccountRelServiceWrapper(
		CommercePriceListAccountRelService commercePriceListAccountRelService) {

		_commercePriceListAccountRelService =
			commercePriceListAccountRelService;
	}

	/**
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link CommercePriceListAccountRelServiceUtil} to access the commerce price list account rel remote service. Add custom service methods to <code>com.liferay.commerce.price.list.service.impl.CommercePriceListAccountRelServiceImpl</code> and rerun ServiceBuilder to automatically copy the method declarations to this interface.
	 */
	@Override
	public com.liferay.commerce.price.list.model.CommercePriceListAccountRel
			addCommercePriceListAccountRel(
				long commercePriceListId, long commerceAccountId, int order,
				com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _commercePriceListAccountRelService.
			addCommercePriceListAccountRel(
				commercePriceListId, commerceAccountId, order, serviceContext);
	}

	@Override
	public void deleteCommercePriceListAccountRel(
			long commercePriceListAccountRelId)
		throws com.liferay.portal.kernel.exception.PortalException {

		_commercePriceListAccountRelService.deleteCommercePriceListAccountRel(
			commercePriceListAccountRelId);
	}

	@Override
	public com.liferay.commerce.price.list.model.CommercePriceListAccountRel
			fetchCommercePriceListAccountRel(
				long commercePriceListId, long commerceAccountId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _commercePriceListAccountRelService.
			fetchCommercePriceListAccountRel(
				commercePriceListId, commerceAccountId);
	}

	@Override
	public java.util.List
		<com.liferay.commerce.price.list.model.CommercePriceListAccountRel>
				getCommercePriceListAccountRels(long commercePriceListId)
			throws com.liferay.portal.kernel.exception.PortalException {

		return _commercePriceListAccountRelService.
			getCommercePriceListAccountRels(commercePriceListId);
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	@Override
	public String getOSGiServiceIdentifier() {
		return _commercePriceListAccountRelService.getOSGiServiceIdentifier();
	}

	@Override
	public CommercePriceListAccountRelService getWrappedService() {
		return _commercePriceListAccountRelService;
	}

	@Override
	public void setWrappedService(
		CommercePriceListAccountRelService commercePriceListAccountRelService) {

		_commercePriceListAccountRelService =
			commercePriceListAccountRelService;
	}

	private CommercePriceListAccountRelService
		_commercePriceListAccountRelService;

}