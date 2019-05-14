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

import com.liferay.commerce.product.model.CommerceChannel;
import com.liferay.commerce.product.service.CommerceCatalogService;
import com.liferay.commerce.product.service.CommerceChannelService;
import com.liferay.item.selector.ItemSelector;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.security.permission.resource.PortletResourcePermission;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.UnicodeProperties;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Alec Sloan
 */
public class CatalogCommerceChannelJSPDisplayContext
	extends BaseCatalogJSPContributorDisplayContext {

	public CatalogCommerceChannelJSPDisplayContext(
			CommerceCatalogService commerceCatalogService,
			CommerceChannelService commerceChannelService,
			HttpServletRequest httpServletRequest, ItemSelector itemSelector,
			PortletResourcePermission portletResourcePermission)
		throws PortalException {

		super(
			commerceCatalogService, httpServletRequest, itemSelector,
			portletResourcePermission);

		_commerceCatalogService = commerceCatalogService;
		_commerceChannelService = commerceChannelService;
		_httpServletRequest = httpServletRequest;
		_itemSelector = itemSelector;
	}

	@Override
	public long[] getCheckedCatalogIds() throws PortalException {
		CommerceChannel commerceChannel = getCommerceChannel();

		if (commerceChannel == null) {
			return new long[0];
		}

		UnicodeProperties typeSettingsProperties =
			commerceChannel.getTypeSettingsProperties();

		return StringUtil.split(
			typeSettingsProperties.getProperty("catalogIds"), 0L);
	}

	public CommerceChannel getCommerceChannel() throws PortalException {
		long commerceChannelId = ParamUtil.getLong(
			_httpServletRequest, "commerceChannelId");

		return _commerceChannelService.fetchCommerceChannel(commerceChannelId);
	}

	private final CommerceCatalogService _commerceCatalogService;
	private final CommerceChannelService _commerceChannelService;
	private final HttpServletRequest _httpServletRequest;
	private final ItemSelector _itemSelector;

}