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
import com.liferay.commerce.account.web.internal.frontend.AccountFilterImpl;
import com.liferay.commerce.model.CommerceAddress;
import com.liferay.commerce.service.CommerceAddressService;
import com.liferay.commerce.service.CommerceCountryService;
import com.liferay.commerce.service.CommerceRegionService;
import com.liferay.petra.string.StringBundler;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.portlet.LiferayPortletResponse;
import com.liferay.portal.kernel.portlet.LiferayWindowState;
import com.liferay.portal.kernel.security.permission.resource.ModelResourcePermission;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.Portal;
import com.liferay.portal.kernel.util.WebKeys;
import com.liferay.portal.kernel.webserver.WebServerServletTokenUtil;
import com.liferay.users.admin.configuration.UserFileUploadsConfiguration;

import java.util.List;

import javax.portlet.PortletURL;
import javax.portlet.WindowStateException;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Marco Leo
 * @author Alessio Antonio Rendina
 */
public class CommerceAccountDisplayContext
	extends BaseCommerceAccountDisplayContext {

	public CommerceAccountDisplayContext(
		CommerceAccountHelper commerceAccountHelper,
		CommerceAccountService commerceAccountService,
		CommerceAddressService commerceAddressService,
		CommerceCountryService commerceCountryService,
		CommerceRegionService commerceRegionService,
		HttpServletRequest httpServletRequest,
		ModelResourcePermission<CommerceAccount> modelResourcePermission,
		Portal portal,
		UserFileUploadsConfiguration userFileUploadsConfiguration) {

		super(
			commerceAccountHelper, commerceAccountService,
			commerceCountryService, commerceRegionService, httpServletRequest,
			modelResourcePermission, portal);

		_commerceAddressService = commerceAddressService;
		_userFileUploadsConfiguration = userFileUploadsConfiguration;
	}

	public AccountFilterImpl getAccountFilter() throws PortalException {
		AccountFilterImpl accountFilter = new AccountFilterImpl();

		HttpServletRequest httpServletRequest =
			commerceAccountRequestHelper.getRequest();

		boolean filterPerAcount = (boolean)httpServletRequest.getAttribute(
			"view.jsp-filterPerAccount");

		if (filterPerAcount) {
			accountFilter.setAccountId(getCurrentCommerceAccountId());
		}

		accountFilter.setKeywords(getKeywords());

		return accountFilter;
	}

	public String getAddCommerceAccountHref() throws WindowStateException {
		HttpServletRequest httpServletRequest =
			commerceAccountRequestHelper.getRequest();
		LiferayPortletResponse liferayPortletResponse =
			commerceAccountRequestHelper.getLiferayPortletResponse();

		ThemeDisplay themeDisplay =
			(ThemeDisplay)httpServletRequest.getAttribute(
				WebKeys.THEME_DISPLAY);

		PortletURL portletURL = liferayPortletResponse.createRenderURL();

		portletURL.setParameter("mvcRenderCommandName", "addCommerceAccount");
		portletURL.setParameter("redirect", themeDisplay.getURLCurrent());

		portletURL.setWindowState(LiferayWindowState.POP_UP);

		StringBundler sb = new StringBundler(9);

		sb.append("javascript:");
		sb.append(liferayPortletResponse.getNamespace());
		sb.append("addCommerceAccount");
		sb.append(StringPool.OPEN_PARENTHESIS);
		sb.append(StringPool.APOSTROPHE);
		sb.append(portletURL.toString());
		sb.append(StringPool.APOSTROPHE);
		sb.append(StringPool.CLOSE_PARENTHESIS);
		sb.append(StringPool.SEMICOLON);

		return sb.toString();
	}

	public CommerceAddress getDefaultBillingCommerceAddress()
		throws PortalException {

		CommerceAccount commerceAccount = getCurrentCommerceAccount();

		if (commerceAccount == null) {
			return null;
		}

		List<CommerceAddress> commerceAddresses =
			_commerceAddressService.getCommerceAddresses(
				commerceAccountRequestHelper.getScopeGroupId(),
				CommerceAccount.class.getName(),
				commerceAccount.getCommerceAccountId());

		for (CommerceAddress commerceAddress : commerceAddresses) {
			if (commerceAddress.isDefaultBilling()) {
				return commerceAddress;
			}
		}

		return null;
	}

	public String getLogo(CommerceAccount commerceAccount) {
		ThemeDisplay themeDisplay =
			commerceAccountRequestHelper.getThemeDisplay();

		StringBundler sb = new StringBundler(5);

		sb.append(themeDisplay.getPathImage());
		sb.append("/organization_logo?img_id=");
		sb.append(commerceAccount.getLogoId());
		sb.append("&t=");
		sb.append(
			WebServerServletTokenUtil.getToken(commerceAccount.getLogoId()));

		return sb.toString();
	}

	public UserFileUploadsConfiguration getUserFileUploadsConfiguration() {
		return _userFileUploadsConfiguration;
	}

	private final CommerceAddressService _commerceAddressService;
	private final UserFileUploadsConfiguration _userFileUploadsConfiguration;

}