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

package com.liferay.commerce.catalog.web.internal.display.context;

import com.liferay.commerce.catalog.web.display.context.BaseCommerceCatalogSearchContainerDisplayContext;
import com.liferay.commerce.product.channel.CommerceChannelType;
import com.liferay.commerce.product.channel.CommerceChannelTypeJSPContributor;
import com.liferay.commerce.product.channel.CommerceChannelTypeJSPContributorRegistry;
import com.liferay.commerce.product.channel.CommerceChannelTypeRegistry;
import com.liferay.commerce.product.constants.CPPortletKeys;
import com.liferay.commerce.product.model.CommerceChannel;
import com.liferay.commerce.product.service.CommerceChannelService;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.dao.search.SearchContainer;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.security.permission.resource.PortletResourcePermission;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.Portal;
import com.liferay.portal.kernel.util.Validator;

import java.util.List;

import javax.portlet.PortletRequest;
import javax.portlet.PortletURL;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Alec Sloan
 */
public class CommerceChannelDisplayContext
	extends BaseCommerceCatalogSearchContainerDisplayContext<CommerceChannel> {

	public CommerceChannelDisplayContext(
			CommerceChannelService commerceChannelService,
			CommerceChannelTypeRegistry commerceChannelTypeRegistry,
			CommerceChannelTypeJSPContributorRegistry
				commerceChannelTypeJSPContributorRegistry,
			HttpServletRequest httpServletRequest, Portal portal,
			PortletResourcePermission portletResourcePermission)
		throws PortalException {

		super(httpServletRequest, CommerceChannel.class.getSimpleName());

		setDefaultOrderByType("desc");

		_commerceChannelService = commerceChannelService;
		_commerceChannelTypeRegistry = commerceChannelTypeRegistry;
		_commerceChannelTypeJSPContributorRegistry =
			commerceChannelTypeJSPContributorRegistry;
		_httpServletRequest = httpServletRequest;
		_portal = portal;
		_portletResourcePermission = portletResourcePermission;
	}

	public String getChannelURL(CommerceChannel commerceChannel)
		throws PortalException {

		if (commerceChannel == null) {
			return StringPool.BLANK;
		}

		PortletURL portletURL = _portal.getControlPanelPortletURL(
			_httpServletRequest, CPPortletKeys.COMMERCE_CHANNELS,
			PortletRequest.RENDER_PHASE);

		portletURL.setParameter("mvcRenderCommandName", "editCommerceChannel");
		portletURL.setParameter(
			"commerceChannelId",
			String.valueOf(commerceChannel.getCommerceChannelId()));

		return portletURL.toString();
	}

	public CommerceChannel getCommerceChannel() throws PortalException {
		long commerceChannelId = ParamUtil.getLong(
			_httpServletRequest, "commerceChannelId");

		return _commerceChannelService.fetchCommerceChannel(commerceChannelId);
	}

	public long getCommerceChannelId() throws PortalException {
		CommerceChannel commerceChannel = getCommerceChannel();

		if (commerceChannel == null) {
			return 0;
		}

		return commerceChannel.getCommerceChannelId();
	}

	public CommerceChannelTypeJSPContributor
		getCommerceChannelTypeJSPContributor(String key) {

		return _commerceChannelTypeJSPContributorRegistry.
			getCommerceChannelTypeJSPContributor(key);
	}

	public List<CommerceChannelType> getCommerceChannelTypes() {
		return _commerceChannelTypeRegistry.getCommerceChannelTypes();
	}

	@Override
	public PortletURL getPortletURL() throws PortalException {
		PortletURL portletURL = super.getPortletURL();

		String filterFields = ParamUtil.getString(
			httpServletRequest, "filterFields");

		if (Validator.isNotNull(filterFields)) {
			portletURL.setParameter("filterFields", filterFields);
		}

		String filtersLabels = ParamUtil.getString(
			httpServletRequest, "filtersLabels");

		if (Validator.isNotNull(filtersLabels)) {
			portletURL.setParameter("filtersLabels", filtersLabels);
		}

		String filtersValues = ParamUtil.getString(
			httpServletRequest, "filtersValues");

		if (Validator.isNotNull(filtersValues)) {
			portletURL.setParameter("filtersValues", filtersValues);
		}

		return portletURL;
	}

	@Override
	public SearchContainer<CommerceChannel> getSearchContainer()
		throws PortalException {

		if (searchContainer != null) {
			return searchContainer;
		}

		searchContainer = new SearchContainer<>(
			liferayPortletRequest, getPortletURL(), null, null);

		searchContainer.setOrderByCol(getOrderByCol());
		searchContainer.setOrderByType(getOrderByType());
		searchContainer.setRowChecker(getRowChecker());

		List<CommerceChannel> channels =
			_commerceChannelService.getCommerceChannels(
				QueryUtil.ALL_POS, QueryUtil.ALL_POS);

		searchContainer.setTotal(channels.size());
		searchContainer.setResults(channels);

		return searchContainer;
	}

	private final CommerceChannelService _commerceChannelService;
	private final CommerceChannelTypeJSPContributorRegistry
		_commerceChannelTypeJSPContributorRegistry;
	private final CommerceChannelTypeRegistry _commerceChannelTypeRegistry;
	private final HttpServletRequest _httpServletRequest;
	private final Portal _portal;
	private final PortletResourcePermission _portletResourcePermission;

}