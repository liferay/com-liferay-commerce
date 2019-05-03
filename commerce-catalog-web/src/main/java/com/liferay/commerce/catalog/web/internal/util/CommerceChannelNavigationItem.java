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

import com.liferay.commerce.product.constants.CPPortletKeys;
import com.liferay.commerce.product.util.ChannelNavigationItem;
import com.liferay.frontend.taglib.clay.servlet.taglib.util.NavigationItem;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.language.LanguageUtil;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.ParamUtil;
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
		"commerce.channel.navigation.item.key=" + CPPortletKeys.COMMERCE_CHANNELS,
		"commerce.channel.navigation.item.order:Integer=10"
	},
	service = ChannelNavigationItem.class
)
public class CommerceChannelNavigationItem implements ChannelNavigationItem {

	@Override
	public NavigationItem getNavigationItem(PortletRequest portletRequest)
		throws PortalException {

		NavigationItem navigationItem = new NavigationItem();

		String portletId = _portal.getPortletId(portletRequest);

		navigationItem.setActive(
			portletId.equals(CPPortletKeys.COMMERCE_CHANNELS));

		PortletURL portletURL = _portal.getControlPanelPortletURL(
			portletRequest, CPPortletKeys.COMMERCE_CHANNELS,
			PortletRequest.RENDER_PHASE);

		String commerceChannelId = ParamUtil.getString(
			portletRequest, "commerceChannelId");

		portletURL.setParameter("commerceChannelId", commerceChannelId);

		portletURL.setParameter("mvcRenderCommandName", "editCommerceChannel");

		navigationItem.setHref(portletURL.toString());

		ThemeDisplay themeDisplay = (ThemeDisplay)portletRequest.getAttribute(
			WebKeys.THEME_DISPLAY);

		ResourceBundle resourceBundle = ResourceBundleUtil.getBundle(
			"content.Language", themeDisplay.getLocale(), getClass());

		navigationItem.setLabel(LanguageUtil.get(resourceBundle, "overview"));

		return navigationItem;
	}

	@Reference
	private Portal _portal;

}