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

package com.liferay.commerce.frontend.internal.search.util;

import com.liferay.commerce.product.constants.CPPortletKeys;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.model.Layout;
import com.liferay.portal.kernel.model.LayoutSet;
import com.liferay.portal.kernel.service.LayoutLocalService;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.Portal;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Marco Leo
 */
@Component(service = CommerceSearchUtil.class)
public class CommerceSearchUtil {

	public String getCatalogFriendlyURL(ThemeDisplay themeDisplay)
		throws PortalException {

		LayoutSet layoutSet = themeDisplay.getLayoutSet();

		Layout layout = _getCatalogLayout(
			themeDisplay.getScopeGroupId(), layoutSet.isPrivateLayout());

		if (layout == null) {
			return null;
		}

		return _portal.getLayoutFriendlyURL(layout, themeDisplay);
	}

	public String getSearchFriendlyURL(ThemeDisplay themeDisplay)
		throws PortalException {

		LayoutSet layoutSet = themeDisplay.getLayoutSet();

		Layout layout = _getSearchLayout(
			themeDisplay.getScopeGroupId(), layoutSet.isPrivateLayout());

		if (layout == null) {
			return null;
		}

		return _portal.getLayoutFriendlyURL(layout, themeDisplay);
	}

	private Layout _getCatalogLayout(long groupId, boolean privateLayout)
		throws PortalException {

		Layout layout = _layoutLocalService.fetchLayoutByFriendlyURL(
			groupId, privateLayout, "/catalog");

		if (layout != null) {
			return layout;
		}

		long plid = _portal.getPlidFromPortletId(
			groupId, CPPortletKeys.CP_SEARCH_RESULTS);

		if (plid > 0) {
			layout = _layoutLocalService.fetchLayout(plid);
		}

		return layout;
	}

	private Layout _getSearchLayout(long groupId, boolean privateLayout) {
		return _layoutLocalService.fetchLayoutByFriendlyURL(
			groupId, privateLayout, "/search");
	}

	@Reference
	private LayoutLocalService _layoutLocalService;

	@Reference
	private Portal _portal;

}