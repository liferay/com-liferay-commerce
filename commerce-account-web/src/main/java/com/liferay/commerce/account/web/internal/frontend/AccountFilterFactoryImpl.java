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

package com.liferay.commerce.account.web.internal.frontend;

import com.liferay.commerce.frontend.Filter;
import com.liferay.commerce.frontend.FilterFactory;
import com.liferay.portal.kernel.util.ParamUtil;

import javax.servlet.http.HttpServletRequest;

import org.osgi.service.component.annotations.Component;

/**
 * @author Marco Leo
 * @author Alessio Antonio Rendina
 */
@Component(
	immediate = true,
	property = {
		"commerce.data.provider.key=" + CommerceAccountAddressClayTable.NAME,
		"commerce.data.provider.key=" + CommerceAccountClayTable.NAME,
		"commerce.data.provider.key=" + CommerceAccountOrganizationClayTable.NAME,
		"commerce.data.provider.key=" + CommerceAccountUserClayTable.NAME,
		"commerce.data.provider.key=" + CommerceAccountUserRolesClayTable.NAME
	},
	service = FilterFactory.class
)
public class AccountFilterFactoryImpl implements FilterFactory {

	@Override
	public Filter create(HttpServletRequest httpServletRequest) {
		AccountFilterImpl accountFilterImpl = new AccountFilterImpl();

		long accountId = ParamUtil.getLong(httpServletRequest, "accountId");
		long userId = ParamUtil.getLong(httpServletRequest, "userId");

		accountFilterImpl.setAccountId(accountId);
		accountFilterImpl.setUserId(userId);

		String keywords = ParamUtil.getString(httpServletRequest, "q");

		accountFilterImpl.setKeywords(keywords);

		return accountFilterImpl;
	}

}