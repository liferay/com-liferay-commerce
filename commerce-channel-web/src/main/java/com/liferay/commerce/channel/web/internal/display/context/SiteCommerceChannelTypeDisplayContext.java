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

package com.liferay.commerce.channel.web.internal.display.context;

import com.liferay.commerce.currency.service.CommerceCurrencyService;
import com.liferay.commerce.item.selector.criterion.SimpleSiteItemSelectorCriterion;
import com.liferay.commerce.product.channel.CommerceChannelTypeJSPContributorRegistry;
import com.liferay.commerce.product.channel.CommerceChannelTypeRegistry;
import com.liferay.commerce.product.model.CommerceChannel;
import com.liferay.commerce.product.service.CommerceChannelService;
import com.liferay.item.selector.ItemSelector;
import com.liferay.item.selector.ItemSelectorReturnType;
import com.liferay.item.selector.criteria.UUIDItemSelectorReturnType;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.model.Group;
import com.liferay.portal.kernel.portlet.RequestBackedPortletURLFactory;
import com.liferay.portal.kernel.portlet.RequestBackedPortletURLFactoryUtil;
import com.liferay.portal.kernel.security.permission.resource.ModelResourcePermission;
import com.liferay.portal.kernel.service.GroupLocalService;
import com.liferay.portal.kernel.util.Portal;

import java.util.Collections;

import javax.portlet.PortletURL;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Alec Sloan
 */
public class SiteCommerceChannelTypeDisplayContext
	extends CommerceChannelDisplayContext {

	public SiteCommerceChannelTypeDisplayContext(
		ModelResourcePermission<CommerceChannel>
			commerceChannelModelResourcePermission,
		CommerceChannelService commerceChannelService,
		CommerceChannelTypeRegistry commerceChannelTypeRegistry,
		CommerceChannelTypeJSPContributorRegistry
			commerceChannelTypeJSPContributorRegistry,
		CommerceCurrencyService commerceCurrencyService,
		GroupLocalService groupLocalService,
		HttpServletRequest httpServletRequest, ItemSelector itemSelector,
		Portal portal) {

		super(
			commerceChannelModelResourcePermission, commerceChannelService,
			commerceChannelTypeRegistry,
			commerceChannelTypeJSPContributorRegistry, commerceCurrencyService,
			httpServletRequest, portal);

		_groupLocalService = groupLocalService;
		_itemSelector = itemSelector;
	}

	public Group getChannelSite() throws PortalException {
		CommerceChannel commerceChannel = getCommerceChannel();

		if (commerceChannel == null) {
			return null;
		}

		return _groupLocalService.fetchGroup(commerceChannel.getSiteGroupId());
	}

	public String getItemSelectorUrl() throws PortalException {
		RequestBackedPortletURLFactory requestBackedPortletURLFactory =
			RequestBackedPortletURLFactoryUtil.create(httpServletRequest);

		SimpleSiteItemSelectorCriterion simpleSiteItemSelectorCriterion =
			new SimpleSiteItemSelectorCriterion();

		simpleSiteItemSelectorCriterion.setDesiredItemSelectorReturnTypes(
			Collections.<ItemSelectorReturnType>singletonList(
				new UUIDItemSelectorReturnType()));

		PortletURL itemSelectorURL = _itemSelector.getItemSelectorURL(
			requestBackedPortletURLFactory, "sitesSelectItem",
			simpleSiteItemSelectorCriterion);

		return itemSelectorURL.toString();
	}

	private final GroupLocalService _groupLocalService;
	private final ItemSelector _itemSelector;

}