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

package com.liferay.commerce.catalog.web.internal.util;

import com.liferay.commerce.product.constants.CPActionKeys;
import com.liferay.commerce.product.constants.CPConstants;
import com.liferay.commerce.product.constants.CPPortletKeys;
import com.liferay.commerce.product.util.CPNavigationItem;
import com.liferay.frontend.taglib.clay.servlet.taglib.util.NavigationItem;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.language.LanguageUtil;
import com.liferay.portal.kernel.portlet.PortletURLFactory;
import com.liferay.portal.kernel.security.permission.resource.PortletResourcePermission;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.Portal;
import com.liferay.portal.kernel.util.ResourceBundleUtil;
import com.liferay.portal.kernel.util.WebKeys;

import java.util.ResourceBundle;

import javax.portlet.PortletRequest;
import javax.portlet.PortletURL;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Alec Sloan
 */
@Component(
	immediate = true,
	property = {
		"commerce.product.navigation.item.key=" + CPPortletKeys.COMMERCE_CATALOGS,
		"commerce.product.navigation.item.order:Integer=10"
	},
	service = CPNavigationItem.class
)
public class CommerceCatalogNavigationItem implements CPNavigationItem {

	public NavigationItem getNavigationItem(PortletRequest portletRequest)
		throws PortalException {

		ThemeDisplay themeDisplay = (ThemeDisplay)portletRequest.getAttribute(
			WebKeys.THEME_DISPLAY);

		boolean manageCatalogPermission = _portletResourcePermission.contains(
			themeDisplay.getPermissionChecker(), themeDisplay.getScopeGroupId(),
			CPActionKeys.MANAGE_CATALOG);

		if (!manageCatalogPermission) {
			return null;
		}

		NavigationItem navigationItem = new NavigationItem();

		String portletId = _portal.getPortletId(portletRequest);

		navigationItem.setActive(
			portletId.equals(CPPortletKeys.COMMERCE_CATALOGS));

		PortletURL portletURL = _portletURLFactory.create(
			portletRequest, CPPortletKeys.COMMERCE_CATALOGS,
			PortletRequest.RENDER_PHASE);

		portletURL.setParameter("mvcRenderCommandName", "editCommerceCatalog");
		portletURL.setParameter("redirect", themeDisplay.getURLCurrent());

		navigationItem.setHref(portletURL.toString());

		ResourceBundle resourceBundle = ResourceBundleUtil.getBundle(
			"content.Language", themeDisplay.getLocale(), getClass());

		navigationItem.setLabel(LanguageUtil.get(resourceBundle, "overview"));

		return navigationItem;
	}

	@Reference
	private Portal _portal;

	@Reference(target = "(resource.name=" + CPConstants.RESOURCE_NAME + ")")
	private PortletResourcePermission _portletResourcePermission;

	@Reference
	private PortletURLFactory _portletURLFactory;

}