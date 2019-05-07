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

import com.liferay.commerce.item.selector.criterion.CatalogItemSelectorCriterion;
import com.liferay.commerce.product.channel.CommerceChannelTypeJSPContributorRegistry;
import com.liferay.commerce.product.channel.CommerceChannelTypeRegistry;
import com.liferay.commerce.product.model.CommerceCatalog;
import com.liferay.commerce.product.model.CommerceChannel;
import com.liferay.commerce.product.service.CommerceCatalogService;
import com.liferay.commerce.product.service.CommerceChannelService;
import com.liferay.item.selector.ItemSelector;
import com.liferay.item.selector.ItemSelectorReturnType;
import com.liferay.item.selector.criteria.UUIDItemSelectorReturnType;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.portlet.RequestBackedPortletURLFactory;
import com.liferay.portal.kernel.portlet.RequestBackedPortletURLFactoryUtil;
import com.liferay.portal.kernel.security.permission.resource.PortletResourcePermission;
import com.liferay.portal.kernel.util.Portal;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.UnicodeProperties;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.portlet.PortletURL;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Alec Sloan
 */
public class CatalogCommerceChannelTypeDisplayContext
	extends CommerceChannelDisplayContext {

	public CatalogCommerceChannelTypeDisplayContext(
			CommerceCatalogService commerceCatalogService,
			CommerceChannelService commerceChannelService,
			CommerceChannelTypeRegistry commerceChannelTypeRegistry,
			CommerceChannelTypeJSPContributorRegistry
				commerceChannelTypeJSPContributorRegistry,
			HttpServletRequest httpServletRequest, ItemSelector itemSelector,
			Portal portal, PortletResourcePermission portletResourcePermission)
		throws PortalException {

		super(
			commerceChannelService, commerceChannelTypeRegistry,
			commerceChannelTypeJSPContributorRegistry, httpServletRequest,
			portal, portletResourcePermission);

		_commerceCatalogService = commerceCatalogService;
		_itemSelector = itemSelector;
	}

	public List<CommerceCatalog> getCatalogs() throws PortalException {
		List<CommerceCatalog> catalogs = new ArrayList<>();

		for (long catalogId : getCheckedCatalogIds()) {
			CommerceCatalog catalog =
				_commerceCatalogService.fetchCommerceCatalog(catalogId);

			if (catalog != null) {
				catalogs.add(catalog);
			}
		}

		return catalogs;
	}

	public String getItemSelectorUrl() throws PortalException {
		RequestBackedPortletURLFactory requestBackedPortletURLFactory =
			RequestBackedPortletURLFactoryUtil.create(httpServletRequest);

		CatalogItemSelectorCriterion catalogItemSelectorCriterion =
			new CatalogItemSelectorCriterion();

		catalogItemSelectorCriterion.setDesiredItemSelectorReturnTypes(
			Collections.<ItemSelectorReturnType>singletonList(
				new UUIDItemSelectorReturnType()));

		PortletURL itemSelectorURL = _itemSelector.getItemSelectorURL(
			requestBackedPortletURLFactory, "catalogsSelectItem",
			catalogItemSelectorCriterion);

		String checkedCatalogIds = StringUtil.merge(getCheckedCatalogIds());

		itemSelectorURL.setParameter("checkedCatalogIds", checkedCatalogIds);

		return itemSelectorURL.toString();
	}

	protected long[] getCheckedCatalogIds() throws PortalException {
		CommerceChannel commerceChannel = getCommerceChannel();

		if (commerceChannel == null) {
			return new long[0];
		}

		UnicodeProperties typeSettingsProperties =
			commerceChannel.getTypeSettingsProperties();

		return StringUtil.split(
			typeSettingsProperties.getProperty("catalogIds"), 0L);
	}

	private final CommerceCatalogService _commerceCatalogService;
	private final ItemSelector _itemSelector;

}