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

package com.liferay.commerce.health.status.web.internal.display.context;

import com.liferay.commerce.constants.CommerceActionKeys;
import com.liferay.commerce.health.status.CommerceHealthStatus;
import com.liferay.commerce.health.status.web.internal.admin.GroupInstanceHealthCheckCommerceAdminModule;
import com.liferay.commerce.health.status.web.internal.util.CommerceHealthStatusRegistry;
import com.liferay.portal.kernel.dao.search.SearchContainer;
import com.liferay.portal.kernel.security.permission.resource.PortletResourcePermission;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.WebKeys;

import java.util.List;

import javax.portlet.PortletURL;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

/**
 * @author Alessio Antonio Rendina
 */
public class CommerceHealthStatusDisplayContext {

	public CommerceHealthStatusDisplayContext(
		CommerceHealthStatusRegistry commerceHealthStatusRegistry,
		PortletResourcePermission portletResourcePermission,
		RenderRequest renderRequest, RenderResponse renderResponse, int type) {

		_commerceHealthStatusRegistry = commerceHealthStatusRegistry;
		_portletResourcePermission = portletResourcePermission;
		_renderRequest = renderRequest;
		_renderResponse = renderResponse;
		_type = type;
	}

	public List<CommerceHealthStatus> getCommerceHealthStatuses() {
		return _commerceHealthStatusRegistry.getCommerceHealthStatuses(_type);
	}

	public PortletURL getPortletURL() {
		PortletURL portletURL = _renderResponse.createRenderURL();

		portletURL.setParameter(
			"commerceAdminModuleKey",
			GroupInstanceHealthCheckCommerceAdminModule.KEY);

		return portletURL;
	}

	public SearchContainer<CommerceHealthStatus> getSearchContainer() {
		if (_searchContainer != null) {
			return _searchContainer;
		}

		_searchContainer = new SearchContainer<>(
			_renderRequest, getPortletURL(), null, "there-are-no-results");

		List<CommerceHealthStatus> results = getCommerceHealthStatuses();

		_searchContainer.setResults(results);
		_searchContainer.setTotal(results.size());

		return _searchContainer;
	}

	public boolean hasManageCommerceHealthStatusPermission() {
		ThemeDisplay themeDisplay = (ThemeDisplay)_renderRequest.getAttribute(
			WebKeys.THEME_DISPLAY);

		return _portletResourcePermission.contains(
			themeDisplay.getPermissionChecker(), themeDisplay.getScopeGroupId(),
			CommerceActionKeys.MANAGE_COMMERCE_HEALTH_STATUS);
	}

	private final CommerceHealthStatusRegistry _commerceHealthStatusRegistry;
	private final PortletResourcePermission _portletResourcePermission;
	private final RenderRequest _renderRequest;
	private final RenderResponse _renderResponse;
	private SearchContainer<CommerceHealthStatus> _searchContainer;
	private final int _type;

}