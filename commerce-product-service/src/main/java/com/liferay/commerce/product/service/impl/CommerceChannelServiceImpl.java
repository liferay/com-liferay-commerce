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

package com.liferay.commerce.product.service.impl;

import com.liferay.commerce.product.model.CommerceChannel;
import com.liferay.commerce.product.service.base.CommerceChannelServiceBaseImpl;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.service.ServiceContext;

import java.util.List;
import java.util.Locale;
import java.util.Map;

/**
 * @author Alec Sloan
 */
public class CommerceChannelServiceImpl extends CommerceChannelServiceBaseImpl {

	@Override
	public CommerceChannel addCommerceChannel(
			Map<Locale, String> nameMap, String filterType, String type,
			String typeSettings, ServiceContext serviceContext)
		throws PortalException {

		return commerceChannelLocalService.addCommerceChannel(
			nameMap, filterType, type, typeSettings, serviceContext);
	}

	@Override
	public CommerceChannel addCommerceChannel(
			String name, String filterType, String type, String typeSettings,
			ServiceContext serviceContext)
		throws PortalException {

		return commerceChannelLocalService.addCommerceChannel(
			name, filterType, type, typeSettings, serviceContext);
	}

	@Override
	public CommerceChannel deleteCommerceChannel(long commerceChannelId)
		throws PortalException {

		return commerceChannelLocalService.deleteCommerceChannel(
			commerceChannelId);
	}

	@Override
	public CommerceChannel fetchCommerceChannel(long commerceChannelId) {
		return commerceChannelLocalService.fetchCommerceChannel(
			commerceChannelId);
	}

	@Override
	public CommerceChannel getCommerceChannel(long commerceChannelId)
		throws PortalException {

		return commerceChannelLocalService.getCommerceChannel(
			commerceChannelId);
	}

	@Override
	public List<CommerceChannel> getCommerceChannels(int start, int end)
		throws PortalException {

		return commerceChannelLocalService.getCommerceChannels(start, end);
	}

	@Override
	public CommerceChannel updateCommerceChannel(
			long commerceChannelId, Map<Locale, String> nameMap,
			String filterType, String type, String typeSettings,
			ServiceContext serviceContext)
		throws PortalException {

		return commerceChannelLocalService.updateCommerceChannel(
			commerceChannelId, nameMap, filterType, type, typeSettings,
			serviceContext);
	}

}