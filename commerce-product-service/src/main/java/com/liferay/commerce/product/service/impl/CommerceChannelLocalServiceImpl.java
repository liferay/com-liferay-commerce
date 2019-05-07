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
import com.liferay.commerce.product.service.base.CommerceChannelLocalServiceBaseImpl;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.model.SystemEventConstants;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.search.Indexable;
import com.liferay.portal.kernel.search.IndexableType;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.systemevent.SystemEvent;

import java.util.Date;

/**
 * @author Alec Sloan
 */
public class CommerceChannelLocalServiceImpl
	extends CommerceChannelLocalServiceBaseImpl {

	@Indexable(type = IndexableType.REINDEX)
	@Override
	public CommerceChannel addCommerceChannel(
			String name, String filterType, String type, String typeSettings,
			ServiceContext serviceContext)
		throws PortalException {

		long commerceChannelId = counterLocalService.increment();

		CommerceChannel commerceChannel = commerceChannelPersistence.create(
			commerceChannelId);

		User user = userLocalService.getUser(serviceContext.getUserId());

		commerceChannel.setCompanyId(user.getCompanyId());
		commerceChannel.setUserId(user.getUserId());
		commerceChannel.setUserName(user.getFullName());

		Date now = new Date();

		commerceChannel.setCreateDate(now);
		commerceChannel.setModifiedDate(now);

		commerceChannel.setName(name);
		commerceChannel.setFilterType(filterType);
		commerceChannel.setType(type);
		commerceChannel.setTypeSettings(typeSettings);

		return commerceChannelPersistence.update(commerceChannel);
	}

	@Indexable(type = IndexableType.REINDEX)
	@Override
	@SystemEvent(type = SystemEventConstants.TYPE_DELETE)
	public CommerceChannel deleteCommerceChannel(long commerceChannelId)
		throws PortalException {

		return commerceChannelPersistence.remove(commerceChannelId);
	}

	@Indexable(type = IndexableType.REINDEX)
	@Override
	public CommerceChannel updateCommerceChannel(
			long commerceChannelId, String name, String filterType, String type,
			String typeSettings, ServiceContext serviceContext)
		throws PortalException {

		CommerceChannel commerceChannel =
			commerceChannelPersistence.findByPrimaryKey(commerceChannelId);

		commerceChannel.setName(name);
		commerceChannel.setFilterType(filterType);
		commerceChannel.setType(type);
		commerceChannel.setTypeSettings(typeSettings);

		return commerceChannelPersistence.update(commerceChannel);
	}

}