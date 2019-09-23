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

import com.liferay.portal.kernel.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link CommerceChannelService}.
 *
 * @author Marco Leo
 * @see CommerceChannelService
 * @generated
 */
public class CommerceChannelServiceWrapper
	implements CommerceChannelService, ServiceWrapper<CommerceChannelService> {

	public CommerceChannelServiceWrapper(
		CommerceChannelService commerceChannelService) {

		_commerceChannelService = commerceChannelService;
	}

	/**
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link CommerceChannelServiceUtil} to access the commerce channel remote service. Add custom service methods to <code>com.liferay.commerce.product.service.impl.CommerceChannelServiceImpl</code> and rerun ServiceBuilder to automatically copy the method declarations to this interface.
	 */
	@Override
	public com.liferay.commerce.product.model.CommerceChannel
			addCommerceChannel(
				long siteGroupId, String name, String type,
				com.liferay.portal.kernel.util.UnicodeProperties
					typeSettingsProperties,
				String commerceCurrencyCode, String externalReferenceCode,
				com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _commerceChannelService.addCommerceChannel(
			siteGroupId, name, type, typeSettingsProperties,
			commerceCurrencyCode, externalReferenceCode, serviceContext);
	}

	@Override
	public com.liferay.commerce.product.model.CommerceChannel
			deleteCommerceChannel(long commerceChannelId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _commerceChannelService.deleteCommerceChannel(commerceChannelId);
	}

	@Override
	public com.liferay.commerce.product.model.CommerceChannel
			fetchCommerceChannel(long commerceChannelId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _commerceChannelService.fetchCommerceChannel(commerceChannelId);
	}

	@Override
	public com.liferay.commerce.product.model.CommerceChannel
			getCommerceChannel(long commerceChannelId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _commerceChannelService.getCommerceChannel(commerceChannelId);
	}

	@Override
	public java.util.List<com.liferay.commerce.product.model.CommerceChannel>
			getCommerceChannels(int start, int end)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _commerceChannelService.getCommerceChannels(start, end);
	}

	@Override
	public java.util.List<com.liferay.commerce.product.model.CommerceChannel>
			getCommerceChannels(long companyId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _commerceChannelService.getCommerceChannels(companyId);
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
	public java.util.List<com.liferay.commerce.product.model.CommerceChannel>
			searchCommerceChannels(long companyId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _commerceChannelService.searchCommerceChannels(companyId);
	}

	@Override
	public java.util.List<com.liferay.commerce.product.model.CommerceChannel>
			searchCommerceChannels(
				long companyId, String keywords, int start, int end,
				com.liferay.portal.kernel.search.Sort sort)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _commerceChannelService.searchCommerceChannels(
			companyId, keywords, start, end, sort);
	}

	@Override
	public int searchCommerceChannelsCount(long companyId, String keywords)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _commerceChannelService.searchCommerceChannelsCount(
			companyId, keywords);
	}

	@Override
	public com.liferay.commerce.product.model.CommerceChannel
			updateCommerceChannel(
				long commerceChannelId, long siteGroupId, String name,
				String type,
				com.liferay.portal.kernel.util.UnicodeProperties
					typeSettingsProperties,
				String commerceCurrencyCode)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _commerceChannelService.updateCommerceChannel(
			commerceChannelId, siteGroupId, name, type, typeSettingsProperties,
			commerceCurrencyCode);
	}

	@Override
	public CommerceChannelService getWrappedService() {
		return _commerceChannelService;
	}

	@Override
	public void setWrappedService(
		CommerceChannelService commerceChannelService) {

		_commerceChannelService = commerceChannelService;
	}

	private CommerceChannelService _commerceChannelService;

}