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
import com.liferay.commerce.service.CommerceCountryService;
import com.liferay.commerce.service.CommerceRegionService;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.security.permission.resource.ModelResourcePermission;
import com.liferay.portal.kernel.service.UserLocalService;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.Portal;
import com.liferay.users.admin.configuration.UserFileUploadsConfiguration;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Alessio Antonio Rendina
 */
public class CommerceAccountUserDisplayContext
	extends BaseCommerceAccountDisplayContext {

	public CommerceAccountUserDisplayContext(
		CommerceAccountHelper commerceAccountHelper,
		CommerceAccountService commerceAccountService,
		CommerceCountryService commerceCountryService,
		CommerceRegionService commerceRegionService,
		HttpServletRequest httpServletRequest,
		ModelResourcePermission<CommerceAccount> modelResourcePermission,
		Portal portal,
		UserFileUploadsConfiguration userFileUploadsConfiguration,
		UserLocalService userLocalService) {

		super(
			commerceAccountHelper, commerceAccountService,
			commerceCountryService, commerceRegionService, httpServletRequest,
			modelResourcePermission, portal);

		_userFileUploadsConfiguration = userFileUploadsConfiguration;
		_userLocalService = userLocalService;
	}

	public AccountFilterImpl getAccountFilter() throws PortalException {
		AccountFilterImpl accountFilter = new AccountFilterImpl();

		accountFilter.setAccountId(getCurrentCommerceAccountId());

		return accountFilter;
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

	private final UserFileUploadsConfiguration _userFileUploadsConfiguration;
	private final UserLocalService _userLocalService;

}