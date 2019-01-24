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

import com.liferay.commerce.account.constants.CommerceAccountActionKeys;
import com.liferay.commerce.account.constants.CommerceAccountConstants;
import com.liferay.commerce.account.model.CommerceAccount;
import com.liferay.commerce.account.service.CommerceAccountService;
import com.liferay.commerce.account.util.CommerceAccountHelper;
import com.liferay.commerce.account.web.internal.frontend.AccountFilterImpl;
import com.liferay.commerce.constants.CommerceWebKeys;
import com.liferay.commerce.context.CommerceContext;
import com.liferay.commerce.model.CommerceAddress;
import com.liferay.commerce.service.CommerceAddressService;
import com.liferay.commerce.service.CommerceCountryService;
import com.liferay.commerce.service.CommerceRegionService;
import com.liferay.petra.string.StringBundler;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.portlet.LiferayPortletResponse;
import com.liferay.portal.kernel.portlet.LiferayWindowState;
import com.liferay.portal.kernel.security.permission.resource.ModelResourcePermission;
import com.liferay.portal.kernel.security.permission.resource.PortletResourcePermission;
import com.liferay.portal.kernel.service.UserLocalService;
import com.liferay.portal.kernel.service.permission.PortalPermissionUtil;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.ParamUtil;
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
		Portal portal, PortletResourcePermission portletResourcePermission,
		UserFileUploadsConfiguration userFileUploadsConfiguration,
		UserLocalService userLocalService) {

		super(
			commerceAccountHelper, commerceAccountService,
			commerceCountryService, commerceRegionService, httpServletRequest,
			modelResourcePermission, portal);

		_commerceAddressService = commerceAddressService;
		_portletResourcePermission = portletResourcePermission;
		_userFileUploadsConfiguration = userFileUploadsConfiguration;
		_userLocalService = userLocalService;

		_commerceContext = (CommerceContext)httpServletRequest.getAttribute(
			CommerceWebKeys.COMMERCE_CONTEXT);
	}

	public AccountFilterImpl getAccountFilter() throws PortalException {
		AccountFilterImpl accountFilter = new AccountFilterImpl();

		User user = getSelectedUser();

		accountFilter.setAccountId(getCurrentCommerceAccountId());
		accountFilter.setUserId(user.getUserId());

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

	public List<CommerceAccount> getCommerceAccounts() throws PortalException {
		return commerceAccountService.getUserCommerceAccounts(
			commerceAccountRequestHelper.getUserId(),
			CommerceAccountConstants.DEFAULT_PARENT_ACCOUNT_ID,
			_commerceContext.getCommerceSiteType(), StringPool.BLANK,
			QueryUtil.ALL_POS, QueryUtil.ALL_POS);
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

	public User getSelectedUser() throws PortalException {
		long userId = ParamUtil.getLong(
			commerceAccountRequestHelper.getRequest(), "userId");

		if (userId > 0) {
			return _userLocalService.getUser(userId);
		}

		return portal.getUser(commerceAccountRequestHelper.getRequest());
	}

	public UserFileUploadsConfiguration getUserFileUploadsConfiguration() {
		return _userFileUploadsConfiguration;
	}

	public boolean hasManageCommerceAccountPermissions()
		throws PortalException {

		return PortalPermissionUtil.contains(
			commerceAccountRequestHelper.getPermissionChecker(),
			CommerceAccountActionKeys.MANAGE_ACCOUNTS);
	}

	private final CommerceAddressService _commerceAddressService;
	private final CommerceContext _commerceContext;
	private final PortletResourcePermission _portletResourcePermission;
	private final UserFileUploadsConfiguration _userFileUploadsConfiguration;
	private final UserLocalService _userLocalService;

}