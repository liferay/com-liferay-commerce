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

package com.liferay.commerce.product.service;

import aQute.bnd.annotation.ProviderType;

import com.liferay.portal.kernel.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link CommerceChannelRelService}.
 *
 * @author Marco Leo
 * @see CommerceChannelRelService
 * @generated
 */
@ProviderType
public class CommerceChannelRelServiceWrapper
	implements CommerceChannelRelService,
			   ServiceWrapper<CommerceChannelRelService> {

	public CommerceChannelRelServiceWrapper(
		CommerceChannelRelService commerceChannelRelService) {

		_commerceChannelRelService = commerceChannelRelService;
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	@Override
	public String getOSGiServiceIdentifier() {
		return _commerceChannelRelService.getOSGiServiceIdentifier();
	}

	@Override
	public CommerceChannelRelService getWrappedService() {
		return _commerceChannelRelService;
	}

	@Override
	public void setWrappedService(
		CommerceChannelRelService commerceChannelRelService) {

		_commerceChannelRelService = commerceChannelRelService;
	}

	private CommerceChannelRelService _commerceChannelRelService;

}