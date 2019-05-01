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
 * Provides a wrapper for {@link CommerceChannelService}.
 *
 * @author Marco Leo
 * @see CommerceChannelService
 * @generated
 */
@ProviderType
public class CommerceChannelServiceWrapper implements CommerceChannelService,
	ServiceWrapper<CommerceChannelService> {
	public CommerceChannelServiceWrapper(
		CommerceChannelService commerceChannelService) {
		_commerceChannelService = commerceChannelService;
	}

	@Override
	public com.liferay.commerce.product.model.CommerceChannel addCommerceChannel(
		java.util.Map<java.util.Locale, String> nameMap, String filterType,
		String type, String typeSettings,
		com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _commerceChannelService.addCommerceChannel(nameMap, filterType,
			type, typeSettings, serviceContext);
	}

	@Override
	public com.liferay.commerce.product.model.CommerceChannel addCommerceChannel(
		String name, String filterType, String type, String typeSettings,
		com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _commerceChannelService.addCommerceChannel(name, filterType,
			type, typeSettings, serviceContext);
	}

	@Override
	public com.liferay.commerce.product.model.CommerceChannel deleteCommerceChannel(
		long commerceChannelId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _commerceChannelService.deleteCommerceChannel(commerceChannelId);
	}

	@Override
	public com.liferay.commerce.product.model.CommerceChannel fetchCommerceChannel(
		long commerceChannelId) {
		return _commerceChannelService.fetchCommerceChannel(commerceChannelId);
	}

	@Override
	public com.liferay.commerce.product.model.CommerceChannel getCommerceChannel(
		long commerceChannelId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _commerceChannelService.getCommerceChannel(commerceChannelId);
	}

	@Override
	public java.util.List<com.liferay.commerce.product.model.CommerceChannel> getCommerceChannels(
		int start, int end)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _commerceChannelService.getCommerceChannels(start, end);
	}

	/**
	* Returns the OSGi service identifier.
	*
	* @return the OSGi service identifier
	*/
	@Override
	public String getOSGiServiceIdentifier() {
		return _commerceChannelService.getOSGiServiceIdentifier();
	}

	@Override
	public com.liferay.commerce.product.model.CommerceChannel updateCommerceChannel(
		long commerceChannelId,
		java.util.Map<java.util.Locale, String> nameMap, String filterType,
		String type, String typeSettings,
		com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _commerceChannelService.updateCommerceChannel(commerceChannelId,
			nameMap, filterType, type, typeSettings, serviceContext);
	}

	@Override
	public CommerceChannelService getWrappedService() {
		return _commerceChannelService;
	}

	@Override
	public void setWrappedService(CommerceChannelService commerceChannelService) {
		_commerceChannelService = commerceChannelService;
	}

	private CommerceChannelService _commerceChannelService;
}