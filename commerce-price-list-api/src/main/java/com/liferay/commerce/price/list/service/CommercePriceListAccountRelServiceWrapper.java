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
 * Provides a wrapper for {@link CommercePriceListAccountRelService}.
 *
 * @author Alessio Antonio Rendina
 * @see CommercePriceListAccountRelService
 * @generated
 */
@ProviderType
public class CommercePriceListAccountRelServiceWrapper
	implements CommercePriceListAccountRelService,
		ServiceWrapper<CommercePriceListAccountRelService> {
	public CommercePriceListAccountRelServiceWrapper(
		CommercePriceListAccountRelService commercePriceListAccountRelService) {
		_commercePriceListAccountRelService = commercePriceListAccountRelService;
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
		_commercePriceListAccountRelService = commercePriceListAccountRelService;
	}

	private CommercePriceListAccountRelService _commercePriceListAccountRelService;
}