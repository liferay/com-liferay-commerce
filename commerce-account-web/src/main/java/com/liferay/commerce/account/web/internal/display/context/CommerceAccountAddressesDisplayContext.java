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

package com.liferay.commerce.account.web.internal.display.context;

import com.liferay.commerce.account.model.CommerceAccount;
import com.liferay.commerce.account.service.CommerceAccountService;
import com.liferay.commerce.account.util.CommerceAccountHelper;
import com.liferay.commerce.account.web.internal.servlet.taglib.ui.CommerceAccountScreenNavigationConstants;
import com.liferay.commerce.constants.CommerceActionKeys;
import com.liferay.commerce.constants.CommerceWebKeys;
import com.liferay.commerce.model.CommerceAddress;
import com.liferay.commerce.service.CommerceAddressService;
import com.liferay.commerce.service.CommerceCountryService;
import com.liferay.commerce.service.CommerceRegionService;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.language.LanguageUtil;
import com.liferay.portal.kernel.portlet.LiferayPortletResponse;
import com.liferay.portal.kernel.portlet.LiferayWindowState;
import com.liferay.portal.kernel.security.permission.resource.ModelResourcePermission;
import com.liferay.portal.kernel.security.permission.resource.PortletResourcePermission;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.Portal;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.WebKeys;

import javax.portlet.PortletURL;
import javax.portlet.WindowStateException;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Alessio Antonio Rendina
 */
public class CommerceAccountAddressesDisplayContext
	extends BaseCommerceAccountDisplayContext {

	public CommerceAccountAddressesDisplayContext(
		CommerceAccountHelper commerceAccountHelper,
		CommerceAccountService commerceAccountService,
		CommerceAddressService commerceAddressService,
		CommerceCountryService commerceCountryService,
		CommerceRegionService commerceRegionService,
		HttpServletRequest httpServletRequest,
		ModelResourcePermission<CommerceAccount> modelResourcePermission,
		Portal portal, PortletResourcePermission portletResourcePermission) {

		super(
			commerceAccountHelper, commerceAccountService,
			commerceCountryService, commerceRegionService, httpServletRequest,
			modelResourcePermission, portal);

		_commerceAddressService = commerceAddressService;
		_portletResourcePermission = portletResourcePermission;
	}

	public String getAddCommerceAddressHref() throws WindowStateException {
		return getEditCommerceAddressHref(0L);
	}

	public CommerceAddress getCommerceAddress() throws PortalException {
		HttpServletRequest httpServletRequest =
			commerceAccountRequestHelper.getRequest();

		CommerceAddress commerceAddress =
			(CommerceAddress)httpServletRequest.getAttribute(
				CommerceWebKeys.COMMERCE_ADDRESS);

		if (commerceAddress != null) {
			return commerceAddress;
		}

		long commerceAddressId = ParamUtil.getLong(
			httpServletRequest, "commerceAddressId");

		if (commerceAddressId > 0) {
			commerceAddress = _commerceAddressService.fetchCommerceAddress(
				commerceAddressId);
		}

		if (commerceAddress != null) {
			httpServletRequest.setAttribute(
				CommerceWebKeys.COMMERCE_ADDRESS, commerceAddress);
		}

		return commerceAddress;
	}

	public long getCommerceAddressId() throws PortalException {
		CommerceAddress commerceAddress = getCommerceAddress();

		if (commerceAddress == null) {
			return 0;
		}

		return commerceAddress.getCommerceAddressId();
	}

	public long getCommerceCountryId() throws PortalException {
		long commerceCountryId = 0;

		CommerceAddress commerceAddress = getCommerceAddress();

		if (commerceAddress != null) {
			commerceCountryId = commerceAddress.getCommerceCountryId();
		}

		return commerceCountryId;
	}

	public long getCommerceRegionId() throws PortalException {
		long commerceRegionId = 0;

		CommerceAddress commerceAddress = getCommerceAddress();

		if (commerceAddress != null) {
			commerceRegionId = commerceAddress.getCommerceRegionId();
		}

		return commerceRegionId;
	}

	public String getEditCommerceAddressHref(long commerceAddressId)
		throws WindowStateException {

		HttpServletRequest httpServletRequest =
			commerceAccountRequestHelper.getRequest();
		LiferayPortletResponse liferayPortletResponse =
			commerceAccountRequestHelper.getLiferayPortletResponse();

		ThemeDisplay themeDisplay =
			(ThemeDisplay)httpServletRequest.getAttribute(
				WebKeys.THEME_DISPLAY);

		String title = LanguageUtil.get(httpServletRequest, "add-address");

		PortletURL portletURL = liferayPortletResponse.createRenderURL();

		portletURL.setParameter("mvcRenderCommandName", "editCommerceAddress");
		portletURL.setParameter("redirect", themeDisplay.getURLCurrent());

		if (commerceAddressId > 0) {
			title = LanguageUtil.get(httpServletRequest, "edit-address");

			portletURL.setParameter(
				"commerceAddressId", String.valueOf(commerceAddressId));
		}

		portletURL.setWindowState(LiferayWindowState.POP_UP);

		StringBundler sb = new StringBundler(11);

		sb.append("javascript:editCommerceAddress");
		sb.append(StringPool.OPEN_PARENTHESIS);
		sb.append(StringPool.APOSTROPHE);
		sb.append(title);
		sb.append(StringPool.APOSTROPHE);
		sb.append(StringPool.COMMA_AND_SPACE);
		sb.append(StringPool.APOSTROPHE);
		sb.append(portletURL.toString());
		sb.append(StringPool.APOSTROPHE);
		sb.append(StringPool.CLOSE_PARENTHESIS);
		sb.append(StringPool.SEMICOLON);

		return sb.toString();
	}

	public String getKeywords() {
		if (_keywords != null) {
			return _keywords;
		}

		_keywords = ParamUtil.getString(
			commerceAccountRequestHelper.getRequest(), "keywords");

		return _keywords;
	}

	@Override
	public PortletURL getPortletURL() throws PortalException {
		PortletURL portletURL = super.getPortletURL();

		portletURL.setParameter(
			"screenNavigationCategoryKey",
			CommerceAccountScreenNavigationConstants.CATEGORY_DETAILS);

		portletURL.setParameter(
			"screenNavigationEntryKey",
			CommerceAccountScreenNavigationConstants.
				ENTRY_KEY_ACCOUNT_ADDRESSES);

		return portletURL;
	}

	public boolean hasManageCommerceAddressPermission() {
		return _portletResourcePermission.contains(
			commerceAccountRequestHelper.getPermissionChecker(),
			commerceAccountRequestHelper.getScopeGroupId(),
			CommerceActionKeys.MANAGE_COMMERCE_ADDRESSES);
	}

	private final CommerceAddressService _commerceAddressService;
	private String _keywords;
	private final PortletResourcePermission _portletResourcePermission;

}