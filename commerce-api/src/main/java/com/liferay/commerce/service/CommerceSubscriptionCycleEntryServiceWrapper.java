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

package com.liferay.commerce.service;

import aQute.bnd.annotation.ProviderType;

import com.liferay.portal.kernel.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link CommerceSubscriptionCycleEntryService}.
 *
 * @author Alessio Antonio Rendina
 * @see CommerceSubscriptionCycleEntryService
 * @generated
 */
@ProviderType
public class CommerceSubscriptionCycleEntryServiceWrapper
	implements CommerceSubscriptionCycleEntryService,
		ServiceWrapper<CommerceSubscriptionCycleEntryService> {
	public CommerceSubscriptionCycleEntryServiceWrapper(
		CommerceSubscriptionCycleEntryService commerceSubscriptionCycleEntryService) {
		_commerceSubscriptionCycleEntryService = commerceSubscriptionCycleEntryService;
	}

	/**
	* Returns the OSGi service identifier.
	*
	* @return the OSGi service identifier
	*/
	@Override
	public String getOSGiServiceIdentifier() {
		return _commerceSubscriptionCycleEntryService.getOSGiServiceIdentifier();
	}

	@Override
	public CommerceSubscriptionCycleEntryService getWrappedService() {
		return _commerceSubscriptionCycleEntryService;
	}

	@Override
	public void setWrappedService(
		CommerceSubscriptionCycleEntryService commerceSubscriptionCycleEntryService) {
		_commerceSubscriptionCycleEntryService = commerceSubscriptionCycleEntryService;
	}

	private CommerceSubscriptionCycleEntryService _commerceSubscriptionCycleEntryService;
}