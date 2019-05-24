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
import com.liferay.portal.kernel.model.Group;
import com.liferay.portal.kernel.model.GroupConstants;
import com.liferay.portal.kernel.model.ResourceConstants;
import com.liferay.portal.kernel.model.SystemEventConstants;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.systemevent.SystemEvent;
import com.liferay.portal.kernel.util.ArrayUtil;
import com.liferay.portal.kernel.util.UnicodeProperties;

import java.util.Collections;
import java.util.List;
import java.util.Locale;
import java.util.Map;

/**
 * @author Alec Sloan
 * @author Alessio Antonio Rendina
 */
public class CommerceChannelLocalServiceImpl
	extends CommerceChannelLocalServiceBaseImpl {

	@Override
	public CommerceChannel addCommerceChannel(
			String name, String type, UnicodeProperties typeSettingsProperties,
			String commerceCurrencyCode, String externalReferenceCode,
			ServiceContext serviceContext)
		throws PortalException {

		if (ArrayUtil.contains(getUsedSiteGroupIds(), siteGroupId)) {
			throw new PortalException();
		}

		User user = userLocalService.getUser(serviceContext.getUserId());

		long commerceChannelId = counterLocalService.increment();

		CommerceChannel commerceChannel = commerceChannelPersistence.create(
			commerceChannelId);

		commerceChannel.setCompanyId(user.getCompanyId());
		commerceChannel.setUserId(user.getUserId());
		commerceChannel.setUserName(user.getFullName());
		commerceChannel.setName(name);
		commerceChannel.setSiteGroupId(siteGroupId);
		commerceChannel.setType(type);
		commerceChannel.setTypeSettingsProperties(typeSettingsProperties);
		commerceChannel.setCommerceCurrencyCode(commerceCurrencyCode);
		commerceChannel.setExternalReferenceCode(externalReferenceCode);

		commerceChannelPersistence.update(commerceChannel);

		// Group

		Map<Locale, String> nameMap = Collections.singletonMap(
			serviceContext.getLocale(), name);

		groupLocalService.addGroup(
			user.getUserId(), GroupConstants.DEFAULT_PARENT_GROUP_ID,
			CommerceChannel.class.getName(), commerceChannelId,
			GroupConstants.DEFAULT_LIVE_GROUP_ID, nameMap, null,
			GroupConstants.TYPE_SITE_PRIVATE, false,
			GroupConstants.DEFAULT_MEMBERSHIP_RESTRICTION, null, false, true,
			null);

		// Resources

		resourceLocalService.addModelResources(commerceChannel, serviceContext);

		return commerceChannel;
	}

	@Override
	@SystemEvent(type = SystemEventConstants.TYPE_DELETE)
	public CommerceChannel deleteCommerceChannel(
		CommerceChannel commerceChannel) throws PortalException {

		// Commerce channel rel

		commerceChannelRelLocalService.deleteCommerceChannelRels(
			commerceChannel.getCommerceChannelId());

		// Resources

		resourceLocalService.deleteResource(
			commerceChannel, ResourceConstants.SCOPE_INDIVIDUAL);

		// Commerce channel

		return commerceChannelPersistence.remove(commerceChannel);
	}

	@Override
	public CommerceChannel deleteCommerceChannel(long commerceChannelId)
		throws PortalException {

		CommerceChannel commerceChannel =
			commerceChannelPersistence.findByPrimaryKey(commerceChannelId);

		return commerceChannelLocalService.deleteCommerceChannel(
			commerceChannel);
	}

	@Override
	public void deleteCommerceChannels(long companyId) {
		List<CommerceChannel> commerceChannels =
			commerceChannelPersistence.findByCompanyId(companyId);

		for (CommerceChannel commerceChannel : commerceChannels) {
			commerceChannelLocalService.deleteCommerceChannel(commerceChannel);
		}
	}

	@Override
	public CommerceChannel fetchCommerceChannelByGroupId(long siteGroupId) {
		return commerceChannelPersistence.fetchBySiteGroupId(siteGroupId);
	}

	@Override
	public Group getCommerceChannelGroup(long commerceChannelId)
		throws PortalException {

		CommerceChannel commerceChannel =
			commerceChannelLocalService.getCommerceChannel(commerceChannelId);

		long classNameId = classNameLocalService.getClassNameId(
			CommerceChannel.class.getName());

		return groupPersistence.findByC_C_C(
			commerceChannel.getCompanyId(), classNameId, commerceChannelId);
	}

	@Override
	public long getCommerceChannelGroupIdBySiteGroupId(long siteGroupId)
		throws PortalException {

		CommerceChannel commerceChannel =
			commerceChannelPersistence.findBySiteGroupId(siteGroupId);

		Group group = commerceChannelLocalService.getCommerceChannelGroup(
			commerceChannel.getCommerceChannelId());

		return group.getGroupId();
	}

	@Override
	public List<CommerceChannel> getCommerceChannels(long companyId) {
		return commerceChannelPersistence.findByCompanyId(companyId);
	}

	@Override
	public long[] getUsedSiteGroupIds() {
		return ArrayUtil.toLongArray(
			commerceChannelFinder.getUsedSiteGroupIds());
	}

	@Override
	public CommerceChannel updateCommerceChannel(
			long commerceChannelId, String name, String type,
			UnicodeProperties typeSettingsProperties,
			String commerceCurrencyCode)
		throws PortalException {

		CommerceChannel commerceChannel =
			commerceChannelPersistence.findByPrimaryKey(commerceChannelId);

		commerceChannel.setName(name);
		commerceChannel.setSiteGroupId(siteGroupId);
		commerceChannel.setType(type);
		commerceChannel.setTypeSettingsProperties(typeSettingsProperties);
		commerceChannel.setCommerceCurrencyCode(commerceCurrencyCode);

		commerceChannelPersistence.update(commerceChannel);

		return commerceChannel;
	}

}