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

import com.liferay.commerce.currency.model.CommerceCurrency;
import com.liferay.commerce.currency.service.CommerceCurrencyService;
import com.liferay.commerce.product.channel.CommerceChannelType;
import com.liferay.commerce.product.channel.CommerceChannelTypeJSPContributor;
import com.liferay.commerce.product.channel.CommerceChannelTypeJSPContributorRegistry;
import com.liferay.commerce.product.channel.CommerceChannelTypeRegistry;
import com.liferay.commerce.product.constants.CPActionKeys;
import com.liferay.commerce.product.constants.CPPortletKeys;
import com.liferay.commerce.product.model.CommerceChannel;
import com.liferay.commerce.product.service.CommerceChannelService;
import com.liferay.commerce.product.util.CPUtil;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.dao.search.SearchContainer;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.search.Sort;
import com.liferay.portal.kernel.security.permission.resource.ModelResourcePermission;
import com.liferay.portal.kernel.service.permission.PortalPermissionUtil;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.Portal;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.util.WebKeys;

import java.util.List;

import javax.portlet.PortletRequest;
import javax.portlet.PortletURL;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Alec Sloan
 * @author Alessio Antonio Rendina
 */
public class CommerceChannelDisplayContext
	extends BaseCommerceChannelSearchContainerDisplayContext<CommerceChannel> {

	public CommerceChannelDisplayContext(
		ModelResourcePermission<CommerceChannel>
			commerceChannelModelResourcePermission,
		CommerceChannelService commerceChannelService,
		CommerceChannelTypeRegistry commerceChannelTypeRegistry,
		CommerceChannelTypeJSPContributorRegistry
			commerceChannelTypeJSPContributorRegistry,
		CommerceCurrencyService commerceCurrencyService,
		HttpServletRequest httpServletRequest, Portal portal) {

		super(httpServletRequest, CommerceChannel.class.getSimpleName());

		setDefaultOrderByType("desc");

		_commerceChannelModelResourcePermission =
			commerceChannelModelResourcePermission;
		_commerceChannelService = commerceChannelService;
		_commerceChannelTypeRegistry = commerceChannelTypeRegistry;
		_commerceChannelTypeJSPContributorRegistry =
			commerceChannelTypeJSPContributorRegistry;
		_commerceCurrencyService = commerceCurrencyService;
		_portal = portal;
	}

	public String getChannelURL(CommerceChannel commerceChannel)
		throws PortalException {

		if (commerceChannel == null) {
			return StringPool.BLANK;
		}

		PortletURL portletURL = _portal.getControlPanelPortletURL(
			httpServletRequest, CPPortletKeys.COMMERCE_CHANNELS,
			PortletRequest.RENDER_PHASE);

		portletURL.setParameter("backURL", portletURL.toString());

		portletURL.setParameter(
			"commerceChannelId",
			String.valueOf(commerceChannel.getCommerceChannelId()));
		portletURL.setParameter("mvcRenderCommandName", "editCommerceChannel");

		return portletURL.toString();
	}

	public CommerceChannel getCommerceChannel() throws PortalException {
		long commerceChannelId = ParamUtil.getLong(
			httpServletRequest, "commerceChannelId");

		if (commerceChannelId == 0) {
			return null;
		}

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

	public List<CommerceCurrency> getCommerceCurrencies()
		throws PortalException {

		return _commerceCurrencyService.getCommerceCurrencies(
			cpRequestHelper.getCompanyId(), true, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
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

		ThemeDisplay themeDisplay =
			(ThemeDisplay)httpServletRequest.getAttribute(
				WebKeys.THEME_DISPLAY);

		Sort sort = CPUtil.getCommerceChannelSort(
			getOrderByCol(), getOrderByType());

		List<CommerceChannel> commerceChannels =
			_commerceChannelService.searchCommerceChannels(
				themeDisplay.getCompanyId(), getKeywords(),
				searchContainer.getStart(), searchContainer.getEnd(), sort);

		searchContainer.setTotal(commerceChannels.size());
		searchContainer.setResults(commerceChannels);

		return searchContainer;
	}

	public boolean hasAddChannelPermission() {
		ThemeDisplay themeDisplay =
			(ThemeDisplay)httpServletRequest.getAttribute(
				WebKeys.THEME_DISPLAY);

		return PortalPermissionUtil.contains(
			themeDisplay.getPermissionChecker(),
			CPActionKeys.ADD_COMMERCE_CHANNEL);
	}

	public boolean hasPermission(long commerceChannelId, String actionId)
		throws PortalException {

		ThemeDisplay themeDisplay =
			(ThemeDisplay)httpServletRequest.getAttribute(
				WebKeys.THEME_DISPLAY);

		return _commerceChannelModelResourcePermission.contains(
			themeDisplay.getPermissionChecker(), commerceChannelId, actionId);
	}

	private final ModelResourcePermission<CommerceChannel>
		_commerceChannelModelResourcePermission;
	private final CommerceChannelService _commerceChannelService;
	private final CommerceChannelTypeJSPContributorRegistry
		_commerceChannelTypeJSPContributorRegistry;
	private final CommerceChannelTypeRegistry _commerceChannelTypeRegistry;
	private final CommerceCurrencyService _commerceCurrencyService;
	private final Portal _portal;

}