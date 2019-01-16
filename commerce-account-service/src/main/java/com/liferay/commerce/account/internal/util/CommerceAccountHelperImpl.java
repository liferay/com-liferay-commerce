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

package com.liferay.commerce.account.internal.util;

import com.liferay.commerce.account.configuration.CommerceAccountGroupServiceConfiguration;
import com.liferay.commerce.account.constants.CommerceAccountConstants;
import com.liferay.commerce.account.model.CommerceAccount;
import com.liferay.commerce.account.service.CommerceAccountLocalService;
import com.liferay.commerce.account.service.CommerceAccountService;
import com.liferay.commerce.account.util.CommerceAccountHelper;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.module.configuration.ConfigurationProvider;
import com.liferay.portal.kernel.portlet.PortletURLFactory;
import com.liferay.portal.kernel.util.Portal;
import com.liferay.portal.kernel.util.SessionParamUtil;

import java.util.List;

import javax.portlet.PortletRequest;
import javax.portlet.PortletURL;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Marco Leo
 * @author Alessio Antonio Rendina
 */
@Component(immediate = true, service = CommerceAccountHelper.class)
public class CommerceAccountHelperImpl implements CommerceAccountHelper {

	@Override
	public String getCommerceUserPortletURL(
			HttpServletRequest httpServletRequest)
		throws PortalException {

		long groupId = _portal.getScopeGroupId(httpServletRequest);

		long plid = _portal.getPlidFromPortletId(
			groupId, _COMMERCE_USER_PORTLET_ID);

		if (plid > 0) {
			PortletURL portletURL = _portletURLFactory.create(
				httpServletRequest, _COMMERCE_USER_PORTLET_ID, plid,
				PortletRequest.RENDER_PHASE);

			return portletURL.toString();
		}

		return StringPool.BLANK;
	}

	@Override
	public CommerceAccount getCurrentCommerceAccount(
			HttpServletRequest httpServletRequest)
		throws PortalException {

		return getCurrentCommerceAccount(
			_portal.getScopeGroupId(httpServletRequest), httpServletRequest);
	}

	@Override
	public CommerceAccount getCurrentCommerceAccount(
			long groupId, HttpServletRequest httpServletRequest)
		throws PortalException {

		httpServletRequest = _portal.getOriginalServletRequest(
			httpServletRequest);

		CommerceAccount commerceAccount = null;

		String curGroupCommerceAccountIdKey =
			_CURRENT_COMMERCE_ACCOUNT_ID_KEY + groupId;

		long currentCommerceAccountId = SessionParamUtil.getLong(
			httpServletRequest, curGroupCommerceAccountIdKey);

		if (currentCommerceAccountId == -1) {
			return null;
		}
		else if (currentCommerceAccountId == 0) {
			commerceAccount = _getSingleCommerceAccount(
				groupId, httpServletRequest);

			if (commerceAccount == null) {
				setCurrentCommerceAccount(httpServletRequest, groupId, -1);
			}
		}
		else if (currentCommerceAccountId > 0) {
			commerceAccount = _commerceAccountService.fetchCommerceAccount(
				currentCommerceAccountId);
		}

		return commerceAccount;
	}

	@Override
	public void setCurrentCommerceAccount(
			HttpServletRequest httpServletRequest, long groupId,
			long commerceAccountId)
		throws PortalException {

		String curGroupOrganizationIdKey =
			_CURRENT_COMMERCE_ACCOUNT_ID_KEY + groupId;

		httpServletRequest = _portal.getOriginalServletRequest(
			httpServletRequest);

		HttpSession httpSession = httpServletRequest.getSession();

		httpSession.setAttribute(curGroupOrganizationIdKey, commerceAccountId);
	}

	private CommerceAccount _getSingleCommerceAccount(
			long groupId, HttpServletRequest httpServletRequest)
		throws PortalException {

		User user = _portal.getUser(httpServletRequest);

		if ((user == null) || user.isDefaultUser()) {
			return _commerceAccountLocalService.getGuestCommerceAccount(
				_portal.getCompanyId(httpServletRequest));
		}

		CommerceAccountGroupServiceConfiguration
			commerceAccountGroupServiceConfiguration =
				_configurationProvider.getGroupConfiguration(
					CommerceAccountGroupServiceConfiguration.class, groupId);

		if (commerceAccountGroupServiceConfiguration.commerceSiteType() ==
				CommerceAccountConstants.SITE_TYPE_B2C) {

			return _commerceAccountService.getPersonalCommerceAccount(
				user.getCompanyId(), user.getUserId());
		}

		List<CommerceAccount> userCommerceAccounts =
			_commerceAccountService.getUserCommerceAccounts(
				CommerceAccountConstants.DEFAULT_PARENT_ACCOUNT_ID,
				commerceAccountGroupServiceConfiguration.commerceSiteType(),
				StringPool.BLANK, QueryUtil.ALL_POS, QueryUtil.ALL_POS);

		if (userCommerceAccounts.size() == 1) {
			return userCommerceAccounts.get(0);
		}

		return null;
	}

	private static final String _COMMERCE_USER_PORTLET_ID =
		"com_liferay_commerce_user_web_internal_portlet_CommerceUserPortlet";

	private static final String _CURRENT_COMMERCE_ACCOUNT_ID_KEY =
		"LIFERAY_SHARED_CURRENT_COMMERCE_ACCOUNT_ID_";

	@Reference
	private CommerceAccountLocalService _commerceAccountLocalService;

	@Reference
	private CommerceAccountService _commerceAccountService;

	@Reference
	private ConfigurationProvider _configurationProvider;

	@Reference
	private Portal _portal;

	@Reference
	private PortletURLFactory _portletURLFactory;

}